package twitter4j;

import java.util.Arrays;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class IDsJSONImpl extends TwitterResponseImpl implements IDs {
    private static final long serialVersionUID = 6999637496007165672L;
    private long[] ids;
    private long nextCursor;
    private long previousCursor;

    IDsJSONImpl(String str) throws TwitterException {
        this.previousCursor = -1L;
        this.nextCursor = -1L;
        init(str);
    }

    IDsJSONImpl(HttpResponse httpResponse, Configuration configuration) throws Throwable {
        super(httpResponse);
        this.previousCursor = -1L;
        this.nextCursor = -1L;
        String strAsString = httpResponse.asString();
        init(strAsString);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, strAsString);
        }
    }

    private void init(String str) throws TwitterException {
        try {
            if (!str.startsWith("{")) {
                JSONArray jSONArray = new JSONArray(str);
                this.ids = new long[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        this.ids[i] = Long.parseLong(jSONArray.getString(i));
                    } catch (NumberFormatException e) {
                        throw new TwitterException("Twitter API returned malformed response: " + jSONArray, e);
                    }
                }
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray2 = jSONObject.getJSONArray("ids");
            this.ids = new long[jSONArray2.length()];
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                try {
                    this.ids[i2] = Long.parseLong(jSONArray2.getString(i2));
                } catch (NumberFormatException e2) {
                    throw new TwitterException("Twitter API returned malformed response: " + jSONObject, e2);
                }
            }
            this.previousCursor = ParseUtil.getLong("previous_cursor", jSONObject);
            this.nextCursor = ParseUtil.getLong("next_cursor", jSONObject);
            return;
        } catch (JSONException e3) {
            throw new TwitterException(e3);
        }
        throw new TwitterException(e3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof IDs) && Arrays.equals(this.ids, ((IDs) obj).getIDs());
    }

    @Override // twitter4j.IDs
    public long[] getIDs() {
        return this.ids;
    }

    @Override // twitter4j.IDs, twitter4j.CursorSupport
    public long getNextCursor() {
        return this.nextCursor;
    }

    @Override // twitter4j.IDs, twitter4j.CursorSupport
    public long getPreviousCursor() {
        return this.previousCursor;
    }

    @Override // twitter4j.IDs, twitter4j.CursorSupport
    public boolean hasNext() {
        return 0 != this.nextCursor;
    }

    @Override // twitter4j.IDs, twitter4j.CursorSupport
    public boolean hasPrevious() {
        return 0 != this.previousCursor;
    }

    public int hashCode() {
        if (this.ids != null) {
            return Arrays.hashCode(this.ids);
        }
        return 0;
    }

    public String toString() {
        return "IDsJSONImpl{ids=" + Arrays.toString(this.ids) + ", previousCursor=" + this.previousCursor + ", nextCursor=" + this.nextCursor + '}';
    }
}
