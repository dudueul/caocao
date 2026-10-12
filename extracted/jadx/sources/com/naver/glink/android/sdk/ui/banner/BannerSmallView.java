package com.naver.glink.android.sdk.ui.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.a.b;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.widget.SmallTouchView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class BannerSmallView extends AbsBanner {
    private View a;
    private ImageView b;

    public BannerSmallView(Context context) {
        super(context);
    }

    public BannerSmallView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BannerSmallView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.naver.glink.android.sdk.ui.banner.AbsBanner
    protected View b(LayoutInflater layoutInflater, Context context) {
        this.a = layoutInflater.inflate(R.layout.item_home_banner, (ViewGroup) this, false);
        this.a.getLayoutParams().height = (int) (((double) d.p().i) * 0.2662d);
        this.b = (ImageView) this.a.findViewById(R.id.image);
        return this.a;
    }

    @Override // com.naver.glink.android.sdk.ui.banner.AbsBanner
    protected void b(final Responses.BannersResponse.HomeBanner homeBanner, Context context) {
        Glide.with(context).load(homeBanner.imageUrl).fitCenter().into(this.b);
        setSmallClickListener(new SmallTouchView.a() { // from class: com.naver.glink.android.sdk.ui.banner.BannerSmallView.1
            @Override // com.naver.glink.android.sdk.ui.widget.SmallTouchView.a
            public void a() {
                b.c(new AbsBanner.a(homeBanner));
            }
        });
    }
}
