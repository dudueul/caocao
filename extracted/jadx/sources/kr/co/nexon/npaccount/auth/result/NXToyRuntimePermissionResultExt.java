package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyRuntimePermissionResultExt extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public int[] grantResults;
        public String[] permissions;
        public int requestCode;

        public ResultSet() {
        }
    }

    public NXToyRuntimePermissionResultExt() {
        this(0, "", "");
    }

    public NXToyRuntimePermissionResultExt(int i, String str) {
        this(i, str, "");
    }

    public NXToyRuntimePermissionResultExt(int i, String str, String str2) {
        super(i, str, str2);
        this.result = new ResultSet();
    }

    public NXToyRuntimePermissionResultExt(int i, String str, String str2, int i2) {
        super(i2, str, str2);
        this.result = new ResultSet();
        this.requestTag = i2;
    }
}
