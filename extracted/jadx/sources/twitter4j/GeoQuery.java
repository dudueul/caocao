package twitter4j;

import com.adjust.sdk.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class GeoQuery implements Serializable {
    private static final long serialVersionUID = 5434503339001056634L;
    private String accuracy;
    private String granularity;
    private String ip;
    private GeoLocation location;
    private int maxResults;
    private String query;

    public GeoQuery(String str) {
        this.query = null;
        this.ip = null;
        this.accuracy = null;
        this.granularity = null;
        this.maxResults = -1;
        this.ip = str;
    }

    public GeoQuery(GeoLocation geoLocation) {
        this.query = null;
        this.ip = null;
        this.accuracy = null;
        this.granularity = null;
        this.maxResults = -1;
        this.location = geoLocation;
    }

    private void appendParameter(String str, double d, List<HttpParameter> list) {
        list.add(new HttpParameter(str, String.valueOf(d)));
    }

    private void appendParameter(String str, int i, List<HttpParameter> list) {
        if (i > 0) {
            list.add(new HttpParameter(str, String.valueOf(i)));
        }
    }

    private void appendParameter(String str, String str2, List<HttpParameter> list) {
        if (str2 != null) {
            list.add(new HttpParameter(str, str2));
        }
    }

    public GeoQuery accuracy(String str) {
        setAccuracy(str);
        return this;
    }

    HttpParameter[] asHttpParameterArray() {
        ArrayList arrayList = new ArrayList();
        if (this.location != null) {
            appendParameter("lat", this.location.getLatitude(), arrayList);
            appendParameter(Constants.LONG, this.location.getLongitude(), arrayList);
        }
        if (this.ip != null) {
            appendParameter("ip", this.ip, arrayList);
        }
        appendParameter("accuracy", this.accuracy, arrayList);
        appendParameter("query", this.query, arrayList);
        appendParameter("granularity", this.granularity, arrayList);
        appendParameter("max_results", this.maxResults, (List<HttpParameter>) arrayList);
        return (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoQuery geoQuery = (GeoQuery) obj;
        if (this.maxResults != geoQuery.maxResults) {
            return false;
        }
        if (this.accuracy == null ? geoQuery.accuracy != null : !this.accuracy.equals(geoQuery.accuracy)) {
            return false;
        }
        if (this.granularity == null ? geoQuery.granularity != null : !this.granularity.equals(geoQuery.granularity)) {
            return false;
        }
        if (this.ip == null ? geoQuery.ip != null : !this.ip.equals(geoQuery.ip)) {
            return false;
        }
        if (this.location != null) {
            if (this.location.equals(geoQuery.location)) {
                return true;
            }
        } else if (geoQuery.location == null) {
            return true;
        }
        return false;
    }

    public String getAccuracy() {
        return this.accuracy;
    }

    public String getGranularity() {
        return this.granularity;
    }

    public String getIp() {
        return this.ip;
    }

    public GeoLocation getLocation() {
        return this.location;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public String getQuery() {
        return this.query;
    }

    public GeoQuery granularity(String str) {
        setGranularity(str);
        return this;
    }

    public int hashCode() {
        return ((((((((this.location != null ? this.location.hashCode() : 0) * 31) + (this.ip != null ? this.ip.hashCode() : 0)) * 31) + (this.accuracy != null ? this.accuracy.hashCode() : 0)) * 31) + (this.granularity != null ? this.granularity.hashCode() : 0)) * 31) + this.maxResults;
    }

    public GeoQuery maxResults(int i) {
        setMaxResults(i);
        return this;
    }

    public void setAccuracy(String str) {
        this.accuracy = str;
    }

    public void setGranularity(String str) {
        this.granularity = str;
    }

    public void setMaxResults(int i) {
        this.maxResults = i;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public String toString() {
        return "GeoQuery{location=" + this.location + ", query='" + this.query + "', ip='" + this.ip + "', accuracy='" + this.accuracy + "', granularity='" + this.granularity + "', maxResults=" + this.maxResults + '}';
    }
}
