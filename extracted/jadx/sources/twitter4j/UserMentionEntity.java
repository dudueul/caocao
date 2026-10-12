package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface UserMentionEntity extends TweetEntity, Serializable {
    @Override // twitter4j.TweetEntity
    int getEnd();

    long getId();

    String getName();

    String getScreenName();

    @Override // twitter4j.TweetEntity
    int getStart();

    @Override // twitter4j.TweetEntity
    String getText();
}
