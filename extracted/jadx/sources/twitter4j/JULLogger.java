package twitter4j;

import java.util.logging.Level;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class JULLogger extends Logger {
    private final java.util.logging.Logger LOGGER;

    JULLogger(java.util.logging.Logger logger) {
        this.LOGGER = logger;
    }

    @Override // twitter4j.Logger
    public void debug(String str) {
        this.LOGGER.fine(str);
    }

    @Override // twitter4j.Logger
    public void debug(String str, String str2) {
        this.LOGGER.fine(String.valueOf(str) + str2);
    }

    @Override // twitter4j.Logger
    public void error(String str) {
        this.LOGGER.severe(str);
    }

    @Override // twitter4j.Logger
    public void error(String str, Throwable th) {
        this.LOGGER.severe(String.valueOf(str) + th.getMessage());
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
        return this.LOGGER.isLoggable(Level.FINE);
    }

    @Override // twitter4j.Logger
    public boolean isErrorEnabled() {
        return this.LOGGER.isLoggable(Level.SEVERE);
    }

    @Override // twitter4j.Logger
    public boolean isInfoEnabled() {
        return this.LOGGER.isLoggable(Level.INFO);
    }

    @Override // twitter4j.Logger
    public boolean isWarnEnabled() {
        return this.LOGGER.isLoggable(Level.WARNING);
    }

    @Override // twitter4j.Logger
    public void warn(String str) {
        this.LOGGER.warning(str);
    }

    @Override // twitter4j.Logger
    public void warn(String str, String str2) {
        this.LOGGER.warning(String.valueOf(str) + str2);
    }
}
