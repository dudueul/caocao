package kr.co.nexon.toy.android.ui.baseplate;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.facebook.GraphResponse;
import com.nexon.core.locale.NXLocale;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXDateUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionListener;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;
import kr.co.nexon.mdev.android.util.NXTelephonyUtil;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyGetPushPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberResult;
import kr.co.nexon.npaccount.auth.result.NXToyPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyValidatePolicyResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.push.NXPPush;
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
public class NXPPushSettingDialog extends NPDialogBase implements NXPAdInformationListAdapter.ItemClickListener {
    private static final int CODE_PERSONAL_INFO_CONSIGNMENT_AGREE = 1;
    private static final int CODE_PERSONAL_INFO_CONSIGNMENT_DISAGREE = 2;
    private static final int CODE_PERSONAL_INFO_CONSIGNMENT_NONE = 0;
    private static final int CODE_POLICY_KOREA_COLLECTION_PHONE_NUMBER = 2505;
    private static final int CODE_POLICY_NONE_TARGET_USER = 2501;
    private static final String CODE_VALIDATE_POLICY_DATA_METHOD_DELETE = "delete";
    private static final String CODE_VALIDATE_POLICY_DATA_METHOD_GET = "get";
    private static final String CODE_VALIDATE_POLICY_DATA_METHOD_PUT = "put";
    public static final String KEY_OPTIONS = "nxp_push_option";
    public static final String TAG = "NXPPushSettingDialog";
    private NPAccount account;
    private List<NXPAdInformationItem> adInformationItems;
    private NXPAdInformationListAdapter adInformationListAdapter;
    private String adPushTitleString;
    private NXToyCommonPreferenceController commonPrefCtl;
    private NXToyLocaleManager localeManager;
    private String nightPushTitleString;
    private String phoneNumberCollectionTitleString;
    private NXPPushPolicies pushPolicies;
    private NPListener resultListener;
    private NXRuntimePermissionManager runtimePermissionManager;
    private NXPTermsManager termsManager;
    private String title;
    private int privacyConsignedStatus = -1;
    private int personalInfoConsignmentId = -1;
    private boolean isEnabledPhoneNumberCollection = false;
    private String personalInfoConsignmentTitle = "";

    /* JADX INFO: renamed from: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog$6, reason: invalid class name */
    class AnonymousClass6 implements NPListener {
        AnonymousClass6() {
        }

        @Override // kr.co.nexon.toy.listener.NPListener
        public void onResult(NXToyResult nXToyResult) {
            List<NXToyPolicy> list;
            NXPPushSettingDialog.this.dismissProgressDialog();
            if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode() && (list = ((NXToyPolicyResult) nXToyResult).result.policy) != null && list.size() != 0) {
                for (NXToyPolicy nXToyPolicy : list) {
                    if (nXToyPolicy.code == 2505) {
                        NXPPushSettingDialog.this.personalInfoConsignmentId = nXToyPolicy.terms.get(0).termID;
                        NXPPushSettingDialog.this.personalInfoConsignmentTitle = nXToyPolicy.terms.get(0).title;
                        NXPPushSettingDialog.this.privacyConsignedStatus = nXToyPolicy.isTermsAgree;
                        break;
                    }
                }
            }
            if (NXPPushSettingDialog.this.privacyConsignedStatus == 0 || NXPPushSettingDialog.this.privacyConsignedStatus == 2) {
                NXPPushSettingDialog.this.adInformationItems.add(new NXPAdInformationItem(3, NXPPushSettingDialog.this.phoneNumberCollectionTitleString, false));
                NXPPushSettingDialog.this.sortList();
            } else {
                if (NXPPushSettingDialog.this.privacyConsignedStatus != 1) {
                    NXPPushSettingDialog.this.sortList();
                    return;
                }
                final NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.6.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        ToyLog.d("get collection phone number Policy result : " + nXToyResult2.toString());
                        NXToyValidatePolicyResult nXToyValidatePolicyResult = (NXToyValidatePolicyResult) nXToyResult2;
                        if (nXToyValidatePolicyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                            if (!NXPPushSettingDialog.this.account.isAuthCrashError(nXToyValidatePolicyResult.errorCode)) {
                                NXPPushSettingDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.6.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        Toast.makeText(NXPPushSettingDialog.this.getActivity(), NXPPushSettingDialog.this.localeManager.getString(R.string.npres_push_sms_error), 0).show();
                                    }
                                });
                                return;
                            } else {
                                NXPPushSettingDialog.this.resultListener.onResult(nXToyValidatePolicyResult);
                                NXPPushSettingDialog.this.dismiss();
                                return;
                            }
                        }
                        int i = nXToyValidatePolicyResult.result.code;
                        if (i == 2501) {
                            NXPPushSettingDialog.this.isEnabledPhoneNumberCollection = true;
                        } else if (i == 2505) {
                            NXPPushSettingDialog.this.isEnabledPhoneNumberCollection = false;
                        }
                        NXPPushSettingDialog.this.adInformationItems.add(new NXPAdInformationItem(3, NXPPushSettingDialog.this.phoneNumberCollectionTitleString, true));
                        NXPPushSettingDialog.this.sortList();
                    }
                };
                if (NXPPushSettingDialog.this.hasPermissionReadPhoneState()) {
                    NXPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("get", nPListener);
                } else {
                    NXPPushSettingDialog.this.grantPhoneNumberPermission(new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.6.2
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                NXPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("get", nPListener);
                            } else {
                                NXPPushSettingDialog.this.adInformationItems.add(new NXPAdInformationItem(3, NXPPushSettingDialog.this.phoneNumberCollectionTitleString, false));
                                NXPPushSettingDialog.this.sortList();
                            }
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayPhoneNumberCollectionStatus() {
        String mnc = NXTelephonyUtil.getMnc(getActivity().getApplicationContext());
        String mcc = NXTelephonyUtil.getMcc(getActivity().getApplicationContext());
        if (NXStringUtil.isNull(mnc) && NXStringUtil.isNull(mcc)) {
            sortList();
        } else if (this.localeManager.getCountry() != NXLocale.COUNTRY.Korea) {
            sortList();
        } else {
            showProgressDialog();
            this.account.getPolicy("terms", new AnonymousClass6());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getStringResource(int i) {
        return this.localeManager.getStringEx(i, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void grantPhoneNumberPermission(final NPListener nPListener) {
        this.runtimePermissionManager.requestPermissionsWithoutExplanation(getActivity(), new String[]{NXRuntimePermissionManager.READ_PHONE_STATE}, 100001, new NXRuntimePermissionListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.10
            @Override // kr.co.nexon.mdev.android.permission.NXRuntimePermissionListener
            public void onResult(int i, String[] strArr, int[] iArr) {
                if (iArr.length <= 0 || iArr[0] != 0) {
                    NXPPushSettingDialog.this.runtimePermissionManager.showPermissionAlert(NXPPushSettingDialog.this.getActivity(), NXPPushSettingDialog.this.localeManager.getString(R.string.npres_runtime_permission_message_after), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.10.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NXToyResult nXToyResult = new NXToyResult();
                            nXToyResult.errorCode = NXToyErrorCode.PERMISSION_DENIED.getCode();
                            nXToyResult.errorText = NXPPushSettingDialog.this.localeManager.getString(R.string.npres_runtime_permission_denied_msg);
                            nXToyResult.errorDetail = NXPPushSettingDialog.this.localeManager.getString(R.string.npres_runtime_permission_denied_msg);
                            ToyLog.d(nXToyResult.toString());
                            if (nPListener != null) {
                                nPListener.onResult(nXToyResult);
                            }
                        }
                    });
                    return;
                }
                String phoneNumber = NXTelephonyUtil.getPhoneNumber(NXPPushSettingDialog.this.getActivity());
                if (!NXStringUtil.isNotNull(phoneNumber)) {
                    ToyLog.d("Not found phone number");
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyPhoneNumberResult(NXToyErrorCode.FAILED_GET_PHONE_NUMBER.getCode(), "Not found phone number", ""));
                        return;
                    }
                    return;
                }
                NXToyPhoneNumberResult nXToyPhoneNumberResult = new NXToyPhoneNumberResult(0, GraphResponse.SUCCESS_KEY, "");
                nXToyPhoneNumberResult.result.phoneNumber = phoneNumber;
                if (nPListener != null) {
                    nPListener.onResult(nXToyPhoneNumberResult);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasPermissionReadPhoneState() {
        return this.runtimePermissionManager.checkGrantedPermission(getActivity(), NXRuntimePermissionManager.READ_PHONE_STATE) && NXStringUtil.isNotNull(NXTelephonyUtil.getPhoneNumber(getActivity()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String makeFormattedMesssage(String str) {
        return str.replace(NXDateUtil.DATE_RESOURCES_FORMAT, NXDateUtil.getCurrentTimeFormat("yyyy-MM-dd"));
    }

    private NPListener makePhoneNumberCollectionListener(final NXPAdInformationItem nXPAdInformationItem) {
        return new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.14
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                ToyLog.d("validate policy result : " + nXToyResult.toString());
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXPPushSettingDialog.this.showToast(nXToyResult.errorText, 0);
                    NXPPushSettingDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                } else {
                    String stringResource = NXPPushSettingDialog.this.isEnabledPhoneNumberCollection ? NXPPushSettingDialog.this.getStringResource(R.string.npres_phone_collect_off) : NXPPushSettingDialog.this.getStringResource(R.string.npres_phone_collect_on);
                    NXPPushSettingDialog.this.isEnabledPhoneNumberCollection = NXPPushSettingDialog.this.isEnabledPhoneNumberCollection ? false : true;
                    NXPPushSettingDialog.this.showAlertDialog(NXPPushSettingDialog.this.makeFormattedMesssage(stringResource), null, null);
                }
            }
        };
    }

    public static NXPPushSettingDialog newInstance(Activity activity, NXPPushMenuOption nXPPushMenuOption) {
        NXPPushSettingDialog nXPPushSettingDialog = new NXPPushSettingDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putParcelable("nxp_push_option", nXPPushMenuOption);
        nXPPushSettingDialog.setArguments(bundle);
        return nXPPushSettingDialog;
    }

    private void onClickAdPushButton(final NXPAdInformationItem nXPAdInformationItem) {
        NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.3
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXPPushSettingDialog.this.dismissProgressDialog();
                ToyLog.d("agree push result : " + nXToyResult.toString());
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPPushSettingDialog.this.showAlertDialog(NXPPushSettingDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                    NXPPushSettingDialog.this.pushPolicies.getAdPolicy().setEnabled(!NXPPushSettingDialog.this.pushPolicies.getAdPolicy().isEnabled());
                    NXPPushSettingDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                } else {
                    NXPPushPolicy adPolicy = NXPPushSettingDialog.this.pushPolicies.getAdPolicy();
                    String stringResource = adPolicy.isEnabled() ? NXPPushSettingDialog.this.getStringResource(R.string.npres_push_receive_on) : NXPPushSettingDialog.this.getStringResource(R.string.npres_push_receive_off);
                    NXPPushSettingDialog.this.setEnabledNightPushPolicy(adPolicy.isEnabled());
                    NXPPushSettingDialog.this.showAlertDialog(NXPPushSettingDialog.this.makeFormattedMesssage(stringResource), null, null);
                }
            }
        };
        showProgressDialog();
        this.pushPolicies.getAdPolicy().setEnabled(!this.pushPolicies.getAdPolicy().isEnabled());
        NXPPush.setPolicy(getActivity(), this.pushPolicies, nPListener);
    }

    private void onClickNightPushButton(final NXPAdInformationItem nXPAdInformationItem) {
        NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.5
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXPPushSettingDialog.this.dismissProgressDialog();
                ToyLog.d("agree night push result : " + nXToyResult.toString());
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXPPushSettingDialog.this.showAlertDialog(NXPPushSettingDialog.this.makeFormattedMesssage(NXPPushSettingDialog.this.pushPolicies.getNightPolicy().isEnabled() ? NXPPushSettingDialog.this.getStringResource(R.string.npres_night_push_receive_on) : NXPPushSettingDialog.this.getStringResource(R.string.npres_night_push_receive_off)), null, null);
                    return;
                }
                NXPPushSettingDialog.this.showAlertDialog(NXPPushSettingDialog.this.localeManager.getString(R.string.npres_push_sms_error), null, null);
                NXPPushSettingDialog.this.pushPolicies.getNightPolicy().setEnabled(!NXPPushSettingDialog.this.pushPolicies.getNightPolicy().isEnabled());
                NXPPushSettingDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
            }
        };
        showProgressDialog();
        this.pushPolicies.getNightPolicy().setEnabled(!this.pushPolicies.getNightPolicy().isEnabled());
        NXPPush.setPolicy(getActivity(), this.pushPolicies, nPListener);
    }

    private void onClickSmsButton(NXPAdInformationItem nXPAdInformationItem) {
        final NPListener nPListenerMakePhoneNumberCollectionListener = makePhoneNumberCollectionListener(nXPAdInformationItem);
        if (this.privacyConsignedStatus == 0 || this.privacyConsignedStatus == 2) {
            showPersonalInformationConsignmentDialog(nPListenerMakePhoneNumberCollectionListener);
            return;
        }
        NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.4
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nPListenerMakePhoneNumberCollectionListener.onResult(nXToyResult);
                } else if (NXPPushSettingDialog.this.isEnabledPhoneNumberCollection) {
                    NXPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("delete", nPListenerMakePhoneNumberCollectionListener);
                } else {
                    NXPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("put", nPListenerMakePhoneNumberCollectionListener);
                }
            }
        };
        if (hasPermissionReadPhoneState()) {
            nPListener.onResult(new NXToyResult(NXToyErrorCode.SUCCESS.getCode(), "", ""));
        } else {
            showPhoneNumberCollectionConfirmDialog(nPListener);
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
    public void setPersonalInformationConsignmentStatus(final NPListener nPListener) {
        HashMap map = new HashMap();
        map.put("phone", "");
        map.put("method", "put");
        map.put("isTermsAgree", Integer.valueOf(this.privacyConsignedStatus));
        this.account.validatePolicy(2505, map, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.7
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                nPListener.onResult(nXToyResult);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlertDialog(final String str, final View.OnClickListener onClickListener, final DialogInterface.OnCancelListener onCancelListener) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.12
            @Override // java.lang.Runnable
            public void run() {
                new NXPAlertDialog.Builder(NXPPushSettingDialog.this.getActivity()).setMessage(str).setPositiveButton(NXPPushSettingDialog.this.getStringResource(R.string.confirm), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.12.2
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(view);
                        }
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.12.1
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

    private void showPersonalInformationConsignmentDialog(final NPListener nPListener) {
        this.termsManager.showPersonalInformationConsignment(getActivity(), this.personalInfoConsignmentTitle, this.personalInfoConsignmentId, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.8
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                nXToyResult.errorText = "";
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPPushSettingDialog.this.privacyConsignedStatus = 2;
                    nPListener.onResult(new NXToyResult(-1, "", ""));
                } else {
                    NXPPushSettingDialog.this.privacyConsignedStatus = 1;
                    NXPPushSettingDialog.this.setPersonalInformationConsignmentStatus(new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.8.1
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                NXPPushSettingDialog.this.showPhoneNumberCollectionConfirmDialog(nPListener);
                            } else {
                                nPListener.onResult(nXToyResult2);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPhoneNumberCollectionConfirmDialog(final NPListener nPListener) {
        NXPPolicyManager.getInstance().showPhonePermissionConfirmationDialog(getActivity(), new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.9
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXPPushSettingDialog.this.grantPhoneNumberPermission(new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.9.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                            NXPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("put", nPListener);
                        } else {
                            nPListener.onResult(nXToyResult2);
                            ToyLog.d("Disagree collection phone number or not found phone number.");
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToast(final String str, final int i) {
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.13
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(NXPPushSettingDialog.this.getActivity(), str, i).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sortList() {
        Collections.sort(this.adInformationItems, new Comparator<NXPAdInformationItem>() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.11
            @Override // java.util.Comparator
            public int compare(NXPAdInformationItem nXPAdInformationItem, NXPAdInformationItem nXPAdInformationItem2) {
                return nXPAdInformationItem.getAdInformationViewOrder() - nXPAdInformationItem2.getAdInformationViewOrder();
            }
        });
        this.adInformationListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validatePhoneNumberCollectionPolicy(String str, NPListener nPListener) {
        HashMap map = new HashMap();
        map.put("phone", NXTelephonyUtil.getPhoneNumber(getActivity()));
        map.put("method", str);
        this.account.validatePolicy(2505, map, nPListener);
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    protected void activityCreated() {
        this.termsManager = NXPTermsManager.getInstance();
        this.runtimePermissionManager = NXRuntimePermissionManager.getInstance();
        this.commonPrefCtl = NXToyCommonPreferenceController.getInstance();
        this.account = NPAccount.getInstance(getActivity());
        showProgressDialog();
        this.account.getPushPolicy(new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXPPushSettingDialog.this.dismissProgressDialog();
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPPushSettingDialog.this.showAlertDialog(NXPPushSettingDialog.this.localeManager.getString(R.string.npres_request_failed), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.2.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NXPPushSettingDialog.this.onBackPressed();
                        }
                    }, null);
                    return;
                }
                NXPPushSettingDialog.this.pushPolicies = ((NXToyGetPushPolicyResult) nXToyResult).result.policies;
                NXPPushPolicy adPolicy = NXPPushSettingDialog.this.pushPolicies.getAdPolicy();
                NXPPushSettingDialog.this.adInformationItems.add(new NXPAdInformationItem(1, NXPPushSettingDialog.this.adPushTitleString, adPolicy.isEnabled()));
                if (NXPPushSettingDialog.this.localeManager.getCountry() == NXLocale.COUNTRY.Korea) {
                    NXPPushPolicy nightPolicy = NXPPushSettingDialog.this.pushPolicies.getNightPolicy();
                    NXPPushSettingDialog.this.adInformationItems.add(new NXPAdInformationItem(2, NXPPushSettingDialog.this.nightPushTitleString, nightPolicy != null && nightPolicy.isEnabled()));
                }
                if (!adPolicy.isEnabled()) {
                    NXPPushSettingDialog.this.setEnabledNightPushPolicy(false);
                }
                NXPPushSettingDialog.this.displayPhoneNumberCollectionStatus();
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
        this.localeManager = NXToyLocaleManager.getInstance(getActivity());
        if (NXStringUtil.isNull(this.adPushTitleString)) {
            this.adPushTitleString = this.localeManager.getString(R.string.npres_push_receive_on_off_setting_title);
        }
        if (NXStringUtil.isNull(this.nightPushTitleString)) {
            this.nightPushTitleString = this.localeManager.getString(R.string.npres_night_push_receive_on_off_setting_title);
        }
        if (NXStringUtil.isNull(this.phoneNumberCollectionTitleString)) {
            this.phoneNumberCollectionTitleString = this.localeManager.getString(R.string.npres_phone_collect_on_off_setting_title);
        }
        nXPSimpleListView.setTitle(this.title);
        nXPSimpleListView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXPPushSettingDialog.this.onBackPressed();
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
        }
        getMainLooperHandler().postDelayed(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog.15
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
