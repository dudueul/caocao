package com.naver.glink.android.sdk.ui.streaming.streamer;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.g;
import com.naver.glink.android.sdk.a.k;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.ui.main.MainFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: compiled from: StreamerUploadFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class e extends com.naver.glink.android.sdk.ui.parent.a.a {
    private static final String a = "com.naver.glink.ARG_STREAMER_SETTINGS";
    private StreamerSettings b;
    private EditText c;
    private Button d;

    public static e a(StreamerSettings streamerSettings) {
        e eVar = new e();
        Bundle bundle = new Bundle();
        bundle.putParcelable(a, streamerSettings);
        eVar.setArguments(bundle);
        return eVar;
    }

    private void a(final EditText editText) {
        editText.setText(this.b.a);
        editText.setSelection(this.b.a.length());
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
        e();
        editText.addTextChangedListener(new g() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.e.3
            @Override // com.naver.glink.android.sdk.a.g, android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                e.this.e();
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.e.4
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                k.a((Context) e.this.getActivity(), editText);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z) {
        if (d()) {
            final com.naver.glink.android.sdk.ui.widget.progress.c cVar = new com.naver.glink.android.sdk.ui.widget.progress.c();
            cVar.a(getActivity());
            this.b.a = c();
            com.naver.glink.android.sdk.api.streaming.c.a(this.b.g, z, this.b.a).execute(getActivity(), new RequestListener<com.naver.glink.android.sdk.api.streaming.c.b>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.e.5
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.streaming.c.b bVar) {
                    e.this.a(z, bVar);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onFinally(com.naver.glink.android.sdk.api.streaming.c.b bVar, PlugError plugError) {
                    cVar.a();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, com.naver.glink.android.sdk.api.streaming.c.b bVar) {
        com.naver.glink.android.sdk.b.a(bVar.viewCount, bVar.likeCount);
        Glink.startWidget(getActivity());
        MainFragmentView mainFragmentViewI = com.naver.glink.android.sdk.ui.main.b.i();
        if (mainFragmentViewI != null) {
            mainFragmentViewI.e();
        }
        if (z) {
            Toast.makeText(getActivity(), R.string.upload_complete_message, 1).show();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.e.6
            @Override // java.lang.Runnable
            public void run() {
                e.this.dismiss();
            }
        }, 700L);
    }

    private String c() {
        return this.c.getText().toString();
    }

    private boolean d() {
        return !TextUtils.isEmpty(c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.d.setTextColor(d() ? -1 : Color.parseColor("#a3a9a9"));
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a
    public void b() {
        a(false);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_streamer_upload, viewGroup, false);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        a();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = (StreamerSettings) getArguments().getParcelable(a);
        ((TextView) view.findViewById(R.id.streaming_play_time_message)).setText(getResources().getString(R.string.live_publish_notice_message, this.b.c()));
        this.d = (Button) view.findViewById(R.id.upload);
        this.d.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.e.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                e.this.a(true);
            }
        });
        view.findViewById(R.id.delete).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.e.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                e.this.a(false);
            }
        });
        this.c = (EditText) view.findViewById(R.id.streaming_title);
        a(this.c);
    }
}
