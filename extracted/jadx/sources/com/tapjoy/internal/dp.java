package com.tapjoy.internal;

import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dp {
    public static float a(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    public static boolean b(View view) {
        if ((Build.VERSION.SDK_INT < 19 || view.isAttachedToWindow()) && view.isShown()) {
            while (view != null) {
                if (view.getAlpha() == 0.0f) {
                    return false;
                }
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            return true;
        }
        return false;
    }

    public static String c(View view) {
        if (Build.VERSION.SDK_INT >= 19 && !view.isAttachedToWindow()) {
            return "notAttached";
        }
        int visibility = view.getVisibility();
        if (visibility == 0) {
            if (view.getAlpha() == 0.0f) {
                return "viewAlphaZero";
            }
            return null;
        }
        switch (visibility) {
            case 4:
                return "viewInvisible";
            case 8:
                return "viewGone";
            default:
                return "viewNotVisible";
        }
    }
}
