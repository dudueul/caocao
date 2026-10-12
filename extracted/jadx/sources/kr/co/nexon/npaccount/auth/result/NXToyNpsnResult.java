package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyNpsnResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public long npsn;

        public ResultSet() {
        }
    }

    public NXToyNpsnResult() {
        this(0, "", "");
    }

    public NXToyNpsnResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyNpsnResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetNPSN.getValue());
        this.result = new ResultSet();
    }

    public NXToyNpsnResult(int i, String str, String str2, int i2) {
        super(i2, str, str2);
        this.result = new ResultSet();
        this.requestTag = i2;
    }
}
