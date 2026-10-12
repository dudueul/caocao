package twitter4j;

import com.tapjoy.TJAdUnitConstants;
import java.io.Serializable;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class OEmbedJSONImpl extends TwitterResponseImpl implements OEmbed, Serializable {
    private static final long serialVersionUID = -2207801480251709819L;
    private String authorName;
    private String authorURL;
    private long cacheAge;
    private String html;
    private String url;
    private String version;
    private int width;

    OEmbedJSONImpl(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        super(httpResponse);
        JSONObject jSONObjectAsJSONObject = httpResponse.asJSONObject();
        init(jSONObjectAsJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, jSONObjectAsJSONObject);
        }
    }

    OEmbedJSONImpl(JSONObject jSONObject) throws TwitterException {
        init(jSONObject);
    }

    private void init(JSONObject jSONObject) throws TwitterException {
        try {
            this.html = jSONObject.getString(TJAdUnitConstants.String.HTML);
            this.authorName = jSONObject.getString("author_name");
            this.url = jSONObject.getString("url");
            this.version = jSONObject.getString("version");
            this.cacheAge = jSONObject.getLong("cache_age");
            this.authorURL = jSONObject.getString("author_url");
            this.width = jSONObject.getInt("width");
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OEmbedJSONImpl oEmbedJSONImpl = (OEmbedJSONImpl) obj;
        if (this.cacheAge == oEmbedJSONImpl.cacheAge && this.width == oEmbedJSONImpl.width) {
            if (this.authorName == null ? oEmbedJSONImpl.authorName != null : !this.authorName.equals(oEmbedJSONImpl.authorName)) {
                return false;
            }
            if (this.authorURL == null ? oEmbedJSONImpl.authorURL != null : !this.authorURL.equals(oEmbedJSONImpl.authorURL)) {
                return false;
            }
            if (this.html == null ? oEmbedJSONImpl.html != null : !this.html.equals(oEmbedJSONImpl.html)) {
                return false;
            }
            if (this.url == null ? oEmbedJSONImpl.url != null : !this.url.equals(oEmbedJSONImpl.url)) {
                return false;
            }
            if (this.version != null) {
                if (this.version.equals(oEmbedJSONImpl.version)) {
                    return true;
                }
            } else if (oEmbedJSONImpl.version == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // twitter4j.TwitterResponseImpl, twitter4j.TwitterResponse
    public /* bridge */ /* synthetic */ int getAccessLevel() {
        return super.getAccessLevel();
    }

    @Override // twitter4j.OEmbed
    public String getAuthorName() {
        return this.authorName;
    }

    @Override // twitter4j.OEmbed
    public String getAuthorURL() {
        return this.authorURL;
    }

    @Override // twitter4j.OEmbed
    public long getCacheAge() {
        return this.cacheAge;
    }

    @Override // twitter4j.OEmbed
    public String getHtml() {
        return this.html;
    }

    @Override // twitter4j.TwitterResponseImpl, twitter4j.TwitterResponse
    public /* bridge */ /* synthetic */ RateLimitStatus getRateLimitStatus() {
        return super.getRateLimitStatus();
    }

    @Override // twitter4j.OEmbed
    public String getURL() {
        return this.url;
    }

    @Override // twitter4j.OEmbed
    public String getVersion() {
        return this.version;
    }

    @Override // twitter4j.OEmbed
    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return ((((((((((((this.html != null ? this.html.hashCode() : 0) * 31) + (this.authorName != null ? this.authorName.hashCode() : 0)) * 31) + (this.url != null ? this.url.hashCode() : 0)) * 31) + (this.version != null ? this.version.hashCode() : 0)) * 31) + ((int) (this.cacheAge ^ (this.cacheAge >>> 32)))) * 31) + (this.authorURL != null ? this.authorURL.hashCode() : 0)) * 31) + this.width;
    }

    public String toString() {
        return "OEmbedJSONImpl{html='" + this.html + "', authorName='" + this.authorName + "', url='" + this.url + "', version='" + this.version + "', cacheAge=" + this.cacheAge + ", authorURL='" + this.authorURL + "', width=" + this.width + '}';
    }
}
