package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyEmailUserInfoResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetEmailUserInfoRequest extends NXToyBoltRequest {
    public NXToyGetEmailUserInfoRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/auth/me.nx");
        this.encryptType = NXToyCryptoType.NONE;
        this.decryptType = NXToyCryptoType.NONE;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put(HttpRequest.HEADER_AUTHORIZATION, "Bearer " + str);
        super.putMessageHeader(map);
        super.setResultClass(NXToyEmailUserInfoResult.class);
    }
}
