package twitter4j;

import com.nexon.nexonanalyticssdk.NxLogSummaryInfos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class Query implements Serializable {
    private static final long serialVersionUID = 7196404519192910019L;
    private int count;
    private String geocode;
    private String lang;
    private String locale;
    private long maxId;
    private String nextPageQuery;
    private String query;
    private ResultType resultType;
    private String since;
    private long sinceId;
    private String until;
    public static final Unit MILES = Unit.mi;
    public static final Unit KILOMETERS = Unit.km;
    public static final ResultType MIXED = ResultType.mixed;
    public static final ResultType POPULAR = ResultType.popular;
    public static final ResultType RECENT = ResultType.recent;
    private static final HttpParameter WITH_TWITTER_USER_ID = new HttpParameter("with_twitter_user_id", "true");

    public enum ResultType {
        popular,
        mixed,
        recent;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static ResultType[] valuesCustom() {
            ResultType[] resultTypeArrValuesCustom = values();
            int length = resultTypeArrValuesCustom.length;
            ResultType[] resultTypeArr = new ResultType[length];
            System.arraycopy(resultTypeArrValuesCustom, 0, resultTypeArr, 0, length);
            return resultTypeArr;
        }
    }

    public enum Unit {
        mi,
        km;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static Unit[] valuesCustom() {
            Unit[] unitArrValuesCustom = values();
            int length = unitArrValuesCustom.length;
            Unit[] unitArr = new Unit[length];
            System.arraycopy(unitArrValuesCustom, 0, unitArr, 0, length);
            return unitArr;
        }
    }

    public Query() {
        this.query = null;
        this.lang = null;
        this.locale = null;
        this.maxId = -1L;
        this.count = -1;
        this.since = null;
        this.sinceId = -1L;
        this.geocode = null;
        this.until = null;
        this.resultType = null;
        this.nextPageQuery = null;
    }

    public Query(String str) {
        this.query = null;
        this.lang = null;
        this.locale = null;
        this.maxId = -1L;
        this.count = -1;
        this.since = null;
        this.sinceId = -1L;
        this.geocode = null;
        this.until = null;
        this.resultType = null;
        this.nextPageQuery = null;
        this.query = str;
    }

    private void appendParameter(String str, long j, List<HttpParameter> list) {
        if (0 <= j) {
            list.add(new HttpParameter(str, String.valueOf(j)));
        }
    }

    private void appendParameter(String str, String str2, List<HttpParameter> list) {
        if (str2 != null) {
            list.add(new HttpParameter(str, str2));
        }
    }

    static Query createWithNextPageQuery(String str) {
        Query query = new Query();
        query.nextPageQuery = str;
        if (str != null) {
            String strSubstring = str.substring(1, str.length());
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (HttpParameter httpParameter : HttpParameter.decodeParameters(strSubstring)) {
                linkedHashMap.put(httpParameter.getName(), httpParameter.getValue());
            }
            if (linkedHashMap.containsKey("q")) {
                query.setQuery((String) linkedHashMap.get("q"));
            }
            if (linkedHashMap.containsKey("lang")) {
                query.setLang((String) linkedHashMap.get("lang"));
            }
            if (linkedHashMap.containsKey("locale")) {
                query.setLocale((String) linkedHashMap.get("locale"));
            }
            if (linkedHashMap.containsKey("max_id")) {
                query.setMaxId(Long.parseLong((String) linkedHashMap.get("max_id")));
            }
            if (linkedHashMap.containsKey(NxLogSummaryInfos.KEY_COUNT)) {
                query.setCount(Integer.parseInt((String) linkedHashMap.get(NxLogSummaryInfos.KEY_COUNT)));
            }
            if (linkedHashMap.containsKey("geocode")) {
                String[] strArrSplit = ((String) linkedHashMap.get("geocode")).split(",");
                double d = Double.parseDouble(strArrSplit[0]);
                double d2 = Double.parseDouble(strArrSplit[1]);
                double d3 = 0.0d;
                Unit unit = null;
                String str2 = strArrSplit[2];
                for (Unit unit2 : Unit.valuesCustom()) {
                    if (str2.endsWith(unit2.name())) {
                        d3 = Double.parseDouble(str2.substring(0, str2.length() - 2));
                        unit = unit2;
                        break;
                    }
                }
                if (unit == null) {
                    throw new IllegalArgumentException("unrecognized geocode radius: " + str2);
                }
                query.setGeoCode(new GeoLocation(d, d2), d3, unit);
            }
            if (linkedHashMap.containsKey("result_type")) {
                query.setResultType(ResultType.valueOf((String) linkedHashMap.get("result_type")));
            }
        }
        return query;
    }

    HttpParameter[] asHttpParameterArray() {
        ArrayList arrayList = new ArrayList(12);
        appendParameter("q", this.query, arrayList);
        appendParameter("lang", this.lang, arrayList);
        appendParameter("locale", this.locale, arrayList);
        appendParameter("max_id", this.maxId, arrayList);
        appendParameter(NxLogSummaryInfos.KEY_COUNT, this.count, arrayList);
        appendParameter("since", this.since, arrayList);
        appendParameter("since_id", this.sinceId, arrayList);
        appendParameter("geocode", this.geocode, arrayList);
        appendParameter("until", this.until, arrayList);
        if (this.resultType != null) {
            arrayList.add(new HttpParameter("result_type", this.resultType.name()));
        }
        arrayList.add(WITH_TWITTER_USER_ID);
        return (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
    }

    public Query count(int i) {
        setCount(i);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Query query = (Query) obj;
        if (this.maxId == query.maxId && this.count == query.count && this.sinceId == query.sinceId) {
            if (this.geocode == null ? query.geocode != null : !this.geocode.equals(query.geocode)) {
                return false;
            }
            if (this.lang == null ? query.lang != null : !this.lang.equals(query.lang)) {
                return false;
            }
            if (this.locale == null ? query.locale != null : !this.locale.equals(query.locale)) {
                return false;
            }
            if (this.nextPageQuery == null ? query.nextPageQuery != null : !this.nextPageQuery.equals(query.nextPageQuery)) {
                return false;
            }
            if (this.query == null ? query.query != null : !this.query.equals(query.query)) {
                return false;
            }
            if (this.resultType == null ? query.resultType != null : !this.resultType.equals(query.resultType)) {
                return false;
            }
            if (this.since == null ? query.since != null : !this.since.equals(query.since)) {
                return false;
            }
            if (this.until != null) {
                if (this.until.equals(query.until)) {
                    return true;
                }
            } else if (query.until == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public Query geoCode(GeoLocation geoLocation, double d, String str) {
        setGeoCode(geoLocation, d, str);
        return this;
    }

    public int getCount() {
        return this.count;
    }

    public String getGeocode() {
        return this.geocode;
    }

    public String getLang() {
        return this.lang;
    }

    public String getLocale() {
        return this.locale;
    }

    public long getMaxId() {
        return this.maxId;
    }

    public String getQuery() {
        return this.query;
    }

    public ResultType getResultType() {
        return this.resultType;
    }

    public String getSince() {
        return this.since;
    }

    public long getSinceId() {
        return this.sinceId;
    }

    public String getUntil() {
        return this.until;
    }

    public int hashCode() {
        return ((((((((((((((((((((this.query != null ? this.query.hashCode() : 0) * 31) + (this.lang != null ? this.lang.hashCode() : 0)) * 31) + (this.locale != null ? this.locale.hashCode() : 0)) * 31) + ((int) (this.maxId ^ (this.maxId >>> 32)))) * 31) + this.count) * 31) + (this.since != null ? this.since.hashCode() : 0)) * 31) + ((int) (this.sinceId ^ (this.sinceId >>> 32)))) * 31) + (this.geocode != null ? this.geocode.hashCode() : 0)) * 31) + (this.until != null ? this.until.hashCode() : 0)) * 31) + (this.resultType != null ? this.resultType.hashCode() : 0)) * 31) + (this.nextPageQuery != null ? this.nextPageQuery.hashCode() : 0);
    }

    public Query lang(String str) {
        setLang(str);
        return this;
    }

    public Query locale(String str) {
        setLocale(str);
        return this;
    }

    public Query maxId(long j) {
        setMaxId(j);
        return this;
    }

    String nextPage() {
        return this.nextPageQuery;
    }

    public Query query(String str) {
        setQuery(str);
        return this;
    }

    public Query resultType(ResultType resultType) {
        setResultType(resultType);
        return this;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setGeoCode(GeoLocation geoLocation, double d, String str) {
        this.geocode = String.valueOf(geoLocation.getLatitude()) + "," + geoLocation.getLongitude() + "," + d + str;
    }

    public void setGeoCode(GeoLocation geoLocation, double d, Unit unit) {
        this.geocode = String.valueOf(geoLocation.getLatitude()) + "," + geoLocation.getLongitude() + "," + d + unit.name();
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setMaxId(long j) {
        this.maxId = j;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public void setSince(String str) {
        this.since = str;
    }

    public void setSinceId(long j) {
        this.sinceId = j;
    }

    public void setUntil(String str) {
        this.until = str;
    }

    public Query since(String str) {
        setSince(str);
        return this;
    }

    public Query sinceId(long j) {
        setSinceId(j);
        return this;
    }

    public String toString() {
        return "Query{query='" + this.query + "', lang='" + this.lang + "', locale='" + this.locale + "', maxId=" + this.maxId + ", count=" + this.count + ", since='" + this.since + "', sinceId=" + this.sinceId + ", geocode='" + this.geocode + "', until='" + this.until + "', resultType='" + this.resultType + "', nextPageQuery='" + this.nextPageQuery + "'}";
    }

    public Query until(String str) {
        setUntil(str);
        return this;
    }
}
