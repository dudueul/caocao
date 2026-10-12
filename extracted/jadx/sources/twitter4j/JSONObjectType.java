package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class JSONObjectType {
    private static final Logger logger = Logger.getLogger(JSONObjectType.class);

    public enum Type {
        SENDER,
        STATUS,
        DIRECT_MESSAGE,
        DELETE,
        LIMIT,
        STALL_WARNING,
        SCRUB_GEO,
        FRIENDS,
        FAVORITE,
        UNFAVORITE,
        FOLLOW,
        UNFOLLOW,
        USER_LIST_MEMBER_ADDED,
        USER_LIST_MEMBER_DELETED,
        USER_LIST_SUBSCRIBED,
        USER_LIST_UNSUBSCRIBED,
        USER_LIST_CREATED,
        USER_LIST_UPDATED,
        USER_LIST_DESTROYED,
        USER_UPDATE,
        USER_DELETE,
        USER_SUSPEND,
        BLOCK,
        UNBLOCK,
        DISCONNECTION,
        UNKNOWN;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static Type[] valuesCustom() {
            Type[] typeArrValuesCustom = values();
            int length = typeArrValuesCustom.length;
            Type[] typeArr = new Type[length];
            System.arraycopy(typeArrValuesCustom, 0, typeArr, 0, length);
            return typeArr;
        }
    }

    public static Type determine(JSONObject jSONObject) {
        Type type;
        if (!jSONObject.isNull("sender")) {
            return Type.SENDER;
        }
        if (!jSONObject.isNull("text")) {
            return Type.STATUS;
        }
        if (!jSONObject.isNull("direct_message")) {
            return Type.DIRECT_MESSAGE;
        }
        if (!jSONObject.isNull("delete")) {
            return Type.DELETE;
        }
        if (!jSONObject.isNull("limit")) {
            return Type.LIMIT;
        }
        if (!jSONObject.isNull("warning")) {
            return Type.STALL_WARNING;
        }
        if (!jSONObject.isNull("scrub_geo")) {
            return Type.SCRUB_GEO;
        }
        if (!jSONObject.isNull("friends")) {
            return Type.FRIENDS;
        }
        if (!jSONObject.isNull("event")) {
            try {
                String string = jSONObject.getString("event");
                if ("favorite".equals(string)) {
                    type = Type.FAVORITE;
                } else if ("unfavorite".equals(string)) {
                    type = Type.UNFAVORITE;
                } else if ("follow".equals(string)) {
                    type = Type.FOLLOW;
                } else if ("unfollow".equals(string)) {
                    type = Type.UNFOLLOW;
                } else if (string.startsWith("list")) {
                    if ("list_member_added".equals(string)) {
                        type = Type.USER_LIST_MEMBER_ADDED;
                    } else if ("list_member_removed".equals(string)) {
                        type = Type.USER_LIST_MEMBER_DELETED;
                    } else if ("list_user_subscribed".equals(string)) {
                        type = Type.USER_LIST_SUBSCRIBED;
                    } else if ("list_user_unsubscribed".equals(string)) {
                        type = Type.USER_LIST_UNSUBSCRIBED;
                    } else if ("list_created".equals(string)) {
                        type = Type.USER_LIST_CREATED;
                    } else if ("list_updated".equals(string)) {
                        type = Type.USER_LIST_UPDATED;
                    } else if ("list_destroyed".equals(string)) {
                        type = Type.USER_LIST_DESTROYED;
                    }
                } else if ("user_update".equals(string)) {
                    type = Type.USER_UPDATE;
                } else if ("user_delete".equals(string)) {
                    type = Type.USER_DELETE;
                } else if ("user_suspend".equals(string)) {
                    type = Type.USER_SUSPEND;
                } else if ("block".equals(string)) {
                    type = Type.BLOCK;
                } else if ("unblock".equals(string)) {
                    type = Type.UNBLOCK;
                }
                return type;
            } catch (JSONException e) {
                try {
                    logger.warn("Failed to get event element: ", jSONObject.toString(2));
                } catch (JSONException e2) {
                }
            }
        } else if (!jSONObject.isNull("disconnect")) {
            return Type.DISCONNECTION;
        }
        return Type.UNKNOWN;
    }
}
