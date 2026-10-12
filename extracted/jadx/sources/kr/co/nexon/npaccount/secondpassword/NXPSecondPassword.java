package kr.co.nexon.npaccount.secondpassword;

import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.npaccount.secondpassword.request.NXToyGet2ndPasswordInfoRequest;
import kr.co.nexon.npaccount.secondpassword.request.NXToyRegister2ndPasswordRequest;
import kr.co.nexon.npaccount.secondpassword.request.NXToyReset2ndPasswordRequest;
import kr.co.nexon.npaccount.secondpassword.request.NXToySend2ndPasswordRegisteredEmailRequest;
import kr.co.nexon.npaccount.secondpassword.request.NXToySet2ndPasswordRequest;
import kr.co.nexon.npaccount.secondpassword.request.NXToyUnregister2ndPasswordRequest;
import kr.co.nexon.npaccount.secondpassword.request.NXToyVerify2ndPasswordRequest;
import kr.co.nexon.npaccount.secondpassword.result.NXToyGetSecondPasswordInfoResult;
import kr.co.nexon.npaccount.secondpassword.result.NXToyRegisterSecondPasswordResult;
import kr.co.nexon.npaccount.secondpassword.result.NXToyResetSecondPasswordResult;
import kr.co.nexon.npaccount.secondpassword.result.NXToySendSecondPasswordRegisteredEmailResult;
import kr.co.nexon.npaccount.secondpassword.result.NXToySetSecondPasswordResult;
import kr.co.nexon.npaccount.secondpassword.result.NXToyUnregisterSecondPasswordResult;
import kr.co.nexon.npaccount.secondpassword.result.NXToyVerifySecondPasswordResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class NXPSecondPassword {
    private NXToyTerm terms;
    private int remainedCount = 10;
    private int isRequired = 0;
    private int isReleased = 0;
    private String emailId = "";
    private NXPSecondPasswordState status = NXPSecondPasswordState.Unregistered;

    public interface NXPSecondPasswordListener {
        void onFail(NXToyResult nXToyResult);

        void onSuccess(NXPSecondPasswordState nXPSecondPasswordState);
    }

    NXPSecondPassword() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEmailId(String str) {
        this.emailId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsReleased(int i) {
        this.isReleased = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsRequired(int i) {
        this.isRequired = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemainedCount(int i) {
        this.remainedCount = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatus(NXPSecondPasswordState nXPSecondPasswordState) {
        this.status = nXPSecondPasswordState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTerms(NXToyTerm nXToyTerm) {
        this.terms = nXToyTerm;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public int getRemainedCount() {
        return this.remainedCount;
    }

    public void getSecondPasswordInfo(final NXPSecondPasswordListener nXPSecondPasswordListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyGet2ndPasswordInfoRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.1
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("Get second password information : " + nXToyResult.toString());
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nXPSecondPasswordListener.onFail(nXToyResult);
                    return;
                }
                NXToyGetSecondPasswordInfoResult.ResultSet resultSet = ((NXToyGetSecondPasswordInfoResult) nXToyResult).result;
                NXPSecondPassword.this.setIsRequired(resultSet.isRequired);
                NXPSecondPassword.this.setIsReleased(resultSet.isReleased);
                NXPSecondPassword.this.setEmailId(resultSet.email);
                NXPSecondPassword.this.setStatus(NXPSecondPasswordState.getStateByCode(resultSet.authStatus));
                NXPSecondPassword.this.setTerms(resultSet.terms);
                nXPSecondPasswordListener.onSuccess(NXPSecondPassword.this.status);
            }
        });
    }

    public NXPSecondPasswordState getStatus() {
        return this.status;
    }

    public NXToyTerm getTerms() {
        return this.terms;
    }

    public boolean isReleased() {
        return this.isReleased == 1;
    }

    public boolean isRequired() {
        return this.isRequired == 1;
    }

    public void registerSecondPassword(final String str, String str2, final NXPSecondPasswordListener nXPSecondPasswordListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyRegister2ndPasswordRequest(str, str2), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("Register second password : " + nXToyResult.toString());
                NXToyRegisterSecondPasswordResult.ResultSet resultSet = ((NXToyRegisterSecondPasswordResult) nXToyResult).result;
                if (resultSet != null) {
                    NXPSecondPassword.this.setStatus(NXPSecondPasswordState.getStateByCode(resultSet.authStatus));
                    NXPSecondPassword.this.setEmailId(str);
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nXPSecondPasswordListener.onFail(nXToyResult);
                } else {
                    nXPSecondPasswordListener.onSuccess(NXPSecondPassword.this.status);
                }
            }
        });
    }

    public void resetSecondPassword(final NXPSecondPasswordListener nXPSecondPasswordListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyReset2ndPasswordRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.6
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("Reset request second password : " + nXToyResult.toString());
                NXToyResetSecondPasswordResult.ResultSet resultSet = ((NXToyResetSecondPasswordResult) nXToyResult).result;
                if (resultSet != null) {
                    NXPSecondPassword.this.setStatus(NXPSecondPasswordState.getStateByCode(resultSet.authStatus));
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nXPSecondPasswordListener.onFail(nXToyResult);
                } else {
                    nXPSecondPasswordListener.onSuccess(NXPSecondPassword.this.status);
                }
            }
        });
    }

    public void sendSecondPasswordRegisteredEmail(final NXPSecondPasswordListener nXPSecondPasswordListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToySend2ndPasswordRegisteredEmailRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.3
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("Resend second password confirm email : " + nXToyResult.toString());
                NXToySendSecondPasswordRegisteredEmailResult.ResultSet resultSet = ((NXToySendSecondPasswordRegisteredEmailResult) nXToyResult).result;
                if (resultSet != null) {
                    NXPSecondPassword.this.setStatus(NXPSecondPasswordState.getStateByCode(resultSet.authStatus));
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nXPSecondPasswordListener.onFail(nXToyResult);
                } else {
                    nXPSecondPasswordListener.onSuccess(NXPSecondPassword.this.status);
                }
            }
        });
    }

    public void setSecondPassword(String str, final NXPSecondPasswordListener nXPSecondPasswordListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToySet2ndPasswordRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.5
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("Reset second password : " + nXToyResult.toString());
                NXToySetSecondPasswordResult.ResultSet resultSet = ((NXToySetSecondPasswordResult) nXToyResult).result;
                if (resultSet != null) {
                    NXPSecondPassword.this.setStatus(NXPSecondPasswordState.getStateByCode(resultSet.authStatus));
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nXPSecondPasswordListener.onFail(nXToyResult);
                } else {
                    nXPSecondPasswordListener.onSuccess(NXPSecondPassword.this.status);
                }
            }
        });
    }

    public void unregisterSecondPassword(final NXPSecondPasswordListener nXPSecondPasswordListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyUnregister2ndPasswordRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.4
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("Unregister second password : " + nXToyResult.toString());
                NXToyUnregisterSecondPasswordResult.ResultSet resultSet = ((NXToyUnregisterSecondPasswordResult) nXToyResult).result;
                if (resultSet != null) {
                    NXPSecondPassword.this.setStatus(NXPSecondPasswordState.getStateByCode(resultSet.authStatus));
                    NXPSecondPassword.this.setEmailId("");
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nXPSecondPasswordListener.onFail(nXToyResult);
                } else {
                    nXPSecondPasswordListener.onSuccess(NXPSecondPassword.this.status);
                }
            }
        });
    }

    public void verifySecondPassword(String str, final NXPSecondPasswordListener nXPSecondPasswordListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyVerify2ndPasswordRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.secondpassword.NXPSecondPassword.7
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d("Verify second password : " + nXToyResult.toString());
                NXToyVerifySecondPasswordResult.ResultSet resultSet = ((NXToyVerifySecondPasswordResult) nXToyResult).result;
                if (resultSet != null) {
                    NXPSecondPassword.this.setStatus(NXPSecondPasswordState.getStateByCode(resultSet.authStatus));
                    NXPSecondPassword.this.setRemainedCount(resultSet.remainedCount);
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nXPSecondPasswordListener.onFail(nXToyResult);
                } else {
                    nXPSecondPasswordListener.onSuccess(NXPSecondPassword.this.status);
                }
            }
        });
    }
}
