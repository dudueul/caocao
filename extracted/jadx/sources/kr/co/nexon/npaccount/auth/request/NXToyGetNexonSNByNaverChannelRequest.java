package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyGetNexonSNByNaverChannelResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetNexonSNByNaverChannelRequest extends NXToyBoltRequest {
    public NXToyGetNexonSNByNaverChannelRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getNexonSNByNaverCh.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("userID", "NAVER_CHANNEL_LOGIN");
        map.put("passwd", str);
        super.setMessageBody(map);
        super.setResultClass(NXToyGetNexonSNByNaverChannelResult.class);
    }
}
