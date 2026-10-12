package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyTermResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetTermsRequest extends NXToyBoltRequest {
    public NXToyGetTermsRequest(int i) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getTerms.nx");
        if (i > 0) {
            HashMap map = new HashMap();
            map.put("termID", Integer.valueOf(i));
            super.setMessageBody(map);
        }
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.NONE;
        super.setRequestHeader();
        super.setResultClass(NXToyTermResult.class);
    }
}
