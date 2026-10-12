package com.tapjoy;

import android.content.Context;
import com.tapjoy.internal.az;
import com.tapjoy.internal.gv;
import com.tapjoy.internal.he;
import com.tapjoy.internal.jq;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJPlacementManager {
    private static final az a = az.a();
    private static int b = 0;
    private static int c = 0;
    private static int d = 3;
    private static int e = 3;

    static TJCorePlacement a(String str) {
        TJCorePlacement tJCorePlacement;
        synchronized (a) {
            tJCorePlacement = (TJCorePlacement) a.get(str);
        }
        return tJCorePlacement;
    }

    static TJCorePlacement a(String str, String str2, String str3, boolean z, boolean z2) {
        TJCorePlacement tJCorePlacementA;
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "!SYSTEM!" : "");
        sb.append(!jq.c(str) ? str : "");
        if (jq.c(str2)) {
            str2 = "";
        }
        sb.append(str2);
        if (jq.c(str3)) {
            str3 = "";
        }
        sb.append(str3);
        sb.append(Boolean.toString(z2));
        String string = sb.toString();
        TapjoyLog.d("TJPlacementManager", "TJCorePlacement key=" + string);
        synchronized (a) {
            tJCorePlacementA = a(string);
            if (tJCorePlacementA == null) {
                tJCorePlacementA = new TJCorePlacement(str, string, z2);
                a.put(string, tJCorePlacementA);
                TapjoyLog.d("TJPlacementManager", "Created TJCorePlacement with GUID: " + tJCorePlacementA.d);
            }
        }
        return tJCorePlacementA;
    }

    public static TJPlacement a(String str, String str2, String str3, TJPlacementListener tJPlacementListener) {
        TJPlacement tJPlacement;
        synchronized (a) {
            tJPlacement = new TJPlacement(a(str, str2, str3, false, false), tJPlacementListener);
        }
        return tJPlacement;
    }

    public static TJPlacement b(String str, String str2, String str3, TJPlacementListener tJPlacementListener) {
        TJPlacement tJPlacement;
        synchronized (a) {
            tJPlacement = new TJPlacement(a(str, str2, str3, false, true), tJPlacementListener);
        }
        return tJPlacement;
    }

    public static boolean canCachePlacement() {
        return getCachedPlacementCount() < getCachedPlacementLimit();
    }

    public static boolean canPreRenderPlacement() {
        return getPreRenderedPlacementCount() < getPreRenderedPlacementLimit();
    }

    public static TJPlacement createPlacement(Context context, String str, boolean z, TJPlacementListener tJPlacementListener) {
        TJCorePlacement tJCorePlacementA = a(str, null, null, z, false);
        tJCorePlacementA.j = z;
        tJCorePlacementA.c.setPlacementType("sdk");
        tJCorePlacementA.setContext(context);
        return new TJPlacement(tJCorePlacementA, tJPlacementListener);
    }

    public static void decrementPlacementCacheCount() {
        int i = b - 1;
        b = i;
        if (i < 0) {
            b = 0;
        }
        printPlacementCacheInformation();
    }

    public static void decrementPlacementPreRenderCount() {
        int i = c - 1;
        c = i;
        if (i < 0) {
            c = 0;
        }
    }

    public static void dismissContentShowing(boolean z) {
        if (z) {
            TJAdUnitActivity.a();
        }
        he.a();
        gv.a();
    }

    public static int getCachedPlacementCount() {
        return b;
    }

    public static int getCachedPlacementLimit() {
        return d;
    }

    public static int getPreRenderedPlacementCount() {
        return c;
    }

    public static int getPreRenderedPlacementLimit() {
        return e;
    }

    public static void incrementPlacementCacheCount() {
        int i = b + 1;
        b = i;
        if (i > d) {
            b = d;
        }
        printPlacementCacheInformation();
    }

    public static void incrementPlacementPreRenderCount() {
        int i = c + 1;
        c = i;
        if (i > e) {
            c = e;
        }
    }

    public static void printPlacementCacheInformation() {
        TapjoyLog.i("TJPlacementManager", "Space available in placement cache: " + b + " out of " + d);
    }

    public static void printPlacementPreRenderInformation() {
        TapjoyLog.i("TJPlacementManager", "Space available for placement pre-render: " + c + " out of " + e);
    }

    public static void setCachedPlacementLimit(int i) {
        d = i;
    }

    public static void setPreRenderedPlacementLimit(int i) {
        e = i;
    }
}
