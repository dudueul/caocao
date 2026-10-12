package com.tapjoy.internal;

import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class ho implements gl {
    private static final String[] a;

    static {
        String[] strArr = {"reward", "purchase", "custom_action"};
        a = strArr;
        Arrays.sort(strArr);
    }

    ho() {
    }

    @Nullable
    public static ho a(String str, bn bnVar) {
        if ("reward".equals(str)) {
            return (ho) bnVar.a(hy.a);
        }
        if ("purchase".equals(str)) {
            return (ho) bnVar.a(hw.a);
        }
        return null;
    }

    public static boolean a(String str) {
        return Arrays.binarySearch(a, str) >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.tapjoy.internal.gl
    public final void a(gm gmVar) {
        if (this instanceof gp) {
            gp gpVar = (gp) this;
            gmVar.a(gpVar.a(), gpVar.b());
        } else if (this instanceof gq) {
            gq gqVar = (gq) this;
            gmVar.a(gqVar.a(), gqVar.b(), gqVar.c(), gqVar.d());
        }
    }
}
