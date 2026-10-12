package twitter4j;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface DirectMessage extends TwitterResponse, EntitySupport, Serializable {
    Date getCreatedAt();

    long getId();

    User getRecipient();

    long getRecipientId();

    String getRecipientScreenName();

    User getSender();

    long getSenderId();

    String getSenderScreenName();

    String getText();
}
