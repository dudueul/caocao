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
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberResult;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.push.NXPPushMenuOption;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.view.adapter.NXPAdInformationListAdapter;
import kr.co.nexon.toy.android.ui.baseplate.view.adapter.model.NXPAdInformationItem;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.common.view.NXPSimpleListView;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPAdInformationDialog extends NPDialogBase implements NXPAdInformationListAdapter.ItemClickListener {
    public static final String KEY_OPTIONS = "nxp_push_option";
    public static final String TAG = "NPAdInformationDialog";
    private List<NXPAdInformationItem> adInformationItems;
    private NXPAdInformationListAdapter adInformationListAdapter;
    private String adPushTitleString;
    private NXToyCommonPreferenceController commonPreferenceController;
    private String emailCollectionTitleString;
    private NXPPolicy emailPolicy;
    private NXToyLocaleManager localeManager;
    private String phoneNumberCollectionTitleString;
    private NXPPolicy phoneNumberPolicy;
    private NXPPolicyManager policyManager;
    private NPListener resultListener;
    private NXPTermsManager termsManager;
    private String title;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkPolicyStatus(int i) {
        return i == 1;
    }

    private void displayedAdInformationPolicies() {
        this.policyManager = NXPPolicyManager.getInstance();
        this.termsManager = NXPTermsManager.getInstance();
        showProgressDialog();
        this.policyManager.getPolicyListV2(getActivity(), new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NPAdInformationDialog.this.dismissProgressDialog();
                if (nXToyResult.errorCode != 0) {
                    NPAdInformationDialog.this.showAlertDialog(nXToyResult.errorText, new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.2.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NPAdInformationDialog.this.onBackPressed();
                        }
                    }, null);
                    return;
                }
                List<NXPPolicy> list = ((NXToyGetPolicyListResult) nXToyResult).result.policyList;
                if (list == null || list.isEmpty()) {
                    ToyLog.d("policyList is empty.  policyList:" + list);
                    NPAdInformationDialog.this.adInformationListAdapter.notifyDataSetChanged();
                    return;
                }
                for (NXPPolicy nXPPolicy : list) {
                    int type = nXPPolicy.getType();
                    int status = nXPPolicy.getStatus();
                    if (type == 1) {
                        NPAdInformationDialog.this.phoneNumberPolicy = nXPPolicy;
                        NPAdInformationDialog.this.adInformationItems.add(new NXPAdInformationItem(3, NPAdInformationDialog.this.phoneNumberCollectionTitleString, NPAdInformationDialog.this.checkPolicyStatus(status)));
                    } else if (type == 2) {
                        NPAdInformationDialog.this.emailPolicy = nXPPolicy;
                        NPAdInformationDialog.this.adInformationItems.add(new NXPAdInformationItem(4, NPAdInformationDialog.this.emailCollectionTitleString, NPAdInformationDialog.this.checkPolicyStatus(status)));
                    }
                }
                NPAdInformationDialog.this.sortList();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String makeFormattedMessage(int i) {
        return this.localeManager.getStringEx(i, "").replace(NXDateUtil.DATE_RESOURCES_FORMAT, NXDateUtil.getCurrentTimeFormat("yyyy-MM-dd"));
    }

    public static NPAdInformationDialog newInstance(Activity activity, NXPPushMenuOption nXPPushMenuOption) {
        NPAdInformationDialog nPAdInformationDialog = new NPAdInformationDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putParcelable("nxp_push_option", nXPPushMenuOption);
        nPAdInformationDialog.setArguments(bundle);
        return nPAdInformationDialog;
    }

    private void onClickAdPushButton(final NXPAdInformationItem nXPAdInformationItem) {
        NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.4
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NPAdInformationDialog.this.dismissProgressDialog();
                ToyLog.d("agree push result : " + nXToyResult.toString());
                if (nXToyResult.errorCode == 0) {
                    NPAdInformationDialog.this.showAlertDialog(NPAdInformationDialog.this.makeFormattedMessage(nXToyResult.requestTag == NXToyRequestTag.OnPush.getValue() ? R.string.npres_push_receive_on : R.string.npres_push_receive_off), null, null);
                } else {
                    NPAdInformationDialog.this.showAlertDialog(NPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                    NPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                }
            }
        };
        int pushAgree = this.commonPreferenceController.getPushAgree();
        ToyLog.d("agree status(push) : " + pushAgree);
        showProgressDialog();
        if (pushAgree == 0) {
            this.policyManager.setPushPolicy(getActivity(), 1, nPListener);
        } else {
            this.policyManager.setPushPolicy(getActivity(), 2, nPListener);
        }
    }

    private void onClickEmailButton(final NXPAdInformationItem nXPAdInformationItem) {
        final NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.9
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                int i;
                NPAdInformationDialog.this.dismissProgressDialog();
                ToyLog.d("validate policy result : " + nXToyResult.toString());
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NPAdInformationDialog.this.showAlertDialog(NPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                    NPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    return;
                }
                if (NPAdInformationDialog.this.emailPolicy != null) {
                    NXToyEmailPolicyResult nXToyEmailPolicyResult = (NXToyEmailPolicyResult) nXToyResult;
                    NPAdInformationDialog.this.emailPolicy.setStatus(nXToyEmailPolicyResult.result.isSubscription);
                    boolean z = nXToyEmailPolicyResult.result.isSubscription == 1;
                    if (z && NPAdInformationDialog.this.emailPolicy.getIsAgreeToast()) {
                        i = R.string.npres_email_receive_on;
                    } else if (z || !NPAdInformationDialog.this.emailPolicy.getIsDisagreeToast()) {
                        return;
                    } else {
                        i = R.string.npres_email_receive_off;
                    }
                    NPAdInformationDialog.this.showAlertDialog(NPAdInformationDialog.this.makeFormattedMessage(i), null, null);
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
            this.termsManager.showTermsAgreementFromPolicyDialog(getActivity(), this.emailPolicy.getTermsList(), loginTermsList, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.10
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    NPAdInformationDialog.this.dismissProgressDialog();
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        NPAdInformationDialog.this.showAlertDialog(NPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                        NPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    } else {
                        NPAdInformationDialog.this.showProgressDialog();
                        NPAdInformationDialog.this.policyManager.putEmailPolicy(emailId, nPListener);
                    }
                }
            });
        }
    }

    private void onClickSmsButton(final NXPAdInformationItem nXPAdInformationItem) {
        final NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.5
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                int i;
                NPAdInformationDialog.this.dismissProgressDialog();
                ToyLog.d("validate policy result : " + nXToyResult.toString());
                if (nXToyResult.errorCode != 0) {
                    NPAdInformationDialog.this.showAlertDialog(NPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                    NPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    return;
                }
                if (NPAdInformationDialog.this.phoneNumberPolicy != null) {
                    NXToyPhoneNumberPolicyResult nXToyPhoneNumberPolicyResult = (NXToyPhoneNumberPolicyResult) nXToyResult;
                    NPAdInformationDialog.this.phoneNumberPolicy.setStatus(nXToyPhoneNumberPolicyResult.result.isSubscription);
                    boolean z = nXToyPhoneNumberPolicyResult.result.isSubscription == 1;
                    if (z && NPAdInformationDialog.this.phoneNumberPolicy.getIsAgreeToast()) {
                        i = R.string.npres_phone_collect_on;
                    } else if (z || !NPAdInformationDialog.this.phoneNumberPolicy.getIsDisagreeToast()) {
                        return;
                    } else {
                        i = R.string.npres_phone_collect_off;
                    }
                    NPAdInformationDialog.this.showAlertDialog(NPAdInformationDialog.this.makeFormattedMessage(i), null, null);
                }
            }
        };
        List<NXToyTerm> loginTermsList = this.termsManager.getLoginTermsList();
        int policyStatus = this.policyManager.getPolicyStatus(loginTermsList, this.phoneNumberPolicy);
        boolean zCheckGrantedPermission = NXRuntimePermissionManager.getInstance().checkGrantedPermission(getActivity(), NXRuntimePermissionManager.READ_PHONE_STATE);
        final NPListener nPListener2 = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.6
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NPAdInformationDialog.this.dismissProgressDialog();
                if (nXToyResult.errorCode != 0) {
                    NPAdInformationDialog.this.showToastWithPolicyEnabledError(NPAdInformationDialog.this.localeManager.getString(R.string.npres_phone_number_not_using_msg));
                    NPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    NXToyCommonPreferenceController.getInstance().setHasPhoneNumberFlag(NXToySessionManager.getInstance().getSession().getNpsn(), false);
                } else {
                    NXToyPhoneNumberResult nXToyPhoneNumberResult = (NXToyPhoneNumberResult) nXToyResult;
                    NPAdInformationDialog.this.showProgressDialog();
                    if (NPAdInformationDialog.this.phoneNumberPolicy.getStatus() == 1) {
                        NPAdInformationDialog.this.policyManager.deletePhoneNumberPolicy(nXToyPhoneNumberResult.result.phoneNumber, nPListener);
                    } else {
                        NPAdInformationDialog.this.policyManager.putPhoneNumberPolicy(nXToyPhoneNumberResult.result.phoneNumber, nPListener);
                    }
                }
            }
        };
        final NPListener nPListener3 = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.7
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NPAdInformationDialog.this.showProgressDialog();
                NPAdInformationDialog.this.policyManager.setPhoneNumberPolicy(NPAdInformationDialog.this.getActivity(), nPListener2);
            }
        };
        if (policyStatus == 0) {
            showToastWithPolicyEnabledError(this.localeManager.getString(R.string.npres_policy_terms_not_using_msg));
            this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
            return;
        }
        if (policyStatus == 2) {
            this.termsManager.showTermsAgreementFromPolicyDialog(getActivity(), this.phoneNumberPolicy.getTermsList(), loginTermsList, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.8
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode == NXToyErrorCode.FAILED_GET_PHONE_NUMBER.getCode()) {
                        NPAdInformationDialog.this.showToastWithPolicyEnabledError(NPAdInformationDialog.this.localeManager.getString(R.string.npres_phone_number_not_using_msg));
                        NPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    } else if (nXToyResult.errorCode == 0) {
                        NPAdInformationDialog.this.policyManager.showPhonePermissionConfirmationDialog(NPAdInformationDialog.this.getActivity(), nPListener3);
                    } else {
                        NPAdInformationDialog.this.showAlertDialog(NPAdInformationDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                        NPAdInformationDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    }
                }
            });
            return;
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
    public void showAlertDialog(final String str, final View.OnClickListener onClickListener, final DialogInterface.OnCancelListener onCancelListener) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.11
            @Override // java.lang.Runnable
            public void run() {
                new NXPAlertDialog.Builder(activity).setMessage(str).setPositiveButton(NPAdInformationDialog.this.localeManager.getString(R.string.confirm), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.11.2
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(view);
                        }
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.11.1
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
            runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.12
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(NPAdInformationDialog.this.getActivity(), str, 0).show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sortList() {
        Collections.sort(this.adInformationItems, new Comparator<NXPAdInformationItem>() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.3
            @Override // java.util.Comparator
            public int compare(NXPAdInformationItem nXPAdInformationItem, NXPAdInformationItem nXPAdInformationItem2) {
                return nXPAdInformationItem.getAdInformationViewOrder() - nXPAdInformationItem2.getAdInformationViewOrder();
            }
        });
        this.adInformationListAdapter.notifyDataSetChanged();
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    protected void activityCreated() {
        displayedAdInformationPolicies();
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPSimpleListView nXPSimpleListView = (NXPSimpleListView) View.inflate(this.applicationContext, R.layout.nxp_simple_list_view, null);
        NXPPushMenuOption nXPPushMenuOption = (NXPPushMenuOption) getArguments().getParcelable("nxp_push_option");
        this.title = nXPPushMenuOption.getTitle();
        this.adPushTitleString = nXPPushMenuOption.getPushLabelName();
        this.phoneNumberCollectionTitleString = nXPPushMenuOption.getSmsLabelName();
        this.emailCollectionTitleString = nXPPushMenuOption.getEmailLabelName();
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        this.commonPreferenceController = NXToyCommonPreferenceController.getInstance();
        this.adInformationItems = new ArrayList();
        if (NXStringUtil.isNull(this.adPushTitleString)) {
            this.adPushTitleString = this.localeManager.getString(R.string.npres_push_receive_on_off_setting_title);
        }
        if (NXStringUtil.isNull(this.phoneNumberCollectionTitleString)) {
            this.phoneNumberCollectionTitleString = this.localeManager.getString(R.string.npres_phone_collect_on_off_setting_title);
        }
        if (NXStringUtil.isNull(this.emailCollectionTitleString)) {
            this.emailCollectionTitleString = this.localeManager.getString(R.string.npres_email_receive_on_off_setting_title);
        }
        this.adInformationItems.add(new NXPAdInformationItem(1, this.adPushTitleString, this.commonPreferenceController.getPushAgree() == 1));
        nXPSimpleListView.setTitle(this.title);
        nXPSimpleListView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPAdInformationDialog.this.onBackPressed();
            }
        });
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
            case 3:
                onClickSmsButton(nXPAdInformationItem);
                break;
            case 4:
                onClickEmailButton(nXPAdInformationItem);
                break;
        }
        getMainLooperHandler().postDelayed(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog.13
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
