package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.nexon.core.android.NXPApplicationConfigInterface;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.nexon.mdev.android.activity.NPActivityResultManager;
import kr.co.nexon.mdev.android.util.NXFileUtil;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.mdev.android.web.NXWebChromeClient;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.util.NXPUIBackupUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public abstract class NPWebDialogBase extends NPDialogBase {
    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";
    public static final String TAG = "NPWebDialogBase";
    protected View backButton;
    protected View closeButton;
    protected WebView currentWebView;
    private Map<String, String> extraHeader;
    protected boolean hasFilePermission = true;
    protected NXToyLocaleManager localeManager;
    private String postData;
    protected ProgressBar progressBar;
    protected String tagId;
    private String url;
    protected NXWebChromeClient webChromeClient;
    protected WebViewClient webViewClient;
    protected FrameLayout webViewContainer;
    private boolean webViewErrorFlag;
    protected ArrayList<WebView> webViewList;

    public class NPChromeClient extends NXWebChromeClient {
        public NPChromeClient(Activity activity) {
            super(activity);
        }

        @Override // android.webkit.WebChromeClient
        public void onCloseWindow(WebView webView) {
            ToyLog.d("============onCloseWindow================");
            NPWebDialogBase.this.closeWindow();
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            try {
                NPWebDialogBase.this.webViewContainer.removeAllViews();
                NPWebDialogBase.this.webViewContainer.setVisibility(0);
                WebView webView2 = new WebView(NPWebDialogBase.this.getActivity());
                NPWebDialogBase.this.initWebView(webView2);
                webView2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                NPWebDialogBase.this.webViewContainer.addView(webView2);
                ((WebView.WebViewTransport) message.obj).setWebView(webView2);
                message.sendToTarget();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            if (!Build.VERSION.RELEASE.startsWith("4.")) {
                return super.onJsAlert(webView, str, str2, jsResult);
            }
            new NXPAlertDialog.Builder(NPWebDialogBase.this.getActivity()).setMessage(str2).setPositiveButton(NPWebDialogBase.this.localeManager.getString(R.string.yes), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPChromeClient.2
                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                protected void onSwallowClick(View view) {
                    jsResult.confirm();
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPChromeClient.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    jsResult.confirm();
                }
            }).create().show();
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            if (!Build.VERSION.RELEASE.startsWith("4.")) {
                return super.onJsConfirm(webView, str, str2, jsResult);
            }
            new NXPAlertDialog.Builder(NPWebDialogBase.this.getActivity()).setMessage(str2).setPositiveButton(NPWebDialogBase.this.localeManager.getString(R.string.yes), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPChromeClient.5
                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                protected void onSwallowClick(View view) {
                    jsResult.confirm();
                }
            }).setNegativeButton(NPWebDialogBase.this.localeManager.getString(R.string.no), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPChromeClient.4
                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                protected void onSwallowClick(View view) {
                    jsResult.cancel();
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPChromeClient.3
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    jsResult.cancel();
                }
            }).create().show();
            return true;
        }

        @Override // kr.co.nexon.mdev.android.web.NXWebChromeClient, android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (NPWebDialogBase.this.hasFilePermission) {
                NPWebDialogBase.this.registerFilInputResult(1);
                return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
            }
            NPWebDialogBase.this.showPermissionDenyMsgToast();
            return false;
        }

        @Override // kr.co.nexon.mdev.android.web.NXWebChromeClient
        protected void showFileSelector() {
            if (!NPWebDialogBase.this.hasFilePermission) {
                NPWebDialogBase.this.showPermissionDenyMsgToast();
            } else {
                NPWebDialogBase.this.registerFilInputResult(1);
                super.showFileSelector();
            }
        }
    }

    public class NPWebViewClient extends WebViewClient {
        public NPWebViewClient() {
        }

        public void checkCanGoBack(WebView webView, String str) {
            if (NPWebDialogBase.this.backButton == null) {
                return;
            }
            if (NPWebDialogBase.this.webViewList.size() > 1) {
                NPWebDialogBase.this.backButton.setVisibility(0);
            } else if (webView.canGoBack()) {
                NPWebDialogBase.this.backButton.setVisibility(0);
            } else {
                NPWebDialogBase.this.backButton.setVisibility(4);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            checkCanGoBack(webView, str);
            NPWebDialogBase.this.hideProgressbar();
            if (NPWebDialogBase.this.webViewErrorFlag) {
                return;
            }
            webView.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (NPWebDialogBase.this.backButton != null) {
                NPWebDialogBase.this.backButton.setVisibility(4);
            }
            if (NPWebDialogBase.this.getActivity() == null || NPWebDialogBase.this.getActivity().isFinishing()) {
                return;
            }
            NPWebDialogBase.this.showProgressbar();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            Log.e("WebViewERROR", "errorCode " + i + " description " + str + " url " + str2);
            NPWebDialogBase.this.webViewErrorFlag = true;
            String webViewClientErrorText = NPWebDialogBase.this.getWebViewClientErrorText(i);
            if (NXStringUtil.isNotNull(webViewClientErrorText)) {
                Toast.makeText(NPWebDialogBase.this.getActivity(), webViewClientErrorText, 0).show();
            } else {
                new NXPAlertDialog.Builder(NPWebDialogBase.this.getActivity()).setMessage(str).setPositiveButton(NPWebDialogBase.this.localeManager.getString(R.string.confirm), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient.1
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        NPWebDialogBase.this.dismiss();
                    }
                }).create().show();
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.startsWith("http")) {
                return false;
            }
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.setFlags(603979776);
                NPWebDialogBase.this.startActivity(intent);
            } catch (Exception e) {
                ToyLog.d("" + e.getMessage());
            }
            return true;
        }
    }

    /* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
    public class NPWebViewJavaScriptInterface {
        public NPWebViewJavaScriptInterface() {
        }

        @JavascriptInterface
        public void open(String str) {
            NPWebDialogBase.this.callGallery(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callGallery(String str) {
        if (!this.hasFilePermission) {
            showPermissionDenyMsgToast();
            return;
        }
        this.tagId = str;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setType("image/*");
        registerFilInputResult(10002);
        getActivity().startActivityForResult(Intent.createChooser(intent, "Select Image"), 10002);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getWebViewClientErrorText(int i) {
        switch (i) {
            case -6:
            case -2:
            case -1:
                return this.localeManager.getString(R.string.npres_check_network);
            case -5:
            case -4:
            case -3:
            default:
                return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initWebView(WebView webView) {
        this.webChromeClient = new NPChromeClient(getActivity());
        webView.getSettings().setCacheMode(2);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode(0);
        }
        this.webViewClient = getWebViewClient();
        webView.setWebViewClient(this.webViewClient);
        webView.addJavascriptInterface(new NPWebViewJavaScriptInterface(), "Android");
        webView.setWebChromeClient(this.webChromeClient);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        this.webViewList.add(webView);
        this.currentWebView = webView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerFilInputResult(int i) {
        NPActivityResultManager.getInstance().registerActivityResultCallback(i, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.4
            @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
            public void onActivityResult(int i2, int i3, Intent intent) {
                String dataString;
                if (Build.VERSION.SDK_INT >= 21) {
                    if (i2 != 1 || NPWebDialogBase.this.webChromeClient.getFilePathCallback() == null) {
                        return;
                    }
                    Uri[] uriArr = null;
                    if (i3 == -1 && (dataString = intent.getDataString()) != null) {
                        uriArr = new Uri[]{Uri.parse(dataString)};
                    }
                    if (NPWebDialogBase.this.webChromeClient.getFilePathCallback() != null) {
                        NPWebDialogBase.this.webChromeClient.getFilePathCallback().onReceiveValue(uriArr);
                        NPWebDialogBase.this.webChromeClient.setFilePathCallback(null);
                        return;
                    }
                    return;
                }
                if (i3 == -1) {
                    if (i2 == 1) {
                        if (NPWebDialogBase.this.webChromeClient.getUploadMessage() != null) {
                            NPWebDialogBase.this.webChromeClient.getUploadMessage().onReceiveValue((intent == null || i3 != -1) ? null : intent.getData());
                            NPWebDialogBase.this.webChromeClient.setUploadMessage(null);
                            return;
                        }
                        return;
                    }
                    if (i2 == 10002) {
                        Uri data = (intent == null || i3 != -1) ? null : intent.getData();
                        if (data != null) {
                            NPWebDialogBase.this.uploadImage(data);
                            return;
                        }
                        return;
                    }
                    if (NPWebDialogBase.this.webChromeClient.getUploadMessage() != null) {
                        NPWebDialogBase.this.webChromeClient.getUploadMessage().onReceiveValue(null);
                        NPWebDialogBase.this.webChromeClient.setUploadMessage(null);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPermissionDenyMsgToast() {
        Toast.makeText(getActivity(), String.format("[%s] %s", this.localeManager.getString(R.string.npres_runtime_permission_group_storage), this.localeManager.getString(R.string.npres_runtime_permission_message_after)), 0).show();
    }

    protected void closeWindow() {
        try {
            this.webViewList.remove(this.webViewList.size() - 1);
            this.currentWebView.stopLoading();
            this.currentWebView.loadUrl("about:blank");
            this.currentWebView.clearCache(true);
            this.currentWebView.destroyDrawingCache();
            this.currentWebView.removeAllViews();
            this.webViewContainer.removeView(this.currentWebView);
            this.currentWebView.destroy();
            this.currentWebView = this.webViewList.get(this.webViewList.size() - 1);
            this.webViewContainer.addView(this.currentWebView);
            ((NPWebViewClient) this.webViewClient).checkCanGoBack(this.currentWebView, "");
            hideProgressbar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        Dialog dialog = getDialog();
        this.webViewList = new ArrayList<>();
        this.localeManager = NXToyLocaleManager.getInstance();
        initContent(dialog);
        initWebView(this.currentWebView);
        this.url = getURL();
        loadURL(this.url);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
        }
        setHederButtonClickLisenter();
        return getView();
    }

    abstract List<Integer> getManagedViewIdList();

    protected String getPageServerUrl() {
        return NXPApplicationConfigManager.getInstance().getServerURL(NXPApplicationConfigInterface.ServerType.Page).getUrl();
    }

    protected Map<String, String> getToyHeader() {
        HashMap map = new HashMap();
        map.put("x-toy-locale", NXToyLocaleManager.getInstance().getLocale().getLocaleCode().replace(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, "-"));
        map.put("x-toy-client-id", NXToyCommonPreferenceController.getInstance().getServiceClientId());
        return map;
    }

    protected String getURL() {
        return this.url;
    }

    protected WebViewClient getWebViewClient() {
        return new NPWebViewClient();
    }

    protected void hideProgressbar() {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }
    }

    abstract void initContent(Dialog dialog);

    protected void loadURL(String str) {
        if (NXStringUtil.isNotNull(this.postData)) {
            this.currentWebView.postUrl(str, this.postData.getBytes(Charset.forName("UTF-8")));
        } else if (this.extraHeader != null) {
            this.currentWebView.loadUrl(str, this.extraHeader);
        } else {
            this.currentWebView.loadUrl(str);
        }
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        hideProgressbar();
        if (this.currentWebView.canGoBack()) {
            this.currentWebView.goBack();
        } else if (this.webViewList.size() > 1) {
            closeWindow();
        } else {
            this.webViewList.clear();
            super.onBackPressed();
        }
    }

    protected void onClose() {
        ToyLog.d(getClass().getSimpleName() + " is dismiss");
        dismiss();
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        List<Integer> managedViewIdList;
        super.onConfigurationChanged(configuration);
        Dialog dialog = getDialog();
        if (dialog == null || (managedViewIdList = getManagedViewIdList()) == null || managedViewIdList.size() == 0) {
            return;
        }
        if (this.webViewContainer != null) {
            this.webViewContainer.removeAllViews();
        }
        List<NXPUIBackupUtil.ViewData> listBackupViewDataFromDialog = NXPUIBackupUtil.backupViewDataFromDialog(dialog, managedViewIdList);
        initContent(dialog);
        NXPUIBackupUtil.restoreViewDataToDialog(dialog, listBackupViewDataFromDialog);
        setHederButtonClickLisenter();
    }

    @Override // android.app.Fragment
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
        super.onDestroy();
    }

    public void setExtraHeader(Map<String, String> map) {
        this.extraHeader = map;
    }

    protected void setHederButtonClickLisenter() {
        if (this.backButton != null) {
            this.backButton.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NPWebDialogBase.this.onBackPressed();
                }
            });
        }
        if (this.closeButton != null) {
            this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NPWebDialogBase.this.onClose();
                }
            });
        }
    }

    public void setPostData(String str) {
        this.postData = str;
    }

    protected void showProgressbar() {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(0);
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kr.co.nexon.toy.android.ui.board.NPWebDialogBase$3] */
    protected void uploadImage(Uri uri) {
        final File fileUriToFile = NXFileUtil.uriToFile(getActivity(), uri);
        new AsyncTask<Void, Void, String>() { // from class: kr.co.nexon.toy.android.ui.board.NPWebDialogBase.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(Void... voidArr) {
                return "javascript:$(\"#" + NPWebDialogBase.this.tagId + "\").html(\"<input id='image' name='image_base64' type='text' style='display:none !important;' value='" + NXFileUtil.fileToString(fileUriToFile) + "' /> <input id='image-name' name='image_name' type='text' style='display:none !important;' value='" + fileUriToFile.getName() + "' />\");addImage($('#image-name'));";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str) {
                super.onPostExecute(str);
                NPWebDialogBase.this.currentWebView.loadUrl(str);
            }

            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                super.onPreExecute();
            }
        }.execute(new Void[0]);
    }
}
