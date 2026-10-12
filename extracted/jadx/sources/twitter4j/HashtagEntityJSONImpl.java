package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class HashtagEntityJSONImpl extends EntityIndex implements HashtagEntity, SymbolEntity {
    private static final long serialVersionUID = -5317828991902848906L;
    private String text;

    HashtagEntityJSONImpl() {
    }

    HashtagEntityJSONImpl(int i, int i2, String str) {
        setStart(i);
        setEnd(i2);
        this.text = str;
    }

    HashtagEntityJSONImpl(JSONObject jSONObject) throws TwitterException {
        init(jSONObject);
    }

    private void init(JSONObject jSONObject) throws TwitterException {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("indices");
            setStart(jSONArray.getInt(0));
            setEnd(jSONArray.getInt(1));
            if (jSONObject.isNull("text")) {
                return;
            }
            this.text = jSONObject.getString("text");
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HashtagEntityJSONImpl hashtagEntityJSONImpl = (HashtagEntityJSONImpl) obj;
        if (this.text != null) {
            if (this.text.equals(hashtagEntityJSONImpl.text)) {
                return true;
            }
        } else if (hashtagEntityJSONImpl.text == null) {
            return true;
        }
        return false;
    }

    @Override // twitter4j.EntityIndex, twitter4j.HashtagEntity, twitter4j.TweetEntity
    public int getEnd() {
        return super.getEnd();
    }

    @Override // twitter4j.EntityIndex, twitter4j.HashtagEntity, twitter4j.TweetEntity
    public int getStart() {
        return super.getStart();
    }

    @Override // twitter4j.HashtagEntity, twitter4j.TweetEntity
    public String getText() {
        return this.text;
    }

    public int hashCode() {
        if (this.text != null) {
            return this.text.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "HashtagEntityJSONImpl{text='" + this.text + "'}";
    }
}
