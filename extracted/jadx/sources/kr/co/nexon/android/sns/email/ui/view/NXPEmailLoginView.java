package kr.co.nexon.android.sns.email.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.NXPRelativeLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPEmailLoginView extends NXPRelativeLayout {
    private ImageButton backButton;
    private ImageButton closeButton;
    private TextView emailIDTextView;
    private TextView forgotButton;
    private Button loginButton;
    private NXInputEditView passwordInputEditText;
    private TextView titleTextView;

    public NXPEmailLoginView(Context context) {
        super(context);
    }

    public NXPEmailLoginView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPEmailLoginView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public String getPasswordText() {
        return this.passwordInputEditText != null ? this.passwordInputEditText.getText() : "";
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.login_title);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        this.backButton = (ImageButton) findViewById(R.id.backBtn);
        this.emailIDTextView = (TextView) findViewById(R.id.tvEmail);
        this.passwordInputEditText = (NXInputEditView) findViewById(R.id.etPasswordInput);
        this.passwordInputEditText.setImeOption(-1879048186);
        this.passwordInputEditText.setInputType(128);
        this.passwordInputEditText.setTransformationMethod();
        this.passwordInputEditText.setFocus();
        this.loginButton = (Button) findViewById(R.id.btLogin);
        this.forgotButton = (TextView) findViewById(R.id.forgot_btn);
    }

    public void setEditHint(String str) {
        if (this.passwordInputEditText != null) {
            this.passwordInputEditText.setHint(str);
        }
    }

    public void setEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        if (this.passwordInputEditText != null) {
            this.passwordInputEditText.setEditorActionListener(onEditorActionListener);
        }
    }

    public void setErrorMessage(String str) {
        if (this.passwordInputEditText != null) {
            this.passwordInputEditText.setErrorMessage(str);
        }
    }

    public void setForgotButton(String str, View.OnClickListener onClickListener) {
        if (this.forgotButton != null) {
            this.forgotButton.setText(str);
            this.forgotButton.setOnClickListener(onClickListener);
        }
    }

    public void setIdText(String str) {
        if (this.emailIDTextView != null) {
            this.emailIDTextView.setText(str);
        }
    }

    public void setLoginButton(String str, View.OnClickListener onClickListener) {
        if (this.loginButton != null) {
            this.loginButton.setText(str);
            this.loginButton.setOnClickListener(onClickListener);
        }
    }

    public void setLoginButtonEnabled(boolean z) {
        if (this.loginButton != null) {
            this.loginButton.setEnabled(z);
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

    public void setPasswordText(String str) {
        if (this.passwordInputEditText != null) {
            this.passwordInputEditText.setText(str);
        }
    }

    public void setPasswordTextChangeListener(NXInputEditView.NPTextChangedListener nPTextChangedListener) {
        if (this.passwordInputEditText != null) {
            this.passwordInputEditText.setListener(nPTextChangedListener);
        }
    }

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
