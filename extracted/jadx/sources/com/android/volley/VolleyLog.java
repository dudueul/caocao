package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class VolleyLog {
    public static String TAG = "Volley";
    public static boolean DEBUG = Log.isLoggable(TAG, 2);

    static class MarkerLog {
        public static final boolean ENABLED = VolleyLog.DEBUG;
        private static final long MIN_DURATION_FOR_LOGGING_MS = 0;
        private final List<Marker> mMarkers = new ArrayList();
        private boolean mFinished = false;

        private static class Marker {
            public final String name;
            public final long thread;
            public final long time;

            public Marker(String str, long j, long j2) {
                this.name = str;
                this.thread = j;
                this.time = j2;
            }
        }

        MarkerLog() {
        }

        private long getTotalDuration() {
            if (this.mMarkers.size() == 0) {
                return 0L;
            }
            return this.mMarkers.get(this.mMarkers.size() - 1).time - this.mMarkers.get(0).time;
        }

        public synchronized void add(String str, long j) {
            if (this.mFinished) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.mMarkers.add(new Marker(str, j, SystemClock.elapsedRealtime()));
        }

        protected void finalize() throws Throwable {
            if (this.mFinished) {
                return;
            }
            finish("Request on the loose");
            VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }

        public synchronized void finish(String str) {
            this.mFinished = true;
            long totalDuration = getTotalDuration();
            if (totalDuration > 0) {
                long j = this.mMarkers.get(0).time;
                VolleyLog.d("(%-4d ms) %s", Long.valueOf(totalDuration), str);
                for (Marker marker : this.mMarkers) {
                    long j2 = marker.time;
                    VolleyLog.d("(+%-4d) [%2d] %s", Long.valueOf(j2 - j), Long.valueOf(marker.thread), marker.name);
                    j = j2;
                }
            }
        }
    }

    private static String buildMessage(String str, Object... objArr) {
        String str2 = objArr == null ? str : String.format(Locale.US, str, objArr);
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String string = "<unknown>";
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClass().equals(VolleyLog.class)) {
                String className = stackTrace[i].getClassName();
                String strSubstring = className.substring(className.lastIndexOf(46) + 1);
                String strValueOf = String.valueOf(String.valueOf(strSubstring.substring(strSubstring.lastIndexOf(36) + 1)));
                String strValueOf2 = String.valueOf(String.valueOf(stackTrace[i].getMethodName()));
                string = new StringBuilder(strValueOf.length() + 1 + strValueOf2.length()).append(strValueOf).append(".").append(strValueOf2).toString();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), string, str2);
    }

    public static void d(String str, Object... objArr) {
        Log.d(TAG, buildMessage(str, objArr));
    }

    public static void e(String str, Object... objArr) {
        Log.e(TAG, buildMessage(str, objArr));
    }

    public static void e(Throwable th, String str, Object... objArr) {
        Log.e(TAG, buildMessage(str, objArr), th);
    }

    public static void setTag(String str) {
        d("Changing log tag to %s", str);
        TAG = str;
        DEBUG = Log.isLoggable(TAG, 2);
    }

    public static void v(String str, Object... objArr) {
        if (DEBUG) {
            Log.v(TAG, buildMessage(str, objArr));
        }
    }

    public static void wtf(String str, Object... objArr) {
        Log.wtf(TAG, buildMessage(str, objArr));
    }

    public static void wtf(Throwable th, String str, Object... objArr) {
        Log.wtf(TAG, buildMessage(str, objArr), th);
    }
}
