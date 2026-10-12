package kr.co.nexon.android.sns.nxnet.api.request;

import android.content.Context;
import com.google.gson.Gson;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetGetProfileNameResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetResult;
import kr.co.nexon.android.sns.nxnet.session.NPNXNetSession;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetLogoutRequest extends NPNXNetRequest {
    public NPNXNetLogoutRequest(Context context, NPNXNetSession nPNXNetSession) {
        super(context, NPNXNetRequestType.logout, nPNXNetSession);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public NPNXNetResult makeResult(String str) {
        return (NPNXNetGetProfileNameResult) new Gson().fromJson(str, NPNXNetGetProfileNameResult.class);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public boolean onPreExec() {
        setHttpMethod(HttpMethod.POST);
        addParam("token", this.nxNetSession.getAccessToken());
        return true;
    }
}
