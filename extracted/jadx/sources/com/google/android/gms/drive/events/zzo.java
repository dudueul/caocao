package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "QueryResultEventParcelableCreator")
@SafeParcelable.Reserved({1})
public final class zzo extends com.google.android.gms.drive.zzu implements DriveEvent {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();

    @Nullable
    @SafeParcelable.Field(id = 2)
    private final DataHolder zzat;

    @SafeParcelable.Field(id = 3)
    private final boolean zzco;

    @SafeParcelable.Field(id = 4)
    private final int zzcp;

    @SafeParcelable.Constructor
    public zzo(@SafeParcelable.Param(id = 2) @Nullable DataHolder dataHolder, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) int i) {
        this.zzat = dataHolder;
        this.zzco = z;
        this.zzcp = i;
    }

    @Override // com.google.android.gms.drive.events.DriveEvent
    public final int getType() {
        return 3;
    }

    @Override // com.google.android.gms.drive.zzu
    public final void zza(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzat, i, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzco);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcp);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zzaa() {
        return this.zzcp;
    }

    @Nullable
    public final DataHolder zzy() {
        return this.zzat;
    }

    public final boolean zzz() {
        return this.zzco;
    }
}
