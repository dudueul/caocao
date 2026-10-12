package kr.co.nexon.npaccount.promotion.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.model.NXToyBanner;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyBannerListResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXToyBanner> bannerList;

        public ResultSet() {
        }
    }

    public NXToyBannerListResult() {
        this(0, "", "");
    }

    public NXToyBannerListResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyBannerListResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetBannerList.getValue());
        this.result = new ResultSet();
    }
}
