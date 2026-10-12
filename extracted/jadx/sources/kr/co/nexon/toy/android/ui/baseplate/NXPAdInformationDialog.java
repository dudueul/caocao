package kr.co.nexon.toy.android.ui.baseplate;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.core.util.NXDateUtil;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;
import kr.co.nexon.mdev.android.util.NXTelephonyUtil;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyEmailPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyGetPolicyListResult;
import kr.co.nexon.npaccount.auth.result.NXToyGetPushPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberResult;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.push.NXPPushMenuOption;
import kr.co.nexon.npaccount.push.NXPPushPolicies;
import kr.co.nexon.npaccount.push.NXPPushPolicy;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.view.adapter.NXPAdInformationListAdapter;
import kr.co.nexon.toy.android.ui.baseplate.view.adapter.model.NXPAdInformationItem;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.common.view.NXPSimpleListView;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAdInformationDialog extends NPDialogBase implements NXPAdInformationListAdapter.ItemClickListener {
    public static final String KEY_OPTIONS = "nxp_push_option";
    public static final String TAG = "NXPAdInformationDialog";
    private List<NXPAdInformationItem> adInformationItems;
    private NXPAdInformationListAdapter adInformationListAdapter;
    private NXPPolicy adPushPolicy;
    private String adPushTitleString;
    private String emailCollectionTitleString;
    private NXPPolicy emailPolicy;
    private NXToyLocaleManager localeManager;
    private NXPPolicy nightPushPolicy;
    private String nightPushTitleString;
    private String phoneNumberCollectionTitleString;
    private NXPPolicy phoneNumberPolicy;
    private NXPPolicyManager policyManager;
    private NXPPushPolicies pushPolicies;
    private NPListener resultListener;
    private NXPTermsManager termsManager;
    private String title;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkPolicyStatus(int i) {
        return i == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayedAdInformationPolicies() {
        Activity activity = getActivity();
        if (activity == null) {
            ToyLog.d("In displayedAdInformationPolicies, activity is null");
        } else {
            showProgressDialog();
            this.policyManager.getPolicyListV2(activity, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.3
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    NXPAdInformationDialog.this.dismissProgressDialog();
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        NXPAdInformationDialog.this.showAlertDialog(nXToyResult.errorText, new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.3.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                NXPAdInformationDialog.this.onBackPressed();
                            }
                        }, null);
                        return;
                    }
                    List<NXPPolicy> list = ((NXToyGetPolicyListResult) nXToyResult).result.policyList;
                    if (list == null || list.isEmpty()) {
                        ToyLog.d("policyList is empty.  policyList:" + list);
                        NXPAdInformationDialog.this.adInformationItems.add(new NXPAdInformationItem(1, NXPAdInformationDialog.this.adPushTitleString, NXPAdInformationDialog.this.checkPolicyStatus(NXPAdInformationDialog.this.pushPolicies.getAdPolicy().isEnabled() ? 1 : 2)));
                        NXPAdInformationDialog.this.adInformationListAdapter.notifyDataSetChanged();
                        return;
                    }
                    for (NXPPolicy nXPPolicy : list) {
                        int type = nXPPolicy.getType();
                        int status = nXPPolicy.getStatus();
                        if (type == 1) {
                            NXPAdInformationDialog.this.phoneNumberPolicy = nXPPolicy;
                            NXPAdInformationDialog.this.adInformationItems.add(new NXPAdInformationItem(3, NXPAdInformationDialog.this.phoneNumberCollectionTitleString, NXPAdInformationDialog.this.checkPolicyStatus(status)));
                        } else if (type == 2) {
                            NXPAdInformationDialog.this.emailPolicy = nXPPolicy;
                            NXPAdInformationDialog.this.adInformationItems.add(new NXPAdInformationItem(4, NXPAdInformationDialog.this.emailCollectionTitleString, NXPAdInformationDialog.this.checkPolicyStatus(status)));
                        } else if (type == 4) {
                            NXPAdInformationDialog.this.adPushPolicy = nXPPolicy;
                            NXPAdInformationDialog.this.adPushPolicy.setStatus(NXPAdInformationDialog.this.pushPolicies.getAdPolicy().isEnabled() ? 1 : 2);
                            NXPAdInformationDialog.this.adInformationItems.add(new NXPAdInformationItem(1, NXPAdInformationDialog.this.adPushTitleString, NXPAdInformationDialog.this.checkPolicyStatus(NXPAdInformationDialog.this.adPushPolicy.getStatus())));
                        } else if (type == 5) {
                            NXPAdInformationDialog.this.nightPushPolicy = nXPPolicy;
                            NXPAdInformationDialog.this.nightPushPolicy.setStatus(NXPAdInformationDialog.this.pushPolicies.getNightPolicy().isEnabled() ? 1 : 2);
                            NXPAdInformationDialog.this.adInformationItems.add(new NXPAdInformationItem(2, NXPAdInformationDialog.this.nightPushTitleString, NXPAdInformationDialog.this.checkPolicyStatus(NXPAdInformationDialog.this.nightPushPolicy.getStatus())));
                        }
                    }
                    if (NXPAdInformationDialog.this.adPushPolicy == null) {
                        NXPAdInformationDialog.this.adInformationItems.add(new NXPAdInformationItem(1, NXPAdInformationDialog.this.adPushTitleString, NXPAdInformationDialog.this.checkPolicyStatus(NXPAdInformationDialog.this.pushPolicies.getAdPolicy().isEnabled() ? 1 : 2)));
                    }
                    if (!NXPAdInformationDialog.this.pushPolicies.getAdPolicy().isEnabled()) {
                        NXPAdInformationDialog.this.setEnabledNightPushPolicy(false);
                    }
                    NXPAdInformationDialog.this.sortList();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String makeFormattedMessage(int i) {
        return this.localeManager.getStringEx(i, "").replace(NXDateUtil.DATE_RESOURCES_FORMAT, NXDateUtil.getCurrentTimeFormat("yyyy-MM-dd"));
    }

    public static NXPAdInformationDialog newInstance(Activity activity, NXPPushMenuOption nXPPushMenuOption) {
        NXPAdInformationDialog nXPAdInformationDialog = new NXPAdInformationDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putParcelable("nxp_push_option", nXPPushMenuOption);
        nXPAdInformationDialog.setArguments(bundle);
        return nXPAdInformationDialog;
    }

    private void onClickAdPushButton(final NXPAdInformationItem nXPAdInformationItem) {
        if (this.adPushPolicy != null) {
            List<NXToyTerm> loginTermsList = this.termsManager.getLoginTermsList();
            if (this.policyManager.getPolicyStatus(loginTermsList, this.adPushPolicy) != 1) {
                this.termsManager.agreeTermsWithoutUpdateToyToken(this.termsManager.changeTermsAgreementStatus(loginTermsList, this.adPushPolicy.getTermsList()), null);
            }
        }
        final String string = this.pushPolicies.toString();
        NXPPushPolicy adPolicy = this.pushPolicies.getAdPolicy();
        if (adPolicy == null) {
            ToyLog.d("ad push Policy is null!!");
            this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
        } else {
            showProgressDialog();
            this.pushPolicies.setEnableAdPolicy(adPolicy.isEnabled() ? false : true);
            this.policyManager.setPushPolicy(getActivity(), this.pushPolicies, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.5
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    int i;
                    NXPAdInformationDialog.this.dismissProgressDialog();
                    ToyLog.d("agree push result : " + nXToyResult.toString());
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        NXPAdInformationDialog.this.pushPolicies = (NXPPushPolicies) NXJsonUtil.fromObject(string, NXPPushPolicies.class);
                        NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                        NXPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                        return;
                    }
                    NXPPushPolicy adPolicy2 = NXPAdInformationDialog.this.pushPolicies.getAdPolicy();
                    NXPAdInformationDialog.this.setEnabledNightPushPolicy(adPolicy2.isEnabled());
                    if (NXPAdInformationDialog.this.adPushPolicy != null) {
                        NXPAdInformationDialog.this.adPushPolicy.setStatus(adPolicy2.isEnabled() ? 1 : 2);
                        boolean zIsEnabled = adPolicy2.isEnabled();
                        if (zIsEnabled && NXPAdInformationDialog.this.adPushPolicy.getIsAgreeToast()) {
                            i = R.string.npres_push_receive_on;
                        } else if (zIsEnabled || !NXPAdInformationDialog.this.adPushPolicy.getIsDisagreeToast()) {
                            return;
                        } else {
                            i = R.string.npres_push_receive_off;
                        }
                        NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.makeFormattedMessage(i), null, null);
                    }
                }
            });
        }
    }

    private void onClickEmailButton(final NXPAdInformationItem nXPAdInformationItem) {
        final NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.11
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                int i;
                NXPAdInformationDialog.this.dismissProgressDialog();
                ToyLog.d("validate policy result : " + nXToyResult.toString());
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                    NXPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    return;
                }
                if (NXPAdInformationDialog.this.emailPolicy != null) {
                    NXToyEmailPolicyResult nXToyEmailPolicyResult = (NXToyEmailPolicyResult) nXToyResult;
                    NXPAdInformationDialog.this.emailPolicy.setStatus(nXToyEmailPolicyResult.result.isSubscription);
                    boolean z = nXToyEmailPolicyResult.result.isSubscription == 1;
                    if (z && NXPAdInformationDialog.this.emailPolicy.getIsAgreeToast()) {
                        i = R.string.npres_email_receive_on;
                    } else if (z || !NXPAdInformationDialog.this.emailPolicy.getIsDisagreeToast()) {
                        return;
                    } else {
                        i = R.string.npres_email_receive_off;
                    }
                    NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.makeFormattedMessage(i), null, null);
                }
            }
        };
        NXToySession session = NXToySessionManager.getInstance().getSession();
        final String emailId = session != null ? session.getEmailId() : "";
        if (this.emailPolicy.getStatus() == 1) {
            showProgressDialog();
            this.policyManager.deleteEmailPolicy(emailId, nPListener);
            return;
        }
        List<NXToyTerm> loginTermsList = this.termsManager.getLoginTermsList();
        int policyStatus = this.policyManager.getPolicyStatus(loginTermsList, this.emailPolicy);
        if (policyStatus == 0) {
            showToastWithPolicyEnabledError(this.localeManager.getString(R.string.npres_policy_terms_not_using_msg));
            this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
            return;
        }
        if (NXStringUtil.isNull(emailId)) {
            ToyLog.d("No email to collect.");
            showToastWithPolicyEnabledError(this.localeManager.getString(R.string.npres_email_can_not_activated_reception));
            this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
        } else if (policyStatus != 2) {
            showProgressDialog();
            this.policyManager.putEmailPolicy(emailId, nPListener);
        } else if (isShowingProgressDialog()) {
            this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
        } else {
            showProgressDialog();
            this.termsManager.showTermsAgreementFromPolicyDialog(getActivity(), this.emailPolicy.getTermsList(), loginTermsList, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.12
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    NXPAdInformationDialog.this.dismissProgressDialog();
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                        NXPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    } else {
                        NXPAdInformationDialog.this.showProgressDialog();
                        NXPAdInformationDialog.this.policyManager.putEmailPolicy(emailId, nPListener);
                    }
                }
            });
        }
    }

    private void onClickNightPushButton(final NXPAdInformationItem nXPAdInformationItem) {
        if (this.nightPushPolicy != null) {
            List<NXToyTerm> loginTermsList = this.termsManager.getLoginTermsList();
            if (this.policyManager.getPolicyStatus(loginTermsList, this.nightPushPolicy) != 1) {
                this.termsManager.agreeTermsWithoutUpdateToyToken(this.termsManager.changeTermsAgreementStatus(loginTermsList, this.nightPushPolicy.getTermsList()), null);
            }
        }
        final NXPPushPolicy nightPolicy = this.pushPolicies.getNightPolicy();
        if (nightPolicy == null) {
            ToyLog.d("night push Policy is null!!");
            this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
        } else {
            final boolean zIsEnabled = nightPolicy.isEnabled();
            nightPolicy.setEnabled(zIsEnabled ? false : true);
            showProgressDialog();
            this.policyManager.setPushPolicy(getActivity(), this.pushPolicies, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.6
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    int i;
                    NXPAdInformationDialog.this.dismissProgressDialog();
                    ToyLog.d("agree night push result : " + nXToyResult.toString());
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        NXPAdInformationDialog.this.pushPolicies.getNightPolicy().setEnabled(zIsEnabled);
                        NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                        NXPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    } else if (NXPAdInformationDialog.this.nightPushPolicy != null) {
                        NXPAdInformationDialog.this.nightPushPolicy.setStatus(nightPolicy.isEnabled() ? 1 : 2);
                        boolean zIsEnabled2 = nightPolicy.isEnabled();
                        if (zIsEnabled2 && NXPAdInformationDialog.this.nightPushPolicy.getIsAgreeToast()) {
                            i = R.string.npres_night_push_receive_on;
                        } else if (zIsEnabled2 || !NXPAdInformationDialog.this.nightPushPolicy.getIsDisagreeToast()) {
                            return;
                        } else {
                            i = R.string.npres_night_push_receive_off;
                        }
                        NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.makeFormattedMessage(i), null, null);
                    }
                }
            });
        }
    }

    private void onClickSmsButton(final NXPAdInformationItem nXPAdInformationItem) {
        final NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.7
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                int i;
                NXPAdInformationDialog.this.dismissProgressDialog();
                ToyLog.d("validate policy result : " + nXToyResult.toString());
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                    NXPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    return;
                }
                if (NXPAdInformationDialog.this.phoneNumberPolicy != null) {
                    NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = (NXToyPhoneNumberPolicyResult) nXToyResult;
                    NXPAdInformationDialog.this.phoneNumberPolicy.setStatus(nXToyPhoneNumberPolicyResult.result.isSubscription);
                    boolean z = nXToyPhoneNumberPolicyResult.result.isSubscription == 1;
                    if (z && NXPAdInformationDialog.this.phoneNumberPolicy.getIsAgreeToast()) {
                        i = R.string.npres_phone_collect_on;
                    } else if (z || !NXPAdInformationDialog.this.phoneNumberPolicy.getIsDisagreeToast()) {
                        return;
                    } else {
                        i = R.string.npres_phone_collect_off;
                    }
                    NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.makeFormattedMessage(i), null, null);
                }
            }
        };
        List<NXToyTerm> loginTermsList = this.termsManager.getLoginTermsList();
        int policyStatus = this.policyManager.getPolicyStatus(loginTermsList, this.phoneNumberPolicy);
        boolean zCheckGrantedPermission = NXRuntimePermissionManager.getInstance().checkGrantedPermission(getActivity(), NXRuntimePermissionManager.READ_PHONE_STATE);
        final NPListener nPListener2 = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.8
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXPAdInformationDialog.this.dismissProgressDialog();
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPAdInformationDialog.this.showToastWithPolicyEnabledError(NXPAdInformationDialog.this.localeManager.getString(R.string.npres_phone_number_not_using_msg));
                    NXPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    NXToyCommonPreferenceController.getInstance().setHasPhoneNumberFlag(NXToySessionManager.getInstance().getSession().getNpsn(), false);
                } else {
                    NXToyPhoneNumberResult nXToyPhoneNumberResult = (NXToyPhoneNumberResult) nXToyResult;
                    NXPAdInformationDialog.this.showProgressDialog();
                    if (NXPAdInformationDialog.this.phoneNumberPolicy.getStatus() == 1) {
                        NXPAdInformationDialog.this.policyManager.deletePhoneNumberPolicy(nXToyPhoneNumberResult.result.phoneNumber, nPListener);
                    } else {
                        NXPAdInformationDialog.this.policyManager.putPhoneNumberPolicy(nXToyPhoneNumberResult.result.phoneNumber, nPListener);
                    }
                }
            }
        };
        final NPListener nPListener3 = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.9
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXPAdInformationDialog.this.showProgressDialog();
                NXPAdInformationDialog.this.policyManager.setPhoneNumberPolicy(NXPAdInformationDialog.this.getActivity(), nPListener2);
            }
        };
        if (policyStatus == 0) {
            showToastWithPolicyEnabledError(this.localeManager.getString(R.string.npres_policy_terms_not_using_msg));
            this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
            return;
        }
        if (policyStatus == 2) {
            if (isShowingProgressDialog()) {
                this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                return;
            } else {
                showProgressDialog();
                this.termsManager.showTermsAgreementFromPolicyDialog(getActivity(), this.phoneNumberPolicy.getTermsList(), loginTermsList, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.10
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult) {
                        NXPAdInformationDialog.this.dismissProgressDialog();
                        if (nXToyResult.errorCode == NXToyErrorCode.FAILED_GET_PHONE_NUMBER.getCode()) {
                            NXPAdInformationDialog.this.showToastWithPolicyEnabledError(NXPAdInformationDialog.this.localeManager.getString(R.string.npres_phone_number_not_using_msg));
                            NXPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                        } else if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                            NXPAdInformationDialog.this.policyManager.showPhonePermissionConfirmationDialog(NXPAdInformationDialog.this.getActivity(), nPListener3);
                        } else {
                            NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                            NXPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                        }
                    }
                });
                return;
            }
        }
        if (!zCheckGrantedPermission) {
            this.policyManager.showPhonePermissionConfirmationDialog(getActivity(), nPListener3);
            return;
        }
        if (NXStringUtil.isNull(NXTelephonyUtil.getPhoneNumber(getActivity()))) {
            showToastWithPolicyEnabledError(this.localeManager.getString(R.string.npres_phone_number_not_using_msg));
            this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
            return;
        }
        showProgressDialog();
        if (this.phoneNumberPolicy.getStatus() == 1) {
            this.policyManager.deletePhoneNumberPolicy(NXTelephonyUtil.getPhoneNumber(getActivity()), nPListener);
        } else {
            this.policyManager.putPhoneNumberPolicy(NXTelephonyUtil.getPhoneNumber(getActivity()), nPListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEnabledNightPushPolicy(boolean z) {
        for (NXPAdInformationItem nXPAdInformationItem : this.adInformationItems) {
            if (nXPAdInformationItem.getAdInformationViewOrder() == 2) {
                nXPAdInformationItem.setEnabled(z);
                if (!z) {
                    nXPAdInformationItem.setChecked(false);
                }
                this.adInformationListAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlertDialog(final String str, final View.OnClickListener onClickListener, final DialogInterface.OnCancelListener onCancelListener) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.14
            @Override // java.lang.Runnable
            public void run() {
                new NXPAlertDialog.Builder(activity).setMessage(str).setPositiveButton(NXPAdInformationDialog.this.localeManager.getString(R.string.confirm), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.14.2
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(view);
                        }
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.14.1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        if (onCancelListener != null) {
                            onCancelListener.onCancel(dialogInterface);
                        } else if (onClickListener != null) {
                            onClickListener.onClick(null);
                        }
                    }
                }).create().show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastWithPolicyEnabledError(final String str) {
        if (NXStringUtil.isNotNull(str)) {
            runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.13
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(NXPAdInformationDialog.this.getActivity(), str, 0).show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sortList() {
        Collections.sort(this.adInformationItems, new Comparator<NXPAdInformationItem>() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.4
            @Override // java.util.Comparator
            public int compare(NXPAdInformationItem nXPAdInformationItem, NXPAdInformationItem nXPAdInformationItem2) {
                return nXPAdInformationItem.getAdInformationViewOrder() - nXPAdInformationItem2.getAdInformationViewOrder();
            }
        });
        this.adInformationListAdapter.notifyDataSetChanged();
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    protected void activityCreated() {
        this.termsManager = NXPTermsManager.getInstance();
        this.policyManager = NXPPolicyManager.getInstance();
        showProgressDialog();
        this.policyManager.getPushPolicy(getActivity(), new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXPAdInformationDialog.this.dismissProgressDialog();
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPAdInformationDialog.this.showAlertDialog(NXPAdInformationDialog.this.localeManager.getString(R.string.npres_request_failed), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.2.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NXPAdInformationDialog.this.onBackPressed();
                        }
                    }, null);
                } else {
                    NXPAdInformationDialog.this.pushPolicies = ((NXToyGetPushPolicyResult) nXToyResult).result.policies;
                    NXPAdInformationDialog.this.displayedAdInformationPolicies();
                }
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPSimpleListView nXPSimpleListView = (NXPSimpleListView) View.inflate(getActivity(), R.layout.nxp_simple_list_view, null);
        NXPPushMenuOption nXPPushMenuOption = (NXPPushMenuOption) getArguments().getParcelable("nxp_push_option");
        this.title = nXPPushMenuOption.getTitle();
        this.adPushTitleString = nXPPushMenuOption.getPushLabelName();
        this.nightPushTitleString = nXPPushMenuOption.getNightPushLabelName();
        this.phoneNumberCollectionTitleString = nXPPushMenuOption.getSmsLabelName();
        this.emailCollectionTitleString = nXPPushMenuOption.getEmailLabelName();
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        if (NXStringUtil.isNull(this.adPushTitleString)) {
            this.adPushTitleString = this.localeManager.getString(R.string.npres_push_receive_on_off_setting_title);
        }
        if (NXStringUtil.isNull(this.nightPushTitleString)) {
            this.nightPushTitleString = this.localeManager.getString(R.string.npres_night_push_receive_on_off_setting_title);
        }
        if (NXStringUtil.isNull(this.phoneNumberCollectionTitleString)) {
            this.phoneNumberCollectionTitleString = this.localeManager.getString(R.string.npres_phone_collect_on_off_setting_title);
        }
        if (NXStringUtil.isNull(this.emailCollectionTitleString)) {
            this.emailCollectionTitleString = this.localeManager.getString(R.string.npres_email_receive_on_off_setting_title);
        }
        nXPSimpleListView.setTitle(this.title);
        nXPSimpleListView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXPAdInformationDialog.this.onBackPressed();
            }
        });
        this.adInformationItems = new ArrayList();
        this.adInformationListAdapter = new NXPAdInformationListAdapter(getActivity(), this.adInformationItems);
        this.adInformationListAdapter.setOnItemClickListener(this);
        nXPSimpleListView.setListAdapter(this.adInformationListAdapter);
        return nXPSimpleListView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (isShowingProgressDialog()) {
            return;
        }
        if (this.resultListener != null) {
            this.resultListener.onResult(new NXToyResult(0, "", "", NXToyRequestTag.ShowPushMenu.getValue()));
        }
        super.onBackPressed();
    }

    @Override // kr.co.nexon.toy.android.ui.baseplate.view.adapter.NXPAdInformationListAdapter.ItemClickListener
    public void onClickItem(final CompoundButton compoundButton, NXPAdInformationItem nXPAdInformationItem) {
        if (compoundButton.isChecked() == nXPAdInformationItem.isChecked()) {
            return;
        }
        compoundButton.setEnabled(false);
        nXPAdInformationItem.setChecked(nXPAdInformationItem.isChecked() ? false : true);
        switch (nXPAdInformationItem.getAdInformationViewOrder()) {
            case 1:
                onClickAdPushButton(nXPAdInformationItem);
                break;
            case 2:
                onClickNightPushButton(nXPAdInformationItem);
                break;
            case 3:
                onClickSmsButton(nXPAdInformationItem);
                break;
            case 4:
                onClickEmailButton(nXPAdInformationItem);
                break;
        }
        getMainLooperHandler().postDelayed(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog.15
            @Override // java.lang.Runnable
            public void run() {
                compoundButton.setEnabled(true);
            }
        }, 600L);
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }
}
