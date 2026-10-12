package com.nhn.android.naverlogin.ui;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.connection.NetworkState;
import com.nhn.android.naverlogin.connection.gen.OAuthQueryGenerator;
import com.nhn.android.naverlogin.data.OAuthLoginData;
import com.nhn.android.naverlogin.ui.view.OAuthLoginLayoutNaverAppDownloadBanner;
import com.nhn.android.naverlogin.util.CookieUtil;
import com.nhn.android.naverlogin.util.DeviceAppInfo;
import com.nhn.android.naverlogin.util.OAuthLoginUiUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLoginInAppBrowserActivity extends Activity implements View.OnClickListener {
    private static final String INSTANCE_STATE_IS_VISIBLE_BANNER = "isVisibleBanner";
    private static final String INSTANCE_STATE_WEBVIEW_RUN_ONLY_ONCE = "IsLoginActivityStarted";
    private static final String TAG = "NaverLoginOAuth|OAuthLoginInAppBrowserActivity";
    private byte[] drawableByteBottomBackGroundImg;
    private byte[] drawableByteCloseBtnImg;
    private Context mContext;
    private ImageView mImgCloseButton;
    private ImageView mImgSeperator;
    public String mInOAuthUrl;
    private OAuthLoginLayoutNaverAppDownloadBanner mNaverDownloadBanner;
    private LinearLayout mNaviBar;
    private OAuthLoginData mOAuthLoginData;
    private String mOAuthSdkVersion;
    private WebView mWebView;
    private String mWebViewContent;
    private ProgressBar mWebviewProgressbar;
    private LinearLayout mWholeView;
    private boolean mIsLoginActivityStarted = false;
    private boolean mFixActivityPortrait = true;
    private boolean mVisibleNaverAppDownloadBanner = true;
    final DownloadListener mDefaultDownloadListener = new DownloadListener() { // from class: com.nhn.android.naverlogin.ui.OAuthLoginInAppBrowserActivity.1
        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            try {
                OAuthLoginInAppBrowserActivity.this.startActivity(intent);
            } catch (Throwable th) {
                th.printStackTrace();
                try {
                    intent.setData(Uri.parse(str));
                    OAuthLoginInAppBrowserActivity.this.startActivity(intent);
                } catch (Exception e) {
                }
            }
        }
    };

    public class InAppBrowserJavascriptInterface {
        Context context;

        InAppBrowserJavascriptInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void closeWebView() {
            ((Activity) this.context).finish();
        }
    }

    private class InAppWebChromeClient extends WebChromeClient {
        private InAppWebChromeClient() {
        }

        /* synthetic */ InAppWebChromeClient(OAuthLoginInAppBrowserActivity oAuthLoginInAppBrowserActivity, InAppWebChromeClient inAppWebChromeClient) {
            this();
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (OAuthLoginInAppBrowserActivity.this.mWebviewProgressbar != null) {
                OAuthLoginInAppBrowserActivity.this.mWebviewProgressbar.setProgress(i);
            }
        }
    }

    private class InAppWebViewClient extends WebViewClient {
        private String preUrl = "";

        public InAppWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public void onFormResubmission(WebView webView, Message message, Message message2) {
            super.onFormResubmission(webView, message, message2);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (OAuthLoginInAppBrowserActivity.this.mWebviewProgressbar != null) {
                OAuthLoginInAppBrowserActivity.this.mWebviewProgressbar.setVisibility(8);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(OAuthLoginInAppBrowserActivity.TAG, "[star] pre url : " + this.preUrl);
                Log.d(OAuthLoginInAppBrowserActivity.TAG, "[star]     url : " + str);
            }
            if (OAuthWebviewUrlUtil.isFinalUrl(false, this.preUrl, str)) {
                OAuthLoginInAppBrowserActivity.this.mWebView.stopLoading();
                OAuthLoginInAppBrowserActivity.this.finish();
            } else {
                if (OAuthWebviewUrlUtil.returnWhenAuthorizationDone(OAuthLoginInAppBrowserActivity.this.mContext, this.preUrl, str, OAuthLoginInAppBrowserActivity.this.mOAuthLoginData)) {
                    OAuthLoginInAppBrowserActivity.this.mWebView.stopLoading();
                    return;
                }
                super.onPageStarted(webView, str, bitmap);
                if (OAuthLoginInAppBrowserActivity.this.mWebviewProgressbar != null) {
                    OAuthLoginInAppBrowserActivity.this.mWebviewProgressbar.setVisibility(0);
                }
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (OAuthLoginInAppBrowserActivity.this.mWebviewProgressbar != null) {
                OAuthLoginInAppBrowserActivity.this.mWebviewProgressbar.setVisibility(8);
            }
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(OAuthLoginInAppBrowserActivity.TAG, "[over] pre url : " + this.preUrl);
                Log.d(OAuthLoginInAppBrowserActivity.TAG, "[over]     url : " + str);
            }
            if (OAuthWebviewUrlUtil.isFinalUrl(true, this.preUrl, str)) {
                OAuthLoginInAppBrowserActivity.this.mWebView.stopLoading();
                OAuthLoginInAppBrowserActivity.this.finish();
            } else if (!OAuthWebviewUrlUtil.returnWhenAuthorizationDone(OAuthLoginInAppBrowserActivity.this.mContext, this.preUrl, str, OAuthLoginInAppBrowserActivity.this.mOAuthLoginData)) {
                if (OAuthLoginInAppBrowserActivity.this.loadBrowser(str)) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    OAuthLoginInAppBrowserActivity.this.startActivity(intent);
                } else {
                    webView.loadUrl(str);
                    this.preUrl = str;
                }
            }
            return true;
        }
    }

    public class OAuthLoginInAppBrowserInIntentData {
        public static final String INTENT_PARAM_KEY_AGREE_FORM_CONTENT = "agreeFormContent";
        public static final String INTENT_PARAM_KEY_AGREE_FORM_URL = "agreeFormUrl";
        public static final String INTENT_PARAM_KEY_APP_NAME = "app_name";
        public static final String INTENT_PARAM_KEY_CALLBACK_URL = "ClientCallbackUrl";
        public static final String INTENT_PARAM_KEY_CLIENT_ID = "ClientId";
        public static final String INTENT_PARAM_KEY_OAUTH_SDK_VERSION = "oauth_sdk_version";
        public static final String INTENT_PARAM_KEY_STATE = "state";

        public OAuthLoginInAppBrowserInIntentData() {
        }
    }

    public class OAuthLoginInAppBrowserOutIntentData {
        public static final String RESULT_CALLBACK = "RESULT_CALLBACK";

        public OAuthLoginInAppBrowserOutIntentData() {
        }
    }

    private void initIntentData() {
        this.mContext = this;
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra(OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_CLIENT_ID);
            String stringExtra2 = getIntent().getStringExtra(OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_CALLBACK_URL);
            String stringExtra3 = getIntent().getStringExtra("state");
            String localeString = DeviceAppInfo.getBaseInstance().getLocaleString(this.mContext);
            String networkState = NetworkState.getNetworkState(this.mContext);
            this.mOAuthLoginData = new OAuthLoginData(stringExtra, null, stringExtra2, stringExtra3);
            this.mInOAuthUrl = new OAuthQueryGenerator().generateRequestInitUrl(stringExtra, this.mOAuthLoginData.getInitState(), stringExtra2, localeString, networkState);
            this.mOAuthSdkVersion = getIntent().getStringExtra(OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_OAUTH_SDK_VERSION);
            this.mFixActivityPortrait = OAuthLoginUiUtil.isFixActivityPortrait(this.mOAuthSdkVersion);
        }
    }

    private void initSavedInstanceStateData(Bundle bundle) {
        if (bundle != null) {
            this.mIsLoginActivityStarted = bundle.getBoolean(INSTANCE_STATE_WEBVIEW_RUN_ONLY_ONCE);
            if (this.mWebView != null) {
                this.mWebView.restoreState(bundle);
            }
            this.mOAuthSdkVersion = bundle.getString("SdkVersionCalledFrom");
            this.mFixActivityPortrait = bundle.getBoolean("IsFixActivityPortrait");
            this.mVisibleNaverAppDownloadBanner = bundle.getBoolean(INSTANCE_STATE_IS_VISIBLE_BANNER);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initView(Bundle bundle) {
        requestWindowFeature(1);
        this.drawableByteBottomBackGroundImg = OAuthLoginImage.hexToByteArray(OAuthLoginImage.drawableByteStrBottomBackGroundImg);
        this.drawableByteCloseBtnImg = OAuthLoginImage.hexToByteArray(OAuthLoginImage.drawableByteStrCloseBtnImg);
        this.mWebviewProgressbar = new ProgressBar(this, null, R.attr.progressBarStyleHorizontal);
        this.mWebviewProgressbar.setLayoutParams(new LinearLayout.LayoutParams(-1, 5));
        this.mWebviewProgressbar.setVisibility(8);
        this.mWebviewProgressbar.setMax(100);
        this.mWebView = new WebView(this);
        this.mWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setVerticalScrollbarOverlay(true);
        this.mWebView.setHorizontalScrollbarOverlay(true);
        this.mWebView.setWebViewClient(new InAppWebViewClient());
        this.mWebView.setWebChromeClient(new InAppWebChromeClient(this, null));
        this.mWebView.setDownloadListener(this.mDefaultDownloadListener);
        this.mWebView.addJavascriptInterface(new InAppBrowserJavascriptInterface(this), "AndroidLoginWebView");
        this.mWebView.getSettings().setUserAgentString(String.valueOf(this.mWebView.getSettings().getUserAgentString()) + " " + DeviceAppInfo.getBaseInstance().getUserAgent(this));
        this.mNaviBar = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) OAuthLoginImage.convertDpToPixel(40.0f, this.mContext));
        this.mNaviBar.setGravity(21);
        this.mNaviBar.setLayoutParams(layoutParams);
        this.mNaviBar.setOrientation(0);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(this.drawableByteBottomBackGroundImg, 0, this.drawableByteBottomBackGroundImg.length));
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                this.mNaviBar.setBackground(bitmapDrawable);
            } else {
                this.mNaviBar.setBackgroundDrawable(bitmapDrawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mImgSeperator = new ImageView(this);
        this.mImgSeperator.setLayoutParams(new LinearLayout.LayoutParams((int) OAuthLoginImage.convertDpToPixel(1.0f, this.mContext), -1));
        this.mImgSeperator.setBackgroundColor(Color.argb(255, 0, 0, 0));
        this.mImgSeperator.invalidate();
        this.mImgCloseButton = new ImageView(this);
        this.mImgCloseButton.setLayoutParams(new LinearLayout.LayoutParams(OAuthLoginImage.getScreenWidth((Activity) this.mContext) / 4, (int) OAuthLoginImage.convertDpToPixel(21.333334f, this.mContext)));
        this.mImgCloseButton.setImageDrawable(new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(this.drawableByteCloseBtnImg, 0, this.drawableByteCloseBtnImg.length)));
        this.mImgCloseButton.setClickable(true);
        this.mImgCloseButton.setOnClickListener(this);
        this.mNaviBar.addView(this.mImgSeperator);
        this.mNaviBar.addView(this.mImgCloseButton);
        if (OAuthLoginDefine.MARKET_LINK_WORKING && this.mVisibleNaverAppDownloadBanner) {
            this.mNaverDownloadBanner = new OAuthLoginLayoutNaverAppDownloadBanner(this);
        }
        this.mWholeView = new LinearLayout(this);
        this.mWholeView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.mWholeView.setOrientation(1);
        if (OAuthLoginDefine.MARKET_LINK_WORKING && this.mNaverDownloadBanner != null && this.mVisibleNaverAppDownloadBanner) {
            this.mWholeView.addView(this.mNaverDownloadBanner);
        }
        this.mWholeView.addView(this.mWebviewProgressbar);
        this.mWholeView.addView(this.mWebView);
        this.mWholeView.addView(this.mNaviBar);
        setContentView(this.mWholeView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean loadBrowser(String str) {
        if (str.length() <= 0 || str.contentEquals("about:blank")) {
            return false;
        }
        if (str.startsWith(CookieUtil.COOKIE_DOMAIN_NID)) {
            return str.startsWith("https://nid.naver.com/mobile/user/help/idInquiry.nhn") || str.startsWith("https://nid.naver.com/mobile/user/help/pwInquiry.nhn") || str.startsWith("https://nid.naver.com/user/mobile_join.nhn");
        }
        return (str.startsWith("https://nid.naver.com/nidlogin.logout") || str.startsWith("http://nid.naver.com/nidlogin.logout") || str.contains("/sso/logout.nhn") || str.contains("/sso/cross-domain.nhn") || str.contains("/sso/finalize.nhn") || str.startsWith("http://cc.naver.com") || str.startsWith("http://cr.naver.com") || str.startsWith("https://cert.vno.co.kr") || str.startsWith("https://ipin.ok-name.co.kr") || str.startsWith("https://ipin.siren24.com")) ? false : true;
    }

    private void registerSizeChangeListener() {
        final LinearLayout linearLayout = this.mWholeView;
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.nhn.android.naverlogin.ui.OAuthLoginInAppBrowserActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Rect rect = new Rect();
                linearLayout.getWindowVisibleDisplayFrame(rect);
                if (linearLayout.getRootView().getHeight() - (rect.bottom - rect.top) > 100) {
                    OAuthLoginInAppBrowserActivity.this.mNaviBar.setVisibility(8);
                } else {
                    OAuthLoginInAppBrowserActivity.this.mNaviBar.setVisibility(0);
                }
            }
        });
    }

    private void runOnlyOnce() {
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra(OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_AGREE_FORM_URL);
            if (!TextUtils.isEmpty(stringExtra)) {
                this.mInOAuthUrl = stringExtra;
            }
            this.mWebViewContent = getIntent().getStringExtra(OAuthLoginInAppBrowserInIntentData.INTENT_PARAM_KEY_AGREE_FORM_CONTENT);
        }
        if (TextUtils.isEmpty(this.mWebViewContent)) {
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "webview url -> " + this.mInOAuthUrl);
            }
            this.mWebView.loadUrl(this.mInOAuthUrl);
        } else {
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "webview url -> " + this.mInOAuthUrl);
                Log.d(TAG, "webview content -> " + this.mWebViewContent);
            }
            this.mWebView.loadDataWithBaseURL(this.mInOAuthUrl, this.mWebViewContent, "text/html", null, null);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mImgCloseButton) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initIntentData();
        initSavedInstanceStateData(bundle);
        initView(bundle);
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "webview onCreate() fix:" + this.mFixActivityPortrait);
        }
        if (this.mFixActivityPortrait) {
            setRequestedOrientation(1);
        }
        registerSizeChangeListener();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "webview onDestroy()");
        }
        this.drawableByteBottomBackGroundImg = null;
        this.drawableByteCloseBtnImg = null;
        if (this.mWebView != null) {
            this.mWebView.stopLoading();
            if (this.mWholeView != null) {
                this.mWholeView.removeView(this.mWebView);
            }
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.mWebView != null && Build.VERSION.SDK_INT >= 11) {
            this.mWebView.onPause();
        }
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "webview onPause()");
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        initSavedInstanceStateData(bundle);
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "webview onRestoreInstanceState() first:" + this.mIsLoginActivityStarted + ", sdk:" + this.mOAuthSdkVersion + ", fix:" + this.mFixActivityPortrait);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.mWebView != null) {
            this.mWebView.resumeTimers();
            if (Build.VERSION.SDK_INT >= 11) {
                this.mWebView.onResume();
            }
        }
        if (!this.mIsLoginActivityStarted) {
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "webview onResume() first");
            }
            this.mIsLoginActivityStarted = true;
            runOnlyOnce();
        }
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "webview onResume()");
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (OAuthLoginDefine.DEVELOPER_VERSION) {
            Log.d(TAG, "webview onSaveInstanceState()");
        }
        bundle.putBoolean(INSTANCE_STATE_WEBVIEW_RUN_ONLY_ONCE, this.mIsLoginActivityStarted);
        if (this.mWebView != null) {
            this.mWebView.saveState(bundle);
        }
        bundle.putString("SdkVersionCalledFrom", this.mOAuthSdkVersion);
        bundle.putBoolean("IsFixActivityPortrait", this.mFixActivityPortrait);
        if (this.mVisibleNaverAppDownloadBanner && this.mNaverDownloadBanner != null && this.mNaverDownloadBanner.getVisibility() == 0) {
            bundle.putBoolean(INSTANCE_STATE_IS_VISIBLE_BANNER, true);
        } else {
            bundle.putBoolean(INSTANCE_STATE_IS_VISIBLE_BANNER, false);
        }
    }
}
