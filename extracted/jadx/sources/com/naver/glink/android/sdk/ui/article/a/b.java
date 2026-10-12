package com.naver.glink.android.sdk.ui.article.a;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.ui.article.ArticleFragmentView;

/* JADX INFO: compiled from: FooterViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private final ImageView a;
    private final ImageView b;
    private final TextView c;
    private final TextView d;
    private final TextView e;
    private final TextView f;
    private final TextView g;
    private final TextView h;

    public b(View view) {
        this.a = (ImageView) view.findViewById(R.id.writer_profile_image);
        this.b = (ImageView) view.findViewById(R.id.staff_image);
        this.c = (TextView) view.findViewById(R.id.writer_nickname);
        this.d = (TextView) view.findViewById(R.id.writer_level);
        this.e = (TextView) view.findViewById(R.id.write_date);
        this.f = (TextView) view.findViewById(R.id.read_count);
        this.g = (TextView) view.findViewById(R.id.like_count);
        this.h = (TextView) view.findViewById(R.id.comments_count);
    }

    public void a(final ArticleFragmentView articleFragmentView, final com.naver.glink.android.sdk.ui.article.c cVar, com.naver.glink.android.sdk.api.requests.b.c.C0027b c0027b, boolean z, int i, String str) {
        if (articleFragmentView == null) {
            return;
        }
        Glide.with(articleFragmentView.getContext()).load(cVar.g()).asBitmap().placeholder(R.drawable.cf_img_pfdefault).fitCenter().into(new BitmapImageViewTarget(this.a) { // from class: com.naver.glink.android.sdk.ui.article.a.b.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
            public void setResource(Bitmap bitmap) {
                RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(articleFragmentView.getResources(), bitmap);
                roundedBitmapDrawableCreate.setCircular(true);
                setDrawable(roundedBitmapDrawableCreate);
            }
        });
        this.a.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.b.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.a(cVar.h(), cVar.i());
            }
        });
        ArticleFragmentView.setStaffIconResource(this.b, cVar.j());
        this.c.setText(cVar.k());
        this.c.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.b.3
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.a(cVar.h(), cVar.i());
            }
        });
        this.d.setText(cVar.l());
        this.e.setText(cVar.m());
        if (Responses.i.CAFE_TYPE_R.equals(str)) {
            this.f.setVisibility(8);
        } else {
            this.f.setText(v.a(articleFragmentView.getContext(), cVar.n()));
        }
        if (i != -1) {
            if (Responses.i.CAFE_TYPE_R.equals(str)) {
                this.g.setText("");
            } else {
                this.g.setText(v.a(articleFragmentView.getContext(), i));
            }
            this.g.setCompoundDrawablesWithIntrinsicBounds(z ? R.drawable.cf_icon_like1_pressed : R.drawable.cf_icon_like1_normal, 0, 0, 0);
        }
        this.g.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.b.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.f();
            }
        });
        if (c0027b != null) {
            if (Responses.i.CAFE_TYPE_R.equals(str)) {
                this.h.setText("");
            } else {
                this.h.setText(v.a(articleFragmentView.getContext(), c0027b.comment));
            }
        }
    }
}
