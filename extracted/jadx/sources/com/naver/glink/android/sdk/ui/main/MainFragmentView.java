package com.naver.glink.android.sdk.ui.main;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.StartTo;
import com.naver.glink.android.sdk.a.j;
import com.naver.glink.android.sdk.a.k;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView;
import com.naver.glink.android.sdk.ui.widget.video.PlugVideoView;
import com.naver.glink.android.sdk.ui.write.WriteFragmentView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class MainFragmentView extends DialogFragmentView {
    public static final String a = "com.naver.glink.ARG_START_TO";
    private static final l d = l.a(MainFragmentView.class.getSimpleName());
    a b;
    c c;
    private StartTo i;
    private boolean j;
    private PlugVideoView k;
    private ViewGroup l;
    private WebChromeClient.CustomViewCallback m;
    private boolean n;

    public MainFragmentView(Context context) {
        super(context);
    }

    private SurfaceView a(ViewGroup viewGroup) {
        SurfaceView surfaceViewA;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= viewGroup.getChildCount()) {
                return null;
            }
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof ViewGroup) && (surfaceViewA = a((ViewGroup) childAt)) != null) {
                return surfaceViewA;
            }
            if (childAt instanceof SurfaceView) {
                return (SurfaceView) childAt;
            }
            i = i2 + 1;
        }
    }

    public static MainFragmentView a(Context context, StartTo startTo) {
        MainFragmentView mainFragmentView = new MainFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.naver.glink.ARG_START_TO", startTo);
        mainFragmentView.setArguments(bundle);
        return mainFragmentView;
    }

    private void f() {
        if (this.k != null) {
            this.k.setFullscreen(false);
            this.k = null;
        }
    }

    private void setCollapsed(boolean z) {
        this.j = z;
        setDialogVisible(z);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_main, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        com.naver.glink.android.sdk.a.a.b.a(this);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.i = (StartTo) arguments.getParcelable("com.naver.glink.ARG_START_TO");
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        d.a("onViewCreated", new Object[0]);
        this.b = new a(this);
        this.b.a(getContext(), d.p());
        this.c = new c(this);
        this.c.a(getContext(), d.p());
        this.l = (ViewGroup) view.findViewById(R.id.custom_view_layout);
        com.naver.glink.android.sdk.ui.tabs.b.a(getContext(), view, this.i);
        this.n = 24 <= Build.VERSION.SDK_INT;
    }

    public void a(final View view, final WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.m != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        SurfaceView surfaceViewA = a((ViewGroup) view);
        if (surfaceViewA != null) {
            surfaceViewA.setZOrderOnTop(true);
        }
        this.l.setVisibility(0);
        this.l.addView(view);
        this.m = new WebChromeClient.CustomViewCallback() { // from class: com.naver.glink.android.sdk.ui.main.MainFragmentView.1
            @Override // android.webkit.WebChromeClient.CustomViewCallback
            public void onCustomViewHidden() {
                MainFragmentView.this.m = null;
                view.setVisibility(8);
                MainFragmentView.this.l.removeView(view);
                MainFragmentView.this.l.setVisibility(8);
                customViewCallback.onCustomViewHidden();
            }
        };
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    protected void a(WindowManager.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return;
        }
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = -1;
        layoutParams.height = d.p().b().y - 1;
        layoutParams.gravity = 17;
        layoutParams.dimAmount = 0.0f;
        layoutParams.format = -2;
        layoutParams.windowAnimations = android.R.style.Animation.Dialog;
        layoutParams.flags |= 16778240;
        layoutParams.softInputMode |= 34;
    }

    public void a(StartTo startTo) {
        if (startTo != null) {
            switch (startTo.a) {
                case ARTICLE:
                    com.naver.glink.android.sdk.ui.tabs.b.a(startTo.d);
                    break;
                case WRITE:
                    com.naver.glink.android.sdk.ui.tabs.b.a(startTo.e, false, this.j);
                    break;
                case MENU:
                    com.naver.glink.android.sdk.ui.tabs.b.a(startTo.c, false, false);
                    break;
            }
        }
        setCollapsed(false);
        if (this.n) {
            this.f.getWindow().getDecorView().setVisibility(0);
            WindowManager.LayoutParams attributes = this.f.getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = d.p().b().y - 1;
            WindowManager windowManager = this.f.getWindow().getWindowManager();
            if (windowManager != null) {
                windowManager.updateViewLayout(this.f.getWindow().getDecorView(), attributes);
            }
        }
    }

    public void a(boolean z) {
        a(getWindowParam().width, z ? -1 : d.p().b().y - 1);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        f();
        com.naver.glink.android.sdk.a.a.b.b(this);
    }

    public void c() {
        if (this.m != null) {
            this.m.onCustomViewHidden();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!j.a(keyEvent)) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (this.m != null) {
            c();
            return true;
        }
        if (this.k != null) {
            f();
            return true;
        }
        if (com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().d() <= 0) {
            l();
            d.c(getContext());
            return true;
        }
        WriteFragmentView writeFragmentViewD = com.naver.glink.android.sdk.ui.tabs.b.d();
        if (writeFragmentViewD != null) {
            writeFragmentViewD.f();
            return true;
        }
        if (com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().d() == 1) {
            d.c(getContext());
            return true;
        }
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().e();
        return true;
    }

    public void e() {
        setCollapsed(true);
        k.a(getContext(), (ViewGroup) this);
        if (this.n) {
            this.f.getWindow().getDecorView().setVisibility(4);
            WindowManager.LayoutParams attributes = this.f.getWindow().getAttributes();
            attributes.width = 0;
            attributes.height = 0;
            WindowManager windowManager = this.f.getWindow().getWindowManager();
            if (windowManager != null) {
                windowManager.updateViewLayout(this.f.getWindow().getDecorView(), attributes);
            }
        }
    }

    public boolean getCollapsed() {
        return this.j;
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(com.naver.glink.android.sdk.ui.profile.image.a.b, "plug plug onAttachedToWindow");
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        if (!d.h() || configuration.orientation == getWindowParam().screenOrientation) {
            return;
        }
        d.a(getContext(), false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(com.naver.glink.android.sdk.ui.profile.image.a.b, "plug plug onDetachedFromWindow");
    }

    public void setFullscreenVideoView(PlugVideoView plugVideoView) {
        this.k = plugVideoView;
    }
}
