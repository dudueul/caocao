package kr.co.nexon.npaccount.secondpassword.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetSecondPasswordInfoResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int authStatus;
        public String email;
        public int isReleased;
        public int isRequired;
        public NXToyTerm terms;

        public ResultSet() {
        }
    }

    public NXToyGetSecondPasswordInfoResult() {
        this(0, "", "");
    }

    public NXToyGetSecondPasswordInfoResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyGetSecondPasswordInfoResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetSecondPasswordInfo.getValue());
        this.result = new ResultSet();
    }
}
