package kr.co.nexon.npaccount.secondpassword.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.secondpassword.result.NXToySendSecondPasswordRegisteredEmailResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToySend2ndPasswordRegisteredEmailRequest extends NXToyBoltRequest {
    public NXToySend2ndPasswordRegisteredEmailRequest() {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/send2ndPasswordRegisteredEmail.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        super.setMessageBody(new HashMap());
        super.setResultClass(NXToySendSecondPasswordRegisteredEmailResult.class);
    }
}
