package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyShareResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String postId;
        public int shareAppNo;

        public ResultSet() {
        }
    }

    public NXToyShareResult() {
        this(0, "", "");
    }

    public NXToyShareResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyShareResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }
}
