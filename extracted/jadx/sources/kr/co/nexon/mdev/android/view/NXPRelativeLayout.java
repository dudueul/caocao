package kr.co.nexon.mdev.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NXPRelativeLayout extends RelativeLayout {
    public NXPRelativeLayout(Context context) {
        super(context);
    }

    public NXPRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected abstract void initView();

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }
}
