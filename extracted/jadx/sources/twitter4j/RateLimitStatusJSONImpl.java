package twitter4j;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class RateLimitStatusJSONImpl implements RateLimitStatus, Serializable {
    private static final long serialVersionUID = 7790337632915862445L;
    private int limit;
    private int remaining;
    private int resetTimeInSeconds;
    private int secondsUntilReset;

    private RateLimitStatusJSONImpl(int i, int i2, int i3) {
        this.limit = i;
        this.remaining = i2;
        this.resetTimeInSeconds = i3;
        this.secondsUntilReset = (int) (((((long) i3) * 1000) - System.currentTimeMillis()) / 1000);
    }

    RateLimitStatusJSONImpl(JSONObject jSONObject) throws TwitterException {
        init(jSONObject);
    }

    static RateLimitStatus createFromResponseHeader(HttpResponse httpResponse) {
        String responseHeader;
        if (httpResponse == null || (responseHeader = httpResponse.getResponseHeader("X-Rate-Limit-Limit")) == null) {
            return null;
        }
        int i = Integer.parseInt(responseHeader);
        String responseHeader2 = httpResponse.getResponseHeader("X-Rate-Limit-Remaining");
        if (responseHeader2 == null) {
            return null;
        }
        int i2 = Integer.parseInt(responseHeader2);
        String responseHeader3 = httpResponse.getResponseHeader("X-Rate-Limit-Reset");
        if (responseHeader3 != null) {
            return new RateLimitStatusJSONImpl(i, i2, (int) Long.parseLong(responseHeader3));
        }
        return null;
    }

    static Map<String, RateLimitStatus> createRateLimitStatuses(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        JSONObject jSONObjectAsJSONObject = httpResponse.asJSONObject();
        Map<String, RateLimitStatus> mapCreateRateLimitStatuses = createRateLimitStatuses(jSONObjectAsJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(mapCreateRateLimitStatuses, jSONObjectAsJSONObject);
        }
        return mapCreateRateLimitStatuses;
    }

    static Map<String, RateLimitStatus> createRateLimitStatuses(JSONObject jSONObject) throws TwitterException {
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("resources");
            Iterator itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject((String) itKeys.next());
                Iterator itKeys2 = jSONObject3.keys();
                while (itKeys2.hasNext()) {
                    String str = (String) itKeys2.next();
                    map.put(str, new RateLimitStatusJSONImpl(jSONObject3.getJSONObject(str)));
                }
            }
            return Collections.unmodifiableMap(map);
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
        RateLimitStatusJSONImpl rateLimitStatusJSONImpl = (RateLimitStatusJSONImpl) obj;
        return this.limit == rateLimitStatusJSONImpl.limit && this.remaining == rateLimitStatusJSONImpl.remaining && this.resetTimeInSeconds == rateLimitStatusJSONImpl.resetTimeInSeconds && this.secondsUntilReset == rateLimitStatusJSONImpl.secondsUntilReset;
    }

    @Override // twitter4j.RateLimitStatus
    public int getLimit() {
        return this.limit;
    }

    @Override // twitter4j.RateLimitStatus
    public int getRemaining() {
        return this.remaining;
    }

    @Override // twitter4j.RateLimitStatus
    public int getResetTimeInSeconds() {
        return this.resetTimeInSeconds;
    }

    @Override // twitter4j.RateLimitStatus
    public int getSecondsUntilReset() {
        return this.secondsUntilReset;
    }

    public int hashCode() {
        return (((((this.remaining * 31) + this.limit) * 31) + this.resetTimeInSeconds) * 31) + this.secondsUntilReset;
    }

    void init(JSONObject jSONObject) throws TwitterException {
        this.limit = ParseUtil.getInt("limit", jSONObject);
        this.remaining = ParseUtil.getInt("remaining", jSONObject);
        this.resetTimeInSeconds = ParseUtil.getInt("reset", jSONObject);
        this.secondsUntilReset = (int) (((((long) this.resetTimeInSeconds) * 1000) - System.currentTimeMillis()) / 1000);
    }

    public String toString() {
        return "RateLimitStatusJSONImpl{remaining=" + this.remaining + ", limit=" + this.limit + ", resetTimeInSeconds=" + this.resetTimeInSeconds + ", secondsUntilReset=" + this.secondsUntilReset + '}';
    }
}
