package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class NullLoggerFactory extends LoggerFactory {
    private static final Logger SINGLETON = new NullLogger();

    NullLoggerFactory() {
    }

    @Override // twitter4j.LoggerFactory
    public Logger getLogger(Class<?> cls) {
        return SINGLETON;
    }
}
