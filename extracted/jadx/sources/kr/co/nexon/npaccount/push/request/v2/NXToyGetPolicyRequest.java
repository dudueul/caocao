package kr.co.nexon.npaccount.push.request.v2;

import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyPushRequest;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import kr.co.nexon.npaccount.push.result.v2.NXToyGetPolicyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetPolicyRequest extends NXToyPushRequest {
    public NXToyGetPolicyRequest() {
        super.setMethod(NXToyRequestMethod.GET);
        NXToySession session = NXToySessionManager.getInstance().getSession();
        super.addPathToHttpURL("/sdk/push/policy?svcID=" + session.getServiceId() + "&npToken=" + session.getNptoken());
        super.setResultClass(NXToyGetPolicyResult.class);
    }
}
