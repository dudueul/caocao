package com.naver.glink.android.sdk.ui.write;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.naver.glink.android.sdk.a.f;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.y;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/* JADX INFO: compiled from: ImageResizeAsyncTask.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a extends AsyncTask<Uri, Integer, Uri> {
    public static final int a = 1280;
    private static final int b = 90;
    private static final l c = l.a(a.class.getSimpleName());
    private Context d;
    private InterfaceC0066a e;
    private int f;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.write.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ImageResizeAsyncTask.java */
    public interface InterfaceC0066a {
        void a(Uri uri);
    }

    public a(Context context) {
        this.f = 90;
        this.d = context;
    }

    public a(Context context, int i) {
        this.f = 90;
        this.d = context;
        this.f = i;
    }

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static void a(final Context context) {
        if (context == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.naver.glink.android.sdk.ui.write.a.2
            @Override // java.lang.Runnable
            public void run() {
                File fileB = a.b(context);
                if (fileB == null || fileB.listFiles() == null) {
                    return;
                }
                File[] fileArrListFiles = fileB.listFiles();
                for (File file : fileArrListFiles) {
                    if (TextUtils.equals(f.a(file), "jpeg")) {
                        a.c.a("delete temporary file: " + file.getAbsolutePath(), new Object[0]);
                        file.delete();
                    }
                }
            }
        }).start();
    }

    public static void a(Context context, Uri uri, InterfaceC0066a interfaceC0066a) {
        a(context, uri, interfaceC0066a, 90, true);
    }

    public static void a(Context context, Uri uri, final InterfaceC0066a interfaceC0066a, int i, boolean z) {
        if (uri == null || interfaceC0066a == null) {
            return;
        }
        if (z && a(context, uri)) {
            interfaceC0066a.a(uri);
            return;
        }
        final String string = UUID.randomUUID().toString();
        com.naver.glink.android.sdk.ui.widget.progress.b.a(string);
        a aVar = new a(context, i);
        aVar.a(new InterfaceC0066a() { // from class: com.naver.glink.android.sdk.ui.write.a.1
            @Override // com.naver.glink.android.sdk.ui.write.a.InterfaceC0066a
            public void a(Uri uri2) {
                interfaceC0066a.a(uri2);
                com.naver.glink.android.sdk.ui.widget.progress.b.b(string);
            }
        });
        aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
    }

    public static boolean a(Context context, Uri uri) {
        if (uri == null) {
            return false;
        }
        if ("image/gif".equals(b(context, uri))) {
            return true;
        }
        String path = uri.getPath();
        return path != null && path.toLowerCase().endsWith(".gif");
    }

    public static File b(Context context) {
        return Glide.getPhotoCacheDir(context, "write_temporary_images_disk_cache");
    }

    /* JADX WARN: Code duplicated, block: B:19:0x003a  */
    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x003f: MOVE (r6 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:22:0x003f */
    public static String b(Context context, Uri uri) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor;
        String string;
        Cursor cursor2 = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(uri, new String[]{"mime_type"}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToNext()) {
                            string = cursorQuery.getString(0);
                        } else {
                            string = null;
                        }
                    } catch (Exception e) {
                        e = e;
                        c.a(e);
                        if (cursorQuery == null) {
                            return null;
                        }
                        cursorQuery.close();
                        return null;
                    }
                } else {
                    string = null;
                }
                if (cursorQuery == null) {
                    return string;
                }
                cursorQuery.close();
                return string;
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public int a(String str) {
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 0)) {
                case 3:
                    return 180;
                case 4:
                case 5:
                case 7:
                default:
                    return 0;
                case 6:
                    return 90;
                case 8:
                    return 270;
            }
        } catch (IOException e) {
            c.a(e);
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Uri doInBackground(Uri... uriArr) throws Throwable {
        File file;
        int iMin;
        int i;
        Uri uri = uriArr[0];
        try {
            if (TextUtils.equals(uri.getScheme(), "file")) {
                File file2 = new File(uri.getPath());
                if (!file2.exists()) {
                    throw new FileNotFoundException();
                }
                file = file2;
            } else {
                file = Glide.with(this.d).load(uri).downloadOnly(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getPath(), options);
            if (options.outWidth > options.outHeight) {
                iMin = Math.min(options.outWidth, a);
                i = (options.outHeight * iMin) / options.outWidth;
            } else {
                int iMin2 = Math.min(options.outHeight, a);
                iMin = (options.outWidth * iMin2) / options.outHeight;
                i = iMin2;
            }
            options.inSampleSize = a(options, iMin, i);
            options.inJustDecodeBounds = false;
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(file.getPath(), options);
            if (bitmapDecodeFile.getWidth() > 1280) {
                bitmapDecodeFile = Bitmap.createScaledBitmap(bitmapDecodeFile, iMin, i, false);
            }
            int iA = a(file.getPath());
            if (iA != 0) {
                Matrix matrix = new Matrix();
                matrix.setRotate(iA);
                bitmapDecodeFile = Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), matrix, true);
            }
            String str = b(this.d) + "/" + UUID.randomUUID().toString() + ".jpeg";
            a(str, bitmapDecodeFile, Bitmap.CompressFormat.JPEG, this.f);
            return y.a("file://" + str);
        } catch (Exception e) {
            c.a(e);
            return uri;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Uri uri) {
        this.e.a(uri);
    }

    public void a(InterfaceC0066a interfaceC0066a) {
        this.e = interfaceC0066a;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v7 */
    public boolean a(String str, Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) throws Throwable {
        ?? r2;
        FileOutputStream fileOutputStream;
        boolean zCompress = false;
        if (bitmap != null) {
            OutputStream outputStream = null;
            File file = new File(str);
            try {
                try {
                    if (file.createNewFile()) {
                        fileOutputStream = new FileOutputStream(file);
                        try {
                            zCompress = bitmap.compress(compressFormat, i, fileOutputStream);
                            r2 = fileOutputStream;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                    r2 = fileOutputStream;
                                } catch (IOException e) {
                                    l lVar = c;
                                    lVar.a(e);
                                    r2 = lVar;
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            c.a(e);
                            r2 = fileOutputStream;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                    r2 = fileOutputStream;
                                } catch (IOException e3) {
                                    l lVar2 = c;
                                    lVar2.a(e3);
                                    r2 = lVar2;
                                }
                            }
                        }
                    } else if (0 != 0) {
                        try {
                            outputStream.close();
                        } catch (IOException e4) {
                            c.a(e4);
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (r2 != 0) {
                        try {
                            r2.close();
                        } catch (IOException e5) {
                            c.a(e5);
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                r2 = 0;
                if (r2 != 0) {
                    r2.close();
                }
                throw th;
            }
        }
        return zCompress;
    }
}
