package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.appevents.UserDataStore;
import com.nexon.core.NXToyVersion;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import com.tapjoy.TapjoyConstants;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionListener;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.toy.listener.NPListener;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPCSDialog extends NPWebDialogFullScreen {
    private static final String KEY_META_DATA = "metaData";
    public static final String TAG = "NPCSDialog";
    private NPAccount account;
    private String country;
    private String locale;
    private NXToySession session;

    /* JADX INFO: Access modifiers changed from: private */
    public String getEmailAddress() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.account.getLoginType() == NPAccount.LoginTypeEmail) {
                jSONObject.put("emailAddr", this.session.getAccountId());
            } else {
                jSONObject.put("emailAddr", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLanguage() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("language", this.locale);
            jSONObject.put(UserDataStore.COUNTRY, this.country);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getMetaInfo() {
        JSONObject jSONObject = new JSONObject();
        String string = getArguments().getString(KEY_META_DATA, "");
        try {
            jSONObject.put("language", this.locale);
            jSONObject.put(UserDataStore.COUNTRY, this.country);
            jSONObject.put("os", "Android " + Build.VERSION.RELEASE);
            jSONObject.put(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, Build.MODEL);
            jSONObject.put("sdk", NXToyVersion.VERSION);
            if (NXStringUtil.isNotNull(string)) {
                jSONObject.put("gameMetaInfo", new JSONObject(string));
            } else {
                jSONObject.put("gameMetaInfo", new JSONObject());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static NPCSDialog newInstance(Activity activity, String str) {
        NPCSDialog nPCSDialog = new NPCSDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString(KEY_META_DATA, str);
        nPCSDialog.setArguments(bundle);
        return nPCSDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestExteralStoragePermission() {
        final Activity activity = getActivity();
        if (activity == null) {
            ToyLog.d("activity is null!!");
            return;
        }
        final NXRuntimePermissionManager nXRuntimePermissionManager = NXRuntimePermissionManager.getInstance();
        final String[] strArr = {NXRuntimePermissionManager.READ_EXTERNAL_STORAGE};
        if (!nXRuntimePermissionManager.isPermissionFromManifest(activity, strArr[0])) {
            this.hasFilePermission = false;
        } else if (nXRuntimePermissionManager.checkGrantedPermission(activity, strArr[0])) {
            this.hasFilePermission = true;
        } else {
            NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(activity);
            NXPPolicyManager.getInstance().showPermissionConfirmDialog(activity, nXToyLocaleManager.getString(R.string.npres_runtime_permission_title), nXToyLocaleManager.getString(R.string.npres_storage_permission_alert_message_for_cs), "", new NPListener() { // from class: kr.co.nexon.toy.android.ui.board.NPCSDialog.2
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    nXRuntimePermissionManager.requestPermissionsWithoutExplanation(activity, strArr, 100002, new NXRuntimePermissionListener() { // from class: kr.co.nexon.toy.android.ui.board.NPCSDialog.2.1
                        @Override // kr.co.nexon.mdev.android.permission.NXRuntimePermissionListener
                        public void onResult(int i, String[] strArr2, int[] iArr) {
                            if (!NXRuntimePermissionManager.isGrantsAllPermission(iArr)) {
                                NPCSDialog.this.hasFilePermission = false;
                            } else {
                                ToyLog.d("Has External Storage");
                                NPCSDialog.this.hasFilePermission = true;
                            }
                        }
                    });
                }
            });
        }
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen, kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public String getURL() {
        return getPageServerUrl() + "/auth?client_id=" + NXToyCommonPreferenceController.getInstance().getServiceClientId() + "&npsn=" + this.session.getNpsn() + "&np_token=" + this.session.getNptoken() + "&redirect_uri=/cc";
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public WebViewClient getWebViewClient() {
        return new NPWebDialogBase.NPWebViewClient() { // from class: kr.co.nexon.toy.android.ui.board.NPCSDialog.1
            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient
            public void checkCanGoBack(WebView webView, String str) {
                ToyLog.d("url:" + str);
                if (str.equals(NPCSDialog.this.getPageServerUrl() + "/cc")) {
                    webView.clearHistory();
                    if (NPCSDialog.this.backButton != null) {
                        NPCSDialog.this.backButton.setVisibility(4);
                        return;
                    }
                    return;
                }
                if (NPCSDialog.this.backButton != null) {
                    if (NPCSDialog.this.webViewList.size() > 1) {
                        NPCSDialog.this.backButton.setVisibility(0);
                        return;
                    }
                    ToyLog.d("view.canGoBack:" + webView.canGoBack());
                    if (webView.canGoBack()) {
                        NPCSDialog.this.backButton.setVisibility(0);
                    } else {
                        NPCSDialog.this.backButton.setVisibility(4);
                    }
                }
            }

            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.loadUrl("javascript:npaInfo = { getInfoData: function() { return " + NPCSDialog.this.getMetaInfo() + "; },getAcceptLanguage: function() { return " + NPCSDialog.this.getLanguage() + "; },getEmailAddr: function() {return " + NPCSDialog.this.getEmailAddress() + "; } }");
                if (Build.VERSION.RELEASE.startsWith("4.")) {
                    webView.loadUrl("javascript:toyClientJS = { popImage: function(popupId) { return window.Android.open(popupId); } }");
                }
                ToyLog.d("url : " + str);
                if (str.contains("cc/report/write?")) {
                    NPCSDialog.this.requestExteralStoragePermission();
                }
            }
        };
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen, kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void initContent(Dialog dialog) {
        this.account = NPAccount.getInstance(getActivity());
        this.session = NXToySessionManager.getInstance().getSession();
        this.locale = this.account.getLocale().getLocaleCode();
        this.country = this.account.getCountry().getCountryCode();
        super.initContent(dialog);
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void loadURL(String str) {
        this.currentWebView.loadUrl(str, getToyHeader());
    }
}
