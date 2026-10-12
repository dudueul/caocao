package com.google.android.gms.common.server.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzh implements FastParser.zza<BigDecimal> {
    zzh() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zza
    public final /* synthetic */ BigDecimal zzh(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return fastParser.zzi(bufferedReader);
    }
}
