package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.gson.Gson;
import com.nexon.core.util.NXStringUtil;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import kr.co.nexon.mdev.android.util.NXFileUtil;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.network.NXHttpURLRequestResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPNCSDialog extends NPWebDialogTitleBar {
    private static final String KEY_EXTRA_DATA = "jsonParams";
    public static final String TAG = "NPNCSDialog";
    private String ncsUrl = "";

    public static NPNCSDialog newInstance(Activity activity, String str, String str2, String str3) {
        NPNCSDialog nPNCSDialog = new NPNCSDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("title", str);
        bundle.putString("url", str2);
        bundle.putString(KEY_EXTRA_DATA, str3);
        nPNCSDialog.setArguments(bundle);
        return nPNCSDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogTitleBar, kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public String getURL() {
        return this.ncsUrl;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public WebViewClient getWebViewClient() {
        return new NPWebDialogBase.NPWebViewClient() { // from class: kr.co.nexon.toy.android.ui.board.NPNCSDialog.2
            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient
            public void checkCanGoBack(WebView webView, String str) {
                if (str.equals(NPNCSDialog.this.getPageServerUrl() + "/cc")) {
                    webView.clearHistory();
                    if (NPNCSDialog.this.backButton != null) {
                        NPNCSDialog.this.backButton.setVisibility(4);
                        return;
                    }
                    return;
                }
                if (!webView.canGoBack() || NPNCSDialog.this.backButton == null) {
                    return;
                }
                NPNCSDialog.this.backButton.setVisibility(0);
            }

            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (Build.VERSION.RELEASE.startsWith("4.4")) {
                    webView.loadUrl("javascript:toyClientJS = { popImage: function(popupId) { return window.Android.open(popupId); } }");
                }
            }

            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }

            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
            }

            @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase.NPWebViewClient, android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
        };
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogTitleBar, kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    public void initContent(Dialog dialog) {
        super.initContent(dialog);
        initUrl();
    }

    public void initUrl() {
        String string = getArguments().getString(KEY_EXTRA_DATA);
        StringBuilder sb = new StringBuilder();
        sb.append(getArguments().getString("url", ""));
        if (NXStringUtil.isNotNull(string)) {
            List list = (List) ((Map) new Gson().fromJson(string, Map.class)).get("questionInfos");
            sb.append("&P1=");
            sb.append(Build.MODEL);
            sb.append("&P2=");
            sb.append("Android+");
            sb.append("OS");
            sb.append(Build.VERSION.RELEASE);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    sb.append("&");
                    sb.append("P");
                    sb.append(i + 3);
                    sb.append("=");
                    try {
                        sb.append(URLEncoder.encode((String) list.get(i), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.ncsUrl = sb.toString();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kr.co.nexon.toy.android.ui.board.NPNCSDialog$1] */
    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    protected void uploadImage(Uri uri) {
        final File fileUriToFile = NXFileUtil.uriToFile(getActivity(), uri);
        new AsyncTask<Void, Void, String>() { // from class: kr.co.nexon.toy.android.ui.board.NPNCSDialog.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(Void... voidArr) {
                String str = "";
                if (fileUriToFile.exists()) {
                    URL url = null;
                    HttpURLConnection httpURLConnection = null;
                    try {
                        url = new URL("https://support.nexon.com/Common/FileUploadPost");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    try {
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                    httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "multipart/form-data;boundary=*******boundary*******");
                    httpURLConnection.setRequestProperty("Accept", "*/*");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    DataOutputStream dataOutputStream = null;
                    try {
                        DataOutputStream dataOutputStream2 = new DataOutputStream(httpURLConnection.getOutputStream());
                        try {
                            dataOutputStream2.writeBytes("--*******boundary*******\r\n");
                            dataOutputStream2.writeBytes("Content-Disposition: form-data; name=\"saveFile\"; filename=\"" + fileUriToFile.getName() + "\"\r\n");
                            dataOutputStream2.writeBytes("\r\n");
                            dataOutputStream2.writeBytes("Content-Type: " + URLConnection.guessContentTypeFromName(fileUriToFile.getName()));
                            dataOutputStream2.writeBytes("\r\n");
                            dataOutputStream2.writeBytes("Content-Transfer-Encoding: binary");
                            dataOutputStream2.writeBytes("\r\n");
                            dataOutputStream2.writeBytes("\r\n");
                            dataOutputStream2.flush();
                            dataOutputStream = dataOutputStream2;
                        } catch (IOException e3) {
                            e = e3;
                            dataOutputStream = dataOutputStream2;
                            e.printStackTrace();
                        }
                    } catch (IOException e4) {
                        e = e4;
                    }
                    byte[] bArr = new byte[65535];
                    try {
                        FileInputStream fileInputStream = new FileInputStream(fileUriToFile);
                        while (true) {
                            try {
                                int i = fileInputStream.read(bArr);
                                if (i == -1) {
                                    break;
                                }
                                dataOutputStream.write(bArr, 0, i);
                            } catch (FileNotFoundException e5) {
                                e = e5;
                                e.printStackTrace();
                            } catch (IOException e6) {
                                e = e6;
                                e.printStackTrace();
                            }
                        }
                        dataOutputStream.writeBytes("\r\n");
                        dataOutputStream.writeBytes("--*******boundary*******--");
                        dataOutputStream.writeBytes("\r\n");
                        fileInputStream.close();
                        dataOutputStream.flush();
                    } catch (FileNotFoundException e7) {
                        e = e7;
                    } catch (IOException e8) {
                        e = e8;
                    }
                    int responseCode = 0;
                    try {
                        responseCode = httpURLConnection.getResponseCode();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                    if (responseCode == 200) {
                        NXHttpURLRequestResult nXHttpURLRequestResult = new NXHttpURLRequestResult();
                        nXHttpURLRequestResult.responseHeaders = httpURLConnection.getHeaderFields();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        BufferedInputStream bufferedInputStream = null;
                        byte[] bArr2 = new byte[65535];
                        try {
                            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpURLConnection.getInputStream());
                            while (true) {
                                try {
                                    int i2 = bufferedInputStream2.read(bArr2);
                                    if (i2 == -1) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(bArr2, 0, i2);
                                } catch (IOException e10) {
                                    e = e10;
                                    bufferedInputStream = bufferedInputStream2;
                                    e.printStackTrace();
                                }
                            }
                            bufferedInputStream = bufferedInputStream2;
                        } catch (IOException e11) {
                            e = e11;
                        }
                        nXHttpURLRequestResult.bytes = byteArrayOutputStream.toByteArray();
                        String str2 = new String(nXHttpURLRequestResult.bytes, 0, nXHttpURLRequestResult.bytes.length);
                        try {
                            bufferedInputStream.close();
                            byteArrayOutputStream.close();
                        } catch (IOException e12) {
                            e12.printStackTrace();
                        }
                        int iIntValue = 0;
                        int iIntValue2 = 0;
                        String str3 = "";
                        try {
                            try {
                                JSONObject jSONObject = (JSONObject) new JSONObject(str2).get("File");
                                if (fileUriToFile != null) {
                                    iIntValue = ((Integer) jSONObject.get("ID")).intValue();
                                    iIntValue2 = ((Integer) jSONObject.get("Size")).intValue();
                                    str3 = (String) jSONObject.get("Name");
                                }
                            } catch (JSONException e13) {
                                e = e13;
                                e.printStackTrace();
                            }
                        } catch (JSONException e14) {
                            e = e14;
                        }
                        if (iIntValue != 0 && iIntValue2 != 0 && NXStringUtil.isNotNull(str3)) {
                            str = "javascript:$(\"#" + NPNCSDialog.this.tagId + "\").append(\"<li><button type='button' onclick='deleteFile(this)' class='btn_delete' data-file-totalsize='" + iIntValue2 + "' value='" + iIntValue + "'>파일 삭제</button>" + str3 + "</li>\")";
                        }
                    }
                    try {
                        dataOutputStream.close();
                    } catch (IOException e15) {
                        e15.printStackTrace();
                    }
                    httpURLConnection.disconnect();
                }
                return str;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str) {
                super.onPostExecute(str);
                if (NXStringUtil.isNotNull(str)) {
                    NPNCSDialog.this.currentWebView.loadUrl(str);
                }
            }

            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                super.onPreExecute();
            }
        }.execute(new Void[0]);
    }
}
