package com.nostra13.universalimageloader.core.download;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NetworkDeniedImageDownloader implements ImageDownloader {
    private final ImageDownloader wrappedDownloader;

    public NetworkDeniedImageDownloader(ImageDownloader imageDownloader) {
        this.wrappedDownloader = imageDownloader;
    }

    @Override // com.nostra13.universalimageloader.core.download.ImageDownloader
    public InputStream getStream(String str, Object obj) throws IOException {
        switch (ImageDownloader.Scheme.ofUri(str)) {
            case HTTP:
            case HTTPS:
                throw new IllegalStateException();
            default:
                return this.wrappedDownloader.getStream(str, obj);
        }
    }
}
