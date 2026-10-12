package com.tapjoy.internal;

import java.math.BigInteger;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ci extends Number {
    private final String a;

    public ci(String str) {
        this.a = str;
    }

    @Override // java.lang.Number
    public final double doubleValue() {
        return Double.parseDouble(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        if (number instanceof Integer) {
            return intValue() == number.intValue();
        }
        if (number instanceof Long) {
            return longValue() == number.longValue();
        }
        if (number instanceof Float) {
            return floatValue() == number.floatValue();
        }
        if (number instanceof Double) {
            return doubleValue() == number.doubleValue();
        }
        return this.a.equals(number.toString());
    }

    @Override // java.lang.Number
    public final float floatValue() {
        return Float.parseFloat(this.a);
    }

    @Override // java.lang.Number
    public final int intValue() {
        try {
            return Integer.parseInt(this.a);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.a);
            } catch (NumberFormatException e2) {
                return new BigInteger(this.a).intValue();
            }
        }
    }

    @Override // java.lang.Number
    public final long longValue() {
        try {
            return Long.parseLong(this.a);
        } catch (NumberFormatException e) {
            return new BigInteger(this.a).longValue();
        }
    }

    public final String toString() {
        return this.a;
    }
}
