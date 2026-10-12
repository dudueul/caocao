package kr.co.nexon.mdev.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tapjoy.TapjoyConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXDeviceUtil {
    public static boolean checkSDCard() {
        String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState != null && externalStorageState.equals("mounted");
    }

    public static int getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    public static int getScreenHeight(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidth(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static boolean is3gConnected(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0).isConnectedOrConnecting();
    }

    public static boolean isAvailableNetwork(@NonNull Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean isHighApiLevel(Context context, int i) {
        return Build.VERSION.SDK_INT >= i && NXPackageUtil.getTargetSdkVersion(context.getApplicationContext()) >= i;
    }

    public static boolean isScreenOn(Context context) {
        return ((PowerManager) context.getSystemService("power")).isScreenOn();
    }

    public static boolean isWIFIEnabled(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI);
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    public static boolean isWiFiConnected(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnectedOrConnecting();
    }

    public static int screenDensity(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }
}
