package com.google.zxing.common;

import java.util.Arrays;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class BitMatrix implements Cloneable {
    private final int[] bits;
    private final int height;
    private final int rowSize;
    private final int width;

    public BitMatrix(int i) {
        this(i, i);
    }

    public BitMatrix(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.width = i;
        this.height = i2;
        this.rowSize = (i + 31) / 32;
        this.bits = new int[this.rowSize * i2];
    }

    private BitMatrix(int i, int i2, int i3, int[] iArr) {
        this.width = i;
        this.height = i2;
        this.rowSize = i3;
        this.bits = iArr;
    }

    private String buildToString(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.height * (this.width + 1));
        for (int i = 0; i < this.height; i++) {
            for (int i2 = 0; i2 < this.width; i2++) {
                sb.append(get(i2, i) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    public static BitMatrix parse(String str, String str2, String str3) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        boolean[] zArr = new boolean[str.length()];
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        int length = 0;
        while (length < str.length()) {
            if (str.charAt(length) == '\n' || str.charAt(length) == '\r') {
                if (i > i2) {
                    if (i3 == -1) {
                        i3 = i - i2;
                    } else if (i - i2 != i3) {
                        throw new IllegalArgumentException("row lengths do not match");
                    }
                    i2 = i;
                    i4++;
                }
                length++;
            } else if (str.substring(length, str2.length() + length).equals(str2)) {
                length += str2.length();
                zArr[i] = true;
                i++;
            } else {
                if (!str.substring(length, str3.length() + length).equals(str3)) {
                    throw new IllegalArgumentException("illegal character encountered: " + str.substring(length));
                }
                length += str3.length();
                zArr[i] = false;
                i++;
            }
        }
        if (i > i2) {
            if (i3 == -1) {
                i3 = i - i2;
            } else if (i - i2 != i3) {
                throw new IllegalArgumentException("row lengths do not match");
            }
            i4++;
        }
        BitMatrix bitMatrix = new BitMatrix(i3, i4);
        for (int i5 = 0; i5 < i; i5++) {
            if (zArr[i5]) {
                bitMatrix.set(i5 % i3, i5 / i3);
            }
        }
        return bitMatrix;
    }

    public void clear() {
        int length = this.bits.length;
        for (int i = 0; i < length; i++) {
            this.bits[i] = 0;
        }
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BitMatrix m8clone() {
        return new BitMatrix(this.width, this.height, this.rowSize, (int[]) this.bits.clone());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitMatrix)) {
            return false;
        }
        BitMatrix bitMatrix = (BitMatrix) obj;
        return this.width == bitMatrix.width && this.height == bitMatrix.height && this.rowSize == bitMatrix.rowSize && Arrays.equals(this.bits, bitMatrix.bits);
    }

    public void flip(int i, int i2) {
        int i3 = (this.rowSize * i2) + (i / 32);
        int[] iArr = this.bits;
        iArr[i3] = iArr[i3] ^ (1 << (i & 31));
    }

    public boolean get(int i, int i2) {
        return ((this.bits[(this.rowSize * i2) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    public int[] getBottomRightOnBit() {
        int length = this.bits.length - 1;
        while (length >= 0 && this.bits[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i = length / this.rowSize;
        int i2 = (length % this.rowSize) << 5;
        int i3 = 31;
        while ((this.bits[length] >>> i3) == 0) {
            i3--;
        }
        return new int[]{i2 + i3, i};
    }

    public int[] getEnclosingRectangle() {
        int i = this.width;
        int i2 = this.height;
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < this.height; i5++) {
            for (int i6 = 0; i6 < this.rowSize; i6++) {
                int i7 = this.bits[(this.rowSize * i5) + i6];
                if (i7 != 0) {
                    if (i5 < i2) {
                        i2 = i5;
                    }
                    if (i5 > i4) {
                        i4 = i5;
                    }
                    if ((i6 << 5) < i) {
                        int i8 = 0;
                        while ((i7 << (31 - i8)) == 0) {
                            i8++;
                        }
                        if ((i6 << 5) + i8 < i) {
                            i = (i6 << 5) + i8;
                        }
                    }
                    if ((i6 << 5) + 31 > i3) {
                        int i9 = 31;
                        while ((i7 >>> i9) == 0) {
                            i9--;
                        }
                        if ((i6 << 5) + i9 > i3) {
                            i3 = (i6 << 5) + i9;
                        }
                    }
                }
            }
        }
        if (i3 < i || i4 < i2) {
            return null;
        }
        return new int[]{i, i2, (i3 - i) + 1, (i4 - i2) + 1};
    }

    public int getHeight() {
        return this.height;
    }

    public BitArray getRow(int i, BitArray bitArray) {
        if (bitArray == null || bitArray.getSize() < this.width) {
            bitArray = new BitArray(this.width);
        } else {
            bitArray.clear();
        }
        int i2 = i * this.rowSize;
        for (int i3 = 0; i3 < this.rowSize; i3++) {
            bitArray.setBulk(i3 << 5, this.bits[i2 + i3]);
        }
        return bitArray;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public int[] getTopLeftOnBit() {
        int i = 0;
        while (i < this.bits.length && this.bits[i] == 0) {
            i++;
        }
        if (i == this.bits.length) {
            return null;
        }
        int i2 = i / this.rowSize;
        int i3 = (i % this.rowSize) << 5;
        int i4 = 0;
        while ((this.bits[i] << (31 - i4)) == 0) {
            i4++;
        }
        return new int[]{i3 + i4, i2};
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (((((((this.width * 31) + this.width) * 31) + this.height) * 31) + this.rowSize) * 31) + Arrays.hashCode(this.bits);
    }

    public void rotate180() {
        int width = getWidth();
        int height = getHeight();
        BitArray bitArray = new BitArray(width);
        BitArray bitArray2 = new BitArray(width);
        for (int i = 0; i < (height + 1) / 2; i++) {
            bitArray = getRow(i, bitArray);
            bitArray2 = getRow((height - 1) - i, bitArray2);
            bitArray.reverse();
            bitArray2.reverse();
            setRow(i, bitArray2);
            setRow((height - 1) - i, bitArray);
        }
    }

    public void set(int i, int i2) {
        int i3 = (this.rowSize * i2) + (i / 32);
        int[] iArr = this.bits;
        iArr[i3] = iArr[i3] | (1 << (i & 31));
    }

    public void setRegion(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i4 <= 0 || i3 <= 0) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int i5 = i + i3;
        int i6 = i2 + i4;
        if (i6 > this.height || i5 > this.width) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        for (int i7 = i2; i7 < i6; i7++) {
            int i8 = i7 * this.rowSize;
            for (int i9 = i; i9 < i5; i9++) {
                int[] iArr = this.bits;
                int i10 = (i9 / 32) + i8;
                iArr[i10] = iArr[i10] | (1 << (i9 & 31));
            }
        }
    }

    public void setRow(int i, BitArray bitArray) {
        System.arraycopy(bitArray.getBitArray(), 0, this.bits, this.rowSize * i, this.rowSize);
    }

    public String toString() {
        return toString("X ", "  ");
    }

    public String toString(String str, String str2) {
        return buildToString(str, str2, "\n");
    }

    @Deprecated
    public String toString(String str, String str2, String str3) {
        return buildToString(str, str2, str3);
    }

    public void unset(int i, int i2) {
        int i3 = (this.rowSize * i2) + (i / 32);
        int[] iArr = this.bits;
        iArr[i3] = iArr[i3] & ((1 << (i & 31)) ^ (-1));
    }

    public void xor(BitMatrix bitMatrix) {
        if (this.width != bitMatrix.getWidth() || this.height != bitMatrix.getHeight() || this.rowSize != bitMatrix.getRowSize()) {
            throw new IllegalArgumentException("input matrix dimensions do not match");
        }
        BitArray bitArray = new BitArray((this.width / 32) + 1);
        for (int i = 0; i < this.height; i++) {
            int i2 = i * this.rowSize;
            int[] bitArray2 = bitMatrix.getRow(i, bitArray).getBitArray();
            for (int i3 = 0; i3 < this.rowSize; i3++) {
                int[] iArr = this.bits;
                int i4 = i2 + i3;
                iArr[i4] = iArr[i4] ^ bitArray2[i3];
            }
        }
    }
}
