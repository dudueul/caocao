package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.GuardedBy;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzau {
    private final SharedPreferences zzct;
    private final zzw zzcu;

    @GuardedBy("this")
    private final Map<String, zzx> zzcv;
    private final Context zzv;

    public zzau(Context context) {
        this(context, new zzw());
    }

    private zzau(Context context, zzw zzwVar) {
        this.zzcv = new ArrayMap();
        this.zzv = context;
        this.zzct = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.zzcu = zzwVar;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.zzv), "com.google.android.gms.appid-no-backup");
        if (file.exists()) {
            return;
        }
        try {
            if (!file.createNewFile() || isEmpty()) {
                return;
            }
            Log.i("FirebaseInstanceId", "App restored, clearing state");
            zzak();
            FirebaseInstanceId.getInstance().zzl();
        } catch (IOException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.d("FirebaseInstanceId", strValueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(strValueOf) : new String("Error creating file in no backup dir: "));
            }
        }
    }

    private final synchronized boolean isEmpty() {
        return this.zzct.getAll().isEmpty();
    }

    private static String zzb(String str, String str2, String str3) {
        return new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(str2).length() + String.valueOf(str3).length()).append(str).append("|T|").append(str2).append("|").append(str3).toString();
    }

    static String zzc(String str, String str2) {
        return new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length()).append(str).append("|S|").append(str2).toString();
    }

    public final synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String strZza = zzav.zza(str4, str5, System.currentTimeMillis());
        if (strZza != null) {
            SharedPreferences.Editor editorEdit = this.zzct.edit();
            editorEdit.putString(zzb(str, str2, str3), strZza);
            editorEdit.commit();
        }
    }

    public final synchronized String zzaj() {
        return this.zzct.getString("topic_operaion_queue", "");
    }

    public final synchronized void zzak() {
        this.zzcv.clear();
        zzw.zza(this.zzv);
        this.zzct.edit().clear().commit();
    }

    public final synchronized zzav zzc(String str, String str2, String str3) {
        return zzav.zzi(this.zzct.getString(zzb(str, str2, str3), null));
    }

    public final synchronized void zzd(String str, String str2, String str3) {
        String strZzb = zzb(str, str2, str3);
        SharedPreferences.Editor editorEdit = this.zzct.edit();
        editorEdit.remove(strZzb);
        editorEdit.commit();
    }

    public final synchronized void zzf(String str) {
        this.zzct.edit().putString("topic_operaion_queue", str).apply();
    }

    public final synchronized zzx zzg(String str) {
        zzx zzxVarZzc;
        zzxVarZzc = this.zzcv.get(str);
        if (zzxVarZzc == null) {
            try {
                zzxVarZzc = this.zzcu.zzb(this.zzv, str);
            } catch (zzy e) {
                Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
                FirebaseInstanceId.getInstance().zzl();
                zzxVarZzc = this.zzcu.zzc(this.zzv, str);
            }
            this.zzcv.put(str, zzxVarZzc);
        }
        return zzxVarZzc;
    }

    public final synchronized void zzh(String str) {
        String strConcat = String.valueOf(str).concat("|T|");
        SharedPreferences.Editor editorEdit = this.zzct.edit();
        for (String str2 : this.zzct.getAll().keySet()) {
            if (str2.startsWith(strConcat)) {
                editorEdit.remove(str2);
            }
        }
        editorEdit.commit();
    }
}
