package com.crashlytics.android.core;

import android.content.Context;
import android.util.Log;
import com.crashlytics.android.core.internal.CrashEventDataProvider;
import com.crashlytics.android.core.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.PriorityCallable;
import io.fabric.sdk.android.services.concurrency.Task;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@DependsOn({CrashEventDataProvider.class})
public class CrashlyticsCore extends Kit<Void> {
    static final float CLS_DEFAULT_PROCESS_DELAY = 1.0f;
    static final String CRASHLYTICS_REQUIRE_BUILD_ID = "com.crashlytics.RequireBuildId";
    static final boolean CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT = true;
    static final String CRASH_MARKER_FILE_NAME = "crash_marker";
    static final int DEFAULT_MAIN_HANDLER_TIMEOUT_SEC = 4;
    private static final String INITIALIZATION_MARKER_FILE_NAME = "initialization_marker";
    static final int MAX_ATTRIBUTES = 64;
    static final int MAX_ATTRIBUTE_SIZE = 1024;
    private static final String MISSING_BUILD_ID_MSG = "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.";
    private static final String PREFERENCE_STORE_NAME = "com.crashlytics.android.core.CrashlyticsCore";
    public static final String TAG = "CrashlyticsCore";
    private final ConcurrentHashMap<String, String> attributes;
    private CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsController controller;
    private CrashlyticsFileMarker crashMarker;
    private float delay;
    private boolean disabled;
    private CrashEventDataProvider externalCrashEventDataProvider;
    private HttpRequestFactory httpRequestFactory;
    private CrashlyticsFileMarker initializationMarker;
    private CrashlyticsListener listener;
    private final PinningInfoProvider pinningInfo;
    private final long startTime;
    private String userEmail;
    private String userId;
    private String userName;

    public static class Builder {
        private float delay = -1.0f;
        private boolean disabled = false;
        private CrashlyticsListener listener;
        private PinningInfoProvider pinningInfoProvider;

        public CrashlyticsCore build() {
            if (this.delay < 0.0f) {
                this.delay = 1.0f;
            }
            return new CrashlyticsCore(this.delay, this.listener, this.pinningInfoProvider, this.disabled);
        }

        public Builder delay(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("delay must be greater than 0");
            }
            if (this.delay > 0.0f) {
                throw new IllegalStateException("delay already set.");
            }
            this.delay = f;
            return this;
        }

        public Builder disabled(boolean z) {
            this.disabled = z;
            return this;
        }

        public Builder listener(CrashlyticsListener crashlyticsListener) {
            if (crashlyticsListener == null) {
                throw new IllegalArgumentException("listener must not be null.");
            }
            if (this.listener != null) {
                throw new IllegalStateException("listener already set.");
            }
            this.listener = crashlyticsListener;
            return this;
        }

        @Deprecated
        public Builder pinningInfo(PinningInfoProvider pinningInfoProvider) {
            if (pinningInfoProvider == null) {
                throw new IllegalArgumentException("pinningInfoProvider must not be null.");
            }
            if (this.pinningInfoProvider != null) {
                throw new IllegalStateException("pinningInfoProvider already set.");
            }
            this.pinningInfoProvider = pinningInfoProvider;
            return this;
        }
    }

    private static final class CrashMarkerCheck implements Callable<Boolean> {
        private final CrashlyticsFileMarker crashMarker;

        public CrashMarkerCheck(CrashlyticsFileMarker crashlyticsFileMarker) {
            this.crashMarker = crashlyticsFileMarker;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            if (!this.crashMarker.isPresent()) {
                return Boolean.FALSE;
            }
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Found previous crash marker.");
            this.crashMarker.remove();
            return Boolean.TRUE;
        }
    }

    private static final class NoOpListener implements CrashlyticsListener {
        private NoOpListener() {
        }

        @Override // com.crashlytics.android.core.CrashlyticsListener
        public void crashlyticsDidDetectCrashDuringPreviousExecution() {
        }
    }

    public CrashlyticsCore() {
        this(1.0f, null, null, false);
    }

    CrashlyticsCore(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z) {
        this(f, crashlyticsListener, pinningInfoProvider, z, ExecutorUtils.buildSingleThreadExecutorService("Crashlytics Exception Handler"));
    }

    CrashlyticsCore(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z, ExecutorService executorService) {
        this.userId = null;
        this.userEmail = null;
        this.userName = null;
        this.delay = f;
        this.listener = crashlyticsListener == null ? new NoOpListener() : crashlyticsListener;
        this.pinningInfo = pinningInfoProvider;
        this.disabled = z;
        this.backgroundWorker = new CrashlyticsBackgroundWorker(executorService);
        this.attributes = new ConcurrentHashMap<>();
        this.startTime = System.currentTimeMillis();
    }

    private void checkForPreviousCrash() {
        if (Boolean.TRUE.equals((Boolean) this.backgroundWorker.submitAndWait(new CrashMarkerCheck(this.crashMarker)))) {
            try {
                this.listener.crashlyticsDidDetectCrashDuringPreviousExecution();
            } catch (Exception e) {
                Fabric.getLogger().e(TAG, "Exception thrown by CrashlyticsListener while notifying of previous crash.", e);
            }
        }
    }

    private void doLog(int i, String str, String str2) {
        if (!this.disabled && ensureFabricWithCalled("prior to logging messages.")) {
            this.controller.writeToLog(System.currentTimeMillis() - this.startTime, formatLogMessage(i, str, str2));
        }
    }

    private static boolean ensureFabricWithCalled(String str) {
        CrashlyticsCore crashlyticsCore = getInstance();
        if (crashlyticsCore != null && crashlyticsCore.controller != null) {
            return true;
        }
        Fabric.getLogger().e(TAG, "Crashlytics must be initialized by calling Fabric.with(Context) " + str, null);
        return false;
    }

    private void finishInitSynchronously() {
        PriorityCallable<Void> priorityCallable = new PriorityCallable<Void>() { // from class: com.crashlytics.android.core.CrashlyticsCore.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                return CrashlyticsCore.this.doInBackground();
            }

            @Override // io.fabric.sdk.android.services.concurrency.PriorityTask, io.fabric.sdk.android.services.concurrency.PriorityProvider
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        Iterator<Task> it = getDependencies().iterator();
        while (it.hasNext()) {
            priorityCallable.addDependency(it.next());
        }
        Future futureSubmit = getFabric().getExecutorService().submit(priorityCallable);
        Fabric.getLogger().d(TAG, "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            futureSubmit.get(4L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Fabric.getLogger().e(TAG, "Crashlytics was interrupted during initialization.", e);
        } catch (ExecutionException e2) {
            Fabric.getLogger().e(TAG, "Problem encountered during Crashlytics initialization.", e2);
        } catch (TimeoutException e3) {
            Fabric.getLogger().e(TAG, "Crashlytics timed out during initialization.", e3);
        }
    }

    private static String formatLogMessage(int i, String str, String str2) {
        return CommonUtils.logPriorityToString(i) + "/" + str + " " + str2;
    }

    public static CrashlyticsCore getInstance() {
        return (CrashlyticsCore) Fabric.getKit(CrashlyticsCore.class);
    }

    static boolean isBuildIdValid(String str, boolean z) {
        if (!z) {
            Fabric.getLogger().d(TAG, "Configured not to require a build ID.");
            return true;
        }
        if (!CommonUtils.isNullOrEmpty(str)) {
            return true;
        }
        Log.e(TAG, ".");
        Log.e(TAG, ".     |  | ");
        Log.e(TAG, ".     |  |");
        Log.e(TAG, ".     |  |");
        Log.e(TAG, ".   \\ |  | /");
        Log.e(TAG, ".    \\    /");
        Log.e(TAG, ".     \\  /");
        Log.e(TAG, ".      \\/");
        Log.e(TAG, ".");
        Log.e(TAG, MISSING_BUILD_ID_MSG);
        Log.e(TAG, ".");
        Log.e(TAG, ".      /\\");
        Log.e(TAG, ".     /  \\");
        Log.e(TAG, ".    /    \\");
        Log.e(TAG, ".   / |  | \\");
        Log.e(TAG, ".     |  |");
        Log.e(TAG, ".     |  |");
        Log.e(TAG, ".     |  |");
        Log.e(TAG, ".");
        return false;
    }

    private static String sanitizeAttribute(String str) {
        if (str == null) {
            return str;
        }
        String strTrim = str.trim();
        return strTrim.length() > 1024 ? strTrim.substring(0, 1024) : strTrim;
    }

    public void crash() {
        new CrashTest().indexOutOfBounds();
    }

    void createCrashMarker() {
        this.crashMarker.create();
    }

    boolean didPreviousInitializationFail() {
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() { // from class: com.crashlytics.android.core.CrashlyticsCore.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(CrashlyticsCore.this.initializationMarker.isPresent());
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.fabric.sdk.android.Kit
    public Void doInBackground() {
        markInitializationStarted();
        SessionEventData externalCrashEventData = getExternalCrashEventData();
        if (externalCrashEventData != null) {
            this.controller.writeExternalCrashEvent(externalCrashEventData);
        }
        this.controller.cleanInvalidTempFiles();
        try {
            SettingsData settingsDataAwaitSettingsData = Settings.getInstance().awaitSettingsData();
            if (settingsDataAwaitSettingsData == null) {
                Fabric.getLogger().w(TAG, "Received null settings, skipping report submission!");
            } else if (settingsDataAwaitSettingsData.featuresData.collectReports) {
                if (!this.controller.finalizeSessions(settingsDataAwaitSettingsData.sessionData)) {
                    Fabric.getLogger().d(TAG, "Could not finalize previous sessions.");
                }
                this.controller.submitAllReports(this.delay, settingsDataAwaitSettingsData);
            } else {
                Fabric.getLogger().d(TAG, "Collection of crash reports disabled in Crashlytics settings.");
            }
        } catch (Exception e) {
            Fabric.getLogger().e(TAG, "Crashlytics encountered a problem during asynchronous initialization.", e);
        } finally {
            markInitializationComplete();
        }
        return null;
    }

    Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    CrashlyticsController getController() {
        return this.controller;
    }

    SessionEventData getExternalCrashEventData() {
        if (this.externalCrashEventDataProvider != null) {
            return this.externalCrashEventDataProvider.getCrashEventData();
        }
        return null;
    }

    @Override // io.fabric.sdk.android.Kit
    public String getIdentifier() {
        return "com.crashlytics.sdk.android.crashlytics-core";
    }

    public PinningInfoProvider getPinningInfoProvider() {
        if (this.disabled) {
            return null;
        }
        return this.pinningInfo;
    }

    String getUserEmail() {
        if (getIdManager().canCollectUserIds()) {
            return this.userEmail;
        }
        return null;
    }

    String getUserIdentifier() {
        if (getIdManager().canCollectUserIds()) {
            return this.userId;
        }
        return null;
    }

    String getUserName() {
        if (getIdManager().canCollectUserIds()) {
            return this.userName;
        }
        return null;
    }

    @Override // io.fabric.sdk.android.Kit
    public String getVersion() {
        return "2.3.17.dev";
    }

    boolean internalVerifyPinning(URL url) {
        if (getPinningInfoProvider() == null) {
            return false;
        }
        HttpRequest httpRequestBuildHttpRequest = this.httpRequestFactory.buildHttpRequest(HttpMethod.GET, url.toString());
        ((HttpsURLConnection) httpRequestBuildHttpRequest.getConnection()).setInstanceFollowRedirects(false);
        httpRequestBuildHttpRequest.code();
        return true;
    }

    public void log(int i, String str, String str2) {
        doLog(i, str, str2);
        Fabric.getLogger().log(i, "" + str, "" + str2, true);
    }

    public void log(String str) {
        doLog(3, TAG, str);
    }

    public void logException(Throwable th) {
        if (!this.disabled && ensureFabricWithCalled("prior to logging exceptions.")) {
            if (th == null) {
                Fabric.getLogger().log(5, TAG, "Crashlytics is ignoring a request to log a null exception.");
            } else {
                this.controller.writeNonFatalException(Thread.currentThread(), th);
            }
        }
    }

    void markInitializationComplete() {
        this.backgroundWorker.submit(new Callable<Boolean>() { // from class: com.crashlytics.android.core.CrashlyticsCore.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                try {
                    boolean zRemove = CrashlyticsCore.this.initializationMarker.remove();
                    Fabric.getLogger().d(CrashlyticsCore.TAG, "Initialization marker file removed: " + zRemove);
                    return Boolean.valueOf(zRemove);
                } catch (Exception e) {
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "Problem encountered deleting Crashlytics initialization marker.", e);
                    return false;
                }
            }
        });
    }

    void markInitializationStarted() {
        this.backgroundWorker.submitAndWait(new Callable<Void>() { // from class: com.crashlytics.android.core.CrashlyticsCore.2
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                CrashlyticsCore.this.initializationMarker.create();
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Initialization marker file created.");
                return null;
            }
        });
    }

    @Override // io.fabric.sdk.android.Kit
    protected boolean onPreExecute() {
        return onPreExecute(super.getContext());
    }

    boolean onPreExecute(Context context) {
        String value;
        if (this.disabled || (value = new ApiKey().getValue(context)) == null) {
            return false;
        }
        String strResolveBuildId = CommonUtils.resolveBuildId(context);
        if (!isBuildIdValid(strResolveBuildId, CommonUtils.getBooleanResourceValue(context, CRASHLYTICS_REQUIRE_BUILD_ID, true))) {
            throw new UnmetDependencyException(MISSING_BUILD_ID_MSG);
        }
        try {
            Fabric.getLogger().i(TAG, "Initializing Crashlytics " + getVersion());
            FileStoreImpl fileStoreImpl = new FileStoreImpl(this);
            this.crashMarker = new CrashlyticsFileMarker(CRASH_MARKER_FILE_NAME, fileStoreImpl);
            this.initializationMarker = new CrashlyticsFileMarker(INITIALIZATION_MARKER_FILE_NAME, fileStoreImpl);
            PreferenceManager preferenceManagerCreate = PreferenceManager.create(new PreferenceStoreImpl(getContext(), PREFERENCE_STORE_NAME), this);
            CrashlyticsPinningInfoProvider crashlyticsPinningInfoProvider = this.pinningInfo != null ? new CrashlyticsPinningInfoProvider(this.pinningInfo) : null;
            this.httpRequestFactory = new DefaultHttpRequestFactory(Fabric.getLogger());
            this.httpRequestFactory.setPinningInfoProvider(crashlyticsPinningInfoProvider);
            IdManager idManager = getIdManager();
            AppData appDataCreate = AppData.create(context, idManager, value, strResolveBuildId);
            ManifestUnityVersionProvider manifestUnityVersionProvider = new ManifestUnityVersionProvider(context, appDataCreate.packageName);
            Fabric.getLogger().d(TAG, "Installer package name is: " + appDataCreate.installerPackageName);
            this.controller = new CrashlyticsController(this, this.backgroundWorker, this.httpRequestFactory, idManager, preferenceManagerCreate, fileStoreImpl, appDataCreate, manifestUnityVersionProvider);
            boolean zDidPreviousInitializationFail = didPreviousInitializationFail();
            checkForPreviousCrash();
            this.controller.enableExceptionHandling(Thread.getDefaultUncaughtExceptionHandler());
            if (!zDidPreviousInitializationFail || !CommonUtils.canTryConnection(context)) {
                Fabric.getLogger().d(TAG, "Exception handling initialization successful");
                return true;
            }
            Fabric.getLogger().d(TAG, "Crashlytics did not finish previous background initialization. Initializing synchronously.");
            finishInitSynchronously();
            return false;
        } catch (Exception e) {
            Fabric.getLogger().e(TAG, "Crashlytics was not started due to an exception during initialization", e);
            this.controller = null;
            return false;
        }
    }

    public void setBool(String str, boolean z) {
        setString(str, Boolean.toString(z));
    }

    public void setDouble(String str, double d) {
        setString(str, Double.toString(d));
    }

    void setExternalCrashEventDataProvider(CrashEventDataProvider crashEventDataProvider) {
        this.externalCrashEventDataProvider = crashEventDataProvider;
    }

    public void setFloat(String str, float f) {
        setString(str, Float.toString(f));
    }

    public void setInt(String str, int i) {
        setString(str, Integer.toString(i));
    }

    @Deprecated
    public synchronized void setListener(CrashlyticsListener crashlyticsListener) {
        Fabric.getLogger().w(TAG, "Use of setListener is deprecated.");
        if (crashlyticsListener == null) {
            throw new IllegalArgumentException("listener must not be null.");
        }
        this.listener = crashlyticsListener;
    }

    public void setLong(String str, long j) {
        setString(str, Long.toString(j));
    }

    public void setString(String str, String str2) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting keys.")) {
            if (str == null) {
                Context context = getContext();
                if (context != null && CommonUtils.isAppDebuggable(context)) {
                    throw new IllegalArgumentException("Custom attribute key must not be null.");
                }
                Fabric.getLogger().e(TAG, "Attempting to set custom attribute with null key, ignoring.", null);
                return;
            }
            String strSanitizeAttribute = sanitizeAttribute(str);
            if (this.attributes.size() >= 64 && !this.attributes.containsKey(strSanitizeAttribute)) {
                Fabric.getLogger().d(TAG, "Exceeded maximum number of custom attributes (64)");
            } else {
                this.attributes.put(strSanitizeAttribute, str2 == null ? "" : sanitizeAttribute(str2));
                this.controller.cacheKeyData(this.attributes);
            }
        }
    }

    public void setUserEmail(String str) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting user data.")) {
            this.userEmail = sanitizeAttribute(str);
            this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setUserIdentifier(String str) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting user data.")) {
            this.userId = sanitizeAttribute(str);
            this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setUserName(String str) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting user data.")) {
            this.userName = sanitizeAttribute(str);
            this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public boolean verifyPinning(URL url) {
        try {
            return internalVerifyPinning(url);
        } catch (Exception e) {
            Fabric.getLogger().e(TAG, "Could not verify SSL pinning", e);
            return false;
        }
    }
}
