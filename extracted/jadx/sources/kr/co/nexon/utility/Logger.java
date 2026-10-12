package kr.co.nexon.utility;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class Logger {
    private static boolean debugEnabled = false;
    private static Logger logger;
    private ArrayList<LogListener> logListeners;

    public interface LogListener {
        void onLog(String str);
    }

    public static void addLogListener(LogListener logListener) {
        getLogger().logListeners.add(logListener);
    }

    public static void d(String str, String str2) {
        if (isDebugEnabled()) {
            Log.d(str, str2);
            if (getLogger().logListeners == null || getLogger().logListeners.isEmpty()) {
                return;
            }
            Iterator<LogListener> it = getLogger().logListeners.iterator();
            while (it.hasNext()) {
                it.next().onLog("D/" + str + ": " + str2);
            }
        }
    }

    public static void e(String str, String str2) {
        if (isDebugEnabled()) {
            Log.e(str, str2);
            if (getLogger().logListeners == null || getLogger().logListeners.isEmpty()) {
                return;
            }
            Iterator<LogListener> it = getLogger().logListeners.iterator();
            while (it.hasNext()) {
                it.next().onLog("E/" + str + ": " + str2);
            }
        }
    }

    public static synchronized Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
            logger.logListeners = new ArrayList<>();
        }
        return logger;
    }

    public static boolean isDebugEnabled() {
        return debugEnabled;
    }

    public static void removeLogListener(LogListener logListener) {
        getLogger().logListeners.remove(logListener);
    }

    public static void setDebugEnabled(boolean z) {
        debugEnabled = z;
    }

    public static void v(String str, String str2) {
        if (isDebugEnabled()) {
            Log.d(str, str2);
            if (getLogger().logListeners == null || getLogger().logListeners.isEmpty()) {
                return;
            }
            Iterator<LogListener> it = getLogger().logListeners.iterator();
            while (it.hasNext()) {
                it.next().onLog("I/" + str + ": " + str2);
            }
        }
    }

    public static void w(String str, String str2) {
        if (isDebugEnabled()) {
            Log.w(str, str2);
            if (getLogger().logListeners == null || getLogger().logListeners.isEmpty()) {
                return;
            }
            Iterator<LogListener> it = getLogger().logListeners.iterator();
            while (it.hasNext()) {
                it.next().onLog("W/" + str + ": " + str2);
            }
        }
    }
}
