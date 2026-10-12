package com.bumptech.glide.load.resource.bitmap;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ImageHeaderParser {
    private static final int[] BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    private static final int EXIF_MAGIC_NUMBER = 65496;
    private static final int EXIF_SEGMENT_TYPE = 225;
    private static final int GIF_HEADER = 4671814;
    private static final int INTEL_TIFF_MAGIC_NUMBER = 18761;
    private static final String JPEG_EXIF_SEGMENT_PREAMBLE = "Exif\u0000\u0000";
    private static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
    private static final int MARKER_EOI = 217;
    private static final int MOTOROLA_TIFF_MAGIC_NUMBER = 19789;
    private static final int ORIENTATION_TAG_TYPE = 274;
    private static final int PNG_HEADER = -1991225785;
    private static final int SEGMENT_SOS = 218;
    private static final int SEGMENT_START_ID = 255;
    private static final String TAG = "ImageHeaderParser";
    private final StreamReader streamReader;

    public enum ImageType {
        GIF(true),
        JPEG(false),
        PNG_A(true),
        PNG(false),
        UNKNOWN(false);

        private final boolean hasAlpha;

        ImageType(boolean z) {
            this.hasAlpha = z;
        }

        public boolean hasAlpha() {
            return this.hasAlpha;
        }
    }

    private static class RandomAccessReader {
        private final ByteBuffer data;

        public RandomAccessReader(byte[] bArr) {
            this.data = ByteBuffer.wrap(bArr);
            this.data.order(ByteOrder.BIG_ENDIAN);
        }

        public short getInt16(int i) {
            return this.data.getShort(i);
        }

        public int getInt32(int i) {
            return this.data.getInt(i);
        }

        public int length() {
            return this.data.array().length;
        }

        public void order(ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }
    }

    private static class StreamReader {
        private final InputStream is;

        public StreamReader(InputStream inputStream) {
            this.is = inputStream;
        }

        public int getByte() throws IOException {
            return this.is.read();
        }

        public int getUInt16() throws IOException {
            return ((this.is.read() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.is.read() & 255);
        }

        public short getUInt8() throws IOException {
            return (short) (this.is.read() & 255);
        }

        public int read(byte[] bArr) throws IOException {
            int length = bArr.length;
            while (length > 0) {
                int i = this.is.read(bArr, bArr.length - length, length);
                if (i == -1) {
                    break;
                }
                length -= i;
            }
            return bArr.length - length;
        }

        public long skip(long j) throws IOException {
            if (j < 0) {
                return 0L;
            }
            long j2 = j;
            while (j2 > 0) {
                long jSkip = this.is.skip(j2);
                if (jSkip > 0) {
                    j2 -= jSkip;
                } else {
                    if (this.is.read() == -1) {
                        break;
                    }
                    j2--;
                }
            }
            return j - j2;
        }
    }

    static {
        byte[] bytes = new byte[0];
        try {
            bytes = JPEG_EXIF_SEGMENT_PREAMBLE.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = bytes;
    }

    public ImageHeaderParser(InputStream inputStream) {
        this.streamReader = new StreamReader(inputStream);
    }

    private static int calcTagOffset(int i, int i2) {
        return i + 2 + (i2 * 12);
    }

    private byte[] getExifSegment() throws IOException {
        short uInt8;
        int uInt16;
        long jSkip;
        do {
            short uInt9 = this.streamReader.getUInt8();
            if (uInt9 != 255) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Unknown segmentId=" + ((int) uInt9));
                }
                return null;
            }
            uInt8 = this.streamReader.getUInt8();
            if (uInt8 == SEGMENT_SOS) {
                return null;
            }
            if (uInt8 == MARKER_EOI) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Found MARKER_EOI in exif segment");
                }
                return null;
            }
            uInt16 = this.streamReader.getUInt16() - 2;
            if (uInt8 == EXIF_SEGMENT_TYPE) {
                byte[] bArr = new byte[uInt16];
                int i = this.streamReader.read(bArr);
                if (i == uInt16) {
                    return bArr;
                }
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Unable to read segment data, type: " + ((int) uInt8) + ", length: " + uInt16 + ", actually read: " + i);
                }
                return null;
            }
            jSkip = this.streamReader.skip(uInt16);
        } while (jSkip == uInt16);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Unable to skip enough data, type: " + ((int) uInt8) + ", wanted to skip: " + uInt16 + ", but actually skipped: " + jSkip);
        }
        return null;
    }

    private static boolean handles(int i) {
        return (i & EXIF_MAGIC_NUMBER) == EXIF_MAGIC_NUMBER || i == MOTOROLA_TIFF_MAGIC_NUMBER || i == INTEL_TIFF_MAGIC_NUMBER;
    }

    private static int parseExifSegment(RandomAccessReader randomAccessReader) {
        ByteOrder byteOrder;
        int length = JPEG_EXIF_SEGMENT_PREAMBLE.length();
        short int16 = randomAccessReader.getInt16(length);
        if (int16 == MOTOROLA_TIFF_MAGIC_NUMBER) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (int16 == INTEL_TIFF_MAGIC_NUMBER) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unknown endianness = " + ((int) int16));
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        randomAccessReader.order(byteOrder);
        int int32 = randomAccessReader.getInt32(length + 4) + length;
        short int17 = randomAccessReader.getInt16(int32);
        for (int i = 0; i < int17; i++) {
            int iCalcTagOffset = calcTagOffset(int32, i);
            short int18 = randomAccessReader.getInt16(iCalcTagOffset);
            if (int18 == ORIENTATION_TAG_TYPE) {
                short int19 = randomAccessReader.getInt16(iCalcTagOffset + 2);
                if (int19 >= 1 && int19 <= 12) {
                    int int33 = randomAccessReader.getInt32(iCalcTagOffset + 4);
                    if (int33 >= 0) {
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Got tagIndex=" + i + " tagType=" + ((int) int18) + " formatCode=" + ((int) int19) + " componentCount=" + int33);
                        }
                        int i2 = int33 + BYTES_PER_FORMAT[int19];
                        if (i2 <= 4) {
                            int i3 = iCalcTagOffset + 8;
                            if (i3 >= 0 && i3 <= randomAccessReader.length()) {
                                if (i2 >= 0 && i3 + i2 <= randomAccessReader.length()) {
                                    return randomAccessReader.getInt16(i3);
                                }
                                if (Log.isLoggable(TAG, 3)) {
                                    Log.d(TAG, "Illegal number of bytes for TI tag data tagType=" + ((int) int18));
                                }
                            } else if (Log.isLoggable(TAG, 3)) {
                                Log.d(TAG, "Illegal tagValueOffset=" + i3 + " tagType=" + ((int) int18));
                            }
                        } else if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Got byte count > 4, not orientation, continuing, formatCode=" + ((int) int19));
                        }
                    } else if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Negative tiff component count");
                    }
                } else if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Got invalid format code=" + ((int) int19));
                }
            }
        }
        return -1;
    }

    public int getOrientation() throws IOException {
        if (!handles(this.streamReader.getUInt16())) {
            return -1;
        }
        byte[] exifSegment = getExifSegment();
        boolean z = exifSegment != null && exifSegment.length > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (z) {
            for (int i = 0; i < JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length; i++) {
                if (exifSegment[i] != JPEG_EXIF_SEGMENT_PREAMBLE_BYTES[i]) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            return parseExifSegment(new RandomAccessReader(exifSegment));
        }
        return -1;
    }

    public ImageType getType() throws IOException {
        int uInt16 = this.streamReader.getUInt16();
        if (uInt16 == EXIF_MAGIC_NUMBER) {
            return ImageType.JPEG;
        }
        int uInt17 = ((uInt16 << 16) & SupportMenu.CATEGORY_MASK) | (this.streamReader.getUInt16() & 65535);
        if (uInt17 != PNG_HEADER) {
            return (uInt17 >> 8) == GIF_HEADER ? ImageType.GIF : ImageType.UNKNOWN;
        }
        this.streamReader.skip(21L);
        return this.streamReader.getByte() >= 3 ? ImageType.PNG_A : ImageType.PNG;
    }

    public boolean hasAlpha() throws IOException {
        return getType().hasAlpha();
    }
}
