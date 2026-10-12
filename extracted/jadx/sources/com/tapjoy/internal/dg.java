package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.webkit.WebView;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dg {
    private static dg a = new dg();

    private dg() {
    }

    public static dg a() {
        return a;
    }

    @VisibleForTesting
    private static void a(StringBuilder sb, Object[] objArr) {
        if (objArr == null || objArr.length <= 0) {
            return;
        }
        for (Object obj : objArr) {
            if (obj == null) {
                sb.append('\"').append('\"');
            } else if (obj instanceof String) {
                String string = obj.toString();
                if (string.startsWith("{")) {
                    sb.append(string);
                } else {
                    sb.append('\"').append(string).append('\"');
                }
            } else {
                sb.append(obj);
            }
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
    }

    public static boolean a(WebView webView, String str) {
        if (webView == null || TextUtils.isEmpty(str)) {
            return false;
        }
        webView.loadUrl("javascript: " + str);
        return true;
    }

    public final void a(WebView webView, String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            a(webView, "publishVideoEvent", str, jSONObject);
        } else {
            a(webView, "publishVideoEvent", str);
        }
    }

    @VisibleForTesting
    public final void a(final WebView webView, String str, Object... objArr) {
        if (webView != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("javascript: if(window.omidBridge!==undefined){omidBridge.");
            sb.append(str);
            sb.append("(");
            a(sb, objArr);
            sb.append(")}");
            final String string = sb.toString();
            Handler handler = webView.getHandler();
            if (handler == null || Looper.myLooper() == handler.getLooper()) {
                webView.loadUrl(string);
            } else {
                handler.post(new Runnable() { // from class: com.tapjoy.internal.dg.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        webView.loadUrl(string);
                    }
                });
            }
        }
    }

    public final void b(WebView webView, String str) {
        a(webView, "setNativeViewHierarchy", str);
    }
}
