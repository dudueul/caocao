package com.crashlytics.android.core;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
interface StackTraceTrimmingStrategy {
    StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr);
}
