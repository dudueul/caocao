package com.naver.glink.android.sdk.ui.articles.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Article;

/* JADX INFO: compiled from: ArticleViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private final TextView a;
    private final TextView b;
    private final TextView c;
    private final TextView d;
    private final TextView e;
    private final View f;
    private final ImageView g;
    private final ImageView h;
    private final ImageView i;

    public a(View view) {
        this.a = (TextView) view.findViewById(R.id.title);
        this.b = (TextView) view.findViewById(R.id.writer_nickname);
        this.c = (TextView) view.findViewById(R.id.write_date);
        this.d = (TextView) view.findViewById(R.id.view_count);
        this.e = (TextView) view.findViewById(R.id.comment_count);
        this.f = view.findViewById(R.id.thumbnail_layout);
        this.g = (ImageView) view.findViewById(R.id.thumbnail_image);
        this.h = (ImageView) view.findViewById(R.id.movie_icon);
        this.i = (ImageView) view.findViewById(R.id.gif_icon);
    }

    public void a(Context context, Article article, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            this.a.setText(article.subject);
        } else {
            this.a.setText(com.naver.glink.android.sdk.ui.articles.a.a(article.subject.replaceAll("<b>", "").replaceAll("</b>", ""), str, d.e().a));
        }
        this.b.setText(article.writerNickname);
        this.c.setText(article.getFormattedWriteDate());
        if (Responses.i.CAFE_TYPE_R.equals(str2)) {
            this.e.setText("");
        } else {
            this.e.setText(v.a(context, article.commentCount, 999));
        }
        if (d.j() || Responses.i.CAFE_TYPE_R.equals(str2)) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
            this.d.setText(v.a(context, article.readCount, 9999));
        }
        if (TextUtils.isEmpty(article.thumbnailImage)) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
            Glide.with(context).load(article.thumbnailImage).centerCrop().into(this.g);
        }
        this.h.setVisibility(article.movie ? 0 : 8);
        this.i.setVisibility(article.gif ? 0 : 8);
    }
}
