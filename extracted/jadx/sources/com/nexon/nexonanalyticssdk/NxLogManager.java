package com.nexon.nexonanalyticssdk;

import android.content.Context;
import android.os.SystemClock;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxLogManager {
    private NxDatabase database;
    private NxLogCreator logCreator;
    private NxLogInfo logInfo;
    private NxScheduler scheduler;

    NxLogManager() {
    }

    private void enqueueCoreLog(long j, String str, Map<String, Object> map, boolean z) {
        this.logCreator.createHeaderLog();
        this.logCreator.addBodyLog(str, map);
        long initUpTime = j - this.logInfo.getInitUpTime();
        this.scheduler.executeLogSenderInOverCounter();
        this.database.insertLog(this.logCreator.toString(), this.logInfo.getCurrentLogKey(), initUpTime, str, z);
    }

    void enqueueErrorLog(Map<String, Object> map) {
        int iIntValue = ((Integer) map.remove(NxLogSummaryInfos.KEY_SUMMARY_TYPE)).intValue();
        this.database.insertSummaryInfo(NxLogInfo.KEY_STABILITY_TYPE, iIntValue);
        if (iIntValue == 1) {
            return;
        }
        long jLongValue = ((Long) map.remove(NxLogInfo.KEY_CREATE_UP_TIME)).longValue();
        int iIntValue2 = ((Integer) map.remove(NxLogInfo.KEY_ERROR_CODE)).intValue();
        String str = (String) map.remove(NxLogInfo.KEY_COMMENT);
        map.put(NxLogInfo.KEY_STAGE_CODE, Integer.valueOf(this.logInfo.getPrevStage()));
        map.put(NxLogInfo.KEY_PREV_STAGE_CODE, Integer.valueOf(NxLogInfo.VALUE_STAGE_INVALID_CODE));
        map.put(NxLogInfo.KEY_ERROR_CODE, Integer.valueOf(iIntValue2));
        if (str != null) {
            map.put(NxLogInfo.KEY_COMMENT, str);
        }
        enqueueCoreLog(jLongValue, NxLogInfo.KEY_STABILITY_TYPE, map, true);
    }

    void enqueueLog(Map<String, Object> map) {
        String str = (String) map.remove("type");
        int iIntValue = ((Integer) map.remove(NxLogSummaryInfos.KEY_SUMMARY_TYPE)).intValue();
        this.database.insertSummaryInfo(str, iIntValue);
        if (iIntValue == 1) {
            return;
        }
        long jLongValue = ((Long) map.remove(NxLogInfo.KEY_CREATE_UP_TIME)).longValue();
        if (str.equals(NxLogInfo.KEY_MOBILE_FUNNEL)) {
            NxSystemInfo nxSystemInfo = NxSystemInfo.getInstance();
            map = nxSystemInfo.fillDeviceInfo(nxSystemInfo.fillAppInfo(map));
            this.database.insertFunnelName((String) map.get(NxLogInfo.KEY_FUNNEL_STAGE_NAME));
        }
        enqueueCoreLog(jLongValue, str, map, true);
    }

    void enqueueStageLog(Map<String, Object> map) {
        int iIntValue = ((Integer) map.remove(NxLogSummaryInfos.KEY_SUMMARY_TYPE)).intValue();
        this.database.insertSummaryInfo(NxLogInfo.KEY_STABILITY_TYPE, iIntValue);
        if (iIntValue == 1) {
            return;
        }
        long jLongValue = ((Long) map.remove(NxLogInfo.KEY_CREATE_UP_TIME)).longValue();
        int iIntValue2 = ((Integer) map.remove(NxLogInfo.KEY_STAGE_CODE)).intValue();
        String str = (String) map.remove(NxLogInfo.KEY_COMMENT);
        map.put(NxLogInfo.KEY_STAGE_CODE, Integer.valueOf(iIntValue2));
        map.put(NxLogInfo.KEY_PREV_STAGE_CODE, Integer.valueOf(this.logInfo.getPrevStage()));
        map.put(NxLogInfo.KEY_ERROR_CODE, Integer.valueOf(NxLogInfo.VALUE_STAGE_INVALID_CODE));
        if (str != null) {
            map.put(NxLogInfo.KEY_COMMENT, str);
        }
        this.logInfo.setPrevStage(iIntValue2);
        enqueueCoreLog(jLongValue, NxLogInfo.KEY_STABILITY_TYPE, map, true);
    }

    void initialize(Map<String, Object> map) {
        NxLogcat.i("initialize Mode is handling ");
        Context context = (Context) map.remove("context");
        Map<String, Object> map2 = (Map) map.remove(NxLogInfo.KEY_GAMECLIENT_INFO);
        long jLongValue = ((Long) map.remove(NxLogInfo.KEY_CREATE_UP_TIME)).longValue();
        NxStateCallback nxStateCallback = (NxStateCallback) map.remove(NxStateCallback.STATE_CALLBACK_KEY);
        this.logInfo = NxLogInfo.getInstance();
        NxDatabase.create(context);
        this.database = NxDatabase.getInstance();
        this.database.loadFunnelNames();
        this.scheduler = new NxScheduler(context);
        NxNetwork nxNetwork = NxNetwork.getInstance();
        this.logCreator = new NxLogCreator(this.logInfo);
        nxNetwork.initialize();
        this.logInfo.initialize(context, map2);
        this.scheduler.setStateCallback(nxStateCallback);
        this.database.insertBaseTime(System.currentTimeMillis());
        long latestLogKey = this.database.getLatestLogKey();
        this.logInfo.setInitUpTime(jLongValue);
        this.logInfo.setCurrentLogKey(latestLogKey);
        map.put("moduleVersion", NxLogInfo.VALUE_NEXON_ANALYTICS_SDK_VERSION);
        enqueueCoreLog(SystemClock.uptimeMillis(), NxLogInfo.KEY_INITIALIZE_TYPE, map, false);
        this.scheduler.cancelAll();
        this.scheduler.startAll();
    }

    void initialize_TEST(Map<String, Object> map) {
        Context context = (Context) map.get("context");
        Map<String, Object> map2 = (Map) map.remove("configInfo");
        context.deleteDatabase(NxDatabase.DATABASE_NAME);
        initialize(map);
        this.scheduler.setUpConfigInfo_TEST(map2);
    }

    void setUserInfo(Map<String, Object> map) {
        this.logInfo.setUserInfo(map);
    }
}
