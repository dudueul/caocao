package com.naver.glink.android.sdk.ui.streaming.publish.publisher;

import android.media.MediaCodec;
import android.support.annotation.NonNull;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: Encoder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class b {
    private a a;

    /* JADX INFO: compiled from: Encoder.java */
    public interface a {
        void a(@NonNull MediaCodec.BufferInfo bufferInfo, ByteBuffer byteBuffer, boolean z);
    }

    b() {
    }

    void a(@NonNull MediaCodec.BufferInfo bufferInfo, ByteBuffer byteBuffer, boolean z) {
        if (this.a != null) {
            this.a.a(bufferInfo, byteBuffer, z);
        }
    }

    void a(a aVar) {
        this.a = aVar;
    }
}
