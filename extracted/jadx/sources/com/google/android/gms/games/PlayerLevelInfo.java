package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "PlayerLevelInfoCreator")
@SafeParcelable.Reserved({1000})
public final class PlayerLevelInfo extends com.google.android.gms.games.internal.zzd {
    public static final Parcelable.Creator<PlayerLevelInfo> CREATOR = new zzar();

    @SafeParcelable.Field(getter = "getCurrentXpTotal", id = 1)
    private final long zzcs;

    @SafeParcelable.Field(getter = "getLastLevelUpTimestamp", id = 2)
    private final long zzct;

    @SafeParcelable.Field(getter = "getCurrentLevel", id = 3)
    private final PlayerLevel zzcu;

    @SafeParcelable.Field(getter = "getNextLevel", id = 4)
    private final PlayerLevel zzcv;

    @SafeParcelable.Constructor
    public PlayerLevelInfo(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) PlayerLevel playerLevel, @SafeParcelable.Param(id = 4) PlayerLevel playerLevel2) {
        Preconditions.checkState(j != -1);
        Preconditions.checkNotNull(playerLevel);
        Preconditions.checkNotNull(playerLevel2);
        this.zzcs = j;
        this.zzct = j2;
        this.zzcu = playerLevel;
        this.zzcv = playerLevel2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevelInfo)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        PlayerLevelInfo playerLevelInfo = (PlayerLevelInfo) obj;
        return Objects.equal(Long.valueOf(this.zzcs), Long.valueOf(playerLevelInfo.zzcs)) && Objects.equal(Long.valueOf(this.zzct), Long.valueOf(playerLevelInfo.zzct)) && Objects.equal(this.zzcu, playerLevelInfo.zzcu) && Objects.equal(this.zzcv, playerLevelInfo.zzcv);
    }

    public final PlayerLevel getCurrentLevel() {
        return this.zzcu;
    }

    public final long getCurrentXpTotal() {
        return this.zzcs;
    }

    public final long getLastLevelUpTimestamp() {
        return this.zzct;
    }

    public final PlayerLevel getNextLevel() {
        return this.zzcv;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzcs), Long.valueOf(this.zzct), this.zzcu, this.zzcv);
    }

    public final boolean isMaxLevel() {
        return this.zzcu.equals(this.zzcv);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, getCurrentXpTotal());
        SafeParcelWriter.writeLong(parcel, 2, getLastLevelUpTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 3, getCurrentLevel(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getNextLevel(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
