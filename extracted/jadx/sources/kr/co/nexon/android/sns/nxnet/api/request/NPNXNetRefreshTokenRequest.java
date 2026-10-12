package kr.co.nexon.android.sns.nxnet.api.request;

import android.content.Context;
import com.google.gson.Gson;
import kr.co.nexon.android.sns.nxnet.NPNXNetInfo;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetRefreshTokenResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetResult;
import kr.co.nexon.android.sns.nxnet.session.NPNXNetSession;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetRefreshTokenRequest extends NPNXNetRequest {
    private NPNXNetInfo nxNetInfo;

    public NPNXNetRefreshTokenRequest(Context context, NPNXNetSession nPNXNetSession) {
        super(context, NPNXNetRequestType.refreshToken, nPNXNetSession);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public NPNXNetResult makeResult(String str) {
        return (NPNXNetRefreshTokenResult) new Gson().fromJson(str, NPNXNetRefreshTokenResult.class);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public void onPostExec(NPNXNetResult nPNXNetResult) {
        super.onPostExec(nPNXNetResult);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public boolean onPreExec() {
        setHttpMethod(HttpMethod.POST);
        addParam("grant_type", "refresh_token");
        addParam("refresh_token", this.nxNetSession.getRefreshToken());
        addParam("product_id", this.nxNetInfo.getProductId());
        addParam("secret_key", this.nxNetInfo.getSecretKey());
        return true;
    }

    public void setNxNetInfo(NPNXNetInfo nPNXNetInfo) {
        this.nxNetInfo = nPNXNetInfo;
    }
}
