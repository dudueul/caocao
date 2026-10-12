package kr.co.nexon.npaccount.push;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.facebook.GraphResponse;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.nexon.npaccount.auth.result.NXToyGetPushPolicyResult;
import kr.co.nexon.npaccount.push.interfaces.NXPPushSetting;
import kr.co.nexon.npaccount.push.request.v2.NXToyGetPolicyRequest;
import kr.co.nexon.npaccount.push.request.v2.NXToyPostPolicyRequest;
import kr.co.nexon.npaccount.push.result.v2.NXToyGetPolicyResult;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushSettingImplV2 implements NXPPushSetting {
    public static final String KEY_AD_PUSH_POLICY = "1";
    public static final String KEY_NIGHT_PUSH_POLICY = "2";
    public static final String RESPONSE_ENABLE = "enable";
    public static final String RESPONSE_KIND = "kind";
    public static final String RESPONSE_NAME = "name";
    public static final String RESPONSE_POLICIES = "policies";
    public static final String RESPONSE_PUSH = "push";
    private static Boolean adPushEnabled = null;
    private static Boolean nightPushEnabled = null;

    /* JADX INFO: Access modifiers changed from: private */
    public void cachePushPolicies(NXPPushPolicies nXPPushPolicies) {
        if (nXPPushPolicies == null) {
            return;
        }
        NXPPushPolicy adPolicy = nXPPushPolicies.getAdPolicy();
        if (adPolicy != null) {
            adPushEnabled = Boolean.valueOf(adPolicy.isEnabled());
        }
        NXPPushPolicy nightPolicy = nXPPushPolicies.getNightPolicy();
        if (nightPolicy != null) {
            nightPushEnabled = Boolean.valueOf(nightPolicy.isEnabled());
        }
        ToyLog.d("[cache Info] adPushEnabled:" + adPushEnabled + "  nightPushEnabled:" + nightPushEnabled);
    }

    private boolean isEmptyOrDisabled(NXPPushPolicy nXPPushPolicy) {
        return nXPPushPolicy == null || !nXPPushPolicy.isEnabled();
    }

    private boolean isEnabledPolicy(NXPPushPolicy nXPPushPolicy) {
        return nXPPushPolicy != null && nXPPushPolicy.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map makeGamePolicyParams(NXPPushPolicies nXPPushPolicies) {
        HashMap map = new HashMap();
        List<NXPPushPolicy> gamePolicyList = nXPPushPolicies.getGamePolicyList();
        ToyLog.d("========  gamePush setting info ======");
        for (NXPPushPolicy nXPPushPolicy : gamePolicyList) {
            ToyLog.d("policy index:" + nXPPushPolicy.getKey() + "  , isEnabled:" + nXPPushPolicy.isEnabled());
            map.put(nXPPushPolicy.getKey(), Boolean.valueOf(nXPPushPolicy.isEnabled()));
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map makeToyPolicyParams(NXPPushPolicies nXPPushPolicies) {
        HashMap map = new HashMap();
        NXPPushPolicy adPolicy = nXPPushPolicies.getAdPolicy();
        NXPPushPolicy nightPolicy = nXPPushPolicies.getNightPolicy();
        ToyLog.d("========  toyPush setting info ======");
        if (adPolicy != null) {
            ToyLog.d("adPolicy index:" + adPolicy.getKey() + "  , isEnabled:" + adPolicy.isEnabled());
            map.put(adPolicy.getKey(), Boolean.valueOf(adPolicy.isEnabled()));
        }
        if (nightPolicy != null) {
            ToyLog.d("nightPolicy index:" + nightPolicy.getKey() + "  , isEnabled:" + nightPolicy.isEnabled());
            map.put(nightPolicy.getKey(), Boolean.valueOf(nightPolicy.isEnabled()));
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NXToyResult validatePolicies(NXPPushPolicies nXPPushPolicies) {
        NXPPushPolicy adPolicy = nXPPushPolicies.getAdPolicy();
        NXPPushPolicy nightPolicy = nXPPushPolicies.getNightPolicy();
        if (adPushEnabled == null || !adPushEnabled.booleanValue()) {
            if (!isEmptyOrDisabled(adPolicy) || !isEnabledPolicy(nightPolicy)) {
                return null;
            }
            NXPPushErrorCode nXPPushErrorCode = NXPPushErrorCode.PushSettingNightPushViolation;
            return new NXToyResult(nXPPushErrorCode.getValue(), nXPPushErrorCode.getMessage(), nXPPushErrorCode.getMessage(), NXToyRequestTag.SetPushPolicy.getValue());
        }
        if (adPolicy == null || adPolicy.isEnabled()) {
            return null;
        }
        nXPPushPolicies.setEnableNightPolicy(false);
        return null;
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushSetting
    public void getPolicy(Context context, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetPolicyRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.push.NXPPushSettingImplV2.1
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("get policy failed. errorCode:" + nXToyResult.errorCode + " , message:" + nXToyResult.errorText);
                    NXToyGetPushPolicyResult nXToyGetPushPolicyResult = new NXToyGetPushPolicyResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail);
                    nXToyGetPushPolicyResult.requestTag = NXToyRequestTag.GetPushPolicy.getValue();
                    if (nPListener != null) {
                        nPListener.onResult(nXToyGetPushPolicyResult);
                        return;
                    }
                    return;
                }
                NXPPushPolicies nXPPushPolicies = new NXPPushPolicies();
                NXToyGetPolicyResult nXToyGetPolicyResult = (NXToyGetPolicyResult) nXToyResult;
                NXToyGetPolicyResult.Push push = nXToyGetPolicyResult.push;
                if (push != null && push.policies != null) {
                    for (String str : push.policies.keySet()) {
                        NXToyGetPolicyResult.PoliciesAgreeStatement policiesAgreeStatement = push.policies.get(str);
                        NXPPushPolicy nXPPushPolicy = new NXPPushPolicy(str, policiesAgreeStatement.enable.booleanValue(), policiesAgreeStatement.name);
                        switch (str) {
                            case "1":
                                nXPPushPolicies.setAdPolicy(nXPPushPolicy);
                                break;
                            case "2":
                                nXPPushPolicies.setNightPolicy(nXPPushPolicy);
                                break;
                        }
                    }
                }
                NXToyGetPolicyResult.Kind kind = nXToyGetPolicyResult.kind;
                if (kind != null && kind.policies != null) {
                    for (String str2 : kind.policies.keySet()) {
                        NXToyGetPolicyResult.PoliciesAgreeStatement policiesAgreeStatement2 = kind.policies.get(str2);
                        nXPPushPolicies.putGamePolicy(new NXPPushPolicy(str2, policiesAgreeStatement2.enable.booleanValue(), policiesAgreeStatement2.name));
                    }
                }
                NXToyGetPushPolicyResult nXToyGetPushPolicyResult2 = new NXToyGetPushPolicyResult(NXToyErrorCode.SUCCESS.getCode(), GraphResponse.SUCCESS_KEY);
                nXToyGetPushPolicyResult2.requestTag = NXToyRequestTag.GetPushPolicy.getValue();
                nXToyGetPushPolicyResult2.result.policies = nXPPushPolicies;
                NXPPushSettingImplV2.this.cachePushPolicies(nXPPushPolicies);
                if (nPListener != null) {
                    nPListener.onResult(nXToyGetPushPolicyResult2);
                }
            }
        });
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushSetting
    public void setPolicy(Context context, @NonNull final NXPPushPolicies nXPPushPolicies, String str, final NPListener nPListener) {
        final Runnable runnable = new Runnable() { // from class: kr.co.nexon.npaccount.push.NXPPushSettingImplV2.2
            @Override // java.lang.Runnable
            public void run() {
                NXToyResult nXToyResultValidatePolicies = NXPPushSettingImplV2.this.validatePolicies(nXPPushPolicies);
                if (nXToyResultValidatePolicies == null) {
                    NXToyRequestPostman.getInstance().postRequest(new NXToyPostPolicyRequest(NXPPushSettingImplV2.this.makeToyPolicyParams(nXPPushPolicies), NXPPushSettingImplV2.this.makeGamePolicyParams(nXPPushPolicies)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.push.NXPPushSettingImplV2.2.1
                        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                        public void onComplete(NXToyResult nXToyResult) {
                            nXToyResult.requestTag = NXToyRequestTag.SetPushPolicy.getValue();
                            if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                NXPPushSettingImplV2.this.cachePushPolicies(nXPPushPolicies);
                            }
                            if (nPListener != null) {
                                nPListener.onResult(nXToyResult);
                            }
                        }
                    });
                } else if (nPListener != null) {
                    nPListener.onResult(nXToyResultValidatePolicies);
                }
            }
        };
        if (adPushEnabled == null) {
            getPolicy(context, new NPListener() { // from class: kr.co.nexon.npaccount.push.NXPPushSettingImplV2.3
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    new Handler(Looper.getMainLooper()).post(runnable);
                }
            });
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
}
