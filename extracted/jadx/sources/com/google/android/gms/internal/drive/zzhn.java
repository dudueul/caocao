package com.google.android.gms.internal.drive;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzhn extends zzir<zzhn> {
    public int versionCode = 1;
    public String zzab = "";
    public long zzac = -1;
    public long zzf = -1;
    public int zzad = -1;

    public zzhn() {
        this.zzmw = null;
        this.zznf = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzhn)) {
            return false;
        }
        zzhn zzhnVar = (zzhn) obj;
        if (this.versionCode != zzhnVar.versionCode) {
            return false;
        }
        if (this.zzab == null) {
            if (zzhnVar.zzab != null) {
                return false;
            }
        } else if (!this.zzab.equals(zzhnVar.zzab)) {
            return false;
        }
        if (this.zzac == zzhnVar.zzac && this.zzf == zzhnVar.zzf && this.zzad == zzhnVar.zzad) {
            if (this.zzmw == null || this.zzmw.isEmpty()) {
                return zzhnVar.zzmw == null || zzhnVar.zzmw.isEmpty();
            }
            return this.zzmw.equals(zzhnVar.zzmw);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((((((((this.zzab == null ? 0 : this.zzab.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.versionCode) * 31)) * 31) + ((int) (this.zzac ^ (this.zzac >>> 32)))) * 31) + ((int) (this.zzf ^ (this.zzf >>> 32)))) * 31) + this.zzad) * 31;
        if (this.zzmw != null && !this.zzmw.isEmpty()) {
            iHashCode = this.zzmw.hashCode();
        }
        return iHashCode2 + iHashCode;
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
                case 18:
                    this.zzab = zzioVar.readString();
                    break;
                case 24:
                    long jZzbf = zzioVar.zzbf();
                    this.zzac = (-(jZzbf & 1)) ^ (jZzbf >>> 1);
                    break;
                case 32:
                    long jZzbf2 = zzioVar.zzbf();
                    this.zzf = (-(jZzbf2 & 1)) ^ (jZzbf2 >>> 1);
                    break;
                case 40:
                    this.zzad = zzioVar.zzbe();
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
        String str = this.zzab;
        zzipVar.zzd(2, 2);
        zzipVar.zzh(str);
        zzipVar.zza(3, this.zzac);
        zzipVar.zza(4, this.zzf);
        if (this.zzad != -1) {
            zzipVar.zzb(5, this.zzad);
        }
        super.zza(zzipVar);
    }

    @Override // com.google.android.gms.internal.drive.zzir, com.google.android.gms.internal.drive.zzix
    protected final int zzaq() {
        int iZzaq = super.zzaq() + zzip.zzc(1, this.versionCode) + zzip.zzi(this.zzab) + zzip.zzo(2) + zzip.zzb(3, this.zzac) + zzip.zzb(4, this.zzf);
        return this.zzad != -1 ? iZzaq + zzip.zzc(5, this.zzad) : iZzaq;
    }
}
