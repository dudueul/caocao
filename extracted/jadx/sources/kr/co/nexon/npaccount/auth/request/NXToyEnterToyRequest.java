package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyEnterResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyEnterToyRequest extends NXToyBoltRequest {
    public NXToyEnterToyRequest(int i, int i2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/enterToy.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.NONE;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("mnc", Integer.valueOf(i));
        map.put("mcc", Integer.valueOf(i2));
        map.put("termsApiVer", Integer.valueOf(NXToyCommonPreferenceController.getInstance().getTermsApiVer()));
        map.put("policyApiVer", Integer.valueOf(NXToyCommonPreferenceController.getInstance().getPolicyApiVer()));
        super.setMessageBody(map);
        super.setResultClass(NXToyEnterResult.class);
    }
}
