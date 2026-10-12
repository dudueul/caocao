package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyLoginWithGCIDResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int memType = -1;
        public long npSN;
        public String npToken;
        public String npaCode;
        public List<NXToyTerm> termsAgree;
        public int withdrawExpiresIn;

        public ResultSet() {
        }
    }

    public NXToyLoginWithGCIDResult() {
        this(0, "", "");
    }

    public NXToyLoginWithGCIDResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyLoginWithGCIDResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.LoginWithGCID.getValue());
        this.result = new ResultSet();
    }
}
