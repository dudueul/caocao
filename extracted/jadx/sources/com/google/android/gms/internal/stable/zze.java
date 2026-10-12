package com.google.android.gms.internal.stable;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zze {

    public static class zza implements BaseColumns {
        private static HashMap<Uri, zzh> zzagq = new HashMap<>();

        private static zzh zza(ContentResolver contentResolver, Uri uri) {
            zzh zzhVar = zzagq.get(uri);
            if (zzhVar == null) {
                zzh zzhVar2 = new zzh();
                zzagq.put(uri, zzhVar2);
                contentResolver.registerContentObserver(uri, true, new zzf(null, zzhVar2));
                return zzhVar2;
            }
            if (!zzhVar.zzagu.getAndSet(false)) {
                return zzhVar;
            }
            synchronized (zzhVar) {
                zzhVar.zzags.clear();
                zzhVar.zzagt = new Object();
            }
            return zzhVar;
        }

        /* JADX WARN: Code duplicated, block: B:21:0x0042 A[Catch: all -> 0x008d, SQLException -> 0x008f, TRY_LEAVE, TryCatch #2 {SQLException -> 0x008f, blocks: (B:19:0x003c, B:29:0x0051, B:21:0x0042), top: B:50:0x003c }] */
        /* JADX WARN: Code duplicated, block: B:23:0x0048  */
        /* JADX WARN: Code duplicated, block: B:41:0x0089  */
        protected static String zza(ContentResolver contentResolver, Uri uri, String str) throws Throwable {
            zzh zzhVarZza;
            Cursor cursorQuery;
            String string;
            synchronized (zza.class) {
                zzhVarZza = zza(contentResolver, uri);
            }
            synchronized (zzhVarZza) {
                Object obj = zzhVarZza.zzagt;
                if (zzhVarZza.zzags.containsKey(str)) {
                    string = zzhVarZza.zzags.get(str);
                } else {
                    try {
                        cursorQuery = contentResolver.query(uri, new String[]{"value"}, "name=?", new String[]{str}, null);
                        if (cursorQuery != null) {
                            try {
                                try {
                                    if (cursorQuery.moveToFirst()) {
                                        string = cursorQuery.getString(0);
                                        try {
                                            zza(zzhVarZza, obj, str, string);
                                            if (cursorQuery != null) {
                                                cursorQuery.close();
                                            }
                                        } catch (SQLException e) {
                                            e = e;
                                            Log.e("GoogleSettings", "Can't get key " + str + " from " + uri, e);
                                            if (cursorQuery != null) {
                                                cursorQuery.close();
                                            }
                                        }
                                    } else {
                                        zza(zzhVarZza, obj, str, null);
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        string = null;
                                    }
                                } catch (SQLException e2) {
                                    e = e2;
                                    string = null;
                                }
                            } catch (Throwable th) {
                                th = th;
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                throw th;
                            }
                        } else {
                            zza(zzhVarZza, obj, str, null);
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            string = null;
                        }
                    } catch (SQLException e3) {
                        e = e3;
                        cursorQuery = null;
                        string = null;
                    } catch (Throwable th2) {
                        th = th2;
                        cursorQuery = null;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        throw th;
                    }
                }
            }
            return string;
        }

        private static void zza(zzh zzhVar, Object obj, String str, String str2) {
            synchronized (zzhVar) {
                if (obj == zzhVar.zzagt) {
                    zzhVar.zzags.put(str, str2);
                }
            }
        }
    }
}
