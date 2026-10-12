package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import kr.co.nexon.npaccount.auth.result.NXToyFundsSettlementPolicyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetFundsSettlementPolicyRequest extends NXToyBoltRequest {
    public NXToyGetFundsSettlementPolicyRequest() {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getFudnsSettlementPolicy.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        super.setResultClass(NXToyFundsSettlementPolicyResult.class);
    }
}
