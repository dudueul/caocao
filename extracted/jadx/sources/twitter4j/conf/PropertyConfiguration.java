package twitter4j.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import twitter4j.HttpClientConfiguration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class PropertyConfiguration extends ConfigurationBase implements Serializable {
    private static final String APPLICATION_ONLY_AUTH_ENABLED = "enableApplicationOnlyAuth";
    private static final String ASYNC_DAEMON_ENABLED = "async.daemonEnabled";
    private static final String ASYNC_DISPATCHER_IMPL = "async.dispatcherImpl";
    private static final String ASYNC_NUM_THREADS = "async.numThreads";
    private static final String CONTRIBUTING_TO = "contributingTo";
    private static final String DEBUG = "debug";
    private static final String HTTP_CONNECTION_TIMEOUT = "http.connectionTimeout";
    private static final String HTTP_GZIP = "http.gzip";
    private static final String HTTP_PRETTY_DEBUG = "http.prettyDebug";
    private static final String HTTP_PROXY_HOST = "http.proxyHost";
    private static final String HTTP_PROXY_HOST_FALLBACK = "http.proxyHost";
    private static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
    private static final String HTTP_PROXY_PORT = "http.proxyPort";
    private static final String HTTP_PROXY_PORT_FALLBACK = "http.proxyPort";
    private static final String HTTP_PROXY_USER = "http.proxyUser";
    private static final String HTTP_READ_TIMEOUT = "http.readTimeout";
    private static final String HTTP_RETRY_COUNT = "http.retryCount";
    private static final String HTTP_RETRY_INTERVAL_SECS = "http.retryIntervalSecs";
    private static final String HTTP_STREAMING_READ_TIMEOUT = "http.streamingReadTimeout";
    private static final String INCLUDE_ENTITIES = "includeEntities";
    private static final String INCLUDE_MY_RETWEET = "includeMyRetweet";
    private static final String JSON_STORE_ENABLED = "jsonStoreEnabled";
    private static final String LOGGER_FACTORY = "loggerFactory";
    private static final String MBEAN_ENABLED = "mbeanEnabled";
    private static final String MEDIA_PROVIDER = "media.provider";
    private static final String MEDIA_PROVIDER_API_KEY = "media.providerAPIKey";
    private static final String MEDIA_PROVIDER_PARAMETERS = "media.providerParameters";
    private static final String OAUTH2_ACCESS_TOKEN = "oauth2.accessToken";
    private static final String OAUTH2_INVALIDATE_TOKEN_URL = "oauth2.invalidateTokenURL";
    private static final String OAUTH2_SCOPE = "oauth2.scope";
    private static final String OAUTH2_TOKEN_TYPE = "oauth2.tokenType";
    private static final String OAUTH2_TOKEN_URL = "oauth2.tokenURL";
    private static final String OAUTH_ACCESS_TOKEN = "oauth.accessToken";
    private static final String OAUTH_ACCESS_TOKEN_SECRET = "oauth.accessTokenSecret";
    private static final String OAUTH_ACCESS_TOKEN_URL = "oauth.accessTokenURL";
    private static final String OAUTH_AUTHENTICATION_URL = "oauth.authenticationURL";
    private static final String OAUTH_AUTHORIZATION_URL = "oauth.authorizationURL";
    private static final String OAUTH_CONSUMER_KEY = "oauth.consumerKey";
    private static final String OAUTH_CONSUMER_SECRET = "oauth.consumerSecret";
    private static final String OAUTH_REQUEST_TOKEN_URL = "oauth.requestTokenURL";
    private static final String PASSWORD = "password";
    private static final String REST_BASE_URL = "restBaseURL";
    private static final String SITE_STREAM_BASE_URL = "siteStreamBaseURL";
    private static final String STREAM_BASE_URL = "streamBaseURL";
    private static final String STREAM_STALL_WARNINGS_ENABLED = "stream.enableStallWarnings";
    private static final String STREAM_USER_REPLIES_ALL = "stream.user.repliesAll";
    private static final String STREAM_USER_WITH_FOLLOWINGS = "stream.user.withFollowings";
    private static final String USER = "user";
    private static final String USER_STREAM_BASE_URL = "userStreamBaseURL";
    private static final long serialVersionUID = -7262615247923693252L;
    private String OAuth2Scope;

    PropertyConfiguration() {
        this("/");
    }

    public PropertyConfiguration(InputStream inputStream) {
        Properties properties = new Properties();
        loadProperties(properties, inputStream);
        setFieldsWithTreePath(properties, "/");
    }

    PropertyConfiguration(String str) throws Throwable {
        Properties properties;
        try {
            properties = (Properties) System.getProperties().clone();
            try {
                Map<String, String> map = System.getenv();
                for (String str2 : map.keySet()) {
                    properties.setProperty(str2, map.get(str2));
                }
            } catch (SecurityException e) {
            }
            normalize(properties);
        } catch (SecurityException e2) {
            properties = new Properties();
        }
        loadProperties(properties, "." + File.separatorChar + "twitter4j.properties");
        loadProperties(properties, Configuration.class.getResourceAsStream("/twitter4j.properties"));
        loadProperties(properties, Configuration.class.getResourceAsStream("/WEB-INF/twitter4j.properties"));
        try {
            loadProperties(properties, new FileInputStream("WEB-INF/twitter4j.properties"));
        } catch (FileNotFoundException e3) {
        } catch (SecurityException e4) {
        }
        setFieldsWithTreePath(properties, str);
    }

    public PropertyConfiguration(Properties properties) {
        this(properties, "/");
    }

    public PropertyConfiguration(Properties properties, String str) {
        setFieldsWithTreePath(properties, str);
    }

    private boolean loadProperties(Properties properties, InputStream inputStream) {
        try {
            properties.load(inputStream);
            normalize(properties);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean loadProperties(Properties properties, String str) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            File file = new File(str);
            if (!file.exists() || !file.isFile()) {
                if (0 != 0) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                }
                return false;
            }
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                properties.load(fileInputStream2);
                normalize(properties);
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e2) {
                    }
                }
                return true;
            } catch (Exception e3) {
                fileInputStream = fileInputStream2;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
        } catch (Throwable th2) {
            th = th2;
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e6) {
            }
        }
        return false;
    }

    private void normalize(Properties properties) {
        ArrayList<String> arrayList = new ArrayList(10);
        for (String str : properties.keySet()) {
            if (-1 != str.indexOf("twitter4j.")) {
                arrayList.add(str);
            }
        }
        for (String str2 : arrayList) {
            String property = properties.getProperty(str2);
            int iIndexOf = str2.indexOf("twitter4j.");
            properties.setProperty(String.valueOf(str2.substring(0, iIndexOf)) + str2.substring(iIndexOf + 10), property);
        }
    }

    private boolean notNull(Properties properties, String str, String str2) {
        return properties.getProperty(new StringBuilder(String.valueOf(str)).append(str2).toString()) != null;
    }

    private void setFieldsWithPrefix(Properties properties, String str) {
        if (notNull(properties, str, "debug")) {
            setDebug(getBoolean(properties, str, "debug"));
        }
        if (notNull(properties, str, USER)) {
            setUser(getString(properties, str, USER));
        }
        if (notNull(properties, str, PASSWORD)) {
            setPassword(getString(properties, str, PASSWORD));
        }
        if (notNull(properties, str, HTTP_PRETTY_DEBUG)) {
            setPrettyDebugEnabled(getBoolean(properties, str, HTTP_PRETTY_DEBUG));
        }
        if (notNull(properties, str, HTTP_GZIP)) {
            setGZIPEnabled(getBoolean(properties, str, HTTP_GZIP));
        }
        if (notNull(properties, str, "http.proxyHost") || notNull(properties, str, "http.proxyHost")) {
            setHttpProxyHost(getString(properties, str, "http.proxyHost"));
        }
        if (notNull(properties, str, HTTP_PROXY_USER)) {
            setHttpProxyUser(getString(properties, str, HTTP_PROXY_USER));
        }
        if (notNull(properties, str, HTTP_PROXY_PASSWORD)) {
            setHttpProxyPassword(getString(properties, str, HTTP_PROXY_PASSWORD));
        }
        if (notNull(properties, str, "http.proxyPort") || notNull(properties, str, "http.proxyPort")) {
            setHttpProxyPort(getIntProperty(properties, str, "http.proxyPort"));
        }
        if (notNull(properties, str, HTTP_CONNECTION_TIMEOUT)) {
            setHttpConnectionTimeout(getIntProperty(properties, str, HTTP_CONNECTION_TIMEOUT));
        }
        if (notNull(properties, str, HTTP_READ_TIMEOUT)) {
            setHttpReadTimeout(getIntProperty(properties, str, HTTP_READ_TIMEOUT));
        }
        if (notNull(properties, str, HTTP_STREAMING_READ_TIMEOUT)) {
            setHttpStreamingReadTimeout(getIntProperty(properties, str, HTTP_STREAMING_READ_TIMEOUT));
        }
        if (notNull(properties, str, HTTP_RETRY_COUNT)) {
            setHttpRetryCount(getIntProperty(properties, str, HTTP_RETRY_COUNT));
        }
        if (notNull(properties, str, HTTP_RETRY_INTERVAL_SECS)) {
            setHttpRetryIntervalSeconds(getIntProperty(properties, str, HTTP_RETRY_INTERVAL_SECS));
        }
        if (notNull(properties, str, OAUTH_CONSUMER_KEY)) {
            setOAuthConsumerKey(getString(properties, str, OAUTH_CONSUMER_KEY));
        }
        if (notNull(properties, str, OAUTH_CONSUMER_SECRET)) {
            setOAuthConsumerSecret(getString(properties, str, OAUTH_CONSUMER_SECRET));
        }
        if (notNull(properties, str, OAUTH_ACCESS_TOKEN)) {
            setOAuthAccessToken(getString(properties, str, OAUTH_ACCESS_TOKEN));
        }
        if (notNull(properties, str, OAUTH_ACCESS_TOKEN_SECRET)) {
            setOAuthAccessTokenSecret(getString(properties, str, OAUTH_ACCESS_TOKEN_SECRET));
        }
        if (notNull(properties, str, OAUTH2_TOKEN_TYPE)) {
            setOAuth2TokenType(getString(properties, str, OAUTH2_TOKEN_TYPE));
        }
        if (notNull(properties, str, OAUTH2_ACCESS_TOKEN)) {
            setOAuth2AccessToken(getString(properties, str, OAUTH2_ACCESS_TOKEN));
        }
        if (notNull(properties, str, OAUTH2_SCOPE)) {
            setOAuth2Scope(getString(properties, str, OAUTH2_SCOPE));
        }
        if (notNull(properties, str, ASYNC_NUM_THREADS)) {
            setAsyncNumThreads(getIntProperty(properties, str, ASYNC_NUM_THREADS));
        }
        if (notNull(properties, str, ASYNC_DAEMON_ENABLED)) {
            setDaemonEnabled(getBoolean(properties, str, ASYNC_DAEMON_ENABLED));
        }
        if (notNull(properties, str, CONTRIBUTING_TO)) {
            setContributingTo(getLongProperty(properties, str, CONTRIBUTING_TO));
        }
        if (notNull(properties, str, ASYNC_DISPATCHER_IMPL)) {
            setDispatcherImpl(getString(properties, str, ASYNC_DISPATCHER_IMPL));
        }
        if (notNull(properties, str, OAUTH_REQUEST_TOKEN_URL)) {
            setOAuthRequestTokenURL(getString(properties, str, OAUTH_REQUEST_TOKEN_URL));
        }
        if (notNull(properties, str, OAUTH_AUTHORIZATION_URL)) {
            setOAuthAuthorizationURL(getString(properties, str, OAUTH_AUTHORIZATION_URL));
        }
        if (notNull(properties, str, OAUTH_ACCESS_TOKEN_URL)) {
            setOAuthAccessTokenURL(getString(properties, str, OAUTH_ACCESS_TOKEN_URL));
        }
        if (notNull(properties, str, OAUTH_AUTHENTICATION_URL)) {
            setOAuthAuthenticationURL(getString(properties, str, OAUTH_AUTHENTICATION_URL));
        }
        if (notNull(properties, str, OAUTH2_TOKEN_URL)) {
            setOAuth2TokenURL(getString(properties, str, OAUTH2_TOKEN_URL));
        }
        if (notNull(properties, str, OAUTH2_INVALIDATE_TOKEN_URL)) {
            setOAuth2InvalidateTokenURL(getString(properties, str, OAUTH2_INVALIDATE_TOKEN_URL));
        }
        if (notNull(properties, str, REST_BASE_URL)) {
            setRestBaseURL(getString(properties, str, REST_BASE_URL));
        }
        if (notNull(properties, str, STREAM_BASE_URL)) {
            setStreamBaseURL(getString(properties, str, STREAM_BASE_URL));
        }
        if (notNull(properties, str, USER_STREAM_BASE_URL)) {
            setUserStreamBaseURL(getString(properties, str, USER_STREAM_BASE_URL));
        }
        if (notNull(properties, str, SITE_STREAM_BASE_URL)) {
            setSiteStreamBaseURL(getString(properties, str, SITE_STREAM_BASE_URL));
        }
        if (notNull(properties, str, INCLUDE_MY_RETWEET)) {
            setIncludeMyRetweetEnabled(getBoolean(properties, str, INCLUDE_MY_RETWEET));
        }
        if (notNull(properties, str, INCLUDE_ENTITIES)) {
            setIncludeEntitiesEnabled(getBoolean(properties, str, INCLUDE_ENTITIES));
        }
        if (notNull(properties, str, LOGGER_FACTORY)) {
            setLoggerFactory(getString(properties, str, LOGGER_FACTORY));
        }
        if (notNull(properties, str, JSON_STORE_ENABLED)) {
            setJSONStoreEnabled(getBoolean(properties, str, JSON_STORE_ENABLED));
        }
        if (notNull(properties, str, MBEAN_ENABLED)) {
            setMBeanEnabled(getBoolean(properties, str, MBEAN_ENABLED));
        }
        if (notNull(properties, str, STREAM_USER_REPLIES_ALL)) {
            setUserStreamRepliesAllEnabled(getBoolean(properties, str, STREAM_USER_REPLIES_ALL));
        }
        if (notNull(properties, str, STREAM_USER_WITH_FOLLOWINGS)) {
            setUserStreamWithFollowingsEnabled(getBoolean(properties, str, STREAM_USER_WITH_FOLLOWINGS));
        }
        if (notNull(properties, str, STREAM_STALL_WARNINGS_ENABLED)) {
            setStallWarningsEnabled(getBoolean(properties, str, STREAM_STALL_WARNINGS_ENABLED));
        }
        if (notNull(properties, str, APPLICATION_ONLY_AUTH_ENABLED)) {
            setApplicationOnlyAuthEnabled(getBoolean(properties, str, APPLICATION_ONLY_AUTH_ENABLED));
        }
        if (notNull(properties, str, MEDIA_PROVIDER)) {
            setMediaProvider(getString(properties, str, MEDIA_PROVIDER));
        }
        if (notNull(properties, str, MEDIA_PROVIDER_API_KEY)) {
            setMediaProviderAPIKey(getString(properties, str, MEDIA_PROVIDER_API_KEY));
        }
        if (notNull(properties, str, MEDIA_PROVIDER_PARAMETERS)) {
            String[] strArrSplit = getString(properties, str, MEDIA_PROVIDER_PARAMETERS).split("&");
            Properties properties2 = new Properties();
            for (String str2 : strArrSplit) {
                String[] strArrSplit2 = str2.split("=");
                properties2.setProperty(strArrSplit2[0], strArrSplit2[1]);
            }
            setMediaProviderParameters(properties2);
        }
        cacheInstance();
    }

    private void setFieldsWithTreePath(Properties properties, String str) {
        setFieldsWithPrefix(properties, "");
        String str2 = null;
        for (String str3 : str.split("/")) {
            if (!"".equals(str3)) {
                str2 = str2 == null ? String.valueOf(str3) + "." : String.valueOf(str2) + str3 + ".";
                setFieldsWithPrefix(properties, str2);
            }
        }
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ void dumpConfiguration() {
        super.dumpConfiguration();
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    boolean getBoolean(Properties properties, String str, String str2) {
        return Boolean.valueOf(properties.getProperty(String.valueOf(str) + str2)).booleanValue();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getDispatcherImpl() {
        return super.getDispatcherImpl();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ HttpClientConfiguration getHttpClientConfiguration() {
        return super.getHttpClientConfiguration();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ int getHttpStreamingReadTimeout() {
        return super.getHttpStreamingReadTimeout();
    }

    int getIntProperty(Properties properties, String str, String str2) {
        try {
            return Integer.parseInt(properties.getProperty(String.valueOf(str) + str2));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getLoggerFactory() {
        return super.getLoggerFactory();
    }

    long getLongProperty(Properties properties, String str, String str2) {
        try {
            return Long.parseLong(properties.getProperty(String.valueOf(str) + str2));
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getMediaProvider() {
        return super.getMediaProvider();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getMediaProviderAPIKey() {
        return super.getMediaProviderAPIKey();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ Properties getMediaProviderParameters() {
        return super.getMediaProviderParameters();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public /* bridge */ /* synthetic */ String getOAuth2AccessToken() {
        return super.getOAuth2AccessToken();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuth2InvalidateTokenURL() {
        return super.getOAuth2InvalidateTokenURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuth2Scope() {
        return super.getOAuth2Scope();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public /* bridge */ /* synthetic */ String getOAuth2TokenType() {
        return super.getOAuth2TokenType();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuth2TokenURL() {
        return super.getOAuth2TokenURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public /* bridge */ /* synthetic */ String getOAuthAccessToken() {
        return super.getOAuthAccessToken();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public /* bridge */ /* synthetic */ String getOAuthAccessTokenSecret() {
        return super.getOAuthAccessTokenSecret();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuthAccessTokenURL() {
        return super.getOAuthAccessTokenURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuthAuthenticationURL() {
        return super.getOAuthAuthenticationURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuthAuthorizationURL() {
        return super.getOAuthAuthorizationURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuthRequestTokenURL() {
        return super.getOAuthRequestTokenURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getRestBaseURL() {
        return super.getRestBaseURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getSiteStreamBaseURL() {
        return super.getSiteStreamBaseURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getStreamBaseURL() {
        return super.getStreamBaseURL();
    }

    String getString(Properties properties, String str, String str2) {
        return properties.getProperty(String.valueOf(str) + str2);
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getUploadBaseURL() {
        return super.getUploadBaseURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getUserStreamBaseURL() {
        return super.getUserStreamBaseURL();
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isApplicationOnlyAuthEnabled() {
        return super.isApplicationOnlyAuthEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isDaemonEnabled() {
        return super.isDaemonEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isIncludeEntitiesEnabled() {
        return super.isIncludeEntitiesEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isIncludeMyRetweetEnabled() {
        return super.isIncludeMyRetweetEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isJSONStoreEnabled() {
        return super.isJSONStoreEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isMBeanEnabled() {
        return super.isMBeanEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isStallWarningsEnabled() {
        return super.isStallWarningsEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isTrimUserEnabled() {
        return super.isTrimUserEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isUserStreamRepliesAllEnabled() {
        return super.isUserStreamRepliesAllEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isUserStreamWithFollowingsEnabled() {
        return super.isUserStreamWithFollowingsEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase
    protected Object readResolve() throws ObjectStreamException {
        return super.readResolve();
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ void setIncludeMyRetweetEnabled(boolean z) {
        super.setIncludeMyRetweetEnabled(z);
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ void setTrimUserEnabled(boolean z) {
        super.setTrimUserEnabled(z);
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
