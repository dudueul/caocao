package kr.co.nexon.toy.android.ui.secondpassword.presenter.setting;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordSetupContract;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordSetupPresenter implements NXPSecondPasswordSetupContract.Presenter {
    private Activity activity;
    private NXPActionListener finishListener;
    private NXPSecondPasswordSetupContract.View setupView;
    private NXPSecondPasswordManager secondPasswordManager = NXPSecondPasswordManager.getInstance();
    private NXToyLocaleManager localeManager = NXToyLocaleManager.getInstance();
    private NXPSecondPasswordState status = this.secondPasswordManager.getStatus();

    public NXPSecondPasswordSetupPresenter(Activity activity) {
        this.activity = activity;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onCancel() {
        NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "");
        nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Registered.getValue();
        this.finishListener.onCancel(nXToySecondPasswordResult);
        this.setupView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordSetupContract.Presenter
    public void onChangeSecondPassword() {
        this.secondPasswordManager.resetSecondPassword(new NPListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.setting.NXPSecondPasswordSetupPresenter.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXPSecondPasswordSetupPresenter.this.showMessageBox(NXPSecondPasswordSetupPresenter.this.localeManager.getString(R.string.npres_second_password_verify_view_change_password_fail_message), null, null);
                    return;
                }
                NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "");
                nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Reset.getValue();
                NXPSecondPasswordSetupPresenter.this.onSuccess(nXToySecondPasswordResult);
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onFail(NXToyResult nXToyResult) {
        this.finishListener.onFail(nXToyResult);
        this.setupView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void onSuccess(NXToyResult nXToyResult) {
        this.finishListener.onSuccess(nXToyResult);
        this.setupView.onDismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordSetupContract.Presenter
    public void onUnregisterSecondPassword() {
        NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "");
        nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Unregistered.getValue();
        onSuccess(nXToySecondPasswordResult);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setActionListener(NXPActionListener nXPActionListener) {
        this.finishListener = nXPActionListener;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void setView(NXPSecondPasswordSetupContract.View view) {
        this.setupView = view;
        this.setupView.setPresenter(this);
        this.setupView.setTitle(this.localeManager.getString(R.string.npres_second_password_setup_view_title_text));
        this.setupView.setUnregisterDescription(this.localeManager.getString(R.string.npres_second_password_setup_view_unregister_description));
        this.setupView.setChangePasswordDescription(this.localeManager.getString(R.string.npres_second_password_setup_view_change_password_description));
        this.setupView.setUnregisterButtonText(this.localeManager.getString(R.string.npres_second_password_setup_view_unregister_button_text));
        this.setupView.setChangePasswordButtonText(this.localeManager.getString(R.string.npres_second_password_setup_view_change_password_button_text));
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter
    public void showMessageBox(String str, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(this.activity);
        builder.setMessage(str).setPositiveButton(this.localeManager.getString(R.string.npres_second_password_dialog_positive_button_text), onClickListener);
        if (onClickListener2 != null) {
            builder.setNegativeButton(this.localeManager.getString(R.string.npres_second_password_dialog_negative_button_text), onClickListener2);
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.presenter.setting.NXPSecondPasswordSetupPresenter.1
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
