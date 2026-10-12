package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyWebShopResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String meta;

        public ResultSet() {
        }
    }

    public NXToyWebShopResult() {
        this(0, "", "");
    }

    public NXToyWebShopResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyWebShopResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }
}
