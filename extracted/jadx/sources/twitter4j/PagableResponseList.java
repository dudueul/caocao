package twitter4j;

import twitter4j.TwitterResponse;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface PagableResponseList<T extends TwitterResponse> extends ResponseList<T>, CursorSupport {
    @Override // twitter4j.CursorSupport
    long getNextCursor();

    @Override // twitter4j.CursorSupport
    long getPreviousCursor();

    @Override // twitter4j.CursorSupport
    boolean hasNext();

    @Override // twitter4j.CursorSupport
    boolean hasPrevious();
}
