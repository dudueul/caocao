package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.model.NXPToyOfferwall;
import kr.co.nexon.npaccount.auth.result.model.NXToyBanner;
import kr.co.nexon.npaccount.auth.result.model.NXToyBasePlate;
import kr.co.nexon.npaccount.auth.result.model.NXToyMaintenance;
import kr.co.nexon.npaccount.auth.result.model.NXToyService;
import kr.co.nexon.npaccount.auth.result.model.NXToyValidateToken;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyEnterResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXToyBasePlate> basePlate;
        public String country;
        public NXToyBanner endBanner;
        public List<NXToyMaintenance> maintenance;
        public NXPToyOfferwall offerwall;
        public NXToyService service;
        public List<NXToyTerm> termsAgree;
        public NXToyValidateToken validateToken;
        public int isPrivacyConsigned = -1;
        public boolean enableLogging = false;

        public ResultSet() {
        }
    }

    public NXToyEnterResult() {
        this(0, "", "");
    }

    public NXToyEnterResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyEnterResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.EnterToy.getValue());
        this.result = new ResultSet();
    }
}
