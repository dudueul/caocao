package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyBanner;
import kr.co.nexon.npaccount.auth.result.model.NXToyServiceInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyInitDataResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXToyServiceInfo {
        public NXToyBanner endBannerInfo;

        public ResultSet() {
        }
    }

    public NXToyInitDataResult() {
        this(0, "", "");
    }

    public NXToyInitDataResult(int i, String str) {
        super(i, str);
        this.result = new ResultSet();
    }

    public NXToyInitDataResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }
}
