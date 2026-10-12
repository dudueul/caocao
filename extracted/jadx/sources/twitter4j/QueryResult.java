package twitter4j;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface QueryResult extends TwitterResponse, Serializable {
    double getCompletedIn();

    int getCount();

    long getMaxId();

    String getQuery();

    String getRefreshURL();

    long getSinceId();

    List<Status> getTweets();

    boolean hasNext();

    Query nextQuery();
}
