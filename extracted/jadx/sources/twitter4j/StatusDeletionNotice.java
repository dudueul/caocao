package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface StatusDeletionNotice extends Comparable<StatusDeletionNotice>, Serializable {
    long getStatusId();

    long getUserId();
}
