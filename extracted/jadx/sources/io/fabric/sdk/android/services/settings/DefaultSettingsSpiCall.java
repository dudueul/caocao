package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    static final String BUILD_VERSION_PARAM = "build_version";
    static final String DISPLAY_VERSION_PARAM = "display_version";
    static final String HEADER_ADVERTISING_TOKEN = "X-CRASHLYTICS-ADVERTISING-TOKEN";
    static final String HEADER_ANDROID_ID = "X-CRASHLYTICS-ANDROID-ID";
    static final String HEADER_DEVICE_MODEL = "X-CRASHLYTICS-DEVICE-MODEL";
    static final String HEADER_INSTALLATION_ID = "X-CRASHLYTICS-INSTALLATION-ID";
    static final String HEADER_OS_BUILD_VERSION = "X-CRASHLYTICS-OS-BUILD-VERSION";
    static final String HEADER_OS_DISPLAY_VERSION = "X-CRASHLYTICS-OS-DISPLAY-VERSION";
    static final String ICON_HASH = "icon_hash";
    static final String INSTANCE_PARAM = "instance";
    static final String SOURCE_PARAM = "source";

    public DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        this(kit, str, str2, httpRequestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, SettingsRequest settingsRequest) {
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_API_KEY, settingsRequest.apiKey);
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_CLIENT_TYPE, "android");
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
        applyNonNullHeader(httpRequest, "Accept", "application/json");
        applyNonNullHeader(httpRequest, HEADER_DEVICE_MODEL, settingsRequest.deviceModel);
        applyNonNullHeader(httpRequest, HEADER_OS_BUILD_VERSION, settingsRequest.osBuildVersion);
        applyNonNullHeader(httpRequest, HEADER_OS_DISPLAY_VERSION, settingsRequest.osDisplayVersion);
        applyNonNullHeader(httpRequest, HEADER_ADVERTISING_TOKEN, settingsRequest.advertisingId);
        applyNonNullHeader(httpRequest, HEADER_INSTALLATION_ID, settingsRequest.installationId);
        applyNonNullHeader(httpRequest, HEADER_ANDROID_ID, settingsRequest.androidId);
        return httpRequest;
    }

    private void applyNonNullHeader(HttpRequest httpRequest, String str, String str2) {
        if (str2 != null) {
            httpRequest.header(str, str2);
        }
    }

    private JSONObject getJsonObjectFrom(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            Fabric.getLogger().d(Fabric.TAG, "Failed to parse settings JSON from " + getUrl(), e);
            Fabric.getLogger().d(Fabric.TAG, "Settings response " + str);
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest settingsRequest) {
        HashMap map = new HashMap();
        map.put(BUILD_VERSION_PARAM, settingsRequest.buildVersion);
        map.put(DISPLAY_VERSION_PARAM, settingsRequest.displayVersion);
        map.put("source", Integer.toString(settingsRequest.source));
        if (settingsRequest.iconHash != null) {
            map.put(ICON_HASH, settingsRequest.iconHash);
        }
        String str = settingsRequest.instanceId;
        if (!CommonUtils.isNullOrEmpty(str)) {
            map.put(INSTANCE_PARAM, str);
        }
        return map;
    }

    JSONObject handleResponse(HttpRequest httpRequest) {
        int iCode = httpRequest.code();
        Fabric.getLogger().d(Fabric.TAG, "Settings result was: " + iCode);
        if (requestWasSuccessful(iCode)) {
            return getJsonObjectFrom(httpRequest.body());
        }
        Fabric.getLogger().e(Fabric.TAG, "Failed to retrieve settings from " + getUrl());
        return null;
    }

    @Override // io.fabric.sdk.android.services.settings.SettingsSpiCall
    public JSONObject invoke(SettingsRequest settingsRequest) {
        JSONObject jSONObjectHandleResponse;
        HttpRequest httpRequestApplyHeadersTo = null;
        try {
            Map<String, String> queryParamsFor = getQueryParamsFor(settingsRequest);
            httpRequestApplyHeadersTo = applyHeadersTo(getHttpRequest(queryParamsFor), settingsRequest);
            Fabric.getLogger().d(Fabric.TAG, "Requesting settings from " + getUrl());
            Fabric.getLogger().d(Fabric.TAG, "Settings query params were: " + queryParamsFor);
            jSONObjectHandleResponse = handleResponse(httpRequestApplyHeadersTo);
        } catch (HttpRequest.HttpRequestException e) {
            Fabric.getLogger().e(Fabric.TAG, "Settings request failed.", e);
            jSONObjectHandleResponse = null;
        } finally {
            if (httpRequestApplyHeadersTo != null) {
                Fabric.getLogger().d(Fabric.TAG, "Settings request ID: " + httpRequestApplyHeadersTo.header(AbstractSpiCall.HEADER_REQUEST_ID));
            }
        }
        return jSONObjectHandleResponse;
    }

    boolean requestWasSuccessful(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }
}
