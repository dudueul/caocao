package com.naver.glink.android.sdk.ui.viewer.b;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.y;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.api.requests.g;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.widget.video.PlugVideoView;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: compiled from: VideoViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class c {
    private static final String c = c.class.getSimpleName();
    public PlugVideoView a;
    public View b;
    private View d;
    private ImageView e;
    private ImageView f;
    private View g;
    private View h;
    private View i;
    private a j;

    /* JADX INFO: compiled from: VideoViewHolder.java */
    public interface a {
        void a(boolean z);
    }

    public c(View view, boolean z) {
        this.a = (PlugVideoView) view.findViewById(R.id.video_view);
        this.b = view.findViewById(R.id.video_error_view);
        this.d = view.findViewById(R.id.video_encoding_layout);
        this.e = (ImageView) view.findViewById(R.id.video_thumb);
        this.f = (ImageView) view.findViewById(R.id.video_thumb_play);
        this.g = view.findViewById(R.id.video_data_alert_layout);
        this.h = view.findViewById(R.id.btn_confirm);
        this.i = view.findViewById(R.id.cancel);
        if (z) {
            this.a.setFullButtonVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.e.setVisibility(8);
        this.f.setVisibility(8);
        f.e(str).execute(context, new RequestListener<Responses.t>() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.2
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.t tVar) {
                if (!tVar.isEncodingComplete || TextUtils.isEmpty(tVar.playUrl)) {
                    c.this.d.setVisibility(0);
                    return;
                }
                try {
                    c.this.a.setVideoURI(y.a(tVar.playUrl));
                    c.this.a.setShouldAutoplay(true);
                    c.this.g.setVisibility(8);
                } catch (Exception e) {
                    Log.d(c.c, "", e);
                }
                c.this.b(context, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, String str) {
        Request<g.a> requestA = g.a(str);
        if (requestA != null) {
            requestA.execute(context);
        }
    }

    public void a() {
        this.e.setVisibility(0);
        this.f.setVisibility(0);
    }

    public void a(final Context context, final String str, String str2) {
        if (context == null || this.a == null) {
            return;
        }
        this.g.setVisibility(8);
        this.b.setVisibility(8);
        this.d.setVisibility(8);
        this.e.setVisibility(0);
        this.f.setVisibility(0);
        this.a.j();
        this.a.setShouldAutoplay(false);
        this.a.setControlsToggleEnabled(false);
        this.a.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                c.this.a.setControlsToggleEnabled(true);
            }
        });
        this.a.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.a.setOnFullScreenListener(new PlugVideoView.a() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.4
            @Override // com.naver.glink.android.sdk.ui.widget.video.PlugVideoView.a
            public void a() {
                com.naver.glink.android.sdk.ui.main.b.a(c.this.a);
            }
        });
        this.a.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.5
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                c.this.a.setFullscreen(false);
                c.this.a(context.getString(R.string.unable_load_videos));
                return false;
            }
        });
        if (!TextUtils.equals(str2, (String) this.e.getTag(R.id.glide_image_url_tag))) {
            this.e.setTag(R.id.glide_image_url_tag, str2);
            Glide.with(context).load(str2).listener((com.bumptech.glide.request.RequestListener<? super String, GlideDrawable>) new com.bumptech.glide.request.RequestListener<String, GlideDrawable>() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.6
                @Override // com.bumptech.glide.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(GlideDrawable glideDrawable, String str3, Target<GlideDrawable> target, boolean z, boolean z2) {
                    c.this.a.setVisibility(0);
                    c.this.e.setTag(R.id.glide_image_url_tag, null);
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onException(Exception exc, String str3, Target<GlideDrawable> target, boolean z) {
                    c.this.e.setTag(R.id.glide_image_url_tag, null);
                    return false;
                }
            }).dontAnimate().into(this.e);
        }
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                c.this.a(true);
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                c.this.g.setVisibility(8);
                c.this.a(false);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                c.this.g.setVisibility(8);
            }
        });
        a(new a() { // from class: com.naver.glink.android.sdk.ui.viewer.b.c.10
            @Override // com.naver.glink.android.sdk.ui.viewer.b.c.a
            public void a(boolean z) {
                if (!com.naver.plug.android.core.a.b.a(context)) {
                    AlertDialogFragmentView.b(context, context.getString(R.string.internet_not_connected_error)).a();
                } else if (!z || com.naver.plug.android.core.a.b.b(context)) {
                    c.this.a(context, str);
                } else {
                    c.this.g.setVisibility(0);
                }
            }
        });
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    public void a(String str) {
        ((TextView) this.b.findViewById(R.id.message)).setText(str);
        this.b.setVisibility(0);
    }

    public void a(boolean z) {
        if (this.j != null) {
            this.j.a(z);
        }
    }

    public void b() {
        if (this.a.w()) {
            this.a.i();
        }
    }

    public a c() {
        return this.j;
    }
}
