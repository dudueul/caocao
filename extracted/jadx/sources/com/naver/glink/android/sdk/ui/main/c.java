package com.naver.glink.android.sdk.ui.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.widget.TransparentGlinkLayout;

/* JADX INFO: compiled from: TransparentViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class c {
    private static final l a = l.a(c.class.getSimpleName());
    private final View b;
    private final View c;
    private final SeekBar d;
    private final ViewGroup e;
    private final TransparentGlinkLayout f;
    private final Point g = new Point();

    c(View view) {
        this.b = view;
        this.c = view.findViewById(R.id.plug_view);
        this.d = (SeekBar) view.findViewById(R.id.transparent_seekbar);
        this.e = (ViewGroup) view.findViewById(R.id.glink_touch_layout);
        this.f = (TransparentGlinkLayout) view.findViewById(R.id.transparent_glink);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, MotionEvent motionEvent) {
        activity.onWindowFocusChanged(true);
        activity.dispatchGenericMotionEvent(motionEvent);
    }

    private boolean a() {
        return this.d.getProgress() < this.d.getMax();
    }

    private boolean a(Context context) {
        return p.f(context) && d.g() && !d.h(context);
    }

    private int b() {
        return (this.d.getProgress() * 255) / this.d.getMax();
    }

    private Drawable b(Context context, com.naver.glink.android.sdk.configure.c cVar) {
        try {
            return new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) ContextCompat.getDrawable(context, R.drawable.cf_btn_opacity_normal)).getBitmap(), cVar.a(31), cVar.a(37), true));
        } catch (Exception e) {
            a.a(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Activity activity, MotionEvent motionEvent) {
        activity.onWindowFocusChanged(true);
        if (Build.VERSION.SDK_INT < 19) {
            if (Build.VERSION.SDK_INT >= 17) {
                activity.dispatchTouchEvent(motionEvent);
            }
        } else {
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            if (this.g != null && this.g.x > 0) {
                motionEventObtain.offsetLocation(this.g.x, this.g.y);
            }
            activity.getWindow().injectInputEvent(motionEventObtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        ((Activity) context).onWindowFocusChanged(true);
        this.f.setWindowAlpha(b());
        this.c.setEnabled(a());
        this.c.setClickable(a());
        this.b.setBackgroundColor(a() ? 0 : ContextCompat.getColor(context, R.color.black2_opacity_50));
    }

    void a(final Context context, com.naver.glink.android.sdk.configure.c cVar) {
        b(context);
        if (!a(context)) {
            this.d.setVisibility(4);
            return;
        }
        this.e.setEnabled(false);
        this.e.setClickable(false);
        this.e.setLongClickable(false);
        this.d.setVisibility(0);
        d.e().a(this.d);
        cVar.a(this.d, -1, 37);
        Drawable drawableB = b(context, cVar);
        if (drawableB != null) {
            this.d.setThumb(drawableB);
            this.d.setThumbOffset(cVar.a(8));
        }
        this.d.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.naver.glink.android.sdk.ui.main.c.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                seekBar.setProgress(Math.max(i, 10));
                c.this.b(context);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.main.c.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.c.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.naver.glink.android.sdk.ui.main.c.3
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                c.this.g.x = iArr[0];
                c.this.g.y = iArr[1];
            }
        });
        final Activity activity = (Activity) context;
        this.c.setOnGenericMotionListener(new View.OnGenericMotionListener() { // from class: com.naver.glink.android.sdk.ui.main.c.4
            @Override // android.view.View.OnGenericMotionListener
            public boolean onGenericMotion(View view, MotionEvent motionEvent) {
                c.this.a(activity, motionEvent);
                return true;
            }
        });
        this.c.setOnTouchListener(new View.OnTouchListener() { // from class: com.naver.glink.android.sdk.ui.main.c.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                c.this.b(activity, motionEvent);
                return true;
            }
        });
    }
}
