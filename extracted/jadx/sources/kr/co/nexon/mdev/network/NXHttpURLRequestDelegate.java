package kr.co.nexon.mdev.network;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface NXHttpURLRequestDelegate {
    void onComplete(NXHttpURLRequestResult nXHttpURLRequestResult);

    void onDataReceive(byte[] bArr, int i, int i2);

    void onError(Exception exc);
}
