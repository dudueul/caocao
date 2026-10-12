package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class HttpResponseEvent {
    private final HttpRequest request;
    private final HttpResponse response;
    private final TwitterException twitterException;

    HttpResponseEvent(HttpRequest httpRequest, HttpResponse httpResponse, TwitterException twitterException) {
        this.request = httpRequest;
        this.response = httpResponse;
        this.twitterException = twitterException;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HttpResponseEvent httpResponseEvent = (HttpResponseEvent) obj;
        if (this.request == null ? httpResponseEvent.request != null : !this.request.equals(httpResponseEvent.request)) {
            return false;
        }
        if (this.response != null) {
            if (this.response.equals(httpResponseEvent.response)) {
                return true;
            }
        } else if (httpResponseEvent.response == null) {
            return true;
        }
        return false;
    }

    public HttpRequest getRequest() {
        return this.request;
    }

    public HttpResponse getResponse() {
        return this.response;
    }

    public TwitterException getTwitterException() {
        return this.twitterException;
    }

    public int hashCode() {
        return ((this.request != null ? this.request.hashCode() : 0) * 31) + (this.response != null ? this.response.hashCode() : 0);
    }

    public boolean isAuthenticated() {
        return this.request.getAuthorization().isEnabled();
    }

    public String toString() {
        return "HttpResponseEvent{request=" + this.request + ", response=" + this.response + '}';
    }
}
