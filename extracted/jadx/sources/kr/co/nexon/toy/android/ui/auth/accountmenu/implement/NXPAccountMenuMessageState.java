package kr.co.nexon.toy.android.ui.auth.accountmenu.implement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nexon.npaccount.R;
import kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState;
import kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuMessageView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuMessageState implements NXPAccountMenuState {
    public static final int RETURN_LINK_STATE = 0;
    public static final int RETURN_PLATFORM_SELECT_STATE = 1;
    private String message;
    private NXPAccountMenuMessageView messageView;
    private int resultDialogType;

    public NXPAccountMenuMessageState(int i, String str) {
        this.resultDialogType = i;
        this.message = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackButtonPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        if (this.resultDialogType == 0) {
            nXPAccountMenuDialog.changeState(new NXPAccountMenuLinkState());
        } else if (this.resultDialogType == 1) {
            nXPAccountMenuDialog.changeState(new NXPAccountMenuSelectPlatformState());
        }
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public View createView(final NXPAccountMenuDialog nXPAccountMenuDialog) {
        this.messageView = (NXPAccountMenuMessageView) ((LayoutInflater) nXPAccountMenuDialog.getActivity().getApplicationContext().getSystemService("layout_inflater")).inflate(R.layout.nxp_account_menu_message, (ViewGroup) null);
        this.messageView.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuMessageState.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getId() == R.id.backBtn) {
                    NXPAccountMenuMessageState.this.onBackButtonPressed(nXPAccountMenuDialog);
                }
            }
        });
        this.messageView.setMessage(this.message);
        return this.messageView;
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void onBackPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        onBackButtonPressed(nXPAccountMenuDialog);
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void setCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.messageView.setCloseButtonClickListener(onClickListener);
    }
}
