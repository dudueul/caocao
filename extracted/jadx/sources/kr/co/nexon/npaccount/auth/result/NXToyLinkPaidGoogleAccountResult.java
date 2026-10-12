package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyLinkPaidGoogleAccountResult extends NXToyResult {
    public NXToyLinkPaidGoogleAccountResult() {
        this(0, "", "");
    }

    public NXToyLinkPaidGoogleAccountResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyLinkPaidGoogleAccountResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.LinkPaidGoogleAccount.getValue());
    }
}
