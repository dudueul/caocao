package kr.co.nexon.android.sns.nxcom.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXCrypto.NXCrypto;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import com.nexon.platform.auth.NXPProviderAuthenticationListener;
import com.nexon.platform.auth.model.NXPProviderAuthenticationInfo;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.facebook.NPFacebook;
import kr.co.nexon.android.sns.google.NPGoogleSignIn;
import kr.co.nexon.android.sns.naver.NPNaverChannel;
import kr.co.nexon.android.sns.nxcom.ui.view.NXPNexonLoginView;
import kr.co.nexon.mdev.android.util.NXDeviceUtil;
import kr.co.nexon.mdev.android.util.NXLocalizedString;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.NXPProviderManager;
import kr.co.nexon.npaccount.auth.request.NXToyGetNexonSNByMembershipIdRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetNexonSNByNXKLoginRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetNexonSNByTPARequest;
import kr.co.nexon.npaccount.auth.result.NXToyGetNexonSNByMembershipIdResult;
import kr.co.nexon.npaccount.auth.result.NXToyNexonSNResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPGetNexonSNDialog extends NPDialogBase {
    public static final String KEY_NEXON_ACCOUNT_ID = "key_nexon_id";
    public static final String KEY_SESSION = "session";
    public static final String KEY_TOY_RESULT = "toyresult";
    public static final String KEY_TPASTATE = "state";
    public static final String KEY_TPATOKEN = "token";
    public static final String KEY_USE_NATIVE_TPA = "use_native_tpa";
    public static final String TAG = "NPGetNexonSNDialog";
    private String localeCode;
    private NXPNexonLoginView nexonLoginView;
    private NXPGetNexonSNListener resultListener;
    private NXToySession session;
    private boolean useNative;
    private boolean useTPA;

    public interface NXPGetNexonSNListener {
        void onFailure(int i, @NonNull String str);

        void onNeedToCache(@NonNull String str);

        void onSuccess(@NonNull NXToyNexonSNResult nXToyNexonSNResult, @Nullable NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NXToyNexonSNResult convertToNexonSNResult(@NonNull NXToyGetNexonSNByMembershipIdResult nXToyGetNexonSNByMembershipIdResult) {
        NXToyNexonSNResult nXToyNexonSNResult = new NXToyNexonSNResult();
        nXToyNexonSNResult.result.memSNUuid = nXToyGetNexonSNByMembershipIdResult.result.memSNUuid;
        nXToyNexonSNResult.result.nexonSNString = nXToyGetNexonSNByMembershipIdResult.result.nexonSN;
        try {
            nXToyNexonSNResult.result.nexonSN = Long.valueOf(nXToyNexonSNResult.result.nexonSNString).longValue();
        } catch (NumberFormatException e) {
            ToyLog.d("numberFormatException e:" + e);
        }
        return nXToyNexonSNResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getNexonSNFromNative(int i) {
        final NPAuthPlugin provider = NXPProviderManager.getInstance().getProvider(i);
        if (provider == null) {
            ToyLog.d("authPlugin is null!! loginType:" + i);
        } else if (this.session.getType() != i) {
            provider.logout(getActivity(), new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.12
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i2, String str, Bundle bundle) {
                    NPGetNexonSNDialog.this.getNexonSNWithProvider(provider);
                }
            });
        } else {
            getNexonSNWithProvider(provider);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getNexonSNFromWeb(String str) {
        ToyLog.d("Login TPA : " + str);
        NXPNXComLoginByTPADialog nXPNXComLoginByTPADialogNewInstance = NXPNXComLoginByTPADialog.newInstance(getActivity(), str);
        nXPNXComLoginByTPADialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.11
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str2, Bundle bundle) {
                String string = bundle.getString("token", null);
                String string2 = bundle.getString("state", null);
                if (NXStringUtil.isNull(string) || NXStringUtil.isNull(string2)) {
                    NPGetNexonSNDialog.this.showToastMessage(NXLocalizedString.getText(NPGetNexonSNDialog.this.applicationContext, NPGetNexonSNDialog.this.localeCode, R.string.npres_request_failed), 0);
                } else {
                    NPGetNexonSNDialog.this.requestNexonSNWithToken(string, string2);
                }
            }
        });
        nXPNXComLoginByTPADialogNewInstance.showDialog(getActivity(), NPDialogBase.TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getNexonSNWithProvider(@NonNull NPAuthPlugin nPAuthPlugin) {
        NXPProviderAuthenticationListener nXPProviderAuthenticationListener = new NXPProviderAuthenticationListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.13
            @Override // com.nexon.platform.auth.NXPProviderAuthenticationListener
            public void onFailure(int i, String str) {
                NPGetNexonSNDialog.this.dismissProgressDialog();
                NPGetNexonSNDialog.this.showSignInErrorMessageUsingToast(i, str);
            }

            @Override // com.nexon.platform.auth.NXPProviderAuthenticationListener
            public void onSuccess(@NonNull NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo) {
                NPGetNexonSNDialog.this.dismissProgressDialog();
                NPGetNexonSNDialog.this.requestNexonSNWithAuthInfo(nXPProviderAuthenticationInfo);
            }
        };
        showProgressDialog();
        if (nPAuthPlugin instanceof NPGoogleSignIn) {
            ((NPGoogleSignIn) nPAuthPlugin).signIn(getActivity(), nXPProviderAuthenticationListener);
            return;
        }
        if (nPAuthPlugin instanceof NPFacebook) {
            ((NPFacebook) nPAuthPlugin).signIn(getActivity(), nXPProviderAuthenticationListener);
        } else if (nPAuthPlugin instanceof NPNaverChannel) {
            ((NPNaverChannel) nPAuthPlugin).signIn(getActivity(), nXPProviderAuthenticationListener);
        } else {
            ToyLog.d("Can not support type. serviceName:" + nPAuthPlugin.getServiceName());
        }
    }

    public static NPGetNexonSNDialog newInstance(Activity activity, String str, String str2, boolean z) {
        NPGetNexonSNDialog nPGetNexonSNDialog = new NPGetNexonSNDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("session", str);
        bundle.putString("key_nexon_id", str2);
        bundle.putBoolean(KEY_USE_NATIVE_TPA, z);
        nPGetNexonSNDialog.setArguments(bundle);
        return nPGetNexonSNDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nxLogin(final String str, String str2) {
        this.nexonLoginView.setErrorMessage("");
        if (NXStringUtil.isNull(str.trim())) {
            this.nexonLoginView.setErrorMessage(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_need_id));
            return;
        }
        if (NXStringUtil.isNull(str2)) {
            this.nexonLoginView.setErrorMessage(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_need_pw));
            return;
        }
        String strEncodeHmacSha256ToHexString = str2;
        if (str.contains("@")) {
            try {
                strEncodeHmacSha256ToHexString = NXCrypto.encodeHmacSha256ToHexString("NexonUser", str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        showProgressDialog();
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetNexonSNByNXKLoginRequest(str, strEncodeHmacSha256ToHexString), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.8
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                NPGetNexonSNDialog.this.dismissProgressDialog();
                NXToyNexonSNResult nXToyNexonSNResult = (NXToyNexonSNResult) nXToyResult;
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NPGetNexonSNDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NPGetNexonSNDialog.this.nexonLoginView.setErrorMessage(nXToyResult.errorText);
                        }
                    });
                    return;
                }
                nXToyNexonSNResult.result.nexonSNString = Long.toString(nXToyNexonSNResult.result.nexonSN);
                if (NPGetNexonSNDialog.this.resultListener != null) {
                    NPGetNexonSNDialog.this.resultListener.onNeedToCache(str);
                    NPGetNexonSNDialog.this.resultListener.onSuccess(nXToyNexonSNResult, null);
                }
                NPGetNexonSNDialog.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestNexonSNWithAuthInfo(@NonNull final NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo) {
        showProgressDialog();
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetNexonSNByMembershipIdRequest(nXPProviderAuthenticationInfo.getLoginType(), nXPProviderAuthenticationInfo.getId(), nXPProviderAuthenticationInfo.getFbBizToken(), nXPProviderAuthenticationInfo.getNaverAccessToken()), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.15
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("getNexonSNResult:" + nXToyResult);
                NPGetNexonSNDialog.this.dismissProgressDialog();
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NPGetNexonSNDialog.this.showAlertMessage(nXToyResult.errorText);
                    return;
                }
                if (NPGetNexonSNDialog.this.resultListener != null) {
                    NXToyNexonSNResult nXToyNexonSNResultConvertToNexonSNResult = NPGetNexonSNDialog.this.convertToNexonSNResult((NXToyGetNexonSNByMembershipIdResult) nXToyResult);
                    if (nXToyNexonSNResultConvertToNexonSNResult.result.nexonSN == 0) {
                        NPGetNexonSNDialog.this.resultListener.onSuccess(nXToyNexonSNResultConvertToNexonSNResult, null);
                    } else {
                        NPGetNexonSNDialog.this.resultListener.onSuccess(nXToyNexonSNResultConvertToNexonSNResult, nXPProviderAuthenticationInfo);
                    }
                }
                NPGetNexonSNDialog.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestNexonSNWithToken(String str, String str2) {
        showProgressDialog();
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetNexonSNByTPARequest(str, str2), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.14
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("nxtoyNexonsnResult:" + nXToyResult);
                NPGetNexonSNDialog.this.dismissProgressDialog();
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NPGetNexonSNDialog.this.showAlertMessage(nXToyResult.errorText);
                    return;
                }
                if (NPGetNexonSNDialog.this.resultListener != null) {
                    NXToyNexonSNResult nXToyNexonSNResult = (NXToyNexonSNResult) nXToyResult;
                    nXToyNexonSNResult.result.nexonSNString = Long.toString(nXToyNexonSNResult.result.nexonSN);
                    NPGetNexonSNDialog.this.resultListener.onSuccess(nXToyNexonSNResult, null);
                }
                NPGetNexonSNDialog.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchID() {
        String jsonString = NXJsonUtil.toJsonString(this.session);
        ToyLog.d("Search ID");
        NPNXComSearchIDPWDialog nPNXComSearchIDPWDialogNewInstance = NPNXComSearchIDPWDialog.newInstance(getActivity(), jsonString, 0);
        nPNXComSearchIDPWDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.9
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
            }
        });
        nPNXComSearchIDPWDialogNewInstance.showDialog(getActivity(), NPNXComSearchIDPWDialog.TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchPassword() {
        String jsonString = NXJsonUtil.toJsonString(this.session);
        ToyLog.d("Search PW");
        NPNXComSearchIDPWDialog nPNXComSearchIDPWDialogNewInstance = NPNXComSearchIDPWDialog.newInstance(getActivity(), jsonString, 1);
        nPNXComSearchIDPWDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.10
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
            }
        });
        nPNXComSearchIDPWDialogNewInstance.showDialog(getActivity(), NPNXComSearchIDPWDialog.TAG);
    }

    private void setSession(String str) {
        if (NXStringUtil.isNotNull(str)) {
            this.session = (NXToySession) NXJsonUtil.fromObject(str, NXToySession.class);
        } else {
            this.session = new NXToySession();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlertMessage(final String str) {
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.16
            @Override // java.lang.Runnable
            public void run() {
                Activity activity = NPGetNexonSNDialog.this.getActivity();
                if (activity == null) {
                    return;
                }
                new NXPAlertDialog.Builder(activity).setMessage(str).setPositiveButton(NXToyLocaleManager.getInstance(NPGetNexonSNDialog.this.applicationContext).getString(R.string.confirm), null).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSignInErrorMessageUsingToast(int i, String str) {
        ToyLog.d("original errorCode:" + i + " , message:" + str);
        if (i == NXToyErrorCode.GOOGLE_LOGIN_USER_CANCEL.getCode() || i == NXToyErrorCode.FACEBOOK_USER_CANCEL.getCode() || i == NXToyErrorCode.NAVERCHN_LOGIN_USER_CANCELED.getCode()) {
            i = NXToyErrorCode.LOGIN_USER_CANCELED.getCode();
            str = NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.npres_logincancel);
        }
        showToastMessage(str + " (" + i + ")", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastMessage(final String str, final int i) {
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.17
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Toast.makeText(NPGetNexonSNDialog.this.applicationContext, str, i).show();
                } catch (Exception e) {
                    ToyLog.e("Toast Exception:" + e.toString());
                }
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.nexonLoginView = (NXPNexonLoginView) View.inflate(this.applicationContext, R.layout.nxp_nexon_login_view, null);
        setSession(getArguments().getString("session"));
        this.useNative = getArguments().getBoolean(KEY_USE_NATIVE_TPA);
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        this.localeCode = nXToyCommonPreferenceController.getLocale();
        this.useTPA = nXToyCommonPreferenceController.useTPA();
        this.nexonLoginView.setIdEditHint(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_id_hint));
        this.nexonLoginView.setIdTextChangeListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.1
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() == 0) {
                    NPGetNexonSNDialog.this.nexonLoginView.setLoginButtonEnabled(false);
                } else {
                    NPGetNexonSNDialog.this.nexonLoginView.setLoginButtonEnabled(NPGetNexonSNDialog.this.nexonLoginView.getPasswordText().isEmpty() ? false : true);
                }
            }
        });
        this.nexonLoginView.setPasswordEditHint(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_pw_hint));
        this.nexonLoginView.setPasswordTextChangeListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.2
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                NPGetNexonSNDialog.this.nexonLoginView.setErrorMessage("");
                if (charSequence.length() == 0) {
                    NPGetNexonSNDialog.this.nexonLoginView.setLoginButtonEnabled(false);
                } else {
                    NPGetNexonSNDialog.this.nexonLoginView.setLoginButtonEnabled(NPGetNexonSNDialog.this.nexonLoginView.getIdText().isEmpty() ? false : true);
                }
            }
        });
        this.nexonLoginView.setLoginButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_login), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                Context context = view.getContext();
                if (!NXDeviceUtil.isAvailableNetwork(context)) {
                    Toast.makeText(context, NXLocalizedString.getText(context, NPGetNexonSNDialog.this.localeCode, R.string.npres_check_network), 0).show();
                    return;
                }
                String passwordText = NPGetNexonSNDialog.this.nexonLoginView.getPasswordText();
                NPGetNexonSNDialog.this.nexonLoginView.clearPasswordText();
                NPGetNexonSNDialog.this.nxLogin(NPGetNexonSNDialog.this.nexonLoginView.getIdText(), passwordText);
            }
        });
        String string = getArguments().getString("key_nexon_id", "");
        if (NXStringUtil.isNotNull(string)) {
            this.nexonLoginView.setIdText(string);
        }
        this.nexonLoginView.setSignUpVisibility(8);
        this.nexonLoginView.setSearchIdButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_searchid_btn), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.4
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPGetNexonSNDialog.this.searchID();
            }
        });
        this.nexonLoginView.setSearchPwButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_searchpw_btn), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.5
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPGetNexonSNDialog.this.searchPassword();
            }
        });
        this.nexonLoginView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.6
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPGetNexonSNDialog.this.onBackPressed();
            }
        });
        this.nexonLoginView.setLoginButtonEnabled(false);
        if (this.useTPA) {
            this.nexonLoginView.setTPALoginVisibility(0);
            this.nexonLoginView.setLoginTPAButtonClickListener(new NXPNexonLoginView.NXPTAPButtonClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.7
                @Override // kr.co.nexon.android.sns.nxcom.ui.view.NXPNexonLoginView.NXPTAPButtonClickListener
                public void onClick(int i) {
                    ToyLog.d("tpaType : " + i);
                    if (NPGetNexonSNDialog.this.useNative) {
                        NPGetNexonSNDialog.this.getNexonSNFromNative(i);
                        return;
                    }
                    NPAuthPlugin provider = NXPProviderManager.getInstance().getProvider(i);
                    if (provider != null) {
                        NPGetNexonSNDialog.this.getNexonSNFromWeb(provider.getServiceName());
                    }
                }
            });
        }
        return this.nexonLoginView;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase, android.app.DialogFragment
    public void dismiss() {
        if (this.nexonLoginView != null) {
            this.nexonLoginView.setIdText("");
            this.nexonLoginView.clearPasswordText();
            this.nexonLoginView = null;
        }
        super.dismiss();
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (isShowingProgressDialog()) {
            return;
        }
        if (this.resultListener != null) {
            this.resultListener.onFailure(NXToyErrorCode.LOGIN_USER_CANCELED.getCode(), "user canceled");
        }
        super.onBackPressed();
    }

    public void setResultListener(NXPGetNexonSNListener nXPGetNexonSNListener) {
        this.resultListener = nXPGetNexonSNListener;
    }
}
