package kr.co.nexon.toy.android.ui.auth.accountmenu.implement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nexon.npaccount.R;
import kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState;
import kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuGenerateCodeView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuGenerateCodeState implements NXPAccountMenuState {
    NXPAccountMenuGenerateCodeView generateCodeView;
    String migrationCode;

    public NXPAccountMenuGenerateCodeState(String str) {
        this.migrationCode = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackButtonPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        nXPAccountMenuDialog.changeState(new NXPAccountMenuSelectPlatformState());
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public View createView(final NXPAccountMenuDialog nXPAccountMenuDialog) {
        this.generateCodeView = (NXPAccountMenuGenerateCodeView) ((LayoutInflater) nXPAccountMenuDialog.getActivity().getApplicationContext().getSystemService("layout_inflater")).inflate(R.layout.nxp_account_menu_generate_code, (ViewGroup) null);
        this.generateCodeView.setMigrationCode(this.migrationCode);
        this.generateCodeView.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuGenerateCodeState.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                NXPAccountMenuGenerateCodeState.this.onBackButtonPressed(nXPAccountMenuDialog);
                if (id == R.id.backBtn) {
                    nXPAccountMenuDialog.changeState(new NXPAccountMenuSelectPlatformState());
                }
            }
        });
        return this.generateCodeView;
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void onBackPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        onBackButtonPressed(nXPAccountMenuDialog);
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void setCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.generateCodeView.setCloseButtonClickListener(onClickListener);
    }
}
