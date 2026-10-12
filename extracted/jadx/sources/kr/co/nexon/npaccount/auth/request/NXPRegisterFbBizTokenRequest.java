package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXPRegisterFbBizTokenResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPRegisterFbBizTokenRequest extends NXToyBoltRequest {
    public NXPRegisterFbBizTokenRequest(String str, String str2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/registerFbBizToken.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("facebookId", str);
        map.put("fbBizToken", str2);
        super.setMessageBody(map);
        super.setResultClass(NXPRegisterFbBizTokenResult.class);
    }
}
