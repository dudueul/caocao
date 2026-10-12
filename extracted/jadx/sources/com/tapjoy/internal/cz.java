package com.tapjoy.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class cz extends cs {
    public final ct a;
    public ds c;
    boolean g;
    private final cu h;
    private dq i;
    public final List b = new ArrayList();
    public boolean d = false;
    public boolean e = false;
    public String f = UUID.randomUUID().toString();

    cz(ct ctVar, cu cuVar) {
        this.a = ctVar;
        this.h = cuVar;
        b(null);
        if (cuVar.f == cv.HTML) {
            this.c = new dt(cuVar.b);
        } else {
            this.c = new du(Collections.unmodifiableList(cuVar.c), cuVar.d);
        }
        this.c.a();
        dd.a().a.add(this);
        ds dsVar = this.c;
        dg dgVarA = dg.a();
        WebView webViewC = dsVar.c();
        JSONObject jSONObject = new JSONObject();
        dm.a(jSONObject, "impressionOwner", ctVar.a);
        dm.a(jSONObject, "videoEventsOwner", ctVar.b);
        dm.a(jSONObject, "isolateVerificationScripts", Boolean.valueOf(ctVar.c));
        dgVarA.a(webViewC, "init", jSONObject);
    }

    private void b(View view) {
        this.i = new dq(view);
    }

    @Override // com.tapjoy.internal.cs
    public final void a() {
        if (this.d) {
            return;
        }
        this.d = true;
        dd ddVarA = dd.a();
        boolean zB = ddVarA.b();
        ddVarA.b.add(this);
        if (!zB) {
            dh dhVarA = dh.a();
            de.a().e = dhVarA;
            final de deVarA = de.a();
            deVarA.b = new BroadcastReceiver() { // from class: com.tapjoy.internal.de.1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    KeyguardManager keyguardManager;
                    if (intent == null) {
                        return;
                    }
                    if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                        de.a(de.this, true);
                        return;
                    }
                    if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                        de.a(de.this, false);
                    } else {
                        if (!"android.intent.action.SCREEN_ON".equals(intent.getAction()) || (keyguardManager = (KeyguardManager) context.getSystemService("keyguard")) == null || keyguardManager.inKeyguardRestrictedInputMode()) {
                            return;
                        }
                        de.a(de.this, false);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            deVarA.a.registerReceiver(deVarA.b, intentFilter);
            deVarA.c = true;
            deVarA.c();
            if (de.a().b()) {
                dv.a();
                dv.b();
            }
            cp cpVar = dhVarA.b;
            cpVar.b = cpVar.a();
            cpVar.b();
            cpVar.a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, cpVar);
        }
        this.c.a(dh.a().a);
        this.c.a(this, this.h);
    }

    @Override // com.tapjoy.internal.cs
    public final void a(View view) {
        if (this.e) {
            return;
        }
        Cdo.a(view, "AdView is null");
        if (c() != view) {
            b(view);
            this.c.d();
            Collection<cz> collectionUnmodifiableCollection = Collections.unmodifiableCollection(dd.a().a);
            if (collectionUnmodifiableCollection == null || collectionUnmodifiableCollection.size() <= 0) {
                return;
            }
            for (cz czVar : collectionUnmodifiableCollection) {
                if (czVar != this && czVar.c() == view) {
                    czVar.i.clear();
                }
            }
        }
    }

    @Override // com.tapjoy.internal.cs
    public final void b() {
        if (this.e) {
            return;
        }
        this.i.clear();
        if (!this.e) {
            this.b.clear();
        }
        this.e = true;
        dg.a().a(this.c.c(), "finishSession", new Object[0]);
        dd ddVarA = dd.a();
        boolean zB = ddVarA.b();
        ddVarA.a.remove(this);
        ddVarA.b.remove(this);
        if (zB && !ddVarA.b()) {
            dh dhVarA = dh.a();
            final dv dvVarA = dv.a();
            dv.c();
            dvVarA.b.clear();
            dv.a.post(new Runnable() { // from class: com.tapjoy.internal.dv.1
                @Override // java.lang.Runnable
                public final void run() {
                    dv.this.h.b();
                }
            });
            de deVarA = de.a();
            if (deVarA.a != null && deVarA.b != null) {
                deVarA.a.unregisterReceiver(deVarA.b);
                deVarA.b = null;
            }
            deVarA.c = false;
            deVarA.d = false;
            deVarA.e = null;
            cp cpVar = dhVarA.b;
            cpVar.a.getContentResolver().unregisterContentObserver(cpVar);
        }
        this.c.b();
        this.c = null;
    }

    public final View c() {
        return (View) this.i.get();
    }

    public final boolean d() {
        return this.d && !this.e;
    }
}
