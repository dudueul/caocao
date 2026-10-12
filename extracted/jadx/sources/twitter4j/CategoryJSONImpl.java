package twitter4j;

import java.io.Serializable;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class CategoryJSONImpl implements Category, Serializable {
    private static final long serialVersionUID = 3811335888122469876L;
    private String name;
    private int size;
    private String slug;

    CategoryJSONImpl(JSONObject jSONObject) throws JSONException {
        init(jSONObject);
    }

    static ResponseList<Category> createCategoriesList(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        return createCategoriesList(httpResponse.asJSONArray(), httpResponse, configuration);
    }

    static ResponseList<Category> createCategoriesList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            ResponseListImpl responseListImpl = new ResponseListImpl(jSONArray.length(), httpResponse);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                CategoryJSONImpl categoryJSONImpl = new CategoryJSONImpl(jSONObject);
                responseListImpl.add(categoryJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(categoryJSONImpl, jSONObject);
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CategoryJSONImpl categoryJSONImpl = (CategoryJSONImpl) obj;
        if (this.size != categoryJSONImpl.size) {
            return false;
        }
        if (this.name == null ? categoryJSONImpl.name != null : !this.name.equals(categoryJSONImpl.name)) {
            return false;
        }
        if (this.slug != null) {
            if (this.slug.equals(categoryJSONImpl.slug)) {
                return true;
            }
        } else if (categoryJSONImpl.slug == null) {
            return true;
        }
        return false;
    }

    @Override // twitter4j.Category
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Category
    public int getSize() {
        return this.size;
    }

    @Override // twitter4j.Category
    public String getSlug() {
        return this.slug;
    }

    public int hashCode() {
        return ((((this.name != null ? this.name.hashCode() : 0) * 31) + (this.slug != null ? this.slug.hashCode() : 0)) * 31) + this.size;
    }

    void init(JSONObject jSONObject) throws JSONException {
        this.name = jSONObject.getString("name");
        this.slug = jSONObject.getString("slug");
        this.size = ParseUtil.getInt("size", jSONObject);
    }

    public String toString() {
        return "CategoryJSONImpl{name='" + this.name + "', slug='" + this.slug + "', size=" + this.size + '}';
    }
}
