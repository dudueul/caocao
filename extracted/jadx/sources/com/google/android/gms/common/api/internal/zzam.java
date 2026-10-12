package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzam extends zzat {
    final /* synthetic */ zzaj zzhv;
    private final Map<Api.Client, zzal> zzhx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzam(zzaj zzajVar, Map<Api.Client, zzal> map) {
        super(zzajVar, null);
        this.zzhv = zzajVar;
        this.zzhx = map;
    }

    @Override // com.google.android.gms.common.api.internal.zzat
    @WorkerThread
    public final void zzaq() {
        int i = 0;
        GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(this.zzhv.zzgk);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client client : this.zzhx.keySet()) {
            if (!client.requiresGooglePlayServices() || this.zzhx.get(client).zzfo) {
                arrayList2.add(client);
            } else {
                arrayList.add(client);
            }
        }
        int clientAvailability = -1;
        if (!arrayList.isEmpty()) {
            ArrayList arrayList3 = arrayList;
            int size = arrayList3.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList3.get(i2);
                i2++;
                clientAvailability = googleApiAvailabilityCache.getClientAvailability(this.zzhv.mContext, (Api.Client) obj);
                if (clientAvailability != 0) {
                    break;
                }
            }
        } else {
            ArrayList arrayList4 = arrayList2;
            int size2 = arrayList4.size();
            while (i < size2) {
                Object obj2 = arrayList4.get(i);
                i++;
                clientAvailability = googleApiAvailabilityCache.getClientAvailability(this.zzhv.mContext, (Api.Client) obj2);
                if (clientAvailability == 0) {
                    break;
                }
            }
        }
        int i3 = clientAvailability;
        if (i3 != 0) {
            this.zzhv.zzhf.zza(new zzan(this, this.zzhv, new ConnectionResult(i3, null)));
            return;
        }
        if (this.zzhv.zzhp) {
            this.zzhv.zzhn.connect();
        }
        for (Api.Client client2 : this.zzhx.keySet()) {
            zzal zzalVar = this.zzhx.get(client2);
            if (!client2.requiresGooglePlayServices() || googleApiAvailabilityCache.getClientAvailability(this.zzhv.mContext, client2) == 0) {
                client2.connect(zzalVar);
            } else {
                this.zzhv.zzhf.zza(new zzao(this, this.zzhv, zzalVar));
            }
        }
    }
}
