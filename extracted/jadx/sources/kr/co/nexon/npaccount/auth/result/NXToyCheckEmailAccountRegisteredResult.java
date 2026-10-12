package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyCheckEmailAccountRegisteredResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int isRegistered;

        public ResultSet() {
        }
    }

    public NXToyCheckEmailAccountRegisteredResult() {
        this(0, "", "");
    }

    public NXToyCheckEmailAccountRegisteredResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyCheckEmailAccountRegisteredResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.CheckEmailAccountRegistered.getValue());
        this.result = new ResultSet();
    }
}
