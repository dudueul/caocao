package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.util.NXDateUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.auth.result.NXToyCloseResult;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPShowTodayDialog extends NPWebDialogFullScreen {
    public static final String KEY_DATE_FORMAT = "yyyyMMdd";
    private static final String KEY_GROUP_CODE = "groupCode";
    private static final String KEY_USE_DONTSHOWTODAY = "useDontShowToday";
    public static final String TAG = "NPShowTodayDialog";
    private static final String urlPath = "/today?client_id=%s&gruopCode=%d";
    private CheckBox dontShowTodayCheckBox;
    private LinearLayout dontShowTodayLayout;
    private NPListener resultListener;

    /* JADX INFO: Access modifiers changed from: private */
    public void displayDontShowToday() {
        if (this.dontShowTodayLayout == null) {
            return;
        }
        if (this.webViewList.size() != 1 || this.currentWebView.canGoBack()) {
            this.dontShowTodayLayout.setVisibility(4);
        } else {
            this.dontShowTodayLayout.setVisibility(0);
        }
    }

    public static NPShowTodayDialog newInstance(Activity activity, int i, boolean z) {
        NPShowTodayDialog nPShowTodayDialog = new NPShowTodayDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putInt(KEY_GROUP_CODE, i);
        bundle.putBoolean(KEY_USE_DONTSHOWTODAY, z);
        nPShowTodayDialog.setArguments(bundle);
        return nPShowTodayDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen, kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public String getURL() {
        int i = getArguments().getInt(KEY_GROUP_CODE, 0);
        String serviceClientId = NXToyCommonPreferenceController.getInstance().getServiceClientId() == null ? "" : NXToyCommonPreferenceController.getInstance().getServiceClientId();
        return NXStringUtil.isNotNull(serviceClientId) ? String.format(getPageServerUrl() + urlPath, serviceClientId, Integer.valueOf(i)) : "";
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public WebViewClient getWebViewClient() {
        return new NPWebDialogBase.NPWebViewClient() { // from class: kr.co.nexon.toy.android.ui.board.NPShowTodayDialog.2
            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                NPShowTodayDialog.this.displayDontShowToday();
            }

            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                if (NPShowTodayDialog.this.dontShowTodayLayout != null) {
                    NPShowTodayDialog.this.dontShowTodayLayout.setVisibility(4);
                }
            }
        };
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen, kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void initContent(Dialog dialog) {
        dialog.setContentView(R.layout.nxp_showtoday_web);
        this.webViewContainer = (FrameLayout) dialog.findViewById(R.id.webViewContainer);
        this.webViewContainer.addView(this.currentWebView, new FrameLayout.LayoutParams(-1, -1));
        this.backButton = (Button) dialog.findViewById(R.id.btnBack);
        this.closeButton = (Button) dialog.findViewById(R.id.btnClose);
        this.progressBar = (ProgressBar) dialog.findViewById(R.id.npcommon_progress_bar);
        boolean z = getArguments().getBoolean(KEY_USE_DONTSHOWTODAY);
        if (!z) {
            ToyLog.d("\"Don't Show Today\" is not Displayed. useDontShowToday : " + z);
            return;
        }
        this.dontShowTodayCheckBox = (CheckBox) dialog.findViewById(R.id.dontShowTodayCheckBox);
        ((TextView) dialog.findViewById(R.id.dontShowTodayText)).setText(this.localeManager.getString(R.string.dont_show_today));
        this.dontShowTodayLayout = (LinearLayout) dialog.findViewById(R.id.dontShowTodayLayout);
        this.dontShowTodayLayout.setVisibility(0);
        this.dontShowTodayLayout.setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.board.NPShowTodayDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int i = NPShowTodayDialog.this.getArguments().getInt(NPShowTodayDialog.KEY_GROUP_CODE, 0);
                NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
                boolean z2 = NPShowTodayDialog.this.dontShowTodayCheckBox.isChecked() ? false : true;
                NPShowTodayDialog.this.dontShowTodayCheckBox.setChecked(z2);
                if (z2) {
                    nXToyCommonPreferenceController.setNotShowDateForShowToday(i, NXDateUtil.getCurrentTimeFormat(NPShowTodayDialog.KEY_DATE_FORMAT));
                } else {
                    nXToyCommonPreferenceController.setNotShowDateForShowToday(i, "");
                }
                NPShowTodayDialog.this.onClose();
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void loadURL(String str) {
        this.currentWebView.loadUrl(str, getToyHeader());
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase, kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        hideProgressbar();
        if (this.currentWebView.canGoBack()) {
            this.currentWebView.goBack();
            displayDontShowToday();
        } else if (this.webViewList.size() > 1) {
            closeWindow();
            displayDontShowToday();
        } else {
            this.webViewList.clear();
            onClose();
        }
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void onClose() {
        if (this.resultListener != null) {
            NXToyCloseResult nXToyCloseResult = new NXToyCloseResult();
            nXToyCloseResult.requestTag = NXToyRequestTag.ShowToday.getValue();
            this.resultListener.onResult(nXToyCloseResult);
        }
        super.onClose();
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }
}
