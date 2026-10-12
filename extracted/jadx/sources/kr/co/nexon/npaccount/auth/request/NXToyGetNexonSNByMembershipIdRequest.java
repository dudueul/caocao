package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.util.NXStringUtil;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyGetNexonSNByMembershipIdResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetNexonSNByMembershipIdRequest extends NXToyBoltRequest {
    public NXToyGetNexonSNByMembershipIdRequest(int i, String str, String str2, String str3) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getNexonSNByMembershipId.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("memType", Integer.valueOf(i));
        if (NXStringUtil.isNotNull(str)) {
            map.put("memID", str);
        }
        if (NXStringUtil.isNotNull(str2)) {
            map.put("bizToken", str2);
        }
        if (NXStringUtil.isNotNull(str3)) {
            map.put("accessToken", str3);
        }
        super.setMessageBody(map);
        super.setResultClass(NXToyGetNexonSNByMembershipIdResult.class);
    }
}
