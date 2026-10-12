package twitter4j.auth;

import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class AuthorizationFactory {
    public static Authorization getInstance(Configuration configuration) {
        Authorization basicAuthorization = null;
        String oAuthConsumerKey = configuration.getOAuthConsumerKey();
        String oAuthConsumerSecret = configuration.getOAuthConsumerSecret();
        if (oAuthConsumerKey == null || oAuthConsumerSecret == null) {
            String user = configuration.getUser();
            String password = configuration.getPassword();
            if (user != null && password != null) {
                basicAuthorization = new BasicAuthorization(user, password);
            }
        } else if (configuration.isApplicationOnlyAuthEnabled()) {
            OAuth2Authorization oAuth2Authorization = new OAuth2Authorization(configuration);
            String oAuth2TokenType = configuration.getOAuth2TokenType();
            String oAuth2AccessToken = configuration.getOAuth2AccessToken();
            if (oAuth2TokenType != null && oAuth2AccessToken != null) {
                oAuth2Authorization.setOAuth2Token(new OAuth2Token(oAuth2TokenType, oAuth2AccessToken));
            }
            basicAuthorization = oAuth2Authorization;
        } else {
            OAuthAuthorization oAuthAuthorization = new OAuthAuthorization(configuration);
            String oAuthAccessToken = configuration.getOAuthAccessToken();
            String oAuthAccessTokenSecret = configuration.getOAuthAccessTokenSecret();
            if (oAuthAccessToken != null && oAuthAccessTokenSecret != null) {
                oAuthAuthorization.setOAuthAccessToken(new AccessToken(oAuthAccessToken, oAuthAccessTokenSecret));
            }
            basicAuthorization = oAuthAuthorization;
        }
        return basicAuthorization == null ? NullAuthorization.getInstance() : basicAuthorization;
    }
}
