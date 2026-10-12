package kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract;

import kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPSecondPasswordPendingContract {

    public interface Presenter extends NXPBasePresenter<View> {
        String getEmailId();

        void onResendEmail();

        void showCS();
    }

    public interface View extends NXPBaseView<Presenter> {
        void setCSButtonText(String str);

        void setEmailId(String str);

        void setResendRegisteredEmailDescription(String str, String str2);

        void setTitle(String str);

        void setTopDescription(String str);
    }
}
