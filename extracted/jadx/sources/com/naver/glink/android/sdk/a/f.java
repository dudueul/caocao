package com.naver.glink.android.sdk.a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;

/* JADX INFO: compiled from: FileUtils.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class f {
    private static final String a = "FileUtils";

    /* JADX WARN: Code duplicated, block: B:22:0x004e  */
    /* JADX WARN: Code duplicated, block: B:33:0x006b  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    public static String a(Context context, Uri uri) throws Throwable {
        Cursor cursorQuery;
        String string;
        if (!"content".equalsIgnoreCase(uri.getScheme())) {
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
            return null;
        }
        ?? r1 = "_data";
        try {
            try {
                cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_data");
                        if (cursorQuery.moveToFirst()) {
                            string = cursorQuery.getString(columnIndexOrThrow);
                        } else {
                            string = null;
                        }
                    } catch (Exception e) {
                        e = e;
                        Log.d(a, "exception: ", e);
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
                if (r1 != 0) {
                    r1.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            r1 = 0;
            if (r1 != 0) {
                r1.close();
            }
            throw th;
        }
    }

    public static String a(File file) {
        String name;
        int iLastIndexOf;
        return (file == null || (name = file.getName()) == null || (iLastIndexOf = name.lastIndexOf(".")) <= 0 || name.endsWith(".")) ? "" : name.substring(iLastIndexOf + 1);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0070  */
    public static long b(Context context, Uri uri) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor;
        long j;
        if (!"content".equalsIgnoreCase(uri.getScheme())) {
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return new File(uri.getPath()).length();
            }
            return 0L;
        }
        try {
            cursorQuery = context.getContentResolver().query(uri, new String[]{"_size"}, null, null, null);
            if (cursorQuery != null) {
                try {
                    int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_size");
                    if (cursorQuery.moveToFirst()) {
                        j = cursorQuery.getLong(columnIndexOrThrow);
                    } else {
                        j = 0;
                    }
                } catch (Exception e) {
                    cursor = cursorQuery;
                    if (cursor == null) {
                        return 0L;
                    }
                    cursor.close();
                    return 0L;
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            } else {
                j = 0;
            }
            if (cursorQuery == null) {
                return j;
            }
            cursorQuery.close();
            return j;
        } catch (Exception e2) {
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
    }

    public static String b(File file) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(a(file).toLowerCase());
        return TextUtils.isEmpty(mimeTypeFromExtension) ? "application/octet-stream" : mimeTypeFromExtension;
    }
}
