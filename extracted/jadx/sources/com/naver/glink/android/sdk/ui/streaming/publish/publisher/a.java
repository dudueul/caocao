package com.naver.glink.android.sdk.ui.streaming.publish.publisher;

import android.annotation.TargetApi;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.support.annotation.NonNull;
import android.view.Surface;
import com.naver.glink.android.sdk.a.l;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: AudioEncoder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(21)
class a extends b {
    private static final l a = l.a(a.class.getSimpleName());
    private MediaCodec b;
    private AudioRecord c;
    private boolean d;
    private MediaCodec.Callback e = new MediaCodec.Callback() { // from class: com.naver.glink.android.sdk.ui.streaming.publish.publisher.a.1
        private long b;

        private void a(ByteBuffer byteBuffer, int i) {
            byteBuffer.clear();
            for (int i2 = 0; i2 < i; i2++) {
                byteBuffer.put((byte) 0);
            }
        }

        @Override // android.media.MediaCodec.Callback
        public void onError(@NonNull MediaCodec mediaCodec, @NonNull MediaCodec.CodecException codecException) {
        }

        @Override // android.media.MediaCodec.Callback
        public void onInputBufferAvailable(@NonNull MediaCodec mediaCodec, int i) {
            if (a.this.c.getRecordingState() == 1) {
                a.this.c.startRecording();
            }
            ByteBuffer inputBuffer = mediaCodec.getInputBuffer(i);
            inputBuffer.clear();
            long jNanoTime = System.nanoTime();
            int i2 = a.this.c.read(inputBuffer, 1024);
            if (a.this.d) {
                a(inputBuffer, i2);
            }
            mediaCodec.queueInputBuffer(i, 0, i2, jNanoTime / 1000, 0);
        }

        @Override // android.media.MediaCodec.Callback
        public void onOutputBufferAvailable(@NonNull MediaCodec mediaCodec, int i, @NonNull MediaCodec.BufferInfo bufferInfo) {
            if ((bufferInfo.flags & 2) != 0) {
                bufferInfo.size = 0;
            }
            if (bufferInfo.size > 0) {
                ByteBuffer outputBuffer = mediaCodec.getOutputBuffer(i);
                outputBuffer.position(bufferInfo.offset);
                outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                if (bufferInfo.presentationTimeUs < this.b) {
                    long j = this.b + 23219;
                    this.b = j;
                    bufferInfo.presentationTimeUs = j;
                }
                this.b = bufferInfo.presentationTimeUs;
                a.this.a(bufferInfo, outputBuffer, false);
            }
            mediaCodec.releaseOutputBuffer(i, false);
        }

        @Override // android.media.MediaCodec.Callback
        public void onOutputFormatChanged(@NonNull MediaCodec mediaCodec, @NonNull MediaFormat mediaFormat) {
        }
    };

    a() {
    }

    private MediaCodec c() {
        try {
            MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", 44100, 1);
            mediaFormatCreateAudioFormat.setInteger("bitrate", 131072);
            mediaFormatCreateAudioFormat.setInteger("aac-profile", 2);
            mediaFormatCreateAudioFormat.setInteger("max-input-size", 16384);
            MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType("audio/mp4a-latm");
            mediaCodecCreateEncoderByType.configure(mediaFormatCreateAudioFormat, (Surface) null, (MediaCrypto) null, 1);
            mediaCodecCreateEncoderByType.setCallback(this.e);
            return mediaCodecCreateEncoderByType;
        } catch (Exception e) {
            a.a(e);
            return null;
        }
    }

    private AudioRecord d() {
        int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
        int i = minBufferSize * 10;
        if (i < minBufferSize) {
            i = ((minBufferSize / 1024) + 1) * 1024 * 2;
        }
        return new AudioRecord(1, 44100, 16, 2, i);
    }

    void a() {
        this.b = c();
        if (this.b == null) {
            return;
        }
        this.c = d();
        this.b.start();
    }

    void a(boolean z) {
        this.d = z;
    }

    void b() {
        if (this.b != null) {
            this.b.stop();
            this.b.release();
            this.b = null;
        }
        if (this.c != null) {
            this.c.stop();
            this.c.release();
            this.c = null;
        }
    }
}
