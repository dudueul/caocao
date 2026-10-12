package com.naver.glink.android.sdk.ui.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.internal.AnalyticsEvents;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.f;
import com.naver.glink.android.sdk.a.g;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.u;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.c;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView;
import com.naver.glink.android.sdk.ui.parent.plugfragment.ItemsDialogFragmentView;
import com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView;
import com.naver.glink.android.sdk.ui.tabs.Tab;
import com.naver.glink.android.sdk.ui.tabs.b;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.io.File;
import java.util.ArrayList;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ProfileModifyDialogFragmentView extends DialogFragmentView {
    public static final int a = 8864;
    private static final int b = 1502;
    private static String[] c = {AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, "delete"};
    private Responses.q d;
    private Responses.d i;
    private View j;
    private EditText k;
    private TextView l;
    private View m;
    private View n;
    private View o;
    private View p;
    private ImageView q;
    private boolean r;
    private boolean s;
    private boolean t;
    private com.naver.glink.android.sdk.ui.profile.a u;
    private a v;
    private String w;
    private String x;

    enum UPDATE_OPTION {
        CHANGE_IMAGE("U"),
        DELETE_IMAGE("D"),
        UNCHAGE_IMAGE("N");

        String code;

        UPDATE_OPTION(String str) {
            this.code = str;
        }

        public String getCode() {
            return this.code;
        }
    }

    public interface a {
        void a(boolean z);
    }

    public ProfileModifyDialogFragmentView(Context context) {
        super(context);
    }

    public static ProfileModifyDialogFragmentView a(Context context, Responses.q qVar, a aVar) {
        Bundle bundle = new Bundle();
        ProfileModifyDialogFragmentView profileModifyDialogFragmentView = new ProfileModifyDialogFragmentView(context);
        profileModifyDialogFragmentView.setArguments(bundle);
        profileModifyDialogFragmentView.d = qVar;
        profileModifyDialogFragmentView.v = aVar;
        return profileModifyDialogFragmentView;
    }

    private void a(Uri uri) {
        com.naver.glink.android.sdk.ui.write.a.a(getContext(), uri, new com.naver.glink.android.sdk.ui.write.a.InterfaceC0066a() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.4
            @Override // com.naver.glink.android.sdk.ui.write.a.InterfaceC0066a
            public void a(Uri uri2) {
                ProfileModifyDialogFragmentView.this.b(f.a(ProfileModifyDialogFragmentView.this.getContext(), uri2));
            }
        }, 100, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        Glide.with(getContext()).load(str).asBitmap().placeholder(R.drawable.cf_img_pfdefault).centerCrop().into(new BitmapImageViewTarget(this.q) { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.9
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
            public void setResource(Bitmap bitmap) {
                if (ProfileModifyDialogFragmentView.this.isAttachedToWindow()) {
                    RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(ProfileModifyDialogFragmentView.this.getContext().getResources(), bitmap);
                    roundedBitmapDrawableCreate.setCircular(true);
                    setDrawable(roundedBitmapDrawableCreate);
                }
            }
        });
        this.s = true;
        this.o.setEnabled(this.t);
        b(str2, UPDATE_OPTION.CHANGE_IMAGE.getCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (isAttachedToWindow() && com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a("imagecrop") == null) {
            com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b().a(ImageEditDialogFragmentView.a(getContext(), str, new ImageEditDialogFragmentView.a() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.5
                @Override // com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.a
                public void a() {
                }

                @Override // com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.a
                public void a(String str2) {
                    ProfileModifyDialogFragmentView.this.c(str2);
                }
            }), "imagecrop").a("imagecrop").a();
        }
    }

    private void b(String str, String str2) {
        this.x = str2;
        if (UPDATE_OPTION.CHANGE_IMAGE.getCode().equals(str2)) {
            this.w = str;
            return;
        }
        if (UPDATE_OPTION.DELETE_IMAGE.getCode().equals(str2)) {
            this.w = "";
        } else if (UPDATE_OPTION.UNCHAGE_IMAGE.getCode().equals(str2)) {
            this.w = "";
        } else {
            this.w = "";
            this.x = UPDATE_OPTION.UNCHAGE_IMAGE.getCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str) {
        if (this.i == null) {
            (d.k() ? com.naver.glink.android.sdk.api.requests.f.e() : c.b()).showProgress(true).execute(getContext(), new RequestListener<Responses.d>() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.6
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.d dVar) {
                    ProfileModifyDialogFragmentView.this.i = dVar;
                    ProfileModifyDialogFragmentView.this.d(str);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    AlertDialogFragmentView.b(ProfileModifyDialogFragmentView.this.getContext(), ProfileModifyDialogFragmentView.this.getResources().getString(R.string.network_error)).a();
                }
            });
        } else {
            d(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str) {
        File file = new File(str);
        if (file == null) {
            AlertDialogFragmentView.b(getContext(), getResources().getString(R.string.unable_load_images)).a();
        } else if (d.k()) {
            com.naver.glink.android.sdk.api.requests.f.a(this.i, file).showProgress(true).execute(getContext(), new RequestListener<Responses.e>() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.7
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.e eVar) {
                    ProfileModifyDialogFragmentView.this.a(str, eVar.imagePath);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    AlertDialogFragmentView.b(ProfileModifyDialogFragmentView.this.getContext(), ProfileModifyDialogFragmentView.this.getResources().getString(R.string.image_upload_fail_message)).a();
                }
            });
        } else {
            c.a(this.i, file).showProgress(true).execute(getContext(), new RequestListener<com.naver.glink.android.sdk.api.a.b>() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.8
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.b bVar) {
                    ProfileModifyDialogFragmentView.this.a(str, bVar.url);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    AlertDialogFragmentView.b(ProfileModifyDialogFragmentView.this.getContext(), ProfileModifyDialogFragmentView.this.getResources().getString(R.string.image_upload_fail_message)).a();
                }
            });
        }
    }

    private void e() {
        this.u = new com.naver.glink.android.sdk.ui.profile.a(new com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.17
            @Override // com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a
            public void a() {
                ProfileModifyDialogFragmentView.this.o.setEnabled(false);
            }

            @Override // com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a
            public void a(Responses.g gVar, String str, boolean z, int i) {
                u uVar = new u();
                uVar.a(str, ContextCompat.getColor(ProfileModifyDialogFragmentView.this.getContext(), i));
                ProfileModifyDialogFragmentView.this.l.setText(uVar.a());
                if (gVar != null) {
                    ProfileModifyDialogFragmentView.this.t = z;
                }
                ProfileModifyDialogFragmentView.this.o.setEnabled(z);
            }

            @Override // com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a
            public void a(PlugError plugError) {
                AlertDialogFragmentView.b(ProfileModifyDialogFragmentView.this.getContext(), plugError.errorMessage).a();
            }

            @Override // com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a
            public void a(Response response) {
                ProfileModifyDialogFragmentView.this.d.nickname = ProfileModifyDialogFragmentView.this.k.getText().toString();
                ProfileModifyDialogFragmentView.this.u.a(ProfileModifyDialogFragmentView.this.d.nickname, !ProfileModifyDialogFragmentView.this.s, false, ProfileModifyDialogFragmentView.this.d, ProfileModifyDialogFragmentView.this.getContext());
                Toast.makeText(ProfileModifyDialogFragmentView.this.getContext(), ProfileModifyDialogFragmentView.this.getContext().getString(R.string.modify_complete_message), 0).show();
                ProfileModifyDialogFragmentView.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        AlertDialogFragmentView.a(getContext(), getContext().getString(R.string.logout_confirm_message)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.2
            @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
            public void a(DialogInterface dialogInterface, int i) {
                LoginHelper.a().logout(ProfileModifyDialogFragmentView.this.getContext());
                ProfileModifyDialogFragmentView.this.c();
                b.a(Tab.Type.BANNERS);
                Toast.makeText(ProfileModifyDialogFragmentView.this.getContext(), ProfileModifyDialogFragmentView.this.getContext().getString(R.string.logout_complete_message), 0).show();
                ProfileModifyDialogFragmentView.this.dismiss();
            }
        }).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ArrayList arrayList = new ArrayList();
        for (String str : c) {
            arrayList.add(str);
        }
        a(ItemsDialogFragmentView.c, getContext().getString(R.string.profile_string), arrayList);
    }

    private void h() {
        if (!com.naver.glink.android.sdk.ui.write.b.a(getContext(), NXRuntimePermissionManager.READ_EXTERNAL_STORAGE)) {
            com.naver.glink.android.sdk.ui.write.b.a(getContext(), 1502, NXRuntimePermissionManager.READ_EXTERNAL_STORAGE);
            return;
        }
        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.a.a(getContext(), intent, a);
        if (d.h(getContext())) {
            setDialogVisible(true);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        this.j = layoutInflater.inflate(R.layout.fragment_profile_modify, (ViewGroup) null);
        return this.j;
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        com.naver.glink.android.sdk.a.a.b.a(this);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Context context) {
        super.a(context);
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    public void a(DialogInterface dialogInterface) {
        if (this.v != null) {
            this.v.a(this.r);
        }
        com.naver.glink.android.sdk.ui.write.a.a(getContext());
        super.a(dialogInterface);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        if (this.d == null) {
            dismiss();
            return;
        }
        e();
        this.r = false;
        this.t = true;
        b((String) null, UPDATE_OPTION.UNCHAGE_IMAGE.getCode());
        if (this.j != null) {
            this.l = (TextView) this.j.findViewById(R.id.valid_check_text);
            this.l.setText(com.naver.glink.android.sdk.ui.profile.a.a(getContext()));
            this.k = (EditText) this.j.findViewById(R.id.nickname);
            this.k.setText(this.d.nickname);
            if (this.d.nickname != null) {
                this.k.setSelection(this.k.getText().length());
            }
            this.k.setFilters(com.naver.glink.android.sdk.ui.profile.a.a());
            this.k.addTextChangedListener(new g() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.1
                @Override // com.naver.glink.android.sdk.a.g, android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    ProfileModifyDialogFragmentView.this.u.a(charSequence.toString(), !ProfileModifyDialogFragmentView.this.s, true, ProfileModifyDialogFragmentView.this.d, ProfileModifyDialogFragmentView.this.getContext());
                }
            });
            this.k.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.10
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view2, boolean z) {
                    if (z) {
                        return;
                    }
                    ProfileModifyDialogFragmentView.this.c();
                }
            });
            this.m = this.j.findViewById(R.id.nickname_deletion);
            this.m.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ProfileModifyDialogFragmentView.this.k.setText((CharSequence) null);
                    ProfileModifyDialogFragmentView.this.t = false;
                }
            });
            this.n = this.j.findViewById(R.id.btn_close_modify);
            this.n.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.12
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ProfileModifyDialogFragmentView.this.dismiss();
                }
            });
            this.o = this.j.findViewById(R.id.btn_confirm);
            this.o.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ProfileModifyDialogFragmentView.this.u.a(ProfileModifyDialogFragmentView.this.k.getText().toString(), ProfileModifyDialogFragmentView.this.d, ProfileModifyDialogFragmentView.this.w, ProfileModifyDialogFragmentView.this.x, ProfileModifyDialogFragmentView.this.getContext());
                    ProfileModifyDialogFragmentView.this.c();
                    ProfileModifyDialogFragmentView.this.r = true;
                }
            });
            d.e().e(this.o);
            this.p = this.j.findViewById(R.id.btn_logout);
            this.p.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ProfileModifyDialogFragmentView.this.f();
                }
            });
            this.q = (ImageView) this.j.findViewById(R.id.profile_image);
            Glide.with(getContext()).load(this.d.profileImage).asBitmap().placeholder(R.drawable.cf_img_pfdefault).centerCrop().into(new BitmapImageViewTarget(this.q) { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.15
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
                public void setResource(Bitmap bitmap) {
                    if (ProfileModifyDialogFragmentView.this.isAttachedToWindow()) {
                        RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(ProfileModifyDialogFragmentView.this.getContext().getResources(), bitmap);
                        roundedBitmapDrawableCreate.setCircular(true);
                        setDrawable(roundedBitmapDrawableCreate);
                    }
                }
            });
            this.q.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.16
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    ProfileModifyDialogFragmentView.this.g();
                }
            });
            View viewFindViewById = this.j.findViewById(R.id.profile_image_layout);
            View viewFindViewById2 = this.j.findViewById(R.id.profile_image_stroke);
            d.p().a(this.q, 70, 70);
            d.p().a(viewFindViewById, 70, 70);
            d.p().a(viewFindViewById2, 70, 70);
            c[0] = getContext().getString(R.string.photo_album);
            c[1] = getContext().getString(R.string.delete);
            this.u.a(this.d.nickname, this.s ? false : true, false, this.d, getContext());
        }
        super.a(view, bundle);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    protected void a(WindowManager.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return;
        }
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getSize(point);
        }
        layoutParams.width = (int) (d.i() ? ((double) point.x) * 0.6d : d.p().i);
        layoutParams.height = -2;
        layoutParams.gravity = 17;
        layoutParams.dimAmount = 0.6f;
        layoutParams.format = -2;
        layoutParams.windowAnimations = android.R.style.Animation.Dialog;
        layoutParams.flags |= 16778242;
        layoutParams.softInputMode |= 34;
    }

    @Subscribe
    public void a(ItemsDialogFragmentView.c cVar) {
        if (TextUtils.equals(cVar.a, ItemsDialogFragmentView.c)) {
            if (c[0].equals(cVar.c)) {
                h();
            } else if (c[1].equals(cVar.c)) {
                this.s = true;
                this.o.setEnabled(this.t);
                b((String) null, UPDATE_OPTION.DELETE_IMAGE.getCode());
                Glide.with(getContext()).load(Integer.valueOf(R.drawable.cf_img_pfdefault)).asBitmap().centerCrop().into(new BitmapImageViewTarget(this.q) { // from class: com.naver.glink.android.sdk.ui.profile.ProfileModifyDialogFragmentView.3
                    /* JADX INFO: Access modifiers changed from: protected */
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
                    public void setResource(Bitmap bitmap) {
                        if (ProfileModifyDialogFragmentView.this.isAttachedToWindow()) {
                            RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(ProfileModifyDialogFragmentView.this.getContext().getResources(), bitmap);
                            roundedBitmapDrawableCreate.setCircular(true);
                            setDrawable(roundedBitmapDrawableCreate);
                        }
                    }
                });
            }
        }
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.a.C0048a c0048a) {
        if (d.h(getContext())) {
            setDialogVisible(false);
        }
        if (c0048a == null) {
            return;
        }
        int i = c0048a.a;
        int i2 = c0048a.b;
        Intent intent = c0048a.c;
        if (i2 == -1 && i == 8864 && i2 == -1) {
            a(intent.getData());
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a_() {
        super.a_();
        this.j = null;
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        com.naver.glink.android.sdk.a.a.b.b(this);
    }

    public void c() {
        if (this.k == null) {
            return;
        }
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.k.getWindowToken(), 0);
        this.k.clearFocus();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, android.content.DialogInterface
    public void dismiss() {
        c();
        super.dismiss();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        if (!d.h() || configuration.orientation == getWindowParam().screenOrientation) {
            return;
        }
        super.onConfigurationChanged(configuration);
    }
}
