package com.google.android.gms.games.internal;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.common.util.PlatformVersion;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(12)
final class zzaf extends zzac implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
    private boolean zzfu;
    private WeakReference<View> zzjf;

    protected zzaf(zze zzeVar, int i) {
        super(zzeVar, i);
        this.zzfu = false;
    }

    @TargetApi(17)
    private final void zzc(View view) {
        Display display;
        int displayId = -1;
        if (PlatformVersion.isAtLeastJellyBeanMR1() && (display = view.getDisplay()) != null) {
            displayId = display.getDisplayId();
        }
        IBinder windowToken = view.getWindowToken();
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int width = view.getWidth();
        int height = view.getHeight();
        this.zzjd.zzje = displayId;
        this.zzjd.zzjb = windowToken;
        this.zzjd.left = iArr[0];
        this.zzjd.top = iArr[1];
        this.zzjd.right = iArr[0] + width;
        this.zzjd.bottom = iArr[1] + height;
        if (this.zzfu) {
            zzbj();
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        View view;
        if (this.zzjf == null || (view = this.zzjf.get()) == null) {
            return;
        }
        zzc(view);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        zzc(view);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.zzjc.zzbd();
        view.removeOnAttachStateChangeListener(this);
    }

    @Override // com.google.android.gms.games.internal.zzac
    @TargetApi(16)
    public final void zzb(View view) {
        this.zzjc.zzbd();
        if (this.zzjf != null) {
            View decorView = this.zzjf.get();
            Context context = this.zzjc.getContext();
            if (decorView == null && (context instanceof Activity)) {
                decorView = ((Activity) context).getWindow().getDecorView();
            }
            if (decorView != null) {
                decorView.removeOnAttachStateChangeListener(this);
                ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
                if (PlatformVersion.isAtLeastJellyBean()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                } else {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
            }
        }
        this.zzjf = null;
        Context context2 = this.zzjc.getContext();
        if (view == null && (context2 instanceof Activity)) {
            View viewFindViewById = ((Activity) context2).findViewById(R.id.content);
            if (viewFindViewById == null) {
                viewFindViewById = ((Activity) context2).getWindow().getDecorView();
            }
            zzh.w("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view. Note that this may not work as expected in multi-screen environments");
            view = viewFindViewById;
        }
        if (view == null) {
            zzh.e("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
            return;
        }
        zzc(view);
        this.zzjf = new WeakReference<>(view);
        view.addOnAttachStateChangeListener(this);
        view.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // com.google.android.gms.games.internal.zzac
    public final void zzbj() {
        if (this.zzjd.zzjb == null) {
            this.zzfu = true;
        } else {
            super.zzbj();
            this.zzfu = false;
        }
    }

    @Override // com.google.android.gms.games.internal.zzac
    protected final void zzm(int i) {
        this.zzjd = new zzae(i, null);
    }
}
