package kr.co.nexon.toy.android.ui.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.common.view.NXPAlertView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAlertDialog extends AlertDialog {
    protected NXPAlertViewBase alertView;
    protected DialogInterface.OnCancelListener cancelListener;
    protected View.OnClickListener negativeBtnClickListener;
    protected View.OnClickListener positiveBtnClickListener;

    public static class Builder {
        protected Context context;
        protected DialogParams p = new DialogParams();

        public Builder(Context context) {
            this.context = context;
        }

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
            } else {
                nXPAlertDialogCreateDialog.setPositiveButtonText(NXToyLocaleManager.getInstance(this.context).getString(R.string.confirm));
            }
            if (NXStringUtil.isNotNull(this.p.negativeButtonText)) {
                nXPAlertDialogCreateDialog.setNegativeButtonText(this.p.negativeButtonText);
            }
            nXPAlertDialogCreateDialog.setMessage(this.p.message);
            nXPAlertDialogCreateDialog.setPositiveButtonClickListener(this.p.positiveBtnClickListener);
            nXPAlertDialogCreateDialog.setNegativeButtonClickListener(this.p.negativeBtnClickListener);
            nXPAlertDialogCreateDialog.setOnCancelListener(this.p.cancelListener);
            nXPAlertDialogCreateDialog.setCanceledOnTouchOutside(false);
            return nXPAlertDialogCreateDialog;
        }

        protected NXPAlertDialog createDialog() {
            return new NXPAlertDialog(this.context);
        }

        public Builder setCancelable(boolean z) {
            this.p.cancelable = Boolean.valueOf(z);
            return this;
        }

        public Builder setMessage(String str) {
            this.p.message = str;
            return this;
        }

        public Builder setNegativeButton(String str, View.OnClickListener onClickListener) {
            this.p.negativeButtonText = str;
            this.p.negativeBtnClickListener = onClickListener;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.p.cancelListener = onCancelListener;
            return this;
        }

        public Builder setPositiveButton(String str, View.OnClickListener onClickListener) {
            this.p.positiveButtonText = str;
            this.p.positiveBtnClickListener = onClickListener;
            return this;
        }

        public Builder setTitle(String str) {
            this.p.title = str;
            return this;
        }

        public NXPAlertDialog show() {
            NXPAlertDialog nXPAlertDialogCreate = create();
            nXPAlertDialogCreate.show();
            return nXPAlertDialogCreate;
        }
    }

    protected static class DialogParams {
        DialogInterface.OnCancelListener cancelListener;
        Boolean cancelable;
        String message;
        View.OnClickListener negativeBtnClickListener;
        String negativeButtonText;
        View.OnClickListener positiveBtnClickListener;
        String positiveButtonText;
        String title;

        protected DialogParams() {
        }
    }

    protected NXPAlertDialog(Context context) {
        super(context, R.style.ToyDialogTheme);
        this.alertView = createAlertView(context);
    }

    protected NXPAlertDialog(Context context, int i) {
        super(context, i);
        this.alertView = createAlertView(getContext());
    }

    protected NXPAlertViewBase createAlertView(Context context) {
        NXPAlertView nXPAlertView = (NXPAlertView) View.inflate(context, R.layout.nxp_alert_dialog_view, null);
        nXPAlertView.setPositiveButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.NXPAlertDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPAlertDialog.this.positiveBtnClickListener != null) {
                    NXPAlertDialog.this.positiveBtnClickListener.onClick(view);
                }
                NXPAlertDialog.this.dismiss();
            }
        });
        nXPAlertView.setNegativeButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.NXPAlertDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPAlertDialog.this.negativeBtnClickListener != null) {
                    NXPAlertDialog.this.negativeBtnClickListener.onClick(view);
                }
                NXPAlertDialog.this.dismiss();
            }
        });
        return nXPAlertView;
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.alertView);
        super.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.common.NXPAlertDialog.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (NXPAlertDialog.this.cancelListener != null) {
                    NXPAlertDialog.this.cancelListener.onCancel(dialogInterface);
                }
            }
        });
    }

    public void setMessage(String str) {
        if (this.alertView != null) {
            this.alertView.setMessage(str);
        }
    }

    public void setNegativeButtonClickListener(View.OnClickListener onClickListener) {
        this.negativeBtnClickListener = onClickListener;
    }

    public void setNegativeButtonText(String str) {
        if (this.alertView != null) {
            this.alertView.setNegativeButtonText(str);
        }
    }

    @Override // android.app.Dialog
    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.cancelListener = onCancelListener;
    }

    public void setPositiveButtonClickListener(View.OnClickListener onClickListener) {
        this.positiveBtnClickListener = onClickListener;
    }

    public void setPositiveButtonText(String str) {
        if (this.alertView != null) {
            this.alertView.setPositiveButtonText(str);
        }
    }

    public void setTitle(String str) {
        if (this.alertView != null) {
            this.alertView.setTitle(str);
        }
    }
}
