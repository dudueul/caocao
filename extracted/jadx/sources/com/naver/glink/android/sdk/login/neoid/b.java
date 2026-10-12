package com.naver.glink.android.sdk.login.neoid;

import android.app.Activity;
import android.content.Context;
import com.nhn.android.naverlogin.ui.OAuthLoginImage;

/* JADX INFO: compiled from: NeoIdImage.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    public static String a = OAuthLoginImage.drawableByteStrBottomBackGroundImg;
    public static String b = OAuthLoginImage.drawableByteStrCloseBtnImg;

    public static float a(float f, Context context) {
        return (context.getResources().getDisplayMetrics().densityDpi / 160.0f) * f;
    }

    public static int a(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    public static byte[] a(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16);
        }
        return bArr;
    }

    public static int b(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }
}
