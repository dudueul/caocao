package com.naver.glink.android.sdk.ui.parent.a;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.d;

/* JADX INFO: compiled from: PlugDialogFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a extends DialogFragment {
    public void a() {
        a(0, 0, -1, d.p().b().y - 1);
    }

    @SuppressLint({"RtlHardcoded"})
    public void a(int i, int i2, int i3, int i4) {
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = i;
        attributes.y = i2;
        attributes.width = i3;
        attributes.height = i4;
        window.getWindowManager().updateViewLayout(window.getDecorView(), attributes);
    }

    public void b() {
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(getActivity(), getTheme()) { // from class: com.naver.glink.android.sdk.ui.parent.a.a.1
            @Override // android.app.Dialog
            public void onBackPressed() {
                a.this.b();
                super.onBackPressed();
            }
        };
        Window window = dialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
            window.setDimAmount(0.0f);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setWindowAnimations(R.style.multimedia_dialog_anim);
            window.addFlags(16778240);
        }
        return dialog;
    }

    @Override // android.app.Fragment
    public void onPause() {
        com.naver.glink.android.sdk.a.a.b.b(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        com.naver.glink.android.sdk.a.a.b.a(this);
    }
}
