package kr.co.nexon.npaccount.secondpassword.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToySecondPasswordResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int authStatus;

        public ResultSet() {
        }
    }

    public NXToySecondPasswordResult() {
        this(0, "", "");
    }

    public NXToySecondPasswordResult(int i, String str) {
        this(i, str, "");
    }

    public NXToySecondPasswordResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }

    public NXToySecondPasswordResult(int i, String str, String str2, int i2) {
        super(i, str, str2, i2);
        this.result = new ResultSet();
    }
}
