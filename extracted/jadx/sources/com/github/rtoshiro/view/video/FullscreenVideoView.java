package com.github.rtoshiro.view.video;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.naver.glink.android.sdk.R;
import com.tapjoy.TJAdUnitConstants;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FullscreenVideoView extends RelativeLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback {
    private static final String a = "FullscreenVideoView";
    protected int A;
    protected String B;
    protected Uri C;
    protected MediaPlayer.OnBufferingUpdateListener D;
    protected MediaPlayer.OnCompletionListener E;
    protected MediaPlayer.OnErrorListener F;
    protected MediaPlayer.OnInfoListener G;
    protected MediaPlayer.OnPreparedListener H;
    protected MediaPlayer.OnSeekCompleteListener I;
    protected MediaPlayer.OnVideoSizeChangedListener J;
    protected Context j;
    protected Activity k;
    protected MediaPlayer l;
    protected SurfaceHolder m;
    protected SurfaceView n;
    protected boolean o;
    protected boolean p;
    protected boolean q;
    protected State r;
    protected State s;
    protected View t;
    protected ViewGroup u;
    protected ViewGroup.LayoutParams v;
    protected boolean w;
    protected boolean x;
    protected int y;
    protected int z;

    public enum State {
        IDLE,
        INITIALIZED,
        PREPARED,
        PREPARING,
        STARTED,
        STOPPED,
        PAUSED,
        PLAYBACKCOMPLETED,
        ERROR,
        END
    }

    public FullscreenVideoView(Context context) {
        super(context);
        this.j = context;
        a();
    }

    public FullscreenVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = context;
        a();
    }

    public FullscreenVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = context;
        a();
    }

    protected void a() {
        if (isInEditMode()) {
            return;
        }
        this.x = false;
        this.r = State.IDLE;
        this.w = false;
        this.y = -1;
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        b();
    }

    public void a(int i) {
        Log.d(a, "seekTo = " + i);
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        if (this.l.getDuration() <= -1 || i > this.l.getDuration()) {
            return;
        }
        this.s = this.r;
        i();
        this.l.seekTo(i);
        p();
    }

    protected void b() {
        Log.d(a, "initObjects");
        if (this.l == null) {
            this.l = new MediaPlayer();
        }
        if (this.n == null) {
            this.n = new SurfaceView(this.j);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this.n.setLayoutParams(layoutParams);
            addView(this.n);
        }
        if (this.m == null) {
            this.m = this.n.getHolder();
            this.m.setType(3);
            this.m.addCallback(this);
        }
        if (this.t == null) {
            this.t = new ProgressBar(this.j);
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.t.setLayoutParams(layoutParams2);
        addView(this.t);
        this.r = State.IDLE;
    }

    protected void c() {
        if (this.m != null) {
            this.m.removeCallback(this);
            this.m = null;
        }
        if (this.l != null) {
            this.l.reset();
        }
        if (this.n != null) {
            removeView(this.n);
            this.n = null;
        }
        if (this.t != null) {
            removeView(this.t);
        }
    }

    protected void g() {
        if (this.p && this.o) {
            if (this.l != null) {
                this.z = this.l.getVideoWidth();
                this.A = this.l.getVideoHeight();
            }
            s();
            q();
            this.r = State.PREPARED;
            if (this.x) {
                h();
            }
            if (this.H != null) {
                this.H.onPrepared(this.l);
            }
        }
    }

    public int getCurrentPosition() {
        if (this.l != null) {
            return this.l.getCurrentPosition();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public synchronized State getCurrentState() {
        return this.r;
    }

    public int getDuration() {
        if (this.l != null) {
            return this.l.getDuration();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public int getVideoHeight() {
        if (this.l != null) {
            return this.l.getVideoHeight();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public int getVideoWidth() {
        if (this.l != null) {
            return this.l.getVideoWidth();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void h() {
        Log.d(a, TJAdUnitConstants.String.VIDEO_START);
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.r = State.STARTED;
        this.l.setOnCompletionListener(this);
        this.l.start();
    }

    public void i() {
        Log.d(a, "pause");
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.r = State.PAUSED;
        this.l.pause();
    }

    public void j() {
        Log.d(a, "reset");
        c();
        b();
    }

    public void k() {
        Log.d(a, "stop");
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.r = State.STOPPED;
        this.l.stop();
    }

    protected void o() {
        p();
        this.o = false;
        this.A = -1;
        this.z = -1;
        this.l.setOnPreparedListener(this);
        this.l.setOnErrorListener(this);
        this.l.setOnSeekCompleteListener(this);
        this.l.setOnInfoListener(this);
        this.l.setOnVideoSizeChangedListener(this);
        this.l.setAudioStreamType(3);
        this.r = State.PREPARING;
        this.l.prepareAsync();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(a, "onAttachedToWindow");
        if (this.l == null && this.r == State.END) {
            b();
            try {
                if (this.B != null) {
                    setVideoPath(this.B);
                } else if (this.C != null) {
                    setVideoURI(this.C);
                }
            } catch (IOException e) {
                Log.d(a, "", e);
            }
        }
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        Log.d(a, "onBufferingUpdate = " + i);
        if (this.D != null) {
            this.D.onBufferingUpdate(mediaPlayer, i);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.l != null && this.r != State.ERROR) {
            Log.d(a, "onCompletion");
            if (this.l.isLooping()) {
                h();
            } else {
                this.r = State.PLAYBACKCOMPLETED;
            }
        }
        if (this.E != null) {
            this.E.onCompletion(mediaPlayer);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Log.d(a, "onDetachedFromWindow - detachedByFullscreen: " + this.q);
        super.onDetachedFromWindow();
        if (!this.q) {
            if (this.l != null) {
                this.l.setOnBufferingUpdateListener(null);
                this.l.setOnPreparedListener(null);
                this.l.setOnErrorListener(null);
                this.l.setOnSeekCompleteListener(null);
                this.l.setOnCompletionListener(null);
                this.l.setOnInfoListener(null);
                this.l.setOnVideoSizeChangedListener(null);
                c();
                this.l.release();
                this.l = null;
            }
            this.o = false;
            this.p = false;
            this.r = State.END;
        }
        this.q = false;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Log.d(a, "onError called - " + i + " - " + i2);
        q();
        this.r = State.ERROR;
        if (this.F != null) {
            return this.F.onError(mediaPlayer, i, i2);
        }
        return false;
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        Log.d(a, "onInfo " + i);
        if (this.G != null) {
            return this.G.onInfo(mediaPlayer, i, i2);
        }
        return false;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public synchronized void onPrepared(MediaPlayer mediaPlayer) {
        Log.d(a, "onPrepared called");
        this.o = true;
        g();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Log.d(a, "onRestoreInstanceState");
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Log.d(a, "onSaveInstanceState");
        return super.onSaveInstanceState();
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        Log.d(a, "onSeekComplete");
        q();
        if (this.s != null) {
            switch (this.s) {
                case STARTED:
                    h();
                    break;
                case PLAYBACKCOMPLETED:
                    this.r = State.PLAYBACKCOMPLETED;
                    break;
                case PREPARED:
                    this.r = State.PREPARED;
                    break;
            }
        }
        if (this.I != null) {
            this.I.onSeekComplete(mediaPlayer);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        s();
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        Log.d(a, "onVideoSizeChanged = " + i + " - " + i2);
        if (this.z == 0 && this.A == 0) {
            this.z = i;
            this.A = i2;
            s();
        }
        if (this.J != null) {
            this.J.onVideoSizeChanged(mediaPlayer, i, i2);
        }
    }

    protected void p() {
        if (this.t != null) {
            this.t.setVisibility(0);
        }
    }

    protected void q() {
        if (this.t != null) {
            this.t.setVisibility(8);
        }
    }

    public boolean r() {
        return this.w;
    }

    public void s() {
        if (this.A == -1 || this.z == -1 || this.n == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.github.rtoshiro.view.video.FullscreenVideoView.2
            @Override // java.lang.Runnable
            public void run() {
                View view;
                int i;
                int i2;
                if (FullscreenVideoView.this.A == -1 || FullscreenVideoView.this.z == -1 || FullscreenVideoView.this.n == null || (view = (View) FullscreenVideoView.this.getParent()) == null) {
                    return;
                }
                float f = FullscreenVideoView.this.z / FullscreenVideoView.this.A;
                int width = view.getWidth();
                int height = view.getHeight();
                if (f > width / height) {
                    i2 = (int) (width / f);
                    i = width;
                } else {
                    i = (int) (height * f);
                    i2 = height;
                }
                ViewGroup.LayoutParams layoutParams = FullscreenVideoView.this.n.getLayoutParams();
                layoutParams.width = i;
                layoutParams.height = i2;
                FullscreenVideoView.this.n.setLayoutParams(layoutParams);
                Log.d(FullscreenVideoView.a, "Resizing: initialMovieWidth: " + FullscreenVideoView.this.z + " - initialMovieHeight: " + FullscreenVideoView.this.A);
                Log.d(FullscreenVideoView.a, "Resizing: screenWidth: " + width + " - screenHeight: " + height);
            }
        });
    }

    public void setActivity(Activity activity) {
        this.k = activity;
        this.y = activity.getRequestedOrientation();
    }

    public void setFullscreen(boolean z) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        if (this.r == State.ERROR || this.w == z) {
            return;
        }
        this.w = z;
        final boolean zIsPlaying = this.l.isPlaying();
        if (zIsPlaying) {
            i();
        }
        if (this.w) {
            if (this.k != null) {
                this.k.setRequestedOrientation(-1);
            }
            View viewFindViewById = getRootView().findViewById(R.id.plug_view);
            ViewParent parent = getParent();
            if (parent instanceof ViewGroup) {
                if (this.u == null) {
                    this.u = (ViewGroup) parent;
                }
                this.q = true;
                this.v = getLayoutParams();
                this.u.removeView(this);
            } else {
                Log.e(a, "Parent View is not a ViewGroup");
            }
            if (viewFindViewById instanceof ViewGroup) {
                ((ViewGroup) viewFindViewById).addView(this);
            } else {
                Log.e(a, "RootView is not a ViewGroup");
            }
        } else {
            if (this.k != null) {
                this.k.setRequestedOrientation(this.y);
            }
            ViewParent parent2 = getParent();
            if (parent2 instanceof ViewGroup) {
                boolean z2 = false;
                if (this.u != null && this.u.getParent() != null) {
                    this.q = true;
                    z2 = true;
                }
                ((ViewGroup) parent2).removeView(this);
                if (z2) {
                    this.u.addView(this);
                    setLayoutParams(this.v);
                }
            }
        }
        s();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.github.rtoshiro.view.video.FullscreenVideoView.1
            @Override // java.lang.Runnable
            public void run() {
                if (!zIsPlaying || FullscreenVideoView.this.l == null) {
                    return;
                }
                FullscreenVideoView.this.h();
            }
        });
    }

    public void setLooping(boolean z) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.l.setLooping(z);
    }

    public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.D = onBufferingUpdateListener;
        this.l.setOnBufferingUpdateListener(this);
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.E = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.F = onErrorListener;
    }

    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.G = onInfoListener;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.H = onPreparedListener;
    }

    public void setOnProgressView(View view) {
        if (this.t != null) {
            removeView(this.t);
        }
        this.t = view;
        if (this.t != null) {
            addView(this.t);
        }
    }

    public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.I = onSeekCompleteListener;
    }

    public void setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.J = onVideoSizeChangedListener;
    }

    public void setShouldAutoplay(boolean z) {
        this.x = z;
    }

    public void setVideoPath(String str) throws IOException {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        if (this.r != State.IDLE) {
            throw new IllegalStateException("FullscreenVideoView Invalid State: " + this.r);
        }
        this.B = str;
        this.C = null;
        this.l.setDataSource(str);
        this.r = State.INITIALIZED;
        o();
    }

    public void setVideoURI(Uri uri) throws IOException {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        if (this.r != State.IDLE) {
            throw new IllegalStateException("FullscreenVideoView Invalid State: " + this.r);
        }
        this.C = uri;
        this.B = null;
        this.l.setDataSource(this.j, uri);
        this.r = State.INITIALIZED;
        o();
    }

    public void setVolume(float f, float f2) {
        if (this.l == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        this.l.setVolume(f, f2);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Log.d(a, "surfaceChanged called");
        s();
    }

    @Override // android.view.SurfaceHolder.Callback
    public synchronized void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(a, "surfaceCreated called = " + this.r);
        if (this.l != null) {
            this.l.setDisplay(this.m);
            if (!this.p) {
                this.p = true;
                if (this.r != State.PREPARED && this.r != State.PAUSED && this.r != State.STARTED && this.r != State.PLAYBACKCOMPLETED) {
                    g();
                }
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(a, "surfaceDestroyed called");
        if (this.l != null && this.l.isPlaying()) {
            this.l.pause();
        }
        this.p = false;
    }

    public boolean t() {
        return this.x;
    }

    @Deprecated
    public void u() {
        setFullscreen(!this.w);
    }

    public boolean v() {
        if (this.l != null) {
            return this.l.isLooping();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public boolean w() {
        if (this.l != null) {
            return this.l.isPlaying();
        }
        throw new RuntimeException("Media Player is not initialized");
    }
}
