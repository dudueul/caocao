package kr.co.nexon.npaccount.push.request.v1;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.push.result.v1.NXToyOffPushResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyOffPushRequest extends NXToyBoltRequest {
    public NXToyOffPushRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/offPush.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("pushKey", str);
        super.setMessageBody(map);
        super.setResultClass(NXToyOffPushResult.class);
    }
}
