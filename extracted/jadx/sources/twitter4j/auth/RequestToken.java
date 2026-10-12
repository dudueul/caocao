package twitter4j.auth;

import java.io.Serializable;
import twitter4j.HttpResponse;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class RequestToken extends OAuthToken implements Serializable {
    private static final long serialVersionUID = -8806439091674811734L;
    private final Configuration conf;
    private OAuthSupport oauth;

    public RequestToken(String str, String str2) {
        super(str, str2);
        this.conf = ConfigurationContext.getInstance();
    }

    RequestToken(String str, String str2, OAuthSupport oAuthSupport) {
        super(str, str2);
        this.conf = ConfigurationContext.getInstance();
        this.oauth = oAuthSupport;
    }

    RequestToken(HttpResponse httpResponse, OAuthSupport oAuthSupport) throws TwitterException {
        super(httpResponse);
        this.conf = ConfigurationContext.getInstance();
        this.oauth = oAuthSupport;
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getAuthenticationURL() {
        return String.valueOf(this.conf.getOAuthAuthenticationURL()) + "?oauth_token=" + getToken();
    }

    public String getAuthorizationURL() {
        return String.valueOf(this.conf.getOAuthAuthorizationURL()) + "?oauth_token=" + getToken();
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ String getParameter(String str) {
        return super.getParameter(str);
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ String getToken() {
        return super.getToken();
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ String getTokenSecret() {
        return super.getTokenSecret();
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
