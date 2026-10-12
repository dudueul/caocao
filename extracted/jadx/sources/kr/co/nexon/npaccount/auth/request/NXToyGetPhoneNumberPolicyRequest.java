package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberPolicyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetPhoneNumberPolicyRequest extends NXToyBoltRequest {
    public NXToyGetPhoneNumberPolicyRequest() {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getPhoneNumberPolicy.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        super.setResultClass(NXToyPhoneNumberPolicyResult.class);
    }
}
