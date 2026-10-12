package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.FirebaseApp;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzal {

    @GuardedBy("this")
    private String zzbz;

    @GuardedBy("this")
    private String zzca;

    @GuardedBy("this")
    private int zzcb;

    @GuardedBy("this")
    private int zzcc = 0;
    private final Context zzv;

    public zzal(Context context) {
        this.zzv = context;
    }

    public static String zza(FirebaseApp firebaseApp) {
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] strArrSplit = applicationId.split(":");
        if (strArrSplit.length < 2) {
            return null;
        }
        String str = strArrSplit[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }

    public static String zza(KeyPair keyPair) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1).digest(keyPair.getPublic().getEncoded());
            bArrDigest[0] = (byte) ((bArrDigest[0] & 15) + 112);
            return Base64.encodeToString(bArrDigest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    private final synchronized void zzaf() {
        PackageInfo packageInfoZze = zze(this.zzv.getPackageName());
        if (packageInfoZze != null) {
            this.zzbz = Integer.toString(packageInfoZze.versionCode);
            this.zzca = packageInfoZze.versionName;
        }
    }

    private final PackageInfo zze(String str) {
        try {
            return this.zzv.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf).length() + 23).append("Failed to find package ").append(strValueOf).toString());
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004b A[Catch: all -> 0x0023, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x0006, B:9:0x000a, B:11:0x001b, B:16:0x0026, B:18:0x002c, B:20:0x003f, B:22:0x0045, B:23:0x004b, B:25:0x005e, B:27:0x0064, B:28:0x006a, B:30:0x0077, B:31:0x007a, B:32:0x007d), top: B:34:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x006a A[Catch: all -> 0x0023, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x0006, B:9:0x000a, B:11:0x001b, B:16:0x0026, B:18:0x002c, B:20:0x003f, B:22:0x0045, B:23:0x004b, B:25:0x005e, B:27:0x0064, B:28:0x006a, B:30:0x0077, B:31:0x007a, B:32:0x007d), top: B:34:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x0077 A[Catch: all -> 0x0023, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x0006, B:9:0x000a, B:11:0x001b, B:16:0x0026, B:18:0x002c, B:20:0x003f, B:22:0x0045, B:23:0x004b, B:25:0x005e, B:27:0x0064, B:28:0x006a, B:30:0x0077, B:31:0x007a, B:32:0x007d), top: B:34:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x007d A[Catch: all -> 0x0023, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x0006, B:9:0x000a, B:11:0x001b, B:16:0x0026, B:18:0x002c, B:20:0x003f, B:22:0x0045, B:23:0x004b, B:25:0x005e, B:27:0x0064, B:28:0x006a, B:30:0x0077, B:31:0x007a, B:32:0x007d), top: B:34:0x0002 }] */
    public final synchronized int zzab() {
        List<ResolveInfo> listQueryBroadcastReceivers;
        int i = 0;
        synchronized (this) {
            if (this.zzcc != 0) {
                i = this.zzcc;
            } else {
                PackageManager packageManager = this.zzv.getPackageManager();
                if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == -1) {
                    Log.e("FirebaseInstanceId", "Google Play services missing or without correct permission.");
                } else if (PlatformVersion.isAtLeastO()) {
                    Intent intent = new Intent("com.google.iid.TOKEN_REQUEST");
                    intent.setPackage("com.google.android.gms");
                    listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
                    if (listQueryBroadcastReceivers != null) {
                        Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
                        if (PlatformVersion.isAtLeastO()) {
                            this.zzcc = 2;
                        } else {
                            this.zzcc = 1;
                        }
                        i = this.zzcc;
                    } else {
                        Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
                        if (PlatformVersion.isAtLeastO()) {
                            this.zzcc = 2;
                        } else {
                            this.zzcc = 1;
                        }
                        i = this.zzcc;
                    }
                } else {
                    Intent intent2 = new Intent("com.google.android.c2dm.intent.REGISTER");
                    intent2.setPackage("com.google.android.gms");
                    List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent2, 0);
                    if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
                        Intent intent3 = new Intent("com.google.iid.TOKEN_REQUEST");
                        intent3.setPackage("com.google.android.gms");
                        listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent3, 0);
                        if (listQueryBroadcastReceivers != null || listQueryBroadcastReceivers.size() <= 0) {
                            Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
                            if (PlatformVersion.isAtLeastO()) {
                                this.zzcc = 2;
                            } else {
                                this.zzcc = 1;
                            }
                            i = this.zzcc;
                        } else {
                            this.zzcc = 2;
                            i = this.zzcc;
                        }
                    } else {
                        this.zzcc = 1;
                        i = this.zzcc;
                    }
                }
            }
        }
        return i;
    }

    public final synchronized String zzac() {
        if (this.zzbz == null) {
            zzaf();
        }
        return this.zzbz;
    }

    public final synchronized String zzad() {
        if (this.zzca == null) {
            zzaf();
        }
        return this.zzca;
    }

    public final synchronized int zzae() {
        PackageInfo packageInfoZze;
        if (this.zzcb == 0 && (packageInfoZze = zze("com.google.android.gms")) != null) {
            this.zzcb = packageInfoZze.versionCode;
        }
        return this.zzcb;
    }
}
