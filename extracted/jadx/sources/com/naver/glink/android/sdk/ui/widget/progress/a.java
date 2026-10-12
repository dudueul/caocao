package com.naver.glink.android.sdk.ui.widget.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import com.naver.glink.android.sdk.R;

/* JADX INFO: compiled from: LoadingProgressDialog.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a extends ProgressDialog {
    public a(Context context) {
        super(context, R.style.notitle_transparent_defaultdialog);
    }

    @Override // android.app.ProgressDialog, android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
        setContentView(R.layout.dialog_loading);
    }
}
