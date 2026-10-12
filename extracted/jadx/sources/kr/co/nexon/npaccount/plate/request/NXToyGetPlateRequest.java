package kr.co.nexon.npaccount.plate.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.plate.result.NXToyPlateInfoResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetPlateRequest extends NXToyBoltRequest {
    public NXToyGetPlateRequest(int i) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getPlate.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("group", Integer.valueOf(i));
        super.setMessageBody(map);
        super.setResultClass(NXToyPlateInfoResult.class);
    }
}
