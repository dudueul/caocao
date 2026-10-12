package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyCouponInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyPutCouponResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public NXToyCouponInfo cpInfo;
        public String cpToken;

        public ResultSet() {
        }
    }

    public NXToyPutCouponResult() {
        this(0, "", "");
    }

    public NXToyPutCouponResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyPutCouponResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.PutCouponPin.getValue());
        this.result = new ResultSet();
    }
}
