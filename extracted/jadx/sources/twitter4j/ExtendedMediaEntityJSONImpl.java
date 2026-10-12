package twitter4j;

import java.util.Arrays;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class ExtendedMediaEntityJSONImpl extends MediaEntityJSONImpl implements ExtendedMediaEntity {
    private static final long serialVersionUID = -3889082303259253211L;
    private int videoAspectRatioHeight;
    private int videoAspectRatioWidth;
    private long videoDurationMillis;
    private Variant[] videoVariants;

    static class Variant implements ExtendedMediaEntity.Variant {
        private static final long serialVersionUID = 1027236588556797980L;
        int bitrate;
        String contentType;
        String url;

        Variant() {
        }

        Variant(JSONObject jSONObject) throws JSONException {
            this.bitrate = jSONObject.has("bitrate") ? jSONObject.getInt("bitrate") : 0;
            this.contentType = jSONObject.getString("content_type");
            this.url = jSONObject.getString("url");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Variant)) {
                return false;
            }
            Variant variant = (Variant) obj;
            return this.bitrate == variant.bitrate && this.contentType.equals(variant.contentType) && this.url.equals(variant.url);
        }

        @Override // twitter4j.ExtendedMediaEntity.Variant
        public int getBitrate() {
            return this.bitrate;
        }

        @Override // twitter4j.ExtendedMediaEntity.Variant
        public String getContentType() {
            return this.contentType;
        }

        @Override // twitter4j.ExtendedMediaEntity.Variant
        public String getUrl() {
            return this.url;
        }

        public int hashCode() {
            return (((this.bitrate * 31) + (this.contentType != null ? this.contentType.hashCode() : 0)) * 31) + (this.url != null ? this.url.hashCode() : 0);
        }

        public String toString() {
            return "Variant{bitrate=" + this.bitrate + ", contentType=" + this.contentType + ", url=" + this.url + '}';
        }
    }

    ExtendedMediaEntityJSONImpl() {
    }

    ExtendedMediaEntityJSONImpl(JSONObject jSONObject) throws TwitterException {
        super(jSONObject);
        try {
            if (!jSONObject.has("video_info")) {
                this.videoVariants = new Variant[0];
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("video_info");
            JSONArray jSONArray = jSONObject2.getJSONArray("aspect_ratio");
            this.videoAspectRatioWidth = jSONArray.getInt(0);
            this.videoAspectRatioHeight = jSONArray.getInt(1);
            if (!jSONObject2.isNull("duration_millis")) {
                this.videoDurationMillis = jSONObject2.getLong("duration_millis");
            }
            JSONArray jSONArray2 = jSONObject2.getJSONArray("variants");
            this.videoVariants = new Variant[jSONArray2.length()];
            for (int i = 0; i < jSONArray2.length(); i++) {
                this.videoVariants[i] = new Variant(jSONArray2.getJSONObject(i));
            }
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.MediaEntityJSONImpl, twitter4j.EntityIndex
    public /* bridge */ /* synthetic */ int compareTo(EntityIndex entityIndex) {
        return super.compareTo(entityIndex);
    }

    @Override // twitter4j.MediaEntityJSONImpl
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ExtendedMediaEntityJSONImpl) && this.id == ((ExtendedMediaEntityJSONImpl) obj).id;
    }

    @Override // twitter4j.ExtendedMediaEntity
    public int getVideoAspectRatioHeight() {
        return this.videoAspectRatioHeight;
    }

    @Override // twitter4j.ExtendedMediaEntity
    public int getVideoAspectRatioWidth() {
        return this.videoAspectRatioWidth;
    }

    @Override // twitter4j.ExtendedMediaEntity
    public long getVideoDurationMillis() {
        return this.videoDurationMillis;
    }

    @Override // twitter4j.ExtendedMediaEntity
    public ExtendedMediaEntity.Variant[] getVideoVariants() {
        return this.videoVariants;
    }

    @Override // twitter4j.MediaEntityJSONImpl
    public int hashCode() {
        return (int) (this.id ^ (this.id >>> 32));
    }

    @Override // twitter4j.MediaEntityJSONImpl
    public String toString() {
        return "ExtendedMediaEntityJSONImpl{id=" + this.id + ", url=" + this.url + ", mediaURL=" + this.mediaURL + ", mediaURLHttps=" + this.mediaURLHttps + ", expandedURL=" + this.expandedURL + ", displayURL='" + this.displayURL + "', sizes=" + this.sizes + ", type=" + this.type + ", videoAspectRatioWidth=" + this.videoAspectRatioWidth + ", videoAspectRatioHeight=" + this.videoAspectRatioHeight + ", videoDurationMillis=" + this.videoDurationMillis + ", videoVariants=" + Arrays.toString(this.videoVariants) + '}';
    }
}
