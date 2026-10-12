package com.naver.glink.android.sdk.ui.streaming.streamings.a;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.api.streaming.StreamingHomeResponse;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PopularStreamersViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private final RecyclerView a;
    private final C0059a b;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.streamings.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PopularStreamersViewHolder.java */
    private static class C0059a extends RecyclerView.Adapter<b> implements b.InterfaceC0060a {
        private Context a;
        private List<StreamingHomeResponse.Streamer> b = new ArrayList();

        public C0059a(Context context) {
            this.a = context;
        }

        public StreamingHomeResponse.Streamer a(int i) {
            return this.b.get(i);
        }

        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public b onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(this.a).inflate(R.layout.item_streamings_popular_streamer, viewGroup, false), this);
        }

        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(b bVar, int i) {
            bVar.a(this.a, a(i));
        }

        public void a(List<StreamingHomeResponse.Streamer> list) {
            this.b.clear();
            this.b.addAll(list);
        }

        @Override // com.naver.glink.android.sdk.ui.streaming.streamings.a.a.b.InterfaceC0060a
        public void b(int i) {
            com.naver.glink.android.sdk.ui.tabs.b.a(a(i).streamerId, false);
        }

        public int getItemCount() {
            return this.b.size();
        }
    }

    /* JADX INFO: compiled from: PopularStreamersViewHolder.java */
    private static class b extends RecyclerView.ViewHolder {
        private final ImageView a;
        private final View b;
        private final View c;
        private final TextView d;
        private InterfaceC0060a e;

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.streamings.a.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PopularStreamersViewHolder.java */
        interface InterfaceC0060a {
            void b(int i);
        }

        public b(View view, InterfaceC0060a interfaceC0060a) {
            super(view);
            this.e = interfaceC0060a;
            this.a = (ImageView) view.findViewById(R.id.streamer_profile);
            this.b = view.findViewById(R.id.live_on);
            this.c = view.findViewById(R.id.live_on_circle);
            this.d = (TextView) view.findViewById(R.id.streamer_nickname);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.streaming.streamings.a.a.b.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    b.this.e.b(b.this.getAdapterPosition());
                }
            });
        }

        public void a(Context context, StreamingHomeResponse.Streamer streamer) {
            if (streamer != null) {
                Glide.with(context).load(streamer.profileImageUrl).error(R.drawable.gl_ls_img_profilephoto).into(this.a);
                this.b.setVisibility(streamer.live ? 0 : 4);
                this.c.setVisibility(streamer.live ? 0 : 4);
                this.d.setText(streamer.nickName);
            }
        }
    }

    public a(Context context, View view) {
        this.a = view.findViewById(R.id.popular_streamers);
        this.a.setLayoutManager(new LinearLayoutManager(context, 0, false));
        this.b = new C0059a(context);
        this.a.setAdapter(this.b);
    }

    public void a(List<StreamingHomeResponse.Streamer> list) {
        this.b.a(list);
        this.b.notifyDataSetChanged();
    }
}
