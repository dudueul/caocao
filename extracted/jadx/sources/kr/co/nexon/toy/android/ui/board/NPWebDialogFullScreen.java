package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.nexon.npaccount.R;
import java.util.Arrays;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPWebDialogFullScreen extends NPWebDialogBase {
    public static final String TAG = "NPWebDialogFullScreen";

    public static NPWebDialogFullScreen newInstance(Activity activity, String str) {
        NPWebDialogFullScreen nPWebDialogFullScreen = new NPWebDialogFullScreen();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("url", str);
        nPWebDialogFullScreen.setArguments(bundle);
        return nPWebDialogFullScreen;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.currentWebView = new WebView(getActivity());
        this.currentWebView.setBackgroundResource(R.color.nxp_color_common_webview_background);
        return super.createView();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    protected List<Integer> getManagedViewIdList() {
        return Arrays.asList(Integer.valueOf(R.id.btnClose), Integer.valueOf(R.id.btnBack), Integer.valueOf(R.id.npcommon_progress_bar), Integer.valueOf(R.id.webViewContainer));
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    protected String getURL() {
        return getArguments().getString("url", "");
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void initContent(Dialog dialog) {
        dialog.setContentView(R.layout.nxp_common_web);
        this.webViewContainer = (FrameLayout) dialog.findViewById(R.id.webViewContainer);
        this.webViewContainer.addView(this.currentWebView, new FrameLayout.LayoutParams(-1, -1));
        this.backButton = (Button) dialog.findViewById(R.id.btnBack);
        this.closeButton = (Button) dialog.findViewById(R.id.btnClose);
        this.progressBar = (ProgressBar) dialog.findViewById(R.id.npcommon_progress_bar);
    }
}
