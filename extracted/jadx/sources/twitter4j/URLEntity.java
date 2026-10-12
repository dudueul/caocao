package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface URLEntity extends TweetEntity, Serializable {
    String getDisplayURL();

    @Override // twitter4j.TweetEntity
    int getEnd();

    String getExpandedURL();

    @Override // twitter4j.TweetEntity
    int getStart();

    @Override // twitter4j.TweetEntity
    String getText();

    String getURL();
}
