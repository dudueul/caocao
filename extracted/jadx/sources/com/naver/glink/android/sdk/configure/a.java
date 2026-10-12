package com.naver.glink.android.sdk.configure;

import android.text.TextUtils;
import com.naver.glink.android.sdk.ChannelCodes;
import com.naver.plug.android.core.api.request.RequestHelper;

/* JADX INFO: compiled from: SdkConfigure.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a extends com.naver.plug.android.core.a.a {
    public final String a;
    public final SdkBuildPhase b;
    public final com.naver.glink.android.sdk.configure.b c;
    public final b d;
    public final b e;
    private String f;
    private int g;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.configure.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SdkConfigure.java */
    public static class C0033a {
        private String a;
        private SdkBuildPhase b;
        private com.naver.glink.android.sdk.configure.b c;
        private b d;
        private b e;
        private String f;
        private int g;

        private C0033a(a aVar) {
            this.b = SdkBuildPhase.REAL;
            this.g = -1;
            if (aVar != null) {
                this.a = aVar.a;
                this.b = aVar.b;
                this.c = aVar.c;
                this.d = aVar.d;
                this.e = aVar.e;
                this.f = aVar.f;
                this.g = aVar.g;
            }
        }

        public C0033a a(SdkBuildPhase sdkBuildPhase) {
            this.b = sdkBuildPhase;
            return this;
        }

        public C0033a a(b bVar) {
            this.d = bVar;
            return this;
        }

        public C0033a a(com.naver.glink.android.sdk.configure.b bVar) {
            this.c = bVar;
            return this;
        }

        public C0033a a(String str) {
            this.a = str;
            return this;
        }

        public a a() {
            return new a(this);
        }

        public C0033a b(b bVar) {
            this.e = bVar;
            return this;
        }
    }

    /* JADX INFO: compiled from: SdkConfigure.java */
    public static class b {
        public final int a;
        public final String b;
        public final String c;

        public b(int i, String str, String str2) {
            this.a = i;
            this.b = str;
            this.c = str2;
        }
    }

    private a(C0033a c0033a) {
        this.g = -1;
        this.a = c0033a.a;
        this.b = c0033a.b;
        this.c = c0033a.c;
        this.d = c0033a.d;
        this.e = c0033a.e;
        this.f = c0033a.f;
        this.g = c0033a.g;
    }

    public static C0033a a(a aVar) {
        return new C0033a();
    }

    public static a g() {
        return a((a) null).a(new b(0, "", "")).b(new b(0, "", "")).a(com.naver.glink.android.sdk.configure.b.a()).a();
    }

    public int a() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public void a(String str) {
        this.f = str;
    }

    public boolean a(String str, int i) {
        return e() && ChannelCodes.KOREAN.equals(str) && i == -1;
    }

    public String b() {
        if (!f()) {
            return ChannelCodes.KOREAN;
        }
        if (TextUtils.isEmpty(this.f)) {
            this.f = RequestHelper.getSystemLangCode();
        }
        return this.f;
    }

    public boolean c() {
        return a(b(), a());
    }

    public boolean d() {
        return !c();
    }

    public boolean e() {
        return (this.d == null || this.d.a == 0) ? false : true;
    }

    public boolean f() {
        return (this.e == null || this.e.a == 0) ? false : true;
    }
}
