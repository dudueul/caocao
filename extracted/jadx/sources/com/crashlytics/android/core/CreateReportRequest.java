package com.crashlytics.android.core;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class CreateReportRequest {
    public final String apiKey;
    public final Report report;

    public CreateReportRequest(String str, Report report) {
        this.apiKey = str;
        this.report = report;
    }
}
