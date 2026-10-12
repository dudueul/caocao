package kr.co.nexon.toy.android.ui.auth.accountmenu.implement;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXProgressDialog;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.request.NXToyCreateMGTokenForGcidRequest;
import kr.co.nexon.npaccount.auth.result.NXToyCreateMGTokenForGcidResult;
import kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState;
import kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuSelectPlatformView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuSelectPlatformState implements NXPAccountMenuState {
    private NXPAccountMenuSelectPlatformView selectPlatformView;

    /* JADX INFO: Access modifiers changed from: private */
    public void createMigrationCode(final NXPAccountMenuDialog nXPAccountMenuDialog, final Activity activity) {
        final NXProgressDialog nXProgressDialog = new NXProgressDialog(activity);
        NXToyRequestListener nXToyRequestListener = new NXToyRequestListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuSelectPlatformState.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuSelectPlatformState.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        nXProgressDialog.dismiss();
                        if (nXToyResult.errorCode != 0) {
                            nXPAccountMenuDialog.changeState(new NXPAccountMenuMessageState(1, NXToyLocaleManager.getInstance(activity.getApplicationContext()).getString(R.string.npres_account_menu_receive_link_other_device_fail_already_add)));
                        } else {
                            nXPAccountMenuDialog.changeState(new NXPAccountMenuGenerateCodeState(((NXToyCreateMGTokenForGcidResult) nXToyResult).result.mgToken));
                        }
                    }
                });
            }
        };
        nXProgressDialog.show();
        NXToyRequestPostman.getInstance().postRequest(new NXToyCreateMGTokenForGcidRequest(), nXToyRequestListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackButtonPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        nXPAccountMenuDialog.changeState(new NXPAccountMenuLinkState());
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public View createView(final NXPAccountMenuDialog nXPAccountMenuDialog) {
        final Activity activity = nXPAccountMenuDialog.getActivity();
        final Context applicationContext = activity.getApplicationContext();
        this.selectPlatformView = (NXPAccountMenuSelectPlatformView) ((LayoutInflater) applicationContext.getSystemService("layout_inflater")).inflate(R.layout.nxp_account_menu_select_platform, (ViewGroup) null);
        this.selectPlatformView.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuSelectPlatformState.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.backBtn) {
                    NXPAccountMenuSelectPlatformState.this.onBackButtonPressed(nXPAccountMenuDialog);
                    return;
                }
                if (id == R.id.account_menu_select_android_link_other_device_btn) {
                    nXPAccountMenuDialog.changeState(new NXPAccountMenuMessageState(1, NXToyLocaleManager.getInstance(applicationContext).getString(R.string.npres_account_menu_link_other_device_os_select_fail)));
                } else if (id == R.id.account_menu_select_ios_link_other_device_btn) {
                    NXPAccountMenuSelectPlatformState.this.createMigrationCode(nXPAccountMenuDialog, activity);
                }
            }
        });
        return this.selectPlatformView;
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void onBackPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        onBackButtonPressed(nXPAccountMenuDialog);
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void setCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.selectPlatformView.setCloseButtonClickListener(onClickListener);
    }
}
