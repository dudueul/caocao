package android.support.v7.widget;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.appcompat.R;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class DropDownListView extends ListViewCompat {
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private ListViewAutoScrollHelper mScrollHelper;

    public DropDownListView(Context context, boolean z) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.mHijackFocus = z;
        setCacheColorHint(0);
    }

    private void clearPressedItem() {
        this.mDrawsInPressedState = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        if (this.mClickAnimation != null) {
            this.mClickAnimation.cancel();
            this.mClickAnimation = null;
        }
    }

    private void clickPressedItem(View view, int i) {
        performItemClick(view, i, getItemIdAtPosition(i));
    }

    private void setPressedItem(View view, int i, float f, float f2) {
        View childAt;
        this.mDrawsInPressedState = true;
        if (Build.VERSION.SDK_INT >= 21) {
            drawableHotspotChanged(f, f2);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        if (this.mMotionPosition != -1 && (childAt = getChildAt(this.mMotionPosition - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.mMotionPosition = i;
        float left = f - view.getLeft();
        float top = f2 - view.getTop();
        if (Build.VERSION.SDK_INT >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        positionSelectorLikeTouchCompat(i, view, f, f2);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.mHijackFocus || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.mHijackFocus || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.mHijackFocus || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
    }

    public boolean onForwardedEvent(MotionEvent motionEvent, int i) {
        boolean z = true;
        boolean z2 = false;
        int actionMasked = motionEvent.getActionMasked();
        switch (actionMasked) {
            case 1:
                z = false;
            case 2:
                int iFindPointerIndex = motionEvent.findPointerIndex(i);
                if (iFindPointerIndex >= 0) {
                    int x = (int) motionEvent.getX(iFindPointerIndex);
                    int y = (int) motionEvent.getY(iFindPointerIndex);
                    int iPointToPosition = pointToPosition(x, y);
                    if (iPointToPosition != -1) {
                        View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                        setPressedItem(childAt, iPointToPosition, x, y);
                        z = true;
                        if (actionMasked == 1) {
                            clickPressedItem(childAt, iPointToPosition);
                        }
                    } else {
                        z2 = true;
                    }
                } else {
                    z = false;
                }
                break;
            case 3:
                z = false;
                break;
        }
        if (!z || z2) {
            clearPressedItem();
        }
        if (z) {
            if (this.mScrollHelper == null) {
                this.mScrollHelper = new ListViewAutoScrollHelper(this);
            }
            this.mScrollHelper.setEnabled(true);
            this.mScrollHelper.onTouch(this, motionEvent);
        } else if (this.mScrollHelper != null) {
            this.mScrollHelper.setEnabled(false);
        }
        return z;
    }

    void setListSelectionHidden(boolean z) {
        this.mListSelectionHidden = z;
    }

    @Override // android.support.v7.widget.ListViewCompat
    protected boolean touchModeDrawsInPressedStateCompat() {
        return this.mDrawsInPressedState || super.touchModeDrawsInPressedStateCompat();
    }
}
