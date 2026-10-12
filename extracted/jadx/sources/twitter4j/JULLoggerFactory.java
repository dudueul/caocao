package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class JULLoggerFactory extends LoggerFactory {
    JULLoggerFactory() {
    }

    @Override // twitter4j.LoggerFactory
    public Logger getLogger(Class<?> cls) {
        return new JULLogger(java.util.logging.Logger.getLogger(cls.getName()));
    }
}
