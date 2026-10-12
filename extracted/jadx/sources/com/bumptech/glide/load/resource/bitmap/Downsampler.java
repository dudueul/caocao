package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.ByteArrayPool;
import com.bumptech.glide.util.ExceptionCatchingInputStream;
import com.bumptech.glide.util.MarkEnforcingInputStream;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class Downsampler implements BitmapDecoder<InputStream> {
    private static final int MARK_POSITION = 5242880;
    private static final String TAG = "Downsampler";
    private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL = EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG);
    private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);
    public static final Downsampler AT_LEAST = new Downsampler() { // from class: com.bumptech.glide.load.resource.bitmap.Downsampler.1
        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler, com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public /* bridge */ /* synthetic */ Bitmap decode(InputStream inputStream, BitmapPool bitmapPool, int i, int i2, DecodeFormat decodeFormat) throws Exception {
            return super.decode(inputStream, bitmapPool, i, i2, decodeFormat);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public String getId() {
            return "AT_LEAST.com.bumptech.glide.load.data.bitmap";
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler
        protected int getSampleSize(int i, int i2, int i3, int i4) {
            return Math.min(i2 / i4, i / i3);
        }
    };
    public static final Downsampler AT_MOST = new Downsampler() { // from class: com.bumptech.glide.load.resource.bitmap.Downsampler.2
        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler, com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public /* bridge */ /* synthetic */ Bitmap decode(InputStream inputStream, BitmapPool bitmapPool, int i, int i2, DecodeFormat decodeFormat) throws Exception {
            return super.decode(inputStream, bitmapPool, i, i2, decodeFormat);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public String getId() {
            return "AT_MOST.com.bumptech.glide.load.data.bitmap";
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler
        protected int getSampleSize(int i, int i2, int i3, int i4) {
            int iCeil = (int) Math.ceil(Math.max(i2 / i4, i / i3));
            int iMax = Math.max(1, Integer.highestOneBit(iCeil));
            return iMax << (iMax >= iCeil ? 0 : 1);
        }
    };
    public static final Downsampler NONE = new Downsampler() { // from class: com.bumptech.glide.load.resource.bitmap.Downsampler.3
        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler, com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public /* bridge */ /* synthetic */ Bitmap decode(InputStream inputStream, BitmapPool bitmapPool, int i, int i2, DecodeFormat decodeFormat) throws Exception {
            return super.decode(inputStream, bitmapPool, i, i2, decodeFormat);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.BitmapDecoder
        public String getId() {
            return "NONE.com.bumptech.glide.load.data.bitmap";
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler
        protected int getSampleSize(int i, int i2, int i3, int i4) {
            return 0;
        }
    };

    private static Bitmap decodeStream(MarkEnforcingInputStream markEnforcingInputStream, RecyclableBufferedInputStream recyclableBufferedInputStream, BitmapFactory.Options options) {
        if (options.inJustDecodeBounds) {
            markEnforcingInputStream.mark(5242880);
        } else {
            recyclableBufferedInputStream.fixMarkLimit();
        }
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(markEnforcingInputStream, null, options);
        try {
            if (options.inJustDecodeBounds) {
                markEnforcingInputStream.reset();
            }
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 6)) {
                Log.e(TAG, "Exception loading inDecodeBounds=" + options.inJustDecodeBounds + " sample=" + options.inSampleSize, e);
            }
        }
        return bitmapDecodeStream;
    }

    private Bitmap downsampleWithSize(MarkEnforcingInputStream markEnforcingInputStream, RecyclableBufferedInputStream recyclableBufferedInputStream, BitmapFactory.Options options, BitmapPool bitmapPool, int i, int i2, int i3, DecodeFormat decodeFormat) {
        Bitmap.Config config = getConfig(markEnforcingInputStream, decodeFormat);
        options.inSampleSize = i3;
        options.inPreferredConfig = config;
        if ((options.inSampleSize == 1 || 19 <= Build.VERSION.SDK_INT) && shouldUsePool(markEnforcingInputStream)) {
            setInBitmap(options, bitmapPool.getDirty((int) Math.ceil(((double) i) / ((double) i3)), (int) Math.ceil(((double) i2) / ((double) i3)), config));
        }
        return decodeStream(markEnforcingInputStream, recyclableBufferedInputStream, options);
    }

    private static Bitmap.Config getConfig(InputStream inputStream, DecodeFormat decodeFormat) {
        if (decodeFormat == DecodeFormat.ALWAYS_ARGB_8888 || decodeFormat == DecodeFormat.PREFER_ARGB_8888 || Build.VERSION.SDK_INT == 16) {
            return Bitmap.Config.ARGB_8888;
        }
        boolean zHasAlpha = false;
        inputStream.mark(1024);
        try {
            try {
                zHasAlpha = new ImageHeaderParser(inputStream).hasAlpha();
                try {
                    inputStream.reset();
                } catch (IOException e) {
                    if (Log.isLoggable(TAG, 5)) {
                        Log.w(TAG, "Cannot reset the input stream", e);
                    }
                }
            } catch (IOException e2) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Cannot determine whether the image has alpha or not from header for format " + decodeFormat, e2);
                }
                try {
                    inputStream.reset();
                } catch (IOException e3) {
                    if (Log.isLoggable(TAG, 5)) {
                        Log.w(TAG, "Cannot reset the input stream", e3);
                    }
                }
            }
            return zHasAlpha ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        } catch (Throwable th) {
            try {
                inputStream.reset();
            } catch (IOException e4) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Cannot reset the input stream", e4);
                }
            }
            throw th;
        }
    }

    @TargetApi(11)
    private static synchronized BitmapFactory.Options getDefaultOptions() {
        BitmapFactory.Options optionsPoll;
        synchronized (OPTIONS_QUEUE) {
            optionsPoll = OPTIONS_QUEUE.poll();
        }
        if (optionsPoll == null) {
            optionsPoll = new BitmapFactory.Options();
            resetOptions(optionsPoll);
        }
        return optionsPoll;
    }

    private int getRoundedSampleSize(int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 == Integer.MIN_VALUE ? i3 : i5;
        int i7 = i4 == Integer.MIN_VALUE ? i2 : i4;
        int sampleSize = (i == 90 || i == 270) ? getSampleSize(i3, i2, i7, i6) : getSampleSize(i2, i3, i7, i6);
        return Math.max(1, sampleSize == 0 ? 0 : Integer.highestOneBit(sampleSize));
    }

    private static void releaseOptions(BitmapFactory.Options options) {
        resetOptions(options);
        synchronized (OPTIONS_QUEUE) {
            OPTIONS_QUEUE.offer(options);
        }
    }

    @TargetApi(11)
    private static void resetOptions(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        if (11 <= Build.VERSION.SDK_INT) {
            options.inBitmap = null;
            options.inMutable = true;
        }
    }

    @TargetApi(11)
    private static void setInBitmap(BitmapFactory.Options options, Bitmap bitmap) {
        if (11 <= Build.VERSION.SDK_INT) {
            options.inBitmap = bitmap;
        }
    }

    private static boolean shouldUsePool(InputStream inputStream) {
        if (19 <= Build.VERSION.SDK_INT) {
            return true;
        }
        inputStream.mark(1024);
        try {
            boolean zContains = TYPES_THAT_USE_POOL.contains(new ImageHeaderParser(inputStream).getType());
            try {
                return zContains;
            } catch (IOException e) {
            }
        } catch (IOException e2) {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Cannot determine the image type from header", e2);
            }
            return false;
        } finally {
            try {
                inputStream.reset();
            } catch (IOException e3) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Cannot reset the input stream", e3);
                }
            }
        }
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapDecoder
    public Bitmap decode(InputStream inputStream, BitmapPool bitmapPool, int i, int i2, DecodeFormat decodeFormat) {
        ByteArrayPool byteArrayPool = ByteArrayPool.get();
        byte[] bytes = byteArrayPool.getBytes();
        byte[] bytes2 = byteArrayPool.getBytes();
        BitmapFactory.Options defaultOptions = getDefaultOptions();
        RecyclableBufferedInputStream recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, bytes2);
        ExceptionCatchingInputStream exceptionCatchingInputStreamObtain = ExceptionCatchingInputStream.obtain(recyclableBufferedInputStream);
        MarkEnforcingInputStream markEnforcingInputStream = new MarkEnforcingInputStream(exceptionCatchingInputStreamObtain);
        try {
            exceptionCatchingInputStreamObtain.mark(5242880);
            int orientation = 0;
            try {
                try {
                    orientation = new ImageHeaderParser(exceptionCatchingInputStreamObtain).getOrientation();
                    try {
                        exceptionCatchingInputStreamObtain.reset();
                    } catch (IOException e) {
                        if (Log.isLoggable(TAG, 5)) {
                            Log.w(TAG, "Cannot reset the input stream", e);
                        }
                    }
                } catch (IOException e2) {
                    if (Log.isLoggable(TAG, 5)) {
                        Log.w(TAG, "Cannot determine the image orientation from header", e2);
                    }
                    try {
                        exceptionCatchingInputStreamObtain.reset();
                    } catch (IOException e3) {
                        if (Log.isLoggable(TAG, 5)) {
                            Log.w(TAG, "Cannot reset the input stream", e3);
                        }
                    }
                }
                defaultOptions.inTempStorage = bytes;
                int[] dimensions = getDimensions(markEnforcingInputStream, recyclableBufferedInputStream, defaultOptions);
                int i3 = dimensions[0];
                int i4 = dimensions[1];
                Bitmap bitmapDownsampleWithSize = downsampleWithSize(markEnforcingInputStream, recyclableBufferedInputStream, defaultOptions, bitmapPool, i3, i4, getRoundedSampleSize(TransformationUtils.getExifOrientationDegrees(orientation), i3, i4, i, i2), decodeFormat);
                IOException exception = exceptionCatchingInputStreamObtain.getException();
                if (exception != null) {
                    throw new RuntimeException(exception);
                }
                Bitmap bitmapRotateImageExif = null;
                if (bitmapDownsampleWithSize != null) {
                    bitmapRotateImageExif = TransformationUtils.rotateImageExif(bitmapDownsampleWithSize, bitmapPool, orientation);
                    if (!bitmapDownsampleWithSize.equals(bitmapRotateImageExif) && !bitmapPool.put(bitmapDownsampleWithSize)) {
                        bitmapDownsampleWithSize.recycle();
                    }
                }
                byteArrayPool.releaseBytes(bytes);
                byteArrayPool.releaseBytes(bytes2);
                exceptionCatchingInputStreamObtain.release();
                releaseOptions(defaultOptions);
                return bitmapRotateImageExif;
            } catch (Throwable th) {
                try {
                    exceptionCatchingInputStreamObtain.reset();
                } catch (IOException e4) {
                    if (Log.isLoggable(TAG, 5)) {
                        Log.w(TAG, "Cannot reset the input stream", e4);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            byteArrayPool.releaseBytes(bytes);
            byteArrayPool.releaseBytes(bytes2);
            exceptionCatchingInputStreamObtain.release();
            releaseOptions(defaultOptions);
            throw th2;
        }
    }

    public int[] getDimensions(MarkEnforcingInputStream markEnforcingInputStream, RecyclableBufferedInputStream recyclableBufferedInputStream, BitmapFactory.Options options) {
        options.inJustDecodeBounds = true;
        decodeStream(markEnforcingInputStream, recyclableBufferedInputStream, options);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    protected abstract int getSampleSize(int i, int i2, int i3, int i4);
}
