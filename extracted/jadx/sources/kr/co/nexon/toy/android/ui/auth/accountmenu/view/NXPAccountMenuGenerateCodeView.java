package kr.co.nexon.toy.android.ui.auth.accountmenu.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuGenerateCodeView extends NXPLinearLayout implements NXPAccountMenuOnClickListener {
    private View.OnClickListener buttonClickListener;
    private View.OnClickListener closeButtonClickListener;
    private NXClickListener onSwallowClickListener;
    private TextView receiveCodeLinkOtherDeviceCode;
    private TextView receiveCodeLinkOtherDeviceDescription;

    public NXPAccountMenuGenerateCodeView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuGenerateCodeView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuGenerateCodeView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuGenerateCodeView.this.buttonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPAccountMenuGenerateCodeView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuGenerateCodeView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuGenerateCodeView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuGenerateCodeView.this.buttonClickListener.onClick(view);
                }
            }
        };
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(getContext());
        TextView textView = (TextView) findViewById(R.id.title);
        if (textView != null) {
            textView.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_add_device_btn));
        }
        this.receiveCodeLinkOtherDeviceDescription = (TextView) findViewById(R.id.account_menu_receive_code_link_other_device_desc);
        if (this.receiveCodeLinkOtherDeviceDescription != null) {
            this.receiveCodeLinkOtherDeviceDescription.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_link_other_device_code_desc));
        }
        this.receiveCodeLinkOtherDeviceCode = (TextView) findViewById(R.id.account_menu_receive_code_link_other_device);
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

    public void setMigrationCode(String str) {
        String str2 = str.substring(0, 4) + " " + str.substring(4, 8) + " " + str.substring(8, 12) + " " + str.substring(12, 16);
        if (this.receiveCodeLinkOtherDeviceCode != null) {
            this.receiveCodeLinkOtherDeviceCode.setText(str2);
        }
    }

    @Override // android.view.View, kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.buttonClickListener = onClickListener;
    }
}
