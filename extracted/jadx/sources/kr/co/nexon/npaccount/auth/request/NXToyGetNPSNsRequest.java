package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.session.NXToySessionManager;
import java.util.HashMap;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.NXToyNPSNsResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetNPSNsRequest extends NXToyBoltRequest {
    public NXToyGetNPSNsRequest(int i, List<String> list) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getNPSNs.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("svcID", Integer.valueOf(Integer.parseInt(NXToySessionManager.getInstance().getSession().getServiceId())));
        map.put("memType", Integer.valueOf(i));
        map.put("memIDs", list);
        super.setMessageBody(map);
        super.setResultClass(NXToyNPSNsResult.class);
    }
}
