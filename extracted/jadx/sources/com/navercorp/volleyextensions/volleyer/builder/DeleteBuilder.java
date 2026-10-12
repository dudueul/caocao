package com.navercorp.volleyextensions.volleyer.builder;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.navercorp.volleyextensions.volleyer.VolleyerConfiguration;
import com.navercorp.volleyextensions.volleyer.http.HttpMethod;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DeleteBuilder extends RequestBuilder<DeleteBuilder> {
    public DeleteBuilder(RequestQueue requestQueue, VolleyerConfiguration volleyerConfiguration, String str) {
        super(requestQueue, volleyerConfiguration, str, HttpMethod.DELETE);
    }

    @Override // com.navercorp.volleyextensions.volleyer.builder.RequestBuilder
    public /* bridge */ /* synthetic */ Request execute() {
        return super.execute();
    }

    @Override // com.navercorp.volleyextensions.volleyer.builder.RequestBuilder
    public /* bridge */ /* synthetic */ ResponseBuilder withErrorListener(Response.ErrorListener errorListener) {
        return super.withErrorListener(errorListener);
    }

    @Override // com.navercorp.volleyextensions.volleyer.builder.RequestBuilder
    public /* bridge */ /* synthetic */ ResponseBuilder withListener(Response.Listener listener) {
        return super.withListener(listener);
    }

    @Override // com.navercorp.volleyextensions.volleyer.builder.RequestBuilder
    public /* bridge */ /* synthetic */ ResponseBuilder withTargetClass(Class cls) {
        return super.withTargetClass(cls);
    }
}
