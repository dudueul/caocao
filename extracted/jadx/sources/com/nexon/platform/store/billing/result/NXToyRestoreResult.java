package com.nexon.platform.store.billing.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyRestoreResult extends NXToyResult {
    public List<RestoreStamp> restore_stamps;

    public class RestoreStamp extends NXClassInfo {
        public List<String> product_ids;
        public String service_payload;
        public String stamp_id;
        public String stamp_payload;
        public int tx_status;

        public RestoreStamp() {
        }
    }

    public NXToyRestoreResult() {
        this.restore_stamps = new ArrayList();
    }

    public NXToyRestoreResult(int i, String str) {
        super(i, str);
        this.restore_stamps = new ArrayList();
    }

    public NXToyRestoreResult(int i, String str, String str2) {
        super(i, str, str2);
        this.restore_stamps = new ArrayList();
    }
}
