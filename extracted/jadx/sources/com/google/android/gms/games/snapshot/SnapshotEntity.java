package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "SnapshotEntityCreator")
@SafeParcelable.Reserved({1000})
public final class SnapshotEntity extends com.google.android.gms.games.internal.zzd implements Snapshot {
    public static final Parcelable.Creator<SnapshotEntity> CREATOR = new zzc();

    @SafeParcelable.Field(getter = "getMetadata", id = 1)
    private final SnapshotMetadataEntity zzqn;

    @SafeParcelable.Field(getter = "getSnapshotContents", id = 3)
    private final zza zzqo;

    @SafeParcelable.Constructor
    public SnapshotEntity(@SafeParcelable.Param(id = 1) SnapshotMetadata snapshotMetadata, @SafeParcelable.Param(id = 3) zza zzaVar) {
        this.zzqn = new SnapshotMetadataEntity(snapshotMetadata);
        this.zzqo = zzaVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Snapshot) {
            if (this == obj) {
                return true;
            }
            Snapshot snapshot = (Snapshot) obj;
            if (Objects.equal(snapshot.getMetadata(), getMetadata()) && Objects.equal(snapshot.getSnapshotContents(), getSnapshotContents())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Snapshot freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.snapshot.Snapshot
    public final SnapshotMetadata getMetadata() {
        return this.zzqn;
    }

    @Override // com.google.android.gms.games.snapshot.Snapshot
    public final SnapshotContents getSnapshotContents() {
        if (this.zzqo.isClosed()) {
            return null;
        }
        return this.zzqo;
    }

    public final int hashCode() {
        return Objects.hashCode(getMetadata(), getSnapshotContents());
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("Metadata", getMetadata()).add("HasContents", Boolean.valueOf(getSnapshotContents() != null)).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getMetadata(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getSnapshotContents(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
