package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface AccountTotals extends TwitterResponse, Serializable {
    int getFavorites();

    int getFollowers();

    int getFriends();

    int getUpdates();
}
