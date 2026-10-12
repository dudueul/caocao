package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyLoginTypeResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int loginType;

        public ResultSet() {
        }
    }

    public NXToyLoginTypeResult() {
        this.result = new ResultSet();
    }

    public NXToyLoginTypeResult(int i, String str) {
        super(i, str);
        this.result = new ResultSet();
    }

    public NXToyLoginTypeResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }
}
