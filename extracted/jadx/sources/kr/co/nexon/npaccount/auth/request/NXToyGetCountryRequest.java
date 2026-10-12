package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyCountryResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetCountryRequest extends NXToyBoltRequest {
    public NXToyGetCountryRequest(int i, int i2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getCountry.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("mnc", Integer.valueOf(i2));
        map.put("mcc", Integer.valueOf(i));
        super.setMessageBody(map);
        super.setResultClass(NXToyCountryResult.class);
    }
}
