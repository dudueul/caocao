package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyTermsListResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXToyTerm> terms;

        public ResultSet() {
        }
    }

    public NXToyTermsListResult() {
        this(0, "", "");
    }

    public NXToyTermsListResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyTermsListResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetTermsList.getValue());
        this.result = new ResultSet();
    }
}
