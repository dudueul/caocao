package com.tapjoy;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.al;
import com.tapjoy.internal.eq;
import com.tapjoy.internal.jq;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SuppressLint({"SetJavaScriptEnabled"})
public class TJAdUnitJSBridge implements TJWebViewJSInterfaceListener {
    public TJAdUnit a;
    public boolean allowRedirect;
    public WebView b;
    final ConcurrentLinkedQueue c;
    public boolean closeRequested;
    public boolean customClose;
    private TJWebViewJSInterface d;
    public boolean didLaunchOtherActivity;
    private TJAdUnitJSBridge e;
    private Context f;
    private TJAdUnitActivity g;
    private TJSplitWebView h;
    private ProgressDialog i;
    private View j;
    private boolean k;
    private eq l;
    public String otherActivityCallbackID;
    public String splitWebViewCallbackID;

    public interface AdUnitAsyncTaskListner {
        void onComplete(boolean z);
    }

    @TargetApi(11)
    class a extends AsyncTask {
        WebView a;

        public a(WebView webView) {
            this.a = webView;
        }

        @Override // android.os.AsyncTask
        protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            return (Boolean[]) objArr;
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(Object obj) {
            Boolean[] boolArr = (Boolean[]) obj;
            final boolean zBooleanValue = boolArr[0].booleanValue();
            final boolean zBooleanValue2 = boolArr[1].booleanValue();
            if (TJAdUnitJSBridge.this.f instanceof Activity) {
                TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnitJSBridge.a.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (!zBooleanValue) {
                            a.this.a.setVisibility(4);
                            if (a.this.a.getParent() instanceof RelativeLayout) {
                                ((RelativeLayout) a.this.a.getParent()).getBackground().setAlpha(0);
                                ((RelativeLayout) a.this.a.getParent()).setBackgroundColor(0);
                                return;
                            }
                            return;
                        }
                        a.this.a.setVisibility(0);
                        if (zBooleanValue2) {
                            if (a.this.a.getParent() instanceof RelativeLayout) {
                                ((RelativeLayout) a.this.a.getParent()).getBackground().setAlpha(0);
                                ((RelativeLayout) a.this.a.getParent()).setBackgroundColor(0);
                            }
                            if (Build.VERSION.SDK_INT >= 11) {
                                a.this.a.setLayerType(1, null);
                                return;
                            }
                            return;
                        }
                        if (a.this.a.getParent() instanceof RelativeLayout) {
                            ((RelativeLayout) a.this.a.getParent()).getBackground().setAlpha(255);
                            ((RelativeLayout) a.this.a.getParent()).setBackgroundColor(-1);
                        }
                        if (Build.VERSION.SDK_INT >= 11) {
                            a.this.a.setLayerType(0, null);
                        }
                    }
                });
            } else {
                TapjoyLog.e("TJAdUnitJSBridge", "Unable to present offerwall. No Activity context provided.");
            }
        }
    }

    public TJAdUnitJSBridge(Context context, WebView webView) {
        this.j = null;
        this.didLaunchOtherActivity = false;
        this.allowRedirect = true;
        this.otherActivityCallbackID = null;
        this.customClose = false;
        this.closeRequested = false;
        this.splitWebViewCallbackID = null;
        this.l = new eq(this);
        this.c = new ConcurrentLinkedQueue();
        TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
        this.f = context;
        this.b = webView;
        this.e = this;
        if (this.b == null) {
            TapjoyLog.e("TJAdUnitJSBridge", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Cannot create AdUnitJSBridge -- webview is NULL"));
            return;
        }
        this.d = new TJWebViewJSInterface(this.b, this);
        this.b.addJavascriptInterface(this.d, TJAdUnitConstants.JAVASCRIPT_INTERFACE_ID);
        setEnabled(true);
    }

    public TJAdUnitJSBridge(Context context, TJAdUnit tJAdUnit) {
        this(context, tJAdUnit.getWebView());
        this.a = tJAdUnit;
    }

    public void alert(JSONObject jSONObject, final String str) throws JSONException {
        JSONArray jSONArray;
        int i;
        TapjoyLog.d("TJAdUnitJSBridge", "alert_method: " + jSONObject);
        String string = "";
        String string2 = "";
        try {
            string = jSONObject.getString("title");
            string2 = jSONObject.getString("message");
            jSONArray = jSONObject.getJSONArray("buttons");
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
            e.printStackTrace();
            jSONArray = null;
        }
        TJAdUnitActivity tJAdUnitActivity = this.g;
        if (tJAdUnitActivity == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "Cannot alert -- TJAdUnitActivity is null");
            return;
        }
        AlertDialog alertDialogCreate = Build.VERSION.SDK_INT >= 21 ? new AlertDialog.Builder(tJAdUnitActivity, R.style.Theme.Material.Light.Dialog.Alert).setTitle(string).setMessage(string2).create() : new AlertDialog.Builder(tJAdUnitActivity).setTitle(string).setMessage(string2).create();
        if (jSONArray == null || jSONArray.length() == 0) {
            invokeJSCallback(str, Boolean.FALSE);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            switch (i2) {
                case 0:
                    i = -2;
                    break;
                case 1:
                    i = -3;
                    break;
                default:
                    i = -1;
                    break;
            }
            try {
                arrayList.add(jSONArray.getString(i2));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            alertDialogCreate.setButton(i, (CharSequence) arrayList.get(i2), new DialogInterface.OnClickListener() { // from class: com.tapjoy.TJAdUnitJSBridge.1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    int i4 = 0;
                    switch (i3) {
                        case -3:
                            i4 = 1;
                            break;
                        case -1:
                            i4 = 2;
                            break;
                    }
                    try {
                        TJAdUnitJSBridge.this.invokeJSCallback(str, Integer.valueOf(i4));
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            });
        }
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
    }

    public void attachVolumeListener(JSONObject jSONObject, String str) {
        try {
            boolean z = jSONObject.getBoolean(TJAdUnitConstants.String.ATTACH);
            int iOptInt = jSONObject.optInt(TJAdUnitConstants.String.INTERVAL, 500);
            if (iOptInt > 0) {
                this.a.attachVolumeListener(z, iOptInt);
                invokeJSCallback(str, true);
            } else {
                TapjoyLog.d("TJAdUnitJSBridge", "Invalid `interval` value passed to attachVolumeListener(): interval=" + iOptInt);
                invokeJSCallback(str, false);
            }
        } catch (Exception e) {
            TapjoyLog.d("TJAdUnitJSBridge", "attachVolumeListener exception " + e.toString());
            invokeJSCallback(str, false);
            e.printStackTrace();
        }
    }

    public void cacheAsset(JSONObject jSONObject, String str) {
        String string = "";
        Long lValueOf = 0L;
        try {
            String string2 = jSONObject.getString("url");
            try {
                string = jSONObject.getString(TapjoyConstants.TJC_PLACEMENT_OFFER_ID);
            } catch (Exception e) {
            }
            try {
                lValueOf = Long.valueOf(jSONObject.getLong(TapjoyConstants.TJC_TIME_TO_LIVE));
            } catch (Exception e2) {
            }
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(str, TapjoyCache.getInstance().cacheAssetFromURL(string2, string, lValueOf.longValue()));
            } else {
                invokeJSCallback(str, Boolean.FALSE);
            }
        } catch (Exception e3) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void cachePathForURL(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString("url");
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(str, TapjoyCache.getInstance().getPathOfCachedURL(string));
            } else {
                invokeJSCallback(str, "");
            }
        } catch (Exception e) {
            invokeJSCallback(str, "");
        }
    }

    public void clearCache(JSONObject jSONObject, String str) {
        if (TapjoyCache.getInstance() == null) {
            invokeJSCallback(str, Boolean.FALSE);
        } else {
            TapjoyCache.getInstance().clearTapjoyCache();
            invokeJSCallback(str, Boolean.TRUE);
        }
    }

    public void clearLoggingLevel(JSONObject jSONObject, String str) {
        TapjoyAppSettings.getInstance().clearLoggingLevel();
    }

    public void clearVideo(JSONObject jSONObject, final String str) {
        if (this.a != null) {
            this.a.clearVideo(new AdUnitAsyncTaskListner() { // from class: com.tapjoy.TJAdUnitJSBridge.10
                @Override // com.tapjoy.TJAdUnitJSBridge.AdUnitAsyncTaskListner
                public final void onComplete(boolean z) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.valueOf(z));
                }
            }, jSONObject.optBoolean(TJAdUnitConstants.String.VISIBLE, false));
        }
    }

    public void closeRequested(Boolean bool) {
        if (this.h != null) {
            if (this.h.goBack()) {
                return;
            }
            this.h.a();
        } else {
            this.closeRequested = true;
            HashMap map = new HashMap();
            map.put(TJAdUnitConstants.String.FORCE_CLOSE, bool);
            invokeJSAdunitMethod(TJAdUnitConstants.String.CLOSE_REQUESTED, map);
        }
    }

    public void contentReady(JSONObject jSONObject, String str) {
        if (this.a == null) {
            invokeJSCallback(str, false);
        } else {
            this.a.fireContentReady();
            invokeJSCallback(str, true);
        }
    }

    public void destroy() {
    }

    public void dismiss(JSONObject jSONObject, String str) {
        TJAdUnitActivity tJAdUnitActivity = this.g;
        if (tJAdUnitActivity != null) {
            invokeJSCallback(str, true);
            tJAdUnitActivity.finish();
        } else {
            TapjoyLog.d("TJAdUnitJSBridge", "Cannot dismiss -- TJAdUnitActivity is null");
            invokeJSCallback(str, false);
        }
    }

    public void dismissSplitView(JSONObject jSONObject, final String str) {
        TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnitJSBridge.4
            @Override // java.lang.Runnable
            public final void run() {
                if (TJAdUnitJSBridge.this.h == null) {
                    if (str != null) {
                        TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.FALSE);
                        return;
                    }
                    return;
                }
                if (str != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.TRUE);
                }
                if (TJAdUnitJSBridge.this.splitWebViewCallbackID != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(TJAdUnitJSBridge.this.splitWebViewCallbackID, Boolean.TRUE);
                    TJAdUnitJSBridge.this.splitWebViewCallbackID = null;
                }
                ((ViewGroup) TJAdUnitJSBridge.this.h.getParent()).removeView(TJAdUnitJSBridge.this.h);
                TJAdUnitJSBridge.this.h = null;
            }
        });
    }

    public void dismissStoreView(JSONObject jSONObject, String str) {
        dismissSplitView(jSONObject, str);
    }

    public void display() {
        invokeJSAdunitMethod("display", new Object[0]);
    }

    public void displayStoreURL(JSONObject jSONObject, String str) {
        displayURL(jSONObject, str);
    }

    public void displayURL(final JSONObject jSONObject, final String str) {
        final String strOptString;
        final String strOptString2;
        try {
            String strOptString3 = jSONObject.optString("style");
            final String string = jSONObject.getString("url");
            final JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(TJAdUnitConstants.String.SPLIT_VIEW_LAYOUT);
            final JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(TJAdUnitConstants.String.SPLIT_VIEW_EXIT_HOSTS);
            final String strOptString4 = jSONObject.optString(TJAdUnitConstants.String.USER_AGENT, null);
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(TJAdUnitConstants.String.SPLIT_VIEW_TRIGGER);
            if (jSONObjectOptJSONObject2 != null) {
                strOptString2 = jSONObjectOptJSONObject2.optString(TJAdUnitConstants.String.SPLIT_VIEW_TRIGGER_ON, null);
                strOptString = jSONObjectOptJSONObject2.optString("to", null);
            } else {
                strOptString = null;
                strOptString2 = null;
            }
            if (TJAdUnitConstants.String.STYLE_SPLIT.equals(strOptString3)) {
                TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnitJSBridge.6
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (TJAdUnitJSBridge.this.b != null) {
                            if (TJAdUnitJSBridge.this.h == null) {
                                ViewParent parent = TJAdUnitJSBridge.this.b.getParent();
                                if (parent instanceof ViewGroup) {
                                    ViewGroup viewGroup = (ViewGroup) parent;
                                    TJAdUnitJSBridge.this.h = new TJSplitWebView(TJAdUnitJSBridge.this.g, jSONObject, TJAdUnitJSBridge.this);
                                    viewGroup.addView(TJAdUnitJSBridge.this.h, new RelativeLayout.LayoutParams(-1, -1));
                                    TJAdUnitJSBridge.this.h.animateOpen(viewGroup);
                                }
                            } else {
                                TJAdUnitJSBridge.this.h.setExitHosts(jSONArrayOptJSONArray);
                                TJAdUnitJSBridge.this.h.applyLayoutOption(jSONObjectOptJSONObject);
                            }
                            if (TJAdUnitJSBridge.this.h != null) {
                                if (strOptString4 != null) {
                                    TJAdUnitJSBridge.this.h.setUserAgent(strOptString4);
                                }
                                TJAdUnitJSBridge.this.h.setTrigger(strOptString2, strOptString);
                                TJAdUnitJSBridge.this.splitWebViewCallbackID = str;
                                TJAdUnitJSBridge.this.h.loadUrl(string);
                                return;
                            }
                        }
                        TJAdUnitJSBridge.this.h = null;
                        TJAdUnitJSBridge.this.splitWebViewCallbackID = null;
                        TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.FALSE);
                    }
                });
                return;
            }
            this.didLaunchOtherActivity = true;
            this.otherActivityCallbackID = str;
            this.f.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.TRUE);
            e.printStackTrace();
        }
    }

    public void displayVideo(JSONObject jSONObject, final String str) {
        try {
            String string = jSONObject.getString("url");
            if (string.length() <= 0 || string == "") {
                invokeJSCallback(str, Boolean.FALSE);
            } else {
                this.a.loadVideoUrl(string, new AdUnitAsyncTaskListner() { // from class: com.tapjoy.TJAdUnitJSBridge.9
                    @Override // com.tapjoy.TJAdUnitJSBridge.AdUnitAsyncTaskListner
                    public final void onComplete(boolean z) {
                        TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.valueOf(z));
                    }
                });
            }
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0023, code lost:
    
        invokeJSCallback(r8, false);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void endUsageTrackingEvent(org.json.JSONObject r7, java.lang.String r8) {
        /*
            r6 = this;
            r5 = 1
            r4 = 0
            java.lang.String r0 = "name"
            java.lang.String r0 = r7.getString(r0)     // Catch: org.json.JSONException -> L3c
            boolean r1 = r0.isEmpty()     // Catch: org.json.JSONException -> L3c
            if (r1 == 0) goto L24
            java.lang.String r0 = "TJAdUnitJSBridge"
            java.lang.String r1 = "Empty name for endUsageTrackingEvent"
            com.tapjoy.TapjoyLog.d(r0, r1)     // Catch: org.json.JSONException -> L3c
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: org.json.JSONException -> L3c
            r1 = 0
            r2 = 0
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: org.json.JSONException -> L3c
            r0[r1] = r2     // Catch: org.json.JSONException -> L3c
            r6.invokeJSCallback(r8, r0)     // Catch: org.json.JSONException -> L3c
        L23:
            return
        L24:
            com.tapjoy.TJAdUnit r1 = r6.a     // Catch: org.json.JSONException -> L3c
            if (r1 == 0) goto L51
            com.tapjoy.TJAdUnit r1 = r6.a     // Catch: org.json.JSONException -> L3c
            r1.endAdContentTracking(r0, r7)     // Catch: org.json.JSONException -> L3c
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: org.json.JSONException -> L3c
            r1 = 0
            r2 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: org.json.JSONException -> L3c
            r0[r1] = r2     // Catch: org.json.JSONException -> L3c
            r6.invokeJSCallback(r8, r0)     // Catch: org.json.JSONException -> L3c
            goto L23
        L3c:
            r0 = move-exception
            java.lang.String r1 = "TJAdUnitJSBridge"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unable to endUsageTrackingEvent. Invalid parameters: "
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tapjoy.TapjoyLog.w(r1, r0)
        L51:
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
            r0[r4] = r1
            r6.invokeJSCallback(r8, r0)
            goto L23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJAdUnitJSBridge.endUsageTrackingEvent(org.json.JSONObject, java.lang.String):void");
    }

    public void flushBacklogMessageQueue() {
        while (true) {
            Pair pair = (Pair) this.c.poll();
            if (pair == null) {
                return;
            } else {
                onDispatchMethod((String) pair.first, (JSONObject) pair.second);
            }
        }
    }

    public void flushMessageQueue() {
        this.d.flushMessageQueue();
    }

    public void getCachedAssets(JSONObject jSONObject, String str) {
        if (TapjoyCache.getInstance() != null) {
            invokeJSCallback(str, TapjoyCache.getInstance().cachedAssetsToJSON());
        } else {
            invokeJSCallback(str, "");
        }
    }

    public void getOrientation(JSONObject jSONObject, String str) {
        if (this.a == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            invokeJSCallback(str, JSONObject.NULL);
            return;
        }
        String screenOrientationString = this.a.getScreenOrientationString();
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.ORIENTATION, screenOrientationString);
        map.put("width", Integer.valueOf(this.a.getScreenWidth()));
        map.put("height", Integer.valueOf(this.a.getScreenHeight()));
        invokeJSCallback(str, map);
    }

    public void getSplitViewURL(JSONObject jSONObject, final String str) {
        TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnitJSBridge.5
            @Override // java.lang.Runnable
            public final void run() {
                if (TJAdUnitJSBridge.this.h != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, TJAdUnitJSBridge.this.h.getLastUrl());
                } else {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, JSONObject.NULL);
                }
            }
        });
    }

    public void getVolume(JSONObject jSONObject, String str) {
        HashMap volumeArgs = getVolumeArgs();
        if (volumeArgs != null) {
            invokeJSCallback(str, volumeArgs);
        } else {
            invokeJSCallback(str, false);
        }
    }

    public HashMap getVolumeArgs() {
        if (this.a == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            return null;
        }
        String str = String.format("%.2f", Float.valueOf(this.a.getVolume()));
        boolean zIsMuted = this.a.isMuted();
        TapjoyLog.d("TJAdUnitJSBridge", "getVolumeArgs: volume=" + str + "; isMuted=" + zIsMuted);
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.CURRENT_VOLUME, str);
        map.put(TJAdUnitConstants.String.IS_MUTED, Boolean.valueOf(zIsMuted));
        return map;
    }

    public void hasSplitView(JSONObject jSONObject, final String str) {
        TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnitJSBridge.3
            @Override // java.lang.Runnable
            public final void run() {
                if (TJAdUnitJSBridge.this.h != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.TRUE);
                } else {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.FALSE);
                }
            }
        });
    }

    public void initMoatVideoTracker(JSONObject jSONObject, String str) {
        invokeJSCallback(str, true);
    }

    public void initViewabilityTracker(final JSONObject jSONObject, final String str) {
        final eq eqVar = this.l;
        if (!eqVar.a(jSONObject)) {
            eqVar.a.invokeJSCallback(str, false);
            return;
        }
        eq.b(jSONObject);
        if (al.a(eq.b)) {
            eqVar.a.invokeJSCallback(str, false);
        } else {
            TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.internal.eq.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (!eq.this.c) {
                            eq eqVar2 = eq.this;
                            ck.a();
                            eqVar2.c = ck.a(eq.this.a.b.getContext());
                        }
                        if (!eq.this.c) {
                            TapjoyLog.d("TJOMViewabilityAgent", "Failed to initialize");
                            eq.this.a.invokeJSCallback(str, false);
                            return;
                        }
                        TapjoyLog.d("TJOMViewabilityAgent", "initialized");
                        eq.this.e = cs.a(ct.a(cw.NATIVE, cw.NATIVE), cu.a(eq.this.f, eq.b, eq.b(jSONObject.optJSONArray(TJAdUnitConstants.String.VENDORS)), ""));
                        eq.this.e.a(eq.this.a.a.getWebView());
                        eq.this.g = dc.a(eq.this.e);
                        eq.this.d = cr.a(eq.this.e);
                        eq.this.a.invokeJSCallback(str, true);
                    } catch (Exception e) {
                        TapjoyLog.d("TJOMViewabilityAgent", "Failed to init with exception: " + e.getMessage());
                        eq.this.a.invokeJSCallback(str, false);
                    }
                }
            });
        }
    }

    public void invokeJSAdunitMethod(String str, Map map) {
        this.d.callback(map, str, (String) null);
    }

    public void invokeJSAdunitMethod(String str, Object... objArr) {
        this.d.callback(new ArrayList(Arrays.asList(objArr)), str, (String) null);
    }

    public void invokeJSCallback(String str, Map map) {
        this.d.callback(map, "", str);
    }

    public void invokeJSCallback(String str, Object... objArr) {
        if (jq.c(str)) {
            TapjoyLog.d("TJAdUnitJSBridge", "invokeJSCallback -- no callbackID provided");
        } else {
            this.d.callback(new ArrayList(Arrays.asList(objArr)), "", str);
        }
    }

    public void isNetworkAvailable(JSONObject jSONObject, String str) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f.getSystemService("connectivity")).getActiveNetworkInfo();
            invokeJSCallback(str, Boolean.valueOf(activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()));
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void log(JSONObject jSONObject, String str) {
        try {
            TapjoyLog.d("TJAdUnitJSBridge", "Logging message=" + jSONObject.getString("message"));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    @TargetApi(19)
    public void nativeEval(final JSONObject jSONObject, final String str) {
        TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnitJSBridge.12
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (Build.VERSION.SDK_INT >= 19) {
                        TJAdUnitJSBridge.this.b.evaluateJavascript(jSONObject.getString(TJAdUnitConstants.String.COMMAND), null);
                    } else {
                        TJAdUnitJSBridge.this.b.loadUrl("javascript:" + jSONObject.getString(TJAdUnitConstants.String.COMMAND));
                    }
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.TRUE);
                } catch (Exception e) {
                    TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.FALSE);
                }
            }
        });
    }

    public void notifyOrientationChanged(String str, int i, int i2) {
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.ORIENTATION, str);
        map.put("width", Integer.valueOf(i));
        map.put("height", Integer.valueOf(i2));
        invokeJSAdunitMethod(TJAdUnitConstants.String.ORIENTATION_CHANGED_EVENT, map);
    }

    @Override // com.tapjoy.TJWebViewJSInterfaceListener
    public void onDispatchMethod(String str, JSONObject jSONObject) {
        String strOptString = null;
        if (!this.k) {
            TapjoyLog.d("TJAdUnitJSBridge", "Bridge currently disabled. Adding " + str + " to message queue");
            this.c.add(new Pair(str, jSONObject));
            return;
        }
        try {
            strOptString = jSONObject.optString(TJAdUnitConstants.String.CALLBACK_ID, null);
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            Method method = TJAdUnitJSBridge.class.getMethod(str, JSONObject.class, String.class);
            TapjoyLog.d("TJAdUnitJSBridge", "Dispatching method: " + method + " with data=" + jSONObject2 + "; callbackID=" + strOptString);
            method.invoke(this.e, jSONObject2, strOptString);
        } catch (Exception e) {
            e.printStackTrace();
            invokeJSCallback(strOptString, Boolean.FALSE);
        }
    }

    public void onVideoCompletion() {
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_COMPLETE_EVENT);
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, map);
    }

    public void onVideoError(String str) {
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_ERROR_EVENT);
        map.put("error", str);
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, map);
    }

    public void onVideoInfo(String str) {
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_INFO_EVENT);
        map.put(TJAdUnitConstants.String.VIDEO_INFO, str);
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, map);
    }

    public void onVideoPaused(int i) {
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_PAUSE_EVENT);
        map.put(TJAdUnitConstants.String.VIDEO_CURRENT_TIME, Integer.valueOf(i));
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, map);
    }

    public void onVideoProgress(int i) {
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_PROGRESS_EVENT);
        map.put(TJAdUnitConstants.String.VIDEO_CURRENT_TIME, Integer.valueOf(i));
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, map);
    }

    public void onVideoReady(int i, int i2, int i3) {
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_READY_EVENT);
        map.put(TJAdUnitConstants.String.VIDEO_DURATION, Integer.valueOf(i));
        map.put(TJAdUnitConstants.String.VIDEO_WIDTH, Integer.valueOf(i2));
        map.put(TJAdUnitConstants.String.VIDEO_HEIGHT, Integer.valueOf(i3));
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, map);
    }

    public void onVideoStarted(int i) {
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.VIDEO_EVENT_NAME, TJAdUnitConstants.String.VIDEO_START_EVENT);
        map.put(TJAdUnitConstants.String.VIDEO_CURRENT_TIME, Integer.valueOf(i));
        invokeJSAdunitMethod(TJAdUnitConstants.String.VIDEO_EVENT, map);
    }

    public void onVolumeChanged() {
        invokeJSAdunitMethod(TJAdUnitConstants.String.VOLUME_CHANGED, getVolumeArgs());
    }

    public void openApp(JSONObject jSONObject, String str) {
        try {
            this.f.startActivity(this.f.getPackageManager().getLaunchIntentForPackage(jSONObject.getString(TJAdUnitConstants.String.BUNDLE)));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    public void pauseVideo(JSONObject jSONObject, String str) {
        if (this.a != null) {
            invokeJSCallback(str, Boolean.valueOf(this.a.pauseVideo()));
        }
    }

    public void playVideo(JSONObject jSONObject, String str) {
        if (this.a != null) {
            invokeJSCallback(str, Boolean.valueOf(this.a.playVideo()));
        }
    }

    public void present(JSONObject jSONObject, String str) {
        try {
            Boolean.valueOf(false);
            boolean zValueOf = false;
            Boolean boolValueOf = Boolean.valueOf(jSONObject.getString(TJAdUnitConstants.String.VISIBLE));
            try {
                zValueOf = Boolean.valueOf(jSONObject.getString(TJAdUnitConstants.String.TRANSPARENT));
            } catch (Exception e) {
            }
            try {
                this.customClose = Boolean.valueOf(jSONObject.getString(TJAdUnitConstants.String.CUSTOM_CLOSE)).booleanValue();
            } catch (Exception e2) {
            }
            new a(this.b).execute(boolValueOf, zValueOf);
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e3) {
            invokeJSCallback(str, Boolean.FALSE);
            e3.printStackTrace();
        }
    }

    public void removeAssetFromCache(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString("url");
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(str, Boolean.valueOf(TapjoyCache.getInstance().removeAssetFromCache(string)));
            } else {
                invokeJSCallback(str, Boolean.FALSE);
            }
        } catch (Exception e) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0023, code lost:
    
        invokeJSCallback(r8, false);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void sendUsageTrackingEvent(org.json.JSONObject r7, java.lang.String r8) {
        /*
            r6 = this;
            r5 = 1
            r4 = 0
            java.lang.String r0 = "name"
            java.lang.String r0 = r7.getString(r0)     // Catch: org.json.JSONException -> L3c
            boolean r1 = r0.isEmpty()     // Catch: org.json.JSONException -> L3c
            if (r1 == 0) goto L24
            java.lang.String r0 = "TJAdUnitJSBridge"
            java.lang.String r1 = "Empty name for sendUsageTrackingEvent"
            com.tapjoy.TapjoyLog.d(r0, r1)     // Catch: org.json.JSONException -> L3c
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: org.json.JSONException -> L3c
            r1 = 0
            r2 = 0
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: org.json.JSONException -> L3c
            r0[r1] = r2     // Catch: org.json.JSONException -> L3c
            r6.invokeJSCallback(r8, r0)     // Catch: org.json.JSONException -> L3c
        L23:
            return
        L24:
            com.tapjoy.TJAdUnit r1 = r6.a     // Catch: org.json.JSONException -> L3c
            if (r1 == 0) goto L51
            com.tapjoy.TJAdUnit r1 = r6.a     // Catch: org.json.JSONException -> L3c
            r1.sendAdContentTracking(r0, r7)     // Catch: org.json.JSONException -> L3c
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: org.json.JSONException -> L3c
            r1 = 0
            r2 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: org.json.JSONException -> L3c
            r0[r1] = r2     // Catch: org.json.JSONException -> L3c
            r6.invokeJSCallback(r8, r0)     // Catch: org.json.JSONException -> L3c
            goto L23
        L3c:
            r0 = move-exception
            java.lang.String r1 = "TJAdUnitJSBridge"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unable to sendUsageTrackingEvent. Invalid parameters: "
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tapjoy.TapjoyLog.w(r1, r0)
        L51:
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
            r0[r4] = r1
            r6.invokeJSCallback(r8, r0)
            goto L23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJAdUnitJSBridge.sendUsageTrackingEvent(org.json.JSONObject, java.lang.String):void");
    }

    public void setAdUnitActivity(TJAdUnitActivity tJAdUnitActivity) {
        this.g = tJAdUnitActivity;
    }

    public void setAllowRedirect(JSONObject jSONObject, String str) {
        boolean z;
        try {
            z = jSONObject.getBoolean(TJAdUnitConstants.String.ENABLED);
        } catch (Exception e) {
            z = true;
        }
        this.allowRedirect = z;
        invokeJSCallback(str, Boolean.TRUE);
    }

    public void setBackgroundColor(JSONObject jSONObject, final String str) {
        try {
            String string = jSONObject.getString(TJAdUnitConstants.String.BACKGROUND_COLOR);
            if (this.a != null) {
                this.a.setBackgroundColor(string, new AdUnitAsyncTaskListner() { // from class: com.tapjoy.TJAdUnitJSBridge.7
                    @Override // com.tapjoy.TJAdUnitJSBridge.AdUnitAsyncTaskListner
                    public final void onComplete(boolean z) {
                        TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.valueOf(z));
                    }
                });
            } else {
                invokeJSCallback(str, false);
            }
        } catch (Exception e) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background color. Invalid parameters.");
            invokeJSCallback(str, false);
        }
    }

    public void setBackgroundWebViewContent(JSONObject jSONObject, final String str) {
        TapjoyLog.d("TJAdUnitJSBridge", "setBackgroundWebViewContent");
        try {
            String string = jSONObject.getString(TJAdUnitConstants.String.BACKGROUND_CONTENT);
            if (this.a != null) {
                this.a.setBackgroundContent(string, new AdUnitAsyncTaskListner() { // from class: com.tapjoy.TJAdUnitJSBridge.8
                    @Override // com.tapjoy.TJAdUnitJSBridge.AdUnitAsyncTaskListner
                    public final void onComplete(boolean z) {
                        TJAdUnitJSBridge.this.invokeJSCallback(str, Boolean.valueOf(z));
                    }
                });
            } else {
                invokeJSCallback(str, false);
            }
        } catch (Exception e) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background content. Invalid parameters.");
            invokeJSCallback(str, false);
        }
    }

    public void setCloseButtonClickable(JSONObject jSONObject, String str) {
        try {
            final boolean zOptBoolean = jSONObject.optBoolean(TJAdUnitConstants.String.CLICKABLE);
            TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnitJSBridge.2
                @Override // java.lang.Runnable
                public final void run() {
                    TJAdUnitActivity tJAdUnitActivity = TJAdUnitJSBridge.this.g;
                    if (tJAdUnitActivity != null) {
                        tJAdUnitActivity.setCloseButtonClickable(zOptBoolean);
                    } else {
                        TapjoyLog.d("TJAdUnitJSBridge", "Cannot setCloseButtonClickable -- TJAdUnitActivity is null");
                    }
                }
            });
            invokeJSCallback(str, true);
        } catch (Exception e) {
            invokeJSCallback(str, false);
            e.printStackTrace();
        }
    }

    public void setCloseButtonVisible(JSONObject jSONObject, String str) {
        try {
            final boolean z = jSONObject.getBoolean(TJAdUnitConstants.String.VISIBLE);
            TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnitJSBridge.13
                @Override // java.lang.Runnable
                public final void run() {
                    TJAdUnitActivity tJAdUnitActivity = TJAdUnitJSBridge.this.g;
                    if (tJAdUnitActivity != null) {
                        tJAdUnitActivity.setCloseButtonVisibility(z);
                    } else {
                        TapjoyLog.d("TJAdUnitJSBridge", "Cannot setCloseButtonVisible -- TJAdUnitActivity is null");
                    }
                }
            });
            invokeJSCallback(str, true);
        } catch (Exception e) {
            invokeJSCallback(str, false);
            e.printStackTrace();
        }
    }

    public void setEnabled(boolean z) {
        this.k = z;
        if (this.k) {
            flushBacklogMessageQueue();
        }
    }

    public void setEventPreloadLimit(JSONObject jSONObject, String str) {
        if (TapjoyCache.getInstance() == null) {
            invokeJSCallback(str, Boolean.FALSE);
            return;
        }
        try {
            TJPlacementManager.setCachedPlacementLimit(jSONObject.getInt(TJAdUnitConstants.String.TJC_PLACEMENT_CACHE_LIMIT));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy cache's event preload limit. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void setLoggingLevel(JSONObject jSONObject, String str) {
        try {
            TapjoyAppSettings.getInstance().saveLoggingLevel(String.valueOf(jSONObject.getString(TJAdUnitConstants.String.LOGGING_LEVEL)));
        } catch (Exception e) {
            TapjoyLog.d("TJAdUnitJSBridge", "setLoggingLevel exception " + e.getLocalizedMessage());
            invokeJSCallback(str, false);
            e.printStackTrace();
        }
    }

    public void setOrientation(JSONObject jSONObject, String str) {
        int i;
        if (this.a == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            invokeJSCallback(str, false);
            return;
        }
        try {
            String string = jSONObject.getString(TJAdUnitConstants.String.ORIENTATION);
            if (string.equals(TJAdUnitConstants.String.LANDSCAPE) || string.equals(TJAdUnitConstants.String.LANDSCAPE_LEFT)) {
                i = 0;
            } else {
                i = string.equals(TJAdUnitConstants.String.LANDSCAPE_RIGHT) ? 8 : 1;
            }
            this.a.setOrientation(i);
            invokeJSCallback(str, true);
        } catch (Exception e) {
            invokeJSCallback(str, false);
        }
    }

    public void setPrerenderLimit(JSONObject jSONObject, String str) {
        try {
            TJPlacementManager.setPreRenderedPlacementLimit(jSONObject.getInt(TJAdUnitConstants.String.TJC_PLACEMENT_PRE_RENDERED_LIMIT));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy placement pre-render limit. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void setSpinnerVisible(JSONObject jSONObject, String str) {
        try {
            boolean z = jSONObject.getBoolean(TJAdUnitConstants.String.VISIBLE);
            String strOptString = jSONObject.optString("title");
            String strOptString2 = jSONObject.optString("message");
            TJAdUnitActivity tJAdUnitActivity = this.g;
            if (tJAdUnitActivity == null) {
                TapjoyLog.d("TJAdUnitJSBridge", "Cannot setSpinnerVisible -- TJAdUnitActivity is null");
                invokeJSCallback(str, Boolean.FALSE);
                return;
            }
            if (z) {
                this.i = ProgressDialog.show(tJAdUnitActivity, strOptString, strOptString2);
            } else if (this.i != null) {
                this.i.dismiss();
            }
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    public void setVideoMargins(JSONObject jSONObject, String str) {
        try {
            final float fOptDouble = (float) jSONObject.optDouble(TJAdUnitConstants.String.TOP, 0.0d);
            final float fOptDouble2 = (float) jSONObject.optDouble(TJAdUnitConstants.String.RIGHT, 0.0d);
            final float fOptDouble3 = (float) jSONObject.optDouble(TJAdUnitConstants.String.BOTTOM, 0.0d);
            final float fOptDouble4 = (float) jSONObject.optDouble(TJAdUnitConstants.String.LEFT, 0.0d);
            final TJAdUnitActivity tJAdUnitActivity = this.g;
            if (tJAdUnitActivity != null) {
                TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnitJSBridge.11
                    @Override // java.lang.Runnable
                    public final void run() {
                        TJAdUnitActivity tJAdUnitActivity2 = tJAdUnitActivity;
                        float f = fOptDouble4;
                        float f2 = fOptDouble;
                        float f3 = fOptDouble2;
                        float f4 = fOptDouble3;
                        DisplayMetrics displayMetrics = tJAdUnitActivity2.getResources().getDisplayMetrics();
                        int iApplyDimension = (int) TypedValue.applyDimension(1, f, displayMetrics);
                        int iApplyDimension2 = (int) TypedValue.applyDimension(1, f2, displayMetrics);
                        int iApplyDimension3 = (int) TypedValue.applyDimension(1, f3, displayMetrics);
                        int iApplyDimension4 = (int) TypedValue.applyDimension(1, f4, displayMetrics);
                        ViewGroup viewGroup = (ViewGroup) tJAdUnitActivity2.a.getVideoView().getParent();
                        ((ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams()).setMargins(iApplyDimension, iApplyDimension2, iApplyDimension3, iApplyDimension4);
                        viewGroup.requestLayout();
                    }
                });
                invokeJSCallback(str, true);
            } else {
                TapjoyLog.d("TJAdUnitJSBridge", "Cannot setVideoMargins -- TJAdUnitActivity is null");
                invokeJSCallback(str, false);
            }
        } catch (Exception e) {
            invokeJSCallback(str, false);
            e.printStackTrace();
        }
    }

    public void setVideoMute(JSONObject jSONObject, String str) {
        try {
            this.a.a(jSONObject.getBoolean(TJAdUnitConstants.String.ENABLED));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (JSONException e) {
            TapjoyLog.d("TJAdUnitJSBridge", "Failed to parse 'enabled' from json params.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void shouldClose(JSONObject jSONObject, String str) {
        TJAdUnitActivity tJAdUnitActivity = this.g;
        try {
            Boolean.valueOf(false);
            if (Boolean.valueOf(jSONObject.getString(TJAdUnitConstants.String.CLOSE)).booleanValue() && tJAdUnitActivity != null) {
                tJAdUnitActivity.finish();
            }
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
            if (tJAdUnitActivity != null) {
                tJAdUnitActivity.finish();
            }
            e.printStackTrace();
        }
        this.closeRequested = false;
    }

    public void startMoatVideoTracker(JSONObject jSONObject, String str) {
        invokeJSCallback(str, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0023, code lost:
    
        invokeJSCallback(r8, false);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void startUsageTrackingEvent(org.json.JSONObject r7, java.lang.String r8) {
        /*
            r6 = this;
            r5 = 1
            r4 = 0
            java.lang.String r0 = "name"
            java.lang.String r0 = r7.getString(r0)     // Catch: org.json.JSONException -> L3c
            boolean r1 = r0.isEmpty()     // Catch: org.json.JSONException -> L3c
            if (r1 == 0) goto L24
            java.lang.String r0 = "TJAdUnitJSBridge"
            java.lang.String r1 = "Empty name for startUsageTrackingEvent"
            com.tapjoy.TapjoyLog.d(r0, r1)     // Catch: org.json.JSONException -> L3c
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: org.json.JSONException -> L3c
            r1 = 0
            r2 = 0
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: org.json.JSONException -> L3c
            r0[r1] = r2     // Catch: org.json.JSONException -> L3c
            r6.invokeJSCallback(r8, r0)     // Catch: org.json.JSONException -> L3c
        L23:
            return
        L24:
            com.tapjoy.TJAdUnit r1 = r6.a     // Catch: org.json.JSONException -> L3c
            if (r1 == 0) goto L51
            com.tapjoy.TJAdUnit r1 = r6.a     // Catch: org.json.JSONException -> L3c
            r1.startAdContentTracking(r0, r7)     // Catch: org.json.JSONException -> L3c
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: org.json.JSONException -> L3c
            r1 = 0
            r2 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: org.json.JSONException -> L3c
            r0[r1] = r2     // Catch: org.json.JSONException -> L3c
            r6.invokeJSCallback(r8, r0)     // Catch: org.json.JSONException -> L3c
            goto L23
        L3c:
            r0 = move-exception
            java.lang.String r1 = "TJAdUnitJSBridge"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unable to startUsageTrackingEvent. Invalid parameters: "
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tapjoy.TapjoyLog.w(r1, r0)
        L51:
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
            r0[r4] = r1
            r6.invokeJSCallback(r8, r0)
            goto L23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJAdUnitJSBridge.startUsageTrackingEvent(org.json.JSONObject, java.lang.String):void");
    }

    public void startViewabilityTracker(JSONObject jSONObject, String str) {
        final eq eqVar = this.l;
        if (eqVar.c) {
            eqVar.a.invokeJSCallback(str, true);
            TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.internal.eq.2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        eq.this.e.a();
                    } catch (Exception e) {
                        TapjoyLog.d("TJOMViewabilityAgent", "Failed to start with exception: " + e.getMessage());
                    }
                }
            });
        } else {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not start -- TJOMViewabilityAgent is not initialized");
            eqVar.a.invokeJSCallback(str, false);
        }
    }

    public void triggerEvent(JSONObject jSONObject, String str) {
        if (this.a != null) {
            try {
                String string = jSONObject.getString("eventName");
                if (string.equals(TJAdUnitConstants.String.VIDEO_START)) {
                    this.a.fireOnVideoStart();
                    return;
                }
                if (string.equals(TJAdUnitConstants.String.VIDEO_COMPLETE)) {
                    this.a.fireOnVideoComplete();
                } else if (string.equals("error")) {
                    this.a.fireOnVideoError("Error while trying to play video.");
                } else if (string.equals(TJAdUnitConstants.String.CLICK)) {
                    this.a.fireOnClick();
                }
            } catch (Exception e) {
                TapjoyLog.w("TJAdUnitJSBridge", "Unable to triggerEvent. No event name.");
            }
        }
    }

    public void triggerMoatVideoEvent(JSONObject jSONObject, String str) {
        invokeJSCallback(str, true);
    }

    public void triggerViewabilityEvent(JSONObject jSONObject, final String str) {
        final eq eqVar = this.l;
        if (!eqVar.c) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not triggerEvent -- TJOMViewabilityAgent is not initialized");
            eqVar.a.invokeJSCallback(str, false);
        } else {
            if (jSONObject == null) {
                TapjoyLog.d("TJOMViewabilityAgent", "Can not triggerEvent -- json parameter is null");
                eqVar.a.invokeJSCallback(str, false);
                return;
            }
            final String strOptString = jSONObject.optString("eventName", null);
            if (strOptString != null) {
                TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.internal.eq.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            if (strOptString.equals(TJAdUnitConstants.String.VIDEO_RENDERED)) {
                                eq.this.g.a(db.a(da.STANDALONE));
                                eq.this.d.a();
                            } else if (strOptString.equals(TJAdUnitConstants.String.VIDEO_BUFFER_START)) {
                                eq.this.g.g();
                            } else if (strOptString.equals(TJAdUnitConstants.String.VIDEO_BUFFER_END)) {
                                eq.this.g.h();
                            } else if (strOptString.equals(TJAdUnitConstants.String.VIDEO_START)) {
                                eq.this.g.a(eq.this.a.a.getVideoView().getDuration(), eq.this.a.a.getVolume());
                            } else if (strOptString.equals(TJAdUnitConstants.String.VIDEO_FIRST_QUARTILE)) {
                                eq.this.g.a();
                            } else if (strOptString.equals(TJAdUnitConstants.String.VIDEO_MIDPOINT)) {
                                eq.this.g.b();
                            } else if (strOptString.equals(TJAdUnitConstants.String.VIDEO_THIRD_QUARTILE)) {
                                eq.this.g.c();
                            } else if (strOptString.equals(TJAdUnitConstants.String.VIDEO_PAUSED)) {
                                eq.this.g.e();
                            } else if (strOptString.equals(TJAdUnitConstants.String.VIDEO_PLAYING)) {
                                eq.this.g.f();
                            } else if (strOptString.equals(TJAdUnitConstants.String.VIDEO_SKIPPED)) {
                                eq.this.g.i();
                            } else if (strOptString.equals(TJAdUnitConstants.String.VOLUME_CHANGED)) {
                                eq.this.g.a(eq.this.a.a.getVolume());
                            } else if (!strOptString.equals(TJAdUnitConstants.String.VIDEO_COMPLETE)) {
                                TapjoyLog.d("TJOMViewabilityAgent", "triggerEvent: event name '" + strOptString + "' not found");
                                eq.this.a.invokeJSCallback(str, false);
                                return;
                            } else {
                                eq.this.g.d();
                                eq.this.e.b();
                                eq.this.e = null;
                            }
                            TapjoyLog.d("TJOMViewabilityAgent", "triggerEvent: event name '" + strOptString + "'");
                            eq.this.a.invokeJSCallback(str, true);
                        } catch (Exception e) {
                            TapjoyLog.d("TJOMViewabilityAgent", "triggerEvent exception:" + e.getMessage());
                            eq.this.a.invokeJSCallback(str, false);
                        }
                    }
                });
            } else {
                TapjoyLog.d("TJOMViewabilityAgent", "triggerEvent: params json did not contain 'eventName'");
                eqVar.a.invokeJSCallback(str, false);
            }
        }
    }

    public void unsetOrientation(JSONObject jSONObject, String str) {
        if (this.a == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            invokeJSCallback(str, false);
        } else {
            try {
                this.a.unsetOrientation();
                invokeJSCallback(str, true);
            } catch (Exception e) {
                invokeJSCallback(str, false);
            }
        }
    }
}
