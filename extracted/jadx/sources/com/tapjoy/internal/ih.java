package com.tapjoy.internal;

import android.graphics.Bitmap;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class ih implements ic.a {
    ih() {
    }

    @Override // com.tapjoy.internal.ic.a
    @Nonnull
    public final Bitmap a(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // com.tapjoy.internal.ic.a
    public final byte[] a(int i) {
        return new byte[i];
    }

    @Override // com.tapjoy.internal.ic.a
    public final int[] b(int i) {
        return new int[i];
    }
}
