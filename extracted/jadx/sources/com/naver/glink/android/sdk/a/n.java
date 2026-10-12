package com.naver.glink.android.sdk.a;

import android.os.Looper;
import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: compiled from: PlayingTimeFormatter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class n {
    private static n c;
    private StringBuilder a = new StringBuilder();
    private Formatter b = new Formatter(this.a, Locale.getDefault());

    public static String b(int i) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("format accessed from non-main thread " + Looper.myLooper());
        }
        if (c == null) {
            c = new n();
        }
        return c.a(i);
    }

    public String a(int i) {
        int i2 = i / 1000;
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 / 3600;
        this.a.setLength(0);
        return i5 > 0 ? this.b.format("%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)).toString() : this.b.format("%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3)).toString();
    }
}
