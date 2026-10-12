package kr.co.nexon.toy.android.ui.auth.accountmenu.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener;
import kr.co.nexon.toy.android.ui.auth.util.NPLoginUIUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuInitialView extends NXPLinearLayout implements NXPAccountMenuOnClickListener {
    private TextView accountMenuDescription;
    private View.OnClickListener buttonClickListener;
    private View.OnClickListener closeButtonClickListener;
    private LinearLayout linearLayout;
    private Button linkAccountButton;
    private Button linkOtherDeviceButton;
    private NXToyLocaleManager localeManager;
    private ImageView loginTypeIcon;
    private NXClickListener onSwallowClickListener;

    public NXPAccountMenuInitialView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuInitialView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuInitialView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuInitialView.this.buttonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPAccountMenuInitialView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuInitialView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuInitialView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuInitialView.this.buttonClickListener.onClick(view);
                }
            }
        };
    }

    private Spanned fromHtml(String str) {
        return Build.VERSION.SDK_INT >= 24 ? Html.fromHtml(str, 0) : Html.fromHtml(str);
    }

    private String getLocaleString(int i) {
        if (this.localeManager == null) {
            this.localeManager = NXToyLocaleManager.getInstance();
        }
        return this.localeManager.getString(i);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.loginTypeIcon = (ImageView) findViewById(R.id.account_menu_current_login_type);
        this.accountMenuDescription = (TextView) findViewById(R.id.account_menu_desc);
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(4);
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
        String str = String.format(getLocaleString(R.string.npres_account_menu_desc), NPLoginUIUtil.getNameFromLoginType(i));
        TextView textView = (TextView) findViewById(R.id.title);
        if (textView != null) {
            if (i == NPAccount.LoginTypeGuest) {
                str = str + "<br>" + getLocaleString(R.string.npres_account_menu_desc_guset);
                textView.setText(getLocaleString(R.string.npres_account_menu_link_account_btn));
            } else {
                textView.setText(getLocaleString(R.string.npres_account_menu_change_account_btn));
            }
        }
        Spanned spannedFromHtml = fromHtml(str);
        this.loginTypeIcon.setImageResource(NPLoginUIUtil.loginIconType(i));
        if (i == NPAccount.LoginTypeGameCenter) {
            this.linearLayout = (LinearLayout) findViewById(R.id.account_menu_gcid);
            this.linkAccountButton = (Button) findViewById(R.id.account_menu_gcid_link_account_btn);
            this.linkOtherDeviceButton = (Button) findViewById(R.id.account_menu_link_other_device_btn);
            if (this.linearLayout != null) {
                this.linearLayout.setVisibility(0);
            }
            if (this.accountMenuDescription != null) {
                this.accountMenuDescription.setText(spannedFromHtml);
            }
            if (this.linkAccountButton != null) {
                this.linkAccountButton.setText(getLocaleString(R.string.npres_account_menu_change_account_btn));
            }
            if (this.linkOtherDeviceButton != null) {
                this.linkOtherDeviceButton.setText(getLocaleString(R.string.npres_account_menu_add_device_btn));
            }
            if (this.linkOtherDeviceButton != null) {
                this.linkOtherDeviceButton.setOnClickListener(this.onSwallowClickListener);
            }
        } else {
            this.linearLayout = (LinearLayout) findViewById(R.id.account_menu_etc);
            this.linkAccountButton = (Button) findViewById(R.id.account_menu_etc_link_account_btn);
            if (this.linearLayout != null) {
                this.linearLayout.setVisibility(0);
            }
            if (this.accountMenuDescription != null) {
                this.accountMenuDescription.setText(spannedFromHtml);
            }
            if (this.linkAccountButton != null) {
                if (i == NPAccount.LoginTypeGuest) {
                    this.linkAccountButton.setText(getLocaleString(R.string.npres_account_menu_link_account_btn));
                } else {
                    this.linkAccountButton.setText(getLocaleString(R.string.npres_account_menu_change_account_btn));
                }
            }
        }
        if (this.linkAccountButton != null) {
            this.linkAccountButton.setOnClickListener(this.onSwallowClickListener);
        }
    }

    @Override // android.view.View, kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.buttonClickListener = onClickListener;
    }
}
