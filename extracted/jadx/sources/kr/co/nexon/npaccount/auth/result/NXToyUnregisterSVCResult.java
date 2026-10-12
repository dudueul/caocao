package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyUnregisterSVCResult extends NXToyResult {
    public NXToyUnregisterSVCResult() {
        this(0, "", "");
    }

    public NXToyUnregisterSVCResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyUnregisterSVCResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.UnregisterSVC.getValue());
    }
}
