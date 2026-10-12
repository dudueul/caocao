package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "CustomPropertyCreator")
@SafeParcelable.Reserved({1})
public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzc> CREATOR = new zzd();

    @Nullable
    @SafeParcelable.Field(id = 3)
    final String value;

    @SafeParcelable.Field(id = 2)
    final CustomPropertyKey zzio;

    @SafeParcelable.Constructor
    public zzc(@SafeParcelable.Param(id = 2) CustomPropertyKey customPropertyKey, @SafeParcelable.Param(id = 3) @Nullable String str) {
        Preconditions.checkNotNull(customPropertyKey, "key");
        this.zzio = customPropertyKey;
        this.value = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        zzc zzcVar = (zzc) obj;
        return Objects.equal(this.zzio, zzcVar.zzio) && Objects.equal(this.value, zzcVar.value);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzio, this.value);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzio, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.value, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
