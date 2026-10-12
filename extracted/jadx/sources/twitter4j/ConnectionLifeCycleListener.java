package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface ConnectionLifeCycleListener {
    void onCleanUp();

    void onConnect();

    void onDisconnect();
}
