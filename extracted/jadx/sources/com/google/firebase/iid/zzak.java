package com.google.firebase.iid;

import android.os.Bundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzak extends zzai<Bundle> {
    zzak(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    @Override // com.google.firebase.iid.zzai
    final boolean zzaa() {
        return false;
    }

    @Override // com.google.firebase.iid.zzai
    final void zzb(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        finish(bundle2);
    }
}
