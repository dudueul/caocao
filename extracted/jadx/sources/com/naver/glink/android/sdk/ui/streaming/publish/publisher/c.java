package com.naver.glink.android.sdk.ui.streaming.publish.publisher;

import android.content.Context;
import android.media.MediaCodec;
import android.media.projection.MediaProjection;
import android.support.annotation.NonNull;
import com.naver.glink.android.sdk.a.h;
import com.naver.vapp.broadcast.publisher.RTMPPublisher;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: compiled from: Publisher.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    private final Context a;
    private final PublisherConfigure b;
    private final MediaProjection c;
    private final int d;
    private d e;
    private a f;
    private RTMPPublisher g;

    public c(Context context, PublisherConfigure publisherConfigure, MediaProjection mediaProjection, int i) {
        this.a = context;
        this.b = publisherConfigure;
        this.c = mediaProjection;
        this.d = i;
    }

    private int a(byte[] bArr) {
        for (int i = 4; i < bArr.length - 4; i++) {
            if (bArr[i] == 0 && bArr[i + 1] == 0 && bArr[i + 2] == 0 && bArr[i + 3] == 1) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MediaCodec.BufferInfo bufferInfo, ByteBuffer byteBuffer) {
        if (this.g != null) {
            return;
        }
        byte[] bArr = new byte[bufferInfo.size];
        byteBuffer.get(bArr, bufferInfo.offset, bufferInfo.size);
        int iA = a(bArr);
        if (iA != -1) {
            this.g = new RTMPPublisher();
            this.g.Start(this.b.a(), this.b.i, "", "", Arrays.copyOfRange(bArr, 4, iA), Arrays.copyOfRange(bArr, iA + 4, bArr.length));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(@NonNull MediaCodec.BufferInfo bufferInfo, ByteBuffer byteBuffer, boolean z) {
        if (this.g != null) {
            this.g.PushBuffer(9, byteBuffer, bufferInfo.size, bufferInfo.presentationTimeUs / 1000, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(@NonNull MediaCodec.BufferInfo bufferInfo, ByteBuffer byteBuffer) {
        if (this.g != null) {
            this.g.PushBuffer(8, byteBuffer, bufferInfo.size, bufferInfo.presentationTimeUs / 1000, false);
        }
    }

    public void a() {
        if (h.a(this.a, this.c, this.d)) {
            c();
        }
    }

    public void a(boolean z) {
        if (this.f != null) {
            this.f.a(z);
        }
    }

    public void b() {
        d();
        if (this.g != null) {
            this.g.Stop();
            this.g = null;
        }
    }

    public void c() {
        d();
        this.e = new d(this.b, this.d, this.c);
        this.e.a(new b.a() { // from class: com.naver.glink.android.sdk.ui.streaming.publish.publisher.c.1
            @Override // com.naver.glink.android.sdk.ui.streaming.publish.publisher.b.a
            public void a(@NonNull MediaCodec.BufferInfo bufferInfo, ByteBuffer byteBuffer, boolean z) {
                if ((bufferInfo.flags & 2) != 0) {
                    c.this.a(bufferInfo, byteBuffer);
                } else {
                    c.this.a(bufferInfo, byteBuffer, z);
                }
            }
        });
        this.e.a();
        this.f = new a();
        this.f.a(new b.a() { // from class: com.naver.glink.android.sdk.ui.streaming.publish.publisher.c.2
            @Override // com.naver.glink.android.sdk.ui.streaming.publish.publisher.b.a
            public void a(@NonNull MediaCodec.BufferInfo bufferInfo, ByteBuffer byteBuffer, boolean z) {
                c.this.b(bufferInfo, byteBuffer);
            }
        });
        this.f.a();
    }

    public void d() {
        if (this.e != null) {
            this.e.b();
            this.e = null;
        }
        if (this.f != null) {
            this.f.b();
            this.f = null;
        }
    }
}
