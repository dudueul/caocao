package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJConnectListener;
import java.util.Hashtable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class et extends es {
    private final gb c = new gb() { // from class: com.tapjoy.internal.et.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.tapjoy.internal.gb
        public final boolean a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
            return et.super.a(context, str, hashtable, tJConnectListener);
        }
    };

    et() {
    }

    @Override // com.tapjoy.internal.es, com.tapjoy.internal.er
    public final boolean a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        return this.c.b(context, str, hashtable, tJConnectListener);
    }
}
