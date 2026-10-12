package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.request.model.NXToySignInRequestOptionalBody;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyRecoverUserWithGcidRequest extends NXToyBoltRequest {
    public NXToyRecoverUserWithGcidRequest(String str, int i, NXToySignInRequestOptionalBody nXToySignInRequestOptionalBody) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/recoverUserWithGcid.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("gcId", str);
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        map.put(NxSystemInfo.KEY_UUID, nXToyCommonPreferenceController.getUUID());
        map.put("memType", Integer.valueOf(i));
        map.put("termsApiVer", Integer.valueOf(nXToyCommonPreferenceController.getTermsApiVer()));
        HashMap map2 = new HashMap();
        map2.put(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, nXToySignInRequestOptionalBody.getDeviceModelName());
        map2.put("name", nXToySignInRequestOptionalBody.getDeviceName());
        map2.put("email", nXToySignInRequestOptionalBody.getEmailId());
        map2.put("fbBizToken", nXToySignInRequestOptionalBody.getFbBizToken());
        map2.put("carrierName", nXToySignInRequestOptionalBody.getCarrierName());
        map.put(NXToyTerm.TYPE_OPTIONAL, map2);
        super.setMessageBody(map);
        super.setResultClass(NXToyLoginResult.class);
    }
}
