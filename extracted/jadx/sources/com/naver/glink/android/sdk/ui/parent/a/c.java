package com.naver.glink.android.sdk.ui.parent.a;

import android.app.Dialog;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.naver.glink.android.sdk.d;

/* JADX INFO: compiled from: WidgetMoveTouchListener.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c implements View.OnTouchListener {
    private static final int a = 250;
    private final a b;
    private final PointF c;
    private boolean d;
    private boolean e;

    /* JADX INFO: compiled from: WidgetMoveTouchListener.java */
    public static class a {
        private final Window a;
        private int b;
        private View.OnClickListener c;
        private boolean d;
        private boolean e;

        private a(Window window) {
            this.d = true;
            this.e = true;
            this.a = window;
        }

        public a a(int i) {
            this.b = i;
            return this;
        }

        public a a(View.OnClickListener onClickListener) {
            this.c = onClickListener;
            return this;
        }

        public a a(boolean z) {
            this.d = z;
            return this;
        }

        public void a(View view) {
            if (this.a == null || view == null) {
                return;
            }
            view.setOnTouchListener(new c(this));
        }

        public a b(boolean z) {
            this.e = z;
            return this;
        }
    }

    private c(a aVar) {
        this.c = new PointF();
        this.b = aVar;
    }

    public static a a(Dialog dialog) {
        return new a(dialog.getWindow());
    }

    private void a(Point point) {
        WindowManager.LayoutParams attributes = this.b.a.getAttributes();
        if (this.b.d) {
            attributes.x = point.x;
        }
        if (this.b.e) {
            attributes.y = point.y;
        }
        this.b.a.getWindowManager().updateViewLayout(this.b.a.getDecorView(), attributes);
    }

    private void a(View view, int i, int i2) {
        a(b(view, i, i2));
        this.e = true;
    }

    private Point b(View view, int i, int i2) {
        if (this.b.b == 0) {
            return new Point(i, i2);
        }
        return new Point(Math.min(Math.max(i, this.b.b), d.p().b().x - (view.getWidth() + this.b.b)), Math.min(Math.max(i2, this.b.b), d.p().b().y - (view.getHeight() + this.b.b)));
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.b.a == null) {
            return false;
        }
        if (motionEvent.getEventTime() - motionEvent.getDownTime() > 250) {
            this.d = true;
        }
        switch (motionEvent.getAction()) {
            case 0:
                WindowManager.LayoutParams attributes = this.b.a.getAttributes();
                this.c.x = motionEvent.getRawX() - attributes.x;
                this.c.y = motionEvent.getRawY() - attributes.y;
                break;
            case 1:
                if (!this.e && this.b.c != null) {
                    this.b.c.onClick(view);
                }
                this.d = false;
                this.e = false;
                break;
            case 2:
                if (this.d) {
                    a(view, (int) (motionEvent.getRawX() - this.c.x), (int) (motionEvent.getRawY() - this.c.y));
                }
                break;
        }
        return true;
    }
}
