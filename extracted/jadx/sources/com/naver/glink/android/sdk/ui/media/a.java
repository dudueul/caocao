package com.naver.glink.android.sdk.ui.media;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.aa;
import com.naver.glink.android.sdk.a.n;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.tabs.Tab;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: AllMediaAdapter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class a extends BaseAdapter {
    private final Context a;
    private final LayoutInflater b;
    private final int c;
    private final com.naver.glink.android.sdk.ui.media.b d;
    private ArticleMedia e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.media.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AllMediaAdapter.java */
    private class C0045a {
        final ImageView a;
        final ImageView b;
        final ImageView c;
        final TextView d;
        final TextView e;
        final TextView f;
        final View g;
        final View h;

        C0045a(View view) {
            this.a = (ImageView) view.findViewById(R.id.thumbnail_image);
            this.b = (ImageView) view.findViewById(R.id.movie_icon);
            this.c = (ImageView) view.findViewById(R.id.gif_icon);
            this.d = (TextView) view.findViewById(R.id.subject);
            this.e = (TextView) view.findViewById(R.id.date);
            this.f = (TextView) view.findViewById(R.id.movie_play_time);
            this.h = view.findViewById(R.id.movie_gradation);
            this.g = view.findViewById(R.id.selected_overlay);
            this.g.setBackgroundColor(com.naver.glink.android.sdk.d.e().a(102));
            this.d.setVisibility(a.this.d.a == Menu.Type.VIDEOS ? 0 : 8);
            this.e.setVisibility(a.this.d.a != Menu.Type.VIDEOS ? 8 : 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b(Context context, final ArticleMedia articleMedia) {
            if (articleMedia.c()) {
                return false;
            }
            if (LoginHelper.a().isLogin(context)) {
                AlertDialogFragmentView.b(context, context.getString(R.string.join_cafe) + "\n" + context.getString(R.string.all_media_no_permissions)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.media.a.a.4
                    @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c, android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        com.naver.glink.android.sdk.ui.tabs.b.a(Tab.Type.PROFILE);
                    }
                }).a();
            } else {
                LoginHelper.a().builder(context, new Glink.OnLoggedInListener() { // from class: com.naver.glink.android.sdk.ui.media.a.a.3
                    @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
                    public void onLoggedIn(boolean z) {
                        a.this.a(articleMedia);
                        com.naver.glink.android.sdk.a.a.b.c(new c(articleMedia));
                    }
                }).b(context.getString(R.string.require_login_message) + "\n" + context.getString(R.string.all_media_no_permissions), null).a();
            }
            return true;
        }

        void a(final Context context, final ArticleMedia articleMedia) {
            if (articleMedia.a(false)) {
                this.a.setVisibility(4);
                this.d.setText(articleMedia.b(false));
                this.d.setTextColor(Color.parseColor("#a3a9a9"));
                this.e.setText((CharSequence) null);
                aa.a(4, (List<View>) Arrays.asList(this.b, this.c, this.g, this.f, this.h));
                return;
            }
            this.a.setVisibility(4);
            Glide.with(context).load(articleMedia.f()).asBitmap().centerCrop().dontAnimate().override(a.this.i, a.this.j).listener((RequestListener<? super Uri, Bitmap>) new RequestListener<Uri, Bitmap>() { // from class: com.naver.glink.android.sdk.ui.media.a.a.1
                @Override // com.bumptech.glide.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(Bitmap bitmap, Uri uri, Target<Bitmap> target, boolean z, boolean z2) {
                    C0045a.this.a.setVisibility(0);
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onException(Exception exc, Uri uri, Target<Bitmap> target, boolean z) {
                    return false;
                }
            }).into(this.a);
            this.a.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.media.a.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (C0045a.this.b(context, articleMedia)) {
                        return;
                    }
                    a.this.a(articleMedia);
                    com.naver.glink.android.sdk.a.a.b.c(new b(articleMedia));
                }
            });
            this.b.setVisibility(articleMedia.b() ? 0 : 4);
            this.c.setVisibility(articleMedia.gif ? 0 : 4);
            this.f.setVisibility(articleMedia.b() ? 0 : 4);
            this.h.setVisibility(articleMedia.b() ? 0 : 4);
            if (articleMedia.b()) {
                this.d.setText(articleMedia.subject);
                this.e.setText(articleMedia.d());
                this.f.setText(n.b((int) (articleMedia.playTime * TimeUnit.SECONDS.toMillis(1L))));
            }
            boolean zEquals = articleMedia.equals(a.this.e);
            this.g.setVisibility(zEquals ? 0 : 8);
            this.d.setTextColor(zEquals ? com.naver.glink.android.sdk.d.e().a : Color.parseColor("#363838"));
            this.e.setTextColor(zEquals ? com.naver.glink.android.sdk.d.e().a : Color.parseColor("#a3a9a9"));
        }
    }

    /* JADX INFO: compiled from: AllMediaAdapter.java */
    static class b {
        final ArticleMedia a;

        b(ArticleMedia articleMedia) {
            this.a = articleMedia;
        }
    }

    /* JADX INFO: compiled from: AllMediaAdapter.java */
    static class c {
        final ArticleMedia a;

        c(ArticleMedia articleMedia) {
            this.a = articleMedia;
        }
    }

    /* JADX INFO: compiled from: AllMediaAdapter.java */
    private class d {
        private int b;
        private List<View> c = new ArrayList();

        d(ViewGroup viewGroup, LayoutInflater layoutInflater, int i) {
            this.b = i;
            for (int i2 = 0; i2 < i; i2++) {
                View viewInflate = layoutInflater.inflate(R.layout.item_all_media_item, viewGroup, false);
                viewInflate.setTag(a.this.new C0045a(viewInflate));
                View viewFindViewById = viewInflate.findViewById(R.id.thumbnail_layout);
                ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
                layoutParams.height = a.this.j;
                viewFindViewById.setLayoutParams(layoutParams);
                if (i2 > 0) {
                    x.a(viewInflate, a.this.h, -1, -1, -1);
                }
                viewGroup.addView(viewInflate);
                this.c.add(viewInflate);
            }
        }

        void a(Context context, List<ArticleMedia> list) {
            int i = 0;
            while (i < this.b) {
                View view = this.c.get(i);
                final ArticleMedia articleMedia = i < list.size() ? list.get(i) : null;
                if (articleMedia == null) {
                    view.setVisibility(4);
                } else {
                    view.setVisibility(0);
                    if (a.this.d.a()) {
                        view.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.media.a.d.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                a.this.a(articleMedia);
                                com.naver.glink.android.sdk.a.a.b.c(new c(articleMedia));
                            }
                        });
                    }
                    ((C0045a) view.getTag()).a(context, articleMedia);
                }
                i++;
            }
        }
    }

    a(@NonNull Context context, int i, com.naver.glink.android.sdk.ui.media.b bVar) {
        this.a = context;
        this.b = LayoutInflater.from(context);
        this.c = i;
        this.d = bVar;
        this.f = bVar.a() ? x.a(16.0f) : x.a(2.0f);
        this.g = com.naver.glink.android.sdk.d.i() ? x.a(22.0f) : x.a(0.0f);
        this.h = x.a(2.0f);
        this.i = (com.naver.glink.android.sdk.d.p().c() - (this.g * 2)) / i;
        this.j = bVar.a() ? (this.i * 87) / 157 : (this.i * 114) / 157;
    }

    ArticleMedia a() {
        return this.e;
    }

    void a(ArticleMedia articleMedia) {
        this.e = articleMedia;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int iB = this.d.b();
        return (iB % this.c > 0 ? 1 : 0) + (iB / this.c);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        int iMin = Math.min((i + 1) * this.c, this.d.b());
        ArrayList arrayList = new ArrayList();
        for (int i2 = this.c * i; i2 < iMin; i2++) {
            arrayList.add(this.d.a(i2));
        }
        return arrayList;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    @NonNull
    public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
        View viewInflate;
        d dVar;
        if (view == null) {
            viewInflate = this.b.inflate(R.layout.item_all_media_row, viewGroup, false);
            viewInflate.setPadding(this.g, 0, this.g, this.f);
        } else {
            viewInflate = view;
        }
        d dVar2 = (d) viewInflate.getTag();
        if (dVar2 == null) {
            dVar = new d((ViewGroup) viewInflate, this.b, this.c);
            viewInflate.setTag(dVar);
        } else {
            dVar = dVar2;
        }
        dVar.a(this.a, (List) getItem(i));
        return viewInflate;
    }
}
