package kr.co.nexon.toy.android.ui.etc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.util.NXResourcesUtil;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSettlementFundItemConfirmView extends NXPLinearLayout {
    private ImageButton backButton;
    private ImageButton closeButton;
    private Button confirmButton;
    private TextView itemNameTextView;
    private TextView itemPriceTextView;
    private LinearLayout termsLinearLayout;
    private TextView titleTextView;

    public NXPSettlementFundItemConfirmView(Context context) {
        super(context);
    }

    public NXPSettlementFundItemConfirmView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPSettlementFundItemConfirmView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void addTerm(String str, View.OnClickListener onClickListener) {
        if (this.termsLinearLayout != null) {
            TextView textView = (TextView) View.inflate(getContext(), R.layout.nxp_settlement_fund_item_terms, null);
            textView.setText(str);
            textView.setTextColor(NXResourcesUtil.getColorStateListByResId(getContext(), R.color.common_text_button_black_selector));
            textView.setOnClickListener(onClickListener);
            this.termsLinearLayout.addView(textView);
        }
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        this.backButton = (ImageButton) findViewById(R.id.backBtn);
        this.backButton.setVisibility(8);
        this.itemNameTextView = (TextView) findViewById(R.id.tvItemName);
        this.itemPriceTextView = (TextView) findViewById(R.id.tvItemPrice);
        this.termsLinearLayout = (LinearLayout) findViewById(R.id.tvTermsListLayout);
        this.confirmButton = (Button) findViewById(R.id.btnConfirm);
    }

    public void setBackButtonVisibility(int i) {
        if (this.backButton != null) {
            this.backButton.setVisibility(i);
        }
    }

    public void setItemNameText(String str) {
        if (this.itemNameTextView != null) {
            this.itemNameTextView.setText(str);
        }
    }

    public void setItemPriceText(String str) {
        if (this.itemPriceTextView != null) {
            this.itemPriceTextView.setText(str);
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

    public void setOnConfirmButton(String str, View.OnClickListener onClickListener) {
        if (this.confirmButton != null) {
            this.confirmButton.setText(str);
            this.confirmButton.setOnClickListener(onClickListener);
        }
    }

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
