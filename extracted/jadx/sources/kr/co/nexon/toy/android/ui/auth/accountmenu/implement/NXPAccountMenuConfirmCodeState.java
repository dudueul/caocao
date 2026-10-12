package kr.co.nexon.toy.android.ui.auth.accountmenu.implement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.request.NXToyMigrationForGcidRequest;
import kr.co.nexon.npaccount.auth.request.NXToyValidateMGTokenRequest;
import kr.co.nexon.npaccount.auth.result.NXToyMigrationForGcidResult;
import kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState;
import kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuConfirmCodeView;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuConfirmCodeState implements NXPAccountMenuState {
    private NXPAccountMenuConfirmCodeView confirmCodeView;
    private NXPAccountMenuDialog dialog;

    /* JADX INFO: Access modifiers changed from: private */
    public void migrationForGcid(String str) {
        final NXToySessionManager nXToySessionManager = NXToySessionManager.getInstance();
        NXToyRequestListener nXToyRequestListener = new NXToyRequestListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuConfirmCodeState.4
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == 0) {
                    NXToyMigrationForGcidResult nXToyMigrationForGcidResult = (NXToyMigrationForGcidResult) nXToyResult;
                    NXToySession session = nXToySessionManager.getSession();
                    session.setNpsn(nXToyMigrationForGcidResult.result.npsn);
                    session.setNPToken(nXToyMigrationForGcidResult.result.npToken);
                    session.setType(NXToyLoginType.LoginTypeGameCenter.getValue());
                    session.setGpgId(session.getGcId());
                    session.setGcId(session.getGcId());
                }
                if (NXPAccountMenuConfirmCodeState.this.dialog != null) {
                    NXPAccountMenuConfirmCodeState.this.dialog.getToyResultListener().onResult(nXToyResult);
                    NXPAccountMenuConfirmCodeState.this.dialog.dismiss();
                }
            }
        };
        NXToyRequestPostman.getInstance().postRequest(new NXToyMigrationForGcidRequest(str, nXToySessionManager.getSession().getGcId()), nXToyRequestListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackButtonPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        nXPAccountMenuDialog.changeState(new NXPAccountMenuLinkState());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConfirmDialog(final Activity activity, final String str) {
        final NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(activity.getApplicationContext());
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuConfirmCodeState.3
            @Override // java.lang.Runnable
            public void run() {
                NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(activity);
                String string = nXToyLocaleManager.getString(R.string.npres_account_menu_receive_link_other_device_check_desc);
                String string2 = nXToyLocaleManager.getString(R.string.confirm);
                String string3 = nXToyLocaleManager.getString(R.string.npres_cancel);
                builder.setMessage(string);
                builder.setPositiveButton(string2, new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuConfirmCodeState.3.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NXPAccountMenuConfirmCodeState.this.migrationForGcid(str);
                    }
                });
                builder.setNegativeButton(string3, new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuConfirmCodeState.3.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                    }
                });
                builder.create().show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateMGTokenForGcid(final Activity activity, final String str) {
        NXToyRequestListener nXToyRequestListener = new NXToyRequestListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuConfirmCodeState.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == 0) {
                    NXPAccountMenuConfirmCodeState.this.showConfirmDialog(activity, str);
                } else {
                    NXPAccountMenuConfirmCodeState.this.confirmCodeView.setErrorText(nXToyResult.errorText);
                }
            }
        };
        NXToyRequestPostman.getInstance().postRequest(new NXToyValidateMGTokenRequest(str), nXToyRequestListener);
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public View createView(final NXPAccountMenuDialog nXPAccountMenuDialog) {
        this.dialog = nXPAccountMenuDialog;
        final Activity activity = nXPAccountMenuDialog.getActivity();
        this.confirmCodeView = (NXPAccountMenuConfirmCodeView) ((LayoutInflater) activity.getApplicationContext().getSystemService("layout_inflater")).inflate(R.layout.nxp_account_menu_confirm_code, (ViewGroup) null);
        this.confirmCodeView.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuConfirmCodeState.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.backBtn) {
                    NXPAccountMenuConfirmCodeState.this.onBackButtonPressed(nXPAccountMenuDialog);
                } else if (id == R.id.account_menu_link_other_device_code_btn) {
                    NXPAccountMenuConfirmCodeState.this.confirmCodeView.setErrorDescriptionVisibility(8);
                    NXPAccountMenuConfirmCodeState.this.validateMGTokenForGcid(activity, NXPAccountMenuConfirmCodeState.this.confirmCodeView.getLinkCode());
                }
            }
        });
        return this.confirmCodeView;
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void onBackPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        onBackButtonPressed(nXPAccountMenuDialog);
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void setCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.confirmCodeView.setCloseButtonClickListener(onClickListener);
    }
}
