package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class zzk extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zzdg;
    protected final AtomicReference<zzl> zzer;
    private final Handler zzes;

    protected zzk(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    @VisibleForTesting
    private zzk(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zzer = new AtomicReference<>(null);
        this.zzes = new Handler(Looper.getMainLooper());
        this.zzdg = googleApiAvailability;
    }

    private static int zza(@Nullable zzl zzlVar) {
        if (zzlVar == null) {
            return -1;
        }
        return zzlVar.zzu();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x005a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:24:0x005c  */
    /* JADX WARN: Code duplicated, block: B:4:0x0011  */
    /* JADX WARN: Code duplicated, block: B:6:0x0014  */
    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onActivityResult(int i, int i2, Intent intent) {
        boolean z;
        zzl zzlVar = this.zzer.get();
        switch (i) {
            case 1:
                if (i2 == -1) {
                    z = true;
                } else if (i2 == 0) {
                    zzl zzlVar2 = new zzl(new ConnectionResult(intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null), zza(zzlVar));
                    this.zzer.set(zzlVar2);
                    zzlVar = zzlVar2;
                    z = false;
                } else {
                    z = false;
                }
                if (z) {
                    zzt();
                } else if (zzlVar != null) {
                    zza(zzlVar.getConnectionResult(), zzlVar.zzu());
                }
                break;
            case 2:
                int iIsGooglePlayServicesAvailable = this.zzdg.isGooglePlayServicesAvailable(getActivity());
                z = iIsGooglePlayServicesAvailable == 0;
                if (zzlVar != null) {
                    if (zzlVar.getConnectionResult().getErrorCode() == 18 && iIsGooglePlayServicesAvailable == 18) {
                    }
                    if (z) {
                        zzt();
                    } else if (zzlVar != null) {
                        zza(zzlVar.getConnectionResult(), zzlVar.zzu());
                    }
                }
                break;
            default:
                z = false;
                if (z) {
                    zzt();
                } else if (zzlVar != null) {
                    zza(zzlVar.getConnectionResult(), zzlVar.zzu());
                }
                break;
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), zza(this.zzer.get()));
        zzt();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzer.set(bundle.getBoolean("resolving_error", false) ? new zzl(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zzl zzlVar = this.zzer.get();
        if (zzlVar != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zzlVar.zzu());
            bundle.putInt("failed_status", zzlVar.getConnectionResult().getErrorCode());
            bundle.putParcelable("failed_resolution", zzlVar.getConnectionResult().getResolution());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    protected abstract void zza(ConnectionResult connectionResult, int i);

    public final void zzb(ConnectionResult connectionResult, int i) {
        zzl zzlVar = new zzl(connectionResult, i);
        if (this.zzer.compareAndSet(null, zzlVar)) {
            this.zzes.post(new zzm(this, zzlVar));
        }
    }

    protected abstract void zzr();

    protected final void zzt() {
        this.zzer.set(null);
        zzr();
    }
}
