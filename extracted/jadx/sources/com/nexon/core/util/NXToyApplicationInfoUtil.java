package com.nexon.core.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.nexon.core.log.ToyLog;
import kr.co.nexon.npaccount.push.NXPPushSettingImplV2;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyApplicationInfoUtil {
    private static final int INDEX_OF_ANALYTICS_SERVICE_ID = 3;
    public static final int INDEX_OF_CLIENT_ID = 1;
    public static final int INDEX_OF_GAME_ID = 2;
    public static final int INDEX_OF_SERVICE_ID = 0;
    private static final String TOY_SERVICE_KEY = "com.nexon.platform.ServiceKey";

    public static boolean canSnsConnectWithGuest(Context context) {
        return NXPPushSettingImplV2.RESPONSE_ENABLE.equals(getApplicationInfo(context).metaData.getString("snsConnectWithGuest", ""));
    }

    public static String getAnalyticsRegionHost(Context context) {
        String string = getApplicationInfo(context).metaData.getString("com.nexon.platform.AnalyticsRegionHost");
        if (!NXStringUtil.isNull(string)) {
            return string;
        }
        ToyLog.d("Analytics region host not included");
        return "jp.livelog.nexon.com";
    }

    public static String getAnalyticsServiceID(Context context) {
        String str = getApplicationInfo(context).metaData.getInt("com.nexon.platform.AnalyticsServiceID") + "";
        if (!str.equals("0") && NXStringUtil.isNotNull(str)) {
            return str;
        }
        String string = getApplicationInfo(context).metaData.getString(TOY_SERVICE_KEY);
        if (NXStringUtil.isNull(string)) {
            ToyLog.d("ServiceKey not included");
            return "";
        }
        String[] strArrSplit = string.split("\\.");
        if (strArrSplit.length < 4) {
            ToyLog.d("AnalyticsID of ServiceKey not included");
            return "";
        }
        String str2 = strArrSplit[3];
        if (NXStringUtil.isNull(str2)) {
            ToyLog.d("AnalyticsID of ServiceKey not included");
            return "";
        }
        if (NXStringUtil.isNumeric(str2)) {
            return str2;
        }
        ToyLog.d("AnalyticsID of ServiceKey not included");
        return "";
    }

    private static ApplicationInfo getApplicationInfo(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (Exception e) {
            throw new IllegalArgumentException("Package not found. exception : " + e.toString());
        }
    }

    public static String getCliendID(Context context) {
        String string = getApplicationInfo(context).metaData.getString(TOY_SERVICE_KEY);
        if (!NXStringUtil.isNull(string)) {
            return getValueOfServiceKeyAtIndex(string, 1);
        }
        ToyLog.d("ClientID of ServiceKey not included");
        return "";
    }

    public static String getGameID(Context context) {
        String string = getApplicationInfo(context).metaData.getString(TOY_SERVICE_KEY);
        if (!NXStringUtil.isNull(string)) {
            return getValueOfServiceKeyAtIndex(string, 2);
        }
        ToyLog.d("GameID of ServiceKey not included");
        return "";
    }

    public static String getGoogleGameAppId(Context context) {
        return getApplicationInfo(context).metaData.getString("com.google.android.gms.games.APP_ID", "");
    }

    public static String getGoogleWebClientId(Context context) {
        return getApplicationInfo(context).metaData.getString("com.google.oauth.server_web_client_id", "");
    }

    public static int getPushVersion(Context context) {
        Object obj = getApplicationInfo(context).metaData.get("com.nexon.platform.push.version");
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj).intValue();
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Check the 'com.nexon.platform.push.version' meta-data in AndroidManifest.xml. Value must be Number format. ex) 1 or 2");
            }
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        return 1;
    }

    public static String getServiceID(Context context) {
        ApplicationInfo applicationInfo = getApplicationInfo(context);
        String string = applicationInfo.metaData.getString(TOY_SERVICE_KEY);
        String string2 = applicationInfo.metaData.getString("NPAServiceID");
        if (NXStringUtil.isNull(string) && NXStringUtil.isNull(string2)) {
            throw new IllegalArgumentException("Check the 'com.nexon.platform.ServiceKey' or 'NPAServiceID' meta-data in AndroidManifest.xml. com.nexon.platform.ServiceKey format ex) 2003.NDg1MaY5dDUz.toy , NPAServiceID format ex) NPA_1003");
        }
        return NXStringUtil.isNull(string) ? string2.replace("NPA_", "") : getValueOfServiceKeyAtIndex(string, 0);
    }

    public static String getValueOfServiceKeyAtIndex(String str, int i) {
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length < 3) {
            throw new IllegalArgumentException("Check the 'com.nexon.platform.ServiceKey' meta-data in AndroidManifest.xml. format ex) 2003.NDg1MaY5dDUz.toy");
        }
        if (NXStringUtil.isNull(strArrSplit[i])) {
            throw new IllegalArgumentException("Check the 'com.nexon.platform.ServiceKey' meta-data in AndroidManifest.xml. format ex) 2003.NDg1MaY5dDUz.toy");
        }
        return strArrSplit[i];
    }

    public static boolean useGuestLoginAlert(Context context) {
        return getApplicationInfo(context).metaData.getBoolean("NPAccountGuestLoginAlert", false);
    }
}
