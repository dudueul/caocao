package com.naver.glink.android.sdk.ui.streaming.a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.api.streaming.ChattingMessage;
import com.naver.glink.android.sdk.ui.input.TextInputFragmentView;
import com.naver.glink.android.sdk.ui.streaming.streamer.StreamerSettings;
import com.naver.glink.android.sdk.ui.streaming.viewer.WatchingStreaming;
import com.squareup.otto.Subscribe;

/* JADX INFO: compiled from: ChattingFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c extends com.naver.glink.android.sdk.ui.parent.a.b implements b.InterfaceC0055b {
    private static final String a = "com.naver.glink.ARG_SHOW_CLOSE_BUTTON";
    private b.a b;
    private TextInputFragmentView c;
    private ListView d;
    private f e;

    public static c a(boolean z) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putBoolean(a, z);
        cVar.setArguments(bundle);
        return cVar;
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.a.b.InterfaceC0055b
    public void a() {
        if (this.c == null) {
            this.c = TextInputFragmentView.b(getActivity());
            new com.naver.glink.android.sdk.ui.input.a(this.c);
        }
        this.c.c_();
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.input.a.C0043a c0043a) {
        this.b.a(c0043a.a);
    }

    @Subscribe
    public void a(a.c cVar) {
        this.e.a(cVar.a, cVar.d);
        this.d.setSelection(cVar.d.size() - 1);
    }

    @Override // com.naver.glink.android.sdk.a.d.b
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void setPresenter(b.a aVar) {
        this.b = aVar;
    }

    @Subscribe
    public void a(d.a aVar) {
        this.e.a(aVar.a);
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.a.b.InterfaceC0055b
    public void a(WatchingStreaming watchingStreaming, ChattingMessage chattingMessage) {
        if (getFragmentManager().findFragmentByTag("chatting-message-detail-fragment") == null) {
            d.a(watchingStreaming, chattingMessage).show(getFragmentManager(), "chatting-message-detail-fragment");
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_streaming_chatting, viewGroup, false);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        if (this.b != null) {
            this.b.b();
        }
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle != null) {
            return;
        }
        this.d = (ListView) view.findViewById(R.id.streaming_chat_messages);
        this.e = new f(getActivity(), false);
        this.e.a(new f.b() { // from class: com.naver.glink.android.sdk.ui.streaming.a.c.1
            @Override // com.naver.glink.android.sdk.ui.streaming.a.f.b
            public void a(f.a aVar) {
                c.this.b.a(aVar.b);
            }
        });
        this.d.setAdapter((ListAdapter) this.e);
        this.d.setEmptyView(view.findViewById(R.id.empty));
        this.d.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.naver.glink.android.sdk.ui.streaming.a.c.2
            private boolean b = false;

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (this.b && c.this.e.getCount() > 0 && i == 0) {
                    c.this.b.d();
                    this.b = false;
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == 1) {
                    this.b = true;
                }
            }
        });
        if (getArguments().getBoolean(a)) {
            View viewFindViewById = view.findViewById(R.id.streaming_close);
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.a.c.3
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    StreamerSettings streamerSettingsE = com.naver.glink.android.sdk.ui.streaming.b.e(c.this.getActivity().getFragmentManager());
                    if (streamerSettingsE != null) {
                        streamerSettingsE.e = false;
                    }
                    new com.naver.glink.android.sdk.ui.streaming.streamer.b.a(false).a();
                }
            });
        }
        view.findViewById(R.id.streaming_write_chat_message).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.a.c.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                c.this.b.c();
            }
        });
        this.b.a();
    }
}
