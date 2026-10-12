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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.i;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.LoginHelper;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CafeInfoView extends RelativeLayout {
    private a a;
    private boolean b;

    public interface a {
        void a();
    }

    public CafeInfoView(Context context) {
        super(context);
        a(context);
    }

    public CafeInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CafeInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(final Context context) {
        this.b = false;
        ViewGroup viewGroup = (ViewGroup) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.item_profile_cafe_info, this);
        Button button = (Button) viewGroup.findViewById(R.id.btn_login);
        button.setText(d.l() ? "Log in" : getResources().getString(R.string.profile_login));
        d.e().b(button, false);
        button.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.CafeInfoView.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                LoginHelper.a().builder(context, new Glink.OnLoggedInListener() { // from class: com.naver.glink.android.sdk.ui.profile.CafeInfoView.1.1
                    @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
                    public void onLoggedIn(boolean z) {
                        if (!z || CafeInfoView.this.a == null) {
                            return;
                        }
                        CafeInfoView.this.a.a();
                    }
                }).a();
            }
        });
        a(context, viewGroup);
    }

    private void a(Context context, View view) {
        final Responses.i iVarA = com.naver.glink.android.sdk.api.requests.a.a();
        if (iVarA == null || this.b) {
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.cafe_background_img);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.cafe_icon_img);
        TextView textView = (TextView) view.findViewById(R.id.cafe_name);
        TextView textView2 = (TextView) view.findViewById(R.id.cafe_info);
        TextView textView3 = (TextView) view.findViewById(R.id.join_cafe);
        View viewFindViewById = view.findViewById(R.id.menu_link_layout);
        TextView textView4 = (TextView) view.findViewById(R.id.menu_link_text);
        d.p().a(imageView2, 72, 72);
        if (d.i()) {
            ImageView imageView3 = (ImageView) view.findViewById(R.id.copyright);
            d.p().a(imageView3, d.l() ? 56 : 86, d.l() ? 14 : 18);
            Glide.with(getContext()).load(d.l() ? "https://ssl.pstatic.net/static/m/plug/default/pluglogo_gray.png" : "https://ssl.pstatic.net/static/m/plug/default/cafelogo_gray.png").asBitmap().fitCenter().into(imageView3);
        }
        BitmapImageViewTarget bitmapImageViewTarget = new BitmapImageViewTarget(imageView2) { // from class: com.naver.glink.android.sdk.ui.profile.CafeInfoView.2
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
            public void setResource(Bitmap bitmap) {
                if (CafeInfoView.this.b) {
                    return;
                }
                RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(CafeInfoView.this.getResources(), bitmap);
                roundedBitmapDrawableCreate.setCornerRadius(d.p().a(19));
                setDrawable(roundedBitmapDrawableCreate);
            }
        };
        int i = d.l() ? R.drawable.pl_img_thumb_none : R.drawable.cf_img_thumb_none;
        if (TextUtils.isEmpty(iVarA.iconImageUrl)) {
            Glide.with(context).load(Integer.valueOf(i)).asBitmap().fitCenter().into(bitmapImageViewTarget);
        } else {
            Glide.with(context).load(iVarA.iconImageUrl).asBitmap().placeholder(i).fitCenter().into(bitmapImageViewTarget);
        }
        String str = iVarA.fullCoverImageUrl;
        if (TextUtils.isEmpty(str)) {
            imageView.setImageBitmap(null);
            imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.green1));
        } else {
            Glide.with(context).load(str).asBitmap().into(imageView);
        }
        textView.setText(iVarA.cafeName);
        textView2.setText(iVarA.desc);
        textView2.setVerticalScrollBarEnabled(false);
        textView2.setHorizontallyScrolling(false);
        textView2.setVerticalFadingEdgeEnabled(false);
        textView2.setScroller(null);
        if (textView2.getLineCount() > 2) {
            textView2.setSingleLine();
            textView2.setTextSize(x.a(5.0f));
        }
        textView3.setText(d.l() ? context.getString(R.string.join_plug) : context.getString(R.string.join_cafe));
        viewFindViewById.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.CafeInfoView.3
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (TextUtils.isEmpty(iVarA.cafeUrl)) {
                    return;
                }
                Responses.j jVarA = iVarA.a(d.b().a());
                i.a(CafeInfoView.this.getContext(), iVarA.cafeUrl, jVarA == null ? -1 : jVarA.channelId);
            }
        });
        textView4.setText(iVarA.cafeUrl);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a = null;
        this.b = true;
    }

    public void setOnCafeInfoListener(a aVar) {
        this.a = aVar;
    }
}
