package kr.co.nexon.npaccount.policy.impl;

import android.content.Context;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import kr.co.nexon.mdev.android.util.NXTelephonyUtil;
import kr.co.nexon.npaccount.auth.request.NXToyPutFundsSettlementPolicyRequest;
import kr.co.nexon.npaccount.auth.result.NXToyFundsSettlementPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyGetPolicyListResult;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.policy.interfaces.NXPFundsSettlementPolicy;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPFundsSettlementPolicyImplV2 implements NXPFundsSettlementPolicy {
    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPFundsSettlementPolicy
    public void getPolicy(Context context, final NPListener nPListener) {
        ToyLog.d("FundsSettlementPolicy V2(Get)");
        NXPPolicyManager.getInstance().getPolicyListV2(context, new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPFundsSettlementPolicyImplV2.1
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("FundsSettlementPolicy V2(Get) : result - " + nXToyResult.toString());
                NXToyFundsSettlementPolicyResult nXToyFundsSettlementPolicyResult = new NXToyFundsSettlementPolicyResult(0, "", "");
                nXToyFundsSettlementPolicyResult.requestTag = NXToyRequestTag.GetPolicyList.getValue();
                nXToyFundsSettlementPolicyResult.result.policy = new NXPPolicy();
                nXToyFundsSettlementPolicyResult.result.policy.setType(3);
                if (nPListener == null) {
                    ToyLog.d("FundsSettlementPolicy V2(Get) : listener is null");
                    return;
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("FundsSettlementPolicy V2(Get) : error return result - " + nXToyFundsSettlementPolicyResult.toString());
                    nXToyFundsSettlementPolicyResult.errorCode = nXToyResult.errorCode;
                    nXToyFundsSettlementPolicyResult.errorText = nXToyResult.errorText;
                    nXToyFundsSettlementPolicyResult.errorDetail = nXToyResult.errorDetail;
                    nPListener.onResult(nXToyFundsSettlementPolicyResult);
                    return;
                }
                List<NXPPolicy> list = ((NXToyGetPolicyListResult) nXToyResult).result.policyList;
                if (list == null || list.isEmpty()) {
                    ToyLog.d("FundsSettlementPolicy V2(Get) : policy is null");
                    nPListener.onResult(nXToyFundsSettlementPolicyResult);
                    return;
                }
                boolean z = false;
                for (NXPPolicy nXPPolicy : list) {
                    if (nXPPolicy.getType() == 3) {
                        nXToyFundsSettlementPolicyResult.result.policy = nXPPolicy;
                        z = true;
                        break;
                    }
                }
                nXToyFundsSettlementPolicyResult.result.policy.setStatus(z ? nXToyFundsSettlementPolicyResult.result.policy.getStatus() == 1 ? 1 : 2 : 0);
                ToyLog.d("FundsSettlementPolicy V2(Get) : return result - " + nXToyFundsSettlementPolicyResult.toString());
                nPListener.onResult(nXToyFundsSettlementPolicyResult);
            }
        });
    }

    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPFundsSettlementPolicy
    public void putPolicy(Context context, String str, final NPListener nPListener) {
        int i;
        int i2;
        ToyLog.d("FundsSettlementPolicy V2(Put)");
        String mcc = NXTelephonyUtil.getMcc(context);
        String mnc = NXTelephonyUtil.getMnc(context);
        if (mcc == null) {
            i = 0;
        } else {
            try {
                i = Integer.parseInt(mcc);
            } catch (Exception e) {
                i2 = 0;
                i = 0;
            }
        }
        i2 = mnc == null ? 0 : Integer.parseInt(mnc);
        NXToyRequestPostman.getInstance().postRequest(new NXToyPutFundsSettlementPolicyRequest(i2, i, str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPFundsSettlementPolicyImplV2.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("FundsSettlementPolicy V2(Put) : result - " + nXToyResult.toString());
                NXToyFundsSettlementPolicyResult nXToyFundsSettlementPolicyResult = (NXToyFundsSettlementPolicyResult) nXToyResult;
                nXToyFundsSettlementPolicyResult.requestTag = NXToyRequestTag.PutFundsSettlementPolicy.getValue();
                nXToyFundsSettlementPolicyResult.result.policy = new NXPPolicy();
                nXToyFundsSettlementPolicyResult.result.policy.setType(3);
                if (nPListener == null) {
                    ToyLog.d("FundsSettlementPolicy V2(Put) : listener is null");
                    return;
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("FundsSettlementPolicy V1(Put) : error return Result - " + nXToyFundsSettlementPolicyResult.toString());
                    nXToyFundsSettlementPolicyResult.errorCode = nXToyResult.errorCode;
                    nXToyFundsSettlementPolicyResult.errorText = nXToyResult.errorText;
                    nXToyFundsSettlementPolicyResult.errorDetail = nXToyResult.errorDetail;
                    nPListener.onResult(nXToyResult);
                }
                ToyLog.d("FundsSettlementPolicy V2(Put) : return result - " + nXToyFundsSettlementPolicyResult.toString());
                nXToyFundsSettlementPolicyResult.result.policy.setStatus(nXToyFundsSettlementPolicyResult.result.isSubscription);
                nPListener.onResult(nXToyFundsSettlementPolicyResult);
            }
        });
    }
}
