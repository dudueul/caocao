package com.google.android.gms.common.data;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zze implements TextFilterable.StringFilter {
    zze() {
    }

    @Override // com.google.android.gms.common.data.TextFilterable.StringFilter
    public final boolean matches(String str, String str2) {
        if (!str.startsWith(str2)) {
            String strValueOf = String.valueOf(str2);
            if (!str.contains(strValueOf.length() != 0 ? " ".concat(strValueOf) : new String(" "))) {
                return false;
            }
        }
        return true;
    }
}
