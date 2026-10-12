package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface SymbolEntity extends TweetEntity, Serializable {
    @Override // twitter4j.TweetEntity
    int getEnd();

    @Override // twitter4j.TweetEntity
    int getStart();
}
