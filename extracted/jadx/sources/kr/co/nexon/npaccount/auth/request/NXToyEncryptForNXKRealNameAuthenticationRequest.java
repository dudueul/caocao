package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.session.model.NXToySession;
import kr.co.nexon.npaccount.auth.result.NXToyNXKEncryptResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyEncryptForNXKRealNameAuthenticationRequest extends NXToyBoltRequest {
    public NXToyEncryptForNXKRealNameAuthenticationRequest(NXToySession nXToySession) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/encryptForNXKRealNameAuthentication.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setCryptoKey(nXToySession.getNpsn());
        super.setRequestHeader(nXToySession);
        super.setResultClass(NXToyNXKEncryptResult.class);
    }
}
