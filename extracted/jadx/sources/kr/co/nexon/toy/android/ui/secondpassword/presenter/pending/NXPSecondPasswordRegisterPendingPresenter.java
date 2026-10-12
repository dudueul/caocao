package kr.co.nexon.toy.android.ui.secondpassword.presenter.pending;

import android.app.Activity;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordRegisterPendingPresenter extends NXPSecondPasswordPendingPresenter {
    public NXPSecondPasswordRegisterPendingPresenter(Activity activity) {
        super(activity);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.Presenter
    public /* bridge */ /* synthetic */ String getEmailId() {
        return super.getEmailId();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onCancel() {
        if (this.secondPasswordManager.isRequired()) {
            NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SECOND_PASSWORD_VERIFY_PROCESSING_CANCELLED.getCode(), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel));
            nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Processing.getValue();
            this.finishListener.onCancel(nXToySecondPasswordResult);
            this.pendingView.onDismiss();
            return;
        }
        NXToySecondPasswordResult nXToySecondPasswordResult2 = new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "");
        nXToySecondPasswordResult2.result.authStatus = NXPSecondPasswordState.Processing.getValue();
        this.finishListener.onSuccess(nXToySecondPasswordResult2);
        this.pendingView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public /* bridge */ /* synthetic */ void onFail(NXToyResult nXToyResult) {
        super.onFail(nXToyResult);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.Presenter
    public void onResendEmail() {
        this.secondPasswordManager.sendSecondPasswordRegisteredEmail(new NPListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordRegisterPendingPresenter.1
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                String string = NXPSecondPasswordRegisterPendingPresenter.this.localeManager.getString(R.string.npres_second_password_pending_view_resend_success_text);
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    string = NXPSecondPasswordRegisterPendingPresenter.this.localeManager.getString(R.string.npres_second_password_error_message_register_resend_mail_fail);
                }
                NXPSecondPasswordRegisterPendingPresenter.this.showMessageBox(string, null, null);
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public /* bridge */ /* synthetic */ void onSuccess(NXToyResult nXToyResult) {
        super.onSuccess(nXToyResult);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public /* bridge */ /* synthetic */ void setActionListener(NXPActionListener nXPActionListener) {
        super.setActionListener(nXPActionListener);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setView(NXPSecondPasswordPendingContract.View view) {
        super.setView(view);
        this.pendingView.setTitle(this.localeManager.getString(R.string.npres_second_password_pending_view_title_text_register));
        this.pendingView.setTopDescription(this.localeManager.getString(R.string.npres_second_password_pending_view_top_description_register));
        this.pendingView.setEmailId(getEmailId());
        this.pendingView.setResendRegisteredEmailDescription(String.format(this.localeManager.getString(R.string.npres_second_password_pending_view_resend_description), this.secondPasswordManager.isRequired() ? this.localeManager.getString(R.string.npres_second_password_pending_view_email_valid_time_day_text) : this.localeManager.getString(R.string.npres_second_password_pending_view_email_valid_time_hour_text)), this.localeManager.getString(R.string.npres_second_password_pending_view_resend_link_text));
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.Presenter
    public /* bridge */ /* synthetic */ void showCS() {
        super.showCS();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public /* bridge */ /* synthetic */ void showMessageBox(String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super.showMessageBox(str, onClickListener, onClickListener2);
    }
}
