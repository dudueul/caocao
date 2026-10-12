package com.naver.glink.android.sdk.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: PrefUtils.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class p {
    public static final String a = "pref_widget_first_position_isleft";
    public static final String b = "pref_widget_first_position_percent";
    private static final String c = "pref_plug_uuid";
    private static final String d = "pref_game_user_id";
    private static final String e = "pref_synced_game_user_id";
    private static final String f = "pref_transparentable";
    private static final String g = "pref_widget_show";
    private static final String h = "pref_widget_position_x";
    private static final String i = "pref_widget_position_y";
    private static final String j = "pref_use_record";
    private static final String k = "pref_use_screenshot";
    private static final String l = "pref_last_new_article";
    private static final String m = "pref_channel_changeable";
    private static final String n = "pref_dk";
    private static final String o = "pref_uk";
    private static final b p;
    private static final b q = new b(100) { // from class: com.naver.glink.android.sdk.a.p.2
        @Override // com.naver.glink.android.sdk.a.p.b
        String a() {
            return p.d("pref_closed_notice_ids");
        }
    };
    private static final b r;

    /* JADX INFO: compiled from: PrefUtils.java */
    public static class a {
        public final String a;

        public a(String str) {
            this.a = str;
        }

        public boolean a(Context context) {
            return TextUtils.isEmpty(this.a) || TextUtils.equals(this.a, p.s(context));
        }
    }

    /* JADX INFO: compiled from: PrefUtils.java */
    private static abstract class b {
        private final int a;

        b(int i) {
            this.a = i;
        }

        abstract String a();

        List<String> a(Context context) {
            return w.b((List<String>) Arrays.asList(TextUtils.split(p.r(context).getString(a(), ""), ",")));
        }

        void a(Context context, String str) {
            ArrayList arrayList;
            ArrayList arrayList2 = new ArrayList(a(context));
            arrayList2.remove(str);
            arrayList2.add(0, str);
            if (arrayList2.size() > this.a) {
                arrayList = new ArrayList(arrayList2.subList(0, this.a));
                arrayList2.clear();
            } else {
                arrayList = arrayList2;
            }
            p.r(context).edit().putString(a(), TextUtils.join(",", w.a(arrayList))).apply();
        }

        void a(Context context, List<String> list) {
            if (list.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList(a(context));
            arrayList.removeAll(list);
            p.r(context).edit().putString(a(), TextUtils.join(",", w.a(arrayList))).apply();
        }
    }

    static {
        int i2 = 20;
        p = new b(i2) { // from class: com.naver.glink.android.sdk.a.p.1
            @Override // com.naver.glink.android.sdk.a.p.b
            String a() {
                return p.c("pref_query_histories");
            }
        };
        r = new b(i2) { // from class: com.naver.glink.android.sdk.a.p.3
            @Override // com.naver.glink.android.sdk.a.p.b
            String a() {
                return p.d("pref_recently_menu_ids");
            }
        };
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        String string = r(context).getString(c, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String string2 = UUID.randomUUID().toString();
        r(context).edit().putString(c, string2).apply();
        return string2;
    }

    private static List<String> a(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next().intValue()));
        }
        return arrayList;
    }

    public static void a(Context context, int i2) {
        q.a(context, String.valueOf(i2));
    }

    public static void a(Context context, int i2, int i3) {
        r(context).edit().putInt(h, i2).putInt(i, i3).apply();
    }

    public static void a(Context context, long j2) {
        r(context).edit().putLong(d(l), j2).apply();
    }

    public static void a(Context context, a aVar) {
        r(context).edit().putString(d, aVar == null ? null : aVar.a).apply();
    }

    public static void a(Context context, String str) {
        p.a(context, str);
    }

    public static void a(Context context, List<Integer> list) {
        r.a(context, a(list));
    }

    public static void a(Context context, boolean z) {
        r(context).edit().putBoolean(f, z).apply();
    }

    public static void a(Context context, boolean z, int i2) {
        r(context).edit().putBoolean(a, z).putInt(b, i2).apply();
    }

    public static List<String> b(Context context) {
        return p.a(context);
    }

    private static List<Integer> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(Integer.parseInt(it.next())));
        }
        return arrayList;
    }

    public static void b(Context context, int i2) {
        r.a(context, String.valueOf(i2));
    }

    public static void b(Context context, String str) {
        p.a(context, Collections.singletonList(str));
    }

    public static void b(Context context, boolean z) {
        r(context).edit().putBoolean(m, z).apply();
    }

    public static a c(Context context) {
        return new a(r(context).getString(d, ""));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str) {
        return com.naver.glink.android.sdk.d.b().f() ? str + ":plug:" + com.naver.glink.android.sdk.d.b().e.a : str + ":cafe:" + com.naver.glink.android.sdk.d.b().d.a;
    }

    public static void c(Context context, String str) {
        if (context == null) {
            return;
        }
        r(context).edit().putString(e, str).apply();
    }

    public static void c(Context context, boolean z) {
        r(context).edit().putBoolean(g, z).apply();
    }

    public static String d(Context context) {
        return r(context).getString(o, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(String str) {
        return c(str) + ":" + com.naver.glink.android.sdk.api.requests.a.b();
    }

    public static void d(Context context, String str) {
        if (context == null) {
            return;
        }
        r(context).edit().putString(o, str).apply();
    }

    public static void d(Context context, boolean z) {
        r(context).edit().putBoolean(j, z).apply();
    }

    public static String e(Context context) {
        return r(context).getString(n, "");
    }

    public static void e(Context context, String str) {
        if (context == null) {
            return;
        }
        r(context).edit().putString(n, str).apply();
    }

    public static void e(Context context, boolean z) {
        r(context).edit().putBoolean(k, z).apply();
    }

    public static boolean f(Context context) {
        return r(context).getBoolean(f, true);
    }

    public static boolean g(Context context) {
        return r(context).getBoolean(m, true);
    }

    public static boolean h(Context context) {
        return r(context).getBoolean(g, true);
    }

    public static boolean i(Context context) {
        return r(context).getBoolean(j, true);
    }

    public static boolean j(Context context) {
        return r(context).getBoolean(k, true);
    }

    public static long k(Context context) {
        return r(context).getLong(d(l), 0L);
    }

    public static Point l(Context context) {
        Point point = new Point();
        point.x = r(context).getInt(h, -1);
        point.y = r(context).getInt(i, -1);
        return point;
    }

    public static Bundle m(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(a, r(context).getBoolean(a, true));
        bundle.putInt(b, r(context).getInt(b, -1));
        return bundle;
    }

    public static List<Integer> n(Context context) {
        return b(q.a(context));
    }

    public static List<Integer> o(Context context) {
        return b(r.a(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SharedPreferences r(Context context) {
        return context.getSharedPreferences("cafeSdk", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String s(Context context) {
        if (context == null) {
            return null;
        }
        return r(context).getString(e, null);
    }
}
