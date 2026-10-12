package kr.co.nexon.toy.android.ui.secondpassword.presenter.register;

import android.app.Activity;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordChangePasswordPresenter extends NXPSecondPasswordRegisterPresenter {

    /* JADX INFO: renamed from: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordChangePasswordPresenter$3, reason: invalid class name */
    class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ String val$password;

        AnonymousClass3(String str) {
            this.val$password = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NXPSecondPasswordChangePasswordPresenter.this.secondPasswordManager.setSecondPassword(this.val$password, new NPListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordChangePasswordPresenter.3.1
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode == NXToyErrorCode.SECOND_PASSWORD_SERVER_ERROR_INVLIAD_EMAIL.getCode()) {
                        NXPSecondPasswordChangePasswordPresenter.this.registerView.setEmailInvalidErrorMessage(NXPSecondPasswordChangePasswordPresenter.this.localeManager.getString(R.string.npres_second_password_register_view_error_msg_email_invaild));
                        return;
                    }
                    if (nXToyResult.errorCode == NXToyErrorCode.SECOND_PASSWORD_SERVER_ERROR_INVALID_PASSWORD.getCode()) {
                        NXPSecondPasswordChangePasswordPresenter.this.registerView.setPasswordInvalidErrorMessage(NXPSecondPasswordChangePasswordPresenter.this.localeManager.getString(R.string.npres_second_password_register_view_error_msg_password_invalid));
                    } else if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        NXPSecondPasswordChangePasswordPresenter.this.showMessageBox(nXToyResult.errorText, null, null);
                    } else {
                        NXPSecondPasswordChangePasswordPresenter.this.showMessageBox(NXPSecondPasswordChangePasswordPresenter.this.localeManager.getString(R.string.npres_second_password_register_successed_change_password_confirm_message), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordChangePasswordPresenter.3.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "");
                                nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Registered.getValue();
                                NXPSecondPasswordChangePasswordPresenter.this.onSuccess(nXToySecondPasswordResult);
                            }
                        }, null);
                    }
                }
            });
        }
    }

    public NXPSecondPasswordChangePasswordPresenter(Activity activity) {
        super(activity);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.Presenter
    public /* bridge */ /* synthetic */ String getEmailId() {
        return super.getEmailId();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.Presenter
    public /* bridge */ /* synthetic */ NXPSecondPasswordState getStatus() {
        return super.getStatus();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onCancel() {
        final NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SECOND_PASSWORD_VERIFY_SET_CANCELLED.getCode(), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel));
        nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Reset.getValue();
        showMessageBox(this.localeManager.getString(R.string.npres_second_password_register_cancel_msg), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordChangePasswordPresenter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NXPSecondPasswordChangePasswordPresenter.this.finishListener.onCancel(nXToySecondPasswordResult);
                NXPSecondPasswordChangePasswordPresenter.this.registerView.onDismiss();
            }
        }, new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordChangePasswordPresenter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public /* bridge */ /* synthetic */ void onFail(NXToyResult nXToyResult) {
        super.onFail(nXToyResult);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.Presenter
    public void onRegisterSecondPassword(String str, String str2, String str3) {
        if (isInvalidPassword(str2, str3)) {
            return;
        }
        showConfirmMessage(str, new AnonymousClass3(str2));
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public /* bridge */ /* synthetic */ void onSuccess(NXToyResult nXToyResult) {
        super.onSuccess(nXToyResult);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public /* bridge */ /* synthetic */ void setActionListener(NXPActionListener nXPActionListener) {
        super.setActionListener(nXPActionListener);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setView(NXPSecondPasswordRegisterContract.View view) {
        super.setView(view);
        this.registerView.setTitle(this.localeManager.getString(R.string.npres_second_password_register_view_change_password_title_text));
        this.registerView.setEmailTextEditable(false);
        this.registerView.setTermsLayoutVisibility(8);
        this.registerView.setTermsAgreementStatus(true);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public /* bridge */ /* synthetic */ void showMessageBox(String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super.showMessageBox(str, onClickListener, onClickListener2);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordRegisterPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.Presenter
    public /* bridge */ /* synthetic */ void showTerms() {
        super.showTerms();
    }
}
