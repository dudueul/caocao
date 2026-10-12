package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyPolicyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetPolicyRequest extends NXToyBoltRequest {
    public static final String TYPE_POLICY_PURCHASE = "purchase";
    public static final String TYPE_POLICY_SIGNIN = "signin";
    public static final String TYPE_POLICY_SIGNUP = "signup";
    public static final String TYPE_POLICY_TERMS = "terms";

    public NXToyGetPolicyRequest(int i, int i2, String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getPolicy.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("mcc", Integer.valueOf(i));
        map.put("mnc", Integer.valueOf(i2));
        map.put("type", str);
        super.setMessageBody(map);
        super.setResultClass(NXToyPolicyResult.class);
    }
}
