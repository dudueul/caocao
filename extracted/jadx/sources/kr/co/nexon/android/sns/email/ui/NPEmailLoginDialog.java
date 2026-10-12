package kr.co.nexon.android.sns.email.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.HashMap;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.email.NPEmail;
import kr.co.nexon.android.sns.email.NPEmailListener;
import kr.co.nexon.android.sns.email.ui.view.NXPEmailLoginView;
import kr.co.nexon.android.sns.email.util.NPLocalizedStringWrapper;
import kr.co.nexon.mdev.android.util.NXDeviceUtil;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.auth.request.NXToyGetNPSNRequest;
import kr.co.nexon.npaccount.auth.request.NXToySignInRequest;
import kr.co.nexon.npaccount.auth.request.model.NXToySignInRequestOptionalBody;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;
import kr.co.nexon.npaccount.auth.result.NXToyNpsnResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.NPUserInfoDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPEmailLoginDialog extends NPDialogBase {
    public static final String KEY_EMAIL_ID = "emailId";
    public static final String KEY_LOGIN_MODE = "mode";
    public static final String KEY_SESSION = "session";
    public static final String TAG = "NPEmailLoginDialog";
    private String emailId;
    private NXPEmailLoginView emailLoginView;
    private NPEmailListener resultListener;
    private NXToySession session;
    private NPLocalizedStringWrapper stringWrapper;

    /* JADX INFO: renamed from: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog$12, reason: invalid class name */
    static /* synthetic */ class AnonymousClass12 {
        static final /* synthetic */ int[] $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType;

        static {
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.WITHDRAWAL_PROCESSING_BY_THE_USER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.USING_NPSN_USER_NEED_RESOLVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.USING_NPSN_USER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.EMAIL_LOGIN_PASSWORD_INVALID.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.NPSN_NOT_FOUND.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType = new int[NXToyLoginType.values().length];
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeEmail.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeNXOneId.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeNXCom.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public static NPEmailLoginDialog newInstance(Activity activity, int i, String str, String str2) {
        NPEmailLoginDialog nPEmailLoginDialog = new NPEmailLoginDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putInt("mode", i);
        bundle.putString("emailId", str);
        bundle.putString("session", str2);
        nPEmailLoginDialog.setArguments(bundle);
        return nPEmailLoginDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickLoginButton(final String str, final String str2) {
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.7
            @Override // java.lang.Runnable
            public void run() {
                if (NPEmailLoginDialog.this.getArguments().getInt("mode") == 0) {
                    NPEmailLoginDialog.this.onLogin(str, str2);
                } else {
                    NPEmailLoginDialog.this.queryNpsn(str, str2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLogin(final String str, final String str2) {
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        String uuid = nXToyCommonPreferenceController.getUUID();
        String uuid2 = nXToyCommonPreferenceController.getUUID2();
        if (NXStringUtil.isNull(uuid) || NXStringUtil.isNull(uuid2)) {
            showToastMessage("UUID is null");
            return;
        }
        String strValueOf = this.session.getType() == NXToyLoginType.LoginTypeGuest.getValue() ? String.valueOf(this.session.getNpsn()) : "0";
        showProgressDialog();
        NXToyRequestPostman.getInstance().postRequest(new NXToySignInRequest(strValueOf, str, str2, uuid, uuid2, NXToyLoginType.LoginTypeEmail.getValue(), new NXToySignInRequestOptionalBody(getActivity(), str, null)), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.8
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                NPEmailLoginDialog.this.dismissProgressDialog();
                NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) nXToyResult;
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToyLoginResult.ResultSet resultSet = nXToyLoginResult.result;
                    NPEmailLoginDialog.this.session.setNpsn(resultSet.npSN);
                    NPEmailLoginDialog.this.session.setNPToken(resultSet.npToken);
                    NPEmailLoginDialog.this.session.setUMKey(resultSet.umKey);
                    NPEmailLoginDialog.this.session.setType(NXToyLoginType.LoginTypeEmail.getValue());
                    NPEmailLoginDialog.this.session.setEmailId(str);
                    int i = AnonymousClass12.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.convertIntLoginTypeToEnumLoginType(nXToyLoginResult.result.loginResultType).ordinal()];
                }
                NXToyErrorCode nXToyErrorCodeConvertIntErrorCodeToEnumErrorCode = NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyResult.errorCode);
                switch (nXToyErrorCodeConvertIntErrorCodeToEnumErrorCode) {
                    case WITHDRAWAL_PROCESSING_BY_THE_USER:
                    case USING_NPSN_USER_NEED_RESOLVE:
                        HashMap map = new HashMap();
                        map.put("id", str);
                        map.put("pw", str2);
                        map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(NXToyLoginType.LoginTypeEmail.getValue()));
                        map.put("email", str);
                        NPEmailLoginDialog.this.session.setTempUserLoginInfo(map);
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
                switch (nXToyErrorCodeConvertIntErrorCodeToEnumErrorCode) {
                    case WITHDRAWAL_PROCESSING_BY_THE_USER:
                    case USING_NPSN_USER_NEED_RESOLVE:
                    case SUCCESS:
                        bundle.putString("toysession", new Gson().toJson(NPEmailLoginDialog.this.session, NXToySession.class));
                        if (NPEmailLoginDialog.this.resultListener != null) {
                            NPEmailLoginDialog.this.resultListener.onComplete(nXToyResult.errorCode, nXToyResult.errorText, bundle);
                        }
                        NPEmailLoginDialog.this.dismiss();
                        break;
                    case USING_NPSN_USER:
                        if (NPEmailLoginDialog.this.resultListener != null) {
                            NPEmailLoginDialog.this.resultListener.onComplete(nXToyResult.errorCode, nXToyResult.errorText, bundle);
                        }
                        NPEmailLoginDialog.this.dismiss();
                        break;
                    case EMAIL_LOGIN_PASSWORD_INVALID:
                        NPEmailLoginDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.8.1
                            @Override // java.lang.Runnable
                            public void run() {
                                NPEmailLoginDialog.this.emailLoginView.setErrorMessage(NPEmailLoginDialog.this.stringWrapper.getString(R.string.np_email_login_check_password_msg));
                            }
                        });
                        break;
                    default:
                        NPEmailLoginDialog.this.showToastMessage(nXToyResult.errorText);
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResetPassword(final String str) {
        final String json = new Gson().toJson(this.session);
        final Activity activity = getActivity();
        NPEmailResetPasswordDialog nPEmailResetPasswordDialogNewInstance = NPEmailResetPasswordDialog.newInstance(getActivity(), str, json);
        nPEmailResetPasswordDialogNewInstance.setResultListener(new NPEmailListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.10
            @Override // kr.co.nexon.android.sns.email.NPEmailListener
            public void onComplete(int i, String str2, Bundle bundle) {
                if (i == 90204) {
                    NPEmailLoginDialog nPEmailLoginDialogNewInstance = NPEmailLoginDialog.newInstance(activity, NPEmailLoginDialog.this.getArguments().getInt("mode"), str, json);
                    nPEmailLoginDialogNewInstance.setResultListener(NPEmailLoginDialog.this.resultListener);
                    nPEmailLoginDialogNewInstance.showDialog(activity, NPEmailLoginDialog.TAG);
                    return;
                }
                if (bundle == null) {
                    NXToyResult nXToyResult = new NXToyResult(i, str2, str2, NXToyLoginType.LoginTypeEmail.getValue());
                    bundle = new Bundle();
                    bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
                }
                if (NPEmailLoginDialog.this.resultListener != null) {
                    NPEmailLoginDialog.this.resultListener.onComplete(i, str2, bundle);
                }
            }
        });
        nPEmailResetPasswordDialogNewInstance.showDialog(activity, NPEmailResetPasswordDialog.TAG);
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryNpsn(final String str, final String str2) {
        showProgressDialog();
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetNPSNRequest(str, str2, NXToyLoginType.LoginTypeEmail.getValue()), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.9
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                NPEmailLoginDialog.this.dismissProgressDialog();
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    HashMap map = new HashMap();
                    map.put("id", str);
                    map.put("pw", str2);
                    map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(NXToyLoginType.LoginTypeEmail.getValue()));
                    NPEmailLoginDialog.this.session.setTempUserLoginInfoForNpsn(((NXToyNpsnResult) nXToyResult).result.npsn, map);
                }
                Bundle bundle = new Bundle();
                bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
                switch (NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyResult.errorCode)) {
                    case SUCCESS:
                        bundle.putString("toysession", new Gson().toJson(NPEmailLoginDialog.this.session, NXToySession.class));
                        if (NPEmailLoginDialog.this.resultListener != null) {
                            NPEmailLoginDialog.this.resultListener.onComplete(nXToyResult.errorCode, nXToyResult.errorText, bundle);
                        }
                        NPEmailLoginDialog.this.dismiss();
                        break;
                    case USING_NPSN_USER:
                    default:
                        NPEmailLoginDialog.this.showToastMessage(nXToyResult.errorText);
                        break;
                    case EMAIL_LOGIN_PASSWORD_INVALID:
                        NPEmailLoginDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.9.1
                            @Override // java.lang.Runnable
                            public void run() {
                                NPEmailLoginDialog.this.emailLoginView.setErrorMessage(NPEmailLoginDialog.this.stringWrapper.getString(R.string.np_email_login_check_password_msg));
                            }
                        });
                        break;
                    case NPSN_NOT_FOUND:
                        if (NPEmailLoginDialog.this.resultListener != null) {
                            NPEmailLoginDialog.this.resultListener.onComplete(nXToyResult.errorCode, nXToyResult.errorText, bundle);
                        }
                        NPEmailLoginDialog.this.dismiss();
                        break;
                }
            }
        });
    }

    private void setSession(String str) {
        if (NXStringUtil.isNotNull(str)) {
            this.session = (NXToySession) NXJsonUtil.fromObject(str, NXToySession.class);
        } else {
            this.session = new NXToySession();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastMessage(final String str) {
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.11
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(NPEmailLoginDialog.this.applicationContext, str, 1).show();
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.emailLoginView = (NXPEmailLoginView) View.inflate(this.applicationContext, R.layout.nxp_email_login_view, null);
        setSession(getArguments().getString("session"));
        this.emailId = getArguments().getString("emailId");
        this.stringWrapper = new NPLocalizedStringWrapper(this.applicationContext);
        this.emailLoginView.setTitle(this.stringWrapper.getString(R.string.np_email_login_title));
        this.emailLoginView.setIdText(this.emailId);
        this.emailLoginView.setLoginButtonEnabled(false);
        this.emailLoginView.setEditHint(this.stringWrapper.getString(R.string.np_email_login_password_input_hint));
        this.emailLoginView.setPasswordTextChangeListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.1
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                NPEmailLoginDialog.this.emailLoginView.setErrorMessage("");
                if (charSequence.length() > 0) {
                    NPEmailLoginDialog.this.emailLoginView.setLoginButtonEnabled(true);
                } else {
                    NPEmailLoginDialog.this.emailLoginView.setLoginButtonEnabled(false);
                }
            }
        });
        this.emailLoginView.setEditorActionListener(new TextView.OnEditorActionListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                Context context = textView.getContext();
                if (!NXDeviceUtil.isAvailableNetwork(context)) {
                    Toast.makeText(context, NPEmailLoginDialog.this.stringWrapper.getString(R.string.npres_check_network), 0).show();
                    return true;
                }
                String passwordText = NPEmailLoginDialog.this.emailLoginView.getPasswordText();
                NPEmailLoginDialog.this.emailLoginView.setPasswordText("");
                NPEmailLoginDialog.this.onClickLoginButton(NPEmailLoginDialog.this.emailId, passwordText);
                return true;
            }
        });
        this.emailLoginView.setForgotButton(this.stringWrapper.getString(R.string.np_btn_reset_password), new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPEmailLoginDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NPEmailLoginDialog.this.onResetPassword(NPEmailLoginDialog.this.emailId);
                    }
                });
            }
        });
        this.emailLoginView.setLoginButton(this.stringWrapper.getString(R.string.np_btn_login), new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.4
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                Context context = view.getContext();
                if (!NXDeviceUtil.isAvailableNetwork(context)) {
                    Toast.makeText(context, NPEmailLoginDialog.this.stringWrapper.getString(R.string.npres_check_network), 0).show();
                    return;
                }
                String passwordText = NPEmailLoginDialog.this.emailLoginView.getPasswordText();
                NPEmailLoginDialog.this.emailLoginView.setPasswordText("");
                NPEmailLoginDialog.this.onClickLoginButton(NPEmailLoginDialog.this.emailId, passwordText);
            }
        });
        this.emailLoginView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.5
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPEmailLoginDialog.this.onCloseBtnClick(view);
            }
        });
        this.emailLoginView.setOnBackButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog.6
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPEmailLoginDialog.this.onBackBtnClick(view);
            }
        });
        return this.emailLoginView;
    }

    public void onBackBtnClick(View view) {
        onBackPressed();
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (isShowingProgressDialog()) {
            return;
        }
        if (this.resultListener != null) {
            this.resultListener.onComplete(NPEmail.CODE_BACK_CLICK, "user cancel(onClickBackButton)", null);
        }
        super.onBackPressed();
    }

    public void onCloseBtnClick(View view) {
        if (this.resultListener != null) {
            Bundle bundle = new Bundle();
            NXToyResult nXToyResult = new NXToyResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", "user cancel");
            nXToyResult.requestTag = NXToyLoginType.LoginTypeEmail.getValue();
            bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
            this.resultListener.onComplete(nXToyResult.errorCode, nXToyResult.errorText, bundle);
        }
        dismiss();
    }

    public void setResultListener(NPEmailListener nPEmailListener) {
        this.resultListener = nPEmailListener;
    }
}
