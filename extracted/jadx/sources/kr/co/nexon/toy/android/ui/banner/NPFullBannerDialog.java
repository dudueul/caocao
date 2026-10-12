package kr.co.nexon.toy.android.ui.banner;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXDateUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.model.NXToyBanner;
import kr.co.nexon.npaccount.promotion.NXBannerManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.banner.view.NXPBannerView;
import kr.co.nexon.toy.android.ui.board.NPShowTodayDialog;
import kr.co.nexon.toy.android.ui.util.NXPUIBackupUtil;
import kr.co.nexon.toy.listener.NXPWebViewClientInterface;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPFullBannerDialog extends NPDialogBase {
    public static final String TAG = "NPFullBannerDialog";
    private static final int WORK_ON_BTN_TYPE_NONE = 2;
    private List<NXToyBanner> bannerList;
    private NXBannerManager bannerManager;
    private NXPBannerView bannerView;
    private int currentBannerIdx;

    private class NXPBannerWebViewClient extends WebViewClient {
        private NXPWebViewClientInterface webViewClientInterface;

        public NXPBannerWebViewClient(NXPWebViewClientInterface nXPWebViewClientInterface) {
            this.webViewClientInterface = nXPWebViewClientInterface;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (this.webViewClientInterface != null) {
                this.webViewClientInterface.onPageFinished(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (this.webViewClientInterface != null) {
                this.webViewClientInterface.onPageStarted(webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (this.webViewClientInterface != null) {
                this.webViewClientInterface.onReceivedError(webView, i, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return this.webViewClientInterface != null ? this.webViewClientInterface.shouldOverrideUrlLoading(webView, str) : super.shouldOverrideUrlLoading(webView, str);
        }
    }

    private WebView createBannerWebView(NXPWebViewClientInterface nXPWebViewClientInterface) {
        WebView webView = new WebView(getActivity());
        webView.setVerticalScrollbarOverlay(true);
        webView.setWebViewClient(new NXPBannerWebViewClient(nXPWebViewClientInterface));
        webView.setBackgroundColor(0);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        return webView;
    }

    private void loadNextBanner() {
        NXToyBanner nXToyBanner = this.bannerList.get(this.currentBannerIdx);
        if (NXStringUtil.isNull(nXToyBanner.buttonText) || nXToyBanner.landType == 2) {
            this.bannerView.setWorkOnButtonVisibility(8);
        } else {
            this.bannerView.setWorkOnButtonText(nXToyBanner.buttonText);
            this.bannerView.setWorkOnButtonVisibility(0);
        }
        this.bannerView.setChecked(false);
        this.bannerView.loadUrl(nXToyBanner.imgURL, this.currentBannerIdx);
    }

    public static NPFullBannerDialog newInstance(Activity activity) {
        NPFullBannerDialog nPFullBannerDialog = new NPFullBannerDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        nPFullBannerDialog.setArguments(bundle);
        return nPFullBannerDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    protected void activityCreated() {
        loadNextBanner();
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.currentBannerIdx = 0;
        this.bannerManager = NXBannerManager.getInstance(this.applicationContext);
        String string = NXToyLocaleManager.getInstance(this.applicationContext).getString(R.string.dont_show_today);
        this.bannerView = (NXPBannerView) View.inflate(this.applicationContext, R.layout.nxp_banner_view, null);
        this.bannerView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.NPFullBannerDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPFullBannerDialog.this.onBackPressed();
            }
        });
        this.bannerView.setOnBannerClickListener(new NXPBannerView.NXPBannerClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.NPFullBannerDialog.2
            @Override // kr.co.nexon.toy.android.ui.banner.view.NXPBannerView.NXPBannerClickListener
            public void onClick(int i, int i2) {
                if (i < 0) {
                    ToyLog.d("key is invalid banner key:" + i);
                } else {
                    NPFullBannerDialog.this.bannerManager.clickBanner(NPFullBannerDialog.this.getActivity(), NPFullBannerDialog.this, (NXToyBanner) NPFullBannerDialog.this.bannerList.get(i), i2 == 0 ? 1 : 0);
                }
            }
        });
        this.bannerView.setOnCheckBoxChangeListener(new NXPBannerView.NXPBannerCheckBoxChangeListener() { // from class: kr.co.nexon.toy.android.ui.banner.NPFullBannerDialog.3
            @Override // kr.co.nexon.toy.android.ui.banner.view.NXPBannerView.NXPBannerCheckBoxChangeListener
            public void onChangeCheckBox(boolean z) {
                if (NPFullBannerDialog.this.currentBannerIdx >= NPFullBannerDialog.this.bannerList.size()) {
                    ToyLog.d("banner currentIndex is invalid  curIndex:" + NPFullBannerDialog.this.currentBannerIdx + "  , bannerSize:" + NPFullBannerDialog.this.bannerList.size());
                } else if (z) {
                    NPFullBannerDialog.this.bannerManager.setNotShowDateForBanner(((NXToyBanner) NPFullBannerDialog.this.bannerList.get(NPFullBannerDialog.this.currentBannerIdx)).sn, NXDateUtil.getCurrentTimeFormat(NPShowTodayDialog.KEY_DATE_FORMAT));
                } else {
                    NPFullBannerDialog.this.bannerManager.setNotShowDateForBanner(((NXToyBanner) NPFullBannerDialog.this.bannerList.get(NPFullBannerDialog.this.currentBannerIdx)).sn, "");
                }
            }
        });
        this.bannerView.setCheckBoxButtonText(string);
        this.bannerView.addBannerWebView(createBannerWebView(this.bannerView));
        return this.bannerView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public synchronized void onBackPressed() {
        this.currentBannerIdx++;
        if (this.currentBannerIdx < this.bannerList.size()) {
            loadNextBanner();
        } else {
            this.bannerManager.dismissBannerDialog(getDialog());
        }
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        WebView webViewRemoveBannerWebView = this.bannerView.removeBannerWebView();
        List<NXPUIBackupUtil.ViewData> listBackupViewDataFromDialog = NXPUIBackupUtil.backupViewDataFromDialog(dialog, this.bannerView.getBackupResouceIdList());
        this.bannerView = (NXPBannerView) createView();
        this.bannerView.addBannerWebView(webViewRemoveBannerWebView);
        this.bannerView.setBannerKey(this.currentBannerIdx);
        dialog.setContentView(this.bannerView);
        NXPUIBackupUtil.restoreViewDataToDialog(dialog, listBackupViewDataFromDialog);
    }

    public void setBannerInfoList(List<NXToyBanner> list) {
        this.bannerList = list;
    }
}
