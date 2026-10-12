package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.cache.MemoryValueCache;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeviceIdentifierProvider;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.BetaSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Beta extends Kit<Boolean> implements DeviceIdentifierProvider {
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String CRASHLYTICS_BUILD_PROPERTIES = "crashlytics-build.properties";
    static final String NO_DEVICE_TOKEN = "";
    public static final String TAG = "Beta";
    private final MemoryValueCache<String> deviceTokenCache = new MemoryValueCache<>();
    private final DeviceTokenLoader deviceTokenLoader = new DeviceTokenLoader();
    private UpdatesController updatesController;

    private String getBetaDeviceToken(Context context, String str) {
        String str2 = null;
        try {
            String str3 = this.deviceTokenCache.get(context, this.deviceTokenLoader);
            str2 = "".equals(str3) ? null : str3;
        } catch (Exception e) {
            Fabric.getLogger().e(TAG, "Failed to load the Beta device token", e);
        }
        Fabric.getLogger().d(TAG, "Beta device token present: " + (!TextUtils.isEmpty(str2)));
        return str2;
    }

    private BetaSettingsData getBetaSettingsData() {
        SettingsData settingsDataAwaitSettingsData = Settings.getInstance().awaitSettingsData();
        if (settingsDataAwaitSettingsData != null) {
            return settingsDataAwaitSettingsData.betaSettingsData;
        }
        return null;
    }

    public static Beta getInstance() {
        return (Beta) Fabric.getKit(Beta.class);
    }

    private BuildProperties loadBuildProperties(Context context) {
        InputStream inputStreamOpen = null;
        BuildProperties buildPropertiesFromPropertiesStream = null;
        try {
            try {
                inputStreamOpen = context.getAssets().open(CRASHLYTICS_BUILD_PROPERTIES);
                if (inputStreamOpen != null) {
                    buildPropertiesFromPropertiesStream = BuildProperties.fromPropertiesStream(inputStreamOpen);
                    Fabric.getLogger().d(TAG, buildPropertiesFromPropertiesStream.packageName + " build properties: " + buildPropertiesFromPropertiesStream.versionName + " (" + buildPropertiesFromPropertiesStream.versionCode + ") - " + buildPropertiesFromPropertiesStream.buildId);
                }
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException e) {
                        Fabric.getLogger().e(TAG, "Error closing Beta build properties asset", e);
                    }
                }
            } catch (Throwable th) {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException e2) {
                        Fabric.getLogger().e(TAG, "Error closing Beta build properties asset", e2);
                    }
                }
                throw th;
            }
        } catch (Exception e3) {
            Fabric.getLogger().e(TAG, "Error reading Beta build properties", e3);
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (IOException e4) {
                    Fabric.getLogger().e(TAG, "Error closing Beta build properties asset", e4);
                }
            }
        }
        return buildPropertiesFromPropertiesStream;
    }

    boolean canCheckForUpdates(BetaSettingsData betaSettingsData, BuildProperties buildProperties) {
        return (betaSettingsData == null || TextUtils.isEmpty(betaSettingsData.updateUrl) || buildProperties == null) ? false : true;
    }

    @TargetApi(14)
    UpdatesController createUpdatesController(int i, Application application) {
        return i >= 14 ? new ActivityLifecycleCheckForUpdatesController(getFabric().getActivityLifecycleManager(), getFabric().getExecutorService()) : new ImmediateCheckForUpdatesController();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.fabric.sdk.android.Kit
    public Boolean doInBackground() {
        Fabric.getLogger().d(TAG, "Beta kit initializing...");
        Context context = getContext();
        IdManager idManager = getIdManager();
        if (TextUtils.isEmpty(getBetaDeviceToken(context, idManager.getInstallerPackageName()))) {
            Fabric.getLogger().d(TAG, "A Beta device token was not found for this app");
            return false;
        }
        Fabric.getLogger().d(TAG, "Beta device token is present, checking for app updates.");
        BetaSettingsData betaSettingsData = getBetaSettingsData();
        BuildProperties buildPropertiesLoadBuildProperties = loadBuildProperties(context);
        if (canCheckForUpdates(betaSettingsData, buildPropertiesLoadBuildProperties)) {
            this.updatesController.initialize(context, this, idManager, betaSettingsData, buildPropertiesLoadBuildProperties, new PreferenceStoreImpl(this), new SystemCurrentTimeProvider(), new DefaultHttpRequestFactory(Fabric.getLogger()));
        }
        return true;
    }

    @Override // io.fabric.sdk.android.services.common.DeviceIdentifierProvider
    public Map<IdManager.DeviceIdentifierType, String> getDeviceIdentifiers() {
        String betaDeviceToken = getBetaDeviceToken(getContext(), getIdManager().getInstallerPackageName());
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(betaDeviceToken)) {
            map.put(IdManager.DeviceIdentifierType.FONT_TOKEN, betaDeviceToken);
        }
        return map;
    }

    @Override // io.fabric.sdk.android.Kit
    public String getIdentifier() {
        return "com.crashlytics.sdk.android:beta";
    }

    String getOverridenSpiEndpoint() {
        return CommonUtils.getStringsFileValue(getContext(), CRASHLYTICS_API_ENDPOINT);
    }

    @Override // io.fabric.sdk.android.Kit
    public String getVersion() {
        return "1.2.5.dev";
    }

    @Override // io.fabric.sdk.android.Kit
    @TargetApi(14)
    protected boolean onPreExecute() {
        this.updatesController = createUpdatesController(Build.VERSION.SDK_INT, (Application) getContext().getApplicationContext());
        return true;
    }
}
