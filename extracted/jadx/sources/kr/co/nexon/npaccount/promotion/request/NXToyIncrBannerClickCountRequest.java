package kr.co.nexon.npaccount.promotion.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyIncrBannerClickCountRequest extends NXToyBoltRequest {
    public NXToyIncrBannerClickCountRequest(String str, int i, int i2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/incrBannerClickCount.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.NONE;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("groupCode", str);
        map.put("bannerSN", Integer.valueOf(i));
        map.put("clickType", Integer.valueOf(i2));
        super.setMessageBody(map);
        super.setResultClass(NXToyResult.class);
    }
}
