package kr.co.nexon.android.sns.nxnet.session;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetSession {
    private String accessToken;
    private String refreshToken;
    private long tokenExpires;
    private String userId;
    private String userNo;

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public long getTokenExpires() {
        return this.tokenExpires;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public void setTokenExpires(String str) {
        this.tokenExpires = (System.currentTimeMillis() / 1000) + Long.valueOf(str).longValue();
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setUserNo(String str) {
        this.userNo = str;
    }
}
