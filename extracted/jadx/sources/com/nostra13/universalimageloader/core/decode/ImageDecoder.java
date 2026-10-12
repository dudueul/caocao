package com.nostra13.universalimageloader.core.decode;

import android.graphics.Bitmap;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface ImageDecoder {
    Bitmap decode(ImageDecodingInfo imageDecodingInfo) throws IOException;
}
