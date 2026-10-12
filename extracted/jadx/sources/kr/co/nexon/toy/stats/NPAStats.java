package kr.co.nexon.toy.stats;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NPAStats {
    void endSession();

    void sendEcommerceItem(String str, String str2, String str3, String str4, Double d, Long l, String str5);

    void sendEcommerceTransaction(String str, String str2, Double d, Double d2, Double d3, String str3);

    void sendEvent(String str, String str2, String str3, String str4);

    void sendScreen(String str);

    void setOptout(boolean z);

    void setUserID(String str);

    void startSession();
}
