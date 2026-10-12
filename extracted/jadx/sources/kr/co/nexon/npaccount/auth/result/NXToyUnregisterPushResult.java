package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyUnregisterPushResult extends NXToyResult {
    public NXToyUnregisterPushResult() {
        this(0, "", "");
    }

    public NXToyUnregisterPushResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyUnregisterPushResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.UnregisterPush.getValue());
    }
}
