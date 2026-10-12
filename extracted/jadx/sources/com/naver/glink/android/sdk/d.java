package com.naver.glink.android.sdk;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.z;
import com.naver.glink.android.sdk.configure.SdkBuildPhase;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.login.neoid.NeoIdSdkManager;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.tabs.Tab;
import com.naver.glink.android.sdk.ui.write.model.WritingArticle;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestHelper;
import com.naver.plug.android.core.api.request.RequestListener;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: G.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d {
    public static final String a = "plug.sdk.lifecycle";
    private static final l b = l.a(d.class.getSimpleName());
    private static WeakReference<Context> c;
    private static WindowManager.LayoutParams d;
    private static com.naver.glink.android.sdk.configure.a e;
    private static com.naver.glink.android.sdk.configure.d f;
    private static com.naver.glink.android.sdk.configure.c g;
    private static StartFrom h;

    /* JADX INFO: compiled from: G.java */
    public static class a extends com.naver.plug.android.core.a.a {
    }

    public static String a(@StringRes int i) {
        Context contextR = r();
        return contextR == null ? "" : contextR.getResources().getString(i);
    }

    public static String a(@StringRes int i, Object... objArr) {
        Context contextR = r();
        return contextR == null ? "" : contextR.getResources().getString(i, objArr);
    }

    public static void a() {
        com.naver.glink.android.sdk.api.requests.a.e();
        b().a(-1);
    }

    static void a(Context context) {
        b(context, StartTo.a());
    }

    static void a(Context context, int i) {
        b(context, StartTo.a(i));
    }

    static void a(Context context, int i, String str, String str2) {
        b(context, StartTo.a(WritingArticle.a(i, -1).a(str).b(str2).a()));
    }

    public static void a(Context context, int i, String str, String str2, String str3) {
        b(context, StartTo.a(WritingArticle.a(i, -1).a(str).b(str2).c(str3).a()));
    }

    public static void a(Context context, Tab.Type type) {
        b(context, StartTo.a(type));
    }

    static void a(Context context, String str, int i) {
        a(context, str, i, -1);
    }

    static void a(Context context, String str, int i, int i2) {
        k(context);
        m(context);
        e = com.naver.glink.android.sdk.configure.a.a(e).a(context.getString(R.string.plug_sdk_version)).a(SdkBuildPhase.from(context)).a(com.naver.glink.android.sdk.configure.b.a(context.getResources())).b(new com.naver.glink.android.sdk.configure.a.b(i, str, null)).a();
        g = new com.naver.glink.android.sdk.configure.c(context);
        if (i2 != -1) {
            e.a(i2);
        }
        LoginHelper.LoginType.NEO_ID.init(context);
        v();
        j(context);
    }

    public static void a(Context context, String str, String str2, int i) {
        k(context);
        m(context);
        e = com.naver.glink.android.sdk.configure.a.a(e).a(context.getString(R.string.plug_sdk_version)).a(SdkBuildPhase.from(context)).a(com.naver.glink.android.sdk.configure.b.a(context.getResources())).a(new com.naver.glink.android.sdk.configure.a.b(i, str, str2)).a();
        g = new com.naver.glink.android.sdk.configure.c(context);
        LoginHelper.LoginType.NAVER.init(context);
        v();
    }

    public static void a(final Context context, final boolean z) {
        a(new Runnable() { // from class: com.naver.glink.android.sdk.d.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    d.d(context, z);
                } catch (Exception e2) {
                    d.b.a(e2);
                }
            }
        });
    }

    public static void a(WindowManager.LayoutParams layoutParams) {
        d = layoutParams;
    }

    public static void a(StartFrom startFrom) {
        h = startFrom;
    }

    public static void a(com.naver.glink.android.sdk.configure.d dVar) {
        f = dVar;
    }

    public static void a(com.naver.glink.android.sdk.ui.tabs.a aVar) {
        com.naver.glink.android.sdk.ui.tabs.b.a(aVar);
    }

    public static void a(Runnable runnable) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            new Handler(Looper.getMainLooper()).post(runnable);
        } else {
            runnable.run();
        }
    }

    public static boolean a(String str) {
        return (r() == null || ContextCompat.checkSelfPermission(r(), str) == -1) ? false : true;
    }

    public static com.naver.glink.android.sdk.configure.a b() {
        return e == null ? com.naver.glink.android.sdk.configure.a.g() : e;
    }

    static void b(Context context) {
        b(context, StartTo.b());
    }

    static void b(Context context, int i) {
        b(context, StartTo.b(i));
    }

    public static void b(Context context, int i, String str, String str2, String str3) {
        b(context, StartTo.a(WritingArticle.a(i, -1).a(str).b(str2).d(str3).a()));
    }

    private static void b(final Context context, final StartTo startTo) {
        if (z.a(context)) {
            m(context);
            a(new Runnable() { // from class: com.naver.glink.android.sdk.d.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        LoginHelper.a(context, new Glink.OnLoggedInListener() { // from class: com.naver.glink.android.sdk.d.3.1
                            @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
                            public void onLoggedIn(boolean z) {
                                d.c(context, startTo);
                            }
                        });
                    } catch (Exception e2) {
                        d.b.a(e2);
                    }
                }
            });
        }
    }

    public static void b(Context context, boolean z) {
        g = new com.naver.glink.android.sdk.configure.c(context, z);
    }

    public static SdkBuildPhase c() {
        return b().b;
    }

    public static void c(Context context) {
        a(context, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, StartTo startTo) {
        if (com.naver.glink.android.sdk.ui.streaming.b.a(context)) {
            Toast.makeText(context, R.string.run_sdk_on_live_message, 1).show();
            return;
        }
        l(context);
        d(context);
        com.naver.glink.android.sdk.ui.main.b.a(context, startTo);
        com.naver.glink.android.sdk.ui.floating.b.a().b();
        Statistics.sendRequest("GST");
        b.a();
    }

    public static void d() {
        e = null;
    }

    public static void d(Context context) {
        if (h(context)) {
            return;
        }
        Activity activity = (Activity) context;
        if ((Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) && activity.getFragmentManager().findFragmentByTag(a) == null) {
            activity.getFragmentManager().beginTransaction().add(com.naver.glink.android.sdk.ui.parent.plugfragment.a.c.a(), a).commitAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context, boolean z) {
        com.naver.glink.android.sdk.a.a.b.c(new a());
        Request.cancelAllRequests(context);
        if (f()) {
            Statistics.sendRequest("GSP");
        }
        if (z) {
            Glide.with(context).onDestroy();
        }
        com.naver.glink.android.sdk.ui.widget.progress.b.c();
        NeoIdSdkManager.b();
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().h();
        b.b();
        e(context);
    }

    public static com.naver.glink.android.sdk.configure.d e() {
        if (f == null) {
            f = new com.naver.glink.android.sdk.configure.d();
        }
        return f;
    }

    public static void e(Context context) {
        if (h(context)) {
            return;
        }
        Activity activity = (Activity) context;
        Fragment fragmentFindFragmentByTag = activity.getFragmentManager().findFragmentByTag(a);
        if (fragmentFindFragmentByTag != null) {
            activity.getFragmentManager().beginTransaction().remove(fragmentFindFragmentByTag).commit();
        }
    }

    public static void f(Context context) {
        if (z.a(context) && !h(context)) {
            final Activity activity = (Activity) context;
            activity.runOnUiThread(new Runnable() { // from class: com.naver.glink.android.sdk.d.5
                @Override // java.lang.Runnable
                public void run() {
                    com.naver.glink.android.sdk.ui.floating.b.a().a(activity, activity.getFragmentManager());
                }
            });
        }
    }

    public static boolean f() {
        return com.naver.glink.android.sdk.ui.main.b.i() != null;
    }

    public static void g(Context context) {
        b.a("stopWidget", new Object[0]);
        if (context == null || h(context)) {
            return;
        }
        ((Activity) context).runOnUiThread(new Runnable() { // from class: com.naver.glink.android.sdk.d.6
            @Override // java.lang.Runnable
            public void run() {
                com.naver.glink.android.sdk.ui.floating.b.a().b();
            }
        });
    }

    public static boolean g() {
        return h == StartFrom.GAME;
    }

    public static boolean h() {
        return h == StartFrom.SAMSUNG_GAME_TOOLS;
    }

    public static boolean h(Context context) {
        return (context == null || (context instanceof Activity)) ? false : true;
    }

    public static WindowManager.LayoutParams i(Context context) {
        if (h(context)) {
            return d;
        }
        return null;
    }

    public static boolean i() {
        return p().a;
    }

    private static void j(final Context context) {
        if (o()) {
            com.naver.glink.android.sdk.api.requests.a.connectionsRequest.execute(context, new RequestListener<com.naver.glink.android.sdk.api.a.c>() { // from class: com.naver.glink.android.sdk.d.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.c cVar) {
                    String strA = cVar.connection.apis.a();
                    if (TextUtils.isEmpty(strA)) {
                        return;
                    }
                    com.naver.glink.android.sdk.configure.a unused = d.e = com.naver.glink.android.sdk.configure.a.a(d.e).a(com.naver.glink.android.sdk.configure.b.a(context.getResources(), strA)).a();
                }
            });
        }
    }

    public static boolean j() {
        return !i();
    }

    private static void k(Context context) {
        if (f()) {
            c(context);
        }
        com.naver.glink.android.sdk.api.requests.a.e();
    }

    public static boolean k() {
        return b().c();
    }

    private static void l(Context context) {
        if (context == null) {
            return;
        }
        if ((context.getResources().getConfiguration().orientation == 2 || !i()) && (context.getResources().getConfiguration().orientation == 1 || !j())) {
            return;
        }
        g = new com.naver.glink.android.sdk.configure.c(r());
    }

    public static boolean l() {
        return b().d();
    }

    private static void m(Context context) {
        c = new WeakReference<>(context);
    }

    public static boolean m() {
        return b().b == SdkBuildPhase.DEV;
    }

    public static boolean n() {
        return b().b == SdkBuildPhase.STAGE;
    }

    public static boolean o() {
        return b().b == SdkBuildPhase.REAL;
    }

    public static com.naver.glink.android.sdk.configure.c p() {
        return g == null ? com.naver.glink.android.sdk.configure.c.a() : g;
    }

    public static boolean q() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static Context r() {
        if (c == null) {
            return null;
        }
        return c.get();
    }

    public static boolean s() {
        return (com.naver.glink.android.sdk.api.requests.a.c() == null || TextUtils.equals(RequestHelper.getSystemLangCode(), com.naver.glink.android.sdk.api.requests.a.c())) ? false : true;
    }

    private static void v() {
        com.naver.plug.android.core.b.a(new com.naver.plug.android.core.b.a() { // from class: com.naver.glink.android.sdk.d.2
            @Override // com.naver.plug.android.core.b.a
            public String a() {
                return String.format("%s  NAVER(client; glink; 100; %s)", System.getProperty("http.agent"), d.b().a);
            }

            @Override // com.naver.plug.android.core.b.a
            public void a(Context context) {
                LoginHelper.a().logout(context);
            }

            @Override // com.naver.plug.android.core.b.a
            public void a(Context context, String str) {
                AlertDialogFragmentView.b(context, str).a();
            }

            @Override // com.naver.plug.android.core.b.a
            public void a(Context context, String str, final com.naver.plug.android.core.b.InterfaceC0067b interfaceC0067b) {
                LoginHelper.a(str.startsWith(d.b().c.a)).login(context, new Glink.OnLoggedInListener() { // from class: com.naver.glink.android.sdk.d.2.1
                    @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
                    public void onLoggedIn(boolean z) {
                        interfaceC0067b.onLoggedIn(z);
                    }
                });
            }

            @Override // com.naver.plug.android.core.b.a
            public void a(String str) {
                com.naver.glink.android.sdk.ui.widget.progress.b.a(str);
            }

            @Override // com.naver.plug.android.core.b.a
            public String b() {
                return d.a(R.string.internet_not_connected_error);
            }

            @Override // com.naver.plug.android.core.b.a
            public void b(String str) {
                com.naver.glink.android.sdk.ui.widget.progress.b.b(str);
            }

            @Override // com.naver.plug.android.core.b.a
            public void c() {
                com.naver.glink.android.sdk.ui.widget.progress.b.c();
            }
        });
    }
}
