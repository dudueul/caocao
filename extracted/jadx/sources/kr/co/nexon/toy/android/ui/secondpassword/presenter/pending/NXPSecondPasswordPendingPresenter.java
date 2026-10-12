package kr.co.nexon.toy.android.ui.secondpassword.presenter.pending;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.Map;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.board.NXBoardManager;
import kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
abstract class NXPSecondPasswordPendingPresenter implements NXPSecondPasswordPendingContract.Presenter {
    public Activity activity;
    public NXPActionListener finishListener;
    public NXPSecondPasswordPendingContract.View pendingView;
    public NXPSecondPasswordManager secondPasswordManager = NXPSecondPasswordManager.getInstance();
    public NXPSecondPasswordState status = this.secondPasswordManager.getStatus();
    public NXToyLocaleManager localeManager = NXToyLocaleManager.getInstance();

    public NXPSecondPasswordPendingPresenter(Activity activity) {
        this.activity = activity;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.Presenter
    public String getEmailId() {
        String emailId = this.secondPasswordManager.getEmailId();
        if (NXStringUtil.isNull(emailId)) {
            emailId = NXToySessionManager.getInstance().getSession().getEmailId();
        }
        return NXStringUtil.isNull(emailId) ? "" : emailId;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onFail(NXToyResult nXToyResult) {
        this.finishListener.onFail((NXToySecondPasswordResult) nXToyResult);
        this.pendingView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.Presenter
    public void onResendEmail() {
        this.secondPasswordManager.resetSecondPassword(new NPListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                String string = NXPSecondPasswordPendingPresenter.this.localeManager.getString(R.string.npres_second_password_pending_view_resend_success_text);
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    string = NXPSecondPasswordPendingPresenter.this.localeManager.getString(R.string.npres_second_password_error_message_register_resend_mail_fail);
                }
                NXPSecondPasswordPendingPresenter.this.showMessageBox(string, null, null);
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onSuccess(NXToyResult nXToyResult) {
        this.finishListener.onSuccess((NXToySecondPasswordResult) nXToyResult);
        this.pendingView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setActionListener(NXPActionListener nXPActionListener) {
        this.finishListener = nXPActionListener;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setView(NXPSecondPasswordPendingContract.View view) {
        this.pendingView = view;
        this.pendingView.setPresenter(this);
        this.pendingView.setCSButtonText(this.localeManager.getString(R.string.npres_second_password_pending_view_cs_button_text));
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.Presenter
    public void showCS() {
        NXBoardManager.getInstance(this.activity).showHelpCenter(this.activity, (Map<String, Object>) null);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void showMessageBox(String str, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(this.activity);
        builder.setMessage(str).setPositiveButton(this.localeManager.getString(R.string.npres_second_password_dialog_positive_button_text), onClickListener);
        if (onClickListener2 != null) {
            builder.setNegativeButton(this.localeManager.getString(R.string.npres_second_password_dialog_negative_button_text), onClickListener2);
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordPendingPresenter.1
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
