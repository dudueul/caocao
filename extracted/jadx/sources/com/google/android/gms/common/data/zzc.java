package com.google.android.gms.common.data;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzc implements TextFilterable.StringFilter {
    zzc() {
    }

    @Override // com.google.android.gms.common.data.TextFilterable.StringFilter
    public final boolean matches(String str, String str2) {
        return str.contains(str2);
    }
}
