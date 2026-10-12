package kr.co.nexon.npaccount.policy.impl;

import android.content.Context;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.HashMap;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyValidatePolicyResult;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;
import kr.co.nexon.npaccount.auth.result.model.NXToyPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPhoneNumberPolicyImplV1 implements NXPPhoneNumberPolicy {
    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy
    public void deletePolicy(String str, final NPListener nPListener) {
        ToyLog.d("PhoneNumberPolicy V1(Delete)");
        NXPPolicyManager nXPPolicyManager = NXPPolicyManager.getInstance();
        HashMap map = new HashMap();
        map.put("phone", str);
        map.put("method", "delete");
        nXPPolicyManager.validatePolicy(NXPPolicyManager.CODE_POLICY_KOREA_COLLECTION_PHONE_NUMBER, map, new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV1.4
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("PhoneNumberPolicy V1(Delete) : result - " + nXToyResult.toString());
                NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = new NXToyPhoneNumberPolicyResult(0, "", "");
                nXToyPhoneNumberPolicyResult.requestTag = NXToyRequestTag.ValidatePolicy.getValue();
                nXToyPhoneNumberPolicyResult.result.policy = new NXPPolicy();
                nXToyPhoneNumberPolicyResult.result.policy.setType(1);
                if (nPListener == null) {
                    ToyLog.d("PhoneNumberPolicy V1(Delete) : listener is null");
                    return;
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("PhoneNumberPolicy V1(Delete) : error return result - " + nXToyPhoneNumberPolicyResult.toString());
                    nXToyPhoneNumberPolicyResult.errorCode = nXToyResult.errorCode;
                    nXToyPhoneNumberPolicyResult.errorText = nXToyResult.errorText;
                    nXToyPhoneNumberPolicyResult.errorDetail = nXToyResult.errorDetail;
                    nPListener.onResult(nXToyPhoneNumberPolicyResult);
                    return;
                }
                int i = ((NXToyValidatePolicyResult) nXToyResult).result.code;
                if (i == 2501) {
                    nXToyPhoneNumberPolicyResult.result.isSubscription = 1;
                } else if (i == 2505) {
                    nXToyPhoneNumberPolicyResult.result.isSubscription = 2;
                }
                ToyLog.d("PhoneNumberPolicy V1(Delete) : return result - " + nXToyPhoneNumberPolicyResult.toString());
                nXToyPhoneNumberPolicyResult.result.policy.setStatus(nXToyPhoneNumberPolicyResult.result.isSubscription);
                nPListener.onResult(nXToyPhoneNumberPolicyResult);
            }
        });
    }

    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy
    public void getPolicy(Context context, final String str, final NPListener nPListener) {
        ToyLog.d("PhoneNumberPolicy V1 : Get");
        hasPolicy(context, new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV1.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("PhoneNumberPolicy V1(Get) : has policy result - " + nXToyResult.toString());
                NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = (NXToyPhoneNumberPolicyResult) nXToyResult;
                if (nXToyPhoneNumberPolicyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("PhoneNumberPolicy V1(Get) : error return result(has policy request) - " + nXToyPhoneNumberPolicyResult.toString());
                    if (nPListener != null) {
                        nPListener.onResult(nXToyPhoneNumberPolicyResult);
                        return;
                    }
                    return;
                }
                HashMap map = new HashMap();
                map.put("phone", str);
                map.put("method", "get");
                NXPPolicyManager.getInstance().validatePolicy(NXPPolicyManager.CODE_POLICY_KOREA_COLLECTION_PHONE_NUMBER, map, new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV1.2.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        ToyLog.d("PhoneNumberPolicy V1(Get) : result - " + nXToyResult2.toString());
                        NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult2 = new NXToyPhoneNumberPolicyResult(0, "", "");
                        nXToyPhoneNumberPolicyResult2.requestTag = NXToyRequestTag.ValidatePolicy.getValue();
                        nXToyPhoneNumberPolicyResult2.result.policy = new NXPPolicy();
                        nXToyPhoneNumberPolicyResult2.result.policy.setType(1);
                        if (nPListener == null) {
                            ToyLog.d("PhoneNumberPolicy V1(Get) : listener is null");
                            return;
                        }
                        if (nXToyResult2.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                            ToyLog.d("PhoneNumberPolicy V1(Get) : error return result - " + nXToyPhoneNumberPolicyResult2.toString());
                            nXToyPhoneNumberPolicyResult2.errorCode = nXToyResult2.errorCode;
                            nXToyPhoneNumberPolicyResult2.errorText = nXToyResult2.errorText;
                            nXToyPhoneNumberPolicyResult2.errorDetail = nXToyResult2.errorDetail;
                            nPListener.onResult(nXToyPhoneNumberPolicyResult2);
                            return;
                        }
                        int i = ((NXToyValidatePolicyResult) nXToyResult2).result.code;
                        if (i == 2501) {
                            nXToyPhoneNumberPolicyResult2.result.isSubscription = 2;
                        } else if (i == 2505) {
                            nXToyPhoneNumberPolicyResult2.result.isSubscription = 1;
                        }
                        ToyLog.d("PhoneNumberPolicy V1(Get) : return result - " + nXToyPhoneNumberPolicyResult2.toString());
                        nXToyPhoneNumberPolicyResult2.result.policy.setStatus(nXToyPhoneNumberPolicyResult2.result.isSubscription);
                        nPListener.onResult(nXToyPhoneNumberPolicyResult2);
                    }
                });
            }
        });
    }

    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy
    public void hasPolicy(Context context, final NPListener nPListener) {
        ToyLog.d("PhoneNumberPolicy V1(Has)");
        NXPPolicyManager.getInstance().getPolicyListV1(context, "terms", new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV1.1
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("PhoneNumberPolicy V1(Has) : result - " + nXToyResult.toString());
                NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = new NXToyPhoneNumberPolicyResult(0, "", "");
                nXToyPhoneNumberPolicyResult.requestTag = NXToyRequestTag.GetPolicy.getValue();
                nXToyPhoneNumberPolicyResult.result.policy = new NXPPolicy();
                nXToyPhoneNumberPolicyResult.result.policy.setType(3);
                if (nPListener == null) {
                    ToyLog.d("PhoneNumberPolicy V1(Has) : listener is null");
                    return;
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("PhoneNumberPolicy V1(Has) : error return result - " + nXToyPhoneNumberPolicyResult.toString());
                    nXToyPhoneNumberPolicyResult.errorCode = nXToyResult.errorCode;
                    nXToyPhoneNumberPolicyResult.errorText = nXToyResult.errorText;
                    nXToyPhoneNumberPolicyResult.errorDetail = nXToyResult.errorDetail;
                    nPListener.onResult(nXToyPhoneNumberPolicyResult);
                    return;
                }
                List<NXToyPolicy> list = ((NXToyPolicyResult) nXToyResult).result.policy;
                if (list == null || list.isEmpty()) {
                    ToyLog.d("PhoneNumberPolicy V1(Has) : policy is null");
                    nPListener.onResult(nXToyPhoneNumberPolicyResult);
                    return;
                }
                for (NXToyPolicy nXToyPolicy : list) {
                    NXPPolicy nXPPolicy = new NXPPolicy();
                    nXPPolicy.setTermsList(nXToyPolicy.terms);
                    nXToyPhoneNumberPolicyResult.result.policy = nXPPolicy;
                    if (nXToyPolicy.code == 2505 && nXToyPolicy.isTermsAgree == 1) {
                        nXToyPhoneNumberPolicyResult.result.isSubscription = 1;
                        break;
                    }
                }
                ToyLog.d("PhoneNumberPolicy V1(Has) : return result - " + nXToyPhoneNumberPolicyResult.toString());
                nXToyPhoneNumberPolicyResult.result.policy.setStatus(nXToyPhoneNumberPolicyResult.result.isSubscription);
                nPListener.onResult(nXToyPhoneNumberPolicyResult);
            }
        });
    }

    @Override // kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy
    public void putPolicy(String str, final NPListener nPListener) {
        ToyLog.d("PhoneNumberPolicy V1(Put)");
        NXPPolicyManager nXPPolicyManager = NXPPolicyManager.getInstance();
        HashMap map = new HashMap();
        map.put("phone", str);
        map.put("method", "put");
        nXPPolicyManager.validatePolicy(NXPPolicyManager.CODE_POLICY_KOREA_COLLECTION_PHONE_NUMBER, map, new NPListener() { // from class: kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV1.3
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("PhoneNumberPolicy V1(Put) : result - " + nXToyResult.toString());
                NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = new NXToyPhoneNumberPolicyResult(0, "", "");
                nXToyPhoneNumberPolicyResult.requestTag = NXToyRequestTag.ValidatePolicy.getValue();
                nXToyPhoneNumberPolicyResult.result.policy = new NXPPolicy();
                nXToyPhoneNumberPolicyResult.result.policy.setType(1);
                if (nPListener == null) {
                    ToyLog.d("PhoneNumberPolicy V1(Put) : listener is null");
                    return;
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("PhoneNumberPolicy V1(Put) : error return result - " + nXToyPhoneNumberPolicyResult.toString());
                    nXToyPhoneNumberPolicyResult.errorCode = nXToyResult.errorCode;
                    nXToyPhoneNumberPolicyResult.errorText = nXToyResult.errorText;
                    nXToyPhoneNumberPolicyResult.errorDetail = nXToyResult.errorDetail;
                    nPListener.onResult(nXToyPhoneNumberPolicyResult);
                    return;
                }
                int i = ((NXToyValidatePolicyResult) nXToyResult).result.code;
                if (i == 2501) {
                    nXToyPhoneNumberPolicyResult.result.isSubscription = 1;
                } else if (i == 2505) {
                    nXToyPhoneNumberPolicyResult.result.isSubscription = 2;
                }
                ToyLog.d("PhoneNumberPolicy V1(Put) : return result - " + nXToyPhoneNumberPolicyResult.toString());
                nXToyPhoneNumberPolicyResult.result.policy.setStatus(nXToyPhoneNumberPolicyResult.result.isSubscription);
                nPListener.onResult(nXToyPhoneNumberPolicyResult);
            }
        });
    }
}
