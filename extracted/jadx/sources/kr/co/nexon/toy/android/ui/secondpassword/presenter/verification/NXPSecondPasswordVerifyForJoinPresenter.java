package kr.co.nexon.toy.android.ui.secondpassword.presenter.verification;

import android.app.Activity;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordVerifyForJoinPresenter extends NXPSecondPasswordVerifyPresenter {
    public NXPSecondPasswordVerifyForJoinPresenter(Activity activity) {
        super(activity);
        this.viewType = 0;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onCancel() {
        final NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SECOND_PASSWORD_VERIFY_CANCELLED.getCode(), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel));
        nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Registered.getValue();
        showMessageBox(this.localeManager.getString(R.string.npres_second_password_verify_view_close_message), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForJoinPresenter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NXPSecondPasswordVerifyForJoinPresenter.this.finishListener.onCancel(nXToySecondPasswordResult);
                NXPSecondPasswordVerifyForJoinPresenter.this.verifyView.hideSoftKeyboard();
                NXPSecondPasswordVerifyForJoinPresenter.this.verifyView.onDismiss();
            }
        }, null);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.Presenter
    public void onVerifySecondPassword(String str) {
        this.secondPasswordManager.verifySecondPassword(str, new NPListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForJoinPresenter.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPSecondPasswordVerifyForJoinPresenter.this.doProcessErrorResult(nXToyResult);
                    return;
                }
                NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "");
                nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordVerifyForJoinPresenter.this.getStatus().getValue();
                NXPSecondPasswordVerifyForJoinPresenter.this.onSuccess(nXToySecondPasswordResult);
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setView(NXPSecondPasswordVerifyContract.View view) {
        super.setView(view);
        this.verifyView.setDefaultDisplayUI(this.activity.getResources().getConfiguration().orientation, this.viewType);
        this.verifyView.setTitle(this.localeManager.getString(R.string.npres_second_password_verify_view_title_text));
        this.verifyView.setChangePasswordButtonText(this.localeManager.getString(R.string.npres_second_password_verify_view_change_password_button_text));
    }
}
