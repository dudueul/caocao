package com.navercorp.volleyextensions.volleyer.multipart;

import com.navercorp.volleyextensions.volleyer.http.ContentType;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface Part extends Writable {
    InputStream getContent() throws IOException;

    ContentType getContentType();

    String getName();
}
