package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import com.tapjoy.internal.fn;
import com.tapjoy.internal.fq;
import com.tapjoy.internal.fw;
import com.tapjoy.internal.ge;
import com.tapjoy.internal.gf;
import com.tapjoy.internal.gs;
import com.tapjoy.internal.gv;
import com.tapjoy.internal.gz;
import com.tapjoy.internal.he;
import com.tapjoy.internal.jq;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJPlacement {
    TJPlacementListener a;
    private TJCorePlacement b;
    private TJPlacementListener c;
    private TJPlacementVideoListener d;
    private String e;
    public String pushId;

    @Deprecated
    public TJPlacement(Context context, String str, TJPlacementListener tJPlacementListener) {
        TJCorePlacement tJCorePlacementA = TJPlacementManager.a(str);
        tJCorePlacementA = tJCorePlacementA == null ? TJPlacementManager.a(str, "", "", false, false) : tJCorePlacementA;
        tJCorePlacementA.setContext(context);
        a(tJCorePlacementA, tJPlacementListener);
    }

    TJPlacement(TJCorePlacement tJCorePlacement, TJPlacementListener tJPlacementListener) {
        a(tJCorePlacement, tJPlacementListener);
    }

    private void a(TJCorePlacement tJCorePlacement, TJPlacementListener tJPlacementListener) {
        this.b = tJCorePlacement;
        this.e = UUID.randomUUID().toString();
        this.c = tJPlacementListener;
        this.a = tJPlacementListener != null ? (TJPlacementListener) fn.a(tJPlacementListener, TJPlacementListener.class) : null;
        FiveRocksIntegration.addPlacementCallback(getName(), this);
    }

    private void a(TJError tJError) {
        this.b.a(this, TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, tJError);
    }

    public static void dismissContent() {
        TJPlacementManager.dismissContentShowing("true".equals(TapjoyConnectCore.getConnectFlagValue("TJC_OPTION_DISMISS_CONTENT_ALL")));
    }

    public String getGUID() {
        return this.e;
    }

    public TJPlacementListener getListener() {
        return this.c;
    }

    public String getName() {
        return this.b.getPlacementData() != null ? this.b.getPlacementData().getPlacementName() : "";
    }

    public TJPlacementVideoListener getVideoListener() {
        return this.d;
    }

    public boolean isContentAvailable() {
        this.b.f.a(1);
        return this.b.isContentAvailable();
    }

    public boolean isContentReady() {
        boolean zIsContentReady = this.b.isContentReady();
        fw fwVar = this.b.f;
        if (zIsContentReady) {
            fwVar.a(4);
        } else {
            fwVar.a(2);
        }
        return zIsContentReady;
    }

    public boolean isLimited() {
        return this.b.isLimited();
    }

    public void requestContent() {
        String name = getName();
        TapjoyLog.i("TJPlacement", "requestContent() called for placement " + name);
        gf.a("TJPlacement.requestContent").a("placement", name).a("placement_type", this.b.c.getPlacementType());
        if (ge.a() != null && jq.c(ge.a().b)) {
            TapjoyLog.w("TJPlacement", "[INFO] Your application calls requestContent without having previously called setUserConsent. You can review Tapjoy supported consent API here - https://dev.tapjoy.com/sdk-integration/#sdk11122_gdpr_release.");
        }
        if (!(!isLimited() ? TapjoyConnectCore.isConnected() : TapjoyConnectCore.isLimitedConnected())) {
            gf.b("TJPlacement.requestContent").b("not connected").c();
            a(new TJError(0, "SDK not connected -- connect must be called first with a successful callback"));
            return;
        }
        if (this.b.getContext() == null) {
            gf.b("TJPlacement.requestContent").b("no context").c();
            a(new TJError(0, "Context is null -- TJPlacement requires a valid Context."));
        } else if (jq.c(name)) {
            gf.b("TJPlacement.requestContent").b("invalid name").c();
            a(new TJError(0, "Invalid placement name -- TJPlacement requires a valid placement name."));
        } else {
            try {
                this.b.a(this);
            } finally {
                gf.d("TJPlacement.requestContent");
            }
        }
    }

    public void setAdapterVersion(String str) {
        this.b.n = str;
    }

    public void setAuctionData(HashMap map) {
        if (map == null || map.isEmpty()) {
            TapjoyLog.d("TJPlacement", "auctionData can not be null or empty");
            return;
        }
        TJCorePlacement tJCorePlacement = this.b;
        tJCorePlacement.q = map;
        String strB = tJCorePlacement.b();
        if (jq.c(strB)) {
            TapjoyLog.i(TJCorePlacement.a, "Placement auction data can not be set for a null app ID");
        } else {
            tJCorePlacement.c.setAuctionMediationURL(TapjoyConnectCore.getPlacementURL() + "v1/apps/" + strB + "/bid_content?");
        }
    }

    public void setMediationId(String str) {
        this.b.p = str;
    }

    public void setMediationName(String str) {
        TapjoyLog.d("TJPlacement", "setMediationName=" + str);
        if (jq.c(str)) {
            return;
        }
        Context context = this.b != null ? this.b.getContext() : null;
        this.b = TJPlacementManager.a(getName(), str, "", false, isLimited());
        TJCorePlacement tJCorePlacement = this.b;
        tJCorePlacement.o = str;
        tJCorePlacement.m = str;
        tJCorePlacement.c.setPlacementType(str);
        String strB = tJCorePlacement.b();
        if (jq.c(strB)) {
            TapjoyLog.i(TJCorePlacement.a, "Placement mediation name can not be set for a null app ID");
        } else {
            tJCorePlacement.c.setMediationURL(TapjoyConnectCore.getPlacementURL() + "v1/apps/" + strB + "/mediation_content?");
        }
        if (context != null) {
            this.b.setContext(context);
        }
    }

    public void setVideoListener(TJPlacementVideoListener tJPlacementVideoListener) {
        this.d = tJPlacementVideoListener;
    }

    public void showContent() {
        int i = 1;
        TapjoyLog.i("TJPlacement", "showContent() called for placement " + getName());
        TJCorePlacement tJCorePlacement = this.b;
        gf.a("TJPlacement.showContent").a("placement", tJCorePlacement.c.getPlacementName()).a("placement_type", tJCorePlacement.c.getPlacementType()).a("content_type", tJCorePlacement.a());
        fw fwVar = tJCorePlacement.f;
        fwVar.a(8);
        fq fqVar = fwVar.a;
        if (fqVar != null) {
            fqVar.a();
        }
        if (!this.b.isContentAvailable()) {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "No placement content available. Can not show content for non-200 placement."));
            gf.b("TJPlacement.showContent").b("no content").c();
            return;
        }
        try {
            final TJCorePlacement tJCorePlacement2 = this.b;
            if (this == null) {
                tJCorePlacement2.a(TapjoyErrorMessage.ErrorType.SDK_ERROR, new TJError(0, "Cannot show content from a NULL placement"));
            } else if (TapjoyConnectCore.isFullScreenViewOpen()) {
                TapjoyLog.w(TJCorePlacement.a, "Only one view can be presented at a time.");
                gf.b("TJPlacement.showContent").b("another content showing").c();
            } else {
                if (TapjoyConnectCore.isViewOpen()) {
                    TapjoyLog.w(TJCorePlacement.a, "Will close N2E content.");
                    TJPlacementManager.dismissContentShowing(false);
                }
                tJCorePlacement2.a("SHOW", this);
                gf.a aVarD = gf.d("TJPlacement.showContent");
                if (tJCorePlacement2.g.isPrerendered()) {
                    aVarD.a(SettingsJsonConstants.ICON_PRERENDERED_KEY, (Object) true);
                }
                if (tJCorePlacement2.isContentReady()) {
                    aVarD.a("content_ready", (Object) true);
                }
                tJCorePlacement2.f.d = aVarD;
                final String string = UUID.randomUUID().toString();
                if (tJCorePlacement2.i != null) {
                    tJCorePlacement2.i.f = string;
                    if (tJCorePlacement2.i != null) {
                        if (tJCorePlacement2.i instanceof gv) {
                            i = 3;
                        } else {
                            i = tJCorePlacement2.i instanceof he ? 2 : 0;
                        }
                    }
                    TapjoyConnectCore.viewWillOpen(string, i);
                    tJCorePlacement2.i.e = new gs() { // from class: com.tapjoy.TJCorePlacement.4
                        @Override // com.tapjoy.internal.gs
                        public final void a(Context context, String str, String str2) {
                            if (str2 == null) {
                                TJCorePlacement.this.c.setRedirectURL(str);
                            } else {
                                TJCorePlacement.this.c.setBaseURL(str);
                                TJCorePlacement.this.c.setHttpResponse(str2);
                            }
                            TJCorePlacement.this.c.setHasProgressSpinner(true);
                            TJCorePlacement.this.c.setContentViewId(string);
                            Intent intent = new Intent(TJCorePlacement.this.b, (Class<?>) TJAdUnitActivity.class);
                            intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, TJCorePlacement.this.c);
                            intent.setFlags(268435456);
                            context.startActivity(intent);
                        }
                    };
                    gz.a(new Runnable() { // from class: com.tapjoy.TJCorePlacement.5
                        @Override // java.lang.Runnable
                        public final void run() {
                            TJCorePlacement.this.i.a(gz.a().p, TJCorePlacement.this.f);
                        }
                    });
                } else {
                    tJCorePlacement2.c.setContentViewId(string);
                    Intent intent = new Intent(tJCorePlacement2.b, (Class<?>) TJAdUnitActivity.class);
                    intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, tJCorePlacement2.c);
                    intent.setFlags(268435456);
                    tJCorePlacement2.b.startActivity(intent);
                }
                tJCorePlacement2.e = 0L;
                tJCorePlacement2.k = false;
                tJCorePlacement2.l = false;
            }
        } finally {
            gf.d("TJPlacement.showContent");
        }
    }
}
