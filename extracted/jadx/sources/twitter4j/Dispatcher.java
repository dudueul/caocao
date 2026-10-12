package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface Dispatcher {
    void invokeLater(Runnable runnable);

    void shutdown();
}
