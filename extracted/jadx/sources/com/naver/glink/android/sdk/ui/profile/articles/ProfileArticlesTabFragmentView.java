package com.naver.glink.android.sdk.ui.profile.articles;

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
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;
import com.naver.glink.android.sdk.ui.streaming.b;
import com.naver.glink.android.sdk.ui.widget.ScrollListenerLayout;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ProfileArticlesTabFragmentView extends PlugFragmentView {
    public static final String a = "-1";
    private static final String b = "com.naver.glink.OTHER_PROFILE_ID";
    private static final String c = "com.naver.glink.IS_WRITER";
    private static final String d = "com.naver.glink.ARG_SELECTED_TAB_TYPE";
    private View e;
    private List<ProfileTab> f;
    private ScrollListenerLayout i;
    private String j;
    private boolean k;

    public ProfileArticlesTabFragmentView(Context context) {
        super(context);
    }

    public static ProfileArticlesTabFragmentView a(Context context, boolean z, ProfileTab.Type type) {
        return a(context, z, type, "-1");
    }

    public static ProfileArticlesTabFragmentView a(Context context, boolean z, ProfileTab.Type type, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(b, str);
        bundle.putBoolean(c, z);
        bundle.putSerializable(d, type);
        ProfileArticlesTabFragmentView profileArticlesTabFragmentView = new ProfileArticlesTabFragmentView(context);
        profileArticlesTabFragmentView.setArguments(bundle);
        return profileArticlesTabFragmentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final ViewGroup viewGroup, Responses.i iVar) {
        this.e = viewGroup.findViewById(R.id.menu_tab_layout);
        RadioGroup radioGroup = (RadioGroup) this.e.findViewById(R.id.profile_menu_radio_group);
        RadioButton radioButton = (RadioButton) this.e.findViewById(R.id.profile_write_tab);
        RadioButton radioButton2 = (RadioButton) this.e.findViewById(R.id.profile_comment_tab);
        RadioButton radioButton3 = (RadioButton) this.e.findViewById(R.id.profile_like_tab);
        RadioButton radioButton4 = (RadioButton) this.e.findViewById(R.id.profile_streaming_tab);
        d.e().a((View) radioButton, false);
        d.e().a((View) radioButton2, false);
        d.e().a((View) radioButton3, false);
        d.e().a((View) radioButton4, false);
        if (iVar == null) {
            return;
        }
        if (this.k) {
            radioButton3.setVisibility(0);
        } else {
            radioButton3.setVisibility(8);
        }
        if (iVar.live && b.b()) {
            radioButton4.setVisibility(0);
        } else {
            radioButton4.setVisibility(8);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.naver.glink.android.sdk.ui.profile.articles.ProfileArticlesTabFragmentView.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup2, @IdRes int i) {
                a.a(ProfileArticlesTabFragmentView.this.f, viewGroup, i);
            }
        });
        ProfileTab.Type type = getArguments() != null ? (ProfileTab.Type) getArguments().getSerializable(d) : null;
        if (type == null) {
            type = ProfileTab.Type.WRITE;
        }
        int iA = a.a(this.f, type);
        if (iA == 0) {
            iA = R.id.profile_write_tab;
        }
        radioGroup.check(iA);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_profile_articles_tab, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        if (getArguments() != null) {
            this.j = getArguments().getString(b);
            this.k = getArguments().getBoolean(c);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(final View view, Bundle bundle) {
        super.a(view, bundle);
        this.f = a.a(this.j);
        this.i = (ScrollListenerLayout) view.findViewById(R.id.profile_list_scroll_view);
        this.i.setEnable(true);
        this.i.setNestedScrollEnable(true);
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.profile.articles.ProfileArticlesTabFragmentView.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                ProfileArticlesTabFragmentView.this.a((ViewGroup) view, iVar);
                ProfileArticlesTabFragmentView.this.c();
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
    }
}
