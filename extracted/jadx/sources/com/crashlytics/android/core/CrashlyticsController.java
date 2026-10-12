package com.crashlytics.android.core;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.core.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStore;
import io.fabric.sdk.android.services.settings.PromptSettingsData;
import io.fabric.sdk.android.services.settings.SessionSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class CrashlyticsController {
    private static final int ANALYZER_VERSION = 1;
    private static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    static final String FATAL_SESSION_DIR = "fatal-sessions";
    private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
    static final String INVALID_CLS_CACHE_DIR = "invalidClsFiles";
    static final int MAX_INVALID_SESSIONS = 4;
    private static final int MAX_LOCAL_LOGGED_EXCEPTIONS = 64;
    static final int MAX_OPEN_SESSIONS = 8;
    static final int MAX_STACK_SIZE = 1024;
    static final String NONFATAL_SESSION_DIR = "nonfatal-sessions";
    static final int NUM_STACK_REPETITIONS_ALLOWED = 10;
    static final String SESSION_BEGIN_TAG = "BeginSession";
    static final String SESSION_EVENT_MISSING_BINARY_IMGS_TAG = "SessionMissingBinaryImages";
    static final String SESSION_FATAL_TAG = "SessionCrash";
    private static final int SESSION_ID_LENGTH = 35;
    static final String SESSION_NON_FATAL_TAG = "SessionEvent";
    private static final boolean SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT = false;
    private final AppData appData;
    private final CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    private final CrashlyticsCore crashlyticsCore;
    private final DevicePowerStateListener devicePowerStateListener;
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final FileStore fileStore;
    private final ReportUploader.HandlingExceptionCheck handlingExceptionCheck;
    private final HttpRequestFactory httpRequestFactory;
    private final IdManager idManager;
    private final LogFileDirectoryProvider logFileDirectoryProvider;
    private final LogFileManager logFileManager;
    private final PreferenceManager preferenceManager;
    private final ReportUploader.ReportFilesProvider reportFilesProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
    private final String unityVersion;
    static final FilenameFilter SESSION_FILE_FILTER = new FilenameFilter() { // from class: com.crashlytics.android.core.CrashlyticsController.1
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.length() == ClsFileOutputStream.SESSION_FILE_EXTENSION.length() + 35 && str.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new Comparator<File>() { // from class: com.crashlytics.android.core.CrashlyticsController.2
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            return file2.getName().compareTo(file.getName());
        }
    };
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new Comparator<File>() { // from class: com.crashlytics.android.core.CrashlyticsController.3
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    };
    static final FilenameFilter ANY_SESSION_FILENAME_FILTER = new FilenameFilter() { // from class: com.crashlytics.android.core.CrashlyticsController.4
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return CrashlyticsController.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    };
    private static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    static final String SESSION_USER_TAG = "SessionUser";
    static final String SESSION_APP_TAG = "SessionApp";
    static final String SESSION_OS_TAG = "SessionOS";
    static final String SESSION_DEVICE_TAG = "SessionDevice";
    private static final String[] INITIAL_SESSION_PART_TAGS = {SESSION_USER_TAG, SESSION_APP_TAG, SESSION_OS_TAG, SESSION_DEVICE_TAG};

    private static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return !CrashlyticsController.SESSION_FILE_FILTER.accept(file, str) && CrashlyticsController.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    }

    static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String str) {
            this.string = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.contains(this.string) && !str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION);
        }
    }

    static class InvalidPartFileFilter implements FilenameFilter {
        InvalidPartFileFilter() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(file, str) || str.contains(CrashlyticsController.SESSION_EVENT_MISSING_BINARY_IMGS_TAG);
        }
    }

    private static final class LogFileDirectoryProvider implements LogFileManager.DirectoryProvider {
        private static final String LOG_FILES_DIR = "log-files";
        private final FileStore rootFileStore;

        public LogFileDirectoryProvider(FileStore fileStore) {
            this.rootFileStore = fileStore;
        }

        @Override // com.crashlytics.android.core.LogFileManager.DirectoryProvider
        public File getLogFileDir() {
            File file = new File(this.rootFileStore.getFilesDir(), LOG_FILES_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
    }

    private static final class PrivacyDialogCheck implements ReportUploader.SendCheck {
        private final Kit kit;
        private final PreferenceManager preferenceManager;
        private final PromptSettingsData promptData;

        public PrivacyDialogCheck(Kit kit, PreferenceManager preferenceManager, PromptSettingsData promptSettingsData) {
            this.kit = kit;
            this.preferenceManager = preferenceManager;
            this.promptData = promptSettingsData;
        }

        @Override // com.crashlytics.android.core.ReportUploader.SendCheck
        public boolean canSendReports() {
            Activity currentActivity = this.kit.getFabric().getCurrentActivity();
            if (currentActivity == null || currentActivity.isFinishing()) {
                return true;
            }
            final CrashPromptDialog crashPromptDialogCreate = CrashPromptDialog.create(currentActivity, this.promptData, new CrashPromptDialog.AlwaysSendCallback() { // from class: com.crashlytics.android.core.CrashlyticsController.PrivacyDialogCheck.1
                @Override // com.crashlytics.android.core.CrashPromptDialog.AlwaysSendCallback
                public void sendUserReportsWithoutPrompting(boolean z) {
                    PrivacyDialogCheck.this.preferenceManager.setShouldAlwaysSendReports(z);
                }
            });
            currentActivity.runOnUiThread(new Runnable() { // from class: com.crashlytics.android.core.CrashlyticsController.PrivacyDialogCheck.2
                @Override // java.lang.Runnable
                public void run() {
                    crashPromptDialogCreate.show();
                }
            });
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Waiting for user opt-in.");
            crashPromptDialogCreate.await();
            return crashPromptDialogCreate.getOptIn();
        }
    }

    private final class ReportUploaderFilesProvider implements ReportUploader.ReportFilesProvider {
        private ReportUploaderFilesProvider() {
        }

        @Override // com.crashlytics.android.core.ReportUploader.ReportFilesProvider
        public File[] getCompleteSessionFiles() {
            return CrashlyticsController.this.listCompleteSessionFiles();
        }

        @Override // com.crashlytics.android.core.ReportUploader.ReportFilesProvider
        public File[] getInvalidSessionFiles() {
            return CrashlyticsController.this.getInvalidFilesDir().listFiles();
        }
    }

    private final class ReportUploaderHandlingExceptionCheck implements ReportUploader.HandlingExceptionCheck {
        private ReportUploaderHandlingExceptionCheck() {
        }

        @Override // com.crashlytics.android.core.ReportUploader.HandlingExceptionCheck
        public boolean isHandlingException() {
            return CrashlyticsController.this.isHandlingException();
        }
    }

    private static final class SendReportRunnable implements Runnable {
        private final Context context;
        private final Report report;
        private final ReportUploader reportUploader;

        public SendReportRunnable(Context context, Report report, ReportUploader reportUploader) {
            this.context = context;
            this.report = report;
            this.reportUploader = reportUploader;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CommonUtils.canTryConnection(this.context)) {
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Attempting to send crash report at time of crash...");
                this.reportUploader.forceUpload(this.report);
            }
        }
    }

    static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String str) {
            this.sessionId = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return (str.equals(new StringBuilder().append(this.sessionId).append(ClsFileOutputStream.SESSION_FILE_EXTENSION).toString()) || !str.contains(this.sessionId) || str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION)) ? false : true;
        }
    }

    CrashlyticsController(CrashlyticsCore crashlyticsCore, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker, HttpRequestFactory httpRequestFactory, IdManager idManager, PreferenceManager preferenceManager, FileStore fileStore, AppData appData, UnityVersionProvider unityVersionProvider) {
        this.crashlyticsCore = crashlyticsCore;
        this.backgroundWorker = crashlyticsBackgroundWorker;
        this.httpRequestFactory = httpRequestFactory;
        this.idManager = idManager;
        this.preferenceManager = preferenceManager;
        this.fileStore = fileStore;
        this.appData = appData;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        Context context = crashlyticsCore.getContext();
        this.logFileDirectoryProvider = new LogFileDirectoryProvider(fileStore);
        this.logFileManager = new LogFileManager(context, this.logFileDirectoryProvider);
        this.reportFilesProvider = new ReportUploaderFilesProvider();
        this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck();
        this.devicePowerStateListener = new DevicePowerStateListener(context);
        this.stackTraceTrimmingStrategy = new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10));
    }

    private void closeOpenSessions(File[] fileArr, int i, int i2) throws Throwable {
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Closing open sessions.");
        for (int i3 = i; i3 < fileArr.length; i3++) {
            File file = fileArr[i3];
            String sessionIdFromSessionFile = getSessionIdFromSessionFile(file);
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Closing session: " + sessionIdFromSessionFile);
            writeSessionPartsToSessionFile(file, sessionIdFromSessionFile, i2);
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream clsFileOutputStream) {
        if (clsFileOutputStream == null) {
            return;
        }
        try {
            clsFileOutputStream.closeInProgressStream();
        } catch (IOException e) {
            Fabric.getLogger().e(CrashlyticsCore.TAG, "Error closing session file stream in the presence of an exception", e);
        }
    }

    private static void copyToCodedOutputStream(InputStream inputStream, CodedOutputStream codedOutputStream, int i) throws IOException {
        int i2;
        byte[] bArr = new byte[i];
        int i3 = 0;
        while (i3 < bArr.length && (i2 = inputStream.read(bArr, i3, bArr.length - i3)) >= 0) {
            i3 += i2;
        }
        codedOutputStream.writeRawBytes(bArr);
    }

    private void deleteSessionPartFilesFor(String str) {
        for (File file : listSessionPartFilesFor(str)) {
            file.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCloseSessions(SessionSettingsData sessionSettingsData, boolean z) throws Exception {
        int i = z ? 1 : 0;
        trimOpenSessions(i + 8);
        File[] fileArrListSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (fileArrListSortedSessionBeginFiles.length <= i) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "No open sessions to be closed.");
            return;
        }
        writeSessionUser(getSessionIdFromSessionFile(fileArrListSortedSessionBeginFiles[i]));
        if (sessionSettingsData == null) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Unable to close session. Settings are not loaded.");
        } else {
            closeOpenSessions(fileArrListSortedSessionBeginFiles, i, sessionSettingsData.maxCustomExceptionEvents);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOpenSession() throws Exception {
        Date date = new Date();
        String string = new CLSUUID(this.idManager).toString();
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Opening a new session with ID " + string);
        writeBeginSession(string, date);
        writeSessionApp(string);
        writeSessionOS(string);
        writeSessionDevice(string);
        this.logFileManager.setCurrentSession(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doWriteExternalCrashEvent(SessionEventData sessionEventData) throws Throwable {
        ClsFileOutputStream clsFileOutputStream = null;
        CodedOutputStream codedOutputStreamNewInstance = null;
        try {
            try {
                String previousSessionId = getPreviousSessionId();
                if (previousSessionId == null) {
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "Tried to write a native crash while no session was open.", null);
                    CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
                    return;
                }
                recordFatalExceptionAnswersEvent(previousSessionId, String.format(Locale.US, "<native-crash [%s (%s)]>", sessionEventData.signal.code, sessionEventData.signal.name));
                ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), previousSessionId + (sessionEventData.binaryImages != null && sessionEventData.binaryImages.length > 0 ? SESSION_FATAL_TAG : SESSION_EVENT_MISSING_BINARY_IMGS_TAG));
                try {
                    codedOutputStreamNewInstance = CodedOutputStream.newInstance(clsFileOutputStream2);
                    NativeCrashWriter.writeNativeCrash(sessionEventData, new LogFileManager(this.crashlyticsCore.getContext(), this.logFileDirectoryProvider, previousSessionId), new MetaDataStore(getFilesDir()).readKeyData(previousSessionId), codedOutputStreamNewInstance);
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close fatal exception file output stream.");
                } catch (Exception e) {
                    e = e;
                    clsFileOutputStream = clsFileOutputStream2;
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "An error occurred in the native crash logger", e);
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                } catch (Throwable th) {
                    th = th;
                    clsFileOutputStream = clsFileOutputStream2;
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doWriteNonFatal(Date date, Thread thread, Throwable th) throws Throwable {
        String currentSessionId = getCurrentSessionId();
        if (currentSessionId == null) {
            Fabric.getLogger().e(CrashlyticsCore.TAG, "Tried to write a non-fatal exception while no session was open.", null);
            return;
        }
        recordLoggedExceptionAnswersEvent(currentSessionId, th.getClass().getName());
        ClsFileOutputStream clsFileOutputStream = null;
        CodedOutputStream codedOutputStreamNewInstance = null;
        try {
            try {
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Crashlytics is logging non-fatal exception \"" + th + "\" from thread " + thread.getName());
                ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), currentSessionId + SESSION_NON_FATAL_TAG + CommonUtils.padWithZerosToMaxIntWidth(this.eventCounter.getAndIncrement()));
                try {
                    codedOutputStreamNewInstance = CodedOutputStream.newInstance(clsFileOutputStream2);
                    writeSessionEvent(codedOutputStreamNewInstance, date, thread, th, "error", false);
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close non-fatal file output stream.");
                } catch (Exception e) {
                    e = e;
                    clsFileOutputStream = clsFileOutputStream2;
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "An error occurred in the non-fatal exception logger", e);
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                } catch (Throwable th2) {
                    th = th2;
                    clsFileOutputStream = clsFileOutputStream2;
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            try {
                trimSessionEventFiles(currentSessionId, 64);
            } catch (Exception e3) {
                Fabric.getLogger().e(CrashlyticsCore.TAG, "An error occurred when trimming non-fatal files.", e3);
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private File[] ensureFileArrayNotNull(File[] fileArr) {
        return fileArr == null ? new File[0] : fileArr;
    }

    private CreateReportSpiCall getCreateReportSpiCall(String str) {
        return new DefaultCreateReportSpiCall(this.crashlyticsCore, CommonUtils.getStringsFileValue(this.crashlyticsCore.getContext(), CRASHLYTICS_API_ENDPOINT), str, this.httpRequestFactory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentSessionId() {
        File[] fileArrListSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (fileArrListSortedSessionBeginFiles.length > 0) {
            return getSessionIdFromSessionFile(fileArrListSortedSessionBeginFiles[0]);
        }
        return null;
    }

    private String getPreviousSessionId() {
        File[] fileArrListSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (fileArrListSortedSessionBeginFiles.length > 1) {
            return getSessionIdFromSessionFile(fileArrListSortedSessionBeginFiles[1]);
        }
        return null;
    }

    static String getSessionIdFromSessionFile(File file) {
        return file.getName().substring(0, 35);
    }

    private File[] getTrimmedNonFatalFiles(String str, File[] fileArr, int i) {
        if (fileArr.length <= i) {
            return fileArr;
        }
        Fabric.getLogger().d(CrashlyticsCore.TAG, String.format(Locale.US, "Trimming down to %d logged exceptions.", Integer.valueOf(i)));
        trimSessionEventFiles(str, i);
        return listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
    }

    private UserMetaData getUserMetaData(String str) {
        return isHandlingException() ? new UserMetaData(this.crashlyticsCore.getUserIdentifier(), this.crashlyticsCore.getUserName(), this.crashlyticsCore.getUserEmail()) : new MetaDataStore(getFilesDir()).readUserData(str);
    }

    private File[] listFiles(File file) {
        return ensureFileArrayNotNull(file.listFiles());
    }

    private File[] listFilesMatching(File file, FilenameFilter filenameFilter) {
        return ensureFileArrayNotNull(file.listFiles(filenameFilter));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File[] listFilesMatching(FilenameFilter filenameFilter) {
        return listFilesMatching(getFilesDir(), filenameFilter);
    }

    private File[] listSessionPartFilesFor(String str) {
        return listFilesMatching(new SessionPartFileFilter(str));
    }

    private File[] listSortedSessionBeginFiles() {
        File[] fileArrListSessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(fileArrListSessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return fileArrListSessionBeginFiles;
    }

    private static void recordFatalExceptionAnswersEvent(String str, String str2) {
        Answers answers = (Answers) Fabric.getKit(Answers.class);
        if (answers == null) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Answers is not available");
        } else {
            answers.onException(new Crash.FatalException(str, str2));
        }
    }

    private static void recordLoggedExceptionAnswersEvent(String str, String str2) {
        Answers answers = (Answers) Fabric.getKit(Answers.class);
        if (answers == null) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Answers is not available");
        } else {
            answers.onException(new Crash.LoggedException(str, str2));
        }
    }

    private void retainSessions(File[] fileArr, Set<String> set) {
        for (File file : fileArr) {
            String name = file.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(name);
            if (!matcher.matches()) {
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Deleting unknown file: " + name);
                file.delete();
                return;
            } else {
                if (!set.contains(matcher.group(1))) {
                    Fabric.getLogger().d(CrashlyticsCore.TAG, "Trimming session file: " + name);
                    file.delete();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSessionReports(SettingsData settingsData) {
        if (settingsData == null) {
            Fabric.getLogger().w(CrashlyticsCore.TAG, "Cannot send reports. Settings are unavailable.");
            return;
        }
        Context context = this.crashlyticsCore.getContext();
        ReportUploader reportUploader = new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(settingsData.appData.reportsUrl), this.reportFilesProvider, this.handlingExceptionCheck);
        for (File file : listCompleteSessionFiles()) {
            this.backgroundWorker.submit(new SendReportRunnable(context, new SessionReport(file, SEND_AT_CRASHTIME_HEADER), reportUploader));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldPromptUserBeforeSendingCrashReports(SettingsData settingsData) {
        return (settingsData == null || !settingsData.featuresData.promptEnabled || this.preferenceManager.shouldAlwaysSendReports()) ? false : true;
    }

    private void synthesizeSessionFile(File file, String str, File[] fileArr, File file2) throws Throwable {
        boolean z = file2 != null;
        File fatalSessionFilesDir = z ? getFatalSessionFilesDir() : getNonFatalSessionFilesDir();
        if (!fatalSessionFilesDir.exists()) {
            fatalSessionFilesDir.mkdirs();
        }
        ClsFileOutputStream clsFileOutputStream = null;
        CodedOutputStream codedOutputStreamNewInstance = null;
        try {
            try {
                ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(fatalSessionFilesDir, str);
                try {
                    codedOutputStreamNewInstance = CodedOutputStream.newInstance(clsFileOutputStream2);
                    Fabric.getLogger().d(CrashlyticsCore.TAG, "Collecting SessionStart data for session ID " + str);
                    writeToCosFromFile(codedOutputStreamNewInstance, file);
                    codedOutputStreamNewInstance.writeUInt64(4, new Date().getTime() / 1000);
                    codedOutputStreamNewInstance.writeBool(5, z);
                    codedOutputStreamNewInstance.writeUInt32(11, 1);
                    codedOutputStreamNewInstance.writeEnum(12, 3);
                    writeInitialPartsTo(codedOutputStreamNewInstance, str);
                    writeNonFatalEventsTo(codedOutputStreamNewInstance, fileArr, str);
                    if (z) {
                        writeToCosFromFile(codedOutputStreamNewInstance, file2);
                    }
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Error flushing session file stream");
                    if (0 != 0) {
                        closeWithoutRenamingOrLog(clsFileOutputStream2);
                    } else {
                        CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close CLS file");
                    }
                } catch (Exception e) {
                    e = e;
                    clsFileOutputStream = clsFileOutputStream2;
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "Failed to write session file for session ID: " + str, e);
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Error flushing session file stream");
                    if (1 != 0) {
                        closeWithoutRenamingOrLog(clsFileOutputStream);
                    } else {
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
                    }
                } catch (Throwable th) {
                    th = th;
                    clsFileOutputStream = clsFileOutputStream2;
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Error flushing session file stream");
                    if (0 != 0) {
                        closeWithoutRenamingOrLog(clsFileOutputStream);
                    } else {
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void trimInvalidSessionFiles() {
        File invalidFilesDir = getInvalidFilesDir();
        if (invalidFilesDir.exists()) {
            File[] fileArrListFilesMatching = listFilesMatching(invalidFilesDir, new InvalidPartFileFilter());
            Arrays.sort(fileArrListFilesMatching, Collections.reverseOrder());
            HashSet hashSet = new HashSet();
            for (int i = 0; i < fileArrListFilesMatching.length && hashSet.size() < 4; i++) {
                hashSet.add(getSessionIdFromSessionFile(fileArrListFilesMatching[i]));
            }
            retainSessions(listFiles(invalidFilesDir), hashSet);
        }
    }

    private void trimOpenSessions(int i) {
        HashSet hashSet = new HashSet();
        File[] fileArrListSortedSessionBeginFiles = listSortedSessionBeginFiles();
        int iMin = Math.min(i, fileArrListSortedSessionBeginFiles.length);
        for (int i2 = 0; i2 < iMin; i2++) {
            hashSet.add(getSessionIdFromSessionFile(fileArrListSortedSessionBeginFiles[i2]));
        }
        this.logFileManager.discardOldLogFiles(hashSet);
        retainSessions(listFilesMatching(new AnySessionPartFileFilter()), hashSet);
    }

    private void trimSessionEventFiles(String str, int i) {
        Utils.capFileCount(getFilesDir(), new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG), i, SMALLEST_FILE_NAME_FIRST);
    }

    private void writeBeginSession(String str, Date date) throws Exception {
        ClsFileOutputStream clsFileOutputStream = null;
        CodedOutputStream codedOutputStreamNewInstance = null;
        try {
            ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), str + SESSION_BEGIN_TAG);
            try {
                codedOutputStreamNewInstance = CodedOutputStream.newInstance(clsFileOutputStream2);
                SessionProtobufHelper.writeBeginSession(codedOutputStreamNewInstance, str, String.format(Locale.US, GENERATOR_FORMAT, this.crashlyticsCore.getVersion()), date.getTime() / 1000);
                CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close begin session file.");
            } catch (Throwable th) {
                th = th;
                clsFileOutputStream = clsFileOutputStream2;
                CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close begin session file.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeFatal(Date date, Thread thread, Throwable th) throws Throwable {
        ClsFileOutputStream clsFileOutputStream = null;
        CodedOutputStream codedOutputStreamNewInstance = null;
        try {
            try {
                String currentSessionId = getCurrentSessionId();
                if (currentSessionId == null) {
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "Tried to write a fatal exception while no session was open.", null);
                    CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
                } else {
                    recordFatalExceptionAnswersEvent(currentSessionId, th.getClass().getName());
                    ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), currentSessionId + SESSION_FATAL_TAG);
                    try {
                        codedOutputStreamNewInstance = CodedOutputStream.newInstance(clsFileOutputStream2);
                        writeSessionEvent(codedOutputStreamNewInstance, date, thread, th, EVENT_TYPE_CRASH, true);
                        CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close fatal exception file output stream.");
                        clsFileOutputStream = clsFileOutputStream2;
                    } catch (Exception e) {
                        e = e;
                        clsFileOutputStream = clsFileOutputStream2;
                        Fabric.getLogger().e(CrashlyticsCore.TAG, "An error occurred in the fatal exception logger", e);
                        CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    } catch (Throwable th2) {
                        th = th2;
                        clsFileOutputStream = clsFileOutputStream2;
                        CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private void writeInitialPartsTo(CodedOutputStream codedOutputStream, String str) throws Throwable {
        for (String str2 : INITIAL_SESSION_PART_TAGS) {
            File[] fileArrListFilesMatching = listFilesMatching(new FileNameContainsFilter(str + str2));
            if (fileArrListFilesMatching.length == 0) {
                Fabric.getLogger().e(CrashlyticsCore.TAG, "Can't find " + str2 + " data for session ID " + str, null);
            } else {
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Collecting " + str2 + " data for session ID " + str);
                writeToCosFromFile(codedOutputStream, fileArrListFilesMatching[0]);
            }
        }
    }

    private static void writeNonFatalEventsTo(CodedOutputStream codedOutputStream, File[] fileArr, String str) throws Throwable {
        Arrays.sort(fileArr, CommonUtils.FILE_MODIFIED_COMPARATOR);
        for (File file : fileArr) {
            try {
                Fabric.getLogger().d(CrashlyticsCore.TAG, String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", str, file.getName()));
                writeToCosFromFile(codedOutputStream, file);
            } catch (Exception e) {
                Fabric.getLogger().e(CrashlyticsCore.TAG, "Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeSessionApp(String str) throws Exception {
        ClsFileOutputStream clsFileOutputStream = null;
        CodedOutputStream codedOutputStreamNewInstance = null;
        try {
            ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), str + SESSION_APP_TAG);
            try {
                codedOutputStreamNewInstance = CodedOutputStream.newInstance(clsFileOutputStream2);
                SessionProtobufHelper.writeSessionApp(codedOutputStreamNewInstance, this.idManager.getAppIdentifier(), this.appData.apiKey, this.appData.versionCode, this.appData.versionName, this.idManager.getAppInstallIdentifier(), DeliveryMechanism.determineFrom(this.appData.installerPackageName).getId(), this.unityVersion);
                CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session app file.");
                CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close session app file.");
            } catch (Throwable th) {
                th = th;
                clsFileOutputStream = clsFileOutputStream2;
                CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session app file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session app file.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void writeSessionDevice(String str) throws Exception {
        ClsFileOutputStream clsFileOutputStream = null;
        CodedOutputStream codedOutputStreamNewInstance = null;
        try {
            ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), str + SESSION_DEVICE_TAG);
            try {
                codedOutputStreamNewInstance = CodedOutputStream.newInstance(clsFileOutputStream2);
                Context context = this.crashlyticsCore.getContext();
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                SessionProtobufHelper.writeSessionDevice(codedOutputStreamNewInstance, this.idManager.getDeviceUUID(), CommonUtils.getCpuArchitectureInt(), Build.MODEL, Runtime.getRuntime().availableProcessors(), CommonUtils.getTotalRamInBytes(), ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()), CommonUtils.isEmulator(context), this.idManager.getDeviceIdentifiers(), CommonUtils.getDeviceState(context), Build.MANUFACTURER, Build.PRODUCT);
                CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush session device info.");
                CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close session device file.");
            } catch (Throwable th) {
                th = th;
                clsFileOutputStream = clsFileOutputStream2;
                CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush session device info.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session device file.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void writeSessionEvent(CodedOutputStream codedOutputStream, Date date, Thread thread, Throwable th, String str, boolean z) throws Exception {
        Thread[] threadArr;
        Map<String, String> attributes;
        TrimmedThrowableData trimmedThrowableData = new TrimmedThrowableData(th, this.stackTraceTrimmingStrategy);
        Context context = this.crashlyticsCore.getContext();
        long time = date.getTime() / 1000;
        Float batteryLevel = CommonUtils.getBatteryLevel(context);
        int batteryVelocity = CommonUtils.getBatteryVelocity(context, this.devicePowerStateListener.isPowerConnected());
        boolean proximitySensorEnabled = CommonUtils.getProximitySensorEnabled(context);
        int i = context.getResources().getConfiguration().orientation;
        long totalRamInBytes = CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(context);
        long jCalculateUsedDiskSpaceInBytes = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        ActivityManager.RunningAppProcessInfo appProcessInfo = CommonUtils.getAppProcessInfo(context.getPackageName(), context);
        LinkedList linkedList = new LinkedList();
        StackTraceElement[] stackTraceElementArr = trimmedThrowableData.stacktrace;
        String str2 = this.appData.buildId;
        String appIdentifier = this.idManager.getAppIdentifier();
        if (z) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            threadArr = new Thread[allStackTraces.size()];
            int i2 = 0;
            for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                threadArr[i2] = entry.getKey();
                linkedList.add(this.stackTraceTrimmingStrategy.getTrimmedStackTrace(entry.getValue()));
                i2++;
            }
        } else {
            threadArr = new Thread[0];
        }
        if (CommonUtils.getBooleanResourceValue(context, COLLECT_CUSTOM_KEYS, true)) {
            attributes = this.crashlyticsCore.getAttributes();
            if (attributes != null && attributes.size() > 1) {
                attributes = new TreeMap(attributes);
            }
        } else {
            attributes = new TreeMap<>();
        }
        SessionProtobufHelper.writeSessionEvent(codedOutputStream, time, str, trimmedThrowableData, thread, stackTraceElementArr, threadArr, linkedList, attributes, this.logFileManager, appProcessInfo, i, appIdentifier, str2, batteryLevel, batteryVelocity, proximitySensorEnabled, totalRamInBytes, jCalculateUsedDiskSpaceInBytes);
    }

    private void writeSessionOS(String str) throws Exception {
        ClsFileOutputStream clsFileOutputStream = null;
        CodedOutputStream codedOutputStreamNewInstance = null;
        try {
            ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), str + SESSION_OS_TAG);
            try {
                codedOutputStreamNewInstance = CodedOutputStream.newInstance(clsFileOutputStream2);
                SessionProtobufHelper.writeSessionOS(codedOutputStreamNewInstance, CommonUtils.isRooted(this.crashlyticsCore.getContext()));
                CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session OS file.");
                CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close session OS file.");
            } catch (Throwable th) {
                th = th;
                clsFileOutputStream = clsFileOutputStream2;
                CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush to session OS file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session OS file.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void writeSessionPartsToSessionFile(File file, String str, int i) throws Throwable {
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Collecting session parts for ID " + str);
        File[] fileArrListFilesMatching = listFilesMatching(new FileNameContainsFilter(str + SESSION_FATAL_TAG));
        boolean z = fileArrListFilesMatching != null && fileArrListFilesMatching.length > 0;
        Fabric.getLogger().d(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has fatal exception: %s", str, Boolean.valueOf(z)));
        File[] fileArrListFilesMatching2 = listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
        boolean z2 = fileArrListFilesMatching2 != null && fileArrListFilesMatching2.length > 0;
        Fabric.getLogger().d(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has non-fatal exceptions: %s", str, Boolean.valueOf(z2)));
        if (z || z2) {
            synthesizeSessionFile(file, str, getTrimmedNonFatalFiles(str, fileArrListFilesMatching2, i), z ? fileArrListFilesMatching[0] : null);
        } else {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "No events present for session ID " + str);
        }
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Removing session part files for ID " + str);
        deleteSessionPartFilesFor(str);
    }

    private void writeSessionUser(String str) throws Exception {
        ClsFileOutputStream clsFileOutputStream = null;
        CodedOutputStream codedOutputStreamNewInstance = null;
        try {
            ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), str + SESSION_USER_TAG);
            try {
                codedOutputStreamNewInstance = CodedOutputStream.newInstance(clsFileOutputStream2);
                UserMetaData userMetaData = getUserMetaData(str);
                if (userMetaData.isEmpty()) {
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush session user file.");
                    CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close session user file.");
                } else {
                    SessionProtobufHelper.writeSessionUser(codedOutputStreamNewInstance, userMetaData.id, userMetaData.name, userMetaData.email);
                    CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush session user file.");
                    CommonUtils.closeOrLog(clsFileOutputStream2, "Failed to close session user file.");
                }
            } catch (Throwable th) {
                th = th;
                clsFileOutputStream = clsFileOutputStream2;
                CommonUtils.flushOrLog(codedOutputStreamNewInstance, "Failed to flush session user file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void writeToCosFromFile(CodedOutputStream codedOutputStream, File file) throws Throwable {
        if (!file.exists()) {
            Fabric.getLogger().e(CrashlyticsCore.TAG, "Tried to include a file that doesn't exist: " + file.getName(), null);
            return;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                copyToCodedOutputStream(fileInputStream2, codedOutputStream, (int) file.length());
                CommonUtils.closeOrLog(fileInputStream2, "Failed to close file input stream.");
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                CommonUtils.closeOrLog(fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    void cacheKeyData(final Map<String, String> map) {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.crashlytics.android.core.CrashlyticsController.10
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeKeyData(CrashlyticsController.this.getCurrentSessionId(), map);
                return null;
            }
        });
    }

    void cacheUserData(final String str, final String str2, final String str3) {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.crashlytics.android.core.CrashlyticsController.9
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeUserData(CrashlyticsController.this.getCurrentSessionId(), new UserMetaData(str, str2, str3));
                return null;
            }
        });
    }

    void cleanInvalidTempFiles() {
        this.backgroundWorker.submit(new Runnable() { // from class: com.crashlytics.android.core.CrashlyticsController.13
            @Override // java.lang.Runnable
            public void run() {
                CrashlyticsController.this.doCleanInvalidTempFiles(CrashlyticsController.this.listFilesMatching(new InvalidPartFileFilter()));
            }
        });
    }

    void doCleanInvalidTempFiles(File[] fileArr) {
        final HashSet hashSet = new HashSet();
        for (File file : fileArr) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Found invalid session part file: " + file);
            hashSet.add(getSessionIdFromSessionFile(file));
        }
        if (hashSet.isEmpty()) {
            return;
        }
        File invalidFilesDir = getInvalidFilesDir();
        if (!invalidFilesDir.exists()) {
            invalidFilesDir.mkdir();
        }
        for (File file2 : listFilesMatching(new FilenameFilter() { // from class: com.crashlytics.android.core.CrashlyticsController.14
            @Override // java.io.FilenameFilter
            public boolean accept(File file3, String str) {
                if (str.length() < 35) {
                    return false;
                }
                return hashSet.contains(str.substring(0, 35));
            }
        })) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Moving session file: " + file2);
            if (!file2.renameTo(new File(invalidFilesDir, file2.getName()))) {
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Could not move session file. Deleting " + file2);
                file2.delete();
            }
        }
        trimInvalidSessionFiles();
    }

    void doCloseSessions(SessionSettingsData sessionSettingsData) throws Exception {
        doCloseSessions(sessionSettingsData, false);
    }

    void enableExceptionHandling(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        openSession();
        this.crashHandler = new CrashlyticsUncaughtExceptionHandler(new CrashlyticsUncaughtExceptionHandler.CrashListener() { // from class: com.crashlytics.android.core.CrashlyticsController.5
            @Override // com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.CrashListener
            public void onUncaughtException(Thread thread, Throwable th) {
                CrashlyticsController.this.handleUncaughtException(thread, th);
            }
        }, uncaughtExceptionHandler);
        Thread.setDefaultUncaughtExceptionHandler(this.crashHandler);
    }

    boolean finalizeSessions(final SessionSettingsData sessionSettingsData) {
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() { // from class: com.crashlytics.android.core.CrashlyticsController.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                if (CrashlyticsController.this.isHandlingException()) {
                    Fabric.getLogger().d(CrashlyticsCore.TAG, "Skipping session finalization because a crash has already occurred.");
                    return Boolean.FALSE;
                }
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Finalizing previously open sessions.");
                CrashlyticsController.this.doCloseSessions(sessionSettingsData, true);
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Closed all previously open sessions");
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    File getFatalSessionFilesDir() {
        return new File(getFilesDir(), FATAL_SESSION_DIR);
    }

    File getFilesDir() {
        return this.fileStore.getFilesDir();
    }

    File getInvalidFilesDir() {
        return new File(getFilesDir(), INVALID_CLS_CACHE_DIR);
    }

    File getNonFatalSessionFilesDir() {
        return new File(getFilesDir(), NONFATAL_SESSION_DIR);
    }

    synchronized void handleUncaughtException(final Thread thread, final Throwable th) {
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Crashlytics is handling uncaught exception \"" + th + "\" from thread " + thread.getName());
        this.devicePowerStateListener.dispose();
        final Date date = new Date();
        this.backgroundWorker.submitAndWait(new Callable<Void>() { // from class: com.crashlytics.android.core.CrashlyticsController.6
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                CrashlyticsController.this.crashlyticsCore.createCrashMarker();
                CrashlyticsController.this.writeFatal(date, thread, th);
                SettingsData settingsDataAwaitSettingsData = Settings.getInstance().awaitSettingsData();
                SessionSettingsData sessionSettingsData = settingsDataAwaitSettingsData != null ? settingsDataAwaitSettingsData.sessionData : null;
                CrashlyticsController.this.doCloseSessions(sessionSettingsData);
                CrashlyticsController.this.doOpenSession();
                if (sessionSettingsData != null) {
                    CrashlyticsController.this.trimSessionFiles(sessionSettingsData.maxCompleteSessionsCount);
                }
                if (!CrashlyticsController.this.shouldPromptUserBeforeSendingCrashReports(settingsDataAwaitSettingsData)) {
                    CrashlyticsController.this.sendSessionReports(settingsDataAwaitSettingsData);
                }
                return null;
            }
        });
    }

    boolean hasOpenSession() {
        return listSessionBeginFiles().length > 0;
    }

    boolean isHandlingException() {
        return this.crashHandler != null && this.crashHandler.isHandlingException();
    }

    File[] listCompleteSessionFiles() {
        LinkedList linkedList = new LinkedList();
        Collections.addAll(linkedList, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
        return (File[]) linkedList.toArray(new File[linkedList.size()]);
    }

    File[] listSessionBeginFiles() {
        return listFilesMatching(new FileNameContainsFilter(SESSION_BEGIN_TAG));
    }

    void openSession() {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.crashlytics.android.core.CrashlyticsController.11
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                CrashlyticsController.this.doOpenSession();
                return null;
            }
        });
    }

    void submitAllReports(float f, SettingsData settingsData) {
        if (settingsData == null) {
            Fabric.getLogger().w(CrashlyticsCore.TAG, "Could not send reports. Settings are not available.");
        } else {
            new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(settingsData.appData.reportsUrl), this.reportFilesProvider, this.handlingExceptionCheck).uploadReports(f, shouldPromptUserBeforeSendingCrashReports(settingsData) ? new PrivacyDialogCheck(this.crashlyticsCore, this.preferenceManager, settingsData.promptData) : new ReportUploader.AlwaysSendCheck());
        }
    }

    void trimSessionFiles(int i) {
        int iCapFileCount = i - Utils.capFileCount(getFatalSessionFilesDir(), i, SMALLEST_FILE_NAME_FIRST);
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, iCapFileCount - Utils.capFileCount(getNonFatalSessionFilesDir(), iCapFileCount, SMALLEST_FILE_NAME_FIRST), SMALLEST_FILE_NAME_FIRST);
    }

    void writeExternalCrashEvent(final SessionEventData sessionEventData) {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.crashlytics.android.core.CrashlyticsController.15
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                if (CrashlyticsController.this.isHandlingException()) {
                    return null;
                }
                CrashlyticsController.this.doWriteExternalCrashEvent(sessionEventData);
                return null;
            }
        });
    }

    void writeNonFatalException(final Thread thread, final Throwable th) {
        final Date date = new Date();
        this.backgroundWorker.submit(new Runnable() { // from class: com.crashlytics.android.core.CrashlyticsController.8
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                if (CrashlyticsController.this.isHandlingException()) {
                    return;
                }
                CrashlyticsController.this.doWriteNonFatal(date, thread, th);
            }
        });
    }

    void writeToLog(final long j, final String str) {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.crashlytics.android.core.CrashlyticsController.7
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                if (CrashlyticsController.this.isHandlingException()) {
                    return null;
                }
                CrashlyticsController.this.logFileManager.writeToLog(j, str);
                return null;
            }
        });
    }
}
