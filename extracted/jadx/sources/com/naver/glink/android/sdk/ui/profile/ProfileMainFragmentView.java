package com.naver.glink.android.sdk.ui.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.c;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;
import com.naver.glink.android.sdk.ui.profile.articles.ProfileTab;
import com.naver.glink.android.sdk.ui.tabs.Tab;
import com.naver.glink.android.sdk.ui.tabs.b;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpWebDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ProfileMainFragmentView extends PlugFragmentView {
    private static final l a = l.a(ProfileMainFragmentView.class.getSimpleName());
    private static final String b = "com.naver.glink.OTHERPROFILE";
    private static final String c = "com.naver.glink.OTHERPROFILE_ID";
    private static final String d = "com.naver.glink.IS_MY_PROFILE";
    private static final String e = "-1";
    private TextView A;
    private TextView B;
    private TextView C;
    private Responses.q D;
    private String f;
    private boolean i;
    private boolean j;
    private ViewGroup k;
    private View l;
    private View m;
    private CafeInfoView n;
    private CafeJoinView o;
    private SwipeRefreshLayout p;
    private ImageView q;
    private TextView r;
    private TextView s;
    private View t;
    private TextView u;
    private View v;
    private View w;
    private View x;
    private View y;
    private TextView z;

    public ProfileMainFragmentView(Context context) {
        super(context);
    }

    public static ProfileMainFragmentView a(Context context, boolean z, String str, boolean z2) {
        ProfileMainFragmentView profileMainFragmentView = new ProfileMainFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putBoolean(b, z);
        bundle.putString(c, str);
        bundle.putBoolean(d, z2);
        profileMainFragmentView.setArguments(bundle);
        return profileMainFragmentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Responses.i iVar) {
        RequestListener<Responses.q> requestListener = new RequestListener<Responses.q>() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.5
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.q qVar) {
                p.d(ProfileMainFragmentView.this.getContext(), qVar.memberId);
                ProfileMainFragmentView.this.a(qVar, iVar);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinally(Responses.q qVar, PlugError plugError) {
                b.i();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                if (plugError.isPendingJoinError()) {
                    b.i();
                    ProfileMainFragmentView.this.c(ProfileMainFragmentView.this.c(R.string.waiting_join_apply_message));
                    return;
                }
                if (plugError.isNeedJoinError() || plugError.isAgreeTermsError()) {
                    b.i();
                    ProfileMainFragmentView.this.p.setVisibility(8);
                    ProfileMainFragmentView.this.b(plugError);
                } else {
                    if (!plugError.isLoginError()) {
                        ProfileMainFragmentView.this.c(plugError.errorMessage);
                        return;
                    }
                    LoginHelper.a().logout(ProfileMainFragmentView.this.getContext());
                    b.i();
                    ProfileMainFragmentView.this.p.setVisibility(8);
                    ProfileMainFragmentView.this.m();
                }
            }
        };
        if (this.i) {
            if (d.k()) {
                f.a(ProfileTab.Type.WRITE.getTypeName(), this.f).execute(getContext(), requestListener);
                return;
            } else {
                if (d.l()) {
                    c.a(this.f).execute(getContext(), requestListener);
                    return;
                }
                return;
            }
        }
        if (d.k()) {
            f.c(ProfileTab.Type.WRITE.getTypeName()).execute(getContext(), requestListener);
        } else if (d.l()) {
            c.a().execute(getContext(), requestListener);
        }
    }

    private void a(Responses.q qVar) {
        if (qVar == null) {
            return;
        }
        boolean z = qVar.live && com.naver.glink.android.sdk.ui.streaming.b.b();
        View viewFindViewById = this.m.findViewById(R.id.profile_articles_blank);
        View viewFindViewById2 = this.m.findViewById(R.id.profile_articles_live_blank);
        if (this.j && z) {
            viewFindViewById.setVisibility(8);
            this.x.setClickable(true);
            viewFindViewById2.setVisibility(8);
            this.y.setClickable(true);
            return;
        }
        if (this.j && !z) {
            viewFindViewById.setVisibility(8);
            this.x.setClickable(true);
            viewFindViewById2.setVisibility(0);
            this.y.setOnClickListener(null);
            return;
        }
        if (this.j || !z) {
            viewFindViewById.setVisibility(0);
            this.x.setOnClickListener(null);
            this.x.setVisibility(8);
            viewFindViewById2.setVisibility(0);
            this.y.setOnClickListener(null);
            return;
        }
        viewFindViewById.setVisibility(0);
        this.x.setOnClickListener(null);
        this.x.setVisibility(8);
        viewFindViewById2.setVisibility(8);
        this.y.setClickable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Responses.q qVar, Responses.i iVar) {
        g();
        this.p.setVisibility(0);
        this.p.setRefreshing(false);
        this.D = qVar;
        a(qVar);
        b(qVar, iVar);
        c(qVar, iVar);
    }

    public static ProfileMainFragmentView b(Context context) {
        return a(context, false, "-1", true);
    }

    private void b(Responses.q qVar, Responses.i iVar) {
        if (qVar == null) {
            this.l.setVisibility(8);
            return;
        }
        this.l.setVisibility(0);
        this.m.setVisibility(0);
        Glide.with(getContext()).load(qVar.profileImage).asBitmap().placeholder(R.drawable.cf_img_pfdefault).centerCrop().into(new BitmapImageViewTarget(this.q) { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.6
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
            public void setResource(Bitmap bitmap) {
                if (ProfileMainFragmentView.this.getContext() == null) {
                    return;
                }
                RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(ProfileMainFragmentView.this.getResources(), bitmap);
                roundedBitmapDrawableCreate.setCircular(true);
                setDrawable(roundedBitmapDrawableCreate);
            }
        });
        this.r.setText(qVar.nickname);
        this.s.setText(qVar.memberLevelName);
        if (!iVar.c()) {
            this.u.setText(String.format("%,d", Integer.valueOf(qVar.visitCount)));
        } else {
            this.t.setVisibility(8);
            this.u.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(PlugError plugError) {
        if (this.k == null || this.o != null) {
            return;
        }
        this.o = new CafeJoinView(getContext());
        this.o.a(getContext(), plugError);
        this.o.a(getContext(), this.o, com.naver.glink.android.sdk.api.requests.a.a(), isAttachedToWindow());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.o.setLayoutParams(layoutParams);
        this.o.setCafeJoinListener(new CafeJoinView.b() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.3
            @Override // com.naver.glink.android.sdk.ui.profile.CafeJoinView.b
            public void a() {
                AlertDialogFragmentView.a(ProfileMainFragmentView.this.getContext(), ProfileMainFragmentView.this.c(R.string.logout_confirm_message)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.3.1
                    @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                    public void a(DialogInterface dialogInterface, int i) {
                        LoginHelper.a().logout(ProfileMainFragmentView.this.getContext());
                        b.a(Tab.Type.BANNERS);
                        Toast.makeText(ProfileMainFragmentView.this.getContext(), ProfileMainFragmentView.this.c(R.string.logout_complete_message), 0).show();
                        ProfileMainFragmentView.this.f();
                    }
                }).a();
            }

            @Override // com.naver.glink.android.sdk.ui.profile.CafeJoinView.b
            public void a(PlugError plugError2) {
                AlertDialogFragmentView.b(ProfileMainFragmentView.this.getContext(), plugError2.errorMessage).a();
            }

            @Override // com.naver.glink.android.sdk.ui.profile.CafeJoinView.b
            public void a(Response response) {
                ProfileMainFragmentView.this.p.setVisibility(0);
                ProfileMainFragmentView.this.c();
                ProfileMainFragmentView.this.f();
                com.naver.glink.android.sdk.b.c();
            }
        });
        this.k.addView(this.o);
    }

    private void c(Responses.q qVar, Responses.i iVar) {
        if (qVar == null) {
            this.m.setVisibility(8);
        }
        if (iVar.c()) {
            this.z.setVisibility(8);
            this.A.setVisibility(8);
            this.B.setVisibility(8);
        } else {
            this.z.setVisibility(0);
            this.A.setVisibility(0);
            this.B.setVisibility(0);
        }
        this.z.setText(String.format("%,d", Integer.valueOf(qVar.count.write)) + "");
        this.A.setText(String.format("%,d", Integer.valueOf(qVar.count.comment)) + "");
        this.B.setText(String.format("%,d", Integer.valueOf(qVar.count.like)) + "");
        if (qVar.live) {
            this.C.setText(String.format("%,d", Integer.valueOf(qVar.count.live)) + "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.k == null || this.n != null) {
            return;
        }
        this.n = new CafeInfoView(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.n.setLayoutParams(layoutParams);
        this.n.setOnCafeInfoListener(new CafeInfoView.a() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.2
            @Override // com.naver.glink.android.sdk.ui.profile.CafeInfoView.a
            public void a() {
                ProfileMainFragmentView.this.p.setVisibility(0);
                ProfileMainFragmentView.this.l.setVisibility(8);
                ProfileMainFragmentView.this.m.setVisibility(8);
                ProfileMainFragmentView.this.f();
                ProfileMainFragmentView.this.c();
            }
        });
        this.k.addView(this.n);
    }

    private void p() {
        if (LoginHelper.a().isLogin(getContext())) {
            com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.PROFILE_USER);
        } else {
            com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.PROFILE_LOGIN);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.7
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                if (!LoginHelper.a().isLogin(ProfileMainFragmentView.this.getContext())) {
                    LoginHelper.a().builder(ProfileMainFragmentView.this.getContext(), new Glink.OnLoggedInListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.7.1
                        @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
                        public void onLoggedIn(boolean z) {
                            if (z) {
                                ProfileMainFragmentView.this.f();
                                ProfileMainFragmentView.this.c();
                            } else if (!com.naver.plug.android.core.a.b.a(ProfileMainFragmentView.this.getContext())) {
                                ProfileMainFragmentView.this.b(R.string.network_error);
                            } else {
                                ProfileMainFragmentView.this.p.setVisibility(8);
                                ProfileMainFragmentView.this.m();
                            }
                        }
                    }).a(false).a();
                } else if (!com.naver.plug.android.core.a.b.a(ProfileMainFragmentView.this.getContext())) {
                    ProfileMainFragmentView.this.b(R.string.network_error);
                } else {
                    ProfileMainFragmentView.this.f();
                    ProfileMainFragmentView.this.c();
                }
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                ProfileMainFragmentView.this.a(plugError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(NPNXComSignUpWebDialog.KEY_NICK_NAME) != null) {
            return;
        }
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b().a(ProfileModifyDialogFragmentView.a(getContext(), this.D, new ProfileModifyDialogFragmentView.a() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.8
            @Override // com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.a
            public void a(boolean z) {
                if (z) {
                    ProfileMainFragmentView.this.c();
                }
            }
        }), NPNXComSignUpWebDialog.KEY_NICK_NAME).a(ProfileModifyDialogFragmentView.class.getName() + System.currentTimeMillis()).a();
    }

    private void setViewScale(View view) {
        View viewFindViewById = view.findViewById(R.id.profile_image_layout);
        View viewFindViewById2 = view.findViewById(R.id.profile_image_stroke);
        View viewFindViewById3 = view.findViewById(R.id.copyright);
        d.p().a(this.q, 70, 70);
        d.p().a(viewFindViewById, 70, 70);
        d.p().a(viewFindViewById2, 70, 70);
        d.p().a(viewFindViewById3, d.l() ? 56 : 86, d.l() ? 14 : 18);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_profile_main, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        p();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        if (getArguments() != null) {
            this.i = getArguments().getBoolean(b);
            this.f = getArguments().getString(c);
            this.j = getArguments().getBoolean(d);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.k = (ViewGroup) view;
        this.l = view.findViewById(R.id.profile_info_layout);
        this.l.setVisibility(8);
        this.m = view.findViewById(R.id.profile_articles_layout);
        this.m.setVisibility(8);
        this.p = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.p.setColorSchemeColors(d.e().a);
        this.p.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.1
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                ProfileMainFragmentView.this.c();
            }
        });
        View viewFindViewById = view.findViewById(R.id.back);
        viewFindViewById.setVisibility(this.i ? 0 : 8);
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                b.f();
            }
        });
        view.findViewById(R.id.header_nickname_setting).setVisibility(this.i ? 8 : 0);
        View viewFindViewById2 = view.findViewById(R.id.header_mem_name_layout);
        viewFindViewById2.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.10
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                ProfileMainFragmentView.this.r();
            }
        });
        viewFindViewById2.setClickable(!this.i);
        viewFindViewById2.setEnabled(this.i ? false : true);
        this.r = (TextView) view.findViewById(R.id.header_nickname);
        this.s = (TextView) view.findViewById(R.id.header_grade);
        this.t = view.findViewById(R.id.header_visit_text);
        this.u = (TextView) view.findViewById(R.id.header_visit_count);
        this.q = (ImageView) view.findViewById(R.id.profile_image);
        this.q.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.11
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (ProfileMainFragmentView.this.D == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                if (TextUtils.isEmpty(ProfileMainFragmentView.this.D.profileImage)) {
                    sb.append("https://ssl.pstatic.net/static/m/cafe/plug/default/gl_desktop_profile_dimg.png");
                } else if (ProfileMainFragmentView.this.D.profileImage.contains("type")) {
                    sb.append((CharSequence) ProfileMainFragmentView.this.D.profileImage, 0, ProfileMainFragmentView.this.D.profileImage.indexOf("type"));
                    if (ProfileMainFragmentView.this.D.profileImage.contains("mcafethumb-phinf")) {
                        sb.append("type=wa1280");
                    } else {
                        sb.append("type=ff960_960_q50");
                    }
                } else {
                    sb.append(ProfileMainFragmentView.this.D.profileImage);
                }
                com.naver.glink.android.sdk.ui.viewer.a.a(sb.toString());
            }
        });
        Glide.with(getContext()).load(d.l() ? "https://ssl.pstatic.net/static/m/plug/default/pluglogo_gray.png" : "https://ssl.pstatic.net/static/m/plug/default/cafelogo_gray.png").asBitmap().fitCenter().into((ImageView) view.findViewById(R.id.copyright));
        this.v = view.findViewById(R.id.profile_articles_post_layout);
        this.w = view.findViewById(R.id.profile_articles_comment_layout);
        this.x = view.findViewById(R.id.profile_articles_like_layout);
        this.y = view.findViewById(R.id.profile_articles_live_layout);
        this.v.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.12
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                b.a(ProfileMainFragmentView.this.i ? ProfileMainFragmentView.this.f : ProfileMainFragmentView.this.D.memberId, ProfileMainFragmentView.this.j, ProfileTab.Type.WRITE);
            }
        });
        this.w.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.13
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                b.a(ProfileMainFragmentView.this.i ? ProfileMainFragmentView.this.f : ProfileMainFragmentView.this.D.memberId, ProfileMainFragmentView.this.j, ProfileTab.Type.COMMENT);
            }
        });
        this.x.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.14
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                b.a(ProfileMainFragmentView.this.i ? ProfileMainFragmentView.this.f : ProfileMainFragmentView.this.D.memberId, ProfileMainFragmentView.this.j, ProfileTab.Type.LIKE);
            }
        });
        this.y.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.15
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                b.a(ProfileMainFragmentView.this.i ? ProfileMainFragmentView.this.f : ProfileMainFragmentView.this.D.memberId, ProfileMainFragmentView.this.j, ProfileTab.Type.STREAMING);
            }
        });
        d.e().c(view.findViewById(R.id.icon_post_go));
        d.e().c(view.findViewById(R.id.icon_comment_go));
        d.e().c(view.findViewById(R.id.icon_like_go));
        d.e().c(view.findViewById(R.id.icon_live_go));
        this.z = (TextView) view.findViewById(R.id.article_count);
        this.A = (TextView) view.findViewById(R.id.written_comment_count);
        this.B = (TextView) view.findViewById(R.id.like_article_count);
        this.C = (TextView) view.findViewById(R.id.live_article_count);
        this.z.setVisibility(8);
        this.A.setVisibility(8);
        this.B.setVisibility(8);
        setViewScale(view);
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.16
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                ProfileMainFragmentView.this.q();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                ProfileMainFragmentView.this.a(plugError);
            }
        });
    }

    @Subscribe
    public void a(LoginHelper.b bVar) {
        if (bVar == null || bVar.a || this.p == null) {
            return;
        }
        this.p.setVisibility(8);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void b_() {
        if (isAttachedToWindow()) {
            q();
            p();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView.4
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                ProfileMainFragmentView.this.a(iVar);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                ProfileMainFragmentView.this.a(plugError);
            }
        });
    }

    public void f() {
        if (this.n != null) {
            this.k.removeView(this.n);
            this.n = null;
        }
        if (this.o != null) {
            this.k.removeView(this.o);
            this.o = null;
        }
    }
}
