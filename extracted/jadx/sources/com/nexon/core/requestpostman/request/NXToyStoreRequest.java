package com.nexon.core.requestpostman.request;

import android.support.v4.util.ArrayMap;
import com.nexon.core.android.NXPApplicationConfigInterface;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.util.NXDateUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyStoreRequest extends NXToyRequest {
    private static final String CHARSET_HEADER_FIELD_VALUE = "UTF-8";
    private static final String CONTENT_TYPE_HEADER_FIELD_NAME = "Content-Type";
    private static final String CONTENT_TYPE_HEADER_FIELD_VALUE = "application/x-www-form-urlencoded";
    private static final String TIME_ZONE_UTC_HEADER_FIELD_NAME = "timeZone";
    public static final String X_TOY_TICKET_HEADER_FIELD_NAME = "X-Toy-Ticket";

    public NXToyStoreRequest() {
        super.setHttpURLWithoutPath(NXPApplicationConfigManager.getInstance().getServerURL(NXPApplicationConfigInterface.ServerType.Store).getUrl());
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("Content-Type", "application/x-www-form-urlencoded");
        arrayMap.put(TIME_ZONE_UTC_HEADER_FIELD_NAME, NXDateUtil.getCurrentTimeZoneOffset());
        ToyLog.d("Set message header : " + arrayMap.toString());
        super.putMessageHeader(arrayMap);
    }

    protected void setMessageBody(Map<String, Object> map) {
        byte[] bytes;
        if (getMethod() == NXToyRequestMethod.GET || map == null) {
            return;
        }
        ToyLog.d("BodyArguments : " + map.toString());
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                sb.append('=');
                sb.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                sb.append('&');
            }
            bytes = sb.toString().getBytes();
        } catch (UnsupportedEncodingException e) {
            bytes = null;
        }
        super.setMessageBody(bytes);
    }
}
