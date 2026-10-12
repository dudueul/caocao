package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.nexon.core.NXToyVersion;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.tapjoy.TapjoyConstants;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPForumDialog extends NPWebDialogFullScreen {
    private static final String KEY_FORUM_ID = "forumId";
    public static final String TAG = "NPForumDialog";
    private int forumId;
    private String forumURLPrefix;

    /* JADX INFO: Access modifiers changed from: private */
    public String getMetaInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("os", "Android " + Build.VERSION.RELEASE);
            jSONObject.put(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, Build.MODEL);
            jSONObject.put("sdk", NXToyVersion.VERSION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static NPForumDialog newInstance(Activity activity, int i) {
        NPForumDialog nPForumDialog = new NPForumDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putInt("forumId", i);
        nPForumDialog.setArguments(bundle);
        return nPForumDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen, kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    protected String getURL() {
        NXToySession session = NXToySessionManager.getInstance().getSession();
        StringBuilder sb = new StringBuilder();
        sb.append(getPageServerUrl());
        sb.append("/auth?");
        sb.append("client_id=");
        sb.append(NXToyCommonPreferenceController.getInstance().getServiceClientId());
        sb.append("&npsn=");
        sb.append(session.getNpsn());
        sb.append("&np_token=");
        sb.append(session.getNptoken());
        sb.append("&redirect_uri=/forum");
        if (this.forumId != -1) {
            sb.append("/");
            sb.append(this.forumId);
        }
        return sb.toString();
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public WebViewClient getWebViewClient() {
        return new NPWebDialogBase.NPWebViewClient() { // from class: kr.co.nexon.toy.android.ui.board.NPForumDialog.1
            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient
            public void checkCanGoBack(WebView webView, String str) {
                if (str.equals(NPForumDialog.this.forumURLPrefix)) {
                    webView.clearHistory();
                    if (NPForumDialog.this.backButton != null) {
                        NPForumDialog.this.backButton.setVisibility(4);
                        return;
                    }
                    return;
                }
                if (!webView.canGoBack() || NPForumDialog.this.backButton == null) {
                    return;
                }
                NPForumDialog.this.backButton.setVisibility(0);
            }

            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.loadUrl("javascript:npaInfo = { getInfoData: function() { return " + NPForumDialog.this.getMetaInfo() + "; } }");
            }
        };
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen, kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void initContent(Dialog dialog) {
        this.forumId = getArguments().getInt("forumId", -1);
        super.initContent(dialog);
        if (this.forumId != -1) {
            this.forumURLPrefix = getPageServerUrl() + "/forum/" + this.forumId;
        } else {
            this.forumURLPrefix = getPageServerUrl() + "/forum";
        }
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void loadURL(String str) {
        this.currentWebView.loadUrl(str, getToyHeader());
    }
}
