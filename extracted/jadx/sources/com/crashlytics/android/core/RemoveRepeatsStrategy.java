package com.crashlytics.android.core;

import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class RemoveRepeatsStrategy implements StackTraceTrimmingStrategy {
    private final int maxRepetitions;

    public RemoveRepeatsStrategy() {
        this(1);
    }

    public RemoveRepeatsStrategy(int i) {
        this.maxRepetitions = i;
    }

    private static boolean isRepeatingSequence(StackTraceElement[] stackTraceElementArr, int i, int i2) {
        int i3 = i2 - i;
        if (i2 + i3 > stackTraceElementArr.length) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!stackTraceElementArr[i + i4].equals(stackTraceElementArr[i2 + i4])) {
                return false;
            }
        }
        return true;
    }

    private static StackTraceElement[] trimRepeats(StackTraceElement[] stackTraceElementArr, int i) {
        HashMap map = new HashMap();
        StackTraceElement[] stackTraceElementArr2 = new StackTraceElement[stackTraceElementArr.length];
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i4 < stackTraceElementArr.length) {
            int i5 = i4;
            StackTraceElement stackTraceElement = stackTraceElementArr[i4];
            Integer num = (Integer) map.get(stackTraceElement);
            if (num == null || !isRepeatingSequence(stackTraceElementArr, num.intValue(), i4)) {
                i3 = 1;
                stackTraceElementArr2[i2] = stackTraceElementArr[i4];
                i2++;
            } else {
                int iIntValue = i4 - num.intValue();
                if (i3 < i) {
                    System.arraycopy(stackTraceElementArr, i4, stackTraceElementArr2, i2, iIntValue);
                    i2 += iIntValue;
                    i3++;
                }
                i4 += iIntValue - 1;
            }
            map.put(stackTraceElement, Integer.valueOf(i5));
            i4++;
        }
        StackTraceElement[] stackTraceElementArr3 = new StackTraceElement[i2];
        System.arraycopy(stackTraceElementArr2, 0, stackTraceElementArr3, 0, stackTraceElementArr3.length);
        return stackTraceElementArr3;
    }

    @Override // com.crashlytics.android.core.StackTraceTrimmingStrategy
    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr) {
        StackTraceElement[] stackTraceElementArrTrimRepeats = trimRepeats(stackTraceElementArr, this.maxRepetitions);
        return stackTraceElementArrTrimRepeats.length < stackTraceElementArr.length ? stackTraceElementArrTrimRepeats : stackTraceElementArr;
    }
}
