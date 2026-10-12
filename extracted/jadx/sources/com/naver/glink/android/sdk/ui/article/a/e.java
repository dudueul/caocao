package com.naver.glink.android.sdk.ui.article.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.ui.article.ArticleFragmentView;
import com.naver.glink.android.sdk.ui.write.model.VideoAttachment;

/* JADX INFO: compiled from: VideoViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class e {
    private final View a;
    private final ImageView b;
    private final View c;

    public e(View view) {
        this.a = view;
        this.b = (ImageView) view.findViewById(R.id.video_log);
        this.c = view.findViewById(R.id.video_gradient);
    }

    private int a(Context context) {
        return (int) (((double) (com.naver.glink.android.sdk.d.p().c() - ((int) (context.getResources().getDimension(R.dimen.article_media_item_margin) * 2.0f)))) * 0.56d);
    }

    public void a(final ArticleFragmentView articleFragmentView, final VideoAttachment videoAttachment, final com.naver.glink.android.sdk.ui.article.c cVar) {
        if (articleFragmentView == null) {
            return;
        }
        int iA = a(articleFragmentView.getContext());
        x.a(this.a, -1, iA);
        x.a(this.c, -1, iA / 2);
        String strD = videoAttachment.d();
        if (!TextUtils.equals(strD, (String) this.b.getTag(R.id.glide_image_url_tag))) {
            this.b.setTag(R.id.glide_image_url_tag, strD);
            Glide.with(articleFragmentView.getContext()).load(strD).dontAnimate().listener((RequestListener<? super String, GlideDrawable>) new RequestListener<String, GlideDrawable>() { // from class: com.naver.glink.android.sdk.ui.article.a.e.1
                @Override // com.bumptech.glide.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(GlideDrawable glideDrawable, String str, Target<GlideDrawable> target, boolean z, boolean z2) {
                    e.this.b.setTag(R.id.glide_image_url_tag, null);
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onException(Exception exc, String str, Target<GlideDrawable> target, boolean z) {
                    e.this.b.setTag(R.id.glide_image_url_tag, null);
                    return false;
                }
            }).into(this.b);
        }
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.article.a.e.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                articleFragmentView.d();
                com.naver.glink.android.sdk.ui.viewer.a.a(cVar, videoAttachment);
            }
        });
    }
}
