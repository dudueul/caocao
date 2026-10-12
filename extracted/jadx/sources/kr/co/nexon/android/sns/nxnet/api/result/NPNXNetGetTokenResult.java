package kr.co.nexon.android.sns.nxnet.api.result;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetGetTokenResult extends NPNXNetResult {
    public String expires_in;
    public String refresh_token;
    public String ticket_type;
    public String token;

    public NPNXNetGetTokenResult() {
    }

    public NPNXNetGetTokenResult(int i, String str) {
        super(i, str);
    }

    public NPNXNetGetTokenResult(int i, String str, String str2) {
        super(i, str, str2);
    }
}
