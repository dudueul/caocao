package kr.co.nexon.android.sns.daum;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.mdev.android.util.NXLocalizedString;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import net.daum.mf.oauth.impl.Constant;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPDaumLoginWebDialog extends NPDialogBase {
    public static final String KEY_CLIENT_ID = "clientId";
    public static final String KEY_URL = "url";
    public static final String TAG = "NPDaumLoginWebDialog";
    private String clientId;
    String locale;
    private NPAuthListener resultListener;
    private LinearLayout webViewContainer;
    private boolean webViewErrorFlag;
    private ArrayList<WebView> webViewList;

    class NPWebViewClient extends WebViewClient {
        NPWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (NPDaumLoginWebDialog.this.progressDialog != null && NPDaumLoginWebDialog.this.progressDialog.isShowing()) {
                NPDaumLoginWebDialog.this.progressDialog.dismiss();
            }
            if (NPDaumLoginWebDialog.this.webViewErrorFlag) {
                return;
            }
            webView.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (NPDaumLoginWebDialog.this.getActivity().isFinishing()) {
                return;
            }
            NPDaumLoginWebDialog.this.progressDialog.show();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            ToyLog.e("errorCode " + i + " description " + str + " url " + str2);
            if (NPDaumLoginWebDialog.this.progressDialog == null || !NPDaumLoginWebDialog.this.progressDialog.isShowing()) {
                return;
            }
            NPDaumLoginWebDialog.this.progressDialog.dismiss();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!str.startsWith(Constant.SCHEME_OAUTH_PREFIX + NPDaumLoginWebDialog.this.clientId)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            Uri uri = Uri.parse(str.replace("#", "?"));
            String queryParameter = uri.getQueryParameter("access_token");
            String queryParameter2 = uri.getQueryParameter("expires_in");
            String queryParameter3 = uri.getQueryParameter("error");
            ToyLog.v("access_token" + queryParameter);
            ToyLog.d("expires_in:" + queryParameter2);
            NPDaumLoginWebDialog.this.sendDaumChannelInfo(uri, queryParameter3 != null ? -1 : 1);
            NPDaumLoginWebDialog.this.dismiss();
            return true;
        }
    }

    class NXPWebChromeClinet extends WebChromeClient {
        NXPWebChromeClinet() {
        }

        @Override // android.webkit.WebChromeClient
        public void onCloseWindow(WebView webView) {
            NPDaumLoginWebDialog.this.webViewContainer.removeAllViews();
            NPDaumLoginWebDialog.this.webViewList.remove(webView);
            webView.loadUrl("about:blank");
            webView.clearCache(true);
            webView.destroy();
            NPDaumLoginWebDialog.this.webViewContainer.addView((View) NPDaumLoginWebDialog.this.webViewList.get(NPDaumLoginWebDialog.this.webViewList.size() - 1));
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            NPDaumLoginWebDialog.this.webViewContainer.removeAllViews();
            ((WebView.WebViewTransport) message.obj).setWebView(NPDaumLoginWebDialog.this.createWebView(webView.getContext()));
            message.sendToTarget();
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            if (Build.VERSION.SDK_INT >= 21) {
                return super.onJsAlert(webView, str, str2, jsResult);
            }
            new NXPAlertDialog.Builder(NPDaumLoginWebDialog.this.getActivity()).setMessage(str2).setPositiveButton(NXLocalizedString.getText(NPDaumLoginWebDialog.this.getActivity(), NPDaumLoginWebDialog.this.locale, R.string.confirm), new View.OnClickListener() { // from class: kr.co.nexon.android.sns.daum.NPDaumLoginWebDialog.NXPWebChromeClinet.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    jsResult.confirm();
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.android.sns.daum.NPDaumLoginWebDialog.NXPWebChromeClinet.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    jsResult.confirm();
                }
            }).create().show();
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            if (Build.VERSION.SDK_INT >= 21) {
                return super.onJsConfirm(webView, str, str2, jsResult);
            }
            new NXPAlertDialog.Builder(NPDaumLoginWebDialog.this.getActivity()).setMessage(str2).setPositiveButton(NXLocalizedString.getText(NPDaumLoginWebDialog.this.getActivity(), NPDaumLoginWebDialog.this.locale, R.string.yes), new View.OnClickListener() { // from class: kr.co.nexon.android.sns.daum.NPDaumLoginWebDialog.NXPWebChromeClinet.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    jsResult.confirm();
                }
            }).setNegativeButton(NXLocalizedString.getText(NPDaumLoginWebDialog.this.getActivity(), NXToyCommonPreferenceController.getInstance().getLocale(), R.string.no), new View.OnClickListener() { // from class: kr.co.nexon.android.sns.daum.NPDaumLoginWebDialog.NXPWebChromeClinet.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    jsResult.cancel();
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.android.sns.daum.NPDaumLoginWebDialog.NXPWebChromeClinet.3
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    jsResult.cancel();
                }
            }).create().show();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WebView createWebView(Context context) {
        WebView webView = new WebView(context);
        webView.setWebViewClient(new NPWebViewClient());
        webView.setWebChromeClient(new NXPWebChromeClinet());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportMultipleWindows(true);
        removeCookie();
        this.webViewContainer.addView(webView);
        this.webViewList.add(webView);
        return webView;
    }

    public static NPDaumLoginWebDialog newInstance(Activity activity, String str, String str2) {
        NPDaumLoginWebDialog nPDaumLoginWebDialog = new NPDaumLoginWebDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("url", str);
        bundle.putString(KEY_CLIENT_ID, str2);
        nPDaumLoginWebDialog.setArguments(bundle);
        return nPDaumLoginWebDialog;
    }

    private void removeCookie() {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT > 20) {
            cookieManager.removeAllCookies(null);
        } else {
            cookieManager.removeAllCookie();
        }
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        Dialog dialog = getDialog();
        dialog.setContentView(R.layout.login);
        dialog.getWindow().setLayout(-1, -1);
        this.webViewList = new ArrayList<>();
        String string = getArguments().getString("url");
        this.clientId = getArguments().getString(KEY_CLIENT_ID);
        TextView textView = (TextView) dialog.findViewById(R.id.title);
        ImageButton imageButton = (ImageButton) dialog.findViewById(R.id.closeBtn);
        ImageButton imageButton2 = (ImageButton) dialog.findViewById(R.id.backBtn);
        textView.setText("Daum Login");
        this.locale = NXToyCommonPreferenceController.getInstance().getLocale();
        this.webViewContainer = (LinearLayout) dialog.findViewById(R.id.webViewContainer);
        WebView webViewCreateWebView = createWebView(dialog.getContext());
        imageButton2.setVisibility(4);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.android.sns.daum.NPDaumLoginWebDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NPDaumLoginWebDialog.this.resultListener != null) {
                    NPDaumLoginWebDialog.this.resultListener.onResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", null);
                }
                NPDaumLoginWebDialog.this.dismiss();
            }
        });
        webViewCreateWebView.loadUrl(string);
        return getView();
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (this.progressDialog == null || !this.progressDialog.isShowing()) {
            WebView webView = this.webViewList.get(this.webViewList.size() - 1);
            if (webView.canGoBack()) {
                webView.goBack();
                return;
            }
            if (this.webViewList.size() <= 1) {
                if (this.resultListener != null) {
                    this.resultListener.onResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", null);
                }
                super.onBackPressed();
            } else {
                this.webViewContainer.removeAllViews();
                this.webViewList.remove(webView);
                webView.loadUrl("about:blank");
                webView.clearCache(true);
                webView.destroy();
                this.webViewContainer.addView(this.webViewList.get(this.webViewList.size() - 1));
            }
        }
    }

    public void sendDaumChannelInfo(Uri uri, int i) {
        if (NPDaum.mDaumOAuthLoginHandler != null) {
            Message message = new Message();
            message.obj = uri;
            message.what = i;
            NPDaum.mDaumOAuthLoginHandler.sendMessage(message);
        }
    }

    public void setResultListener(NPAuthListener nPAuthListener) {
        this.resultListener = nPAuthListener;
    }
}
