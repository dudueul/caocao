package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface TimeZone extends Serializable {
    String getName();

    String tzinfoName();

    int utcOffset();
}
