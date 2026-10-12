package com.nexon.nexonanalyticssdk;

import android.os.SystemClock;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxLogSummaryWorker implements Runnable {
    NxLogSummaryWorker() {
    }

    public static boolean initalizeSummaryBeginTime() {
        NxLogInfo nxLogInfo = NxLogInfo.getInstance();
        NxDatabase nxDatabase = NxDatabase.getInstance();
        if (nxDatabase.getSummaryBeginTime() != 0) {
            return false;
        }
        nxDatabase.insertSummaryBeginTime(nxLogInfo.getCurrentTime());
        return true;
    }

    public Map<String, Object> createSummaryLogBody() {
        NxLogInfo nxLogInfo = NxLogInfo.getInstance();
        NxDatabase nxDatabase = NxDatabase.getInstance();
        Map<String, Object> mapPopSummaryInfo = nxDatabase.popSummaryInfo();
        if (mapPopSummaryInfo == null) {
            return null;
        }
        long summaryBeginTime = nxDatabase.getSummaryBeginTime();
        long currentTime = nxLogInfo.getCurrentTime();
        String strConvertToStringTime = NxUtils.convertToStringTime(summaryBeginTime);
        String strConvertToStringTime2 = NxUtils.convertToStringTime(currentTime);
        nxDatabase.updateSummaryBeginTime(currentTime);
        mapPopSummaryInfo.put(NxLogSummaryInfos.KEY_BEGIN_DATETIME, strConvertToStringTime);
        mapPopSummaryInfo.put(NxLogSummaryInfos.KEY_END_DATETIME, strConvertToStringTime2);
        mapPopSummaryInfo.put(NxLogSummaryInfos.KEY_LOG_SERVER_ADDRESS, NxLogSendWorker.logProtocol);
        mapPopSummaryInfo.put(NxLogSummaryInfos.KEY_LOG_SERVER_PROTOCOL, NxLogSendWorker.logSendHostUri);
        return mapPopSummaryInfo;
    }

    @Override // java.lang.Runnable
    public void run() {
        NxLogInfo nxLogInfo = NxLogInfo.getInstance();
        NxDatabase nxDatabase = NxDatabase.getInstance();
        Map<String, Object> mapCreateSummaryLogBody = createSummaryLogBody();
        if (mapCreateSummaryLogBody == null) {
            return;
        }
        NxLogCreator nxLogCreator = new NxLogCreator(nxLogInfo);
        nxLogCreator.createHeaderLog();
        nxLogCreator.addBodyLog(NxLogSummaryInfos.TYPE_SDK_SUMMARY, mapCreateSummaryLogBody);
        nxDatabase.insertLog(nxLogCreator.toString(), nxLogInfo.getCurrentLogKey(), SystemClock.uptimeMillis() - nxLogInfo.getInitUpTime(), NxLogSummaryInfos.TYPE_SDK_SUMMARY, false);
    }
}
