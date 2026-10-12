package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class TrendJSONImpl implements Trend, Serializable {
    private static final long serialVersionUID = -4353426776065521132L;
    private final String name;
    private String query;
    private String url;

    TrendJSONImpl(JSONObject jSONObject) {
        this(jSONObject, false);
    }

    TrendJSONImpl(JSONObject jSONObject, boolean z) {
        this.url = null;
        this.query = null;
        this.name = ParseUtil.getRawString("name", jSONObject);
        this.url = ParseUtil.getRawString("url", jSONObject);
        this.query = ParseUtil.getRawString("query", jSONObject);
        if (z) {
            TwitterObjectFactory.registerJSONObject(this, jSONObject);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trend)) {
            return false;
        }
        Trend trend = (Trend) obj;
        if (!this.name.equals(trend.getName())) {
            return false;
        }
        if (this.query == null ? trend.getQuery() != null : !this.query.equals(trend.getQuery())) {
            return false;
        }
        if (this.url != null) {
            if (this.url.equals(trend.getURL())) {
                return true;
            }
        } else if (trend.getURL() == null) {
            return true;
        }
        return false;
    }

    @Override // twitter4j.Trend
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Trend
    public String getQuery() {
        return this.query;
    }

    @Override // twitter4j.Trend
    public String getURL() {
        return this.url;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + (this.url != null ? this.url.hashCode() : 0)) * 31) + (this.query != null ? this.query.hashCode() : 0);
    }

    public String toString() {
        return "TrendJSONImpl{name='" + this.name + "', url='" + this.url + "', query='" + this.query + "'}";
    }
}
