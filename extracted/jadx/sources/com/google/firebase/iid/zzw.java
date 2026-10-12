package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzw {
    zzw() {
    }

    @Nullable
    private static zzx zza(SharedPreferences sharedPreferences, String str) throws zzy {
        String string = sharedPreferences.getString(zzau.zzc(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzau.zzc(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzx(zzb(string, string2), zzb(sharedPreferences, str));
    }

    @Nullable
    private static zzx zza(File file) throws Throwable {
        Throwable th;
        Throwable th2;
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String property = properties.getProperty("pub");
            String property2 = properties.getProperty("pri");
            if (property == null || property2 == null) {
                zza((Throwable) null, fileInputStream);
                return null;
            }
            try {
                zzx zzxVar = new zzx(zzb(property, property2), Long.parseLong(properties.getProperty("cre")));
                zza((Throwable) null, fileInputStream);
                return zzxVar;
            } catch (NumberFormatException e) {
                throw new zzy(e);
            }
        } catch (Throwable th3) {
            th = th3;
            th2 = null;
            zza(th2, fileInputStream);
            throw th;
        }
    }

    static void zza(Context context) {
        for (File file : zzb(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    private static void zza(Context context, String str, zzx zzxVar) {
        Throwable th = null;
        try {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Writing key to properties file");
            }
            File fileZzf = zzf(context, str);
            fileZzf.createNewFile();
            Properties properties = new Properties();
            properties.setProperty("pub", zzxVar.zzu());
            properties.setProperty("pri", zzxVar.zzv());
            properties.setProperty("cre", String.valueOf(zzxVar.zzbj));
            FileOutputStream fileOutputStream = new FileOutputStream(fileZzf);
            try {
                try {
                    properties.store(fileOutputStream, (String) null);
                    zza((Throwable) null, fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                zza(th, fileOutputStream);
                throw th3;
            }
        } catch (IOException e) {
            String strValueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf).length() + 21).append("Failed to write key: ").append(strValueOf).toString());
        }
    }

    private static /* synthetic */ void zza(Throwable th, FileInputStream fileInputStream) throws IOException {
        if (th == null) {
            fileInputStream.close();
            return;
        }
        try {
            fileInputStream.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zza.zza(th, th2);
        }
    }

    private static /* synthetic */ void zza(Throwable th, FileOutputStream fileOutputStream) throws IOException {
        if (th == null) {
            fileOutputStream.close();
            return;
        }
        try {
            fileOutputStream.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zza.zza(th, th2);
        }
    }

    private static long zzb(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzau.zzc(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException e) {
            }
        }
        return 0L;
    }

    private static File zzb(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private static KeyPair zzb(String str, String str2) throws zzy {
        try {
            byte[] bArrDecode = Base64.decode(str, 8);
            byte[] bArrDecode2 = Base64.decode(str2, 8);
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                return new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(bArrDecode)), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArrDecode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String strValueOf = String.valueOf(e);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf).length() + 19).append("Invalid key stored ").append(strValueOf).toString());
                throw new zzy(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzy(e2);
        }
    }

    private final void zzb(Context context, String str, zzx zzxVar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzxVar.equals(zza(sharedPreferences, str))) {
                return;
            }
        } catch (zzy e) {
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to shared preferences");
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(zzau.zzc(str, "|P|"), zzxVar.zzu());
        editorEdit.putString(zzau.zzc(str, "|K|"), zzxVar.zzv());
        editorEdit.putString(zzau.zzc(str, "cre"), String.valueOf(zzxVar.zzbj));
        editorEdit.commit();
    }

    @Nullable
    private final zzx zzd(Context context, String str) throws zzy {
        zzy zzyVar;
        try {
            zzx zzxVarZze = zze(context, str);
            if (zzxVarZze != null) {
                zzb(context, str, zzxVarZze);
                return zzxVarZze;
            }
            zzyVar = null;
        } catch (zzy e) {
            zzyVar = e;
        }
        try {
            zzx zzxVarZza = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
            if (zzxVarZza != null) {
                zza(context, str, zzxVarZza);
                return zzxVarZza;
            }
            e = zzyVar;
        } catch (zzy e2) {
            e = e2;
        }
        if (e != null) {
            throw e;
        }
        return null;
    }

    @Nullable
    private final zzx zze(Context context, String str) throws zzy {
        File fileZzf = zzf(context, str);
        if (!fileZzf.exists()) {
            return null;
        }
        try {
            return zza(fileZzf);
        } catch (IOException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(e);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf).length() + 40).append("Failed to read key from file, retrying: ").append(strValueOf).toString());
            }
            try {
                return zza(fileZzf);
            } catch (IOException e2) {
                String strValueOf2 = String.valueOf(e2);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf2).length() + 45).append("IID file exists, but failed to read from it: ").append(strValueOf2).toString());
                throw new zzy(e2);
            }
        }
    }

    private static File zzf(Context context, String str) {
        String string;
        if (TextUtils.isEmpty(str)) {
            string = "com.google.InstanceId.properties";
        } else {
            try {
                String strEncodeToString = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                string = new StringBuilder(String.valueOf(strEncodeToString).length() + 33).append("com.google.InstanceId_").append(strEncodeToString).append(".properties").toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzb(context), string);
    }

    @WorkerThread
    final zzx zzb(Context context, String str) throws zzy {
        zzx zzxVarZzd = zzd(context, str);
        return zzxVarZzd != null ? zzxVarZzd : zzc(context, str);
    }

    @WorkerThread
    final zzx zzc(Context context, String str) {
        zzx zzxVar = new zzx(zza.zzb(), System.currentTimeMillis());
        try {
            zzx zzxVarZzd = zzd(context, str);
            if (zzxVarZzd != null) {
                if (!Log.isLoggable("FirebaseInstanceId", 3)) {
                    return zzxVarZzd;
                }
                Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
                return zzxVarZzd;
            }
        } catch (zzy e) {
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Generated new key");
        }
        zza(context, str, zzxVar);
        zzb(context, str, zzxVar);
        return zzxVar;
    }
}
