package kr.co.nexon.toy.android.ui.auth.accountmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXProgressDialog;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuInitialState;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuDialog extends NPDialogBase {
    public static final String TAG = "NXPAccountMenuDialog";
    private NXPAccountMenuState accountMenuState;
    private NPListener listener;
    private View.OnClickListener onCloseButtonClickListener = new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.closeBtn) {
                NXPAccountMenuDialog.this.dismissDialog();
            }
        }
    };
    private NXProgressDialog progressBar;

    public static NXPAccountMenuDialog newInstance(Activity activity, NPListener nPListener) {
        NXPAccountMenuDialog nXPAccountMenuDialog = new NXPAccountMenuDialog();
        nXPAccountMenuDialog.listener = nPListener;
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        nXPAccountMenuDialog.setArguments(bundle);
        nXPAccountMenuDialog.accountMenuState = new NXPAccountMenuInitialState();
        return nXPAccountMenuDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public void activityCreated() {
        NPAccount nPAccount = NPAccount.getInstance(getActivity());
        this.progressBar = new NXProgressDialog(getActivity());
        this.progressBar.show();
        nPAccount.getUserInfo(new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(final NXToyResult nXToyResult) {
                NXPAccountMenuDialog.this.getActivity().runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NXPAccountMenuDialog.this.progressBar.dismiss();
                        if (nXToyResult.errorCode == 0) {
                            NXPAccountMenuDialog.this.getDialog().setContentView(NXPAccountMenuDialog.this.accountMenuState.createView(NXPAccountMenuDialog.this));
                            NXPAccountMenuDialog.this.accountMenuState.setCloseButtonClickListener(NXPAccountMenuDialog.this.onCloseButtonClickListener);
                        } else {
                            if (NXPAccountMenuDialog.this.listener != null) {
                                NXPAccountMenuDialog.this.listener.onResult(new NXToyResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail, NXToyRequestTag.ShowAccountMenu.getValue()));
                            }
                            NXPAccountMenuDialog.this.dismiss();
                        }
                    }
                });
            }
        });
    }

    public void changeState(NXPAccountMenuState nXPAccountMenuState) {
        this.accountMenuState = nXPAccountMenuState;
        getDialog().setContentView(this.accountMenuState.createView(this));
        this.accountMenuState.setCloseButtonClickListener(this.onCloseButtonClickListener);
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        return getView();
    }

    public void dismissDialog() {
        this.listener.onResult(new NXToyResult(NXToyErrorCode.USER_CANCEL.getCode(), NXToyLocaleManager.getInstance(this.applicationContext).getString(R.string.npres_cancel), "", NXToyRequestTag.ShowAccountMenu.getValue()));
        dismiss();
    }

    public NPListener getToyResultListener() {
        return this.listener;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (this.progressBar == null || !this.progressBar.isShowing()) {
            this.accountMenuState.onBackPressed(this);
        }
    }
}
