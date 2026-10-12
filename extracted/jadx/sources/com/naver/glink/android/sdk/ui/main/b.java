package com.naver.glink.android.sdk.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.StartTo;
import com.naver.glink.android.sdk.Statistics;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.parent.plugfragment.ItemsDialogFragmentView;
import com.naver.glink.android.sdk.ui.widget.video.PlugVideoView;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.ArrayList;

/* JADX INFO: compiled from: MainHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static final String a = "plug.sdk.main";

    public static void a() {
        a(true);
    }

    public static void a(final Context context) {
        com.naver.glink.android.sdk.api.requests.a.forChannelsRequest.execute(context, new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.main.b.4
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                ArrayList arrayList = new ArrayList();
                int iIndexOf = -1;
                for (Responses.j jVar : iVar.a()) {
                    arrayList.add(jVar.language);
                    if (d.k() && TextUtils.equals(jVar.langCode, d.b().b())) {
                        iIndexOf = iVar.a().indexOf(jVar);
                    } else if (jVar.channelId == d.b().a()) {
                        iIndexOf = iVar.a().indexOf(jVar);
                    }
                }
                ItemsDialogFragmentView.a(context, ItemsDialogFragmentView.b, context.getString(R.string.select_channel_title), arrayList, iIndexOf, context.getResources().getDrawable(R.drawable.cf_icon_chanel_s));
            }
        });
    }

    public static void a(Context context, StartTo startTo) {
        if (d.f()) {
            a(startTo);
        } else {
            com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b().a(MainFragmentView.a(context, startTo), a).a(a).a();
        }
    }

    public static void a(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        MainFragmentView mainFragmentViewI = i();
        if (mainFragmentViewI != null) {
            mainFragmentViewI.a(view, customViewCallback);
        }
    }

    private static void a(final StartTo startTo) {
        d.a(new Runnable() { // from class: com.naver.glink.android.sdk.ui.main.b.2
            @Override // java.lang.Runnable
            public void run() {
                MainFragmentView mainFragmentViewI = b.i();
                if (mainFragmentViewI != null) {
                    mainFragmentViewI.a(startTo);
                }
            }
        });
    }

    public static void a(PlugVideoView plugVideoView) {
        MainFragmentView mainFragmentViewI = i();
        if (mainFragmentViewI != null) {
            mainFragmentViewI.setFullscreenVideoView(plugVideoView);
        }
    }

    public static void a(final boolean z) {
        d.a(new Runnable() { // from class: com.naver.glink.android.sdk.ui.main.b.1
            @Override // java.lang.Runnable
            public void run() {
                MainFragmentView mainFragmentViewI = b.i();
                if (mainFragmentViewI != null) {
                    mainFragmentViewI.e();
                    if (z) {
                        d.f(d.r());
                    }
                    Statistics.sendRequest("GSP");
                    com.naver.glink.android.sdk.b.b();
                    com.naver.glink.android.sdk.a.a.b.c(new d.a());
                }
            }
        });
    }

    public static void b() {
        a((StartTo) null);
    }

    public static void b(boolean z) {
        MainFragmentView mainFragmentViewI = i();
        if (mainFragmentViewI != null) {
            mainFragmentViewI.a(z);
        }
    }

    public static boolean c() {
        MainFragmentView mainFragmentViewI = i();
        if (mainFragmentViewI != null) {
            return mainFragmentViewI.getCollapsed();
        }
        return false;
    }

    public static void d() {
        MainFragmentView mainFragmentViewI = i();
        if (mainFragmentViewI != null) {
            mainFragmentViewI.c();
        }
    }

    public static void e() {
        final a aVarK = k();
        if (aVarK != null) {
            if (aVarK.a.getVisibility() != 0) {
                aVarK.a.setVisibility(0);
                aVarK.a.postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.main.b.3
                    @Override // java.lang.Runnable
                    public void run() {
                        aVarK.a.setVisibility(8);
                    }
                }, 3000L);
            }
            if (d.o()) {
                return;
            }
            com.naver.glink.android.sdk.ui.tabs.b.j();
        }
    }

    public static void f() {
        if (d.b().f()) {
            com.naver.glink.android.sdk.api.requests.a.forChannelsRequest.execute(d.r(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.main.b.5
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.i iVar) {
                    Responses.j jVarA = d.k() ? iVar.a(d.b().b()) : iVar.a(d.b().a());
                    a aVarK = b.k();
                    if (aVarK == null || jVarA == null) {
                        return;
                    }
                    aVarK.b.setText("" + jVarA.language);
                }
            });
        }
    }

    public static void g() {
        a aVarK = k();
        if (aVarK != null) {
            aVarK.c.setVisibility(0);
        }
    }

    public static void h() {
        a aVarK = k();
        if (aVarK != null) {
            aVarK.c.setVisibility(8);
        }
    }

    public static MainFragmentView i() {
        return (MainFragmentView) com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static a k() {
        MainFragmentView mainFragmentViewI = i();
        if (mainFragmentViewI != null) {
            return mainFragmentViewI.b;
        }
        return null;
    }
}
