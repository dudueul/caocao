package kr.co.nexon.npaccount.auth.request.deprecated;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyFriendsResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetFriendsOfExtraMembershipRequest extends NXToyBoltRequest {
    public NXToyGetFriendsOfExtraMembershipRequest(String str, String str2, int i, String str3) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getFriendsOfExtraMembership.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("memToken", str);
        map.put("memID", str2);
        map.put("memType", Integer.valueOf(i));
        map.put("filter", str3);
        super.setMessageBody(map);
        super.setResultClass(NXToyFriendsResult.class);
    }
}
