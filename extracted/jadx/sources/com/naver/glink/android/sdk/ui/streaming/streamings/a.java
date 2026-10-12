package com.naver.glink.android.sdk.ui.streaming.streamings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.streaming.StreamingHomeResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingVideosResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingsResponse;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.streaming.streamings.a.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: StreamingsAdapter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class a extends BaseAdapter {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;
    private static final int i = 8;
    private static final int j = 9;
    private final Context k;
    private final StreamingsFragmentView.Type l;
    private View.OnClickListener n;
    private View.OnClickListener o;
    private List<C0058a> m = new ArrayList();
    private StreamingsFragmentView.SORT_BY p = StreamingsFragmentView.SORT_BY.NEWEST;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.streamings.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: StreamingsAdapter.java */
    static class C0058a {
        public final int a;
        public final Object b;

        C0058a(int i, Object obj) {
            this.a = i;
            this.b = obj;
        }
    }

    a(Context context, StreamingsFragmentView.Type type) {
        this.k = context;
        this.l = type;
    }

    private int b(int i2) {
        for (int i3 = 0; i3 < getCount(); i3++) {
            if (getItemViewType(i3) == i2) {
                return i3;
            }
        }
        return -1;
    }

    private C0058a b(String str) {
        for (C0058a c0058a : this.m) {
            if (c0058a.b instanceof StreamingsResponse.Streaming) {
                if (TextUtils.equals(str, ((StreamingsResponse.Streaming) c0058a.b).streamingId)) {
                    return c0058a;
                }
            } else if ((c0058a.b instanceof StreamingVideosResponse.StreamingVideo) && TextUtils.equals(str, ((StreamingVideosResponse.StreamingVideo) c0058a.b).streamingId)) {
                return c0058a;
            }
        }
        return null;
    }

    private List<C0058a> b(StreamingVideosResponse streamingVideosResponse) {
        ArrayList arrayList = new ArrayList();
        Iterator<StreamingVideosResponse.StreamingVideo> it = streamingVideosResponse.streamingVideos.iterator();
        while (it.hasNext()) {
            arrayList.add(new C0058a(2, it.next()));
        }
        return arrayList;
    }

    private List<C0058a> b(StreamingsResponse streamingsResponse) {
        ArrayList arrayList = new ArrayList();
        Iterator<StreamingsResponse.Streaming> it = streamingsResponse.streamings.iterator();
        while (it.hasNext()) {
            arrayList.add(new C0058a(2, it.next()));
        }
        if (!streamingsResponse.metadata.last) {
            arrayList.add(new C0058a(3, null));
        }
        return arrayList;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public C0058a getItem(int i2) {
        return this.m.get(i2);
    }

    void a(View.OnClickListener onClickListener) {
        this.n = onClickListener;
    }

    void a(StreamingHomeResponse streamingHomeResponse, StreamingsFragmentView.SORT_BY sort_by) {
        this.p = sort_by;
        this.m.clear();
        ArrayList arrayList = new ArrayList();
        switch (this.l) {
            case ALL_STREAMING:
                int i2 = streamingHomeResponse.live.metadata.totalCount;
                if (i2 == 0) {
                    arrayList.add(new C0058a(0, d.a(R.string.live_inprogress)));
                    arrayList.add(new C0058a(6, null));
                } else {
                    arrayList.add(new C0058a(0, d.a(R.string.live_inprogress) + " " + v.a(this.k, i2)));
                    arrayList.addAll(b(streamingHomeResponse.live));
                }
                StreamingHomeResponse.StreamingMembers streamingMembers = streamingHomeResponse.popularStreamer;
                if (streamingMembers != null && !streamingMembers.streamers.isEmpty()) {
                    arrayList.add(new C0058a(4, d.a(R.string.live_popular_streamer)));
                    arrayList.add(new C0058a(5, streamingMembers.streamers));
                }
                int i3 = streamingHomeResponse.vod.metadata.totalCount;
                if (i3 != 0) {
                    arrayList.add(new C0058a(1, d.a(R.string.live_end) + " " + v.a(this.k, i3)));
                    arrayList.addAll(b(streamingHomeResponse.vod));
                } else {
                    arrayList.add(new C0058a(1, d.a(R.string.live_end)));
                    arrayList.add(new C0058a(6, null));
                }
                break;
            case PROFILE_STREAMING:
                if (streamingHomeResponse.live.streamings.isEmpty()) {
                    arrayList.add(new C0058a(8, this.k.getString(R.string.empty_live_message)));
                } else {
                    arrayList.add(new C0058a(8, this.k.getString(R.string.on_live_message)));
                }
                if (!streamingHomeResponse.isEmpty()) {
                    if (d.i()) {
                        arrayList.add(new C0058a(9, null));
                    }
                    arrayList.addAll(b(streamingHomeResponse.live));
                    arrayList.addAll(b(streamingHomeResponse.vod));
                } else {
                    arrayList.add(new C0058a(7, null));
                }
                break;
        }
        this.m.addAll(arrayList);
        notifyDataSetChanged();
    }

    void a(StreamingVideosResponse streamingVideosResponse) {
        this.m.addAll(this.m.size(), b(streamingVideosResponse));
        notifyDataSetChanged();
    }

    void a(StreamingsResponse streamingsResponse) {
        int iB = b(3);
        if (iB == -1) {
            return;
        }
        this.m.remove(iB);
        this.m.addAll(iB, b(streamingsResponse));
        notifyDataSetChanged();
    }

    void a(String str) {
        C0058a c0058aB = b(str);
        if (c0058aB != null) {
            this.m.remove(c0058aB);
            notifyDataSetChanged();
        }
    }

    void b(View.OnClickListener onClickListener) {
        this.o = onClickListener;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.m.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        return getItem(i2).a;
    }

    @Override // android.widget.Adapter
    @NonNull
    public View getView(int i2, @Nullable View view, @NonNull ViewGroup viewGroup) {
        com.naver.glink.android.sdk.ui.streaming.streamings.a.a aVar;
        b bVar;
        int itemViewType = getItemViewType(i2);
        switch (itemViewType) {
            case 0:
            case 1:
            case 4:
                if (view == null) {
                    view = LayoutInflater.from(this.k).inflate(R.layout.item_streamings_subtitle, viewGroup, false);
                }
                ((TextView) view.findViewById(R.id.subtitle)).setText((String) getItem(i2).b);
                TextView textView = (TextView) view.findViewById(R.id.streaming_order);
                View viewFindViewById = view.findViewById(R.id.divider);
                x.a(viewFindViewById, d.i() ? x.a(16.0f) : -1, -1, -1, -1);
                if (itemViewType == 4) {
                    viewFindViewById.setVisibility(4);
                    return view;
                }
                if (itemViewType != 1) {
                    textView.setVisibility(8);
                    return view;
                }
                textView.setVisibility(0);
                String strA = d.a(R.string.search_recent);
                switch (this.p) {
                    case NEWEST:
                        strA = d.a(R.string.search_recent);
                        break;
                    case VIEWS:
                        strA = d.a(R.string.live_vod_sort_viewcount);
                        break;
                }
                textView.setText(strA);
                if (this.o == null) {
                    return view;
                }
                textView.setOnClickListener(this.o);
                return view;
            case 2:
                if (view == null) {
                    view = LayoutInflater.from(this.k).inflate(R.layout.item_streamings_streaming, viewGroup, false);
                }
                b bVar2 = (b) view.getTag();
                if (bVar2 == null) {
                    b bVar3 = new b(view);
                    view.setTag(bVar3);
                    bVar = bVar3;
                } else {
                    bVar = bVar2;
                }
                Object obj = getItem(i2).b;
                if (obj instanceof StreamingsResponse.Streaming) {
                    bVar.a(this.k, (StreamingsResponse.Streaming) obj);
                    return view;
                }
                if (!(obj instanceof StreamingVideosResponse.StreamingVideo)) {
                    return view;
                }
                bVar.a(this.k, (StreamingVideosResponse.StreamingVideo) obj);
                return view;
            case 3:
                if (view == null) {
                    view = LayoutInflater.from(this.k).inflate(R.layout.item_streamings_load_more, viewGroup, false);
                }
                view.findViewById(R.id.load_more).setOnClickListener(this.n);
                return view;
            case 5:
                if (view == null) {
                    view = LayoutInflater.from(this.k).inflate(R.layout.item_streamings_popular_streamers, viewGroup, false);
                }
                com.naver.glink.android.sdk.ui.streaming.streamings.a.a aVar2 = (com.naver.glink.android.sdk.ui.streaming.streamings.a.a) view.getTag();
                if (aVar2 == null) {
                    com.naver.glink.android.sdk.ui.streaming.streamings.a.a aVar3 = new com.naver.glink.android.sdk.ui.streaming.streamings.a.a(this.k, view);
                    view.setTag(aVar3);
                    aVar = aVar3;
                } else {
                    aVar = aVar2;
                }
                aVar.a((List) getItem(i2).b);
                return view;
            case 6:
                return view == null ? LayoutInflater.from(this.k).inflate(R.layout.item_streamings_empty, viewGroup, false) : view;
            case 7:
                if (view != null) {
                    return view;
                }
                View viewInflate = LayoutInflater.from(this.k).inflate(R.layout.item_streamings_profile_empty, viewGroup, false);
                ((TextView) viewInflate.findViewById(R.id.live_empty_text)).setPadding(0, (d.i() ? viewGroup.getHeight() / 12 : viewGroup.getHeight() / 4) + x.a(20.0f), 0, 0);
                return viewInflate;
            case 8:
                if (view == null) {
                    view = LayoutInflater.from(this.k).inflate(R.layout.item_streamings_live_message, viewGroup, false);
                }
                TextView textView2 = (TextView) view.findViewById(R.id.text);
                Object obj2 = getItem(i2).b;
                if (!(obj2 instanceof String)) {
                    return view;
                }
                textView2.setText(Html.fromHtml((String) obj2));
                return view;
            case 9:
                return view == null ? LayoutInflater.from(this.k).inflate(R.layout.item_streamings_divider, viewGroup, false) : view;
            default:
                throw new IllegalStateException("지원하지 않는 view type 입니다.");
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 10;
    }
}
