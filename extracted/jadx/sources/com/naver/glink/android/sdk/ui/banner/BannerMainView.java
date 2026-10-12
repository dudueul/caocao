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
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.widget.SmallTouchView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class BannerMainView extends AbsBanner {
    private View a;
    private ImageView b;
    private View c;
    private View d;

    public BannerMainView(Context context) {
        super(context);
    }

    public BannerMainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BannerMainView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.naver.glink.android.sdk.ui.banner.AbsBanner
    protected View b(LayoutInflater layoutInflater, Context context) {
        this.a = layoutInflater.inflate(R.layout.item_home_main_banner, (ViewGroup) this, false);
        this.a.getLayoutParams().height = d.p().i;
        this.c = this.a.findViewById(R.id.movie_icon);
        this.d = this.a.findViewById(R.id.movie_gradient);
        this.b = (ImageView) this.a.findViewById(R.id.main_banner_image);
        return this.a;
    }

    @Override // com.naver.glink.android.sdk.ui.banner.AbsBanner
    protected void b(final Responses.BannersResponse.HomeBanner homeBanner, Context context) {
        this.c.setVisibility(homeBanner.a() ? 0 : 8);
        x.a(this.c, 0, 0, x.a(6.0f, 18.0f), x.a(6.0f, 18.0f));
        this.d.setVisibility(homeBanner.a() ? 0 : 8);
        d.p().a(this.c, 62, 62);
        Glide.with(context).load(homeBanner.imageUrl).centerCrop().into(this.b);
        setSmallClickListener(new SmallTouchView.a() { // from class: com.naver.glink.android.sdk.ui.banner.BannerMainView.1
            @Override // com.naver.glink.android.sdk.ui.widget.SmallTouchView.a
            public void a() {
                b.c(new AbsBanner.a(homeBanner));
            }
        });
    }
}
