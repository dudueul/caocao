package com.google.android.gms.common.stats;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class StatsUtils {
    public static String getEventKey(AbstractThreadedSyncAdapter abstractThreadedSyncAdapter, String str) {
        String strValueOf = String.valueOf(String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(abstractThreadedSyncAdapter))));
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        String strValueOf2 = String.valueOf(str);
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }

    public static String getEventKey(Context context, Intent intent) {
        return String.valueOf((((long) System.identityHashCode(context)) << 32) | ((long) System.identityHashCode(intent)));
    }

    public static String getEventKey(PowerManager.WakeLock wakeLock, String str) {
        String strValueOf = String.valueOf(String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(wakeLock))));
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        String strValueOf2 = String.valueOf(str);
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }

    public static boolean isLoggingEnabled() {
        StatisticalEventTrackerProvider.StatisticalEventTracker impl = StatisticalEventTrackerProvider.getImpl();
        return impl != null && impl.isEnabled() && (zza(Integer.valueOf(impl.getLogLevel(3))) || zza(Integer.valueOf(impl.getLogLevel(2))) || zza(Integer.valueOf(impl.getLogLevel(1))));
    }

    public static boolean isTimeOutEvent(StatsEvent statsEvent) {
        switch (statsEvent.getEventType()) {
            case 6:
            case 9:
            case 12:
                return true;
            default:
                return false;
        }
    }

    private static boolean zza(Integer num) {
        return !num.equals(Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
    }
}
