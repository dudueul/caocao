package kr.co.nexon.toy.android.ui.backup;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.request.NXToyMigrationRequest;
import kr.co.nexon.npaccount.auth.request.NXToyValidateMGTokenRequest;
import kr.co.nexon.npaccount.auth.result.NXToyMigrationResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.backup.view.NXPDataRestoreView;
import kr.co.nexon.toy.android.ui.common.NXPFullAlertDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPDataRestoreDialog extends NPDialogBase {
    public static final String KEY_TITLE = "title";
    public static final String TAG = "NPDataRestoreDialog";
    private NPListener dataRestoreCallback = new NPListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.6
        @Override // kr.co.nexon.toy.listener.NPListener
        public void onResult(final NXToyResult nXToyResult) {
            new NXPFullAlertDialog.FullBuilder(NPDataRestoreDialog.this.getActivity()).setTitle(NPDataRestoreDialog.this.getTitle()).setMessage(nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode() ? NPDataRestoreDialog.this.localeManager.getStringEx(R.string.npres_restore_restore_success, "") : nXToyResult.errorText).setPositiveButton(NPDataRestoreDialog.this.localeManager.getStringEx(R.string.confirm, ""), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.6.2
                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                protected void onSwallowClick(View view) {
                    NPDataRestoreDialog.this.dispatchDataRestoreCallback(nXToyResult);
                    NPDataRestoreDialog.this.dismiss();
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.6.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    NPDataRestoreDialog.this.dispatchDataRestoreCallback(nXToyResult);
                    NPDataRestoreDialog.this.dismiss();
                }
            }).show();
        }
    };
    private NXPDataRestoreView dataRestoreView;
    private NXToyLocaleManager localeManager;
    private NPListener resultListener;
    private String title;

    /* JADX INFO: Access modifiers changed from: private */
    public void dataRestoreByCode(final String str, final NPListener nPListener) {
        NPAccount nPAccount = NPAccount.getInstance(getActivity());
        final NXToySessionManager nXToySessionManager = NXToySessionManager.getInstance();
        final Runnable runnable = new Runnable() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.4
            @Override // java.lang.Runnable
            public void run() {
                NXToyRequestPostman.getInstance().postRequest(new NXToyMigrationRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.4.1
                    @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                    public void onComplete(NXToyResult nXToyResult) {
                        if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                            NXToyMigrationResult nXToyMigrationResult = (NXToyMigrationResult) nXToyResult;
                            NXToySession session = nXToySessionManager.getSession();
                            session.setNpsn(nXToyMigrationResult.result.npsn);
                            session.setNPToken(nXToyMigrationResult.result.npToken);
                            session.setType(NXToyLoginType.LoginTypeGuest.getValue());
                            nXToySessionManager.saveSession();
                        }
                        if (nPListener != null) {
                            nPListener.onResult(nXToyResult);
                        }
                    }
                });
            }
        };
        if (nPAccount.getLoginType() == NXToyLoginType.LoginTypeGuest.getValue()) {
            nPAccount.logout(new NPListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.5
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                        runnable.run();
                    } else if (nPListener != null) {
                        nPListener.onResult(nXToyResult);
                    }
                }
            });
        } else {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchDataRestoreCallback(NXToyResult nXToyResult) {
        if (this.resultListener != null) {
            nXToyResult.requestTag = NXToyRequestTag.DataRestore.getValue();
            this.resultListener.onResult(nXToyResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTitle() {
        if (NXStringUtil.isNotNull(this.title)) {
            return this.title;
        }
        this.title = this.localeManager.getStringEx(R.string.npres_data_restore_title, "");
        return this.title;
    }

    public static NPDataRestoreDialog newInstance(Activity activity, String str) {
        NPDataRestoreDialog nPDataRestoreDialog = new NPDataRestoreDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("title", str);
        nPDataRestoreDialog.setArguments(bundle);
        return nPDataRestoreDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestDataRestore(final String str) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyValidateMGTokenRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.3
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NPDataRestoreDialog.this.dataRestoreView.displayErrorText(nXToyResult.errorText);
                    return;
                }
                String stringEx = NPDataRestoreDialog.this.localeManager.getStringEx(R.string.npres_restore_restore, "");
                String stringEx2 = NPDataRestoreDialog.this.localeManager.getStringEx(R.string.npres_restore, "");
                new NXPFullAlertDialog.FullBuilder(NPDataRestoreDialog.this.getActivity()).setTitle(NPDataRestoreDialog.this.getTitle()).setMessage(stringEx).setPositiveButton(stringEx2, new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.3.1
                    @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                    protected void onSwallowClick(View view) {
                        NPDataRestoreDialog.this.dataRestoreByCode(str, NPDataRestoreDialog.this.dataRestoreCallback);
                    }
                }).setNegativeButton(NPDataRestoreDialog.this.localeManager.getStringEx(R.string.npres_cancel, ""), null).show();
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.dataRestoreView = (NXPDataRestoreView) View.inflate(this.applicationContext, R.layout.nxp_data_restore_view, null);
        this.title = getArguments().getString("title", "");
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        this.dataRestoreView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPDataRestoreDialog.this.onBackPressed();
            }
        });
        this.dataRestoreView.setOnRestoreButtonClickListener(new NXPDataRestoreView.RestoreButtonClickListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog.2
            @Override // kr.co.nexon.toy.android.ui.backup.view.NXPDataRestoreView.RestoreButtonClickListener
            public void onClickRestoreButton(View view, String str) {
                NPDataRestoreDialog.this.requestDataRestore(str);
            }
        });
        this.dataRestoreView.setTitle(getTitle());
        this.dataRestoreView.setDescriptionText(this.localeManager.getStringEx(R.string.npres_restore_description_code, ""));
        this.dataRestoreView.setRestoreButtonText(this.localeManager.getStringEx(R.string.npres_restore, ""));
        return this.dataRestoreView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        String stringEx = this.localeManager.getStringEx(R.string.npres_cancel, "");
        NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.RESTORE_CANCEL.getCode(), stringEx, stringEx);
        nXToyResult.requestTag = NXToyRequestTag.DataRestore.getValue();
        dispatchDataRestoreCallback(nXToyResult);
        super.onBackPressed();
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }
}
