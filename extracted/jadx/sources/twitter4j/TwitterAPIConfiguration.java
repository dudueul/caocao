package twitter4j;

import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface TwitterAPIConfiguration extends TwitterResponse, Serializable {
    int getCharactersReservedPerMedia();

    int getMaxMediaPerUpload();

    String[] getNonUsernamePaths();

    int getPhotoSizeLimit();

    Map<Integer, MediaEntity.Size> getPhotoSizes();

    int getShortURLLength();

    int getShortURLLengthHttps();
}
