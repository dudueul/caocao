package kr.co.nexon.android.sns.nxnet.api.request;

import android.content.Context;
import com.google.gson.Gson;
import io.fabric.sdk.android.services.network.HttpRequest;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetGetProfileNameResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetResult;
import kr.co.nexon.android.sns.nxnet.session.NPNXNetSession;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetGetProfileNameRequest extends NPNXNetRequest {
    public NPNXNetGetProfileNameRequest(Context context, NPNXNetSession nPNXNetSession) {
        super(context, NPNXNetRequestType.getProfilename, nPNXNetSession);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public NPNXNetResult makeResult(String str) {
        return (NPNXNetGetProfileNameResult) new Gson().fromJson(str, NPNXNetGetProfileNameResult.class);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public boolean onPreExec() {
        setHttpMethod(HttpMethod.GET);
        addHeader(HttpRequest.HEADER_AUTHORIZATION, "bearer " + this.nxNetSession.getAccessToken());
        return true;
    }
}
