package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Error {
    private int code;
    private String description;
    private String message;

    public Error(int i, String str, String str2) {
        this.code = 0;
        this.message = "";
        this.description = "";
        this.code = i;
        this.message = str;
        this.description = str2;
    }

    static Error createError(int i, String str, String str2) {
        return new Error(i, str, str2);
    }

    static Error createError(Constants.ErrorCode errorCode) {
        return new Error(errorCode.getValue(), errorCode.getMessage(), errorCode.getMessage());
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "{code:" + this.code + ", message:" + this.message + ", description:" + this.description + "}";
    }
}
