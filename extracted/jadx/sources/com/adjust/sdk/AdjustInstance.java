package com.adjust.sdk;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AdjustInstance {
    private IActivityHandler activityHandler;
    private String basePath;
    private List<IRunActivityHandler> preLaunchActionsArray;
    private String pushToken;
    private Boolean startEnabled = null;
    private boolean startOffline = false;

    private boolean checkActivityHandler() {
        return checkActivityHandler(null);
    }

    private boolean checkActivityHandler(String str) {
        if (this.activityHandler != null) {
            return true;
        }
        if (str != null) {
            AdjustFactory.getLogger().warn("Adjust not initialized, but %s saved for launch", str);
            return false;
        }
        AdjustFactory.getLogger().error("Adjust not initialized correctly", new Object[0]);
        return false;
    }

    private boolean checkActivityHandler(boolean z, String str, String str2) {
        return z ? checkActivityHandler(str) : checkActivityHandler(str2);
    }

    private boolean isInstanceEnabled() {
        return this.startEnabled == null || this.startEnabled.booleanValue();
    }

    private void savePushToken(final String str, final Context context) {
        Util.runInBackground(new Runnable() { // from class: com.adjust.sdk.AdjustInstance.8
            @Override // java.lang.Runnable
            public void run() {
                new SharedPreferencesManager(context).savePushToken(str);
            }
        });
    }

    private void saveRawReferrer(final String str, final long j, final Context context) {
        Util.runInBackground(new Runnable() { // from class: com.adjust.sdk.AdjustInstance.7
            @Override // java.lang.Runnable
            public void run() {
                new SharedPreferencesManager(context).saveRawReferrer(str, j);
            }
        });
    }

    private void setSendingReferrersAsNotSent(final Context context) {
        Util.runInBackground(new Runnable() { // from class: com.adjust.sdk.AdjustInstance.9
            @Override // java.lang.Runnable
            public void run() {
                new SharedPreferencesManager(context).setSendingReferrersAsNotSent();
            }
        });
    }

    public void addSessionCallbackParameter(final String str, final String str2) {
        if (checkActivityHandler("adding session callback parameter")) {
            this.activityHandler.addSessionCallbackParameter(str, str2);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() { // from class: com.adjust.sdk.AdjustInstance.1
            @Override // com.adjust.sdk.IRunActivityHandler
            public void run(ActivityHandler activityHandler) {
                activityHandler.addSessionCallbackParameterI(str, str2);
            }
        });
    }

    public void addSessionPartnerParameter(final String str, final String str2) {
        if (checkActivityHandler("adding session partner parameter")) {
            this.activityHandler.addSessionPartnerParameter(str, str2);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() { // from class: com.adjust.sdk.AdjustInstance.2
            @Override // com.adjust.sdk.IRunActivityHandler
            public void run(ActivityHandler activityHandler) {
                activityHandler.addSessionPartnerParameterI(str, str2);
            }
        });
    }

    public void appWillOpenUrl(Uri uri) {
        if (checkActivityHandler()) {
            this.activityHandler.readOpenUrl(uri, System.currentTimeMillis());
        }
    }

    public String getAdid() {
        if (checkActivityHandler()) {
            return this.activityHandler.getAdid();
        }
        return null;
    }

    public AdjustAttribution getAttribution() {
        if (checkActivityHandler()) {
            return this.activityHandler.getAttribution();
        }
        return null;
    }

    public boolean isEnabled() {
        return !checkActivityHandler() ? isInstanceEnabled() : this.activityHandler.isEnabled();
    }

    public void onCreate(AdjustConfig adjustConfig) {
        if (adjustConfig == null) {
            AdjustFactory.getLogger().error("AdjustConfig missing", new Object[0]);
            return;
        }
        if (!adjustConfig.isValid()) {
            AdjustFactory.getLogger().error("AdjustConfig not initialized correctly", new Object[0]);
            return;
        }
        if (this.activityHandler != null) {
            AdjustFactory.getLogger().error("Adjust already initialized", new Object[0]);
            return;
        }
        adjustConfig.preLaunchActionsArray = this.preLaunchActionsArray;
        adjustConfig.pushToken = this.pushToken;
        adjustConfig.startEnabled = this.startEnabled;
        adjustConfig.startOffline = this.startOffline;
        adjustConfig.basePath = this.basePath;
        this.activityHandler = AdjustFactory.getActivityHandler(adjustConfig);
        setSendingReferrersAsNotSent(adjustConfig.context);
    }

    public void onPause() {
        if (checkActivityHandler()) {
            this.activityHandler.onPause();
        }
    }

    public void onResume() {
        if (checkActivityHandler()) {
            this.activityHandler.onResume();
        }
    }

    public void removeSessionCallbackParameter(final String str) {
        if (checkActivityHandler("removing session callback parameter")) {
            this.activityHandler.removeSessionCallbackParameter(str);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() { // from class: com.adjust.sdk.AdjustInstance.3
            @Override // com.adjust.sdk.IRunActivityHandler
            public void run(ActivityHandler activityHandler) {
                activityHandler.removeSessionCallbackParameterI(str);
            }
        });
    }

    public void removeSessionPartnerParameter(final String str) {
        if (checkActivityHandler("removing session partner parameter")) {
            this.activityHandler.removeSessionPartnerParameter(str);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() { // from class: com.adjust.sdk.AdjustInstance.4
            @Override // com.adjust.sdk.IRunActivityHandler
            public void run(ActivityHandler activityHandler) {
                activityHandler.removeSessionPartnerParameterI(str);
            }
        });
    }

    public void resetSessionCallbackParameters() {
        if (checkActivityHandler("resetting session callback parameters")) {
            this.activityHandler.resetSessionCallbackParameters();
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() { // from class: com.adjust.sdk.AdjustInstance.5
            @Override // com.adjust.sdk.IRunActivityHandler
            public void run(ActivityHandler activityHandler) {
                activityHandler.resetSessionCallbackParametersI();
            }
        });
    }

    public void resetSessionPartnerParameters() {
        if (checkActivityHandler("resetting session partner parameters")) {
            this.activityHandler.resetSessionPartnerParameters();
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() { // from class: com.adjust.sdk.AdjustInstance.6
            @Override // com.adjust.sdk.IRunActivityHandler
            public void run(ActivityHandler activityHandler) {
                activityHandler.resetSessionPartnerParametersI();
            }
        });
    }

    public void sendFirstPackages() {
        if (checkActivityHandler()) {
            this.activityHandler.sendFirstPackages();
        }
    }

    public void sendReferrer(String str, Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (str == null || str.length() == 0) {
            return;
        }
        saveRawReferrer(str, jCurrentTimeMillis, context);
        if (checkActivityHandler("referrer") && this.activityHandler.isEnabled()) {
            this.activityHandler.sendReftagReferrer();
        }
    }

    public void setEnabled(boolean z) {
        this.startEnabled = Boolean.valueOf(z);
        if (checkActivityHandler(z, "enabled mode", "disabled mode")) {
            this.activityHandler.setEnabled(z);
        }
    }

    public void setOfflineMode(boolean z) {
        if (checkActivityHandler(z, "offline mode", "online mode")) {
            this.activityHandler.setOfflineMode(z);
        } else {
            this.startOffline = z;
        }
    }

    public void setPushToken(String str) {
        if (checkActivityHandler("push token")) {
            this.activityHandler.setPushToken(str, false);
        } else {
            this.pushToken = str;
        }
    }

    public void setPushToken(String str, Context context) {
        savePushToken(str, context);
        if (checkActivityHandler("push token") && this.activityHandler.isEnabled()) {
            this.activityHandler.setPushToken(str, true);
        }
    }

    public void setTestOptions(AdjustTestOptions adjustTestOptions) {
        if (adjustTestOptions.basePath != null) {
            this.basePath = adjustTestOptions.basePath;
        }
        if (adjustTestOptions.baseUrl != null) {
            AdjustFactory.setBaseUrl(adjustTestOptions.baseUrl);
        }
        if (adjustTestOptions.useTestConnectionOptions != null && adjustTestOptions.useTestConnectionOptions.booleanValue()) {
            AdjustFactory.useTestConnectionOptions();
        }
        if (adjustTestOptions.timerIntervalInMilliseconds != null) {
            AdjustFactory.setTimerInterval(adjustTestOptions.timerIntervalInMilliseconds.longValue());
        }
        if (adjustTestOptions.timerStartInMilliseconds != null) {
            AdjustFactory.setTimerStart(adjustTestOptions.timerIntervalInMilliseconds.longValue());
        }
        if (adjustTestOptions.sessionIntervalInMilliseconds != null) {
            AdjustFactory.setSessionInterval(adjustTestOptions.sessionIntervalInMilliseconds.longValue());
        }
        if (adjustTestOptions.subsessionIntervalInMilliseconds != null) {
            AdjustFactory.setSubsessionInterval(adjustTestOptions.subsessionIntervalInMilliseconds.longValue());
        }
        if (adjustTestOptions.tryInstallReferrer != null) {
            AdjustFactory.setTryInstallReferrer(adjustTestOptions.tryInstallReferrer.booleanValue());
        }
    }

    public void teardown() {
        if (checkActivityHandler()) {
            this.activityHandler.teardown();
            this.activityHandler = null;
        }
    }

    public void trackEvent(AdjustEvent adjustEvent) {
        if (checkActivityHandler()) {
            this.activityHandler.trackEvent(adjustEvent);
        }
    }
}
