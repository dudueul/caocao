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
public class NXPEmailLoginResetPasswordView extends NXPRelativeLayout {
    private ImageButton backButton;
    private ImageButton closeButton;
    private TextView emailTextView;
    private Button resetPasswordButton;
    private TextView titleTextView;

    public NXPEmailLoginResetPasswordView(Context context) {
        super(context);
    }

    public NXPEmailLoginResetPasswordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPEmailLoginResetPasswordView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CharSequence getEmail() {
        return this.emailTextView != null ? this.emailTextView.getText() : "";
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.login_title);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        this.backButton = (ImageButton) findViewById(R.id.backBtn);
        this.emailTextView = (TextView) findViewById(R.id.tvEmail);
        this.resetPasswordButton = (Button) findViewById(R.id.btResetPasswd);
    }

    public void setEmail(CharSequence charSequence) {
        if (this.emailTextView != null) {
            this.emailTextView.setText(charSequence);
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

    public void setResetPasswordButton(String str, View.OnClickListener onClickListener) {
        if (this.resetPasswordButton != null) {
            this.resetPasswordButton.setText(str);
            this.resetPasswordButton.setOnClickListener(onClickListener);
        }
    }

    public void setResetPasswordButtonEnabled(boolean z) {
        if (this.resetPasswordButton != null) {
            this.resetPasswordButton.setEnabled(z);
        }
    }

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
