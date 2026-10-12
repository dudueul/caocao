package kr.co.nexon.android.sns.nxnet;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetInfo {
    private String productId;
    private String redirectUri;
    private String secretKey;
    private String ticket;

    public String getProductId() {
        return this.productId;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public void setRedirectUri(String str) {
        this.redirectUri = str;
    }

    public void setSecretKey(String str) {
        this.secretKey = str;
    }

    public void setTicket(String str) {
        this.ticket = str;
    }
}
