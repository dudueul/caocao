package twitter4j.auth;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import twitter4j.BASE64Encoder;
import twitter4j.HttpClient;
import twitter4j.HttpClientFactory;
import twitter4j.HttpParameter;
import twitter4j.HttpRequest;
import twitter4j.HttpResponse;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class OAuth2Authorization implements Authorization, Serializable, OAuth2Support {
    private static final long serialVersionUID = -2895232598422218647L;
    private final Configuration conf;
    private String consumerKey;
    private String consumerSecret;
    private final HttpClient http;
    private OAuth2Token token;

    public OAuth2Authorization(Configuration configuration) {
        this.conf = configuration;
        setOAuthConsumer(configuration.getOAuthConsumerKey(), configuration.getOAuthConsumerSecret());
        this.http = HttpClientFactory.getInstance(configuration.getHttpClientConfiguration());
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof OAuth2Authorization)) {
            return false;
        }
        OAuth2Authorization oAuth2Authorization = (OAuth2Authorization) obj;
        if (this.consumerKey != null) {
            if (!this.consumerKey.equals(oAuth2Authorization.consumerKey)) {
                return false;
            }
        } else if (oAuth2Authorization.consumerKey != null) {
            return false;
        }
        if (this.consumerSecret != null) {
            if (!this.consumerSecret.equals(oAuth2Authorization.consumerSecret)) {
                return false;
            }
        } else if (oAuth2Authorization.consumerSecret != null) {
            return false;
        }
        if (this.token != null) {
            if (!this.token.equals(oAuth2Authorization.token)) {
                return false;
            }
        } else if (oAuth2Authorization.token != null) {
            return false;
        }
        return true;
    }

    @Override // twitter4j.auth.Authorization
    public String getAuthorizationHeader(HttpRequest httpRequest) {
        if (this.token != null) {
            return this.token.generateAuthorizationHeader();
        }
        String str = "";
        try {
            str = String.valueOf(URLEncoder.encode(this.consumerKey, "UTF-8")) + ":" + URLEncoder.encode(this.consumerSecret, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return "Basic " + BASE64Encoder.encode(str.getBytes());
    }

    @Override // twitter4j.auth.OAuth2Support
    public OAuth2Token getOAuth2Token() throws TwitterException {
        if (this.token != null) {
            throw new IllegalStateException("OAuth 2 Bearer Token is already available.");
        }
        HttpParameter[] httpParameterArr = new HttpParameter[this.conf.getOAuth2Scope() == null ? 1 : 2];
        httpParameterArr[0] = new HttpParameter("grant_type", "client_credentials");
        if (this.conf.getOAuth2Scope() != null) {
            httpParameterArr[1] = new HttpParameter("scope", this.conf.getOAuth2Scope());
        }
        HttpResponse httpResponsePost = this.http.post(this.conf.getOAuth2TokenURL(), httpParameterArr, this, null);
        if (httpResponsePost.getStatusCode() != 200) {
            throw new TwitterException("Obtaining OAuth 2 Bearer Token failed.", httpResponsePost);
        }
        this.token = new OAuth2Token(httpResponsePost);
        return this.token;
    }

    public int hashCode() {
        return ((((this.consumerKey != null ? this.consumerKey.hashCode() : 0) * 31) + (this.consumerSecret != null ? this.consumerSecret.hashCode() : 0)) * 31) + (this.token != null ? this.token.hashCode() : 0);
    }

    @Override // twitter4j.auth.OAuth2Support
    public void invalidateOAuth2Token() throws TwitterException {
        if (this.token == null) {
            throw new IllegalStateException("OAuth 2 Bearer Token is not available.");
        }
        HttpParameter[] httpParameterArr = {new HttpParameter("access_token", this.token.getAccessToken())};
        OAuth2Token oAuth2Token = this.token;
        boolean z = false;
        try {
            this.token = null;
            HttpResponse httpResponsePost = this.http.post(this.conf.getOAuth2InvalidateTokenURL(), httpParameterArr, this, null);
            if (httpResponsePost.getStatusCode() != 200) {
                throw new TwitterException("Invalidating OAuth 2 Bearer Token failed.", httpResponsePost);
            }
            z = true;
        } finally {
            if (!z) {
                this.token = oAuth2Token;
            }
        }
    }

    @Override // twitter4j.auth.Authorization
    public boolean isEnabled() {
        return this.token != null;
    }

    @Override // twitter4j.auth.OAuth2Support
    public void setOAuth2Token(OAuth2Token oAuth2Token) {
        this.token = oAuth2Token;
    }

    @Override // twitter4j.auth.OAuth2Support
    public void setOAuthConsumer(String str, String str2) {
        if (str == null) {
            str = "";
        }
        this.consumerKey = str;
        if (str2 == null) {
            str2 = "";
        }
        this.consumerSecret = str2;
    }

    public String toString() {
        return "OAuth2Authorization{consumerKey='" + this.consumerKey + "', consumerSecret='******************************************', token=" + (this.token == null ? "null" : this.token.toString()) + '}';
    }
}
