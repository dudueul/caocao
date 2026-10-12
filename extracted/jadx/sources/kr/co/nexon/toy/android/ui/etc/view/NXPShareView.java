package kr.co.nexon.toy.android.ui.etc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPShareView extends NXPLinearLayout {
    private ImageButton closeButton;
    private TextView descriptionTextView;
    private GridView shareGridView;
    private TextView titleTextView;

    public NXPShareView(Context context) {
        super(context);
    }

    public NXPShareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPShareView(Context context, AttributeSet attributeSet, int i) {
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
        this.descriptionTextView = (TextView) findViewById(R.id.npshare_title);
        this.shareGridView = (GridView) findViewById(R.id.share_type_Info);
    }

    public void setDescriptionText(String str) {
        if (this.descriptionTextView != null) {
            this.descriptionTextView.setText(str);
        }
    }

    public void setListAdapter(ListAdapter listAdapter) {
        if (listAdapter == null) {
            ToyLog.d("NXPShareView Adapter is null");
        } else if (this.shareGridView != null) {
            this.shareGridView.setAdapter(listAdapter);
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
