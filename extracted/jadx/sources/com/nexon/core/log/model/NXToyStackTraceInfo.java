package com.nexon.core.log.model;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyStackTraceInfo {
    private final String className;
    private final String lineNumber;
    private final String methodName;

    public NXToyStackTraceInfo(String str) {
        this.className = str;
        this.methodName = str;
        this.lineNumber = str;
    }

    public NXToyStackTraceInfo(String str, String str2, String str3) {
        this.className = str;
        this.methodName = str2;
        this.lineNumber = str3;
    }

    public String getClassName() {
        return this.className;
    }

    public String getLineNumber() {
        return this.lineNumber;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getTag() {
        return "Toy:" + this.className + "." + this.methodName + "(" + this.lineNumber + ")";
    }
}
