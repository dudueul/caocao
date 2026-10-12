package kr.co.nexon.npaccount.policy.impl;

import android.content.Context;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.HashMap;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.NXToyFundsSettlementPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyValidatePolicyResult;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;
import kr.co.nexon.npaccount.auth.result.model.NXToyPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.policy.interfaces.NXPFundsSettlementPolicy;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPFundsSettlementPolicyImplV1 implements NXPFundsSettlementPolicy {
    public static final String KEY_BIRTH_DATE_TYPE = "yyyyMM";

    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPFundsSettlementPolicy
    public void getPolicy(Context context, final NPListener nPListener) {
        ToyLog.d("FundsSettlementPolicy V1(Get)");
        NXPPolicyManager.getInstance().getPolicyListV1(context, "purchase", new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPFundsSettlementPolicyImplV1.1
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("FundsSettlementPolicy V1(Get) : result - " + nXToyResult.toString());
                NXToyFundsSettlementPolicyResult nXToyFundsSettlementPolicyResult = new NXToyFundsSettlementPolicyResult(0, "", "");
                nXToyFundsSettlementPolicyResult.requestTag = NXToyRequestTag.GetPolicy.getValue();
                nXToyFundsSettlementPolicyResult.result.policy = new NXPPolicy();
                nXToyFundsSettlementPolicyResult.result.policy.setType(3);
                if (nPListener == null) {
                    ToyLog.d("FundsSettlementPolicy V1(Get) : listener is null");
                    return;
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("FundsSettlementPolicy V1(Get) : error return result - " + nXToyFundsSettlementPolicyResult.toString());
                    nXToyFundsSettlementPolicyResult.errorCode = nXToyResult.errorCode;
                    nXToyFundsSettlementPolicyResult.errorText = nXToyResult.errorText;
                    nXToyFundsSettlementPolicyResult.errorDetail = nXToyResult.errorDetail;
                    nPListener.onResult(nXToyFundsSettlementPolicyResult);
                    return;
                }
                List<NXToyPolicy> list = ((NXToyPolicyResult) nXToyResult).result.policy;
                if (list == null || list.isEmpty()) {
                    ToyLog.d("FundsSettlementPolicy V1(Get) : policy is null");
                    nPListener.onResult(nXToyFundsSettlementPolicyResult);
                    return;
                }
                for (NXToyPolicy nXToyPolicy : list) {
                    int i = nXToyPolicy.code;
                    NXPPolicy nXPPolicy = new NXPPolicy();
                    nXPPolicy.setTermsList(nXToyPolicy.terms);
                    nXToyFundsSettlementPolicyResult.result.policy = nXPPolicy;
                    nXToyFundsSettlementPolicyResult.result.policy.setStatus((i == 2502 || i == 2503) ? 2 : i == 2504 ? 1 : 0);
                }
                ToyLog.d("FundsSettlementPolicy V1(Get) : return result - " + nXToyFundsSettlementPolicyResult.toString());
                nPListener.onResult(nXToyFundsSettlementPolicyResult);
            }
        });
    }

    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPFundsSettlementPolicy
    public void putPolicy(Context context, String str, final NPListener nPListener) {
        ToyLog.d("FundsSettlementPolicy V1(Put)");
        NXPPolicyManager nXPPolicyManager = NXPPolicyManager.getInstance();
        HashMap map = new HashMap();
        map.put(KEY_BIRTH_DATE_TYPE, str);
        nXPPolicyManager.validatePolicy(NXPPolicyManager.CODE_POLICY_JAPAN_SETTLEMENT_FOUND_YEARS, map, new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPFundsSettlementPolicyImplV1.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("FundsSettlementPolicy V1(Put) : result - " + nXToyResult.toString());
                NXToyFundsSettlementPolicyResult nXToyFundsSettlementPolicyResult = new NXToyFundsSettlementPolicyResult(0, "", "");
                nXToyFundsSettlementPolicyResult.requestTag = NXToyRequestTag.ValidatePolicy.getValue();
                nXToyFundsSettlementPolicyResult.result.policy = new NXPPolicy();
                nXToyFundsSettlementPolicyResult.result.policy.setType(3);
                if (nPListener == null) {
                    ToyLog.d("FundsSettlementPolicy V1(Put) : listener is null");
                    return;
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("FundsSettlementPolicy V1(Put) : error return Result - " + nXToyFundsSettlementPolicyResult.toString());
                    nXToyFundsSettlementPolicyResult.errorCode = nXToyResult.errorCode;
                    nXToyFundsSettlementPolicyResult.errorText = nXToyResult.errorText;
                    nXToyFundsSettlementPolicyResult.errorDetail = nXToyResult.errorDetail;
                    nPListener.onResult(nXToyFundsSettlementPolicyResult);
                }
                int i = ((NXToyValidatePolicyResult) nXToyResult).result.code;
                if (i == 2504) {
                    nXToyFundsSettlementPolicyResult.result.isSubscription = 1;
                } else if (i == 2503) {
                    nXToyFundsSettlementPolicyResult.result.isSubscription = 2;
                }
                ToyLog.d("FundsSettlementPolicy V1(Put) : return result - " + nXToyFundsSettlementPolicyResult.toString());
                nXToyFundsSettlementPolicyResult.result.policy.setStatus(nXToyFundsSettlementPolicyResult.result.isSubscription);
                nPListener.onResult(nXToyFundsSettlementPolicyResult);
            }
        });
    }
}
