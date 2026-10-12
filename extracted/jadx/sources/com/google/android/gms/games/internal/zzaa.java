package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "PopupLocationInfoParcelableCreator")
@SafeParcelable.Reserved({1000})
public final class zzaa extends zzd {
    public static final Parcelable.Creator<zzaa> CREATOR = new zzab();

    @SafeParcelable.Field(getter = "getInfoBundle", id = 1)
    private final Bundle zzja;

    @SafeParcelable.Field(getter = "getWindowToken", id = 2)
    private final IBinder zzjb;

    @SafeParcelable.Constructor
    zzaa(@SafeParcelable.Param(id = 1) Bundle bundle, @SafeParcelable.Param(id = 2) IBinder iBinder) {
        this.zzja = bundle;
        this.zzjb = iBinder;
    }

    public zzaa(zzae zzaeVar) {
        this.zzja = zzaeVar.zzbk();
        this.zzjb = zzaeVar.zzjb;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzja, false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzjb, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
