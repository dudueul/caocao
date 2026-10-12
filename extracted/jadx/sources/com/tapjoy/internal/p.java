package com.tapjoy.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import java.sql.Timestamp;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class p extends q {
    public final Context a;
    public final r b;
    private boolean c = false;

    public p(Context context, r rVar) {
        this.a = context.getApplicationContext();
        this.b = rVar;
    }

    private static void b(Context context, Intent intent) {
        intent.putExtra("app", PendingIntent.getBroadcast(context, 0, new Intent(), 0));
    }

    private boolean b() {
        boolean zE = this.b.e(this.a);
        if (zE) {
            long jF = this.b.f(this.a);
            if (jF != 0 && System.currentTimeMillis() > jF) {
                new Object[1][0] = new Timestamp(jF);
                return false;
            }
        }
        return zE;
    }

    private static boolean b(Context context, String str) {
        try {
            int iB = z.b(context.getPackageManager(), "com.google.android.gms");
            if (iB < 3159130) {
                new Object[1][0] = Integer.valueOf(iB);
                return false;
            }
            Object[] objArr = {Integer.valueOf(iB), str};
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            b(context, intent);
            intent.putExtra("sender", str);
            return context.startService(intent) != null;
        } catch (RuntimeException e) {
        }
    }

    private static boolean c(Context context, String str) {
        try {
            Object[] objArr = {Integer.valueOf(z.b(context.getPackageManager(), "com.google.android.gsf")), str};
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gsf");
            b(context, intent);
            intent.putExtra("sender", str);
            return context.startService(intent) != null;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private void e(String str) {
        this.b.a(this.a, str);
        this.b.a(this.a, true);
        if ((this.c || !b(this.a, str)) && c(this.a, str)) {
        }
    }

    private synchronized void f(String str) {
        a(this.a);
        this.b.a(this.a, false);
        this.b.a(this.a, z.a(this.a));
        if (!str.equals(this.b.b(this.a))) {
            new Object[1][0] = str;
            this.b.b(this.a, false);
            this.b.b(this.a, str);
            a(this.a, str);
        } else if (b()) {
            new Object[1][0] = str;
        } else {
            new Object[1][0] = str;
            a(this.a, str);
        }
    }

    protected final void a(Context context) {
        this.b.b(context, 3000);
    }

    protected final void a(String str) {
        String strB = this.b.b(this.a);
        if (strB.length() == 0) {
            e(str);
            return;
        }
        String strA = this.b.a(this.a);
        if (!str.equals(strA)) {
            Object[] objArr = {strA, str, strB};
            e(str);
            return;
        }
        int iD = this.b.d(this.a);
        int iA = z.a(this.a);
        if (iD != Integer.MIN_VALUE && iD != iA) {
            Object[] objArr2 = {Integer.valueOf(iD), Integer.valueOf(iA), strB};
            e(str);
        } else if (this.b.c(this.a)) {
            new Object[1][0] = strB;
            e(str);
        } else if (b()) {
            Object[] objArr3 = {str, strB};
        } else {
            Object[] objArr4 = {str, strB};
            a(this.a, strB);
        }
    }

    public final boolean a() {
        String strA = this.b.a(this.a);
        if (jq.c(strA)) {
            return false;
        }
        a(strA);
        return true;
    }

    public final boolean a(Intent intent) {
        boolean zD;
        String action = intent.getAction();
        if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            if (!"com.google.android.c2dm.intent.RECEIVE".equals(action)) {
                if (!"com.google.android.gcm.intent.RETRY".equals(action)) {
                    new Object[1][0] = action;
                    return false;
                }
                String str = intent.getPackage();
                if (str == null || !str.equals(this.a.getPackageName())) {
                    new Object[1][0] = str;
                    return false;
                }
                a();
                return true;
            }
            String stringExtra = intent.getStringExtra("message_type");
            if (stringExtra == null) {
                return a(this.a, intent);
            }
            if (!"deleted_messages".equals(stringExtra)) {
                new Object[1][0] = stringExtra;
                return false;
            }
            String stringExtra2 = intent.getStringExtra("total_deleted");
            if (stringExtra2 == null) {
                return false;
            }
            try {
                int i = Integer.parseInt(stringExtra2);
                new Object[1][0] = Integer.valueOf(i);
                a(i);
                return false;
            } catch (NumberFormatException e) {
                new Object[1][0] = stringExtra2;
                return false;
            }
        }
        String stringExtra3 = intent.getStringExtra("registration_id");
        String stringExtra4 = intent.getStringExtra("unregistered");
        String stringExtra5 = intent.getStringExtra("error");
        if (stringExtra3 != null) {
            if (stringExtra3.length() > 0) {
                if (stringExtra3.startsWith("|")) {
                    return false;
                }
                f(stringExtra3);
                return false;
            }
            if (stringExtra4 == null && stringExtra5 == null && !this.c) {
                this.c = true;
                a();
                return true;
            }
        }
        if (stringExtra4 != null) {
            String strB = this.b.b(this.a);
            a(this.a);
            this.b.b(this.a, false);
            this.b.b(this.a, "");
            this.b.a(this.a, true);
            b(strB);
            return false;
        }
        if (stringExtra5 == null) {
            return false;
        }
        if (!"SERVICE_NOT_AVAILABLE".equals(stringExtra5)) {
            if (this.c) {
                zD = d(stringExtra5);
            } else {
                c(stringExtra5);
                this.c = true;
                a();
            }
            return true;
        }
        zD = c(stringExtra5);
        if (zD) {
            int iG = this.b.g(this.a);
            new Object[1][0] = Integer.valueOf(iG);
            Intent intent2 = new Intent("com.google.android.gcm.intent.RETRY");
            intent2.setPackage(this.a.getPackageName());
            ((AlarmManager) this.a.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(3, SystemClock.elapsedRealtime() + ((long) iG), PendingIntent.getBroadcast(this.a, 0, intent2, 0));
            if (iG < 3600000) {
                this.b.b(this.a, iG * 2);
            }
        }
        return true;
    }
}
