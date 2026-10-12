package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ApiKey {
    static final String CRASHLYTICS_API_KEY = "com.crashlytics.ApiKey";
    static final String FABRIC_API_KEY = "io.fabric.ApiKey";

    @Deprecated
    public static String getApiKey(Context context) {
        Fabric.getLogger().w(Fabric.TAG, "getApiKey(context) is deprecated, please upgrade kit(s) to the latest version.");
        return new ApiKey().getValue(context);
    }

    @Deprecated
    public static String getApiKey(Context context, boolean z) {
        Fabric.getLogger().w(Fabric.TAG, "getApiKey(context, debug) is deprecated, please upgrade kit(s) to the latest version.");
        return new ApiKey().getValue(context);
    }

    protected String buildApiKeyInstructions() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }

    protected String getApiKeyFromManifest(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return null;
            }
            String string = bundle.getString(FABRIC_API_KEY);
            if (string != null) {
                return string;
            }
            Fabric.getLogger().d(Fabric.TAG, "Falling back to Crashlytics key lookup from Manifest");
            return bundle.getString(CRASHLYTICS_API_KEY);
        } catch (Exception e) {
            Fabric.getLogger().d(Fabric.TAG, "Caught non-fatal exception while retrieving apiKey: " + e);
            return null;
        }
    }

    protected String getApiKeyFromStrings(Context context) {
        int resourcesIdentifier = CommonUtils.getResourcesIdentifier(context, FABRIC_API_KEY, "string");
        if (resourcesIdentifier == 0) {
            Fabric.getLogger().d(Fabric.TAG, "Falling back to Crashlytics key lookup from Strings");
            resourcesIdentifier = CommonUtils.getResourcesIdentifier(context, CRASHLYTICS_API_KEY, "string");
        }
        if (resourcesIdentifier != 0) {
            return context.getResources().getString(resourcesIdentifier);
        }
        return null;
    }

    public String getValue(Context context) {
        String apiKeyFromManifest = getApiKeyFromManifest(context);
        if (TextUtils.isEmpty(apiKeyFromManifest)) {
            apiKeyFromManifest = getApiKeyFromStrings(context);
        }
        if (TextUtils.isEmpty(apiKeyFromManifest)) {
            logErrorOrThrowException(context);
        }
        return apiKeyFromManifest;
    }

    protected void logErrorOrThrowException(Context context) {
        if (Fabric.isDebuggable() || CommonUtils.isAppDebuggable(context)) {
            throw new IllegalArgumentException(buildApiKeyInstructions());
        }
        Fabric.getLogger().e(Fabric.TAG, buildApiKeyInstructions());
    }
}
