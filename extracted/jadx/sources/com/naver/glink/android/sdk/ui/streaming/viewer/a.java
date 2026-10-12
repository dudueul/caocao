package com.naver.glink.android.sdk.ui.streaming.viewer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.n;
import com.naver.glink.android.sdk.a.t;

/* JADX INFO: compiled from: StreamingMediaController.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class a {
    private static final l a = l.a(a.class.getSimpleName());
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3000;
    private static final int e = 5;
    private boolean f;
    private final VideoView g;
    private final View h;
    private final ImageButton i;
    private final View j;
    private final SeekBar k;
    private final TextView l;
    private final TextView m;
    private int n;
    private InterfaceC0061a o;
    private final Handler p = new Handler() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.a.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    int iD = a.this.d();
                    if (!a.this.f && a.this.g.isPlaying()) {
                        sendEmptyMessageDelayed(1, 1000 - (iD % 1000));
                        break;
                    }
                    break;
                case 2:
                    a.this.f();
                    break;
            }
        }
    };

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.viewer.a$7, reason: invalid class name */
    /* JADX INFO: compiled from: StreamingMediaController.java */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[WatchingStreaming.Type.values().length];

        static {
            try {
                a[WatchingStreaming.Type.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[WatchingStreaming.Type.STREAMING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.viewer.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: StreamingMediaController.java */
    public interface InterfaceC0061a {
        void a();
    }

    a(VideoView videoView, View view) {
        this.g = videoView;
        this.h = view.findViewById(R.id.black_overlay_while_video_prepared);
        this.i = (ImageButton) view.findViewById(R.id.vod_pause_play);
        this.j = view.findViewById(R.id.vod_controls_layout);
        this.k = (SeekBar) view.findViewById(R.id.vod_seek_bar);
        this.l = (TextView) view.findViewById(R.id.vod_current_time);
        this.m = (TextView) view.findViewById(R.id.vod_end_time);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i) {
        return n.b(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.i.setImageResource(z ? R.drawable.cf_icon_pause_l : R.drawable.cf_icon_play_l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        d();
        c();
        this.p.sendEmptyMessage(1);
    }

    private void b(WatchingStreaming watchingStreaming) {
        this.i.setVisibility(0);
        this.j.setVisibility(0);
        this.i.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.a.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                if (a.this.g.isPlaying()) {
                    a.this.g.pause();
                } else {
                    a.this.g.start();
                }
                a.this.b();
            }
        });
        this.l.setText(n.b(0));
        this.m.setText(n.b(watchingStreaming.o));
        this.k.setMax(1000);
        this.k.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.a.5
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    long duration = (((long) a.this.g.getDuration()) * ((long) i)) / 1000;
                    a.this.g.seekTo((int) duration);
                    a.this.l.setText(a.this.a((int) duration));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                a.this.f = true;
                a.this.p.removeMessages(1);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                a.this.f = false;
                a.this.d();
                a.this.c();
                a.this.p.sendEmptyMessage(1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        a(this.g.isPlaying());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d() {
        if (this.g == null || this.f) {
            return 0;
        }
        int currentPosition = this.g.getCurrentPosition();
        int duration = this.g.getDuration();
        if (duration > 0) {
            this.k.setProgress((int) ((1000 * ((long) currentPosition)) / ((long) duration)));
        }
        this.k.setSecondaryProgress(this.g.getBufferPercentage() * 10);
        this.m.setText(a(duration));
        this.l.setText(a(currentPosition));
        return currentPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.n = 5;
        a.a("Video initRetryCount " + this.n, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.o != null) {
            this.o.a();
            this.n--;
            a.a("Video sendRetryEvent " + this.n, new Object[0]);
        }
    }

    void a(final WatchingStreaming watchingStreaming) {
        if (WatchingStreaming.Type.VIDEO.equals(watchingStreaming.a)) {
            b(watchingStreaming);
        }
        this.g.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.a.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                a.a.a("Video OnPrepared " + a.this.n, new Object[0]);
                a.this.h.setVisibility(8);
                if (WatchingStreaming.Type.VIDEO.equals(watchingStreaming.a)) {
                    a.this.d();
                    a.this.a(true);
                    a.this.p.sendEmptyMessage(1);
                }
                a.this.e();
            }
        });
        this.g.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.a.2
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                a.this.g.stopPlayback();
                a.a.a("Video OnError " + a.this.n, new Object[0]);
                if (a.this.n <= 0) {
                    return false;
                }
                a.this.p.sendEmptyMessageDelayed(2, 3000L);
                return true;
            }
        });
        this.g.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.a.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                a.a.a("Video OnCompletion " + a.this.n, new Object[0]);
                switch (AnonymousClass7.a[watchingStreaming.a.ordinal()]) {
                    case 1:
                        a.this.c();
                        break;
                    case 2:
                        if (a.this.n > 0) {
                            a.this.p.sendEmptyMessageDelayed(2, 3000L);
                        }
                        break;
                }
            }
        });
        e();
    }

    public void a(InterfaceC0061a interfaceC0061a) {
        this.o = interfaceC0061a;
    }
}
