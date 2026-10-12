package kr.co.nexon.toy.android.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.toy.android.ui.dialog.NPLoadingDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public abstract class NPDialogBase extends NXPDialogFragment {
    public static final String TAG = "NPDialogBase";
    protected Context applicationContext;
    private Handler handler;
    protected NPLoadingDialog progressDialog;

    public static int getToyDefaultTheme(Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) == 1024 ? R.style.ToyDialogThemeFullScreen : R.style.ToyDialogTheme;
    }

    protected void activityCreated() {
    }

    public abstract View createView();

    @Override // android.app.DialogFragment
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    protected void dismissProgressDialog() {
        if (this.progressDialog == null) {
            ToyLog.d("progressDialog is null");
        } else if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }

    protected Handler getMainLooperHandler() {
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper());
        }
        return this.handler;
    }

    protected boolean isNotRunningActivity() {
        Activity activity = getActivity();
        return activity == null || activity.isFinishing();
    }

    protected boolean isShowingProgressDialog() {
        if (this.progressDialog == null) {
            return false;
        }
        return this.progressDialog.isShowing();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getDialog() == null) {
            dismiss();
        } else {
            activityCreated();
        }
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment, android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        ToyLog.d("onCreateDialog : " + bundle);
        if (bundle != null || isNotRunningActivity()) {
            setShowsDialog(false);
            return null;
        }
        Activity activity = getActivity();
        this.applicationContext = activity.getApplicationContext();
        this.progressDialog = new NPLoadingDialog(activity);
        NPAccount.getInstance(activity);
        return super.onCreateDialog(bundle);
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ToyLog.d("onCreateView : " + bundle);
        if (getDialog() == null) {
            return null;
        }
        return createView();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
        }
        if (isNotRunningActivity()) {
            dialog.dismiss();
        }
    }

    protected void runOnUiThread(@NonNull Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            getMainLooperHandler().post(runnable);
        }
    }

    protected void showProgressDialog() {
        if (this.progressDialog == null) {
            ToyLog.d("progressDialog is null");
        } else {
            if (this.progressDialog.isShowing()) {
                return;
            }
            this.progressDialog.show();
        }
    }
}
