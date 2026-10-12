package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyValidateMGTokenResult;
import kr.co.nexon.toy.android.ui.backup.NPDataBackupDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyValidateMGTokenRequest extends NXToyBoltRequest {
    public NXToyValidateMGTokenRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/validateMGToken.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put(NPDataBackupDialog.KEY_MIGRATION_TOKEN, str);
        super.setMessageBody(map);
        super.setResultClass(NXToyValidateMGTokenResult.class);
    }
}
