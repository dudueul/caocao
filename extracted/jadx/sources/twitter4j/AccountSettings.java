package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface AccountSettings extends TwitterResponse, Serializable {
    String getLanguage();

    String getScreenName();

    String getSleepEndTime();

    String getSleepStartTime();

    TimeZone getTimeZone();

    Location[] getTrendLocations();

    boolean isAlwaysUseHttps();

    boolean isDiscoverableByEmail();

    boolean isGeoEnabled();

    boolean isSleepTimeEnabled();
}
