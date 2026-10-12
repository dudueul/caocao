package com.naver.glink.android.sdk.ui.profile.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: BitmapUtil.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static final String d = a.class.getSimpleName();
    public static final String b = "plug";
    public static final String a = Environment.getExternalStorageDirectory().getPath() + File.separator + b + File.separator + ".img";
    public static final String c = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + b;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.profile.image.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BitmapUtil.java */
    public static class C0053a {
        public String a;
        public int b;
        public int c;

        public C0053a(String str, int i, int i2) {
            this.a = str;
            this.b = i;
            this.c = i2;
        }
    }

    private static int a(Context context, int i) {
        return 1;
    }

    public static Bitmap a(Context context, String str) {
        C0053a c0053aA = a(str);
        int i = c0053aA.c;
        int i2 = c0053aA.b;
        if (i == 0 && i2 == 0) {
            return null;
        }
        if (i <= i2) {
            i = i2;
        }
        int iA = a(context, i);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = iA;
        return BitmapFactory.decodeFile(str, options);
    }

    public static C0053a a(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new C0053a(str, options.outHeight, options.outWidth);
    }

    public static void a() {
        File file = new File(a);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(a, ".nomedia");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                Log.d(d, "", e);
            }
        }
        File file3 = new File(c);
        if (file3.exists()) {
            return;
        }
        file3.mkdirs();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    public static boolean a(String str, Bitmap bitmap) throws Throwable {
        FileOutputStream fileOutputStream;
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        }
        a();
        File file = new File(str);
        try {
            file.createNewFile();
            ?? IsRecycled = bitmap.isRecycled();
            try {
                if (IsRecycled != 0) {
                    return false;
                }
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        if (str.endsWith("png")) {
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        } else {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e) {
                                Log.d(d, "", e);
                            }
                        }
                        return true;
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        Log.d(d, "", e);
                        if (fileOutputStream == null) {
                            return false;
                        }
                        try {
                            fileOutputStream.close();
                            return false;
                        } catch (IOException e3) {
                            Log.d(d, "", e3);
                            return false;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        Log.d(d, "", e);
                        if (fileOutputStream == null) {
                            return false;
                        }
                        try {
                            fileOutputStream.close();
                            return false;
                        } catch (IOException e5) {
                            Log.d(d, "", e5);
                            return false;
                        }
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                    fileOutputStream = null;
                } catch (Exception e7) {
                    e = e7;
                    fileOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    IsRecycled = 0;
                    if (IsRecycled != 0) {
                        try {
                            IsRecycled.close();
                        } catch (IOException e8) {
                            Log.d(d, "", e8);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e9) {
            Log.d(d, "", e9);
            return false;
        }
    }

    public static void b(String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            for (String str2 : file.list()) {
                File file2 = new File(file, str2);
                if (file2.isFile()) {
                    file2.delete();
                }
            }
        }
    }
}
