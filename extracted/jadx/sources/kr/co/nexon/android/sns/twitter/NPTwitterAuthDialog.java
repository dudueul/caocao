package kr.co.nexon.android.sns.twitter;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.tapjoy.TJAdUnitConstants;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import kr.co.nexon.android.sns.NPAuthListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPTwitterAuthDialog extends Dialog {
    static final String DISPLAY_STRING = "touch";
    static final int FB_BLUE = -9599820;
    static final String FB_ICON = "icon.png";
    static final int MARGIN = 4;
    static final int PADDING = 2;
    private Activity activity;
    public Bundle bundle;
    private String callbackURL;
    public int errorCode;
    public String errorDetail;
    private FrameLayout mContent;
    private ImageView mCrossImage;
    private NPAuthListener mListener;
    private ProgressDialog mSpinner;
    private String mUrl;
    private WebView mWebView;
    private NPTwitter sns;
    private WebViewClient webViewClient;
    static final float[] DIMENSIONS_DIFF_LANDSCAPE = {20.0f, 60.0f};
    static final float[] DIMENSIONS_DIFF_PORTRAIT = {40.0f, 60.0f};
    static final FrameLayout.LayoutParams FILL = new FrameLayout.LayoutParams(-1, -1);

    /* JADX INFO: renamed from: kr.co.nexon.android.sns.twitter.NPTwitterAuthDialog$3, reason: invalid class name */
    class AnonymousClass3 implements DialogInterface.OnShowListener {
        AnonymousClass3() {
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(final DialogInterface dialogInterface) {
            NPTwitterAuthDialog.this.mSpinner.show();
            new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.twitter.NPTwitterAuthDialog.3.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        final RequestToken oAuthRequestToken = NPTwitterAuthDialog.this.sns.getTwitterInstance().getOAuthRequestToken();
                        ToyLog.d("save Twitter requestToken :" + oAuthRequestToken.getToken());
                        ToyLog.d("save Twitter requestTokenSecret :" + oAuthRequestToken.getTokenSecret());
                        NPTwitterAuthDialog.this.sns.setTwitterRequestToken(oAuthRequestToken.getToken());
                        NPTwitterAuthDialog.this.sns.setTwitterRequestSecretToken(oAuthRequestToken.getTokenSecret());
                        NPTwitterAuthDialog.this.mSpinner.dismiss();
                        NPTwitterAuthDialog.this.activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.twitter.NPTwitterAuthDialog.3.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                NPTwitterAuthDialog.this.mWebView.loadUrl(oAuthRequestToken.getAuthorizationURL());
                                ToyLog.d("requestToken auth url : " + oAuthRequestToken.getAuthorizationURL());
                            }
                        });
                    } catch (TwitterException e) {
                        NPTwitterAuthDialog.this.mSpinner.dismiss();
                        e.printStackTrace();
                        NPTwitterAuthDialog.this.errorCode = NXToyErrorCode.TWITTER_GET_REQUEST_TOKEN_FAIL.getCode();
                        NPTwitterAuthDialog.this.errorDetail = e.toString();
                        NPTwitterAuthDialog.this.bundle = null;
                        dialogInterface.dismiss();
                    } catch (Exception e2) {
                        NPTwitterAuthDialog.this.mSpinner.dismiss();
                        e2.printStackTrace();
                        NPTwitterAuthDialog.this.errorCode = NXToyErrorCode.TWITTER_GET_REQUEST_TOKEN_FAIL.getCode();
                        NPTwitterAuthDialog.this.errorDetail = e2.toString();
                        NPTwitterAuthDialog.this.bundle = null;
                        dialogInterface.dismiss();
                    }
                }
            }).start();
        }
    }

    public NPTwitterAuthDialog(Activity activity, NPTwitter nPTwitter, NPAuthListener nPAuthListener) {
        super(activity, R.style.Theme.Translucent.NoTitleBar);
        this.webViewClient = new WebViewClient() { // from class: kr.co.nexon.android.sns.twitter.NPTwitterAuthDialog.1
            @Override // android.webkit.WebViewClient
            public void onLoadResource(WebView webView, String str) {
                ToyLog.d(str);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                ToyLog.d(str);
                if (str.contains("login/error") && webView.canGoBack()) {
                    webView.goBack();
                }
                super.onPageFinished(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                ToyLog.d(str);
                if (str.startsWith(NPTwitterAuthDialog.this.callbackURL + "?denied=") || str.startsWith(NPTwitterAuthDialog.this.callbackURL + "/?denied=")) {
                    webView.stopLoading();
                    NPTwitterAuthDialog.this.errorCode = NXToyErrorCode.TWITTER_USER_CANCELED.getCode();
                    NPTwitterAuthDialog.this.errorDetail = "cancel";
                    NPTwitterAuthDialog.this.bundle = null;
                    NPTwitterAuthDialog.this.dismiss();
                    return;
                }
                if (str.startsWith(NPTwitterAuthDialog.this.callbackURL)) {
                    String[] strArrSplit = str.replace(NPTwitterAuthDialog.this.callbackURL, "").split("\\?");
                    ToyLog.d("twitter args process");
                    if (strArrSplit.length > 2) {
                        webView.stopLoading();
                        NPTwitterAuthDialog.this.errorCode = NXToyErrorCode.TWITTER_GET_TOKEN_FAIL.getCode();
                        NPTwitterAuthDialog.this.errorDetail = "Login Failed";
                        NPTwitterAuthDialog.this.bundle = null;
                        NPTwitterAuthDialog.this.dismiss();
                        return;
                    }
                    HashMap<String, String> queryString = strArrSplit.length == 2 ? NPTwitterAuthDialog.parseQueryString(strArrSplit[1]) : null;
                    ToyLog.d("twitter args process = " + queryString.toString());
                    final String str2 = queryString.get("oauth_verifier");
                    webView.stopLoading();
                    if (str2 == null) {
                        NPTwitterAuthDialog.this.errorCode = NXToyErrorCode.TWITTER_GET_TOKEN_FAIL.getCode();
                        NPTwitterAuthDialog.this.errorDetail = "oauth verifier is null";
                        NPTwitterAuthDialog.this.bundle = null;
                        NPTwitterAuthDialog.this.dismiss();
                        return;
                    }
                    final Twitter twitterInstance = NPTwitterAuthDialog.this.sns.getTwitterInstance();
                    ToyLog.d("twitter thread start  ");
                    new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.twitter.NPTwitterAuthDialog.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                AccessToken oAuthAccessToken = twitterInstance.getOAuthAccessToken(new RequestToken(NPTwitterAuthDialog.this.sns.getTwitterRequestToken(), NPTwitterAuthDialog.this.sns.getTwitterRequestSecretToken()), str2);
                                if (oAuthAccessToken == null) {
                                    ToyLog.d("accessToken null");
                                } else {
                                    ToyLog.v("accessToken " + oAuthAccessToken.getToken());
                                    NPTwitterAuthDialog.this.sns.setTwitterToken(oAuthAccessToken.getToken());
                                    NPTwitterAuthDialog.this.sns.setTwitterSecretToken(oAuthAccessToken.getTokenSecret());
                                    twitterInstance.setOAuthAccessToken(oAuthAccessToken);
                                    NPTwitterAuthDialog.this.sns.initInstance();
                                    NPTwitterAuthDialog.this.mListener.onResult(0, "Login Success", null);
                                }
                            } catch (TwitterException e) {
                                e.printStackTrace();
                                NPTwitterAuthDialog.this.mListener.onResult(NXToyErrorCode.TWITTER_TOKEN_SETUP_FAIL.getCode(), e.toString(), null);
                            }
                        }
                    });
                    NPTwitterAuthDialog.this.stopWebview();
                    webView.stopLoading();
                    NPTwitterAuthDialog.this.errorCode = 0;
                    NPTwitterAuthDialog.this.errorDetail = "";
                    NPTwitterAuthDialog.this.bundle = new Bundle();
                    NPTwitterAuthDialog.this.bundle.putString("oauthVerifier", str2);
                    NPTwitterAuthDialog.this.dismiss();
                }
            }
        };
        this.mListener = nPAuthListener;
        this.sns = nPTwitter;
        this.activity = activity;
        this.callbackURL = nPTwitter.getCallbackURL();
    }

    public NPTwitterAuthDialog(Context context) {
        super(context);
        this.webViewClient = new WebViewClient() { // from class: kr.co.nexon.android.sns.twitter.NPTwitterAuthDialog.1
            @Override // android.webkit.WebViewClient
            public void onLoadResource(WebView webView, String str) {
                ToyLog.d(str);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                ToyLog.d(str);
                if (str.contains("login/error") && webView.canGoBack()) {
                    webView.goBack();
                }
                super.onPageFinished(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                ToyLog.d(str);
                if (str.startsWith(NPTwitterAuthDialog.this.callbackURL + "?denied=") || str.startsWith(NPTwitterAuthDialog.this.callbackURL + "/?denied=")) {
                    webView.stopLoading();
                    NPTwitterAuthDialog.this.errorCode = NXToyErrorCode.TWITTER_USER_CANCELED.getCode();
                    NPTwitterAuthDialog.this.errorDetail = "cancel";
                    NPTwitterAuthDialog.this.bundle = null;
                    NPTwitterAuthDialog.this.dismiss();
                    return;
                }
                if (str.startsWith(NPTwitterAuthDialog.this.callbackURL)) {
                    String[] strArrSplit = str.replace(NPTwitterAuthDialog.this.callbackURL, "").split("\\?");
                    ToyLog.d("twitter args process");
                    if (strArrSplit.length > 2) {
                        webView.stopLoading();
                        NPTwitterAuthDialog.this.errorCode = NXToyErrorCode.TWITTER_GET_TOKEN_FAIL.getCode();
                        NPTwitterAuthDialog.this.errorDetail = "Login Failed";
                        NPTwitterAuthDialog.this.bundle = null;
                        NPTwitterAuthDialog.this.dismiss();
                        return;
                    }
                    HashMap<String, String> queryString = strArrSplit.length == 2 ? NPTwitterAuthDialog.parseQueryString(strArrSplit[1]) : null;
                    ToyLog.d("twitter args process = " + queryString.toString());
                    final String str2 = queryString.get("oauth_verifier");
                    webView.stopLoading();
                    if (str2 == null) {
                        NPTwitterAuthDialog.this.errorCode = NXToyErrorCode.TWITTER_GET_TOKEN_FAIL.getCode();
                        NPTwitterAuthDialog.this.errorDetail = "oauth verifier is null";
                        NPTwitterAuthDialog.this.bundle = null;
                        NPTwitterAuthDialog.this.dismiss();
                        return;
                    }
                    final Twitter twitterInstance = NPTwitterAuthDialog.this.sns.getTwitterInstance();
                    ToyLog.d("twitter thread start  ");
                    new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.twitter.NPTwitterAuthDialog.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                AccessToken oAuthAccessToken = twitterInstance.getOAuthAccessToken(new RequestToken(NPTwitterAuthDialog.this.sns.getTwitterRequestToken(), NPTwitterAuthDialog.this.sns.getTwitterRequestSecretToken()), str2);
                                if (oAuthAccessToken == null) {
                                    ToyLog.d("accessToken null");
                                } else {
                                    ToyLog.v("accessToken " + oAuthAccessToken.getToken());
                                    NPTwitterAuthDialog.this.sns.setTwitterToken(oAuthAccessToken.getToken());
                                    NPTwitterAuthDialog.this.sns.setTwitterSecretToken(oAuthAccessToken.getTokenSecret());
                                    twitterInstance.setOAuthAccessToken(oAuthAccessToken);
                                    NPTwitterAuthDialog.this.sns.initInstance();
                                    NPTwitterAuthDialog.this.mListener.onResult(0, "Login Success", null);
                                }
                            } catch (TwitterException e) {
                                e.printStackTrace();
                                NPTwitterAuthDialog.this.mListener.onResult(NXToyErrorCode.TWITTER_TOKEN_SETUP_FAIL.getCode(), e.toString(), null);
                            }
                        }
                    });
                    NPTwitterAuthDialog.this.stopWebview();
                    webView.stopLoading();
                    NPTwitterAuthDialog.this.errorCode = 0;
                    NPTwitterAuthDialog.this.errorDetail = "";
                    NPTwitterAuthDialog.this.bundle = new Bundle();
                    NPTwitterAuthDialog.this.bundle.putString("oauthVerifier", str2);
                    NPTwitterAuthDialog.this.dismiss();
                }
            }
        };
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

    private void setUpWebView(int i) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.mWebView = new WebView(getContext());
        this.mWebView.setVerticalScrollBarEnabled(false);
        this.mWebView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setWebViewClient(this.webViewClient);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.loadData(TJAdUnitConstants.SPINNER_TITLE, "text/plain", UrlUtils.UTF8);
        this.mWebView.setLayoutParams(FILL);
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.mWebView);
        this.mContent.addView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopWebview() {
        this.activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.twitter.NPTwitterAuthDialog.2
            @Override // java.lang.Runnable
            public void run() {
                NPTwitterAuthDialog.this.mWebView.stopLoading();
            }
        });
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
        this.errorCode = NXToyErrorCode.TWITTER_USER_CANCELED.getCode();
        this.errorDetail = "cancel";
        this.bundle = null;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSpinner = new ProgressDialog(getContext());
        this.mSpinner.requestWindowFeature(1);
        this.mSpinner.setMessage(TJAdUnitConstants.SPINNER_TITLE);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.mContent = new FrameLayout(getContext());
        setOnShowListener(new AnonymousClass3());
        setUpWebView(20);
        addContentView(this.mContent, new ViewGroup.LayoutParams(-1, -1));
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ToyLog.d("onDetachedFromWindow");
    }
}
