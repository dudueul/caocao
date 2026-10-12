package com.naver.glink.android.sdk.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.i;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.a;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CafeInfoFragmentView extends PlugFragmentView {
    public CafeInfoFragmentView(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view, final Responses.h hVar, Responses.i iVar) {
        ImageView imageView = (ImageView) view.findViewById(R.id.copyright);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.cafe_background_img);
        View viewFindViewById = view.findViewById(R.id.cafe_background_gradation);
        View viewFindViewById2 = view.findViewById(R.id.cafe_background_img_dimmed);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.cafe_icon_img);
        View viewFindViewById3 = view.findViewById(R.id.cafe_info_layout);
        View viewFindViewById4 = view.findViewById(R.id.cafe_count_layout);
        TextView textView = (TextView) view.findViewById(R.id.cafe_name);
        TextView textView2 = (TextView) view.findViewById(R.id.member_name);
        TextView textView3 = (TextView) view.findViewById(R.id.cafe_member_count);
        TextView textView4 = (TextView) view.findViewById(R.id.today_visit_count);
        TextView textView5 = (TextView) view.findViewById(R.id.total_article_count);
        View viewFindViewById5 = view.findViewById(R.id.cafe_info_count_line);
        TextView textView6 = (TextView) view.findViewById(R.id.cafe_info_no_count_title);
        TextView textView7 = (TextView) view.findViewById(R.id.cafe_info);
        TextView textView8 = (TextView) view.findViewById(R.id.menu_link_text);
        ((RelativeLayout.LayoutParams) imageView3.getLayoutParams()).setMargins(0, d.p().a(d.i() ? 72 : 72), d.i() ? x.a(15.0f) : x.a(24.0f), 0);
        ((RelativeLayout.LayoutParams) viewFindViewById3.getLayoutParams()).setMargins(0, 0, 0, d.i() ? x.a(15.0f) : x.a(18.0f));
        d.p().a(imageView2, -1, d.i() ? 144 : 144);
        d.p().a(viewFindViewById, -1, d.i() ? 96 : 96);
        d.p().a(viewFindViewById2, -1, d.i() ? 144 : 144);
        d.p().a(imageView3, 96, 96);
        d.p().a(imageView, d.l() ? 56 : 86, d.l() ? 14 : 18);
        Glide.with(getContext()).load(d.l() ? "https://ssl.pstatic.net/static/m/plug/default/pluglogo_white.png" : "https://ssl.pstatic.net/static/m/plug/default/cafelogo_white.png").into(imageView);
        BitmapImageViewTarget bitmapImageViewTarget = new BitmapImageViewTarget(imageView3) { // from class: com.naver.glink.android.sdk.ui.CafeInfoFragmentView.2
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
            public void setResource(Bitmap bitmap) {
                if (CafeInfoFragmentView.this.isAttachedToWindow()) {
                    RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(CafeInfoFragmentView.this.getResources(), bitmap);
                    roundedBitmapDrawableCreate.setCornerRadius(d.p().a(19));
                    setDrawable(roundedBitmapDrawableCreate);
                }
            }
        };
        int i = d.l() ? R.drawable.pl_img_thumb_none : R.drawable.cf_img_thumb_none;
        if (TextUtils.isEmpty(hVar.iconImageUrl)) {
            Glide.with(getContext()).load(Integer.valueOf(i)).asBitmap().fitCenter().into(bitmapImageViewTarget);
        } else {
            Glide.with(getContext()).load(hVar.iconImageUrl).asBitmap().placeholder(i).fitCenter().into(bitmapImageViewTarget);
        }
        if (TextUtils.isEmpty(hVar.topCoverImageUrl)) {
            imageView2.setImageBitmap(null);
            imageView2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black2_opacity_20));
        } else {
            Glide.with(getContext()).load(hVar.topCoverImageUrl).asBitmap().into(imageView2);
        }
        textView.setText(hVar.cafeName);
        if (hVar.managers != null && hVar.managers.size() > 0) {
            StringBuilder sb = new StringBuilder("");
            sb.append(hVar.managers.get(0).nickname);
            if (hVar.managers.size() > 1) {
                sb.append(String.format(c(R.string.cafe_manager_count), Integer.valueOf(hVar.managers.size() - 1)));
            }
            textView2.setText(sb.toString());
        }
        textView3.setText(a(R.string.count_format, Integer.valueOf(hVar.memberCount)));
        if (!d.l()) {
            textView4.setText(a(R.string.count_format, Integer.valueOf(hVar.todayVisitCount)));
        }
        textView5.setText(a(R.string.count_format, Integer.valueOf(hVar.articleCount)));
        textView7.setText(hVar.desc);
        textView8.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.CafeInfoFragmentView.3
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (TextUtils.isEmpty(hVar.cafeUrl)) {
                    return;
                }
                i.a(CafeInfoFragmentView.this.getContext(), hVar.cafeUrl, -1);
            }
        });
        textView8.setText(hVar.cafeUrl);
        View viewFindViewById6 = view.findViewById(R.id.today_visit_layout);
        View viewFindViewById7 = view.findViewById(R.id.today_visit_divider);
        if (d.l()) {
            viewFindViewById6.setVisibility(8);
            viewFindViewById7.setVisibility(8);
        } else {
            viewFindViewById6.setVisibility(0);
            viewFindViewById7.setVisibility(0);
        }
        view.findViewById(R.id.scroll_view).setVisibility(0);
        if (iVar.c()) {
            viewFindViewById4.setVisibility(8);
            viewFindViewById5.setVisibility(8);
            textView6.setVisibility(0);
        } else {
            viewFindViewById4.setVisibility(0);
            viewFindViewById5.setVisibility(0);
            textView6.setVisibility(8);
        }
    }

    public static CafeInfoFragmentView b(Context context) {
        Bundle bundle = new Bundle();
        CafeInfoFragmentView cafeInfoFragmentView = new CafeInfoFragmentView(context);
        cafeInfoFragmentView.setArguments(bundle);
        return cafeInfoFragmentView;
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_cafe_info, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        view.findViewById(R.id.scroll_view).setVisibility(8);
        view.findViewById(R.id.close).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.CafeInfoFragmentView.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                CafeInfoFragmentView.this.l();
            }
        });
        c();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.CafeInfoFragmentView.4
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull final Responses.i iVar) {
                f.b().showProgress(true).execute(CafeInfoFragmentView.this.getContext(), new RequestListener<Responses.h>() { // from class: com.naver.glink.android.sdk.ui.CafeInfoFragmentView.4.1
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull Responses.h hVar) {
                        CafeInfoFragmentView.this.a(CafeInfoFragmentView.this, hVar, iVar);
                    }

                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    public void onFailure(@NonNull PlugError plugError) {
                        CafeInfoFragmentView.this.a(plugError);
                    }
                });
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                CafeInfoFragmentView.this.a(plugError);
            }
        });
    }
}
