package kr.co.nexon.npaccount.auth.request.deprecated;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyMigrationInfoResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetMigrationInfoRequest extends NXToyBoltRequest {
    public NXToyGetMigrationInfoRequest(long j, String str, String str2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getMigrationInfo.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("current", Long.valueOf(j));
        map.put("stored", str);
        map.put("storedType", str2);
        super.setMessageBody(map);
        super.setResultClass(NXToyMigrationInfoResult.class);
    }
}
