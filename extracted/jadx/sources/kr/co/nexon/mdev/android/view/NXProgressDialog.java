package kr.co.nexon.mdev.android.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import com.nexon.npaccount.R;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXProgressDialog {
    private Activity currentActivity;
    private boolean isShowing = false;
    private View progressDialog;

    public NXProgressDialog(Activity activity) {
        this.currentActivity = activity;
        createProgressView();
    }

    private void createProgressView() {
        this.progressDialog = ((LayoutInflater) this.currentActivity.getSystemService("layout_inflater")).inflate(R.layout.nx_progressbar_big_style, (ViewGroup) null);
        this.progressDialog.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.mdev.android.view.NXProgressDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    public void dismiss() {
        this.currentActivity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.mdev.android.view.NXProgressDialog.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (NXProgressDialog.this.isShowing) {
                        ((ViewManager) NXProgressDialog.this.progressDialog.getParent()).removeView(NXProgressDialog.this.progressDialog);
                        NXProgressDialog.this.isShowing = false;
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    public void show() {
        this.currentActivity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.mdev.android.view.NXProgressDialog.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (NXProgressDialog.this.isShowing) {
                        return;
                    }
                    NXProgressDialog.this.currentActivity.addContentView(NXProgressDialog.this.progressDialog, new ViewGroup.LayoutParams(-1, -1));
                    NXProgressDialog.this.isShowing = true;
                } catch (Exception e) {
                }
            }
        });
    }
}
