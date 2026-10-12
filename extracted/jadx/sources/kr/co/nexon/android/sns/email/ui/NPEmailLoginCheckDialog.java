package kr.co.nexon.android.sns.email.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.email.ui.view.NXPEmailLoginCheckView;
import kr.co.nexon.android.sns.email.util.NPLocalizedStringWrapper;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.auth.request.NXToyIsRegisteredNPAARequest;
import kr.co.nexon.npaccount.auth.result.NXToyCheckEmailAccountRegisteredResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPEmailLoginCheckDialog extends NPDialogBase implements TextView.OnEditorActionListener {
    public static final String KEY_SESSION = "session";
    public static final String TAG = "NPEmailLoginCheckDialog";
    private String emailId;
    private NXPEmailLoginCheckView emailLoginCheckView;
    private NXPEmailLoginCheckListener resultListener;
    private NXToySession session;
    private NPLocalizedStringWrapper stringWrapper;

    public interface NXPEmailLoginCheckListener {
        void onComplete(int i, String str, boolean z, String str2);
    }

    public static NPEmailLoginCheckDialog newInstance(Activity activity, String str) {
        NPEmailLoginCheckDialog nPEmailLoginCheckDialog = new NPEmailLoginCheckDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("session", str);
        nPEmailLoginCheckDialog.setArguments(bundle);
        return nPEmailLoginCheckDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onContinue() {
        final String passwordText = this.emailLoginCheckView.getPasswordText();
        if (!(passwordText.length() > 50 ? false : NXStringUtil.isValidEmail(passwordText))) {
            this.emailLoginCheckView.setErrorMessage(this.stringWrapper.getString(R.string.np_email_login_check_email_format_warning_msg));
        } else {
            this.progressDialog.show();
            NXToyRequestPostman.getInstance().postRequest(new NXToyIsRegisteredNPAARequest(passwordText), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginCheckDialog.4
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    NPEmailLoginCheckDialog.this.progressDialog.dismiss();
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        NPEmailLoginCheckDialog.this.showToastMessage(nXToyResult.errorText);
                        return;
                    }
                    NXToyCheckEmailAccountRegisteredResult nXToyCheckEmailAccountRegisteredResult = (NXToyCheckEmailAccountRegisteredResult) nXToyResult;
                    NPEmailLoginCheckDialog.this.resultListener.onComplete(nXToyCheckEmailAccountRegisteredResult.errorCode, nXToyCheckEmailAccountRegisteredResult.errorText, nXToyCheckEmailAccountRegisteredResult.result.isRegistered == 1, passwordText);
                    NPEmailLoginCheckDialog.this.dismiss();
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
    public void showToastMessage(final String str) {
        getActivity().runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginCheckDialog.5
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(NPEmailLoginCheckDialog.this.applicationContext, str, 1).show();
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.emailLoginCheckView = (NXPEmailLoginCheckView) View.inflate(this.applicationContext, R.layout.nxp_email_login_check_view, null);
        setSession(getArguments().getString("session"));
        this.stringWrapper = new NPLocalizedStringWrapper(this.applicationContext);
        this.emailLoginCheckView.setBackButtonInvisible();
        this.emailLoginCheckView.setTitle(this.stringWrapper.getString(R.string.nplogin_type_email_btn));
        this.emailLoginCheckView.setEditListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginCheckDialog.1
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() > 0) {
                    NPEmailLoginCheckDialog.this.emailLoginCheckView.setContinueButtonEnabled(true);
                } else {
                    NPEmailLoginCheckDialog.this.emailLoginCheckView.setErrorMessage("");
                    NPEmailLoginCheckDialog.this.emailLoginCheckView.setContinueButtonEnabled(false);
                }
            }
        });
        this.emailLoginCheckView.setEditorActionListener(this);
        this.emailLoginCheckView.setEditHint(this.stringWrapper.getString(R.string.np_email_login_email_input_hint));
        this.emailLoginCheckView.setContinueButtonEnabled(false);
        if (NXStringUtil.isNotNull(this.emailId)) {
            this.emailLoginCheckView.setIdText(this.emailId);
            this.emailLoginCheckView.setContinueButtonEnabled(true);
        }
        this.emailLoginCheckView.setContinueButton(this.stringWrapper.getString(R.string.np_btn_continue), new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginCheckDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPEmailLoginCheckDialog.this.onContinue();
            }
        });
        this.emailLoginCheckView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailLoginCheckDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPEmailLoginCheckDialog.this.onCloseBtnClick(view);
            }
        });
        return this.emailLoginCheckView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (this.resultListener != null) {
            new Bundle().putString("toyresult", NXJsonUtil.toJsonString(new NXToyResult(NPAuthPlugin.CODE_USER_CANCEL, "user canceled", "")));
            this.resultListener.onComplete(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", false, "");
        }
        super.onBackPressed();
    }

    public void onCloseBtnClick(View view) {
        onBackPressed();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        onContinue();
        return true;
    }

    public void setEmailId(String str) {
        this.emailId = str;
    }

    public void setResultListener(NXPEmailLoginCheckListener nXPEmailLoginCheckListener) {
        this.resultListener = nXPEmailLoginCheckListener;
    }
}
