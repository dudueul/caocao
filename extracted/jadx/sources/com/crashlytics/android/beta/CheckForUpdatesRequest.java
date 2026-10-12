package com.crashlytics.android.beta;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import kr.co.nexon.npaccount.board.NXBoardManager;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class CheckForUpdatesRequest extends AbstractSpiCall {
    static final String BETA_SOURCE = "3";
    static final String BUILD_VERSION = "build_version";
    static final String DISPLAY_VERSION = "display_version";
    static final String HEADER_BETA_TOKEN = "X-CRASHLYTICS-BETA-TOKEN";
    static final String INSTANCE = "instance";
    static final String SDK_ANDROID_DIR_TOKEN_TYPE = "3";
    static final String SOURCE = "source";
    private final CheckForUpdatesResponseTransform responseTransform;

    public CheckForUpdatesRequest(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, CheckForUpdatesResponseTransform checkForUpdatesResponseTransform) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.GET);
        this.responseTransform = checkForUpdatesResponseTransform;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, String str, String str2) {
        return httpRequest.header("Accept", "application/json").header("User-Agent", AbstractSpiCall.CRASHLYTICS_USER_AGENT + this.kit.getVersion()).header(AbstractSpiCall.HEADER_DEVELOPER_TOKEN, "470fa2b4ae81cd56ecbcda9735803434cec591fa").header(AbstractSpiCall.HEADER_CLIENT_TYPE, "android").header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion()).header(AbstractSpiCall.HEADER_API_KEY, str).header(HEADER_BETA_TOKEN, createBetaTokenHeaderValueFor(str2));
    }

    static String createBetaTokenHeaderValueFor(String str) {
        return "3:" + str;
    }

    private Map<String, String> getQueryParamsFor(BuildProperties buildProperties) {
        HashMap map = new HashMap();
        map.put(BUILD_VERSION, buildProperties.versionCode);
        map.put(DISPLAY_VERSION, buildProperties.versionName);
        map.put(INSTANCE, buildProperties.buildId);
        map.put("source", NXBoardManager.BOARD_HELPCENTER);
        return map;
    }

    public CheckForUpdatesResponse invoke(String str, String str2, BuildProperties buildProperties) {
        HttpRequest httpRequest = null;
        try {
            try {
                Map<String, String> queryParamsFor = getQueryParamsFor(buildProperties);
                HttpRequest httpRequestApplyHeadersTo = applyHeadersTo(getHttpRequest(queryParamsFor), str, str2);
                Fabric.getLogger().d(Beta.TAG, "Checking for updates from " + getUrl());
                Fabric.getLogger().d(Beta.TAG, "Checking for updates query params are: " + queryParamsFor);
                if (!httpRequestApplyHeadersTo.ok()) {
                    Fabric.getLogger().e(Beta.TAG, "Checking for updates failed. Response code: " + httpRequestApplyHeadersTo.code());
                    if (httpRequestApplyHeadersTo != null) {
                        Fabric.getLogger().d(Fabric.TAG, "Checking for updates request ID: " + httpRequestApplyHeadersTo.header(AbstractSpiCall.HEADER_REQUEST_ID));
                    }
                    return null;
                }
                Fabric.getLogger().d(Beta.TAG, "Checking for updates was successful");
                CheckForUpdatesResponse checkForUpdatesResponseFromJson = this.responseTransform.fromJson(new JSONObject(httpRequestApplyHeadersTo.body()));
                if (httpRequestApplyHeadersTo == null) {
                    return checkForUpdatesResponseFromJson;
                }
                Fabric.getLogger().d(Fabric.TAG, "Checking for updates request ID: " + httpRequestApplyHeadersTo.header(AbstractSpiCall.HEADER_REQUEST_ID));
                return checkForUpdatesResponseFromJson;
            } catch (Exception e) {
                Fabric.getLogger().e(Beta.TAG, "Error while checking for updates from " + getUrl(), e);
                if (0 != 0) {
                    Fabric.getLogger().d(Fabric.TAG, "Checking for updates request ID: " + httpRequest.header(AbstractSpiCall.HEADER_REQUEST_ID));
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                Fabric.getLogger().d(Fabric.TAG, "Checking for updates request ID: " + httpRequest.header(AbstractSpiCall.HEADER_REQUEST_ID));
            }
            throw th;
        }
    }
}
