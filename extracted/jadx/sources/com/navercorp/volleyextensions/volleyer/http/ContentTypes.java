package com.navercorp.volleyextensions.volleyer.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ContentTypes {
    private List<ContentType> contentTypes = new ArrayList();

    public ContentTypes(ContentType... contentTypeArr) {
        setContentTypes(contentTypeArr);
        assertTypeArguments();
    }

    private void addContentType(ContentType contentType) {
        if (contentType == null) {
            return;
        }
        this.contentTypes.add(contentType);
    }

    private void assertTypeArguments() {
        if (this.contentTypes.size() == 0) {
            throw new IllegalArgumentException("None of content types were added.");
        }
    }

    private void setContentTypes(ContentType... contentTypeArr) {
        if (contentTypeArr == null) {
            return;
        }
        for (ContentType contentType : contentTypeArr) {
            addContentType(contentType);
        }
    }

    public List<ContentType> getListOfContentTypes() {
        return Collections.unmodifiableList(this.contentTypes);
    }
}
