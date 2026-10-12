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
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.parent.PlugListFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ArticlesFragmentView extends PlugListFragmentView {
    private static final String a = "com.naver.glink.ARG_MENU_ID";
    private static final String b = "com.naver.glink.ARG_BACK_BUTTON";
    private int c;
    private ArticlesAdapter d;
    private View e;
    private SwipeRefreshLayout f;
    private boolean i;

    static class a {
        a() {
        }
    }

    public ArticlesFragmentView(Context context) {
        super(context);
    }

    public static ArticlesFragmentView a(Context context, int i, boolean z) {
        ArticlesFragmentView articlesFragmentView = new ArticlesFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putInt(a, i);
        bundle.putBoolean(b, z);
        articlesFragmentView.setArguments(bundle);
        return articlesFragmentView;
    }

    private void a(View view) {
        com.naver.glink.android.sdk.ui.articles.a.a(view.findViewById(R.id.back), this.i);
        View viewFindViewById = view.findViewById(R.id.article_write);
        viewFindViewById.setVisibility(0);
        com.naver.glink.android.sdk.ui.articles.a.a(viewFindViewById, this.c, new com.naver.glink.android.sdk.ui.articles.a.InterfaceC0040a() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesFragmentView.3
            @Override // com.naver.glink.android.sdk.ui.articles.a.InterfaceC0040a
            public void a() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view, Responses.c cVar) {
        TextView textView = (TextView) view.findViewById(R.id.menu_name);
        textView.setText(cVar.menu.getMenuName());
        view.findViewById(R.id.menu_list_button).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesFragmentView.5
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                b.a(ArticlesFragmentView.this.getContext(), ArticlesFragmentView.this.c, new b.a() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesFragmentView.5.1
                    @Override // com.naver.glink.android.sdk.ui.articles.b.a
                    public void a(Menu menu) {
                        com.naver.glink.android.sdk.ui.tabs.b.a(menu.getMenuId(), true, false);
                    }
                });
            }
        });
        if (d.h()) {
            textView.setCompoundDrawablePadding(x.a(6.0f));
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cf_blt_arrow_down, 0);
            textView.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesFragmentView.6
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    b.a(ArticlesFragmentView.this.getContext(), ArticlesFragmentView.this.c, new b.a() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesFragmentView.6.1
                        @Override // com.naver.glink.android.sdk.ui.articles.b.a
                        public void a(Menu menu) {
                            ArticlesFragmentView.this.a(menu.getMenuId());
                        }
                    });
                }
            });
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_articles, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        com.naver.glink.android.sdk.a.a.b.a(this.d);
        a(this.c);
    }

    public void a(int i) {
        if ((this.d.getCount() > 0) && this.c == i) {
            return;
        }
        this.c = i;
        c();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        if (getArguments() != null) {
            this.c = getArguments().getInt(a);
            this.i = getArguments().getBoolean(b);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView, com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.e = LayoutInflater.from(getContext()).inflate(R.layout.item_articles_header, (ViewGroup) getListView(), false);
        getListView().addHeaderView(this.e);
        this.d = new ArticlesAdapter(getContext());
        this.d.a(new ArticlesAdapter.a() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesFragmentView.1
            @Override // com.naver.glink.android.sdk.ui.articles.ArticlesAdapter.a
            public void a(int i, Responses.c cVar, PlugError plugError) {
                if (plugError != null) {
                    ArticlesFragmentView.this.a(plugError);
                    return;
                }
                ArticlesFragmentView.this.g();
                if (i == 1) {
                    ArticlesFragmentView.this.a(ArticlesFragmentView.this.e, cVar);
                    ArticlesFragmentView.this.e.setVisibility(0);
                }
                ArticlesFragmentView.this.getListView().setVisibility(0);
                ArticlesFragmentView.this.f.setRefreshing(false);
            }
        });
        this.d.a(getListView());
        this.f = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.f.setColorSchemeColors(d.e().a);
        this.f.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesFragmentView.2
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                ArticlesFragmentView.this.getListView().clearChoices();
                ArticlesFragmentView.this.d.a(ArticlesFragmentView.this.c, false);
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
            com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesFragmentView.4
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.i iVar) {
                    if (ArticlesFragmentView.this.c == -1) {
                        ArticlesFragmentView.this.b(R.string.no_menu_setting_message);
                        return;
                    }
                    if (ArticlesFragmentView.this.c == iVar.eventMenuId || ArticlesFragmentView.this.c == iVar.noticeMenuId) {
                        ArticlesFragmentView.this.findViewById(R.id.back).setVisibility(8);
                    }
                    ArticlesFragmentView.this.e.setVisibility(8);
                    ArticlesFragmentView.this.getListView().setVisibility(8);
                    ArticlesFragmentView.this.getListView().clearChoices();
                    ArticlesFragmentView.this.d.a(ArticlesFragmentView.this.c, true);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    ArticlesFragmentView.this.a(plugError);
                }
            });
        }
    }
}
