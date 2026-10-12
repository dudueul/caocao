package com.nexon.nexonanalyticssdk;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum NexonAnalytics {
    INSTANCE;

    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_STOP = 0;
    private NxMsgHandlerThread msgHandlerThread;
    private int curState = 0;
    NxStateCallback stateCallback = new NxStateCallback() { // from class: com.nexon.nexonanalyticssdk.NexonAnalytics.2
        @Override // com.nexon.nexonanalyticssdk.NxStateCallback
        public void setCurrentState(int i) {
            NexonAnalytics.this.curState = i;
            NxLogcat.i("Set current State : " + (i == 1 ? "Initialize" : "Stop"));
        }
    };

    NexonAnalytics() {
        if (this.msgHandlerThread == null) {
            this.msgHandlerThread = new NxMsgHandlerThread();
            this.msgHandlerThread.startAndWait();
        }
    }

    private void enqueueCoreLog(int i, Map<String, Object> map) {
        Handler nxMsgHandler = this.msgHandlerThread.getNxMsgHandler();
        Message messageObtainMessage = nxMsgHandler.obtainMessage();
        messageObtainMessage.what = i;
        messageObtainMessage.obj = map;
        if (nxMsgHandler.sendMessage(messageObtainMessage)) {
            return;
        }
        NxLogcat.e("Processing looper is exited!");
    }

    public static NexonAnalytics getInstance() {
        return INSTANCE;
    }

    private void initTestInfos() {
        NxTESTInfos nxTESTInfos = NxTESTInfos.getInstance();
        nxTESTInfos.setDeletedLogCount(0L);
        nxTESTInfos.setSendTryLogCount(0L);
        nxTESTInfos.setSendCompletedLogCount(0L);
        nxTESTInfos.setRemainLogConunt(0L);
    }

    public boolean enqueueErrorLog(int i, String str) {
        boolean z = true;
        NxLogcat.i("EnqueueErrorLog() called by NexonAnalyticsSDK");
        if (this.curState <= 0) {
            NxLogcat.e("NexonAnalyticsSDK is not Initialize!");
            z = false;
        }
        if (i == -9999) {
            NxLogcat.e("You not use this error code!");
            z = false;
        }
        HashMap map = new HashMap();
        if (z) {
            map.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 2);
            map.put(NxLogInfo.KEY_CREATE_UP_TIME, Long.valueOf(SystemClock.uptimeMillis()));
            map.put(NxLogInfo.KEY_ERROR_CODE, Integer.valueOf(i));
            if (str != null) {
                map.put(NxLogInfo.KEY_COMMENT, str);
            }
        } else {
            map.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 1);
        }
        enqueueCoreLog(3, map);
        return z;
    }

    public boolean enqueueFunnelLog(String str, Map<String, Object> map) {
        boolean z = true;
        NxLogcat.i("EnqueueFunnelLog() called by NexonAnalyticsSDK");
        if (this.curState <= 0) {
            NxLogcat.e("NexonAnalyticsSDK is not Initialize!");
            z = false;
        }
        if (str == null || str.isEmpty()) {
            NxLogcat.e("Funnel Name is empty! You have to fill in funnel name!");
            z = false;
        }
        if (z && !NxLogInfo.checkAndSetFunnelName(str)) {
            NxLogcat.w("Funnel Container already have that FunnelName! FunnelName : " + str);
            return true;
        }
        HashMap map2 = new HashMap();
        if (z) {
            map2.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 2);
            map2.put(NxLogInfo.KEY_CREATE_UP_TIME, Long.valueOf(SystemClock.uptimeMillis()));
            map2.put(NxLogInfo.KEY_FUNNEL_STAGE_NAME, str);
            if (map != null) {
                map2.put(NxLogInfo.KEY_FUNNEL_INFO, map);
            }
        } else {
            map2.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 1);
        }
        map2.put("type", NxLogInfo.KEY_MOBILE_FUNNEL);
        enqueueCoreLog(4, map2);
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean enqueueLog(String str, Map map) {
        boolean z = true;
        NxLogcat.i("EnqueueLog() called by NexonAnalyticsSDK");
        if (this.curState <= 0) {
            NxLogcat.e("NexonAnalyticsSDK is not Initialize!");
            z = false;
        }
        if (str == null || str.isEmpty()) {
            NxLogcat.e("Log type is null or empty! You have to fill in log type!");
            z = false;
            str = NxLogInfo.KEY_NULL_TYPE;
        }
        if (map == null) {
            NxLogcat.e("Log Body is null! You have to fill in body");
            z = false;
            map = new HashMap();
        } else if (map.isEmpty()) {
            NxLogcat.e("Log Body is empty! You have to fill in body");
            z = false;
        }
        if (z) {
            map.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 2);
            map.put(NxLogInfo.KEY_CREATE_UP_TIME, Long.valueOf(SystemClock.uptimeMillis()));
        } else {
            map.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 1);
        }
        map.put("type", str);
        enqueueCoreLog(4, map);
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean enqueueLogOnlyOnce(String str, Map map) {
        boolean z = true;
        NxLogcat.i("EnqueueLogOnlyOnce() called by NexonAnalyticsSDK");
        if (this.curState <= 0) {
            NxLogcat.e("NexonAnalyticsSDK is not Initialize!");
            z = false;
        }
        if (str == null || str.isEmpty()) {
            NxLogcat.e("Log type is null or empty! You have to fill in log type!");
            z = false;
            str = NxLogInfo.KEY_NULL_TYPE;
        }
        if (map == null) {
            NxLogcat.e("Log Body is null! You have to fill in body");
            z = false;
            map = new HashMap();
        } else if (map.isEmpty()) {
            NxLogcat.e("Log Body is empty! You have to fill in body");
            z = false;
        }
        if (z && !NxLogInfo.checkAndSetOnlyOnceType(str) && !str.equals(NxLogInfo.KEY_NULL_TYPE)) {
            NxLogcat.w("Containers already have that type! Type : " + str);
            return true;
        }
        if (z) {
            map.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 2);
            map.put(NxLogInfo.KEY_CREATE_UP_TIME, Long.valueOf(SystemClock.uptimeMillis()));
        } else {
            map.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 1);
        }
        map.put("type", NxLogInfo.ONLYONCE_PRE_TAG + str);
        enqueueCoreLog(4, map);
        return z;
    }

    public boolean enqueueNxLog(INxLog iNxLog) {
        boolean z = true;
        NxLogcat.i("EnqueueNxLog() called by NexonAnalyticsSDK");
        if (this.curState <= 0) {
            NxLogcat.e("NexonAnalyticsSDK is not Initialize!");
            z = false;
        }
        if (iNxLog == null) {
            NxLogcat.e("Parameter is Empty! You have to fill in Parameter(nxlog)!");
            z = false;
            iNxLog = new INxLog() { // from class: com.nexon.nexonanalyticssdk.NexonAnalytics.1
                @Override // com.nexon.nexonanalyticssdk.INxLog
                public Map getNxLogBody() {
                    return new HashMap();
                }

                @Override // com.nexon.nexonanalyticssdk.INxLog
                public String getTypeName() {
                    return NxLogInfo.KEY_NULL_TYPE;
                }
            };
        }
        String typeName = iNxLog.getTypeName();
        if (typeName == null || typeName.isEmpty()) {
            typeName = NxLogInfo.KEY_NULL_TYPE;
            z = false;
            NxLogcat.e("NXLog type is null or empty! you have to fill log type!");
        }
        Map<String, Object> nxLogBody = iNxLog.getNxLogBody();
        if (nxLogBody == null) {
            NxLogcat.e("NxLog's body is null! You have to fill in body");
            z = false;
            nxLogBody = new HashMap<>();
        } else if (nxLogBody.isEmpty()) {
            NxLogcat.e("NxLog's body is Empty! You have to fill in body");
            z = false;
        }
        if (z) {
            nxLogBody.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 2);
            nxLogBody.put(NxLogInfo.KEY_CREATE_UP_TIME, Long.valueOf(SystemClock.uptimeMillis()));
        } else {
            nxLogBody.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 1);
        }
        nxLogBody.put("type", typeName);
        enqueueCoreLog(4, nxLogBody);
        return z;
    }

    public boolean enqueueStageLog(int i, String str) {
        boolean z = true;
        NxLogcat.i("EnqueueStageLog() called by NexonAnalyticsSDK");
        if (this.curState <= 0) {
            NxLogcat.e("NexonAnalyticsSDK is not Initialize!");
            z = false;
        }
        if (i == -9999) {
            NxLogcat.e("You not use this stage code!");
            z = false;
        }
        HashMap map = new HashMap();
        if (z) {
            map.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 2);
            map.put(NxLogInfo.KEY_CREATE_UP_TIME, Long.valueOf(SystemClock.uptimeMillis()));
            map.put(NxLogInfo.KEY_STAGE_CODE, Integer.valueOf(i));
            if (str != null) {
                map.put(NxLogInfo.KEY_COMMENT, str);
            }
        } else {
            map.put(NxLogSummaryInfos.KEY_SUMMARY_TYPE, 1);
        }
        enqueueCoreLog(2, map);
        return z;
    }

    public HashMap<String, Object> getSDKResult_TEST() {
        NxTESTInfos nxTESTInfos = NxTESTInfos.getInstance();
        HashMap<String, Object> map = new HashMap<>();
        long deletedLogCount = nxTESTInfos.getDeletedLogCount();
        long sendTryLogCount = nxTESTInfos.getSendTryLogCount();
        long sendCompletedLogCount = nxTESTInfos.getSendCompletedLogCount();
        long remainLogConunt = nxTESTInfos.getRemainLogConunt();
        boolean zIsFirstSendCompletedFlag = nxTESTInfos.isFirstSendCompletedFlag();
        map.put("DeletedCount", Long.valueOf(deletedLogCount));
        map.put("TryCount", Long.valueOf(sendTryLogCount));
        map.put("CompletedCount", Long.valueOf(sendCompletedLogCount));
        map.put("RemainCount", Long.valueOf(remainLogConunt));
        map.put("isFirstOk", Boolean.valueOf(zIsFirstSendCompletedFlag));
        return map;
    }

    public boolean initialize(Context context, NxGameClientInfo nxGameClientInfo) {
        NxLogcat.i("Initialize() called by NexonAnalyticsSDK");
        if (nxGameClientInfo == null || nxGameClientInfo.serviceId == null || nxGameClientInfo.serviceId.isEmpty() || context == null) {
            NxLogcat.w("Parameter is Empty! You have to fill in Parameter(Context, NxGameClientInfo)!");
            return false;
        }
        NxLogSendWorker.setDomain(nxGameClientInfo.regionDomain);
        HashMap map = new HashMap();
        map.put(NxLogInfo.KEY_CREATE_UP_TIME, Long.valueOf(SystemClock.uptimeMillis()));
        map.put(NxLogInfo.KEY_GAMECLIENT_INFO, nxGameClientInfo.info());
        map.put("context", context);
        map.put(NxStateCallback.STATE_CALLBACK_KEY, this.stateCallback);
        this.curState = 1;
        enqueueCoreLog(1, map);
        return true;
    }

    public boolean initialize_TEST(Context context, NxGameClientInfo nxGameClientInfo, Map<String, Object> map) {
        NxLogcat.i("TEST_Initialize() called by NexonAnalyticsSDK");
        if (nxGameClientInfo == null || nxGameClientInfo.serviceId == null || nxGameClientInfo.serviceId.isEmpty() || context == null) {
            NxLogcat.w("Parameter is Empty! You have to fill in Parameter(Context, NxGameClientInfo)!");
            return false;
        }
        HashMap map2 = new HashMap();
        map2.put(NxLogInfo.KEY_CREATE_UP_TIME, Long.valueOf(SystemClock.uptimeMillis()));
        map2.put(NxLogInfo.KEY_GAMECLIENT_INFO, nxGameClientInfo.info());
        map2.put("context", context);
        map2.put(NxStateCallback.STATE_CALLBACK_KEY, this.stateCallback);
        map2.put("configInfo", map);
        initTestInfos();
        this.curState = 1;
        enqueueCoreLog(6, map2);
        return true;
    }

    public boolean setUserInfo(NxUserInfo nxUserInfo) {
        if (this.curState <= 0) {
            NxLogcat.e("NexonAnalyticsSDK is not Initialize!");
            return false;
        }
        Map<String, Object> mapInfo = nxUserInfo != null ? nxUserInfo.info() : null;
        NxLogcat.i("NexonAnalyticsSDK call to set UserInfo!");
        enqueueCoreLog(5, mapInfo);
        return true;
    }
}
