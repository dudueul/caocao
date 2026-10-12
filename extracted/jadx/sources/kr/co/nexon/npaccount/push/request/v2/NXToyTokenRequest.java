package kr.co.nexon.npaccount.push.request.v2;

import com.facebook.appevents.UserDataStore;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyPushRequest;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyTokenRequest extends NXToyPushRequest {
    public NXToyTokenRequest(String str, String str2, String str3) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/push/token");
        NXToySession session = NXToySessionManager.getInstance().getSession();
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        HashMap map = new HashMap();
        map.put("svcID", session.getServiceId());
        map.put("npToken", session.getNptoken());
        map.put("ldid", nXToyCommonPreferenceController.getUUID());
        map.put(TapjoyConstants.TJC_DEVICE_ID_NAME, nXToyCommonPreferenceController.getUUID2());
        map.put("pushToken", str);
        map.put(UserDataStore.COUNTRY, str2);
        map.put("loc", str3);
        map.put(TapjoyConstants.TJC_PLATFORM, "1");
        super.setMessageBody(map);
        super.setResultClass(NXToyResult.class);
    }
}
