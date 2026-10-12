package kr.co.nexon.npaccount.secondpassword.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import kr.co.nexon.npaccount.secondpassword.result.NXToyGetSecondPasswordInfoResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGet2ndPasswordInfoRequest extends NXToyBoltRequest {
    public NXToyGet2ndPasswordInfoRequest() {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/get2ndPasswordInfo.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        super.setResultClass(NXToyGetSecondPasswordInfoResult.class);
    }
}
