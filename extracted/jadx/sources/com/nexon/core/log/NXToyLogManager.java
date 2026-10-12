package com.nexon.core.log;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import com.nexon.core.log.constant.NXToyLogLevel;
import com.nexon.core.log.logger.NXToyLogcatLogger;
import com.nexon.core.log.logger.NXToyLogger;
import com.nexon.core.log.logger.NXToyPlexLogger;
import com.nexon.core.log.model.NXToyStackTraceInfo;
import com.nexon.core.log.util.NXToyLogUtil;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyLogManager {
    private final Map<Class<? extends NXToyLogger>, NXToyLogger> loggers;

    private static class Singleton {
        private static final NXToyLogManager instance = new NXToyLogManager();

        private Singleton() {
        }
    }

    private NXToyLogManager() {
        this.loggers = new ConcurrentHashMap();
        this.loggers.put(NXToyPlexLogger.class, new NXToyPlexLogger());
    }

    public static NXToyLogManager getInstance() {
        return Singleton.instance;
    }

    public void log(NXToyLogLevel nXToyLogLevel, String str, Object... objArr) {
        NXToyStackTraceInfo nXToyStackTraceInfoExtractStackTrace = NXToyLogUtil.extractStackTrace();
        Iterator<Class<? extends NXToyLogger>> it = this.loggers.keySet().iterator();
        while (it.hasNext()) {
            NXToyLogger nXToyLogger = this.loggers.get(it.next());
            if (nXToyLogger != null) {
                nXToyLogger.addLog(nXToyStackTraceInfoExtractStackTrace, nXToyLogLevel, str, objArr);
            }
        }
    }

    public void setEnableSendServer(boolean z) {
        NXToyPlexLogger nXToyPlexLogger = (NXToyPlexLogger) this.loggers.get(NXToyPlexLogger.class);
        if (nXToyPlexLogger == null) {
            return;
        }
        nXToyPlexLogger.setEnableSend(z);
    }

    public void setupLogcatLogger(PackageManager packageManager) {
        if (this.loggers.get(NXToyLogcatLogger.class) != null) {
            return;
        }
        try {
            Signature[] signatureArr = packageManager.getPackageInfo("com.nexon.platform.mobile.toycontroller", 64).signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return;
            }
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(signatureArr[0].toByteArray());
            if (Base64.encodeToString(messageDigest.digest(), 0).equals("8bv6YOGFv0JgnF+aEwTIpKoHA3w=\n")) {
                this.loggers.put(NXToyLogcatLogger.class, new NXToyLogcatLogger());
            }
        } catch (Exception e) {
        }
    }
}
