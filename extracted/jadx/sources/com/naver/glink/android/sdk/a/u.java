package com.naver.glink.android.sdk.a;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: SpannableBuilder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class u {
    private List<a> a = new ArrayList();
    private StringBuilder b = new StringBuilder();

    /* JADX INFO: compiled from: SpannableBuilder.java */
    private static class a extends com.naver.plug.android.core.a.a {
        final Object a;
        final int b;
        final int c;

        a(Object obj, int i, int i2) {
            this.a = obj;
            this.b = i;
            this.c = i2;
        }
    }

    public SpannableStringBuilder a() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.b.toString());
        for (a aVar : this.a) {
            spannableStringBuilder.setSpan(aVar.a, aVar.b, aVar.c, 33);
        }
        return spannableStringBuilder;
    }

    public u a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.append(str);
        }
        return this;
    }

    public u a(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            this.a.add(new a(new ForegroundColorSpan(i), this.b.length(), this.b.length() + str.length()));
            this.b.append(str);
        }
        return this;
    }
}
