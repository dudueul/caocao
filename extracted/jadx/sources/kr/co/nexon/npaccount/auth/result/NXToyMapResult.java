package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyMapResult extends NXToyResult {
    public Map<String, Object> result;

    public NXToyMapResult() {
        this(0, "", "");
    }

    public NXToyMapResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyMapResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.AppInviteFacebook.getValue());
        this.result = new HashMap();
    }
}
