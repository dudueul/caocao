package kr.co.nexon.android.sns.nxnet.api.request;

import android.content.Context;
import com.google.gson.Gson;
import io.fabric.sdk.android.services.network.HttpRequest;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetGetUserIdResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetResult;
import kr.co.nexon.android.sns.nxnet.session.NPNXNetSession;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetGetUserIdRequest extends NPNXNetRequest {
    public NPNXNetGetUserIdRequest(Context context, NPNXNetSession nPNXNetSession) {
        super(context, NPNXNetRequestType.getUserId, nPNXNetSession);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public NPNXNetResult makeResult(String str) {
        return (NPNXNetGetUserIdResult) new Gson().fromJson(str, NPNXNetGetUserIdResult.class);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public void onPostExec(NPNXNetResult nPNXNetResult) {
        super.onPostExec(nPNXNetResult);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public boolean onPreExec() {
        setHttpMethod(HttpMethod.GET);
        addHeader(HttpRequest.HEADER_AUTHORIZATION, "bearer " + this.nxNetSession.getAccessToken());
        return true;
    }
}
