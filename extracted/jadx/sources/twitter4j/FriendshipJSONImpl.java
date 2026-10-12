package twitter4j;

import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class FriendshipJSONImpl implements Friendship {
    private static final long serialVersionUID = 6847273186993125826L;
    private boolean followedBy;
    private boolean following;
    private final long id;
    private final String name;
    private final String screenName;

    FriendshipJSONImpl(JSONObject jSONObject) throws TwitterException {
        this.following = false;
        this.followedBy = false;
        try {
            this.id = ParseUtil.getLong("id", jSONObject);
            this.name = jSONObject.getString("name");
            this.screenName = jSONObject.getString("screen_name");
            JSONArray jSONArray = jSONObject.getJSONArray("connections");
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                if ("following".equals(string)) {
                    this.following = true;
                } else if ("followed_by".equals(string)) {
                    this.followedBy = true;
                }
            }
        } catch (JSONException e) {
            throw new TwitterException(String.valueOf(e.getMessage()) + ":" + jSONObject.toString(), e);
        }
    }

    static ResponseList<Friendship> createFriendshipList(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONArray jSONArrayAsJSONArray = httpResponse.asJSONArray();
            int length = jSONArrayAsJSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArrayAsJSONArray.getJSONObject(i);
                FriendshipJSONImpl friendshipJSONImpl = new FriendshipJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(friendshipJSONImpl, jSONObject);
                }
                responseListImpl.add(friendshipJSONImpl);
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArrayAsJSONArray);
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
        FriendshipJSONImpl friendshipJSONImpl = (FriendshipJSONImpl) obj;
        return this.followedBy == friendshipJSONImpl.followedBy && this.following == friendshipJSONImpl.following && this.id == friendshipJSONImpl.id && this.name.equals(friendshipJSONImpl.name) && this.screenName.equals(friendshipJSONImpl.screenName);
    }

    @Override // twitter4j.Friendship
    public long getId() {
        return this.id;
    }

    @Override // twitter4j.Friendship
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Friendship
    public String getScreenName() {
        return this.screenName;
    }

    public int hashCode() {
        return (((((((((int) (this.id ^ (this.id >>> 32))) * 31) + (this.name != null ? this.name.hashCode() : 0)) * 31) + (this.screenName != null ? this.screenName.hashCode() : 0)) * 31) + (this.following ? 1 : 0)) * 31) + (this.followedBy ? 1 : 0);
    }

    @Override // twitter4j.Friendship
    public boolean isFollowedBy() {
        return this.followedBy;
    }

    @Override // twitter4j.Friendship
    public boolean isFollowing() {
        return this.following;
    }

    public String toString() {
        return "FriendshipJSONImpl{id=" + this.id + ", name='" + this.name + "', screenName='" + this.screenName + "', following=" + this.following + ", followedBy=" + this.followedBy + '}';
    }
}
