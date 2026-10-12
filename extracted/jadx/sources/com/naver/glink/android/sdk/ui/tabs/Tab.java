package com.naver.glink.android.sdk.ui.tabs;

import android.support.annotation.DrawableRes;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Tab extends com.naver.plug.android.core.a.a {
    public final Type a;

    @DrawableRes
    final int b;
    public final PlugFragmentView c;
    boolean d;
    public boolean e;

    public enum Type {
        BANNERS,
        NOTICE,
        ALL_MEDIA,
        SEARCH,
        PROFILE,
        ALL_ARTICLES,
        PHOTOS,
        VIDEOS,
        SHOME,
        STREAMING,
        NONE;

        public final String fragmentTag = name() + ":" + getClass().getName();

        Type() {
        }
    }

    public Tab(Type type, int i, PlugFragmentView plugFragmentView) {
        this.a = type;
        this.b = i;
        this.c = plugFragmentView;
    }
}
