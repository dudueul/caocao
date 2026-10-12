package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.session.model.NXToyTerm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.NXPAgreeTermsWithoutUpdateToyTokenResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAgreeTermsWithoutUpdateToyTokenRequest extends NXToyBoltRequest {
    public NXPAgreeTermsWithoutUpdateToyTokenRequest(List<NXToyTerm> list) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/agreeTermsWithoutUpdateToyToken.nx");
        this.encryptType = NXToyCryptoType.NPSN;
        this.decryptType = NXToyCryptoType.NPSN;
        super.setRequestHeader();
        ArrayList arrayList = new ArrayList();
        for (NXToyTerm nXToyTerm : list) {
            NXToyTerm nXToyTerm2 = new NXToyTerm();
            nXToyTerm2.termID = nXToyTerm.termID;
            nXToyTerm2.isAgree = nXToyTerm.isAgree;
            nXToyTerm2.isUpdate = nXToyTerm.isUpdate;
            nXToyTerm2.contents = "";
            nXToyTerm2.title = "";
            arrayList.add(nXToyTerm2);
        }
        HashMap map = new HashMap();
        map.put("terms", arrayList);
        map.put("termsApiVer", Integer.valueOf(NXToyCommonPreferenceController.getInstance().getTermsApiVer()));
        super.setMessageBody(map);
        super.setResultClass(NXPAgreeTermsWithoutUpdateToyTokenResult.class);
    }
}
