package kr.co.nexon.npaccount.push.result.v1;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyPushStatusResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String createdAt;
        public int pushStatus;
        public String status;

        public ResultSet() {
        }
    }

    public NXToyPushStatusResult() {
        this(0, "", "");
    }

    public NXToyPushStatusResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyPushStatusResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.OffPush.getValue());
        this.result = new ResultSet();
    }
}
