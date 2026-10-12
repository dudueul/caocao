package com.facebook.marketing;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.tapjoy.TapjoyConstants;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FacebookUninstallTracker {
    private static final String SUCCESS = "success";
    private static final String TAG = FacebookUninstallTracker.class.getCanonicalName();
    private static final String UPLOADED_TOKEN_STORE = "com.facebook.marketing.FacebookUninstallTracker.UPLOADED_TOKEN";
    private static final SharedPreferences uploadedTokenSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(UPLOADED_TOKEN_STORE, 0);

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static GraphRequest buildPushDeviceTokenRequest(String str, String str2, AccessToken accessToken) {
        GraphRequest graphRequestNewPostRequest = GraphRequest.newPostRequest(accessToken, String.format(Locale.US, "%s/app_push_device_token", str), null, null);
        AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
        if (attributionIdentifiers == null) {
            return null;
        }
        String androidAdvertiserId = attributionIdentifiers.getAndroidAdvertiserId();
        Bundle parameters = graphRequestNewPostRequest.getParameters();
        if (parameters == null) {
            parameters = new Bundle();
        }
        parameters.putString("device_id", androidAdvertiserId);
        parameters.putString("device_token", str2);
        parameters.putString(TapjoyConstants.TJC_PLATFORM, "android");
        graphRequestNewPostRequest.setParameters(parameters);
        return graphRequestNewPostRequest;
    }

    private static void sendToServer(final String str) {
        FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.marketing.FacebookUninstallTracker.1
            @Override // java.lang.Runnable
            public void run() {
                GraphRequest graphRequestBuildPushDeviceTokenRequest = FacebookUninstallTracker.buildPushDeviceTokenRequest(FacebookSdk.getApplicationId(), str, null);
                if (graphRequestBuildPushDeviceTokenRequest != null) {
                    GraphResponse graphResponseExecuteAndWait = graphRequestBuildPushDeviceTokenRequest.executeAndWait();
                    try {
                        JSONObject jSONObject = graphResponseExecuteAndWait.getJSONObject();
                        if (jSONObject != null) {
                            if (jSONObject.has("success") && jSONObject.getString("success").equals("true")) {
                                SharedPreferences.Editor editorEdit = FacebookUninstallTracker.uploadedTokenSharedPrefs.edit();
                                editorEdit.putString("uploaded_token", str);
                                editorEdit.putBoolean("pre_track_uninstall_enabled", true);
                                editorEdit.apply();
                            } else {
                                Log.e(FacebookUninstallTracker.TAG, "Error sending device token to Facebook: " + graphResponseExecuteAndWait.getError());
                            }
                        }
                    } catch (JSONException e) {
                        Log.e(FacebookUninstallTracker.TAG, "Error decoding server response.", e);
                    }
                }
            }
        });
    }

    public static void updateDeviceToken(String str) {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if (appSettingsWithoutQuery == null) {
            return;
        }
        boolean trackUninstallEnabled = appSettingsWithoutQuery.getTrackUninstallEnabled();
        String string = uploadedTokenSharedPrefs.getString("uploaded_token", null);
        boolean z = uploadedTokenSharedPrefs.getBoolean("pre_track_uninstall_enabled", false);
        if (trackUninstallEnabled && (!z || !str.equals(string))) {
            sendToServer(str);
        } else {
            if (trackUninstallEnabled || !z) {
                return;
            }
            SharedPreferences.Editor editorEdit = uploadedTokenSharedPrefs.edit();
            editorEdit.putBoolean("pre_track_uninstall_enabled", false);
            editorEdit.apply();
        }
    }
}
