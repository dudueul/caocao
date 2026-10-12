package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.SignInResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzaj implements zzbc {
    private final Context mContext;
    private final Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> zzdh;
    private final Lock zzga;
    private final ClientSettings zzgf;
    private final Map<Api<?>, Boolean> zzgi;
    private final GoogleApiAvailabilityLight zzgk;
    private ConnectionResult zzgt;
    private final zzbd zzhf;
    private int zzhi;
    private int zzhk;
    private SignInClient zzhn;
    private boolean zzho;
    private boolean zzhp;
    private boolean zzhq;
    private IAccountAccessor zzhr;
    private boolean zzhs;
    private boolean zzht;
    private int zzhj = 0;
    private final Bundle zzhl = new Bundle();
    private final Set<Api.AnyClientKey> zzhm = new HashSet();
    private ArrayList<Future<?>> zzhu = new ArrayList<>();

    public zzaj(zzbd zzbdVar, ClientSettings clientSettings, Map<Api<?>, Boolean> map, GoogleApiAvailabilityLight googleApiAvailabilityLight, Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> abstractClientBuilder, Lock lock, Context context) {
        this.zzhf = zzbdVar;
        this.zzgf = clientSettings;
        this.zzgi = map;
        this.zzgk = googleApiAvailabilityLight;
        this.zzdh = abstractClientBuilder;
        this.zzga = lock;
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zza(SignInResponse signInResponse) {
        if (zze(0)) {
            ConnectionResult connectionResult = signInResponse.getConnectionResult();
            if (!connectionResult.isSuccess()) {
                if (!zzd(connectionResult)) {
                    zze(connectionResult);
                    return;
                } else {
                    zzau();
                    zzas();
                    return;
                }
            }
            ResolveAccountResponse resolveAccountResponse = signInResponse.getResolveAccountResponse();
            ConnectionResult connectionResult2 = resolveAccountResponse.getConnectionResult();
            if (!connectionResult2.isSuccess()) {
                String strValueOf = String.valueOf(connectionResult2);
                Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(strValueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(strValueOf).toString(), new Exception());
                zze(connectionResult2);
            } else {
                this.zzhq = true;
                this.zzhr = resolveAccountResponse.getAccountAccessor();
                this.zzhs = resolveAccountResponse.getSaveDefaultAccount();
                this.zzht = resolveAccountResponse.isFromCrossClientAuth();
                zzas();
            }
        }
    }

    private final void zza(boolean z) {
        if (this.zzhn != null) {
            if (this.zzhn.isConnected() && z) {
                this.zzhn.clearAccountFromSessionStore();
            }
            this.zzhn.disconnect();
            this.zzhr = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zzar() {
        this.zzhk--;
        if (this.zzhk > 0) {
            return false;
        }
        if (this.zzhk < 0) {
            Log.w("GoogleApiClientConnecting", this.zzhf.zzfq.zzbb());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zze(new ConnectionResult(8, null));
            return false;
        }
        if (this.zzgt == null) {
            return true;
        }
        this.zzhf.zzje = this.zzhi;
        zze(this.zzgt);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zzas() {
        if (this.zzhk != 0) {
            return;
        }
        if (!this.zzhp || this.zzhq) {
            ArrayList arrayList = new ArrayList();
            this.zzhj = 1;
            this.zzhk = this.zzhf.zzil.size();
            for (Api.AnyClientKey<?> anyClientKey : this.zzhf.zzil.keySet()) {
                if (!this.zzhf.zzjb.containsKey(anyClientKey)) {
                    arrayList.add(this.zzhf.zzil.get(anyClientKey));
                } else if (zzar()) {
                    zzat();
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            this.zzhu.add(zzbg.zzbe().submit(new zzap(this, arrayList)));
        }
    }

    @GuardedBy("mLock")
    private final void zzat() {
        this.zzhf.zzbd();
        zzbg.zzbe().execute(new zzak(this));
        if (this.zzhn != null) {
            if (this.zzhs) {
                this.zzhn.saveDefaultAccount(this.zzhr, this.zzht);
            }
            zza(false);
        }
        Iterator<Api.AnyClientKey<?>> it = this.zzhf.zzjb.keySet().iterator();
        while (it.hasNext()) {
            this.zzhf.zzil.get(it.next()).disconnect();
        }
        this.zzhf.zzjf.zzb(this.zzhl.isEmpty() ? null : this.zzhl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zzau() {
        this.zzhp = false;
        this.zzhf.zzfq.zzim = Collections.emptySet();
        for (Api.AnyClientKey<?> anyClientKey : this.zzhm) {
            if (!this.zzhf.zzjb.containsKey(anyClientKey)) {
                this.zzhf.zzjb.put(anyClientKey, new ConnectionResult(17, null));
            }
        }
    }

    private final void zzav() {
        ArrayList<Future<?>> arrayList = this.zzhu;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Future<?> future = arrayList.get(i);
            i++;
            future.cancel(true);
        }
        this.zzhu.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<Scope> zzaw() {
        if (this.zzgf == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zzgf.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zzgf.getOptionalApiSettings();
        for (Api<?> api : optionalApiSettings.keySet()) {
            if (!this.zzhf.zzjb.containsKey(api.getClientKey())) {
                hashSet.addAll(optionalApiSettings.get(api).mScopes);
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x003f  */
    /* JADX WARN: Code duplicated, block: B:8:0x0015  */
    @GuardedBy("mLock")
    public final void zzb(ConnectionResult connectionResult, Api<?> api, boolean z) {
        boolean z2 = true;
        int priority = api.zzj().getPriority();
        if (z) {
            boolean z3 = connectionResult.hasResolution() || this.zzgk.getErrorResolutionIntent(connectionResult.getErrorCode()) != null;
            if (!z3) {
                z2 = false;
            } else if (this.zzgt != null) {
                z2 = false;
            }
        } else if (this.zzgt != null && priority >= this.zzhi) {
            z2 = false;
        }
        if (z2) {
            this.zzgt = connectionResult;
            this.zzhi = priority;
        }
        this.zzhf.zzjb.put(api.getClientKey(), connectionResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zzd(ConnectionResult connectionResult) {
        return this.zzho && !connectionResult.hasResolution();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zze(ConnectionResult connectionResult) {
        zzav();
        zza(!connectionResult.hasResolution());
        this.zzhf.zzf(connectionResult);
        this.zzhf.zzjf.zzc(connectionResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zze(int i) {
        if (this.zzhj == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zzhf.zzfq.zzbb());
        String strValueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(strValueOf).length() + 23).append("Unexpected callback in ").append(strValueOf).toString());
        Log.w("GoogleApiClientConnecting", new StringBuilder(33).append("mRemainingConnections=").append(this.zzhk).toString());
        String strZzf = zzf(this.zzhj);
        String strZzf2 = zzf(i);
        Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(strZzf).length() + 70 + String.valueOf(strZzf2).length()).append("GoogleApiClient connecting is in step ").append(strZzf).append(" but received callback for step ").append(strZzf2).toString(), new Exception());
        zze(new ConnectionResult(8, null));
        return false;
    }

    private static String zzf(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbc
    public final void begin() {
        zzak zzakVar = null;
        this.zzhf.zzjb.clear();
        this.zzhp = false;
        this.zzgt = null;
        this.zzhj = 0;
        this.zzho = true;
        this.zzhq = false;
        this.zzhs = false;
        HashMap map = new HashMap();
        boolean z = false;
        for (Api<?> api : this.zzgi.keySet()) {
            Api.Client client = this.zzhf.zzil.get(api.getClientKey());
            boolean z2 = (api.zzj().getPriority() == 1) | z;
            boolean zBooleanValue = this.zzgi.get(api).booleanValue();
            if (client.requiresSignIn()) {
                this.zzhp = true;
                if (zBooleanValue) {
                    this.zzhm.add(api.getClientKey());
                } else {
                    this.zzho = false;
                }
            }
            map.put(client, new zzal(this, api, zBooleanValue));
            z = z2;
        }
        if (z) {
            this.zzhp = false;
        }
        if (this.zzhp) {
            this.zzgf.setClientSessionId(Integer.valueOf(System.identityHashCode(this.zzhf.zzfq)));
            zzas zzasVar = new zzas(this, zzakVar);
            this.zzhn = (SignInClient) this.zzdh.buildClient(this.mContext, this.zzhf.zzfq.getLooper(), this.zzgf, this.zzgf.getSignInOptions(), zzasVar, zzasVar);
        }
        this.zzhk = this.zzhf.zzil.size();
        this.zzhu.add(zzbg.zzbe().submit(new zzam(this, map)));
    }

    @Override // com.google.android.gms.common.api.internal.zzbc
    public final void connect() {
    }

    @Override // com.google.android.gms.common.api.internal.zzbc
    public final boolean disconnect() {
        zzav();
        zza(true);
        this.zzhf.zzf(null);
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zzbc
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        this.zzhf.zzfq.zzgo.add(t);
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zzbc
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // com.google.android.gms.common.api.internal.zzbc
    @GuardedBy("mLock")
    public final void onConnected(Bundle bundle) {
        if (zze(1)) {
            if (bundle != null) {
                this.zzhl.putAll(bundle);
            }
            if (zzar()) {
                zzat();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbc
    @GuardedBy("mLock")
    public final void onConnectionSuspended(int i) {
        zze(new ConnectionResult(8, null));
    }

    @Override // com.google.android.gms.common.api.internal.zzbc
    @GuardedBy("mLock")
    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zze(1)) {
            zzb(connectionResult, api, z);
            if (zzar()) {
                zzat();
            }
        }
    }
}
