package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.gms.common.util.CrashUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface DividerMode {
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = typedArrayObtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = typedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        int i2 = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = tintTypedArrayObtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = tintTypedArrayObtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = tintTypedArrayObtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = tintTypedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        tintTypedArrayObtainStyledAttributes.recycle();
    }

    private void forceUniformHeight(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), CrashUtils.ErrorDialogData.SUPPRESSED);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, iMakeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), CrashUtils.ErrorDialogData.SUPPRESSED);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, iMakeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i + i3, i2 + i4);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    void drawDividersHorizontal(Canvas canvas) {
        int left;
        int virtualChildCount = getVirtualChildCount();
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                drawVerticalDivider(canvas, zIsLayoutRtl ? virtualChildAt.getRight() + layoutParams.rightMargin : (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                left = zIsLayoutRtl ? getPaddingLeft() : (getWidth() - getPaddingRight()) - this.mDividerWidth;
            } else {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                left = zIsLayoutRtl ? (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth : virtualChildAt2.getRight() + layoutParams2.rightMargin;
            }
            drawVerticalDivider(canvas, left);
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            drawHorizontalDivider(canvas, virtualChildAt2 == null ? (getHeight() - getPaddingBottom()) - this.mDividerHeight : virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -2);
        }
        if (this.mOrientation == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        if (getChildCount() <= this.mBaselineAlignedChildIndex) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(this.mBaselineAlignedChildIndex);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex != 0) {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
            return -1;
        }
        int bottom = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
            switch (i) {
                case 16:
                    bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                    break;
                case 80:
                    bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    break;
            }
        }
        return ((LayoutParams) childAt.getLayoutParams()).topMargin + bottom + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    protected boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        boolean z = false;
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                z = true;
                break;
            }
        }
        return z;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    void layoutHorizontal(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int measuredHeight;
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i5 = i4 - i2;
        int paddingBottom = i5 - getPaddingBottom();
        int paddingBottom2 = (i5 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i6 = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i7 = this.mGravity & 112;
        boolean z = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        switch (GravityCompat.getAbsoluteGravity(i6, ViewCompat.getLayoutDirection(this))) {
            case 1:
                paddingLeft = getPaddingLeft() + (((i3 - i) - this.mTotalLength) / 2);
                break;
            case 5:
                paddingLeft = ((getPaddingLeft() + i3) - i) - this.mTotalLength;
                break;
            default:
                paddingLeft = getPaddingLeft();
                break;
        }
        int i8 = 0;
        int i9 = 1;
        if (zIsLayoutRtl) {
            i8 = virtualChildCount - 1;
            i9 = -1;
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            int i10 = i8 + (i9 * childrenSkipCount);
            View virtualChildAt = getVirtualChildAt(i10);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i10);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight2 = virtualChildAt.getMeasuredHeight();
                int baseline = -1;
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (z && layoutParams.height != -1) {
                    baseline = virtualChildAt.getBaseline();
                }
                int i11 = layoutParams.gravity;
                if (i11 < 0) {
                    i11 = i7;
                }
                switch (i11 & 112) {
                    case 16:
                        measuredHeight = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                        break;
                    case 48:
                        measuredHeight = paddingTop + layoutParams.topMargin;
                        if (baseline != -1) {
                            measuredHeight += iArr[1] - baseline;
                        }
                        break;
                    case 80:
                        measuredHeight = (paddingBottom - measuredHeight2) - layoutParams.bottomMargin;
                        if (baseline != -1) {
                            measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                        }
                        break;
                    default:
                        measuredHeight = paddingTop;
                        break;
                }
                if (hasDividerBeforeChildAt(i10)) {
                    paddingLeft += this.mDividerWidth;
                }
                int i12 = paddingLeft + layoutParams.leftMargin;
                setChildFrame(virtualChildAt, i12 + getLocationOffset(virtualChildAt), measuredHeight, measuredWidth, measuredHeight2);
                paddingLeft = i12 + layoutParams.rightMargin + measuredWidth + getNextLocationOffset(virtualChildAt);
                childrenSkipCount += getChildrenSkipCount(virtualChildAt, i10);
            }
            childrenSkipCount++;
        }
    }

    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int i5;
        int paddingLeft = getPaddingLeft();
        int i6 = i3 - i;
        int paddingRight = i6 - getPaddingRight();
        int paddingRight2 = (i6 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i7 = this.mGravity & 112;
        int i8 = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        switch (i7) {
            case 16:
                paddingTop = getPaddingTop() + (((i4 - i2) - this.mTotalLength) / 2);
                break;
            case 80:
                paddingTop = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
                break;
            default:
                paddingTop = getPaddingTop();
                break;
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(childrenSkipCount);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i9 = layoutParams.gravity;
                if (i9 < 0) {
                    i9 = i8;
                }
                switch (GravityCompat.getAbsoluteGravity(i9, ViewCompat.getLayoutDirection(this)) & 7) {
                    case 1:
                        i5 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                        break;
                    case 5:
                        i5 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                        break;
                    default:
                        i5 = paddingLeft + layoutParams.leftMargin;
                        break;
                }
                if (hasDividerBeforeChildAt(childrenSkipCount)) {
                    paddingTop += this.mDividerHeight;
                }
                int i10 = paddingTop + layoutParams.topMargin;
                setChildFrame(virtualChildAt, i5, i10 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                paddingTop = i10 + layoutParams.bottomMargin + measuredHeight + getNextLocationOffset(virtualChildAt);
                childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
            }
            childrenSkipCount++;
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    void measureHorizontal(int i, int i2) {
        int baseline;
        int baseline2;
        this.mTotalLength = 0;
        int iMax = 0;
        int iCombineMeasuredStates = 0;
        int iMax2 = 0;
        int iMax3 = 0;
        boolean z = true;
        float f = 0.0f;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        boolean z2 = false;
        boolean z3 = false;
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        iArr[3] = -1;
        iArr[2] = -1;
        iArr[1] = -1;
        iArr[0] = -1;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        boolean z4 = this.mBaselineAligned;
        boolean z5 = this.mUseLargestChild;
        boolean z6 = mode == 1073741824;
        int iMax4 = 0;
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount);
            } else if (virtualChildAt.getVisibility() == 8) {
                childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
            } else {
                if (hasDividerBeforeChildAt(childrenSkipCount)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                f += layoutParams.weight;
                if (mode == 1073741824 && layoutParams.width == 0 && layoutParams.weight > 0.0f) {
                    if (z6) {
                        this.mTotalLength += layoutParams.leftMargin + layoutParams.rightMargin;
                    } else {
                        int i3 = this.mTotalLength;
                        this.mTotalLength = Math.max(i3, layoutParams.leftMargin + i3 + layoutParams.rightMargin);
                    }
                    if (z4) {
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                        virtualChildAt.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    } else {
                        z3 = true;
                    }
                } else {
                    int i4 = Integer.MIN_VALUE;
                    if (layoutParams.width == 0 && layoutParams.weight > 0.0f) {
                        i4 = 0;
                        layoutParams.width = -2;
                    }
                    measureChildBeforeLayout(virtualChildAt, childrenSkipCount, i, f == 0.0f ? this.mTotalLength : 0, i2, 0);
                    if (i4 != Integer.MIN_VALUE) {
                        layoutParams.width = i4;
                    }
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    if (z6) {
                        this.mTotalLength += layoutParams.leftMargin + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
                    } else {
                        int i5 = this.mTotalLength;
                        this.mTotalLength = Math.max(i5, i5 + measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt));
                    }
                    if (z5) {
                        iMax4 = Math.max(measuredWidth, iMax4);
                    }
                }
                boolean z7 = false;
                if (mode2 != 1073741824 && layoutParams.height == -1) {
                    z2 = true;
                    z7 = true;
                }
                int i6 = layoutParams.topMargin + layoutParams.bottomMargin;
                int measuredHeight = virtualChildAt.getMeasuredHeight() + i6;
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt.getMeasuredState());
                if (z4 && (baseline2 = virtualChildAt.getBaseline()) != -1) {
                    int i7 = ((((layoutParams.gravity < 0 ? this.mGravity : layoutParams.gravity) & 112) >> 4) & (-2)) >> 1;
                    iArr[i7] = Math.max(iArr[i7], baseline2);
                    iArr2[i7] = Math.max(iArr2[i7], measuredHeight - baseline2);
                }
                iMax = Math.max(iMax, measuredHeight);
                z = z && layoutParams.height == -1;
                if (layoutParams.weight > 0.0f) {
                    if (!z7) {
                        i6 = measuredHeight;
                    }
                    iMax3 = Math.max(iMax3, i6);
                } else {
                    if (!z7) {
                        i6 = measuredHeight;
                    }
                    iMax2 = Math.max(iMax2, i6);
                }
                childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
            }
            childrenSkipCount++;
        }
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        if (iArr[1] != -1 || iArr[0] != -1 || iArr[2] != -1 || iArr[3] != -1) {
            iMax = Math.max(iMax, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
        }
        if (z5 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            int childrenSkipCount2 = 0;
            while (childrenSkipCount2 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(childrenSkipCount2);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(childrenSkipCount2);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    childrenSkipCount2 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount2);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                    if (z6) {
                        this.mTotalLength += layoutParams2.leftMargin + iMax4 + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2);
                    } else {
                        int i8 = this.mTotalLength;
                        this.mTotalLength = Math.max(i8, i8 + iMax4 + layoutParams2.leftMargin + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2));
                    }
                }
                childrenSkipCount2++;
            }
        }
        this.mTotalLength += getPaddingLeft() + getPaddingRight();
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumWidth()), i, 0);
        int i9 = (iResolveSizeAndState & ViewCompat.MEASURED_SIZE_MASK) - this.mTotalLength;
        if (z3 || (i9 != 0 && f > 0.0f)) {
            float f2 = this.mWeightSum > 0.0f ? this.mWeightSum : f;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iMax = -1;
            this.mTotalLength = 0;
            for (int i10 = 0; i10 < virtualChildCount; i10++) {
                View virtualChildAt3 = getVirtualChildAt(i10);
                if (virtualChildAt3 != null && virtualChildAt3.getVisibility() != 8) {
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f3 = layoutParams3.weight;
                    if (f3 > 0.0f) {
                        int i11 = (int) ((i9 * f3) / f2);
                        f2 -= f3;
                        i9 -= i11;
                        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + layoutParams3.topMargin + layoutParams3.bottomMargin, layoutParams3.height);
                        if (layoutParams3.width == 0 && mode == 1073741824) {
                            if (i11 <= 0) {
                                i11 = 0;
                            }
                            virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i11, CrashUtils.ErrorDialogData.SUPPRESSED), childMeasureSpec);
                        } else {
                            int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i11;
                            if (measuredWidth2 < 0) {
                                measuredWidth2 = 0;
                            }
                            virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, CrashUtils.ErrorDialogData.SUPPRESSED), childMeasureSpec);
                        }
                        iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt3.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                    }
                    if (z6) {
                        this.mTotalLength += virtualChildAt3.getMeasuredWidth() + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3);
                    } else {
                        int i12 = this.mTotalLength;
                        this.mTotalLength = Math.max(i12, virtualChildAt3.getMeasuredWidth() + i12 + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3));
                    }
                    boolean z8 = mode2 != 1073741824 && layoutParams3.height == -1;
                    int i13 = layoutParams3.topMargin + layoutParams3.bottomMargin;
                    int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i13;
                    iMax = Math.max(iMax, measuredHeight2);
                    if (!z8) {
                        i13 = measuredHeight2;
                    }
                    iMax2 = Math.max(iMax2, i13);
                    z = z && layoutParams3.height == -1;
                    if (z4 && (baseline = virtualChildAt3.getBaseline()) != -1) {
                        int i14 = ((((layoutParams3.gravity < 0 ? this.mGravity : layoutParams3.gravity) & 112) >> 4) & (-2)) >> 1;
                        iArr[i14] = Math.max(iArr[i14], baseline);
                        iArr2[i14] = Math.max(iArr2[i14], measuredHeight2 - baseline);
                    }
                }
            }
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            if (iArr[1] != -1 || iArr[0] != -1 || iArr[2] != -1 || iArr[3] != -1) {
                iMax = Math.max(iMax, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
            }
        } else {
            iMax2 = Math.max(iMax2, iMax3);
            if (z5 && mode != 1073741824) {
                for (int i15 = 0; i15 < virtualChildCount; i15++) {
                    View virtualChildAt4 = getVirtualChildAt(i15);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(iMax4, CrashUtils.ErrorDialogData.SUPPRESSED), View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredHeight(), CrashUtils.ErrorDialogData.SUPPRESSED));
                    }
                }
            }
        }
        if (!z && mode2 != 1073741824) {
            iMax = iMax2;
        }
        setMeasuredDimension(((-16777216) & iCombineMeasuredStates) | iResolveSizeAndState, View.resolveSizeAndState(Math.max(iMax + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, iCombineMeasuredStates << 16));
        if (z2) {
            forceUniformHeight(virtualChildCount, i);
        }
    }

    int measureNullChild(int i) {
        return 0;
    }

    void measureVertical(int i, int i2) {
        this.mTotalLength = 0;
        int iMax = 0;
        int iCombineMeasuredStates = 0;
        int iMax2 = 0;
        int iMax3 = 0;
        boolean z = true;
        float f = 0.0f;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        boolean z2 = false;
        boolean z3 = false;
        int i3 = this.mBaselineAlignedChildIndex;
        boolean z4 = this.mUseLargestChild;
        int iMax4 = 0;
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount);
            } else if (virtualChildAt.getVisibility() == 8) {
                childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
            } else {
                if (hasDividerBeforeChildAt(childrenSkipCount)) {
                    this.mTotalLength += this.mDividerHeight;
                }
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                f += layoutParams.weight;
                if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.weight > 0.0f) {
                    int i4 = this.mTotalLength;
                    this.mTotalLength = Math.max(i4, layoutParams.topMargin + i4 + layoutParams.bottomMargin);
                    z3 = true;
                } else {
                    int i5 = Integer.MIN_VALUE;
                    if (layoutParams.height == 0 && layoutParams.weight > 0.0f) {
                        i5 = 0;
                        layoutParams.height = -2;
                    }
                    measureChildBeforeLayout(virtualChildAt, childrenSkipCount, i, 0, i2, f == 0.0f ? this.mTotalLength : 0);
                    if (i5 != Integer.MIN_VALUE) {
                        layoutParams.height = i5;
                    }
                    int measuredHeight = virtualChildAt.getMeasuredHeight();
                    int i6 = this.mTotalLength;
                    this.mTotalLength = Math.max(i6, i6 + measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt));
                    if (z4) {
                        iMax4 = Math.max(measuredHeight, iMax4);
                    }
                }
                if (i3 >= 0 && i3 == childrenSkipCount + 1) {
                    this.mBaselineChildTop = this.mTotalLength;
                }
                if (childrenSkipCount < i3 && layoutParams.weight > 0.0f) {
                    throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                }
                boolean z5 = false;
                if (mode != 1073741824 && layoutParams.width == -1) {
                    z2 = true;
                    z5 = true;
                }
                int i7 = layoutParams.leftMargin + layoutParams.rightMargin;
                int measuredWidth = virtualChildAt.getMeasuredWidth() + i7;
                iMax = Math.max(iMax, measuredWidth);
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt.getMeasuredState());
                z = z && layoutParams.width == -1;
                if (layoutParams.weight > 0.0f) {
                    if (!z5) {
                        i7 = measuredWidth;
                    }
                    iMax3 = Math.max(iMax3, i7);
                } else {
                    if (!z5) {
                        i7 = measuredWidth;
                    }
                    iMax2 = Math.max(iMax2, i7);
                }
                childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
            }
            childrenSkipCount++;
        }
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerHeight;
        }
        if (z4 && (mode2 == Integer.MIN_VALUE || mode2 == 0)) {
            this.mTotalLength = 0;
            int childrenSkipCount2 = 0;
            while (childrenSkipCount2 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(childrenSkipCount2);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(childrenSkipCount2);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    childrenSkipCount2 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount2);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                    int i8 = this.mTotalLength;
                    this.mTotalLength = Math.max(i8, i8 + iMax4 + layoutParams2.topMargin + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt2));
                }
                childrenSkipCount2++;
            }
        }
        this.mTotalLength += getPaddingTop() + getPaddingBottom();
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), i2, 0);
        int i9 = (iResolveSizeAndState & ViewCompat.MEASURED_SIZE_MASK) - this.mTotalLength;
        if (z3 || (i9 != 0 && f > 0.0f)) {
            float f2 = this.mWeightSum > 0.0f ? this.mWeightSum : f;
            this.mTotalLength = 0;
            for (int i10 = 0; i10 < virtualChildCount; i10++) {
                View virtualChildAt3 = getVirtualChildAt(i10);
                if (virtualChildAt3.getVisibility() != 8) {
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f3 = layoutParams3.weight;
                    if (f3 > 0.0f) {
                        int i11 = (int) ((i9 * f3) / f2);
                        f2 -= f3;
                        i9 -= i11;
                        int childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams3.leftMargin + layoutParams3.rightMargin, layoutParams3.width);
                        if (layoutParams3.height == 0 && mode2 == 1073741824) {
                            if (i11 <= 0) {
                                i11 = 0;
                            }
                            virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i11, CrashUtils.ErrorDialogData.SUPPRESSED));
                        } else {
                            int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i11;
                            if (measuredHeight2 < 0) {
                                measuredHeight2 = 0;
                            }
                            virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight2, CrashUtils.ErrorDialogData.SUPPRESSED));
                        }
                        iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                    }
                    int i12 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                    int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i12;
                    iMax = Math.max(iMax, measuredWidth2);
                    if (!(mode != 1073741824 && layoutParams3.width == -1)) {
                        i12 = measuredWidth2;
                    }
                    iMax2 = Math.max(iMax2, i12);
                    z = z && layoutParams3.width == -1;
                    int i13 = this.mTotalLength;
                    this.mTotalLength = Math.max(i13, virtualChildAt3.getMeasuredHeight() + i13 + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(virtualChildAt3));
                }
            }
            this.mTotalLength += getPaddingTop() + getPaddingBottom();
        } else {
            iMax2 = Math.max(iMax2, iMax3);
            if (z4 && mode2 != 1073741824) {
                for (int i14 = 0; i14 < virtualChildCount; i14++) {
                    View virtualChildAt4 = getVirtualChildAt(i14);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredWidth(), CrashUtils.ErrorDialogData.SUPPRESSED), View.MeasureSpec.makeMeasureSpec(iMax4, CrashUtils.ErrorDialogData.SUPPRESSED));
                    }
                }
            }
        }
        if (!z && mode != 1073741824) {
            iMax = iMax2;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, iCombineMeasuredStates), iResolveSizeAndState);
        if (z2) {
            forceUniformWidth(virtualChildCount, i2);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if ((this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) != i2) {
            this.mGravity = (this.mGravity & (-8388616)) | i2;
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        if ((this.mGravity & 112) != i2) {
            this.mGravity = (this.mGravity & (-113)) | i2;
            requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
