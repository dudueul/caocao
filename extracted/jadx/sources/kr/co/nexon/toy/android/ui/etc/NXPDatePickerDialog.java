package kr.co.nexon.toy.android.ui.etc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.etc.view.NXPDatePickerView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPDatePickerDialog extends NPDialogBase {
    public static final String TAG = "NXPDatePickerDialog";
    private NXPDatePickerView.NXPDatePcikerListener datePickerListener;

    public static NXPDatePickerDialog newInstance(Activity activity) {
        NXPDatePickerDialog nXPDatePickerDialog = new NXPDatePickerDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        nXPDatePickerDialog.setArguments(bundle);
        return nXPDatePickerDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPDatePickerView nXPDatePickerView = (NXPDatePickerView) View.inflate(this.applicationContext, R.layout.nxp_date_picker_view, null);
        nXPDatePickerView.setOnDatePickerListener(new NXPDatePickerView.NXPDatePcikerListener() { // from class: kr.co.nexon.toy.android.ui.etc.NXPDatePickerDialog.1
            @Override // kr.co.nexon.toy.android.ui.etc.view.NXPDatePickerView.NXPDatePcikerListener
            public void onCancel() {
                if (NXPDatePickerDialog.this.datePickerListener != null) {
                    NXPDatePickerDialog.this.datePickerListener.onCancel();
                }
                NXPDatePickerDialog.this.dismiss();
            }

            @Override // kr.co.nexon.toy.android.ui.etc.view.NXPDatePickerView.NXPDatePcikerListener
            public void onConfirm(int i, int i2) {
                if (NXPDatePickerDialog.this.datePickerListener != null) {
                    NXPDatePickerDialog.this.datePickerListener.onConfirm(i, i2);
                }
                NXPDatePickerDialog.this.dismiss();
            }
        });
        return nXPDatePickerView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (this.datePickerListener != null) {
            this.datePickerListener.onCancel();
        }
        dismiss();
    }

    public void setDatePickerListener(NXPDatePickerView.NXPDatePcikerListener nXPDatePcikerListener) {
        this.datePickerListener = nXPDatePcikerListener;
    }
}
