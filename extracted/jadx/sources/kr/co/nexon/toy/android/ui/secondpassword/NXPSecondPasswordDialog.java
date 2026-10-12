package kr.co.nexon.toy.android.ui.secondpassword;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.secondpassword.constants.NXPSecondPasswordState;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.presenter.NXPBasePresenter;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordDialog extends NPDialogBase {
    private static final String KEY_STATUS = "status";
    public static final String TAG = "NXP2ndPasswordDialog";
    private NXPBasePresenter presenter;
    private NXPSecondPasswordState status;
    private NXPBaseView view;

    public static NXPSecondPasswordDialog newInstance(Activity activity, int i) {
        NXPSecondPasswordDialog nXPSecondPasswordDialog = new NXPSecondPasswordDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putInt("status", i);
        nXPSecondPasswordDialog.setArguments(bundle);
        return nXPSecondPasswordDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.status = NXPSecondPasswordState.getStateByCode(getArguments().getInt("status", 0));
        return this.view.getView();
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        this.view.onBackPressed();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.view.setFragment(this);
        this.presenter.setView(this.view);
    }

    public void setPresenter(NXPBasePresenter nXPBasePresenter) {
        this.presenter = nXPBasePresenter;
    }

    public void setView(NXPBaseView nXPBaseView) {
        this.view = nXPBaseView;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public void showProgressDialog() {
        super.showProgressDialog();
    }
}
