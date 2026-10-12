package android.support.v4.util;

import android.support.annotation.RestrictTo;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class TimeUtils {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final Object sFormatSync = new Object();
    private static char[] sFormatStr = new char[24];

    private TimeUtils() {
    }

    private static int accumField(int i, int i2, boolean z, int i3) {
        if (i > 99 || (z && i3 >= 3)) {
            return i2 + 3;
        }
        if (i > 9 || (z && i3 >= 2)) {
            return i2 + 2;
        }
        if (z || i > 0) {
            return i2 + 1;
        }
        return 0;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, long j2, PrintWriter printWriter) {
        if (j == 0) {
            printWriter.print("--");
        } else {
            formatDuration(j - j2, printWriter, 0);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, PrintWriter printWriter) {
        formatDuration(j, printWriter, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, PrintWriter printWriter, int i) {
        synchronized (sFormatSync) {
            printWriter.print(new String(sFormatStr, 0, formatDurationLocked(j, i)));
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, StringBuilder sb) {
        synchronized (sFormatSync) {
            sb.append(sFormatStr, 0, formatDurationLocked(j, 0));
        }
    }

    private static int formatDurationLocked(long j, int i) {
        char c;
        if (sFormatStr.length < i) {
            sFormatStr = new char[i];
        }
        char[] cArr = sFormatStr;
        if (j == 0) {
            int i2 = i - 1;
            while (0 < i2) {
                cArr[0] = ' ';
            }
            cArr[0] = '0';
            return 1;
        }
        if (j > 0) {
            c = '+';
        } else {
            c = '-';
            j = -j;
        }
        int i3 = (int) (j % 1000);
        int iFloor = (int) Math.floor(j / 1000);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        if (iFloor > SECONDS_PER_DAY) {
            i4 = iFloor / SECONDS_PER_DAY;
            iFloor -= SECONDS_PER_DAY * i4;
        }
        if (iFloor > 3600) {
            i5 = iFloor / 3600;
            iFloor -= i5 * 3600;
        }
        if (iFloor > 60) {
            i6 = iFloor / 60;
            iFloor -= i6 * 60;
        }
        int i7 = 0;
        if (i != 0) {
            int iAccumField = accumField(i4, 1, false, 0);
            int iAccumField2 = iAccumField + accumField(i5, 1, iAccumField > 0, 2);
            int iAccumField3 = iAccumField2 + accumField(i6, 1, iAccumField2 > 0, 2);
            int iAccumField4 = iAccumField3 + accumField(iFloor, 1, iAccumField3 > 0, 2);
            for (int iAccumField5 = iAccumField4 + accumField(i3, 2, true, iAccumField4 > 0 ? 3 : 0) + 1; iAccumField5 < i; iAccumField5++) {
                cArr[i7] = ' ';
                i7++;
            }
        }
        cArr[i7] = c;
        int i8 = i7 + 1;
        boolean z = i != 0;
        int iPrintField = printField(cArr, i4, 'd', i8, false, 0);
        int iPrintField2 = printField(cArr, i5, 'h', iPrintField, iPrintField != i8, z ? 2 : 0);
        int iPrintField3 = printField(cArr, i6, 'm', iPrintField2, iPrintField2 != i8, z ? 2 : 0);
        int iPrintField4 = printField(cArr, iFloor, 's', iPrintField3, iPrintField3 != i8, z ? 2 : 0);
        int iPrintField5 = printField(cArr, i3, 'm', iPrintField4, true, (!z || iPrintField4 == i8) ? 0 : 3);
        cArr[iPrintField5] = 's';
        return iPrintField5 + 1;
    }

    private static int printField(char[] cArr, int i, char c, int i2, boolean z, int i3) {
        if (!z && i <= 0) {
            return i2;
        }
        if ((z && i3 >= 3) || i > 99) {
            int i4 = i / 100;
            cArr[i2] = (char) (i4 + 48);
            i2++;
            i -= i4 * 100;
        }
        if ((z && i3 >= 2) || i > 9 || i2 != i2) {
            int i5 = i / 10;
            cArr[i2] = (char) (i5 + 48);
            i2++;
            i -= i5 * 10;
        }
        cArr[i2] = (char) (i + 48);
        int i6 = i2 + 1;
        cArr[i6] = c;
        return i6 + 1;
    }
}
