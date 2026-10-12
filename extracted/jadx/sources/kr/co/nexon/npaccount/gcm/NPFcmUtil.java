package kr.co.nexon.npaccount.gcm;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.util.NXStringUtil;
import java.io.IOException;
import kr.co.nexon.android.sns.google.NPGoogleSignIn;
import kr.co.nexon.mdev.android.util.NXPackageUtil;
import kr.co.nexon.npaccount.listener.NPGCMListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPFcmUtil {
    private final String PROPERTY_REG_TOKEN = "registration_id";
    private final String PROPERTY_APP_VERSION = "appVersion";

    private SharedPreferences getFcmPreferences(Context context) {
        return context.getSharedPreferences("NPAccountGCMPref", 0);
    }

    public String getRegistrationToken(Context context) {
        SharedPreferences fcmPreferences = getFcmPreferences(context);
        String string = fcmPreferences.getString("registration_id", "");
        if (NXStringUtil.isNull(string)) {
            ToyLog.d("Registration Token is empty.");
            return "";
        }
        if (fcmPreferences.getInt("appVersion", Integer.MIN_VALUE) == NXPackageUtil.getAppVersionCode(context)) {
            return string;
        }
        ToyLog.d("App version changed.");
        return "";
    }

    public int getValidNotificationCount(NotificationManager notificationManager, int i) {
        StatusBarNotification[] activeNotifications;
        if (Build.VERSION.SDK_INT <= 23 || (activeNotifications = notificationManager.getActiveNotifications()) == null) {
            return -1;
        }
        int length = activeNotifications.length;
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            if (statusBarNotification.getId() == i) {
                return length - 1;
            }
        }
        return length;
    }

    public boolean initializeFCM(Activity activity, final NPGCMListener nPGCMListener, boolean z, final String str) {
        int iIsAvailable = NPGoogleSignIn.isAvailable(activity, false);
        if (iIsAvailable == 1) {
            try {
                activity.getPackageManager().getApplicationInfo("com.android.vending", 0);
            } catch (PackageManager.NameNotFoundException e) {
                ToyLog.d("Google init fail, Android market is not installed.");
                if (nPGCMListener == null) {
                    return false;
                }
                nPGCMListener.onResult(1);
                return false;
            }
        }
        if (iIsAvailable != 0) {
            ToyLog.d("Google sign-in not available.");
            NPGoogleSignIn.isAvailable(activity, z ? false : true);
            if (nPGCMListener == null) {
                return false;
            }
            nPGCMListener.onResult(iIsAvailable);
            return false;
        }
        final Context applicationContext = activity.getApplicationContext();
        if (!NXStringUtil.isNotNull(getRegistrationToken(applicationContext))) {
            new Thread(new Runnable() { // from class: kr.co.nexon.npaccount.gcm.NPFcmUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ToyLog.d("Try to make registration Token");
                        String token = FirebaseInstanceId.getInstance().getToken(str, FirebaseMessaging.INSTANCE_ID_SCOPE);
                        NPFcmUtil.this.storeRegistrationToken(applicationContext, token);
                        ToyLog.d("Success to make registration token", "RegistrationToken", token);
                    } catch (IOException e2) {
                        ToyLog.d("Fail to make registration Token, Error :" + e2.getMessage());
                    } catch (IllegalStateException e3) {
                        ToyLog.e(e3.toString());
                    }
                    if (nPGCMListener != null) {
                        nPGCMListener.onResult(NXToyErrorCode.SUCCESS.getCode());
                    }
                }
            }).start();
            return true;
        }
        if (nPGCMListener != null) {
            nPGCMListener.onResult(0);
        }
        return true;
    }

    public void storeRegistrationToken(Context context, String str) {
        int appVersionCode = NXPackageUtil.getAppVersionCode(context);
        ToyLog.d("Store registrationToken, app version " + appVersionCode);
        SharedPreferences.Editor editorEdit = getFcmPreferences(context).edit();
        editorEdit.putString("registration_id", str);
        editorEdit.putInt("appVersion", appVersionCode);
        editorEdit.commit();
    }
}
