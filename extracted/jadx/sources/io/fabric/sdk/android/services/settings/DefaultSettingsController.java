package io.fabric.sdk.android.services.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class DefaultSettingsController implements SettingsController {
    private static final String LOAD_ERROR_MESSAGE = "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.";
    private static final String PREFS_BUILD_INSTANCE_IDENTIFIER = "existing_instance_identifier";
    private final CachedSettingsIo cachedSettingsIo;
    private final CurrentTimeProvider currentTimeProvider;
    private final Kit kit;
    private final PreferenceStore preferenceStore;
    private final SettingsJsonTransform settingsJsonTransform;
    private final SettingsRequest settingsRequest;
    private final SettingsSpiCall settingsSpiCall;

    public DefaultSettingsController(Kit kit, SettingsRequest settingsRequest, CurrentTimeProvider currentTimeProvider, SettingsJsonTransform settingsJsonTransform, CachedSettingsIo cachedSettingsIo, SettingsSpiCall settingsSpiCall) {
        this.kit = kit;
        this.settingsRequest = settingsRequest;
        this.currentTimeProvider = currentTimeProvider;
        this.settingsJsonTransform = settingsJsonTransform;
        this.cachedSettingsIo = cachedSettingsIo;
        this.settingsSpiCall = settingsSpiCall;
        this.preferenceStore = new PreferenceStoreImpl(this.kit);
    }

    private SettingsData getCachedSettingsData(SettingsCacheBehavior settingsCacheBehavior) {
        SettingsData settingsData = null;
        try {
            if (!SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                JSONObject cachedSettings = this.cachedSettingsIo.readCachedSettings();
                if (cachedSettings != null) {
                    SettingsData settingsDataBuildFromJson = this.settingsJsonTransform.buildFromJson(this.currentTimeProvider, cachedSettings);
                    if (settingsDataBuildFromJson != null) {
                        logSettings(cachedSettings, "Loaded cached settings: ");
                        long currentTimeMillis = this.currentTimeProvider.getCurrentTimeMillis();
                        if (SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior) || !settingsDataBuildFromJson.isExpired(currentTimeMillis)) {
                            settingsData = settingsDataBuildFromJson;
                            Fabric.getLogger().d(Fabric.TAG, "Returning cached settings.");
                        } else {
                            Fabric.getLogger().d(Fabric.TAG, "Cached settings have expired.");
                        }
                    } else {
                        Fabric.getLogger().e(Fabric.TAG, "Failed to transform cached settings data.", null);
                    }
                } else {
                    Fabric.getLogger().d(Fabric.TAG, "No cached settings data found.");
                }
            }
        } catch (Exception e) {
            Fabric.getLogger().e(Fabric.TAG, "Failed to get cached settings", e);
        }
        return settingsData;
    }

    private void logSettings(JSONObject jSONObject, String str) throws JSONException {
        Fabric.getLogger().d(Fabric.TAG, str + jSONObject.toString());
    }

    boolean buildInstanceIdentifierChanged() {
        return !getStoredBuildInstanceIdentifier().equals(getBuildInstanceIdentifierFromContext());
    }

    String getBuildInstanceIdentifierFromContext() {
        return CommonUtils.createInstanceIdFrom(CommonUtils.resolveBuildId(this.kit.getContext()));
    }

    String getStoredBuildInstanceIdentifier() {
        return this.preferenceStore.get().getString(PREFS_BUILD_INSTANCE_IDENTIFIER, "");
    }

    @Override // io.fabric.sdk.android.services.settings.SettingsController
    public SettingsData loadSettingsData() {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE);
    }

    @Override // io.fabric.sdk.android.services.settings.SettingsController
    public SettingsData loadSettingsData(SettingsCacheBehavior settingsCacheBehavior) {
        JSONObject jSONObjectInvoke;
        SettingsData settingsDataBuildFromJson = null;
        try {
            if (!Fabric.isDebuggable() && !buildInstanceIdentifierChanged()) {
                settingsDataBuildFromJson = getCachedSettingsData(settingsCacheBehavior);
            }
            if (settingsDataBuildFromJson == null && (jSONObjectInvoke = this.settingsSpiCall.invoke(this.settingsRequest)) != null) {
                settingsDataBuildFromJson = this.settingsJsonTransform.buildFromJson(this.currentTimeProvider, jSONObjectInvoke);
                this.cachedSettingsIo.writeCachedSettings(settingsDataBuildFromJson.expiresAtMillis, jSONObjectInvoke);
                logSettings(jSONObjectInvoke, "Loaded settings: ");
                setStoredBuildInstanceIdentifier(getBuildInstanceIdentifierFromContext());
            }
            return settingsDataBuildFromJson == null ? getCachedSettingsData(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION) : settingsDataBuildFromJson;
        } catch (Exception e) {
            Fabric.getLogger().e(Fabric.TAG, LOAD_ERROR_MESSAGE, e);
            return null;
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    boolean setStoredBuildInstanceIdentifier(String str) {
        SharedPreferences.Editor editorEdit = this.preferenceStore.edit();
        editorEdit.putString(PREFS_BUILD_INSTANCE_IDENTIFIER, str);
        return this.preferenceStore.save(editorEdit);
    }
}
