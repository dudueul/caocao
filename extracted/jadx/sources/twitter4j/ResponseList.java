package twitter4j;

import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface ResponseList<T> extends TwitterResponse, List<T> {
    @Override // twitter4j.TwitterResponse
    RateLimitStatus getRateLimitStatus();
}
