package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class PlayerRef extends DataBufferRef implements Player {
    private final PlayerLevelInfo zzce;
    private final com.google.android.gms.games.internal.player.zze zzcw;
    private final com.google.android.gms.games.internal.player.zzd zzcx;

    public PlayerRef(DataHolder dataHolder, int i) {
        this(dataHolder, i, null);
    }

    public PlayerRef(DataHolder dataHolder, int i, String str) {
        super(dataHolder, i);
        this.zzcw = new com.google.android.gms.games.internal.player.zze(str);
        this.zzcx = new com.google.android.gms.games.internal.player.zzd(dataHolder, i, this.zzcw);
        if (!((hasNull(this.zzcw.zzlu) || getLong(this.zzcw.zzlu) == -1) ? false : true)) {
            this.zzce = null;
            return;
        }
        int integer = getInteger(this.zzcw.zzlv);
        int integer2 = getInteger(this.zzcw.zzly);
        PlayerLevel playerLevel = new PlayerLevel(integer, getLong(this.zzcw.zzlw), getLong(this.zzcw.zzlx));
        this.zzce = new PlayerLevelInfo(getLong(this.zzcw.zzlu), getLong(this.zzcw.zzma), playerLevel, integer != integer2 ? new PlayerLevel(integer2, getLong(this.zzcw.zzlx), getLong(this.zzcw.zzlz)) : playerLevel);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return PlayerEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ Player freeze() {
        return new PlayerEntity(this);
    }

    @Override // com.google.android.gms.games.Player
    public final Uri getBannerImageLandscapeUri() {
        return parseUri(this.zzcw.zzmk);
    }

    @Override // com.google.android.gms.games.Player
    public final String getBannerImageLandscapeUrl() {
        return getString(this.zzcw.zzml);
    }

    @Override // com.google.android.gms.games.Player
    public final Uri getBannerImagePortraitUri() {
        return parseUri(this.zzcw.zzmm);
    }

    @Override // com.google.android.gms.games.Player
    public final String getBannerImagePortraitUrl() {
        return getString(this.zzcw.zzmn);
    }

    @Override // com.google.android.gms.games.Player
    public final String getDisplayName() {
        return getString(this.zzcw.zzlm);
    }

    @Override // com.google.android.gms.games.Player
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        copyToBuffer(this.zzcw.zzlm, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.Player
    public final Uri getHiResImageUri() {
        return parseUri(this.zzcw.zzlp);
    }

    @Override // com.google.android.gms.games.Player
    public final String getHiResImageUrl() {
        return getString(this.zzcw.zzlq);
    }

    @Override // com.google.android.gms.games.Player
    public final Uri getIconImageUri() {
        return parseUri(this.zzcw.zzln);
    }

    @Override // com.google.android.gms.games.Player
    public final String getIconImageUrl() {
        return getString(this.zzcw.zzlo);
    }

    @Override // com.google.android.gms.games.Player
    public final long getLastPlayedWithTimestamp() {
        if (!hasColumn(this.zzcw.zzlt) || hasNull(this.zzcw.zzlt)) {
            return -1L;
        }
        return getLong(this.zzcw.zzlt);
    }

    @Override // com.google.android.gms.games.Player
    public final PlayerLevelInfo getLevelInfo() {
        return this.zzce;
    }

    @Override // com.google.android.gms.games.Player
    public final String getName() {
        return getString(this.zzcw.name);
    }

    @Override // com.google.android.gms.games.Player
    public final String getPlayerId() {
        return getString(this.zzcw.zzll);
    }

    @Override // com.google.android.gms.games.Player
    public final long getRetrievedTimestamp() {
        return getLong(this.zzcw.zzlr);
    }

    @Override // com.google.android.gms.games.Player
    public final String getTitle() {
        return getString(this.zzcw.zzcc);
    }

    @Override // com.google.android.gms.games.Player
    public final void getTitle(CharArrayBuffer charArrayBuffer) {
        copyToBuffer(this.zzcw.zzcc, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.Player
    public final boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    @Override // com.google.android.gms.games.Player
    public final boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return PlayerEntity.zza(this);
    }

    @Override // com.google.android.gms.games.Player
    public final boolean isMuted() {
        return getBoolean(this.zzcw.zzmq);
    }

    public final String toString() {
        return PlayerEntity.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        ((PlayerEntity) ((Player) freeze())).writeToParcel(parcel, i);
    }

    @Override // com.google.android.gms.games.Player
    public final String zzg() {
        return getString(this.zzcw.zzch);
    }

    @Override // com.google.android.gms.games.Player
    public final boolean zzh() {
        return getBoolean(this.zzcw.zzmj);
    }

    @Override // com.google.android.gms.games.Player
    public final int zzi() {
        return getInteger(this.zzcw.zzls);
    }

    @Override // com.google.android.gms.games.Player
    public final boolean zzj() {
        return getBoolean(this.zzcw.zzmc);
    }

    @Override // com.google.android.gms.games.Player
    public final com.google.android.gms.games.internal.player.zza zzk() {
        if (hasNull(this.zzcw.zzmd)) {
            return null;
        }
        return this.zzcx;
    }

    @Override // com.google.android.gms.games.Player
    public final int zzl() {
        return getInteger(this.zzcw.zzmo);
    }

    @Override // com.google.android.gms.games.Player
    public final long zzm() {
        return getLong(this.zzcw.zzmp);
    }
}
