package kr.co.nexon.toy.android.ui.backup.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPDataBackupView extends NXPLinearLayout {
    private View.OnClickListener closeButtonClickListener;
    private TextView descriptionCodeTextView;
    private TextView mgTokenTextView;
    private TextView titleTextView;

    public NXPDataBackupView(Context context) {
        super(context);
    }

    public NXPDataBackupView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPDataBackupView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CharSequence getMgTokenText() {
        return this.mgTokenTextView == null ? "" : this.mgTokenTextView.getText();
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.descriptionCodeTextView = (TextView) findViewById(R.id.descCode);
        this.mgTokenTextView = (TextView) findViewById(R.id.mgToken);
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(4);
        }
        View viewFindViewById2 = findViewById(R.id.closeBtn);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.backup.view.NXPDataBackupView.1
                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                protected void onSwallowClick(View view) {
                    if (NXPDataBackupView.this.closeButtonClickListener != null) {
                        NXPDataBackupView.this.closeButtonClickListener.onClick(view);
                    }
                }
            });
        }
    }

    public void setDescriptionCodeText(CharSequence charSequence) {
        if (this.descriptionCodeTextView != null) {
            this.descriptionCodeTextView.setText(charSequence);
        }
    }

    public void setMgTokenText(CharSequence charSequence) {
        if (this.mgTokenTextView != null) {
            this.mgTokenTextView.setText(charSequence);
        }
    }

    public void setMgTokenTextVisibility(int i) {
        if (this.mgTokenTextView != null) {
            this.mgTokenTextView.setVisibility(i);
        }
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    public void setTitleTextView(CharSequence charSequence) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(charSequence);
        }
    }
}
