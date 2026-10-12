package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class DefaultCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    static final String FILE_CONTENT_TYPE = "application/octet-stream";
    static final String FILE_PARAM = "report[file]";
    static final String IDENTIFIER_PARAM = "report[identifier]";
    static final String MULTI_FILE_PARAM = "report[file";

    public DefaultCreateReportSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
    }

    DefaultCreateReportSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, CreateReportRequest createReportRequest) {
        HttpRequest httpRequestHeader = httpRequest.header(AbstractSpiCall.HEADER_API_KEY, createReportRequest.apiKey).header(AbstractSpiCall.HEADER_CLIENT_TYPE, "android").header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
        Iterator<Map.Entry<String, String>> it = createReportRequest.report.getCustomHeaders().entrySet().iterator();
        while (it.hasNext()) {
            httpRequestHeader = httpRequestHeader.header(it.next());
        }
        return httpRequestHeader;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, Report report) throws Throwable {
        httpRequest.part(IDENTIFIER_PARAM, report.getIdentifier());
        if (report.getFiles().length == 1) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Adding single file " + report.getFileName() + " to report " + report.getIdentifier());
            return httpRequest.part(FILE_PARAM, report.getFileName(), FILE_CONTENT_TYPE, report.getFile());
        }
        int i = 0;
        for (File file : report.getFiles()) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Adding file " + file.getName() + " to report " + report.getIdentifier());
            httpRequest.part(MULTI_FILE_PARAM + i + "]", file.getName(), FILE_CONTENT_TYPE, file);
            i++;
        }
        return httpRequest;
    }

    @Override // com.crashlytics.android.core.CreateReportSpiCall
    public boolean invoke(CreateReportRequest createReportRequest) throws Throwable {
        HttpRequest httpRequestApplyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), createReportRequest), createReportRequest.report);
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Sending report to: " + getUrl());
        int iCode = httpRequestApplyMultipartDataTo.code();
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Create report request ID: " + httpRequestApplyMultipartDataTo.header(AbstractSpiCall.HEADER_REQUEST_ID));
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Result was: " + iCode);
        return ResponseParser.parse(iCode) == 0;
    }
}
