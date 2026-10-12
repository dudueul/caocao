package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyLoginResult extends NXToyResult {
    public static final int LOGIN_RESULT_TYPE_CHANGE = 2;
    public static final int LOGIN_RESULT_TYPE_LINK = 1;
    public static final int LOGIN_RESULT_TYPE_LOGIN = 0;
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int isNewUser;
        public int loginResultType;
        public long npSN;
        public String npToken;
        public List<NXToyTerm> termsAgree;
        public String umKey;
        public int withdrawExpiresIn;

        public ResultSet() {
        }
    }

    public NXToyLoginResult() {
        this(0, "", "");
    }

    public NXToyLoginResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyLoginResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.Login.getValue());
        this.result = new ResultSet();
    }

    public NXToyLoginResult(int i, String str, String str2, int i2) {
        super(i, str, str2, i2);
        this.result = new ResultSet();
    }
}
