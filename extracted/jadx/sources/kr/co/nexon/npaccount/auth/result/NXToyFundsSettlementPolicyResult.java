package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.result.model.NXPPolicy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyFundsSettlementPolicyResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int isSubscription;
        public String itemName;
        public String itemPrice;
        public NXPPolicy policy;
        public String productId;

        public ResultSet() {
        }
    }

    public NXToyFundsSettlementPolicyResult() {
        this(0, "", "");
    }

    public NXToyFundsSettlementPolicyResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyFundsSettlementPolicyResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }
}
