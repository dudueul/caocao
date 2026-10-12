package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXAuthResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String name;
        public int type;

        public ResultSet() {
        }
    }

    public NXAuthResult() {
        this(0, "", "");
    }

    public NXAuthResult(int i, String str) {
        super(i, str);
        this.result = new ResultSet();
    }

    public NXAuthResult(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }
}
