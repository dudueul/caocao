package kr.co.nexon.android.sns.email.ui;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.nexon.core.android.NXPApplicationConfigInterface;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.locale.NXLocale;
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
import kr.co.nexon.android.sns.email.ui.view.NXPEmailLoginSignUpView;
import kr.co.nexon.android.sns.email.util.NPLocalizedStringWrapper;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.auth.request.NXToySignUpNPAARequest;
import kr.co.nexon.npaccount.auth.request.model.NXToySignInRequestOptionalBody;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;
import kr.co.nexon.npaccount.board.NXBoardManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.NPUserInfoDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPEmailSignUpDialog extends NPDialogBase {
    public static final String KEY_EMAIL_ID = "emailId";
    public static final String KEY_SESSION = "session";
    public static final String TAG = "NPEmailSignUpDialog";
    private String emailId;
    private NXPEmailLoginSignUpView emailLoginSignUpView;
    private NPEmailListener resultListener;
    private NXToySession session;
    private NPLocalizedStringWrapper stringWrapper;

    /* JADX INFO: renamed from: kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode = new int[NXToyErrorCode.values().length];

        static {
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.WITHDRAWAL_PROCESSING_BY_THE_USER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.USING_NPSN_USER_NEED_RESOLVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static NPEmailSignUpDialog newInstance(Activity activity, String str, String str2) {
        NPEmailSignUpDialog nPEmailSignUpDialog = new NPEmailSignUpDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("emailId", str);
        bundle.putString("session", str2);
        nPEmailSignUpDialog.setArguments(bundle);
        return nPEmailSignUpDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSignUp() {
        final String passwordText = this.emailLoginSignUpView.getPasswordText();
        if (passwordText.length() <= 5) {
            this.emailLoginSignUpView.setErrorMessage(this.stringWrapper.getString(R.string.np_email_login_password_length_warning_msg));
            return;
        }
        final String string = getArguments().getString("emailId");
        this.emailLoginSignUpView.setErrorMessage("");
        this.progressDialog.show();
        if (string == null || passwordText == null) {
            showToastMessage("ID or password error");
        } else {
            NXToyRequestPostman.getInstance().postRequest(new NXToySignUpNPAARequest(string, passwordText, new NXToySignInRequestOptionalBody(getActivity(), string, null)), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog.7
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                        NXToyLoginResult.ResultSet resultSet = ((NXToyLoginResult) nXToyResult).result;
                        NPEmailSignUpDialog.this.session.setNpsn(resultSet.npSN);
                        NPEmailSignUpDialog.this.session.setNPToken(resultSet.npToken);
                        NPEmailSignUpDialog.this.session.setUMKey(resultSet.umKey);
                        NPEmailSignUpDialog.this.session.setType(NXToyLoginType.LoginTypeEmail.getValue());
                        NPEmailSignUpDialog.this.session.setAccountId(string);
                        NPEmailSignUpDialog.this.session.setEmailId(string);
                    }
                    switch (AnonymousClass9.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyResult.errorCode).ordinal()]) {
                        case 1:
                        case 2:
                            HashMap map = new HashMap();
                            map.put("id", string);
                            map.put("pw", passwordText);
                            map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(NXToyLoginType.LoginTypeEmail.getValue()));
                            map.put("email", string);
                            NPEmailSignUpDialog.this.session.setTempUserLoginInfo(map);
                            break;
                    }
                    NPEmailSignUpDialog.this.progressDialog.dismiss();
                    Bundle bundle = new Bundle();
                    int i = nXToyResult.errorCode;
                    String str = nXToyResult.errorText;
                    if (i == NXToyErrorCode.SUCCESS.getCode()) {
                        bundle.putString("toysession", NXJsonUtil.toJsonString(NPEmailSignUpDialog.this.session));
                        bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
                        NPEmailSignUpDialog.this.resultListener.onComplete(i, str, bundle);
                        NPEmailSignUpDialog.this.dismiss();
                        return;
                    }
                    if (i != NXToyErrorCode.WITHDRAWAL_PROCESSING_BY_THE_USER.getCode() && i != NXToyErrorCode.USING_NPSN_USER_NEED_RESOLVE.getCode() && i != NXToyErrorCode.USING_NPSN_USER.getCode()) {
                        NPEmailSignUpDialog.this.showToastMessage(nXToyResult.errorText);
                        return;
                    }
                    bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
                    NPEmailSignUpDialog.this.resultListener.onComplete(nXToyResult.errorCode, nXToyResult.errorText, bundle);
                    NPEmailSignUpDialog.this.dismiss();
                }
            });
        }
    }

    private void setSession(String str) {
        if (NXStringUtil.isNotNull(str)) {
            this.session = (NXToySession) NXJsonUtil.fromObject(str, NXToySession.class);
        } else {
            this.session = new NXToySession();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTerms() {
        String string = null;
        try {
            string = getActivity().getPackageManager().getApplicationInfo(getActivity().getPackageName(), 128).metaData.getString("NPUILoginEmailSignupTermsCode");
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (NXStringUtil.isNull(string)) {
            string = NXLocale.getLocaleCode(NXToyCommonPreferenceController.getInstance().getLocale()) == NXLocale.LOCALE.JA_JP ? "2" : NXBoardManager.BOARD_HELPCENTER;
        }
        NPEmailWebDialog.newInstance(getActivity(), NXPApplicationConfigManager.getInstance().getServerURL(NXPApplicationConfigInterface.ServerType.Page).getUrl() + "/term/" + string).showDialog(getActivity(), NPEmailWebDialog.TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastMessage(final String str) {
        getActivity().runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog.8
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(NPEmailSignUpDialog.this.applicationContext, str, 1).show();
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.emailLoginSignUpView = (NXPEmailLoginSignUpView) View.inflate(this.applicationContext, R.layout.nxp_email_login_signup_npemail_signup_view, null);
        setSession(getArguments().getString("session"));
        this.emailId = getArguments().getString("emailId");
        this.stringWrapper = new NPLocalizedStringWrapper(this.applicationContext);
        this.emailLoginSignUpView.setTitle(this.stringWrapper.getString(R.string.np_email_sign_up_title));
        this.emailLoginSignUpView.setDescriptionText(this.stringWrapper.getString(R.string.np_email_login_create_desc_msg));
        this.emailLoginSignUpView.setIdText(this.emailId);
        this.emailLoginSignUpView.setLoginButtonEnabled(false);
        this.emailLoginSignUpView.setEditHint(this.stringWrapper.getString(R.string.np_email_login_create_password_input_hint));
        this.emailLoginSignUpView.setEditListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog.1
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() > 0) {
                    NPEmailSignUpDialog.this.emailLoginSignUpView.setLoginButtonEnabled(true);
                } else {
                    NPEmailSignUpDialog.this.emailLoginSignUpView.setErrorMessage("");
                    NPEmailSignUpDialog.this.emailLoginSignUpView.setLoginButtonEnabled(false);
                }
            }
        });
        this.emailLoginSignUpView.setEditorActionListener(new TextView.OnEditorActionListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                NPEmailSignUpDialog.this.onSignUp();
                return true;
            }
        });
        this.emailLoginSignUpView.setTermsPolicyTextView(this.stringWrapper.getString(R.string.np_login_terms_and_policy), new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPEmailSignUpDialog.this.showTerms();
            }
        });
        this.emailLoginSignUpView.setSignUpButton(this.stringWrapper.getString(R.string.np_btn_signup), new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog.4
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPEmailSignUpDialog.this.onSignUp();
            }
        });
        this.emailLoginSignUpView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog.5
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPEmailSignUpDialog.this.onCloseBtnClick(view);
            }
        });
        this.emailLoginSignUpView.setOnBackButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog.6
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPEmailSignUpDialog.this.onBackBtnClick(view);
            }
        });
        return this.emailLoginSignUpView;
    }

    public void onBackBtnClick(View view) {
        onBackPressed();
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (this.progressDialog.isShowing()) {
            return;
        }
        this.resultListener.onComplete(NPEmail.CODE_BACK_CLICK, "user cancel(onClickBackButton)", null);
        super.onBackPressed();
    }

    public void onCloseBtnClick(View view) {
        Bundle bundle = new Bundle();
        NXToyResult nXToyResult = new NXToyResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", "user cancel");
        nXToyResult.requestTag = NXToyLoginType.LoginTypeEmail.getValue();
        bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
        this.resultListener.onComplete(nXToyResult.errorCode, nXToyResult.errorText, bundle);
        dismiss();
    }

    public void setResultListener(NPEmailListener nPEmailListener) {
        this.resultListener = nPEmailListener;
    }
}
