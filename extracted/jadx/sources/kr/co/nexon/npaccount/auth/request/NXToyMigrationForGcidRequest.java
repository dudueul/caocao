package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import java.util.HashMap;
import kr.co.nexon.npaccount.auth.result.NXToyMigrationForGcidResult;
import kr.co.nexon.toy.android.ui.backup.NPDataBackupDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyMigrationForGcidRequest extends NXToyBoltRequest {
    public NXToyMigrationForGcidRequest(String str, String str2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/migrationForGcid.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put(NPDataBackupDialog.KEY_MIGRATION_TOKEN, str);
        map.put("gcId", str2);
        super.setMessageBody(map);
        super.setResultClass(NXToyMigrationForGcidResult.class);
    }
}
