package com.google.firebase.iid;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzv implements InstanceIdResult {
    private final String zzbg;
    private final String zzbh;

    zzv(String str, String str2) {
        this.zzbg = str;
        this.zzbh = str2;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getId() {
        return this.zzbg;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getToken() {
        return this.zzbh;
    }
}
