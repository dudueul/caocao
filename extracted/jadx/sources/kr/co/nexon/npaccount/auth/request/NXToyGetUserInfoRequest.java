package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyUserInfoResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetUserInfoRequest extends NXToyBoltRequest {
    public NXToyGetUserInfoRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getUserInfo.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        String advertisingId = NXToyCommonPreferenceController.getInstance().getAdvertisingId();
        advertisingId = (advertisingId == null || advertisingId.length() == 0) ? "" : advertisingId;
        if (str != null) {
            map.put("memToken", str);
        }
        map.put(NxLogInfo.KEY_ADID, advertisingId);
        super.setMessageBody(map);
        super.setResultClass(NXToyUserInfoResult.class);
    }
}
