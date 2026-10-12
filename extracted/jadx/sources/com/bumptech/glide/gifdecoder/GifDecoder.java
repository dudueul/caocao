package com.bumptech.glide.gifdecoder;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GifDecoder {
    private static final int DISPOSAL_BACKGROUND = 2;
    private static final int DISPOSAL_NONE = 1;
    private static final int DISPOSAL_PREVIOUS = 3;
    private static final int DISPOSAL_UNSPECIFIED = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARTIAL_DECODE = 3;
    private int[] act;
    private BitmapProvider bitmapProvider;
    private byte[] data;
    private int framePointer;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;
    private static final String TAG = GifDecoder.class.getSimpleName();
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private final byte[] block = new byte[256];
    private GifHeader header = new GifHeader();

    public interface BitmapProvider {
        Bitmap obtain(int i, int i2, Bitmap.Config config);

        void release(Bitmap bitmap);
    }

    public GifDecoder(BitmapProvider bitmapProvider) {
        this.bitmapProvider = bitmapProvider;
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 5231. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r25) {
        /*
            Method dump skipped, instruction units count: 523
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    private Bitmap getNextBitmap() {
        Bitmap bitmapObtain = this.bitmapProvider.obtain(this.header.width, this.header.height, BITMAP_CONFIG);
        if (bitmapObtain == null) {
            bitmapObtain = Bitmap.createBitmap(this.header.width, this.header.height, BITMAP_CONFIG);
        }
        setAlpha(bitmapObtain);
        return bitmapObtain;
    }

    private int read() {
        try {
            return this.rawData.get() & 255;
        } catch (Exception e) {
            this.status = 1;
            return 0;
        }
    }

    private int readBlock() {
        int i = read();
        int i2 = 0;
        if (i > 0) {
            while (i2 < i) {
                int i3 = i - i2;
                try {
                    this.rawData.get(this.block, i2, i3);
                    i2 += i3;
                } catch (Exception e) {
                    Log.w(TAG, "Error Reading Block", e);
                    this.status = 1;
                }
            }
        }
        return i2;
    }

    @TargetApi(12)
    private static void setAlpha(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        int i = this.header.width;
        int i2 = this.header.height;
        int[] iArr = this.mainScratch;
        if (gifFrame2 != null && gifFrame2.dispose > 0) {
            if (gifFrame2.dispose == 2) {
                Arrays.fill(iArr, gifFrame.transparency ? 0 : this.header.bgColor);
            } else if (gifFrame2.dispose == 3 && this.previousImage != null) {
                this.previousImage.getPixels(iArr, 0, i, 0, 0, i, i2);
            }
        }
        decodeBitmapData(gifFrame);
        int i3 = 1;
        int i4 = 8;
        int i5 = 0;
        for (int i6 = 0; i6 < gifFrame.ih; i6++) {
            int i7 = i6;
            if (gifFrame.interlace) {
                if (i5 >= gifFrame.ih) {
                    i3++;
                    switch (i3) {
                        case 2:
                            i5 = 4;
                            break;
                        case 3:
                            i5 = 2;
                            i4 = 4;
                            break;
                        case 4:
                            i5 = 1;
                            i4 = 2;
                            break;
                    }
                }
                i7 = i5;
                i5 += i4;
            }
            int i8 = i7 + gifFrame.iy;
            if (i8 < this.header.height) {
                int i9 = i8 * this.header.width;
                int i10 = i9 + gifFrame.ix;
                int i11 = i10 + gifFrame.iw;
                if (this.header.width + i9 < i11) {
                    i11 = i9 + this.header.width;
                }
                int i12 = i6 * gifFrame.iw;
                while (i10 < i11) {
                    int i13 = i12 + 1;
                    int i14 = this.act[this.mainPixels[i12] & 255];
                    if (i14 != 0) {
                        iArr[i10] = i14;
                    }
                    i10++;
                    i12 = i13;
                }
            }
        }
        if (this.savePrevious && (gifFrame.dispose == 0 || gifFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            this.previousImage.setPixels(iArr, 0, i, 0, 0, i, i2);
        }
        Bitmap nextBitmap = getNextBitmap();
        nextBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
        return nextBitmap;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public void clear() {
        this.header = null;
        this.data = null;
        this.mainPixels = null;
        this.mainScratch = null;
        if (this.previousImage != null) {
            this.bitmapProvider.release(this.previousImage);
        }
        this.previousImage = null;
        this.rawData = null;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getDelay(int i) {
        if (i < 0 || i >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i).delay;
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getHeight() {
        return this.header.height;
    }

    public int getLoopCount() {
        return this.header.loopCount;
    }

    public int getNextDelay() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            return -1;
        }
        return getDelay(this.framePointer);
    }

    public synchronized Bitmap getNextFrame() {
        Bitmap pixels = null;
        synchronized (this) {
            if (this.header.frameCount <= 0 || this.framePointer < 0) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "unable to decode frame, frameCount=" + this.header.frameCount + " framePointer=" + this.framePointer);
                }
                this.status = 1;
            }
            if (this.status != 1 && this.status != 2) {
                this.status = 0;
                GifFrame gifFrame = this.header.frames.get(this.framePointer);
                int i = this.framePointer - 1;
                GifFrame gifFrame2 = i >= 0 ? this.header.frames.get(i) : null;
                if (gifFrame.lct == null) {
                    this.act = this.header.gct;
                } else {
                    this.act = gifFrame.lct;
                    if (this.header.bgIndex == gifFrame.transIndex) {
                        this.header.bgColor = 0;
                    }
                }
                int i2 = 0;
                if (gifFrame.transparency) {
                    i2 = this.act[gifFrame.transIndex];
                    this.act[gifFrame.transIndex] = 0;
                }
                if (this.act == null) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "No Valid Color Table");
                    }
                    this.status = 1;
                } else {
                    pixels = setPixels(gifFrame, gifFrame2);
                    if (gifFrame.transparency) {
                        this.act[gifFrame.transIndex] = i2;
                    }
                }
            } else if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to decode frame, status=" + this.status);
            }
        }
        return pixels;
    }

    public int getStatus() {
        return this.status;
    }

    public int getWidth() {
        return this.header.width;
    }

    public int read(InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int i2 = inputStream.read(bArr, 0, bArr.length);
                    if (i2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public int read(byte[] bArr) {
        this.data = bArr;
        this.header = getHeaderParser().setData(bArr).parseHeader();
        if (bArr != null) {
            this.rawData = ByteBuffer.wrap(bArr);
            this.rawData.rewind();
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.mainPixels = new byte[this.header.width * this.header.height];
            this.mainScratch = new int[this.header.width * this.header.height];
            this.savePrevious = false;
            Iterator<GifFrame> it = this.header.frames.iterator();
            while (it.hasNext()) {
                if (it.next().dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            }
        }
        return this.status;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    public void setData(GifHeader gifHeader, byte[] bArr) {
        this.header = gifHeader;
        this.data = bArr;
        this.status = 0;
        this.framePointer = -1;
        this.rawData = ByteBuffer.wrap(bArr);
        this.rawData.rewind();
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = gifHeader.frames.iterator();
        while (it.hasNext()) {
            if (it.next().dispose == 3) {
                this.savePrevious = true;
                break;
            }
        }
        this.mainPixels = new byte[gifHeader.width * gifHeader.height];
        this.mainScratch = new int[gifHeader.width * gifHeader.height];
    }
}
