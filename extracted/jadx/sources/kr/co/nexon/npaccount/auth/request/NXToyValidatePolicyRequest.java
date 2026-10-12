package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import java.util.Map;
import kr.co.nexon.npaccount.auth.result.NXToyValidatePolicyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyValidatePolicyRequest extends NXToyBoltRequest {
    public static final String CODE_VALIDATE_POLICY_DATA_IS_TERMS_AGREE = "isTermsAgree";
    public static final String CODE_VALIDATE_POLICY_DATA_METHOD = "method";
    public static final String CODE_VALIDATE_POLICY_DATA_METHOD_DELETE = "delete";
    public static final String CODE_VALIDATE_POLICY_DATA_METHOD_GET = "get";
    public static final String CODE_VALIDATE_POLICY_DATA_METHOD_PUT = "put";
    public static final String CODE_VALIDATE_POLICY_DATA_PHONE = "phone";

    public NXToyValidatePolicyRequest(int i, Map<String, Object> map) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/validatePolicy.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map2 = new HashMap();
        map2.put("policy", Integer.valueOf(i));
        map2.put("data", map);
        super.setMessageBody(map2);
        super.setResultClass(NXToyValidatePolicyResult.class);
    }
}
