package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyLog;
import java.util.Observable;
import java.util.Observer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class fz {
    volatile a b;

    class a implements TJPlacementListener, Observer {
        private final Object b;
        private final fi c;
        private volatile boolean d;
        private TJPlacement e;

        a(fz fzVar, Object obj) {
            this(obj, new fi(TapjoyConstants.TIMER_INCREMENT));
        }

        a(Object obj, fi fiVar) {
            this.b = obj;
            this.c = fiVar;
        }

        private void a(String str) {
            synchronized (this) {
                String strA = fz.this.a(this.b);
                if (str == null) {
                    TapjoyLog.i("SystemPlacement", "Placement " + strA + " is presented now");
                } else {
                    TapjoyLog.i("SystemPlacement", "Cannot show placement " + strA + " now (" + str + ")");
                }
                this.d = true;
                this.e = null;
                fs.a.deleteObserver(this);
                fs.e.deleteObserver(this);
                fs.c.deleteObserver(this);
            }
            fz fzVar = fz.this;
            synchronized (fzVar) {
                if (fzVar.b == this) {
                    fzVar.b = null;
                }
            }
        }

        final void a() {
            synchronized (this) {
                if (this.d) {
                    return;
                }
                if (this.c.a()) {
                    a("Timed out");
                    return;
                }
                if (!TapjoyConnectCore.isConnected()) {
                    fs.a.addObserver(this);
                    if (!TapjoyConnectCore.isConnected()) {
                        return;
                    } else {
                        fs.a.deleteObserver(this);
                    }
                }
                if (this.e == null) {
                    if (!fz.this.a()) {
                        a("Cannot request");
                        return;
                    } else {
                        this.e = fz.this.a(TapjoyConnectCore.getContext(), this, this.b);
                        this.e.requestContent();
                        return;
                    }
                }
                if (this.e.isContentReady()) {
                    if (fz.this.a((Observer) this)) {
                        this.e.showContent();
                        a(null);
                    }
                }
            }
        }

        @Override // com.tapjoy.TJPlacementListener
        public final void onClick(TJPlacement tJPlacement) {
        }

        @Override // com.tapjoy.TJPlacementListener
        public final void onContentDismiss(TJPlacement tJPlacement) {
        }

        @Override // com.tapjoy.TJPlacementListener
        public final void onContentReady(TJPlacement tJPlacement) {
            a();
        }

        @Override // com.tapjoy.TJPlacementListener
        public final void onContentShow(TJPlacement tJPlacement) {
        }

        @Override // com.tapjoy.TJPlacementListener
        public final void onPurchaseRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str) {
        }

        @Override // com.tapjoy.TJPlacementListener
        public final void onRequestFailure(TJPlacement tJPlacement, TJError tJError) {
            a(tJError.message);
        }

        @Override // com.tapjoy.TJPlacementListener
        public final void onRequestSuccess(TJPlacement tJPlacement) {
        }

        @Override // com.tapjoy.TJPlacementListener
        public final void onRewardRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str, int i) {
        }

        @Override // java.util.Observer
        public final void update(Observable observable, Object obj) {
            a();
        }
    }

    fz() {
    }

    protected abstract TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj);

    protected abstract String a(Object obj);

    protected boolean a() {
        return !TapjoyConnectCore.isFullScreenViewOpen();
    }

    protected boolean a(Observer observer) {
        if (TapjoyConnectCore.isFullScreenViewOpen()) {
            fs.e.addObserver(observer);
            if (TapjoyConnectCore.isFullScreenViewOpen()) {
                return false;
            }
            fs.e.deleteObserver(observer);
        }
        if (!gz.a().d()) {
            fs.c.addObserver(observer);
            if (!gz.a().d()) {
                return false;
            }
            fs.c.deleteObserver(observer);
        }
        return true;
    }

    protected a b(Object obj) {
        return new a(this, obj);
    }

    public final boolean c(Object obj) {
        if (!a()) {
            return false;
        }
        a aVarB = null;
        synchronized (this) {
            if (this.b == null) {
                aVarB = b(obj);
                this.b = aVarB;
            }
        }
        if (aVarB == null) {
            return false;
        }
        aVarB.a();
        return true;
    }
}
