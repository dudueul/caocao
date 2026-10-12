package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyUserInfo;
import kr.co.nexon.npaccount.auth.result.model.NXToyNexonUserInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyUserInfoResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public boolean doToast = false;
        public NXToyNexonUserInfo nkUserInfo;
        public String npToken;
        public String npaCode;
        public long npsn;
        public String npsnString;
        public NXToyUserInfo npsnUserInfo;
        public int pushAgree;

        public ResultSet() {
        }
    }

    public NXToyUserInfoResult() {
        this(0, "", "");
    }

    public NXToyUserInfoResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyUserInfoResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetUserInfo.getValue());
        this.result = new ResultSet();
    }
}
