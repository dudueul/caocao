package kr.co.nexon.mdev.android.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import com.nexon.core.log.ToyLog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPDialogFragment extends DialogFragment {
    public static final String KEY_THEME = "android:theme";

    public static NXPDialogFragment newInstance(int i) {
        NXPDialogFragment nXPDialogFragment = new NXPDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_THEME, i);
        nXPDialogFragment.setArguments(bundle);
        return nXPDialogFragment;
    }

    protected final View findViewById(int i) {
        return getDialog().findViewById(i);
    }

    public boolean isLandscape() {
        return getActivity().getResources().getConfiguration().orientation == 2;
    }

    public void onBackPressed() {
        ToyLog.d(getClass().getSimpleName() + " is dismiss");
        dismissAllowingStateLoss();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(KEY_THEME, getArguments().getInt(KEY_THEME, 0));
        super.onCreate(bundle);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new Dialog(getActivity(), getTheme()) { // from class: kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment.1
            @Override // android.app.Dialog
            public void onBackPressed() {
                this.onBackPressed();
            }
        };
    }

    @Override // android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        try {
            FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
            fragmentTransactionBeginTransaction.add(this, str);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void showDialog(Activity activity, String str) {
        if (activity.isFinishing()) {
            return;
        }
        show(activity.getFragmentManager(), str);
    }
}
