package twitter4j;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class TrendsJSONImpl extends TwitterResponseImpl implements Trends, Serializable {
    private static final long serialVersionUID = 2054973282133379835L;
    private Date asOf;
    private Location location;
    private Date trendAt;
    private Trend[] trends;

    TrendsJSONImpl(String str) throws TwitterException {
        this(str, false);
    }

    TrendsJSONImpl(String str, boolean z) throws TwitterException {
        init(str, z);
    }

    TrendsJSONImpl(Date date, Location location, Date date2, Trend[] trendArr) {
        this.asOf = date;
        this.location = location;
        this.trendAt = date2;
        this.trends = trendArr;
    }

    TrendsJSONImpl(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        super(httpResponse);
        init(httpResponse.asString(), configuration.isJSONStoreEnabled());
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, httpResponse.asString());
        }
    }

    static ResponseList<Trends> createTrendsList(HttpResponse httpResponse, boolean z) throws TwitterException {
        JSONObject jSONObjectAsJSONObject = httpResponse.asJSONObject();
        try {
            Date trendsDate = ParseUtil.parseTrendsDate(jSONObjectAsJSONObject.getString("as_of"));
            JSONObject jSONObject = jSONObjectAsJSONObject.getJSONObject("trends");
            Location locationExtractLocation = extractLocation(jSONObjectAsJSONObject, z);
            ResponseListImpl responseListImpl = new ResponseListImpl(jSONObject.length(), httpResponse);
            Iterator itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String str = (String) itKeys.next();
                Trend[] trendArrJsonArrayToTrendArray = jsonArrayToTrendArray(jSONObject.getJSONArray(str), z);
                if (str.length() == 19) {
                    responseListImpl.add(new TrendsJSONImpl(trendsDate, locationExtractLocation, ParseUtil.getDate(str, "yyyy-MM-dd HH:mm:ss"), trendArrJsonArrayToTrendArray));
                } else if (str.length() == 16) {
                    responseListImpl.add(new TrendsJSONImpl(trendsDate, locationExtractLocation, ParseUtil.getDate(str, "yyyy-MM-dd HH:mm"), trendArrJsonArrayToTrendArray));
                } else if (str.length() == 10) {
                    responseListImpl.add(new TrendsJSONImpl(trendsDate, locationExtractLocation, ParseUtil.getDate(str, "yyyy-MM-dd"), trendArrJsonArrayToTrendArray));
                }
            }
            Collections.sort(responseListImpl);
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(String.valueOf(e.getMessage()) + ":" + httpResponse.asString(), e);
        }
    }

    private static Location extractLocation(JSONObject jSONObject, boolean z) throws TwitterException {
        if (jSONObject.isNull("locations")) {
            return null;
        }
        try {
            ResponseList<Location> responseListCreateLocationList = LocationJSONImpl.createLocationList(jSONObject.getJSONArray("locations"), z);
            if (responseListCreateLocationList.size() != 0) {
                return responseListCreateLocationList.get(0);
            }
            return null;
        } catch (JSONException e) {
            throw new AssertionError("locations can't be null");
        }
    }

    private static Trend[] jsonArrayToTrendArray(JSONArray jSONArray, boolean z) throws JSONException {
        Trend[] trendArr = new Trend[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            trendArr[i] = new TrendJSONImpl(jSONArray.getJSONObject(i), z);
        }
        return trendArr;
    }

    @Override // java.lang.Comparable
    public int compareTo(Trends trends) {
        return this.trendAt.compareTo(trends.getTrendAt());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trends)) {
            return false;
        }
        Trends trends = (Trends) obj;
        if (this.asOf == null ? trends.getAsOf() != null : !this.asOf.equals(trends.getAsOf())) {
            return false;
        }
        if (this.trendAt == null ? trends.getTrendAt() != null : !this.trendAt.equals(trends.getTrendAt())) {
            return false;
        }
        return Arrays.equals(this.trends, trends.getTrends());
    }

    @Override // twitter4j.Trends
    public Date getAsOf() {
        return this.asOf;
    }

    @Override // twitter4j.Trends
    public Location getLocation() {
        return this.location;
    }

    @Override // twitter4j.Trends
    public Date getTrendAt() {
        return this.trendAt;
    }

    @Override // twitter4j.Trends
    public Trend[] getTrends() {
        return this.trends;
    }

    public int hashCode() {
        return ((((this.asOf != null ? this.asOf.hashCode() : 0) * 31) + (this.trendAt != null ? this.trendAt.hashCode() : 0)) * 31) + (this.trends != null ? Arrays.hashCode(this.trends) : 0);
    }

    void init(String str, boolean z) throws TwitterException {
        JSONObject jSONObject;
        try {
            if (str.startsWith("[")) {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() <= 0) {
                    throw new TwitterException("No trends found on the specified woeid");
                }
                jSONObject = jSONArray.getJSONObject(0);
            } else {
                jSONObject = new JSONObject(str);
            }
            this.asOf = ParseUtil.parseTrendsDate(jSONObject.getString("as_of"));
            this.location = extractLocation(jSONObject, z);
            JSONArray jSONArray2 = jSONObject.getJSONArray("trends");
            this.trendAt = this.asOf;
            this.trends = jsonArrayToTrendArray(jSONArray2, z);
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage(), e);
        }
    }

    public String toString() {
        return "TrendsJSONImpl{asOf=" + this.asOf + ", trendAt=" + this.trendAt + ", trends=" + (this.trends == null ? null : Arrays.asList(this.trends)) + '}';
    }
}
