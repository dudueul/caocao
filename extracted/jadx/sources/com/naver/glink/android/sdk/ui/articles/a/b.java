package com.naver.glink.android.sdk.ui.articles.a;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.model.Article;

/* JADX INFO: compiled from: NoticeViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private final View a;
    private final TextView b;
    private final TextView c;
    private final TextView d;
    private final View e;

    public b(View view) {
        this.a = view;
        this.b = (TextView) view.findViewById(R.id.notice_type);
        this.c = (TextView) view.findViewById(R.id.notice_date);
        this.d = (TextView) view.findViewById(R.id.notice_subject);
        this.e = view.findViewById(R.id.header_notice_close);
    }

    public void a(final Context context, final Article article) {
        this.a.setBackgroundColor(Color.parseColor("#f8f8f8"));
        this.b.setText(context.getResources().getString(R.string.pin_to_top));
        this.c.setText(article.getFormattedWriteDate());
        this.d.setText(article.subject);
        this.e.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.articles.a.b.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                com.naver.glink.android.sdk.a.a.b.c(new com.naver.glink.android.sdk.ui.article.b.a(article.articleId));
                p.a(context, article.articleId);
            }
        });
        this.a.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.articles.a.b.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                com.naver.glink.android.sdk.ui.tabs.b.a(article.articleId);
            }
        });
    }
}
