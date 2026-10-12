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
public class NXPSettlementFundConfirmView extends NXPLinearLayout {
    private ImageButton backButton;
    private ImageButton closeButton;
    private TextView descriptionTextView;
    private Button noButton;
    private TextView titleTextView;
    private Button yesButton;

    public NXPSettlementFundConfirmView(Context context) {
        super(context);
    }

    public NXPSettlementFundConfirmView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPSettlementFundConfirmView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        this.backButton = (ImageButton) findViewById(R.id.backBtn);
        this.descriptionTextView = (TextView) findViewById(R.id.tvDesc);
        this.yesButton = (Button) findViewById(R.id.btnYes);
        this.noButton = (Button) findViewById(R.id.btnNo);
    }

    public void setDescriptionText(String str) {
        if (this.descriptionTextView != null) {
            this.descriptionTextView.setText(str);
        }
    }

    public void setNoButton(String str, View.OnClickListener onClickListener) {
        if (this.noButton != null) {
            this.noButton.setText(str);
            this.noButton.setOnClickListener(onClickListener);
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

    public void setYesButton(String str, View.OnClickListener onClickListener) {
        if (this.yesButton != null) {
            this.yesButton.setText(str);
            this.yesButton.setOnClickListener(onClickListener);
        }
    }
}
