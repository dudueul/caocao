package com.naver.api.util;

import com.naver.api.security.HmacUtil;
import com.naver.api.security.client.MACManager;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import javax.crypto.Mac;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum Type {
    KEY { // from class: com.naver.api.util.Type.1
        @Override // com.naver.api.util.Type
        public Mac getMac(String str) throws Exception {
            return HmacUtil.getMac(str);
        }
    },
    FILE { // from class: com.naver.api.util.Type.2
        @Override // com.naver.api.util.Type
        public Mac getMac(String str) throws Exception {
            Properties properties = new Properties();
            InputStream inputStream = null;
            try {
                InputStream resourceAsStream = MACManager.class.getResourceAsStream(str);
                if (resourceAsStream == null) {
                    throw new FileNotFoundException(str);
                }
                properties.load(resourceAsStream);
                Mac mac = HmacUtil.getMac((String) properties.elements().nextElement());
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
                return mac;
            } catch (Throwable th) {
                if (0 != 0) {
                    inputStream.close();
                }
                throw th;
            }
        }
    };

    public abstract Mac getMac(String str) throws Exception;
}
