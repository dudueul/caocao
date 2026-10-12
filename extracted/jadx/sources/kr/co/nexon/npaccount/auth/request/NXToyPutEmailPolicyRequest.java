package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyEmailPolicyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyPutEmailPolicyRequest extends NXToyBoltRequest {
    public NXToyPutEmailPolicyRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/putEmailPolicy.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("email", str);
        super.setMessageBody(map);
        super.setResultClass(NXToyEmailPolicyResult.class);
    }
}
