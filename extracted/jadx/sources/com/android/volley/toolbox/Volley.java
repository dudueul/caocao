package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import com.android.volley.RequestQueue;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Volley {
    private static final String DEFAULT_CACHE_DIR = "volley";

    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }

    public static RequestQueue newRequestQueue(Context context, HttpStack httpStack) {
        File file = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);
        String string = "volley/0";
        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            String strValueOf = String.valueOf(String.valueOf(packageName));
            string = new StringBuilder(strValueOf.length() + 12).append(strValueOf).append("/").append(packageInfo.versionCode).toString();
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (httpStack == null) {
            httpStack = Build.VERSION.SDK_INT >= 9 ? new HurlStack() : new HttpClientStack(AndroidHttpClient.newInstance(string));
        }
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file), new BasicNetwork(httpStack));
        requestQueue.start();
        return requestQueue;
    }
}
