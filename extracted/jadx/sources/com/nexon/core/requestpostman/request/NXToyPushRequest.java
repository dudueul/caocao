package com.nexon.core.requestpostman.request;

import android.support.v4.util.ArrayMap;
import com.nexon.core.android.NXPApplicationConfigInterface;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXDateUtil;
import com.nexon.core.util.NXJsonUtil;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyPushRequest extends NXToyRequest {
    private static final String CONTENT_TYPE_HEADER_FIELD_NAME = "Content-Type";
    private static final String CONTENT_TYPE_HEADER_FIELD_VALUE = "application/json";
    private static final String TIME_ZONE_UTC_HEADER_FIELD_NAME = "timeZone";

    public NXToyPushRequest() {
        super.setHttpURLWithoutPath(NXPApplicationConfigManager.getInstance().getServerURL(NXPApplicationConfigInterface.ServerType.Push).getUrl());
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("Content-Type", "application/json");
        arrayMap.put(TIME_ZONE_UTC_HEADER_FIELD_NAME, NXDateUtil.getCurrentTimeZoneOffset());
        ToyLog.d("Set message header : " + arrayMap.toString());
        super.putMessageHeader(arrayMap);
    }

    protected void setMessageBody(Map<String, Object> map) {
        ToyLog.d("BodyArguments : " + map.toString());
        new NXJsonUtil();
        super.setMessageBody(NXJsonUtil.toJsonString(map).getBytes());
    }
}
