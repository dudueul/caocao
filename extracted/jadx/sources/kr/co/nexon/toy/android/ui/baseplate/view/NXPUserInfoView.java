package kr.co.nexon.toy.android.ui.baseplate.view;

import android.content.Context;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.toy.android.ui.auth.util.NPLoginUIUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPUserInfoView extends NXPLinearLayout {
    private ImageButton closeButton;
    private ImageView loginTypeImageView;
    private TextView titleTextView;
    private TextView userNameTextView;

    public NXPUserInfoView(Context context) {
        super(context);
    }

    public NXPUserInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPUserInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(4);
        }
        this.userNameTextView = (TextView) findViewById(R.id.login_text);
        this.loginTypeImageView = (ImageView) findViewById(R.id.login_type_icon);
    }

    public void setLoginTypeIcon(int i) {
        int iLoginIconType;
        if (this.loginTypeImageView == null || (iLoginIconType = NPLoginUIUtil.loginIconType(i)) == -1) {
            return;
        }
        this.loginTypeImageView.setImageResource(iLoginIconType);
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        if (this.closeButton != null) {
            this.closeButton.setOnClickListener(onClickListener);
        }
    }

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }

    public void setUserNameText(Spannable spannable) {
        if (this.userNameTextView != null) {
            this.userNameTextView.setText(spannable);
        }
    }
}
