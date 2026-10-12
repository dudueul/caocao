package kr.co.nexon.npaccount.promotion.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.model.NXToyBannerPromotion;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyPromotionResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXToyBannerPromotion> bannerList;

        public ResultSet() {
        }
    }

    public NXToyPromotionResult() {
        this(0, "", "");
    }

    public NXToyPromotionResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyPromotionResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetPromotion.getValue());
        this.result = new ResultSet();
    }
}
