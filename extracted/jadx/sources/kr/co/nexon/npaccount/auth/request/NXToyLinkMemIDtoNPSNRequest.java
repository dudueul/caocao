package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyLinkMemIDtoNPSNResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyLinkMemIDtoNPSNRequest extends NXToyBoltRequest {
    public NXToyLinkMemIDtoNPSNRequest(String str, int i) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/linkMemIDtoNPSN.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("memID", str);
        map.put("memType", Integer.valueOf(i));
        super.setMessageBody(map);
        super.setResultClass(NXToyLinkMemIDtoNPSNResult.class);
    }
}
