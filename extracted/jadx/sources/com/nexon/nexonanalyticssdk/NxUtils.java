package com.nexon.nexonanalyticssdk;

import android.content.Context;
import android.provider.Settings;
import com.google.gson.Gson;
import com.tapjoy.TapjoyConstants;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxUtils {
    private static final String NX_LOG_TIME_RFC3339_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static SimpleDateFormat dateRFC3339Format = new SimpleDateFormat(NX_LOG_TIME_RFC3339_PATTERN, Locale.getDefault());
    private static final String NX_LOG_TIME_DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    private static SimpleDateFormat dateDefaultFormat = new SimpleDateFormat(NX_LOG_TIME_DEFAULT_PATTERN);
    public static Gson gson = new Gson();

    NxUtils() {
    }

    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            NxLogcat.e("close : " + e.getMessage());
        }
    }

    public static StringBuilder completeLog(StringBuilder sb, int i, long j, int i2) {
        int iIndexOf = sb.indexOf(NxLogInfo.VALUE_DEFAULT_CREATE_DATE, i);
        sb.replace(iIndexOf, iIndexOf + NxLogInfo.VALUE_DEFAULT_CREATE_DATE.length(), convertToStringTime(j));
        int iIndexOf2 = sb.indexOf(NxLogInfo.VALUE_DEFAULT_TIME_SYNC, i);
        sb.replace(iIndexOf2, iIndexOf2 + NxLogInfo.VALUE_DEFAULT_TIME_SYNC.length(), i2 == 1 ? "true" : "false");
        return sb;
    }

    public static synchronized String convertMapToString(Map<String, Object> map) {
        return gson.toJson(map);
    }

    public static long convertToLongSyncTime(String str) {
        Date date = null;
        try {
            date = dateDefaultFormat.parse(str);
        } catch (ParseException e) {
            NxLogcat.e("convertToLongSyncTime : " + e.getMessage());
        }
        return date.getTime();
    }

    public static String convertToStringTime(long j) {
        return dateRFC3339Format.format(Long.valueOf(j));
    }

    public static Map<String, Object> fromJson(String str) {
        return (Map) gson.fromJson(str, Map.class);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static String loadLocalIpV4Address() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                for (InetAddress inetAddress : Collections.list(((NetworkInterface) it.next()).getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (hostAddress.indexOf(58) < 0) {
                            return hostAddress;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            NxLogcat.e("loadLocalIpV4Address : " + e.getMessage());
        }
        return null;
    }

    public static String loadMachineId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID).toUpperCase();
    }
}
