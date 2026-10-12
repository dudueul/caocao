package com.naver.glink.android.sdk.ui.streaming.streamings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.streaming.StreamingHomeResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingVideosResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingsResponse;
import com.naver.glink.android.sdk.api.streaming.c;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.parent.PlugListFragmentView;
import com.naver.glink.android.sdk.ui.streaming.b;
import com.naver.glink.android.sdk.ui.streaming.viewer.e;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class StreamingsFragmentView extends PlugListFragmentView {
    private static final String c = "com.naver.glink.ARG_TYPE";
    private static final String d = "com.naver.glink.ARG_USER_ID";
    public StreamingsResponse a;
    public StreamingVideosResponse b;
    private Type e;
    private String f;
    private a i;
    private Request<StreamingsResponse> j;
    private Request<StreamingVideosResponse> k;
    private SwipeRefreshLayout l;
    private View m;
    private SORT_BY n;

    public enum SORT_BY {
        NEWEST(0),
        VIEWS(1);

        final int type;

        SORT_BY(int i) {
            this.type = i;
        }

        public int getType() {
            return this.type;
        }
    }

    enum Type {
        ALL_STREAMING,
        PROFILE_STREAMING
    }

    public StreamingsFragmentView(Context context) {
        super(context);
    }

    public static StreamingsFragmentView a(Context context, String str) {
        StreamingsFragmentView streamingsFragmentView = new StreamingsFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putSerializable(c, TextUtils.isEmpty(str) ? Type.ALL_STREAMING : Type.PROFILE_STREAMING);
        bundle.putString(d, str);
        streamingsFragmentView.setArguments(bundle);
        return streamingsFragmentView;
    }

    public static StreamingsFragmentView b(Context context) {
        return a(context, (String) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        b.a(getContext(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        StreamingsResponse.Metadata metadata;
        if (this.j != null || this.a == null || (metadata = this.a.metadata) == null || metadata.last) {
            return;
        }
        this.j = c.b(this.f, metadata.lastStreamingId);
        this.j.execute(getContext(), new RequestListener<StreamingsResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.12
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull StreamingsResponse streamingsResponse) {
                StreamingsFragmentView.this.a = streamingsResponse;
                StreamingsFragmentView.this.i.a(streamingsResponse);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinally(StreamingsResponse streamingsResponse, PlugError plugError) {
                StreamingsFragmentView.this.j = null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        StreamingVideosResponse.Metadata metadata;
        if (this.k != null || this.b == null || (metadata = this.b.metadata) == null || metadata.last) {
            return;
        }
        this.k = c.a(this.f, metadata.lastStreamingId, this.n.getType());
        this.k.execute(getContext(), new RequestListener<StreamingVideosResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.2
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull StreamingVideosResponse streamingVideosResponse) {
                StreamingsFragmentView.this.b = streamingVideosResponse;
                StreamingsFragmentView.this.i.a(streamingVideosResponse);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinally(StreamingVideosResponse streamingVideosResponse, PlugError plugError) {
                StreamingsFragmentView.this.k = null;
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    @SuppressLint({"InflateParams"})
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_streamings, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView, com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.e = (Type) getArguments().getSerializable(c);
        this.f = getArguments().getString(d);
        this.n = SORT_BY.NEWEST;
        getListView().addFooterView(x.a(getContext(), getListView(), x.a(8.0f), android.R.color.transparent));
        this.i = new a(getContext(), this.e);
        this.i.a(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                StreamingsFragmentView.this.p();
            }
        });
        this.i.b(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.5
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                StreamingsFragmentView.this.f();
            }
        });
        setListAdapter(this.i);
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.6
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i3 < i + i2 + 10) {
                    StreamingsFragmentView.this.q();
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }
        });
        this.m = view.findViewById(R.id.create_streaming);
        this.l = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.l.setColorSchemeColors(d.e().a);
        this.l.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.7
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                StreamingsFragmentView.this.getListView().clearChoices();
                StreamingsFragmentView.this.c();
            }
        });
        if (this.e == Type.PROFILE_STREAMING) {
            getListView().addHeaderView(x.a(getContext(), getListView(), x.a(48.0f), android.R.color.transparent));
            this.l.setProgressViewOffset(false, x.a(48.0f), x.a(112.0f));
        }
        if (this.e == Type.PROFILE_STREAMING) {
            View viewFindViewById = view.findViewById(R.id.back);
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.8
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    com.naver.glink.android.sdk.ui.tabs.b.f();
                }
            });
        }
        c();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView
    public void a(ListView listView, View view, int i, long j) {
        Object obj = ((a.C0058a) listView.getItemAtPosition(i)).b;
        if (obj instanceof StreamingsResponse.Streaming) {
            com.naver.glink.android.sdk.ui.streaming.viewer.c.a(getContext(), ((StreamingsResponse.Streaming) obj).streamingId);
        } else if (obj instanceof StreamingVideosResponse.StreamingVideo) {
            com.naver.glink.android.sdk.ui.streaming.viewer.c.b(getContext(), ((StreamingVideosResponse.StreamingVideo) obj).streamingId);
        }
    }

    @Subscribe
    public void a(LoginHelper.b bVar) {
        c();
    }

    @Subscribe
    public void a(e.a aVar) {
        this.i.a(aVar.a);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void b_() {
        super.b_();
        com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.STREAMING_LIST);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        if (b.b()) {
            if (com.naver.glink.android.sdk.api.requests.a.forStreamingCafeRequest.getCachedResponse() == null) {
                com.naver.glink.android.sdk.api.requests.a.forStreamingCafeRequest.execute(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.9
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull Responses.i iVar) {
                        StreamingsFragmentView.this.c();
                    }
                });
                return;
            }
            if (this.e == Type.ALL_STREAMING) {
                this.m.setVisibility(b.a() && ((Responses.i) com.naver.glink.android.sdk.api.requests.a.forStreamingCafeRequest.getCachedResponse()).liveAuthority ? 0 : 8);
                d.e().a(this.m, R.drawable.cf_btn_flvideo);
                this.m.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.10
                    @Override // com.naver.glink.android.sdk.a.t
                    public void a(View view) {
                        StreamingsFragmentView.this.m();
                        com.naver.glink.android.sdk.a.a.a(JackpotEvent.CLICK.STREAMING_LIST_LIVE_START);
                    }
                });
            }
            c.a(this.f, this.n.getType()).showProgress(true).execute(getContext(), new RequestListener<StreamingHomeResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.11
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull StreamingHomeResponse streamingHomeResponse) {
                    StreamingsFragmentView.this.i.a(streamingHomeResponse, StreamingsFragmentView.this.n);
                    StreamingsFragmentView.this.a = streamingHomeResponse.live;
                    StreamingsFragmentView.this.b = streamingHomeResponse.vod;
                    StreamingsFragmentView.this.g();
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onFinally(StreamingHomeResponse streamingHomeResponse, PlugError plugError) {
                    StreamingsFragmentView.this.l.setRefreshing(false);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    StreamingsFragmentView.this.a(plugError);
                }
            });
        }
    }

    public void f() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair<>(c(R.string.search_recent), new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.3
            @Override // java.lang.Runnable
            public void run() {
                StreamingsFragmentView.this.n = SORT_BY.NEWEST;
                StreamingsFragmentView.this.c();
            }
        }));
        arrayList.add(new Pair<>(c(R.string.live_vod_sort_viewcount), new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView.4
            @Override // java.lang.Runnable
            public void run() {
                StreamingsFragmentView.this.n = SORT_BY.VIEWS;
                StreamingsFragmentView.this.c();
            }
        }));
        a("Sort", arrayList);
    }
}
