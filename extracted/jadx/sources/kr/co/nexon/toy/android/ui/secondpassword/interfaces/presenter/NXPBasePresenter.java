package kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter;

import android.view.View;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.NXPActionListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPBasePresenter<V> {
    void onCancel();

    void onFail(NXToyResult nXToyResult);

    void onSuccess(NXToyResult nXToyResult);

    void setActionListener(NXPActionListener nXPActionListener);

    void setView(V v);

    void showMessageBox(String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2);
}
