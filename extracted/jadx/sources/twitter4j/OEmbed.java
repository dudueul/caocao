package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface OEmbed extends TwitterResponse, Serializable {
    String getAuthorName();

    String getAuthorURL();

    long getCacheAge();

    String getHtml();

    String getURL();

    String getVersion();

    int getWidth();
}
