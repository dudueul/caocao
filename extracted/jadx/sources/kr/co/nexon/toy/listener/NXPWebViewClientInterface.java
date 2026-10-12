package kr.co.nexon.toy.listener;

import android.graphics.Bitmap;
import android.webkit.WebView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPWebViewClientInterface {
    void onPageFinished(WebView webView, String str);

    void onPageStarted(WebView webView, String str, Bitmap bitmap);

    void onReceivedError(WebView webView, int i, String str, String str2);

    boolean shouldOverrideUrlLoading(WebView webView, String str);
}
