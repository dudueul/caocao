package com.tapjoy.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dm {
    private static WindowManager b = null;
    private static String[] c = {"x", "y", "width", "height"};
    static float a = Resources.getSystem().getDisplayMetrics().density;

    static class a {
        final float a;
        final float b;

        a(float f, float f2) {
            this.a = f;
            this.b = f2;
        }
    }

    public static JSONObject a(int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("x", i / a);
            jSONObject.put("y", i2 / a);
            jSONObject.put("width", i3 / a);
            jSONObject.put("height", i4 / a);
        } catch (JSONException e) {
            dn.a("Error with creating viewStateObject", e);
        }
        return jSONObject;
    }

    public static void a(Context context) {
        if (context != null) {
            a = context.getResources().getDisplayMetrics().density;
            b = (WindowManager) context.getSystemService("window");
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0086  */
    public static void a(JSONObject jSONObject) {
        float fMax;
        float fMax2;
        if (Build.VERSION.SDK_INT < 17) {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("childViews");
            if (jSONArrayOptJSONArray != null) {
                int length = jSONArrayOptJSONArray.length();
                fMax = 0.0f;
                fMax2 = 0.0f;
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject != null) {
                        double dOptDouble = jSONObjectOptJSONObject.optDouble("x");
                        double dOptDouble2 = jSONObjectOptJSONObject.optDouble("y");
                        double dOptDouble3 = jSONObjectOptJSONObject.optDouble("width");
                        double dOptDouble4 = jSONObjectOptJSONObject.optDouble("height");
                        fMax2 = Math.max(fMax2, (float) (dOptDouble + dOptDouble3));
                        fMax = Math.max(fMax, (float) (dOptDouble2 + dOptDouble4));
                    }
                }
            } else {
                fMax = 0.0f;
                fMax2 = 0.0f;
            }
        } else if (b != null) {
            Point point = new Point(0, 0);
            b.getDefaultDisplay().getRealSize(point);
            float f = point.x / a;
            fMax = point.y / a;
            fMax2 = f;
        } else {
            fMax = 0.0f;
            fMax2 = 0.0f;
        }
        a aVar = new a(fMax2, fMax);
        try {
            jSONObject.put("width", aVar.a);
            jSONObject.put("height", aVar.b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("adSessionId", str);
        } catch (JSONException e) {
            dn.a("Error with setting ad session id", e);
        }
    }

    public static void a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            dn.a("JSONException during JSONObject.put for name [" + str + "]", e);
        }
    }

    public static void a(JSONObject jSONObject, List list) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        try {
            jSONObject.put("isFriendlyObstructionFor", jSONArray);
        } catch (JSONException e) {
            dn.a("Error with setting friendly obstruction", e);
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("childViews");
            if (jSONArrayOptJSONArray == null) {
                jSONArrayOptJSONArray = new JSONArray();
                jSONObject.put("childViews", jSONArrayOptJSONArray);
            }
            jSONArrayOptJSONArray.put(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static boolean a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        if (jSONArray == null || jSONArray2 == null) {
            return false;
        }
        return jSONArray.length() == jSONArray2.length();
    }

    public static void b(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("notVisibleReason", str);
        } catch (JSONException e) {
            dn.a("Error with setting not visible reason", e);
        }
    }

    public static boolean b(@NonNull JSONObject jSONObject, @Nullable JSONObject jSONObject2) {
        boolean z;
        boolean z2;
        boolean z3;
        if (jSONObject == null && jSONObject2 == null) {
            return true;
        }
        if (jSONObject == null || jSONObject2 == null) {
            return false;
        }
        String[] strArr = c;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            String str = strArr[i];
            if (jSONObject.optDouble(str) != jSONObject2.optDouble(str)) {
                z = false;
                break;
            }
            i++;
        }
        if (!z || !jSONObject.optString("adSessionId", "").equals(jSONObject2.optString("adSessionId", ""))) {
            return false;
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("isFriendlyObstructionFor");
        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("isFriendlyObstructionFor");
        if (jSONArrayOptJSONArray == null && jSONArrayOptJSONArray2 == null) {
            z2 = true;
            break;
        }
        if (a(jSONArrayOptJSONArray, jSONArrayOptJSONArray2)) {
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArrayOptJSONArray.length()) {
                    z2 = true;
                    break;
                }
                if (!jSONArrayOptJSONArray.optString(i2, "").equals(jSONArrayOptJSONArray2.optString(i2, ""))) {
                    z2 = false;
                    break;
                }
                i2++;
            }
        } else {
            z2 = false;
        }
        if (!z2) {
            return false;
        }
        JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("childViews");
        JSONArray jSONArrayOptJSONArray4 = jSONObject2.optJSONArray("childViews");
        if (jSONArrayOptJSONArray3 == null && jSONArrayOptJSONArray4 == null) {
            z3 = true;
        } else if (a(jSONArrayOptJSONArray3, jSONArrayOptJSONArray4)) {
            for (int i3 = 0; i3 < jSONArrayOptJSONArray3.length(); i3++) {
                if (!b(jSONArrayOptJSONArray3.optJSONObject(i3), jSONArrayOptJSONArray4.optJSONObject(i3))) {
                    z3 = false;
                }
            }
            z3 = true;
        } else {
            z3 = false;
        }
        return z3;
    }
}
