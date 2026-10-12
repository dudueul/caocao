package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzw implements zzbp {
    private final Looper zzcn;
    private final GoogleApiManager zzcq;
    private final Lock zzga;
    private final ClientSettings zzgf;
    private final Map<Api<?>, Boolean> zzgi;
    private final zzav zzgj;
    private final GoogleApiAvailabilityLight zzgk;
    private final Condition zzgl;
    private final boolean zzgm;
    private final boolean zzgn;

    @GuardedBy("mLock")
    private boolean zzgp;

    @GuardedBy("mLock")
    private Map<zzh<?>, ConnectionResult> zzgq;

    @GuardedBy("mLock")
    private Map<zzh<?>, ConnectionResult> zzgr;

    @GuardedBy("mLock")
    private zzz zzgs;

    @GuardedBy("mLock")
    private ConnectionResult zzgt;
    private final Map<Api.AnyClientKey<?>, zzv<?>> zzgg = new HashMap();
    private final Map<Api.AnyClientKey<?>, zzv<?>> zzgh = new HashMap();
    private final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zzgo = new LinkedList();

    public zzw(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> abstractClientBuilder, ArrayList<zzp> arrayList, zzav zzavVar, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zzga = lock;
        this.zzcn = looper;
        this.zzgl = lock.newCondition();
        this.zzgk = googleApiAvailabilityLight;
        this.zzgj = zzavVar;
        this.zzgi = map2;
        this.zzgf = clientSettings;
        this.zzgm = z;
        HashMap map3 = new HashMap();
        for (Api<?> api : map2.keySet()) {
            map3.put(api.getClientKey(), api);
        }
        HashMap map4 = new HashMap();
        ArrayList<zzp> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            zzp zzpVar = arrayList2.get(i);
            i++;
            zzp zzpVar2 = zzpVar;
            map4.put(zzpVar2.mApi, zzpVar2);
        }
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        for (Map.Entry<Api.AnyClientKey<?>, Api.Client> entry : map.entrySet()) {
            Api api2 = (Api) map3.get(entry.getKey());
            Api.Client value = entry.getValue();
            if (value.requiresGooglePlayServices()) {
                z2 = true;
                if (this.zzgi.get(api2).booleanValue()) {
                    z3 = z6;
                    z4 = z7;
                } else {
                    z3 = z6;
                    z4 = true;
                }
            } else {
                z2 = z5;
                z3 = false;
                z4 = z7;
            }
            zzv<?> zzvVar = new zzv<>(context, api2, looper, value, (zzp) map4.get(api2), clientSettings, abstractClientBuilder);
            this.zzgg.put(entry.getKey(), zzvVar);
            if (value.requiresSignIn()) {
                this.zzgh.put(entry.getKey(), zzvVar);
            }
            z5 = z2;
            z6 = z3;
            z7 = z4;
        }
        this.zzgn = (!z5 || z6 || z7) ? false : true;
        this.zzcq = GoogleApiManager.zzbf();
    }

    @Nullable
    private final ConnectionResult zza(@NonNull Api.AnyClientKey<?> anyClientKey) {
        this.zzga.lock();
        try {
            zzv<?> zzvVar = this.zzgg.get(anyClientKey);
            if (this.zzgq == null || zzvVar == null) {
                return null;
            }
            return this.zzgq.get(zzvVar.zzm());
        } finally {
            this.zzga.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(zzv<?> zzvVar, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zzgi.get(zzvVar.getApi()).booleanValue() && zzvVar.zzae().requiresGooglePlayServices() && this.zzgk.isUserResolvableError(connectionResult.getErrorCode());
    }

    static /* synthetic */ boolean zza(zzw zzwVar, boolean z) {
        zzwVar.zzgp = false;
        return false;
    }

    private final boolean zzaf() {
        this.zzga.lock();
        try {
            if (!this.zzgp || !this.zzgm) {
                return false;
            }
            Iterator<Api.AnyClientKey<?>> it = this.zzgh.keySet().iterator();
            while (it.hasNext()) {
                ConnectionResult connectionResultZza = zza(it.next());
                if (connectionResultZza == null || !connectionResultZza.isSuccess()) {
                    return false;
                }
            }
            return true;
        } finally {
            this.zzga.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zzag() {
        if (this.zzgf == null) {
            this.zzgj.zzim = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zzgf.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zzgf.getOptionalApiSettings();
        for (Api<?> api : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(optionalApiSettings.get(api).mScopes);
            }
        }
        this.zzgj.zzim = hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zzah() {
        while (!this.zzgo.isEmpty()) {
            execute(this.zzgo.remove());
        }
        this.zzgj.zzb((Bundle) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    @Nullable
    public final ConnectionResult zzai() {
        int i = 0;
        ConnectionResult connectionResult = null;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zzv<?> zzvVar : this.zzgg.values()) {
            Api<?> api = zzvVar.getApi();
            ConnectionResult connectionResult3 = this.zzgq.get(zzvVar.zzm());
            if (!connectionResult3.isSuccess() && (!this.zzgi.get(api).booleanValue() || connectionResult3.hasResolution() || this.zzgk.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() == 4 && this.zzgm) {
                    int priority = api.zzj().getPriority();
                    if (connectionResult == null || i > priority) {
                        i = priority;
                        connectionResult = connectionResult3;
                    }
                } else {
                    int priority2 = api.zzj().getPriority();
                    if (connectionResult2 != null && i2 <= priority2) {
                        priority2 = i2;
                        connectionResult3 = connectionResult2;
                    }
                    i2 = priority2;
                    connectionResult2 = connectionResult3;
                }
            }
        }
        return (connectionResult2 == null || connectionResult == null || i2 <= i) ? connectionResult2 : connectionResult;
    }

    private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zzb(@NonNull T t) {
        Api.AnyClientKey<?> clientKey = t.getClientKey();
        ConnectionResult connectionResultZza = zza(clientKey);
        if (connectionResultZza == null || connectionResultZza.getErrorCode() != 4) {
            return false;
        }
        t.setFailedResult(new Status(4, null, this.zzcq.zza(this.zzgg.get(clientKey).zzm(), System.identityHashCode(this.zzgj))));
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzgl.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        return this.zzgt != null ? this.zzgt : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                disconnect();
                return new ConnectionResult(14, null);
            }
            try {
                nanos = this.zzgl.awaitNanos(nanos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, null);
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        return this.zzgt != null ? this.zzgt : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final void connect() {
        this.zzga.lock();
        try {
            if (this.zzgp) {
                return;
            }
            this.zzgp = true;
            this.zzgq = null;
            this.zzgr = null;
            this.zzgs = null;
            this.zzgt = null;
            this.zzcq.zzr();
            this.zzcq.zza(this.zzgg.values()).addOnCompleteListener(new HandlerExecutor(this.zzcn), new zzy(this));
        } finally {
            this.zzga.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final void disconnect() {
        this.zzga.lock();
        try {
            this.zzgp = false;
            this.zzgq = null;
            this.zzgr = null;
            if (this.zzgs != null) {
                this.zzgs.cancel();
                this.zzgs = null;
            }
            this.zzgt = null;
            while (!this.zzgo.isEmpty()) {
                BaseImplementation.ApiMethodImpl<?, ?> apiMethodImplRemove = this.zzgo.remove();
                apiMethodImplRemove.zza((zzcn) null);
                apiMethodImplRemove.cancel();
            }
            this.zzgl.signalAll();
        } finally {
            this.zzga.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        if (this.zzgm && zzb(t)) {
            return t;
        }
        if (isConnected()) {
            this.zzgj.zzir.zzb(t);
            return (T) this.zzgg.get(t.getClientKey()).doRead(t);
        }
        this.zzgo.add(t);
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        Api.AnyClientKey<A> clientKey = t.getClientKey();
        if (this.zzgm && zzb(t)) {
            return t;
        }
        this.zzgj.zzir.zzb(t);
        return (T) this.zzgg.get(clientKey).doWrite(t);
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zza(api.getClientKey());
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final boolean isConnected() {
        this.zzga.lock();
        try {
            return this.zzgq != null && this.zzgt == null;
        } finally {
            this.zzga.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final boolean isConnecting() {
        this.zzga.lock();
        try {
            return this.zzgq == null && this.zzgp;
        } finally {
            this.zzga.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        Lock lock;
        this.zzga.lock();
        try {
            if (!this.zzgp || zzaf()) {
                return false;
            }
            this.zzcq.zzr();
            this.zzgs = new zzz(this, signInConnectionListener);
            this.zzcq.zza(this.zzgh.values()).addOnCompleteListener(new HandlerExecutor(this.zzcn), this.zzgs);
            return true;
        } finally {
            this.zzga.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final void maybeSignOut() {
        this.zzga.lock();
        try {
            this.zzcq.maybeSignOut();
            if (this.zzgs != null) {
                this.zzgs.cancel();
                this.zzgs = null;
            }
            if (this.zzgr == null) {
                this.zzgr = new ArrayMap(this.zzgh.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            Iterator<zzv<?>> it = this.zzgh.values().iterator();
            while (it.hasNext()) {
                this.zzgr.put(it.next().zzm(), connectionResult);
            }
            if (this.zzgq != null) {
                this.zzgq.putAll(this.zzgr);
            }
        } finally {
            this.zzga.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbp
    public final void zzz() {
    }
}
