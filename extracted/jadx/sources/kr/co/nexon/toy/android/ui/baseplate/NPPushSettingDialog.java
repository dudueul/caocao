package kr.co.nexon.toy.android.ui.baseplate;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
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
import kr.co.nexon.npaccount.auth.result.NXToyPhoneNumberResult;
import kr.co.nexon.npaccount.auth.result.NXToyPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyValidatePolicyResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.view.adapter.NXPAdInformationListAdapter;
import kr.co.nexon.toy.android.ui.baseplate.view.adapter.model.NXPAdInformationItem;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.common.view.NXPSimpleListView;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPPushSettingDialog extends NPDialogBase implements NXPAdInformationListAdapter.ItemClickListener {
    private static final int CODE_PERSONAL_INFO_CONSIGNMENT_AGREE = 1;
    private static final int CODE_PERSONAL_INFO_CONSIGNMENT_DISAGREE = 2;
    private static final int CODE_PERSONAL_INFO_CONSIGNMENT_NONE = 0;
    private static final int CODE_POLICY_KOREA_COLLECTION_PHONE_NUMBER = 2505;
    private static final int CODE_POLICY_NONE_TARGET_USER = 2501;
    private static final String CODE_VALIDATE_POLICY_DATA_METHOD_DELETE = "delete";
    private static final String CODE_VALIDATE_POLICY_DATA_METHOD_GET = "get";
    private static final String CODE_VALIDATE_POLICY_DATA_METHOD_PUT = "put";
    public static final String KEY_PUSH_TITLE = "pushTitle";
    public static final String KEY_SMS_TITLE = "smsTitle";
    public static final String KEY_TITLE = "title";
    public static final String TAG = "NPPushSettingDialog";
    private NPAccount account;
    private List<NXPAdInformationItem> adInformationItems;
    private NXPAdInformationListAdapter adInformationListAdapter;
    private String adPushTitleString;
    private NXToyCommonPreferenceController commonPreferenceController;
    private NXToyLocaleManager localeManager;
    private String phoneNumberCollectionTitleString;
    private NXPPolicyManager policyManager;
    private NPListener resultListener;
    private NXRuntimePermissionManager runtimePermissionManager;
    private NXPTermsManager termsManager;
    private String title;
    private int privacyConsignedStatus = -1;
    private int personalInfoConsignmentId = -1;
    private boolean isEnabledPhoneNumberCollection = false;
    private String personalInfoConsignmentTitle = "";

    /* JADX INFO: renamed from: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog$2, reason: invalid class name */
    class AnonymousClass2 implements NPListener {
        AnonymousClass2() {
        }

        @Override // kr.co.nexon.toy.listener.NPListener
        public void onResult(NXToyResult nXToyResult) {
            List<NXToyPolicy> list;
            NPPushSettingDialog.this.dismissProgressDialog();
            if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode() && (list = ((NXToyPolicyResult) nXToyResult).result.policy) != null && list.size() > 0) {
                for (NXToyPolicy nXToyPolicy : list) {
                    if (nXToyPolicy.code == 2505) {
                        NPPushSettingDialog.this.personalInfoConsignmentId = nXToyPolicy.terms.get(0).termID;
                        NPPushSettingDialog.this.personalInfoConsignmentTitle = nXToyPolicy.terms.get(0).title;
                        NPPushSettingDialog.this.privacyConsignedStatus = nXToyPolicy.isTermsAgree;
                        break;
                    }
                }
            }
            if (NPPushSettingDialog.this.privacyConsignedStatus == 0 || NPPushSettingDialog.this.privacyConsignedStatus == 2) {
                NPPushSettingDialog.this.adInformationItems.add(new NXPAdInformationItem(3, NPPushSettingDialog.this.phoneNumberCollectionTitleString, false));
                NPPushSettingDialog.this.sortList();
            } else {
                if (NPPushSettingDialog.this.privacyConsignedStatus != 1) {
                    NPPushSettingDialog.this.sortList();
                    return;
                }
                NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.2.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        ToyLog.d("get collection phone number Policy result : " + nXToyResult2);
                        NXToyValidatePolicyResult nXToyValidatePolicyResult = (NXToyValidatePolicyResult) nXToyResult2;
                        if (nXToyValidatePolicyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                            if (!NPPushSettingDialog.this.account.isAuthCrashError(nXToyValidatePolicyResult.errorCode)) {
                                ToyLog.d("Get validate phone number policy information error : " + nXToyResult2);
                                return;
                            } else {
                                NPPushSettingDialog.this.resultListener.onResult(nXToyValidatePolicyResult);
                                NPPushSettingDialog.this.dismiss();
                                return;
                            }
                        }
                        int i = nXToyValidatePolicyResult.result.code;
                        if (i == 2501) {
                            NPPushSettingDialog.this.isEnabledPhoneNumberCollection = true;
                        } else if (i == 2505) {
                            NPPushSettingDialog.this.isEnabledPhoneNumberCollection = false;
                        }
                        NPPushSettingDialog.this.adInformationItems.add(new NXPAdInformationItem(3, NPPushSettingDialog.this.phoneNumberCollectionTitleString, NPPushSettingDialog.this.isEnabledPhoneNumberCollection));
                        NPPushSettingDialog.this.sortList();
                    }
                };
                if (NPPushSettingDialog.this.hasPermissionReadPhoneState()) {
                    NPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("get", nPListener);
                } else {
                    NPPushSettingDialog.this.grantPhoneNumberPermission(new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.2.2
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                NPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("put", new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.2.2.1
                                    @Override // kr.co.nexon.toy.listener.NPListener
                                    public void onResult(NXToyResult nXToyResult3) {
                                        if (nXToyResult3.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                            NPPushSettingDialog.this.isEnabledPhoneNumberCollection = true;
                                        } else {
                                            NPPushSettingDialog.this.isEnabledPhoneNumberCollection = false;
                                        }
                                        NPPushSettingDialog.this.adInformationItems.add(new NXPAdInformationItem(3, NPPushSettingDialog.this.phoneNumberCollectionTitleString, NPPushSettingDialog.this.isEnabledPhoneNumberCollection));
                                        NPPushSettingDialog.this.sortList();
                                    }
                                });
                            } else {
                                NPPushSettingDialog.this.adInformationItems.add(new NXPAdInformationItem(3, NPPushSettingDialog.this.phoneNumberCollectionTitleString, false));
                                NPPushSettingDialog.this.sortList();
                            }
                        }
                    });
                }
            }
        }
    }

    private void displayPhoneNumberCollectionStatus() {
        String mnc = NXTelephonyUtil.getMnc(getActivity().getApplicationContext());
        String mcc = NXTelephonyUtil.getMcc(getActivity().getApplicationContext());
        if (NXStringUtil.isNull(mnc) && NXStringUtil.isNull(mcc)) {
            sortList();
        } else {
            if (getLocaleManager().getCountry() != NXLocale.COUNTRY.Korea) {
                sortList();
                return;
            }
            AnonymousClass2 anonymousClass2 = new AnonymousClass2();
            showProgressDialog();
            this.account.getPolicy("terms", anonymousClass2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NXToyLocaleManager getLocaleManager() {
        if (this.localeManager == null) {
            this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        }
        return this.localeManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getStringResource(int i) {
        return getLocaleManager().getStringEx(i, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void grantPhoneNumberPermission(final NPListener nPListener) {
        this.runtimePermissionManager.requestPermissionsWithoutExplanation(getActivity(), new String[]{NXRuntimePermissionManager.READ_PHONE_STATE}, 100001, new NXRuntimePermissionListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.6
            @Override // kr.co.nexon.mdev.android.permission.NXRuntimePermissionListener
            public void onResult(int i, String[] strArr, int[] iArr) {
                if (iArr.length <= 0 || iArr[0] != 0) {
                    NPPushSettingDialog.this.runtimePermissionManager.showPermissionAlert(NPPushSettingDialog.this.getActivity(), NPPushSettingDialog.this.getLocaleManager().getString(R.string.npres_runtime_permission_message_after), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.6.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NXToyResult nXToyResult = new NXToyResult();
                            nXToyResult.errorCode = NXToyErrorCode.PERMISSION_DENIED.getCode();
                            nXToyResult.errorText = NPPushSettingDialog.this.getLocaleManager().getString(R.string.npres_runtime_permission_denied_msg);
                            nXToyResult.errorDetail = NPPushSettingDialog.this.getLocaleManager().getString(R.string.npres_runtime_permission_denied_msg);
                            ToyLog.d(nXToyResult.toString());
                            if (nPListener != null) {
                                nPListener.onResult(nXToyResult);
                            }
                        }
                    });
                    return;
                }
                String phoneNumber = NXTelephonyUtil.getPhoneNumber(NPPushSettingDialog.this.getActivity());
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

    public static NPPushSettingDialog newInstance(Activity activity, String str, String str2, String str3) {
        NPPushSettingDialog nPPushSettingDialog = new NPPushSettingDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("title", str);
        bundle.putString(KEY_PUSH_TITLE, str2);
        bundle.putString(KEY_SMS_TITLE, str3);
        nPPushSettingDialog.setArguments(bundle);
        return nPPushSettingDialog;
    }

    private void onClickAdPushButton(final NXPAdInformationItem nXPAdInformationItem) {
        NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.9
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(final NXToyResult nXToyResult) {
                NPPushSettingDialog.this.dismissProgressDialog();
                ToyLog.d("agree push result : " + nXToyResult.toString());
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NPPushSettingDialog.this.showAlertDialog(NPPushSettingDialog.this.getLocaleManager().getString(R.string.npres_push_sms_error), null, null);
                    NPPushSettingDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                    return;
                }
                if (NPPushSettingDialog.this.getLocaleManager().getCountry() != NXLocale.COUNTRY.Korea) {
                    if (NPPushSettingDialog.this.resultListener != null) {
                        NPPushSettingDialog.this.resultListener.onResult(nXToyResult);
                        return;
                    }
                    return;
                }
                String string = "";
                if (nXToyResult.requestTag == NXToyRequestTag.OnPush.getValue()) {
                    string = NPPushSettingDialog.this.getLocaleManager().getString(R.string.npres_push_receive_on);
                } else if (nXToyResult.requestTag == NXToyRequestTag.OffPush.getValue()) {
                    string = NPPushSettingDialog.this.getLocaleManager().getString(R.string.npres_push_receive_off);
                }
                NPPushSettingDialog.this.showAlertDialog(NPPushSettingDialog.this.makeFormattedMesssage(string), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.9.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (NPPushSettingDialog.this.resultListener != null) {
                            NPPushSettingDialog.this.resultListener.onResult(nXToyResult);
                        }
                    }
                }, null);
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

    private void onClickSmsButton(final NXPAdInformationItem nXPAdInformationItem) {
        final NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.10
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(final NXToyResult nXToyResult) {
                ToyLog.d("validate policy result : " + nXToyResult.toString());
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NPPushSettingDialog.this.showAlertDialog(NPPushSettingDialog.this.getLocaleManager().getString(R.string.npres_push_sms_error), null, null);
                    NPPushSettingDialog.this.adInformationListAdapter.toggleSwitch(nXPAdInformationItem);
                } else if (NPPushSettingDialog.this.getLocaleManager().getCountry() != NXLocale.COUNTRY.Korea) {
                    if (NPPushSettingDialog.this.resultListener != null) {
                        NPPushSettingDialog.this.resultListener.onResult(nXToyResult);
                    }
                } else {
                    String stringResource = NPPushSettingDialog.this.isEnabledPhoneNumberCollection ? NPPushSettingDialog.this.getStringResource(R.string.npres_phone_collect_off) : NPPushSettingDialog.this.getStringResource(R.string.npres_phone_collect_on);
                    NPPushSettingDialog.this.isEnabledPhoneNumberCollection = !NPPushSettingDialog.this.isEnabledPhoneNumberCollection;
                    NPPushSettingDialog.this.showAlertDialog(NPPushSettingDialog.this.makeFormattedMesssage(stringResource), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.10.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (NPPushSettingDialog.this.resultListener != null) {
                                NPPushSettingDialog.this.resultListener.onResult(nXToyResult);
                            }
                        }
                    }, null);
                }
            }
        };
        if (this.privacyConsignedStatus == 0 || this.privacyConsignedStatus == 2) {
            showPersonalInformationConsignmentDialog(nPListener);
            return;
        }
        NPListener nPListener2 = new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.11
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nPListener.onResult(nXToyResult);
                } else if (NPPushSettingDialog.this.isEnabledPhoneNumberCollection) {
                    NPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("delete", nPListener);
                } else {
                    NPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("put", nPListener);
                }
            }
        };
        if (hasPermissionReadPhoneState()) {
            nPListener2.onResult(new NXToyResult(NXToyErrorCode.SUCCESS.getCode(), "", ""));
        } else {
            showPhoneNumberCollectionConfirmDialog(nPListener2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPersonalInformationConsignmentStatus(final NPListener nPListener) {
        HashMap map = new HashMap();
        map.put("phone", "");
        map.put("method", "put");
        map.put("isTermsAgree", Integer.valueOf(this.privacyConsignedStatus));
        this.account.validatePolicy(2505, map, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.3
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                nPListener.onResult(nXToyResult);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlertDialog(final String str, final View.OnClickListener onClickListener, final DialogInterface.OnCancelListener onCancelListener) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.8
            @Override // java.lang.Runnable
            public void run() {
                new NXPAlertDialog.Builder(activity).setMessage(str).setPositiveButton(NPPushSettingDialog.this.getStringResource(R.string.confirm), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.8.2
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(view);
                        }
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.8.1
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
        this.termsManager.showPersonalInformationConsignment(getActivity(), this.personalInfoConsignmentTitle, this.personalInfoConsignmentId, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.4
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                nXToyResult.errorText = "";
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NPPushSettingDialog.this.privacyConsignedStatus = 2;
                    nPListener.onResult(new NXToyResult(-1, "", ""));
                } else {
                    NPPushSettingDialog.this.privacyConsignedStatus = 1;
                    NPPushSettingDialog.this.setPersonalInformationConsignmentStatus(new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.4.1
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                NPPushSettingDialog.this.showPhoneNumberCollectionConfirmDialog(nPListener);
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
        this.policyManager.showPhonePermissionConfirmationDialog(getActivity(), new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.5
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NPPushSettingDialog.this.grantPhoneNumberPermission(new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.5.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                            NPPushSettingDialog.this.validatePhoneNumberCollectionPolicy("put", nPListener);
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
    public void sortList() {
        Collections.sort(this.adInformationItems, new Comparator<NXPAdInformationItem>() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.7
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
        this.account = NPAccount.getInstance(getActivity());
        this.policyManager = NXPPolicyManager.getInstance();
        this.termsManager = NXPTermsManager.getInstance();
        this.runtimePermissionManager = NXRuntimePermissionManager.getInstance();
        displayPhoneNumberCollectionStatus();
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPSimpleListView nXPSimpleListView = (NXPSimpleListView) View.inflate(getActivity(), R.layout.nxp_simple_list_view, null);
        this.title = getArguments().getString("title", "");
        this.adPushTitleString = getArguments().getString(KEY_PUSH_TITLE, "");
        this.phoneNumberCollectionTitleString = getArguments().getString(KEY_SMS_TITLE, "");
        if (NXStringUtil.isNull(this.adPushTitleString)) {
            this.adPushTitleString = getLocaleManager().getString(R.string.npres_push_receive_on_off_setting_title);
        }
        if (NXStringUtil.isNull(this.phoneNumberCollectionTitleString)) {
            this.phoneNumberCollectionTitleString = getLocaleManager().getString(R.string.npres_phone_collect_on_off_setting_title);
        }
        nXPSimpleListView.setTitle(this.title);
        nXPSimpleListView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPPushSettingDialog.this.onBackPressed();
            }
        });
        this.adInformationItems = new ArrayList();
        this.commonPreferenceController = NXToyCommonPreferenceController.getInstance();
        this.adInformationItems.add(new NXPAdInformationItem(1, this.adPushTitleString, this.commonPreferenceController.getPushAgree() == 1));
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
        }
        getMainLooperHandler().postDelayed(new Runnable() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog.12
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
