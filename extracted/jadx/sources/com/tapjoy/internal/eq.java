package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAdUnitJSBridge;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TapjoyCachedAssetData;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyUtil;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class eq {
    public static String b = "";
    public TJAdUnitJSBridge a;
    private cr d;
    private cs e;
    private dc g;
    private cx f = cx.a("Tapjoy", Tapjoy.getVersion());
    public boolean c = false;

    public eq(TJAdUnitJSBridge tJAdUnitJSBridge) {
        this.a = tJAdUnitJSBridge;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List b(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String strOptString = jSONObject.optString(TJAdUnitConstants.String.VENDOR_JS_URL, null);
                if (strOptString == null) {
                    TapjoyLog.d("TJOMViewabilityAgent", "Vendor JS URL not found. Skipping.");
                } else {
                    try {
                        URL url = new URL(strOptString);
                        String strOptString2 = jSONObject.optString(TJAdUnitConstants.String.VENDOR_NAME, null);
                        String strOptString3 = jSONObject.optString(TJAdUnitConstants.String.VENDOR_PARAMETERS, null);
                        arrayList.add((strOptString3 == null || strOptString2 == null) ? cy.a(url) : cy.a(strOptString2, url, strOptString3));
                    } catch (Exception e) {
                        TapjoyLog.d("TJOMViewabilityAgent", "Malformed vendor JS URL. Skipping " + strOptString);
                    }
                }
            } catch (JSONException e2) {
                TapjoyLog.d("TJOMViewabilityAgent", "Malformed vendor object. Skipping.");
            }
        }
        return arrayList;
    }

    public static void b(JSONObject jSONObject) {
        if (al.a(b)) {
            String strOptString = jSONObject.optString(TJAdUnitConstants.String.OM_JAVASCRIPT_URL, null);
            if (strOptString == null) {
                TapjoyLog.d("TJOMViewabilityAgent", "Open Mediation JavaScript name not found in json.");
                return;
            }
            try {
                TapjoyCachedAssetData cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(strOptString);
                if (cachedDataForURL == null) {
                    TapjoyCache.getInstance().cacheAssetFromURL(strOptString, "", 30L).get();
                    cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(strOptString);
                }
                b = cachedDataForURL == null ? "" : TapjoyUtil.getFileContents(new File(cachedDataForURL.getLocalFilePath()));
            } catch (Exception e) {
                TapjoyLog.d("TJOMViewabilityAgent", "Failed downloading Open Mediation JavaScript");
            }
        }
    }

    public final boolean a(JSONObject jSONObject) {
        if (this.a.b == null) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- WebView is null");
            return false;
        }
        if (this.a.a == null) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- TJAdUnit is null");
            return false;
        }
        if (this.a.a.getVideoView() == null) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- VideoView is null");
            return false;
        }
        if (jSONObject == null) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- json parameter is null");
            return false;
        }
        if (!jSONObject.has(TJAdUnitConstants.String.OM_JAVASCRIPT_URL)) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- unable to parse om javascript url from json");
            return false;
        }
        try {
            jSONObject.getJSONArray(TJAdUnitConstants.String.VENDORS);
            return true;
        } catch (JSONException e) {
            TapjoyLog.d("TJOMViewabilityAgent", "Can not init -- unable to parse vendors from json");
            return false;
        }
    }
}
