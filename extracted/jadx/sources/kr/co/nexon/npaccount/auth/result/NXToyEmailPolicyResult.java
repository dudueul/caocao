package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyEmailPolicyResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int isSubscription;
        public NXPPolicy policy;

        public ResultSet() {
        }
    }

    public NXToyEmailPolicyResult() {
        this(0, "", "");
    }

    public NXToyEmailPolicyResult(int i, String str) {
        super(i, str);
        this.result = new ResultSet();
    }

    public NXToyEmailPolicyResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }
}
