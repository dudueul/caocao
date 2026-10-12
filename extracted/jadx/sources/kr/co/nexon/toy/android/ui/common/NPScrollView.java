package kr.co.nexon.toy.android.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import com.google.android.gms.common.util.CrashUtils;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPScrollView extends ScrollView {
    private Integer maxHeight;
    private boolean scrollable;

    public NPScrollView(Context context) {
        super(context);
        init();
    }

    public NPScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public NPScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setScrollEnabled(true);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.scrollable) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.maxHeight == null) {
            super.onMeasure(i, i2);
            return;
        }
        if (this.maxHeight.intValue() > 0) {
            int size = View.MeasureSpec.getSize(i2);
            switch (View.MeasureSpec.getMode(i2)) {
                case Integer.MIN_VALUE:
                    i2 = View.MeasureSpec.makeMeasureSpec(Math.min(size, this.maxHeight.intValue()), Integer.MIN_VALUE);
                    break;
                case 0:
                    i2 = View.MeasureSpec.makeMeasureSpec(this.maxHeight.intValue(), Integer.MIN_VALUE);
                    break;
                case CrashUtils.ErrorDialogData.SUPPRESSED /* 1073741824 */:
                    i2 = View.MeasureSpec.makeMeasureSpec(Math.min(size, this.maxHeight.intValue()), CrashUtils.ErrorDialogData.SUPPRESSED);
                    break;
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || this.scrollable) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setMaxHeight(int i) {
        this.maxHeight = Integer.valueOf(i);
    }

    public void setScrollEnabled(boolean z) {
        this.scrollable = z;
    }
}
