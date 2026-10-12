package com.adjust.sdk;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class DeviceInfo {
    String abi;
    String androidId;
    String apiLevel;
    String appInstallTime;
    String appUpdateTime;
    String appVersion;
    String buildName;
    String clientSdk;
    String country;
    String deviceManufacturer;
    String deviceName;
    String deviceType;
    String displayHeight;
    String displayWidth;
    String fbAttributionId;
    String hardwareName;
    Boolean isTrackingEnabled;
    String language;
    String macSha1;
    String macShortMd5;
    private boolean nonGoogleIdsRead = false;
    String osName;
    String osVersion;
    String packageName;
    String playAdId;
    Map<String, String> pluginKeys;
    String screenDensity;
    String screenFormat;
    String screenSize;
    String vmInstructionSet;

    DeviceInfo(Context context, String str) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = Util.getLocale(configuration);
        int i = configuration.screenLayout;
        context.getContentResolver();
        reloadDeviceIds(context);
        this.packageName = getPackageName(context);
        this.appVersion = getAppVersion(context);
        this.deviceType = getDeviceType(i);
        this.deviceName = getDeviceName();
        this.deviceManufacturer = getDeviceManufacturer();
        this.osName = getOsName();
        this.osVersion = getOsVersion();
        this.apiLevel = getApiLevel();
        this.language = getLanguage(locale);
        this.country = getCountry(locale);
        this.screenSize = getScreenSize(i);
        this.screenFormat = getScreenFormat(i);
        this.screenDensity = getScreenDensity(displayMetrics);
        this.displayWidth = getDisplayWidth(displayMetrics);
        this.displayHeight = getDisplayHeight(displayMetrics);
        this.clientSdk = getClientSdk(str);
        this.fbAttributionId = getFacebookAttributionId(context);
        this.pluginKeys = Util.getPluginKeys(context);
        this.hardwareName = getHardwareName();
        this.abi = getABI();
        this.buildName = getBuildName();
        this.vmInstructionSet = getVmInstructionSet();
        this.appInstallTime = getAppInstallTime(context);
        this.appUpdateTime = getAppUpdateTime(context);
    }

    private String getABI() {
        String[] supportedAbis = Util.getSupportedAbis();
        return (supportedAbis == null || supportedAbis.length == 0) ? Util.getCpuAbi() : supportedAbis[0];
    }

    private String getApiLevel() {
        return "" + Build.VERSION.SDK_INT;
    }

    private String getAppInstallTime(Context context) {
        try {
            return Util.dateFormatter.format(new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).firstInstallTime));
        } catch (Exception e) {
            return null;
        }
    }

    private String getAppUpdateTime(Context context) {
        try {
            return Util.dateFormatter.format(new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).lastUpdateTime));
        } catch (Exception e) {
            return null;
        }
    }

    private String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }

    private String getBuildName() {
        return Build.ID;
    }

    private String getClientSdk(String str) {
        return str == null ? Constants.CLIENT_SDK : Util.formatString("%s@%s", str, Constants.CLIENT_SDK);
    }

    private String getCountry(Locale locale) {
        return locale.getCountry();
    }

    private String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    private String getDeviceName() {
        return Build.MODEL;
    }

    private String getDeviceType(int i) {
        switch (i & 15) {
            case 1:
            case 2:
                return "phone";
            case 3:
            case 4:
                return "tablet";
            default:
                return null;
        }
    }

    private String getDisplayHeight(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.heightPixels);
    }

    private String getDisplayWidth(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.widthPixels);
    }

    private String getFacebookAttributionId(Context context) {
        String string;
        try {
            Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{"aid"}, null, null, null);
            if (cursorQuery == null) {
                string = null;
            } else if (cursorQuery.moveToFirst()) {
                string = cursorQuery.getString(cursorQuery.getColumnIndex("aid"));
                cursorQuery.close();
            } else {
                cursorQuery.close();
                string = null;
            }
            return string;
        } catch (Exception e) {
            return null;
        }
    }

    private String getHardwareName() {
        return Build.DISPLAY;
    }

    private String getLanguage(Locale locale) {
        return locale.getLanguage();
    }

    private String getMacAddress(Context context, boolean z) {
        if (z) {
            return null;
        }
        if (!Util.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            AdjustFactory.getLogger().warn("Missing permission: ACCESS_WIFI_STATE", new Object[0]);
        }
        return Util.getMacAddress(context);
    }

    private String getMacSha1(String str) {
        if (str == null) {
            return null;
        }
        return Util.sha1(str);
    }

    private String getMacShortMd5(String str) {
        if (str == null) {
            return null;
        }
        return Util.md5(str.replaceAll(":", ""));
    }

    private String getOsName() {
        return "android";
    }

    private String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    private String getPackageName(Context context) {
        return context.getPackageName();
    }

    private String getScreenDensity(DisplayMetrics displayMetrics) {
        int i = displayMetrics.densityDpi;
        if (i == 0) {
            return null;
        }
        if (i < 140) {
            return Constants.LOW;
        }
        return i > 200 ? Constants.HIGH : Constants.MEDIUM;
    }

    private String getScreenFormat(int i) {
        switch (i & 48) {
            case 16:
                return "normal";
            case 32:
                return Constants.LONG;
            default:
                return null;
        }
    }

    private String getScreenSize(int i) {
        switch (i & 15) {
            case 1:
                return Constants.SMALL;
            case 2:
                return "normal";
            case 3:
                return Constants.LARGE;
            case 4:
                return Constants.XLARGE;
            default:
                return null;
        }
    }

    private String getVmInstructionSet() {
        return Util.getVmInstructionSet();
    }

    void reloadDeviceIds(Context context) {
        this.isTrackingEnabled = Util.isPlayTrackingEnabled(context);
        this.playAdId = Util.getPlayAdId(context);
        if (this.playAdId != null || this.nonGoogleIdsRead) {
            return;
        }
        if (!Util.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            AdjustFactory.getLogger().warn("Missing permission: ACCESS_WIFI_STATE", new Object[0]);
        }
        String macAddress = Util.getMacAddress(context);
        this.macSha1 = getMacSha1(macAddress);
        this.macShortMd5 = getMacShortMd5(macAddress);
        this.androidId = Util.getAndroidId(context);
        this.nonGoogleIdsRead = true;
    }
}
