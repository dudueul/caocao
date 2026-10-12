package kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces;

import android.view.View;
import kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPAccountMenuState {
    View createView(NXPAccountMenuDialog nXPAccountMenuDialog);

    void onBackPressed(NXPAccountMenuDialog nXPAccountMenuDialog);

    void setCloseButtonClickListener(View.OnClickListener onClickListener);
}
