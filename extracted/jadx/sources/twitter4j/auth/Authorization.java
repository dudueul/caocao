package twitter4j.auth;

import java.io.Serializable;
import twitter4j.HttpRequest;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface Authorization extends Serializable {
    String getAuthorizationHeader(HttpRequest httpRequest);

    boolean isEnabled();
}
