package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzb {
    private static void zzc(Context context, String str, Intent intent) {
        Bundle bundle = new Bundle();
        String stringExtra = intent.getStringExtra("google.c.a.c_id");
        if (stringExtra != null) {
            bundle.putString("_nmid", stringExtra);
        }
        String stringExtra2 = intent.getStringExtra("google.c.a.c_l");
        if (stringExtra2 != null) {
            bundle.putString("_nmn", stringExtra2);
        }
        String stringExtra3 = intent.getStringExtra("google.c.a.m_l");
        if (!TextUtils.isEmpty(stringExtra3)) {
            bundle.putString("label", stringExtra3);
        }
        String stringExtra4 = intent.getStringExtra("from");
        if (stringExtra4 == null || !stringExtra4.startsWith("/topics/")) {
            stringExtra4 = null;
        }
        if (stringExtra4 != null) {
            bundle.putString("_nt", stringExtra4);
        }
        try {
            bundle.putInt("_nmt", Integer.parseInt(intent.getStringExtra("google.c.a.ts")));
        } catch (NumberFormatException e) {
            Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", e);
        }
        if (intent.hasExtra("google.c.a.udt")) {
            try {
                bundle.putInt("_ndt", Integer.parseInt(intent.getStringExtra("google.c.a.udt")));
            } catch (NumberFormatException e2) {
                Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", e2);
            }
        }
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            String strValueOf = String.valueOf(bundle);
            Log.d("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 22 + String.valueOf(strValueOf).length()).append("Sending event=").append(str).append(" params=").append(strValueOf).toString());
        }
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (analyticsConnector != null) {
            analyticsConnector.logEvent("fcm", str, bundle);
        } else {
            Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
        }
    }

    public static void zze(Context context, Intent intent) {
        zzc(context, "_nr", intent);
    }

    public static void zzf(Context context, Intent intent) {
        if (intent != null) {
            if ("1".equals(intent.getStringExtra("google.c.a.tc"))) {
                AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
                if (Log.isLoggable("FirebaseMessaging", 3)) {
                    Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
                }
                if (analyticsConnector != null) {
                    String stringExtra = intent.getStringExtra("google.c.a.c_id");
                    analyticsConnector.setUserProperty("fcm", "_ln", stringExtra);
                    Bundle bundle = new Bundle();
                    bundle.putString(ShareConstants.FEED_SOURCE_PARAM, "Firebase");
                    bundle.putString(Constants.MEDIUM, "notification");
                    bundle.putString("campaign", stringExtra);
                    analyticsConnector.logEvent("fcm", "_cmp", bundle);
                } else {
                    Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
                }
            } else if (Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
            }
        }
        zzc(context, "_no", intent);
    }

    public static void zzg(Context context, Intent intent) {
        zzc(context, "_nd", intent);
    }

    public static void zzh(Context context, Intent intent) {
        zzc(context, "_nf", intent);
    }
}
