package com.nhn.android.naverlogin.connection;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.nhn.android.naverlogin.data.OAuthLoginString;
import com.tapjoy.TapjoyConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NetworkState {
    static boolean shown = false;

    public interface RetryListener {
        void onResult(boolean z);
    }

    public static boolean checkConnectivity(Context context, boolean z, RetryListener retryListener) {
        if (isDataConnected(context)) {
            return true;
        }
        if (z) {
            if (retryListener == null) {
                Toast.makeText(context, OAuthLoginString.naveroauthlogin_string_network_state_not_available.getString(context), 1).show();
                return false;
            }
            showRetry(context, retryListener);
        }
        return false;
    }

    public static String getNetworkState(Context context) {
        if (is3GConnected(context)) {
            return "cell";
        }
        return isWifiConnected(context) ? TapjoyConstants.TJC_CONNECTION_TYPE_WIFI : FacebookRequestErrorClassification.KEY_OTHER;
    }

    public static boolean is3GConnected(Context context) {
        NetworkInfo networkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            return (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(0)) == null || !networkInfo.isConnected()) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isDataConnected(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager.getActiveNetworkInfo() != null) {
                return connectivityManager.getActiveNetworkInfo().isConnected();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean isWifiConnected(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            return connectivityManager != null && connectivityManager.getNetworkInfo(1).isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showRetry(final Context context, final RetryListener retryListener) {
        if (shown || context == null) {
            return;
        }
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        shown = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_dialog_alert);
        builder.setMessage(OAuthLoginString.naveroauthlogin_string_network_state_not_available.getString(context));
        builder.setPositiveButton(OAuthLoginString.naveroauthlogin_string_msg_retry.getString(context), new DialogInterface.OnClickListener() { // from class: com.nhn.android.naverlogin.connection.NetworkState.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                NetworkState.shown = false;
                if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
                    return;
                }
                retryListener.onResult(true);
            }
        });
        builder.setNegativeButton(OAuthLoginString.naveroauthlogin_string_msg_cancel.getString(context), new DialogInterface.OnClickListener() { // from class: com.nhn.android.naverlogin.connection.NetworkState.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                NetworkState.shown = false;
                if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
                    return;
                }
                retryListener.onResult(false);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.nhn.android.naverlogin.connection.NetworkState.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                NetworkState.shown = false;
                if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
                    return;
                }
                retryListener.onResult(false);
            }
        });
        try {
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
