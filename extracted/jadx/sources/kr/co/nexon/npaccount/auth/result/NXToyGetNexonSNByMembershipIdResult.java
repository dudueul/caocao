package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetNexonSNByMembershipIdResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String memSNUuid;
        public String nexonSN;

        public ResultSet() {
        }
    }

    public NXToyGetNexonSNByMembershipIdResult() {
        this(0, "", "");
    }

    public NXToyGetNexonSNByMembershipIdResult(int i, String str) {
        this(i, str, str);
    }

    public NXToyGetNexonSNByMembershipIdResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetNexonSNByMembershipId.getValue());
        this.result = new ResultSet();
    }
}
