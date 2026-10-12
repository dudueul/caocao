package com.nexon.nexonanalyticssdk;

import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxLogCreator {
    private Map<String, Object> log;
    private NxLogInfo logInfo;

    public NxLogCreator(NxLogInfo nxLogInfo) {
        this.logInfo = nxLogInfo;
    }

    public boolean addBodyLog(String str, Map<String, Object> map) {
        if (str == null || str.isEmpty() || map == null) {
            return false;
        }
        this.log.put("type", str);
        this.log.put(str, map);
        return true;
    }

    public Map<String, Object> createHeaderLog() {
        this.log = new HashMap();
        this.log.put(NxLogInfo.KEY_SEQUENCE_NO, Long.valueOf(this.logInfo.getSequenceNo()));
        this.log.put(NxLogInfo.KEY_SESSION_ID, String.format("N%d", Long.valueOf(this.logInfo.getCurrentLogKey() + TapjoyConstants.TIMER_INCREMENT)));
        this.log.put(NxLogInfo.KEY_CREATE_DATE, NxLogInfo.VALUE_DEFAULT_CREATE_DATE);
        this.log.put(NxLogInfo.KEY_TIME_SYNC, NxLogInfo.VALUE_DEFAULT_TIME_SYNC);
        this.log.put(NxLogInfo.KEY_CLIENT_IP_ADDRESS, this.logInfo.getClientIPAddress());
        this.log.put(NxLogInfo.KEY_CURRENT_STEP, 4);
        this.log.put(NxLogInfo.KEY_UUID2, this.logInfo.getUuid2());
        this.log.put(NxLogInfo.KEY_ADID, this.logInfo.getAdid());
        this.log.put("version", NxLogInfo.VALUE_NXLOG_VERSION);
        this.log.putAll(this.logInfo.getGameClientInfo());
        if (this.logInfo.getUserInfo() != null) {
            this.log.putAll(this.logInfo.getUserInfo());
        }
        return this.log;
    }

    public Map<String, Object> getLog() {
        return this.log;
    }

    public String toString() {
        return NxUtils.convertMapToString(this.log);
    }
}
