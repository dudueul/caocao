package kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract;

import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPSecondPasswordVerifyContract {

    public interface Presenter extends NXPBasePresenter<View> {
        String getEmailId();

        NXPSecondPasswordState getStatus();

        void onVerifySecondPassword(String str);

        void showChangePasswordView();
    }

    public interface View extends NXPBaseView<Presenter> {
        void hideSoftKeyboard();

        void resetInputPassword();

        void setChangePasswordButtonText(String str);

        void setDefaultDisplayUI(int i, int i2);

        void setErrorText(String str);

        void setSubDescriptionText(String str);

        void setTitle(String str);

        void showSoftKeyboard();
    }
}
