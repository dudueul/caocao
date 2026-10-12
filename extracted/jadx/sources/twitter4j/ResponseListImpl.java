package twitter4j;

import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class ResponseListImpl<T> extends ArrayList<T> implements ResponseList<T> {
    private static final long serialVersionUID = 9105950888010803544L;
    private transient int accessLevel;
    private transient RateLimitStatus rateLimitStatus;

    ResponseListImpl(int i, HttpResponse httpResponse) {
        super(i);
        this.rateLimitStatus = null;
        init(httpResponse);
    }

    ResponseListImpl(HttpResponse httpResponse) {
        this.rateLimitStatus = null;
        init(httpResponse);
    }

    ResponseListImpl(RateLimitStatus rateLimitStatus, int i) {
        this.rateLimitStatus = null;
        this.rateLimitStatus = rateLimitStatus;
        this.accessLevel = i;
    }

    private void init(HttpResponse httpResponse) {
        this.rateLimitStatus = RateLimitStatusJSONImpl.createFromResponseHeader(httpResponse);
        this.accessLevel = ParseUtil.toAccessLevel(httpResponse);
    }

    @Override // twitter4j.TwitterResponse
    public int getAccessLevel() {
        return this.accessLevel;
    }

    @Override // twitter4j.ResponseList, twitter4j.TwitterResponse
    public RateLimitStatus getRateLimitStatus() {
        return this.rateLimitStatus;
    }
}
