package com.crashlytics.android.core;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
interface FileLogStore {
    void closeLogFile();

    void deleteLogFile();

    ByteString getLogAsByteString();

    void writeToLog(long j, String str);
}
