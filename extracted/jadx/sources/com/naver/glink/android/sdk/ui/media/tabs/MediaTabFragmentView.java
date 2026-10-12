package com.naver.glink.android.sdk.ui.media.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;
import com.naver.glink.android.sdk.ui.profile.CafeJoinView;
import com.naver.glink.android.sdk.ui.widget.MenuRadioButton;
import com.naver.glink.android.sdk.ui.widget.ScrollListenerLayout;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class MediaTabFragmentView extends PlugFragmentView {
    public static final String a = "TAB_TAG";
    private List<MediaTab> b;
    private ScrollListenerLayout c;
    private RadioGroup d;
    private MediaTab.Type e;

    public MediaTabFragmentView(Context context) {
        super(context);
    }

    private int a(MediaTab.Type type) {
        for (MediaTab mediaTab : this.b) {
            if (type.equals(mediaTab.a)) {
                return mediaTab.c;
            }
        }
        return R.id.media_menu_image;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaTab.Type a(int i) {
        for (MediaTab mediaTab : this.b) {
            if (i == mediaTab.c) {
                return mediaTab.a;
            }
        }
        return MediaTab.Type.PHOTOS;
    }

    public static MediaTabFragmentView a(Context context, MediaTab.Type type) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(a, type);
        MediaTabFragmentView mediaTabFragmentView = new MediaTabFragmentView(context);
        mediaTabFragmentView.setArguments(bundle);
        return mediaTabFragmentView;
    }

    private void a(final ViewGroup viewGroup) {
        this.d = (RadioGroup) viewGroup.findViewById(R.id.media_menu_radio_group);
        RadioButton radioButton = (RadioButton) viewGroup.findViewById(R.id.media_menu_image);
        MenuRadioButton menuRadioButton = (MenuRadioButton) viewGroup.findViewById(R.id.media_menu_video);
        d.e().a((View) radioButton, false);
        d.e().a((View) menuRadioButton, false);
        this.d.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.naver.glink.android.sdk.ui.media.tabs.MediaTabFragmentView.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                a.a(MediaTabFragmentView.this.b, viewGroup, i);
                MediaTabFragmentView.this.e = MediaTabFragmentView.this.a(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (this.d.getCheckedRadioButtonId() == -1) {
            this.d.check(a(this.e));
        } else {
            this.d.check(a(this.e));
            a.a(this.b, a(this.e));
        }
    }

    private void f() {
        if (this.e != null) {
            if (this.e == MediaTab.Type.PHOTOS) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.MEDIA_IMAGE_LIST);
            } else if (this.e == MediaTab.Type.VIDEOS) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.MEDIA_VIDEO_LIST);
            }
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_media_tab, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.b = a.a();
        this.e = (MediaTab.Type) getArguments().getSerializable(a);
        if (this.e == null) {
            this.e = MediaTab.Type.PHOTOS;
        }
        this.c = (ScrollListenerLayout) view.findViewById(R.id.media_list_scroll_view);
        this.c.setEnable(true);
        this.c.setNestedScrollEnable(true);
        a((ViewGroup) view);
        c();
    }

    @Subscribe
    public void a(LoginHelper.b bVar) {
        if (bVar != null) {
            c();
        }
    }

    @Subscribe
    public void a(CafeJoinView.a aVar) {
        if (aVar == null || !aVar.a) {
            return;
        }
        c();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void b_() {
        if (isAttachedToWindow()) {
            if (this.d != null) {
                this.d.check(a(this.e));
            }
            f();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.media.tabs.MediaTabFragmentView.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                if (iVar == null) {
                    return;
                }
                MediaTabFragmentView.this.g();
                MediaTabFragmentView.this.a(iVar.live);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                MediaTabFragmentView.this.a(plugError);
            }
        });
    }
}
