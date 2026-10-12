package com.nexon.nexonanalyticssdk;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxResponse {
    private String body;
    private int statusCode;

    public NxResponse(int i, String str) {
        this.statusCode = i;
        this.body = str;
    }

    public String getBody() {
        return this.body;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
