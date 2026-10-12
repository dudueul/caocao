package twitter4j;

import com.adjust.sdk.Constants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class TwitterAPIConfigurationJSONImpl extends TwitterResponseImpl implements TwitterAPIConfiguration {
    private static final long serialVersionUID = -3588904550808591686L;
    private int charactersReservedPerMedia;
    private int maxMediaPerUpload;
    private String[] nonUsernamePaths;
    private int photoSizeLimit;
    private Map<Integer, MediaEntity.Size> photoSizes;
    private int shortURLLength;
    private int shortURLLengthHttps;

    TwitterAPIConfigurationJSONImpl(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        super(httpResponse);
        try {
            JSONObject jSONObjectAsJSONObject = httpResponse.asJSONObject();
            this.photoSizeLimit = ParseUtil.getInt("photo_size_limit", jSONObjectAsJSONObject);
            this.shortURLLength = ParseUtil.getInt("short_url_length", jSONObjectAsJSONObject);
            this.shortURLLengthHttps = ParseUtil.getInt("short_url_length_https", jSONObjectAsJSONObject);
            this.charactersReservedPerMedia = ParseUtil.getInt("characters_reserved_per_media", jSONObjectAsJSONObject);
            JSONObject jSONObject = jSONObjectAsJSONObject.getJSONObject("photo_sizes");
            this.photoSizes = new HashMap(4);
            this.photoSizes.put(MediaEntity.Size.LARGE, new MediaEntityJSONImpl.Size(jSONObject.getJSONObject(Constants.LARGE)));
            this.photoSizes.put(MediaEntity.Size.MEDIUM, new MediaEntityJSONImpl.Size(jSONObject.isNull("med") ? jSONObject.getJSONObject(Constants.MEDIUM) : jSONObject.getJSONObject("med")));
            this.photoSizes.put(MediaEntity.Size.SMALL, new MediaEntityJSONImpl.Size(jSONObject.getJSONObject(Constants.SMALL)));
            this.photoSizes.put(MediaEntity.Size.THUMB, new MediaEntityJSONImpl.Size(jSONObject.getJSONObject("thumb")));
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
                TwitterObjectFactory.registerJSONObject(this, httpResponse.asJSONObject());
            }
            JSONArray jSONArray = jSONObjectAsJSONObject.getJSONArray("non_username_paths");
            this.nonUsernamePaths = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                this.nonUsernamePaths[i] = jSONArray.getString(i);
            }
            this.maxMediaPerUpload = ParseUtil.getInt("max_media_per_upload", jSONObjectAsJSONObject);
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TwitterAPIConfigurationJSONImpl)) {
            return false;
        }
        TwitterAPIConfigurationJSONImpl twitterAPIConfigurationJSONImpl = (TwitterAPIConfigurationJSONImpl) obj;
        if (this.charactersReservedPerMedia == twitterAPIConfigurationJSONImpl.charactersReservedPerMedia && this.maxMediaPerUpload == twitterAPIConfigurationJSONImpl.maxMediaPerUpload && this.photoSizeLimit == twitterAPIConfigurationJSONImpl.photoSizeLimit && this.shortURLLength == twitterAPIConfigurationJSONImpl.shortURLLength && this.shortURLLengthHttps == twitterAPIConfigurationJSONImpl.shortURLLengthHttps && Arrays.equals(this.nonUsernamePaths, twitterAPIConfigurationJSONImpl.nonUsernamePaths)) {
            if (this.photoSizes != null) {
                if (this.photoSizes.equals(twitterAPIConfigurationJSONImpl.photoSizes)) {
                    return true;
                }
            } else if (twitterAPIConfigurationJSONImpl.photoSizes == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getCharactersReservedPerMedia() {
        return this.charactersReservedPerMedia;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getMaxMediaPerUpload() {
        return this.maxMediaPerUpload;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public String[] getNonUsernamePaths() {
        return this.nonUsernamePaths;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getPhotoSizeLimit() {
        return this.photoSizeLimit;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public Map<Integer, MediaEntity.Size> getPhotoSizes() {
        return this.photoSizes;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getShortURLLength() {
        return this.shortURLLength;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getShortURLLengthHttps() {
        return this.shortURLLengthHttps;
    }

    public int hashCode() {
        return (((((((((((this.photoSizeLimit * 31) + this.shortURLLength) * 31) + this.shortURLLengthHttps) * 31) + this.charactersReservedPerMedia) * 31) + (this.photoSizes != null ? this.photoSizes.hashCode() : 0)) * 31) + (this.nonUsernamePaths != null ? Arrays.hashCode(this.nonUsernamePaths) : 0)) * 31) + this.maxMediaPerUpload;
    }

    public String toString() {
        return "TwitterAPIConfigurationJSONImpl{photoSizeLimit=" + this.photoSizeLimit + ", shortURLLength=" + this.shortURLLength + ", shortURLLengthHttps=" + this.shortURLLengthHttps + ", charactersReservedPerMedia=" + this.charactersReservedPerMedia + ", photoSizes=" + this.photoSizes + ", nonUsernamePaths=" + (this.nonUsernamePaths == null ? null : Arrays.asList(this.nonUsernamePaths)) + ", maxMediaPerUpload=" + this.maxMediaPerUpload + '}';
    }
}
