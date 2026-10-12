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
public class NXPEmailLoginSignUpView extends NXPRelativeLayout {
    private ImageButton backButton;
    private ImageButton closeButton;
    private TextView descriptionText;
    private TextView emailIDTextView;
    private View midContainer;
    private NXInputEditView passwordInputEditText;
    private Button signUpButton;
    private TextView termsPolicyTextView;
    private TextView titleTextView;
    private View topContainer;

    public NXPEmailLoginSignUpView(Context context) {
        super(context);
    }

    public NXPEmailLoginSignUpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPEmailLoginSignUpView(Context context, AttributeSet attributeSet, int i) {
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
        this.topContainer = findViewById(R.id.topContainer);
        this.midContainer = findViewById(R.id.midContainer);
        this.descriptionText = (TextView) findViewById(R.id.desc_text);
        this.emailIDTextView = (TextView) findViewById(R.id.tvEmail);
        this.passwordInputEditText = (NXInputEditView) findViewById(R.id.etPasswordInput);
        this.passwordInputEditText.setImeOption(-1879048186);
        this.passwordInputEditText.setInputType(128);
        this.passwordInputEditText.setTransformationMethod();
        this.passwordInputEditText.setFocus();
        this.signUpButton = (Button) findViewById(R.id.btSignUp);
        this.termsPolicyTextView = (TextView) findViewById(R.id.tvTermsPolicy);
    }

    public void setDescriptionText(String str) {
        if (this.descriptionText != null) {
            this.descriptionText.setText(str);
        }
    }

    public void setEditHint(String str) {
        if (this.passwordInputEditText != null) {
            this.passwordInputEditText.setHint(str);
        }
    }

    public void setEditListener(NXInputEditView.NPTextChangedListener nPTextChangedListener) {
        if (this.passwordInputEditText != null) {
            this.passwordInputEditText.setListener(nPTextChangedListener);
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

    public void setIdText(String str) {
        if (this.emailIDTextView != null) {
            this.emailIDTextView.setText(str);
        }
    }

    public void setLoginButtonEnabled(boolean z) {
        if (this.signUpButton != null) {
            this.signUpButton.setEnabled(z);
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

    public void setSignUpButton(String str, View.OnClickListener onClickListener) {
        if (this.signUpButton != null) {
            this.signUpButton.setText(str);
            this.signUpButton.setOnClickListener(onClickListener);
        }
    }

    public void setTermsPolicyTextView(String str, View.OnClickListener onClickListener) {
        if (this.termsPolicyTextView != null) {
            this.termsPolicyTextView.setText(str);
            this.termsPolicyTextView.setOnClickListener(onClickListener);
        }
    }

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
