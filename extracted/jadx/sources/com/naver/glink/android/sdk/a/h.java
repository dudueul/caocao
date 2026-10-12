package com.naver.glink.android.sdk.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.view.Display;

/* JADX INFO: compiled from: GameEngineHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class h {
    private static VirtualDisplay a;

    public static boolean a() {
        try {
            Class.forName("com.unity3d.player.UnityPlayer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @TargetApi(21)
    public static boolean a(Context context, MediaProjection mediaProjection, int i) {
        if (!a()) {
            return true;
        }
        if (a != null) {
            new Handler().postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.a.h.1
                @Override // java.lang.Runnable
                public void run() {
                    h.a.release();
                    VirtualDisplay unused = h.a = null;
                }
            }, 100L);
        }
        Display[] displayArrA = a(context);
        if (displayArrA == null) {
            return false;
        }
        if (displayArrA.length != 0) {
            return true;
        }
        a = mediaProjection.createVirtualDisplay("plug-live-stream-unity", 1, 1, i, 2, null, null, null);
        return true;
    }

    private static Display[] a(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        if (displayManager == null) {
            return null;
        }
        return displayManager.getDisplays(DisplayManagerCompat.DISPLAY_CATEGORY_PRESENTATION);
    }
}
