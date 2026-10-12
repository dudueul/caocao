package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyAdIdResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String adId;
        public boolean isLimitAdTrackingEnabled;

        public ResultSet() {
        }
    }

    public NXToyAdIdResult() {
        this(0, "", "");
    }

    public NXToyAdIdResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyAdIdResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetAdId.getValue());
        this.result = new ResultSet();
    }
}
