package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyTermResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXToyTerm> terms;

        public ResultSet() {
        }
    }

    public NXToyTermResult() {
        super(0, "", "", NXToyRequestTag.GetTerms.getValue());
        this.result = new ResultSet();
        this.result = new ResultSet();
    }
}
