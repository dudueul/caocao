package com.navercorp.volleyextensions.volleyer.builder;

import com.android.volley.RequestQueue;
import com.navercorp.volleyextensions.volleyer.VolleyerConfiguration;
import com.navercorp.volleyextensions.volleyer.builder.BodyBuilder;
import com.navercorp.volleyextensions.volleyer.http.HttpMethod;
import com.navercorp.volleyextensions.volleyer.multipart.FilePart;
import com.navercorp.volleyextensions.volleyer.multipart.Part;
import com.navercorp.volleyextensions.volleyer.multipart.StringPart;
import com.navercorp.volleyextensions.volleyer.util.VolleyerLog;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class BodyBuilder<B extends BodyBuilder<B>> extends RequestBuilder<B> {
    public BodyBuilder(RequestQueue requestQueue, VolleyerConfiguration volleyerConfiguration, String str, HttpMethod httpMethod) {
        super(requestQueue, volleyerConfiguration, str, httpMethod);
    }

    private boolean isBodyNull(Object obj) {
        if (obj != null) {
            return false;
        }
        VolleyerLog.warn("You have to set the body which is not null.", new Object[0]);
        return true;
    }

    public B addFilePart(File file) {
        return (B) addFilePart(file.getName(), file);
    }

    public B addFilePart(String str, File file) {
        this.httpContent.addPart(new FilePart(str, file));
        return this;
    }

    public B addPart(Part part) {
        this.httpContent.addPart(part);
        return this;
    }

    public B addStringPart(String str, String str2) {
        this.httpContent.addPart(new StringPart(str, str2));
        return this;
    }

    public B withBody(String str) {
        return isBodyNull(str) ? this : (B) withBody(str.getBytes());
    }

    public B withBody(byte[] bArr) {
        if (!isBodyNull(bArr)) {
            this.httpContent.setBody(bArr);
        }
        return this;
    }
}
