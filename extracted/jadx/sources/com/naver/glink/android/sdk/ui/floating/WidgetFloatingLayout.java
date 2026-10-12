package com.naver.glink.android.sdk.ui.floating;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.alarm.LiveAlarmMessage;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class WidgetFloatingLayout extends DialogFragmentView {
    private static final String a = "floatingWidget";
    private static final int b = 40;
    private static final int c = 400;
    private static final int d = 1;
    private static final int i = 2;
    private static final int j = 6;
    private static WidgetFloatingLayout l;
    private WindowManager.LayoutParams A;
    private WindowManager B;
    private com.naver.glink.android.sdk.ui.floating.b.a C;
    private final int k;
    private Animation m;
    private Animation n;
    private Animation o;
    private Point p;
    private float q;
    private float r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private Handler x;
    private View y;
    private View z;

    class a extends Timer {
        private TimerTask b;

        a(final Handler handler, final int i) {
            final int i2;
            WidgetFloatingLayout.this.u = 0;
            if (i == 2) {
                i2 = (WidgetFloatingLayout.this.p.x - WidgetFloatingLayout.this.A.x) + WidgetFloatingLayout.this.A.width;
            } else if (i != 1) {
                return;
            } else {
                i2 = WidgetFloatingLayout.this.A.x;
            }
            this.b = new TimerTask() { // from class: com.naver.glink.android.sdk.ui.floating.WidgetFloatingLayout.a.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    WidgetFloatingLayout.this.v = true;
                    Message message = new Message();
                    message.what = i;
                    message.arg1 = i2;
                    handler.sendMessage(message);
                    if (WidgetFloatingLayout.this.u >= i2 || WidgetFloatingLayout.l == null) {
                        a.this.cancel();
                        WidgetFloatingLayout.this.v = false;
                    }
                }
            };
        }

        public void a() {
            schedule(this.b, 0L, 1L);
        }
    }

    class b extends Handler {
        b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.arg1;
            if (WidgetFloatingLayout.this.u > i) {
                if (message.what == 1) {
                    WidgetFloatingLayout.this.startAnimation(WidgetFloatingLayout.this.o = WidgetFloatingLayout.this.m);
                } else if (message.what == 2) {
                    WidgetFloatingLayout.this.startAnimation(WidgetFloatingLayout.this.o = WidgetFloatingLayout.this.n);
                }
                WidgetFloatingLayout.this.setLiveIconPosition(message.what == 1);
                return;
            }
            int i2 = i - WidgetFloatingLayout.this.u;
            switch (message.what) {
                case 1:
                    WidgetFloatingLayout.this.A.x -= i2 > 0 ? WidgetFloatingLayout.this.k : WidgetFloatingLayout.this.k - i2;
                    if (WidgetFloatingLayout.this.A.x < 0) {
                        WidgetFloatingLayout.this.A.x = 0;
                    }
                    break;
                case 2:
                    WindowManager.LayoutParams layoutParams = WidgetFloatingLayout.this.A;
                    layoutParams.x = (i2 > 0 ? WidgetFloatingLayout.this.k : WidgetFloatingLayout.this.k - i2) + layoutParams.x;
                    if (WidgetFloatingLayout.this.A.x < 0) {
                        WidgetFloatingLayout.this.A.x = 0;
                    } else if (WidgetFloatingLayout.this.A.x + WidgetFloatingLayout.this.y.getWidth() > WidgetFloatingLayout.this.p.x) {
                        WidgetFloatingLayout.this.A.x = WidgetFloatingLayout.this.p.x - WidgetFloatingLayout.this.y.getWidth();
                    }
                    break;
            }
            WidgetFloatingLayout.this.u += WidgetFloatingLayout.this.k;
            try {
                if (WidgetFloatingLayout.l != null) {
                    WidgetFloatingLayout.this.B.updateViewLayout(WidgetFloatingLayout.this.f.getWindow().getDecorView(), WidgetFloatingLayout.this.A);
                }
            } catch (RuntimeException e) {
                Log.e("glink", "[error] updateViewLayout. " + e.getMessage());
            } catch (Exception e2) {
                Log.e("glink", "[error] updateViewLayout. " + e2.getMessage());
            }
            if (WidgetFloatingLayout.this.u >= i || WidgetFloatingLayout.l == null) {
                if (message.what == 1) {
                    WidgetFloatingLayout.this.startAnimation(WidgetFloatingLayout.this.o = WidgetFloatingLayout.this.m);
                } else if (message.what == 2) {
                    WidgetFloatingLayout.this.startAnimation(WidgetFloatingLayout.this.o = WidgetFloatingLayout.this.n);
                }
                WidgetFloatingLayout.this.setLiveIconPosition(message.what == 1);
            }
        }
    }

    public WidgetFloatingLayout(Context context) {
        super(context);
        this.x = new b();
        this.k = getMoveSpeed();
    }

    private boolean a(int i2) {
        return true;
    }

    public static WidgetFloatingLayout b(Context context) {
        Bundle bundle = new Bundle();
        WidgetFloatingLayout widgetFloatingLayout = new WidgetFloatingLayout(context);
        widgetFloatingLayout.setArguments(bundle);
        return widgetFloatingLayout;
    }

    public static void c() {
        if (l != null) {
            try {
                l.dismiss();
                l = null;
            } catch (Exception e) {
                Log.e("glink", "[error] closeFloatingView. " + e.getMessage());
                l = null;
            }
        }
    }

    public static boolean c(Context context) {
        if (com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(a) != null) {
            f();
            return false;
        }
        d.d(d.r());
        l = b(context);
        l.a(a);
        return true;
    }

    public static void e() {
        if (l != null) {
            l.f.getWindow().getDecorView().setVisibility(8);
            com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b().c(l).a();
        }
    }

    public static void f() {
        if (l != null) {
            l.f.getWindow().getDecorView().setVisibility(0);
            com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b().b(l).a();
            l.startAnimation(l.o);
        }
    }

    public static boolean g() {
        return l != null;
    }

    private Point getFirstPosition() {
        Point point = new Point();
        Bundle bundleM = p.m(getContext());
        boolean z = bundleM.getBoolean(p.a);
        int i2 = bundleM.getInt(p.b);
        if (i2 < 0 || i2 > 100) {
            point.x = 0;
            point.y = this.p.y / 2;
            this.o = this.m;
        } else {
            if (z) {
                this.o = this.m;
                point.x = 0;
            } else {
                this.o = this.n;
                point.x = this.p.x - getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal);
            }
            point.y = (i2 * (this.p.y - getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal))) / 100;
        }
        return point;
    }

    private int getMoveSpeed() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.widget_speed);
        if (dimensionPixelSize > 6) {
            return 6;
        }
        return dimensionPixelSize;
    }

    public static boolean h() {
        return l != null && l.getVisibility() == 0;
    }

    public static void setListener(com.naver.glink.android.sdk.ui.floating.b.a aVar) {
        if (l != null) {
            l.C = aVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLiveIconPosition(boolean z) {
        if (this.z.getVisibility() == 8) {
            return;
        }
        if (z) {
            ((FrameLayout.LayoutParams) this.z.getLayoutParams()).leftMargin = x.a(30.0f);
        } else {
            ((FrameLayout.LayoutParams) this.z.getLayoutParams()).leftMargin = 0;
        }
        this.z.setLayoutParams(this.z.getLayoutParams());
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.widget_floating_layout, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        com.naver.glink.android.sdk.a.a.b.a(this);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.y = view.findViewById(R.id.floating_button);
        this.z = view.findViewById(R.id.floating_live_icon);
        com.naver.glink.android.sdk.ui.floating.b.a(this.y);
        startAnimation(this.o);
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.floating.WidgetFloatingLayout.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                if (iVar.live && com.naver.glink.android.sdk.ui.streaming.b.b()) {
                    com.naver.glink.android.sdk.alarm.b.a(WidgetFloatingLayout.this.getContext());
                    WidgetFloatingLayout.this.z.setVisibility(com.naver.glink.android.sdk.alarm.b.c() ? 8 : 0);
                    WidgetFloatingLayout.this.setLiveIconPosition(WidgetFloatingLayout.this.o == WidgetFloatingLayout.this.m);
                }
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                WidgetFloatingLayout.this.dismiss();
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    protected void a(WindowManager.LayoutParams layoutParams) {
        this.p = d.p().b();
        this.A = layoutParams;
        this.A.width = -2;
        this.A.height = -2;
        this.A.gravity = 51;
        this.A.format = -3;
        this.A.flags = 263176;
        this.A.windowAnimations = R.style.widget_dialog_anim;
        boolean zD = d(getContext());
        boolean zI = d.i();
        this.m = AnimationUtils.loadAnimation(getContext(), R.anim.widget_half_left);
        this.n = AnimationUtils.loadAnimation(getContext(), (zD && zI) ? R.anim.widget_alpha_right : R.anim.widget_half_right);
        Animation.AnimationListener animationListener = new Animation.AnimationListener() { // from class: com.naver.glink.android.sdk.ui.floating.WidgetFloatingLayout.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (WidgetFloatingLayout.l != null) {
                    p.a(WidgetFloatingLayout.l.getContext(), WidgetFloatingLayout.l.A.x < WidgetFloatingLayout.this.p.x / 2 ? 1 : 2, WidgetFloatingLayout.l.A.y);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
        this.m.setAnimationListener(animationListener);
        this.n.setAnimationListener(animationListener);
        Point pointL = p.l(getContext());
        this.o = pointL.x == 2 ? this.n : this.m;
        if (pointL.x == -1) {
            Point firstPosition = getFirstPosition();
            this.A.x = firstPosition.x;
            this.A.y = firstPosition.y;
        } else {
            this.A.x = pointL.x == 1 ? 0 : this.p.x - getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal);
            if (pointL.y < 0 || pointL.y > this.p.y) {
                this.A.y = this.p.y / 2;
            } else {
                this.A.y = pointL.y;
            }
        }
        this.B = (WindowManager) getContext().getSystemService("window");
    }

    @Subscribe
    public void a(LiveAlarmMessage liveAlarmMessage) {
        if (liveAlarmMessage == null) {
            return;
        }
        this.z.setVisibility(!com.naver.glink.android.sdk.alarm.b.b(liveAlarmMessage) ? 0 : 8);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        com.naver.glink.android.sdk.a.a.b.b(this);
    }

    public boolean d(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (identifier > 0) {
            return resources.getBoolean(identifier);
        }
        return (ViewConfiguration.get(context).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) ? false : true;
    }

    @Override // android.view.ViewGroup
    protected void detachAllViewsFromParent() {
        super.detachAllViewsFromParent();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        l = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.v && getVisibility() != 8 && this.y != null) {
            int rawX = (int) (motionEvent.getRawX() - this.q);
            int rawY = (int) (motionEvent.getRawY() - this.r);
            if (motionEvent.getEventTime() - motionEvent.getDownTime() > 400 && !this.w && this.C != null) {
                this.C.a();
                this.w = true;
            }
            switch (motionEvent.getAction()) {
                case 0:
                    clearAnimation();
                    invalidate();
                    this.q = motionEvent.getRawX();
                    this.r = motionEvent.getRawY();
                    this.s = this.A.x;
                    this.t = this.A.y;
                    break;
                case 1:
                    if (motionEvent.getRawX() > this.p.x / 2) {
                        new a(this.x, 2).a();
                    } else {
                        new a(this.x, 1).a();
                    }
                    if (this.C != null) {
                        this.C.a(motionEvent);
                        this.w = false;
                    }
                    if (Math.abs(this.r - motionEvent.getRawY()) < 40.0f && Math.abs(this.q - motionEvent.getRawX()) < 40.0f && this.C != null && a((int) motionEvent.getRawX())) {
                        this.C.a(motionEvent, rawX + this.s, rawY + this.t);
                    }
                    break;
                case 2:
                    if (this.s + rawX > this.p.x - getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal)) {
                        this.A.x = this.p.x - getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal);
                    } else {
                        this.A.x = rawX + this.s;
                    }
                    if (this.t + rawY > this.p.y - getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal)) {
                        this.A.y = this.p.y - getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal);
                    } else {
                        this.A.y = rawY + this.t;
                    }
                    try {
                        this.B.updateViewLayout(this.f.getWindow().getDecorView(), this.A);
                    } catch (RuntimeException e) {
                        Log.e("glink", "[error] updateViewLayout. " + e.getMessage());
                    } catch (Exception e2) {
                        Log.e("glink", "[error] updateViewLayout. " + e2.getMessage());
                    }
                    if (this.C != null) {
                        this.C.b(motionEvent);
                    }
                    break;
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z) {
        super.removeDetachedView(view, z);
    }
}
