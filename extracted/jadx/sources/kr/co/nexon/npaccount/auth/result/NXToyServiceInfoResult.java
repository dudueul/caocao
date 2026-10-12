package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyServiceInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyServiceInfoResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXToyServiceInfo {
        public ResultSet() {
        }
    }

    public NXToyServiceInfoResult() {
        this(0, "", "");
    }

    public NXToyServiceInfoResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyServiceInfoResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetSvcInfo.getValue());
        this.result = new ResultSet();
    }
}
