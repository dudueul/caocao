package com.google.android.gms.common.server.response;

import java.io.BufferedReader;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zza implements FastParser.zza<Integer> {
    zza() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zza
    public final /* synthetic */ Integer zzh(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return Integer.valueOf(fastParser.zzd(bufferedReader));
    }
}
