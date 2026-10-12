package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface HttpClientConfiguration {
    int getHttpConnectionTimeout();

    String getHttpProxyHost();

    String getHttpProxyPassword();

    int getHttpProxyPort();

    String getHttpProxyUser();

    int getHttpReadTimeout();

    int getHttpRetryCount();

    int getHttpRetryIntervalSeconds();

    boolean isGZIPEnabled();

    boolean isPrettyDebugEnabled();
}
