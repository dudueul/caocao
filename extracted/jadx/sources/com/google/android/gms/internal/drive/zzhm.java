package com.google.android.gms.internal.drive;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzhm extends zzir<zzhm> {
    public int versionCode = 1;
    public long zze = -1;
    public long zzf = -1;
    public long zzg = -1;

    public zzhm() {
        this.zzmw = null;
        this.zznf = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzhm)) {
            return false;
        }
        zzhm zzhmVar = (zzhm) obj;
        if (this.versionCode == zzhmVar.versionCode && this.zze == zzhmVar.zze && this.zzf == zzhmVar.zzf && this.zzg == zzhmVar.zzg) {
            if (this.zzmw == null || this.zzmw.isEmpty()) {
                return zzhmVar.zzmw == null || zzhmVar.zzmw.isEmpty();
            }
            return this.zzmw.equals(zzhmVar.zzmw);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzmw == null || this.zzmw.isEmpty()) ? 0 : this.zzmw.hashCode()) + ((((((((((getClass().getName().hashCode() + 527) * 31) + this.versionCode) * 31) + ((int) (this.zze ^ (this.zze >>> 32)))) * 31) + ((int) (this.zzf ^ (this.zzf >>> 32)))) * 31) + ((int) (this.zzg ^ (this.zzg >>> 32)))) * 31);
    }

    @Override // com.google.android.gms.internal.drive.zzix
    public final /* synthetic */ zzix zza(zzio zzioVar) throws IOException {
        while (true) {
            int iZzbd = zzioVar.zzbd();
            switch (iZzbd) {
                case 0:
                    break;
                case 8:
                    this.versionCode = zzioVar.zzbe();
                    break;
                case 16:
                    long jZzbf = zzioVar.zzbf();
                    this.zze = (-(jZzbf & 1)) ^ (jZzbf >>> 1);
                    break;
                case 24:
                    long jZzbf2 = zzioVar.zzbf();
                    this.zzf = (-(jZzbf2 & 1)) ^ (jZzbf2 >>> 1);
                    break;
                case 32:
                    long jZzbf3 = zzioVar.zzbf();
                    this.zzg = (-(jZzbf3 & 1)) ^ (jZzbf3 >>> 1);
                    break;
                default:
                    if (!super.zza(zzioVar, iZzbd)) {
                    }
                    break;
            }
        }
        return this;
    }

    @Override // com.google.android.gms.internal.drive.zzir, com.google.android.gms.internal.drive.zzix
    public final void zza(zzip zzipVar) throws IOException {
        zzipVar.zzb(1, this.versionCode);
        zzipVar.zza(2, this.zze);
        zzipVar.zza(3, this.zzf);
        zzipVar.zza(4, this.zzg);
        super.zza(zzipVar);
    }

    @Override // com.google.android.gms.internal.drive.zzir, com.google.android.gms.internal.drive.zzix
    protected final int zzaq() {
        return super.zzaq() + zzip.zzc(1, this.versionCode) + zzip.zzb(2, this.zze) + zzip.zzb(3, this.zzf) + zzip.zzb(4, this.zzg);
    }
}
