package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import com.tapjoy.internal.gz;
import com.tapjoy.internal.j;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class InstallReferrerReceiver extends j {
    @Override // com.tapjoy.internal.j, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String strA = gz.a(context, intent);
        int iA = a(context, intent);
        if (intent.getBooleanExtra("fiverocks:verify", false) && isOrderedBroadcast()) {
            setResultCode(iA + 1);
            if (strA != null) {
                try {
                    setResultData("http://play.google.com/store/apps/details?id=" + context.getPackageName() + "&referrer=" + URLEncoder.encode(strA, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
    }
}
