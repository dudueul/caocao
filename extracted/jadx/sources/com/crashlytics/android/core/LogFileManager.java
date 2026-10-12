package com.crashlytics.android.core;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.File;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class LogFileManager {
    private static final String COLLECT_CUSTOM_LOGS = "com.crashlytics.CollectCustomLogs";
    private static final String LOGFILE_EXT = ".temp";
    private static final String LOGFILE_PREFIX = "crashlytics-userlog-";
    static final int MAX_LOG_SIZE = 65536;
    private static final NoopLogStore NOOP_LOG_STORE = new NoopLogStore();
    private final Context context;
    private FileLogStore currentLog;
    private final DirectoryProvider directoryProvider;

    public interface DirectoryProvider {
        File getLogFileDir();
    }

    private static final class NoopLogStore implements FileLogStore {
        private NoopLogStore() {
        }

        @Override // com.crashlytics.android.core.FileLogStore
        public void closeLogFile() {
        }

        @Override // com.crashlytics.android.core.FileLogStore
        public void deleteLogFile() {
        }

        @Override // com.crashlytics.android.core.FileLogStore
        public ByteString getLogAsByteString() {
            return null;
        }

        @Override // com.crashlytics.android.core.FileLogStore
        public void writeToLog(long j, String str) {
        }
    }

    LogFileManager(Context context, DirectoryProvider directoryProvider) {
        this(context, directoryProvider, null);
    }

    LogFileManager(Context context, DirectoryProvider directoryProvider, String str) {
        this.context = context;
        this.directoryProvider = directoryProvider;
        this.currentLog = NOOP_LOG_STORE;
        setCurrentSession(str);
    }

    private String getSessionIdForFile(File file) {
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(LOGFILE_EXT);
        return iLastIndexOf == -1 ? name : name.substring(LOGFILE_PREFIX.length(), iLastIndexOf);
    }

    private File getWorkingFileForSession(String str) {
        return new File(this.directoryProvider.getLogFileDir(), LOGFILE_PREFIX + str + LOGFILE_EXT);
    }

    void clearLog() {
        this.currentLog.deleteLogFile();
    }

    void discardOldLogFiles(Set<String> set) {
        File[] fileArrListFiles = this.directoryProvider.getLogFileDir().listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (!set.contains(getSessionIdForFile(file))) {
                    file.delete();
                }
            }
        }
    }

    ByteString getByteStringForLog() {
        return this.currentLog.getLogAsByteString();
    }

    final void setCurrentSession(String str) {
        this.currentLog.closeLogFile();
        this.currentLog = NOOP_LOG_STORE;
        if (str == null) {
            return;
        }
        if (CommonUtils.getBooleanResourceValue(this.context, COLLECT_CUSTOM_LOGS, true)) {
            setLogFile(getWorkingFileForSession(str), 65536);
        } else {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Preferences requested no custom logs. Aborting log file creation.");
        }
    }

    void setLogFile(File file, int i) {
        this.currentLog = new QueueFileLogStore(file, i);
    }

    void writeToLog(long j, String str) {
        this.currentLog.writeToLog(j, str);
    }
}
