package com.naver.glink.android.sdk.ui.article.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.ui.article.ArticleFragmentView;
import com.naver.glink.android.sdk.ui.write.model.ImageAttachment;

/* JADX INFO: compiled from: ImageViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d {
    private static final int a = 360;
    private static final int b = 540;
    private boolean c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private ProgressBar g;

    public d(View view) {
        this.d = (ImageView) view.findViewById(R.id.image_view);
        this.e = (ImageView) view.findViewById(R.id.gif_image_view);
        this.f = (ImageView) view.findViewById(R.id.gif_play);
        this.g = (ProgressBar) view.findViewById(R.id.gif_progress_bar);
    }

    private Pair<Integer, Integer> a(int i, int i2) {
        if (i <= 4096 && i2 <= 4096) {
            return Pair.create(Integer.valueOf(i), Integer.valueOf(i2));
        }
        float fMax = (Math.max(i, i2) - 4096) / Math.max(i, i2);
        return Pair.create(Integer.valueOf(i - ((int) (i * fMax))), Integer.valueOf(i2 - ((int) (fMax * i2))));
    }

    private Pair<Integer, Integer> a(Context context, ImageAttachment imageAttachment) {
        int i = com.naver.glink.android.sdk.d.i() ? b : 360;
        int iC = com.naver.glink.android.sdk.d.p().c() - ((int) (context.getResources().getDimension(R.dimen.article_media_item_margin) * 2.0f));
        int iC2 = imageAttachment.c() > i ? iC : (iC * imageAttachment.c()) / i;
        return a(iC2, (imageAttachment.d() * iC2) / imageAttachment.c());
    }

    private void a(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    private void a(ListView listView) {
        d dVar;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= listView.getChildCount()) {
                return;
            }
            View childAt = listView.getChildAt(i2);
            if (childAt != null && (childAt.getTag() instanceof d) && this != (dVar = (d) childAt.getTag())) {
                dVar.a();
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ArticleFragmentView articleFragmentView, ImageAttachment imageAttachment, int i, int i2) {
        a(articleFragmentView.getListView());
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        Glide.with(articleFragmentView.getContext()).load(imageAttachment.g()).asGif().dontAnimate().dontTransform().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(i, i2).listener((RequestListener) new RequestListener<Uri, GifDrawable>() { // from class: com.naver.glink.android.sdk.ui.article.a.d.5
            @Override // com.bumptech.glide.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public boolean onResourceReady(GifDrawable gifDrawable, Uri uri, Target<GifDrawable> target, boolean z, boolean z2) {
                d.this.d.setVisibility(8);
                d.this.e.setVisibility(0);
                d.this.f.setVisibility(8);
                d.this.g.setVisibility(8);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public boolean onException(Exception exc, Uri uri, Target<GifDrawable> target, boolean z) {
                d.this.a();
                return false;
            }
        }).into(this.e);
    }

    public void a() {
        Glide.clear(this.e);
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setVisibility(this.c ? 0 : 8);
        this.g.setVisibility(8);
    }

    public void a(final ArticleFragmentView articleFragmentView, final ImageAttachment imageAttachment, final com.naver.glink.android.sdk.ui.article.c cVar) {
        if (articleFragmentView != null && imageAttachment.c() > 0 && imageAttachment.d() > 0) {
            this.c = imageAttachment.f();
            Pair<Integer, Integer> pairA = a(articleFragmentView.getContext(), imageAttachment);
            final int iIntValue = pairA.first.intValue();
            final int iIntValue2 = pairA.second.intValue();
            a(this.d, iIntValue, iIntValue2);
            a(this.e, iIntValue, iIntValue2);
            this.f.setVisibility(8);
            this.f.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.article.a.d.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    articleFragmentView.d();
                    d.this.a(articleFragmentView, imageAttachment, iIntValue, iIntValue2);
                }
            });
            String string = imageAttachment.h().toString();
            if (!TextUtils.equals(string, (String) this.d.getTag(R.id.glide_image_url_tag))) {
                this.d.setTag(R.id.glide_image_url_tag, string);
                Glide.with(articleFragmentView.getContext()).load(string).asBitmap().override(iIntValue, iIntValue2).centerCrop().dontAnimate().listener((RequestListener<? super String, Bitmap>) new RequestListener<String, Bitmap>() { // from class: com.naver.glink.android.sdk.ui.article.a.d.2
                    @Override // com.bumptech.glide.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public boolean onResourceReady(Bitmap bitmap, String str, Target<Bitmap> target, boolean z, boolean z2) {
                        d.this.d.setTag(R.id.glide_image_url_tag, null);
                        d.this.f.setVisibility(d.this.c ? 0 : 8);
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public boolean onException(Exception exc, String str, Target<Bitmap> target, boolean z) {
                        d.this.d.setTag(R.id.glide_image_url_tag, null);
                        return false;
                    }
                }).into(this.d);
            }
            this.d.setVisibility(0);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.article.a.d.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    articleFragmentView.d();
                    com.naver.glink.android.sdk.ui.viewer.a.a(cVar, imageAttachment);
                }
            });
            Glide.clear(this.e);
            this.e.setVisibility(8);
            this.e.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.article.a.d.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    articleFragmentView.d();
                    com.naver.glink.android.sdk.ui.viewer.a.a(cVar, imageAttachment);
                }
            });
            this.g.setVisibility(8);
        }
    }
}
