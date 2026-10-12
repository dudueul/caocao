package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.npaccount.R;
import java.util.Arrays;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.auth.result.NXToyCloseResult;
import kr.co.nexon.npaccount.listener.NPCloseListener;
import kr.co.nexon.toy.android.ui.baseplate.NPPlateCodes;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPNoticeDialog extends NPWebDialogBase {
    public static final String TAG = "NPNoticeDialog";
    private NPCloseListener closeListener;
    private TextView titleTextView;

    public static NPNoticeDialog newInstance(Activity activity) {
        NPNoticeDialog nPNoticeDialog = new NPNoticeDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        nPNoticeDialog.setArguments(bundle);
        return nPNoticeDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.currentWebView = new WebView(getActivity());
        this.currentWebView.setBackgroundResource(R.color.nxp_color_common_webview_background);
        return super.createView();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    List<Integer> getManagedViewIdList() {
        return Arrays.asList(Integer.valueOf(R.id.btnClose), Integer.valueOf(R.id.btnBack), Integer.valueOf(R.id.npcommon_progress_bar), Integer.valueOf(R.id.webViewContainer));
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public String getURL() {
        return getPageServerUrl() + "/notice?client_id=" + NXToyCommonPreferenceController.getInstance().getServiceClientId();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void initContent(Dialog dialog) {
        dialog.setContentView(R.layout.nxp_common_web);
        this.webViewContainer = (FrameLayout) dialog.findViewById(R.id.webViewContainer);
        this.webViewContainer.addView(this.currentWebView, new FrameLayout.LayoutParams(-1, -1));
        this.closeButton = (Button) dialog.findViewById(R.id.btnClose);
        this.backButton = (Button) dialog.findViewById(R.id.btnBack);
        this.progressBar = (ProgressBar) dialog.findViewById(R.id.npcommon_progress_bar);
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
            return;
        }
        if (this.webViewList.size() > 1) {
            closeWindow();
            return;
        }
        if (this.closeListener != null) {
            NXToyCloseResult nXToyCloseResult = new NXToyCloseResult();
            nXToyCloseResult.screenName = NPPlateCodes.CODE_NOTICE;
            nXToyCloseResult.requestTag = NXToyRequestTag.ShowNotice.getValue();
            this.closeListener.onClose(nXToyCloseResult);
        }
        super.onBackPressed();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void onClose() {
        if (this.closeListener != null) {
            NXToyCloseResult nXToyCloseResult = new NXToyCloseResult();
            nXToyCloseResult.screenName = NPPlateCodes.CODE_NOTICE;
            nXToyCloseResult.requestTag = NXToyRequestTag.ShowNotice.getValue();
            this.closeListener.onClose(nXToyCloseResult);
        }
        super.onClose();
    }

    public void setCloseListener(NPCloseListener nPCloseListener) {
        this.closeListener = nPCloseListener;
    }
}
