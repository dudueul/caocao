package com.crashlytics.android.core;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class TrimmedThrowableData {
    public final TrimmedThrowableData cause;
    public final String className;
    public final String localizedMessage;
    public final StackTraceElement[] stacktrace;

    public TrimmedThrowableData(Throwable th, StackTraceTrimmingStrategy stackTraceTrimmingStrategy) {
        this.localizedMessage = th.getLocalizedMessage();
        this.className = th.getClass().getName();
        this.stacktrace = stackTraceTrimmingStrategy.getTrimmedStackTrace(th.getStackTrace());
        Throwable cause = th.getCause();
        this.cause = cause != null ? new TrimmedThrowableData(cause, stackTraceTrimmingStrategy) : null;
    }
}
