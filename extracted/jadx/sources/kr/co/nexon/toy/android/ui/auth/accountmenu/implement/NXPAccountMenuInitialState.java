package kr.co.nexon.toy.android.ui.auth.accountmenu.implement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState;
import kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuInitialView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuInitialState implements NXPAccountMenuState {
    private NXPAccountMenuInitialView initalView;

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public View createView(final NXPAccountMenuDialog nXPAccountMenuDialog) {
        Activity activity = nXPAccountMenuDialog.getActivity();
        this.initalView = (NXPAccountMenuInitialView) ((LayoutInflater) activity.getApplicationContext().getSystemService("layout_inflater")).inflate(R.layout.nxp_account_menu_inital, (ViewGroup) null);
        this.initalView.findViewById(R.id.backBtn).setVisibility(4);
        this.initalView.setLoginType(NPAccount.getInstance(activity).getLoginType());
        this.initalView.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuInitialState.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.account_menu_link_other_device_btn) {
                    nXPAccountMenuDialog.changeState(new NXPAccountMenuLinkState());
                } else if (id == R.id.account_menu_gcid_link_account_btn || id == R.id.account_menu_etc_link_account_btn) {
                    nXPAccountMenuDialog.changeState(new NXPAccountMenuChangeState());
                }
            }
        });
        return this.initalView;
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void onBackPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        nXPAccountMenuDialog.dismissDialog();
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void setCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.initalView.setCloseButtonClickListener(onClickListener);
    }
}
