package kr.co.nexon.android.sns.nxcom.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.facebook.GraphResponse;
import com.google.gson.Gson;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.mdev.android.util.NXLocalizedString;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.auth.request.NXToyEncryptForJoinNXKWithGPlusRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetSvcInfoRequest;
import kr.co.nexon.npaccount.auth.result.NXToyNXKEncryptResult;
import kr.co.nexon.npaccount.auth.result.NXToyServiceInfoResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXComWebDialog extends NPDialogBase {
    public static final String BIRTHDAY = "birtyday";
    public static final String EMAIL = "email";
    public static final String KEY_APPCRYPT = "appcrypt";
    public static final String KEY_RESULT = "NXComResult";
    public static final String KEY_SESSION = "session";
    public static final String NICKNAME = "nickname";
    public static final String TAG = "NPNXComWebDialog";
    protected NPAuthListener resultListener;
    private int serviceATLLevel = 0;
    private NXToySession session;
    protected WebView webview;

    /* JADX INFO: renamed from: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog$3, reason: invalid class name */
    class AnonymousClass3 implements NXToyRequestListener {
        final /* synthetic */ String val$email;
        final /* synthetic */ String val$nickName;

        AnonymousClass3(String str, String str2) {
            this.val$email = str;
            this.val$nickName = str2;
        }

        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
        public void onComplete(NXToyResult nXToyResult) {
            NXToyServiceInfoResult.ResultSet resultSet = ((NXToyServiceInfoResult) nXToyResult).result;
            if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                NPNXComWebDialog.this.serviceATLLevel = resultSet.nxkATL;
            } else {
                NPNXComWebDialog.this.serviceATLLevel = 1;
            }
            if (NXStringUtil.isNull(this.val$email)) {
                NPNXComWebDialog.this.progressDialog.dismiss();
                NPNXComWebDialog.this.dismiss();
                Toast.makeText(NPNXComWebDialog.this.getActivity(), "Need email information", 0).show();
            } else if (this.val$nickName != null && !"".equals(this.val$nickName)) {
                NXToyRequestPostman.getInstance().postRequest(new NXToyEncryptForJoinNXKWithGPlusRequest(this.val$email, this.val$nickName), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.3.1
                    @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                    public void onComplete(final NXToyResult nXToyResult2) {
                        NPNXComWebDialog.this.getActivity().runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.3.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                NXToyNXKEncryptResult nXToyNXKEncryptResult = (NXToyNXKEncryptResult) nXToyResult2;
                                if (nXToyNXKEncryptResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                    NPNXComWebDialog.this.webview.loadUrl("https://member.nexon.com/mobile/join/mobilejoin.aspx?esscode=218&pagetype=2&needverify=" + (NPNXComWebDialog.this.serviceATLLevel == 2 ? "1" : "0") + "&appcrypt=" + nXToyNXKEncryptResult.result.encrypt);
                                    return;
                                }
                                NPNXComWebDialog.this.progressDialog.dismiss();
                                NPNXComWebDialog.this.dismiss();
                                Toast.makeText(NPNXComWebDialog.this.getActivity(), nXToyNXKEncryptResult.errorText, 0).show();
                            }
                        });
                    }
                });
            } else {
                NPNXComWebDialog.this.progressDialog.dismiss();
                NPNXComWebDialog.this.dismiss();
                Toast.makeText(NPNXComWebDialog.this.getActivity(), "Need nickname information", 0).show();
            }
        }
    }

    public class NXJoinWebViewClient extends WebViewClient {
        private Activity activity;
        private String authpopupPrefix = "nxp://authpopup/?openurl=";
        private String webviewClosePrefix = "nxp://webviewclose";
        private String authSuccessPrefix = "nxp://authsuccess/?callbackfunc=";
        private String userverifySuccessPrefix = "nxp://realnamesuccess";

        public NXJoinWebViewClient(Activity activity) {
            this.activity = activity;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (NPNXComWebDialog.this.progressDialog.isShowing()) {
                NPNXComWebDialog.this.progressDialog.dismiss();
            }
            Log.e("EEE", str);
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (!NPNXComWebDialog.this.progressDialog.isShowing()) {
                NPNXComWebDialog.this.progressDialog.show();
            }
            Log.e("SSS", str);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (NPNXComWebDialog.this.progressDialog.isShowing()) {
                NPNXComWebDialog.this.progressDialog.dismiss();
            }
            Log.e("ERROR", i + " " + str + " " + str2);
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.e("TTT", str);
            if (str.startsWith(this.authpopupPrefix)) {
                String strReplace = str.replace(this.authpopupPrefix, "");
                int iIndexOf = strReplace.indexOf("?");
                String str2 = strReplace.substring(0, iIndexOf) + "?protocol=webkit";
                String strReplace2 = strReplace.substring(iIndexOf + 1).replace("protocol=webkit", "");
                Log.e("TTT2", str2);
                Log.e("TTT3", strReplace2);
                NPNXComSignUpUserAuthWebDialog nPNXComSignUpUserAuthWebDialogNewInstance = NPNXComSignUpUserAuthWebDialog.newInstance(this.activity, new Gson().toJson(NPNXComWebDialog.this.session), strReplace, strReplace2);
                nPNXComSignUpUserAuthWebDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.NXJoinWebViewClient.1
                    @Override // kr.co.nexon.android.sns.NPAuthListener
                    public void onResult(int i, String str3, Bundle bundle) {
                        if (i == NXToyErrorCode.SUCCESS.getCode()) {
                            String string = bundle.getString(NPNXComWebDialog.KEY_RESULT);
                            Log.e("PAPA", string);
                            ArrayList<String> queryString = NPNXComWebDialog.parseQueryString(string);
                            String str4 = ("javascript:" + queryString.get(0)) + "(";
                            for (int i2 = 1; i2 < queryString.size(); i2++) {
                                str4 = ((str4 + "'") + queryString.get(i2)) + "'";
                                if (i2 + 1 < queryString.size()) {
                                    str4 = str4 + ',';
                                }
                            }
                            NPNXComWebDialog.this.webview.loadUrl(str4 + ");");
                        }
                    }
                });
                nPNXComSignUpUserAuthWebDialogNewInstance.showDialog(this.activity, NPNXComSignUpUserAuthWebDialog.TAG);
                return true;
            }
            if (str.startsWith(this.webviewClosePrefix)) {
                if (NPNXComWebDialog.this.resultListener != null) {
                    NPNXComWebDialog.this.resultListener.onResult(10006, "close webview", null);
                }
                NPNXComWebDialog.this.dismiss();
                return true;
            }
            if (str.startsWith(this.authSuccessPrefix)) {
                Bundle bundle = new Bundle();
                bundle.putString(NPNXComWebDialog.KEY_RESULT, str.substring(str.indexOf(63) + 1));
                if (NPNXComWebDialog.this.resultListener != null) {
                    NPNXComWebDialog.this.resultListener.onResult(0, GraphResponse.SUCCESS_KEY, bundle);
                }
                NPNXComWebDialog.this.dismiss();
            } else if (str.startsWith(this.userverifySuccessPrefix)) {
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    private void initWebView() {
        final String locale = NXToyCommonPreferenceController.getInstance().getLocale();
        this.webview.setWebViewClient(new NXJoinWebViewClient(getActivity()));
        this.webview.setWebChromeClient(new WebChromeClient() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.2
            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
                if (Build.VERSION.SDK_INT >= 21) {
                    return super.onJsAlert(webView, str, str2, jsResult);
                }
                new NXPAlertDialog.Builder(NPNXComWebDialog.this.getActivity()).setMessage(str2).setPositiveButton(NXLocalizedString.getText(NPNXComWebDialog.this.applicationContext, locale, R.string.yes), new View.OnClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.2.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        jsResult.confirm();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.2.1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        jsResult.confirm();
                    }
                }).create().show();
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
                if (Build.VERSION.SDK_INT >= 21) {
                    return super.onJsConfirm(webView, str, str2, jsResult);
                }
                new NXPAlertDialog.Builder(NPNXComWebDialog.this.getActivity()).setMessage(str2).setPositiveButton(NXLocalizedString.getText(NPNXComWebDialog.this.applicationContext, locale, R.string.yes), new View.OnClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.2.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        jsResult.confirm();
                    }
                }).setNegativeButton(NXLocalizedString.getText(NPNXComWebDialog.this.applicationContext, NXToyCommonPreferenceController.getInstance().getLocale(), R.string.no), new View.OnClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.2.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        jsResult.cancel();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.2.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        jsResult.cancel();
                    }
                }).create().show();
                return true;
            }
        });
        WebSettings settings = this.webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }

    public static NPNXComWebDialog newInstance(Activity activity, String str) {
        NPNXComWebDialog nPNXComWebDialog = new NPNXComWebDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("session", str);
        nPNXComWebDialog.setArguments(bundle);
        return nPNXComWebDialog;
    }

    public static ArrayList<String> parseQueryString(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str2 : str.split("&")) {
            String[] strArrSplit = str2.split("=");
            if (strArrSplit.length == 2) {
                arrayList.add(strArrSplit[1]);
            }
        }
        return arrayList;
    }

    private void setSession(String str) {
        if (NXStringUtil.isNotNull(str)) {
            this.session = (NXToySession) NXJsonUtil.fromObject(str, NXToySession.class);
        } else {
            this.session = new NXToySession();
        }
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        Dialog dialog = getDialog();
        dialog.setContentView(R.layout.nxp_common_web);
        setSession(getArguments().getString("session"));
        FrameLayout frameLayout = (FrameLayout) dialog.findViewById(R.id.webViewContainer);
        this.webview = new WebView(getActivity());
        this.webview.setBackgroundResource(R.color.nxp_color_common_webview_background);
        frameLayout.addView(this.webview, new FrameLayout.LayoutParams(-1, -1));
        ((Button) dialog.findViewById(R.id.btnBack)).setVisibility(4);
        ((Button) dialog.findViewById(R.id.btnClose)).setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPNXComWebDialog.this.dismiss();
            }
        });
        return getView();
    }

    public void initNXAuth() {
        initWebView();
        this.webview.loadUrl("https://member.nexon.com/mobile/verify/realname.aspx?pagetype=2&appcrypt=" + getArguments().getString(KEY_APPCRYPT, ""));
    }

    public void initNXJoin(String str, String str2) {
        initWebView();
        this.progressDialog.show();
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetSvcInfoRequest(), new AnonymousClass3(str, str2));
    }

    public void initNXJoinAuth(String str, String str2) {
        initWebView();
        this.webview.postUrl(str, str2.getBytes());
    }

    public void initSearchID() {
        initWebView();
        this.webview.loadUrl("https://member.nexon.com/mobile/find/findid.aspx?pagetype=2");
    }

    public void initSearchPW() {
        initWebView();
        this.webview.loadUrl("https://member.nexon.com/mobile/find/findpwd.aspx?pagetype=2");
    }

    public String loadingMessage() {
        return getString(R.string.progress_loading);
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        this.webview.loadUrl("javascript:MFW.Back();");
    }

    public void setResultListener(NPAuthListener nPAuthListener) {
        this.resultListener = nPAuthListener;
    }
}
