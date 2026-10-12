package kr.co.nexon.android.sns.nxcom.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPRelativeLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPNexonSignUpView extends NXPRelativeLayout {
    private ImageButton backButton;
    private ImageButton closeButton;
    private TextView descriptionTextView;
    private Button nxComSignUpByEmail;
    private View nxComSignUpByGoogle;
    private TextView nxComSignUpByGoogleText;
    private TextView titleTextView;

    public NXPNexonSignUpView(Context context) {
        super(context);
    }

    public NXPNexonSignUpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPNexonSignUpView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        this.backButton = (ImageButton) findViewById(R.id.backBtn);
        this.nxComSignUpByEmail = (Button) findViewById(R.id.nxjoin_select_email_btn);
        this.nxComSignUpByGoogle = findViewById(R.id.nxjoin_select_gplus_btn);
        this.nxComSignUpByGoogleText = (TextView) findViewById(R.id.nxjoin_select_gplus_text);
        this.descriptionTextView = (TextView) findViewById(R.id.nxjoin_gplus_description);
    }

    public void setDescriptionText(String str) {
        if (this.descriptionTextView != null) {
            this.descriptionTextView.setText(str);
        }
    }

    public void setEmailSignUpButton(String str, View.OnClickListener onClickListener) {
        if (this.nxComSignUpByEmail != null) {
            this.nxComSignUpByEmail.setText(str);
            this.nxComSignUpByEmail.setOnClickListener(onClickListener);
        }
    }

    public void setGoogleSignUpButton(String str, View.OnClickListener onClickListener) {
        if (this.nxComSignUpByGoogle != null) {
            if (this.nxComSignUpByGoogleText != null) {
                this.nxComSignUpByGoogleText.setText(str);
            }
            this.nxComSignUpByGoogle.setOnClickListener(onClickListener);
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

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
