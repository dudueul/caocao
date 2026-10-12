package com.nexon.nexonanalyticssdk;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NxUserInfo {
    public String accountNo;
    public Long npsn;
    public String sessionId;
    public String umk;

    public Map<String, Object> info() {
        HashMap map = new HashMap();
        if (this.npsn.longValue() != 0) {
            map.put(NxLogInfo.KEY_NPSN, this.npsn);
        }
        if (this.sessionId != null && !this.sessionId.isEmpty()) {
            map.put(NxLogInfo.KEY_SESSION_ID, this.sessionId);
        }
        if (this.accountNo != null && !this.accountNo.isEmpty()) {
            map.put(NxLogInfo.KEY_ACCOUNTNO, this.accountNo);
        }
        if (this.umk != null && !this.umk.isEmpty()) {
            map.put(NxLogInfo.KEY_UMK, this.umk);
        }
        return map;
    }
}
