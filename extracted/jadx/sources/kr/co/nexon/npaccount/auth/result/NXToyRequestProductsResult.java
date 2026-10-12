package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.model.NXToyBillingProduct;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyRequestProductsResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXToyBillingProduct> products;

        public ResultSet() {
        }
    }

    public NXToyRequestProductsResult() {
        this.result = new ResultSet();
    }

    public NXToyRequestProductsResult(int i, String str) {
        super(i, str);
        this.result = new ResultSet();
    }

    public NXToyRequestProductsResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }

    public NXToyRequestProductsResult(int i, String str, String str2, int i2) {
        super(i, str, str2, i2);
        this.result = new ResultSet();
    }
}
