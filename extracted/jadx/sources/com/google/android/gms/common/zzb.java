package com.google.android.gms.common;

import java.util.Arrays;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzb extends GoogleCertificates.CertData {
    private final byte[] zzbd;

    zzb(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zzbd = bArr;
    }

    @Override // com.google.android.gms.common.GoogleCertificates.CertData
    final byte[] getBytes() {
        return this.zzbd;
    }
}
