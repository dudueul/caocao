package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class jf {
    public static jf a = new jf(null);
    public String b;
    public Throwable c;
    private Object[] d;

    public jf(String str) {
        this(str, null, null);
    }

    public jf(String str, Object[] objArr, Throwable th) {
        this.b = str;
        this.c = th;
        if (th == null) {
            this.d = objArr;
            return;
        }
        if (objArr == null || objArr.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        }
        int length = objArr.length - 1;
        Object[] objArr2 = new Object[length];
        System.arraycopy(objArr, 0, objArr2, 0, length);
        this.d = objArr2;
    }
}
