package com.naver.glink.android.sdk.ui.widget.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.github.rtoshiro.view.video.FullscreenVideoLayout;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.n;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PlugVideoView extends FullscreenVideoLayout {
    private n K;
    private View L;
    private a M;
    private View.OnClickListener N;
    private boolean O;

    public interface a {
        void a();
    }

    public PlugVideoView(Context context) {
        this(context, null);
    }

    public PlugVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlugVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.O = true;
        this.K = new n();
    }

    private void setProgress(int i) {
        int duration = getDuration();
        int iMin = Math.min(i, duration);
        if (this.b != null) {
            this.b.setProgress(iMin);
        }
        if (this.e != null) {
            this.e.setText(this.K.a(duration));
        }
        if (this.f != null) {
            this.f.setText(this.K.a(iMin));
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoLayout, com.github.rtoshiro.view.video.FullscreenVideoView
    protected void b() {
        super.b();
        if (this.a != null) {
            this.L = this.a.findViewById(R.id.video_close);
            setOnVideoCloseClickListener(this.N);
            ((TextView) this.a.findViewById(R.id.vcv_txt_divider)).setText(" / ");
        }
        this.n.setZOrderOnTop(true);
        this.n.setZOrderMediaOverlay(true);
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoLayout
    protected void f() {
        setProgress(getCurrentPosition());
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoLayout, com.github.rtoshiro.view.video.FullscreenVideoView, android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        super.onCompletion(mediaPlayer);
        setProgress(getDuration());
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoLayout, android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.O && super.onTouch(view, motionEvent);
    }

    public void setControlsToggleEnabled(boolean z) {
        this.O = z;
    }

    public void setFullButtonVisibility(int i) {
        if (this.d != null) {
            this.d.setVisibility(i);
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    public void setFullscreen(boolean z) {
        if (z && this.M != null) {
            this.M.a();
        }
        if (this.l != null) {
            super.setFullscreen(z);
        }
    }

    public void setOnFullScreenListener(a aVar) {
        this.M = aVar;
    }

    public void setOnVideoCloseClickListener(@Nullable View.OnClickListener onClickListener) {
        this.N = onClickListener;
        if (this.L != null) {
            this.L.setVisibility(onClickListener == null ? 8 : 0);
            this.L.setOnClickListener(onClickListener);
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    public boolean w() {
        return this.l != null && super.w();
    }
}
