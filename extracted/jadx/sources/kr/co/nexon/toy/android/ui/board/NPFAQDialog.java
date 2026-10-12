package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.npaccount.R;
import java.util.Arrays;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPFAQDialog extends NPWebDialogBase {
    public static final String TAG = "NPFAQDialog";

    public static NPFAQDialog newInstance(Activity activity) {
        NPFAQDialog nPFAQDialog = new NPFAQDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        nPFAQDialog.setArguments(bundle);
        return nPFAQDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.currentWebView = new WebView(getActivity());
        this.currentWebView.setBackgroundResource(R.color.nxp_color_common_webview_background);
        return super.createView();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public List<Integer> getManagedViewIdList() {
        return Arrays.asList(Integer.valueOf(R.id.btnClose), Integer.valueOf(R.id.btnBack), Integer.valueOf(R.id.npcommon_progress_bar), Integer.valueOf(R.id.webViewContainer));
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    protected String getURL() {
        return getPageServerUrl() + "/faq?client_id=" + NXToyCommonPreferenceController.getInstance().getServiceClientId();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void initContent(Dialog dialog) {
        dialog.setContentView(R.layout.nxp_common_web);
        this.webViewContainer = (FrameLayout) dialog.findViewById(R.id.webViewContainer);
        this.webViewContainer.addView(this.currentWebView, new FrameLayout.LayoutParams(-1, -1));
        this.closeButton = dialog.findViewById(R.id.btnClose);
        this.backButton = dialog.findViewById(R.id.btnBack);
        this.progressBar = (ProgressBar) dialog.findViewById(R.id.npcommon_progress_bar);
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void loadURL(String str) {
        this.currentWebView.loadUrl(str, getToyHeader());
    }
}
