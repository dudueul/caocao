package kr.co.nexon.toy.android.ui.auth.accountmenu.implement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState;
import kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuLinkView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuLinkState implements NXPAccountMenuState {
    private NXPAccountMenuLinkView linkView;

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackButtonPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        nXPAccountMenuDialog.changeState(new NXPAccountMenuInitialState());
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public View createView(final NXPAccountMenuDialog nXPAccountMenuDialog) {
        this.linkView = (NXPAccountMenuLinkView) ((LayoutInflater) nXPAccountMenuDialog.getActivity().getApplicationContext().getSystemService("layout_inflater")).inflate(R.layout.nxp_account_menu_link, (ViewGroup) null);
        this.linkView.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuLinkState.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.backBtn) {
                    NXPAccountMenuLinkState.this.onBackButtonPressed(nXPAccountMenuDialog);
                    return;
                }
                if (id == R.id.account_menu_select_link_other_device_code_btn) {
                    nXPAccountMenuDialog.changeState(new NXPAccountMenuSelectPlatformState());
                    return;
                }
                if (id == R.id.account_menu_select_receive_code_link_other_device_btn) {
                    if (!NXStringUtil.isNotNull(NXToySessionManager.getInstance().getSession().getAgcId())) {
                        nXPAccountMenuDialog.changeState(new NXPAccountMenuConfirmCodeState());
                    } else {
                        nXPAccountMenuDialog.changeState(new NXPAccountMenuMessageState(0, NXToyLocaleManager.getInstance().getString(R.string.npres_account_menu_receive_link_other_device_fail_already_add)));
                    }
                }
            }
        });
        return this.linkView;
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void onBackPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        onBackButtonPressed(nXPAccountMenuDialog);
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void setCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.linkView.setCloseButtonClickListener(onClickListener);
    }
}
