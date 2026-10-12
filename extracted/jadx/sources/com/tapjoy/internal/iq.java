package com.tapjoy.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class iq extends RelativeLayout implements View.OnClickListener {
    private boolean a;
    private float b;
    private View c;
    private View d;
    private FrameLayout e;
    private ImageView f;
    private in g;
    private hu h;
    private a i;

    public interface a {
        void a();

        void a(hs hsVar);

        void b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public iq(Context context, hu huVar, a aVar) {
        super(context);
        boolean z = true;
        this.b = 1.0f;
        this.h = huVar;
        this.i = aVar;
        Context context2 = getContext();
        this.c = new View(context2);
        this.c.setId(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(13);
        addView(this.c, layoutParams);
        this.d = new View(context2);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(0, 0);
        layoutParams2.addRule(13);
        addView(this.d, layoutParams2);
        this.e = new FrameLayout(context2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(0, 0);
        layoutParams3.addRule(13);
        addView(this.e, layoutParams3);
        this.f = new ImageView(context2);
        this.f.setOnClickListener(this);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(0, 0);
        layoutParams4.addRule(7, this.c.getId());
        layoutParams4.addRule(6, this.c.getId());
        addView(this.f, layoutParams4);
        if (this.h.m != null) {
            hv hvVar = this.h.m;
            if (hvVar.a == null || (hvVar.b == null && hvVar.c == null)) {
                z = false;
            }
            if (z) {
                this.g = new in(context2);
                this.g.setOnClickListener(this);
                RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(0, 0);
                layoutParams5.addRule(5, this.d.getId());
                layoutParams5.addRule(8, this.d.getId());
                addView(this.g, layoutParams5);
            }
        }
        this.f.setImageBitmap(huVar.c.b);
        if (this.g == null || huVar.m == null || huVar.m.a == null) {
            return;
        }
        this.g.setImageBitmap(huVar.m.a.b);
    }

    private int a(int i) {
        return (int) (i * this.b);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view == this.f) {
            this.i.a();
            return;
        }
        if (view == null || view != this.g) {
            if (view.getTag() instanceof hs) {
                this.i.a((hs) view.getTag());
            }
        } else {
            in inVar = this.g;
            inVar.a = !inVar.a;
            inVar.a();
            inVar.invalidate();
            this.i.b();
        }
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    /* JADX WARN: Code duplicated, block: B:52:0x017c  */
    @Override // android.widget.RelativeLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        Point point;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.a) {
            this.b = Math.min(size / 480.0f, size2 / 320.0f);
        } else {
            this.b = Math.min(size / 320.0f, size2 / 480.0f);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.c.getLayoutParams();
        layoutParams.width = a(this.a ? com.naver.glink.android.sdk.ui.record.c.b : 320);
        layoutParams.height = a(this.a ? 320 : com.naver.glink.android.sdk.ui.record.c.b);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
        layoutParams2.width = a(this.a ? 448 : 290);
        layoutParams2.height = a(this.a ? 290 : 448);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
        layoutParams3.width = layoutParams2.width;
        layoutParams3.height = layoutParams2.height;
        for (View view : ac.a(this.e)) {
            FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) view.getLayoutParams();
            Rect rect = ((hs) view.getTag()).a;
            layoutParams4.width = a(rect.width());
            layoutParams4.height = a(rect.height());
            layoutParams4.leftMargin = a(rect.left);
            layoutParams4.topMargin = a(rect.top);
        }
        int iA = a(0);
        this.f.setPadding(iA, iA, iA, iA);
        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
        layoutParams5.width = a(30);
        layoutParams5.height = layoutParams5.width;
        layoutParams5.rightMargin = (-iA) + a(this.h.d.x);
        layoutParams5.topMargin = (-iA) + a(this.h.d.y);
        if (this.g != null) {
            int iA2 = a(this.a ? 16 : 15);
            int iA3 = a(this.a ? 15 : 16);
            this.g.setPadding(iA, iA, iA, iA);
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.g.getLayoutParams();
            layoutParams6.width = a(26);
            layoutParams6.height = layoutParams6.width;
            if (this.h.m == null) {
                i3 = 0;
                i4 = 0;
            } else {
                if (this.a) {
                    hv hvVar = this.h.m;
                    point = hvVar.b == null ? hvVar.c : hvVar.b;
                } else {
                    hv hvVar2 = this.h.m;
                    point = hvVar2.c == null ? hvVar2.b : hvVar2.c;
                }
                if (point != null) {
                    int i5 = point.x;
                    i3 = point.y;
                    i4 = i5;
                } else {
                    i3 = 0;
                    i4 = 0;
                }
            }
            layoutParams6.leftMargin = a(i4) + iA2;
            layoutParams6.topMargin = a(i3) + iA3;
        }
        super.onMeasure(i, i2);
    }

    public final void setLandscape(boolean z) {
        Bitmap bitmap;
        Bitmap bitmap2;
        ArrayList<hs> arrayList;
        this.a = z;
        if (z) {
            bitmap = this.h.b.b;
            bitmap2 = this.h.f.b;
            arrayList = this.h.j;
        } else {
            bitmap = this.h.a.b;
            bitmap2 = this.h.e.b;
            arrayList = this.h.i;
        }
        ab.a(this.c, new BitmapDrawable((Resources) null, bitmap));
        ab.a(this.d, new BitmapDrawable((Resources) null, bitmap2));
        if (this.e.getChildCount() > 0) {
            this.e.removeAllViews();
        }
        Context context = getContext();
        for (hs hsVar : arrayList) {
            View view = new View(context);
            view.setTag(hsVar);
            view.setOnClickListener(this);
            this.e.addView(view, new FrameLayout.LayoutParams(0, 0, 51));
        }
    }
}
