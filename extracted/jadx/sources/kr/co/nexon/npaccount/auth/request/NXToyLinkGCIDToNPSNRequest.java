package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyLinkGCIDToNPSNResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyLinkGCIDToNPSNRequest extends NXToyBoltRequest {
    public NXToyLinkGCIDToNPSNRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/linkGCID2NPSN.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("gcId", str);
        super.setMessageBody(map);
        super.setResultClass(NXToyLinkGCIDToNPSNResult.class);
    }
}
