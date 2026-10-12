package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.appevents.UserDataStore;
import com.nexon.core.android.NXPApplicationConfigInterface;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.util.NXStringUtil;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import java.util.HashMap;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.auth.result.NXToyWebShopResult;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPShopDialog extends NPWebDialogFullScreen {
    public static final String KEY_COUNTRY_CODE = "nxpCountryCode";
    public static final String KEY_LOCALE_CODE = "nxpLocaleCode";
    public static final String KEY_SHOP_ID = "shopid";
    private static final String NP_COMMON_PREFIX = "webshop://shop.mp.nexon.com?";
    private static final String NP_LINKTYPE_META = "META";
    private static final String NP_LINKTYPE_URL_SCHEME = "URL_SCHEME";
    private static final String NP_QUERY_PARAMETER_LINK = "link";
    private static final String NP_QUERY_PARAMETER_LINKTYPE = "linktype";
    public static final String TAG = "NPShopDialog";
    private String countryCode;
    private String localeCode;
    private NPListener resultListener;
    private int shopid;

    public static NPShopDialog newInstance(Activity activity, int i, String str, String str2) {
        NPShopDialog nPShopDialog = new NPShopDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putInt(KEY_SHOP_ID, i);
        bundle.putString(KEY_LOCALE_CODE, str);
        bundle.putString(KEY_COUNTRY_CODE, str2);
        nPShopDialog.setArguments(bundle);
        return nPShopDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlert(String str) {
        new NXPAlertDialog.Builder(getActivity()).setMessage(str).show();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen, kr.co.nexon.toy.android.ui.board.NPWebDialogBase, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        Bundle arguments = getArguments();
        this.shopid = arguments.getInt(KEY_SHOP_ID, 0);
        this.localeCode = arguments.getString(KEY_LOCALE_CODE, "");
        this.countryCode = arguments.getString(KEY_COUNTRY_CODE, "");
        return super.createView();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen, kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public String getURL() {
        this.currentWebView.getSettings().setUseWideViewPort(false);
        String url = NXPApplicationConfigManager.getInstance().getServerURL(NXPApplicationConfigInterface.ServerType.WebShop).getUrl();
        HashMap map = new HashMap();
        map.put(UserDataStore.COUNTRY, this.countryCode);
        map.put("locale", this.localeCode);
        map.put(NxLogInfo.KEY_NPSN, String.valueOf(NXToySessionManager.getInstance().getSession().getNpsn()));
        map.put("svcId", NXToySessionManager.getInstance().getSession().getServiceId());
        setExtraHeader(map);
        return url + "?shopId=" + this.shopid + "&market=GOOGLE";
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public WebViewClient getWebViewClient() {
        return new NPWebDialogBase.NPWebViewClient() { // from class: kr.co.nexon.toy.android.ui.board.NPShopDialog.1
            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                ToyLog.d("webshop url:" + str);
                if (!str.startsWith(NPShopDialog.NP_COMMON_PREFIX)) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                String queryParameter = Uri.parse(str).getQueryParameter(NPShopDialog.NP_QUERY_PARAMETER_LINKTYPE);
                if (NXStringUtil.isNull(queryParameter)) {
                    NPShopDialog.this.showAlert("Linktype is empty.");
                    NPShopDialog.this.dismiss();
                    return true;
                }
                String queryParameter2 = Uri.parse(str).getQueryParameter("link");
                if (queryParameter.equals(NPShopDialog.NP_LINKTYPE_URL_SCHEME)) {
                    try {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(Uri.parse(queryParameter2));
                        intent.setFlags(536870912);
                        NPShopDialog.this.startActivity(intent);
                        NPShopDialog.this.dismiss();
                    } catch (Exception e) {
                        NPShopDialog.this.showAlert("The scheme cannot be called.");
                        return true;
                    }
                } else if (queryParameter.equals(NPShopDialog.NP_LINKTYPE_META)) {
                    if (NPShopDialog.this.resultListener == null) {
                        NPShopDialog.this.showAlert("The listener is not registered.");
                        return true;
                    }
                    NXToyWebShopResult nXToyWebShopResult = new NXToyWebShopResult();
                    nXToyWebShopResult.result.meta = queryParameter2;
                    nXToyWebShopResult.requestTag = NXToyRequestTag.WebShop.getValue();
                    NPShopDialog.this.resultListener.onResult(nXToyWebShopResult);
                    NPShopDialog.this.dismiss();
                    return true;
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        };
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase, android.app.Fragment
    public void onDestroy() {
        if (this.webViewList == null) {
            super.onDestroy();
            return;
        }
        for (int i = 0; i < this.webViewList.size(); i++) {
            if (this.webViewContainer != null) {
                this.webViewContainer.removeAllViews();
            }
            WebView webView = this.webViewList.get(i);
            webView.stopLoading();
            webView.loadUrl("about:blank");
            webView.clearCache(true);
            webView.destroyDrawingCache();
            webView.removeAllViews();
            webView.destroy();
        }
        this.webViewList.clear();
        if (this.resultListener != null) {
            this.resultListener = null;
        }
        super.onDestroy();
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }
}
