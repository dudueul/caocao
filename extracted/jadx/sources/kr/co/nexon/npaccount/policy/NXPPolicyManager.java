package kr.co.nexon.npaccount.policy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.GraphResponse;
import com.nexon.core.locale.NXLocale;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionListener;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;
import kr.co.nexon.mdev.android.util.NXTelephonyUtil;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.request.NXToyDeleteEmailPolicyRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetEmailPolicyRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetPolicyListRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetPolicyRequest;
import kr.co.nexon.npaccount.auth.request.NXToyPutEmailPolicyRequest;
import kr.co.nexon.npaccount.auth.request.NXToyValidatePolicyRequest;
import kr.co.nexon.npaccount.auth.result.NXToyEmailPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyFundsSettlementPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyGetPolicyListResult;
import kr.co.nexon.npaccount.auth.result.NXToyGetPushPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyGetSmsEnabledResult;
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberResult;
import kr.co.nexon.npaccount.auth.result.NXToyPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyValidatePolicyResult;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;
import kr.co.nexon.npaccount.auth.result.model.NXToyPolicy;
import kr.co.nexon.npaccount.policy.impl.NXPFundsSettlementPolicyImplV1;
import kr.co.nexon.npaccount.policy.impl.NXPFundsSettlementPolicyImplV2;
import kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV1;
import kr.co.nexon.npaccount.policy.impl.NXPPhoneNumberPolicyImplV2;
import kr.co.nexon.npaccount.policy.interfaces.NXPFundsSettlementPolicy;
import kr.co.nexon.npaccount.policy.interfaces.NXPPhoneNumberPolicy;
import kr.co.nexon.npaccount.push.NXPPush;
import kr.co.nexon.npaccount.push.NXPPushErrorCode;
import kr.co.nexon.npaccount.push.NXPPushMenuOption;
import kr.co.nexon.npaccount.push.NXPPushPolicies;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog;
import kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPolicyManager {
    public static final int CODE_POLICY_JAPAN_SETTLEMENT_FOUND_OVER_AGE = 2504;
    public static final int CODE_POLICY_JAPAN_SETTLEMENT_FOUND_UNDER_AGE = 2503;
    public static final int CODE_POLICY_JAPAN_SETTLEMENT_FOUND_YEARS = 2502;
    public static final int CODE_POLICY_KOREA_COLLECTION_PHONE_NUMBER = 2505;
    public static final int CODE_POLICY_NONE = 2500;
    public static final int CODE_POLICY_NONE_TARGET_USER = 2501;
    public static final int CODE_POLICY_TYPE_AD_PUSH = 4;
    public static final int CODE_POLICY_TYPE_EMAIL = 2;
    public static final int CODE_POLICY_TYPE_FUNDS_SETTLEMTNT = 3;
    public static final int CODE_POLICY_TYPE_NIGHT_PUSH = 5;
    public static final int CODE_POLICY_TYPE_PHONE_NUMBER = 1;
    public static final String CODE_VALIDATE_POLICY_DATA_IS_TERMS_AGREE = "isTermsAgree";
    public static final String CODE_VALIDATE_POLICY_DATA_METHOD = "method";
    public static final String CODE_VALIDATE_POLICY_DATA_METHOD_DELETE = "delete";
    public static final String CODE_VALIDATE_POLICY_DATA_METHOD_GET = "get";
    public static final String CODE_VALIDATE_POLICY_DATA_METHOD_PUT = "put";
    public static final String CODE_VALIDATE_POLICY_DATA_PHONE = "phone";
    public static final int POLICY_AGREE = 1;
    public static final int POLICY_DISAGREE = 2;
    public static final int POLICY_NONE = 0;
    public static final String TYPE_POLICY_PURCHASE = "purchase";
    public static final String TYPE_POLICY_TERMS = "terms";
    private static volatile NXPPolicyManager instance;

    private NXPPolicyManager() {
    }

    private NXPFundsSettlementPolicy createFundsSettlementPolicy() {
        return getPolicyVersion() == 1 ? new NXPFundsSettlementPolicyImplV1() : new NXPFundsSettlementPolicyImplV2();
    }

    private NXPPhoneNumberPolicy createPhoneNumberPolicy() {
        return getPolicyVersion() == 1 ? new NXPPhoneNumberPolicyImplV1() : new NXPPhoneNumberPolicyImplV2();
    }

    public static NXPPolicyManager getInstance() {
        if (instance == null) {
            synchronized (NXPPolicyManager.class) {
                if (instance == null) {
                    instance = new NXPPolicyManager();
                }
            }
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPolicyVersion() {
        return NXToyCommonPreferenceController.getInstance().getPolicyApiVer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAddedTermsOfPolicy(List<NXToyTerm> list, List<NXToyTerm> list2) {
        if (list2.isEmpty()) {
            ToyLog.d("policyTermsList is empty");
            return false;
        }
        if (list.isEmpty()) {
            ToyLog.d("displayTermsList is empty");
            return false;
        }
        HashMap<Integer, NXToyTerm> mapTermsListToTermsMap = NXPTermsManager.getInstance().termsListToTermsMap(list);
        Iterator<NXToyTerm> it = list2.iterator();
        while (it.hasNext()) {
            if (mapTermsListToTermsMap.containsKey(Integer.valueOf(it.next().termID))) {
                return true;
            }
        }
        return false;
    }

    private boolean isAllIncludedTermsFromPolicy(HashMap<Integer, NXToyTerm> map, List<NXToyTerm> list) {
        if (map.isEmpty() || list.isEmpty()) {
            return false;
        }
        Iterator<NXToyTerm> it = list.iterator();
        while (it.hasNext()) {
            if (!map.containsKey(Integer.valueOf(it.next().termID))) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlreadyAgreeAllTermsLinkedToPolicy(List<NXToyTerm> list, NXPPolicy nXPPolicy) {
        int agreeTermsCountLinkedToPolicy = getAgreeTermsCountLinkedToPolicy(list, nXPPolicy);
        ToyLog.d("alreadyAgreeTermsCountLinkedToPolicy:" + agreeTermsCountLinkedToPolicy + " ,  termsListLinkedToPolicy.size:" + nXPPolicy.getTermsList().size());
        return agreeTermsCountLinkedToPolicy == nXPPolicy.getTermsList().size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isEnabledPushPolicy(List<NXToyTerm> list, NXPPolicy nXPPolicy) {
        int i = 0;
        if (nXPPolicy != null) {
            int policyStatus = getPolicyStatus(list, nXPPolicy);
            ToyLog.d("getPolicyStatus from agreeTermsList and pushPolicy :" + policyStatus);
            i = policyStatus == 1 ? 1 : 2;
            ToyLog.d("pushPolicyStatus : " + i);
        }
        return i != 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NXToyFundsSettlementPolicyResult makeFundsSettlementPolicyResult(NXToyResult nXToyResult, String str, String str2, String str3) {
        NXToyFundsSettlementPolicyResult nXToyFundsSettlementPolicyResult;
        if (nXToyResult instanceof NXToyFundsSettlementPolicyResult) {
            nXToyFundsSettlementPolicyResult = (NXToyFundsSettlementPolicyResult) nXToyResult;
        } else {
            nXToyFundsSettlementPolicyResult = new NXToyFundsSettlementPolicyResult();
            nXToyFundsSettlementPolicyResult.errorCode = nXToyResult.errorCode;
            nXToyFundsSettlementPolicyResult.errorText = nXToyResult.errorText;
            nXToyFundsSettlementPolicyResult.errorDetail = nXToyResult.errorDetail;
            nXToyFundsSettlementPolicyResult.requestTag = nXToyResult.requestTag;
        }
        NXToyFundsSettlementPolicyResult.ResultSet resultSet = nXToyFundsSettlementPolicyResult.result;
        if (NXStringUtil.isNull(str)) {
            str = "";
        }
        resultSet.itemName = str;
        NXToyFundsSettlementPolicyResult.ResultSet resultSet2 = nXToyFundsSettlementPolicyResult.result;
        if (NXStringUtil.isNull(str2)) {
            str2 = "";
        }
        resultSet2.itemPrice = str2;
        NXToyFundsSettlementPolicyResult.ResultSet resultSet3 = nXToyFundsSettlementPolicyResult.result;
        if (NXStringUtil.isNull(str3)) {
            str3 = "";
        }
        resultSet3.productId = str3;
        return nXToyFundsSettlementPolicyResult;
    }

    private NXPPushPolicies makeV1PushPolicies(boolean z) {
        boolean z2;
        if (NXToyLocaleManager.getInstance().getCountry() == NXLocale.COUNTRY.Korea) {
            ToyLog.d("didAgreeAdPushTerms:" + z);
            z2 = z;
        } else {
            z2 = true;
        }
        NXPPushPolicies nXPPushPolicies = new NXPPushPolicies();
        nXPPushPolicies.setEnableAdPolicy(z2);
        return nXPPushPolicies;
    }

    private NXPPushPolicies makeV2PushPolicies(boolean z, boolean z2) {
        NXPPushPolicies nXPPushPolicies = new NXPPushPolicies();
        if (NXToyLocaleManager.getInstance().getCountry() == NXLocale.COUNTRY.Korea) {
            ToyLog.d("didAgreeAdPushTerms:" + z + ", didAgreeNightPushTerms:" + z2);
            nXPPushPolicies.setEnableAdPolicy(z);
            nXPPushPolicies.setEnableNightPolicy(z2);
        } else {
            nXPPushPolicies.setEnableAdPolicy(true);
            nXPPushPolicies.setEnableNightPolicy(true);
        }
        return nXPPushPolicies;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processPhoneNumberCollection(final Activity activity, final NPListener nPListener) {
        if (!NXRuntimePermissionManager.getInstance().isPermissionFromManifest(activity, NXRuntimePermissionManager.READ_PHONE_STATE)) {
            if (nPListener != null) {
                nPListener.onResult(new NXToyResult(NXToyErrorCode.FAILED_GET_PHONE_NUMBER.getCode(), "", ""));
                return;
            }
            return;
        }
        final long npsn = NXToySessionManager.getInstance().getSession().getNpsn();
        final NPListener nPListener2 = new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.13
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("collectPhoneNumber Result : " + nXToyResult.toString());
                NPListener nPListener3 = new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.13.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        if (nPListener != null) {
                            nPListener.onResult(new NXToyResult(NXToyErrorCode.PERMISSION_DENIED.getCode(), "", ""));
                        }
                    }
                };
                if (nXToyResult.errorCode == NXToyErrorCode.PERMISSION_DENIED.getCode()) {
                    ToyLog.d("Read phone state permission denied. errorText : " + nXToyResult.errorText);
                    NXToyCommonPreferenceController.getInstance().setHasPhoneNumberFlag(npsn, false);
                    NXPPolicyManager.this.deletePhoneNumberPolicy("", nPListener3);
                    return;
                }
                String str = ((NXToyPhoneNumberResult) nXToyResult).result.phoneNumber;
                if (!NXStringUtil.isNull(str)) {
                    NXPPolicyManager.this.putPhoneNumberPolicy(str, nPListener);
                    return;
                }
                ToyLog.d("Not found phone number. errorText : " + nXToyResult.errorText);
                NXToyCommonPreferenceController.getInstance().setHasPhoneNumberFlag(npsn, false);
                if (nPListener != null) {
                    nPListener.onResult(new NXToyResult(NXToyErrorCode.FAILED_GET_PHONE_NUMBER.getCode(), "", ""));
                }
            }
        };
        boolean zCheckGrantedPermission = NXRuntimePermissionManager.getInstance().checkGrantedPermission(activity, NXRuntimePermissionManager.READ_PHONE_STATE);
        boolean hasPhoneNumberFlag = NXToyCommonPreferenceController.getInstance().getHasPhoneNumberFlag(npsn);
        String phoneNumber = zCheckGrantedPermission ? NXTelephonyUtil.getPhoneNumber(activity) : "";
        if (hasPhoneNumberFlag || !NXStringUtil.isNull(phoneNumber)) {
            showPhonePermissionConfirmationDialog(activity, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.14
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    NXPPolicyManager.this.setPhoneNumberPolicy(activity, nPListener2);
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NXToyErrorCode.FAILED_GET_PHONE_NUMBER.getCode(), "", ""));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSetupPolicyWithEmailCollection(List<NXToyTerm> list, List<NXToyTerm> list2, NXPPolicy nXPPolicy) {
        ToyLog.d("emailPolicyStatus description =>  0 -> None , 1 -> Agree, 2 -> Disagree");
        ToyLog.d("emailPolicyStatus:" + nXPPolicy.getStatus() + " signUpTermsList.size:" + list.size() + " displayTermsList.size:" + list2.size());
        NXToySession session = NXToySessionManager.getInstance().getSession();
        if (session == null) {
            ToyLog.d("Email collection status : session is null");
            return;
        }
        String emailId = session.getEmailId();
        if (NXStringUtil.isNull(emailId)) {
            ToyLog.d("Email collection status : email is null or empty emailId :[" + NXStringUtil.getMaskedEmail(emailId) + "]");
            return;
        }
        List<NXToyTerm> termsList = nXPPolicy.getTermsList();
        if (nXPPolicy.getStatus() == 0 && (termsList.isEmpty() || isAlreadyAgreeAllTermsLinkedToPolicy(list, nXPPolicy))) {
            putEmailPolicy(emailId, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.6
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    ToyLog.d("Email policy result :  " + nXToyResult);
                }
            });
            return;
        }
        if (!isAddedTermsOfPolicy(list2, termsList)) {
            ToyLog.d("Not exist added terms list in email policy");
        } else if (getPolicyStatus(list, nXPPolicy) == 1) {
            putEmailPolicy(emailId, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.7
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    ToyLog.d("Email policy result :  " + nXToyResult);
                }
            });
        } else {
            ToyLog.d("Email collection status : delete emailID : " + NXStringUtil.getMaskedEmail(emailId));
            deleteEmailPolicy(emailId, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSetupPolicyWithPhoneNumberConllection(final Activity activity, List<NXToyTerm> list, List<NXToyTerm> list2, NXPPolicy nXPPolicy, final NPListener nPListener) {
        if (nXPPolicy.getStatus() == 0 && (nXPPolicy.getTermsList().isEmpty() || isAlreadyAgreeAllTermsLinkedToPolicy(list, nXPPolicy))) {
            processPhoneNumberCollection(activity, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.8
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    ToyLog.d("Phone number policy result : " + nXToyResult);
                    nPListener.onResult(nXToyResult);
                }
            });
            return;
        }
        NXToyResult nXToyResult = new NXToyResult(0, "", "");
        if (!isAddedTermsOfPolicy(list2, nXPPolicy.getTermsList())) {
            ToyLog.d("Not exist added terms list in phone number policy");
            nPListener.onResult(nXToyResult);
        } else {
            if (getPolicyStatus(list, nXPPolicy) == 1) {
                processPhoneNumberCollection(activity, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.10
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        ToyLog.d("Phone number policy result : " + nXToyResult2);
                        nPListener.onResult(nXToyResult2);
                    }
                });
                return;
            }
            boolean zCheckGrantedPermission = NXRuntimePermissionManager.getInstance().checkGrantedPermission(activity, NXRuntimePermissionManager.READ_PHONE_STATE);
            if (zCheckGrantedPermission) {
                deletePhoneNumberPolicy(NXTelephonyUtil.getPhoneNumber(activity), new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.9
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        ToyLog.d("Phone number collection status : delete phone number : ", "phoneNumber", NXTelephonyUtil.getPhoneNumber(activity));
                        nPListener.onResult(nXToyResult2);
                    }
                });
            } else {
                ToyLog.d("Phone number collection status : not used permission : " + zCheckGrantedPermission);
                nPListener.onResult(nXToyResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSetupPolicyWithPushReceiveAgreement(final Activity activity, final List<NXToyTerm> list, final List<NXToyTerm> list2, final NXPPolicy nXPPolicy, final NXPPolicy nXPPolicy2) {
        if (NXPPush.getPushServiceVersion() == 1) {
            return;
        }
        NXPPush.getPolicy(activity, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.5
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXPPushPolicies nXPPushPolicies;
                ToyLog.d("agreeTermsList:" + list);
                ToyLog.d("adPushPolicy:" + nXPPolicy);
                ToyLog.d("nightPushPolicy:" + nXPPolicy2);
                boolean zIsEnabledPushPolicy = NXPPolicyManager.this.isEnabledPushPolicy(list, nXPPolicy);
                boolean zIsEnabledPushPolicy2 = NXPPolicyManager.this.isEnabledPushPolicy(list, nXPPolicy2);
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    nXPPushPolicies = ((NXToyGetPushPolicyResult) nXToyResult).result.policies;
                    if (nXPPolicy != null && NXPPolicyManager.this.isAddedTermsOfPolicy(list2, nXPPolicy.getTermsList())) {
                        nXPPushPolicies.setEnableAdPolicy(zIsEnabledPushPolicy);
                    }
                    if (nXPPolicy2 != null && NXPPolicyManager.this.isAddedTermsOfPolicy(list2, nXPPolicy2.getTermsList())) {
                        nXPPushPolicies.setEnableNightPolicy(zIsEnabledPushPolicy2);
                    }
                } else {
                    if (nXToyResult.errorCode != NXPPushErrorCode.NotRegisteredNpsn.getValue()) {
                        return;
                    }
                    nXPPushPolicies = new NXPPushPolicies();
                    if (nXPPolicy == null) {
                        if (NXToyLocaleManager.getInstance().getCountry() == NXLocale.COUNTRY.Korea) {
                            zIsEnabledPushPolicy2 = false;
                        }
                    } else if (nXPPolicy2 == null && NXToyLocaleManager.getInstance().getCountry() == NXLocale.COUNTRY.Korea) {
                        zIsEnabledPushPolicy2 = false;
                    }
                    if (!zIsEnabledPushPolicy) {
                        zIsEnabledPushPolicy2 = false;
                    }
                    nXPPushPolicies.setEnableAdPolicy(zIsEnabledPushPolicy);
                    nXPPushPolicies.setEnableNightPolicy(zIsEnabledPushPolicy2);
                }
                ToyLog.d("setPushPolicy Parameter Info : " + nXPPushPolicies.toString());
                NXPPolicyManager.this.setPushPolicy(activity, nXPPushPolicies, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.5.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        ToyLog.d("setPushPolicy result :  " + nXToyResult2);
                    }
                });
            }
        });
    }

    public void deleteEmailPolicy(String str, final NPListener nPListener) {
        ToyLog.d("EmailPolicy V2(Delete)");
        NXToyRequestPostman.getInstance().postRequest(new NXToyDeleteEmailPolicyRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.20
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nPListener == null) {
                    ToyLog.d("EmailPolicy V2(Delete) : listener is null");
                    return;
                }
                NXToyEmailPolicyResult nXToyEmailPolicyResult = (NXToyEmailPolicyResult) nXToyResult;
                nXToyEmailPolicyResult.requestTag = NXToyRequestTag.DeleteEmailPolicy.getValue();
                nXToyEmailPolicyResult.result.policy = new NXPPolicy();
                nXToyEmailPolicyResult.result.policy.setType(2);
                ToyLog.d("EmailPolicy V2(Delete) : return result - " + nXToyResult.toString());
                nPListener.onResult(nXToyEmailPolicyResult);
            }
        });
    }

    public void deletePhoneNumberPolicy(String str, NPListener nPListener) {
        createPhoneNumberPolicy().deletePolicy(str, nPListener);
    }

    public int getAgreeTermsCountLinkedToPolicy(List<NXToyTerm> list, NXPPolicy nXPPolicy) {
        int i = 0;
        HashMap<Integer, NXToyTerm> mapTermsListToTermsMap = NXPTermsManager.getInstance().termsListToTermsMap(list);
        for (NXToyTerm nXToyTerm : nXPPolicy.getTermsList()) {
            NXToyTerm nXToyTerm2 = mapTermsListToTermsMap.get(Integer.valueOf(nXToyTerm.termID));
            if (nXToyTerm2 == null) {
                ToyLog.d("not exist in signUpTerm.  policyTermsId:" + nXToyTerm.termID);
            } else if (nXToyTerm2.isAgree == 1) {
                i++;
                ToyLog.d("agreeTermsId :" + nXToyTerm2.termID + " , matchCount:" + i);
            }
        }
        return i;
    }

    public void getEmailPolicy(final NPListener nPListener) {
        ToyLog.d("EmailPolicy V2(Get)");
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetEmailPolicyRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.18
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nPListener == null) {
                    ToyLog.d("EmailPolicy V2(Get) : listener is null");
                    return;
                }
                NXToyEmailPolicyResult nXToyEmailPolicyResult = (NXToyEmailPolicyResult) nXToyResult;
                nXToyEmailPolicyResult.requestTag = NXToyRequestTag.GetEmailPolicy.getValue();
                nXToyEmailPolicyResult.result.policy = new NXPPolicy();
                nXToyEmailPolicyResult.result.policy.setType(2);
                ToyLog.d("EmailPolicy V2(Get) : return result - " + nXToyResult.toString());
                nPListener.onResult(nXToyEmailPolicyResult);
            }
        });
    }

    public void getPhoneNumberPolicy(Activity activity, String str, NPListener nPListener) {
        createPhoneNumberPolicy().getPolicy(activity, str, nPListener);
    }

    public void getPolicyListV1(Context context, String str, final NPListener nPListener) {
        int i;
        int i2 = 0;
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
        if (mnc != null) {
            i2 = Integer.parseInt(mnc);
        }
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetPolicyRequest(i, i2, str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.1
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    public void getPolicyListV2(Context context, final NPListener nPListener) {
        int i;
        int i2 = 0;
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
        if (mnc != null) {
            i2 = Integer.parseInt(mnc);
        }
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetPolicyListRequest(i, i2), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    public int getPolicyStatus(List<NXToyTerm> list, @NonNull NXPPolicy nXPPolicy) {
        List<NXToyTerm> termsList = nXPPolicy.getTermsList();
        if (termsList.isEmpty()) {
            return 1;
        }
        HashMap<Integer, NXToyTerm> mapTermsListToTermsMap = NXPTermsManager.getInstance().termsListToTermsMap(list);
        if (!isAllIncludedTermsFromPolicy(mapTermsListToTermsMap, termsList)) {
            return 0;
        }
        Iterator<NXToyTerm> it = termsList.iterator();
        while (it.hasNext()) {
            if (mapTermsListToTermsMap.get(Integer.valueOf(it.next().termID)).isAgree != 1) {
                return 2;
            }
        }
        return 1;
    }

    public void getPushPolicy(Context context, final NPListener nPListener) {
        if (NXToySessionManager.getInstance().getSession().getType() == NXToyLoginType.LoginTypeNotLogined.getValue()) {
            NXToyGetPushPolicyResult nXToyGetPushPolicyResult = new NXToyGetPushPolicyResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), "not logged in user", "not logged in user");
            nXToyGetPushPolicyResult.requestTag = NXToyRequestTag.GetPushPolicy.getValue();
            if (nPListener != null) {
                nPListener.onResult(nXToyGetPushPolicyResult);
                return;
            }
            return;
        }
        if (NXPPush.getPushServiceVersion() >= 2) {
            NXPPush.getPolicy(context, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.25
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode != NXPPushErrorCode.NotRegisteredNpsn.getValue()) {
                        if (nPListener != null) {
                            nPListener.onResult(nXToyResult);
                            return;
                        }
                        return;
                    }
                    NXToyGetPushPolicyResult nXToyGetPushPolicyResult2 = (NXToyGetPushPolicyResult) nXToyResult;
                    nXToyGetPushPolicyResult2.errorCode = 0;
                    nXToyGetPushPolicyResult2.errorText = "";
                    nXToyGetPushPolicyResult2.errorDetail = "";
                    NXPPushPolicies nXPPushPolicies = new NXPPushPolicies();
                    nXPPushPolicies.setEnableAdPolicy(false);
                    nXPPushPolicies.setEnableNightPolicy(false);
                    nXToyGetPushPolicyResult2.result.policies = nXPPushPolicies;
                    if (nPListener != null) {
                        nPListener.onResult(nXToyGetPushPolicyResult2);
                    }
                }
            });
            return;
        }
        NXPPushErrorCode nXPPushErrorCode = NXPPushErrorCode.UnSupportedVersionError;
        NXToyGetPushPolicyResult nXToyGetPushPolicyResult2 = new NXToyGetPushPolicyResult(nXPPushErrorCode.getValue(), nXPPushErrorCode.getMessage(), nXPPushErrorCode.getMessage());
        nXToyGetPushPolicyResult2.requestTag = NXToyRequestTag.GetPushPolicy.getValue();
        if (nPListener != null) {
            nPListener.onResult(nXToyGetPushPolicyResult2);
        }
    }

    public void getSmsEnabled(final Activity activity, final NPListener nPListener) {
        final NXToyGetSmsEnabledResult nXToyGetSmsEnabledResult = new NXToyGetSmsEnabledResult(0, "", "");
        nXToyGetSmsEnabledResult.requestTag = NXToyRequestTag.GetSmsEnabled.getValue();
        nXToyGetSmsEnabledResult.result.isEnable = false;
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance();
        if (nXToyLocaleManager.getCountry() != NXLocale.COUNTRY.Korea) {
            if (nPListener != null) {
                nPListener.onResult(nXToyGetSmsEnabledResult);
            }
        } else {
            if (NXRuntimePermissionManager.getInstance().checkGrantedPermission(activity, NXRuntimePermissionManager.READ_PHONE_STATE)) {
                if (getPolicyVersion() == 2) {
                    getPhoneNumberPolicy(activity, NXTelephonyUtil.getPhoneNumber(activity), new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.16
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult) {
                            nXToyGetSmsEnabledResult.errorCode = nXToyResult.errorCode;
                            nXToyGetSmsEnabledResult.errorText = nXToyResult.errorText;
                            nXToyGetSmsEnabledResult.errorDetail = nXToyResult.errorDetail;
                            if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                nXToyGetSmsEnabledResult.result.isEnable = ((NXToyPhoneNumberPolicyResult) nXToyResult).result.isSubscription == 1;
                            }
                            if (nPListener != null) {
                                nPListener.onResult(nXToyGetSmsEnabledResult);
                            }
                        }
                    });
                    return;
                } else {
                    getPolicyListV1(activity, "terms", new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.17
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult) {
                            nXToyGetSmsEnabledResult.errorCode = nXToyResult.errorCode;
                            nXToyGetSmsEnabledResult.errorText = nXToyResult.errorText;
                            nXToyGetSmsEnabledResult.errorDetail = nXToyResult.errorDetail;
                            List<NXToyPolicy> list = ((NXToyPolicyResult) nXToyResult).result.policy;
                            boolean z = false;
                            if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode() && list != null) {
                                for (NXToyPolicy nXToyPolicy : list) {
                                    if (nXToyPolicy.code == 2505 && nXToyPolicy.isTermsAgree == 1) {
                                        z = true;
                                        break;
                                    }
                                }
                            }
                            if (!z) {
                                if (nPListener != null) {
                                    nPListener.onResult(nXToyGetSmsEnabledResult);
                                }
                            } else {
                                HashMap map = new HashMap();
                                map.put("phone", NXTelephonyUtil.getPhoneNumber(activity));
                                map.put("method", "get");
                                NXPPolicyManager.this.validatePolicy(NXPPolicyManager.CODE_POLICY_KOREA_COLLECTION_PHONE_NUMBER, map, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.17.1
                                    @Override // kr.co.nexon.toy.listener.NPListener
                                    public void onResult(NXToyResult nXToyResult2) {
                                        nXToyGetSmsEnabledResult.errorCode = nXToyResult2.errorCode;
                                        nXToyGetSmsEnabledResult.errorText = nXToyResult2.errorText;
                                        nXToyGetSmsEnabledResult.errorDetail = nXToyResult2.errorDetail;
                                        if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode() && ((NXToyValidatePolicyResult) nXToyResult2).result.code == 2501) {
                                            nXToyGetSmsEnabledResult.result.isEnable = true;
                                        }
                                        if (nPListener != null) {
                                            nPListener.onResult(nXToyGetSmsEnabledResult);
                                        }
                                    }
                                });
                            }
                        }
                    });
                    return;
                }
            }
            if (nPListener != null) {
                nXToyGetSmsEnabledResult.errorCode = NXToyErrorCode.PERMISSION_DENIED.getCode();
                nXToyGetSmsEnabledResult.errorText = nXToyLocaleManager.getString(R.string.npres_runtime_permission_denied_common_msg);
                nPListener.onResult(nXToyGetSmsEnabledResult);
            }
        }
    }

    public void putEmailPolicy(String str, final NPListener nPListener) {
        ToyLog.d("EmailPolicy V2(Put)");
        NXToyRequestPostman.getInstance().postRequest(new NXToyPutEmailPolicyRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.19
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nPListener == null) {
                    ToyLog.d("EmailPolicy V2(Put) : listener is null");
                    return;
                }
                NXToyEmailPolicyResult nXToyEmailPolicyResult = (NXToyEmailPolicyResult) nXToyResult;
                nXToyEmailPolicyResult.requestTag = NXToyRequestTag.PutEmailPolicy.getValue();
                nXToyEmailPolicyResult.result.policy = new NXPPolicy();
                nXToyEmailPolicyResult.result.policy.setType(2);
                ToyLog.d("EmailPolicy V2(Put) : return result - " + nXToyResult.toString());
                nPListener.onResult(nXToyEmailPolicyResult);
            }
        });
    }

    public void putFundsSettlementPolicy(Context context, String str, final NPListener nPListener) {
        createFundsSettlementPolicy().putPolicy(context, str, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.23
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    public void putPhoneNumberPolicy(String str, NPListener nPListener) {
        createPhoneNumberPolicy().putPolicy(str, nPListener);
    }

    public void setAllEnabledPolicy(final Activity activity, final List<NXToyTerm> list, final List<NXToyTerm> list2, final NPListener nPListener) {
        getPolicyListV2(activity, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.4
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("setAllEnabledPolicy - " + nXToyResult.toString());
                NXToyGetPolicyListResult nXToyGetPolicyListResult = (NXToyGetPolicyListResult) nXToyResult;
                if (nXToyGetPolicyListResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    if (nPListener != null) {
                        nPListener.onResult(nXToyResult);
                        return;
                    }
                    return;
                }
                NXPPolicy nXPPolicy = null;
                NXPPolicy nXPPolicy2 = null;
                NXPPolicy nXPPolicy3 = null;
                NXPPolicy nXPPolicy4 = null;
                for (NXPPolicy nXPPolicy5 : nXToyGetPolicyListResult.result.policyList) {
                    int type = nXPPolicy5.getType();
                    if (type == 1) {
                        nXPPolicy = nXPPolicy5;
                    } else if (type == 2) {
                        nXPPolicy2 = nXPPolicy5;
                    } else if (type == 4) {
                        nXPPolicy3 = nXPPolicy5;
                    } else if (type == 5) {
                        nXPPolicy4 = nXPPolicy5;
                    }
                }
                NXPPolicyManager.this.processSetupPolicyWithPushReceiveAgreement(activity, list, list2, nXPPolicy3, nXPPolicy4);
                if (nXPPolicy2 != null) {
                    NXPPolicyManager.this.processSetupPolicyWithEmailCollection(list, list2, nXPPolicy2);
                }
                if (nXPPolicy != null) {
                    NXPPolicyManager.this.processSetupPolicyWithPhoneNumberConllection(activity, list, list2, nXPPolicy, nPListener);
                } else if (nPListener != null) {
                    nPListener.onResult(new NXToyResult(0, "", ""));
                }
            }
        });
    }

    public void setDefaultPushPolicyIfNotRegistered(final Context context, final NPListener nPListener) {
        if (NXPPush.getPushServiceVersion() != 1) {
            NXPPush.getPolicy(context, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.24
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    ToyLog.d("getPushPolicyResult: " + nXToyResult);
                    if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                        if (nPListener != null) {
                            nPListener.onResult(new NXToyResult());
                        }
                    } else if (nXToyResult.errorCode != NXPPushErrorCode.NotRegisteredNpsn.getValue()) {
                        if (nPListener != null) {
                            nPListener.onResult(new NXToyResult());
                        }
                    } else {
                        NXPPushPolicies nXPPushPolicies = new NXPPushPolicies();
                        nXPPushPolicies.setEnableAdPolicy(true);
                        nXPPushPolicies.setEnableNightPolicy(true);
                        NXPPush.setPolicy(context, nXPPushPolicies, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.24.1
                            @Override // kr.co.nexon.toy.listener.NPListener
                            public void onResult(NXToyResult nXToyResult2) {
                                if (nPListener != null) {
                                    nPListener.onResult(new NXToyResult());
                                }
                            }
                        });
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult());
        }
    }

    public void setPersonalInformationConsingenmentState(final Activity activity, final int i, final NPListener nPListener) {
        HashMap map = new HashMap();
        map.put("phone", "");
        map.put("method", "put");
        map.put("isTermsAgree", Integer.valueOf(i));
        validatePolicy(CODE_POLICY_KOREA_COLLECTION_PHONE_NUMBER, map, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.11
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                String mnc = NXTelephonyUtil.getMnc(activity);
                String mcc = NXTelephonyUtil.getMcc(activity);
                if (NXStringUtil.isNull(mnc) && NXStringUtil.isNull(mcc)) {
                    if (nPListener != null) {
                        nPListener.onResult(nXToyResult);
                    }
                } else if (i == 1) {
                    NXPPolicyManager.this.processPhoneNumberCollection(activity, nPListener);
                } else if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    public void setPhoneNumberPolicy(final Activity activity, final NPListener nPListener) {
        String[] strArr = {NXRuntimePermissionManager.READ_PHONE_STATE};
        final NXRuntimePermissionManager nXRuntimePermissionManager = NXRuntimePermissionManager.getInstance();
        final NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance();
        nXRuntimePermissionManager.requestPermissionsWithoutExplanation(activity, strArr, 100001, new NXRuntimePermissionListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.15
            @Override // kr.co.nexon.mdev.android.permission.NXRuntimePermissionListener
            public void onResult(int i, String[] strArr2, int[] iArr) {
                if (iArr.length < 1 || iArr[0] == -1) {
                    nXRuntimePermissionManager.showPermissionAlert(activity, nXToyLocaleManager.getString(R.string.npres_runtime_permission_message_after), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.15.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NXToyResult nXToyResult = new NXToyResult();
                            nXToyResult.errorCode = NXToyErrorCode.PERMISSION_DENIED.getCode();
                            nXToyResult.errorText = nXToyLocaleManager.getString(R.string.npres_runtime_permission_denied_msg);
                            nXToyResult.errorDetail = nXToyLocaleManager.getString(R.string.npres_runtime_permission_denied_msg);
                            ToyLog.d(nXToyResult.toString());
                            if (nPListener != null) {
                                nPListener.onResult(nXToyResult);
                            }
                        }
                    });
                    return;
                }
                String phoneNumber = NXTelephonyUtil.getPhoneNumber(activity);
                NXToyPhoneNumberResult nXToyPhoneNumberResult = new NXToyPhoneNumberResult(0, GraphResponse.SUCCESS_KEY, "");
                nXToyPhoneNumberResult.result.phoneNumber = phoneNumber;
                if (NXStringUtil.isNull(phoneNumber)) {
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyPhoneNumberResult(NXToyErrorCode.FAILED_GET_PHONE_NUMBER.getCode(), "Not found phone number", ""));
                    }
                } else if (nPListener != null) {
                    nPListener.onResult(nXToyPhoneNumberResult);
                }
            }
        });
    }

    public void setPushPolicy(Context context, int i, NPListener nPListener) {
        if (NXPPush.getPushServiceVersion() == 1) {
            boolean z = i == 1;
            NXPPushPolicies nXPPushPolicies = new NXPPushPolicies();
            nXPPushPolicies.setEnableAdPolicy(z);
            NXPPush.setPolicy(context, nXPPushPolicies, nPListener);
            return;
        }
        int value = i == 1 ? NXToyRequestTag.OnPush.getValue() : NXToyRequestTag.OffPush.getValue();
        NXPPushErrorCode nXPPushErrorCode = NXPPushErrorCode.UnSupportedVersionError;
        NXToyResult nXToyResult = new NXToyResult(nXPPushErrorCode.getValue(), nXPPushErrorCode.getMessage(), nXPPushErrorCode.getMessage(), value);
        if (nPListener != null) {
            nPListener.onResult(nXToyResult);
        }
    }

    public void setPushPolicy(Context context, NXPPushPolicies nXPPushPolicies, NPListener nPListener) {
        if (NXPPush.getPushServiceVersion() < 2) {
            NXPPushErrorCode nXPPushErrorCode = NXPPushErrorCode.UnSupportedVersionError;
            NXToyResult nXToyResult = new NXToyResult(nXPPushErrorCode.getValue(), nXPPushErrorCode.getMessage(), nXPPushErrorCode.getMessage(), NXToyRequestTag.SetPushPolicy.getValue());
            if (nPListener != null) {
                nPListener.onResult(nXToyResult);
                return;
            }
            return;
        }
        if (nXPPushPolicies == null) {
            NXPPushErrorCode nXPPushErrorCode2 = NXPPushErrorCode.ParameterInvalid;
            NXToyResult nXToyResult2 = new NXToyResult(nXPPushErrorCode2.getValue(), nXPPushErrorCode2.getMessage(), nXPPushErrorCode2.getMessage(), NXToyRequestTag.SetPushPolicy.getValue());
            if (nPListener != null) {
                nPListener.onResult(nXToyResult2);
            }
        }
        NXPPush.setPolicy(context, nXPPushPolicies, nPListener);
    }

    public void showFundsSettlement(final Activity activity, final String str, final String str2, final String str3, final NPListener nPListener) {
        ToyLog.d("showFundsSettlement  itemName:" + str + "  itemPrice:" + str2 + " productId:" + str3);
        final NPListener nPListener2 = new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.21
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    if (NXPPolicyManager.this.getPolicyVersion() == 2) {
                        nXToyResult.requestTag = NXToyRequestTag.GetPolicyList.getValue();
                    }
                    nPListener.onResult(NXPPolicyManager.this.makeFundsSettlementPolicyResult(nXToyResult, str, str2, str3));
                }
            }
        };
        createFundsSettlementPolicy().getPolicy(activity, new NPListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.22
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXToyFundsSettlementPolicyResult nXToyFundsSettlementPolicyResultMakeFundsSettlementPolicyResult = NXPPolicyManager.this.makeFundsSettlementPolicyResult(nXToyResult, str, str2, str3);
                if (nXToyFundsSettlementPolicyResultMakeFundsSettlementPolicyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("Method Name : showFundsSettlement : error result : " + nXToyFundsSettlementPolicyResultMakeFundsSettlementPolicyResult.toString());
                    if (nPListener != null) {
                        nPListener.onResult(nXToyFundsSettlementPolicyResultMakeFundsSettlementPolicyResult);
                        return;
                    }
                    return;
                }
                if (nXToyFundsSettlementPolicyResultMakeFundsSettlementPolicyResult.result.policy == null) {
                    if (nPListener != null) {
                        nPListener.onResult(nXToyFundsSettlementPolicyResultMakeFundsSettlementPolicyResult);
                        return;
                    }
                    return;
                }
                NXPPolicy nXPPolicy = nXToyFundsSettlementPolicyResultMakeFundsSettlementPolicyResult.result.policy;
                if (nXPPolicy.getStatus() == 2) {
                    NPFundsSettlementDialog nPFundsSettlementDialogNewInsatnce = NPFundsSettlementDialog.newInsatnce(activity, str, str2);
                    nPFundsSettlementDialogNewInsatnce.setResultListener(nPListener2);
                    nPFundsSettlementDialogNewInsatnce.setPolicyTerms(nXPPolicy.getTermsList());
                    nPFundsSettlementDialogNewInsatnce.showDialog(activity, NPFundsSettlementDialog.TAG);
                    return;
                }
                if (nXPPolicy.getStatus() != 1) {
                    if (nPListener != null) {
                        nPListener.onResult(nXToyFundsSettlementPolicyResultMakeFundsSettlementPolicyResult);
                    }
                } else {
                    NPFundsSettlementItemConfirmDialog nPFundsSettlementItemConfirmDialogNewInstance = NPFundsSettlementItemConfirmDialog.newInstance(activity, str, str2, false);
                    nPFundsSettlementItemConfirmDialogNewInstance.setResultListener(new NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.22.1
                        @Override // kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener
                        public void onCancel() {
                            ToyLog.d("Back button visibility is gone");
                            ToyLog.d("On click back button - NPFundsSettlementItemConfirmDialog");
                        }

                        @Override // kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener
                        public void onClose(NXToyResult nXToyResult2) {
                            if (nPListener2 != null) {
                                nPListener2.onResult(nXToyResult2);
                            }
                        }

                        @Override // kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener
                        public void onConfirm(NXToyResult nXToyResult2) {
                            if (nPListener2 != null) {
                                nPListener2.onResult(nXToyResult2);
                            }
                        }
                    });
                    nPFundsSettlementItemConfirmDialogNewInstance.setPolicyTerms(nXPPolicy.getTermsList());
                    nPFundsSettlementItemConfirmDialogNewInstance.showDialog(activity, NPFundsSettlementItemConfirmDialog.TAG);
                }
            }
        });
    }

    public void showPermissionConfirmDialog(@NonNull final Activity activity, final String str, final String str2, final String str3, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.12
            @Override // java.lang.Runnable
            public void run() {
                View viewInflate = activity.getLayoutInflater().inflate(R.layout.nxp_permission_confirm_alert_view, (ViewGroup) null);
                Button button = (Button) viewInflate.findViewById(R.id.permissionConfirmButton);
                TextView textView = (TextView) viewInflate.findViewById(R.id.permissionConfirmMessageTop);
                TextView textView2 = (TextView) viewInflate.findViewById(R.id.permissionConfirmMessage);
                TextView textView3 = (TextView) viewInflate.findViewById(R.id.permissionConfirmMessageBottom);
                textView.setText(str);
                textView2.setText(str2);
                textView2.setTypeface(textView2.getTypeface(), 1);
                textView3.setText(str3);
                NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(activity);
                if (nXToyLocaleManager != null) {
                    button.setText(nXToyLocaleManager.getString(R.string.confirm));
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setView(viewInflate).create();
                final AlertDialog alertDialogShow = builder.show();
                alertDialogShow.setCanceledOnTouchOutside(false);
                button.setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.12.1
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        if (nPListener != null) {
                            nPListener.onResult(new NXToyResult(0, "", ""));
                        }
                        alertDialogShow.dismiss();
                    }
                });
                alertDialogShow.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.12.2
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        if (nPListener != null) {
                            nPListener.onResult(new NXToyResult(0, "", ""));
                        }
                    }
                });
            }
        });
    }

    public void showPhonePermissionConfirmationDialog(@NonNull Activity activity, NPListener nPListener) {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(activity);
        showPermissionConfirmDialog(activity, nXToyLocaleManager.getString(R.string.npres_phone_number_collection_policy_msg_top), nXToyLocaleManager.getString(R.string.npres_phone_number_collection_policy_msg), nXToyLocaleManager.getString(R.string.npres_phone_number_collection_policy_msg_bottom), nPListener);
    }

    public void showPolicySettings(Activity activity, NPListener nPListener) {
        if (NXPPush.getPushServiceVersion() >= 2) {
            NXPPush.showPushSettingDialog(activity, null, nPListener);
            return;
        }
        NXPPushErrorCode nXPPushErrorCode = NXPPushErrorCode.UnSupportedVersionError;
        NXToyResult nXToyResult = new NXToyResult(nXPPushErrorCode.getValue(), nXPPushErrorCode.getMessage(), nXPPushErrorCode.getMessage(), NXToyRequestTag.ShowPushMenu.getValue());
        if (nPListener != null) {
            nPListener.onResult(nXToyResult);
        }
    }

    public void showPushNSmsSetting(Activity activity, String str, String str2, String str3, NPListener nPListener) {
        if (NXPPush.getPushServiceVersion() == 1) {
            NXPPush.showPushSettingDialog(activity, new NXPPushMenuOption(str, str2, str3), nPListener);
            return;
        }
        NXPPushErrorCode nXPPushErrorCode = NXPPushErrorCode.UnSupportedVersionError;
        NXToyResult nXToyResult = new NXToyResult(nXPPushErrorCode.getValue(), nXPPushErrorCode.getMessage(), nXPPushErrorCode.getMessage(), NXToyRequestTag.OffPush.getValue());
        if (nPListener != null) {
            nPListener.onResult(nXToyResult);
        }
    }

    public void showPushNSmsSetting(Activity activity, NPListener nPListener) {
        if (NXPPush.getPushServiceVersion() == 1) {
            NXPPush.showPushSettingDialog(activity, null, nPListener);
            return;
        }
        NXPPushErrorCode nXPPushErrorCode = NXPPushErrorCode.UnSupportedVersionError;
        NXToyResult nXToyResult = new NXToyResult(nXPPushErrorCode.getValue(), nXPPushErrorCode.getMessage(), nXPPushErrorCode.getMessage(), NXToyRequestTag.ShowPushNSMSSetting.getValue());
        if (nPListener != null) {
            nPListener.onResult(nXToyResult);
        }
    }

    public void validatePolicy(int i, Map<String, Object> map, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyValidatePolicyRequest(i, map), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.policy.NXPPolicyManager.3
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }
}
