package twitter4j;

import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface MediaEntity extends URLEntity {

    public interface Size extends Serializable {
        public static final int CROP = 101;
        public static final int FIT = 100;
        public static final Integer THUMB = 0;
        public static final Integer SMALL = 1;
        public static final Integer MEDIUM = 2;
        public static final Integer LARGE = 3;

        int getHeight();

        int getResize();

        int getWidth();
    }

    long getId();

    String getMediaURL();

    String getMediaURLHttps();

    Map<Integer, Size> getSizes();

    String getType();
}
