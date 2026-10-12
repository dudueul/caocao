package kr.co.nexon.mdev.android.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPRelativeLayout extends RelativeLayout {
    public NPOrientationEventListener listener;
    public Context mContext;
    public int orientation;

    public interface NPOrientationEventListener {
        void onOrientationChanged(int i, int i2);
    }

    public NPRelativeLayout(Context context) {
        super(context);
        this.orientation = -1;
        this.mContext = context;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.mContext.getResources().getConfiguration().orientation;
        if (this.orientation != i3) {
            if (this.orientation != -1 && this.listener != null) {
                this.listener.onOrientationChanged(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
            }
            this.orientation = i3;
        }
        super.onMeasure(i, i2);
    }

    public void setOrientationEventListener(NPOrientationEventListener nPOrientationEventListener) {
        this.listener = nPOrientationEventListener;
    }
}
