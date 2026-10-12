package kr.co.nexon.npaccount.push.request.v2;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyPushRequest;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyAckRequest extends NXToyPushRequest {
    public NXToyAckRequest(String str, String str2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/push/ack");
        HashMap map = new HashMap();
        map.put("svcId", NXToySessionManager.getInstance().getSession().getServiceId());
        map.put("pushId", str);
        map.put("messageId", str2);
        map.put(TapjoyConstants.TJC_DEVICE_ID_NAME, NXToyCommonPreferenceController.getInstance().getUUID2());
        super.setMessageBody(map);
        super.setResultClass(NXToyResult.class);
    }
}
