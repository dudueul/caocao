package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class TimeZoneJSONImpl implements TimeZone {
    private static final long serialVersionUID = 81958969762484144L;
    private final String NAME;
    private final String TZINFO_NAME;
    private final int UTC_OFFSET;

    TimeZoneJSONImpl(JSONObject jSONObject) throws TwitterException {
        try {
            this.UTC_OFFSET = ParseUtil.getInt("utc_offset", jSONObject);
            this.NAME = jSONObject.getString("name");
            this.TZINFO_NAME = jSONObject.getString("tzinfo_name");
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.TimeZone
    public String getName() {
        return this.NAME;
    }

    @Override // twitter4j.TimeZone
    public String tzinfoName() {
        return this.TZINFO_NAME;
    }

    @Override // twitter4j.TimeZone
    public int utcOffset() {
        return this.UTC_OFFSET;
    }
}
