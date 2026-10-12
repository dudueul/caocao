package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class fm extends fq {
    private static final String c = fm.class.getSimpleName();

    public fm(String str, String str2) {
        super(str, str2, "ad");
    }

    public static Map a(JSONObject jSONObject) {
        HashMap map = new HashMap();
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(TJAdUnitConstants.String.USAGE_TRACKER_DIMENSIONS);
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject2.get(next));
                }
            } catch (JSONException e) {
                TapjoyLog.d(c, "Unable to getAdUnitDimensions. Invalid params: " + e);
            }
        }
        return map;
    }

    public static Map b(JSONObject jSONObject) {
        HashMap map = new HashMap();
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(TJAdUnitConstants.String.USAGE_TRACKER_VALUES);
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, Long.valueOf(jSONObject2.getLong(next)));
                }
            } catch (JSONException e) {
                TapjoyLog.d(c, "Unable to getAdUnitValues. Invalid params: " + e);
            }
        }
        return map;
    }

    public final gf.a a(String str, JSONObject jSONObject) {
        return a(str, a(jSONObject), b(jSONObject));
    }

    public final gf.a b(String str, JSONObject jSONObject) {
        return b(str, a(jSONObject), b(jSONObject));
    }
}
