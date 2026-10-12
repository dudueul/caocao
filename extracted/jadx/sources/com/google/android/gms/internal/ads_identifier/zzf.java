package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.IInterface;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class zzf extends zzb implements zze {
    public static zze zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(AdvertisingInfoServiceStrategy.AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
        return iInterfaceQueryLocalInterface instanceof zze ? (zze) iInterfaceQueryLocalInterface : new zzg(iBinder);
    }
}
