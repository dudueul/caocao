package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyLogoutSVCResult extends NXToyResult {
    public NXToyLogoutSVCResult() {
        this(0, "", "");
    }

    public NXToyLogoutSVCResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyLogoutSVCResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.Logout.getValue());
    }
}
