package com.nexon.core.requestpostman.result;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyResult extends NXClassInfo {
    public int errorCode;
    public String errorDetail;
    public String errorText;
    public int requestTag;

    public NXToyResult() {
        this(0, "", "");
    }

    public NXToyResult(int i, String str) {
        this(i, str, "", 0);
    }

    public NXToyResult(int i, String str, String str2) {
        this(i, str, str2, 0);
    }

    public NXToyResult(int i, String str, String str2, int i2) {
        this.errorCode = i;
        this.errorText = str;
        this.errorDetail = str2;
        this.requestTag = i2;
    }
}
