package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface Friendship extends Serializable {
    long getId();

    String getName();

    String getScreenName();

    boolean isFollowedBy();

    boolean isFollowing();
}
