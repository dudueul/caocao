package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetPolicyListResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXPPolicy> policyList;

        public ResultSet() {
        }
    }

    public NXToyGetPolicyListResult() {
        this(0, "", "");
    }

    public NXToyGetPolicyListResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyGetPolicyListResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetPolicyList.getValue());
        this.result = new ResultSet();
    }
}
