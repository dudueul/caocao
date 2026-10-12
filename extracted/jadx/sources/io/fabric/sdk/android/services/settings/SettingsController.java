package io.fabric.sdk.android.services.settings;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface SettingsController {
    SettingsData loadSettingsData();

    SettingsData loadSettingsData(SettingsCacheBehavior settingsCacheBehavior);
}
