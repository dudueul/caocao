package com.naver.glink.android.sdk.configure;

import android.content.res.Resources;
import android.text.TextUtils;
import com.naver.glink.android.sdk.R;

/* JADX INFO: compiled from: SdkHosts.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;

    private b(Resources resources, String str) {
        if (resources == null) {
            this.a = "";
            this.b = "";
            this.c = "";
            this.d = "";
            this.e = "";
            this.f = "";
            this.g = "";
            this.h = "";
            return;
        }
        this.a = resources.getString(R.string.hosts_glink);
        this.b = resources.getString(R.string.hosts_attach);
        this.c = resources.getString(R.string.hosts_article);
        this.d = TextUtils.isEmpty(str) ? resources.getString(R.string.hosts_plug) : str;
        this.e = resources.getString(R.string.hosts_upphoto);
        this.f = resources.getString(R.string.hosts_phinf);
        this.g = resources.getString(R.string.hosts_playlog);
        this.h = resources.getString(R.string.hosts_ba);
    }

    static b a() {
        return new b(null, null);
    }

    public static b a(Resources resources) {
        return new b(resources, null);
    }

    public static b a(Resources resources, String str) {
        return new b(resources, str);
    }
}
