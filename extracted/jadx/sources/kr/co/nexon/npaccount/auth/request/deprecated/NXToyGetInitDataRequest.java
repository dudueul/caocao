package kr.co.nexon.npaccount.auth.request.deprecated;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import kr.co.nexon.npaccount.auth.result.NXToyEnterResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetInitDataRequest extends NXToyBoltRequest {
    public NXToyGetInitDataRequest() {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getInitData.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.NONE;
        super.setRequestHeader();
        super.setResultClass(NXToyEnterResult.class);
    }
}
