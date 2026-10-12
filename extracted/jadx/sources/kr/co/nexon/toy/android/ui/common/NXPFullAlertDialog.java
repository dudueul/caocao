package kr.co.nexon.toy.android.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.common.view.NXPFullAlertView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPFullAlertDialog extends NXPAlertDialog {

    public static class FullBuilder extends NXPAlertDialog.Builder {
        public FullBuilder(Context context) {
            super(context);
        }

        @Override // kr.co.nexon.toy.android.ui.common.NXPAlertDialog.Builder
        public NXPAlertDialog create() {
            NXPAlertDialog nXPAlertDialogCreateDialog = createDialog();
            if (this.p.title != null) {
                nXPAlertDialogCreateDialog.setTitle(this.p.title);
            }
            if (this.p.cancelable != null) {
                nXPAlertDialogCreateDialog.setCancelable(this.p.cancelable.booleanValue());
            }
            if (NXStringUtil.isNotNull(this.p.positiveButtonText)) {
                nXPAlertDialogCreateDialog.setPositiveButtonText(this.p.positiveButtonText);
            }
            if (NXStringUtil.isNotNull(this.p.negativeButtonText)) {
                nXPAlertDialogCreateDialog.setNegativeButtonText(this.p.negativeButtonText);
            }
            nXPAlertDialogCreateDialog.setMessage(this.p.message);
            nXPAlertDialogCreateDialog.setPositiveButtonClickListener(this.p.positiveBtnClickListener);
            nXPAlertDialogCreateDialog.setNegativeButtonClickListener(this.p.negativeBtnClickListener);
            nXPAlertDialogCreateDialog.setOnCancelListener(this.p.cancelListener);
            return nXPAlertDialogCreateDialog;
        }

        @Override // kr.co.nexon.toy.android.ui.common.NXPAlertDialog.Builder
        public NXPAlertDialog createDialog() {
            return new NXPFullAlertDialog(this.context);
        }
    }

    protected NXPFullAlertDialog(Context context) {
        super(context, R.style.ToyDialogThemeFullScreen);
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertDialog
    protected NXPAlertViewBase createAlertView(Context context) {
        NXPFullAlertView nXPFullAlertView = (NXPFullAlertView) View.inflate(context, R.layout.nxp_full_alert_dialog_view, null);
        nXPFullAlertView.setPositiveButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.NXPFullAlertDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPFullAlertDialog.this.positiveBtnClickListener != null) {
                    NXPFullAlertDialog.this.positiveBtnClickListener.onClick(view);
                }
                NXPFullAlertDialog.this.dismiss();
            }
        });
        nXPFullAlertView.setNegativeButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.NXPFullAlertDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPFullAlertDialog.this.negativeBtnClickListener != null) {
                    NXPFullAlertDialog.this.negativeBtnClickListener.onClick(view);
                }
                NXPFullAlertDialog.this.dismiss();
            }
        });
        nXPFullAlertView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.NXPFullAlertDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPFullAlertDialog.this.cancelListener != null) {
                    NXPFullAlertDialog.this.cancelListener.onCancel(NXPFullAlertDialog.this);
                }
                NXPFullAlertDialog.this.dismiss();
            }
        });
        return nXPFullAlertView;
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertDialog, android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
        }
    }
}
