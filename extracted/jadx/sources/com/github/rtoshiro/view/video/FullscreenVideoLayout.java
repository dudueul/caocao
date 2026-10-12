package com.github.rtoshiro.view.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.tapjoy.TJAdUnitConstants;
import java.util.Locale;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FullscreenVideoLayout extends FullscreenVideoView implements MediaPlayer.OnPreparedListener, View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {
    private static final String K = "FullscreenVideoLayout";
    protected static final Handler h = new Handler();
    protected View a;
    protected SeekBar b;
    protected ImageButton c;
    protected ImageButton d;
    protected TextView e;
    protected TextView f;
    protected View.OnTouchListener g;
    protected Runnable i;

    public FullscreenVideoLayout(Context context) {
        super(context);
        this.i = new Runnable() { // from class: com.github.rtoshiro.view.video.FullscreenVideoLayout.1
            @Override // java.lang.Runnable
            public void run() {
                FullscreenVideoLayout.this.f();
                FullscreenVideoLayout.h.postDelayed(this, 200L);
            }
        };
    }

    public FullscreenVideoLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new Runnable() { // from class: com.github.rtoshiro.view.video.FullscreenVideoLayout.1
            @Override // java.lang.Runnable
            public void run() {
                FullscreenVideoLayout.this.f();
                FullscreenVideoLayout.h.postDelayed(this, 200L);
            }
        };
    }

    public FullscreenVideoLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new Runnable() { // from class: com.github.rtoshiro.view.video.FullscreenVideoLayout.1
            @Override // java.lang.Runnable
            public void run() {
                FullscreenVideoLayout.this.f();
                FullscreenVideoLayout.h.postDelayed(this, 200L);
            }
        };
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    protected void a() {
        Log.d(K, "init");
        super.a();
        if (isInEditMode()) {
            return;
        }
        super.setOnTouchListener(this);
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    protected void b() {
        super.b();
        if (this.a == null) {
            this.a = ((LayoutInflater) this.j.getSystemService("layout_inflater")).inflate(R.layout.item_view_videocontrols, (ViewGroup) this, false);
        }
        if (this.a != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(12);
            addView(this.a, layoutParams);
            this.b = (SeekBar) this.a.findViewById(R.id.vcv_seekbar);
            this.d = (ImageButton) this.a.findViewById(R.id.vcv_img_fullscreen);
            this.c = (ImageButton) this.a.findViewById(R.id.vcv_img_play);
            this.e = (TextView) this.a.findViewById(R.id.vcv_txt_total);
            this.f = (TextView) this.a.findViewById(R.id.vcv_txt_elapsed);
        }
        if (this.c != null) {
            this.c.setOnClickListener(this);
        }
        if (this.d != null) {
            this.d.setOnClickListener(this);
        }
        if (this.b != null) {
            this.b.setOnSeekBarChangeListener(this);
        }
        if (this.a != null) {
            this.a.setVisibility(4);
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    protected void c() {
        super.c();
        if (this.a != null) {
            removeView(this.a);
        }
    }

    protected void d() {
        Log.d(K, "startCounter");
        h.postDelayed(this.i, 200L);
    }

    protected void e() {
        Log.d(K, "stopCounter");
        h.removeCallbacks(this.i);
    }

    protected void f() {
        int currentPosition;
        if (this.f != null && (currentPosition = getCurrentPosition()) > 0 && currentPosition < getDuration()) {
            this.b.setProgress(currentPosition);
            int iRound = Math.round(currentPosition / 1000.0f);
            long j = iRound % 60;
            long j2 = (iRound / 60) % 60;
            long j3 = (iRound / 3600) % 24;
            if (j3 > 0) {
                this.f.setText(String.format(Locale.US, "%d:%02d:%02d", Long.valueOf(j3), Long.valueOf(j2), Long.valueOf(j)));
            } else {
                this.f.setText(String.format(Locale.US, "%02d:%02d", Long.valueOf(j2), Long.valueOf(j)));
            }
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    protected void g() {
        int duration;
        Log.d(K, "tryToPrepare");
        super.g();
        if (getCurrentState() == FullscreenVideoView.State.PREPARED || getCurrentState() == FullscreenVideoView.State.STARTED) {
            if (this.f != null && this.e != null && (duration = getDuration()) > 0) {
                this.b.setMax(duration);
                this.b.setProgress(0);
                int i = duration / 1000;
                long j = i % 60;
                long j2 = (i / 60) % 60;
                long j3 = (i / 3600) % 24;
                if (j3 > 0) {
                    this.f.setText("00:00:00");
                    this.e.setText(String.format(Locale.US, "%d:%02d:%02d", Long.valueOf(j3), Long.valueOf(j2), Long.valueOf(j)));
                } else {
                    this.f.setText("00:00");
                    this.e.setText(String.format(Locale.US, "%02d:%02d", Long.valueOf(j2), Long.valueOf(j)));
                }
            }
            if (this.a != null) {
                this.a.setVisibility(0);
            }
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    public void h() {
        Log.d(K, TJAdUnitConstants.String.VIDEO_START);
        if (w()) {
            return;
        }
        super.h();
        d();
        l();
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    public void i() {
        Log.d(K, "pause");
        if (w()) {
            e();
            super.i();
            l();
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    public void j() {
        Log.d(K, "reset");
        super.j();
        e();
        l();
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView
    public void k() {
        Log.d(K, "stop");
        super.k();
        e();
        l();
    }

    protected void l() {
        if (this.c == null) {
            return;
        }
        this.c.setBackgroundDrawable(getCurrentState() == FullscreenVideoView.State.STARTED ? ContextCompat.getDrawable(this.j, R.drawable.cf_icon_pause_l) : ContextCompat.getDrawable(this.j, R.drawable.cf_icon_play_l));
    }

    public void m() {
        Log.d(K, "hideControls");
        if (this.a != null) {
            this.a.setVisibility(4);
        }
    }

    public void n() {
        Log.d(K, "showControls");
        if (this.a != null) {
            this.a.setVisibility(0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.vcv_img_play) {
            setFullscreen(!r());
        } else if (w()) {
            i();
        } else {
            h();
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView, android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d(K, "onCompletion");
        super.onCompletion(mediaPlayer);
        e();
        l();
        if (this.r != FullscreenVideoView.State.ERROR) {
            f();
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (getCurrentState() == FullscreenVideoView.State.END) {
            Log.d(K, "onDetachedFromWindow END");
            e();
        }
    }

    @Override // com.github.rtoshiro.view.video.FullscreenVideoView, android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        boolean zOnError = super.onError(mediaPlayer, i, i2);
        e();
        l();
        return zOnError;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        Log.d(K, "onProgressChanged " + i);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        e();
        Log.d(K, "onStartTrackingTouch");
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        a(seekBar.getProgress());
        Log.d(K, "onStopTrackingTouch");
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && this.a != null) {
            if (this.a.getVisibility() == 0) {
                m();
            } else {
                n();
            }
        }
        if (this.g != null) {
            return this.g.onTouch(this, motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.g = onTouchListener;
    }
}
