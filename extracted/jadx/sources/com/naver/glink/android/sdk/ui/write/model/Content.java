package com.naver.glink.android.sdk.ui.write.model;

import android.os.Parcelable;
import com.naver.glink.android.sdk.api.Responses;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class Content extends com.naver.plug.android.core.a.a implements Parcelable {
    public final Responses.u.a b;
    public final Map<String, Object> c;

    Content(Responses.u.a aVar) {
        this.b = aVar;
        this.c = null;
    }

    Content(Map<String, Object> map) {
        this.b = null;
        this.c = map;
    }

    Map<String, Object> b() {
        if (this.b == null) {
            return this.c != null ? this.c : new HashMap();
        }
        HashMap map = new HashMap();
        map.put("mediaType", this.b.mediaType);
        map.put("htmlTag", this.b.htmlTag);
        map.put("param", this.b.param);
        return map;
    }
}
