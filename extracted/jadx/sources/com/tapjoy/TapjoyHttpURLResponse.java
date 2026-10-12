package com.tapjoy;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TapjoyHttpURLResponse {
    public int contentLength;
    public long date;
    public long expires;
    public Map headerFields;
    public String redirectURL;
    public String response;
    public int statusCode;

    public String getHeaderFieldAsString(String str) {
        List list;
        return (this.headerFields == null || (list = (List) this.headerFields.get(str)) == null || list.get(0) == null) ? "" : (String) list.get(0);
    }
}
