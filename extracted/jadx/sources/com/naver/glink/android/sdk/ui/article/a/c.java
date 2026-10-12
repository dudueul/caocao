package com.naver.glink.android.sdk.ui.article.a;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.ui.article.ArticleFragmentView;

/* JADX INFO: compiled from: HeaderViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    private final TextView a;
    private final View b;
    private final TextView c;
    private final ImageView d;
    private final ImageView e;

    public c(View view) {
        this.a = (TextView) view.findViewById(R.id.menu_name);
        this.b = view.findViewById(R.id.more_actions);
        this.c = (TextView) view.findViewById(R.id.subject);
        this.d = (ImageView) view.findViewById(R.id.translate);
        this.e = (ImageView) view.findViewById(R.id.share);
        com.naver.glink.android.sdk.d.e().b(this.a, false);
        com.naver.glink.android.sdk.d.e().b(this.d);
    }

    public void a(final ArticleFragmentView articleFragmentView, final com.naver.glink.android.sdk.ui.article.c cVar) {
        if (articleFragmentView == null) {
            return;
        }
        this.a.setText(cVar.e());
        this.a.setTextColor(com.naver.glink.android.sdk.d.e().a);
        this.a.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.c.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.a(cVar.f());
            }
        });
        this.c.setText(cVar.c());
        this.b.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.c.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.a(view);
            }
        });
        this.d.setVisibility(com.naver.glink.android.sdk.d.s() ? 0 : 8);
        this.d.setActivated(cVar.b());
        this.d.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.c.3
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.e();
            }
        });
        this.e.setVisibility(TextUtils.isEmpty(cVar.o()) ? 8 : 0);
        this.e.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.c.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", cVar.o());
                articleFragmentView.getContext().startActivity(Intent.createChooser(intent, null));
            }
        });
    }
}
