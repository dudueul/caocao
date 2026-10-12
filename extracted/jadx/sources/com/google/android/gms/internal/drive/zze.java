package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.Objects;
import java.util.Locale;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zze implements com.google.android.gms.drive.events.zzk {
    private final com.google.android.gms.drive.events.zzm zzct;
    private final long zzcu;
    private final long zzcv;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.drive.events.zzm, com.google.android.gms.internal.drive.zzf] */
    public zze(zzh zzhVar) {
        this.zzct = new zzf(zzhVar);
        this.zzcu = zzhVar.zzcu;
        this.zzcv = zzhVar.zzcv;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        zze zzeVar = (zze) obj;
        return Objects.equal(this.zzct, zzeVar.zzct) && this.zzcu == zzeVar.zzcu && this.zzcv == zzeVar.zzcv;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzcv), Long.valueOf(this.zzcu), Long.valueOf(this.zzcv));
    }

    public final String toString() {
        return String.format(Locale.US, "FileTransferProgress[FileTransferState: %s, BytesTransferred: %d, TotalBytes: %d]", this.zzct.toString(), Long.valueOf(this.zzcu), Long.valueOf(this.zzcv));
    }
}
