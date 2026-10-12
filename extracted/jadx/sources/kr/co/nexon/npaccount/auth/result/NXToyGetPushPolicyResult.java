package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.push.NXPPushPolicies;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetPushPolicyResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public NXPPushPolicies policies;

        public ResultSet() {
        }
    }

    public NXToyGetPushPolicyResult() {
        this(0, "", "");
    }

    public NXToyGetPushPolicyResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyGetPushPolicyResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetPushPolicy.getValue());
        this.result = new ResultSet();
    }
}
