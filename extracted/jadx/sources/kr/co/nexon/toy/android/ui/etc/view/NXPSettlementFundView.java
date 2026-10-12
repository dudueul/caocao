package kr.co.nexon.toy.android.ui.etc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSettlementFundView extends NXPLinearLayout {
    private TextView birthDayText;
    private ImageButton closeButton;
    private Button confirmButton;
    private TextView descriptionTextView;
    private TextView titleTextView;

    public NXPSettlementFundView(Context context) {
        super(context);
    }

    public NXPSettlementFundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPSettlementFundView(Context context, AttributeSet attributeSet, int i) {
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
        this.descriptionTextView = (TextView) findViewById(R.id.tvDesc);
        this.birthDayText = (TextView) findViewById(R.id.etBirthDay);
        this.confirmButton = (Button) findViewById(R.id.btnConfirm);
    }

    public void setBirthDay(String str) {
        if (this.birthDayText != null) {
            this.birthDayText.setText(str);
        }
    }

    public void setConfirmButton(String str, View.OnClickListener onClickListener) {
        if (this.confirmButton != null) {
            this.confirmButton.setText(str);
            this.confirmButton.setOnClickListener(onClickListener);
        }
    }

    public void setConfirmButtonEnabled(boolean z) {
        if (this.confirmButton != null) {
            this.confirmButton.setEnabled(z);
        }
    }

    public void setDescriptionText(String str) {
        if (this.descriptionTextView != null) {
            this.descriptionTextView.setText(str);
        }
    }

    public void setOnBirthDayEditTextClickListener(View.OnClickListener onClickListener) {
        if (this.birthDayText != null) {
            this.birthDayText.setOnClickListener(onClickListener);
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
