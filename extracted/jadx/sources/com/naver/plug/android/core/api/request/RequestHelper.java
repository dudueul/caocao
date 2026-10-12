package com.naver.plug.android.core.api.request;

import android.support.v4.util.Pair;
import com.naver.glink.android.sdk.ChannelCodes;
import com.naver.plug.android.core.b;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class RequestHelper {
    private static final List<Pair<String, String>> WITH_COUNTRY_LANG_CODES;

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Pair.create("zh", "CN"));
        arrayList.add(Pair.create("zh", "TW"));
        arrayList.add(Pair.create(ChannelCodes.SPANISH, "MX"));
        arrayList.add(Pair.create(ChannelCodes.PORTUGUESE, "BR"));
        WITH_COUNTRY_LANG_CODES = Collections.unmodifiableList(arrayList);
    }

    public static String getSystemLangCode() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return "";
        }
        for (Pair<String, String> pair : WITH_COUNTRY_LANG_CODES) {
            if (pair.first.equals(locale.getLanguage()) && pair.second.equals(locale.getCountry())) {
                return locale.getLanguage() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + locale.getCountry();
            }
        }
        return locale.getLanguage();
    }

    public static String getTimeZoneId() {
        TimeZone timeZone = TimeZone.getDefault();
        if (timeZone == null) {
            return null;
        }
        return timeZone.getID();
    }

    public static String getUserAgent() {
        return b.a();
    }

    public static String makeQueryString(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    if (sb.length() > 0) {
                        sb.append('&');
                    }
                    sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    sb.append('=');
                    sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: UTF-8", e);
        }
    }
}
