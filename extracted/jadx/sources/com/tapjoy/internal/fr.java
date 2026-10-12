package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fr {
    private static final fz a = new fz() { // from class: com.tapjoy.internal.fr.1
        @Override // com.tapjoy.internal.fz
        protected final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            return TJPlacementManager.createPlacement(context, "InsufficientCurrency", true, tJPlacementListener);
        }

        @Override // com.tapjoy.internal.fz
        protected final /* bridge */ /* synthetic */ String a(Object obj) {
            return "InsufficientCurrency";
        }
    };

    public static void a() {
        a.c(null);
    }
}
