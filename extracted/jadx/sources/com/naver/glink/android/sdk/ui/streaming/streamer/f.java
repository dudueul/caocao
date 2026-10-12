package com.naver.glink.android.sdk.ui.streaming.streamer;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.m;
import com.naver.glink.android.sdk.a.v;
import com.squareup.otto.Subscribe;

/* JADX INFO: compiled from: StreamerWidgetFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(17)
public class f extends com.naver.glink.android.sdk.ui.parent.a.a {
    private static final String a = "com.naver.glink.ARG_STREAMING_ID";
    private String b;
    private TextView c;
    private TextView d;
    private TextView e;
    private com.naver.glink.android.sdk.ui.streaming.a.f f;
    private View.OnClickListener g = new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.f.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.naver.glink.android.sdk.ui.streaming.b.a((Context) f.this.getActivity(), com.naver.glink.android.sdk.ui.streaming.b.e(f.this.getFragmentManager()), true);
        }
    };

    public static f a(String str) {
        f fVar = new f();
        Bundle bundle = new Bundle();
        bundle.putString(a, str);
        fVar.setArguments(bundle);
        return fVar;
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.streaming.a.a.C0054a c0054a) {
        if (TextUtils.equals(c0054a.a, this.b)) {
            this.e.setText(v.a(getActivity(), c0054a.b, Integer.MAX_VALUE));
        }
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.streaming.a.a.b bVar) {
        if (TextUtils.equals(bVar.a, this.b)) {
            this.d.setText(v.a(getActivity(), Math.max(bVar.b, 0), Integer.MAX_VALUE));
        }
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.streaming.a.a.c cVar) {
        if (!TextUtils.equals(cVar.c, this.b) || cVar.a) {
            return;
        }
        this.f.a(false, cVar.d);
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.streaming.a.d.a aVar) {
        this.f.a(aVar.a);
    }

    @Subscribe
    public void a(c.a aVar) {
        this.c.setText(aVar.a);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getArguments().getString(a);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(bundle);
        dialogOnCreateDialog.setCanceledOnTouchOutside(false);
        Window window = dialogOnCreateDialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
            window.setDimAmount(0.0f);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(51);
            window.setWindowAnimations(R.style.multimedia_dialog_anim);
            window.addFlags(android.R.string.face_error_security_update_required);
        }
        return dialogOnCreateDialog;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_streamer_widget, viewGroup, false);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.c = (TextView) view.findViewById(R.id.streamer_streaming_time);
        this.d = (TextView) view.findViewById(R.id.streamer_viewer_count);
        this.d.setText("0");
        this.e = (TextView) view.findViewById(R.id.streamer_like_count);
        this.e.setText("0");
        com.naver.glink.android.sdk.ui.parent.a.c.a(getDialog()).a(this.g).a(view.findViewById(R.id.streamer_touch_move));
        ListView listView = (ListView) view.findViewById(R.id.streamer_chat_messages);
        this.f = new com.naver.glink.android.sdk.ui.streaming.a.f(getActivity(), true);
        listView.setAdapter((ListAdapter) this.f);
        listView.setEmptyView(view.findViewById(R.id.empty));
        final View viewFindViewById = view.findViewById(R.id.streamer_toggle_chatting_icon);
        final View viewFindViewById2 = view.findViewById(R.id.streamer_chat_messages_layout);
        view.findViewById(R.id.streamer_toggle_chatting).setOnClickListener(new m() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.f.2
            @Override // com.naver.glink.android.sdk.a.m
            public void a(boolean z) {
                viewFindViewById.setRotation(z ? 180.0f : 0.0f);
                viewFindViewById2.setVisibility(z ? 0 : 8);
            }
        });
    }
}
