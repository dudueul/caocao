package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacementListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@ft
public class TapjoyNative {
    @ft
    public static Object createPlacement(Context context, String str, TJPlacementListener tJPlacementListener) {
        return gc.a().a(context, str, tJPlacementListener);
    }
}
