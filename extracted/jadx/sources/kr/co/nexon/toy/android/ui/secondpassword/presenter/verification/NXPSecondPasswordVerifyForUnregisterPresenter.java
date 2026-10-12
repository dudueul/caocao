package kr.co.nexon.toy.android.ui.secondpassword.presenter.verification;

import android.app.Activity;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordVerifyForUnregisterPresenter extends NXPSecondPasswordVerifyPresenter {
    public NXPSecondPasswordVerifyForUnregisterPresenter(Activity activity) {
        super(activity);
        this.viewType = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterSeoncdPassword() {
        this.secondPasswordManager.unreigsterSecondPassword(new NPListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForUnregisterPresenter.4
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                final NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail);
                if (nXToySecondPasswordResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXPSecondPasswordVerifyForUnregisterPresenter.this.showMessageBox(NXPSecondPasswordVerifyForUnregisterPresenter.this.localeManager.getString(R.string.npres_second_password_unregister_success_msg), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForUnregisterPresenter.4.2
                        @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                        protected void onSwallowClick(View view) {
                            nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Unregistered.getValue();
                            NXPSecondPasswordVerifyForUnregisterPresenter.this.onSuccess(nXToySecondPasswordResult);
                        }
                    }, null);
                    return;
                }
                nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Registered.getValue();
                NXPSecondPasswordVerifyForUnregisterPresenter.this.showMessageBox(nXToySecondPasswordResult.errorText, new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForUnregisterPresenter.4.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NXPSecondPasswordVerifyForUnregisterPresenter.this.onFail(nXToySecondPasswordResult);
                    }
                }, null);
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onCancel() {
        final NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SECOND_PASSWORD_VERIFY_CANCELLED.getCode(), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel));
        nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Registered.getValue();
        showMessageBox(this.localeManager.getString(R.string.npres_second_password_error_message_unregister_verify_close), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForUnregisterPresenter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NXPSecondPasswordVerifyForUnregisterPresenter.this.finishListener.onCancel(nXToySecondPasswordResult);
                NXPSecondPasswordVerifyForUnregisterPresenter.this.verifyView.resetInputPassword();
                NXPSecondPasswordVerifyForUnregisterPresenter.this.verifyView.hideSoftKeyboard();
                NXPSecondPasswordVerifyForUnregisterPresenter.this.verifyView.onDismiss();
            }
        }, new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForUnregisterPresenter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.Presenter
    public void onVerifySecondPassword(String str) {
        this.secondPasswordManager.verifySecondPassword(str, new NPListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForUnregisterPresenter.3
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXPSecondPasswordVerifyForUnregisterPresenter.this.unregisterSeoncdPassword();
                } else {
                    NXPSecondPasswordVerifyForUnregisterPresenter.this.doProcessErrorResult(nXToyResult);
                }
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setView(NXPSecondPasswordVerifyContract.View view) {
        super.setView(view);
        this.verifyView.setDefaultDisplayUI(this.activity.getResources().getConfiguration().orientation, this.viewType);
        this.verifyView.setTitle(this.localeManager.getString(R.string.npres_second_password_verify_view_title_text));
        this.verifyView.setChangePasswordButtonText(this.localeManager.getString(R.string.npres_second_password_verify_view_change_password_button_text));
        this.verifyView.setSubDescriptionText(this.localeManager.getString(R.string.npres_second_password_unregister_input_password_description));
    }
}
