package com.google.android.gms.common.server.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzg implements FastParser.zza<BigInteger> {
    zzg() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zza
    public final /* synthetic */ BigInteger zzh(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return fastParser.zzf(bufferedReader);
    }
}
