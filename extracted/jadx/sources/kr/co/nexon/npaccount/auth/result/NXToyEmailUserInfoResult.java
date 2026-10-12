package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyEmailUserInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyEmailUserInfoResult extends NXToyResult {
    public String auth_type;
    public String email;
    public NXToyEmailUserInfo extend;
    public String npsn;
    public int verified;

    public NXToyEmailUserInfoResult() {
        this(0, "", "");
    }

    public NXToyEmailUserInfoResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyEmailUserInfoResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetEmailUserInfo.getValue());
    }
}
