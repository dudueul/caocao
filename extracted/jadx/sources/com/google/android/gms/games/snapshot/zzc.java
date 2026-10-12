package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzc implements Parcelable.Creator<SnapshotEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SnapshotEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zza zzaVar = null;
        SnapshotMetadataEntity snapshotMetadataEntity = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    snapshotMetadataEntity = (SnapshotMetadataEntity) SafeParcelReader.createParcelable(parcel, header, SnapshotMetadataEntity.CREATOR);
                    break;
                case 2:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 3:
                    zzaVar = (zza) SafeParcelReader.createParcelable(parcel, header, zza.CREATOR);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SnapshotEntity(snapshotMetadataEntity, zzaVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SnapshotEntity[] newArray(int i) {
        return new SnapshotEntity[i];
    }
}
