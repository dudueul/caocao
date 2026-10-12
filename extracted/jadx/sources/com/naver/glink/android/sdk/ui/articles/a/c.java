package com.naver.glink.android.sdk.ui.articles.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.x;

/* JADX INFO: compiled from: PopularViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    private final TextView a;
    private final TextView b;
    private final ViewGroup c;

    public c(View view) {
        this.a = (TextView) view.findViewById(R.id.popular_title_text);
        this.b = (TextView) view.findViewById(R.id.popular_nick_name);
        this.c = (ViewGroup) view.findViewById(R.id.popular_layout);
    }

    public void a(Context context, com.naver.glink.android.sdk.ui.articles.c.b bVar, boolean z, boolean z2) {
        this.a.setText(bVar.b.subject);
        this.b.setText(bVar.b.writerNickname);
        if (!z || z2) {
            ((RelativeLayout.LayoutParams) this.c.getLayoutParams()).rightMargin = x.a(0.0f);
        } else {
            ((RelativeLayout.LayoutParams) this.c.getLayoutParams()).rightMargin = x.a(16.0f);
        }
    }
}
