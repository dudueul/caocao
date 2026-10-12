package com.naver.glink.android.sdk.ui.streaming.viewer;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.streaming.StreamingVideoResponse;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.streaming.a.g;
import com.naver.glink.android.sdk.ui.streaming.animation.LikeSurfaceView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;

/* JADX INFO: compiled from: StreamingViewerDialog.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c extends com.naver.glink.android.sdk.ui.parent.a.a implements d.a {
    private static final String a = "com.naver.glink.ARG_STREAMING_TYPE";
    private static final String b = "com.naver.glink.ARG_STREAMING_ID";
    private WatchingStreaming.Type c;
    private String d;
    private WatchingStreaming e;
    private e f;
    private g g;
    private Point h;
    private View i;
    private LikeSurfaceView j;

    public static c a(WatchingStreaming.Type type, String str) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putSerializable(a, type);
        bundle.putString(b, str);
        cVar.setArguments(bundle);
        return cVar;
    }

    private static void a(Context context, WatchingStreaming.Type type, String str) {
        FragmentManager fragmentManagerB = com.naver.glink.android.sdk.ui.streaming.b.b(context);
        if (fragmentManagerB.findFragmentByTag("streaming-viewer-fragment") != null) {
            ((c) fragmentManagerB.findFragmentByTag("streaming-viewer-fragment")).dismiss();
        }
        a(type, str).show(fragmentManagerB.beginTransaction().addToBackStack("streaming-viewer-fragment"), "streaming-viewer-fragment");
    }

    public static void a(Context context, String str) {
        a(context, WatchingStreaming.Type.STREAMING, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(PlugError plugError) {
        if (plugError.hasErrorMessage()) {
            AlertDialogFragmentView.b(getActivity(), plugError.errorMessage).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.c.2
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface, int i) {
                    c.this.dismiss();
                }
            }).a();
        }
    }

    public static void b(Context context, String str) {
        a(context, WatchingStreaming.Type.VIDEO, str);
    }

    private void e() {
        if (this.c == WatchingStreaming.Type.STREAMING) {
            com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.STREAMING_DETAIL_ONAIR, this.d);
        } else if (this.c == WatchingStreaming.Type.VIDEO) {
            com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.STREAMING_DETAIL_VOD, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.e == null) {
            com.naver.glink.android.sdk.api.streaming.c.a(getActivity(), this.c, this.d, new com.naver.glink.android.sdk.api.streaming.c.a() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.c.1
                @Override // com.naver.glink.android.sdk.api.streaming.c.a
                public void a(@NonNull WatchingStreaming watchingStreaming) {
                    c.this.e = watchingStreaming;
                    c.this.f();
                }

                @Override // com.naver.glink.android.sdk.api.streaming.c.a
                public void a(@NonNull PlugError plugError) {
                    c.this.a(plugError);
                }
            });
        } else {
            g();
            h();
        }
    }

    private void g() {
        d dVarC = (d) getChildFragmentManager().findFragmentById(R.id.streaming_viewer);
        if (dVarC == null) {
            dVarC = d.c();
            getChildFragmentManager().beginTransaction().add(R.id.streaming_viewer, dVarC).commitAllowingStateLoss();
        }
        dVarC.a(this);
        this.f = new e(getActivity(), dVarC, this.e);
    }

    private void h() {
        com.naver.glink.android.sdk.ui.streaming.a.c cVarA = (com.naver.glink.android.sdk.ui.streaming.a.c) getChildFragmentManager().findFragmentById(R.id.streaming_chatting);
        if (cVarA == null) {
            cVarA = com.naver.glink.android.sdk.ui.streaming.a.c.a(false);
            getChildFragmentManager().beginTransaction().add(R.id.streaming_chatting, cVarA).commit();
        }
        this.g = new g(getActivity(), cVarA, this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.clearFlags(8);
            WindowManager.LayoutParams attributes = window.getAttributes();
            this.h = new Point(attributes.x, attributes.y);
            a();
            if (this.j != null) {
                this.j.b(false);
            }
        }
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.d.a
    public void a(View view, final Runnable runnable) {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.addFlags(8);
            int iA = x.a(4.0f);
            if (this.h == null) {
                this.h = new Point(iA, iA);
            }
            view.setOnClickListener(null);
            com.naver.glink.android.sdk.ui.parent.a.c.a(getDialog()).a(iA).a(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.c.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    c.this.i();
                    runnable.run();
                }
            }).a(view);
            a(this.h.x, this.h.y, x.a(176.0f), x.a(99.0f));
            if (this.j != null) {
                this.j.b(true);
            }
        }
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.streaming.a.a.C0054a c0054a) {
        if (c0054a.d) {
            this.j.a(c0054a.c);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.d.a
    public void a(boolean z) {
        this.i.setVisibility(z ? 0 : 8);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a
    public void b() {
        dismiss();
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.d.a
    public void c() {
        this.g.e();
    }

    @Override // com.naver.glink.android.sdk.ui.streaming.viewer.d.a
    public void d() {
        com.naver.glink.android.sdk.api.streaming.c.a(this.d).execute(getActivity(), new RequestListener<StreamingVideoResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.c.3
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull StreamingVideoResponse streamingVideoResponse) {
                c.this.e = new WatchingStreaming(streamingVideoResponse);
                c.this.f.a(c.this.e);
            }
        });
    }

    @Override // android.app.DialogFragment
    public void dismiss() {
        this.j.b();
        super.dismiss();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = (WatchingStreaming.Type) getArguments().getSerializable(a);
        this.d = getArguments().getString(b);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(bundle);
        Window window = dialogOnCreateDialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
            window.setDimAmount(0.0f);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(51);
            window.setWindowAnimations(R.style.multimedia_dialog_anim);
            window.addFlags(android.R.string.face_error_canceled);
            window.setSoftInputMode(window.getAttributes().softInputMode | 48 | 2);
            window.clearFlags(2);
        }
        return dialogOnCreateDialog;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.dialog_streaming_viewer, viewGroup, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.j != null) {
            this.j.a();
        }
        e();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        a();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.i = view.findViewById(R.id.streaming_chatting);
        this.j = (LikeSurfaceView) view.findViewById(R.id.streaming_like_animation_layout);
        f();
    }
}
