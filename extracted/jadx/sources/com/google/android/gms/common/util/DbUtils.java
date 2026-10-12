package com.google.android.gms.common.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class DbUtils {
    private DbUtils() {
    }

    public static void clearDatabase(SQLiteDatabase sQLiteDatabase) throws Throwable {
        zza(sQLiteDatabase, "table", "sqlite_sequence", "android_metadata");
        zza(sQLiteDatabase, "trigger", new String[0]);
        zza(sQLiteDatabase, "view", new String[0]);
    }

    public static long countCurrentRowBytes(Cursor cursor) {
        return countCurrentRowBytes(cursor, Charset.forName("UTF-8"));
    }

    public static long countCurrentRowBytes(Cursor cursor, Charset charset) {
        long length = 0;
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            switch (cursor.getType(i)) {
                case 0:
                case 1:
                case 2:
                    length += 4;
                    break;
                case 3:
                    length += (long) cursor.getString(i).getBytes(charset).length;
                    break;
                case 4:
                    length += (long) cursor.getBlob(i).length;
                    break;
            }
        }
        return length;
    }

    public static long getDatabaseSize(Context context, String str) {
        try {
            File databasePath = context.getDatabasePath(str);
            if (databasePath != null) {
                return databasePath.length();
            }
        } catch (SecurityException e) {
            String strValueOf = String.valueOf(str);
            Log.w("DbUtils", strValueOf.length() != 0 ? "Failed to get db size for ".concat(strValueOf) : new String("Failed to get db size for "));
        }
        return 0L;
    }

    @Nullable
    public static Integer getIntegerFromCursor(Cursor cursor, int i) {
        return getIntegerFromCursor(cursor, i, null);
    }

    @Nullable
    public static Integer getIntegerFromCursor(Cursor cursor, int i, @Nullable Integer num) {
        return (i < 0 || cursor.isNull(i)) ? num : Integer.valueOf(cursor.getInt(i));
    }

    @Nullable
    public static Long getLongFromCursor(Cursor cursor, int i) {
        return getLongFromCursor(cursor, i, null);
    }

    @Nullable
    public static Long getLongFromCursor(Cursor cursor, int i, @Nullable Long l) {
        return (i < 0 || cursor.isNull(i)) ? l : Long.valueOf(cursor.getLong(i));
    }

    @Nullable
    public static String getStringFromCursor(Cursor cursor, int i) {
        return getStringFromCursor(cursor, i, null);
    }

    @Nullable
    public static String getStringFromCursor(Cursor cursor, int i, @Nullable String str) {
        return (i < 0 || cursor.isNull(i)) ? str : cursor.getString(i);
    }

    public static void putIntegerIntoContentValues(ContentValues contentValues, String str, @Nullable Integer num) {
        if (num != null) {
            contentValues.put(str, num);
        } else {
            contentValues.putNull(str);
        }
    }

    public static void putLongIntoContentValues(ContentValues contentValues, String str, @Nullable Long l) {
        if (l != null) {
            contentValues.put(str, l);
        } else {
            contentValues.putNull(str);
        }
    }

    public static void putStringIntoContentValues(ContentValues contentValues, String str, @Nullable String str2) {
        if (str2 != null) {
            contentValues.put(str, str2);
        } else {
            contentValues.putNull(str);
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x008d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:31:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:34:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private static void zza(SQLiteDatabase sQLiteDatabase, String str, String... strArr) throws Throwable {
        Throwable th = null;
        Preconditions.checkArgument("table".equals(str) || "view".equals(str) || "trigger".equals(str));
        Cursor cursorQuery = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "type == ?", new String[]{str}, null, null, null);
        try {
            Set of = CollectionUtils.setOf((Object[]) strArr);
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(0);
                if (!of.contains(string)) {
                    sQLiteDatabase.execSQL(new StringBuilder(String.valueOf(str).length() + 8 + String.valueOf(string).length()).append("DROP ").append(str).append(" '").append(string).append("'").toString());
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                th = th3;
                th = th2;
                if (cursorQuery != null) {
                    if (th != null) {
                        cursorQuery.close();
                    } else {
                        cursorQuery.close();
                    }
                }
                throw th;
            }
        }
    }
}
