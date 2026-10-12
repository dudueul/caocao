package kr.co.nexon.android.sns.google;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPBaseGameUtils {
    public static Dialog makeSimpleDialog(Activity activity, String str) {
        return new AlertDialog.Builder(activity).setMessage(str).setNeutralButton(R.string.ok, (DialogInterface.OnClickListener) null).create();
    }

    public static Dialog makeSimpleDialog(Activity activity, String str, String str2) {
        return new AlertDialog.Builder(activity).setTitle(str).setMessage(str2).setNeutralButton(R.string.ok, (DialogInterface.OnClickListener) null).create();
    }

    public static boolean resolveConnectionFailure(Activity activity, GoogleApiClient googleApiClient, ConnectionResult connectionResult, int i, String str) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, i);
                return true;
            } catch (IntentSender.SendIntentException e) {
                googleApiClient.connect();
                return false;
            }
        }
        Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), activity, i);
        if (errorDialog != null) {
            errorDialog.show();
            return false;
        }
        showAlert(activity, str);
        return false;
    }

    public static void showActivityResultError(Activity activity, int i, int i2, int i3) {
        Dialog dialogMakeSimpleDialog;
        if (activity == null) {
            Log.e("NPBaseGameUtils", "*** No Activity. Can't show failure dialog!");
            return;
        }
        switch (i2) {
            case 10002:
                dialogMakeSimpleDialog = makeSimpleDialog(activity, "[TOY] SIGN_IN_FAILED");
                break;
            case 10003:
                dialogMakeSimpleDialog = makeSimpleDialog(activity, "[TOY] RESULT_LICENSE_FAILED");
                break;
            case 10004:
                dialogMakeSimpleDialog = makeSimpleDialog(activity, "[TOY] RESULT_APP_MISCONFIGURED");
                break;
            default:
                dialogMakeSimpleDialog = GoogleApiAvailability.getInstance().getErrorDialog(activity, GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity), i, null);
                if (dialogMakeSimpleDialog == null) {
                    Log.e("BaseGamesUtils", "No standard error dialog available. Making fallback dialog.");
                    dialogMakeSimpleDialog = makeSimpleDialog(activity, activity.getString(i3));
                }
                break;
        }
        dialogMakeSimpleDialog.show();
    }

    public static void showActivityResultError(Activity activity, int i, int i2, String str) {
        Dialog dialogMakeSimpleDialog;
        if (activity == null) {
            Log.e("NPBaseGameUtils", "*** No Activity. Can't show failure dialog!");
            return;
        }
        switch (i2) {
            case 10002:
                dialogMakeSimpleDialog = makeSimpleDialog(activity, "[TOY] SIGN_IN_FAILED");
                break;
            case 10003:
                dialogMakeSimpleDialog = makeSimpleDialog(activity, "[TOY] RESULT_LICENSE_FAILED");
                break;
            case 10004:
                dialogMakeSimpleDialog = makeSimpleDialog(activity, "[TOY] RESULT_APP_MISCONFIGURED");
                break;
            default:
                dialogMakeSimpleDialog = GoogleApiAvailability.getInstance().getErrorDialog(activity, GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity), i, null);
                if (dialogMakeSimpleDialog == null) {
                    Log.e("BaseGamesUtils", "No standard error dialog available. Making fallback dialog.");
                    dialogMakeSimpleDialog = makeSimpleDialog(activity, str);
                }
                break;
        }
        dialogMakeSimpleDialog.show();
    }

    public static void showAlert(Activity activity, String str) {
        new AlertDialog.Builder(activity).setMessage(str).setNeutralButton(R.string.ok, (DialogInterface.OnClickListener) null).create().show();
    }

    public static boolean verifySampleSetup(Activity activity, int... iArr) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        sb.append("The following set up problems were found:\n\n");
        if (activity.getPackageName().startsWith("com.google.example.games")) {
            z = true;
            sb.append("- Package name cannot be com.google.*. You need to change the sample's package name to your own package.").append("\n");
        }
        for (int i : iArr) {
            if (activity.getString(i).toLowerCase().contains("replaceme")) {
                z = true;
                sb.append("- You must replace all placeholder IDs in the ids.xml file by your project's IDs.").append("\n");
                break;
            }
        }
        if (!z) {
            return true;
        }
        sb.append("\n\nThese problems may prevent the app from working properly.");
        showAlert(activity, sb.toString());
        return false;
    }
}
