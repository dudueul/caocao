package kr.co.nexon.android.sns.nxcom.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import com.facebook.GraphResponse;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.npaccount.R;
import java.util.Random;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.mdev.android.util.NXLocalizedString;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPNXComLoginByTPADialog extends NPDialogBase {
    public static final String KEY_PACKAGE = "package";
    public static final String KEY_SCHEME = "scheme";
    public static final String KEY_TPASTATE = "state";
    public static final String KEY_TPATOKEN = "token";
    public static final String KEY_TPA_TYPE = "tpaType";
    protected NPAuthListener resultListener;
    protected WebView webview;

    public class NXTPAWebViewClient extends WebViewClient {
        private Activity activity;
        private String tpaloginPrefix = "nxp://NexonComTPAAuth";

        public NXTPAWebViewClient(Activity activity) {
            this.activity = activity;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (NXPNXComLoginByTPADialog.this.progressDialog.isShowing()) {
                NXPNXComLoginByTPADialog.this.progressDialog.dismiss();
            }
            ToyLog.d(str);
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (!NXPNXComLoginByTPADialog.this.progressDialog.isShowing()) {
                NXPNXComLoginByTPADialog.this.progressDialog.show();
            }
            ToyLog.d(str);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (NXPNXComLoginByTPADialog.this.progressDialog.isShowing()) {
                NXPNXComLoginByTPADialog.this.progressDialog.dismiss();
            }
            ToyLog.d(i + " " + str + " " + str2);
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!str.startsWith(this.tpaloginPrefix)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            ToyLog.d(str);
            Bundle bundle = new Bundle();
            try {
                String strSubstring = str.substring(str.indexOf("token="));
                String strSubstring2 = strSubstring.substring(strSubstring.indexOf("=") + 1);
                ToyLog.d("tokenValue : " + strSubstring2);
                String string = NXPNXComLoginByTPADialog.this.getArguments().getString("state");
                ToyLog.d("state : " + string);
                if (NXPNXComLoginByTPADialog.this.resultListener != null) {
                    bundle.putString("token", strSubstring2);
                    bundle.putString("state", string);
                    NXPNXComLoginByTPADialog.this.resultListener.onResult(0, GraphResponse.SUCCESS_KEY, bundle);
                }
                NXPNXComLoginByTPADialog.this.dismiss();
                return true;
            } catch (IndexOutOfBoundsException e) {
                NXPNXComLoginByTPADialog.this.resultListener.onResult(0, e.getMessage(), bundle);
                NXPNXComLoginByTPADialog.this.dismiss();
                return true;
            }
        }
    }

    private static String generatorTPAStateString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_.-".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_.-".length())));
        }
        return sb.toString();
    }

    private void initWebView() {
        final String locale = NXToyCommonPreferenceController.getInstance().getLocale();
        this.webview.setWebViewClient(new NXTPAWebViewClient(getActivity()));
        this.webview.setWebChromeClient(new WebChromeClient() { // from class: kr.co.nexon.android.sns.nxcom.ui.NXPNXComLoginByTPADialog.2
            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
                if (Build.VERSION.SDK_INT >= 21) {
                    return super.onJsAlert(webView, str, str2, jsResult);
                }
                new NXPAlertDialog.Builder(NXPNXComLoginByTPADialog.this.getActivity()).setMessage(str2).setPositiveButton(NXLocalizedString.getText(NXPNXComLoginByTPADialog.this.applicationContext, locale, R.string.yes), new View.OnClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NXPNXComLoginByTPADialog.2.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        jsResult.confirm();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NXPNXComLoginByTPADialog.2.1
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
                new NXPAlertDialog.Builder(NXPNXComLoginByTPADialog.this.getActivity()).setMessage(str2).setPositiveButton(NXLocalizedString.getText(NXPNXComLoginByTPADialog.this.applicationContext, locale, R.string.yes), new View.OnClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NXPNXComLoginByTPADialog.2.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        jsResult.confirm();
                    }
                }).setNegativeButton(NXLocalizedString.getText(NXPNXComLoginByTPADialog.this.applicationContext, NXToyCommonPreferenceController.getInstance().getLocale(), R.string.no), new View.OnClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NXPNXComLoginByTPADialog.2.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        jsResult.cancel();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NXPNXComLoginByTPADialog.2.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        jsResult.cancel();
                    }
                }).create().show();
                return true;
            }
        });
        WebSettings settings = this.webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        this.webview.clearCache(true);
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT > 20) {
            cookieManager.removeAllCookies(null);
        } else {
            cookieManager.removeAllCookie();
        }
    }

    public static NXPNXComLoginByTPADialog newInstance(Activity activity, String str) {
        NXPNXComLoginByTPADialog nXPNXComLoginByTPADialog = new NXPNXComLoginByTPADialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString(KEY_TPA_TYPE, str);
        bundle.putString(KEY_SCHEME, "nxp");
        bundle.putString(KEY_PACKAGE, activity.getPackageName());
        bundle.putString("state", generatorTPAStateString());
        nXPNXComLoginByTPADialog.setArguments(bundle);
        return nXPNXComLoginByTPADialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        Dialog dialog = getDialog();
        dialog.setContentView(R.layout.nxp_common_web);
        FrameLayout frameLayout = (FrameLayout) dialog.findViewById(R.id.webViewContainer);
        this.webview = new WebView(getActivity());
        this.webview.setBackgroundResource(R.color.nxp_color_common_webview_background);
        frameLayout.addView(this.webview, new FrameLayout.LayoutParams(-1, -1));
        ((Button) dialog.findViewById(R.id.btnBack)).setVisibility(4);
        ((Button) dialog.findViewById(R.id.btnClose)).setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NXPNXComLoginByTPADialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NXPNXComLoginByTPADialog.this.dismiss();
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append("https://login.nexon.com/login/app/").append(getArguments().getString(KEY_TPA_TYPE)).append("?").append(KEY_SCHEME).append("=").append(getArguments().get(KEY_SCHEME)).append("&").append("state").append("=").append(getArguments().get("state"));
        initTPALogin(sb.toString());
        return getView();
    }

    public void initTPALogin(String str) {
        initWebView();
        this.webview.loadUrl(str);
    }

    public void setResultListener(NPAuthListener nPAuthListener) {
        this.resultListener = nPAuthListener;
    }
}
