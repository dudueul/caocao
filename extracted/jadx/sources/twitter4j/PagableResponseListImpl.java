package twitter4j;

import twitter4j.TwitterResponse;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
class PagableResponseListImpl<T extends TwitterResponse> extends ResponseListImpl<T> implements PagableResponseList<T> {
    private static final long serialVersionUID = -8603601553967559275L;
    private final long nextCursor;
    private final long previousCursor;

    PagableResponseListImpl(int i, JSONObject jSONObject, HttpResponse httpResponse) {
        super(i, httpResponse);
        this.previousCursor = ParseUtil.getLong("previous_cursor", jSONObject);
        this.nextCursor = ParseUtil.getLong("next_cursor", jSONObject);
    }

    PagableResponseListImpl(RateLimitStatus rateLimitStatus, int i) {
        super(rateLimitStatus, i);
        this.previousCursor = 0L;
        this.nextCursor = 0L;
    }

    @Override // twitter4j.PagableResponseList, twitter4j.CursorSupport
    public long getNextCursor() {
        return this.nextCursor;
    }

    @Override // twitter4j.PagableResponseList, twitter4j.CursorSupport
    public long getPreviousCursor() {
        return this.previousCursor;
    }

    @Override // twitter4j.PagableResponseList, twitter4j.CursorSupport
    public boolean hasNext() {
        return 0 != this.nextCursor;
    }

    @Override // twitter4j.PagableResponseList, twitter4j.CursorSupport
    public boolean hasPrevious() {
        return 0 != this.previousCursor;
    }
}
