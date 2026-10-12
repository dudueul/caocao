package com.naver.glink.android.sdk.ui.streaming.streamings.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.n;
import com.naver.glink.android.sdk.a.q;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.api.streaming.StreamingVideosResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingsResponse;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: StreamingViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private final ImageView a;
    private final ImageView b;
    private final TextView c;
    private final TextView d;
    private final TextView e;
    private final TextView f;
    private final TextView g;
    private final View h;
    private final View i;

    public b(View view) {
        this.a = (ImageView) view.findViewById(R.id.streaming_thumbnail);
        this.b = (ImageView) view.findViewById(R.id.live_icon);
        this.c = (TextView) view.findViewById(R.id.play_time);
        this.d = (TextView) view.findViewById(R.id.streamer_nickname_time);
        this.e = (TextView) view.findViewById(R.id.streaming_title);
        this.f = (TextView) view.findViewById(R.id.view_count);
        this.g = (TextView) view.findViewById(R.id.like_count);
        this.h = view.findViewById(R.id.live_encoding);
        this.i = view.findViewById(R.id.play_thumb);
    }

    private void a(Context context, String str) {
        this.a.setVisibility(4);
        Glide.with(context).load(str).asBitmap().listener((RequestListener<? super String, TranscodeType>) new RequestListener<String, Bitmap>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.a.b.1
            @Override // com.bumptech.glide.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public boolean onResourceReady(Bitmap bitmap, String str2, Target<Bitmap> target, boolean z, boolean z2) {
                b.this.a.setVisibility(0);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public boolean onException(Exception exc, String str2, Target<Bitmap> target, boolean z) {
                return false;
            }
        }).into(this.a);
    }

    public void a(Context context, StreamingVideosResponse.StreamingVideo streamingVideo) {
        a(context, streamingVideo.thumbnailImage);
        this.c.setVisibility(0);
        this.c.setText(n.b((int) (streamingVideo.playTime * TimeUnit.SECONDS.toMillis(1L))));
        this.b.setVisibility(8);
        this.e.setText(streamingVideo.title);
        this.f.setText(v.a(context, streamingVideo.viewCount, Integer.MAX_VALUE));
        this.g.setText(v.a(context, streamingVideo.videoLikeCount, Integer.MAX_VALUE));
        this.h.setVisibility(streamingVideo.encoded ? 8 : 0);
        this.i.setVisibility(0);
        this.d.setText(streamingVideo.nickName + " · " + q.a(streamingVideo.createDateTime));
        this.f.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cf_icon_vod_play_s, 0, 0, 0);
        this.g.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cf_icon_like_3_s, 0, 0, 0);
    }

    public void a(Context context, StreamingsResponse.Streaming streaming) {
        a(context, streaming.thumbnailImage);
        this.c.setVisibility(8);
        this.b.setVisibility(0);
        this.d.setText(streaming.nickName);
        this.e.setText(streaming.title);
        this.f.setText(v.a(context, streaming.viewCount, Integer.MAX_VALUE));
        this.g.setText(v.a(context, streaming.likeCount, Integer.MAX_VALUE));
        this.h.setVisibility(8);
        this.i.setVisibility(8);
        this.f.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cf_icon_liveread_s, 0, 0, 0);
        this.g.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cf_icon_dublelike_3_s, 0, 0, 0);
    }
}
