package com.nexon.nexonanalyticssdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum NxSystemInfo {
    INSTANCE;

    public static final String KEY_APP_LOCALE = "app_locale";
    public static final String KEY_APP_VERSION = "app_version";
    public static final String KEY_APP_VERSION_CODE = "app_version_code";
    public static final String KEY_COUNTRY_NAME = "country_name";
    public static final String KEY_DEVICE_NAME = "device_name";
    public static final String KEY_FREE_MEMORY = "free_memory";
    public static final String KEY_FREE_STORAGE = "free_storage";
    public static final String KEY_NETWORK_TYPE = "network_type";
    public static final String KEY_OS_NAME = "os_name";
    public static final String KEY_OS_PLATFORM = "os_platform";
    public static final String KEY_SYSTEM_TYPE = "NXLog_SystemSnapshotM";
    public static final String KEY_TOTAL_MEMORY = "total_memory";
    public static final String KEY_TOTAL_STORAGE = "total_storage";
    public static final String KEY_USAGE_MEMORY = "usage_memory";
    public static final String KEY_USAGE_STORAGE = "usage_storage";
    public static final String KEY_UUID = "uuid";
    private static final String VALUE_NETWORK_TYPE_MOBILE_CONNECT = "mobile";
    private static final String VALUE_NETWORK_TYPE_NOT_CONNECT = "not";
    private static final String VALUE_NETWORK_TYPE_WIFI_CONNECT = "wifi";
    private static Context applicationContext = null;
    private String appLocale;
    private String countryName;
    private String deviceName;
    private String freeMem;
    private String freeStorage;
    private boolean isLoadedAppInfo;
    private boolean isLoadedDeviceInfo;
    private String networkType;
    private String osName;
    private String osPlatform;
    private String totalMem;
    private String totalStorage;
    private String usageMem;
    private String usageStorage;
    private String uuid;
    private int versionCode;
    private String versionName;

    private boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static NxSystemInfo getInstance() {
        return INSTANCE;
    }

    private long loadFreeStorage() {
        long blockSizeLong;
        long availableBlocksLong;
        StatFs statFs = new StatFs((externalMemoryAvailable() ? Environment.getExternalStorageDirectory() : Environment.getDataDirectory()).getPath());
        if (Build.VERSION.SDK_INT < 18) {
            blockSizeLong = statFs.getBlockSize();
            availableBlocksLong = statFs.getAvailableBlocks();
        } else {
            blockSizeLong = statFs.getBlockSizeLong();
            availableBlocksLong = statFs.getAvailableBlocksLong();
        }
        return availableBlocksLong * blockSizeLong;
    }

    private long loadTotalStorage() {
        long blockSizeLong;
        long blockCountLong;
        StatFs statFs = new StatFs((externalMemoryAvailable() ? Environment.getExternalStorageDirectory() : Environment.getDataDirectory()).getPath());
        if (Build.VERSION.SDK_INT < 18) {
            blockSizeLong = statFs.getBlockSize();
            blockCountLong = statFs.getBlockCount();
        } else {
            blockSizeLong = statFs.getBlockSizeLong();
            blockCountLong = statFs.getBlockCountLong();
        }
        return blockCountLong * blockSizeLong;
    }

    public Map<String, Object> fillAppInfo(Map<String, Object> map) {
        if (!this.isLoadedAppInfo) {
            loadAppInfo();
        }
        map.put("app_version", this.versionName);
        map.put(KEY_APP_VERSION_CODE, Integer.valueOf(this.versionCode));
        map.put(KEY_APP_LOCALE, this.appLocale);
        map.put(KEY_COUNTRY_NAME, this.countryName);
        map.put(KEY_UUID, this.uuid);
        return map;
    }

    public Map<String, Object> fillDeviceInfo(Map<String, Object> map) {
        if (!this.isLoadedDeviceInfo) {
            loadDeviceInfo();
        }
        map.put(KEY_OS_NAME, this.osName);
        map.put(KEY_OS_PLATFORM, this.osPlatform);
        map.put("device_name", this.deviceName);
        return map;
    }

    public String getAppLocale() {
        return this.appLocale;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getFreeMem() {
        return this.freeMem;
    }

    public String getFreeStorage() {
        return this.freeStorage;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public String getOsName() {
        return this.osName;
    }

    public String getOsPlatform() {
        return this.osPlatform;
    }

    public String getTotalMem() {
        return this.totalMem;
    }

    public String getTotalStorage() {
        return this.totalStorage;
    }

    public String getUsageMem() {
        return this.usageMem;
    }

    public String getUsageStorage() {
        return this.usageStorage;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void initialize(Context context) {
        applicationContext = context;
    }

    public boolean loadAppInfo() {
        if (applicationContext == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0);
            this.versionName = packageInfo.versionName;
            this.versionCode = packageInfo.versionCode;
            NxLogcat.i("app_version :" + this.versionName + ", app_version_code:" + this.versionCode);
            this.appLocale = Locale.getDefault().toString();
            this.countryName = Locale.getDefault().getCountry();
            NxLogcat.i("app_locale:" + this.appLocale + ", country_name:" + this.countryName);
            this.uuid = NxLogInfo.getInstance().getMid();
            this.isLoadedAppInfo = true;
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            NxLogcat.e("loadAppInfo NameNotFoundException : " + e.getMessage());
            return false;
        }
    }

    public void loadConnectState() {
        int i = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        boolean zIsConnected = false;
        boolean zIsConnected2 = false;
        if (Build.VERSION.SDK_INT > 21) {
            Network[] allNetworks = connectivityManager.getAllNetworks();
            int length = allNetworks.length;
            while (i < length) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(allNetworks[i]);
                if (networkInfo.getType() == 1) {
                    zIsConnected |= networkInfo.isConnected();
                }
                if (networkInfo.getType() == 0) {
                    zIsConnected2 |= networkInfo.isConnected();
                }
                i++;
            }
        } else {
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            int length2 = allNetworkInfo.length;
            while (i < length2) {
                NetworkInfo networkInfo2 = allNetworkInfo[i];
                if (networkInfo2.getType() == 1) {
                    zIsConnected |= networkInfo2.isConnected();
                }
                if (networkInfo2.getType() == 0) {
                    zIsConnected2 |= networkInfo2.isConnected();
                }
                i++;
            }
        }
        this.networkType = VALUE_NETWORK_TYPE_NOT_CONNECT;
        if (zIsConnected) {
            this.networkType = "wifi";
        } else {
            if (zIsConnected || !zIsConnected2) {
                return;
            }
            this.networkType = "mobile";
        }
    }

    public void loadDeviceInfo() {
        this.osName = "Android " + Build.VERSION.RELEASE;
        this.osPlatform = "android";
        this.deviceName = Build.MODEL;
        NxLogcat.i("OS name :" + this.osName + ", OS Platform : " + this.osPlatform + ", Device Model : " + this.deviceName);
        this.isLoadedDeviceInfo = true;
    }

    public void loadMemoryInfo() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) applicationContext.getSystemService("activity")).getMemoryInfo(memoryInfo);
        this.totalMem = (memoryInfo.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) + "MB";
        this.freeMem = (memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) + "MB";
        this.usageMem = ((memoryInfo.totalMem - memoryInfo.availMem) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) + "MB";
    }

    public void loadStorageInfo() {
        long jLoadTotalStorage = loadTotalStorage();
        long jLoadFreeStorage = loadFreeStorage();
        this.totalStorage = (jLoadTotalStorage / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) + "MB";
        this.freeStorage = (jLoadFreeStorage / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) + "MB";
        this.usageStorage = ((jLoadTotalStorage - jLoadFreeStorage) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) + "MB";
    }
}
