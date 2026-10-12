package com.tapjoy.internal;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class iu implements Serializable, Comparable {
    static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final iu b = new iu((byte[]) new byte[0].clone());
    final byte[] c;
    transient int d;
    transient String e;

    public iu(byte[] bArr) {
        this.c = bArr;
    }

    public byte a(int i) {
        return this.c[i];
    }

    public iu a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if (i2 > this.c.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.c.length + ")");
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i == 0 && i2 == this.c.length) {
            return this;
        }
        byte[] bArr = new byte[i3];
        System.arraycopy(this.c, i, bArr, 0, i3);
        return new iu(bArr);
    }

    public String a() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.c, je.a);
        this.e = str2;
        return str2;
    }

    void a(ir irVar) {
        irVar.a(this.c, 0, this.c.length);
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        return i >= 0 && i <= this.c.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && je.a(this.c, i, bArr, i2, i3);
    }

    public String b() {
        char[] cArr = new char[this.c.length * 2];
        byte[] bArr = this.c;
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b2 = bArr[i];
            int i3 = i2 + 1;
            cArr[i2] = a[(b2 >> 4) & 15];
            cArr[i3] = a[b2 & 15];
            i++;
            i2 = i3 + 1;
        }
        return new String(cArr);
    }

    public int c() {
        return this.c.length;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(Object obj) {
        iu iuVar = (iu) obj;
        int iC = c();
        int iC2 = iuVar.c();
        int iMin = Math.min(iC, iC2);
        for (int i = 0; i < iMin; i++) {
            int iA = a(i) & 255;
            int iA2 = iuVar.a(i) & 255;
            if (iA != iA2) {
                return iA < iA2 ? -1 : 1;
            }
        }
        if (iC == iC2) {
            return 0;
        }
        return iC >= iC2 ? 1 : -1;
    }

    public byte[] d() {
        return (byte[]) this.c.clone();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof iu) && ((iu) obj).c() == this.c.length && ((iu) obj).a(0, this.c, 0, this.c.length);
    }

    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.c);
        this.d = iHashCode;
        return iHashCode;
    }

    public String toString() {
        if (this.c.length == 0) {
            return "[size=0]";
        }
        String strA = a();
        int length = strA.length();
        int i = 0;
        int length2 = 0;
        while (true) {
            if (length2 >= length) {
                length2 = strA.length();
                break;
            }
            if (i != 64) {
                int iCodePointAt = strA.codePointAt(length2);
                if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                    length2 = -1;
                    break;
                }
                i++;
                length2 += Character.charCount(iCodePointAt);
            } else {
                break;
            }
        }
        if (length2 == -1) {
            return this.c.length <= 64 ? "[hex=" + b() + "]" : "[size=" + this.c.length + " hex=" + a(0, 64).b() + "…]";
        }
        String strReplace = strA.substring(0, length2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        return length2 < strA.length() ? "[size=" + this.c.length + " text=" + strReplace + "…]" : "[text=" + strReplace + "]";
    }
}
