package kr.co.nexon.android.sns.email.ui;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;
import java.util.Map;
import kr.co.nexon.android.sns.email.util.NPLocalizedStringWrapper;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPEmailWebDialog extends NPDialogBase {
    public static final String KEY_URL = "url";
    public static final String TAG = "NPEmailWebDialog";
    private Button backButton;
    private Button closeButton;
    private NPLocalizedStringWrapper stringWrapper;
    private WebView webView;
    private FrameLayout webViewContainer;
    private boolean webViewErrorFlag;

    public class NXPWebViewClient extends WebViewClient {
        public NXPWebViewClient() {
        }

        public void checkCanGoBack(WebView webView, String str) {
            if (!webView.canGoBack() || NPEmailWebDialog.this.backButton == null) {
                return;
            }
            NPEmailWebDialog.this.backButton.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (NPEmailWebDialog.this.progressDialog != null && NPEmailWebDialog.this.progressDialog.isShowing()) {
                NPEmailWebDialog.this.progressDialog.dismiss();
                NPEmailWebDialog.this.closeButton.setVisibility(0);
                checkCanGoBack(webView, str);
            }
            if (NPEmailWebDialog.this.webViewErrorFlag) {
                return;
            }
            webView.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            NPEmailWebDialog.this.closeButton.setVisibility(4);
            if (NPEmailWebDialog.this.backButton != null) {
                NPEmailWebDialog.this.backButton.setVisibility(4);
            }
            if (NPEmailWebDialog.this.getActivity().isFinishing()) {
                return;
            }
            NPEmailWebDialog.this.progressDialog.show();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            Log.e("WebViewERROR", "errorCode " + i + " description " + str + " url " + str2);
            NPEmailWebDialog.this.webViewErrorFlag = true;
            webView.setVisibility(8);
            String webViewClientErrorText = NPEmailWebDialog.this.getWebViewClientErrorText(i);
            if (NXStringUtil.isNotNull(webViewClientErrorText)) {
                Toast.makeText(NPEmailWebDialog.this.getActivity(), webViewClientErrorText, 0).show();
            } else {
                new NXPAlertDialog.Builder(NPEmailWebDialog.this.getActivity()).setMessage(str).setPositiveButton(NPEmailWebDialog.this.stringWrapper.getString(R.string.confirm), new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailWebDialog.NXPWebViewClient.1
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        NPEmailWebDialog.this.dismiss();
                    }
                }).create().show();
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            NPEmailWebDialog.this.webView.loadUrl(str, NPEmailWebDialog.this.getToyExtraHeader());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> getToyExtraHeader() {
        HashMap map = new HashMap();
        map.put("x-toy-locale", NXToyCommonPreferenceController.getInstance().getLocale().replace(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, "-"));
        return map;
    }

    private void initView(Dialog dialog) {
        dialog.setContentView(R.layout.nxp_common_web);
        this.webView = new WebView(getActivity());
        this.webView.setBackgroundResource(R.color.nxp_color_common_webview_background);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.webView.setWebViewClient(new NXPWebViewClient());
        WebSettings settings = this.webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        this.webViewContainer = (FrameLayout) dialog.findViewById(R.id.webViewContainer);
        this.webViewContainer.addView(this.webView, new FrameLayout.LayoutParams(-1, -1));
        this.backButton = (Button) dialog.findViewById(R.id.btnBack);
        this.backButton.setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailWebDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPEmailWebDialog.this.onBackPressed();
            }
        });
        this.closeButton = (Button) dialog.findViewById(R.id.btnClose);
        this.closeButton.setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.email.ui.NPEmailWebDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPEmailWebDialog.this.dismiss();
            }
        });
    }

    public static NPEmailWebDialog newInstance(Activity activity, String str) {
        NPEmailWebDialog nPEmailWebDialog = new NPEmailWebDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("url", str);
        nPEmailWebDialog.setArguments(bundle);
        return nPEmailWebDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        initView(getDialog());
        this.stringWrapper = new NPLocalizedStringWrapper(getActivity());
        String string = getArguments().getString("url");
        this.progressDialog.show();
        this.webView.loadUrl(string, getToyExtraHeader());
        return getView();
    }

    protected String getWebViewClientErrorText(int i) {
        switch (i) {
            case -6:
            case -2:
            case -1:
                return this.stringWrapper.getString(R.string.npres_check_network);
            case -5:
            case -4:
            case -3:
            default:
                return "";
        }
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (this.progressDialog == null || !this.progressDialog.isShowing()) {
            if (this.webView.canGoBack()) {
                this.webView.goBack();
            } else {
                super.onBackPressed();
            }
        }
    }
}
