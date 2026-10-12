package kr.co.nexon.toy.android.ui.auth.accountmenu.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.List;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener;
import kr.co.nexon.toy.android.ui.auth.util.NPLoginUIUtil;
import kr.co.nexon.toy.android.ui.common.NPScrollView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuChangeView extends NXPLinearLayout implements NXPAccountMenuOnClickListener {
    private List<Button> accountButtonList;
    private TextView accountMenuLinkAccountDesc;
    private View.OnClickListener buttonClickListener;
    private View.OnClickListener closeButtonClickListener;
    private NXClickListener onSwallowClickListener;

    public NXPAccountMenuChangeView(Context context) {
        super(context);
        this.accountButtonList = new ArrayList();
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuChangeView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuChangeView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuChangeView.this.buttonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPAccountMenuChangeView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.accountButtonList = new ArrayList();
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuChangeView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuChangeView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuChangeView.this.buttonClickListener.onClick(view);
                }
            }
        };
    }

    private Button createAccountButton(int i) {
        Button button = (Button) LayoutInflater.from(getContext()).inflate(R.layout.nxp_login_select_view_button, (ViewGroup) null);
        int loginSelectorResourceId = NPLoginUIUtil.getLoginSelectorResourceId(i);
        if (loginSelectorResourceId == -1) {
            return null;
        }
        button.setBackgroundResource(loginSelectorResourceId);
        button.setPadding(0, 0, 0, 0);
        button.setText(NPLoginUIUtil.getLoginButtonDisplayText(i));
        button.setTag(Integer.valueOf(i));
        button.setOnClickListener(this.onSwallowClickListener);
        return button;
    }

    private void drawLinear() {
        if (this.accountButtonList.size() >= 4) {
            ((NPScrollView) findViewById(R.id.login_select_scrollview)).setScrollEnabled(true);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.account_menu_login_icon);
        for (int i = 0; i < this.accountButtonList.size(); i++) {
            linearLayout.addView(this.accountButtonList.get(i));
        }
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.accountMenuLinkAccountDesc = (TextView) findViewById(R.id.account_menu_link_account_desc);
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(this.onSwallowClickListener);
        }
        View viewFindViewById2 = findViewById(R.id.closeBtn);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(this.onSwallowClickListener);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener
    public void setCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    public void setLoginType(int i) {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(getContext());
        TextView textView = (TextView) findViewById(R.id.title);
        if (i == NPAccount.LoginTypeGuest) {
            textView.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_link_account_btn));
        } else {
            textView.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_change_account_btn));
        }
        String string = (i != NPAccount.LoginTypeGameCenter && i == NPAccount.LoginTypeGuest) ? nXToyLocaleManager.getString(R.string.npres_account_menu_link_account_guest) : nXToyLocaleManager.getString(R.string.npres_account_menu_link_account_common);
        this.accountMenuLinkAccountDesc.setText(string);
    }

    public void setMembershipList(List<Integer> list) {
        ToyLog.d("setMembershipList :" + list);
        if (list == null) {
            ToyLog.d("membershipList is null");
            return;
        }
        this.accountButtonList.clear();
        for (int i = 0; i < list.size(); i++) {
            Button buttonCreateAccountButton = createAccountButton(list.get(i).intValue());
            if (buttonCreateAccountButton != null) {
                this.accountButtonList.add(buttonCreateAccountButton);
            }
        }
        drawLinear();
    }

    @Override // android.view.View, kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.buttonClickListener = onClickListener;
    }
}
