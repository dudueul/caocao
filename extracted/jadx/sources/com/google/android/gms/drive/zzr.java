package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "PermissionCreator")
@SafeParcelable.Reserved({1})
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();

    @SafeParcelable.Field(getter = "getAccountType", id = 3)
    private int accountType;

    @Nullable
    @SafeParcelable.Field(getter = "getAccountIdentifier", id = 2)
    private String zzbe;

    @Nullable
    @SafeParcelable.Field(getter = "getAccountDisplayName", id = 4)
    private String zzbf;

    @Nullable
    @SafeParcelable.Field(getter = "getPhotoLink", id = 5)
    private String zzbg;

    @SafeParcelable.Field(getter = "getRole", id = 6)
    private int zzbh;

    @SafeParcelable.Field(getter = "isLinkRequiredForAccess", id = 7)
    private boolean zzbi;

    @SafeParcelable.Constructor
    public zzr(@SafeParcelable.Param(id = 2) @Nullable String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) @Nullable String str2, @SafeParcelable.Param(id = 5) @Nullable String str3, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) boolean z) {
        this.zzbe = str;
        this.accountType = i;
        this.zzbf = str2;
        this.zzbg = str3;
        this.zzbh = i2;
        this.zzbi = z;
    }

    private static boolean zzb(int i) {
        switch (i) {
            case 256:
            case 257:
            case 258:
                return true;
            default:
                return false;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        zzr zzrVar = (zzr) obj;
        return Objects.equal(this.zzbe, zzrVar.zzbe) && this.accountType == zzrVar.accountType && this.zzbh == zzrVar.zzbh && this.zzbi == zzrVar.zzbi;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbe, Integer.valueOf(this.accountType), Integer.valueOf(this.zzbh), Boolean.valueOf(this.zzbi));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        boolean z;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, !zzb(this.accountType) ? null : this.zzbe, false);
        SafeParcelWriter.writeInt(parcel, 3, !zzb(this.accountType) ? -1 : this.accountType);
        SafeParcelWriter.writeString(parcel, 4, this.zzbf, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbg, false);
        switch (this.zzbh) {
            case 0:
            case 1:
            case 2:
            case 3:
                z = true;
                break;
            default:
                z = false;
                break;
        }
        SafeParcelWriter.writeInt(parcel, 6, z ? this.zzbh : -1);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzbi);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
