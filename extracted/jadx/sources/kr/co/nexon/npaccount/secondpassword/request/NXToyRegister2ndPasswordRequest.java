package kr.co.nexon.npaccount.secondpassword.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.secondpassword.result.NXToyRegisterSecondPasswordResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyRegister2ndPasswordRequest extends NXToyBoltRequest {
    public NXToyRegister2ndPasswordRequest(String str, String str2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/register2ndPassword.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put("email", str);
        map.put("password", str2);
        super.setMessageBody(map);
        super.setResultClass(NXToyRegisterSecondPasswordResult.class);
    }
}
