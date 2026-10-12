package twitter4j.api;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.TwitterException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface SearchResource {
    QueryResult search(Query query) throws TwitterException;
}
