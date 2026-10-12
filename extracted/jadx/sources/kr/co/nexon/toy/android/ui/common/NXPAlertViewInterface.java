package kr.co.nexon.toy.android.ui.common;

import android.view.View;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPAlertViewInterface {
    void setMessage(String str);

    void setNegativeButtonClickListener(View.OnClickListener onClickListener);

    void setNegativeButtonText(String str);

    void setPositiveButtonClickListener(View.OnClickListener onClickListener);

    void setPositiveButtonText(String str);

    void setTitle(String str);
}
