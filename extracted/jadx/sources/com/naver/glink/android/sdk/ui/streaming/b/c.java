package com.naver.glink.android.sdk.ui.streaming.b;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.d;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: compiled from: SelfCameraFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c extends com.naver.glink.android.sdk.ui.parent.a.a {
    private TextureView a;
    private b b;

    public static c c() {
        return new c();
    }

    private void d() {
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = d.p().b().x;
        attributes.y = 0;
        window.getWindowManager().updateViewLayout(window.getDecorView(), attributes);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(bundle);
        dialogOnCreateDialog.setCanceledOnTouchOutside(false);
        Window window = dialogOnCreateDialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
            window.setDimAmount(0.0f);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(51);
            window.setWindowAnimations(R.style.multimedia_dialog_anim);
            window.addFlags(android.R.string.face_error_security_update_required);
        }
        return dialogOnCreateDialog;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_self_camera, viewGroup, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.Fragment
    public void onPause() {
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
        super.onPause();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.Fragment
    public void onResume() {
        super.onResume();
        if (!com.naver.glink.android.sdk.ui.write.b.a(getActivity(), NXRuntimePermissionManager.CAMERA)) {
            dismiss();
        } else {
            this.b = new b(getActivity(), this.a);
            this.b.a();
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        d();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        com.naver.glink.android.sdk.ui.parent.a.c.a(getDialog()).a(view);
        this.a = (TextureView) view.findViewById(R.id.self_camera);
    }
}
