package kr.co.nexon.android.sns.nxnet.ui;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import kr.co.nexon.android.sns.nxnet.NPNXNetInfo;
import kr.co.nexon.mdev.android.view.NXProgressDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetAuthDialog extends Dialog {
    public static final String CODE_TICKET = "ticket";
    public static final String NX_NET_LOGIN_PARAM_PROD_ID = "prod_id";
    public static final String NX_NET_LOGIN_PARAM_REDIRECT_URI = "redirect_uri";
    public static final String NX_NET_LOGIN_URL = "https://accounts.nexon.net/auth/login";
    private Activity activity;
    public Bundle bundle;
    public int errorCode;
    public String errorDetail;
    private FrameLayout mContent;
    private WebView mWebView;
    private NPNXNetInfo nxNetInfo;
    private NXProgressDialog progressDialog;
    private WebViewClient webViewClient;

    public NPNXNetAuthDialog(Activity activity, NPNXNetInfo nPNXNetInfo) {
        super(activity, R.style.Theme.Translucent.NoTitleBar);
        this.webViewClient = new WebViewClient() { // from class: kr.co.nexon.android.sns.nxnet.ui.NPNXNetAuthDialog.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                if (NPNXNetAuthDialog.this.progressDialog == null || !NPNXNetAuthDialog.this.progressDialog.isShowing()) {
                    return;
                }
                NPNXNetAuthDialog.this.progressDialog.dismiss();
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                ToyLog.d("NXMP SDK URL: " + str);
                if (!str.startsWith(NPNXNetAuthDialog.this.nxNetInfo.getRedirectUri())) {
                    super.onPageStarted(webView, str, bitmap);
                    return;
                }
                HashMap<String, String> queryString = NPNXNetAuthDialog.parseQueryString(str.split("\\?")[1]);
                NPNXNetAuthDialog.this.errorCode = NXToyErrorCode.SUCCESS.getCode();
                NPNXNetAuthDialog.this.errorDetail = "";
                NPNXNetAuthDialog.this.bundle = new Bundle();
                NPNXNetAuthDialog.this.bundle.putString("ticket", queryString.get("ticket"));
                NPNXNetAuthDialog.this.dismiss();
            }
        };
        this.activity = activity;
        this.nxNetInfo = nPNXNetInfo;
    }

    public NPNXNetAuthDialog(Context context) {
        super(context);
        this.webViewClient = new WebViewClient() { // from class: kr.co.nexon.android.sns.nxnet.ui.NPNXNetAuthDialog.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                if (NPNXNetAuthDialog.this.progressDialog == null || !NPNXNetAuthDialog.this.progressDialog.isShowing()) {
                    return;
                }
                NPNXNetAuthDialog.this.progressDialog.dismiss();
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                ToyLog.d("NXMP SDK URL: " + str);
                if (!str.startsWith(NPNXNetAuthDialog.this.nxNetInfo.getRedirectUri())) {
                    super.onPageStarted(webView, str, bitmap);
                    return;
                }
                HashMap<String, String> queryString = NPNXNetAuthDialog.parseQueryString(str.split("\\?")[1]);
                NPNXNetAuthDialog.this.errorCode = NXToyErrorCode.SUCCESS.getCode();
                NPNXNetAuthDialog.this.errorDetail = "";
                NPNXNetAuthDialog.this.bundle = new Bundle();
                NPNXNetAuthDialog.this.bundle.putString("ticket", queryString.get("ticket"));
                NPNXNetAuthDialog.this.dismiss();
            }
        };
    }

    private String getUrl() {
        return String.format("%s?%s=%s&%s=%s", NX_NET_LOGIN_URL, NX_NET_LOGIN_PARAM_PROD_ID, this.nxNetInfo.getProductId(), "redirect_uri", this.nxNetInfo.getRedirectUri());
    }

    public static HashMap<String, String> parseQueryString(String str) {
        HashMap<String, String> map = new HashMap<>();
        for (String str2 : str.split("&")) {
            String[] strArrSplit = str2.split("=");
            if (strArrSplit.length == 2) {
                try {
                    map.put(strArrSplit[0], URLDecoder.decode(strArrSplit[1], UrlUtils.UTF8));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    private void removeCookie() {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT > 20) {
            cookieManager.removeAllCookies(null);
        } else {
            cookieManager.removeAllCookie();
        }
    }

    private void setUpWebView(int i) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.mWebView = new WebView(getContext());
        this.mWebView.setVerticalScrollBarEnabled(false);
        this.mWebView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setWebViewClient(this.webViewClient);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.loadUrl(getUrl());
        this.mWebView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.mWebView);
        this.mContent.addView(linearLayout);
    }

    private void stopWebview() {
        this.activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.nxnet.ui.NPNXNetAuthDialog.2
            @Override // java.lang.Runnable
            public void run() {
                NPNXNetAuthDialog.this.mWebView.stopLoading();
            }
        });
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
        this.errorCode = NXToyErrorCode.NXNET_USER_CANCELED.getCode();
        this.errorDetail = "cancel";
        this.bundle = null;
        dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.mContent = new FrameLayout(getContext());
        removeCookie();
        this.progressDialog = new NXProgressDialog(this.activity);
        this.progressDialog.show();
        setUpWebView(20);
        addContentView(this.mContent, new ViewGroup.LayoutParams(-1, -1));
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ToyLog.d("onDetachedFromWindow");
    }
}
