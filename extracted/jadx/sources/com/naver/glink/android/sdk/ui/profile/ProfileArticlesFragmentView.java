package com.naver.glink.android.sdk.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.places.model.PlaceFields;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.c;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Article;
import com.naver.glink.android.sdk.ui.parent.PlugListFragmentView;
import com.naver.glink.android.sdk.ui.profile.articles.ProfileTab;
import com.naver.glink.android.sdk.ui.tabs.Tab;
import com.naver.glink.android.sdk.ui.tabs.b;
import com.naver.glink.android.sdk.ui.write.WriteFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ProfileArticlesFragmentView extends PlugListFragmentView {
    public static final int a = -1;
    public static final String b = "-1";
    public static final int c = -1;
    private static final int d = 10;
    private static final int e = 5;
    private static final String f = "com.naver.glink.OTHERPROFILE_ID";
    private static final String i = "com.naver.glink.TYPE";
    private SwipeRefreshLayout j;
    private a k;
    private View l;
    private int m;
    private int n;
    private int o;
    private String p;
    private ProfileTab.Type q;
    private Request<Responses.p> r;
    private Responses.p s;
    private String t;

    private class a extends ArrayAdapter<Object> {
        private static final int b = 0;
        private static final int c = 1;
        private static final int d = 2;
        private static final int e = 3;
        private static final String f = "lastArticleId";
        private static final String g = "page";
        private static final String h = "lastLikedItDateTime";
        private static final String i = "offset";
        private ProfileTab.Type j;

        public a(Context context) {
            super(context, 0, new ArrayList());
            this.j = ProfileArticlesFragmentView.this.q;
            if (ProfileTab.Type.WRITE.equals(this.j) || ProfileTab.Type.COMMENT.equals(this.j) || ProfileTab.Type.LIKE.equals(this.j)) {
                return;
            }
            this.j = ProfileTab.Type.WRITE;
        }

        private View a(int i2, View view, ViewGroup viewGroup) {
            Article article = (Article) getItem(i2);
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_articles_article, viewGroup, false);
            }
            com.naver.glink.android.sdk.ui.articles.a.a aVar = (com.naver.glink.android.sdk.ui.articles.a.a) view.getTag();
            if (aVar == null) {
                aVar = new com.naver.glink.android.sdk.ui.articles.a.a(view);
                view.setTag(aVar);
            }
            aVar.a(getContext(), article, null, ProfileArticlesFragmentView.this.t);
            return view;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private View a(View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_profile_articles_empty, viewGroup, false);
                if (d.i()) {
                    view.setPadding(0, viewGroup.getHeight() / 8, 0, 0);
                } else {
                    view.setPadding(0, viewGroup.getHeight() / 4, 0, 0);
                }
                view.invalidate();
            }
            TextView textView = (TextView) view.findViewById(R.id.tv_no_article_error);
            switch (a()) {
                case WRITE:
                    textView.setText(ProfileArticlesFragmentView.this.c(R.string.error_no_article_wirte));
                    return view;
                case COMMENT:
                    textView.setText(ProfileArticlesFragmentView.this.c(R.string.error_no_article_comment));
                    return view;
                case LIKE:
                    textView.setText(ProfileArticlesFragmentView.this.c(R.string.error_no_article_like));
                    return view;
                default:
                    return view;
            }
        }

        private void a(int i2, View view) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingLeft(), i2 == getCount() + (-1) ? x.a(8.0f) : 0);
        }

        private View b(View view, ViewGroup viewGroup) {
            return view == null ? LayoutInflater.from(getContext()).inflate(R.layout.item_articles_deleted_article, viewGroup, false) : view;
        }

        public ProfileTab.Type a() {
            return this.j;
        }

        public void a(boolean z, boolean z2) {
            if (z) {
                c();
            }
            ProfileArticlesFragmentView.this.n = 0;
            ProfileArticlesFragmentView.this.m = 1;
            if (ProfileArticlesFragmentView.this.r != null) {
                ProfileArticlesFragmentView.this.r.cancel();
                ProfileArticlesFragmentView.this.r = null;
            }
            if (z2) {
                b.h();
            }
            ProfileArticlesFragmentView.this.m();
        }

        public void b() {
            add(1);
        }

        public void c() {
            for (int count = getCount() - 1; count >= 0; count--) {
                if (getItem(count) instanceof Article) {
                    remove(getItem(count));
                }
            }
        }

        public void d() {
            for (int count = getCount() - 1; count >= 0; count--) {
                if ((getItem(count) instanceof Integer) && getItem(count) == 1) {
                    remove(1);
                }
            }
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i2) {
            if (getItem(i2) instanceof Article) {
                return ((Article) getItem(i2)).articleId == -1 ? 3 : 0;
            }
            return ((Integer) getItem(i2)).intValue();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        @NonNull
        public View getView(int i2, View view, @NonNull ViewGroup viewGroup) {
            switch (getItemViewType(i2)) {
                case 0:
                    return a(i2, view, viewGroup);
                case 1:
                    return a(view, viewGroup);
                case 2:
                    return view == null ? LayoutInflater.from(getContext()).inflate(R.layout.item_profile_pending_message, viewGroup, false) : view;
                case 3:
                    return b(view, viewGroup);
                default:
                    throw new IllegalStateException("지원하지 않는 view type 입니다.");
            }
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 4;
        }
    }

    public ProfileArticlesFragmentView(Context context) {
        super(context);
        this.m = 1;
        this.n = 0;
        this.o = 0;
    }

    public static ProfileArticlesFragmentView a(Context context, ProfileTab.Type type) {
        return a(context, "-1", type);
    }

    public static ProfileArticlesFragmentView a(Context context, String str, ProfileTab.Type type) {
        Bundle bundle = new Bundle();
        ProfileArticlesFragmentView profileArticlesFragmentView = new ProfileArticlesFragmentView(context);
        bundle.putString(f, str);
        bundle.putSerializable(i, type);
        profileArticlesFragmentView.setArguments(bundle);
        return profileArticlesFragmentView;
    }

    private String a(Article article) {
        switch (this.k.a()) {
            case WRITE:
                return String.valueOf(article.articleId);
            case COMMENT:
                return String.valueOf(this.m);
            case LIKE:
                return (!d.l() || this.s == null) ? article.likedItDateTime : String.valueOf(this.s.metadata.nextOffset);
            default:
                return String.valueOf(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Responses.p pVar) {
        switch (this.k.a()) {
            case WRITE:
                return pVar.articles.size() < 10;
            case COMMENT:
                return pVar.metadata.isLastPage;
            case LIKE:
                if (d.l()) {
                    return pVar.metadata.lastPage || pVar.metadata.nextOffset == -1;
                }
                return pVar.articles.size() < 10;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        TextView textView = (TextView) this.l.findViewById(R.id.profile_articles_count);
        textView.setText(String.format(c(R.string.article_count_message), Integer.valueOf(this.o)));
        textView.setVisibility(0);
    }

    static /* synthetic */ int g(ProfileArticlesFragmentView profileArticlesFragmentView) {
        int i2 = profileArticlesFragmentView.m;
        profileArticlesFragmentView.m = i2 + 1;
        return i2;
    }

    private String getPageString() {
        switch (this.k.a()) {
            case WRITE:
                return "lastArticleId";
            case COMMENT:
                return PlaceFields.PAGE;
            case LIKE:
                return d.l() ? "offset" : "lastLikedItDateTime";
            default:
                return "lastArticleId";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.r != null) {
            return;
        }
        String strValueOf = String.valueOf(-1);
        String pageString = "lastArticleId";
        if (this.k != null && this.k.getCount() > 3 && (this.k.getItem(this.k.getCount() - 1) instanceof Article)) {
            strValueOf = a((Article) this.k.getItem(this.k.getCount() - 1));
            pageString = getPageString();
        }
        this.k.d();
        if (d.k()) {
            this.r = f.b(this.k.a().getTypeName(), strValueOf, pageString, this.p);
        } else if (d.l()) {
            this.r = c.a(this.k.a().getTypeName(), strValueOf, pageString, this.p);
        }
        this.r.execute(getContext(), new RequestListener<Responses.p>() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileArticlesFragmentView.5
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.p pVar) {
                ProfileArticlesFragmentView.this.s = pVar;
                ProfileArticlesFragmentView.this.n += pVar.articles.size();
                if (!ProfileTab.Type.LIKE.equals(ProfileArticlesFragmentView.this.k.a()) && pVar.metadata.totalCount < ProfileArticlesFragmentView.this.n) {
                    ProfileArticlesFragmentView.this.m = -1;
                    return;
                }
                ProfileArticlesFragmentView.this.k.addAll(pVar.articles);
                ProfileArticlesFragmentView.g(ProfileArticlesFragmentView.this);
                if (ProfileArticlesFragmentView.this.n == 0) {
                    ProfileArticlesFragmentView.this.k.b();
                }
                if (ProfileArticlesFragmentView.this.a(pVar)) {
                    ProfileArticlesFragmentView.this.m = -1;
                }
                ProfileArticlesFragmentView.this.o = pVar.metadata.totalCount;
                ProfileArticlesFragmentView.this.f();
                ProfileArticlesFragmentView.this.g();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinally(Responses.p pVar, PlugError plugError) {
                ProfileArticlesFragmentView.this.r = null;
                b.i();
                ProfileArticlesFragmentView.this.j.setRefreshing(false);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                ProfileArticlesFragmentView.this.a(plugError);
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_profile_articles, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        if (getArguments() != null) {
            this.p = getArguments().getString(f);
            this.q = (ProfileTab.Type) getArguments().getSerializable(i);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView, com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        if (bundle != null) {
            return;
        }
        this.j = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.j.setColorSchemeColors(d.e().a);
        this.j.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileArticlesFragmentView.1
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                ProfileArticlesFragmentView.this.getListView().clearChoices();
                ProfileArticlesFragmentView.this.c();
            }
        });
        this.j.setProgressViewOffset(false, x.a(48.0f), x.a(112.0f));
        this.l = LayoutInflater.from(getContext()).inflate(R.layout.item_profile_articles_header, (ViewGroup) getListView(), false);
        getListView().addHeaderView(this.l);
        this.k = new a(getContext());
        setListAdapter(this.k);
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileArticlesFragmentView.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                if (ProfileArticlesFragmentView.this.n == 0 || absListView.getLastVisiblePosition() + 5 <= i4 || ProfileArticlesFragmentView.this.m == -1) {
                    return;
                }
                ProfileArticlesFragmentView.this.m();
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
            }
        });
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileArticlesFragmentView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                b.f();
            }
        });
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileArticlesFragmentView.4
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                if (iVar == null) {
                    return;
                }
                ProfileArticlesFragmentView.this.t = iVar.cafeType;
                if (ProfileArticlesFragmentView.this.t == null || !Responses.i.CAFE_TYPE_R.equals(ProfileArticlesFragmentView.this.t)) {
                    ProfileArticlesFragmentView.this.l.setVisibility(0);
                } else {
                    ProfileArticlesFragmentView.this.l.setVisibility(4);
                    ProfileArticlesFragmentView.this.l.getLayoutParams().height = x.a(48.0f);
                }
                ProfileArticlesFragmentView.this.k.a(false, true);
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView
    public void a(ListView listView, View view, int i2, long j) {
        if (listView.getItemAtPosition(i2) instanceof Article) {
            b.a(((Article) listView.getItemAtPosition(i2)).articleId);
        }
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.article.b.a aVar) {
        if (b.g() == Tab.Type.PROFILE) {
            c();
        }
    }

    @Subscribe
    public void a(WriteFragmentView.a aVar) {
        if (b.g() == Tab.Type.PROFILE) {
            c();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void b_() {
        if (isAttachedToWindow()) {
            c();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        if (this.k != null) {
            this.k.a(true, false);
        }
    }
}
