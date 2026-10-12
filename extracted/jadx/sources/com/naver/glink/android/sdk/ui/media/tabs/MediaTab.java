package com.naver.glink.android.sdk.ui.media.tabs;

import android.support.annotation.IdRes;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class MediaTab extends com.naver.plug.android.core.a.a {
    public final Type a;
    public final PlugFragmentView b;

    @IdRes
    public final int c;
    boolean d;
    public boolean e;

    public enum Type {
        PHOTOS,
        VIDEOS,
        NONE;

        public final String fragmentTag = name() + ":" + getClass().getName();

        Type() {
        }
    }

    public MediaTab(Type type, int i, PlugFragmentView plugFragmentView) {
        this.a = type;
        this.c = i;
        this.b = plugFragmentView;
    }
}
