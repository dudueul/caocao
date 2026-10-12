package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AvailabilityException extends Exception {
    private final ArrayMap<zzh<?>, ConnectionResult> zzcc;

    public AvailabilityException(ArrayMap<zzh<?>, ConnectionResult> arrayMap) {
        this.zzcc = arrayMap;
    }

    public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> googleApi) {
        Object objZzm = googleApi.zzm();
        Preconditions.checkArgument(this.zzcc.get(objZzm) != null, "The given API was not part of the availability request.");
        return this.zzcc.get(objZzm);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (zzh<?> zzhVar : this.zzcc.keySet()) {
            ConnectionResult connectionResult = this.zzcc.get(zzhVar);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String strZzq = zzhVar.zzq();
            String strValueOf = String.valueOf(connectionResult);
            arrayList.add(new StringBuilder(String.valueOf(strZzq).length() + 2 + String.valueOf(strValueOf).length()).append(strZzq).append(": ").append(strValueOf).toString());
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join("; ", arrayList));
        return sb.toString();
    }

    public final ArrayMap<zzh<?>, ConnectionResult> zzl() {
        return this.zzcc;
    }
}
