package kr.co.nexon.npaccount.secondpassword.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.secondpassword.result.NXToyVerifySecondPasswordResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyVerify2ndPasswordRequest extends NXToyBoltRequest {
    public NXToyVerify2ndPasswordRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/verify2ndPassword.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("password", str);
        super.setMessageBody(map);
        super.setResultClass(NXToyVerifySecondPasswordResult.class);
    }
}
