package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzi {
    static MetadataField<?> zza(MetadataBundle metadataBundle) {
        Set<MetadataField<?>> setZzay = metadataBundle.zzay();
        if (setZzay.size() != 1) {
            throw new IllegalArgumentException("bundle should have exactly 1 populated field");
        }
        return setZzay.iterator().next();
    }
}
