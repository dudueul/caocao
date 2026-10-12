package kr.co.nexon.npaccount.promotion.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.promotion.result.NXToyBannerListResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetBannerListRequest extends NXToyBoltRequest {
    public NXToyGetBannerListRequest(int i) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getBannerList.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("groupCode", Integer.valueOf(i));
        super.setMessageBody(map);
        super.setResultClass(NXToyBannerListResult.class);
    }
}
