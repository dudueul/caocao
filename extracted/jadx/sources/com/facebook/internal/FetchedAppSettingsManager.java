package com.facebook.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import com.facebook.appevents.internal.InAppPurchaseActivityLifecycleTracker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class FetchedAppSettingsManager {
    private static final String ADVERTISER_ID_KEY = "advertiser_id";
    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final int AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 8;
    private static final int CODELESS_EVENTS_ENABLED_BITMASK_FIELD = 32;
    private static final int IAP_AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 16;
    private static final String SDK_UPDATE_MESSAGE = "sdk_update_message";
    private static final int TRACK_UNINSTALL_ENABLED_BITMASK_FIELD = 256;
    private static final String TAG = FetchedAppSettingsManager.class.getCanonicalName();
    private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    private static final String APP_SETTING_CUSTOM_TABS_ENABLED = "gdpv4_chrome_custom_tabs_enabled";
    private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    private static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
    private static final String APP_SETTING_APP_EVENTS_SESSION_TIMEOUT = "app_events_session_timeout";
    private static final String APP_SETTING_APP_EVENTS_FEATURE_BITMASK = "app_events_feature_bitmask";
    private static final String APP_SETTING_APP_EVENTS_EVENT_BINDINGS = "auto_event_mapping_android";
    private static final String APP_SETTING_APP_EVENTS_CODELESS_SETUP_ENABLED = "auto_event_setup_enabled";
    private static final String APP_SETTING_SMART_LOGIN_OPTIONS = "seamless_login";
    private static final String SMART_LOGIN_BOOKMARK_ICON_URL = "smart_login_bookmark_icon_url";
    private static final String SMART_LOGIN_MENU_ICON_URL = "smart_login_menu_icon_url";
    private static final String[] APP_SETTING_FIELDS = {APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, APP_SETTING_NUX_CONTENT, APP_SETTING_NUX_ENABLED, APP_SETTING_CUSTOM_TABS_ENABLED, APP_SETTING_DIALOG_CONFIGS, APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES, APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, APP_SETTING_APP_EVENTS_FEATURE_BITMASK, APP_SETTING_APP_EVENTS_EVENT_BINDINGS, APP_SETTING_APP_EVENTS_CODELESS_SETUP_ENABLED, APP_SETTING_SMART_LOGIN_OPTIONS, SMART_LOGIN_BOOKMARK_ICON_URL, SMART_LOGIN_MENU_ICON_URL};
    private static final Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
    private static final AtomicReference<FetchAppSettingState> loadingState = new AtomicReference<>(FetchAppSettingState.NOT_LOADED);
    private static final ConcurrentLinkedQueue<FetchedAppSettingsCallback> fetchedAppSettingsCallbacks = new ConcurrentLinkedQueue<>();
    private static boolean printedSDKUpdatedMessage = false;

    enum FetchAppSettingState {
        NOT_LOADED,
        LOADING,
        SUCCESS,
        ERROR
    }

    public interface FetchedAppSettingsCallback {
        void onError();

        void onSuccess(FetchedAppSettings fetchedAppSettings);
    }

    public static synchronized void getAppSettingsAsync(FetchedAppSettingsCallback fetchedAppSettingsCallback) {
        fetchedAppSettingsCallbacks.add(fetchedAppSettingsCallback);
        loadAppSettingsAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject getAppSettingsQueryResponse(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", TextUtils.join(",", new ArrayList(Arrays.asList(APP_SETTING_FIELDS))));
        try {
            Class.forName("com.facebook.marketing.Marketing");
            AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
            if (attributionIdentifiers != null && attributionIdentifiers.getAndroidAdvertiserId() != null) {
                bundle.putString(ADVERTISER_ID_KEY, attributionIdentifiers.getAndroidAdvertiserId());
            }
        } catch (ClassNotFoundException e) {
        }
        GraphRequest graphRequestNewGraphPathRequest = GraphRequest.newGraphPathRequest(null, str, null);
        graphRequestNewGraphPathRequest.setSkipClientToken(true);
        graphRequestNewGraphPathRequest.setParameters(bundle);
        return graphRequestNewGraphPathRequest.executeAndWait().getJSONObject();
    }

    public static FetchedAppSettings getAppSettingsWithoutQuery(String str) {
        if (str != null) {
            return fetchedAppSettings.get(str);
        }
        return null;
    }

    public static synchronized void loadAppSettingsAsync() {
        synchronized (FetchedAppSettingsManager.class) {
            final Context applicationContext = FacebookSdk.getApplicationContext();
            final String applicationId = FacebookSdk.getApplicationId();
            if (Utility.isNullOrEmpty(applicationId)) {
                loadingState.set(FetchAppSettingState.ERROR);
                pollCallbacks();
            } else if (fetchedAppSettings.containsKey(applicationId)) {
                loadingState.set(FetchAppSettingState.SUCCESS);
                pollCallbacks();
            } else {
                if (loadingState.compareAndSet(FetchAppSettingState.NOT_LOADED, FetchAppSettingState.LOADING) || loadingState.compareAndSet(FetchAppSettingState.ERROR, FetchAppSettingState.LOADING)) {
                    final String str = String.format(APP_SETTINGS_PREFS_KEY_FORMAT, applicationId);
                    FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.internal.FetchedAppSettingsManager.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(FetchedAppSettingsManager.APP_SETTINGS_PREFS_STORE, 0);
                            String string = sharedPreferences.getString(str, null);
                            FetchedAppSettings appSettingsFromJSON = null;
                            if (!Utility.isNullOrEmpty(string)) {
                                JSONObject jSONObject = null;
                                try {
                                    jSONObject = new JSONObject(string);
                                } catch (JSONException e) {
                                    Utility.logd("FacebookSDK", e);
                                }
                                if (jSONObject != null) {
                                    appSettingsFromJSON = FetchedAppSettingsManager.parseAppSettingsFromJSON(applicationId, jSONObject);
                                }
                            }
                            JSONObject appSettingsQueryResponse = FetchedAppSettingsManager.getAppSettingsQueryResponse(applicationId);
                            if (appSettingsQueryResponse != null) {
                                FetchedAppSettingsManager.parseAppSettingsFromJSON(applicationId, appSettingsQueryResponse);
                                sharedPreferences.edit().putString(str, appSettingsQueryResponse.toString()).apply();
                            }
                            if (appSettingsFromJSON != null) {
                                String sdkUpdateMessage = appSettingsFromJSON.getSdkUpdateMessage();
                                if (!FetchedAppSettingsManager.printedSDKUpdatedMessage && sdkUpdateMessage != null && sdkUpdateMessage.length() > 0) {
                                    boolean unused = FetchedAppSettingsManager.printedSDKUpdatedMessage = true;
                                    Log.w(FetchedAppSettingsManager.TAG, sdkUpdateMessage);
                                }
                            }
                            AutomaticAnalyticsLogger.logActivateAppEvent();
                            InAppPurchaseActivityLifecycleTracker.update();
                            FetchedAppSettingsManager.loadingState.set(FetchedAppSettingsManager.fetchedAppSettings.containsKey(applicationId) ? FetchAppSettingState.SUCCESS : FetchAppSettingState.ERROR);
                            FetchedAppSettingsManager.pollCallbacks();
                        }
                    });
                } else {
                    pollCallbacks();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FetchedAppSettings parseAppSettingsFromJSON(String str, JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES);
        FacebookRequestErrorClassification defaultErrorClassification = jSONArrayOptJSONArray == null ? FacebookRequestErrorClassification.getDefaultErrorClassification() : FacebookRequestErrorClassification.createFromJSON(jSONArrayOptJSONArray);
        int iOptInt = jSONObject.optInt(APP_SETTING_APP_EVENTS_FEATURE_BITMASK, 0);
        boolean z = (iOptInt & 8) != 0;
        boolean z2 = (iOptInt & 16) != 0;
        boolean z3 = (iOptInt & 32) != 0;
        boolean z4 = (iOptInt & 256) != 0;
        FetchedAppSettings fetchedAppSettings2 = new FetchedAppSettings(jSONObject.optBoolean(APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, false), jSONObject.optString(APP_SETTING_NUX_CONTENT, ""), jSONObject.optBoolean(APP_SETTING_NUX_ENABLED, false), jSONObject.optBoolean(APP_SETTING_CUSTOM_TABS_ENABLED, false), jSONObject.optInt(APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, Constants.getDefaultAppEventsSessionTimeoutInSeconds()), SmartLoginOption.parseOptions(jSONObject.optLong(APP_SETTING_SMART_LOGIN_OPTIONS)), parseDialogConfigurations(jSONObject.optJSONObject(APP_SETTING_DIALOG_CONFIGS)), z, defaultErrorClassification, jSONObject.optString(SMART_LOGIN_BOOKMARK_ICON_URL), jSONObject.optString(SMART_LOGIN_MENU_ICON_URL), z2, z3, jSONObject.optJSONArray(APP_SETTING_APP_EVENTS_EVENT_BINDINGS), jSONObject.optString(SDK_UPDATE_MESSAGE), z4, jSONObject.optBoolean(APP_SETTING_APP_EVENTS_CODELESS_SETUP_ENABLED, false));
        fetchedAppSettings.put(str, fetchedAppSettings2);
        return fetchedAppSettings2;
    }

    private static Map<String, Map<String, FetchedAppSettings.DialogFeatureConfig>> parseDialogConfigurations(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray;
        HashMap map = new HashMap();
        if (jSONObject != null && (jSONArrayOptJSONArray = jSONObject.optJSONArray("data")) != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                FetchedAppSettings.DialogFeatureConfig dialogConfig = FetchedAppSettings.DialogFeatureConfig.parseDialogConfig(jSONArrayOptJSONArray.optJSONObject(i));
                if (dialogConfig != null) {
                    String dialogName = dialogConfig.getDialogName();
                    Map map2 = (Map) map.get(dialogName);
                    if (map2 == null) {
                        map2 = new HashMap();
                        map.put(dialogName, map2);
                    }
                    map2.put(dialogConfig.getFeatureName(), dialogConfig);
                }
            }
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void pollCallbacks() {
        FetchAppSettingState fetchAppSettingState = loadingState.get();
        if (!FetchAppSettingState.NOT_LOADED.equals(fetchAppSettingState) && !FetchAppSettingState.LOADING.equals(fetchAppSettingState)) {
            final FetchedAppSettings fetchedAppSettings2 = fetchedAppSettings.get(FacebookSdk.getApplicationId());
            Handler handler = new Handler(Looper.getMainLooper());
            if (FetchAppSettingState.ERROR.equals(fetchAppSettingState)) {
                while (!fetchedAppSettingsCallbacks.isEmpty()) {
                    final FetchedAppSettingsCallback fetchedAppSettingsCallbackPoll = fetchedAppSettingsCallbacks.poll();
                    handler.post(new Runnable() { // from class: com.facebook.internal.FetchedAppSettingsManager.2
                        @Override // java.lang.Runnable
                        public void run() {
                            fetchedAppSettingsCallbackPoll.onError();
                        }
                    });
                }
            } else {
                while (!fetchedAppSettingsCallbacks.isEmpty()) {
                    final FetchedAppSettingsCallback fetchedAppSettingsCallbackPoll2 = fetchedAppSettingsCallbacks.poll();
                    handler.post(new Runnable() { // from class: com.facebook.internal.FetchedAppSettingsManager.3
                        @Override // java.lang.Runnable
                        public void run() {
                            fetchedAppSettingsCallbackPoll2.onSuccess(fetchedAppSettings2);
                        }
                    });
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0017 A[Catch: all -> 0x0039, TRY_ENTER, TryCatch #0 {, blocks: (B:5:0x0005, B:7:0x000d, B:10:0x0017, B:13:0x001f, B:15:0x002d), top: B:20:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:12:0x001d  */
    /* JADX WARN: Code duplicated, block: B:13:0x001f A[Catch: all -> 0x0039, TryCatch #0 {, blocks: (B:5:0x0005, B:7:0x000d, B:10:0x0017, B:13:0x001f, B:15:0x002d), top: B:20:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x002d A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0005, B:7:0x000d, B:10:0x0017, B:13:0x001f, B:15:0x002d), top: B:20:0x0005 }] */
    public static synchronized FetchedAppSettings queryAppSettings(String str, boolean z) {
        FetchedAppSettings fetchedAppSettings2;
        JSONObject appSettingsQueryResponse;
        if (z) {
            appSettingsQueryResponse = getAppSettingsQueryResponse(str);
            if (appSettingsQueryResponse == null) {
                fetchedAppSettings2 = null;
            } else {
                FetchedAppSettings appSettingsFromJSON = parseAppSettingsFromJSON(str, appSettingsQueryResponse);
                if (str.equals(FacebookSdk.getApplicationId())) {
                    loadingState.set(FetchAppSettingState.SUCCESS);
                    pollCallbacks();
                }
                fetchedAppSettings2 = appSettingsFromJSON;
            }
        } else if (fetchedAppSettings.containsKey(str)) {
            fetchedAppSettings2 = fetchedAppSettings.get(str);
        } else {
            appSettingsQueryResponse = getAppSettingsQueryResponse(str);
            if (appSettingsQueryResponse == null) {
                fetchedAppSettings2 = null;
            } else {
                FetchedAppSettings appSettingsFromJSON2 = parseAppSettingsFromJSON(str, appSettingsQueryResponse);
                if (str.equals(FacebookSdk.getApplicationId())) {
                    loadingState.set(FetchAppSettingState.SUCCESS);
                    pollCallbacks();
                }
                fetchedAppSettings2 = appSettingsFromJSON2;
            }
        }
        return fetchedAppSettings2;
    }
}
