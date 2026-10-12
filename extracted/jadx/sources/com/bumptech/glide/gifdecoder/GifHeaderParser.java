package com.bumptech.glide.gifdecoder;

import android.util.Log;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GifHeaderParser {
    static final int DEFAULT_FRAME_DELAY = 10;
    private static final int MAX_BLOCK_SIZE = 256;
    static final int MIN_FRAME_DELAY = 3;
    public static final String TAG = "GifHeaderParser";
    private final byte[] block = new byte[256];
    private int blockSize = 0;
    private GifHeader header;
    private ByteBuffer rawData;

    private boolean err() {
        return this.header.status != 0;
    }

    private int read() {
        try {
            return this.rawData.get() & 255;
        } catch (Exception e) {
            this.header.status = 1;
            return 0;
        }
    }

    private void readBitmap() {
        this.header.currentFrame.ix = readShort();
        this.header.currentFrame.iy = readShort();
        this.header.currentFrame.iw = readShort();
        this.header.currentFrame.ih = readShort();
        int i = read();
        boolean z = (i & 128) != 0;
        int iPow = (int) Math.pow(2.0d, (i & 7) + 1);
        this.header.currentFrame.interlace = (i & 64) != 0;
        if (z) {
            this.header.currentFrame.lct = readColorTable(iPow);
        } else {
            this.header.currentFrame.lct = null;
        }
        this.header.currentFrame.bufferFrameStart = this.rawData.position();
        skipImageData();
        if (err()) {
            return;
        }
        this.header.frameCount++;
        this.header.frames.add(this.header.currentFrame);
    }

    private int readBlock() {
        this.blockSize = read();
        int i = 0;
        if (this.blockSize > 0) {
            int i2 = 0;
            while (i < this.blockSize) {
                try {
                    i2 = this.blockSize - i;
                    this.rawData.get(this.block, i, i2);
                    i += i2;
                } catch (Exception e) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Error Reading Block n: " + i + " count: " + i2 + " blockSize: " + this.blockSize, e);
                    }
                    this.header.status = 1;
                }
            }
        }
        return i;
    }

    private int[] readColorTable(int i) {
        int[] iArr = null;
        byte[] bArr = new byte[i * 3];
        try {
            this.rawData.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i3 < i) {
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                int i6 = i4 + 1;
                int i7 = bArr[i4] & 255;
                int i8 = i6 + 1;
                int i9 = i3 + 1;
                iArr[i3] = (-16777216) | (i5 << 16) | (i7 << 8) | (bArr[i6] & 255);
                i2 = i8;
                i3 = i9;
            }
        } catch (BufferUnderflowException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Format Error Reading Color Table", e);
            }
            this.header.status = 1;
        }
        return iArr;
    }

    private void readContents() {
        boolean z = false;
        while (!z && !err()) {
            switch (read()) {
                case 33:
                    switch (read()) {
                        case 1:
                            skip();
                            break;
                        case 249:
                            this.header.currentFrame = new GifFrame();
                            readGraphicControlExt();
                            break;
                        case 254:
                            skip();
                            break;
                        case 255:
                            readBlock();
                            String str = "";
                            for (int i = 0; i < 11; i++) {
                                str = str + ((char) this.block[i]);
                            }
                            if (str.equals("NETSCAPE2.0")) {
                                readNetscapeExt();
                            } else {
                                skip();
                            }
                            break;
                        default:
                            skip();
                            break;
                    }
                    break;
                case 44:
                    if (this.header.currentFrame == null) {
                        this.header.currentFrame = new GifFrame();
                    }
                    readBitmap();
                    break;
                case 59:
                    z = true;
                    break;
                default:
                    this.header.status = 1;
                    break;
            }
        }
    }

    private void readGraphicControlExt() {
        read();
        int i = read();
        this.header.currentFrame.dispose = (i & 28) >> 2;
        if (this.header.currentFrame.dispose == 0) {
            this.header.currentFrame.dispose = 1;
        }
        this.header.currentFrame.transparency = (i & 1) != 0;
        int i2 = readShort();
        if (i2 < 3) {
            i2 = 10;
        }
        this.header.currentFrame.delay = i2 * 10;
        this.header.currentFrame.transIndex = read();
        read();
    }

    private void readHeader() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) read());
        }
        if (!str.startsWith("GIF")) {
            this.header.status = 1;
            return;
        }
        readLSD();
        if (!this.header.gctFlag || err()) {
            return;
        }
        this.header.gct = readColorTable(this.header.gctSize);
        this.header.bgColor = this.header.gct[this.header.bgIndex];
    }

    private void readLSD() {
        this.header.width = readShort();
        this.header.height = readShort();
        int i = read();
        this.header.gctFlag = (i & 128) != 0;
        this.header.gctSize = 2 << (i & 7);
        this.header.bgIndex = read();
        this.header.pixelAspect = read();
    }

    private void readNetscapeExt() {
        do {
            readBlock();
            if (this.block[0] == 1) {
                this.header.loopCount = ((this.block[2] & 255) << 8) | (this.block[1] & 255);
            }
            if (this.blockSize <= 0) {
                return;
            }
        } while (!err());
    }

    private int readShort() {
        return this.rawData.getShort();
    }

    private void reset() {
        this.rawData = null;
        Arrays.fill(this.block, (byte) 0);
        this.header = new GifHeader();
        this.blockSize = 0;
    }

    private void skip() {
        int i;
        do {
            i = read();
            this.rawData.position(this.rawData.position() + i);
        } while (i > 0);
    }

    private void skipImageData() {
        read();
        skip();
    }

    public void clear() {
        this.rawData = null;
        this.header = null;
    }

    public GifHeader parseHeader() {
        if (this.rawData == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (err()) {
            return this.header;
        }
        readHeader();
        if (!err()) {
            readContents();
            if (this.header.frameCount < 0) {
                this.header.status = 1;
            }
        }
        return this.header;
    }

    public GifHeaderParser setData(byte[] bArr) {
        reset();
        if (bArr != null) {
            this.rawData = ByteBuffer.wrap(bArr);
            this.rawData.rewind();
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.rawData = null;
            this.header.status = 2;
        }
        return this;
    }
}
