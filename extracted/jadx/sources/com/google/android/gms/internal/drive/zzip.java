package com.google.android.gms.internal.drive;

import com.naver.glink.android.sdk.ui.record.c;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzip {
    private final ByteBuffer zzmv;

    private zzip(ByteBuffer byteBuffer) {
        this.zzmv = byteBuffer;
        this.zzmv.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzip(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence) {
        int i;
        int i2 = 0;
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length && charSequence.charAt(i3) < 128) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 < length) {
                char cCharAt = charSequence.charAt(i3);
                if (cCharAt >= 2048) {
                    int length2 = charSequence.length();
                    while (i3 < length2) {
                        char cCharAt2 = charSequence.charAt(i3);
                        if (cCharAt2 < 2048) {
                            i2 += (127 - cCharAt2) >>> 31;
                        } else {
                            i2 += 2;
                            if (55296 <= cCharAt2 && cCharAt2 <= 57343) {
                                if (Character.codePointAt(charSequence, i3) < 65536) {
                                    throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3).toString());
                                }
                                i3++;
                            }
                        }
                        i3++;
                    }
                    i = i4 + i2;
                    break;
                }
                i4 += (127 - cCharAt) >>> 31;
                i3++;
            } else {
                i = i4;
                break;
            }
        }
        if (i >= length) {
            return i;
        }
        throw new IllegalArgumentException(new StringBuilder(54).append("UTF-8 length does not fit in int: ").append(((long) i) + 4294967296L).toString());
    }

    private final void zza(long j) throws IOException {
        while (((-128) & j) != 0) {
            zzn((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzn((int) j);
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        int i3 = 0;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        if (!byteBuffer.hasArray()) {
            int length = charSequence.length();
            while (i3 < length) {
                char cCharAt = charSequence.charAt(i3);
                if (cCharAt < 128) {
                    byteBuffer.put((byte) cCharAt);
                } else if (cCharAt < 2048) {
                    byteBuffer.put((byte) ((cCharAt >>> 6) | 960));
                    byteBuffer.put((byte) ((cCharAt & '?') | 128));
                } else {
                    if (cCharAt >= 55296 && 57343 >= cCharAt) {
                        if (i3 + 1 != charSequence.length()) {
                            i3++;
                            char cCharAt2 = charSequence.charAt(i3);
                            if (Character.isSurrogatePair(cCharAt, cCharAt2)) {
                                int codePoint = Character.toCodePoint(cCharAt, cCharAt2);
                                byteBuffer.put((byte) ((codePoint >>> 18) | 240));
                                byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                                byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put((byte) ((codePoint & 63) | 128));
                            }
                        }
                        throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3 - 1).toString());
                    }
                    byteBuffer.put((byte) ((cCharAt >>> '\f') | c.b));
                    byteBuffer.put((byte) (((cCharAt >>> 6) & 63) | 128));
                    byteBuffer.put((byte) ((cCharAt & '?') | 128));
                }
                i3++;
            }
            return;
        }
        try {
            byte[] bArrArray = byteBuffer.array();
            int iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
            int iRemaining = byteBuffer.remaining();
            int length2 = charSequence.length();
            int i4 = iArrayOffset + iRemaining;
            while (i3 < length2 && i3 + iArrayOffset < i4) {
                char cCharAt3 = charSequence.charAt(i3);
                if (cCharAt3 >= 128) {
                    break;
                }
                bArrArray[iArrayOffset + i3] = (byte) cCharAt3;
                i3++;
            }
            if (i3 == length2) {
                i = iArrayOffset + length2;
            } else {
                int i5 = iArrayOffset + i3;
                while (i3 < length2) {
                    char cCharAt4 = charSequence.charAt(i3);
                    if (cCharAt4 < 128 && i5 < i4) {
                        i2 = i5 + 1;
                        bArrArray[i5] = (byte) cCharAt4;
                    } else if (cCharAt4 < 2048 && i5 <= i4 - 2) {
                        int i6 = i5 + 1;
                        bArrArray[i5] = (byte) ((cCharAt4 >>> 6) | 960);
                        i2 = i6 + 1;
                        bArrArray[i6] = (byte) ((cCharAt4 & '?') | 128);
                    } else {
                        if ((cCharAt4 >= 55296 && 57343 >= cCharAt4) || i5 > i4 - 3) {
                            if (i5 > i4 - 4) {
                                throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(cCharAt4).append(" at index ").append(i5).toString());
                            }
                            if (i3 + 1 != charSequence.length()) {
                                i3++;
                                char cCharAt5 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(cCharAt4, cCharAt5)) {
                                    int codePoint2 = Character.toCodePoint(cCharAt4, cCharAt5);
                                    int i7 = i5 + 1;
                                    bArrArray[i5] = (byte) ((codePoint2 >>> 18) | 240);
                                    int i8 = i7 + 1;
                                    bArrArray[i7] = (byte) (((codePoint2 >>> 12) & 63) | 128);
                                    int i9 = i8 + 1;
                                    bArrArray[i8] = (byte) (((codePoint2 >>> 6) & 63) | 128);
                                    i2 = i9 + 1;
                                    bArrArray[i9] = (byte) ((codePoint2 & 63) | 128);
                                }
                            }
                            throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3 - 1).toString());
                        }
                        int i10 = i5 + 1;
                        bArrArray[i5] = (byte) ((cCharAt4 >>> '\f') | c.b);
                        int i11 = i10 + 1;
                        bArrArray[i10] = (byte) (((cCharAt4 >>> 6) & 63) | 128);
                        i2 = i11 + 1;
                        bArrArray[i11] = (byte) ((cCharAt4 & '?') | 128);
                    }
                    i3++;
                    i5 = i2;
                }
                i = i5;
            }
            byteBuffer.position(i - byteBuffer.arrayOffset());
        } catch (ArrayIndexOutOfBoundsException e) {
            BufferOverflowException bufferOverflowException = new BufferOverflowException();
            bufferOverflowException.initCause(e);
            throw bufferOverflowException;
        }
    }

    public static int zzb(int i, long j) {
        int i2;
        int iZzo = zzo(i);
        long jZzb = zzb(j);
        if (((-128) & jZzb) == 0) {
            i2 = 1;
        } else if (((-16384) & jZzb) == 0) {
            i2 = 2;
        } else if (((-2097152) & jZzb) == 0) {
            i2 = 3;
        } else if (((-268435456) & jZzb) == 0) {
            i2 = 4;
        } else if (((-34359738368L) & jZzb) == 0) {
            i2 = 5;
        } else if (((-4398046511104L) & jZzb) == 0) {
            i2 = 6;
        } else if (((-562949953421312L) & jZzb) == 0) {
            i2 = 7;
        } else if (((-72057594037927936L) & jZzb) == 0) {
            i2 = 8;
        } else {
            i2 = (jZzb & Long.MIN_VALUE) == 0 ? 9 : 10;
        }
        return i2 + iZzo;
    }

    private static long zzb(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static zzip zzb(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static zzip zzb(byte[] bArr, int i, int i2) {
        return new zzip(bArr, 0, i2);
    }

    public static int zzc(int i, int i2) {
        return zzo(i) + zzm(i2);
    }

    public static int zzi(String str) {
        int iZza = zza(str);
        return iZza + zzq(iZza);
    }

    public static int zzm(int i) {
        if (i >= 0) {
            return zzq(i);
        }
        return 10;
    }

    private final void zzn(int i) throws IOException {
        byte b = (byte) i;
        if (!this.zzmv.hasRemaining()) {
            throw new zziq(this.zzmv.position(), this.zzmv.limit());
        }
        this.zzmv.put(b);
    }

    public static int zzo(int i) {
        return zzq(i << 3);
    }

    public static int zzq(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return ((-268435456) & i) == 0 ? 4 : 5;
    }

    public final void zza(int i, long j) throws IOException {
        zzd(i, 0);
        zza(zzb(j));
    }

    public final void zzb(int i, int i2) throws IOException {
        zzd(i, 0);
        if (i2 >= 0) {
            zzp(i2);
        } else {
            zza(i2);
        }
    }

    public final void zzbh() {
        if (this.zzmv.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", Integer.valueOf(this.zzmv.remaining())));
        }
    }

    public final void zzc(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzmv.remaining() < length) {
            throw new zziq(this.zzmv.position(), this.zzmv.limit());
        }
        this.zzmv.put(bArr, 0, length);
    }

    public final void zzd(int i, int i2) throws IOException {
        zzp((i << 3) | i2);
    }

    public final void zzh(String str) throws IOException {
        try {
            int iZzq = zzq(str.length());
            if (iZzq != zzq(str.length() * 3)) {
                zzp(zza(str));
                zza(str, this.zzmv);
                return;
            }
            int iPosition = this.zzmv.position();
            if (this.zzmv.remaining() < iZzq) {
                throw new zziq(iZzq + iPosition, this.zzmv.limit());
            }
            this.zzmv.position(iPosition + iZzq);
            zza(str, this.zzmv);
            int iPosition2 = this.zzmv.position();
            this.zzmv.position(iPosition);
            zzp((iPosition2 - iPosition) - iZzq);
            this.zzmv.position(iPosition2);
        } catch (BufferOverflowException e) {
            zziq zziqVar = new zziq(this.zzmv.position(), this.zzmv.limit());
            zziqVar.initCause(e);
            throw zziqVar;
        }
    }

    public final void zzp(int i) throws IOException {
        while ((i & (-128)) != 0) {
            zzn((i & 127) | 128);
            i >>>= 7;
        }
        zzn(i);
    }
}
