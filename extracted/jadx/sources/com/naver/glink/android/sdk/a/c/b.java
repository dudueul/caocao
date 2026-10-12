package com.naver.glink.android.sdk.a.c;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.kaleido.AndroidContext;
import com.naver.kaleido.AndroidSyncAgents;
import com.naver.kaleido.Authentication;
import com.naver.kaleido.KaleidoClient;
import com.naver.kaleido.KaleidoManager;
import com.squareup.otto.Subscribe;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: KaleidoClientWrapper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class b {
    private static final l b = l.a(b.class.getSimpleName());
    protected String a;
    private KaleidoClient c;

    b() {
    }

    private void a(KaleidoClient kaleidoClient) {
        if (kaleidoClient == null) {
            return;
        }
        try {
            kaleidoClient.sync().get();
        } catch (Exception e) {
            b.a(e);
        }
    }

    private boolean g() {
        b.a("detach Kaleido" + this.c, new Object[0]);
        if (this.c == null) {
            return false;
        }
        a(this.c);
        KaleidoManager.unloadClient(this.c);
        this.c = null;
        return true;
    }

    private void h() {
        com.naver.glink.android.sdk.a.a.b.a(this);
    }

    private void i() {
        com.naver.glink.android.sdk.a.a.b.b(this);
    }

    abstract Authentication a();

    KaleidoClient a(@NonNull Activity activity, String str) {
        if (this.c == null) {
            KaleidoManager.init(new AndroidContext(activity.getApplication()));
            this.a = b(str);
            this.c = KaleidoManager.getClient(a(), b(), this.a);
            try {
                this.c.connect(AndroidSyncAgents.createRealtime(true)).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e2) {
                e2.printStackTrace();
            }
            h();
            b.a("connect!!! ", new Object[0]);
        } else {
            b.a("connect. but already connected ", new Object[0]);
        }
        return this.c;
    }

    @Subscribe
    abstract void a(LoginHelper.b bVar);

    boolean a(String str) {
        if (this.c == null) {
            return false;
        }
        g();
        this.a = b(str);
        this.c = KaleidoManager.getClient(a(), b(), this.a);
        this.c.connect(AndroidSyncAgents.createRealtime());
        b.a("reconnect", new Object[0]);
        return true;
    }

    abstract String b();

    abstract String b(String str);

    boolean c() {
        return this.c != null;
    }

    protected KaleidoClient d() {
        return this.c;
    }

    void e() {
        b.a("disconnect Kaleido", new Object[0]);
        if (g()) {
            i();
        }
    }

    void f() {
        a(this.c);
    }
}
