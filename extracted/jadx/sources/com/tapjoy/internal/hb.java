package com.tapjoy.internal;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyReceiver;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hb extends p {
    private static hb c;

    private hb(Context context) {
        super(context, new r() { // from class: com.tapjoy.internal.hb.1
            @Override // com.tapjoy.internal.r
            public final String a(Context context2) {
                return hj.a(context2).b.getString("gcm.senderIds", "");
            }

            @Override // com.tapjoy.internal.r
            public final void a(Context context2, int i) {
                n.a(hj.a(context2).b, "gcm.appVersion", i);
            }

            @Override // com.tapjoy.internal.r
            public final void a(Context context2, long j) {
                SharedPreferences.Editor editorEdit = hj.a(context2).b.edit();
                editorEdit.putLong("gcm.onServerExpirationTime", j);
                editorEdit.apply();
            }

            @Override // com.tapjoy.internal.r
            public final void a(Context context2, String str) {
                n.a(hj.a(context2).b, "gcm.senderIds", str);
            }

            @Override // com.tapjoy.internal.r
            public final void a(Context context2, boolean z) {
                n.a(hj.a(context2).b, "gcm.stale", z);
            }

            @Override // com.tapjoy.internal.r
            public final String b(Context context2) {
                return hj.a(context2).b.getString("gcm.regId", "");
            }

            @Override // com.tapjoy.internal.r
            public final void b(Context context2, int i) {
                n.a(hj.a(context2).b, "gcm.backoff", i);
            }

            @Override // com.tapjoy.internal.r
            public final void b(Context context2, String str) {
                n.a(hj.a(context2).b, "gcm.regId", str);
            }

            @Override // com.tapjoy.internal.r
            public final void b(Context context2, boolean z) {
                hj.a(context2).a(z);
            }

            @Override // com.tapjoy.internal.r
            public final boolean c(Context context2) {
                return hj.a(context2).b.getBoolean("gcm.stale", true);
            }

            @Override // com.tapjoy.internal.r
            public final int d(Context context2) {
                return hj.a(context2).b.getInt("gcm.appVersion", Integer.MIN_VALUE);
            }

            @Override // com.tapjoy.internal.r
            public final boolean e(Context context2) {
                return hj.a(context2).b.getBoolean("gcm.onServer", false);
            }

            @Override // com.tapjoy.internal.r
            public final long f(Context context2) {
                return hj.a(context2).b.getLong("gcm.onServerExpirationTime", 0L);
            }

            @Override // com.tapjoy.internal.r
            public final int g(Context context2) {
                return hj.a(context2).b.getInt("gcm.backoff", 0);
            }
        });
    }

    private static int a(Bundle bundle, String str, Context context) {
        if (bundle != null) {
            Object obj = bundle.get(str);
            if (obj instanceof Integer) {
                int iIntValue = ((Integer) obj).intValue();
                try {
                    if ("drawable".equals(context.getResources().getResourceTypeName(iIntValue))) {
                        return iIntValue;
                    }
                } catch (Resources.NotFoundException e) {
                }
            }
            if (obj != null) {
                Object[] objArr = {str};
                if (gw.a) {
                    x.a(4, "Tapjoy", "meta-data of {} invalid", objArr);
                }
            }
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r12v2, types: [android.text.Spanned] */
    /* JADX WARN: Type inference failed for: r1v16, types: [com.tapjoy.internal.ji$c] */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.tapjoy.internal.ji$b] */
    @Nullable
    public static Notification a(Context context, String str, String str2, String str3, boolean z, boolean z2, String str4, String str5, int i, String str6) {
        Spanned spannedFromHtml;
        int i2;
        CharSequence applicationLabel;
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) TapjoyReceiver.class);
        intent.setAction("com.tapjoy.PUSH_CLICK");
        intent.putExtra("com.tapjoy.PUSH_ID", str);
        if (str4 != null) {
            intent.putExtra(Tapjoy.INTENT_EXTRA_PUSH_PAYLOAD, str4);
        }
        if (str5 != null) {
            intent.putExtra("com.tapjoy.PUSH_PLACEMENT", str5);
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(context.getApplicationContext(), i, intent, Build.VERSION.SDK_INT == 19 ? 268435456 : 134217728);
        if (broadcast == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            int length = str2.length();
            CharSequence charSequence = str2;
            if (length == 0) {
                applicationLabel = packageManager.getApplicationLabel(applicationInfo);
            } else if (z) {
                spannedFromHtml = Html.fromHtml(str2);
            }
            if (z) {
                charSequence = spannedFromHtml;
                charSequence = applicationLabel;
                str3 = Html.fromHtml(str3);
            }
            charSequence = spannedFromHtml;
            charSequence = applicationLabel;
            int iA = a(applicationInfo.metaData, "com.tapjoy.notification.icon", context);
            if (iA == 0) {
                i2 = applicationInfo.icon != 0 ? applicationInfo.icon : R.drawable.sym_def_app_icon;
            } else {
                i2 = iA;
            }
            int iA2 = a(applicationInfo.metaData, "com.tapjoy.notification.icon.large", context);
            Bitmap bitmapDecodeResource = iA2 != 0 ? BitmapFactory.decodeResource(context.getResources(), iA2) : null;
            if (str6 == null && applicationInfo.metaData != null) {
                str6 = applicationInfo.metaData.getString("com.tapjoy.notification.default_channel_id", "tapjoy");
            }
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (str6 == null || notificationManager.getNotificationChannel(str6) == null) {
                    notificationManager.createNotificationChannel(new NotificationChannel("tapjoy", "Tapjoy", 3));
                    str6 = "tapjoy";
                }
            } else {
                str6 = null;
            }
            ji.c cVarA = new ji.c(context, str6).a(i2).c(charSequence).a(charSequence).b(str3).a(broadcast).a().c().a(new ji.b().a(charSequence).b(str3));
            if (z2) {
                cVarA.b();
            }
            if (bitmapDecodeResource != null) {
                cVarA.a(bitmapDecodeResource);
            }
            return cVarA.d();
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static void a(Context context, int i, Notification notification) {
        ((NotificationManager) context.getSystemService("notification")).notify(i, notification);
    }

    public static boolean a(Object obj) {
        return Boolean.TRUE.equals(obj) || "true".equals(obj);
    }

    public static int b(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    public static synchronized hb b(Context context) {
        if (c == null) {
            c = new hb(context);
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.q
    public final void a(int i) {
        new Object[1][0] = Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.q
    public final void a(Context context, String str) {
        new Object[1][0] = str;
        gz.a(context).a(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.q
    public final boolean a(Context context, Intent intent) {
        Object[] objArr = {intent, intent.getExtras()};
        String stringExtra = intent.getStringExtra("fiverocks");
        if (stringExtra == null) {
            return false;
        }
        if (hc.a(context).f()) {
            gz.a(context).b(stringExtra);
            return true;
        }
        String stringExtra2 = intent.getStringExtra("title");
        String stringExtra3 = intent.getStringExtra("message");
        if (stringExtra3 != null) {
            Bundle extras = intent.getExtras();
            Object obj = extras.get("rich");
            Object obj2 = extras.get("sound");
            String string = extras.getString(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
            Object obj3 = extras.get("always");
            boolean z = "true".equals(obj3) || Boolean.TRUE.equals(obj3);
            Object obj4 = extras.get("repeatable");
            boolean z2 = "true".equals(obj4) || Boolean.TRUE.equals(obj4);
            String string2 = extras.getString("placement");
            int iB = b(extras.get("nid"));
            String string3 = extras.getString("channel_id");
            if (z || !gz.a(context).d()) {
                Notification notificationA = a(context, stringExtra, jq.a(stringExtra2), stringExtra3, a(obj), a(obj2), string, string2, iB, string3);
                if (gz.a(context).a(context, stringExtra, z2)) {
                    a(context, iB, notificationA);
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.q
    public final void b(String str) {
        new Object[1][0] = str;
        a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.q
    public final boolean c(String str) {
        new Object[1][0] = str;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.q
    public final boolean d(String str) {
        new Object[1][0] = str;
        return false;
    }

    final void e(String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        super.a(this.a);
        super.a(new String[]{str}[0]);
    }
}
