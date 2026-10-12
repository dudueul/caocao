package kr.co.nexon.toy.android.ui.secondpassword.presenter.verification;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public abstract class NXPSecondPasswordVerifyPresenter implements NXPSecondPasswordVerifyContract.Presenter {
    protected static final int VIEW_TYPE_JOIN = 0;
    protected static final int VIEW_TYPE_UNREGISTER = 1;
    public Activity activity;
    public NXPActionListener finishListener;
    public NXPSecondPasswordVerifyContract.View verifyView;
    public int viewType;
    public NXPSecondPasswordManager secondPasswordManager = NXPSecondPasswordManager.getInstance();
    public NXToyLocaleManager localeManager = NXToyLocaleManager.getInstance();
    public NXPSecondPasswordState status = this.secondPasswordManager.getStatus();

    public NXPSecondPasswordVerifyPresenter(Activity activity) {
        this.activity = activity;
    }

    protected void doProcessErrorResult(NXToyResult nXToyResult) {
        if (this.secondPasswordManager.getStatus() == NXPSecondPasswordState.Locked || nXToyResult.errorCode == NXToyErrorCode.SECOND_PASSWORD_SERVER_ERROR_LOCKED.getCode()) {
            NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail);
            nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Locked.getValue();
            onFail(nXToySecondPasswordResult);
        } else if (nXToyResult.errorCode == NXToyErrorCode.SECOND_PASSWORD_SERVER_ERROR_PASSWORD_NOT_FOUDN.getCode() || nXToyResult.errorCode == NXToyErrorCode.SECOND_PASSWORD_SERVER_ERROR_EMAIL_NOT_FOUDN.getCode()) {
            this.verifyView.setErrorText(this.localeManager.getString(R.string.npres_second_password_error_message_password_not_found));
            this.verifyView.resetInputPassword();
        } else if (nXToyResult.errorCode == NXToyErrorCode.SECOND_PASSWORD_SERVER_ERROR_INVALID_PASSWORD.getCode()) {
            this.verifyView.setErrorText(String.format(this.localeManager.getString(R.string.npres_second_password_verify_view_error_msg_verfiy_fail), Integer.valueOf(this.secondPasswordManager.getRemainedCount())));
            this.verifyView.resetInputPassword();
        } else if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
            this.verifyView.setErrorText(this.localeManager.getString(R.string.npres_second_password_error_message_verify_fail_etc));
            this.verifyView.resetInputPassword();
        }
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.Presenter
    public String getEmailId() {
        String emailId = this.secondPasswordManager.getEmailId();
        if (NXStringUtil.isNull(emailId)) {
            emailId = NXToySessionManager.getInstance().getSession().getEmailId();
        }
        return NXStringUtil.isNull(emailId) ? "" : emailId;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.Presenter
    public NXPSecondPasswordState getStatus() {
        return this.status;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onFail(NXToyResult nXToyResult) {
        this.finishListener.onFail(nXToyResult);
        this.verifyView.resetInputPassword();
        this.verifyView.hideSoftKeyboard();
        this.verifyView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onSuccess(NXToyResult nXToyResult) {
        this.finishListener.onSuccess(nXToyResult);
        this.verifyView.resetInputPassword();
        this.verifyView.hideSoftKeyboard();
        this.verifyView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setActionListener(NXPActionListener nXPActionListener) {
        this.finishListener = nXPActionListener;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setView(NXPSecondPasswordVerifyContract.View view) {
        this.verifyView = view;
        this.verifyView.setPresenter(this);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.Presenter
    public void showChangePasswordView() {
        this.secondPasswordManager.resetSecondPassword(new NPListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyPresenter.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPSecondPasswordVerifyPresenter.this.showMessageBox(NXPSecondPasswordVerifyPresenter.this.localeManager.getString(R.string.npres_second_password_verify_view_change_password_fail_message), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyPresenter.2.1
                        @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                        protected void onSwallowClick(View view) {
                            NXPSecondPasswordVerifyPresenter.this.verifyView.showSoftKeyboard();
                        }
                    }, null);
                    return;
                }
                NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "");
                nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Reset.getValue();
                NXPSecondPasswordVerifyPresenter.this.onSuccess(nXToySecondPasswordResult);
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
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyPresenter.1
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
}
