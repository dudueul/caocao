package kr.co.nexon.android.sns.nxnet.api.result;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetRefreshTokenResult extends NPNXNetResult {
    public String expires_in;
    public String refresh_token;
    public String token;

    public NPNXNetRefreshTokenResult() {
    }

    public NPNXNetRefreshTokenResult(int i, String str) {
        super(i, str);
    }

    public NPNXNetRefreshTokenResult(int i, String str, String str2) {
        super(i, str, str2);
    }
}
