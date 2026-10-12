package kr.co.nexon.npaccount.push.request.v2;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyPushRequest;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Map;
import kr.co.nexon.npaccount.push.NXPPushSettingImplV2;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyPostPolicyRequest extends NXToyPushRequest {
    public NXToyPostPolicyRequest(Map map, Map map2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/push/policy");
        HashMap map3 = new HashMap();
        NXToySession session = NXToySessionManager.getInstance().getSession();
        map3.put("svcID", session.getServiceId());
        map3.put("npToken", session.getNptoken());
        map3.put(TapjoyConstants.TJC_DEVICE_ID_NAME, NXToyCommonPreferenceController.getInstance().getUUID2());
        map3.put(TapjoyConstants.TJC_PLATFORM, "1");
        if (map != null) {
            map3.put("push", map);
        }
        if (map2 != null) {
            map3.put(NXPPushSettingImplV2.RESPONSE_KIND, map2);
        }
        super.setMessageBody(map3);
        super.setResultClass(NXToyResult.class);
    }
}
