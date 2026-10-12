package com.naver.glink.android.sdk.ui.articles;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.g;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.u;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.parent.PlugListFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SearchArticlesFragmentView extends PlugListFragmentView {
    private static final String a = "SearchArticles";
    private static final String b = "com.naver.glink.ARG_MENU";
    private static final int c = 100;
    private Menu d;
    private String e;
    private Responses.c f;
    private ArticlesAdapter i;
    private a j;
    private View k;
    private ArticlesAdapter.SORT_BY l;

    private class a extends ArrayAdapter<String> {
        a(Context context) {
            super(context, 0);
        }

        private View a(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_articles_search_history, viewGroup, false);
            }
            final String item = getItem(i);
            TextView textView = (TextView) view.findViewById(R.id.history);
            textView.setText(SearchArticlesFragmentView.this.d(item));
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SearchArticlesFragmentView.this.a(item, SearchArticlesFragmentView.this.l);
                }
            });
            view.findViewById(R.id.delete_history).setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SearchArticlesFragmentView.this.h();
                    p.b(a.this.getContext(), item);
                    a.this.remove(item);
                }
            });
            a(i, view);
            return view;
        }

        private void a(int i, View view) {
            view.setPadding(view.getPaddingLeft(), i == 0 ? x.a(5.0f) : 0, view.getPaddingLeft(), view.getPaddingBottom());
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        @NonNull
        public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
            return a(i, view, viewGroup);
        }
    }

    public SearchArticlesFragmentView(Context context) {
        super(context);
    }

    public static SearchArticlesFragmentView a(Context context, Menu menu) {
        SearchArticlesFragmentView searchArticlesFragmentView = new SearchArticlesFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putParcelable(b, menu);
        searchArticlesFragmentView.setArguments(bundle);
        return searchArticlesFragmentView;
    }

    private void a(View view, Responses.c cVar, boolean z) {
        TextView textView = (TextView) view.findViewById(R.id.menu_name);
        EditText editText = (EditText) view.findViewById(R.id.query_string);
        Button button = (Button) view.findViewById(R.id.show_histories_button);
        button.setVisibility(m() ? 8 : 0);
        View viewFindViewById = view.findViewById(R.id.query_string_deletion);
        View viewFindViewById2 = view.findViewById(R.id.search);
        final TextView textView2 = (TextView) view.findViewById(R.id.search_order);
        if (z) {
            textView.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.8
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    SearchArticlesFragmentView.this.q();
                }
            });
            viewFindViewById2.setEnabled(!v.a(this.e));
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SearchArticlesFragmentView.this.a(SearchArticlesFragmentView.this.e, SearchArticlesFragmentView.this.l);
                }
            });
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SearchArticlesFragmentView.this.setQueryString("");
                }
            });
            editText.setFilters(new InputFilter[]{new com.naver.glink.android.sdk.a.b(100)});
            editText.addTextChangedListener(new g() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.11
                @Override // com.naver.glink.android.sdk.a.g, android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    SearchArticlesFragmentView.this.setQueryString(charSequence.toString());
                    if (SearchArticlesFragmentView.this.m()) {
                        SearchArticlesFragmentView.this.j.notifyDataSetChanged();
                    }
                }
            });
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.12
                @Override // android.widget.TextView.OnEditorActionListener
                public boolean onEditorAction(TextView textView3, int i, KeyEvent keyEvent) {
                    if (i != 3) {
                        return false;
                    }
                    SearchArticlesFragmentView.this.a(SearchArticlesFragmentView.this.e, SearchArticlesFragmentView.this.l);
                    return true;
                }
            });
            button.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SearchArticlesFragmentView.this.a(true);
                }
            });
        }
        if (!TextUtils.equals(textView.getText(), this.d.getMenuName())) {
            textView.setText(this.d.getMenuName());
        }
        if (!TextUtils.equals(this.e, editText.getText())) {
            editText.setText(this.e);
            editText.setSelection(editText.length());
        }
        viewFindViewById2.setEnabled(!v.a(this.e));
        viewFindViewById.setVisibility(TextUtils.isEmpty(this.e) ? 4 : 0);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.searched_info_layout);
        if (m() || cVar == null || cVar.articles.isEmpty()) {
            viewGroup.setVisibility(8);
        } else {
            viewGroup.setVisibility(0);
            TextView textView3 = (TextView) view.findViewById(R.id.searched_count);
            u uVar = new u();
            uVar.a(a(R.string.search_result_count, Integer.valueOf(cVar.metadata.totalCount)), d.e().a);
            uVar.a(c(R.string.search_result_text), ContextCompat.getColor(getContext(), R.color.black2));
            textView3.setText(uVar.a());
        }
        textView2.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Pair(SearchArticlesFragmentView.this.c(R.string.search_recent), new Runnable() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        textView2.setText(SearchArticlesFragmentView.this.c(R.string.search_recent));
                        SearchArticlesFragmentView.this.l = ArticlesAdapter.SORT_BY.NEWEST;
                        SearchArticlesFragmentView.this.a(SearchArticlesFragmentView.this.e, SearchArticlesFragmentView.this.l);
                    }
                }));
                arrayList.add(new Pair(SearchArticlesFragmentView.this.c(R.string.search_correct), new Runnable() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        textView2.setText(SearchArticlesFragmentView.this.c(R.string.search_correct));
                        SearchArticlesFragmentView.this.l = ArticlesAdapter.SORT_BY.ACC;
                        SearchArticlesFragmentView.this.a(SearchArticlesFragmentView.this.e, SearchArticlesFragmentView.this.l);
                    }
                }));
                SearchArticlesFragmentView.this.a("Sort", arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Responses.c cVar, boolean z) {
        a(this.k, cVar, z);
        a(getListView().getEmptyView(), cVar, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, ArticlesAdapter.SORT_BY sort_by) {
        if (v.a(str)) {
            return;
        }
        if (!com.naver.plug.android.core.a.b.a(getContext())) {
            AlertDialogFragmentView.b(getContext(), c(R.string.internet_not_connected_error)).a();
            return;
        }
        a(getQueryEdit());
        setQueryString(str);
        this.f = null;
        this.i.a(getListView());
        setListAdapter(this.i);
        this.i.a(this.d, str, sort_by);
        b(R.drawable.cf_icon_error_info, R.string.no_search_results);
        getEmptyContentsLayout().setVisibility(4);
        p.a(getContext(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (!m()) {
            this.j.clear();
            this.j.addAll(p.b(getContext()));
            setListAdapter(this.j);
            getListView().setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.6
                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                    if (i <= 0 || !SearchArticlesFragmentView.this.getQueryEdit().isFocused()) {
                        return;
                    }
                    SearchArticlesFragmentView.this.a(SearchArticlesFragmentView.this.getQueryEdit());
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView absListView, int i) {
                }
            });
            a((Responses.c) null, false);
            b(R.drawable.cf_icon_error_info, R.string.empty_recently_search_message);
            getEmptyContentsLayout().setVisibility(0);
        }
        if (z) {
            final EditText queryEdit = getQueryEdit();
            queryEdit.requestFocus();
            queryEdit.postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ((InputMethodManager) SearchArticlesFragmentView.this.getContext().getSystemService("input_method")).showSoftInput(queryEdit, 2);
                    } catch (Exception e) {
                        Log.d(SearchArticlesFragmentView.a, "exception: " + e);
                    }
                }
            }, 300L);
        }
    }

    public static SearchArticlesFragmentView b(Context context) {
        SearchArticlesFragmentView searchArticlesFragmentView = new SearchArticlesFragmentView(context);
        searchArticlesFragmentView.d = Menu.allArticles("");
        searchArticlesFragmentView.p();
        return searchArticlesFragmentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CharSequence d(String str) {
        return com.naver.glink.android.sdk.ui.articles.a.a(str, this.e, d.e().a);
    }

    private void f() {
        com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.SEARCH);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EditText getQueryEdit() {
        return this.j.getCount() == 0 ? (EditText) getListView().getEmptyView().findViewById(R.id.query_string) : (EditText) this.k.findViewById(R.id.query_string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m() {
        return this.j != null && this.j.equals(getListAdapter());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.d != null && TextUtils.isEmpty(this.d.getMenuName()) && TextUtils.isEmpty(this.d.getMenuName())) {
            com.naver.glink.android.sdk.api.requests.a.menusRequest.execute(getContext(), new RequestListener<Responses.r>() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.4
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.r rVar) {
                    SearchArticlesFragmentView.this.d.setMenuName(rVar.metadata.allPosts);
                    if (SearchArticlesFragmentView.this.k != null) {
                        ((TextView) SearchArticlesFragmentView.this.k.findViewById(R.id.menu_name)).setText(SearchArticlesFragmentView.this.d.getMenuName());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        a(getQueryEdit());
        b.a(getContext(), this.d.getMenuId(), new b.a() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.5
            @Override // com.naver.glink.android.sdk.ui.articles.b.a
            public void a(Menu menu) {
                if (SearchArticlesFragmentView.this.d.getMenuId() == menu.getMenuId()) {
                    return;
                }
                SearchArticlesFragmentView.this.d = menu;
                if (TextUtils.isEmpty(SearchArticlesFragmentView.this.e)) {
                    SearchArticlesFragmentView.this.a((Responses.c) null, false);
                } else {
                    SearchArticlesFragmentView.this.a(SearchArticlesFragmentView.this.e, SearchArticlesFragmentView.this.l);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQueryString(String str) {
        if (TextUtils.equals(this.e, str)) {
            return;
        }
        this.e = str.replaceAll("(\r\n|\n)", " ");
        a(this.f, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_search_articles, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        com.naver.glink.android.sdk.a.a.b.a(this.i);
        c();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        if (getArguments() != null) {
            this.d = (Menu) getArguments().getParcelable(b);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView, com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.k = LayoutInflater.from(getContext()).inflate(R.layout.item_articles_search_header, (ViewGroup) getListView(), false);
        getListView().addHeaderView(this.k);
        a((Responses.c) null, true);
        this.j = new a(getContext());
        this.i = new ArticlesAdapter(getContext());
        this.i.a(new ArticlesAdapter.a() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.1
            @Override // com.naver.glink.android.sdk.ui.articles.ArticlesAdapter.a
            public void a(int i, Responses.c cVar, PlugError plugError) {
                if (cVar != null) {
                    SearchArticlesFragmentView.this.f = cVar;
                    SearchArticlesFragmentView.this.a(SearchArticlesFragmentView.this.f, false);
                    SearchArticlesFragmentView.this.i.notifyDataSetChanged();
                    SearchArticlesFragmentView.this.getEmptyContentsLayout().setVisibility(0);
                }
            }
        });
        this.i.a(true);
        a(false);
        this.l = ArticlesAdapter.SORT_BY.NEWEST;
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView
    public void a(ListView listView, View view, int i, long j) {
        if (listView.getItemAtPosition(i) instanceof ArticlesAdapter.c) {
            com.naver.glink.android.sdk.ui.tabs.b.a(((ArticlesAdapter.c) listView.getItemAtPosition(i)).d.articleId);
        }
    }

    @Subscribe
    public void a(d.a aVar) {
        a(getQueryEdit());
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        com.naver.glink.android.sdk.a.a.b.b(this.i);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void b_() {
        super.b_();
        f();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView.3
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                SearchArticlesFragmentView.this.g();
                SearchArticlesFragmentView.this.p();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                SearchArticlesFragmentView.this.a(plugError);
            }
        });
    }
}
