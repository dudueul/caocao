package kr.co.nexon.toy.android.ui.secondpassword;

import android.app.Activity;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView;
import kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordLockPendingPresenter;
import kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordRegisterPendingPresenter;
import kr.co.nexon.toy.android.ui.secondpassword.presenter.pending.NXPSecondPasswordResetPendingPresenter;
import kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordChangePasswordPresenter;
import kr.co.nexon.toy.android.ui.secondpassword.presenter.register.NXPSecondPasswordGenerationPresenter;
import kr.co.nexon.toy.android.ui.secondpassword.presenter.setting.NXPSecondPasswordSetupPresenter;
import kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForJoinPresenter;
import kr.co.nexon.toy.android.ui.secondpassword.presenter.verification.NXPSecondPasswordVerifyForUnregisterPresenter;
import kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordPendingView;
import kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordRegisterView;
import kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordSetupView;
import kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordVerifyView;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordViewHandler {
    public static final int REQUEST_TYPE_SETUP = 1;
    public static final int REQUEST_TYPE_VERIFY = 0;
    private static final int VIEW_TYPE_PENDING_LOCKED = 4;
    private static final int VIEW_TYPE_PENDING_REGISTER = 3;
    private static final int VIEW_TYPE_PENDING_RESET = 5;
    private static final int VIEW_TYPE_REGISTER_CHANGE_PASSWORD_SETUP = 2;
    private static final int VIEW_TYPE_REGISTER_CHANGE_PASSWORD_VERIFY = 1;
    private static final int VIEW_TYPE_REGISTER_GENERATION = 0;
    private static final int VIEW_TYPE_SETUP = 7;
    private static final int VIEW_TYPE_SETUP_CHANGE_PASSWORD = 9;
    private static final int VIEW_TYPE_SETUP_UNREGISTER_VERIFY = 8;
    private static final int VIEW_TYPE_VERIFY = 6;
    private Activity activity;
    private int requestApiType;
    private NPListener resultListener;
    private NXPSecondPasswordState status;

    public NXPSecondPasswordViewHandler(Activity activity, int i, NXPSecondPasswordState nXPSecondPasswordState) {
        this.activity = activity;
        this.requestApiType = i;
        this.status = nXPSecondPasswordState;
    }

    private NXPActionListener createActionListener(final int i) {
        return new NXPActionListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.NXPSecondPasswordViewHandler.1
            @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener
            public void onCancel(NXToyResult nXToyResult) {
                NXPSecondPasswordViewHandler.this.sendResult(nXToyResult);
            }

            @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener
            public void onFail(NXToyResult nXToyResult) {
                NXToySecondPasswordResult nXToySecondPasswordResult = (NXToySecondPasswordResult) nXToyResult;
                switch (i) {
                    case 6:
                    case 8:
                        if (nXToySecondPasswordResult.result.authStatus != NXPSecondPasswordState.Locked.getValue()) {
                            NXPSecondPasswordViewHandler.this.sendResult(nXToyResult);
                        } else {
                            NXPSecondPasswordViewHandler.this.showSecondPasswordView(4);
                        }
                        break;
                    case 7:
                    default:
                        NXPSecondPasswordViewHandler.this.sendResult(nXToyResult);
                        break;
                }
            }

            @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener
            public void onSuccess(NXToyResult nXToyResult) {
                NXToySecondPasswordResult nXToySecondPasswordResult = (NXToySecondPasswordResult) nXToyResult;
                switch (i) {
                    case 0:
                        NXPSecondPasswordViewHandler.this.showSecondPasswordView(3);
                        break;
                    case 1:
                        NXPSecondPasswordViewHandler.this.showSecondPasswordView(6);
                        break;
                    case 2:
                        NXPSecondPasswordViewHandler.this.showSecondPasswordView(7);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    default:
                        NXPSecondPasswordViewHandler.this.sendResult(nXToyResult);
                        break;
                    case 6:
                        if (nXToySecondPasswordResult.result.authStatus != NXPSecondPasswordState.Reset.getValue()) {
                            NXPSecondPasswordViewHandler.this.sendResult(nXToyResult);
                        } else {
                            NXPSecondPasswordViewHandler.this.showSecondPasswordView(5);
                        }
                        break;
                    case 7:
                        if (nXToySecondPasswordResult.result.authStatus == NXPSecondPasswordState.Reset.getValue()) {
                            NXPSecondPasswordViewHandler.this.showSecondPasswordView(5);
                        } else if (nXToySecondPasswordResult.result.authStatus != NXPSecondPasswordState.Unregistered.getValue()) {
                            NXPSecondPasswordViewHandler.this.sendResult(nXToyResult);
                        } else {
                            NXPSecondPasswordViewHandler.this.showSecondPasswordView(8);
                        }
                        break;
                    case 8:
                        if (nXToySecondPasswordResult.result.authStatus != NXPSecondPasswordState.Reset.getValue()) {
                            NXPSecondPasswordViewHandler.this.sendResult(nXToyResult);
                        } else {
                            NXPSecondPasswordViewHandler.this.showSecondPasswordView(5);
                        }
                        break;
                }
            }
        };
    }

    private NXPBasePresenter createPresenter(int i) {
        NXPBasePresenter nXPSecondPasswordResetPendingPresenter;
        switch (i) {
            case 0:
                nXPSecondPasswordResetPendingPresenter = new NXPSecondPasswordGenerationPresenter(this.activity);
                break;
            case 1:
            case 2:
                nXPSecondPasswordResetPendingPresenter = new NXPSecondPasswordChangePasswordPresenter(this.activity);
                break;
            case 3:
                nXPSecondPasswordResetPendingPresenter = new NXPSecondPasswordRegisterPendingPresenter(this.activity);
                break;
            case 4:
                nXPSecondPasswordResetPendingPresenter = new NXPSecondPasswordLockPendingPresenter(this.activity);
                break;
            case 5:
                nXPSecondPasswordResetPendingPresenter = new NXPSecondPasswordResetPendingPresenter(this.activity);
                break;
            case 6:
                nXPSecondPasswordResetPendingPresenter = new NXPSecondPasswordVerifyForJoinPresenter(this.activity);
                break;
            case 7:
                nXPSecondPasswordResetPendingPresenter = new NXPSecondPasswordSetupPresenter(this.activity);
                break;
            case 8:
                nXPSecondPasswordResetPendingPresenter = new NXPSecondPasswordVerifyForUnregisterPresenter(this.activity);
                break;
            case 9:
                nXPSecondPasswordResetPendingPresenter = new NXPSecondPasswordResetPendingPresenter(this.activity);
                break;
            default:
                nXPSecondPasswordResetPendingPresenter = null;
                break;
        }
        if (nXPSecondPasswordResetPendingPresenter != null) {
            nXPSecondPasswordResetPendingPresenter.setActionListener(createActionListener(i));
        }
        return nXPSecondPasswordResetPendingPresenter;
    }

    private NXPBaseView createView(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                return (NXPBaseView) NXPSecondPasswordRegisterView.createView(this.activity);
            case 3:
            case 4:
            case 5:
            case 9:
                return (NXPBaseView) NXPSecondPasswordPendingView.createView(this.activity);
            case 6:
            case 8:
                return (NXPBaseView) NXPSecondPasswordVerifyView.createView(this.activity);
            case 7:
                return (NXPBaseView) NXPSecondPasswordSetupView.createView(this.activity);
            default:
                return null;
        }
    }

    private int getViewType() {
        if (this.status == NXPSecondPasswordState.Unregistered) {
            return 0;
        }
        if (this.status == NXPSecondPasswordState.Processing) {
            return 3;
        }
        if (this.status == NXPSecondPasswordState.Locked) {
            return 4;
        }
        if (this.status == NXPSecondPasswordState.Reset) {
            return this.requestApiType == 0 ? 1 : 2;
        }
        if (this.status == NXPSecondPasswordState.Registered) {
            return this.requestApiType == 0 ? 6 : 7;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendResult(NXToyResult nXToyResult) {
        if (this.resultListener != null) {
            this.resultListener.onResult(nXToyResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSecondPasswordView(int i) {
        NXPBasePresenter nXPBasePresenterCreatePresenter = createPresenter(i);
        NXPBaseView nXPBaseViewCreateView = createView(i);
        NXPSecondPasswordDialog nXPSecondPasswordDialogNewInstance = NXPSecondPasswordDialog.newInstance(this.activity, this.status.getValue());
        nXPSecondPasswordDialogNewInstance.setPresenter(nXPBasePresenterCreatePresenter);
        nXPSecondPasswordDialogNewInstance.setView(nXPBaseViewCreateView);
        nXPSecondPasswordDialogNewInstance.show(this.activity.getFragmentManager(), NXPSecondPasswordDialog.TAG);
    }

    public void doProcess(NPListener nPListener) {
        this.resultListener = nPListener;
        showSecondPasswordView(getViewType());
    }

    public int getRequestTag() {
        return this.requestApiType == 0 ? NXToyRequestTag.VerifySecondPassword.getValue() : NXToyRequestTag.SetupSecondPassword.getValue();
    }
}
