package com.naver.glink.android.sdk.ui.media;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.parent.PlugListFragmentView;
import com.naver.glink.android.sdk.ui.viewer.MediaViewerDialog;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AllMediaFragmentView extends PlugListFragmentView {
    private static final String a = "com.naver.glink.ARG_MENU";
    private Menu b;
    private int c;
    private b d;
    private View e;
    private SwipeRefreshLayout f;
    private b.a i;

    public static class a {
        final ArticleMedia a;

        public a(ArticleMedia articleMedia) {
            this.a = articleMedia;
        }
    }

    public AllMediaFragmentView(Context context) {
        super(context);
        this.i = new b.a() { // from class: com.naver.glink.android.sdk.ui.media.AllMediaFragmentView.1
            @Override // com.naver.glink.android.sdk.ui.media.b.a
            public void a(com.naver.glink.android.sdk.api.response.a aVar, PlugError plugError) {
                if (aVar != null) {
                    AllMediaFragmentView.this.getListAdapter().notifyDataSetChanged();
                } else {
                    AllMediaFragmentView.this.a(plugError);
                }
                AllMediaFragmentView.this.f.setRefreshing(false);
            }
        };
    }

    public static AllMediaFragmentView a(Context context, Menu menu) {
        AllMediaFragmentView allMediaFragmentView = new AllMediaFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putParcelable(a, menu);
        allMediaFragmentView.setArguments(bundle);
        return allMediaFragmentView;
    }

    private void a(View view) {
        com.naver.glink.android.sdk.ui.articles.a.a(view.findViewById(R.id.back), false);
        com.naver.glink.android.sdk.ui.articles.a.a(view.findViewById(R.id.article_write), -1, new com.naver.glink.android.sdk.ui.articles.a.InterfaceC0040a() { // from class: com.naver.glink.android.sdk.ui.media.AllMediaFragmentView.3
            @Override // com.naver.glink.android.sdk.ui.articles.a.InterfaceC0040a
            public void a() {
                AllMediaFragmentView.this.m();
            }
        });
    }

    private void f() {
        if (this.b != null) {
            if (this.b.getType() == Menu.Type.IMAGES) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.MEDIA_IMAGE_LIST);
            } else if (this.b.getType() == Menu.Type.VIDEOS) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.MEDIA_VIDEO_LIST);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.b != null) {
            if (this.b.getType() == Menu.Type.IMAGES) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.CLICK.MEDIA_IMAGE_LIST_POST);
            } else if (this.b.getType() == Menu.Type.VIDEOS) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.CLICK.MEDIA_VIDEO_LIST_POST);
            }
        }
    }

    private void setSelectedItem(ArticleMedia articleMedia) {
        if (articleMedia == null || articleMedia.equals(getListAdapter().a())) {
            return;
        }
        getListAdapter().a(articleMedia);
        getListView().setSelection((this.d.a(articleMedia) / this.c) + getListView().getHeaderViewsCount());
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_all_media, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        this.d.a(this.i);
        if (this.d.c()) {
            c();
        }
        f();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        if (getArguments() != null) {
            this.b = (Menu) getArguments().getParcelable(a);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView, com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.e = LayoutInflater.from(getContext()).inflate(R.layout.item_media_header, (ViewGroup) getListView(), false);
        this.e.getLayoutParams().height = d.i() ? x.a(64.0f) : x.a(56.0f);
        getListView().addHeaderView(this.e);
        this.c = d.i() ? 3 : 2;
        this.d = new b(getContext(), this.b.getType());
        setListAdapter(new com.naver.glink.android.sdk.ui.media.a(getContext(), this.c, this.d));
        b(R.drawable.cf_icon_error_info, R.string.article_empty);
        getListView().getEmptyView().setVisibility(4);
        this.f = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.f.setColorSchemeColors(d.e().a);
        this.f.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.naver.glink.android.sdk.ui.media.AllMediaFragmentView.2
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                AllMediaFragmentView.this.c();
            }
        });
        this.f.setProgressViewOffset(false, x.a(48.0f), x.a(112.0f));
        a(view);
    }

    @Subscribe
    public void a(a aVar) {
        if (this.d.b(aVar.a)) {
            setSelectedItem(aVar.a);
        }
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.media.a.b bVar) {
        if (!this.d.b(bVar.a) || bVar.a.a(false)) {
            return;
        }
        int iA = this.d.a(bVar.a);
        com.naver.glink.android.sdk.ui.viewer.a.a(MediaViewerDialog.a(getContext(), MediaViewerDialog.From.ALL_MEDIA, new com.naver.glink.android.sdk.ui.viewer.a.a(this.d), iA));
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.media.a.c cVar) {
        if (!this.d.b(cVar.a) || cVar.a.a(true)) {
            return;
        }
        com.naver.glink.android.sdk.ui.tabs.b.a(cVar.a.articleId);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        this.d.b(this.i);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void b_() {
        if (isAttachedToWindow()) {
            c();
            f();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.media.AllMediaFragmentView.4
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                AllMediaFragmentView.this.g();
                AllMediaFragmentView.this.d.d();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                AllMediaFragmentView.this.a(plugError);
            }
        });
        if (TextUtils.isEmpty(this.b.getMenuName())) {
            com.naver.glink.android.sdk.api.requests.a.menusRequest.execute(getContext(), new RequestListener<Responses.r>() { // from class: com.naver.glink.android.sdk.ui.media.AllMediaFragmentView.5
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.r rVar) {
                    AllMediaFragmentView.this.b.setMenuName(AllMediaFragmentView.this.b.getType() == Menu.Type.IMAGES ? rVar.metadata.allPhotos : rVar.metadata.allVideos);
                }
            });
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView
    public com.naver.glink.android.sdk.ui.media.a getListAdapter() {
        return (com.naver.glink.android.sdk.ui.media.a) super.getListAdapter();
    }
}
