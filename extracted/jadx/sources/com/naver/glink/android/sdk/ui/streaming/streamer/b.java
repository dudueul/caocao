package com.naver.glink.android.sdk.ui.streaming.streamer;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.ui.streaming.a.g;
import com.squareup.otto.Subscribe;

/* JADX INFO: compiled from: StreamerChattingFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b extends com.naver.glink.android.sdk.ui.parent.a.a {
    private static final String a = "com.naver.glink.ARG_STREAMING_ID";
    private String b;

    /* JADX INFO: compiled from: StreamerChattingFragment.java */
    public static class a extends com.naver.glink.android.sdk.a.a.a {
        public final boolean a;

        public a(boolean z) {
            this.a = z;
        }
    }

    public static b a(String str) {
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putString(a, str);
        bVar.setArguments(bundle);
        return bVar;
    }

    private void a(final boolean z) {
        FrameLayout frameLayout = (FrameLayout) getView().findViewById(R.id.streaming_chatting);
        frameLayout.setVisibility(z ? 0 : 4);
        frameLayout.post(new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.b.1
            @Override // java.lang.Runnable
            public void run() {
                b.this.b(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        int dimension;
        int i;
        if (isAdded()) {
            int dimension2 = com.naver.glink.android.sdk.d.p().b().x;
            int i2 = com.naver.glink.android.sdk.d.p().b().y;
            if (com.naver.glink.android.sdk.d.j()) {
                dimension = (int) getResources().getDimension(R.dimen.streaming_chatting_height);
                i = i2 - dimension;
            } else {
                dimension2 = (int) getResources().getDimension(R.dimen.streaming_chatting_width);
                dimension = i2 - 1;
                i = 0;
            }
            if (!z) {
                dimension = 0;
            }
            a(0, i, dimension2, dimension);
        }
    }

    private void c() {
        com.naver.glink.android.sdk.ui.streaming.a.c cVarA = (com.naver.glink.android.sdk.ui.streaming.a.c) getChildFragmentManager().findFragmentById(R.id.streaming_chatting);
        if (cVarA == null) {
            cVarA = com.naver.glink.android.sdk.ui.streaming.a.c.a(true);
            getChildFragmentManager().beginTransaction().add(R.id.streaming_chatting, cVarA).commit();
        }
        new g(getActivity(), cVarA, this.b);
    }

    private void d() {
        a(false);
    }

    @Subscribe
    public void a(a aVar) {
        a(aVar.a);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getArguments().getString(a);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(bundle);
        Window window = dialogOnCreateDialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
            window.setDimAmount(0.0f);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(53);
            window.setWindowAnimations(R.style.multimedia_dialog_anim);
            window.addFlags(16778248);
        }
        return dialogOnCreateDialog;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_streamer_chatting, viewGroup, false);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        d();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        c();
    }
}
