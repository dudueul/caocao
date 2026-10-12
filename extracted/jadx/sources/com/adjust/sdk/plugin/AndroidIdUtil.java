package com.adjust.sdk.plugin;

import android.content.Context;
import android.provider.Settings;
import com.tapjoy.TapjoyConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AndroidIdUtil {
    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
    }
}
