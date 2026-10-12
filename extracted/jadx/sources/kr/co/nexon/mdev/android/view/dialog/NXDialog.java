package kr.co.nexon.mdev.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXDialog extends Dialog {
    private Context mContext;

    public class NXTouchArea {
        int bottom;
        int left;
        int right;
        int top;

        public NXTouchArea(int i, int i2, int i3, int i4) {
            this.left = i;
            this.top = i2;
            this.right = i3;
            this.bottom = i4;
        }
    }

    public NXDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public NXDialog(Context context, int i) {
        super(context, i);
        this.mContext = context;
    }

    public View addClickArea(NXTouchArea nXTouchArea, ViewGroup viewGroup, View.OnClickListener onClickListener) {
        RelativeLayout relativeLayout = new RelativeLayout(this.mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = nXTouchArea.right - nXTouchArea.left;
        layoutParams.height = nXTouchArea.bottom - nXTouchArea.top;
        layoutParams.topMargin = nXTouchArea.top;
        layoutParams.leftMargin = nXTouchArea.left;
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setClickable(true);
        relativeLayout.setOnClickListener(onClickListener);
        viewGroup.addView(relativeLayout);
        return relativeLayout;
    }

    public void addClickArea(NXTouchArea nXTouchArea, ViewGroup viewGroup, View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = nXTouchArea.right - nXTouchArea.left;
        layoutParams.height = nXTouchArea.bottom - nXTouchArea.top;
        layoutParams.topMargin = nXTouchArea.top;
        layoutParams.leftMargin = nXTouchArea.left;
        view.setLayoutParams(layoutParams);
        viewGroup.addView(view);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
