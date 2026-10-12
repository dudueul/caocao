package com.naver.glink.android.sdk.ui.record;

import android.graphics.Point;
import android.os.Environment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: RecordInfo.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    public static final int a = 360;
    public static final int c = 720;
    public static final int d = 900000;
    public static final int f = 4000000;
    private int j;
    private int k = i;
    private int l;
    private String m;
    private boolean n;
    public static final int b = 480;
    public static int g = b;
    public static final int e = 2200000;
    public static int h = e;
    public static int i = 30;

    public c(int i2, int i3, boolean z) {
        this.j = i2;
        this.n = z;
        a(i2, i3);
        if (i2 != 480 && i2 != 720 && i2 != 360) {
            h();
        } else {
            if (i3 == 900000 || i3 == 2200000 || i3 == 4000000) {
                return;
            }
            h();
        }
    }

    public static c a() {
        return new c(b, e, false);
    }

    private String a(long j) {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(j));
    }

    private void a(int i2, int i3) {
        if (i2 == 720) {
            this.l = i3;
            return;
        }
        if (i2 == 480) {
            this.l = (int) (i3 * 0.7f);
        } else if (i2 == 360) {
            this.l = (int) (i3 * 0.5f);
        } else {
            this.l = (int) (i3 * 0.7f);
        }
    }

    private void h() {
        this.j = g;
        this.k = i;
        a(this.j, h);
    }

    private String i() {
        return "/NCR_" + a(System.currentTimeMillis()) + ".mp4";
    }

    public int b() {
        return this.j;
    }

    public int c() {
        return this.k;
    }

    public int d() {
        return this.l;
    }

    public Point e() {
        double d2;
        double d3;
        Point point = new Point();
        boolean zI = com.naver.glink.android.sdk.d.i();
        Point pointB = com.naver.glink.android.sdk.d.p().b();
        if (pointB == null) {
            return null;
        }
        if (zI) {
            d2 = (((double) this.j) / ((double) pointB.y)) * ((double) pointB.x);
            d3 = this.j;
        } else {
            d2 = this.j;
            d3 = ((double) pointB.y) * (((double) this.j) / ((double) pointB.x));
        }
        point.x = (int) d2;
        point.y = (int) d3;
        if (point.x % 16 != 0) {
            point.x = ((point.x / 16) + 1) * 16;
        }
        if (point.y % 16 != 0) {
            point.y = ((point.y / 16) + 1) * 16;
        }
        return point;
    }

    public boolean f() {
        return this.n;
    }

    public String g() {
        if (this.m == null) {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
            if (!externalStoragePublicDirectory.exists()) {
                externalStoragePublicDirectory.mkdir();
            }
            this.m = externalStoragePublicDirectory + i();
        }
        return this.m;
    }
}
