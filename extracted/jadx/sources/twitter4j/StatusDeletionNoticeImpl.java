package twitter4j;

import com.facebook.AccessToken;
import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class StatusDeletionNoticeImpl implements StatusDeletionNotice, Serializable {
    private static final long serialVersionUID = 9144204870473786368L;
    private final long statusId;
    private final long userId;

    StatusDeletionNoticeImpl(JSONObject jSONObject) {
        this.statusId = ParseUtil.getLong("id", jSONObject);
        this.userId = ParseUtil.getLong(AccessToken.USER_ID_KEY, jSONObject);
    }

    @Override // java.lang.Comparable
    public int compareTo(StatusDeletionNotice statusDeletionNotice) {
        long statusId = this.statusId - statusDeletionNotice.getStatusId();
        if (statusId < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (statusId > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) statusId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StatusDeletionNoticeImpl statusDeletionNoticeImpl = (StatusDeletionNoticeImpl) obj;
        return this.statusId == statusDeletionNoticeImpl.statusId && this.userId == statusDeletionNoticeImpl.userId;
    }

    @Override // twitter4j.StatusDeletionNotice
    public long getStatusId() {
        return this.statusId;
    }

    @Override // twitter4j.StatusDeletionNotice
    public long getUserId() {
        return this.userId;
    }

    public int hashCode() {
        return (((int) (this.statusId ^ (this.statusId >>> 32))) * 31) + ((int) (this.userId ^ (this.userId >>> 32)));
    }

    public String toString() {
        return "StatusDeletionNoticeImpl{statusId=" + this.statusId + ", userId=" + this.userId + '}';
    }
}
