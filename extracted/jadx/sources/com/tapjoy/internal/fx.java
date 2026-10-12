package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TapjoyConnectCore;
import java.util.Observer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fx extends hk {
    private final fz b = new fz() { // from class: com.tapjoy.internal.fx.1
        @Override // com.tapjoy.internal.fz
        protected final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            hk.a aVar = (hk.a) obj;
            TJPlacement tJPlacementCreatePlacement = TJPlacementManager.createPlacement(TapjoyConnectCore.getContext(), aVar.b, false, tJPlacementListener);
            tJPlacementCreatePlacement.pushId = aVar.a;
            return tJPlacementCreatePlacement;
        }

        @Override // com.tapjoy.internal.fz
        protected final /* bridge */ /* synthetic */ String a(Object obj) {
            hk.a aVar = (hk.a) obj;
            if (aVar != null) {
                return aVar.b;
            }
            return null;
        }

        @Override // com.tapjoy.internal.fz
        protected final boolean a() {
            return true;
        }

        @Override // com.tapjoy.internal.fz
        protected final boolean a(Observer observer) {
            if (TapjoyConnectCore.isViewOpen()) {
                TJPlacementManager.dismissContentShowing(true);
            }
            return super.a(observer);
        }

        @Override // com.tapjoy.internal.fz
        protected final /* synthetic */ fz.a b(Object obj) {
            hk.a aVar = (hk.a) obj;
            return new fz.a(aVar, aVar.d);
        }
    };

    static {
        hk.a(new fx());
    }

    private fx() {
    }

    public static void a() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.hk
    public final void a(hk.a aVar) {
        this.b.c(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.hk
    public final boolean b() {
        return this.b.b != null;
    }
}
