package com.google.android.gms.common.server.response;

import java.io.BufferedReader;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zze implements FastParser.zza<Boolean> {
    zze() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zza
    public final /* synthetic */ Boolean zzh(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return Boolean.valueOf(fastParser.zza(bufferedReader, false));
    }
}
