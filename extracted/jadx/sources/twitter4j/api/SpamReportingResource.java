package twitter4j.api;

import twitter4j.TwitterException;
import twitter4j.User;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface SpamReportingResource {
    User reportSpam(long j) throws TwitterException;

    User reportSpam(String str) throws TwitterException;
}
