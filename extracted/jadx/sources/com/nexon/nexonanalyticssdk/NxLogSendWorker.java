package com.nexon.nexonanalyticssdk;

import com.adjust.sdk.Constants;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxLogSendWorker implements Runnable {
    public static int retryDelayTime = 500;
    public static String logProtocol = Constants.SCHEME;
    public static String logSendHostUri = "jp.livelog.nexon.com";
    private int sendLimitCount = 100;
    private long curLogKey = 0;
    private String logSendURLFormat = "%s://%s/client.all";

    NxLogSendWorker() {
    }

    public static void setDomain(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (str.contains("http://")) {
            str = str.replace("http://", "");
            setProtocol("http");
        } else if (str.contains("https://")) {
            str = str.replace("https://", "");
            setProtocol(Constants.SCHEME);
        }
        logSendHostUri = str;
    }

    public static void setProtocol(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (str.equals("http") || str.equals(Constants.SCHEME)) {
            logProtocol = str;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        NxLogInfo nxLogInfo = NxLogInfo.getInstance();
        NxDatabase nxDatabase = NxDatabase.getInstance();
        NxNetwork nxNetwork = NxNetwork.getInstance();
        NxTESTInfos nxTESTInfos = NxTESTInfos.getInstance();
        this.curLogKey = nxLogInfo.getCurrentLogKey();
        long oldestLogKey = 0;
        while (true) {
            if (oldestLogKey == 0) {
                oldestLogKey = nxDatabase.getOldestLogKey();
            }
            int logCount = nxDatabase.getLogCount();
            int storageLimitCount = nxDatabase.getStorageLimitCount();
            if (logCount == 0) {
                return;
            }
            if (logCount >= storageLimitCount) {
                List<Object[]> listSelectLogToDelete = nxDatabase.selectLogToDelete(logCount - storageLimitCount);
                nxDatabase.insertBulkSummaryInfo(listSelectLogToDelete, 4);
                long jDeleteLog = nxDatabase.deleteLog(listSelectLogToDelete);
                logCount = storageLimitCount;
                NxLogcat.d("NxLogCreator is Deleted! NxLogCreator is only remained until " + storageLimitCount);
                nxTESTInfos.addDeletedLogCount(jDeleteLog);
            }
            nxTESTInfos.setRemainLogConunt(logCount);
            int iLoadNxLogInfo = nxDatabase.loadNxLogInfo(oldestLogKey, this.sendLimitCount);
            nxTESTInfos.setSendTryLogCount(iLoadNxLogInfo);
            if (iLoadNxLogInfo != 0) {
                String bulkLog = nxDatabase.getBulkLog();
                String str = String.format(this.logSendURLFormat, logProtocol, logSendHostUri);
                NxLogcat.i("sending Address : " + str);
                if (!nxNetwork.postNxLog(bulkLog, str)) {
                    if (!nxTESTInfos.isFirstSendCompletedFlag()) {
                        nxTESTInfos.setFirstSendCompletedFlag(true);
                    }
                    try {
                        Thread.sleep(retryDelayTime);
                        return;
                    } catch (InterruptedException e) {
                        NxLogcat.e("Interrupt Exception : " + e.getMessage());
                        return;
                    }
                }
                nxDatabase.insertBulkSummaryInfo(nxDatabase.getBulkNxLogType(), 3);
                NxLogcat.d("Log send Ok! So Transmitted Logs Delete!");
                long jDeleteLog2 = nxDatabase.deleteLog(nxDatabase.getLogIds());
                int logCount2 = nxDatabase.getLogCount();
                nxTESTInfos.addSendCompletedLogCount(jDeleteLog2);
                nxTESTInfos.setRemainLogConunt(logCount2);
                if (!nxTESTInfos.isFirstSendCompletedFlag()) {
                    nxTESTInfos.setFirstSendCompletedFlag(true);
                }
            } else {
                if (oldestLogKey == this.curLogKey) {
                    return;
                }
                nxDatabase.deleteLogKey(oldestLogKey);
                oldestLogKey = 0;
            }
        }
    }

    public void setRetryDelayTime(Integer num) {
        if (num != null) {
            retryDelayTime = num.intValue();
        }
    }

    public void setSendLimitCount(int i) {
        if (i == 0) {
            return;
        }
        this.sendLimitCount = i;
    }
}
