package com.google.zxing.common;

import java.util.Arrays;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class BitArray implements Cloneable {
    private int[] bits;
    private int size;

    public BitArray() {
        this.size = 0;
        this.bits = new int[1];
    }

    public BitArray(int i) {
        this.size = i;
        this.bits = makeArray(i);
    }

    BitArray(int[] iArr, int i) {
        this.bits = iArr;
        this.size = i;
    }

    private void ensureCapacity(int i) {
        if (i > (this.bits.length << 5)) {
            int[] iArrMakeArray = makeArray(i);
            System.arraycopy(this.bits, 0, iArrMakeArray, 0, this.bits.length);
            this.bits = iArrMakeArray;
        }
    }

    private static int[] makeArray(int i) {
        return new int[(i + 31) / 32];
    }

    public void appendBit(boolean z) {
        ensureCapacity(this.size + 1);
        if (z) {
            int[] iArr = this.bits;
            int i = this.size / 32;
            iArr[i] = iArr[i] | (1 << (this.size & 31));
        }
        this.size++;
    }

    public void appendBitArray(BitArray bitArray) {
        int i = bitArray.size;
        ensureCapacity(this.size + i);
        for (int i2 = 0; i2 < i; i2++) {
            appendBit(bitArray.get(i2));
        }
    }

    public void appendBits(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        ensureCapacity(this.size + i2);
        for (int i3 = i2; i3 > 0; i3--) {
            appendBit(((i >> (i3 + (-1))) & 1) == 1);
        }
    }

    public void clear() {
        int length = this.bits.length;
        for (int i = 0; i < length; i++) {
            this.bits[i] = 0;
        }
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BitArray m7clone() {
        return new BitArray((int[]) this.bits.clone(), this.size);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitArray)) {
            return false;
        }
        BitArray bitArray = (BitArray) obj;
        return this.size == bitArray.size && Arrays.equals(this.bits, bitArray.bits);
    }

    public void flip(int i) {
        int[] iArr = this.bits;
        int i2 = i / 32;
        iArr[i2] = iArr[i2] ^ (1 << (i & 31));
    }

    public boolean get(int i) {
        return (this.bits[i / 32] & (1 << (i & 31))) != 0;
    }

    public int[] getBitArray() {
        return this.bits;
    }

    public int getNextSet(int i) {
        if (i >= this.size) {
            return this.size;
        }
        int i2 = i / 32;
        int i3 = this.bits[i2] & (((1 << (i & 31)) - 1) ^ (-1));
        while (i3 == 0) {
            i2++;
            if (i2 == this.bits.length) {
                return this.size;
            }
            i3 = this.bits[i2];
        }
        int iNumberOfTrailingZeros = (i2 << 5) + Integer.numberOfTrailingZeros(i3);
        return iNumberOfTrailingZeros > this.size ? this.size : iNumberOfTrailingZeros;
    }

    public int getNextUnset(int i) {
        if (i >= this.size) {
            return this.size;
        }
        int i2 = i / 32;
        int i3 = (this.bits[i2] ^ (-1)) & (((1 << (i & 31)) - 1) ^ (-1));
        while (i3 == 0) {
            i2++;
            if (i2 == this.bits.length) {
                return this.size;
            }
            i3 = this.bits[i2] ^ (-1);
        }
        int iNumberOfTrailingZeros = (i2 << 5) + Integer.numberOfTrailingZeros(i3);
        return iNumberOfTrailingZeros > this.size ? this.size : iNumberOfTrailingZeros;
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) / 8;
    }

    public int hashCode() {
        return (this.size * 31) + Arrays.hashCode(this.bits);
    }

    public boolean isRange(int i, int i2, boolean z) {
        if (i2 < i || i < 0 || i2 > this.size) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return true;
        }
        int i3 = i2 - 1;
        int i4 = i / 32;
        int i5 = i3 / 32;
        int i6 = i4;
        while (i6 <= i5) {
            int i7 = (2 << (i6 < i5 ? 31 : i3 & 31)) - (1 << (i6 > i4 ? 0 : i & 31));
            int i8 = this.bits[i6] & i7;
            if (!z) {
                i7 = 0;
            }
            if (i8 != i7) {
                return false;
            }
            i6++;
        }
        return true;
    }

    public void reverse() {
        int[] iArr = new int[this.bits.length];
        int i = (this.size - 1) / 32;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = this.bits[i3];
            long j2 = ((j >> 1) & 1431655765) | ((1431655765 & j) << 1);
            long j3 = ((j2 >> 2) & 858993459) | ((858993459 & j2) << 2);
            long j4 = ((j3 >> 4) & 252645135) | ((252645135 & j3) << 4);
            long j5 = ((j4 >> 8) & 16711935) | ((16711935 & j4) << 8);
            iArr[i - i3] = (int) (((j5 >> 16) & 65535) | ((65535 & j5) << 16));
        }
        if (this.size != (i2 << 5)) {
            int i4 = (i2 << 5) - this.size;
            int i5 = iArr[0] >>> i4;
            for (int i6 = 1; i6 < i2; i6++) {
                int i7 = iArr[i6];
                iArr[i6 - 1] = i5 | (i7 << (32 - i4));
                i5 = i7 >>> i4;
            }
            iArr[i2 - 1] = i5;
        }
        this.bits = iArr;
    }

    public void set(int i) {
        int[] iArr = this.bits;
        int i2 = i / 32;
        iArr[i2] = iArr[i2] | (1 << (i & 31));
    }

    public void setBulk(int i, int i2) {
        this.bits[i / 32] = i2;
    }

    public void setRange(int i, int i2) {
        if (i2 < i || i < 0 || i2 > this.size) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return;
        }
        int i3 = i2 - 1;
        int i4 = i / 32;
        int i5 = i3 / 32;
        int i6 = i4;
        while (i6 <= i5) {
            int i7 = (2 << (i6 < i5 ? 31 : i3 & 31)) - (1 << (i6 > i4 ? 0 : i & 31));
            int[] iArr = this.bits;
            iArr[i6] = iArr[i6] | i7;
            i6++;
        }
    }

    public void toBytes(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < 8; i6++) {
                if (get(i)) {
                    i5 |= 1 << (7 - i6);
                }
                i++;
            }
            bArr[i2 + i4] = (byte) i5;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.size);
        for (int i = 0; i < this.size; i++) {
            if ((i & 7) == 0) {
                sb.append(' ');
            }
            sb.append(get(i) ? 'X' : '.');
        }
        return sb.toString();
    }

    public void xor(BitArray bitArray) {
        if (this.size != bitArray.size) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        for (int i = 0; i < this.bits.length; i++) {
            int[] iArr = this.bits;
            iArr[i] = iArr[i] ^ bitArray.bits[i];
        }
    }
}
