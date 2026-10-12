package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "SnapshotMetadataChangeCreator")
@SafeParcelable.Reserved({1000})
public final class zze extends com.google.android.gms.games.internal.zzd implements SnapshotMetadataChange {
    public static final Parcelable.Creator<zze> CREATOR = new zzd();

    @SafeParcelable.Field(getter = "getDescription", id = 1)
    private final String description;

    @SafeParcelable.Field(getter = "getProgressValue", id = 6)
    private final Long zzqq;

    @SafeParcelable.Field(getter = "getCoverImageUri", id = 4)
    private final Uri zzqs;

    @SafeParcelable.Field(getter = "getPlayedTimeMillis", id = 2)
    private final Long zzqt;

    @SafeParcelable.Field(getter = "getCoverImageTeleporter", id = 5)
    private BitmapTeleporter zzqu;

    zze() {
        this(null, null, null, null, null);
    }

    @SafeParcelable.Constructor
    zze(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) Long l, @SafeParcelable.Param(id = 5) BitmapTeleporter bitmapTeleporter, @SafeParcelable.Param(id = 4) Uri uri, @SafeParcelable.Param(id = 6) Long l2) {
        this.description = str;
        this.zzqt = l;
        this.zzqu = bitmapTeleporter;
        this.zzqs = uri;
        this.zzqq = l2;
        if (this.zzqu != null) {
            Preconditions.checkState(this.zzqs == null, "Cannot set both a URI and an image");
        } else if (this.zzqs != null) {
            Preconditions.checkState(this.zzqu == null, "Cannot set both a URI and an image");
        }
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final Bitmap getCoverImage() {
        if (this.zzqu == null) {
            return null;
        }
        return this.zzqu.get();
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final String getDescription() {
        return this.description;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final Long getPlayedTimeMillis() {
        return this.zzqt;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final Long getProgressValue() {
        return this.zzqq;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getDescription(), false);
        SafeParcelWriter.writeLongObject(parcel, 2, getPlayedTimeMillis(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzqs, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzqu, i, false);
        SafeParcelWriter.writeLongObject(parcel, 6, getProgressValue(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final BitmapTeleporter zzcm() {
        return this.zzqu;
    }
}
