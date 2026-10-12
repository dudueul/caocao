package kr.co.nexon.mdev.android.view.listener;

import android.os.SystemClock;
import android.view.View;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NXClickListener implements View.OnClickListener {
    public static final long DEFAULT_DELAY_TIME = 600;
    public static final long MIN_DELAY_TIME = 300;
    private long clickEventDelayTime = 300;
    private long lastClickTime = 0;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        long j = jUptimeMillis - this.lastClickTime;
        this.lastClickTime = jUptimeMillis;
        if (j <= this.clickEventDelayTime) {
            return;
        }
        onSwallowClick(view);
    }

    protected abstract void onSwallowClick(View view);

    public void setClickEventDelayTime(long j) {
        if (j >= 300) {
            this.clickEventDelayTime = j;
        }
    }
}
