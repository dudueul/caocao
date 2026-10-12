package kr.co.nexon.toy.android.ui.secondpassword.presenter.pending;

import android.app.Activity;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordResetPendingPresenter extends NXPSecondPasswordPendingPresenter {
    public NXPSecondPasswordResetPendingPresenter(Activity activity) {
        super(activity);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.Presenter
    public /* bridge */ /* synthetic */ String getEmailId() {
        return super.getEmailId();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onCancel() {
        NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SECOND_PASSWORD_VERIFY_RESET_CANCELLED.getCode(), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel), this.localeManager.getString(R.string.npres_second_password_error_message_Verify_cancel));
        nXToySecondPasswordResult.result.authStatus = this.secondPasswordManager.getStatus().getValue();
        this.finishListener.onCancel(nXToySecondPasswordResult);
        this.pendingView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public /* bridge */ /* synthetic */ void onFail(NXToyResult nXToyResult) {
        super.onFail(nXToyResult);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter, kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.Presenter
    public /* bridge */ /* synthetic */ void onResendEmail() {
        super.onResendEmail();
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
        this.pendingView.setTitle(this.localeManager.getString(R.string.npres_second_password_pending_view_title_text_reset));
        this.pendingView.setTopDescription(this.localeManager.getString(R.string.npres_second_password_pending_view_top_description_reset));
        this.pendingView.setEmailId(NXStringUtil.getMaskedEmail(getEmailId()));
        this.pendingView.setResendRegisteredEmailDescription(String.format(this.localeManager.getString(R.string.npres_second_password_pending_view_resend_description), this.localeManager.getString(R.string.npres_second_password_pending_view_email_valid_time_day_text)), this.localeManager.getString(R.string.npres_second_password_pending_view_resend_link_text));
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
