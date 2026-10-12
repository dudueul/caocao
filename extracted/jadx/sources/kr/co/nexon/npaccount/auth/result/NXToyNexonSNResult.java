package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyNexonSNResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String memSNUuid;
        public long nexonSN;
        public String nexonSNString;

        public ResultSet() {
        }
    }

    public NXToyNexonSNResult() {
        this(0, "", "");
    }

    public NXToyNexonSNResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyNexonSNResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetNexonSN.getValue());
        this.result = new ResultSet();
    }

    public NXToyNexonSNResult(int i, String str, String str2, int i2) {
        super(i, str, str2, i2);
        this.result = new ResultSet();
    }
}
