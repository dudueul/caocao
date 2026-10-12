package twitter4j;

import com.adjust.sdk.Constants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class MediaEntityJSONImpl extends EntityIndex implements MediaEntity {
    private static final long serialVersionUID = 3609683338035442290L;
    protected String displayURL;
    protected String expandedURL;
    protected long id;
    protected String mediaURL;
    protected String mediaURLHttps;
    protected Map<Integer, MediaEntity.Size> sizes;
    protected String type;
    protected String url;

    static class Size implements MediaEntity.Size {
        private static final long serialVersionUID = -2515842281909325169L;
        int height;
        int resize;
        int width;

        Size() {
        }

        Size(JSONObject jSONObject) throws JSONException {
            this.width = jSONObject.getInt("w");
            this.height = jSONObject.getInt("h");
            this.resize = "fit".equals(jSONObject.getString("resize")) ? 100 : 101;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Size)) {
                return false;
            }
            Size size = (Size) obj;
            return this.height == size.height && this.resize == size.resize && this.width == size.width;
        }

        @Override // twitter4j.MediaEntity.Size
        public int getHeight() {
            return this.height;
        }

        @Override // twitter4j.MediaEntity.Size
        public int getResize() {
            return this.resize;
        }

        @Override // twitter4j.MediaEntity.Size
        public int getWidth() {
            return this.width;
        }

        public int hashCode() {
            return (((this.width * 31) + this.height) * 31) + this.resize;
        }

        public String toString() {
            return "Size{width=" + this.width + ", height=" + this.height + ", resize=" + this.resize + '}';
        }
    }

    MediaEntityJSONImpl() {
    }

    MediaEntityJSONImpl(JSONObject jSONObject) throws TwitterException {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("indices");
            setStart(jSONArray.getInt(0));
            setEnd(jSONArray.getInt(1));
            this.id = ParseUtil.getLong("id", jSONObject);
            this.url = jSONObject.getString("url");
            this.expandedURL = jSONObject.getString("expanded_url");
            this.mediaURL = jSONObject.getString("media_url");
            this.mediaURLHttps = jSONObject.getString("media_url_https");
            this.displayURL = jSONObject.getString("display_url");
            JSONObject jSONObject2 = jSONObject.getJSONObject("sizes");
            this.sizes = new HashMap(4);
            addMediaEntitySizeIfNotNull(this.sizes, jSONObject2, MediaEntity.Size.LARGE, Constants.LARGE);
            addMediaEntitySizeIfNotNull(this.sizes, jSONObject2, MediaEntity.Size.MEDIUM, Constants.MEDIUM);
            addMediaEntitySizeIfNotNull(this.sizes, jSONObject2, MediaEntity.Size.SMALL, Constants.SMALL);
            addMediaEntitySizeIfNotNull(this.sizes, jSONObject2, MediaEntity.Size.THUMB, "thumb");
            if (jSONObject.isNull("type")) {
                return;
            }
            this.type = jSONObject.getString("type");
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    private void addMediaEntitySizeIfNotNull(Map<Integer, MediaEntity.Size> map, JSONObject jSONObject, Integer num, String str) throws JSONException {
        if (jSONObject.isNull(str)) {
            return;
        }
        map.put(num, new Size(jSONObject.getJSONObject(str)));
    }

    @Override // twitter4j.EntityIndex
    public /* bridge */ /* synthetic */ int compareTo(EntityIndex entityIndex) {
        return super.compareTo(entityIndex);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof MediaEntityJSONImpl) && this.id == ((MediaEntityJSONImpl) obj).id;
    }

    @Override // twitter4j.URLEntity
    public String getDisplayURL() {
        return this.displayURL;
    }

    @Override // twitter4j.EntityIndex, twitter4j.HashtagEntity, twitter4j.TweetEntity
    public int getEnd() {
        return super.getEnd();
    }

    @Override // twitter4j.URLEntity
    public String getExpandedURL() {
        return this.expandedURL;
    }

    @Override // twitter4j.MediaEntity
    public long getId() {
        return this.id;
    }

    @Override // twitter4j.MediaEntity
    public String getMediaURL() {
        return this.mediaURL;
    }

    @Override // twitter4j.MediaEntity
    public String getMediaURLHttps() {
        return this.mediaURLHttps;
    }

    @Override // twitter4j.MediaEntity
    public Map<Integer, MediaEntity.Size> getSizes() {
        return this.sizes;
    }

    @Override // twitter4j.EntityIndex, twitter4j.HashtagEntity, twitter4j.TweetEntity
    public int getStart() {
        return super.getStart();
    }

    @Override // twitter4j.URLEntity, twitter4j.TweetEntity
    public String getText() {
        return this.url;
    }

    @Override // twitter4j.MediaEntity
    public String getType() {
        return this.type;
    }

    @Override // twitter4j.URLEntity
    public String getURL() {
        return this.url;
    }

    public int hashCode() {
        return (int) (this.id ^ (this.id >>> 32));
    }

    public String toString() {
        return "MediaEntityJSONImpl{id=" + this.id + ", url=" + this.url + ", mediaURL=" + this.mediaURL + ", mediaURLHttps=" + this.mediaURLHttps + ", expandedURL=" + this.expandedURL + ", displayURL='" + this.displayURL + "', sizes=" + this.sizes + ", type=" + this.type + '}';
    }
}
