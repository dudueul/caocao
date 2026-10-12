package com.navercorp.volleyextensions.volleyer.multipart.stack;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;
import com.navercorp.volleyextensions.volleyer.multipart.MultipartContainer;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class MultipartHttpStackWrapper implements HttpStack {
    private MultipartHttpStack multipartStack;
    private final HttpStack stack;

    public MultipartHttpStackWrapper(HttpStack httpStack) {
        this(httpStack, new DefaultMultipartHttpStack());
    }

    public MultipartHttpStackWrapper(HttpStack httpStack, MultipartHttpStack multipartHttpStack) {
        Assert.notNull(httpStack, "HttpStack");
        Assert.notNull(multipartHttpStack, "MultipartHttpStack");
        this.stack = httpStack;
        this.multipartStack = multipartHttpStack;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean hasMultipart(Request<?> request) {
        if ((request instanceof MultipartContainer) && ((MultipartContainer) request).hasMultipart()) {
            return true;
        }
        return false;
    }

    @Override // com.android.volley.toolbox.HttpStack
    public HttpResponse performRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        return hasMultipart(request) ? this.multipartStack.performRequest(request, map) : this.stack.performRequest(request, map);
    }
}
