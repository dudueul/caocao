package com.naver.glink.android.sdk.ui.record;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.naver.glink.android.sdk.R;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: compiled from: RecordSettingDialog.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d extends DialogFragment {
    private a a;

    /* JADX INFO: compiled from: RecordSettingDialog.java */
    public interface a {
        void a(c cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(RadioGroup radioGroup) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.rb_quality_low) {
            return c.d;
        }
        return (checkedRadioButtonId == R.id.rb_quality_std || checkedRadioButtonId != R.id.rb_quality_high) ? c.e : c.f;
    }

    public static d a() {
        Bundle bundle = new Bundle();
        d dVar = new d();
        dVar.setArguments(bundle);
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(RadioGroup radioGroup) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.rb_resolution_360) {
            return c.a;
        }
        if (checkedRadioButtonId == R.id.rb_resolution_480) {
            return c.b;
        }
        return checkedRadioButtonId == R.id.rb_resolution_720 ? c.c : c.e;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        com.naver.glink.android.sdk.d.f(com.naver.glink.android.sdk.d.r());
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(bundle);
        dialogOnCreateDialog.getWindow().requestFeature(1);
        dialogOnCreateDialog.getWindow().addFlags(16777216);
        dialogOnCreateDialog.getWindow().addFlags(1024);
        dialogOnCreateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return dialogOnCreateDialog;
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.widget_record_setting_layout, viewGroup, false);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.gravity = 81;
        attributes.width = -1;
        attributes.height = -2;
        getDialog().getWindow().setAttributes(attributes);
        getDialog().getWindow().setWindowAnimations(R.style.record_setting_anim);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        View viewFindViewById = view.findViewById(R.id.record_setting_ok);
        View viewFindViewById2 = view.findViewById(R.id.record_setting_cancel);
        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.setting_quality_radio_group);
        final RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.setting_resolution_radio_group);
        final RadioButton radioButton = (RadioButton) view.findViewById(R.id.record_setting_mic_on);
        RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.record_setting_mic_off);
        radioButton.setChecked(true);
        radioButton2.setChecked(false);
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.record.d.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!com.naver.glink.android.sdk.ui.write.b.a(d.this.getActivity(), NXRuntimePermissionManager.WRITE_EXTERNAL_STORAGE)) {
                    com.naver.glink.android.sdk.ui.write.b.a(d.this.getActivity(), NXRuntimePermissionManager.WRITE_EXTERNAL_STORAGE, d.this.getString(R.string.record_permission_error), 8090);
                    d.this.dismiss();
                } else {
                    if (d.this.a != null) {
                        d.this.a.a(new c(d.this.b(radioGroup2), d.this.a(radioGroup), radioButton.isChecked()));
                    }
                    d.this.dismiss();
                }
            }
        });
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.record.d.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                d.this.dismiss();
                com.naver.glink.android.sdk.d.f(com.naver.glink.android.sdk.d.r());
            }
        });
        com.naver.glink.android.sdk.d.e().a(radioButton);
        com.naver.glink.android.sdk.d.e().a(radioButton2);
        com.naver.glink.android.sdk.d.e().a(viewFindViewById, true, 2);
    }

    @Override // android.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle == null || this.a != null) {
            return;
        }
        dismiss();
    }
}
