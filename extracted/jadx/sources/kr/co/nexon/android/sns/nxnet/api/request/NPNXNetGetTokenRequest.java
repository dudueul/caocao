package kr.co.nexon.android.sns.nxnet.api.request;

import android.content.Context;
import com.google.gson.Gson;
import kr.co.nexon.android.sns.nxnet.NPNXNetInfo;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetGetTokenResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetResult;
import kr.co.nexon.android.sns.nxnet.session.NPNXNetSession;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetGetTokenRequest extends NPNXNetRequest {
    private NPNXNetInfo nxNetInfo;

    public NPNXNetGetTokenRequest(Context context, NPNXNetSession nPNXNetSession) {
        super(context, NPNXNetRequestType.getToken, nPNXNetSession);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public NPNXNetResult makeResult(String str) {
        return (NPNXNetGetTokenResult) new Gson().fromJson(str, NPNXNetGetTokenResult.class);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public void onPostExec(NPNXNetResult nPNXNetResult) {
        super.onPostExec(nPNXNetResult);
    }

    @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest
    public boolean onPreExec() {
        setHttpMethod(HttpMethod.POST);
        addParam("ticket", this.nxNetInfo.getTicket());
        addParam("product_id", this.nxNetInfo.getProductId());
        addParam("secret_key", this.nxNetInfo.getSecretKey());
        return true;
    }

    public void setNxNetInfo(NPNXNetInfo nPNXNetInfo) {
        this.nxNetInfo = nPNXNetInfo;
    }
}
