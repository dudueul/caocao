package com.naver.glink.android.sdk.ui.articles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.model.Article;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.Iterator;

/* JADX INFO: compiled from: PopularSimpleListAdapter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c extends ArrayAdapter<b> {
    private static final int a = 3;
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private boolean e;
    private Request<Responses.c> f;
    private a g;

    /* JADX INFO: compiled from: PopularSimpleListAdapter.java */
    public interface a {
        void a(Responses.c cVar, PlugError plugError);
    }

    /* JADX INFO: compiled from: PopularSimpleListAdapter.java */
    public static class b {
        public int a;
        public Article b;

        public b(int i, Article article) {
            this.a = i;
            this.b = article;
        }
    }

    public c(Context context) {
        super(context, 0);
    }

    private void c() {
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
        clear();
        notifyDataSetInvalidated();
        this.f = f.g("H");
        this.f.execute(getContext(), new RequestListener<Responses.c>() { // from class: com.naver.glink.android.sdk.ui.articles.c.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.c cVar) {
                c.this.f = null;
                if (cVar.articles.size() == 0) {
                    return;
                }
                c.this.e = cVar.articles.size() >= 3;
                c.this.a();
                c.this.a(cVar);
                c.this.a(c.this.e);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinally(Responses.c cVar, PlugError plugError) {
                c.this.f = null;
                if (c.this.g != null) {
                    c.this.g.a(cVar, plugError);
                }
            }
        });
    }

    public void a() {
        add(new b(1, null));
    }

    public void a(Responses.c cVar) {
        Iterator<Article> it = cVar.articles.iterator();
        while (it.hasNext()) {
            add(new b(0, it.next()));
        }
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void a(boolean z) {
        if (z) {
            add(new b(2, null));
        }
    }

    public void b() {
        c();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return getItem(i).a;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @NonNull
    public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
        com.naver.glink.android.sdk.ui.articles.a.c cVar;
        switch (getItemViewType(i)) {
            case 0:
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_popular_article, viewGroup, false);
                }
                com.naver.glink.android.sdk.ui.articles.a.c cVar2 = (com.naver.glink.android.sdk.ui.articles.a.c) view.getTag();
                if (cVar2 == null) {
                    com.naver.glink.android.sdk.ui.articles.a.c cVar3 = new com.naver.glink.android.sdk.ui.articles.a.c(view);
                    view.setTag(cVar3);
                    cVar = cVar3;
                } else {
                    cVar = cVar2;
                }
                b item = getItem(i);
                if (item == null) {
                    return view;
                }
                cVar.a(getContext(), item, i == getCount() + (-1), this.e);
                return view;
            case 1:
                return view == null ? LayoutInflater.from(getContext()).inflate(R.layout.item_popular_header, viewGroup, false) : view;
            case 2:
                return view == null ? LayoutInflater.from(getContext()).inflate(R.layout.item_popular_footer, viewGroup, false) : view;
            default:
                throw new IllegalStateException("지원하지 않는 type 입니다.");
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 3;
    }
}
