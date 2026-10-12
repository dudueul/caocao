package kr.co.nexon.npaccount.secondpassword.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyVerifySecondPasswordResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int authStatus;
        public int remainedCount;

        public ResultSet() {
        }
    }

    public NXToyVerifySecondPasswordResult() {
        this(0, "", "");
    }

    public NXToyVerifySecondPasswordResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyVerifySecondPasswordResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.VerifySecondPassword.getValue());
        this.result = new ResultSet();
    }
}
