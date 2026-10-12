package kr.co.nexon.npaccount.policy.impl;

import android.content.Context;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.request.NXToyDeletePhoneNumberPolicyRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetPhoneNumberPolicyRequest;
import kr.co.nexon.npaccount.auth.request.NXToyPutPhoneNumberPolicyRequest;
import kr.co.nexon.npaccount.auth.result.NXToyGetPolicyListResult;
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberPolicyResult;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPhoneNumberPolicyImplV2 implements NXPPhoneNumberPolicy {
    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy
    public void deletePolicy(String str, final NPListener nPListener) {
        ToyLog.d("PhoneNumberPolicy V2(Delete)");
        NXToyRequestPostman.getInstance().postRequest(new NXToyDeletePhoneNumberPolicyRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV2.4
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("PhoneNumberPolicy V2(Delete) : result - " + nXToyResult.toString());
                if (nPListener == null) {
                    ToyLog.d("PhoneNumberPolicy V2(Delete) : listener is null");
                    return;
                }
                NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = (NXToyPhoneNumberPolicyResult) nXToyResult;
                nXToyPhoneNumberPolicyResult.requestTag = NXToyRequestTag.DeletePhoneNumberPolicy.getValue();
                nXToyPhoneNumberPolicyResult.result.policy = new NXPPolicy();
                nXToyPhoneNumberPolicyResult.result.policy.setType(1);
                ToyLog.d("PhoneNumberPolicy V2 : return result - " + nXToyPhoneNumberPolicyResult.toString());
                nPListener.onResult(nXToyPhoneNumberPolicyResult);
            }
        });
    }

    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy
    public void getPolicy(Context context, String str, final NPListener nPListener) {
        ToyLog.d("PhoneNumberPolicy V2(Get)");
        hasPolicy(context, new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV2.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("PhoneNumberPolicy V2(Get) : has policy result - " + nXToyResult.toString());
                final NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = (NXToyPhoneNumberPolicyResult) nXToyResult;
                if (nXToyPhoneNumberPolicyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("PhoneNumberPolicy V2(Get) : error return result(has policy request) - " + nXToyPhoneNumberPolicyResult.toString());
                    if (nPListener != null) {
                        nPListener.onResult(nXToyPhoneNumberPolicyResult);
                        return;
                    }
                    return;
                }
                if (nXToyPhoneNumberPolicyResult.result.policy != null) {
                    NXToyRequestPostman.getInstance().postRequest(new NXToyGetPhoneNumberPolicyRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV2.2.1
                        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                        public void onComplete(NXToyResult nXToyResult2) {
                            if (nPListener == null) {
                                ToyLog.d("PhoneNumberPolicy V2(Get) : listener is null");
                                return;
                            }
                            ToyLog.d("PhoneNumberPolicy V2(Get) : result - " + nXToyResult2.toString());
                            NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult2 = (NXToyPhoneNumberPolicyResult) nXToyResult2;
                            nXToyPhoneNumberPolicyResult2.requestTag = NXToyRequestTag.GetPhoneNumberPolicy.getValue();
                            nXToyPhoneNumberPolicyResult2.result.policy = new NXPPolicy();
                            nXToyPhoneNumberPolicyResult2.result.policy.setType(1);
                            nXToyPhoneNumberPolicyResult2.result.policy = nXToyPhoneNumberPolicyResult.result.policy;
                            ToyLog.d("PhoneNumberPolicy V2(Get) : return result - " + nXToyPhoneNumberPolicyResult2.toString());
                            nPListener.onResult(nXToyPhoneNumberPolicyResult2);
                        }
                    });
                } else {
                    ToyLog.d("PhoneNumberPolicy V2(Get) : no exist policy - " + nXToyPhoneNumberPolicyResult.toString());
                    if (nPListener != null) {
                        nPListener.onResult(nXToyPhoneNumberPolicyResult);
                    }
                }
            }
        });
    }

    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy
    public void hasPolicy(Context context, final NPListener nPListener) {
        ToyLog.d("PhoneNumberPolicy V2(Has)");
        NXPPolicyManager.getInstance().getPolicyListV2(context, new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV2.1
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("PhoneNumberPolicy V2(Has) : result - " + nXToyResult.toString());
                NXToyGetPolicyListResult nXToyGetPolicyListResult = (NXToyGetPolicyListResult) nXToyResult;
                NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = new NXToyPhoneNumberPolicyResult(0, "", "");
                nXToyPhoneNumberPolicyResult.requestTag = NXToyRequestTag.GetPolicyList.getValue();
                if (nPListener == null) {
                    ToyLog.d("PhoneNumberPolicy V2(Has) : listener is null");
                    return;
                }
                if (nXToyGetPolicyListResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("PhoneNumberPolicy V2(Has) : error return result - " + nXToyGetPolicyListResult.toString());
                    nXToyPhoneNumberPolicyResult.errorCode = nXToyResult.errorCode;
                    nXToyPhoneNumberPolicyResult.errorText = nXToyResult.errorText;
                    nXToyPhoneNumberPolicyResult.errorDetail = nXToyResult.errorDetail;
                    nXToyPhoneNumberPolicyResult.result.policy = new NXPPolicy();
                    nXToyPhoneNumberPolicyResult.result.policy.setType(1);
                    nPListener.onResult(nXToyPhoneNumberPolicyResult);
                    return;
                }
                for (NXPPolicy nXPPolicy : nXToyGetPolicyListResult.result.policyList) {
                    if (nXPPolicy.getType() == 1) {
                        nXToyPhoneNumberPolicyResult.result.policy = nXPPolicy;
                        break;
                    }
                }
                ToyLog.d("PhoneNumberPolicy V2(Has) : return result - " + nXToyPhoneNumberPolicyResult.toString());
                nPListener.onResult(nXToyPhoneNumberPolicyResult);
            }
        });
    }

    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy
    public void putPolicy(String str, final NPListener nPListener) {
        ToyLog.d("PhoneNumberPolicy V2(Put)");
        NXToyRequestPostman.getInstance().postRequest(new NXToyPutPhoneNumberPolicyRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV2.3
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("PhoneNumberPolicy V2(Put) : result - " + nXToyResult.toString());
                if (nPListener == null) {
                    ToyLog.d("PhoneNumberPolicy V2(Put) : listener is null");
                    return;
                }
                NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = (NXToyPhoneNumberPolicyResult) nXToyResult;
                nXToyPhoneNumberPolicyResult.requestTag = NXToyRequestTag.PutPhoneNumberPolicy.getValue();
                nXToyPhoneNumberPolicyResult.result.policy = new NXPPolicy();
                nXToyPhoneNumberPolicyResult.result.policy.setType(1);
                ToyLog.d("PhoneNumberPolicy V2(Put) : return result - " + nXToyPhoneNumberPolicyResult.toString());
                nPListener.onResult(nXToyPhoneNumberPolicyResult);
            }
        });
    }
}
