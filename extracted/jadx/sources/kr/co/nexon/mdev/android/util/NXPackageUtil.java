package kr.co.nexon.mdev.android.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import com.nexon.core.log.ToyLog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPackageUtil {
    public static String getAppVersion(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionName;
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getApplicationId(Context context) {
        return context.getPackageName();
    }

    public static String getBase64SHA1FromKeyStore(Context context) {
        byte[] sHA1FromKeyStore = getSHA1FromKeyStore(context);
        return sHA1FromKeyStore.length <= 0 ? "" : Base64.encodeToString(sHA1FromKeyStore, 2);
    }

    public static String getHexStringSHA1FromKeyStore(Context context) {
        byte[] sHA1FromKeyStore = getSHA1FromKeyStore(context);
        if (sHA1FromKeyStore.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sHA1FromKeyStore.length; i++) {
            sb.append(String.format("%02x", Integer.valueOf(sHA1FromKeyStore[i] & 255)).toUpperCase());
            if (i < sHA1FromKeyStore.length - 1) {
                sb.append(":");
            }
        }
        String string = sb.toString();
        ToyLog.d("SHA1 : " + string);
        return string;
    }

    private static byte[] getSHA1FromKeyStore(Context context) {
        byte[] bArrDigest;
        int i = 0;
        byte[] bArr = new byte[0];
        try {
            ToyLog.d("Package Name : " + context.getPackageName());
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            int length = signatureArr.length;
            bArrDigest = bArr;
            while (i < length) {
                try {
                    Signature signature = signatureArr[i];
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                    messageDigest.update(signature.toByteArray());
                    i++;
                    bArrDigest = messageDigest.digest();
                } catch (PackageManager.NameNotFoundException e) {
                    e = e;
                    ToyLog.d(e.getStackTrace().toString());
                    return bArrDigest;
                } catch (NoSuchAlgorithmException e2) {
                    e = e2;
                    ToyLog.d(e.getStackTrace().toString());
                    return bArrDigest;
                } catch (Exception e3) {
                    e = e3;
                    ToyLog.d(e.getStackTrace().toString());
                    return bArrDigest;
                }
            }
            return bArrDigest;
        } catch (PackageManager.NameNotFoundException e4) {
            e = e4;
            bArrDigest = bArr;
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            bArrDigest = bArr;
        } catch (Exception e6) {
            e = e6;
            bArrDigest = bArr;
        }
    }

    public static int getTargetSdkVersion(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.targetSdkVersion;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean hasPermission(Context context, String... strArr) {
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }
}
