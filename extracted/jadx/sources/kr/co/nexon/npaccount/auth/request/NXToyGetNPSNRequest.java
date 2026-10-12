package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.util.NXCrypto.NXCrypto;
import com.nexon.core.util.NXStringUtil;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyNpsnResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetNPSNRequest extends NXToyBoltRequest {
    public NXToyGetNPSNRequest(String str, String str2, int i) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/getNPSN.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("userID", str);
        String strEncodeHmacSha256ToHexString = str2;
        if (i == NXToyLoginType.LoginTypeNXCom.getValue() && NXStringUtil.isValidEmail(str)) {
            try {
                strEncodeHmacSha256ToHexString = NXCrypto.encodeHmacSha256ToHexString("NexonUser", str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.put("passwd", strEncodeHmacSha256ToHexString);
        map.put("memType", Integer.valueOf(i));
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        map.put(NxSystemInfo.KEY_UUID, nXToyCommonPreferenceController.getUUID());
        map.put(NxLogInfo.KEY_UUID2, nXToyCommonPreferenceController.getUUID2());
        super.setMessageBody(map);
        super.setResultClass(NXToyNpsnResult.class);
    }
}
