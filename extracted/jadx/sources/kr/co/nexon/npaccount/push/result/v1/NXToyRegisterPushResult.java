package kr.co.nexon.npaccount.push.result.v1;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyRegisterPushResult extends NXToyResult {
    public NXToyRegisterPushResult() {
        this(0, "", "");
    }

    public NXToyRegisterPushResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyRegisterPushResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.RegisterPush.getValue());
    }
}
