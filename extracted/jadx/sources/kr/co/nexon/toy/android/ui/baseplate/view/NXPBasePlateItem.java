package kr.co.nexon.toy.android.ui.baseplate.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPBasePlateItem extends NXPLinearLayout {
    private ImageView baseplateIcon;
    private TextView baseplateTitle;
    private View containerView;

    public NXPBasePlateItem(Context context) {
        super(context);
    }

    public NXPBasePlateItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPBasePlateItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.baseplateTitle = (TextView) findViewById(R.id.baseplate_item_title);
        this.baseplateIcon = (ImageView) findViewById(R.id.baseplate_icon);
        this.containerView = findViewById(R.id.baseplate_item);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        if (this.containerView != null) {
            this.containerView.setBackgroundResource(i);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.baseplateIcon != null) {
            this.baseplateIcon.setBackground(drawable);
        }
    }

    public void setIconResource(@DrawableRes int i) {
        if (this.baseplateIcon != null) {
            this.baseplateIcon.setBackgroundResource(i);
        }
    }

    public void setTitle(String str) {
        if (this.baseplateTitle != null) {
            this.baseplateTitle.setText(str);
        }
    }
}
