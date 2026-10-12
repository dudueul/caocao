package com.navercorp.volleyextensions.volleyer.http;

import com.navercorp.volleyextensions.volleyer.util.Assert;
import com.navercorp.volleyextensions.volleyer.util.StringUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ContentType {
    private static final char SEPERATOR_CHARACTER_OF_CONTENT_TYPE_HEADER = ';';
    private String contentTypeString;
    public static final ContentType CONTENT_TYPE_APPLICATION_JSON = new ContentType("application/json");
    public static final ContentType CONTENT_TYPE_APPLICATION_XML = new ContentType("application/xml");
    public static final ContentType CONTENT_TYPE_TEXT_JSON = new ContentType("text/json");
    public static final ContentType CONTENT_TYPE_TEXT_XML = new ContentType("text/xml");
    public static final ContentType CONTENT_TYPE_TEXT_PLAIN = new ContentType("text/plain");
    private static final Map<String, ContentType> DEFAULT_CONTENT_TYPES = new HashMap();

    static {
        DEFAULT_CONTENT_TYPES.put(CONTENT_TYPE_APPLICATION_JSON.contentTypeString, CONTENT_TYPE_APPLICATION_JSON);
        DEFAULT_CONTENT_TYPES.put(CONTENT_TYPE_TEXT_JSON.contentTypeString, CONTENT_TYPE_TEXT_JSON);
        DEFAULT_CONTENT_TYPES.put(CONTENT_TYPE_APPLICATION_XML.contentTypeString, CONTENT_TYPE_APPLICATION_XML);
        DEFAULT_CONTENT_TYPES.put(CONTENT_TYPE_TEXT_XML.contentTypeString, CONTENT_TYPE_TEXT_XML);
        DEFAULT_CONTENT_TYPES.put(CONTENT_TYPE_TEXT_PLAIN.contentTypeString, CONTENT_TYPE_TEXT_PLAIN);
    }

    private ContentType(String str) {
        Assert.notNull(str, "Content type");
        assertContentTypeString(str);
        this.contentTypeString = filterContentTypeString(str);
    }

    private void assertContentTypeString(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Content type must not be empty.");
        }
    }

    public static ContentType createContentType(String str) {
        ContentType defaultContentType = getDefaultContentType(str);
        return defaultContentType != null ? defaultContentType : new ContentType(str);
    }

    private static String deleteCharset(String str) {
        int iIndexOf = str.indexOf(59);
        return iIndexOf < 0 ? str : str.substring(0, iIndexOf);
    }

    private static String deleteSpaces(String str) {
        return str.replaceAll(" ", "");
    }

    private static String filterContentTypeString(String str) {
        return deleteSpaces(deleteCharset(str)).toLowerCase();
    }

    private static ContentType getDefaultContentType(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return DEFAULT_CONTENT_TYPES.get(filterContentTypeString(str));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ContentType)) {
            return false;
        }
        ContentType contentType = (ContentType) obj;
        return this == contentType || this.contentTypeString.equals(contentType.contentTypeString);
    }

    public int hashCode() {
        return this.contentTypeString.hashCode();
    }

    public String toString() {
        return this.contentTypeString;
    }
}
