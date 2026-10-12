package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.model.NXToyBillingTransaction;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyBillingResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXToyBillingTransaction> billingTransactions;

        public ResultSet() {
        }
    }

    public NXToyBillingResult() {
        this.result = new ResultSet();
    }

    public NXToyBillingResult(int i, String str) {
        super(i, str);
        this.result = new ResultSet();
    }

    public NXToyBillingResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }

    public NXToyBillingResult(int i, String str, String str2, int i2) {
        super(i, str, str2, i2);
        this.result = new ResultSet();
    }
}
