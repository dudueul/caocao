package com.google.android.gms.common;

import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzi extends zzg {
    private final String packageName;
    private final GoogleCertificates.CertData zzbn;
    private final boolean zzbo;
    private final boolean zzbp;

    private zzi(String str, GoogleCertificates.CertData certData, boolean z, boolean z2) {
        super(false, null, null);
        this.packageName = str;
        this.zzbn = certData;
        this.zzbo = z;
        this.zzbp = z2;
    }

    @Override // com.google.android.gms.common.zzg
    final String getErrorMessage() {
        String str = this.zzbp ? "debug cert rejected" : "not whitelisted";
        String str2 = this.packageName;
        String strBytesToStringLowercase = Hex.bytesToStringLowercase(AndroidUtilsLight.getMessageDigest("SHA-1").digest(this.zzbn.getBytes()));
        return new StringBuilder(String.valueOf(str).length() + 44 + String.valueOf(str2).length() + String.valueOf(strBytesToStringLowercase).length()).append(str).append(": pkg=").append(str2).append(", sha1=").append(strBytesToStringLowercase).append(", atk=").append(this.zzbo).append(", ver=12451009.false").toString();
    }
}
