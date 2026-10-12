package twitter4j;

import com.facebook.appevents.UserDataStore;
import com.naver.glink.android.sdk.login.neoid.PlugSchemeActivity;
import com.tapjoy.TapjoyConstants;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class PlaceJSONImpl extends TwitterResponseImpl implements Place, Serializable {
    private static final long serialVersionUID = -6368276880878829754L;
    private GeoLocation[][] boundingBoxCoordinates;
    private String boundingBoxType;
    private Place[] containedWithIn;
    private String country;
    private String countryCode;
    private String fullName;
    private GeoLocation[][] geometryCoordinates;
    private String geometryType;
    private String id;
    private String name;
    private String placeType;
    private String streetAddress;
    private String url;

    PlaceJSONImpl() {
    }

    PlaceJSONImpl(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        super(httpResponse);
        JSONObject jSONObjectAsJSONObject = httpResponse.asJSONObject();
        init(jSONObjectAsJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, jSONObjectAsJSONObject);
        }
    }

    PlaceJSONImpl(JSONObject jSONObject) throws TwitterException {
        init(jSONObject);
    }

    static ResponseList<Place> createPlaceList(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        JSONObject jSONObjectAsJSONObject = null;
        try {
            jSONObjectAsJSONObject = httpResponse.asJSONObject();
            return createPlaceList(jSONObjectAsJSONObject.getJSONObject(PlugSchemeActivity.b).getJSONArray("places"), httpResponse, configuration);
        } catch (JSONException e) {
            throw new TwitterException(String.valueOf(e.getMessage()) + ":" + jSONObjectAsJSONObject.toString(), e);
        }
    }

    static ResponseList<Place> createPlaceList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        try {
            int length = jSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                PlaceJSONImpl placeJSONImpl = new PlaceJSONImpl(jSONObject);
                responseListImpl.add(placeJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(placeJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArray);
            }
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    private void init(JSONObject jSONObject) throws TwitterException {
        try {
            this.name = ParseUtil.getUnescapedString("name", jSONObject);
            this.streetAddress = ParseUtil.getUnescapedString("street_address", jSONObject);
            this.countryCode = ParseUtil.getRawString(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, jSONObject);
            this.id = ParseUtil.getRawString("id", jSONObject);
            this.country = ParseUtil.getRawString(UserDataStore.COUNTRY, jSONObject);
            if (jSONObject.isNull("place_type")) {
                this.placeType = ParseUtil.getRawString("type", jSONObject);
            } else {
                this.placeType = ParseUtil.getRawString("place_type", jSONObject);
            }
            this.url = ParseUtil.getRawString("url", jSONObject);
            this.fullName = ParseUtil.getRawString("full_name", jSONObject);
            if (jSONObject.isNull("bounding_box")) {
                this.boundingBoxType = null;
                this.boundingBoxCoordinates = null;
            } else {
                JSONObject jSONObject2 = jSONObject.getJSONObject("bounding_box");
                this.boundingBoxType = ParseUtil.getRawString("type", jSONObject2);
                this.boundingBoxCoordinates = JSONImplFactory.coordinatesAsGeoLocationArray(jSONObject2.getJSONArray("coordinates"));
            }
            if (jSONObject.isNull("geometry")) {
                this.geometryType = null;
                this.geometryCoordinates = null;
            } else {
                JSONObject jSONObject3 = jSONObject.getJSONObject("geometry");
                this.geometryType = ParseUtil.getRawString("type", jSONObject3);
                JSONArray jSONArray = jSONObject3.getJSONArray("coordinates");
                if (this.geometryType.equals("Point")) {
                    this.geometryCoordinates = (GeoLocation[][]) Array.newInstance((Class<?>) GeoLocation.class, 1, 1);
                    this.geometryCoordinates[0][0] = new GeoLocation(jSONArray.getDouble(1), jSONArray.getDouble(0));
                } else if (this.geometryType.equals("Polygon")) {
                    this.geometryCoordinates = JSONImplFactory.coordinatesAsGeoLocationArray(jSONArray);
                } else {
                    this.geometryType = null;
                    this.geometryCoordinates = null;
                }
            }
            if (jSONObject.isNull("contained_within")) {
                this.containedWithIn = null;
                return;
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("contained_within");
            this.containedWithIn = new Place[jSONArray2.length()];
            for (int i = 0; i < jSONArray2.length(); i++) {
                this.containedWithIn[i] = new PlaceJSONImpl(jSONArray2.getJSONObject(i));
            }
        } catch (JSONException e) {
            throw new TwitterException(String.valueOf(e.getMessage()) + ":" + jSONObject.toString(), e);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Place place) {
        return this.id.compareTo(place.getId());
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return (obj instanceof Place) && ((Place) obj).getId().equals(this.id);
    }

    @Override // twitter4j.Place
    public GeoLocation[][] getBoundingBoxCoordinates() {
        return this.boundingBoxCoordinates;
    }

    @Override // twitter4j.Place
    public String getBoundingBoxType() {
        return this.boundingBoxType;
    }

    @Override // twitter4j.Place
    public Place[] getContainedWithIn() {
        return this.containedWithIn;
    }

    @Override // twitter4j.Place
    public String getCountry() {
        return this.country;
    }

    @Override // twitter4j.Place
    public String getCountryCode() {
        return this.countryCode;
    }

    @Override // twitter4j.Place
    public String getFullName() {
        return this.fullName;
    }

    @Override // twitter4j.Place
    public GeoLocation[][] getGeometryCoordinates() {
        return this.geometryCoordinates;
    }

    @Override // twitter4j.Place
    public String getGeometryType() {
        return this.geometryType;
    }

    @Override // twitter4j.Place
    public String getId() {
        return this.id;
    }

    @Override // twitter4j.Place
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Place
    public String getPlaceType() {
        return this.placeType;
    }

    @Override // twitter4j.Place
    public String getStreetAddress() {
        return this.streetAddress;
    }

    @Override // twitter4j.Place
    public String getURL() {
        return this.url;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String toString() {
        return "PlaceJSONImpl{name='" + this.name + "', streetAddress='" + this.streetAddress + "', countryCode='" + this.countryCode + "', id='" + this.id + "', country='" + this.country + "', placeType='" + this.placeType + "', url='" + this.url + "', fullName='" + this.fullName + "', boundingBoxType='" + this.boundingBoxType + "', boundingBoxCoordinates=" + (this.boundingBoxCoordinates == null ? null : Arrays.asList(this.boundingBoxCoordinates)) + ", geometryType='" + this.geometryType + "', geometryCoordinates=" + (this.geometryCoordinates == null ? null : Arrays.asList(this.geometryCoordinates)) + ", containedWithIn=" + (this.containedWithIn != null ? Arrays.asList(this.containedWithIn) : null) + '}';
    }
}
