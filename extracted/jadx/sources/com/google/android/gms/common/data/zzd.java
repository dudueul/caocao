package com.google.android.gms.common.data;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzd implements TextFilterable.StringFilter {
    zzd() {
    }

    @Override // com.google.android.gms.common.data.TextFilterable.StringFilter
    public final boolean matches(String str, String str2) {
        return str.startsWith(str2);
    }
}
