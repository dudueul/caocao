package com.naver.glink.android.sdk.ui.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.ui.widget.SmallTouchView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class AbsBanner extends SmallTouchView {

    public static class a {
        public final Responses.BannersResponse.HomeBanner a;

        a(Responses.BannersResponse.HomeBanner homeBanner) {
            this.a = homeBanner;
        }
    }

    public AbsBanner(Context context) {
        super(context);
    }

    public AbsBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AbsBanner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a(LayoutInflater layoutInflater, Context context) {
        addView(b(layoutInflater, context));
    }

    public void a(Responses.BannersResponse.HomeBanner homeBanner, Context context) {
        b(homeBanner, context);
    }

    protected abstract View b(LayoutInflater layoutInflater, Context context);

    protected abstract void b(Responses.BannersResponse.HomeBanner homeBanner, Context context);
}
