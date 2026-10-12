package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ib {
    public float a;
    public int b;

    public static ib a(String str) {
        if (jq.c(str)) {
            return null;
        }
        try {
            ib ibVar = new ib();
            int length = str.length();
            char cCharAt = str.charAt(length - 1);
            if (cCharAt == 'w') {
                ibVar.a = Float.valueOf(str.substring(0, length - 1)).floatValue();
                ibVar.b = 1;
            } else if (cCharAt == 'h') {
                ibVar.a = Float.valueOf(str.substring(0, length - 1)).floatValue();
                ibVar.b = 2;
            } else {
                ibVar.a = Float.valueOf(str).floatValue();
                ibVar.b = 0;
            }
            return ibVar;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public final float a(float f, float f2) {
        if (this.b == 1) {
            return (this.a * f) / 100.0f;
        }
        return this.b == 2 ? (this.a * f2) / 100.0f : this.a;
    }
}
