package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyUnregisterPushResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyUnregisterPushRequest extends NXToyBoltRequest {
    public NXToyUnregisterPushRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/unregisterPush.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.NONE;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("pushKey", str);
        super.setMessageBody(map);
        super.setResultClass(NXToyUnregisterPushResult.class);
    }
}
