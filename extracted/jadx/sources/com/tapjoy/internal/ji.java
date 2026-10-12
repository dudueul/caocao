package com.tapjoy.internal;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.app.NotificationCompatExtras;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ji {

    public static class a {
        final Bundle a;
        final jl[] b;
        final jl[] c;
        boolean d;
        public int e;
        public CharSequence f;
        public PendingIntent g;
    }

    public static class b extends d {
        private CharSequence e;

        public final b a(CharSequence charSequence) {
            this.b = c.d(charSequence);
            return this;
        }

        @Override // com.tapjoy.internal.ji.d
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final void a(jh jhVar) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigTextStyle bigTextStyleBigText = new Notification.BigTextStyle(jhVar.a()).setBigContentTitle(this.b).bigText(this.e);
                if (this.d) {
                    bigTextStyleBigText.setSummaryText(this.c);
                }
            }
        }

        public final b b(CharSequence charSequence) {
            this.e = c.d(charSequence);
            return this;
        }
    }

    public static class c {
        Bundle A;
        Notification D;
        RemoteViews E;
        RemoteViews F;
        RemoteViews G;
        String H;
        String J;
        long K;

        @Deprecated
        public ArrayList N;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Context a;
        CharSequence c;
        CharSequence d;
        PendingIntent e;
        PendingIntent f;
        RemoteViews g;
        Bitmap h;
        CharSequence i;
        int j;
        int k;
        boolean m;
        d n;
        CharSequence o;
        CharSequence[] p;
        int q;
        int r;
        boolean s;
        String t;
        boolean u;
        String v;
        boolean x;
        boolean y;
        String z;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ArrayList b = new ArrayList();
        boolean l = true;
        boolean w = false;
        int B = 0;
        int C = 0;
        int I = 0;
        int L = 0;
        Notification M = new Notification();

        public c(@NonNull Context context, @NonNull String str) {
            this.a = context;
            this.H = str;
            this.M.when = System.currentTimeMillis();
            this.M.audioStreamType = -1;
            this.k = 0;
            this.N = new ArrayList();
        }

        protected static CharSequence d(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public final c a() {
            this.M.flags |= 16;
            return this;
        }

        public final c a(int i) {
            this.M.icon = i;
            return this;
        }

        public final c a(PendingIntent pendingIntent) {
            this.e = pendingIntent;
            return this;
        }

        public final c a(Bitmap bitmap) {
            this.h = bitmap;
            return this;
        }

        public final c a(d dVar) {
            if (this.n != dVar) {
                this.n = dVar;
                if (this.n != null) {
                    this.n.a(this);
                }
            }
            return this;
        }

        public final c a(CharSequence charSequence) {
            this.c = d(charSequence);
            return this;
        }

        public final c b() {
            this.M.defaults = 1;
            return this;
        }

        public final c b(CharSequence charSequence) {
            this.d = d(charSequence);
            return this;
        }

        public final c c() {
            this.k = 0;
            return this;
        }

        public final c c(CharSequence charSequence) {
            this.M.tickerText = d(charSequence);
            return this;
        }

        public final Notification d() {
            Notification notification;
            jj jjVar = new jj(this);
            d dVar = jjVar.b.n;
            if (dVar != null) {
                dVar.a(jjVar);
            }
            if (Build.VERSION.SDK_INT >= 26) {
                notification = jjVar.a.build();
            } else if (Build.VERSION.SDK_INT >= 24) {
                notification = jjVar.a.build();
                if (jjVar.g != 0) {
                    if (notification.getGroup() != null && (notification.flags & 512) != 0 && jjVar.g == 2) {
                        jj.a(notification);
                    }
                    if (notification.getGroup() != null && (notification.flags & 512) == 0 && jjVar.g == 1) {
                        jj.a(notification);
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 21) {
                jjVar.a.setExtras(jjVar.f);
                notification = jjVar.a.build();
                if (jjVar.c != null) {
                    notification.contentView = jjVar.c;
                }
                if (jjVar.d != null) {
                    notification.bigContentView = jjVar.d;
                }
                if (jjVar.h != null) {
                    notification.headsUpContentView = jjVar.h;
                }
                if (jjVar.g != 0) {
                    if (notification.getGroup() != null && (notification.flags & 512) != 0 && jjVar.g == 2) {
                        jj.a(notification);
                    }
                    if (notification.getGroup() != null && (notification.flags & 512) == 0 && jjVar.g == 1) {
                        jj.a(notification);
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 20) {
                jjVar.a.setExtras(jjVar.f);
                notification = jjVar.a.build();
                if (jjVar.c != null) {
                    notification.contentView = jjVar.c;
                }
                if (jjVar.d != null) {
                    notification.bigContentView = jjVar.d;
                }
                if (jjVar.g != 0) {
                    if (notification.getGroup() != null && (notification.flags & 512) != 0 && jjVar.g == 2) {
                        jj.a(notification);
                    }
                    if (notification.getGroup() != null && (notification.flags & 512) == 0 && jjVar.g == 1) {
                        jj.a(notification);
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 19) {
                SparseArray<? extends Parcelable> sparseArrayA = jk.a(jjVar.e);
                if (sparseArrayA != null) {
                    jjVar.f.putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, sparseArrayA);
                }
                jjVar.a.setExtras(jjVar.f);
                notification = jjVar.a.build();
                if (jjVar.c != null) {
                    notification.contentView = jjVar.c;
                }
                if (jjVar.d != null) {
                    notification.bigContentView = jjVar.d;
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                Notification notificationBuild = jjVar.a.build();
                Bundle bundleA = ji.a(notificationBuild);
                Bundle bundle = new Bundle(jjVar.f);
                for (String str : jjVar.f.keySet()) {
                    if (bundleA.containsKey(str)) {
                        bundle.remove(str);
                    }
                }
                bundleA.putAll(bundle);
                SparseArray<? extends Parcelable> sparseArrayA2 = jk.a(jjVar.e);
                if (sparseArrayA2 != null) {
                    ji.a(notificationBuild).putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, sparseArrayA2);
                }
                if (jjVar.c != null) {
                    notificationBuild.contentView = jjVar.c;
                }
                if (jjVar.d != null) {
                    notificationBuild.bigContentView = jjVar.d;
                }
                notification = notificationBuild;
            } else {
                notification = jjVar.a.getNotification();
            }
            if (jjVar.b.E != null) {
                notification.contentView = jjVar.b.E;
            }
            int i = Build.VERSION.SDK_INT;
            int i2 = Build.VERSION.SDK_INT;
            if (Build.VERSION.SDK_INT >= 16 && dVar != null) {
                ji.a(notification);
            }
            return notification;
        }
    }

    public static abstract class d {

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        protected c a;
        CharSequence b;
        CharSequence c;
        boolean d = false;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void a(jh jhVar) {
        }

        public final void a(c cVar) {
            if (this.a != cVar) {
                this.a = cVar;
                if (this.a != null) {
                    this.a.a(this);
                }
            }
        }
    }

    public static Bundle a(Notification notification) {
        if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return jk.a(notification);
        }
        return null;
    }
}
