package kr.co.nexon.toy.android.ui.common.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSimpleListArrowItem extends NXPLinearLayout {
    private int position;
    private TextView textView;

    public NXPSimpleListArrowItem(Context context) {
        super(context);
    }

    public NXPSimpleListArrowItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPSimpleListArrowItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public int getPosition() {
        return this.position;
    }

    public CharSequence getText() {
        return this.textView != null ? this.textView.getText() : "";
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.textView = (TextView) findViewById(R.id.simple_listivew_text);
        this.position = -1;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setText(CharSequence charSequence) {
        if (this.textView != null) {
            this.textView.setText(charSequence);
        }
    }
}
