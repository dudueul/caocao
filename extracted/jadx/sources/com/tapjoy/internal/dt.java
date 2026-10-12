package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.webkit.WebView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class dt extends ds {
    @SuppressLint({"SetJavaScriptEnabled"})
    public dt(WebView webView) {
        if (webView != null && !webView.getSettings().getJavaScriptEnabled()) {
            webView.getSettings().setJavaScriptEnabled(true);
        }
        a(webView);
    }
}
