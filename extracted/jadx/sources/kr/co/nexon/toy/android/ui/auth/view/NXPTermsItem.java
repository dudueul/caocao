package kr.co.nexon.toy.android.ui.auth.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPTermsItem extends NXPLinearLayout {
    private Button checkBoxButton;
    private LinearLayout termsItemLinearLayout;
    private TextView termsTitleTextView;
    private Button viewTermsButton;

    public NXPTermsItem(Context context) {
        super(context);
    }

    public NXPTermsItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPTermsItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.termsItemLinearLayout = (LinearLayout) findViewById(R.id.npterms_item);
        this.termsTitleTextView = (TextView) findViewById(R.id.npterms_item_title);
        this.viewTermsButton = (Button) findViewById(R.id.npterms_item_view);
        this.checkBoxButton = (Button) findViewById(R.id.npterms_item_checkbox);
    }

    public void setCheckBoxBackgroundResource(int i) {
        if (this.checkBoxButton != null) {
            this.checkBoxButton.setBackgroundResource(i);
        }
    }

    public void setLayoutMarginBottom(int i) {
        if (this.termsItemLinearLayout != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.termsItemLinearLayout.getLayoutParams();
            layoutParams.bottomMargin = i;
            this.termsItemLinearLayout.setLayoutParams(layoutParams);
        }
    }

    public void setOnCheckBoxClickListener(View.OnClickListener onClickListener) {
        if (this.checkBoxButton != null) {
            this.checkBoxButton.setOnClickListener(onClickListener);
        }
    }

    public void setOnTermsViewButtonClickListener(View.OnClickListener onClickListener) {
        if (this.viewTermsButton != null) {
            this.viewTermsButton.setOnClickListener(onClickListener);
        }
    }

    public void setTermsTitle(String str) {
        if (this.termsTitleTextView != null) {
            this.termsTitleTextView.setText(str);
        }
    }
}
