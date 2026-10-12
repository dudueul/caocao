package com.tapjoy;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TapjoyCachedAssetData implements Serializable {
    private long a;
    private long b;
    private String c;
    private String d;
    private String e;
    private long f;
    private String g;
    private String h;

    public TapjoyCachedAssetData(String str, String str2, long j) {
        this(str, str2, j, System.currentTimeMillis() / 1000);
    }

    public TapjoyCachedAssetData(String str, String str2, long j, long j2) {
        setAssetURL(str);
        setLocalFilePath(str2);
        this.b = j;
        this.a = j2;
        this.f = j2 + j;
    }

    public static TapjoyCachedAssetData fromJSONObject(JSONObject jSONObject) {
        TapjoyCachedAssetData tapjoyCachedAssetData;
        try {
            tapjoyCachedAssetData = new TapjoyCachedAssetData(jSONObject.getString("assetURL"), jSONObject.getString("localFilePath"), jSONObject.getLong(TapjoyConstants.TJC_TIME_TO_LIVE), jSONObject.getLong(TapjoyConstants.TJC_TIMESTAMP));
            try {
                tapjoyCachedAssetData.setOfferID(jSONObject.optString("offerID"));
            } catch (JSONException e) {
                TapjoyLog.i("TapjoyCachedAssetData", "Can not build TapjoyVideoObject -- not enough data.");
            }
        } catch (JSONException e2) {
            tapjoyCachedAssetData = null;
        }
        return tapjoyCachedAssetData;
    }

    public static TapjoyCachedAssetData fromRawJSONString(String str) {
        try {
            return fromJSONObject(new JSONObject(str));
        } catch (JSONException e) {
            TapjoyLog.i("TapjoyCachedAssetData", "Can not build TapjoyVideoObject -- error reading json string");
            return null;
        }
    }

    public String getAssetURL() {
        return this.c;
    }

    public String getLocalFilePath() {
        return this.d;
    }

    public String getLocalURL() {
        return this.e;
    }

    public String getMimeType() {
        return this.g;
    }

    public String getOfferId() {
        return this.h;
    }

    public long getTimeOfDeathInSeconds() {
        return this.f;
    }

    public long getTimeToLiveInSeconds() {
        return this.b;
    }

    public long getTimestampInSeconds() {
        return this.a;
    }

    public void resetTimeToLive(long j) {
        this.b = j;
        this.f = (System.currentTimeMillis() / 1000) + j;
    }

    public void setAssetURL(String str) {
        this.c = str;
        this.g = TapjoyUtil.determineMimeType(str);
    }

    public void setLocalFilePath(String str) {
        this.d = str;
        this.e = "file://" + str;
    }

    public void setOfferID(String str) {
        this.h = str;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TapjoyConstants.TJC_TIMESTAMP, getTimestampInSeconds());
            jSONObject.put(TapjoyConstants.TJC_TIME_TO_LIVE, getTimeToLiveInSeconds());
            jSONObject.put("assetURL", getAssetURL());
            jSONObject.put("localFilePath", getLocalFilePath());
            jSONObject.put("offerID", getOfferId());
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public String toRawJSONString() {
        return toJSON().toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nURL=").append(this.e).append("\n");
        sb.append("AssetURL=").append(this.c).append("\n");
        sb.append("MimeType=").append(this.g).append("\n");
        sb.append("Timestamp=").append(getTimestampInSeconds()).append("\n");
        sb.append("TimeOfDeath=").append(this.f).append("\n");
        sb.append("TimeToLive=").append(this.b).append("\n");
        return sb.toString();
    }
}
