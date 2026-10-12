package com.naver.glink.android.sdk.ui.articles;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.parent.PlugListFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PopularArticlesFragment extends PlugListFragmentView {
    private static final String a = "com.naver.glink.ARG_TAB_TYPE";
    private static final String b = "com.naver.glink.ARG_MENU_ID";
    private static final String c = "com.naver.glink.ARG_BACK_BUTTON";
    private ArticlesAdapter d;
    private View e;
    private SwipeRefreshLayout f;

    static class a {
        a() {
        }
    }

    public PopularArticlesFragment(Context context) {
        super(context);
    }

    private void a(View view) {
        com.naver.glink.android.sdk.ui.articles.a.a(view.findViewById(R.id.back), true);
        View viewFindViewById = view.findViewById(R.id.article_write);
        viewFindViewById.setVisibility(0);
        com.naver.glink.android.sdk.ui.articles.a.a(viewFindViewById, -1, (com.naver.glink.android.sdk.ui.articles.a.InterfaceC0040a) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view, Responses.c cVar) {
        ((TextView) view.findViewById(R.id.menu_name)).setText(cVar.menu.getMenuName());
        view.findViewById(R.id.menu_list_button).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.articles.PopularArticlesFragment.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                b.a(PopularArticlesFragment.this.getContext(), -1, new b.a() { // from class: com.naver.glink.android.sdk.ui.articles.PopularArticlesFragment.4.1
                    @Override // com.naver.glink.android.sdk.ui.articles.b.a
                    public void a(Menu menu) {
                        com.naver.glink.android.sdk.ui.tabs.b.a(menu.getMenuId(), true, false);
                    }
                });
            }
        });
    }

    public static PopularArticlesFragment b(Context context) {
        PopularArticlesFragment popularArticlesFragment = new PopularArticlesFragment(context);
        popularArticlesFragment.setArguments(new Bundle());
        return popularArticlesFragment;
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_articles, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        com.naver.glink.android.sdk.a.a.b.a(this.d);
        c();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView, com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.e = LayoutInflater.from(getContext()).inflate(R.layout.item_articles_header, (ViewGroup) getListView(), false);
        getListView().addHeaderView(this.e);
        this.d = new ArticlesAdapter(getContext());
        this.d.a(new ArticlesAdapter.a() { // from class: com.naver.glink.android.sdk.ui.articles.PopularArticlesFragment.1
            @Override // com.naver.glink.android.sdk.ui.articles.ArticlesAdapter.a
            public void a(int i, Responses.c cVar, PlugError plugError) {
                if ((cVar != null && cVar.getError() != null) || plugError != null) {
                    PopularArticlesFragment.this.a(plugError);
                    return;
                }
                PopularArticlesFragment.this.g();
                if (i == 1) {
                    PopularArticlesFragment.this.a(PopularArticlesFragment.this.e, cVar);
                    PopularArticlesFragment.this.e.setVisibility(0);
                }
                PopularArticlesFragment.this.getListView().setVisibility(0);
                PopularArticlesFragment.this.f.setRefreshing(false);
            }
        });
        this.d.a(getListView());
        this.f = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.f.setColorSchemeColors(d.e().a);
        this.f.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.naver.glink.android.sdk.ui.articles.PopularArticlesFragment.2
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                PopularArticlesFragment.this.getListView().clearChoices();
                PopularArticlesFragment.this.d.a(-4, "A");
            }
        });
        a(view);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView
    public void a(ListView listView, View view, int i, long j) {
        if (listView.getItemAtPosition(i) instanceof ArticlesAdapter.c) {
            com.naver.glink.android.sdk.ui.tabs.b.a(((ArticlesAdapter.c) listView.getItemAtPosition(i)).d.articleId);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        com.naver.glink.android.sdk.a.a.b.b(this.d);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        if (isAttachedToWindow()) {
            com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.articles.PopularArticlesFragment.3
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.i iVar) {
                    PopularArticlesFragment.this.e.setVisibility(8);
                    PopularArticlesFragment.this.getListView().setVisibility(8);
                    PopularArticlesFragment.this.getListView().clearChoices();
                    PopularArticlesFragment.this.d.a(-4, "A");
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    PopularArticlesFragment.this.a(plugError);
                }
            });
        }
    }
}
