package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyNexonSNResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetNexonSNByTPARequest extends NXToyBoltRequest {
    public NXToyGetNexonSNByTPARequest(String str, String str2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getNexonSNByTPA.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("tpaToken", str);
        map.put("tpaState", str2);
        super.setMessageBody(map);
        super.setResultClass(NXToyNexonSNResult.class);
    }
}
