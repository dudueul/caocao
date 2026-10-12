package com.naver.api.security.client;

import com.naver.api.security.HmacUtil;
import com.naver.api.util.Type;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.crypto.Mac;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class MACManager {
    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 30000;
    private static final long DEFAULT_CORRECTION_MILLIS = 0;
    private static final int DEFAULT_READ_TIMEOUT_MS = 30000;
    private static final String KEY_FILENAME = "/NHNAPIGatewayKey.properties";
    private static final int THREAD_COUNT = 2;
    static Future<Long> correctionMillisFuture;
    private static Mac mac;
    private static final Object lock = new Object();
    private static int connectionTimeoutMs = 30000;
    private static int readTimeoutMs = 30000;
    private static final long DEFAULT_CORRECTION_CONDITION_MILLIS = 600000;
    private static long correctionWhenConditionMs = DEFAULT_CORRECTION_CONDITION_MILLIS;
    private static final String DEFAULT_REMOTE_CURRENTTIME_URL = "http://global.apis.naver.com/currentTime";
    private static String remoteCurrentTimeUrl = DEFAULT_REMOTE_CURRENTTIME_URL;
    private static volatile boolean isThreadRunning = false;
    static long correctionMillis = 0;
    static final ExecutorService executor = Executors.newFixedThreadPool(2);

    private static void calcuateCorrectionTime(long j) {
        long jCurrentTimeMillis = j - System.currentTimeMillis();
        if (jCurrentTimeMillis > correctionWhenConditionMs) {
            correctionMillis = jCurrentTimeMillis;
        } else {
            correctionMillis = 0L;
        }
    }

    private static Future<Long> calcuateCorrectionTimeOnAnotherThread() {
        isThreadRunning = true;
        try {
            correctionMillisFuture = executor.submit(new Callable<Long>() { // from class: com.naver.api.security.client.MACManager.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Long call() throws Exception {
                    try {
                        URLConnection uRLConnectionOpenConnection = new URL(MACManager.remoteCurrentTimeUrl).openConnection();
                        uRLConnectionOpenConnection.setConnectTimeout(MACManager.connectionTimeoutMs);
                        uRLConnectionOpenConnection.setReadTimeout(MACManager.readTimeoutMs);
                        long j = Long.parseLong(MACManager.toStringFromInputStream(uRLConnectionOpenConnection.getInputStream(), "UTF-8")) - System.currentTimeMillis();
                        if (j > MACManager.correctionWhenConditionMs) {
                            MACManager.correctionMillis = j;
                        } else {
                            MACManager.correctionMillis = 0L;
                        }
                        return Long.valueOf(MACManager.correctionMillis);
                    } finally {
                        boolean unused = MACManager.isThreadRunning = false;
                    }
                }
            });
            return correctionMillisFuture;
        } catch (Exception e) {
            isThreadRunning = false;
            e.printStackTrace();
            return null;
        }
    }

    public static int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public static long getCorrectionWhenConditionMs() {
        return correctionWhenConditionMs;
    }

    public static String getEncryptUrl(String str) throws Exception {
        if (mac == null) {
            initialize();
        }
        return HmacUtil.makeEncryptUrl(mac, str, correctionMillis);
    }

    public static String getEncryptUrl(String str, long j) throws Exception {
        if (mac == null) {
            initialize();
        }
        return HmacUtil.makeEncryptUrlWithMsgpad(mac, str, j);
    }

    public static String getEncryptUrl(String str, Type type, String str2) throws Exception {
        return HmacUtil.makeEncryptUrl(type.getMac(str2), str, correctionMillis);
    }

    public static String getEncryptUrl(String str, Type type, String str2, long j) throws Exception {
        return HmacUtil.makeEncryptUrlWithMsgpad(type.getMac(str2), str, j);
    }

    public static int getReadTimeoutMs() {
        return readTimeoutMs;
    }

    public static String getRemoteCurrentTimeUrl() {
        return remoteCurrentTimeUrl;
    }

    public static void initialize() throws Exception {
        initialize(Type.FILE, KEY_FILENAME);
    }

    public static void initialize(Type type, String str) throws Exception {
        synchronized (lock) {
            if (mac == null) {
                mac = type.getMac(str);
            }
        }
    }

    public static void setConnectionTimeoutMs(int i) {
        connectionTimeoutMs = i;
    }

    public static void setCorrectionWhenConditionMs(long j) {
        correctionWhenConditionMs = j;
    }

    public static void setReadTimeoutMs(int i) {
        readTimeoutMs = i;
    }

    public static void setRemoteCurrentTimeUrl(String str) {
        remoteCurrentTimeUrl = str;
    }

    public static void syncWithServerTime(long j) {
        calcuateCorrectionTime(j);
    }

    public static Future<Long> syncWithServerTimeByHttpAsync() {
        return isThreadRunning ? correctionMillisFuture : calcuateCorrectionTimeOnAnotherThread();
    }

    public static Future<Long> syncWithServerTimeByHttpAsync(int i, int i2) {
        setConnectionTimeoutMs(i);
        setReadTimeoutMs(i2);
        return syncWithServerTimeByHttpAsync();
    }

    public static String toStringFromInputStream(InputStream inputStream, String str) throws IOException {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
            StringBuffer stringBuffer = new StringBuffer();
            char[] cArr = new char[1024];
            while (true) {
                int i = inputStreamReader.read(cArr);
                if (i == -1) {
                    break;
                }
                stringBuffer.append(new String(cArr, 0, i));
            }
            return stringBuffer.toString();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
