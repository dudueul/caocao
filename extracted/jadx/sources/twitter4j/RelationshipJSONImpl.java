package twitter4j;

import com.facebook.share.internal.ShareConstants;
import java.io.Serializable;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class RelationshipJSONImpl extends TwitterResponseImpl implements Relationship, Serializable {
    private static final long serialVersionUID = -2001484553401916448L;
    private final boolean sourceBlockingTarget;
    private final boolean sourceCanDm;
    private final boolean sourceFollowedByTarget;
    private final boolean sourceFollowingTarget;
    private final boolean sourceMutingTarget;
    private final boolean sourceNotificationsEnabled;
    private final long sourceUserId;
    private final String sourceUserScreenName;
    private final long targetUserId;
    private final String targetUserScreenName;
    private boolean wantRetweets;

    RelationshipJSONImpl(HttpResponse httpResponse, JSONObject jSONObject) throws TwitterException {
        super(httpResponse);
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("relationship");
            JSONObject jSONObject3 = jSONObject2.getJSONObject(ShareConstants.FEED_SOURCE_PARAM);
            JSONObject jSONObject4 = jSONObject2.getJSONObject("target");
            this.sourceUserId = ParseUtil.getLong("id", jSONObject3);
            this.targetUserId = ParseUtil.getLong("id", jSONObject4);
            this.sourceUserScreenName = ParseUtil.getUnescapedString("screen_name", jSONObject3);
            this.targetUserScreenName = ParseUtil.getUnescapedString("screen_name", jSONObject4);
            this.sourceBlockingTarget = ParseUtil.getBoolean("blocking", jSONObject3);
            this.sourceFollowingTarget = ParseUtil.getBoolean("following", jSONObject3);
            this.sourceFollowedByTarget = ParseUtil.getBoolean("followed_by", jSONObject3);
            this.sourceCanDm = ParseUtil.getBoolean("can_dm", jSONObject3);
            this.sourceMutingTarget = ParseUtil.getBoolean("muting", jSONObject3);
            this.sourceNotificationsEnabled = ParseUtil.getBoolean("notifications_enabled", jSONObject3);
            this.wantRetweets = ParseUtil.getBoolean("want_retweets", jSONObject3);
        } catch (JSONException e) {
            throw new TwitterException(String.valueOf(e.getMessage()) + ":" + jSONObject.toString(), e);
        }
    }

    RelationshipJSONImpl(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        this(httpResponse, httpResponse.asJSONObject());
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, httpResponse.asJSONObject());
        }
    }

    RelationshipJSONImpl(JSONObject jSONObject) throws TwitterException {
        this((HttpResponse) null, jSONObject);
    }

    static ResponseList<Relationship> createRelationshipList(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONArray jSONArrayAsJSONArray = httpResponse.asJSONArray();
            int length = jSONArrayAsJSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArrayAsJSONArray.getJSONObject(i);
                RelationshipJSONImpl relationshipJSONImpl = new RelationshipJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(relationshipJSONImpl, jSONObject);
                }
                responseListImpl.add(relationshipJSONImpl);
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArrayAsJSONArray);
            }
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.Relationship
    public boolean canSourceDm() {
        return this.sourceCanDm;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RelationshipJSONImpl relationshipJSONImpl = (RelationshipJSONImpl) obj;
        if (this.sourceBlockingTarget == relationshipJSONImpl.sourceBlockingTarget && this.sourceCanDm == relationshipJSONImpl.sourceCanDm && this.sourceFollowedByTarget == relationshipJSONImpl.sourceFollowedByTarget && this.sourceFollowingTarget == relationshipJSONImpl.sourceFollowingTarget && this.sourceMutingTarget == relationshipJSONImpl.sourceMutingTarget && this.sourceNotificationsEnabled == relationshipJSONImpl.sourceNotificationsEnabled && this.sourceUserId == relationshipJSONImpl.sourceUserId && this.targetUserId == relationshipJSONImpl.targetUserId && this.wantRetweets == relationshipJSONImpl.wantRetweets) {
            if (this.sourceUserScreenName == null ? relationshipJSONImpl.sourceUserScreenName != null : !this.sourceUserScreenName.equals(relationshipJSONImpl.sourceUserScreenName)) {
                return false;
            }
            if (this.targetUserScreenName != null) {
                if (this.targetUserScreenName.equals(relationshipJSONImpl.targetUserScreenName)) {
                    return true;
                }
            } else if (relationshipJSONImpl.targetUserScreenName == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // twitter4j.Relationship
    public long getSourceUserId() {
        return this.sourceUserId;
    }

    @Override // twitter4j.Relationship
    public String getSourceUserScreenName() {
        return this.sourceUserScreenName;
    }

    @Override // twitter4j.Relationship
    public long getTargetUserId() {
        return this.targetUserId;
    }

    @Override // twitter4j.Relationship
    public String getTargetUserScreenName() {
        return this.targetUserScreenName;
    }

    public int hashCode() {
        return (((((((((((((((((((((int) (this.targetUserId ^ (this.targetUserId >>> 32))) * 31) + (this.targetUserScreenName != null ? this.targetUserScreenName.hashCode() : 0)) * 31) + (this.sourceBlockingTarget ? 1 : 0)) * 31) + (this.sourceNotificationsEnabled ? 1 : 0)) * 31) + (this.sourceFollowingTarget ? 1 : 0)) * 31) + (this.sourceFollowedByTarget ? 1 : 0)) * 31) + (this.sourceCanDm ? 1 : 0)) * 31) + (this.sourceMutingTarget ? 1 : 0)) * 31) + ((int) (this.sourceUserId ^ (this.sourceUserId >>> 32)))) * 31) + (this.sourceUserScreenName != null ? this.sourceUserScreenName.hashCode() : 0)) * 31) + (this.wantRetweets ? 1 : 0);
    }

    @Override // twitter4j.Relationship
    public boolean isSourceBlockingTarget() {
        return this.sourceBlockingTarget;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceFollowedByTarget() {
        return this.sourceFollowedByTarget;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceFollowingTarget() {
        return this.sourceFollowingTarget;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceMutingTarget() {
        return this.sourceMutingTarget;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceNotificationsEnabled() {
        return this.sourceNotificationsEnabled;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceWantRetweets() {
        return this.wantRetweets;
    }

    @Override // twitter4j.Relationship
    public boolean isTargetFollowedBySource() {
        return this.sourceFollowingTarget;
    }

    @Override // twitter4j.Relationship
    public boolean isTargetFollowingSource() {
        return this.sourceFollowedByTarget;
    }

    public String toString() {
        return "RelationshipJSONImpl{targetUserId=" + this.targetUserId + ", targetUserScreenName='" + this.targetUserScreenName + "', sourceBlockingTarget=" + this.sourceBlockingTarget + ", sourceNotificationsEnabled=" + this.sourceNotificationsEnabled + ", sourceFollowingTarget=" + this.sourceFollowingTarget + ", sourceFollowedByTarget=" + this.sourceFollowedByTarget + ", sourceCanDm=" + this.sourceCanDm + ", sourceMutingTarget=" + this.sourceMutingTarget + ", sourceUserId=" + this.sourceUserId + ", sourceUserScreenName='" + this.sourceUserScreenName + "', wantRetweets=" + this.wantRetweets + '}';
    }
}
