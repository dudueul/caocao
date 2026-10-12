package kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract;

import kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPSecondPasswordSetupContract {

    public interface Presenter extends NXPBasePresenter<View> {
        void onChangeSecondPassword();

        void onUnregisterSecondPassword();
    }

    public interface View extends NXPBaseView<Presenter> {
        void setChangePasswordButtonText(String str);

        void setChangePasswordDescription(String str);

        void setTitle(String str);

        void setUnregisterButtonText(String str);

        void setUnregisterDescription(String str);
    }
}
