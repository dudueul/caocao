package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.request.model.NXToySignInRequestOptionalBody;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToySignUpNPAARequest extends NXToyBoltRequest {
    public NXToySignUpNPAARequest(String str, String str2, NXToySignInRequestOptionalBody nXToySignInRequestOptionalBody) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/signUpNPAA.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        HashMap map = new HashMap();
        map.put("userID", str);
        map.put("passwd", str2);
        map.put(NxSystemInfo.KEY_UUID, nXToyCommonPreferenceController.getUUID());
        map.put(NxLogInfo.KEY_UUID2, nXToyCommonPreferenceController.getUUID2());
        map.put("termsApiVer", Integer.valueOf(nXToyCommonPreferenceController.getTermsApiVer()));
        HashMap map2 = new HashMap();
        map2.put(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, nXToySignInRequestOptionalBody.getDeviceModelName());
        map2.put("name", nXToySignInRequestOptionalBody.getDeviceName());
        map2.put("email", nXToySignInRequestOptionalBody.getEmailId());
        map.put(NXToyTerm.TYPE_OPTIONAL, map2);
        super.setMessageBody(map);
        super.setResultClass(NXToyLoginResult.class);
    }
}
