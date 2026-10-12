package kr.co.nexon.android.sns.nxnet.api.result;

import com.nexon.core.requestpostman.result.NXClassInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetErrorResult extends NXClassInfo {
    public NXNetErrorResultSet error;

    public class NXNetErrorResultSet extends NXClassInfo {
        public int code;
        public String message;
        public String type;

        public NXNetErrorResultSet() {
        }
    }
}
