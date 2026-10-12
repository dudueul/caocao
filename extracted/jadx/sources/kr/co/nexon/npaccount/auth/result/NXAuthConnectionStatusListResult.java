package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.model.NXAuthConnectionStatus;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXAuthConnectionStatusListResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXAuthConnectionStatus> list;

        public ResultSet() {
        }
    }

    public NXAuthConnectionStatusListResult() {
        this(0, "", "");
    }

    public NXAuthConnectionStatusListResult(int i, String str) {
        this(i, str, "");
    }

    public NXAuthConnectionStatusListResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetSnsConnectionStatus.getValue());
        this.result = new ResultSet();
    }
}
