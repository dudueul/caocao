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
public class NXPAccountMenuMessageView extends NXPLinearLayout implements NXPAccountMenuOnClickListener {
    private View.OnClickListener buttonClickListener;
    private View.OnClickListener closeButtonClickListener;
    private TextView message;
    private NXClickListener onSwallowClickListener;

    public NXPAccountMenuMessageView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuMessageView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuMessageView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuMessageView.this.buttonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPAccountMenuMessageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuMessageView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    NXPAccountMenuMessageView.this.closeButtonClickListener.onClick(view);
                } else {
                    NXPAccountMenuMessageView.this.buttonClickListener.onClick(view);
                }
            }
        };
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance();
        TextView textView = (TextView) findViewById(R.id.title);
        if (textView != null) {
            textView.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_add_device_btn));
        }
        this.message = (TextView) findViewById(R.id.account_menu_message_text_btn_desc);
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

    public void setMessage(String str) {
        if (this.message != null) {
            this.message.setText(str);
        }
    }

    @Override // android.view.View, kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.buttonClickListener = onClickListener;
    }
}
