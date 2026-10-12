package twitter4j;

import com.facebook.share.internal.ShareConstants;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class UserListJSONImpl extends TwitterResponseImpl implements UserList, Serializable {
    private static final long serialVersionUID = 449418980060197008L;
    private Date createdAt;
    private String description;
    private boolean following;
    private String fullName;
    private long id;
    private int memberCount;
    private boolean mode;
    private String name;
    private String slug;
    private int subscriberCount;
    private String uri;
    private User user;

    UserListJSONImpl(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
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

    UserListJSONImpl(JSONObject jSONObject) throws TwitterException {
        init(jSONObject);
    }

    static PagableResponseList<UserList> createPagableUserListList(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONObject jSONObjectAsJSONObject = httpResponse.asJSONObject();
            JSONArray jSONArray = jSONObjectAsJSONObject.getJSONArray("lists");
            int length = jSONArray.length();
            PagableResponseListImpl pagableResponseListImpl = new PagableResponseListImpl(length, jSONObjectAsJSONObject, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                UserListJSONImpl userListJSONImpl = new UserListJSONImpl(jSONObject);
                pagableResponseListImpl.add(userListJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(userListJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(pagableResponseListImpl, jSONObjectAsJSONObject);
            }
            return pagableResponseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    static ResponseList<UserList> createUserListList(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONArray jSONArrayAsJSONArray = httpResponse.asJSONArray();
            int length = jSONArrayAsJSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArrayAsJSONArray.getJSONObject(i);
                UserListJSONImpl userListJSONImpl = new UserListJSONImpl(jSONObject);
                responseListImpl.add(userListJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(userListJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArrayAsJSONArray);
            }
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    private void init(JSONObject jSONObject) throws TwitterException {
        this.id = ParseUtil.getLong("id", jSONObject);
        this.name = ParseUtil.getRawString("name", jSONObject);
        this.fullName = ParseUtil.getRawString("full_name", jSONObject);
        this.slug = ParseUtil.getRawString("slug", jSONObject);
        this.description = ParseUtil.getRawString("description", jSONObject);
        this.subscriberCount = ParseUtil.getInt("subscriber_count", jSONObject);
        this.memberCount = ParseUtil.getInt("member_count", jSONObject);
        this.uri = ParseUtil.getRawString(ShareConstants.MEDIA_URI, jSONObject);
        this.mode = "public".equals(ParseUtil.getRawString("mode", jSONObject));
        this.following = ParseUtil.getBoolean("following", jSONObject);
        this.createdAt = ParseUtil.getDate("created_at", jSONObject);
        try {
            if (jSONObject.isNull("user")) {
                return;
            }
            this.user = new UserJSONImpl(jSONObject.getJSONObject("user"));
        } catch (JSONException e) {
            throw new TwitterException(String.valueOf(e.getMessage()) + ":" + jSONObject.toString(), e);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(UserList userList) {
        long id = this.id - userList.getId();
        if (id < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (id > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return (obj instanceof UserList) && ((UserList) obj).getId() == this.id;
    }

    @Override // twitter4j.UserList
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override // twitter4j.UserList
    public String getDescription() {
        return this.description;
    }

    @Override // twitter4j.UserList
    public String getFullName() {
        return this.fullName;
    }

    @Override // twitter4j.UserList
    public long getId() {
        return this.id;
    }

    @Override // twitter4j.UserList
    public int getMemberCount() {
        return this.memberCount;
    }

    @Override // twitter4j.UserList
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.UserList
    public String getSlug() {
        return this.slug;
    }

    @Override // twitter4j.UserList
    public int getSubscriberCount() {
        return this.subscriberCount;
    }

    @Override // twitter4j.UserList
    public URI getURI() {
        try {
            return new URI(this.uri);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override // twitter4j.UserList
    public User getUser() {
        return this.user;
    }

    public int hashCode() {
        return (int) this.id;
    }

    @Override // twitter4j.UserList
    public boolean isFollowing() {
        return this.following;
    }

    @Override // twitter4j.UserList
    public boolean isPublic() {
        return this.mode;
    }

    public String toString() {
        return "UserListJSONImpl{id=" + this.id + ", name='" + this.name + "', fullName='" + this.fullName + "', slug='" + this.slug + "', description='" + this.description + "', subscriberCount=" + this.subscriberCount + ", memberCount=" + this.memberCount + ", uri='" + this.uri + "', mode=" + this.mode + ", user=" + this.user + ", following=" + this.following + '}';
    }
}
