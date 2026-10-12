package com.naver.glink.android.sdk.ui.articles;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.parent.PlugListFragmentView;
import com.naver.glink.android.sdk.ui.widget.HorizontalListView;
import com.naver.glink.android.sdk.ui.widget.MenuRadioButton;
import com.naver.glink.android.sdk.ui.widget.ScrollListenerLayout;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ArticlesMainFragmentView extends PlugListFragmentView {
    private static final String a = "com.naver.glink.ARG_MENU_ID";
    private SwipeRefreshLayout b;
    private ViewGroup c;
    private ArticlesAdapter d;
    private HorizontalListView e;
    private c f;
    private ScrollListenerLayout i;
    private View j;
    private RadioGroup.OnCheckedChangeListener k;
    private int l;
    private boolean m;
    private int n;
    private RadioGroup o;

    public ArticlesMainFragmentView(Context context) {
        super(context);
        this.l = 0;
        this.m = false;
        this.n = 0;
    }

    public static ArticlesMainFragmentView a(Context context, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(a, i);
        ArticlesMainFragmentView articlesMainFragmentView = new ArticlesMainFragmentView(context);
        articlesMainFragmentView.setArguments(bundle);
        return articlesMainFragmentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Responses.i iVar) {
        if (i == R.id.articles_menu_all_article) {
            this.l = 0;
            return;
        }
        if (i == R.id.articles_menu_notice) {
            this.l = iVar.noticeMenuId;
        } else if (i == R.id.articles_menu_event) {
            this.l = iVar.eventMenuId;
        } else if (i == R.id.articles_menu_tip) {
            this.l = iVar.tipMenuId;
        }
    }

    private void a(View view) {
        this.e = (HorizontalListView) view.findViewById(R.id.popular_list);
        this.e.requestLayout();
        this.e.setDividerWidth(x.a(2.0f));
        this.e.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.8
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                if (ArticlesMainFragmentView.this.e.getItemAtPosition(i) instanceof c.b) {
                    c.b bVar = (c.b) ArticlesMainFragmentView.this.e.getItemAtPosition(i);
                    if (bVar.b != null) {
                        com.naver.glink.android.sdk.ui.tabs.b.a(bVar.b.articleId);
                    } else {
                        com.naver.glink.android.sdk.ui.tabs.b.b(false);
                    }
                }
            }
        });
        this.f = new c(getContext());
        this.e.setAdapter((ListAdapter) this.f);
        this.f.a(new c.a() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.9
            @Override // com.naver.glink.android.sdk.ui.articles.c.a
            public void a(Responses.c cVar, PlugError plugError) {
                if (cVar == null || cVar.articles.isEmpty()) {
                    ArticlesMainFragmentView.this.e.setVisibility(8);
                } else {
                    ArticlesMainFragmentView.this.e.setVisibility(0);
                }
            }
        });
        this.e.setOnScrollStateChangedListener(new HorizontalListView.OnScrollStateChangedListener() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.10
            @Override // com.naver.glink.android.sdk.ui.widget.HorizontalListView.OnScrollStateChangedListener
            public void a(HorizontalListView.OnScrollStateChangedListener.ScrollState scrollState) {
                if (scrollState == HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE) {
                    ArticlesMainFragmentView.this.b.setEnabled(true);
                } else {
                    ArticlesMainFragmentView.this.b.setEnabled(false);
                }
            }
        });
        this.e.setOnCustomTouchEvent(new HorizontalListView.d() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.11
            @Override // com.naver.glink.android.sdk.ui.widget.HorizontalListView.d
            public void a() {
                ArticlesMainFragmentView.this.e.requestDisallowInterceptTouchEvent(true);
            }
        });
        d.e().f(this.e);
    }

    private void a(ViewGroup viewGroup) {
        this.j = LayoutInflater.from(getContext()).inflate(R.layout.item_articles_menu_selector, (ViewGroup) null, false);
        this.o = (RadioGroup) this.j.findViewById(R.id.articles_menu_radio_group);
        RadioButton radioButton = (RadioButton) this.j.findViewById(R.id.articles_menu_all_article);
        MenuRadioButton menuRadioButton = (MenuRadioButton) this.j.findViewById(R.id.articles_menu_notice);
        MenuRadioButton menuRadioButton2 = (MenuRadioButton) this.j.findViewById(R.id.articles_menu_event);
        MenuRadioButton menuRadioButton3 = (MenuRadioButton) this.j.findViewById(R.id.articles_menu_tip);
        d.e().a((View) radioButton, false);
        d.e().a((View) menuRadioButton, false);
        d.e().a((View) menuRadioButton2, false);
        d.e().a((View) menuRadioButton3, false);
        this.j.findViewById(R.id.menu_list_button).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                b.a(ArticlesMainFragmentView.this.getContext(), ArticlesMainFragmentView.this.l, new b.a() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.2.1
                    @Override // com.naver.glink.android.sdk.ui.articles.b.a
                    public void a(Menu menu) {
                        com.naver.glink.android.sdk.ui.tabs.b.a(menu.getMenuId(), true, false);
                    }
                });
            }
        });
        this.o.check(R.id.articles_menu_notice);
        viewGroup.addView(this.j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Responses.i iVar) {
        if (iVar == null || this.j == null) {
            return;
        }
        RadioGroup radioGroup = (RadioGroup) this.j.findViewById(R.id.articles_menu_radio_group);
        MenuRadioButton menuRadioButton = (MenuRadioButton) this.j.findViewById(R.id.articles_menu_notice);
        MenuRadioButton menuRadioButton2 = (MenuRadioButton) this.j.findViewById(R.id.articles_menu_event);
        MenuRadioButton menuRadioButton3 = (MenuRadioButton) this.j.findViewById(R.id.articles_menu_tip);
        menuRadioButton.setNewVisible(iVar.hasNewNoticeArticle);
        menuRadioButton2.setNewVisible(iVar.hasNewEventArticle);
        if (iVar.tipMenuId == -1) {
            menuRadioButton3.setVisibility(8);
        } else {
            menuRadioButton3.setVisibility(0);
        }
        a(radioGroup.getCheckedRadioButtonId(), iVar);
        if (this.k == null) {
            this.k = new RadioGroup.OnCheckedChangeListener() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.3
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public void onCheckedChanged(RadioGroup radioGroup2, @IdRes int i) {
                    ArticlesMainFragmentView.this.m = false;
                    ArticlesMainFragmentView.this.n = 0;
                    if (ArticlesMainFragmentView.this.d.getCount() <= 0 || ArticlesMainFragmentView.this.getListView().getChildAt(0) != ArticlesMainFragmentView.this.c) {
                        ArticlesMainFragmentView.this.d.clear();
                        ArticlesMainFragmentView.this.i.b(ArticlesMainFragmentView.this.j);
                        if (ArticlesMainFragmentView.this.j.getParent() == null) {
                            ArticlesMainFragmentView.this.c.addView(ArticlesMainFragmentView.this.j);
                        }
                    } else {
                        ArticlesMainFragmentView.this.m = d.i();
                        if (ArticlesMainFragmentView.this.c.getTop() != 0) {
                            ArticlesMainFragmentView.this.n = ArticlesMainFragmentView.this.c.getTop();
                        }
                    }
                    ArticlesMainFragmentView.this.a(i, iVar);
                    com.naver.glink.android.sdk.ui.tabs.b.h();
                    ArticlesMainFragmentView.this.d.a(ArticlesMainFragmentView.this.l, false);
                    ArticlesMainFragmentView.this.m();
                }
            };
        }
        radioGroup.setOnCheckedChangeListener(this.k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.o.getCheckedRadioButtonId() == R.id.articles_menu_notice) {
            com.naver.glink.android.sdk.a.a.a(JackpotEvent.CLICK.ARTICLE_LIST_NOTICE_POST);
            return;
        }
        if (this.o.getCheckedRadioButtonId() == R.id.articles_menu_event) {
            com.naver.glink.android.sdk.a.a.a(JackpotEvent.CLICK.ARTICLE_LIST_EVENT_POST);
        } else if (this.o.getCheckedRadioButtonId() == R.id.articles_menu_tip) {
            com.naver.glink.android.sdk.a.a.a(JackpotEvent.CLICK.ARTICLE_LIST_TIP_POST);
        } else if (this.o.getCheckedRadioButtonId() == R.id.articles_menu_all_article) {
            com.naver.glink.android.sdk.a.a.a(JackpotEvent.CLICK.ARTICLE_LIST_ALL_POST);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.o != null) {
            if (this.o.getCheckedRadioButtonId() == R.id.articles_menu_notice) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.ARTICLE_LIST_NOTICE);
                return;
            }
            if (this.o.getCheckedRadioButtonId() == R.id.articles_menu_event) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.ARTICLE_LIST_EVENT);
            } else if (this.o.getCheckedRadioButtonId() == R.id.articles_menu_tip) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.ARTICLE_LIST_TIP);
            } else if (this.o.getCheckedRadioButtonId() == R.id.articles_menu_all_article) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.ARTICLE_LIST_ALL);
            }
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_articles_main, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        com.naver.glink.android.sdk.a.a.b.a(this.d);
        m();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView, com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.l = arguments.getInt(a, 0);
        }
        this.c = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.item_articles_main_header, (ViewGroup) getListView(), false);
        a((View) this.c);
        getListView().addHeaderView(this.c);
        this.i = (ScrollListenerLayout) view.findViewById(R.id.article_list_scroll_view);
        this.i.setEnable(true);
        this.b = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.b.setColorSchemeColors(d.e().a);
        this.b.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.1
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                ArticlesMainFragmentView.this.c();
            }
        });
        this.d = new ArticlesAdapter(getContext());
        this.d.a(new ArticlesAdapter.a() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.4
            @Override // com.naver.glink.android.sdk.ui.articles.ArticlesAdapter.a
            public void a(int i, Responses.c cVar, PlugError plugError) {
                com.naver.glink.android.sdk.ui.tabs.b.i();
                if (cVar != null && cVar.getError() != null) {
                    ArticlesMainFragmentView.this.d.clear();
                    ArticlesMainFragmentView.this.d.a(cVar.getError().errorMessage);
                } else if (plugError != null) {
                    ArticlesMainFragmentView.this.getListView().setVisibility(8);
                    ArticlesMainFragmentView.this.a(plugError);
                } else {
                    ArticlesMainFragmentView.this.g();
                    ArticlesMainFragmentView.this.getListView().setVisibility(0);
                }
                ArticlesMainFragmentView.this.b.setRefreshing(false);
                if (ArticlesMainFragmentView.this.m) {
                    if (ArticlesMainFragmentView.this.n != 0) {
                        ArticlesMainFragmentView.this.getListView().setSelectionFromTop(0, ArticlesMainFragmentView.this.n);
                    } else {
                        ArticlesMainFragmentView.this.getListView().smoothScrollToPositionFromTop(1, x.a(48.0f), 70);
                    }
                }
                ArticlesMainFragmentView.this.m = false;
                ArticlesMainFragmentView.this.n = 0;
            }
        });
        this.d.a(getListView());
        this.d.a(new AbsListView.OnScrollListener() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.5
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i <= 1 && ArticlesMainFragmentView.this.i.b()) {
                    ArticlesMainFragmentView.this.i.b(ArticlesMainFragmentView.this.j);
                    if (ArticlesMainFragmentView.this.j != null && ArticlesMainFragmentView.this.j.getParent() == null) {
                        ArticlesMainFragmentView.this.c.addView(ArticlesMainFragmentView.this.j);
                    }
                    ArticlesMainFragmentView.this.i.setEnable(false);
                    ArticlesMainFragmentView.this.i.c();
                    return;
                }
                if (i <= 1 || ArticlesMainFragmentView.this.i.b()) {
                    return;
                }
                ArticlesMainFragmentView.this.c.removeView(ArticlesMainFragmentView.this.j);
                if (ArticlesMainFragmentView.this.j != null && ArticlesMainFragmentView.this.j.getParent() == null) {
                    ArticlesMainFragmentView.this.i.a(ArticlesMainFragmentView.this.j);
                }
                ArticlesMainFragmentView.this.i.setEnable(true);
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }
        });
        a.a(view.findViewById(R.id.article_write), this.l, new a.InterfaceC0040a() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.6
            @Override // com.naver.glink.android.sdk.ui.articles.a.InterfaceC0040a
            public void a() {
                ArticlesMainFragmentView.this.f();
            }
        });
        a(this.c);
        c();
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
    public void b_() {
        super.b_();
        m();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView.7
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                if (ArticlesMainFragmentView.this.isAttachedToWindow()) {
                    if (ArticlesMainFragmentView.this.j != null) {
                        ArticlesMainFragmentView.this.a(iVar);
                    }
                    com.naver.glink.android.sdk.ui.tabs.b.h();
                    ArticlesMainFragmentView.this.getListView().clearChoices();
                    if (iVar.popularArticle) {
                        ArticlesMainFragmentView.this.f.b();
                        ArticlesMainFragmentView.this.e.setVisibility(0);
                    } else {
                        ArticlesMainFragmentView.this.e.setVisibility(8);
                    }
                    ArticlesMainFragmentView.this.d.a(ArticlesMainFragmentView.this.l, false);
                }
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinally(Responses.i iVar, PlugError plugError) {
                com.naver.glink.android.sdk.ui.tabs.b.i();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                ArticlesMainFragmentView.this.a(plugError);
            }
        });
    }
}
