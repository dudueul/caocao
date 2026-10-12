package com.android.volley.toolbox;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class HttpHeaderParser {
    public static Cache.Entry parseCacheHeaders(NetworkResponse networkResponse) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = networkResponse.headers;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        boolean z = false;
        boolean z2 = false;
        String str = map.get(HttpRequest.HEADER_DATE);
        long dateAsEpoch = str != null ? parseDateAsEpoch(str) : 0L;
        String str2 = map.get(HttpRequest.HEADER_CACHE_CONTROL);
        if (str2 != null) {
            z = true;
            for (String str3 : str2.split(",")) {
                String strTrim = str3.trim();
                if (strTrim.equals("no-cache") || strTrim.equals("no-store")) {
                    return null;
                }
                if (strTrim.startsWith("max-age=")) {
                    try {
                        j3 = Long.parseLong(strTrim.substring(8));
                    } catch (Exception e) {
                    }
                } else if (strTrim.startsWith("stale-while-revalidate=")) {
                    try {
                        j4 = Long.parseLong(strTrim.substring(23));
                    } catch (Exception e2) {
                    }
                } else if (strTrim.equals("must-revalidate") || strTrim.equals("proxy-revalidate")) {
                    z2 = true;
                }
            }
        }
        String str4 = map.get(HttpRequest.HEADER_EXPIRES);
        long dateAsEpoch2 = str4 != null ? parseDateAsEpoch(str4) : 0L;
        String str5 = map.get(HttpRequest.HEADER_LAST_MODIFIED);
        long dateAsEpoch3 = str5 != null ? parseDateAsEpoch(str5) : 0L;
        String str6 = map.get(HttpRequest.HEADER_ETAG);
        if (z) {
            j = jCurrentTimeMillis + (1000 * j3);
            j2 = z2 ? j : j + (1000 * j4);
        } else if (dateAsEpoch > 0 && dateAsEpoch2 >= dateAsEpoch) {
            j = jCurrentTimeMillis + (dateAsEpoch2 - dateAsEpoch);
            j2 = j;
        }
        Cache.Entry entry = new Cache.Entry();
        entry.data = networkResponse.data;
        entry.etag = str6;
        entry.softTtl = j;
        entry.ttl = j2;
        entry.serverDate = dateAsEpoch;
        entry.lastModified = dateAsEpoch3;
        entry.responseHeaders = map;
        return entry;
    }

    public static String parseCharset(Map<String, String> map) {
        return parseCharset(map, "ISO-8859-1");
    }

    public static String parseCharset(Map<String, String> map, String str) {
        String str2 = map.get(HttpRequest.HEADER_CONTENT_TYPE);
        if (str2 == null) {
            return str;
        }
        String[] strArrSplit = str2.split(";");
        for (int i = 1; i < strArrSplit.length; i++) {
            String[] strArrSplit2 = strArrSplit[i].trim().split("=");
            if (strArrSplit2.length == 2 && strArrSplit2[0].equals(HttpRequest.PARAM_CHARSET)) {
                return strArrSplit2[1];
            }
        }
        return str;
    }

    public static long parseDateAsEpoch(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0L;
        }
    }
}
