package kr.co.nexon.toy.android.ui.baseplate.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPGameInfoView extends NXPLinearLayout {
    private TextView ageRatingTextView;
    private ImageButton closeButton;
    private TextView companyNameTextView;
    private TextView companyNoTextView;
    private GridLayout grbInfoGridLayout;
    private TextView serviceTitleTextView;
    private TextView titleTextView;

    public NXPGameInfoView(Context context) {
        super(context);
    }

    public NXPGameInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPGameInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void addGrbFieldRating(int i) {
        if (this.grbInfoGridLayout != null) {
            LinearLayout linearLayout = (LinearLayout) View.inflate(getContext(), R.layout.nxp_game_info_grb_image, null);
            ((ImageView) linearLayout.findViewById(R.id.imgGrbRating)).setImageResource(i);
            this.grbInfoGridLayout.addView(linearLayout);
        }
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(4);
        }
        this.grbInfoGridLayout = (GridLayout) findViewById(R.id.gameGrbInfo);
        this.ageRatingTextView = (TextView) findViewById(R.id.ageRating);
        this.serviceTitleTextView = (TextView) findViewById(R.id.serviceTitle);
        this.companyNameTextView = (TextView) findViewById(R.id.companyName);
        this.companyNoTextView = (TextView) findViewById(R.id.companyNo);
    }

    public void setAgeRating(String str) {
        if (this.ageRatingTextView != null) {
            this.ageRatingTextView.setText(str);
        }
    }

    public void setCompanyNameText(String str) {
        if (this.companyNameTextView != null) {
            this.companyNameTextView.setText(str);
        }
    }

    public void setCompanyNoText(String str) {
        if (this.companyNoTextView != null) {
            this.companyNoTextView.setText(str);
        }
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        if (this.closeButton != null) {
            this.closeButton.setOnClickListener(onClickListener);
        }
    }

    public void setServiceTitleText(String str) {
        if (this.serviceTitleTextView != null) {
            this.serviceTitleTextView.setText(str);
        }
    }

    public void setTitle(String str) {
        this.titleTextView = (TextView) findViewById(R.id.title);
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
