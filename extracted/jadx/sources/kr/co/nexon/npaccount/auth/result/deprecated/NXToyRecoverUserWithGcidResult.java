package kr.co.nexon.npaccount.auth.result.deprecated;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyRecoverUserWithGcidResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int memType;
        public long npSN;
        public String npToken;
        public String npaCode;
        public List<NXToyTerm> termsAgree;

        public ResultSet() {
        }
    }

    public NXToyRecoverUserWithGcidResult() {
        this(0, "", "");
    }

    public NXToyRecoverUserWithGcidResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyRecoverUserWithGcidResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }
}
