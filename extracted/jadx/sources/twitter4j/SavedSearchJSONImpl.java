package twitter4j;

import java.util.Date;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class SavedSearchJSONImpl extends TwitterResponseImpl implements SavedSearch {
    private static final long serialVersionUID = 846086437256360810L;
    private Date createdAt;
    private int id;
    private String name;
    private int position;
    private String query;

    SavedSearchJSONImpl(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        super(httpResponse);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        JSONObject jSONObjectAsJSONObject = httpResponse.asJSONObject();
        init(jSONObjectAsJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.registerJSONObject(this, jSONObjectAsJSONObject);
        }
    }

    SavedSearchJSONImpl(JSONObject jSONObject) throws TwitterException {
        init(jSONObject);
    }

    static ResponseList<SavedSearch> createSavedSearchList(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        JSONArray jSONArrayAsJSONArray = httpResponse.asJSONArray();
        try {
            ResponseListImpl responseListImpl = new ResponseListImpl(jSONArrayAsJSONArray.length(), httpResponse);
            for (int i = 0; i < jSONArrayAsJSONArray.length(); i++) {
                JSONObject jSONObject = jSONArrayAsJSONArray.getJSONObject(i);
                SavedSearchJSONImpl savedSearchJSONImpl = new SavedSearchJSONImpl(jSONObject);
                responseListImpl.add(savedSearchJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(savedSearchJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArrayAsJSONArray);
            }
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(String.valueOf(e.getMessage()) + ":" + httpResponse.asString(), e);
        }
    }

    private void init(JSONObject jSONObject) throws TwitterException {
        this.createdAt = ParseUtil.getDate("created_at", jSONObject, "EEE MMM dd HH:mm:ss z yyyy");
        this.query = ParseUtil.getUnescapedString("query", jSONObject);
        this.position = ParseUtil.getInt("position", jSONObject);
        this.name = ParseUtil.getUnescapedString("name", jSONObject);
        this.id = ParseUtil.getInt("id", jSONObject);
    }

    @Override // java.lang.Comparable
    public int compareTo(SavedSearch savedSearch) {
        return this.id - savedSearch.getId();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SavedSearch) && this.id == ((SavedSearch) obj).getId();
    }

    @Override // twitter4j.SavedSearch
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override // twitter4j.SavedSearch
    public int getId() {
        return this.id;
    }

    @Override // twitter4j.SavedSearch
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.SavedSearch
    public int getPosition() {
        return this.position;
    }

    @Override // twitter4j.SavedSearch
    public String getQuery() {
        return this.query;
    }

    public int hashCode() {
        return (((((((this.createdAt.hashCode() * 31) + this.query.hashCode()) * 31) + this.position) * 31) + this.name.hashCode()) * 31) + this.id;
    }

    public String toString() {
        return "SavedSearchJSONImpl{createdAt=" + this.createdAt + ", query='" + this.query + "', position=" + this.position + ", name='" + this.name + "', id=" + this.id + '}';
    }
}
