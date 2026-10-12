package kr.co.nexon.toy.android.ui.dialog;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.dialog.NXDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPLoadingDialog extends NXDialog {
    private Handler handler;
    private boolean isCancelable;
    private boolean isShow;

    public NPLoadingDialog(Activity activity) {
        super(activity);
        this.isShow = false;
        this.isCancelable = false;
        setOwnerActivity(activity);
        this.handler = new Handler(Looper.getMainLooper());
    }

    private void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.handler.post(runnable);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.isShow = false;
        if (getOwnerActivity().isFinishing()) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.dialog.NPLoadingDialog.1
            @Override // java.lang.Runnable
            public void run() {
                NPLoadingDialog.super.dismiss();
            }
        });
    }

    @Override // android.app.Dialog
    public boolean isShowing() {
        return this.isShow;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXDialog, android.app.Dialog
    public void onBackPressed() {
        if (this.isCancelable) {
            super.onBackPressed();
        }
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.nx_progressbar_big_style);
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        this.isCancelable = z;
    }

    @Override // android.app.Dialog
    public void show() {
        this.isShow = true;
        runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.dialog.NPLoadingDialog.2
            @Override // java.lang.Runnable
            public void run() {
                if (NPLoadingDialog.this.getOwnerActivity().isFinishing()) {
                    NPLoadingDialog.this.isShow = false;
                } else {
                    NPLoadingDialog.super.show();
                }
            }
        });
    }
}
