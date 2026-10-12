package com.naver.glink.android.sdk.ui.profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.g;
import com.naver.glink.android.sdk.a.i;
import com.naver.glink.android.sdk.a.k;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.u;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CafeJoinView extends RelativeLayout {
    private static final String a = "http://cafe.naver.com/common/cafein_service.htm";
    private EditText b;
    private TextView c;
    private Button d;
    private TextView e;
    private ViewGroup f;
    private CheckBox g;
    private View h;
    private com.naver.glink.android.sdk.ui.profile.a i;
    private Responses.g j;
    private b k;

    public static class a {
        public boolean a;

        public a(boolean z) {
            this.a = z;
        }
    }

    public interface b {
        void a();

        void a(PlugError plugError);

        void a(Response response);
    }

    public CafeJoinView(Context context) {
        super(context);
        b(context);
    }

    public CafeJoinView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context);
    }

    public CafeJoinView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b(context);
    }

    private void a() {
        this.i = new com.naver.glink.android.sdk.ui.profile.a(new com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a() { // from class: com.naver.glink.android.sdk.ui.profile.CafeJoinView.9
            @Override // com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a
            public void a() {
                if (CafeJoinView.this.d != null) {
                    CafeJoinView.this.d.setEnabled(false);
                    CafeJoinView.this.j = null;
                }
            }

            @Override // com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a
            public void a(Responses.g gVar, String str, boolean z, int i) {
                if (CafeJoinView.this.i != null) {
                    CafeJoinView.this.a(str, z, i);
                    CafeJoinView.this.j = gVar;
                }
            }

            @Override // com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a
            public void a(PlugError plugError) {
                if (CafeJoinView.this.k != null) {
                    CafeJoinView.this.k.a(plugError);
                }
            }

            @Override // com.naver.glink.android.sdk.ui.profile.a.InterfaceC0051a
            public void a(Response response) {
                if (response != null && (response instanceof Responses.n) && ((Responses.n) response).success) {
                    if (CafeJoinView.this.k != null) {
                        CafeJoinView.this.k.a(response);
                    }
                    com.naver.glink.android.sdk.a.a.b.c(new a(true));
                }
            }
        });
    }

    private void b(Context context) {
        ViewGroup viewGroup = (ViewGroup) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.item_profile_cafe_join, this);
        this.c = (TextView) viewGroup.findViewById(R.id.valid_check_text);
        this.c.setText(com.naver.glink.android.sdk.ui.profile.a.a(context));
        this.b = (EditText) viewGroup.findViewById(R.id.nickname);
        this.b.setFilters(com.naver.glink.android.sdk.ui.profile.a.a());
        this.h = viewGroup.findViewById(R.id.nickname_deletion);
        this.h.setVisibility(4);
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.CafeJoinView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CafeJoinView.this.b.setText((CharSequence) null);
            }
        });
        this.f = (ViewGroup) viewGroup.findViewById(R.id.agree_layout);
        this.g = (CheckBox) viewGroup.findViewById(R.id.agree_check);
        d.p().a(this.g, 20, 20);
        this.e = (TextView) viewGroup.findViewById(R.id.agree_text);
        this.d = (Button) viewGroup.findViewById(R.id.join);
        this.d.setEnabled(false);
        this.d.setText(getResources().getString(R.string.nick_name_join));
        com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.PROFILE_JOIN);
    }

    public void a(Context context) {
        k.a(context, this.b);
    }

    public void a(Context context, View view, final Responses.i iVar, final boolean z) {
        if (iVar == null || context == null || view == null || !z) {
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.cafe_background_img);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.cafe_icon_img);
        TextView textView = (TextView) view.findViewById(R.id.cafe_name);
        TextView textView2 = (TextView) view.findViewById(R.id.cafe_info);
        View viewFindViewById = view.findViewById(R.id.btn_logout);
        d.p().a(imageView2, 72, 72);
        BitmapImageViewTarget bitmapImageViewTarget = new BitmapImageViewTarget(imageView2) { // from class: com.naver.glink.android.sdk.ui.profile.CafeJoinView.6
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
            public void setResource(Bitmap bitmap) {
                if (z) {
                    RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(CafeJoinView.this.getResources(), bitmap);
                    roundedBitmapDrawableCreate.setCornerRadius(d.p().a(19));
                    setDrawable(roundedBitmapDrawableCreate);
                }
            }
        };
        int i = d.l() ? R.drawable.pl_img_thumb_none : R.drawable.cf_img_thumb_none;
        if (TextUtils.isEmpty(iVar.iconImageUrl)) {
            Glide.with(context).load(Integer.valueOf(i)).asBitmap().fitCenter().into(bitmapImageViewTarget);
        } else {
            Glide.with(context).load(iVar.iconImageUrl).asBitmap().placeholder(i).fitCenter().into(bitmapImageViewTarget);
        }
        String str = iVar.fullCoverImageUrl;
        if (TextUtils.isEmpty(str)) {
            imageView.setImageBitmap(null);
            imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.green1));
        } else {
            Glide.with(context).load(str).asBitmap().centerCrop().into(imageView);
        }
        textView.setText(iVar.cafeName);
        textView2.setText(iVar.desc);
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.CafeJoinView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (CafeJoinView.this.k != null) {
                    CafeJoinView.this.k.a();
                }
            }
        });
        if (d.i()) {
            View viewFindViewById2 = view.findViewById(R.id.menu_link_layout);
            TextView textView3 = (TextView) view.findViewById(R.id.menu_link_text);
            viewFindViewById2.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.CafeJoinView.8
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    if (TextUtils.isEmpty(iVar.cafeUrl)) {
                        return;
                    }
                    Responses.j jVarA = iVar.a(d.b().a());
                    i.a(CafeJoinView.this.getContext(), iVar.cafeUrl, jVarA == null ? -1 : jVarA.channelId);
                }
            });
            textView3.setText(iVar.cafeUrl);
        }
    }

    public void a(final Context context, PlugError plugError) {
        a();
        this.f.setVisibility(plugError != null && plugError.isAgreeTermsError() ? 0 : 8);
        this.b.addTextChangedListener(new g() { // from class: com.naver.glink.android.sdk.ui.profile.CafeJoinView.2
            @Override // com.naver.glink.android.sdk.a.g, android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (CafeJoinView.this.i == null) {
                    return;
                }
                CafeJoinView.this.h.setVisibility(TextUtils.isEmpty(charSequence.toString()) ? 4 : 0);
                CafeJoinView.this.i.a(charSequence.toString(), false, true, (Responses.q) null, context);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.CafeJoinView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CafeJoinView.this.i == null) {
                    return;
                }
                CafeJoinView.this.a(context);
                CafeJoinView.this.i.a(CafeJoinView.this.b.getText().toString(), context);
            }
        });
        d.e().b(this.d, false);
        this.g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.naver.glink.android.sdk.ui.profile.CafeJoinView.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (CafeJoinView.this.d == null) {
                    return;
                }
                if (!z) {
                    CafeJoinView.this.d.setEnabled(false);
                } else if (CafeJoinView.this.j != null) {
                    CafeJoinView.this.d.setEnabled(CafeJoinView.this.j.available);
                }
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.CafeJoinView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                i.a(context, CafeJoinView.a);
            }
        });
    }

    public void a(String str, boolean z, int i) {
        u uVar = new u();
        uVar.a(str, ContextCompat.getColor(getContext(), i));
        this.c.setText(uVar.a());
        this.d.setEnabled(z);
        if (this.f.getVisibility() == 0) {
            this.d.setEnabled(z && this.g.isChecked());
        } else {
            this.d.setEnabled(z);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.i = null;
        this.k = null;
        this.j = null;
    }

    public void setCafeJoinListener(b bVar) {
        this.k = bVar;
    }
}
