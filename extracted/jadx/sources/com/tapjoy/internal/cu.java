package com.tapjoy.internal;

import android.webkit.WebView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class cu {
    public final cx a;
    final String d;
    public final String e;
    public final cv f;
    public final List c = new ArrayList();
    final WebView b = null;

    private cu(cx cxVar, String str, List list, String str2) {
        this.a = cxVar;
        this.d = str;
        if (list != null) {
            this.c.addAll(list);
            this.f = cv.NATIVE;
        } else {
            this.f = cv.HTML;
        }
        this.e = str2;
    }

    public static cu a(cx cxVar, String str, List list, String str2) {
        Cdo.a(cxVar, "Partner is null");
        Cdo.a((Object) str, "OM SDK JS script content is null");
        Cdo.a(list, "VerificationScriptResources is null");
        if (str2.length() > 256) {
            throw new IllegalArgumentException("CustomReferenceData is greater than 256 characters");
        }
        return new cu(cxVar, str, list, str2);
    }
}
