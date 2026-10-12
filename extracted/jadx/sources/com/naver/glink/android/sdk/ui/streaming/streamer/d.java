package com.naver.glink.android.sdk.ui.streaming.streamer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.g;
import com.naver.glink.android.sdk.a.i;
import com.naver.glink.android.sdk.a.k;
import com.naver.glink.android.sdk.a.m;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.PermissionDialogFragmentView;
import com.naver.glink.android.sdk.ui.parent.plugfragment.ItemsDialogFragmentView;
import com.naver.glink.android.sdk.ui.streaming.publish.PublishService;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: compiled from: StreamerSettingFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d extends com.naver.glink.android.sdk.ui.parent.a.a {
    private static final String a = "com.naver.glink.ARG_STREAMER_SETTINGS";
    private static final String b = "com.naver.glink.ARG_SHOW_WIDGET";
    private static final int c = 100000;
    private StreamerSettings d;
    private c e;
    private EditText f;
    private Button g;
    private TextView h;

    /* JADX INFO: compiled from: StreamerSettingFragment.java */
    private class a {
        private com.naver.glink.android.sdk.ui.widget.progress.c b;
        private boolean c;

        private a() {
            this.b = new com.naver.glink.android.sdk.ui.widget.progress.c();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(PlugError plugError) {
            Toast.makeText(d.this.getActivity(), plugError.errorMessage, 1).show();
            this.b.a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            this.c = true;
            com.naver.glink.android.sdk.api.streaming.c.c(d.this.d.g).execute(d.this.getActivity(), new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.a.3
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.x xVar) {
                    com.naver.glink.android.sdk.api.streaming.c.a(d.this.d.g, false, d.this.d.a).execute(d.this.getActivity(), new RequestListener<com.naver.glink.android.sdk.api.streaming.c.b>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.a.3.1
                        @Override // com.naver.plug.android.core.api.request.RequestListener
                        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                        public void onFinally(com.naver.glink.android.sdk.api.streaming.c.b bVar, PlugError plugError) {
                            a.this.c();
                        }
                    });
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    a.this.a(plugError);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            d.this.e.b();
            com.naver.glink.android.sdk.ui.streaming.b.g(d.this.getFragmentManager());
            if (this.c) {
                Glink.startWidget(d.this.getActivity());
            } else {
                com.naver.glink.android.sdk.ui.streaming.b.a(d.this.getActivity(), d.this.e.c());
            }
            this.b.a();
        }

        void a() {
            if (!d.this.d.b()) {
                AlertDialogFragmentView.a(d.this.getActivity(), d.this.getResources().getString(R.string.publish_vod_min_time)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.a.1
                    @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                    public void a(DialogInterface dialogInterface, int i) {
                        a.this.b();
                    }
                }).a();
            } else {
                this.b.a(d.this.getActivity());
                com.naver.glink.android.sdk.api.streaming.c.c(d.this.d.g).execute(d.this.getActivity(), new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.a.2
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull Responses.x xVar) {
                        a.this.c();
                    }

                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    public void onFailure(@NonNull PlugError plugError) {
                        a.this.a(plugError);
                    }
                });
            }
        }
    }

    public static d a(StreamerSettings streamerSettings, boolean z) {
        d dVar = new d();
        Bundle bundle = new Bundle();
        bundle.putParcelable(a, streamerSettings);
        bundle.putBoolean(b, z);
        dVar.setArguments(bundle);
        return dVar;
    }

    private void a(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.streming_body_layout);
        if (linearLayout != null) {
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            if (d()) {
                layoutParams.height = (int) (layoutParams.height + (48.0f * getActivity().getResources().getDisplayMetrics().density));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final TextView textView) {
        ItemsDialogFragmentView.a aVarC = ItemsDialogFragmentView.c();
        for (int i = 0; i < StreamerSettings.VideoResolution.values().length; i++) {
            final StreamerSettings.VideoResolution videoResolution = StreamerSettings.VideoResolution.values()[i];
            aVarC.a(videoResolution.displayName, new Runnable() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.15
                @Override // java.lang.Runnable
                public void run() {
                    d.this.d.f = videoResolution;
                    textView.setText(videoResolution.displayName);
                }
            });
            if (this.d.f == videoResolution) {
                aVarC.a(i);
            }
        }
        aVarC.a(getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(List<String> list) {
        List<String> listA = com.naver.glink.android.sdk.ui.write.b.a(getActivity(), list);
        final String[] strArr = (String[]) listA.toArray(new String[listA.size()]);
        if (strArr.length == 0) {
            return true;
        }
        Activity activity = getActivity();
        String[] strArr2 = new String[2];
        strArr2[0] = getString(R.string.live_require_permission);
        strArr2[1] = this.d.d ? getString(R.string.live_camera_permission) : null;
        PermissionDialogFragmentView.a(activity, strArr2).a(getString(R.string.essential_permission)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.6
            @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c, android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= 23) {
                    d.this.requestPermissions(strArr, 100000);
                }
            }
        }).a();
        return false;
    }

    private void b(View view) {
        if (e()) {
            view.findViewById(R.id.resolution_layout).setVisibility(0);
            TextView textView = (TextView) view.findViewById(R.id.streamer_video_resolution);
            textView.setText(this.d.f.displayName);
            textView.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.11
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    d.this.a((TextView) view2);
                }
            });
        } else {
            final View viewFindViewById = view.findViewById(R.id.chatting_layout);
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(new m(this.d.e) { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.12
                @Override // com.naver.glink.android.sdk.a.m
                public void a(boolean z) {
                    d.this.d.e = z;
                    viewFindViewById.setActivated(z);
                    new b.a(z).a();
                }
            });
            final View viewFindViewById2 = view.findViewById(R.id.mic_layout);
            viewFindViewById2.setVisibility(0);
            viewFindViewById2.setActivated(this.d.c);
            viewFindViewById2.setOnClickListener(new m() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.13
                private void c(boolean z) {
                    PublishService.a(d.this.getActivity(), z);
                }

                @Override // com.naver.glink.android.sdk.a.m
                public void a(boolean z) {
                    d.this.d.c = z;
                    viewFindViewById2.setActivated(z);
                    c(!z);
                }
            });
        }
        c(view);
    }

    private void c(View view) {
        final View viewFindViewById = view.findViewById(R.id.self_camera_layout);
        if (this.d.b && com.naver.glink.android.sdk.d.a(NXRuntimePermissionManager.CAMERA)) {
            viewFindViewById.setVisibility(0);
            viewFindViewById.setEnabled(true);
            viewFindViewById.setOnClickListener(new m(this.d.d) { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.14
                @Override // com.naver.glink.android.sdk.a.m
                public void a(boolean z) {
                    if (!d.this.e()) {
                        if (z && !d.this.a((List<String>) Collections.singletonList(NXRuntimePermissionManager.CAMERA))) {
                            return;
                        }
                        if (z) {
                            com.naver.glink.android.sdk.ui.streaming.b.b(d.this.getFragmentManager());
                        } else {
                            com.naver.glink.android.sdk.ui.streaming.b.c(d.this.getFragmentManager());
                        }
                    }
                    d.this.d.d = z;
                    viewFindViewById.setActivated(z);
                }
            });
        } else if (this.d.b && Build.VERSION.SDK_INT >= 23 && e()) {
            ArrayList arrayList = new ArrayList();
            if (this.d.c) {
                arrayList.add(NXRuntimePermissionManager.RECORD_AUDIO);
                arrayList.add("android.permission.MODIFY_AUDIO_SETTINGS");
            }
            arrayList.add(NXRuntimePermissionManager.CAMERA);
            a(arrayList);
            viewFindViewById.setVisibility(0);
            viewFindViewById.setEnabled(false);
            this.d.d = false;
        }
    }

    private void d(View view) {
        this.f = (EditText) view.findViewById(R.id.streaming_title);
        this.h = (TextView) view.findViewById(R.id.streaming_title_update);
        this.f.setText(this.d.a);
        this.f.setSelection(this.d.a.length());
        this.f.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
        f();
        this.f.addTextChangedListener(new g() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.2
            @Override // com.naver.glink.android.sdk.a.g, android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                d.this.f();
            }
        });
        this.f.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                k.a((Context) d.this.getActivity(), d.this.f);
                return true;
            }
        });
        if (e()) {
            return;
        }
        this.h.setVisibility(0);
        this.h.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                k.a((Context) d.this.getActivity(), d.this.f);
                if (d.this.g()) {
                    d.this.k();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        return !this.d.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        int color = g() ? -1 : Color.parseColor("#a3a9a9");
        if (e()) {
            this.g.setTextColor(color);
        } else {
            this.h.setTextColor(color);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        String strJ = j();
        if (e()) {
            return !TextUtils.isEmpty(strJ);
        }
        return (TextUtils.isEmpty(strJ) || TextUtils.equals(strJ, this.d.a)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (g()) {
            ArrayList arrayList = new ArrayList();
            if (this.d.c) {
                arrayList.add(NXRuntimePermissionManager.RECORD_AUDIO);
                arrayList.add("android.permission.MODIFY_AUDIO_SETTINGS");
            }
            if (this.d.d) {
                arrayList.add(NXRuntimePermissionManager.CAMERA);
            }
            if (a(arrayList)) {
                com.naver.glink.android.sdk.api.requests.a.a(getActivity(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.5
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull Responses.i iVar) {
                        d.this.i();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.d.a = j();
        this.e.b(this.d);
        dismiss();
    }

    private String j() {
        return this.f.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        final String strJ = j();
        com.naver.glink.android.sdk.api.streaming.c.a(this.d.g, strJ).execute(getActivity(), new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.7
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.x xVar) {
                d.this.d.a = strJ;
                d.this.f();
                Toast.makeText(d.this.getActivity(), R.string.live_title_update, 1).show();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                Toast.makeText(d.this.getActivity(), plugError.errorMessage, 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.f != null) {
            k.a((Context) getActivity(), this.f);
        }
        if (!e()) {
            dismiss();
            return;
        }
        com.naver.glink.android.sdk.ui.streaming.b.g(getFragmentManager());
        if (m()) {
            Glink.startWidget(getActivity());
        }
    }

    private boolean m() {
        return getArguments().getBoolean(b);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a
    public void b() {
        l();
    }

    public void c() {
        new a().a();
    }

    public boolean d() {
        return (KeyCharacterMap.deviceHasKey(4) && KeyCharacterMap.deviceHasKey(3)) ? false : true;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = (StreamerSettings) getArguments().getParcelable(a);
        if (e()) {
            this.e = com.naver.glink.android.sdk.ui.streaming.b.f(getFragmentManager());
        } else {
            this.e = com.naver.glink.android.sdk.ui.streaming.b.d(getFragmentManager());
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(bundle);
        Window window = dialogOnCreateDialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
            window.setDimAmount(0.0f);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setWindowAnimations(R.style.multimedia_dialog_anim);
            window.addFlags(16778240);
            window.getAttributes().softInputMode |= 2;
        }
        return dialogOnCreateDialog;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_streamer_setting, viewGroup, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.Fragment
    public void onPause() {
        com.naver.glink.android.sdk.a.a.b.b(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (NXRuntimePermissionManager.CAMERA.equals(strArr[i2]) && iArr[i2] == 0) {
                c(getView());
                return;
            }
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a, android.app.Fragment
    public void onResume() {
        super.onResume();
        com.naver.glink.android.sdk.a.a.b.a(this);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        a();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle != null) {
            return;
        }
        view.findViewById(R.id.outside_view).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                d.this.l();
            }
        });
        this.g = (Button) view.findViewById(R.id.streamer_create_or_finish);
        this.g.setText(e() ? com.naver.glink.android.sdk.d.a(R.string.live_broadcast_start) : com.naver.glink.android.sdk.d.a(R.string.live_broadcast_end));
        this.g.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.8
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (d.this.e()) {
                    d.this.h();
                } else {
                    d.this.c();
                }
            }
        });
        if (com.naver.glink.android.sdk.d.i()) {
            view.findViewById(R.id.cancel).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.9
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    d.this.l();
                }
            });
            view.findViewById(R.id.streaming_share).setVisibility(TextUtils.isEmpty(this.d.k) ? 8 : 0);
            view.findViewById(R.id.streaming_share).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.d.10
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    i.a(d.this.getActivity(), com.naver.glink.android.sdk.d.a(R.string.share_url), d.this.d.k);
                }
            });
        }
        d(view);
        b(view);
        a(view);
    }
}
