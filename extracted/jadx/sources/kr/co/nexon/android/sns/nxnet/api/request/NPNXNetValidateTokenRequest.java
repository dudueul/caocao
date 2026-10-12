package kr.co.nexon.android.sns.nxnet.api.request;

import android.content.Context;
import com.google.gson.Gson;
import io.fabric.sdk.android.services.network.HttpRequest;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetValidateTokenResult;
import kr.co.nexon.android.sns.nxnet.session.NPNXNetSession;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetValidateTokenRequest extends NPNXNetRequest {
    public NPNXNetValidateTokenRequest(Context context, NPNXNetSession nPNXNetSession) {
        super(context, NPNXNetRequestType.validateToken, nPNXNetSession);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public NPNXNetResult makeResult(String str) {
        return (NPNXNetValidateTokenResult) new Gson().fromJson(str, NPNXNetValidateTokenResult.class);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public boolean onPreExec() {
        setHttpMethod(HttpMethod.GET);
        addHeader(HttpRequest.HEADER_AUTHORIZATION, "bearer " + this.nxNetSession.getAccessToken());
        return true;
    }
}
