package com.google.android.gms.common.server.response;

import java.io.BufferedReader;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzf implements FastParser.zza<String> {
    zzf() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zza
    public final /* synthetic */ String zzh(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return fastParser.zzc(bufferedReader);
    }
}
