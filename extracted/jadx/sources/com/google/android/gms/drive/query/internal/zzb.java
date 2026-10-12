package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "ComparisonFilterCreator")
@SafeParcelable.Reserved({1000})
public final class zzb<T> extends zza {
    public static final zzc CREATOR = new zzc();

    @SafeParcelable.Field(id = 1)
    private final zzx zzlj;

    @SafeParcelable.Field(id = 2)
    private final MetadataBundle zzlk;
    private final MetadataField<T> zzll;

    public zzb(zzx zzxVar, SearchableMetadataField<T> searchableMetadataField, T t) {
        this(zzxVar, MetadataBundle.zza(searchableMetadataField, t));
    }

    @SafeParcelable.Constructor
    zzb(@SafeParcelable.Param(id = 1) zzx zzxVar, @SafeParcelable.Param(id = 2) MetadataBundle metadataBundle) {
        this.zzlj = zzxVar;
        this.zzlk = metadataBundle;
        this.zzll = (MetadataField<T>) zzi.zza(metadataBundle);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzlk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.drive.query.Filter
    public final <F> F zza(zzj<F> zzjVar) {
        return zzjVar.zza(this.zzlj, this.zzll, this.zzlk.zza(this.zzll));
    }
}
