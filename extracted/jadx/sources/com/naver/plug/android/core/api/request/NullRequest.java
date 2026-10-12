package com.naver.plug.android.core.api.request;

import android.content.Context;
import com.naver.plug.android.core.api.Response;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NullRequest<T extends Response> extends Request<T> {
    private NullRequest(RequestBuilder<T> requestBuilder) {
        super(requestBuilder);
    }

    public static <T extends Response> Request<T> nullRequest() {
        return new NullRequest(new RequestBuilder());
    }

    @Override // com.naver.plug.android.core.api.request.Request
    public void execute(Context context, int i, RequestListener<T> requestListener) {
    }
}
