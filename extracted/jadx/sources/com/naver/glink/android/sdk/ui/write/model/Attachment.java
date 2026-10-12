package com.naver.glink.android.sdk.ui.write.model;

import com.naver.glink.android.sdk.api.Responses;
import com.naver.plug.android.core.api.Response;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class Attachment extends Content {
    public Response a;

    Attachment(Responses.u.a aVar) {
        super(aVar);
    }

    Attachment(Map<String, Object> map) {
        super(map);
    }

    public void a(Response response) {
        this.a = response;
    }

    boolean a() {
        return this.a == null && this.b == null && this.c == null;
    }
}
