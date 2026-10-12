package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nexon.npaccount.R;
import java.util.Arrays;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPWebDialogTitleBar extends NPWebDialogBase {
    public static final String TAG = "NPWebDialogTitleBar";

    public static NPWebDialogTitleBar newInstance(Activity activity, String str, String str2) {
        NPWebDialogTitleBar nPWebDialogTitleBar = new NPWebDialogTitleBar();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("title", str);
        bundle.putString("url", str2);
        nPWebDialogTitleBar.setArguments(bundle);
        return nPWebDialogTitleBar;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.currentWebView = new WebView(getActivity());
        this.currentWebView.setBackgroundResource(R.color.nxp_color_common_webview_background);
        return super.createView();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    List<Integer> getManagedViewIdList() {
        return Arrays.asList(Integer.valueOf(R.id.backBtn), Integer.valueOf(R.id.title), Integer.valueOf(R.id.webViewContainer), Integer.valueOf(R.id.closeBtn), Integer.valueOf(R.id.npcommon_progress_bar));
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    protected String getURL() {
        return getArguments().getString("url", "");
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void initContent(Dialog dialog) {
        dialog.setContentView(R.layout.nxp_web);
        this.backButton = dialog.findViewById(R.id.backBtn);
        this.backButton.setVisibility(4);
        ((TextView) dialog.findViewById(R.id.title)).setText(getArguments().getString("title", ""));
        this.webViewContainer = (FrameLayout) dialog.findViewById(R.id.webViewContainer);
        this.webViewContainer.addView(this.currentWebView, new FrameLayout.LayoutParams(-1, -1));
        this.closeButton = dialog.findViewById(R.id.closeBtn);
        this.progressBar = (ProgressBar) dialog.findViewById(R.id.npcommon_progress_bar);
    }
}
