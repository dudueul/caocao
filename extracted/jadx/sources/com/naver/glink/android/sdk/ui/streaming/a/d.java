package com.naver.glink.android.sdk.ui.streaming.a;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.streaming.ChattingMessage;
import com.naver.glink.android.sdk.api.streaming.StreamingResponse;
import com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment;
import com.naver.glink.android.sdk.ui.streaming.viewer.WatchingStreaming;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: compiled from: ChattingMessageDetailFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d extends com.naver.glink.android.sdk.ui.parent.a.a {
    private static final String a = "com.naver.glink.ARG_STREAMING";
    private static final String b = "com.naver.glink.ARG_MESSAGE";
    private WatchingStreaming c;
    private ChattingMessage d;

    /* JADX INFO: compiled from: ChattingMessageDetailFragment.java */
    public static class a {
        public final ChattingMessage a;

        private a(ChattingMessage chattingMessage) {
            this.a = chattingMessage;
        }
    }

    public static d a(WatchingStreaming watchingStreaming, ChattingMessage chattingMessage) {
        d dVar = new d();
        Bundle bundle = new Bundle();
        bundle.putParcelable(a, watchingStreaming);
        bundle.putParcelable("com.naver.glink.ARG_MESSAGE", chattingMessage);
        dVar.setArguments(bundle);
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        com.naver.glink.android.sdk.api.streaming.a.a(this.c.b, this.d.streamingMessageId).execute(getActivity(), new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.ui.streaming.a.d.5
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.x xVar) {
                com.naver.glink.android.sdk.a.a.b.c(new a(d.this.d));
                d.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        StreamingResponse.Authority authority = this.c.j.report;
        if (!authority.able) {
            Toast.makeText(getActivity(), authority.errorMessage, 1).show();
        } else {
            StreamingReportFragment.a(StreamingReportFragment.Report.a(this.c.b, this.d)).show(getFragmentManager(), (String) null);
            dismiss();
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_chatting_message_detail, viewGroup, false);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        a();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.c = (WatchingStreaming) getArguments().getParcelable(a);
        this.d = (ChattingMessage) getArguments().getParcelable("com.naver.glink.ARG_MESSAGE");
        Glide.with(getActivity()).load(this.d.profileImageUrl).placeholder(R.drawable.cf_img_pfdefault).into((ImageView) view.findViewById(R.id.chatting_message_detail_profile));
        ((TextView) view.findViewById(R.id.chatting_message_detail_nickname)).setText(this.d.nickName);
        View viewFindViewById = view.findViewById(R.id.chatting_message_detail_report_layout);
        View viewFindViewById2 = view.findViewById(R.id.chatting_message_detail_delete_layout);
        if (this.d.own) {
            viewFindViewById.setVisibility(8);
            view.findViewById(R.id.chatting_message_detail_delete).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.a.d.1
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    d.this.c();
                    d.this.dismiss();
                }
            });
        } else {
            viewFindViewById2.setVisibility(8);
            view.findViewById(R.id.chatting_message_detail_report).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.a.d.2
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    d.this.d();
                }
            });
        }
        view.findViewById(R.id.chatting_message_detail_outside).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.a.d.3
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                d.this.dismiss();
            }
        });
        view.findViewById(R.id.chatting_message_detail_cancel).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.a.d.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                d.this.dismiss();
            }
        });
    }
}
