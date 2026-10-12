package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zza extends ActivityLifecycleObserver {
    private final WeakReference<C0012zza> zzds;

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.zza$zza, reason: collision with other inner class name */
    @VisibleForTesting(otherwise = 2)
    static class C0012zza extends LifecycleCallback {
        private List<Runnable> zzdt;

        private C0012zza(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zzdt = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static C0012zza zza(Activity activity) {
            C0012zza c0012zza;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                c0012zza = (C0012zza) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0012zza.class);
                if (c0012zza == null) {
                    c0012zza = new C0012zza(fragment);
                }
            }
            return c0012zza;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zza(Runnable runnable) {
            this.zzdt.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zzdt;
                this.zzdt = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }

    public zza(Activity activity) {
        this(C0012zza.zza(activity));
    }

    @VisibleForTesting(otherwise = 2)
    private zza(C0012zza c0012zza) {
        this.zzds = new WeakReference<>(c0012zza);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0012zza c0012zza = this.zzds.get();
        if (c0012zza == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0012zza.zza(runnable);
        return this;
    }
}
