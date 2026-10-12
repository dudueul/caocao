package com.naver.glink.android.sdk.a.c;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.kaleido.Authentication;
import com.naver.kaleido.BasicHandler;
import com.naver.kaleido.KaleidoClient;
import com.naver.kaleido.KaleidoConfig;
import com.naver.kaleido.NaverOauth;
import com.naver.kaleido.Neoid;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;

/* JADX INFO: compiled from: KaleidoHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    private static final l a = l.a(c.class.getSimpleName());
    private static final com.naver.glink.android.sdk.a.c.b b = new AnonymousClass1();
    private static final com.naver.glink.android.sdk.a.c.b c = new AnonymousClass2();

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.a.c.c$1, reason: invalid class name */
    /* JADX INFO: compiled from: KaleidoHelper.java */
    static class AnonymousClass1 extends com.naver.glink.android.sdk.a.c.b {
        AnonymousClass1() {
        }

        @Override // com.naver.glink.android.sdk.a.c.b
        Authentication a() {
            String accessToken = LoginHelper.a().getAccessToken();
            return TextUtils.isEmpty(accessToken) ? NaverOauth.Anonymous() : new NaverOauth(accessToken, (BasicHandler) null);
        }

        @Override // com.naver.glink.android.sdk.a.c.b
        @Subscribe
        public void a(LoginHelper.b bVar) {
            if (bVar == null || d() == null) {
                return;
            }
            c.b(com.naver.glink.android.sdk.d.r(), new RequestListener<f.a>() { // from class: com.naver.glink.android.sdk.a.c.c.1.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull f.a aVar) {
                    String str = aVar != null ? aVar.userId : null;
                    if (AnonymousClass1.this.b(str).equals(AnonymousClass1.this.a)) {
                        return;
                    }
                    com.naver.glink.android.sdk.a.a.b.c(new a(true, null));
                    if (AnonymousClass1.this.a(str)) {
                        com.naver.glink.android.sdk.a.a.b.c(new a(false, AnonymousClass1.this.d()));
                    }
                }
            });
        }

        @Override // com.naver.glink.android.sdk.a.c.b
        String b() {
            return com.naver.glink.android.sdk.d.c().kaleidoLocalWorkspaceKey;
        }

        @Override // com.naver.glink.android.sdk.a.c.b
        String b(String str) {
            return "glink:" + (com.naver.glink.android.sdk.d.b().b.name() + ":") + str + ":" + com.naver.glink.android.sdk.d.r().getPackageName();
        }
    }

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.a.c.c$2, reason: invalid class name */
    /* JADX INFO: compiled from: KaleidoHelper.java */
    static class AnonymousClass2 extends com.naver.glink.android.sdk.a.c.b {
        AnonymousClass2() {
        }

        @Override // com.naver.glink.android.sdk.a.c.b
        Authentication a() {
            String accessToken = LoginHelper.a().getAccessToken();
            return TextUtils.isEmpty(accessToken) ? Neoid.Anonymous() : new Neoid(com.naver.glink.android.sdk.d.c().kaleidoNeoIdServiceId, com.naver.glink.android.sdk.d.c().kaleidoNeoIdServiceKey, com.naver.glink.android.sdk.d.b().e.b, accessToken, (BasicHandler) null);
        }

        @Override // com.naver.glink.android.sdk.a.c.b
        @Subscribe
        public void a(LoginHelper.b bVar) {
            if (bVar == null || d() == null) {
                return;
            }
            c.b(com.naver.glink.android.sdk.d.r(), new RequestListener<f.a>() { // from class: com.naver.glink.android.sdk.a.c.c.2.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull f.a aVar) {
                    String str = aVar != null ? aVar.userId : null;
                    if (AnonymousClass2.this.b(str).equals(AnonymousClass2.this.a)) {
                        return;
                    }
                    com.naver.glink.android.sdk.a.a.b.c(new a(true, null));
                    if (AnonymousClass2.this.a(str)) {
                        com.naver.glink.android.sdk.a.a.b.c(new a(false, AnonymousClass2.this.d()));
                    }
                }
            });
        }

        @Override // com.naver.glink.android.sdk.a.c.b
        String b() {
            return com.naver.glink.android.sdk.d.c().kaleidoGlobalWorkspaceKey;
        }

        @Override // com.naver.glink.android.sdk.a.c.b
        String b(String str) {
            return "plug:" + (com.naver.glink.android.sdk.d.b().b.name() + ":") + str + ":" + com.naver.glink.android.sdk.d.r().getPackageName();
        }
    }

    /* JADX INFO: compiled from: KaleidoHelper.java */
    public static class a {
        public boolean a;
        public KaleidoClient b;

        public a(boolean z, KaleidoClient kaleidoClient) {
            this.a = z;
            this.b = kaleidoClient;
        }
    }

    /* JADX INFO: compiled from: KaleidoHelper.java */
    public interface b {
        void a(KaleidoClient kaleidoClient);
    }

    public static void a() {
        d().f();
    }

    public static void a(final Activity activity, final b bVar) {
        if (activity == null) {
            return;
        }
        if (!com.naver.glink.android.sdk.d.m()) {
            KaleidoConfig.setEnvironment(com.naver.glink.android.sdk.a.d);
        }
        a.a("connect(). isConnected ? " + d().c(), new Object[0]);
        if (!d().c()) {
            b(activity, new RequestListener<f.a>() { // from class: com.naver.glink.android.sdk.a.c.c.3
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull f.a aVar) {
                    KaleidoClient kaleidoClientA = c.d().a(activity, aVar != null ? aVar.userId : null);
                    if (bVar != null) {
                        bVar.a(kaleidoClientA);
                    }
                }
            });
        } else if (bVar != null) {
            bVar.a(d().d());
        }
    }

    public static void b() {
        d().e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, final RequestListener<f.a> requestListener) {
        if (!LoginHelper.a().isLogin(context)) {
            requestListener.onSuccess(null);
            return;
        }
        f.a aVar = (f.a) com.naver.glink.android.sdk.api.requests.a.userRequest.getCachedResponse();
        if (aVar == null) {
            com.naver.glink.android.sdk.api.requests.a.userRequest.execute(context, new RequestListener<f.a>() { // from class: com.naver.glink.android.sdk.a.c.c.4
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull f.a aVar2) {
                    requestListener.onSuccess(aVar2);
                }
            });
        } else {
            requestListener.onSuccess(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static com.naver.glink.android.sdk.a.c.b d() {
        return com.naver.glink.android.sdk.d.k() ? b : c;
    }
}
