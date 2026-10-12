package com.naver.glink.android.sdk.ui.streaming.viewer;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.m;
import com.naver.glink.android.sdk.a.q;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.streaming.StreamingResponse;
import com.naver.glink.android.sdk.ui.parent.plugfragment.ItemsDialogFragmentView;
import com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment;
import com.squareup.otto.Subscribe;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: StreamingViewerFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d extends com.naver.glink.android.sdk.ui.parent.a.b implements b.InterfaceC0062b {
    private static final l a = l.a(d.class.getSimpleName());
    private View b;
    private View c;
    private View d;
    private ImageButton e;
    private ImageButton f;
    private ImageButton g;
    private VideoView h;
    private TextView i;
    private ImageView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private View p;
    private ImageButton q;
    private View r;
    private TextView s;
    private TextView t;
    private m u;
    private m v;
    private a w;
    private b.a x;
    private boolean y;
    private int z;

    /* JADX INFO: compiled from: StreamingViewerFragment.java */
    interface a {
        void a(View view, Runnable runnable);

        void a(boolean z);

        void c();

        void d();
    }

    private void a(int i) {
        this.m.setText(v.a(getActivity(), Math.max(i, 1), Integer.MAX_VALUE));
    }

    private void a(View view) {
        this.u = new m(true) { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.18
            @Override // com.naver.glink.android.sdk.a.m
            public void a(boolean z) {
                d.this.b.setVisibility(z ? 0 : 8);
            }
        };
        view.setOnClickListener(this.u);
        this.v = new m() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.2
            @Override // com.naver.glink.android.sdk.a.m
            public void a(boolean z) {
                d.this.b(z);
            }
        };
        this.g.setOnClickListener(this.v);
        this.e.setOnClickListener(new m() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.3
            @Override // com.naver.glink.android.sdk.a.m
            public void a(boolean z) {
                d.this.c(z);
            }
        });
    }

    private void a(View view, final WatchingStreaming watchingStreaming) {
        this.r.setVisibility(8);
        this.i.setText(watchingStreaming.e);
        this.k.setText(watchingStreaming.f);
        a(watchingStreaming.k);
        b(watchingStreaming.l);
        Glide.with(getActivity()).load(watchingStreaming.g).placeholder(R.drawable.cf_img_pfdefault).into(this.j);
        com.naver.glink.android.sdk.ui.streaming.viewer.a aVar = new com.naver.glink.android.sdk.ui.streaming.viewer.a(this.h, view);
        aVar.a(watchingStreaming);
        aVar.a(new com.naver.glink.android.sdk.ui.streaming.viewer.a.InterfaceC0061a() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.16
            @Override // com.naver.glink.android.sdk.ui.streaming.viewer.a.InterfaceC0061a
            public void a() {
                if (d.this.r.getVisibility() != 0) {
                    d.this.x.a(watchingStreaming.c(), false);
                }
            }
        });
        if (watchingStreaming.b()) {
            this.f.setVisibility(0);
            this.f.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.17
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    d.this.x.j();
                }
            });
            view.findViewById(R.id.live_text).setVisibility(8);
            this.l.setVisibility(0);
            this.l.setText(q.a(watchingStreaming.m));
            this.m.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cf_icon_vodview_ws, 0, 0, 0);
            this.o.setVisibility(0);
            this.q.setVisibility(8);
        }
        a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WatchingStreaming.Type type, StreamingResponse.Quality quality) {
        switch (type) {
            case STREAMING:
                this.x.a(quality, false);
                break;
            case VIDEO:
                this.x.a(quality, ((long) (this.h.getDuration() - this.h.getCurrentPosition())) > TimeUnit.SECONDS.toMillis(1L));
                break;
        }
    }

    private void a(boolean z) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (!com.naver.glink.android.sdk.d.i() || z) {
            layoutParams.addRule(18, R.id.streaming_etc_layout);
            layoutParams.addRule(3, R.id.streaming_etc_layout);
            layoutParams.topMargin = x.a(4.0f);
        } else {
            layoutParams.addRule(1, R.id.streaming_etc_layout);
            layoutParams.addRule(3, R.id.streaming_title);
            layoutParams.topMargin = x.a(4.0f);
            layoutParams.leftMargin = x.a(8.0f);
        }
        this.p.setLayoutParams(layoutParams);
    }

    private void b(int i) {
        this.n.setText(v.a(getActivity(), i, Integer.MAX_VALUE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        this.g.setImageResource(z ? R.drawable.cf_icon_livechat_w_pressed : R.drawable.cf_icon_livechat_w_normal);
        this.d.setVisibility(z ? 0 : 8);
        this.e.setVisibility(z ? 0 : 8);
        c(z ? false : true);
        a(z);
        this.w.a(z);
    }

    public static d c() {
        return new d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        this.e.setImageResource(z ? R.drawable.cf_icon_chatreduction_w : R.drawable.cf_icon_chatfull_w);
        this.c.setVisibility(z ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        final View view = getView();
        if (view != null) {
            this.s.setVisibility(8);
            this.t.setVisibility(0);
            e();
            d(true);
            this.w.a(getView(), new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.4
                @Override // java.lang.Runnable
                public void run() {
                    d.this.s.setVisibility(0);
                    d.this.t.setVisibility(8);
                    view.setOnClickListener(d.this.u);
                    view.setOnTouchListener(null);
                    d.this.d(true);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (Build.VERSION.SDK_INT < 23) {
            this.y = z;
        }
    }

    private void e() {
        if (this.u != null) {
            this.u.b(false);
        }
        if (this.v != null) {
            this.v.b(false);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void a() {
        this.w.d();
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void a(int i, boolean z) {
        this.f.setActivated(z);
        this.o.setText(v.a(getActivity(), i));
        this.o.setCompoundDrawablesWithIntrinsicBounds(z ? R.drawable.cf_icon_heart_gs_pressed : R.drawable.cf_icon_heart_ws, 0, 0, 0);
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void a(StreamingResponse.Quality quality, boolean z) {
        try {
            int currentPosition = this.h.getCurrentPosition();
            this.h.setVideoURI(Uri.parse(quality.url));
            if (z) {
                this.h.seekTo(currentPosition);
            }
            this.h.start();
        } catch (Exception e) {
            a.a(e);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void a(StreamingReportFragment.Report report) {
        StreamingReportFragment.a(report).show(getFragmentManager(), (String) null);
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.streaming.a.a.C0054a c0054a) {
        b(c0054a.b);
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.streaming.a.a.b bVar) {
        if (this.x.b() == WatchingStreaming.Type.STREAMING) {
            a(bVar.b);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void a(WatchingStreaming watchingStreaming) {
        View view = getView();
        if (view != null) {
            a(view, watchingStreaming);
            a(view);
            view.postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.15
                @Override // java.lang.Runnable
                public void run() {
                    d.this.u.b(false);
                }
            }, TimeUnit.SECONDS.toMillis(3L));
        }
    }

    @Override // com.naver.glink.android.sdk.a.d.b
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void setPresenter(b.a aVar) {
        this.x = aVar;
    }

    public void a(a aVar) {
        this.w = aVar;
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void a(String str) {
        this.i.setText(str);
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void a(String str, @DrawableRes int i) {
        this.h.stopPlayback();
        this.r.setVisibility(0);
        this.s.setText(str);
        this.s.setCompoundDrawablesWithIntrinsicBounds(0, i, 0, 0);
        this.t.setText(str);
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void b() {
        if (getParentFragment() instanceof c) {
            ((c) getParentFragment()).dismiss();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void b(WatchingStreaming watchingStreaming) {
        ItemsDialogFragmentView.a aVarC = ItemsDialogFragmentView.c();
        StreamingResponse.Quality qualityC = watchingStreaming.c();
        if (qualityC != null) {
            aVarC.a(com.naver.glink.android.sdk.d.a(R.string.live_video_resolution) + " / " + qualityC.title, R.drawable.cf_icon_resolution_b, new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.5
                @Override // java.lang.Runnable
                public void run() {
                    d.this.x.g();
                }
            });
        }
        if (!TextUtils.isEmpty(watchingStreaming.i)) {
            aVarC.a(com.naver.glink.android.sdk.d.a(R.string.share_url), R.drawable.cf_icon_share_s, new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.6
                @Override // java.lang.Runnable
                public void run() {
                    d.this.x.k();
                }
            });
        }
        final StreamingResponse.Authorities authorities = watchingStreaming.j;
        if (authorities != null) {
            if (!watchingStreaming.d) {
                aVarC.a(com.naver.glink.android.sdk.d.a(R.string.live_report_message), R.drawable.cf_icon_report_b, new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.7
                    @Override // java.lang.Runnable
                    public void run() {
                        if (authorities.report.able) {
                            d.this.x.i();
                        } else {
                            Toast.makeText(d.this.getActivity(), authorities.report.errorMessage, 1).show();
                        }
                    }
                });
            }
            if (authorities.deletion.able) {
                aVarC.a(com.naver.glink.android.sdk.d.a(R.string.delete), R.drawable.cf_icon_del_b_s, new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.8
                    @Override // java.lang.Runnable
                    public void run() {
                        d.this.x.h();
                    }
                });
            }
        }
        aVarC.a(getActivity());
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.b.InterfaceC0062b
    public void c(final WatchingStreaming watchingStreaming) {
        ItemsDialogFragmentView.a aVarC = ItemsDialogFragmentView.c();
        for (final StreamingResponse.Quality quality : watchingStreaming.h) {
            final boolean zEquals = TextUtils.equals(quality.title, watchingStreaming.c().title);
            if (zEquals) {
                aVarC.a(watchingStreaming.h.indexOf(quality));
            }
            aVarC.a(quality.title, new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.9
                @Override // java.lang.Runnable
                public void run() {
                    if (zEquals) {
                        return;
                    }
                    d.this.a(watchingStreaming.a, quality);
                }
            });
        }
        aVarC.a(getActivity());
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_streaming_viewer, viewGroup, false);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        this.x.c();
        super.onDestroy();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.b, android.app.Fragment
    public void onPause() {
        this.x.e();
        if (this.x.b() == WatchingStreaming.Type.VIDEO) {
            this.z = this.h.getCurrentPosition();
        }
        this.h.pause();
        super.onPause();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.b, android.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.x.b() == WatchingStreaming.Type.VIDEO) {
            this.h.seekTo(this.z);
        }
        this.h.start();
        this.x.d();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = view.findViewById(R.id.streaming_controls_overlay);
        this.c = view.findViewById(R.id.empty_view_for_chatting1);
        this.d = view.findViewById(R.id.empty_view_for_chatting2);
        this.e = (ImageButton) view.findViewById(R.id.streaming_video_size_toggle);
        this.f = (ImageButton) view.findViewById(R.id.streaming_video_like);
        this.g = (ImageButton) view.findViewById(R.id.streaming_chatting_toggle);
        this.h = (VideoView) view.findViewById(R.id.video_view);
        this.i = (TextView) view.findViewById(R.id.streaming_title);
        this.j = (ImageView) view.findViewById(R.id.streamer_profile);
        this.k = (TextView) view.findViewById(R.id.streamer_nickname);
        this.l = (TextView) view.findViewById(R.id.video_create_date_time);
        this.m = (TextView) view.findViewById(R.id.streaming_viewer_count);
        this.n = (TextView) view.findViewById(R.id.streaming_like_count);
        this.o = (TextView) view.findViewById(R.id.video_like_count);
        this.p = view.findViewById(R.id.streaming_counts_layout);
        this.q = (ImageButton) view.findViewById(R.id.streaming_like);
        this.r = view.findViewById(R.id.streaming_error_layout);
        this.s = (TextView) view.findViewById(R.id.streaming_error_text);
        this.t = (TextView) view.findViewById(R.id.streaming_error_text_for_minimize);
        view.findViewById(R.id.streaming_more).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                d.this.x.f();
            }
        });
        view.findViewById(R.id.streaming_close).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.11
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                d.this.b();
            }
        });
        view.findViewById(R.id.streaming_minimize).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.12
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                d.this.d();
            }
        });
        this.q.setOnClickListener(new t(30) { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.13
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                d.this.w.c();
            }
        });
        if (Build.VERSION.SDK_INT < 23) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.d.14
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    if (d.this.h == null || !d.this.y) {
                        return;
                    }
                    d.this.d(false);
                    d.this.h.getHolder().setSizeFromLayout();
                    d.this.h.start();
                }
            });
        }
        this.x.a();
    }
}
