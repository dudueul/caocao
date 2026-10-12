package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyTermsListResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetTermsListRequest extends NXToyBoltRequest {
    public static final String METHOD_APP = "app";
    public static final String METHOD_BASEPLATE = "baseplate";

    public NXToyGetTermsListRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getTermsList.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("method", str);
        map.put("termsApiVer", Integer.valueOf(NXToyCommonPreferenceController.getInstance().getTermsApiVer()));
        super.setMessageBody(map);
        super.setResultClass(NXToyTermsListResult.class);
    }
}
