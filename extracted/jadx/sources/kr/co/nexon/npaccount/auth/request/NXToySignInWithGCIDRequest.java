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
import kr.co.nexon.npaccount.auth.result.NXToyLoginWithGCIDResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToySignInWithGCIDRequest extends NXToyBoltRequest {
    public NXToySignInWithGCIDRequest(String str, String str2, String str3, String str4, int i, NXToySignInRequestOptionalBody nXToySignInRequestOptionalBody) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/signInWithGCID.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put(NxLogInfo.KEY_NPSN, str);
        super.putMessageHeader(map);
        HashMap map2 = new HashMap();
        map2.put("gcId", str2);
        map2.put(NxSystemInfo.KEY_UUID, str3);
        map2.put(NxLogInfo.KEY_UUID2, str4);
        map2.put("memType", Integer.valueOf(i));
        map2.put("termsApiVer", Integer.valueOf(NXToyCommonPreferenceController.getInstance().getTermsApiVer()));
        HashMap map3 = new HashMap();
        map3.put(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, nXToySignInRequestOptionalBody.getDeviceModelName());
        map3.put("name", nXToySignInRequestOptionalBody.getDeviceName());
        map3.put("email", "");
        map2.put(NXToyTerm.TYPE_OPTIONAL, map3);
        super.setMessageBody(map2);
        super.setResultClass(NXToyLoginWithGCIDResult.class);
    }
}
