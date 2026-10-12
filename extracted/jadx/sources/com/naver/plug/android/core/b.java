package com.naver.plug.android.core;

import android.content.Context;

/* JADX INFO: compiled from: CoreDelegate.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static a a;

    /* JADX INFO: compiled from: CoreDelegate.java */
    public interface a {
        String a();

        void a(Context context);

        void a(Context context, String str);

        void a(Context context, String str, InterfaceC0067b interfaceC0067b);

        void a(String str);

        String b();

        void b(String str);

        void c();
    }

    /* JADX INFO: renamed from: com.naver.plug.android.core.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CoreDelegate.java */
    public interface InterfaceC0067b {
        void onLoggedIn(boolean z);
    }

    public static String a() {
        return a.a();
    }

    public static void a(Context context) {
        a.a(context);
    }

    public static void a(Context context, String str) {
        a.a(context, str);
    }

    public static void a(Context context, String str, InterfaceC0067b interfaceC0067b) {
        a.a(context, str, interfaceC0067b);
    }

    public static void a(a aVar) {
        a = aVar;
    }

    public static void a(String str) {
        a.a(str);
    }

    public static String b() {
        return a.b();
    }

    public static void b(String str) {
        a.b(str);
    }

    public static void c() {
        a.c();
    }
}
