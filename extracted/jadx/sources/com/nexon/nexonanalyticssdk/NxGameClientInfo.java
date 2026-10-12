package com.nexon.nexonanalyticssdk;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NxGameClientInfo {
    public String accountNo;
    public String mid;
    public String nxlogVersion;
    public String regionDomain;
    public String serviceId;
    public String sessionId;

    public Map<String, Object> info() {
        HashMap map = new HashMap();
        map.put(NxLogInfo.KEY_SERVICE_ID, this.serviceId);
        if (this.sessionId != null && !this.sessionId.isEmpty()) {
            map.put(NxLogInfo.KEY_SESSION_ID, this.sessionId);
        }
        if (this.mid != null && !this.mid.isEmpty()) {
            map.put(NxLogInfo.KEY_MID, this.mid);
        }
        if (this.accountNo != null && !this.accountNo.isEmpty()) {
            map.put(NxLogInfo.KEY_ACCOUNTNO, this.accountNo);
        }
        if (this.nxlogVersion != null && !this.nxlogVersion.isEmpty()) {
            map.put("version", this.nxlogVersion);
        }
        return map;
    }
}
