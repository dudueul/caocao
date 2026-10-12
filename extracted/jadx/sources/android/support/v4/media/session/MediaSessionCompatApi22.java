package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.support.annotation.RequiresApi;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@RequiresApi(22)
class MediaSessionCompatApi22 {
    MediaSessionCompatApi22() {
    }

    public static void setRatingType(Object obj, int i) {
        ((MediaSession) obj).setRatingType(i);
    }
}
