package com.nhn.android.naverlogin.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLoginDialogMng {
    public Object mProgressDialogSync = new Object();
    public Object mAlertDialogSync = new Object();
    private ProgressDialog mNLoginGlobalDefaultProgressDialog = null;

    public synchronized boolean hideProgressDlg() {
        boolean z = false;
        synchronized (this) {
            synchronized (this.mProgressDialogSync) {
                if (this.mNLoginGlobalDefaultProgressDialog != null) {
                    try {
                        this.mNLoginGlobalDefaultProgressDialog.hide();
                        this.mNLoginGlobalDefaultProgressDialog.dismiss();
                        this.mNLoginGlobalDefaultProgressDialog = null;
                        z = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public boolean showProgressDlg(Context context, String str, DialogInterface.OnCancelListener onCancelListener) {
        synchronized (this.mProgressDialogSync) {
            try {
                if (this.mNLoginGlobalDefaultProgressDialog != null) {
                    this.mNLoginGlobalDefaultProgressDialog.hide();
                    this.mNLoginGlobalDefaultProgressDialog.dismiss();
                }
                this.mNLoginGlobalDefaultProgressDialog = new ProgressDialog(context);
                this.mNLoginGlobalDefaultProgressDialog.setIndeterminate(true);
                this.mNLoginGlobalDefaultProgressDialog.setMessage(str);
                this.mNLoginGlobalDefaultProgressDialog.setProgressStyle(0);
                if (onCancelListener != null) {
                    this.mNLoginGlobalDefaultProgressDialog.setOnCancelListener(onCancelListener);
                }
                this.mNLoginGlobalDefaultProgressDialog.setCanceledOnTouchOutside(false);
                this.mNLoginGlobalDefaultProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.nhn.android.naverlogin.ui.OAuthLoginDialogMng.1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        OAuthLoginDialogMng.this.mNLoginGlobalDefaultProgressDialog = null;
                    }
                });
                this.mNLoginGlobalDefaultProgressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
