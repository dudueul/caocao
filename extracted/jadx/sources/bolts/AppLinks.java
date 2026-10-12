package bolts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class AppLinks {
    static final String KEY_NAME_APPLINK_DATA = "al_applink_data";
    static final String KEY_NAME_EXTRAS = "extras";
    static final String KEY_NAME_TARGET = "target_url";

    public static Bundle getAppLinkData(Intent intent) {
        return intent.getBundleExtra(KEY_NAME_APPLINK_DATA);
    }

    public static Bundle getAppLinkExtras(Intent intent) {
        Bundle appLinkData = getAppLinkData(intent);
        if (appLinkData == null) {
            return null;
        }
        return appLinkData.getBundle("extras");
    }

    public static Uri getTargetUrl(Intent intent) {
        String string;
        Bundle appLinkData = getAppLinkData(intent);
        return (appLinkData == null || (string = appLinkData.getString(KEY_NAME_TARGET)) == null) ? intent.getData() : Uri.parse(string);
    }

    public static Uri getTargetUrlFromInboundIntent(Context context, Intent intent) {
        String string;
        Bundle appLinkData = getAppLinkData(intent);
        if (appLinkData == null || (string = appLinkData.getString(KEY_NAME_TARGET)) == null) {
            return null;
        }
        MeasurementEvent.sendBroadcastEvent(context, MeasurementEvent.APP_LINK_NAVIGATE_IN_EVENT_NAME, intent, null);
        return Uri.parse(string);
    }
}
