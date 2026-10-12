package com.tapjoy.internal;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.InputStream;
import java.net.ContentHandler;
import java.net.URLConnection;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class s extends ContentHandler {
    public static final s a = new s();

    private s() {
    }

    @Nullable
    public static Bitmap a(InputStream inputStream) throws IOException {
        try {
            return t.a.b(inputStream);
        } finally {
            inputStream.close();
        }
    }

    @Nullable
    private static Bitmap a(URLConnection uRLConnection) throws IOException {
        InputStream inputStream = uRLConnection.getInputStream();
        try {
            return t.a.b(inputStream);
        } finally {
            inputStream.close();
        }
    }

    @Override // java.net.ContentHandler
    @Nullable
    public final /* synthetic */ Object getContent(URLConnection uRLConnection) {
        return a(uRLConnection);
    }
}
