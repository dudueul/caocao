package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyNPSNsResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public Map<String, List<Long>> linkWithSNS;
        public List<Long> npSNs;

        public ResultSet() {
        }
    }

    public NXToyNPSNsResult() {
        this(0, "", "");
    }

    public NXToyNPSNsResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyNPSNsResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetNPSNs.getValue());
        this.result = new ResultSet();
    }
}
