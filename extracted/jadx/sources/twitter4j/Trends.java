package twitter4j;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface Trends extends TwitterResponse, Comparable<Trends>, Serializable {
    Date getAsOf();

    Location getLocation();

    Date getTrendAt();

    Trend[] getTrends();
}
