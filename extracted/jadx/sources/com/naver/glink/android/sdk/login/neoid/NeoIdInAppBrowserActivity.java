package com.naver.glink.android.sdk.login.neoid;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.naver.glink.android.sdk.a.i;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.a.y;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NeoIdInAppBrowserActivity extends Activity implements View.OnClickListener {
    public String a;
    final DownloadListener b = new DownloadListener() { // from class: com.naver.glink.android.sdk.login.neoid.NeoIdInAppBrowserActivity.2
        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(y.a(str), str4);
            try {
                NeoIdInAppBrowserActivity.this.startActivity(intent);
            } catch (Throwable th) {
                th.printStackTrace();
                try {
                    intent.setData(y.a(str));
                    NeoIdInAppBrowserActivity.this.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private Context c;
    private byte[] d;
    private byte[] e;
    private ImageView f;
    private WebView g;
    private ProgressBar h;
    private RelativeLayout i;
    private LinearLayout j;
    private boolean k;
    private String l;
    private String m;
    private boolean n;

    private class a extends WebChromeClient {
        private a() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (NeoIdInAppBrowserActivity.this.h != null) {
                NeoIdInAppBrowserActivity.this.h.setProgress(i);
            }
        }
    }

    private class b extends WebViewClient {
        private String b = "";

        public b() {
        }

        private boolean a(Uri uri) {
            byte b = 0;
            switch (uri.getScheme()) {
                case "market":
                    NeoIdInAppBrowserActivity.this.startActivity(new Intent("android.intent.action.VIEW", uri));
                    return true;
                case "glink":
                    String host = uri.getHost();
                    switch (host.hashCode()) {
                        case -1263203643:
                            b = !host.equals("openUrl") ? (byte) -1 : (byte) 1;
                            break;
                        case 3267882:
                            if (!host.equals("join")) {
                                b = -1;
                            }
                            break;
                        default:
                            b = -1;
                            break;
                    }
                    switch (b) {
                        case 0:
                            com.naver.glink.android.sdk.b.c();
                            return true;
                        case 1:
                            i.a((Context) NeoIdInAppBrowserActivity.this, uri.getQueryParameter("url"));
                            return true;
                        default:
                            return true;
                    }
                default:
                    return false;
            }
        }

        @Override // android.webkit.WebViewClient
        public void onFormResubmission(WebView webView, Message message, Message message2) {
            super.onFormResubmission(webView, message, message2);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (NeoIdInAppBrowserActivity.this.h != null) {
                NeoIdInAppBrowserActivity.this.h.setVisibility(8);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (!NeoIdInAppBrowserActivity.this.a(false, this.b, str)) {
                super.onPageStarted(webView, str, bitmap);
                if (NeoIdInAppBrowserActivity.this.h != null) {
                    NeoIdInAppBrowserActivity.this.h.setVisibility(0);
                    return;
                }
                return;
            }
            NeoIdInAppBrowserActivity.this.g.stopLoading();
            if (NeoIdInAppBrowserActivity.this.k) {
                NeoIdInAppBrowserActivity.this.setResult(-1, new Intent().putExtra("url", str));
            } else {
                NeoIdSdkManager.a(str);
                NeoIdInAppBrowserActivity.this.n = true;
            }
            NeoIdInAppBrowserActivity.this.d();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (NeoIdInAppBrowserActivity.this.h != null) {
                NeoIdInAppBrowserActivity.this.h.setVisibility(8);
            }
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            if (NeoIdInAppBrowserActivity.this.h != null) {
                NeoIdInAppBrowserActivity.this.h.setVisibility(8);
            }
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (NeoIdInAppBrowserActivity.this.a(true, this.b, str)) {
                NeoIdInAppBrowserActivity.this.g.stopLoading();
                if (NeoIdInAppBrowserActivity.this.k) {
                    NeoIdInAppBrowserActivity.this.setResult(-1, new Intent().putExtra("url", str));
                } else {
                    NeoIdSdkManager.a(str);
                    NeoIdInAppBrowserActivity.this.n = true;
                }
                NeoIdInAppBrowserActivity.this.d();
            } else {
                try {
                    if (!a(y.a(str))) {
                        HashMap map = new HashMap();
                        map.put(HttpRequest.HEADER_REFERER, NeoIdInAppBrowserActivity.this.a);
                        webView.loadUrl(str, map);
                        this.b = str;
                    }
                } catch (Exception e) {
                }
            }
            return true;
        }
    }

    public class c {
        public static final String a = "from_token_login";
        public static final String b = "url";
        public static final String c = "finish_url";
        public static final String d = "content";

        public c() {
        }
    }

    public class d {
        public static final String a = "url";

        public d() {
        }
    }

    private void a() {
        this.c = this;
        if (getIntent() != null) {
            this.k = getIntent().getBooleanExtra(c.a, false);
            this.a = getIntent().getStringExtra("url");
            this.l = getIntent().getStringExtra(c.c);
            this.m = getIntent().getStringExtra("content");
        }
    }

    private boolean a(String str) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(boolean z, String str, String str2) {
        if (str2 == null || this.l == null) {
            return false;
        }
        return str2.contains(this.l);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void b() {
        requestWindowFeature(1);
        this.d = com.naver.glink.android.sdk.login.neoid.b.a(com.naver.glink.android.sdk.login.neoid.b.a);
        this.e = com.naver.glink.android.sdk.login.neoid.b.a(com.naver.glink.android.sdk.login.neoid.b.b);
        this.h = new ProgressBar(this, null, R.attr.progressBarStyleHorizontal);
        this.h.setLayoutParams(new LinearLayout.LayoutParams(-1, 5));
        this.h.setVisibility(8);
        this.h.setMax(100);
        this.g = new WebView(this);
        this.g.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
        this.g.getSettings().setJavaScriptEnabled(true);
        this.g.setVerticalScrollbarOverlay(true);
        this.g.setHorizontalScrollbarOverlay(true);
        this.g.setWebViewClient(new b());
        this.g.setWebChromeClient(new a());
        this.g.setDownloadListener(this.b);
        this.g.getSettings().setUserAgentString(this.g.getSettings().getUserAgentString() + " " + com.naver.glink.android.sdk.login.neoid.a.c(this));
        this.f = new ImageView(this);
        this.f.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.f.setImageResource(com.naver.glink.android.sdk.R.drawable.gl_ls_btn_login_back);
        this.f.setPadding(x.a(17.0f), x.a(10.0f), 0, 0);
        this.f.setClickable(true);
        this.f.setOnClickListener(this);
        this.j = new LinearLayout(this);
        this.j.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.j.setOrientation(1);
        this.j.addView(this.h);
        this.j.addView(this.g);
        this.i = new RelativeLayout(this);
        this.i.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.i.addView(this.j);
        this.i.addView(this.f);
        setContentView(this.i);
    }

    private void c() {
        final LinearLayout linearLayout = this.j;
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.naver.glink.android.sdk.login.neoid.NeoIdInAppBrowserActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                linearLayout.getWindowVisibleDisplayFrame(new Rect());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            this.g.stopLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (this.n) {
            return;
        }
        NeoIdSdkManager.a(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.f) {
            d();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        b();
        c();
        if (this.a == null) {
            return;
        }
        if (TextUtils.isEmpty(this.m)) {
            this.g.loadUrl(this.a);
        } else {
            this.g.loadDataWithBaseURL(this.a, this.m, "text/html", null, null);
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.d = null;
        this.e = null;
        if (this.g != null) {
            this.g.stopLoading();
            ((ViewGroup) this.g.getParent()).removeView(this.g);
            if (this.j != null) {
                this.j.removeView(this.g);
            }
            this.g.removeAllViews();
            this.g.destroy();
            this.g = null;
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        switch (intent.getAction()) {
            case "PlugSchemeActivity.ACTION_LOGIN_FINISH":
                NeoIdSdkManager.b(intent.getStringExtra(PlugSchemeActivity.b));
                finish();
                break;
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.g == null || Build.VERSION.SDK_INT < 11) {
            return;
        }
        this.g.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.g != null) {
            this.g.resumeTimers();
            if (Build.VERSION.SDK_INT >= 11) {
                this.g.onResume();
            }
        }
    }
}
