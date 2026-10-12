package twitter4j;

import java.util.Date;
import twitter4j.conf.ConfigurationContext;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class StdOutLogger extends Logger {
    private static final boolean DEBUG = ConfigurationContext.getInstance().isDebugEnabled();

    StdOutLogger() {
    }

    @Override // twitter4j.Logger
    public void debug(String str) {
        if (DEBUG) {
            System.out.println("[" + new Date() + "]" + str);
        }
    }

    @Override // twitter4j.Logger
    public void debug(String str, String str2) {
        if (DEBUG) {
            debug(String.valueOf(str) + str2);
        }
    }

    @Override // twitter4j.Logger
    public void error(String str) {
        System.out.println("[" + new Date() + "]" + str);
    }

    @Override // twitter4j.Logger
    public void error(String str, Throwable th) {
        System.out.println(str);
        th.printStackTrace(System.err);
    }

    @Override // twitter4j.Logger
    public void info(String str) {
        System.out.println("[" + new Date() + "]" + str);
    }

    @Override // twitter4j.Logger
    public void info(String str, String str2) {
        info(String.valueOf(str) + str2);
    }

    @Override // twitter4j.Logger
    public boolean isDebugEnabled() {
        return DEBUG;
    }

    @Override // twitter4j.Logger
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // twitter4j.Logger
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // twitter4j.Logger
    public boolean isWarnEnabled() {
        return true;
    }

    @Override // twitter4j.Logger
    public void warn(String str) {
        System.out.println("[" + new Date() + "]" + str);
    }

    @Override // twitter4j.Logger
    public void warn(String str, String str2) {
        warn(String.valueOf(str) + str2);
    }
}
