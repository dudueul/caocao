package kr.co.nexon.mdev.android.web;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXWebChromeClient extends WebChromeClient {
    public static final int FILECHOOSER_KITKAT = 10002;
    public static final int FILECHOOSER_RESULTCODE = 1;
    private Activity mActivity;
    private ValueCallback<Uri[]> mFilePathCallback;
    private ValueCallback<Uri> mUploadMessage;

    public NXWebChromeClient(Activity activity) {
        this.mActivity = activity;
    }

    public ValueCallback<Uri[]> getFilePathCallback() {
        return this.mFilePathCallback;
    }

    public ValueCallback<Uri> getUploadMessage() {
        return this.mUploadMessage;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        if (this.mFilePathCallback != null) {
            this.mFilePathCallback.onReceiveValue(null);
        }
        this.mFilePathCallback = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        this.mActivity.startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
        return true;
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooser(valueCallback, "");
    }

    public void openFileChooser(ValueCallback valueCallback, String str) {
        this.mUploadMessage = valueCallback;
        showFileSelector();
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        openFileChooser(valueCallback, "");
    }

    public void setFilePathCallback(ValueCallback<Uri[]> valueCallback) {
        this.mFilePathCallback = valueCallback;
    }

    public void setUploadMessage(ValueCallback<Uri> valueCallback) {
        this.mUploadMessage = valueCallback;
    }

    protected void showFileSelector() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        this.mActivity.startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
    }
}
