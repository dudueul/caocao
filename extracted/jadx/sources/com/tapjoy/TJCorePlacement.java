package com.tapjoy;

import android.content.Context;
import android.os.SystemClock;
import com.tapjoy.internal.b;
import com.tapjoy.internal.cb;
import com.tapjoy.internal.fb;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.fq;
import com.tapjoy.internal.fu;
import com.tapjoy.internal.fv;
import com.tapjoy.internal.fw;
import com.tapjoy.internal.ga;
import com.tapjoy.internal.gf;
import com.tapjoy.internal.gi;
import com.tapjoy.internal.gv;
import com.tapjoy.internal.gw;
import com.tapjoy.internal.gz;
import com.tapjoy.internal.he;
import com.tapjoy.internal.hg;
import com.tapjoy.internal.hh;
import com.tapjoy.internal.hi;
import com.tapjoy.internal.ij;
import com.tapjoy.internal.jq;
import com.tapjoy.internal.v;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJCorePlacement {
    static final String a = TJCorePlacement.class.getSimpleName();
    TJPlacementData c;
    String d;
    long e;
    TJAdUnit g;
    boolean j;
    String m;
    String n;
    String o;
    String p;
    HashMap q;
    private Map s;
    private fm t;
    private boolean y;
    private Map r = new HashMap();
    final fw f = new fw();
    boolean h = false;
    private boolean u = false;
    private ij v = null;
    hg i = null;
    private volatile boolean w = false;
    private volatile boolean x = false;
    volatile boolean k = false;
    volatile boolean l = false;
    private TJAdUnit.TJAdUnitWebViewListener z = new TJAdUnit.TJAdUnitWebViewListener() { // from class: com.tapjoy.TJCorePlacement.1
        @Override // com.tapjoy.TJAdUnit.TJAdUnitWebViewListener
        public final void onClick() {
            TJCorePlacement.e(TJCorePlacement.this);
        }

        @Override // com.tapjoy.TJAdUnit.TJAdUnitWebViewListener
        public final void onClosed() {
            if (TJCorePlacement.this.h) {
                TJPlacementManager.decrementPlacementCacheCount();
                TJCorePlacement.c(TJCorePlacement.this);
            }
            if (TJCorePlacement.this.u) {
                TJPlacementManager.decrementPlacementPreRenderCount();
                TJCorePlacement.this.u = false;
            }
        }

        @Override // com.tapjoy.TJAdUnit.TJAdUnitWebViewListener
        public final void onContentReady() {
            TJCorePlacement.this.e();
        }
    };
    private TJAdUnit.TJAdUnitVideoListener A = new TJAdUnit.TJAdUnitVideoListener() { // from class: com.tapjoy.TJCorePlacement.2
        @Override // com.tapjoy.TJAdUnit.TJAdUnitVideoListener
        public final void onVideoCompleted() {
            TJPlacement tJPlacementA = TJCorePlacement.this.a("SHOW");
            if (tJPlacementA == null || tJPlacementA.getVideoListener() == null) {
                return;
            }
            tJPlacementA.getVideoListener().onVideoComplete(tJPlacementA);
        }

        @Override // com.tapjoy.TJAdUnit.TJAdUnitVideoListener
        public final void onVideoError(String str) {
            TJPlacement tJPlacementA = TJCorePlacement.this.a("SHOW");
            if (tJPlacementA == null || tJPlacementA.getVideoListener() == null) {
                return;
            }
            tJPlacementA.getVideoListener().onVideoError(tJPlacementA, str);
        }

        @Override // com.tapjoy.TJAdUnit.TJAdUnitVideoListener
        public final void onVideoStart() {
            TJPlacement tJPlacementA = TJCorePlacement.this.a("SHOW");
            if (tJPlacementA == null || tJPlacementA.getVideoListener() == null) {
                return;
            }
            tJPlacementA.getVideoListener().onVideoStart(tJPlacementA);
        }
    };
    Context b = b.c();

    TJCorePlacement(String str, String str2, boolean z) {
        if (this.b == null) {
            TapjoyLog.d(a, "getVisibleActivity() is NULL. Activity can be explicitly set via `Tapjoy.setActivity(Activity)`");
        }
        this.y = z;
        this.c = new TJPlacementData(str2, getPlacementContentUrl());
        this.c.setPlacementName(str);
        this.d = UUID.randomUUID().toString();
        this.g = new TJAdUnit();
        this.g.setWebViewListener(this.z);
        this.g.setVideoListener(this.A);
    }

    static /* synthetic */ void a(TJCorePlacement tJCorePlacement, String str) throws TapjoyException {
        if (str == null) {
            throw new TapjoyException("TJPlacement request failed due to null response");
        }
        try {
            TapjoyLog.d(a, "Disable preload flag is set for placement " + tJCorePlacement.c.getPlacementName());
            tJCorePlacement.c.setRedirectURL(new JSONObject(str).getString(TapjoyConstants.TJC_REDIRECT_URL));
            tJCorePlacement.c.setPreloadDisabled(true);
            tJCorePlacement.c.setHasProgressSpinner(true);
            TapjoyLog.d(a, "redirect_url:" + tJCorePlacement.c.getRedirectURL());
        } catch (JSONException e) {
            throw new TapjoyException("TJPlacement request failed, malformed server response");
        }
    }

    /* JADX WARN: Type inference failed for: r0v16, types: [com.tapjoy.TJCorePlacement$3] */
    private synchronized void a(final String str, Map map) {
        String strA = null;
        synchronized (this) {
            if (this.w) {
                TapjoyLog.i(a, "Placement " + this.c.getPlacementName() + " is already requesting content");
                gf.b("TJPlacement.requestContent").b("already doing").c();
            } else {
                this.c.resetPlacementRequestData();
                fw fwVar = this.f;
                fwVar.b = null;
                fwVar.d = null;
                fwVar.a = null;
                this.g.resetContentLoadState();
                this.w = false;
                this.x = false;
                this.k = false;
                this.l = false;
                this.i = null;
                this.v = null;
                this.w = true;
                final TJPlacement tJPlacementA = a("REQUEST");
                if (this.y) {
                    this.s = TapjoyConnectCore.getLimitedGenericURLParams();
                    this.s.putAll(TapjoyConnectCore.getLimitedTimeStampAndVerifierParams());
                } else {
                    this.s = TapjoyConnectCore.getGenericURLParams();
                    this.s.putAll(TapjoyConnectCore.getTimeStampAndVerifierParams());
                }
                TapjoyUtil.safePut(this.s, "event_name", this.c.getPlacementName(), true);
                TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PLACEMENT_PRELOAD, "true", true);
                TapjoyUtil.safePut(this.s, "debug", Boolean.toString(gw.a), true);
                gz gzVarA = gz.a();
                Map map2 = this.s;
                if (gzVarA.b != null) {
                    hi hiVar = gzVarA.b;
                    hiVar.b();
                    strA = hiVar.b.a();
                }
                TapjoyUtil.safePut(map2, TJAdUnitConstants.PARAM_ACTION_ID_EXCLUSION, strA, true);
                TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PLACEMENT_BY_SDK, String.valueOf(this.j), true);
                TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PUSH_ID, tJPlacementA.pushId, true);
                TapjoyUtil.safePut(this.s, TapjoyConstants.TJC_MEDIATION_SOURCE, this.m, true);
                TapjoyUtil.safePut(this.s, TapjoyConstants.TJC_ADAPTER_VERSION, this.n, true);
                if (!jq.c(TapjoyConnectCore.getCustomParameter())) {
                    TapjoyUtil.safePut(this.s, TapjoyConstants.TJC_CUSTOM_PARAMETER, TapjoyConnectCore.getCustomParameter(), true);
                }
                if (map != null) {
                    this.s.putAll(map);
                }
                final fi fiVar = new fi(ga.b().b("placement_request_content_retry_timeout"));
                final gi giVarC = ga.b().c("placement_request_content_retry_backoff");
                final gf.a aVarD = gf.d("TJPlacement.requestContent");
                new Thread() { // from class: com.tapjoy.TJCorePlacement.3
                    private boolean a() {
                        TapjoyLog.i(TJCorePlacement.a, "Sending content request for placement " + TJCorePlacement.g(TJCorePlacement.this));
                        TJCorePlacement tJCorePlacement = TJCorePlacement.this;
                        gz gzVarA2 = gz.a();
                        String strG = TJCorePlacement.g(TJCorePlacement.this);
                        Context context = TJCorePlacement.this.b;
                        hh hhVar = gzVarA2.a;
                        fb fbVarA = hhVar.a.a(false);
                        tJCorePlacement.v = new ij(hhVar.a, fbVarA.d, fbVarA.e, fbVarA.f, strG, context);
                        TapjoyHttpURLResponse responseFromURL = new TapjoyURLConnection().getResponseFromURL(str, (Map) null, (Map) null, TJCorePlacement.this.s);
                        TJCorePlacement.this.c.setHttpStatusCode(responseFromURL.statusCode);
                        TJCorePlacement.this.c.setHttpResponse(responseFromURL.response);
                        if (!responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_PRERENDER_HEADER).equals("0")) {
                            TJCorePlacement.this.c.setPrerenderingRequested(true);
                        }
                        String headerFieldAsString = responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_DEBUG_HEADER);
                        if (headerFieldAsString != null) {
                            TapjoyLog.v(TJCorePlacement.a, "Tapjoy-Server-Debug: " + headerFieldAsString);
                        }
                        if (responseFromURL.expires > 0) {
                            long jB = responseFromURL.expires - (responseFromURL.date > 0 ? responseFromURL.date : v.b());
                            if (jB > 0) {
                                TJCorePlacement.this.e = jB + SystemClock.elapsedRealtime();
                            }
                        } else {
                            TJCorePlacement.this.e = 0L;
                        }
                        if (responseFromURL != null && tJPlacementA.getListener() != null) {
                            switch (responseFromURL.statusCode) {
                                case 0:
                                    if (!fiVar.a(giVarC.e)) {
                                        gi giVar = giVarC;
                                        long j = giVar.e;
                                        long j2 = (long) (giVar.e * giVar.d);
                                        if (j2 < giVar.b) {
                                            j2 = giVar.b;
                                        } else if (j2 > giVar.c) {
                                            j2 = giVar.c;
                                        }
                                        giVar.e = j2;
                                        if (j > 0) {
                                            synchronized (giVar) {
                                                try {
                                                    giVar.wait(j);
                                                    break;
                                                } catch (InterruptedException e) {
                                                }
                                                break;
                                            }
                                        }
                                        return false;
                                    }
                                    gf.b("TJPlacement.requestContent").a("network error").a("retry_timeout", Long.valueOf(fiVar.b)).c();
                                    TJCorePlacement.this.a(tJPlacementA, TapjoyErrorMessage.ErrorType.NETWORK_ERROR, new TJError(responseFromURL.statusCode, responseFromURL.response));
                                    break;
                                    break;
                                case 200:
                                    TJCorePlacement.j(TJCorePlacement.this);
                                    String headerFieldAsString2 = responseFromURL.getHeaderFieldAsString(HttpRequest.HEADER_CONTENT_TYPE);
                                    if (jq.c(headerFieldAsString2) || !headerFieldAsString2.contains("json")) {
                                        gf.b("TJPlacement.requestContent").a("content_type", "ad").c();
                                        TJCorePlacement.this.f.a = TJCorePlacement.this.t;
                                        TJCorePlacement.l(TJCorePlacement.this);
                                        final TJCorePlacement tJCorePlacement2 = TJCorePlacement.this;
                                        final TJCacheListener tJCacheListener = new TJCacheListener() { // from class: com.tapjoy.TJCorePlacement.3.1
                                            @Override // com.tapjoy.TJCacheListener
                                            public final void onCachingComplete(int i) {
                                                TJCorePlacement.this.u = TJCorePlacement.this.getAdUnit().preload(TJCorePlacement.this.c, TJCorePlacement.this.b);
                                            }
                                        };
                                        TapjoyLog.i(TJCorePlacement.a, "Checking if there is content to cache for placement " + tJCorePlacement2.c.getPlacementName());
                                        String headerFieldAsString3 = responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_CACHE_HEADER);
                                        try {
                                            if (TJPlacementManager.canCachePlacement()) {
                                                JSONArray jSONArray = new JSONArray(headerFieldAsString3);
                                                if (jSONArray.length() > 0) {
                                                    TapjoyLog.i(TJCorePlacement.a, "Begin caching content for placement " + tJCorePlacement2.c.getPlacementName());
                                                    TJPlacementManager.incrementPlacementCacheCount();
                                                    tJCorePlacement2.h = true;
                                                    TapjoyCache.getInstance().cacheAssetGroup(jSONArray, new TJCacheListener() { // from class: com.tapjoy.TJCorePlacement.6
                                                        @Override // com.tapjoy.TJCacheListener
                                                        public final void onCachingComplete(int i) {
                                                            tJCacheListener.onCachingComplete(i);
                                                        }
                                                    });
                                                } else {
                                                    tJCacheListener.onCachingComplete(1);
                                                }
                                            } else {
                                                TapjoyLog.i(TJCorePlacement.a, "Placement caching limit reached. No content will be cached for placement " + tJCorePlacement2.c.getPlacementName());
                                                tJCacheListener.onCachingComplete(2);
                                            }
                                        } catch (Exception e2) {
                                            tJCacheListener.onCachingComplete(2);
                                            TapjoyLog.d(TJCorePlacement.a, "Error while handling placement cache: " + e2.getMessage());
                                        }
                                    } else if (responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_DISABLE_PRELOAD_HEADER).equals("1")) {
                                        try {
                                            TJCorePlacement.a(TJCorePlacement.this, responseFromURL.response);
                                            gf.b("TJPlacement.requestContent").a("content_type", "ad").c();
                                            TJCorePlacement.this.f.a = TJCorePlacement.this.t;
                                            TJCorePlacement.l(TJCorePlacement.this);
                                            TJCorePlacement.this.e();
                                        } catch (TapjoyException e3) {
                                            String str2 = e3.getMessage() + " for placement " + TJCorePlacement.g(TJCorePlacement.this);
                                            gf.b("TJPlacement.requestContent").a("server error").c();
                                            TJCorePlacement.this.a(tJPlacementA, TapjoyErrorMessage.ErrorType.SERVER_ERROR, new TJError(responseFromURL.statusCode, str2));
                                        }
                                    } else if (!TJCorePlacement.this.b(responseFromURL.response)) {
                                        gf.b("TJPlacement.requestContent").a("asset error").c();
                                        TJCorePlacement.this.a(tJPlacementA, TapjoyErrorMessage.ErrorType.SERVER_ERROR, new TJError(responseFromURL.statusCode, responseFromURL.response));
                                    } else {
                                        gf.b("TJPlacement.requestContent").a("content_type", "mm").c();
                                        TJCorePlacement.l(TJCorePlacement.this);
                                        TJCorePlacement.this.e();
                                    }
                                    break;
                                default:
                                    gf.b("TJPlacement.requestContent").a("content_type", "none").a("code", Integer.valueOf(responseFromURL.statusCode)).c();
                                    TJCorePlacement.this.b(tJPlacementA);
                                    break;
                            }
                        }
                        TJCorePlacement.m(TJCorePlacement.this);
                        return true;
                    }

                    @Override // java.lang.Thread, java.lang.Runnable
                    public final void run() {
                        gf.a("TJPlacement.requestContent", aVarD);
                        int i = 0;
                        while (!a()) {
                            i++;
                            TJCorePlacement.this.s.put(TapjoyConstants.TJC_RETRY, Integer.toString(i));
                            if (i == 1) {
                                aVarD.a("retry_timeout", Long.valueOf(fiVar.b));
                            }
                            aVarD.a("retry_count", i);
                        }
                    }
                }.start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(TJPlacement tJPlacement) {
        fw fwVar = this.f;
        String placementName = this.c.getPlacementName();
        String placementType = this.c.getPlacementType();
        String strA = a();
        fwVar.c = 0;
        fwVar.b = gf.e("PlacementContent.funnel").a().a("placement", placementName).a("placement_type", placementType).a("content_type", strA).a("state", Integer.valueOf(fwVar.c));
        fwVar.b.c();
        if (!"none".equals(strA)) {
            fwVar.e = gf.e("PlacementContent.ready").a().a("placement", placementName).a("placement_type", placementType).a("content_type", strA);
        }
        if (tJPlacement == null || tJPlacement.getListener() == null) {
            return;
        }
        TapjoyLog.i(a, "Content request delivered successfully for placement " + this.c.getPlacementName() + ", contentAvailable: " + isContentAvailable() + ", mediationAgent: " + this.o);
        tJPlacement.getListener().onRequestSuccess(tJPlacement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        try {
            ij.a aVar = (ij.a) this.v.a(URI.create(this.c.getUrl()), new ByteArrayInputStream(str.getBytes()));
            this.i = aVar.a;
            aVar.a.b();
            if (!aVar.a.c()) {
                TapjoyLog.e(a, "Failed to load fiverocks placement");
                return false;
            }
            fq fvVar = null;
            if (this.i instanceof he) {
                fvVar = new fu(this.c.getPlacementName(), this.c.getPlacementType(), this.t);
            } else if (this.i instanceof gv) {
                fvVar = new fv(this.c.getPlacementName(), this.c.getPlacementType(), this.t);
            }
            this.f.a = fvVar;
            return true;
        } catch (cb e) {
            TapjoyLog.e(a, e.toString());
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            TapjoyLog.e(a, e2.toString());
            e2.printStackTrace();
            return false;
        }
    }

    static /* synthetic */ boolean c(TJCorePlacement tJCorePlacement) {
        tJCorePlacement.h = false;
        return false;
    }

    private synchronized void d() {
        String url = this.c.getUrl();
        if (jq.c(url)) {
            url = getPlacementContentUrl();
            if (jq.c(url)) {
                gf.b("TJPlacement.requestContent").a("TJPlacement is missing APP_ID").c();
                a(TapjoyErrorMessage.ErrorType.SDK_ERROR, new TJError(0, "TJPlacement is missing APP_ID"));
            } else {
                this.c.updateUrl(url);
                TapjoyLog.d(a, "sendContentRequest -- URL: " + url + " name: " + this.c.getPlacementName());
                a(url, (Map) null);
            }
        } else {
            TapjoyLog.d(a, "sendContentRequest -- URL: " + url + " name: " + this.c.getPlacementName());
            a(url, (Map) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.x) {
            return;
        }
        this.l = true;
        TapjoyLog.i(a, "Content is ready for placement " + this.c.getPlacementName());
        if (this.g.isPrerendered()) {
            fw fwVar = this.f;
            gf.a aVar = fwVar.b;
            if (aVar != null) {
                aVar.a(SettingsJsonConstants.ICON_PRERENDERED_KEY, (Object) true);
            }
            gf.a aVar2 = fwVar.e;
            if (aVar2 != null) {
                aVar2.a(SettingsJsonConstants.ICON_PRERENDERED_KEY, (Object) true);
            }
        }
        fw fwVar2 = this.f;
        gf.a aVar3 = fwVar2.e;
        if (aVar3 != null) {
            fwVar2.e = null;
            aVar3.b().c();
        }
        TJPlacement tJPlacementA = a("REQUEST");
        if (tJPlacementA == null || tJPlacementA.getListener() == null) {
            return;
        }
        tJPlacementA.getListener().onContentReady(tJPlacementA);
        this.x = true;
    }

    static /* synthetic */ void e(TJCorePlacement tJCorePlacement) {
        TJPlacement tJPlacementA = tJCorePlacement.a("SHOW");
        TapjoyLog.i(a, "Handle onClick for placement " + tJCorePlacement.c.getPlacementName());
        if (tJPlacementA == null || tJPlacementA.getListener() == null) {
            return;
        }
        tJPlacementA.getListener().onClick(tJPlacementA);
    }

    static /* synthetic */ String g(TJCorePlacement tJCorePlacement) {
        return tJCorePlacement.c.getPlacementName();
    }

    static /* synthetic */ void j(TJCorePlacement tJCorePlacement) {
        tJCorePlacement.t = new fm(tJCorePlacement.c.getPlacementName(), tJCorePlacement.c.getPlacementType());
        tJCorePlacement.g.setAdContentTracker(tJCorePlacement.t);
    }

    static /* synthetic */ void l(TJCorePlacement tJCorePlacement) {
        tJCorePlacement.k = true;
        tJCorePlacement.b(tJCorePlacement.a("REQUEST"));
    }

    static /* synthetic */ boolean m(TJCorePlacement tJCorePlacement) {
        tJCorePlacement.w = false;
        return false;
    }

    final TJPlacement a(String str) {
        TJPlacement tJPlacement;
        synchronized (this.r) {
            tJPlacement = (TJPlacement) this.r.get(str);
            if (tJPlacement != null) {
                TapjoyLog.d(a, "Returning " + str + " placement: " + tJPlacement.getGUID());
            }
        }
        return tJPlacement;
    }

    final String a() {
        if (this.i != null) {
            return "mm";
        }
        return this.k ? "ad" : "none";
    }

    final void a(TJPlacement tJPlacement) {
        if (tJPlacement == null) {
            a(TapjoyErrorMessage.ErrorType.SDK_ERROR, new TJError(0, "Cannot request content from a NULL placement"));
            return;
        }
        a("REQUEST", tJPlacement);
        if (this.e - SystemClock.elapsedRealtime() > 0) {
            TapjoyLog.d(a, "Content has not expired yet for " + this.c.getPlacementName());
            if (!this.k) {
                gf.b("TJPlacement.requestContent").a("content_type", "none").a("from", "cache").c();
                b(tJPlacement);
                return;
            } else {
                gf.b("TJPlacement.requestContent").a("content_type", a()).a("from", "cache").c();
                this.x = false;
                b(tJPlacement);
                e();
                return;
            }
        }
        if (this.k) {
            gf.c("TJPlacement.requestContent").a("was_available", (Object) true);
        }
        if (this.l) {
            gf.c("TJPlacement.requestContent").a("was_ready", (Object) true);
        }
        if (!(!jq.c(this.o))) {
            d();
            return;
        }
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_AGENT, this.o);
        map.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_ID, this.p);
        if (!((this.q == null || this.q.isEmpty()) ? false : true)) {
            a(this.c.getMediationURL(), map);
            return;
        }
        for (String str : this.q.keySet()) {
            map.put(TJAdUnitConstants.AUCTION_PARAM_PREFIX + str, this.q.get(str));
        }
        a(this.c.getAuctionMediationURL(), map);
    }

    final void a(TJPlacement tJPlacement, TapjoyErrorMessage.ErrorType errorType, TJError tJError) {
        TapjoyLog.e(a, new TapjoyErrorMessage(errorType, "Content request failed for placement " + this.c.getPlacementName() + "; Reason= " + tJError.message));
        if (tJPlacement == null || tJPlacement.getListener() == null) {
            return;
        }
        tJPlacement.getListener().onRequestFailure(tJPlacement, tJError);
    }

    final void a(TapjoyErrorMessage.ErrorType errorType, TJError tJError) {
        a(a("REQUEST"), errorType, tJError);
    }

    final void a(String str, TJPlacement tJPlacement) {
        synchronized (this.r) {
            this.r.put(str, tJPlacement);
            if (tJPlacement != null) {
                TapjoyLog.d(a, "Setting " + str + " placement: " + tJPlacement.getGUID());
            }
        }
    }

    final String b() {
        return !this.y ? TapjoyConnectCore.getAppID() : TapjoyConnectCore.getLimitedAppID();
    }

    public TJAdUnit getAdUnit() {
        return this.g;
    }

    public Context getContext() {
        return this.b;
    }

    public String getPlacementContentUrl() {
        String strB = b();
        if (!jq.c(strB)) {
            return TapjoyConnectCore.getPlacementURL() + "v1/apps/" + strB + "/content?";
        }
        TapjoyLog.i(a, "Placement content URL cannot be generated for null app ID");
        return "";
    }

    public TJPlacementData getPlacementData() {
        return this.c;
    }

    public boolean isContentAvailable() {
        return this.k;
    }

    public boolean isContentReady() {
        return this.l;
    }

    public boolean isLimited() {
        return this.y;
    }

    public void setContext(Context context) {
        this.b = context;
    }
}
