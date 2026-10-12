package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface Place extends TwitterResponse, Comparable<Place>, Serializable {
    GeoLocation[][] getBoundingBoxCoordinates();

    String getBoundingBoxType();

    Place[] getContainedWithIn();

    String getCountry();

    String getCountryCode();

    String getFullName();

    GeoLocation[][] getGeometryCoordinates();

    String getGeometryType();

    String getId();

    String getName();

    String getPlaceType();

    String getStreetAddress();

    String getURL();
}
