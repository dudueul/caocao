package kr.co.nexon.android.sns.email.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.email.NPEmail;
import kr.co.nexon.android.sns.email.NPEmailListener;
import kr.co.nexon.android.sns.email.ui.view.NXPEmailLoginResetPasswordSuccessView;
import kr.co.nexon.android.sns.email.util.NPLocalizedStringWrapper;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.NPDialogBase;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPEmailResetPasswordSuccessDialog extends NPDialogBase {
    public static final String KEY_EMAIL_ID = "emailId";
    public static final String KEY_SESSION = "session";
    public static final String TAG = "NPEmailResetPasswordSuccessDialog";
    private String emailId;
    private NPEmailListener resultListener;
    private String session;

    public static NPEmailResetPasswordSuccessDialog newInstance(Activity activity, String str, String str2) {
        NPEmailResetPasswordSuccessDialog nPEmailResetPasswordSuccessDialog = new NPEmailResetPasswordSuccessDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("emailId", str);
        bundle.putString("session", str2);
        nPEmailResetPasswordSuccessDialog.setArguments(bundle);
        return nPEmailResetPasswordSuccessDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPEmailLoginResetPasswordSuccessView nXPEmailLoginResetPasswordSuccessView = (NXPEmailLoginResetPasswordSuccessView) View.inflate(this.applicationContext, R.layout.nxp_email_login_reset_passwd_success_view, null);
        this.emailId = getArguments().getString("emailId");
        this.session = getArguments().getString("session");
        NPLocalizedStringWrapper nPLocalizedStringWrapper = new NPLocalizedStringWrapper(this.applicationContext);
        nXPEmailLoginResetPasswordSuccessView.setTitle(nPLocalizedStringWrapper.getString(R.string.np_email_reset_password_title));
        nXPEmailLoginResetPasswordSuccessView.setMainMessage(nPLocalizedStringWrapper.getString(R.string.np_email_reset_success_msg));
        nXPEmailLoginResetPasswordSuccessView.setSubMessage(String.format(nPLocalizedStringWrapper.getString(R.string.np_email_reset_success_sub_msg), this.emailId));
        nXPEmailLoginResetPasswordSuccessView.setGoBackButton(nPLocalizedStringWrapper.getString(R.string.np_btn_go_back), new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailResetPasswordSuccessDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                if (NPEmailResetPasswordSuccessDialog.this.resultListener != null) {
                    NPEmailResetPasswordSuccessDialog.this.resultListener.onComplete(NPEmail.CODE_BACK_CLICK, "", null);
                }
                NPEmailResetPasswordSuccessDialog.this.dismiss();
            }
        });
        nXPEmailLoginResetPasswordSuccessView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailResetPasswordSuccessDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPEmailResetPasswordSuccessDialog.this.onCloseBtnClick(view);
            }
        });
        nXPEmailLoginResetPasswordSuccessView.setOnBackButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailResetPasswordSuccessDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPEmailResetPasswordSuccessDialog.this.onBackBtnClick(view);
            }
        });
        return nXPEmailLoginResetPasswordSuccessView;
    }

    public void onBackBtnClick(View view) {
        onBackPressed();
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        NPEmailResetPasswordDialog nPEmailResetPasswordDialogNewInstance = NPEmailResetPasswordDialog.newInstance(getActivity(), this.emailId, this.session);
        nPEmailResetPasswordDialogNewInstance.setResultListener(this.resultListener);
        nPEmailResetPasswordDialogNewInstance.showDialog(getActivity(), TAG);
        dismiss();
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
