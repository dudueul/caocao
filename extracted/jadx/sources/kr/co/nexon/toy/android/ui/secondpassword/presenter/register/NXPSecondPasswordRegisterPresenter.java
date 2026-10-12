package kr.co.nexon.toy.android.ui.secondpassword.presenter.register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
abstract class NXPSecondPasswordRegisterPresenter implements NXPSecondPasswordRegisterContract.Presenter {
    public Activity activity;
    public NXPActionListener finishListener;
    public NXPSecondPasswordRegisterContract.View registerView;
    public NXPSecondPasswordManager secondPasswordManager = NXPSecondPasswordManager.getInstance();
    public NXPSecondPasswordState status = this.secondPasswordManager.getStatus();
    public NXToyLocaleManager localeManager = NXToyLocaleManager.getInstance();

    public NXPSecondPasswordRegisterPresenter(Activity activity) {
        this.activity = activity;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.Presenter
    public String getEmailId() {
        String emailId = this.secondPasswordManager.getEmailId();
        if (NXStringUtil.isNull(emailId)) {
            emailId = NXToySessionManager.getInstance().getSession().getEmailId();
        }
        return NXStringUtil.isNull(emailId) ? "" : emailId;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.Presenter
    public NXPSecondPasswordState getStatus() {
        return this.status;
    }

    protected boolean isInvalidEmailId(String str) {
        if (NXStringUtil.isValidEmail(str)) {
            return false;
        }
        this.registerView.setEmailInvalidErrorMessage(this.localeManager.getString(R.string.npres_second_password_register_view_error_msg_email_invaild));
        return true;
    }

    protected boolean isInvalidPassword(String str, String str2) {
        if (str.equals(str2)) {
            return false;
        }
        this.registerView.setPasswordInvalidErrorMessage(this.localeManager.getString(R.string.npres_second_password_register_view_error_msg_password_invalid));
        return true;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onFail(NXToyResult nXToyResult) {
        this.finishListener.onFail(nXToyResult);
        this.registerView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onSuccess(NXToyResult nXToyResult) {
        this.finishListener.onSuccess(nXToyResult);
        this.registerView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setActionListener(NXPActionListener nXPActionListener) {
        this.finishListener = nXPActionListener;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setView(NXPSecondPasswordRegisterContract.View view) {
        this.registerView = view;
        this.registerView.setPresenter(this);
        this.registerView.setEmailId(getEmailId());
        this.registerView.setEmailInputEditTextHint(this.localeManager.getString(R.string.npres_second_password_register_view_input_email_hint));
        this.registerView.setPasswordInputEditTextHint(this.localeManager.getString(R.string.npres_second_password_register_view_input_password_hint));
        this.registerView.setConfirmPasswordInputEditTextHint(this.localeManager.getString(R.string.npres_second_password_register_view_input_confirm_password_hint));
        this.registerView.setConfirmButtonText(this.localeManager.getString(R.string.npres_second_password_register_view_register_button_text));
    }

    protected void showConfirmMessage(final String str, final View.OnClickListener onClickListener) {
        this.activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter.2
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(NXPSecondPasswordRegisterPresenter.this.activity);
                View viewInflate = NXPSecondPasswordRegisterPresenter.this.activity.getLayoutInflater().inflate(R.layout.nxp_second_password_confirm_register_dialog, (ViewGroup) null);
                Button button = (Button) viewInflate.findViewById(R.id.nxp_second_password_register_confirm_confirm_button);
                Button button2 = (Button) viewInflate.findViewById(R.id.nxp_second_password_register_confirm_reset_button);
                TextView textView = (TextView) viewInflate.findViewById(R.id.nxp_second_password_register_confirm_title_text);
                TextView textView2 = (TextView) viewInflate.findViewById(R.id.nxp_second_password_register_confirm_description_text);
                TextView textView3 = (TextView) viewInflate.findViewById(R.id.nxp_second_password_register_confirm_email_id);
                textView.setText(NXPSecondPasswordRegisterPresenter.this.localeManager.getString(R.string.npres_second_password_register_confirm_dialog_title_text));
                textView2.setText(NXPSecondPasswordRegisterPresenter.this.localeManager.getString(R.string.npres_second_password_register_confirm_dialog_description));
                textView3.setText(str);
                button.setText(NXPSecondPasswordRegisterPresenter.this.localeManager.getString(R.string.confirm));
                button2.setText(NXPSecondPasswordRegisterPresenter.this.localeManager.getString(R.string.npres_second_password_register_confirm_dialog_reset_button_text));
                builder.setView(viewInflate).create();
                final AlertDialog alertDialogShow = builder.show();
                alertDialogShow.setCanceledOnTouchOutside(false);
                alertDialogShow.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter.2.1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        alertDialogShow.dismiss();
                    }
                });
                button.setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter.2.2
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        onClickListener.onClick(view);
                        alertDialogShow.dismiss();
                    }
                });
                button2.setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter.2.3
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        alertDialogShow.dismiss();
                    }
                });
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void showMessageBox(String str, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(this.activity);
        builder.setMessage(str).setPositiveButton(this.localeManager.getString(R.string.npres_second_password_dialog_positive_button_text), onClickListener);
        if (onClickListener2 != null) {
            builder.setNegativeButton(this.localeManager.getString(R.string.npres_second_password_dialog_negative_button_text), onClickListener2);
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (onClickListener2 != null) {
                    onClickListener2.onClick(null);
                } else if (onClickListener != null) {
                    onClickListener.onClick(null);
                }
            }
        });
        builder.create().show();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.Presenter
    public void showTerms() {
        NXToyTerm terms = this.secondPasswordManager.getTerms();
        if (terms == null) {
            ToyLog.d("There are no terms set in the second password of Toy Console");
        } else {
            NXPTermsManager.getInstance().showTermsDetail(this.activity, terms.termID);
        }
    }
}
