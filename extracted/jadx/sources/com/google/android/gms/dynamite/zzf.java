package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzf implements DynamiteModule.VersionPolicy {
    zzf() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        selectionResult.localVersion = iVersions.getLocalVersion(context, str);
        selectionResult.remoteVersion = iVersions.getRemoteVersion(context, str, true);
        if (selectionResult.localVersion == 0 && selectionResult.remoteVersion == 0) {
            selectionResult.selection = 0;
        } else if (selectionResult.remoteVersion >= selectionResult.localVersion) {
            selectionResult.selection = 1;
        } else {
            selectionResult.selection = -1;
        }
        return selectionResult;
    }
}
