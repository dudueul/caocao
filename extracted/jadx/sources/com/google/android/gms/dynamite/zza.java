package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zza implements DynamiteModule.VersionPolicy.IVersions {
    zza() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int getLocalVersion(Context context, String str) {
        return DynamiteModule.getLocalVersion(context, str);
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int getRemoteVersion(Context context, String str, boolean z) throws DynamiteModule.LoadingException {
        return DynamiteModule.getRemoteVersion(context, str, z);
    }
}
