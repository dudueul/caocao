package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "UserMetadataCreator")
@SafeParcelable.Reserved({1})
public class UserMetadata extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<UserMetadata> CREATOR = new zzt();

    @SafeParcelable.Field(id = 2)
    private final String zzbm;

    @Nullable
    @SafeParcelable.Field(id = 3)
    private final String zzbn;

    @Nullable
    @SafeParcelable.Field(id = 4)
    private final String zzbo;

    @SafeParcelable.Field(id = 5)
    private final boolean zzbp;

    @Nullable
    @SafeParcelable.Field(id = 6)
    private final String zzbq;

    @SafeParcelable.Constructor
    public UserMetadata(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) @Nullable String str2, @SafeParcelable.Param(id = 4) @Nullable String str3, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) @Nullable String str4) {
        this.zzbm = str;
        this.zzbn = str2;
        this.zzbo = str3;
        this.zzbp = z;
        this.zzbq = str4;
    }

    public String toString() {
        return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", this.zzbm, this.zzbn, this.zzbo, Boolean.valueOf(this.zzbp), this.zzbq);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbm, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbn, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbo, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzbp);
        SafeParcelWriter.writeString(parcel, 6, this.zzbq, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
