package com.adjust.sdk;

import android.content.ContentResolver;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.appevents.UserDataStore;
import com.facebook.share.internal.ShareConstants;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import com.tapjoy.TapjoyConstants;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class PackageBuilder {
    private static ILogger logger = AdjustFactory.getLogger();
    private ActivityStateCopy activityStateCopy;
    private AdjustConfig adjustConfig;
    AdjustAttribution attribution;
    private long createdAt;
    String deeplink;
    private DeviceInfo deviceInfo;
    Map<String, String> extraParameters;
    String rawReferrer;
    String referrer;
    String reftag;
    private SessionParameters sessionParameters;
    long clickTimeInMilliseconds = -1;
    long clicktTimeInSeconds = -1;
    long installBeginTimeInSeconds = -1;

    private class ActivityStateCopy {
        int eventCount;
        long lastInterval;
        String pushToken;
        int sessionCount;
        long sessionLength;
        int subsessionCount;
        long timeSpent;
        String uuid;

        ActivityStateCopy(ActivityState activityState) {
            this.lastInterval = -1L;
            this.eventCount = -1;
            this.uuid = null;
            this.sessionCount = -1;
            this.subsessionCount = -1;
            this.sessionLength = -1L;
            this.timeSpent = -1L;
            this.pushToken = null;
            if (activityState == null) {
                return;
            }
            this.lastInterval = activityState.lastInterval;
            this.eventCount = activityState.eventCount;
            this.uuid = activityState.uuid;
            this.sessionCount = activityState.sessionCount;
            this.subsessionCount = activityState.subsessionCount;
            this.sessionLength = activityState.sessionLength;
            this.timeSpent = activityState.timeSpent;
            this.pushToken = activityState.pushToken;
        }
    }

    public PackageBuilder(AdjustConfig adjustConfig, DeviceInfo deviceInfo, ActivityState activityState, SessionParameters sessionParameters, long j) {
        this.adjustConfig = adjustConfig;
        this.deviceInfo = deviceInfo;
        this.activityStateCopy = new ActivityStateCopy(activityState);
        this.sessionParameters = sessionParameters;
        this.createdAt = j;
    }

    public static void addBoolean(Map<String, String> map, String str, Boolean bool) {
        if (bool == null) {
            return;
        }
        addLong(map, str, bool.booleanValue() ? 1 : 0);
    }

    public static void addDate(Map<String, String> map, String str, Date date) {
        if (date == null) {
            return;
        }
        addString(map, str, Util.dateFormatter.format(date));
    }

    public static void addDateInMilliseconds(Map<String, String> map, String str, long j) {
        if (j <= 0) {
            return;
        }
        addDate(map, str, new Date(j));
    }

    public static void addDateInSeconds(Map<String, String> map, String str, long j) {
        if (j <= 0) {
            return;
        }
        addDate(map, str, new Date(1000 * j));
    }

    public static void addDouble(Map<String, String> map, String str, Double d) {
        if (d == null) {
            return;
        }
        addString(map, str, Util.formatString("%.5f", d));
    }

    public static void addDuration(Map<String, String> map, String str, long j) {
        if (j < 0) {
            return;
        }
        addLong(map, str, (500 + j) / 1000);
    }

    public static void addLong(Map<String, String> map, String str, long j) {
        if (j < 0) {
            return;
        }
        addString(map, str, Long.toString(j));
    }

    public static void addMapJson(Map<String, String> map, String str, Map<String, String> map2) {
        if (map2 == null || map2.size() == 0) {
            return;
        }
        addString(map, str, new JSONObject(map2).toString());
    }

    public static void addString(Map<String, String> map, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        map.put(str, str2);
    }

    private void checkDeviceIds(Map<String, String> map) {
        if (map.containsKey("mac_sha1") || map.containsKey("mac_md5") || map.containsKey(TapjoyConstants.TJC_ANDROID_ID) || map.containsKey("gps_adid")) {
            return;
        }
        logger.error("Missing device id's. Please check if Proguard is correctly set with Adjust SDK", new Object[0]);
    }

    private void fillPluginKeys(Map<String, String> map) {
        if (this.deviceInfo.pluginKeys == null) {
            return;
        }
        for (Map.Entry<String, String> entry : this.deviceInfo.pluginKeys.entrySet()) {
            addString(map, entry.getKey(), entry.getValue());
        }
    }

    private Map<String, String> getAttributableParameters(boolean z) {
        Map<String, String> defaultParameters = getDefaultParameters();
        addDuration(defaultParameters, "last_interval", this.activityStateCopy.lastInterval);
        addString(defaultParameters, "default_tracker", this.adjustConfig.defaultTracker);
        addString(defaultParameters, "installed_at", this.deviceInfo.appInstallTime);
        addString(defaultParameters, "updated_at", this.deviceInfo.appUpdateTime);
        if (!z) {
            addMapJson(defaultParameters, Constants.CALLBACK_PARAMETERS, this.sessionParameters.callbackParameters);
            addMapJson(defaultParameters, Constants.PARTNER_PARAMETERS, this.sessionParameters.partnerParameters);
        }
        return defaultParameters;
    }

    private ActivityPackage getDefaultActivityPackage(ActivityKind activityKind) {
        ActivityPackage activityPackage = new ActivityPackage(activityKind);
        activityPackage.setClientSdk(this.deviceInfo.clientSdk);
        return activityPackage;
    }

    private Map<String, String> getDefaultParameters() {
        HashMap map = new HashMap();
        injectDeviceInfo(map);
        injectConfig(map);
        injectActivityState(map);
        injectCommonParameters(map);
        checkDeviceIds(map);
        return map;
    }

    private String getEventSuffix(AdjustEvent adjustEvent) {
        return adjustEvent.revenue == null ? Util.formatString("'%s'", adjustEvent.eventToken) : Util.formatString("(%.5f %s, '%s')", adjustEvent.revenue, adjustEvent.currency, adjustEvent.eventToken);
    }

    private Map<String, String> getIdsParameters() {
        HashMap map = new HashMap();
        injectDeviceInfoIds(map);
        injectConfig(map);
        injectCommonParameters(map);
        checkDeviceIds(map);
        return map;
    }

    private void injectActivityState(Map<String, String> map) {
        addString(map, "android_uuid", this.activityStateCopy.uuid);
        addLong(map, "session_count", this.activityStateCopy.sessionCount);
        addLong(map, "subsession_count", this.activityStateCopy.subsessionCount);
        addDuration(map, "session_length", this.activityStateCopy.sessionLength);
        addDuration(map, "time_spent", this.activityStateCopy.timeSpent);
    }

    private void injectAttribution(Map<String, String> map) {
        if (this.attribution == null) {
            return;
        }
        addString(map, "tracker", this.attribution.trackerName);
        addString(map, "campaign", this.attribution.campaign);
        addString(map, "adgroup", this.attribution.adgroup);
        addString(map, "creative", this.attribution.creative);
    }

    private void injectCommonParameters(Map<String, String> map) {
        addDateInMilliseconds(map, "created_at", this.createdAt);
        addBoolean(map, "attribution_deeplink", true);
        addBoolean(map, "needs_response_details", true);
    }

    private void injectConfig(Map<String, String> map) {
        addString(map, "app_token", this.adjustConfig.appToken);
        addString(map, "environment", this.adjustConfig.environment);
        addBoolean(map, "device_known", this.adjustConfig.deviceKnown);
        addBoolean(map, "event_buffering_enabled", Boolean.valueOf(this.adjustConfig.eventBufferingEnabled));
        addString(map, "push_token", this.activityStateCopy.pushToken);
        ContentResolver contentResolver = this.adjustConfig.context.getContentResolver();
        addString(map, "fire_adid", Util.getFireAdvertisingId(contentResolver));
        addBoolean(map, "fire_tracking_enabled", Util.getFireTrackingEnabled(contentResolver));
        addString(map, "secret_id", this.adjustConfig.secretId);
        addString(map, "app_secret", this.adjustConfig.appSecret);
        if (this.adjustConfig.readMobileEquipmentIdentity) {
            TelephonyManager telephonyManager = (TelephonyManager) this.adjustConfig.context.getSystemService("phone");
            addString(map, "device_ids", Util.getTelephonyIds(telephonyManager));
            addString(map, "imeis", Util.getIMEIs(telephonyManager));
            addString(map, "meids", Util.getMEIDs(telephonyManager));
        }
    }

    private void injectDeviceInfo(Map<String, String> map) {
        injectDeviceInfoIds(map);
        addString(map, "fb_id", this.deviceInfo.fbAttributionId);
        addString(map, "package_name", this.deviceInfo.packageName);
        addString(map, "app_version", this.deviceInfo.appVersion);
        addString(map, TapjoyConstants.TJC_DEVICE_TYPE_NAME, this.deviceInfo.deviceType);
        addString(map, "device_name", this.deviceInfo.deviceName);
        addString(map, TapjoyConstants.TJC_DEVICE_MANUFACTURER, this.deviceInfo.deviceManufacturer);
        addString(map, NxSystemInfo.KEY_OS_NAME, this.deviceInfo.osName);
        addString(map, TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, this.deviceInfo.osVersion);
        addString(map, "api_level", this.deviceInfo.apiLevel);
        addString(map, "language", this.deviceInfo.language);
        addString(map, UserDataStore.COUNTRY, this.deviceInfo.country);
        addString(map, "screen_size", this.deviceInfo.screenSize);
        addString(map, "screen_format", this.deviceInfo.screenFormat);
        addString(map, TapjoyConstants.TJC_DEVICE_SCREEN_DENSITY, this.deviceInfo.screenDensity);
        addString(map, "display_width", this.deviceInfo.displayWidth);
        addString(map, "display_height", this.deviceInfo.displayHeight);
        addString(map, "hardware_name", this.deviceInfo.hardwareName);
        addString(map, "cpu_type", this.deviceInfo.abi);
        addString(map, "os_build", this.deviceInfo.buildName);
        addString(map, "vm_isa", this.deviceInfo.vmInstructionSet);
        addString(map, "mcc", Util.getMcc(this.adjustConfig.context));
        addString(map, "mnc", Util.getMnc(this.adjustConfig.context));
        addLong(map, "connectivity_type", Util.getConnectivityType(this.adjustConfig.context));
        addLong(map, NxSystemInfo.KEY_NETWORK_TYPE, Util.getNetworkType(this.adjustConfig.context));
        fillPluginKeys(map);
    }

    private void injectDeviceInfoIds(Map<String, String> map) {
        this.deviceInfo.reloadDeviceIds(this.adjustConfig.context);
        addBoolean(map, "tracking_enabled", this.deviceInfo.isTrackingEnabled);
        addString(map, "gps_adid", this.deviceInfo.playAdId);
        if (this.deviceInfo.playAdId == null) {
            addString(map, "mac_sha1", this.deviceInfo.macSha1);
            addString(map, "mac_md5", this.deviceInfo.macShortMd5);
            addString(map, TapjoyConstants.TJC_ANDROID_ID, this.deviceInfo.androidId);
        }
    }

    public ActivityPackage buildAttributionPackage() {
        Map<String, String> idsParameters = getIdsParameters();
        ActivityPackage defaultActivityPackage = getDefaultActivityPackage(ActivityKind.ATTRIBUTION);
        defaultActivityPackage.setPath("attribution");
        defaultActivityPackage.setSuffix("");
        defaultActivityPackage.setParameters(idsParameters);
        return defaultActivityPackage;
    }

    public ActivityPackage buildClickPackage(String str) {
        Map<String, String> attributableParameters = getAttributableParameters(false);
        addString(attributableParameters, ShareConstants.FEED_SOURCE_PARAM, str);
        addDateInMilliseconds(attributableParameters, "click_time", this.clickTimeInMilliseconds);
        addString(attributableParameters, Constants.REFTAG, this.reftag);
        addMapJson(attributableParameters, "params", this.extraParameters);
        addString(attributableParameters, "referrer", this.referrer);
        addString(attributableParameters, "raw_referrer", this.rawReferrer);
        addString(attributableParameters, Constants.DEEPLINK, this.deeplink);
        addDateInSeconds(attributableParameters, "click_time", this.clicktTimeInSeconds);
        addDateInSeconds(attributableParameters, "install_begin_time", this.installBeginTimeInSeconds);
        injectAttribution(attributableParameters);
        ActivityPackage defaultActivityPackage = getDefaultActivityPackage(ActivityKind.CLICK);
        defaultActivityPackage.setPath("/sdk_click");
        defaultActivityPackage.setSuffix("");
        defaultActivityPackage.setClickTimeInMilliseconds(this.clickTimeInMilliseconds);
        defaultActivityPackage.setClickTimeInSeconds(this.clicktTimeInSeconds);
        defaultActivityPackage.setInstallBeginTimeInSeconds(this.installBeginTimeInSeconds);
        defaultActivityPackage.setParameters(attributableParameters);
        return defaultActivityPackage;
    }

    public ActivityPackage buildEventPackage(AdjustEvent adjustEvent, boolean z) {
        Map<String, String> defaultParameters = getDefaultParameters();
        addLong(defaultParameters, "event_count", this.activityStateCopy.eventCount);
        addString(defaultParameters, "event_token", adjustEvent.eventToken);
        addDouble(defaultParameters, "revenue", adjustEvent.revenue);
        addString(defaultParameters, "currency", adjustEvent.currency);
        if (!z) {
            addMapJson(defaultParameters, Constants.CALLBACK_PARAMETERS, Util.mergeParameters(this.sessionParameters.callbackParameters, adjustEvent.callbackParameters, "Callback"));
            addMapJson(defaultParameters, Constants.PARTNER_PARAMETERS, Util.mergeParameters(this.sessionParameters.partnerParameters, adjustEvent.partnerParameters, "Partner"));
        }
        ActivityPackage defaultActivityPackage = getDefaultActivityPackage(ActivityKind.EVENT);
        defaultActivityPackage.setPath("/event");
        defaultActivityPackage.setSuffix(getEventSuffix(adjustEvent));
        defaultActivityPackage.setParameters(defaultParameters);
        if (z) {
            defaultActivityPackage.setCallbackParameters(adjustEvent.callbackParameters);
            defaultActivityPackage.setPartnerParameters(adjustEvent.partnerParameters);
        }
        return defaultActivityPackage;
    }

    public ActivityPackage buildInfoPackage(String str) {
        Map<String, String> idsParameters = getIdsParameters();
        addString(idsParameters, ShareConstants.FEED_SOURCE_PARAM, str);
        ActivityPackage defaultActivityPackage = getDefaultActivityPackage(ActivityKind.INFO);
        defaultActivityPackage.setPath("/sdk_info");
        defaultActivityPackage.setSuffix("");
        defaultActivityPackage.setParameters(idsParameters);
        return defaultActivityPackage;
    }

    public ActivityPackage buildSessionPackage(boolean z) {
        Map<String, String> attributableParameters = getAttributableParameters(z);
        ActivityPackage defaultActivityPackage = getDefaultActivityPackage(ActivityKind.SESSION);
        defaultActivityPackage.setPath("/session");
        defaultActivityPackage.setSuffix("");
        defaultActivityPackage.setParameters(attributableParameters);
        return defaultActivityPackage;
    }
}
