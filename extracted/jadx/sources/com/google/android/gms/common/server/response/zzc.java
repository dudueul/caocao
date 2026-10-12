package com.google.android.gms.common.server.response;

import java.io.BufferedReader;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzc implements FastParser.zza<Float> {
    zzc() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zza
    public final /* synthetic */ Float zzh(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return Float.valueOf(fastParser.zzg(bufferedReader));
    }
}
