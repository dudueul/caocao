package twitter4j.conf;

import java.io.Serializable;
import java.util.Properties;
import twitter4j.HttpClientConfiguration;
import twitter4j.auth.AuthorizationConfiguration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface Configuration extends AuthorizationConfiguration, Serializable {
    int getAsyncNumThreads();

    long getContributingTo();

    String getDispatcherImpl();

    HttpClientConfiguration getHttpClientConfiguration();

    int getHttpStreamingReadTimeout();

    String getLoggerFactory();

    String getMediaProvider();

    String getMediaProviderAPIKey();

    Properties getMediaProviderParameters();

    @Override // twitter4j.auth.AuthorizationConfiguration
    String getOAuth2AccessToken();

    String getOAuth2InvalidateTokenURL();

    String getOAuth2Scope();

    @Override // twitter4j.auth.AuthorizationConfiguration
    String getOAuth2TokenType();

    String getOAuth2TokenURL();

    @Override // twitter4j.auth.AuthorizationConfiguration
    String getOAuthAccessToken();

    @Override // twitter4j.auth.AuthorizationConfiguration
    String getOAuthAccessTokenSecret();

    String getOAuthAccessTokenURL();

    String getOAuthAuthenticationURL();

    String getOAuthAuthorizationURL();

    @Override // twitter4j.auth.AuthorizationConfiguration
    String getOAuthConsumerKey();

    @Override // twitter4j.auth.AuthorizationConfiguration
    String getOAuthConsumerSecret();

    String getOAuthRequestTokenURL();

    @Override // twitter4j.auth.AuthorizationConfiguration
    String getPassword();

    String getRestBaseURL();

    String getSiteStreamBaseURL();

    String getStreamBaseURL();

    String getUploadBaseURL();

    @Override // twitter4j.auth.AuthorizationConfiguration
    String getUser();

    String getUserStreamBaseURL();

    boolean isApplicationOnlyAuthEnabled();

    boolean isDaemonEnabled();

    boolean isDebugEnabled();

    boolean isIncludeEntitiesEnabled();

    boolean isIncludeMyRetweetEnabled();

    boolean isJSONStoreEnabled();

    boolean isMBeanEnabled();

    boolean isStallWarningsEnabled();

    boolean isTrimUserEnabled();

    boolean isUserStreamRepliesAllEnabled();

    boolean isUserStreamWithFollowingsEnabled();
}
