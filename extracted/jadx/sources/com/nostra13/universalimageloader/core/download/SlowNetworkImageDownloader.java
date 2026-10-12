package com.nostra13.universalimageloader.core.download;

import com.nostra13.universalimageloader.core.assist.FlushedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SlowNetworkImageDownloader implements ImageDownloader {
    private final ImageDownloader wrappedDownloader;

    public SlowNetworkImageDownloader(ImageDownloader imageDownloader) {
        this.wrappedDownloader = imageDownloader;
    }

    @Override // com.nostra13.universalimageloader.core.download.ImageDownloader
    public InputStream getStream(String str, Object obj) throws IOException {
        InputStream stream = this.wrappedDownloader.getStream(str, obj);
        switch (ImageDownloader.Scheme.ofUri(str)) {
            case HTTP:
            case HTTPS:
                return new FlushedInputStream(stream);
            default:
                return stream;
        }
    }
}
