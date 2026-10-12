package com.naver.api.security.client;

import com.naver.api.security.HmacUtil;
import java.io.InputStream;
import java.util.Properties;
import javax.crypto.Mac;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ServiceMACManager {
    private static final String EXTSERVICE = "__extservice__";
    private static final String KEY_FILENAME = "/NHNAPIGatewayKey.properties";
    private static Mac mac;
    private static String serviceId;

    public static String getEncryptUrl(String str) throws Exception {
        if (mac == null) {
            initialize();
        }
        if (!str.contains(EXTSERVICE)) {
            throw new Exception("wrong url : '__extservice__' not exists");
        }
        return HmacUtil.makeEncryptUrl(mac, str.replaceAll(EXTSERVICE, serviceId), MACManager.correctionMillis);
    }

    public static String getEncryptUrl(String str, long j) throws Exception {
        if (mac == null) {
            initialize();
        }
        if (!str.contains(EXTSERVICE)) {
            throw new Exception("wrong url : '__extservice__' not exists");
        }
        return HmacUtil.makeEncryptUrlWithMsgpad(mac, str.replaceAll(EXTSERVICE, serviceId), j);
    }

    public static void initialize() throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            InputStream resourceAsStream = ServiceMACManager.class.getResourceAsStream(KEY_FILENAME);
            properties.load(resourceAsStream);
            serviceId = (String) properties.keySet().iterator().next();
            String property = properties.getProperty(serviceId);
            if (property == null) {
                throw new Exception("HMAC key Not Exists");
            }
            mac = HmacUtil.getMac(property);
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                inputStream.close();
            }
            throw th;
        }
    }
}
