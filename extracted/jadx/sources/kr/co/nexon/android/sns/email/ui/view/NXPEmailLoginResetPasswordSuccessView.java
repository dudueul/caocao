package kr.co.nexon.android.sns.email.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPRelativeLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPEmailLoginResetPasswordSuccessView extends NXPRelativeLayout {
    private ImageButton backButton;
    private ImageButton closeButton;
    private Button goBackButton;
    private TextView mainMessageTextView;
    private TextView messageAreaTextView;
    private TextView titleTextView;

    public NXPEmailLoginResetPasswordSuccessView(Context context) {
        super(context);
    }

    public NXPEmailLoginResetPasswordSuccessView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPEmailLoginResetPasswordSuccessView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.login_title);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        this.backButton = (ImageButton) findViewById(R.id.backBtn);
        this.mainMessageTextView = (TextView) findViewById(R.id.tvMainMessage);
        this.messageAreaTextView = (TextView) findViewById(R.id.tvMessageArea);
        this.goBackButton = (Button) findViewById(R.id.btGoBack);
    }

    public void setGoBackButton(String str, View.OnClickListener onClickListener) {
        if (this.goBackButton != null) {
            this.goBackButton.setText(str);
            this.goBackButton.setOnClickListener(onClickListener);
        }
    }

    public void setMainMessage(String str) {
        if (this.mainMessageTextView != null) {
            this.mainMessageTextView.setText(str);
        }
    }

    public void setOnBackButtonClickListener(View.OnClickListener onClickListener) {
        if (this.backButton != null) {
            this.backButton.setOnClickListener(onClickListener);
        }
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        if (this.closeButton != null) {
            this.closeButton.setOnClickListener(onClickListener);
        }
    }

    public void setSubMessage(String str) {
        if (this.messageAreaTextView != null) {
            this.messageAreaTextView.setText(str);
        }
    }

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
