package com.naver.glink.android.sdk.ui.main;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.d;

/* JADX INFO: compiled from: ExtraViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class a {
    final TextView a;
    final TextView b;
    final View c;
    private final View d;
    private final View e;
    private final View f;
    private final ImageView g;
    private final TextView h;
    private final View i;

    a(View view) {
        this.d = view.findViewById(R.id.close);
        this.e = view.findViewById(R.id.collapse);
        this.f = view.findViewById(R.id.top_layout);
        this.a = (TextView) view.findViewById(R.id.version);
        this.b = (TextView) view.findViewById(R.id.channel_name);
        this.g = (ImageView) view.findViewById(R.id.copyright);
        this.i = view.findViewById(R.id.glink);
        this.c = view.findViewById(R.id.black_overlay_for_video_viewer);
        this.h = (TextView) view.findViewById(R.id.cafe_info);
    }

    private String a() {
        String str = String.format("V%s", d.b().a);
        return !d.o() ? str + "-" + d.b().b.name().charAt(0) : str;
    }

    private boolean a(Context context) {
        return d.b().f() && p.g(context);
    }

    private void b(Context context, com.naver.glink.android.sdk.configure.c cVar) {
        if (d.h()) {
            this.g.setVisibility(0);
            cVar.a(this.g, 102, 14);
            this.g.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.main.a.5
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view) {
                }
            });
            this.b.setVisibility(8);
            Glide.with(context).load("http://cafeimgs.naver.net/plug/default_image/with_naver_cafe.png").into(this.g);
        }
    }

    void a(final Context context, com.naver.glink.android.sdk.configure.c cVar) {
        x.a(this.i, cVar.c, cVar.d);
        cVar.a(this.d, 24, 24);
        this.d.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.main.a.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                d.c(context);
            }
        });
        this.e.setVisibility(p.h(context) ? 0 : 8);
        cVar.a(this.e, 24, 24);
        this.e.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.main.a.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                b.a();
            }
        });
        if (cVar.a) {
            x.a(this.f, x.a(6.0f), 0, cVar.a(6), 0);
        }
        this.a.setText(a());
        if (a(context)) {
            this.b.setVisibility(0);
            this.b.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.main.a.3
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view) {
                    b.a(context);
                }
            });
        } else {
            this.b.setVisibility(8);
        }
        this.h.setText("" + context.getResources().getString(d.l() ? R.string.plug_info : R.string.cafe_info));
        this.h.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.main.a.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                com.naver.glink.android.sdk.ui.tabs.b.c();
            }
        });
        b(context, cVar);
    }
}
