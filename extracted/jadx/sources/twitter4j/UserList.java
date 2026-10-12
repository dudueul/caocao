package twitter4j;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface UserList extends Comparable<UserList>, TwitterResponse, Serializable {
    Date getCreatedAt();

    String getDescription();

    String getFullName();

    long getId();

    int getMemberCount();

    String getName();

    String getSlug();

    int getSubscriberCount();

    URI getURI();

    User getUser();

    boolean isFollowing();

    boolean isPublic();
}
