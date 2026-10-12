package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface ExtendedMediaEntity extends MediaEntity {

    public interface Variant extends Serializable {
        int getBitrate();

        String getContentType();

        String getUrl();
    }

    int getVideoAspectRatioHeight();

    int getVideoAspectRatioWidth();

    long getVideoDurationMillis();

    Variant[] getVideoVariants();
}
