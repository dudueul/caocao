package kr.co.nexon.toy.android.ui.auth.accountmenu.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuSelectPlatformView extends NXPLinearLayout implements NXPAccountMenuOnClickListener {
    private View.OnClickListener buttonClickListener;
    private View.OnClickListener closeButtonClickListener;
    private NXClickListener onSwallowClickListener;

    public NXPAccountMenuSelectPlatformView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuSelectPlatformView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuSelectPlatformView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuSelectPlatformView.this.buttonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPAccountMenuSelectPlatformView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuSelectPlatformView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuSelectPlatformView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuSelectPlatformView.this.buttonClickListener.onClick(view);
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
        Button button = (Button) findViewById(R.id.account_menu_select_android_link_other_device_btn);
        if (button != null) {
            button.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_link_other_device_os_android));
            button.setOnClickListener(this.onSwallowClickListener);
        }
        Button button2 = (Button) findViewById(R.id.account_menu_select_ios_link_other_device_btn);
        if (button2 != null) {
            button2.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_link_other_device_os_ios));
            button2.setOnClickListener(this.onSwallowClickListener);
        }
        TextView textView2 = (TextView) findViewById(R.id.account_menu_select_os_link_other_device_desc);
        if (textView2 != null) {
            textView2.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_link_other_device_os_select_desc));
        }
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

    @Override // android.view.View, kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.buttonClickListener = onClickListener;
    }
}
