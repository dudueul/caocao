package com.tapjoy.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class io extends RelativeLayout {
    private hr a;
    private a b;
    private aa c;
    private int d;
    private int e;
    private ia f;
    private ArrayList g;
    private ArrayList h;

    public interface a {
        void a();

        void a(hz hzVar);
    }

    public io(Context context, hr hrVar, a aVar) {
        super(context);
        this.c = aa.UNSPECIFIED;
        this.d = 0;
        this.e = 0;
        this.f = null;
        this.g = null;
        this.h = null;
        this.a = hrVar;
        this.b = aVar;
    }

    private void a() {
        final ig igVar;
        final ig igVar2;
        ia iaVar = null;
        for (ia iaVar2 : this.a.a) {
            if (iaVar2.a == this.c) {
                iaVar = iaVar2;
                break;
            } else {
                if (iaVar2.a != aa.UNSPECIFIED) {
                    iaVar2 = iaVar;
                }
                iaVar = iaVar2;
            }
        }
        removeAllViews();
        if (this.g != null) {
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                ig igVar3 = (ig) ((WeakReference) it.next()).get();
                if (igVar3 != null) {
                    igVar3.c();
                }
            }
            this.g.clear();
        }
        if (this.h != null) {
            Iterator it2 = this.h.iterator();
            while (it2.hasNext()) {
                ig igVar4 = (ig) ((WeakReference) it2.next()).get();
                if (igVar4 != null) {
                    igVar4.c();
                }
            }
            this.h.clear();
        }
        if (iaVar != null) {
            this.f = iaVar;
            Context context = getContext();
            for (final hz hzVar : iaVar.c) {
                final RelativeLayout relativeLayout = new RelativeLayout(context);
                if (hzVar.l.c != null) {
                    igVar = new ig(context);
                    igVar.setScaleType(ImageView.ScaleType.FIT_XY);
                    igVar.a(hzVar.l.d, hzVar.l.c);
                    if (this.g == null) {
                        this.g = new ArrayList();
                    }
                    this.g.add(new WeakReference(igVar));
                } else {
                    igVar = null;
                }
                if (hzVar.m == null || hzVar.m.c == null) {
                    igVar2 = null;
                } else {
                    igVar2 = new ig(context);
                    igVar2.setScaleType(ImageView.ScaleType.FIT_XY);
                    igVar2.a(hzVar.m.d, hzVar.m.c);
                    if (this.h == null) {
                        this.h = new ArrayList();
                    }
                    this.h.add(new WeakReference(igVar2));
                }
                ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(0, 0);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                Bitmap bitmap = hzVar.l.b;
                Bitmap bitmap2 = hzVar.m != null ? hzVar.m.b : null;
                final BitmapDrawable bitmapDrawable = bitmap != null ? new BitmapDrawable((Resources) null, bitmap) : null;
                final BitmapDrawable bitmapDrawable2 = bitmap2 != null ? new BitmapDrawable((Resources) null, bitmap2) : null;
                if (bitmapDrawable != null) {
                    ab.a(relativeLayout, bitmapDrawable);
                }
                if (igVar != null) {
                    relativeLayout.addView(igVar, layoutParams2);
                    igVar.a();
                }
                if (igVar2 != null) {
                    relativeLayout.addView(igVar2, layoutParams2);
                    igVar2.setVisibility(4);
                }
                relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.tapjoy.internal.io.1
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        boolean z = true;
                        if (motionEvent.getAction() == 0) {
                            if (igVar2 != null || bitmapDrawable2 != null) {
                                if (igVar != null) {
                                    igVar.b();
                                    igVar.setVisibility(4);
                                }
                                ab.a(view, null);
                            }
                            if (bitmapDrawable2 != null) {
                                ab.a(view, bitmapDrawable2);
                            } else if (igVar2 != null) {
                                igVar2.setVisibility(0);
                                igVar2.a();
                            }
                        } else if (motionEvent.getAction() == 1) {
                            float x = motionEvent.getX();
                            float y = motionEvent.getY();
                            if (x >= 0.0f && x < view.getWidth() && y >= 0.0f && y < view.getHeight()) {
                                z = false;
                            }
                            if (z) {
                                if (bitmapDrawable != null) {
                                    ab.a(view, bitmapDrawable);
                                } else if (bitmapDrawable2 != null) {
                                    ab.a(view, null);
                                }
                            }
                            if (igVar2 != null) {
                                igVar2.b();
                                igVar2.setVisibility(4);
                            }
                            if ((igVar2 != null || bitmapDrawable2 != null) && igVar != null && z) {
                                igVar.setVisibility(0);
                                igVar.a();
                            }
                        }
                        return false;
                    }
                });
                relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.tapjoy.internal.io.2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        if (igVar2 != null) {
                            igVar2.b();
                            relativeLayout.removeView(igVar2);
                        }
                        if (igVar != null) {
                            igVar.b();
                            relativeLayout.removeView(igVar);
                        }
                        io.this.b.a(hzVar);
                    }
                });
                relativeLayout.setTag(hzVar);
                addView(relativeLayout, layoutParams);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.a();
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0127  */
    @Override // android.widget.RelativeLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        float f;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (size >= size2) {
            if (this.c != aa.LANDSCAPE) {
                this.c = aa.LANDSCAPE;
                a();
            }
        } else if (this.c != aa.PORTRAIT) {
            this.c = aa.PORTRAIT;
            a();
        }
        if (this.d != size || this.e != size2) {
            this.d = size;
            this.e = size2;
            float f2 = 0.0f;
            float f3 = 0.0f;
            float f4 = size;
            float f5 = size2;
            if (this.f == null || this.f.b == null) {
                f = f5;
            } else {
                float f6 = ((this.f.b.y * f4) / this.f.b.x) / f5;
                if (f6 < 1.0f) {
                    float f7 = (this.f.b.y * f4) / this.f.b.x;
                    f = f7;
                    f3 = (f5 - f7) / 2.0f;
                } else if (f6 > 1.0f) {
                    float f8 = (this.f.b.x * f5) / this.f.b.y;
                    f2 = (f4 - f8) / 2.0f;
                    f = f5;
                    f4 = f8;
                } else {
                    f = f5;
                }
            }
            for (View view : ac.a(this)) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                hz hzVar = (hz) view.getTag();
                float fA = hzVar.a.a(f4, f);
                float fA2 = hzVar.b.a(f4, f);
                float fA3 = hzVar.c.a(f4, f);
                float fA4 = hzVar.d.a(f4, f);
                int i3 = hzVar.e;
                int i4 = hzVar.f;
                if (i3 == 14) {
                    i3 = 9;
                    fA += (f4 - fA3) / 2.0f;
                }
                if (i4 == 15) {
                    i4 = 10;
                    fA2 += (f - fA4) / 2.0f;
                }
                layoutParams.addRule(i3, -1);
                layoutParams.addRule(i4, -1);
                layoutParams.width = Math.round(fA3);
                layoutParams.height = Math.round(fA4);
                if (i3 == 9) {
                    layoutParams.leftMargin = Math.round(f2 + fA);
                } else if (i3 == 11) {
                    layoutParams.rightMargin = Math.round(f2 + fA);
                }
                if (i4 == 10) {
                    layoutParams.topMargin = Math.round(f3 + fA2);
                } else if (i4 == 12) {
                    layoutParams.bottomMargin = Math.round(f3 + fA2);
                }
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            if (this.g != null) {
                Iterator it = this.g.iterator();
                while (it.hasNext()) {
                    ig igVar = (ig) ((WeakReference) it.next()).get();
                    if (igVar != null) {
                        igVar.b();
                    }
                }
            }
            if (this.h != null) {
                Iterator it2 = this.h.iterator();
                while (it2.hasNext()) {
                    ig igVar2 = (ig) ((WeakReference) it2.next()).get();
                    if (igVar2 != null) {
                        igVar2.b();
                    }
                }
                return;
            }
            return;
        }
        if (this.h != null) {
            Iterator it3 = this.h.iterator();
            while (it3.hasNext()) {
                ig igVar3 = (ig) ((WeakReference) it3.next()).get();
                if (igVar3 != null) {
                    igVar3.setVisibility(4);
                    igVar3.b();
                }
            }
        }
        if (this.g != null) {
            Iterator it4 = this.g.iterator();
            while (it4.hasNext()) {
                ig igVar4 = (ig) ((WeakReference) it4.next()).get();
                if (igVar4 != null) {
                    igVar4.setVisibility(0);
                    igVar4.a();
                }
            }
        }
    }
}
