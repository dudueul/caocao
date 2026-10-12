package com.naver.glink.android.sdk.ui.widget.dragviewer;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.naver.glink.android.sdk.R;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DragView extends RelativeLayout implements com.naver.glink.android.sdk.ui.viewer.b.b.a {
    private View a;
    private boolean b;
    private int c;
    private int d;
    private float e;
    private float f;
    private float g;
    private float h;
    private boolean i;
    private a j;
    private DragState k;

    public enum DragState {
        NONE,
        DRAGGING,
        END
    }

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            DragView.this.requestLayout();
            DragView.this.a.setAlpha(DragView.this.getBackgroundAlpha());
        }
    }

    class b extends Timer {
        private final int b = 6;
        private final int c = b();
        private TimerTask d;

        b(final Handler handler) {
            this.d = new TimerTask() { // from class: com.naver.glink.android.sdk.ui.widget.dragviewer.DragView.b.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Message message = new Message();
                    if (DragView.this.d > 0) {
                        DragView.this.d -= b.this.c;
                    } else if (DragView.this.d < 0) {
                        DragView.this.d += b.this.c;
                    }
                    if (DragView.this.c > 0) {
                        DragView.this.c -= b.this.c;
                    } else if (DragView.this.c < 0) {
                        DragView.this.c += b.this.c;
                    }
                    handler.sendMessage(message);
                    DragView.this.c = Math.abs(DragView.this.c) <= b.this.c ? 0 : DragView.this.c;
                    if (DragView.this.d == 0 && DragView.this.c == 0) {
                        cancel();
                        DragView.this.a();
                    }
                }
            };
        }

        private int b() {
            int dimensionPixelSize = DragView.this.getResources().getDimensionPixelSize(R.dimen.viewer_animation_speed);
            if (dimensionPixelSize > 6) {
                return 6;
            }
            return dimensionPixelSize;
        }

        public void a() {
            schedule(this.d, 0L, 1L);
        }
    }

    public static class c extends com.naver.plug.android.core.a.a {
        public final DragState a;

        c(DragState dragState) {
            this.a = dragState;
        }
    }

    public DragView(Context context) {
        super(context);
        this.c = 0;
        this.d = 0;
        this.j = new a();
        this.k = DragState.NONE;
        a(context);
    }

    public DragView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 0;
        this.d = 0;
        this.j = new a();
        this.k = DragState.NONE;
        a(context);
    }

    public DragView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0;
        this.d = 0;
        this.j = new a();
        this.k = DragState.NONE;
        a(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.c = 0;
        this.g = 0.0f;
        this.h = 0.0f;
        this.d = 0;
        this.b = false;
        setCurrentState(DragState.NONE);
    }

    private void a(Context context) {
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.a = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.a.setBackground(new ColorDrawable(getResources().getColor(R.color.viewer_bg)));
        addView(this.a, layoutParams);
        setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.widget.dragviewer.DragView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    private void b() {
        new b(this.j).a();
    }

    private void c() {
        this.a.setAlpha(0.0f);
        setCurrentState(DragState.END);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getBackgroundAlpha() {
        if (this.d == 0 && this.c == 0) {
            return 1.0f;
        }
        double dAbs = 1.0d - ((Math.abs(this.c) > 0 ? ((double) (Math.abs(this.c) * 100)) / (((double) getMeasuredHeight()) * 0.1d) : 1.0d) / 100.0d);
        if (dAbs < 0.01d) {
            dAbs = 0.0d;
        }
        return (float) dAbs;
    }

    private View getContentChildView() {
        return getChildAt(1);
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.b.b.a
    public void a(float f) {
        this.i = f > 1.0f || ((double) f) < 0.99d;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.i) {
                    return super.onInterceptTouchEvent(motionEvent);
                }
                this.e = motionEvent.getRawX();
                this.f = motionEvent.getRawY();
                break;
            case 1:
                if (Math.abs(this.c) >= ((int) (((double) getMeasuredHeight()) * 0.15d))) {
                    c();
                    return super.onInterceptTouchEvent(motionEvent);
                }
                b();
                requestLayout();
                return super.onInterceptTouchEvent(motionEvent);
            case 2:
                if (this.i) {
                    return super.onInterceptTouchEvent(motionEvent);
                }
                this.b = true;
                if (this.g == 0.0f) {
                    this.g = motionEvent.getRawY();
                }
                if (this.h == 0.0f) {
                    this.h = motionEvent.getRawX();
                }
                if ((((double) Math.abs(this.e - motionEvent.getRawX())) * 1.5d < Math.abs(this.f - motionEvent.getRawY()) && Math.abs(this.f - motionEvent.getRawY()) > ((double) getMeasuredHeight()) * 0.02d && this.f < ((double) getMeasuredHeight()) * 0.85d) || Math.abs(this.f - motionEvent.getRawY()) > ((double) getMeasuredHeight()) * 0.3d) {
                    setCurrentState(DragState.DRAGGING);
                }
                if (this.k == DragState.DRAGGING) {
                    this.c += (int) (motionEvent.getRawY() - this.g);
                    this.g = motionEvent.getRawY();
                    this.h = motionEvent.getRawX();
                    this.a.setAlpha(getBackgroundAlpha());
                }
                break;
        }
        requestLayout();
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth;
        super.onLayout(z, i, i2, i3, i4);
        View contentChildView = getContentChildView();
        if (contentChildView == null) {
            return;
        }
        if (this.b) {
            measuredWidth = ((double) getMeasuredWidth()) * 0.06d < ((double) Math.abs(this.c)) ? (int) (((double) getMeasuredWidth()) * 0.06d) : Math.abs(this.c);
        } else {
            measuredWidth = 0;
        }
        int i5 = measuredWidth + 0 + this.d;
        int measuredHeight = ((getMeasuredHeight() / 2) - (contentChildView.getMeasuredHeight() / 2)) + this.c;
        int measuredWidth2 = (getMeasuredWidth() - measuredWidth) + this.d;
        contentChildView.layout(0, measuredHeight, getMeasuredWidth(), ((getMeasuredHeight() / 2) - (contentChildView.getMeasuredHeight() / 2)) + this.c + contentChildView.getMeasuredHeight());
    }

    public void setCurrentState(final DragState dragState) {
        if (this.k == dragState) {
            return;
        }
        this.k = dragState;
        post(new Runnable() { // from class: com.naver.glink.android.sdk.ui.widget.dragviewer.DragView.2
            @Override // java.lang.Runnable
            public void run() {
                com.naver.glink.android.sdk.a.a.b.c(new c(dragState));
            }
        });
    }
}
