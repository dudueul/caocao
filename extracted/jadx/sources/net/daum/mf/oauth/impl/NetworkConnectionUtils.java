package net.daum.mf.oauth.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class NetworkConnectionUtils {
    private NetworkConnectionUtils() {
    }

    public static boolean checkWIFIAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED;
    }

    public static boolean showMesageIfNetworkDisconnected(Context context) {
        if (isNetworkConnected(context)) {
            return false;
        }
        showNetworkDisconnectionToast(context);
        return true;
    }

    public static void showNetworkDisconnectionToast(Context context) {
        Toast.makeText(context, "네트워크 연결이 불안정해요.\\n잠시 후 다시 시도해주세요.", 0).show();
    }
}
