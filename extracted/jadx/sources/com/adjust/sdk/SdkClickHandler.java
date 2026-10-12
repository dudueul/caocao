package com.adjust.sdk;

import com.facebook.share.internal.ShareConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SdkClickHandler implements ISdkClickHandler {
    private static final double MILLISECONDS_TO_SECONDS_DIVISOR = 1000.0d;
    private static final String SCHEDULED_EXECUTOR_SOURCE = "SdkClickHandler";
    private static final String SOURCE_INSTALL_REFERRER = "install_referrer";
    private static final String SOURCE_REFTAG = "reftag";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private BackoffStrategy backoffStrategy;
    private String basePath;
    private ILogger logger;
    private List<ActivityPackage> packageQueue;
    private boolean paused;
    private CustomScheduledExecutor scheduledExecutor;

    public SdkClickHandler(IActivityHandler iActivityHandler, boolean z) {
        init(iActivityHandler, z);
        this.logger = AdjustFactory.getLogger();
        this.backoffStrategy = AdjustFactory.getSdkClickBackoffStrategy();
        this.scheduledExecutor = new CustomScheduledExecutor(SCHEDULED_EXECUTOR_SOURCE, false);
    }

    private void logErrorMessageI(ActivityPackage activityPackage, String str, Throwable th) {
        this.logger.error(Util.formatString("%s. (%s)", activityPackage.getFailureMessage(), Util.getReasonString(str, th)), new Object[0]);
    }

    private void retrySendingI(ActivityPackage activityPackage) {
        this.logger.error("Retrying sdk_click package for the %d time", Integer.valueOf(activityPackage.increaseRetries()));
        sendSdkClick(activityPackage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNextSdkClick() {
        this.scheduledExecutor.submit(new Runnable() { // from class: com.adjust.sdk.SdkClickHandler.3
            @Override // java.lang.Runnable
            public void run() {
                SdkClickHandler.this.sendNextSdkClickI();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNextSdkClickI() {
        if (this.paused || this.packageQueue.isEmpty()) {
            return;
        }
        final ActivityPackage activityPackageRemove = this.packageQueue.remove(0);
        int retries = activityPackageRemove.getRetries();
        Runnable runnable = new Runnable() { // from class: com.adjust.sdk.SdkClickHandler.4
            @Override // java.lang.Runnable
            public void run() {
                SdkClickHandler.this.sendSdkClickI(activityPackageRemove);
                SdkClickHandler.this.sendNextSdkClick();
            }
        };
        if (retries <= 0) {
            runnable.run();
            return;
        }
        long waitingTime = Util.getWaitingTime(retries, this.backoffStrategy);
        this.logger.verbose("Waiting for %s seconds before retrying sdk_click for the %d time", Util.SecondsDisplayFormat.format(waitingTime / MILLISECONDS_TO_SECONDS_DIVISOR), Integer.valueOf(retries));
        this.scheduledExecutor.schedule(runnable, waitingTime, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSdkClickI(ActivityPackage activityPackage) {
        IActivityHandler iActivityHandler = this.activityHandlerWeakRef.get();
        String str = activityPackage.getParameters().get(ShareConstants.FEED_SOURCE_PARAM);
        boolean z = str != null && str.equals("reftag");
        String str2 = activityPackage.getParameters().get("raw_referrer");
        if (z && new SharedPreferencesManager(iActivityHandler.getContext()).getRawReferrer(str2, activityPackage.getClickTimeInMilliseconds()) == null) {
            return;
        }
        boolean z2 = str != null && str.equals("install_referrer");
        long clickTimeInSeconds = -1;
        long installBeginTimeInSeconds = -1;
        String str3 = null;
        if (z2) {
            clickTimeInSeconds = activityPackage.getClickTimeInSeconds();
            installBeginTimeInSeconds = activityPackage.getInstallBeginTimeInSeconds();
            str3 = activityPackage.getParameters().get("referrer");
        }
        String baseUrl = AdjustFactory.getBaseUrl();
        if (this.basePath != null) {
            baseUrl = baseUrl + this.basePath;
        }
        try {
            SdkClickResponseData sdkClickResponseData = (SdkClickResponseData) UtilNetworking.createPOSTHttpsURLConnection(baseUrl + activityPackage.getPath(), activityPackage, this.packageQueue.size() - 1);
            if (sdkClickResponseData.jsonResponse == null) {
                retrySendingI(activityPackage);
                return;
            }
            if (iActivityHandler != null) {
                if (z) {
                    new SharedPreferencesManager(iActivityHandler.getContext()).removeRawReferrer(str2, activityPackage.getClickTimeInMilliseconds());
                }
                if (z2) {
                    sdkClickResponseData.clickTime = clickTimeInSeconds;
                    sdkClickResponseData.installBegin = installBeginTimeInSeconds;
                    sdkClickResponseData.installReferrer = str3;
                    sdkClickResponseData.isInstallReferrer = true;
                }
                iActivityHandler.finishedTrackingActivity(sdkClickResponseData);
            }
        } catch (UnsupportedEncodingException e) {
            logErrorMessageI(activityPackage, "Sdk_click failed to encode parameters", e);
        } catch (SocketTimeoutException e2) {
            logErrorMessageI(activityPackage, "Sdk_click request timed out. Will retry later", e2);
            retrySendingI(activityPackage);
        } catch (IOException e3) {
            logErrorMessageI(activityPackage, "Sdk_click request failed. Will retry later", e3);
            retrySendingI(activityPackage);
        } catch (Throwable th) {
            logErrorMessageI(activityPackage, "Sdk_click runtime exception", th);
        }
    }

    @Override // com.adjust.sdk.ISdkClickHandler
    public void init(IActivityHandler iActivityHandler, boolean z) {
        this.paused = !z;
        this.packageQueue = new ArrayList();
        this.activityHandlerWeakRef = new WeakReference<>(iActivityHandler);
        this.basePath = iActivityHandler.getBasePath();
    }

    @Override // com.adjust.sdk.ISdkClickHandler
    public void pauseSending() {
        this.paused = true;
    }

    @Override // com.adjust.sdk.ISdkClickHandler
    public void resumeSending() {
        this.paused = false;
        sendNextSdkClick();
    }

    @Override // com.adjust.sdk.ISdkClickHandler
    public void sendReftagReferrers() {
        this.scheduledExecutor.submit(new Runnable() { // from class: com.adjust.sdk.SdkClickHandler.2
            @Override // java.lang.Runnable
            public void run() {
                IActivityHandler iActivityHandler = (IActivityHandler) SdkClickHandler.this.activityHandlerWeakRef.get();
                SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(iActivityHandler.getContext());
                try {
                    JSONArray rawReferrerArray = sharedPreferencesManager.getRawReferrerArray();
                    boolean z = false;
                    for (int i = 0; i < rawReferrerArray.length(); i++) {
                        JSONArray jSONArray = rawReferrerArray.getJSONArray(i);
                        if (jSONArray.optInt(2, -1) == 0) {
                            String strOptString = jSONArray.optString(0, null);
                            long jOptLong = jSONArray.optLong(1, -1L);
                            jSONArray.put(2, 1);
                            z = true;
                            SdkClickHandler.this.sendSdkClick(PackageFactory.buildReftagSdkClickPackage(strOptString, jOptLong, iActivityHandler.getActivityState(), iActivityHandler.getAdjustConfig(), iActivityHandler.getDeviceInfo(), iActivityHandler.getSessionParameters()));
                        }
                    }
                    if (z) {
                        sharedPreferencesManager.saveRawReferrerArray(rawReferrerArray);
                    }
                } catch (JSONException e) {
                    SdkClickHandler.this.logger.error("Send saved raw referrers error (%s)", e.getMessage());
                }
            }
        });
    }

    @Override // com.adjust.sdk.ISdkClickHandler
    public void sendSdkClick(final ActivityPackage activityPackage) {
        this.scheduledExecutor.submit(new Runnable() { // from class: com.adjust.sdk.SdkClickHandler.1
            @Override // java.lang.Runnable
            public void run() {
                SdkClickHandler.this.packageQueue.add(activityPackage);
                SdkClickHandler.this.logger.debug("Added sdk_click %d", Integer.valueOf(SdkClickHandler.this.packageQueue.size()));
                SdkClickHandler.this.logger.verbose("%s", activityPackage.getExtendedString());
                SdkClickHandler.this.sendNextSdkClick();
            }
        });
    }

    @Override // com.adjust.sdk.ISdkClickHandler
    public void teardown() {
        this.logger.verbose("SdkClickHandler teardown", new Object[0]);
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.packageQueue != null) {
            this.packageQueue.clear();
        }
        if (this.activityHandlerWeakRef != null) {
            this.activityHandlerWeakRef.clear();
        }
        this.logger = null;
        this.packageQueue = null;
        this.backoffStrategy = null;
        this.scheduledExecutor = null;
    }
}
