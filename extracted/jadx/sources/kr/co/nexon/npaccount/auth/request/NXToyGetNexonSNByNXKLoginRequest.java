package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyNexonSNResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetNexonSNByNXKLoginRequest extends NXToyBoltRequest {
    public NXToyGetNexonSNByNXKLoginRequest(String str, String str2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getNexonSNByNXKLogin.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("passwd", str2);
        map.put("userID", str);
        map.put(NxSystemInfo.KEY_UUID, NXToyCommonPreferenceController.getInstance().getUUID());
        super.setMessageBody(map);
        super.setResultClass(NXToyNexonSNResult.class);
    }
}
