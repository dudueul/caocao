package com.naver.glink.android.sdk.ui.streaming.publish.publisher;

import android.annotation.TargetApi;
import android.hardware.display.VirtualDisplay;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.projection.MediaProjection;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Surface;
import com.naver.glink.android.sdk.a.l;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: VideoEncoder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(21)
class d extends b {
    private static final l a = l.a(d.class.getSimpleName());
    private final PublisherConfigure b;
    private final int c;
    private final MediaProjection d;
    private MediaCodec e;
    private VirtualDisplay f;
    private long g;
    private long h;
    private long i;
    private boolean j;
    private MediaCodec.Callback k = new MediaCodec.Callback() { // from class: com.naver.glink.android.sdk.ui.streaming.publish.publisher.d.1
        @Override // android.media.MediaCodec.Callback
        public void onError(@NonNull MediaCodec mediaCodec, @NonNull MediaCodec.CodecException codecException) {
        }

        @Override // android.media.MediaCodec.Callback
        public void onInputBufferAvailable(@NonNull MediaCodec mediaCodec, int i) {
        }

        @Override // android.media.MediaCodec.Callback
        public void onOutputBufferAvailable(@NonNull MediaCodec mediaCodec, int i, @NonNull MediaCodec.BufferInfo bufferInfo) {
            ByteBuffer outputBuffer = mediaCodec.getOutputBuffer(i);
            if ((bufferInfo.flags & 2) != 0) {
                d.this.a(bufferInfo, outputBuffer, false);
                bufferInfo.size = 0;
            }
            if (bufferInfo.size > 0) {
                outputBuffer.position(bufferInfo.offset);
                outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                boolean z = (bufferInfo.flags & 1) != 0;
                d.this.a(bufferInfo, outputBuffer, z);
                d.this.a(z);
            }
            mediaCodec.releaseOutputBuffer(i, false);
        }

        @Override // android.media.MediaCodec.Callback
        public void onOutputFormatChanged(@NonNull MediaCodec mediaCodec, @NonNull MediaFormat mediaFormat) {
        }
    };

    d(PublisherConfigure publisherConfigure, int i, MediaProjection mediaProjection) {
        this.b = publisherConfigure;
        this.c = i;
        this.d = mediaProjection;
    }

    private MediaCodec a(PublisherConfigure publisherConfigure) {
        try {
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", publisherConfigure.j, publisherConfigure.k);
            mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
            mediaFormatCreateVideoFormat.setInteger("bitrate", publisherConfigure.l);
            mediaFormatCreateVideoFormat.setInteger("frame-rate", 30);
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 2);
            MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType("video/avc");
            mediaCodecCreateEncoderByType.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            mediaCodecCreateEncoderByType.setCallback(this.k);
            return mediaCodecCreateEncoderByType;
        } catch (Exception e) {
            a.a(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (this.g == 0) {
            this.g = TimeUnit.SECONDS.toMillis(2L);
            this.h = this.g;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.i != 0) {
            long j = jCurrentTimeMillis - this.i;
            if (z) {
                this.j = false;
                this.h = ((this.g - (j - this.g)) + this.h) / 2;
                this.h = Math.min(Math.max(this.h, 500L), this.g);
                a.a("key frame interval: " + j, new Object[0]);
            } else if (j > this.h) {
                c();
            }
        }
        if (z) {
            this.i = jCurrentTimeMillis;
        }
    }

    private void c() {
        if (this.j || this.e == null) {
            return;
        }
        this.j = true;
        Bundle bundle = new Bundle();
        bundle.putInt("request-sync", 0);
        this.e.setParameters(bundle);
        a.a("request sync frame", new Object[0]);
    }

    void a() {
        this.e = a(this.b);
        if (this.e == null) {
            return;
        }
        this.f = this.d.createVirtualDisplay("plug-live-stream", this.b.j, this.b.k, this.c, 2, this.e.createInputSurface(), null, null);
        this.e.start();
    }

    void b() {
        if (this.e != null) {
            this.e.stop();
            this.e.release();
            this.e = null;
        }
        if (this.f != null) {
            this.f.release();
            this.f = null;
        }
    }
}
