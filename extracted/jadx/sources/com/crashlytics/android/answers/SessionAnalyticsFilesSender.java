package com.crashlytics.android.answers;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class SessionAnalyticsFilesSender extends AbstractSpiCall implements FilesSender {
    static final String FILE_CONTENT_TYPE = "application/vnd.crashlytics.android.events";
    static final String FILE_PARAM_NAME = "session_analytics_file_";
    private final String apiKey;

    public SessionAnalyticsFilesSender(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, String str3) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
        this.apiKey = str3;
    }

    @Override // io.fabric.sdk.android.services.events.FilesSender
    public boolean send(List<File> list) throws Throwable {
        HttpRequest httpRequestHeader = getHttpRequest().header(AbstractSpiCall.HEADER_CLIENT_TYPE, "android").header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion()).header(AbstractSpiCall.HEADER_API_KEY, this.apiKey);
        int i = 0;
        for (File file : list) {
            httpRequestHeader.part(FILE_PARAM_NAME + i, file.getName(), FILE_CONTENT_TYPE, file);
            i++;
        }
        Fabric.getLogger().d(Answers.TAG, "Sending " + list.size() + " analytics files to " + getUrl());
        int iCode = httpRequestHeader.code();
        Fabric.getLogger().d(Answers.TAG, "Response code for analytics file send is " + iCode);
        return ResponseParser.parse(iCode) == 0;
    }
}
