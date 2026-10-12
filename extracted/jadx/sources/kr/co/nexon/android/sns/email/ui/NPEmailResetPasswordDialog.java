package kr.co.nexon.android.sns.email.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.email.NPEmail;
import kr.co.nexon.android.sns.email.NPEmailListener;
import kr.co.nexon.android.sns.email.ui.view.NXPEmailLoginResetPasswordView;
import kr.co.nexon.android.sns.email.util.NPLocalizedStringWrapper;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.auth.request.NXToyRequestResetPasswordNPAARequest;
import kr.co.nexon.toy.android.ui.NPDialogBase;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPEmailResetPasswordDialog extends NPDialogBase {
    public static final String KEY_EMAIL_ID = "emailId";
    public static final String KEY_SESSION = "session";
    public static final String TAG = "NPEmailResetPasswordDialog";
    private String emailId;
    private NPEmailListener resultListener;
    private NXToySession session;
    private NPLocalizedStringWrapper stringWrapper;

    /* JADX INFO: Access modifiers changed from: private */
    public void moveToSuccessPage(String str) {
        NPEmailResetPasswordSuccessDialog nPEmailResetPasswordSuccessDialogNewInstance = NPEmailResetPasswordSuccessDialog.newInstance(getActivity(), str, new Gson().toJson(this.session, NXToySession.class));
        nPEmailResetPasswordSuccessDialogNewInstance.setResultListener(this.resultListener);
        nPEmailResetPasswordSuccessDialogNewInstance.showDialog(getActivity(), NPEmailResetPasswordSuccessDialog.TAG);
        dismiss();
    }

    public static NPEmailResetPasswordDialog newInstance(Activity activity, String str, String str2) {
        NPEmailResetPasswordDialog nPEmailResetPasswordDialog = new NPEmailResetPasswordDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("emailId", str);
        bundle.putString("session", str2);
        nPEmailResetPasswordDialog.setArguments(bundle);
        return nPEmailResetPasswordDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResetPassword() {
        this.progressDialog.show();
        NXToyRequestPostman.getInstance().postRequest(new NXToyRequestResetPasswordNPAARequest(this.emailId), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailResetPasswordDialog.4
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                NPEmailResetPasswordDialog.this.progressDialog.dismiss();
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NPEmailResetPasswordDialog.this.moveToSuccessPage(NPEmailResetPasswordDialog.this.emailId);
                } else {
                    NPEmailResetPasswordDialog.this.showToastMessage(nXToyResult.errorText);
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
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailResetPasswordDialog.5
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(NPEmailResetPasswordDialog.this.applicationContext, str, 1).show();
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPEmailLoginResetPasswordView nXPEmailLoginResetPasswordView = (NXPEmailLoginResetPasswordView) View.inflate(this.applicationContext, R.layout.nxp_email_login_reset_passwd_view, null);
        setSession(getArguments().getString("session"));
        this.emailId = getArguments().getString("emailId");
        this.stringWrapper = new NPLocalizedStringWrapper(this.applicationContext);
        nXPEmailLoginResetPasswordView.setTitle(this.stringWrapper.getString(R.string.np_email_reset_password_title));
        nXPEmailLoginResetPasswordView.setResetPasswordButtonEnabled(false);
        if (NXStringUtil.isNotNull(this.emailId)) {
            nXPEmailLoginResetPasswordView.setEmail(this.emailId);
            nXPEmailLoginResetPasswordView.setResetPasswordButtonEnabled(true);
        }
        nXPEmailLoginResetPasswordView.setResetPasswordButton(this.stringWrapper.getString(R.string.np_btn_reset_password), new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailResetPasswordDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPEmailResetPasswordDialog.this.onResetPassword();
            }
        });
        nXPEmailLoginResetPasswordView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailResetPasswordDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPEmailResetPasswordDialog.this.onCloseBtnClick(view);
            }
        });
        nXPEmailLoginResetPasswordView.setOnBackButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailResetPasswordDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPEmailResetPasswordDialog.this.onBackBtnClick(view);
            }
        });
        return nXPEmailLoginResetPasswordView;
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
