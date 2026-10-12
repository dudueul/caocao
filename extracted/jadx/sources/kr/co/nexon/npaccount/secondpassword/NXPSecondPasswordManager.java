package kr.co.nexon.npaccount.secondpassword;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.result.NXToySecondPasswordResult;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.secondpassword.NXPSecondPasswordViewHandler;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordManager {
    private NXPSecondPassword secondPassword;

    private static class Singleton {
        private static final NXPSecondPasswordManager instance = new NXPSecondPasswordManager();

        private Singleton() {
        }
    }

    private NXPSecondPasswordManager() {
        this.secondPassword = new NXPSecondPassword();
    }

    public static NXPSecondPasswordManager getInstance() {
        return Singleton.instance;
    }

    private boolean isNotLoginedUser() {
        int type = NXToySessionManager.getInstance().getSession().getType();
        return type == NXToyLoginType.LoginTypeDefault.getValue() || type == NXToyLoginType.LoginTypeNotLogined.getValue();
    }

    public void doProcessBySecondPasswordStatus(Activity activity, NXPSecondPasswordState nXPSecondPasswordState, int i, NPListener nPListener) {
        new NXPSecondPasswordViewHandler(activity, i, nXPSecondPasswordState).doProcess(nPListener);
    }

    public String getEmailId() {
        return this.secondPassword.getEmailId();
    }

    public int getRemainedCount() {
        return this.secondPassword.getRemainedCount();
    }

    public NXPSecondPasswordState getStatus() {
        return this.secondPassword.getStatus();
    }

    public NXToyTerm getTerms() {
        return this.secondPassword.getTerms();
    }

    public boolean isRequired() {
        return this.secondPassword.isRequired();
    }

    public void registerSecondPassword(String str, String str2, final NPListener nPListener) {
        this.secondPassword.registerSecondPassword(str, str2, new NXPSecondPassword.NXPSecondPasswordListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.3
            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onFail(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail, NXToyRequestTag.RegisterSecondPassword.getValue()));
                }
            }

            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onSuccess(NXPSecondPasswordState nXPSecondPasswordState) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "", NXToyRequestTag.RegisterSecondPassword.getValue()));
                }
            }
        });
    }

    public void resetSecondPassword(final NPListener nPListener) {
        this.secondPassword.resetSecondPassword(new NXPSecondPassword.NXPSecondPasswordListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.6
            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onFail(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail, NXToyRequestTag.ResetSecondPassword.getValue()));
                }
            }

            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onSuccess(NXPSecondPasswordState nXPSecondPasswordState) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "", NXToyRequestTag.ResetSecondPassword.getValue()));
                }
            }
        });
    }

    public void sendSecondPasswordRegisteredEmail(final NPListener nPListener) {
        this.secondPassword.sendSecondPasswordRegisteredEmail(new NXPSecondPassword.NXPSecondPasswordListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.5
            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onFail(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail, NXToyRequestTag.UnregisterSecondPassword.getValue()));
                }
            }

            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onSuccess(NXPSecondPasswordState nXPSecondPasswordState) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "", NXToyRequestTag.UnregisterSecondPassword.getValue()));
                }
            }
        });
    }

    public void setSecondPassword(String str, final NPListener nPListener) {
        this.secondPassword.setSecondPassword(str, new NXPSecondPassword.NXPSecondPasswordListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.7
            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onFail(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail, NXToyRequestTag.SetSecondPassword.getValue()));
                }
            }

            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onSuccess(NXPSecondPasswordState nXPSecondPasswordState) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "", NXToyRequestTag.SetSecondPassword.getValue()));
                }
            }
        });
    }

    public void setupSecondPassword(final Activity activity, final NPListener nPListener) {
        if (!isNotLoginedUser()) {
            this.secondPassword.getSecondPasswordInfo(new NXPSecondPassword.NXPSecondPasswordListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.2
                @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
                public void onFail(NXToyResult nXToyResult) {
                    if (nPListener != null) {
                        NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail, NXToyRequestTag.SetupSecondPassword.getValue());
                        ToyLog.d("Setup second password (Fail) : " + nXToyResult.toString());
                        nPListener.onResult(nXToySecondPasswordResult);
                    }
                }

                @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
                public void onSuccess(NXPSecondPasswordState nXPSecondPasswordState) {
                    if (NXPSecondPasswordManager.this.secondPassword.isReleased()) {
                        NXPSecondPasswordManager.this.doProcessBySecondPasswordStatus(activity, nXPSecondPasswordState, 1, new NPListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.2.3
                            @Override // kr.co.nexon.toy.listener.NPListener
                            public void onResult(NXToyResult nXToyResult) {
                                NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "", NXToyRequestTag.SetupSecondPassword.getValue());
                                nXToySecondPasswordResult.result.authStatus = ((NXToySecondPasswordResult) nXToyResult).result.authStatus;
                                if (nPListener != null) {
                                    ToyLog.d("Setup second password (Success) : " + nXToyResult.toString());
                                    nPListener.onResult(nXToySecondPasswordResult);
                                }
                            }
                        });
                        return;
                    }
                    NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance();
                    final NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SECOND_PASSWORD_NOT_RELEASED.getCode(), nXToyLocaleManager.getString(R.string.npres_second_password_error_message_not_used), nXToyLocaleManager.getString(R.string.npres_second_password_error_message_not_used), NXToyRequestTag.SetupSecondPassword.getValue());
                    nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Unregistered.getValue();
                    new NXPAlertDialog.Builder(activity).setMessage(nXToyLocaleManager.getString(R.string.npres_second_password_error_message_not_used)).setPositiveButton(nXToyLocaleManager.getString(R.string.npres_second_password_dialog_positive_button_text), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.2.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (nPListener != null) {
                                nPListener.onResult(nXToySecondPasswordResult);
                            }
                        }
                    }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.2.1
                        @Override // android.content.DialogInterface.OnCancelListener
                        public void onCancel(DialogInterface dialogInterface) {
                            if (nPListener != null) {
                                nPListener.onResult(nXToySecondPasswordResult);
                            }
                        }
                    }).create().show();
                }
            });
            return;
        }
        NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SECOND_PASSWORD_USER_NOT_FOUND.getCode(), NXToyLocaleManager.getInstance().getString(R.string.npres_second_password_error_message_not_login_user), "", NXToyRequestTag.SetupSecondPassword.getValue());
        nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Unregistered.getValue();
        if (nPListener != null) {
            nPListener.onResult(nXToySecondPasswordResult);
        }
    }

    public void unreigsterSecondPassword(final NPListener nPListener) {
        this.secondPassword.unregisterSecondPassword(new NXPSecondPassword.NXPSecondPasswordListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.4
            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onFail(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail, NXToyRequestTag.UnregisterSecondPassword.getValue()));
                }
            }

            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onSuccess(NXPSecondPasswordState nXPSecondPasswordState) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToyResult(NXToyErrorCode.SUCCESS.getCode(), "", "", NXToyRequestTag.UnregisterSecondPassword.getValue()));
                }
            }
        });
    }

    public void verifySecondPassword(final Activity activity, final NPListener nPListener) {
        NXToySessionManager.getInstance().getSession().getType();
        if (!isNotLoginedUser()) {
            this.secondPassword.getSecondPasswordInfo(new NXPSecondPassword.NXPSecondPasswordListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.1
                @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
                public void onFail(NXToyResult nXToyResult) {
                    if (nPListener != null) {
                        NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SECOND_PASSWORD_SERVER_ERROR_INFO_NOT_FOUND.getCode(), nXToyResult.errorText, nXToyResult.toString(), NXToyRequestTag.VerifySecondPassword.getValue());
                        nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Unregistered.getValue();
                        ToyLog.d("Verify second password (Fail) : " + nXToyResult.toString());
                        nPListener.onResult(nXToySecondPasswordResult);
                    }
                }

                @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
                public void onSuccess(NXPSecondPasswordState nXPSecondPasswordState) {
                    NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "", NXToyRequestTag.VerifySecondPassword.getValue());
                    if (!NXPSecondPasswordManager.this.secondPassword.isReleased()) {
                        if (nPListener != null) {
                            nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Unregistered.getValue();
                            nPListener.onResult(nXToySecondPasswordResult);
                            return;
                        }
                        return;
                    }
                    if (NXPSecondPasswordManager.this.secondPassword.isRequired() || nXPSecondPasswordState != NXPSecondPasswordState.Unregistered) {
                        NXPSecondPasswordManager.this.doProcessBySecondPasswordStatus(activity, nXPSecondPasswordState, 0, new NPListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.1.1
                            @Override // kr.co.nexon.toy.listener.NPListener
                            public void onResult(NXToyResult nXToyResult) {
                                NXToySecondPasswordResult nXToySecondPasswordResult2 = (NXToySecondPasswordResult) nXToyResult;
                                nXToySecondPasswordResult2.requestTag = NXToyRequestTag.VerifySecondPassword.getValue();
                                if (nPListener != null) {
                                    ToyLog.d("Verify second password (Success) : " + nXToyResult.toString());
                                    nPListener.onResult(nXToySecondPasswordResult2);
                                }
                            }
                        });
                    } else if (nPListener != null) {
                        nXToySecondPasswordResult.result.authStatus = nXPSecondPasswordState.getValue();
                        nPListener.onResult(nXToySecondPasswordResult);
                    }
                }
            });
            return;
        }
        NXToySecondPasswordResult nXToySecondPasswordResult = new NXToySecondPasswordResult(NXToyErrorCode.SECOND_PASSWORD_USER_NOT_FOUND.getCode(), NXToyLocaleManager.getInstance().getString(R.string.npres_second_password_error_message_not_login_user), "", NXToyRequestTag.VerifySecondPassword.getValue());
        nXToySecondPasswordResult.result.authStatus = NXPSecondPasswordState.Unregistered.getValue();
        if (nPListener != null) {
            nPListener.onResult(nXToySecondPasswordResult);
        }
    }

    public void verifySecondPassword(String str, final NPListener nPListener) {
        this.secondPassword.verifySecondPassword(str, new NXPSecondPassword.NXPSecondPasswordListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager.8
            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onFail(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail, NXToyRequestTag.SetSecondPassword.getValue()));
                }
            }

            @Override // kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.NXPSecondPasswordListener
            public void onSuccess(NXPSecondPasswordState nXPSecondPasswordState) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToySecondPasswordResult(NXToyErrorCode.SUCCESS.getCode(), "", "", NXToyRequestTag.SetSecondPassword.getValue()));
                }
            }
        });
    }
}
