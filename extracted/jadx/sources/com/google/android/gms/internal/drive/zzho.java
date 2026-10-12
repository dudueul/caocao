package com.google.android.gms.internal.drive;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzho extends zzir<zzho> {
    public long zzac = -1;
    public long zzf = -1;

    public zzho() {
        this.zzmw = null;
        this.zznf = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzho)) {
            return false;
        }
        zzho zzhoVar = (zzho) obj;
        if (this.zzac == zzhoVar.zzac && this.zzf == zzhoVar.zzf) {
            if (this.zzmw == null || this.zzmw.isEmpty()) {
                return zzhoVar.zzmw == null || zzhoVar.zzmw.isEmpty();
            }
            return this.zzmw.equals(zzhoVar.zzmw);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzmw == null || this.zzmw.isEmpty()) ? 0 : this.zzmw.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzac ^ (this.zzac >>> 32)))) * 31) + ((int) (this.zzf ^ (this.zzf >>> 32)))) * 31);
    }

    @Override // com.google.android.gms.internal.drive.zzix
    public final /* synthetic */ zzix zza(zzio zzioVar) throws IOException {
        while (true) {
            int iZzbd = zzioVar.zzbd();
            switch (iZzbd) {
                case 0:
                    break;
                case 8:
                    long jZzbf = zzioVar.zzbf();
                    this.zzac = (-(jZzbf & 1)) ^ (jZzbf >>> 1);
                    break;
                case 16:
                    long jZzbf2 = zzioVar.zzbf();
                    this.zzf = (-(jZzbf2 & 1)) ^ (jZzbf2 >>> 1);
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
        zzipVar.zza(1, this.zzac);
        zzipVar.zza(2, this.zzf);
        super.zza(zzipVar);
    }

    @Override // com.google.android.gms.internal.drive.zzir, com.google.android.gms.internal.drive.zzix
    protected final int zzaq() {
        return super.zzaq() + zzip.zzb(1, this.zzac) + zzip.zzb(2, this.zzf);
    }
}
