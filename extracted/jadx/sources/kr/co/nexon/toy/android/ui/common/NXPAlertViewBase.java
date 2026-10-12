package kr.co.nexon.toy.android.ui.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public abstract class NXPAlertViewBase extends NXPLinearLayout implements NXPAlertViewInterface {
    public NXPAlertViewBase(Context context) {
        super(context);
    }

    public NXPAlertViewBase(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPAlertViewBase(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
