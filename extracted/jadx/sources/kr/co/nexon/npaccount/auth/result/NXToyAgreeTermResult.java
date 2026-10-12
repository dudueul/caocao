package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyAgreeTermResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String agreeAt;
        public String npToken;

        public ResultSet() {
        }
    }

    public NXToyAgreeTermResult() {
        this(0, "", "");
    }

    public NXToyAgreeTermResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyAgreeTermResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.AgreeTerms.getValue());
        this.result = new ResultSet();
    }
}
