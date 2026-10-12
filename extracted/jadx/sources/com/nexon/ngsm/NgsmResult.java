package com.nexon.ngsm;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NgsmResult {
    public static final int INVALID_PARAMETER = 1;
    public static final int SUCCESS = 0;
    private String message;
    private int response;

    public NgsmResult(int i) {
        this.message = "Success";
        this.response = i;
    }

    public NgsmResult(int i, String str) {
        this.message = "Success";
        this.response = i;
        if (i != 0) {
            this.message = str;
        }
    }

    public String getMessage() {
        return this.message;
    }

    public int getResponse() {
        return this.response;
    }

    public boolean isSuccess() {
        return this.response == 0;
    }
}
