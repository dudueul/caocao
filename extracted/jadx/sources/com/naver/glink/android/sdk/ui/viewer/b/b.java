package com.naver.glink.android.sdk.ui.viewer.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.ui.viewer.MediaViewerDialog;
import com.naver.glink.android.sdk.ui.widget.dragviewer.DragView;
import com.naver.glink.android.sdk.ui.widget.photoview.PhotoView;
import com.naver.glink.android.sdk.ui.widget.progress.PlugProgressbar;

/* JADX INFO: compiled from: PhotoViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b implements com.naver.glink.android.sdk.ui.viewer.b.a {
    private static final String a = b.class.getSimpleName();
    private final View b;
    private final PhotoView c;
    private float d;
    private final PlugProgressbar e;
    private final View f;
    private final View g;
    private a h;

    /* JADX INFO: compiled from: PhotoViewHolder.java */
    public interface a {
        void a(float f);
    }

    public b(View view) {
        this.b = view;
        this.e = (PlugProgressbar) view.findViewById(R.id.full_image_progress);
        this.f = view.findViewById(R.id.viewer_error_view);
        this.g = view.findViewById(R.id.retry);
        com.naver.glink.android.sdk.d.e().b(this.g, true);
        this.c = (PhotoView) view.findViewById(R.id.full_image);
        this.c.setOnViewTapListener(new com.naver.glink.android.sdk.ui.widget.photoview.d.f() { // from class: com.naver.glink.android.sdk.ui.viewer.b.b.1
            @Override // com.naver.glink.android.sdk.ui.widget.photoview.d.f
            public void a(View view2, float f, float f2) {
                com.naver.glink.android.sdk.a.a.b.c(new MediaViewerDialog.b(-1));
            }
        });
        if (view instanceof DragView) {
            this.h = (DragView) view;
            this.c.setOnScaleChangeListener(new com.naver.glink.android.sdk.ui.widget.photoview.d.e() { // from class: com.naver.glink.android.sdk.ui.viewer.b.b.2
                @Override // com.naver.glink.android.sdk.ui.widget.photoview.d.e
                public void a(float f, float f2, float f3) {
                    if (b.this.h != null) {
                        b.this.h.a(b.this.c.getScale());
                    }
                }
            });
        }
    }

    private void a(final Context context, ArticleMedia articleMedia) {
        if (articleMedia.a(true)) {
            a(articleMedia.b(true), false);
        } else {
            this.f.setVisibility(8);
            Glide.with(context).load(articleMedia.e()).dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).dontTransform().listener((RequestListener<? super Uri, GlideDrawable>) new RequestListener<Uri, GlideDrawable>() { // from class: com.naver.glink.android.sdk.ui.viewer.b.b.4
                @Override // com.bumptech.glide.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(GlideDrawable glideDrawable, Uri uri, Target<GlideDrawable> target, boolean z, boolean z2) {
                    b.this.e.setVisibility(8);
                    b.this.d = b.this.c.getScale();
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onException(Exception exc, Uri uri, Target<GlideDrawable> target, boolean z) {
                    b.this.a(context.getString(R.string.network_error), true);
                    return false;
                }
            }).into(this.c);
        }
    }

    private static void a(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        try {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                }
                drawable.setCallback(null);
            }
            imageView.setImageDrawable(null);
        } catch (Exception e) {
            Log.d(a, "", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, boolean z) {
        this.f.setVisibility(0);
        ((TextView) this.f.findViewById(R.id.message)).setText(str);
        this.g.setVisibility(z ? 0 : 8);
        com.naver.glink.android.sdk.a.a.b.c(new MediaViewerDialog.b(0));
    }

    private void d() {
        if (this.c.getDrawable() instanceof GifDrawable) {
            ((GifDrawable) this.c.getDrawable()).stop();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.b.a
    public View a() {
        return this.b;
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.b.a
    public void a(final Context context, final MediaViewerDialog.From from, final ArticleMedia articleMedia, boolean z) {
        if (z) {
            a(context, articleMedia);
            this.g.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.viewer.b.b.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    b.this.a(context, from, articleMedia, true);
                }
            });
        } else {
            d();
            if (this.d != this.c.getScale()) {
                this.c.setScale(this.d);
            }
        }
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.b.a
    public void b() {
        d();
        Glide.clear(this.c);
        a(this.c);
        this.c.setOnScaleChangeListener(null);
        this.h = null;
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.b.a
    public boolean c() {
        return false;
    }
}
