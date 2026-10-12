package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class de {

    @SuppressLint({"StaticFieldLeak"})
    private static de f = new de();
    public Context a;
    public BroadcastReceiver b;
    public boolean c;
    public boolean d;
    public a e;

    public interface a {
        void a(boolean z);
    }

    private de() {
    }

    public static de a() {
        return f;
    }

    static /* synthetic */ void a(de deVar, boolean z) {
        if (deVar.d != z) {
            deVar.d = z;
            if (deVar.c) {
                deVar.c();
                if (deVar.e != null) {
                    deVar.e.a(deVar.b());
                }
            }
        }
    }

    public final boolean b() {
        return !this.d;
    }

    public final void c() {
        boolean z = !this.d;
        Iterator it = Collections.unmodifiableCollection(dd.a().a).iterator();
        while (it.hasNext()) {
            ds dsVar = ((cz) it.next()).c;
            if (dsVar.a.get() != null) {
                dg.a().a(dsVar.c(), "setState", z ? "foregrounded" : "backgrounded");
            }
        }
    }
}
