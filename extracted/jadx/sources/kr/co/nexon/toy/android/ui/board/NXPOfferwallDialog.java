package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.npaccount.R;
import java.util.Arrays;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyCloseResult;
import kr.co.nexon.npaccount.auth.result.model.NXPToyOfferwall;
import kr.co.nexon.npaccount.listener.NPCloseListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPOfferwallDialog extends NPWebDialogBase {
    private static final String KEY_LIMIT_AD_TRACKING_ENABLED = "limitAdTrackingEnabled";
    private static final int KEY_OFFERWALL_CONTENTS_PAGE = 0;
    private static final int KEY_OFFERWALL_HELP_PAGE = 1;
    private static final String KEY_OFFERWALL_INFO = "offerwallInfo";
    private static final String KEY_SCREEN_ORIENTATION = "screenOrientation";
    public static final String TAG = "NXPOfferwallDialog";
    private NPCloseListener closeListener;
    private String helpPageUrl;
    private int offerwallId;
    private String offerwallTitle;
    private int printedPageInfo;
    private TextView title;

    /* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
    public class NXPOfferwallJavaScriptInterface {
        public NXPOfferwallJavaScriptInterface() {
        }

        @JavascriptInterface
        public void CallOnTargetGame(String str, String str2) {
            Intent launchIntentForPackage = NXPOfferwallDialog.this.getActivity().getPackageManager().getLaunchIntentForPackage(str2);
            if (launchIntentForPackage == null) {
                NXPOfferwallDialog.this.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str2)));
            } else {
                NXPOfferwallDialog.this.startActivity(launchIntentForPackage);
            }
        }
    }

    public static NXPOfferwallDialog newInstance(Activity activity, String str, boolean z) {
        NXPOfferwallDialog nXPOfferwallDialog = new NXPOfferwallDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_OFFERWALL_INFO, str);
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putInt(KEY_SCREEN_ORIENTATION, activity.getRequestedOrientation());
        bundle.putBoolean(KEY_LIMIT_AD_TRACKING_ENABLED, z);
        nXPOfferwallDialog.setArguments(bundle);
        return nXPOfferwallDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTitle(String str) {
        if (this.title != null) {
            this.title.setText(str);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.currentWebView = new WebView(getActivity());
        this.currentWebView.setBackgroundResource(R.color.nxp_color_common_webview_background);
        this.currentWebView.getSettings().setTextZoom(100);
        return super.createView();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    List<Integer> getManagedViewIdList() {
        return Arrays.asList(Integer.valueOf(R.id.btnClose), Integer.valueOf(R.id.btnBack), Integer.valueOf(R.id.npcommon_progress_bar), Integer.valueOf(R.id.webViewContainer), Integer.valueOf(R.id.title));
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    protected String getURL() {
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        NXToySession session = NXToySessionManager.getInstance().getSession();
        StringBuilder sb = new StringBuilder();
        sb.append(getPageServerUrl());
        sb.append("/auth?");
        sb.append("client_id=");
        sb.append(nXToyCommonPreferenceController.getServiceClientId());
        sb.append("&npsn=");
        sb.append(session.getNpsn());
        sb.append("&np_token=");
        sb.append(session.getNptoken());
        sb.append("&redirect_uri=");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("/offerwall?offerwall_id=");
        sb2.append(this.offerwallId);
        sb2.append("&adid=");
        sb2.append(getArguments().getBoolean(KEY_LIMIT_AD_TRACKING_ENABLED) ? "0" : nXToyCommonPreferenceController.getAdvertisingId());
        sb2.append("&language=");
        sb2.append(nXToyCommonPreferenceController.getLocale());
        sb2.append("&country=");
        sb2.append(nXToyCommonPreferenceController.getCountry());
        sb2.append("&uuid=");
        sb2.append(nXToyCommonPreferenceController.getUUID());
        sb2.append("&uuid2=");
        sb2.append(nXToyCommonPreferenceController.getUUID2());
        sb.append(Uri.encode(sb2.toString()));
        setExtraHeader(getToyHeader());
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public NPWebDialogBase.NPWebViewClient getWebViewClient() {
        return new NPWebDialogBase.NPWebViewClient() { // from class: kr.co.nexon.toy.android.ui.board.NXPOfferwallDialog.3
            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                NXPOfferwallDialog.this.hideProgressbar();
                if (str.contains("redirect_uri")) {
                    return;
                }
                NXPOfferwallDialog.this.backButton.setVisibility(0);
                if (str.contains(NXPOfferwallDialog.this.helpPageUrl)) {
                    ((ImageButton) NXPOfferwallDialog.this.backButton).setImageResource(R.drawable.common_titlebar_back_btn_selector);
                    NXPOfferwallDialog.this.printedPageInfo = 1;
                } else {
                    ((ImageButton) NXPOfferwallDialog.this.backButton).setImageResource(R.drawable.common_webview_question_btn_selector);
                    NXPOfferwallDialog.this.printedPageInfo = 0;
                }
            }

            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (str.contains(NXPOfferwallDialog.this.helpPageUrl)) {
                    NXPOfferwallDialog.this.setTitle(NXToyLocaleManager.getInstance().getString(R.string.npres_offerwall_help_view_title));
                } else {
                    NXPOfferwallDialog.this.setTitle(NXPOfferwallDialog.this.offerwallTitle);
                }
                super.onPageStarted(webView, str, bitmap);
            }
        };
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    void initContent(Dialog dialog) {
        ToyLog.d("NXPOfferwallDialog initContent, Dialog dialog : " + dialog);
        dialog.setContentView(R.layout.nxp_offerwall_web);
        this.webViewContainer = (FrameLayout) dialog.findViewById(R.id.webViewContainer);
        this.webViewContainer.addView(this.currentWebView, new FrameLayout.LayoutParams(-1, -1));
        this.title = (TextView) dialog.findViewById(R.id.title);
        this.closeButton = (ImageButton) dialog.findViewById(R.id.closeBtn);
        this.backButton = (ImageButton) dialog.findViewById(R.id.backBtn);
        this.progressBar = (ProgressBar) dialog.findViewById(R.id.npcommon_progress_bar);
        NXPToyOfferwall nXPToyOfferwall = (NXPToyOfferwall) NXJsonUtil.fromObject(getArguments().getString(KEY_OFFERWALL_INFO), NXPToyOfferwall.class);
        this.offerwallId = nXPToyOfferwall.id;
        this.offerwallTitle = nXPToyOfferwall.title;
        ((ImageButton) this.backButton).setImageResource(R.drawable.common_webview_question_btn_selector);
        this.helpPageUrl = getPageServerUrl() + "/offerwall/help";
        this.currentWebView.addJavascriptInterface(new NXPOfferwallJavaScriptInterface(), "Offerwall");
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase, kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        hideProgressbar();
        if (this.printedPageInfo == 0) {
            onClose();
        } else if (this.printedPageInfo == 1) {
            loadURL(getURL());
        }
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase, android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.printedPageInfo == 0) {
            loadURL(getURL());
        } else if (this.printedPageInfo == 1) {
            loadURL(this.helpPageUrl);
        }
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        getActivity().setRequestedOrientation(getArguments().getInt(KEY_SCREEN_ORIENTATION));
        if (this.closeListener != null) {
            NXToyCloseResult nXToyCloseResult = new NXToyCloseResult();
            nXToyCloseResult.screenName = "offerwall";
            nXToyCloseResult.requestTag = NXToyRequestTag.ShowOfferwall.getValue();
            this.closeListener.onClose(nXToyCloseResult);
        }
        super.onDismiss(dialogInterface);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        ToyLog.d("NXPOfferwallDialog, onSaveInstanceState");
        bundle.putInt(KEY_SCREEN_ORIENTATION, getArguments().getInt(KEY_SCREEN_ORIENTATION));
        super.onSaveInstanceState(bundle);
    }

    public void setCloseListener(NPCloseListener nPCloseListener) {
        this.closeListener = nPCloseListener;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    protected void setHederButtonClickLisenter() {
        if (this.backButton != null) {
            this.backButton.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.board.NXPOfferwallDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (NXPOfferwallDialog.this.printedPageInfo == 0) {
                        NXPOfferwallDialog.this.loadURL(NXPOfferwallDialog.this.helpPageUrl);
                    } else if (NXPOfferwallDialog.this.printedPageInfo == 1) {
                        NXPOfferwallDialog.this.onBackPressed();
                    }
                }
            });
        }
        if (this.closeButton != null) {
            this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.board.NXPOfferwallDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NXPOfferwallDialog.this.onClose();
                }
            });
        }
    }
}
