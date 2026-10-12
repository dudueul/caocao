package com.nexon.nexonanalyticssdk;

import android.content.Context;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxSnapshotWorker implements Runnable {
    private NxLogCreator logCreator;
    private NxLogInfo logInfo = NxLogInfo.getInstance();
    private NxSystemInfo systemInfo = NxSystemInfo.getInstance();

    public NxSnapshotWorker(Context context) {
        this.systemInfo.initialize(context);
        this.logCreator = new NxLogCreator(this.logInfo);
    }

    public Map<String, Object> createSystemLogBody() {
        this.systemInfo.loadAppInfo();
        this.systemInfo.loadConnectState();
        this.systemInfo.loadDeviceInfo();
        this.systemInfo.loadMemoryInfo();
        this.systemInfo.loadStorageInfo();
        HashMap map = new HashMap();
        map.put(NxSystemInfo.KEY_OS_NAME, this.systemInfo.getOsName());
        map.put(NxSystemInfo.KEY_OS_PLATFORM, this.systemInfo.getOsPlatform());
        map.put("device_name", this.systemInfo.getDeviceName());
        map.put("app_version", this.systemInfo.getVersionName());
        map.put(NxSystemInfo.KEY_APP_VERSION_CODE, Integer.valueOf(this.systemInfo.getVersionCode()));
        map.put(NxSystemInfo.KEY_APP_LOCALE, this.systemInfo.getAppLocale());
        map.put(NxSystemInfo.KEY_COUNTRY_NAME, this.systemInfo.getCountryName());
        map.put(NxSystemInfo.KEY_TOTAL_STORAGE, this.systemInfo.getTotalStorage());
        map.put(NxSystemInfo.KEY_FREE_STORAGE, this.systemInfo.getFreeStorage());
        map.put(NxSystemInfo.KEY_USAGE_STORAGE, this.systemInfo.getUsageStorage());
        map.put(NxSystemInfo.KEY_TOTAL_MEMORY, this.systemInfo.getTotalMem());
        map.put(NxSystemInfo.KEY_FREE_MEMORY, this.systemInfo.getFreeMem());
        map.put(NxSystemInfo.KEY_USAGE_MEMORY, this.systemInfo.getUsageMem());
        map.put(NxSystemInfo.KEY_NETWORK_TYPE, this.systemInfo.getNetworkType());
        return map;
    }

    @Override // java.lang.Runnable
    public void run() {
        NxDatabase nxDatabase = NxDatabase.getInstance();
        this.logCreator.createHeaderLog();
        this.logCreator.addBodyLog(NxSystemInfo.KEY_SYSTEM_TYPE, createSystemLogBody());
        nxDatabase.insertLog(this.logCreator.toString(), this.logInfo.getCurrentLogKey(), SystemClock.uptimeMillis() - this.logInfo.getInitUpTime(), NxSystemInfo.KEY_SYSTEM_TYPE, false);
    }
}
