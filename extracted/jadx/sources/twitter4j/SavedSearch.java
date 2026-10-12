package twitter4j;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface SavedSearch extends Comparable<SavedSearch>, TwitterResponse, Serializable {
    Date getCreatedAt();

    int getId();

    String getName();

    int getPosition();

    String getQuery();
}
