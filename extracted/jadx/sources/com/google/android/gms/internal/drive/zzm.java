package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "CloseContentsAndUpdateMetadataRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzm> CREATOR = new zzn();

    @SafeParcelable.Field(id = 6)
    private final String zzal;

    @SafeParcelable.Field(id = 5)
    private final boolean zzam;

    @SafeParcelable.Field(defaultValue = "true", id = 10)
    private final boolean zzar;

    @SafeParcelable.Field(id = 2)
    private final DriveId zzdb;

    @SafeParcelable.Field(id = 3)
    private final MetadataBundle zzdc;

    @Nullable
    @SafeParcelable.Field(id = 4)
    private final Contents zzdd;

    @SafeParcelable.Field(id = 7)
    private final int zzde;

    @SafeParcelable.Field(id = 8)
    private final int zzdf;

    @SafeParcelable.Field(id = 9)
    private final boolean zzdg;

    @VisibleForTesting
    public zzm(DriveId driveId, MetadataBundle metadataBundle, int i, boolean z, com.google.android.gms.drive.zzn zznVar) {
        this(driveId, metadataBundle, null, zznVar.zzl(), zznVar.zzk(), zznVar.zzm(), i, z, zznVar.zzo());
    }

    @SafeParcelable.Constructor
    zzm(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) MetadataBundle metadataBundle, @SafeParcelable.Param(id = 4) Contents contents, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) int i, @SafeParcelable.Param(id = 8) int i2, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 10) boolean z3) {
        this.zzdb = driveId;
        this.zzdc = metadataBundle;
        this.zzdd = contents;
        this.zzam = z;
        this.zzal = str;
        this.zzde = i;
        this.zzdf = i2;
        this.zzdg = z2;
        this.zzar = z3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdb, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdc, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzdd, i, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzam);
        SafeParcelWriter.writeString(parcel, 6, this.zzal, false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzde);
        SafeParcelWriter.writeInt(parcel, 8, this.zzdf);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzdg);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzar);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
