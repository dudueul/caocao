package com.naver.glink.android.sdk.ui.articles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.e;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Article;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.input.CommentInputPresenter;
import com.naver.glink.android.sdk.ui.write.WriteFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ArticlesAdapter extends ArrayAdapter<c> {
    private static final int a = 10;
    protected static final int b = 20;
    private static final int d = -1;
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    protected Responses.c c;
    private boolean i;
    private int j;
    private String k;
    private Menu l;
    private int m;
    private Request<Responses.c> n;
    private a o;
    private AbsListView.OnScrollListener p;
    private SORT_BY q;
    private String r;
    private String s;

    public enum SORT_BY {
        NEWEST(0),
        ACC(1);

        final int type;

        SORT_BY(int i) {
            this.type = i;
        }

        public int getType() {
            return this.type;
        }
    }

    public interface a {
        void a(int i, Responses.c cVar, PlugError plugError);
    }

    public static class b extends c {
        public final int a;
        public final String b;

        b(int i, String str) {
            super(i, null);
            this.a = i;
            this.b = str;
        }
    }

    public static class c {
        public final int c;
        public final Article d;

        c(int i, Article article) {
            this.c = i;
            this.d = article;
        }
    }

    public ArticlesAdapter(Context context) {
        super(context, 0);
        this.q = SORT_BY.NEWEST;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<c> a(Responses.c cVar) {
        ArrayList arrayList = new ArrayList();
        List<Integer> listN = p.n(getContext());
        if (cVar.notices != null) {
            for (Article article : cVar.notices) {
                if (!listN.contains(Integer.valueOf(article.articleId))) {
                    arrayList.add(new c(1, article));
                }
            }
        }
        if (cVar.articles.isEmpty() && !this.i) {
            arrayList.add(new c(2, null));
        } else if (!cVar.articles.isEmpty()) {
            Iterator<Article> it = cVar.articles.iterator();
            while (it.hasNext()) {
                arrayList.add(new c(0, it.next()));
            }
        }
        return arrayList;
    }

    private void a(int i, View view) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingLeft(), i == getCount() + (-1) ? x.a(5.0f) : 0);
    }

    private c b(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= getCount()) {
                return null;
            }
            c item = getItem(i3);
            if (item.d != null && item.d.articleId == i) {
                return item;
            }
            i2 = i3 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.n != null || this.m == -1) {
            return;
        }
        b(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (this.j != -1 || d.h()) {
            final int i = this.m + 1;
            this.n = a(i);
            this.n.showProgress(z).execute(getContext(), new RequestListener<Responses.c>() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesAdapter.3
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.c cVar) {
                    if (i == 1) {
                        ArticlesAdapter.this.clear();
                        if (ArticlesAdapter.this.l == null) {
                            ArticlesAdapter.this.l = cVar.menu;
                        }
                    }
                    ArticlesAdapter.this.addAll(ArticlesAdapter.this.a(cVar));
                    ArticlesAdapter.this.c = cVar;
                    ArticlesAdapter.this.m = ArticlesAdapter.this.b(cVar) ? -1 : ArticlesAdapter.this.m + 1;
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onFinally(Responses.c cVar, PlugError plugError) {
                    com.naver.glink.android.sdk.a.a.b.c(new ArticlesFragmentView.a());
                    if (ArticlesAdapter.this.o != null) {
                        ArticlesAdapter.this.o.a(i, cVar, plugError);
                    }
                    ArticlesAdapter.this.n = null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Responses.c cVar) {
        if (cVar.articles.isEmpty()) {
            return true;
        }
        return c() ? cVar.metadata.isLastPage : cVar.metadata.isLast;
    }

    private boolean c() {
        return !TextUtils.isEmpty(this.k);
    }

    private boolean d() {
        return !TextUtils.isEmpty(this.s);
    }

    public Menu a() {
        return this.l;
    }

    protected Request<Responses.c> a(int i) {
        if (c()) {
            return f.a(this.j, this.k, i, 20, this.q.getType());
        }
        if (d()) {
            return f.g(this.s);
        }
        return f.a(this.j, this.c == null ? -1 : this.c.metadata.lastArticleId, 20);
    }

    void a(int i, String str) {
        this.s = str;
        a(i, true);
    }

    public void a(int i, final boolean z) {
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
        this.j = i;
        this.m = 0;
        this.l = null;
        this.c = null;
        if (this.r == null) {
            com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesAdapter.2
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Responses.i iVar) {
                    ArticlesAdapter.this.r = iVar.cafeType;
                    ArticlesAdapter.this.b(z);
                }
            });
        } else {
            b(z);
        }
    }

    public void a(AbsListView.OnScrollListener onScrollListener) {
        this.p = onScrollListener;
    }

    public void a(ListView listView) {
        listView.setAdapter((ListAdapter) this);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesAdapter.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (ArticlesAdapter.this.m == 0) {
                    return;
                }
                if (i3 < i + i2 + 10) {
                    ArticlesAdapter.this.b();
                }
                if (ArticlesAdapter.this.p != null) {
                    ArticlesAdapter.this.p.onScroll(absListView, i, i2, i3);
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (ArticlesAdapter.this.p != null) {
                    ArticlesAdapter.this.p.onScrollStateChanged(absListView, i);
                }
            }
        });
    }

    @Subscribe
    public void a(e.b bVar) {
        c cVarB = b(bVar.articleId);
        if (cVarB == null) {
            return;
        }
        cVarB.d.likeCount = bVar.count;
        cVarB.d.likeIt = bVar.isReacted;
        notifyDataSetChanged();
    }

    void a(Menu menu, String str, SORT_BY sort_by) {
        this.k = str;
        clear();
        if (!TextUtils.isEmpty(str)) {
            this.q = sort_by;
            a(menu.getMenuId(), true);
        }
        this.l = menu;
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.article.b.a aVar) {
        c cVarB = b(aVar.a);
        if (cVarB == null) {
            return;
        }
        remove(cVarB);
        notifyDataSetChanged();
    }

    public void a(a aVar) {
        this.o = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe
    public void a(CommentInputPresenter.a aVar) {
        c cVarB;
        if (aVar.d == null || !aVar.d.success || (cVarB = b(aVar.b.articleId)) == null) {
            return;
        }
        cVarB.d.commentCount = ((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.d.result).count.total;
        notifyDataSetChanged();
    }

    @Subscribe
    public void a(WriteFragmentView.a aVar) {
        if (this.j == aVar.a.menuId || this.j == 0) {
            a(this.j, true);
        }
    }

    public void a(String str) {
        add(new b(3, str));
    }

    public void a(boolean z) {
        this.i = z;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return getItem(i).c;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @NonNull
    public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
        com.naver.glink.android.sdk.ui.articles.a.b bVar;
        com.naver.glink.android.sdk.ui.articles.a.a aVar;
        switch (getItemViewType(i)) {
            case 0:
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_articles_article, viewGroup, false);
                }
                com.naver.glink.android.sdk.ui.articles.a.a aVar2 = (com.naver.glink.android.sdk.ui.articles.a.a) view.getTag();
                if (aVar2 == null) {
                    com.naver.glink.android.sdk.ui.articles.a.a aVar3 = new com.naver.glink.android.sdk.ui.articles.a.a(view);
                    view.setTag(aVar3);
                    aVar = aVar3;
                } else {
                    aVar = aVar2;
                }
                aVar.a(getContext(), getItem(i).d, this.k, this.r);
                a(i, view);
                return view;
            case 1:
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_articles_notice, viewGroup, false);
                }
                com.naver.glink.android.sdk.ui.articles.a.b bVar2 = (com.naver.glink.android.sdk.ui.articles.a.b) view.getTag();
                if (bVar2 == null) {
                    com.naver.glink.android.sdk.ui.articles.a.b bVar3 = new com.naver.glink.android.sdk.ui.articles.a.b(view);
                    view.setTag(bVar3);
                    bVar = bVar3;
                } else {
                    bVar = bVar2;
                }
                bVar.a(getContext(), getItem(i).d);
                return view;
            case 2:
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_profile_articles_empty, viewGroup, false);
                    int iA = d.j() ? x.a(80.0f) : x.a(40.0f);
                    view.setPadding(0, iA, 0, iA);
                }
                ((TextView) view.findViewById(R.id.tv_no_article_error)).setText(R.string.article_empty);
                return view;
            case 3:
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_profile_articles_empty, viewGroup, false);
                    int iA2 = d.j() ? x.a(80.0f) : x.a(40.0f);
                    view.setPadding(0, iA2, 0, iA2);
                }
                c item = getItem(i);
                ((TextView) view.findViewById(R.id.tv_no_article_error)).setText(item instanceof b ? ((b) item).b : "");
                return view;
            default:
                throw new IllegalStateException("지원하지 않는 view type 입니다.");
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 4;
    }
}
