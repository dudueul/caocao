package kr.co.nexon.npaccount.promotion.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyIncrBannerClickCountResult extends NXToyResult {
    public NXToyIncrBannerClickCountResult() {
        this(0, "", "");
    }

    public NXToyIncrBannerClickCountResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyIncrBannerClickCountResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.IncrBannerClickCount.getValue());
    }
}
