package kr.co.nexon.toy.android.ui.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;
import com.google.gson.reflect.TypeToken;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.nxcom.NPNexonCom;
import kr.co.nexon.mdev.android.util.NXDeviceUtil;
import kr.co.nexon.mdev.android.util.NXLocalizedString;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.NXPProviderManager;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;
import kr.co.nexon.npaccount.auth.result.NXToyNXKEncryptResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.auth.util.NPLoginUIUtil;
import kr.co.nexon.toy.android.ui.auth.view.NXPLoginSelectView;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.listener.NPListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPLoginSelectDialog extends NPDialogBase implements NXPLoginSelectView.NXPLoginButtonClickListener {
    public static final String KEY_MEMBERSHIP_INFO = "useMembershipInfo";
    public static final String KEY_NEXON_TPA_MODE = "nexonTPAMode";
    public static final String TAG = "NXPLoginSelectDialog";
    private String localeCode;
    private NXToyLocaleManager localeManager;
    private NXPLoginSelectView loginSelectView;
    private ArrayList<Integer> membershipList;
    private NPListener resultListener;
    private NXToySession session;
    private NPListener loginListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.1
        @Override // kr.co.nexon.toy.listener.NPListener
        public void onResult(final NXToyResult nXToyResult) {
            NXPLoginSelectDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.1.1
                @Override // java.lang.Runnable
                public void run() {
                    NXPLoginSelectDialog.this.dismissProgressDialog();
                    if (nXToyResult.errorCode == 0) {
                        nXToyResult.errorText = NXPLoginSelectDialog.this.localeManager.getString(R.string.npres_loginsuccess);
                        nXToyResult.errorDetail = NXPLoginSelectDialog.this.localeManager.getString(R.string.npres_loginsuccess);
                        if (NXPLoginSelectDialog.this.resultListener != null) {
                            NXPLoginSelectDialog.this.resultListener.onResult(nXToyResult);
                        }
                        NXPLoginSelectDialog.this.dismiss();
                        return;
                    }
                    Activity activity = NXPLoginSelectDialog.this.getActivity();
                    if (activity == null || NXPLoginSelectDialog.this.getView() == null) {
                        NXPLoginSelectDialog.this.onBackPressed();
                        return;
                    }
                    NXPLoginSelectDialog.this.getView().setVisibility(0);
                    if (nXToyResult.errorCode == NXToyErrorCode.WITHDRAWAL_PROCESSING_BY_THE_USER.getCode()) {
                        NXPLoginSelectDialog.this.recoverUser(activity);
                        return;
                    }
                    if (nXToyResult.errorCode == NXToyErrorCode.USING_NPSN_USER_NEED_RESOLVE.getCode()) {
                        NXPLoginSelectDialog.this.resolveAlreadyLoginedUser(activity);
                    } else if (nXToyResult.errorCode == NXToyErrorCode.NEED_CHANNELING_AGREE.getCode()) {
                        NXPLoginSelectDialog.this.showChannelingGuideAlert(activity, nXToyResult);
                    } else {
                        Toast.makeText(NXPLoginSelectDialog.this.applicationContext, String.format(nXToyResult.errorText + "(%d)", Integer.valueOf(nXToyResult.errorCode)), 0).show();
                    }
                }
            });
        }
    };
    private NPListener recoverUserListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.2
        @Override // kr.co.nexon.toy.listener.NPListener
        public void onResult(final NXToyResult nXToyResult) {
            NXPLoginSelectDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.2.1
                @Override // java.lang.Runnable
                public void run() {
                    NXPLoginSelectDialog.this.dismissProgressDialog();
                    if (nXToyResult.errorCode != 0) {
                        Toast.makeText(NXPLoginSelectDialog.this.applicationContext, String.format(nXToyResult.errorText + "(%d)", Integer.valueOf(nXToyResult.errorCode)), 0).show();
                        return;
                    }
                    nXToyResult.errorText = NXPLoginSelectDialog.this.localeManager.getString(R.string.npres_loginsuccess);
                    nXToyResult.errorDetail = NXPLoginSelectDialog.this.localeManager.getString(R.string.npres_loginsuccess);
                    if (NXPLoginSelectDialog.this.resultListener != null) {
                        NXPLoginSelectDialog.this.resultListener.onResult(nXToyResult);
                    }
                    NXPLoginSelectDialog.this.dismiss();
                }
            });
        }
    };

    /* JADX INFO: renamed from: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog$6, reason: invalid class name */
    class AnonymousClass6 extends NXClickListener {
        final /* synthetic */ NPNexonCom val$nexonCom;
        final /* synthetic */ NXPLoginSelectView val$view;

        /* JADX INFO: renamed from: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog$6$1, reason: invalid class name */
        class AnonymousClass1 implements NPListener {
            final /* synthetic */ byte[] val$password;

            /* JADX INFO: renamed from: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog$6$1$1, reason: invalid class name and collision with other inner class name */
            class RunnableC01111 implements Runnable {
                final /* synthetic */ NXToyResult val$toyResult;

                RunnableC01111(NXToyResult nXToyResult) {
                    this.val$toyResult = nXToyResult;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (this.val$toyResult.errorCode == 0) {
                        NXPLoginSelectDialog.this.loginListener.onResult(this.val$toyResult);
                        return;
                    }
                    if (this.val$toyResult.errorCode == NXToyErrorCode.NEED_AUTH.getCode()) {
                        final NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) this.val$toyResult;
                        String text = NXLocalizedString.getText(NXPLoginSelectDialog.this.applicationContext, NXPLoginSelectDialog.this.localeCode, R.string.nxauth2_text);
                        String text2 = NXLocalizedString.getText(NXPLoginSelectDialog.this.applicationContext, NXPLoginSelectDialog.this.localeCode, R.string.no);
                        new NXPAlertDialog.Builder(NXPLoginSelectDialog.this.getActivity()).setMessage(text).setNegativeButton(text2, null).setPositiveButton(NXLocalizedString.getText(NXPLoginSelectDialog.this.applicationContext, NXPLoginSelectDialog.this.localeCode, R.string.yes), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.6.1.1.1
                            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                            public void onSwallowClick(View view) {
                                NXToySession nXToySession = new NXToySession();
                                nXToySession.setNpsn(nXToyLoginResult.result.npSN);
                                nXToySession.setNPToken(nXToyLoginResult.result.npToken);
                                nXToySession.setUMKey(nXToyLoginResult.result.umKey);
                                nXToySession.setType(NXToyLoginType.LoginTypeNXCom.getValue());
                                nXToySession.setServiceId(NXPLoginSelectDialog.this.session.getServiceId());
                                NXPLoginSelectDialog.this.showProgressDialog();
                                AnonymousClass6.this.val$nexonCom.prepareNexonAuth(NXPLoginSelectDialog.this.getActivity(), nXToySession, new NPAuthListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.6.1.1.1.1
                                    @Override // kr.co.nexon.android.sns.NPAuthListener
                                    public void onResult(int i, String str, Bundle bundle) {
                                        NXPLoginSelectDialog.this.dismissProgressDialog();
                                        NXToyNXKEncryptResult nXToyNXKEncryptResult = (NXToyNXKEncryptResult) NXJsonUtil.fromObject(bundle.getString("toyresult"), NXToyNXKEncryptResult.class);
                                        if (i == 0) {
                                            AnonymousClass6.this.val$nexonCom.showNXAuth(NXPLoginSelectDialog.this.getActivity(), NXPLoginSelectDialog.this.session, nXToyNXKEncryptResult.result.encrypt, new NPAuthListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.6.1.1.1.1.1
                                                @Override // kr.co.nexon.android.sns.NPAuthListener
                                                public void onResult(int i2, String str2, Bundle bundle2) {
                                                }
                                            });
                                        } else {
                                            AnonymousClass6.this.val$view.setErrorMessage(nXToyNXKEncryptResult.errorText);
                                        }
                                    }
                                });
                            }
                        }).create().show();
                        return;
                    }
                    if (this.val$toyResult.errorCode == NXToyErrorCode.WITHDRAWAL_PROCESSING_BY_THE_USER.getCode() || this.val$toyResult.errorCode == NXToyErrorCode.USING_NPSN_USER_NEED_RESOLVE.getCode() || this.val$toyResult.errorCode == NXToyErrorCode.USING_NPSN_USER.getCode()) {
                        NXPLoginSelectDialog.this.loginListener.onResult(this.val$toyResult);
                    } else {
                        AnonymousClass6.this.val$view.setErrorMessage(this.val$toyResult.errorText);
                    }
                }
            }

            AnonymousClass1(byte[] bArr) {
                this.val$password = bArr;
            }

            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NXPLoginSelectDialog.this.dismissProgressDialog();
                Arrays.fill(this.val$password, (byte) 0);
                NXPLoginSelectDialog.this.runOnUiThread(new RunnableC01111(nXToyResult));
            }
        }

        AnonymousClass6(NXPLoginSelectView nXPLoginSelectView, NPNexonCom nPNexonCom) {
            this.val$view = nXPLoginSelectView;
            this.val$nexonCom = nPNexonCom;
        }

        @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
        protected void onSwallowClick(View view) {
            Context context = view.getContext();
            if (!NXDeviceUtil.isAvailableNetwork(context)) {
                Toast.makeText(context, NXLocalizedString.getText(context, NXPLoginSelectDialog.this.localeCode, R.string.npres_check_network), 0).show();
                return;
            }
            String strTrim = this.val$view.getIdText().trim();
            byte[] passwordTextToBytes = this.val$view.getPasswordTextToBytes();
            NXPLoginSelectDialog.this.showProgressDialog();
            this.val$view.clearPasswordText();
            this.val$view.setErrorMessage("");
            NPAccount.getInstance(NXPLoginSelectDialog.this.getActivity()).NXLogin(NXPLoginSelectDialog.this.getActivity(), strTrim, passwordTextToBytes, new AnonymousClass1(passwordTextToBytes));
        }
    }

    public static NXPLoginSelectDialog newInstance(Activity activity, List<Integer> list) {
        return newInstance(activity, list, false);
    }

    public static NXPLoginSelectDialog newInstance(Activity activity, List<Integer> list, boolean z) {
        ArrayList<Integer> arrayList = (ArrayList) NXJsonUtil.fromObject(NXJsonUtil.toJsonString(list), new TypeToken<ArrayList<Integer>>() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.3
        }.getType());
        NXPLoginSelectDialog nXPLoginSelectDialog = new NXPLoginSelectDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putBoolean(KEY_NEXON_TPA_MODE, z);
        bundle.putIntegerArrayList(KEY_MEMBERSHIP_INFO, arrayList);
        nXPLoginSelectDialog.setArguments(bundle);
        return nXPLoginSelectDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recoverUser(@NonNull Activity activity) {
        showProgressDialog();
        NPAccount.getInstance(activity).recoverUser(activity, this.recoverUserListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveAlreadyLoginedUser(@NonNull Activity activity) {
        showProgressDialog();
        NPAccount.getInstance(activity).resolveAlreadyLoginedUser(activity, this.loginListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChannelingGuideAlert(Activity activity, NXToyResult nXToyResult) {
        try {
            JSONObject jSONObject = new JSONObject(nXToyResult.errorDetail);
            String string = jSONObject.getString("channelingErrorMessage");
            final String string2 = jSONObject.getString("channelingUrl");
            if (NXStringUtil.isNotNull(string) && NXStringUtil.isNotNull(string2)) {
                new NXPAlertDialog.Builder(activity).setMessage(string).setPositiveButton(this.localeManager.getString(R.string.npres_channeling_agree_btn), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.11
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NXPLoginSelectDialog.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string2)));
                    }
                }).setNegativeButton(this.localeManager.getString(R.string.npres_cancel), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.10
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                    }
                }).create().show();
            } else {
                Toast.makeText(this.applicationContext, nXToyResult.errorText, 0).show();
            }
        } catch (Exception e) {
            ToyLog.d("showChannelingGuideAlert Exception :" + e);
            Toast.makeText(this.applicationContext, nXToyResult.errorText, 0).show();
        }
    }

    public NXPLoginSelectView createDefaultView() {
        NXPLoginSelectView nXPLoginSelectView = (NXPLoginSelectView) View.inflate(this.applicationContext, R.layout.nxp_login_select_view, null);
        nXPLoginSelectView.setTitleText(this.localeManager.getString(R.string.nplogin_login));
        nXPLoginSelectView.setDescriptionText(this.localeManager.getString(R.string.np_login_guide_msg));
        NPLoginUIUtil.sortDisplayPriority(NXToyLoginType.LoginTypeDefault.getValue(), this.membershipList);
        nXPLoginSelectView.setMembershipList(this.membershipList);
        nXPLoginSelectView.setLoginViewStateChangeListener(this);
        return nXPLoginSelectView;
    }

    public NXPLoginSelectView createTPAView() {
        final NXPLoginSelectView nXPLoginSelectView = (NXPLoginSelectView) View.inflate(this.applicationContext, R.layout.nxp_login_select_nexon_tpa_view, null);
        NPLoginUIUtil.sortDisplayPriority(NXToyLoginType.LoginTypeDefault.getValue(), this.membershipList);
        nXPLoginSelectView.setIsTPAMode(true);
        nXPLoginSelectView.initTPAView();
        nXPLoginSelectView.setTPAMembershipList(this.membershipList);
        for (int i = 0; i < this.membershipList.size(); i++) {
            if (this.membershipList.get(i).intValue() == NXToyLoginType.LoginTypeNXCom.getValue()) {
                this.membershipList.remove(i);
                break;
            }
        }
        nXPLoginSelectView.setLoginViewStateChangeListener(this);
        this.session = NXToySessionManager.getInstance().getSession();
        this.localeCode = NXToyCommonPreferenceController.getInstance().getLocale();
        nXPLoginSelectView.setIdEditHint(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_id_hint));
        nXPLoginSelectView.setIdTextChangeListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.4
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                if (charSequence.length() == 0) {
                    nXPLoginSelectView.setNexonLoginButtonEnabled(false);
                } else {
                    nXPLoginSelectView.setNexonLoginButtonEnabled(nXPLoginSelectView.getPasswordText().isEmpty() ? false : true);
                }
            }
        });
        nXPLoginSelectView.setPasswordEditHint(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_pw_hint));
        nXPLoginSelectView.setPasswordTextChangeListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.5
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                nXPLoginSelectView.setErrorMessage("");
                if (charSequence.length() == 0) {
                    nXPLoginSelectView.setNexonLoginButtonEnabled(false);
                } else {
                    nXPLoginSelectView.setNexonLoginButtonEnabled(nXPLoginSelectView.getIdText().isEmpty() ? false : true);
                }
            }
        });
        final NPNexonCom nPNexonCom = (NPNexonCom) NXPProviderManager.getInstance().getProvider(NXToyLoginType.LoginTypeNXCom.getValue());
        String cachedNexonAccountId = nPNexonCom.getCachedNexonAccountId();
        if (NXStringUtil.isNotNull(cachedNexonAccountId)) {
            nXPLoginSelectView.setIdText(cachedNexonAccountId);
        }
        nXPLoginSelectView.setNexonLoginButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_login), new AnonymousClass6(nXPLoginSelectView, nPNexonCom));
        nXPLoginSelectView.setSignUpButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nxjoin_description), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.7
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                nPNexonCom.showSignUpDialog(NXPLoginSelectDialog.this.getActivity());
            }
        });
        nXPLoginSelectView.setSearchIdButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_searchid_btn), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.8
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                nPNexonCom.showSearchIDDialog(NXPLoginSelectDialog.this.getActivity());
            }
        });
        nXPLoginSelectView.setSearchPwButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_searchpw_btn), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog.9
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                nPNexonCom.showSearchPasswordDialog(NXPLoginSelectDialog.this.getActivity());
            }
        });
        nXPLoginSelectView.setNexonLoginButtonEnabled(false);
        return nXPLoginSelectView;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        Bundle arguments = getArguments();
        boolean z = arguments.getBoolean(KEY_NEXON_TPA_MODE, false);
        this.membershipList = arguments.getIntegerArrayList(KEY_MEMBERSHIP_INFO);
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        if (z) {
            this.loginSelectView = createTPAView();
        } else {
            this.loginSelectView = createDefaultView();
        }
        return this.loginSelectView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (isShowingProgressDialog()) {
            return;
        }
        if (this.resultListener != null) {
            String string = this.localeManager.getString(R.string.npres_logincancel);
            this.resultListener.onResult(new NXToyResult(NXToyErrorCode.LOGIN_USER_CANCELED.getCode(), string, string, NXToyRequestTag.Login.getValue()));
        }
        super.onBackPressed();
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPLoginSelectView.NXPLoginButtonClickListener
    public void onClose() {
        onBackPressed();
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPLoginSelectView.NXPLoginButtonClickListener
    public void onLogin(String str) {
        if (isShowingProgressDialog()) {
            ToyLog.d("Another Login operation is in progress..");
        }
        int value = NXToyLoginType.LoginTypeNotLogined.getValue();
        try {
            value = Integer.parseInt(str);
        } catch (Exception e) {
            ToyLog.d("tag is not number type. exception:" + e.toString());
        }
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(value)) {
            case LoginTypeFaceBook:
            case LoginTypeGoogle:
            case LoginTypeTwitter:
            case LoginTypeGuest:
            case LoginTypeNaver:
            case LoginTypeNaverChannel:
            case LoginTypeNXNet:
            case LoginTypeGameCenter:
            case LoginTypeDaumChannel:
            case LoginTypeNXCom:
            case LoginTypeEmail:
            case LoginTypeVKontakte:
                showProgressDialog();
                getView().setVisibility(4);
                NPAccount.getInstance(getActivity()).login(getActivity(), value, this.loginListener);
                break;
            default:
                ToyLog.d("login type is invalid!! loginType:" + value);
                break;
        }
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }
}
