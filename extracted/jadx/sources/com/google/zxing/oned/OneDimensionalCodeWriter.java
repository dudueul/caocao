package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class OneDimensionalCodeWriter implements Writer {
    protected static int appendPattern(boolean[] zArr, int i, int[] iArr, boolean z) {
        boolean z2 = z;
        int i2 = 0;
        int length = iArr.length;
        int i3 = 0;
        while (i3 < length) {
            int i4 = iArr[i3];
            int i5 = 0;
            int i6 = i;
            while (i5 < i4) {
                zArr[i6] = z2;
                i5++;
                i6++;
            }
            i2 += i4;
            z2 = !z2;
            i3++;
            i = i6;
        }
        return i2;
    }

    private static BitMatrix renderResult(boolean[] zArr, int i, int i2, int i3) {
        int length = zArr.length;
        int i4 = length + i3;
        int iMax = Math.max(i, i4);
        int iMax2 = Math.max(1, i2);
        int i5 = iMax / i4;
        BitMatrix bitMatrix = new BitMatrix(iMax, iMax2);
        int i6 = 0;
        int i7 = (iMax - (length * i5)) / 2;
        while (i6 < length) {
            if (zArr[i6]) {
                bitMatrix.setRegion(i7, 0, i5, iMax2);
            }
            i6++;
            i7 += i5;
        }
        return bitMatrix;
    }

    @Override // com.google.zxing.Writer
    public final BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Negative size is not allowed. Input: " + i + 'x' + i2);
        }
        int defaultMargin = getDefaultMargin();
        if (map != null && map.containsKey(EncodeHintType.MARGIN)) {
            defaultMargin = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
        }
        return renderResult(encode(str), i, i2, defaultMargin);
    }

    public abstract boolean[] encode(String str);

    public int getDefaultMargin() {
        return 10;
    }
}
