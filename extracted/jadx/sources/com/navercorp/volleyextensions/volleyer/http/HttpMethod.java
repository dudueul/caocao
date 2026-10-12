package com.navercorp.volleyextensions.volleyer.http;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum HttpMethod {
    GET(0),
    POST(1),
    PUT(2),
    DELETE(3);

    private int methodCode;

    HttpMethod(int i) {
        this.methodCode = i;
    }

    public int getMethodCode() {
        return this.methodCode;
    }
}
