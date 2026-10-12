package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.iid.zzat;
import com.squareup.otto.Bus;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zza {
    private static zza zzde;
    private Bundle zzdf;
    private Method zzdg;
    private Method zzdh;
    private final AtomicInteger zzdi = new AtomicInteger((int) SystemClock.elapsedRealtime());
    private final Context zzv;

    private zza(Context context) {
        this.zzv = context.getApplicationContext();
    }

    @TargetApi(26)
    private final Notification zza(CharSequence charSequence, String str, int i, Integer num, Uri uri, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str2) {
        Notification.Builder smallIcon = new Notification.Builder(this.zzv).setAutoCancel(true).setSmallIcon(i);
        if (!TextUtils.isEmpty(charSequence)) {
            smallIcon.setContentTitle(charSequence);
        }
        if (!TextUtils.isEmpty(str)) {
            smallIcon.setContentText(str);
            smallIcon.setStyle(new Notification.BigTextStyle().bigText(str));
        }
        if (num != null) {
            smallIcon.setColor(num.intValue());
        }
        if (uri != null) {
            smallIcon.setSound(uri);
        }
        if (pendingIntent != null) {
            smallIcon.setContentIntent(pendingIntent);
        }
        if (pendingIntent2 != null) {
            smallIcon.setDeleteIntent(pendingIntent2);
        }
        if (str2 != null) {
            if (this.zzdg == null) {
                this.zzdg = zzl("setChannelId");
            }
            if (this.zzdg == null) {
                this.zzdg = zzl("setChannel");
            }
            if (this.zzdg == null) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel");
            } else {
                try {
                    this.zzdg.invoke(smallIcon, str2);
                } catch (IllegalAccessException e) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e);
                } catch (IllegalArgumentException e2) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e2);
                } catch (SecurityException e3) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e3);
                } catch (InvocationTargetException e4) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e4);
                }
            }
        }
        return smallIcon.build();
    }

    static String zza(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private final Bundle zzar() {
        if (this.zzdf != null) {
            return this.zzdf;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.zzv.getPackageManager().getApplicationInfo(this.zzv.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return Bundle.EMPTY;
        }
        this.zzdf = applicationInfo.metaData;
        return this.zzdf;
    }

    static String zzb(Bundle bundle, String str) {
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf("_loc_key");
        return zza(bundle, strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
    }

    @TargetApi(26)
    private final boolean zzb(int i) {
        if (Build.VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(this.zzv.getResources().getDrawable(i, null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            Log.e("FirebaseMessaging", new StringBuilder(77).append("Adaptive icons cannot be used in notifications. Ignoring icon id: ").append(i).toString());
            return false;
        } catch (Resources.NotFoundException e) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static Object[] zzc(Bundle bundle, String str) {
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf("_loc_args");
        String strZza = zza(bundle, strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(strZza);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return strArr;
        } catch (JSONException e) {
            String strValueOf3 = String.valueOf(str);
            String strValueOf4 = String.valueOf("_loc_args");
            String strSubstring = (strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3)).substring(6);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strSubstring).length() + 41 + String.valueOf(strZza).length()).append("Malformed ").append(strSubstring).append(": ").append(strZza).append("  Default value will be used.").toString());
            return null;
        }
    }

    static synchronized zza zzd(Context context) {
        if (zzde == null) {
            zzde = new zza(context);
        }
        return zzde;
    }

    private final String zzd(Bundle bundle, String str) {
        String strZza = zza(bundle, str);
        if (!TextUtils.isEmpty(strZza)) {
            return strZza;
        }
        String strZzb = zzb(bundle, str);
        if (TextUtils.isEmpty(strZzb)) {
            return null;
        }
        Resources resources = this.zzv.getResources();
        int identifier = resources.getIdentifier(strZzb, "string", this.zzv.getPackageName());
        if (identifier == 0) {
            String strValueOf = String.valueOf(str);
            String strValueOf2 = String.valueOf("_loc_key");
            String strSubstring = (strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf)).substring(6);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strSubstring).length() + 49 + String.valueOf(strZzb).length()).append(strSubstring).append(" resource not found: ").append(strZzb).append(" Default value will be used.").toString());
            return null;
        }
        Object[] objArrZzc = zzc(bundle, str);
        if (objArrZzc == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, objArrZzc);
        } catch (MissingFormatArgumentException e) {
            String string = Arrays.toString(objArrZzc);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strZzb).length() + 58 + String.valueOf(string).length()).append("Missing format argument for ").append(strZzb).append(": ").append(string).append(" Default value will be used.").toString(), e);
            return null;
        }
    }

    static boolean zzf(Bundle bundle) {
        return "1".equals(zza(bundle, "gcm.n.e")) || zza(bundle, "gcm.n.icon") != null;
    }

    @Nullable
    static Uri zzg(@NonNull Bundle bundle) {
        String strZza = zza(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(strZza)) {
            strZza = zza(bundle, "gcm.n.link");
        }
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        return Uri.parse(strZza);
    }

    static String zzi(Bundle bundle) {
        String strZza = zza(bundle, "gcm.n.sound2");
        return TextUtils.isEmpty(strZza) ? zza(bundle, "gcm.n.sound") : strZza;
    }

    @TargetApi(26)
    private static Method zzl(String str) {
        try {
            return Notification.Builder.class.getMethod(str, String.class);
        } catch (NoSuchMethodException | SecurityException e) {
            return null;
        }
    }

    private final Integer zzm(String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException e) {
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 54).append("Color ").append(str).append(" not valid. Notification will use default color.").toString());
            }
        }
        int i = zzar().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i == 0) {
            return null;
        }
        try {
            return Integer.valueOf(ContextCompat.getColor(this.zzv, i));
        } catch (Resources.NotFoundException e2) {
            Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            return null;
        }
    }

    @TargetApi(26)
    private final String zzn(String str) {
        if (!PlatformVersion.isAtLeastO()) {
            return null;
        }
        NotificationManager notificationManager = (NotificationManager) this.zzv.getSystemService(NotificationManager.class);
        try {
            if (this.zzdh == null) {
                this.zzdh = notificationManager.getClass().getMethod("getNotificationChannel", String.class);
            }
            if (!TextUtils.isEmpty(str)) {
                if (this.zzdh.invoke(notificationManager, str) != null) {
                    return str;
                }
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 122).append("Notification Channel requested (").append(str).append(") has not been created by the app. Manifest configuration, or default, value will be used.").toString());
            }
            String string = zzar().getString("com.google.firebase.messaging.default_notification_channel_id");
            if (TextUtils.isEmpty(string)) {
                Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
            } else {
                if (this.zzdh.invoke(notificationManager, string) != null) {
                    return string;
                }
                Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
            }
            if (this.zzdh.invoke(notificationManager, "fcm_fallback_notification_channel") == null) {
                Class<?> cls = Class.forName("android.app.NotificationChannel");
                notificationManager.getClass().getMethod("createNotificationChannel", cls).invoke(notificationManager, cls.getConstructor(String.class, CharSequence.class, Integer.TYPE).newInstance("fcm_fallback_notification_channel", this.zzv.getString(R.string.fcm_fallback_notification_channel_label), 3));
            }
            return "fcm_fallback_notification_channel";
        } catch (ClassNotFoundException e) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e);
            return null;
        } catch (IllegalAccessException e2) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e2);
            return null;
        } catch (IllegalArgumentException e3) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e3);
            return null;
        } catch (InstantiationException e4) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e4);
            return null;
        } catch (LinkageError e5) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e5);
            return null;
        } catch (NoSuchMethodException e6) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e6);
            return null;
        } catch (SecurityException e7) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e7);
            return null;
        } catch (InvocationTargetException e8) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e8);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0065 A[EDGE_INSN: B:24:0x0065->B:21:0x005f BREAK  A[LOOP:0: B:14:0x0048->B:112:?]] */
    /* JADX WARN: Code duplicated, block: B:66:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:70:0x01e3  */
    final boolean zzh(Bundle bundle) {
        boolean z;
        int i;
        int identifier;
        Uri defaultUri;
        Intent intent;
        PendingIntent activity;
        PendingIntent pendingIntentZza;
        Notification notificationBuild;
        PendingIntent pendingIntentZza2 = null;
        if ("1".equals(zza(bundle, "gcm.n.noui"))) {
            return true;
        }
        if (((KeyguardManager) this.zzv.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            z = false;
            break;
        }
        if (!PlatformVersion.isAtLeastLollipop()) {
            SystemClock.sleep(10L);
        }
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.zzv.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            z = false;
            break;
        }
        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.pid == iMyPid) {
                if (next.importance != 100) {
                    z = false;
                    break;
                }
                z = true;
                break;
            }
        }
        if (z) {
            return false;
        }
        CharSequence charSequenceZzd = zzd(bundle, "gcm.n.title");
        if (TextUtils.isEmpty(charSequenceZzd)) {
            charSequenceZzd = this.zzv.getApplicationInfo().loadLabel(this.zzv.getPackageManager());
        }
        String strZzd = zzd(bundle, "gcm.n.body");
        String strZza = zza(bundle, "gcm.n.icon");
        if (TextUtils.isEmpty(strZza)) {
            i = zzar().getInt("com.google.firebase.messaging.default_notification_icon", 0);
            if (i != 0 || !zzb(i)) {
                i = this.zzv.getApplicationInfo().icon;
            }
            if (i != 0 || !zzb(i)) {
                i = android.R.drawable.sym_def_app_icon;
            }
            identifier = i;
        } else {
            Resources resources = this.zzv.getResources();
            identifier = resources.getIdentifier(strZza, "drawable", this.zzv.getPackageName());
            if ((identifier == 0 || !zzb(identifier)) && ((identifier = resources.getIdentifier(strZza, "mipmap", this.zzv.getPackageName())) == 0 || !zzb(identifier))) {
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strZza).length() + 61).append("Icon resource ").append(strZza).append(" not found. Notification will use default icon.").toString());
                i = zzar().getInt("com.google.firebase.messaging.default_notification_icon", 0);
                if (i != 0) {
                    i = this.zzv.getApplicationInfo().icon;
                } else {
                    i = this.zzv.getApplicationInfo().icon;
                }
                if (i != 0) {
                    i = android.R.drawable.sym_def_app_icon;
                } else {
                    i = android.R.drawable.sym_def_app_icon;
                }
                identifier = i;
            }
        }
        Integer numZzm = zzm(zza(bundle, "gcm.n.color"));
        String strZzi = zzi(bundle);
        if (TextUtils.isEmpty(strZzi)) {
            defaultUri = null;
        } else if (Bus.DEFAULT_IDENTIFIER.equals(strZzi) || this.zzv.getResources().getIdentifier(strZzi, "raw", this.zzv.getPackageName()) == 0) {
            defaultUri = RingtoneManager.getDefaultUri(2);
        } else {
            String packageName = this.zzv.getPackageName();
            defaultUri = Uri.parse(new StringBuilder(String.valueOf(packageName).length() + 24 + String.valueOf(strZzi).length()).append("android.resource://").append(packageName).append("/raw/").append(strZzi).toString());
        }
        String strZza2 = zza(bundle, "gcm.n.click_action");
        if (TextUtils.isEmpty(strZza2)) {
            Uri uriZzg = zzg(bundle);
            if (uriZzg != null) {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setPackage(this.zzv.getPackageName());
                intent2.setData(uriZzg);
                intent = intent2;
            } else {
                Intent launchIntentForPackage = this.zzv.getPackageManager().getLaunchIntentForPackage(this.zzv.getPackageName());
                if (launchIntentForPackage == null) {
                    Log.w("FirebaseMessaging", "No activity found to launch app");
                }
                intent = launchIntentForPackage;
            }
        } else {
            Intent intent3 = new Intent(strZza2);
            intent3.setPackage(this.zzv.getPackageName());
            intent3.setFlags(268435456);
            intent = intent3;
        }
        if (intent == null) {
            activity = null;
        } else {
            intent.addFlags(67108864);
            Bundle bundle2 = new Bundle(bundle);
            FirebaseMessagingService.zzj(bundle2);
            intent.putExtras(bundle2);
            for (String str : bundle2.keySet()) {
                if (str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                    intent.removeExtra(str);
                }
            }
            activity = PendingIntent.getActivity(this.zzv, this.zzdi.incrementAndGet(), intent, CrashUtils.ErrorDialogData.SUPPRESSED);
        }
        if (FirebaseMessagingService.zzk(bundle)) {
            Intent intent4 = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
            zza(intent4, bundle);
            intent4.putExtra("pending_intent", activity);
            pendingIntentZza = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), intent4, CrashUtils.ErrorDialogData.SUPPRESSED);
            Intent intent5 = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
            zza(intent5, bundle);
            pendingIntentZza2 = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), intent5, CrashUtils.ErrorDialogData.SUPPRESSED);
        } else {
            pendingIntentZza = activity;
        }
        if (!PlatformVersion.isAtLeastO() || this.zzv.getApplicationInfo().targetSdkVersion <= 25) {
            NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(this.zzv).setAutoCancel(true).setSmallIcon(identifier);
            if (!TextUtils.isEmpty(charSequenceZzd)) {
                smallIcon.setContentTitle(charSequenceZzd);
            }
            if (!TextUtils.isEmpty(strZzd)) {
                smallIcon.setContentText(strZzd);
                smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(strZzd));
            }
            if (numZzm != null) {
                smallIcon.setColor(numZzm.intValue());
            }
            if (defaultUri != null) {
                smallIcon.setSound(defaultUri);
            }
            if (pendingIntentZza != null) {
                smallIcon.setContentIntent(pendingIntentZza);
            }
            if (pendingIntentZza2 != null) {
                smallIcon.setDeleteIntent(pendingIntentZza2);
            }
            notificationBuild = smallIcon.build();
        } else {
            notificationBuild = zza(charSequenceZzd, strZzd, identifier, numZzm, defaultUri, pendingIntentZza, pendingIntentZza2, zzn(zza(bundle, "gcm.n.android_channel_id")));
        }
        String strZza3 = zza(bundle, "gcm.n.tag");
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Showing notification");
        }
        NotificationManager notificationManager = (NotificationManager) this.zzv.getSystemService("notification");
        if (TextUtils.isEmpty(strZza3)) {
            strZza3 = new StringBuilder(37).append("FCM-Notification:").append(SystemClock.uptimeMillis()).toString();
        }
        notificationManager.notify(strZza3, 0, notificationBuild);
        return true;
    }
}
