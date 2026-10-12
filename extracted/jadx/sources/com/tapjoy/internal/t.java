package com.tapjoy.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class t implements bd {
    public static final t a = new t();

    private t() {
    }

    @Override // com.tapjoy.internal.be
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final Bitmap b(final InputStream inputStream) {
        try {
            return (Bitmap) y.a(new bb() { // from class: com.tapjoy.internal.t.1
                @Override // com.tapjoy.internal.bb, java.util.concurrent.Callable
                public final /* synthetic */ Object call() {
                    return inputStream instanceof bc ? BitmapFactory.decodeStream(inputStream) : BitmapFactory.decodeStream(new bc(inputStream));
                }
            });
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    @Override // com.tapjoy.internal.bf
    public final /* synthetic */ void a(OutputStream outputStream, Object obj) {
        if (!((Bitmap) obj).compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
            throw new RuntimeException();
        }
    }
}
