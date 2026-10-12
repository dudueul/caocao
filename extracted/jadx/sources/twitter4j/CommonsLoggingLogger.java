package twitter4j;

import org.apache.commons.logging.Log;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class CommonsLoggingLogger extends Logger {
    private final Log LOGGER;

    CommonsLoggingLogger(Log log) {
        this.LOGGER = log;
    }

    @Override // twitter4j.Logger
    public void debug(String str) {
        this.LOGGER.debug(str);
    }

    @Override // twitter4j.Logger
    public void debug(String str, String str2) {
        this.LOGGER.debug(String.valueOf(str) + str2);
    }

    @Override // twitter4j.Logger
    public void error(String str) {
        this.LOGGER.error(str);
    }

    @Override // twitter4j.Logger
    public void error(String str, Throwable th) {
        this.LOGGER.error(str, th);
    }

    @Override // twitter4j.Logger
    public void info(String str) {
        this.LOGGER.info(str);
    }

    @Override // twitter4j.Logger
    public void info(String str, String str2) {
        this.LOGGER.info(String.valueOf(str) + str2);
    }

    @Override // twitter4j.Logger
    public boolean isDebugEnabled() {
        return this.LOGGER.isDebugEnabled();
    }

    @Override // twitter4j.Logger
    public boolean isErrorEnabled() {
        return this.LOGGER.isErrorEnabled();
    }

    @Override // twitter4j.Logger
    public boolean isInfoEnabled() {
        return this.LOGGER.isInfoEnabled();
    }

    @Override // twitter4j.Logger
    public boolean isWarnEnabled() {
        return this.LOGGER.isWarnEnabled();
    }

    @Override // twitter4j.Logger
    public void warn(String str) {
        this.LOGGER.warn(str);
    }

    @Override // twitter4j.Logger
    public void warn(String str, String str2) {
        this.LOGGER.warn(String.valueOf(str) + str2);
    }
}
