package twitter4j;

import java.util.EventObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class RateLimitStatusEvent extends EventObject {
    private static final long serialVersionUID = 3749366911109722414L;
    private final boolean isAccountRateLimitStatus;
    private final RateLimitStatus rateLimitStatus;

    RateLimitStatusEvent(Object obj, RateLimitStatus rateLimitStatus, boolean z) {
        super(obj);
        this.rateLimitStatus = rateLimitStatus;
        this.isAccountRateLimitStatus = z;
    }

    public RateLimitStatus getRateLimitStatus() {
        return this.rateLimitStatus;
    }

    public boolean isAccountRateLimitStatus() {
        return this.isAccountRateLimitStatus;
    }

    public boolean isIPRateLimitStatus() {
        return !this.isAccountRateLimitStatus;
    }
}
