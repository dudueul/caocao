package twitter4j;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class TwitterObjectFactory {
    private static /* synthetic */ int[] $SWITCH_TABLE$twitter4j$JSONObjectType$Type;
    private static final ThreadLocal<Map> rawJsonMap = new ThreadLocal<Map>() { // from class: twitter4j.TwitterObjectFactory.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Map initialValue() {
            return new HashMap();
        }
    };
    private static boolean registeredAtleastOnce = false;

    static /* synthetic */ int[] $SWITCH_TABLE$twitter4j$JSONObjectType$Type() {
        int[] iArr = $SWITCH_TABLE$twitter4j$JSONObjectType$Type;
        if (iArr == null) {
            iArr = new int[JSONObjectType.Type.valuesCustom().length];
            try {
                iArr[JSONObjectType.Type.BLOCK.ordinal()] = 23;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[JSONObjectType.Type.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[JSONObjectType.Type.DIRECT_MESSAGE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[JSONObjectType.Type.DISCONNECTION.ordinal()] = 25;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[JSONObjectType.Type.FAVORITE.ordinal()] = 9;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[JSONObjectType.Type.FOLLOW.ordinal()] = 11;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[JSONObjectType.Type.FRIENDS.ordinal()] = 8;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[JSONObjectType.Type.LIMIT.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[JSONObjectType.Type.SCRUB_GEO.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[JSONObjectType.Type.SENDER.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[JSONObjectType.Type.STALL_WARNING.ordinal()] = 6;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[JSONObjectType.Type.STATUS.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[JSONObjectType.Type.UNBLOCK.ordinal()] = 24;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[JSONObjectType.Type.UNFAVORITE.ordinal()] = 10;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[JSONObjectType.Type.UNFOLLOW.ordinal()] = 12;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[JSONObjectType.Type.UNKNOWN.ordinal()] = 26;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[JSONObjectType.Type.USER_DELETE.ordinal()] = 21;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[JSONObjectType.Type.USER_LIST_CREATED.ordinal()] = 17;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[JSONObjectType.Type.USER_LIST_DESTROYED.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[JSONObjectType.Type.USER_LIST_MEMBER_ADDED.ordinal()] = 13;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[JSONObjectType.Type.USER_LIST_MEMBER_DELETED.ordinal()] = 14;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[JSONObjectType.Type.USER_LIST_SUBSCRIBED.ordinal()] = 15;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[JSONObjectType.Type.USER_LIST_UNSUBSCRIBED.ordinal()] = 16;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[JSONObjectType.Type.USER_LIST_UPDATED.ordinal()] = 18;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[JSONObjectType.Type.USER_SUSPEND.ordinal()] = 22;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[JSONObjectType.Type.USER_UPDATE.ordinal()] = 20;
            } catch (NoSuchFieldError e26) {
            }
            $SWITCH_TABLE$twitter4j$JSONObjectType$Type = iArr;
        }
        return iArr;
    }

    private TwitterObjectFactory() {
        throw new AssertionError("not intended to be instantiated.");
    }

    static void clearThreadLocalMap() {
        rawJsonMap.get().clear();
    }

    public static AccountTotals createAccountTotals(String str) throws TwitterException {
        try {
            return new AccountTotalsJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static Category createCategory(String str) throws TwitterException {
        try {
            return new CategoryJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static DirectMessage createDirectMessage(String str) throws TwitterException {
        try {
            return new DirectMessageJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static IDs createIDs(String str) throws TwitterException {
        return new IDsJSONImpl(str);
    }

    public static Location createLocation(String str) throws TwitterException {
        try {
            return new LocationJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static OEmbed createOEmbed(String str) throws TwitterException {
        try {
            return new OEmbedJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static Object createObject(String str) throws TwitterException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch ($SWITCH_TABLE$twitter4j$JSONObjectType$Type()[JSONObjectType.determine(jSONObject).ordinal()]) {
                case 1:
                    return registerJSONObject(new DirectMessageJSONImpl(jSONObject.getJSONObject("direct_message")), jSONObject);
                case 2:
                    return registerJSONObject(new StatusJSONImpl(jSONObject), jSONObject);
                case 3:
                    return registerJSONObject(new DirectMessageJSONImpl(jSONObject.getJSONObject("direct_message")), jSONObject);
                case 4:
                    return registerJSONObject(new StatusDeletionNoticeImpl(jSONObject.getJSONObject("delete").getJSONObject("status")), jSONObject);
                case 5:
                case 6:
                case 7:
                default:
                    return jSONObject;
            }
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static Place createPlace(String str) throws TwitterException {
        try {
            return new PlaceJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static Map<String, RateLimitStatus> createRateLimitStatus(String str) throws TwitterException {
        try {
            return RateLimitStatusJSONImpl.createRateLimitStatuses(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static Relationship createRelationship(String str) throws TwitterException {
        try {
            return new RelationshipJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static SavedSearch createSavedSearch(String str) throws TwitterException {
        try {
            return new SavedSearchJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static Status createStatus(String str) throws TwitterException {
        try {
            return new StatusJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static Trend createTrend(String str) throws TwitterException {
        try {
            return new TrendJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static Trends createTrends(String str) throws TwitterException {
        return new TrendsJSONImpl(str);
    }

    public static User createUser(String str) throws TwitterException {
        try {
            return new UserJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static UserList createUserList(String str) throws TwitterException {
        try {
            return new UserListJSONImpl(new JSONObject(str));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static String getRawJSON(Object obj) {
        if (!registeredAtleastOnce) {
            throw new IllegalStateException("Apparently jsonStoreEnabled is not set to true.");
        }
        Object obj2 = rawJsonMap.get().get(obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (obj2 != null) {
            return obj2.toString();
        }
        return null;
    }

    static <T> T registerJSONObject(T t, Object obj) {
        registeredAtleastOnce = true;
        rawJsonMap.get().put(t, obj);
        return t;
    }
}
