package kr.co.nexon.toy.android.ui.secondpassword.interfaces.view;

import android.app.Fragment;
import android.view.View;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPBaseView<P> {
    View getView();

    void onBackPressed();

    void onDismiss();

    void setFragment(Fragment fragment);

    void setPresenter(P p);
}
