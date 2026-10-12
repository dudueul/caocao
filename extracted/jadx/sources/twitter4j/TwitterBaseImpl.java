package twitter4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.AuthorizationFactory;
import twitter4j.auth.BasicAuthorization;
import twitter4j.auth.NullAuthorization;
import twitter4j.auth.OAuth2Authorization;
import twitter4j.auth.OAuth2Support;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.OAuthSupport;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
abstract class TwitterBaseImpl implements TwitterBase, Serializable, OAuthSupport, OAuth2Support, HttpResponseListener {
    private static final String WWW_DETAILS = "See http://twitter4j.org/en/configuration.html for details";
    private static final long serialVersionUID = -7824361938865528554L;
    Authorization auth;
    Configuration conf;
    ObjectFactory factory;
    transient HttpClient http;
    private transient String screenName = null;
    private transient long id = 0;
    private List<RateLimitStatusListener> rateLimitStatusListeners = new ArrayList(0);

    TwitterBaseImpl(Configuration configuration, Authorization authorization) {
        this.conf = configuration;
        this.auth = authorization;
        init();
    }

    private OAuthSupport getOAuth() {
        if (this.auth instanceof OAuthSupport) {
            return (OAuthSupport) this.auth;
        }
        throw new IllegalStateException("OAuth consumer key/secret combination not supplied");
    }

    private OAuth2Support getOAuth2() {
        if (this.auth instanceof OAuth2Support) {
            return (OAuth2Support) this.auth;
        }
        throw new IllegalStateException("OAuth consumer key/secret combination not supplied");
    }

    private void init() {
        if (this.auth == null) {
            String oAuthConsumerKey = this.conf.getOAuthConsumerKey();
            String oAuthConsumerSecret = this.conf.getOAuthConsumerSecret();
            if (oAuthConsumerKey == null || oAuthConsumerSecret == null) {
                this.auth = NullAuthorization.getInstance();
            } else if (this.conf.isApplicationOnlyAuthEnabled()) {
                OAuth2Authorization oAuth2Authorization = new OAuth2Authorization(this.conf);
                String oAuth2TokenType = this.conf.getOAuth2TokenType();
                String oAuth2AccessToken = this.conf.getOAuth2AccessToken();
                if (oAuth2TokenType != null && oAuth2AccessToken != null) {
                    oAuth2Authorization.setOAuth2Token(new OAuth2Token(oAuth2TokenType, oAuth2AccessToken));
                }
                this.auth = oAuth2Authorization;
            } else {
                OAuthAuthorization oAuthAuthorization = new OAuthAuthorization(this.conf);
                String oAuthAccessToken = this.conf.getOAuthAccessToken();
                String oAuthAccessTokenSecret = this.conf.getOAuthAccessTokenSecret();
                if (oAuthAccessToken != null && oAuthAccessTokenSecret != null) {
                    oAuthAuthorization.setOAuthAccessToken(new AccessToken(oAuthAccessToken, oAuthAccessTokenSecret));
                }
                this.auth = oAuthAuthorization;
            }
        }
        this.http = HttpClientFactory.getInstance(this.conf.getHttpClientConfiguration());
        setFactory();
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.readFields();
        this.conf = (Configuration) objectInputStream.readObject();
        this.auth = (Authorization) objectInputStream.readObject();
        this.rateLimitStatusListeners = (List) objectInputStream.readObject();
        this.http = HttpClientFactory.getInstance(this.conf.getHttpClientConfiguration());
        setFactory();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.putFields();
        objectOutputStream.writeFields();
        objectOutputStream.writeObject(this.conf);
        objectOutputStream.writeObject(this.auth);
        ArrayList arrayList = new ArrayList(0);
        for (RateLimitStatusListener rateLimitStatusListener : this.rateLimitStatusListeners) {
            if (rateLimitStatusListener instanceof Serializable) {
                arrayList.add(rateLimitStatusListener);
            }
        }
        objectOutputStream.writeObject(arrayList);
    }

    @Override // twitter4j.TwitterBase
    public void addRateLimitStatusListener(RateLimitStatusListener rateLimitStatusListener) {
        this.rateLimitStatusListeners.add(rateLimitStatusListener);
    }

    final void ensureAuthorizationEnabled() {
        if (!this.auth.isEnabled()) {
            throw new IllegalStateException("Authentication credentials are missing. See http://twitter4j.org/en/configuration.html for details");
        }
    }

    final void ensureOAuthEnabled() {
        if (!(this.auth instanceof OAuthAuthorization)) {
            throw new IllegalStateException("OAuth required. Authentication credentials are missing. See http://twitter4j.org/en/configuration.html for details");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TwitterBaseImpl)) {
            return false;
        }
        TwitterBaseImpl twitterBaseImpl = (TwitterBaseImpl) obj;
        if (this.auth == null ? twitterBaseImpl.auth != null : !this.auth.equals(twitterBaseImpl.auth)) {
            return false;
        }
        if (!this.conf.equals(twitterBaseImpl.conf)) {
            return false;
        }
        if (this.http == null ? twitterBaseImpl.http != null : !this.http.equals(twitterBaseImpl.http)) {
            return false;
        }
        return this.rateLimitStatusListeners.equals(twitterBaseImpl.rateLimitStatusListeners);
    }

    User fillInIDAndScreenName() throws TwitterException {
        ensureAuthorizationEnabled();
        UserJSONImpl userJSONImpl = new UserJSONImpl(this.http.get(String.valueOf(this.conf.getRestBaseURL()) + "account/verify_credentials.json", null, this.auth, this), this.conf);
        this.screenName = userJSONImpl.getScreenName();
        this.id = userJSONImpl.getId();
        return userJSONImpl;
    }

    @Override // twitter4j.TwitterBase
    public final Authorization getAuthorization() {
        return this.auth;
    }

    @Override // twitter4j.TwitterBase
    public Configuration getConfiguration() {
        return this.conf;
    }

    @Override // twitter4j.TwitterBase
    public long getId() throws IllegalStateException, TwitterException {
        if (!this.auth.isEnabled()) {
            throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
        }
        if (0 == this.id) {
            fillInIDAndScreenName();
        }
        return this.id;
    }

    @Override // twitter4j.auth.OAuth2Support
    public synchronized OAuth2Token getOAuth2Token() throws TwitterException {
        return getOAuth2().getOAuth2Token();
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken() throws TwitterException {
        AccessToken oAuthAccessToken;
        Authorization authorization = getAuthorization();
        if (authorization instanceof BasicAuthorization) {
            BasicAuthorization basicAuthorization = (BasicAuthorization) authorization;
            Authorization authorizationFactory = AuthorizationFactory.getInstance(this.conf);
            if (!(authorizationFactory instanceof OAuthAuthorization)) {
                throw new IllegalStateException("consumer key / secret combination not supplied.");
            }
            this.auth = authorizationFactory;
            oAuthAccessToken = ((OAuthAuthorization) authorizationFactory).getOAuthAccessToken(basicAuthorization.getUserId(), basicAuthorization.getPassword());
        } else if (authorization instanceof XAuthAuthorization) {
            XAuthAuthorization xAuthAuthorization = (XAuthAuthorization) authorization;
            this.auth = xAuthAuthorization;
            OAuthAuthorization oAuthAuthorization = new OAuthAuthorization(this.conf);
            oAuthAuthorization.setOAuthConsumer(xAuthAuthorization.getConsumerKey(), xAuthAuthorization.getConsumerSecret());
            oAuthAccessToken = oAuthAuthorization.getOAuthAccessToken(xAuthAuthorization.getUserId(), xAuthAuthorization.getPassword());
        } else {
            oAuthAccessToken = getOAuth().getOAuthAccessToken();
        }
        this.screenName = oAuthAccessToken.getScreenName();
        this.id = oAuthAccessToken.getUserId();
        return oAuthAccessToken;
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken(String str) throws TwitterException {
        AccessToken oAuthAccessToken;
        oAuthAccessToken = getOAuth().getOAuthAccessToken(str);
        this.screenName = oAuthAccessToken.getScreenName();
        return oAuthAccessToken;
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken(String str, String str2) throws TwitterException {
        return getOAuth().getOAuthAccessToken(str, str2);
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken(RequestToken requestToken) throws TwitterException {
        AccessToken oAuthAccessToken;
        oAuthAccessToken = getOAuth().getOAuthAccessToken(requestToken);
        this.screenName = oAuthAccessToken.getScreenName();
        return oAuthAccessToken;
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken(RequestToken requestToken, String str) throws TwitterException {
        return getOAuth().getOAuthAccessToken(requestToken, str);
    }

    @Override // twitter4j.auth.OAuthSupport
    public RequestToken getOAuthRequestToken() throws TwitterException {
        return getOAuthRequestToken(null);
    }

    @Override // twitter4j.auth.OAuthSupport
    public RequestToken getOAuthRequestToken(String str) throws TwitterException {
        return getOAuth().getOAuthRequestToken(str);
    }

    @Override // twitter4j.auth.OAuthSupport
    public RequestToken getOAuthRequestToken(String str, String str2) throws TwitterException {
        return getOAuth().getOAuthRequestToken(str, str2);
    }

    @Override // twitter4j.auth.OAuthSupport
    public RequestToken getOAuthRequestToken(String str, String str2, String str3) throws TwitterException {
        return getOAuth().getOAuthRequestToken(str, str2, str3);
    }

    @Override // twitter4j.TwitterBase
    public String getScreenName() throws IllegalStateException, TwitterException {
        if (!this.auth.isEnabled()) {
            throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
        }
        if (this.screenName == null) {
            if (this.auth instanceof BasicAuthorization) {
                this.screenName = ((BasicAuthorization) this.auth).getUserId();
                if (this.screenName.contains("@")) {
                    this.screenName = null;
                }
            }
            if (this.screenName == null) {
                fillInIDAndScreenName();
            }
        }
        return this.screenName;
    }

    public int hashCode() {
        return (((((this.conf.hashCode() * 31) + (this.http != null ? this.http.hashCode() : 0)) * 31) + this.rateLimitStatusListeners.hashCode()) * 31) + (this.auth != null ? this.auth.hashCode() : 0);
    }

    @Override // twitter4j.HttpResponseListener
    public void httpResponseReceived(HttpResponseEvent httpResponseEvent) {
        RateLimitStatus rateLimitStatusCreateRateLimitStatusFromResponseHeader;
        int statusCode;
        if (this.rateLimitStatusListeners.size() != 0) {
            HttpResponse response = httpResponseEvent.getResponse();
            TwitterException twitterException = httpResponseEvent.getTwitterException();
            if (twitterException != null) {
                rateLimitStatusCreateRateLimitStatusFromResponseHeader = twitterException.getRateLimitStatus();
                statusCode = twitterException.getStatusCode();
            } else {
                rateLimitStatusCreateRateLimitStatusFromResponseHeader = JSONImplFactory.createRateLimitStatusFromResponseHeader(response);
                statusCode = response.getStatusCode();
            }
            if (rateLimitStatusCreateRateLimitStatusFromResponseHeader != null) {
                RateLimitStatusEvent rateLimitStatusEvent = new RateLimitStatusEvent(this, rateLimitStatusCreateRateLimitStatusFromResponseHeader, httpResponseEvent.isAuthenticated());
                if (statusCode == 420 || statusCode == 503 || statusCode == 429) {
                    for (RateLimitStatusListener rateLimitStatusListener : this.rateLimitStatusListeners) {
                        rateLimitStatusListener.onRateLimitStatus(rateLimitStatusEvent);
                        rateLimitStatusListener.onRateLimitReached(rateLimitStatusEvent);
                    }
                } else {
                    Iterator<RateLimitStatusListener> it = this.rateLimitStatusListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onRateLimitStatus(rateLimitStatusEvent);
                    }
                }
            }
        }
    }

    @Override // twitter4j.auth.OAuth2Support
    public synchronized void invalidateOAuth2Token() throws TwitterException {
        getOAuth2().invalidateOAuth2Token();
    }

    void setFactory() {
        this.factory = new JSONImplFactory(this.conf);
    }

    @Override // twitter4j.auth.OAuth2Support
    public void setOAuth2Token(OAuth2Token oAuth2Token) {
        getOAuth2().setOAuth2Token(oAuth2Token);
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized void setOAuthAccessToken(AccessToken accessToken) {
        getOAuth().setOAuthAccessToken(accessToken);
    }

    @Override // twitter4j.auth.OAuthSupport, twitter4j.auth.OAuth2Support
    public synchronized void setOAuthConsumer(String str, String str2) {
        try {
            if (str == null) {
                throw new NullPointerException("consumer key is null");
            }
            if (str2 == null) {
                throw new NullPointerException("consumer secret is null");
            }
            if (this.auth instanceof NullAuthorization) {
                if (this.conf.isApplicationOnlyAuthEnabled()) {
                    OAuth2Authorization oAuth2Authorization = new OAuth2Authorization(this.conf);
                    oAuth2Authorization.setOAuthConsumer(str, str2);
                    this.auth = oAuth2Authorization;
                } else {
                    OAuthAuthorization oAuthAuthorization = new OAuthAuthorization(this.conf);
                    oAuthAuthorization.setOAuthConsumer(str, str2);
                    this.auth = oAuthAuthorization;
                }
            } else if (this.auth instanceof BasicAuthorization) {
                XAuthAuthorization xAuthAuthorization = new XAuthAuthorization((BasicAuthorization) this.auth);
                xAuthAuthorization.setOAuthConsumer(str, str2);
                this.auth = xAuthAuthorization;
            } else if ((this.auth instanceof OAuthAuthorization) || (this.auth instanceof OAuth2Authorization)) {
                throw new IllegalStateException("consumer key/secret pair already set.");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public String toString() {
        return "TwitterBase{conf=" + this.conf + ", http=" + this.http + ", rateLimitStatusListeners=" + this.rateLimitStatusListeners + ", auth=" + this.auth + '}';
    }
}
