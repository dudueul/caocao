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
public class NXPEmailLoginCheckView extends NXPRelativeLayout {
    private ImageButton backButton;
    private ImageButton closeButton;
    private Button continueButton;
    private NXInputEditView emailIdInputEditText;
    private View midContainer;
    private TextView titleTextView;
    private View topContainer;

    public NXPEmailLoginCheckView(Context context) {
        super(context);
    }

    public NXPEmailLoginCheckView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPEmailLoginCheckView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public String getPasswordText() {
        return this.emailIdInputEditText != null ? this.emailIdInputEditText.getText() : "";
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.login_title);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        this.backButton = (ImageButton) findViewById(R.id.backBtn);
        this.topContainer = findViewById(R.id.topContainer);
        this.midContainer = findViewById(R.id.midContainer);
        this.emailIdInputEditText = (NXInputEditView) findViewById(R.id.etEmailInput);
        this.emailIdInputEditText.setImeOption(-1879048186);
        this.emailIdInputEditText.setInputType(32);
        this.emailIdInputEditText.setFocus();
        this.continueButton = (Button) findViewById(R.id.btContinue);
    }

    public void setBackButtonInvisible() {
        if (this.backButton != null) {
            this.backButton.setVisibility(4);
        }
    }

    public void setContinueButton(String str, View.OnClickListener onClickListener) {
        if (this.continueButton != null) {
            this.continueButton.setText(str);
            this.continueButton.setOnClickListener(onClickListener);
        }
    }

    public void setContinueButtonEnabled(boolean z) {
        if (this.continueButton != null) {
            this.continueButton.setEnabled(z);
        }
    }

    public void setEditHint(String str) {
        if (this.emailIdInputEditText != null) {
            this.emailIdInputEditText.setHint(str);
        }
    }

    public void setEditListener(NXInputEditView.NPTextChangedListener nPTextChangedListener) {
        if (this.emailIdInputEditText != null) {
            this.emailIdInputEditText.setListener(nPTextChangedListener);
        }
    }

    public void setEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        if (this.emailIdInputEditText != null) {
            this.emailIdInputEditText.setEditorActionListener(onEditorActionListener);
        }
    }

    public void setErrorMessage(String str) {
        if (this.emailIdInputEditText != null) {
            this.emailIdInputEditText.setErrorMessage(str);
        }
    }

    public void setIdText(String str) {
        if (this.emailIdInputEditText != null) {
            this.emailIdInputEditText.setText(str);
        }
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
}
