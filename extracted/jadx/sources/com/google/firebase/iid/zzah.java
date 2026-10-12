package com.google.firebase.iid;

import android.os.Bundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzah extends zzai<Void> {
    zzah(int i, int i2, Bundle bundle) {
        super(i, 2, bundle);
    }

    @Override // com.google.firebase.iid.zzai
    final boolean zzaa() {
        return true;
    }

    @Override // com.google.firebase.iid.zzai
    final void zzb(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            finish(null);
        } else {
            zza(new zzaj(4, "Invalid response to one way request"));
        }
    }
}
