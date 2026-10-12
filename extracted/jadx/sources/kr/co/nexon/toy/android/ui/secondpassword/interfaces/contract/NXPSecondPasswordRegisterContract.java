package kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract;

import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPSecondPasswordRegisterContract {

    public interface Presenter extends NXPBasePresenter<View> {
        String getEmailId();

        NXPSecondPasswordState getStatus();

        void onRegisterSecondPassword(String str, String str2, String str3);

        void showTerms();
    }

    public interface View extends NXPBaseView<Presenter> {
        void setConfirmButtonEnabled(boolean z);

        void setConfirmButtonText(String str);

        void setConfirmPasswordInputEditTextHint(String str);

        void setEmailId(String str);

        void setEmailInputEditTextHint(String str);

        void setEmailInvalidErrorMessage(String str);

        void setEmailTextEditable(boolean z);

        void setPasswordInputEditTextHint(String str);

        void setPasswordInvalidErrorMessage(String str);

        void setTermsAgreementStatus(boolean z);

        void setTermsLayoutVisibility(int i);

        void setTermsTitle(String str);

        void setTitle(String str);
    }
}
