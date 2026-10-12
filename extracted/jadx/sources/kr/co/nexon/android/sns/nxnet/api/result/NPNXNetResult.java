package kr.co.nexon.android.sns.nxnet.api.result;

import com.nexon.core.requestpostman.result.NXClassInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetResult extends NXClassInfo {
    public int error_code;
    public String error_description;
    public String error_message;
    public int requestTag;

    public NPNXNetResult() {
        this.error_code = 0;
        this.error_message = "";
        this.error_description = "";
    }

    public NPNXNetResult(int i, String str) {
        this.error_code = i;
        this.error_message = str;
        this.error_description = str;
    }

    public NPNXNetResult(int i, String str, String str2) {
        this.error_code = i;
        this.error_message = str;
        this.error_description = str2;
    }

    public NPNXNetResult(int i, String str, String str2, int i2) {
        this(i, str, str2);
        this.requestTag = i2;
    }
}
