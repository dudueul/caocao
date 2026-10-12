package com.adjust.sdk;

import android.content.Context;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.tapjoy.TapjoyConstants;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AdjustFactory {
    private static IPackageHandler packageHandler = null;
    private static IRequestHandler requestHandler = null;
    private static IAttributionHandler attributionHandler = null;
    private static IActivityHandler activityHandler = null;
    private static ILogger logger = null;
    private static HttpsURLConnection httpsURLConnection = null;
    private static ISdkClickHandler sdkClickHandler = null;
    private static long timerInterval = -1;
    private static long timerStart = -1;
    private static long sessionInterval = -1;
    private static long subsessionInterval = -1;
    private static BackoffStrategy sdkClickBackoffStrategy = null;
    private static BackoffStrategy packageHandlerBackoffStrategy = null;
    private static long maxDelayStart = -1;
    private static String baseUrl = Constants.BASE_URL;
    private static UtilNetworking.IConnectionOptions connectionOptions = null;
    private static boolean tryInstallReferrer = true;

    public static class URLGetConnection {
        HttpsURLConnection httpsURLConnection;
        URL url;

        URLGetConnection(HttpsURLConnection httpsURLConnection, URL url) {
            this.httpsURLConnection = httpsURLConnection;
            this.url = url;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
        }
        return sb.toString();
    }

    public static IActivityHandler getActivityHandler(AdjustConfig adjustConfig) {
        if (activityHandler == null) {
            return ActivityHandler.getInstance(adjustConfig);
        }
        activityHandler.init(adjustConfig);
        return activityHandler;
    }

    public static IAttributionHandler getAttributionHandler(IActivityHandler iActivityHandler, ActivityPackage activityPackage, boolean z) {
        if (attributionHandler == null) {
            return new AttributionHandler(iActivityHandler, activityPackage, z);
        }
        attributionHandler.init(iActivityHandler, activityPackage, z);
        return attributionHandler;
    }

    public static String getBaseUrl() {
        return baseUrl == null ? Constants.BASE_URL : baseUrl;
    }

    public static UtilNetworking.IConnectionOptions getConnectionOptions() {
        return connectionOptions == null ? new UtilNetworking.ConnectionOptions() : connectionOptions;
    }

    public static HttpsURLConnection getHttpsURLConnection(URL url) throws IOException {
        return httpsURLConnection == null ? (HttpsURLConnection) url.openConnection() : httpsURLConnection;
    }

    public static ILogger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public static long getMaxDelayStart() {
        return maxDelayStart == -1 ? TapjoyConstants.TIMER_INCREMENT : maxDelayStart;
    }

    public static IPackageHandler getPackageHandler(IActivityHandler iActivityHandler, Context context, boolean z) {
        if (packageHandler == null) {
            return new PackageHandler(iActivityHandler, context, z);
        }
        packageHandler.init(iActivityHandler, context, z);
        return packageHandler;
    }

    public static BackoffStrategy getPackageHandlerBackoffStrategy() {
        return packageHandlerBackoffStrategy == null ? BackoffStrategy.LONG_WAIT : packageHandlerBackoffStrategy;
    }

    public static IRequestHandler getRequestHandler(IPackageHandler iPackageHandler) {
        if (requestHandler == null) {
            return new RequestHandler(iPackageHandler);
        }
        requestHandler.init(iPackageHandler);
        return requestHandler;
    }

    public static BackoffStrategy getSdkClickBackoffStrategy() {
        return sdkClickBackoffStrategy == null ? BackoffStrategy.SHORT_WAIT : sdkClickBackoffStrategy;
    }

    public static ISdkClickHandler getSdkClickHandler(IActivityHandler iActivityHandler, boolean z) {
        if (sdkClickHandler == null) {
            return new SdkClickHandler(iActivityHandler, z);
        }
        sdkClickHandler.init(iActivityHandler, z);
        return sdkClickHandler;
    }

    public static long getSessionInterval() {
        return sessionInterval == -1 ? TapjoyConstants.SESSION_ID_INACTIVITY_TIME : sessionInterval;
    }

    public static long getSubsessionInterval() {
        if (subsessionInterval == -1) {
            return 1000L;
        }
        return subsessionInterval;
    }

    public static long getTimerInterval() {
        if (timerInterval == -1) {
            return 60000L;
        }
        return timerInterval;
    }

    public static long getTimerStart() {
        if (timerStart == -1) {
            return 60000L;
        }
        return timerStart;
    }

    public static boolean getTryInstallReferrer() {
        return tryInstallReferrer;
    }

    public static void setActivityHandler(IActivityHandler iActivityHandler) {
        activityHandler = iActivityHandler;
    }

    public static void setAttributionHandler(IAttributionHandler iAttributionHandler) {
        attributionHandler = iAttributionHandler;
    }

    public static void setBaseUrl(String str) {
        baseUrl = str;
    }

    public static void setHttpsURLConnection(HttpsURLConnection httpsURLConnection2) {
        httpsURLConnection = httpsURLConnection2;
    }

    public static void setLogger(ILogger iLogger) {
        logger = iLogger;
    }

    public static void setPackageHandler(IPackageHandler iPackageHandler) {
        packageHandler = iPackageHandler;
    }

    public static void setPackageHandlerBackoffStrategy(BackoffStrategy backoffStrategy) {
        packageHandlerBackoffStrategy = backoffStrategy;
    }

    public static void setRequestHandler(IRequestHandler iRequestHandler) {
        requestHandler = iRequestHandler;
    }

    public static void setSdkClickBackoffStrategy(BackoffStrategy backoffStrategy) {
        sdkClickBackoffStrategy = backoffStrategy;
    }

    public static void setSdkClickHandler(ISdkClickHandler iSdkClickHandler) {
        sdkClickHandler = iSdkClickHandler;
    }

    public static void setSessionInterval(long j) {
        sessionInterval = j;
    }

    public static void setSubsessionInterval(long j) {
        subsessionInterval = j;
    }

    public static void setTimerInterval(long j) {
        timerInterval = j;
    }

    public static void setTimerStart(long j) {
        timerStart = j;
    }

    public static void setTryInstallReferrer(boolean z) {
        tryInstallReferrer = z;
    }

    public static void teardown(Context context) {
        if (context != null) {
            ActivityHandler.deleteState(context);
            PackageHandler.deleteState(context);
        }
        packageHandler = null;
        requestHandler = null;
        attributionHandler = null;
        activityHandler = null;
        logger = null;
        httpsURLConnection = null;
        sdkClickHandler = null;
        timerInterval = -1L;
        timerStart = -1L;
        sessionInterval = -1L;
        subsessionInterval = -1L;
        sdkClickBackoffStrategy = null;
        packageHandlerBackoffStrategy = null;
        maxDelayStart = -1L;
        baseUrl = Constants.BASE_URL;
        connectionOptions = null;
        tryInstallReferrer = true;
    }

    public static void useTestConnectionOptions() {
        connectionOptions = new UtilNetworking.IConnectionOptions() { // from class: com.adjust.sdk.AdjustFactory.1
            @Override // com.adjust.sdk.UtilNetworking.IConnectionOptions
            public void applyConnectionOptions(HttpsURLConnection httpsURLConnection2, String str) {
                new UtilNetworking.ConnectionOptions().applyConnectionOptions(httpsURLConnection2, str);
                try {
                    SSLContext sSLContext = SSLContext.getInstance("TLS");
                    sSLContext.init(null, new TrustManager[]{new X509TrustManager() { // from class: com.adjust.sdk.AdjustFactory.1.1
                        @Override // javax.net.ssl.X509TrustManager
                        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str2) {
                            AdjustFactory.getLogger().verbose("checkClientTrusted ", new Object[0]);
                        }

                        @Override // javax.net.ssl.X509TrustManager
                        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str2) throws CertificateException {
                            AdjustFactory.getLogger().verbose("checkServerTrusted ", new Object[0]);
                            try {
                                if (AdjustFactory.byte2HexFormatted(MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1).digest(x509CertificateArr[0].getEncoded())).equalsIgnoreCase("7BCFF44099A35BC093BB48C5A6B9A516CDFDA0D1")) {
                                } else {
                                    throw new CertificateException();
                                }
                            } catch (NoSuchAlgorithmException e) {
                                AdjustFactory.getLogger().error("testingMode error %s", e.getMessage());
                            } catch (CertificateEncodingException e2) {
                                AdjustFactory.getLogger().error("testingMode error %s", e2.getMessage());
                            }
                        }

                        @Override // javax.net.ssl.X509TrustManager
                        public X509Certificate[] getAcceptedIssuers() {
                            AdjustFactory.getLogger().verbose("getAcceptedIssuers", new Object[0]);
                            return null;
                        }
                    }}, new SecureRandom());
                    httpsURLConnection2.setSSLSocketFactory(sSLContext.getSocketFactory());
                    httpsURLConnection2.setHostnameVerifier(new HostnameVerifier() { // from class: com.adjust.sdk.AdjustFactory.1.2
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String str2, SSLSession sSLSession) {
                            AdjustFactory.getLogger().verbose("verify hostname ", new Object[0]);
                            return true;
                        }
                    });
                } catch (Exception e) {
                    AdjustFactory.getLogger().error("testingMode error %s", e.getMessage());
                }
            }
        };
    }
}
