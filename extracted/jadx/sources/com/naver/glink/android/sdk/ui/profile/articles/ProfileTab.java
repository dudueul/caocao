package com.naver.glink.android.sdk.ui.profile.articles;

import android.support.annotation.IdRes;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ProfileTab extends com.naver.plug.android.core.a.a {
    public final Type a;
    public final PlugFragmentView b;

    @IdRes
    public final int c;
    public boolean d;

    public enum Type {
        WRITE("W"),
        COMMENT("C"),
        LIKE("L"),
        STREAMING("S"),
        NONE("N");

        public final String fragmentTag = name() + ":" + getClass().getName();
        private final String typeName;

        Type(String str) {
            this.typeName = str;
        }

        public String getTypeName() {
            return this.typeName;
        }
    }

    public ProfileTab(Type type, int i, PlugFragmentView plugFragmentView) {
        this.a = type;
        this.c = i;
        this.b = plugFragmentView;
    }
}
