package twitter4j;

import com.facebook.appevents.UserDataStore;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class LocationJSONImpl implements Location {
    private static final long serialVersionUID = -1312752311160422264L;
    private final String countryCode;
    private final String countryName;
    private final String name;
    private final int placeCode;
    private final String placeName;
    private final String url;
    private final int woeid;

    LocationJSONImpl(JSONObject jSONObject) throws TwitterException {
        try {
            this.woeid = ParseUtil.getInt("woeid", jSONObject);
            this.countryName = ParseUtil.getUnescapedString(UserDataStore.COUNTRY, jSONObject);
            this.countryCode = ParseUtil.getRawString("countryCode", jSONObject);
            if (jSONObject.isNull("placeType")) {
                this.placeName = null;
                this.placeCode = -1;
            } else {
                JSONObject jSONObject2 = jSONObject.getJSONObject("placeType");
                this.placeName = ParseUtil.getUnescapedString("name", jSONObject2);
                this.placeCode = ParseUtil.getInt("code", jSONObject2);
            }
            this.name = ParseUtil.getUnescapedString("name", jSONObject);
            this.url = ParseUtil.getUnescapedString("url", jSONObject);
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    static ResponseList<Location> createLocationList(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        return createLocationList(httpResponse.asJSONArray(), configuration.isJSONStoreEnabled());
    }

    static ResponseList<Location> createLocationList(JSONArray jSONArray, boolean z) throws TwitterException {
        try {
            int length = jSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, (HttpResponse) null);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                LocationJSONImpl locationJSONImpl = new LocationJSONImpl(jSONObject);
                responseListImpl.add(locationJSONImpl);
                if (z) {
                    TwitterObjectFactory.registerJSONObject(locationJSONImpl, jSONObject);
                }
            }
            if (z) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArray);
            }
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LocationJSONImpl) && this.woeid == ((LocationJSONImpl) obj).woeid;
    }

    @Override // twitter4j.Location
    public String getCountryCode() {
        return this.countryCode;
    }

    @Override // twitter4j.Location
    public String getCountryName() {
        return this.countryName;
    }

    @Override // twitter4j.Location
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Location
    public int getPlaceCode() {
        return this.placeCode;
    }

    @Override // twitter4j.Location
    public String getPlaceName() {
        return this.placeName;
    }

    @Override // twitter4j.Location
    public String getURL() {
        return this.url;
    }

    @Override // twitter4j.Location
    public int getWoeid() {
        return this.woeid;
    }

    public int hashCode() {
        return this.woeid;
    }

    public String toString() {
        return "LocationJSONImpl{woeid=" + this.woeid + ", countryName='" + this.countryName + "', countryCode='" + this.countryCode + "', placeName='" + this.placeName + "', placeCode='" + this.placeCode + "', name='" + this.name + "', url='" + this.url + "'}";
    }
}
