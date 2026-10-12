package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyBannerClickResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String landInfo;

        public ResultSet() {
        }
    }

    public NXToyBannerClickResult() {
        this.result = new ResultSet();
    }

    public NXToyBannerClickResult(int i, String str) {
        super(i, str);
        this.result = new ResultSet();
    }

    public NXToyBannerClickResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }
}
