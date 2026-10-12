package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.common.util.CrashUtils;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ContentFrameLayout extends FrameLayout {
    private OnAttachListener mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    public interface OnAttachListener {
        void onAttachedFromWindow();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDecorPadding = new Rect();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void dispatchFitSystemWindows(Rect rect) {
        fitSystemWindows(rect);
    }

    public TypedValue getFixedHeightMajor() {
        if (this.mFixedHeightMajor == null) {
            this.mFixedHeightMajor = new TypedValue();
        }
        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.mFixedHeightMinor == null) {
            this.mFixedHeightMinor = new TypedValue();
        }
        return this.mFixedHeightMinor;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.mFixedWidthMajor == null) {
            this.mFixedWidthMajor = new TypedValue();
        }
        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.mFixedWidthMinor == null) {
            this.mFixedWidthMinor = new TypedValue();
        }
        return this.mFixedWidthMinor;
    }

    public TypedValue getMinWidthMajor() {
        if (this.mMinWidthMajor == null) {
            this.mMinWidthMajor = new TypedValue();
        }
        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        if (this.mMinWidthMinor == null) {
            this.mMinWidthMinor = new TypedValue();
        }
        return this.mMinWidthMinor;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onAttachedFromWindow();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onDetachedFromWindow();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        boolean z = displayMetrics.widthPixels < displayMetrics.heightPixels;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        boolean z2 = false;
        if (mode == Integer.MIN_VALUE) {
            TypedValue typedValue = z ? this.mFixedWidthMinor : this.mFixedWidthMajor;
            if (typedValue != null && typedValue.type != 0) {
                int fraction = 0;
                if (typedValue.type == 5) {
                    fraction = (int) typedValue.getDimension(displayMetrics);
                } else if (typedValue.type == 6) {
                    fraction = (int) typedValue.getFraction(displayMetrics.widthPixels, displayMetrics.widthPixels);
                }
                if (fraction > 0) {
                    i = View.MeasureSpec.makeMeasureSpec(Math.min(fraction - (this.mDecorPadding.left + this.mDecorPadding.right), View.MeasureSpec.getSize(i)), CrashUtils.ErrorDialogData.SUPPRESSED);
                    z2 = true;
                }
            }
        }
        if (mode2 == Integer.MIN_VALUE) {
            TypedValue typedValue2 = z ? this.mFixedHeightMajor : this.mFixedHeightMinor;
            if (typedValue2 != null && typedValue2.type != 0) {
                int fraction2 = 0;
                if (typedValue2.type == 5) {
                    fraction2 = (int) typedValue2.getDimension(displayMetrics);
                } else if (typedValue2.type == 6) {
                    fraction2 = (int) typedValue2.getFraction(displayMetrics.heightPixels, displayMetrics.heightPixels);
                }
                if (fraction2 > 0) {
                    i2 = View.MeasureSpec.makeMeasureSpec(Math.min(fraction2 - (this.mDecorPadding.top + this.mDecorPadding.bottom), View.MeasureSpec.getSize(i2)), CrashUtils.ErrorDialogData.SUPPRESSED);
                }
            }
        }
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        boolean z3 = false;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, CrashUtils.ErrorDialogData.SUPPRESSED);
        if (!z2 && mode == Integer.MIN_VALUE) {
            TypedValue typedValue3 = z ? this.mMinWidthMinor : this.mMinWidthMajor;
            if (typedValue3 != null && typedValue3.type != 0) {
                int fraction3 = 0;
                if (typedValue3.type == 5) {
                    fraction3 = (int) typedValue3.getDimension(displayMetrics);
                } else if (typedValue3.type == 6) {
                    fraction3 = (int) typedValue3.getFraction(displayMetrics.widthPixels, displayMetrics.widthPixels);
                }
                if (fraction3 > 0) {
                    fraction3 -= this.mDecorPadding.left + this.mDecorPadding.right;
                }
                if (measuredWidth < fraction3) {
                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(fraction3, CrashUtils.ErrorDialogData.SUPPRESSED);
                    z3 = true;
                }
            }
        }
        if (z3) {
            super.onMeasure(iMakeMeasureSpec, i2);
        }
    }

    public void setAttachListener(OnAttachListener onAttachListener) {
        this.mAttachListener = onAttachListener;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setDecorPadding(int i, int i2, int i3, int i4) {
        this.mDecorPadding.set(i, i2, i3, i4);
        if (ViewCompat.isLaidOut(this)) {
            requestLayout();
        }
    }
}
